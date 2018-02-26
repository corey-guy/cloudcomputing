package edu.bu.cs755;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sound.sampled.Line;

import org.apache.log4j.PropertyConfigurator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class Main {
	static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	
	 public static String extractText(String line){
	        
		 return "";
	 }

	public static void main(String[] args) {

		// Configure the log4j
		PropertyConfigurator.configure("log4j.properties");

		String local_data_folder_relative_path = "../data/";
		String bucket_name= "metcs755";
		String key_name = local_data_folder_relative_path + "taxi-data-sorted-small.csv"; 
		
		
		//AmazonS3 s3Client = AmazonS3Client.builder().withRegion("us-east-1").build();
		 
		try {
			//S3Object s3object = s3Client.getObject(bucket_name, key_name);
			//S3ObjectInputStream s3is = s3object.getObjectContent();
			
//          Some s3 file metadata printouts 			
//		    System.out.println(s3object.getObjectMetadata().getContentType());
//		    System.out.println(s3object.getObjectMetadata().getContentLength());

		    //BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
		    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(key_name)));
		    
		    //Data Validation
		    List<TaxiReport> taxiReports =reader.lines().map(mapToItem).collect(Collectors.toList());
		    
		    //Data Map
		    List<TimeAndErrorTuple> timeAndErrorTuples = 
		    		taxiReports.stream()
		    			.filter( tr -> tr.getGPSError() == true)
		    			.map(reportToTimeAndErrorTuple)
		    			.collect(Collectors.toList());
		    
		    //Data Shuffle
		    Map<Integer, List<TimeAndErrorTuple>> shuffle = timeAndErrorTuples.stream()
		    		.collect(Collectors.groupingBy(TimeAndErrorTuple::getHour));
		    
		    //Data Reduce without streams
		    //for(int i = 0; i < 24; i++) {	
		    	//	System.out.println("Hour " + i + " :" + shuffle.get(new Integer(i)).size());
		    //}
		    
		    //Data Reduce the Correct Way
		    Map<Integer, Integer> taskOneResult = shuffle.entrySet().stream()
		    		.collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().size()));
		    
		    taskOneResult.entrySet().stream().forEach(s -> System.out.println("Real Hour " + s.getKey() + ": " + s.getValue()));

		    
		    reader.close();
			//s3is.close();
						
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

	}
	public static Function<TimeAndErrorTuple, Integer> TupleToOne = (line) -> {
		  return new Integer(1);
	};
	
	public static Function<TimeAndErrorTuple, Integer> TupleToMapKey = (line) -> {
		  return line.getHourAsInteger();
	};
	
	public static Function<String, TaxiReport> mapToItem = (line) -> {
		  TaxiReport taxiReport = new TaxiReport();
		  String[] values = line.split(",");// a CSV has comma separated lines
		  if(values.length != 17) {
			System.out.println("Bad Line");
			return taxiReport;
		  }
		  else {
			  taxiReport.setMedallion(values[0]);
			  taxiReport.setDropoffLongitude(values[8]);
			  taxiReport.setFairAmount(values[11]);
			  taxiReport.setHackLicense(values[1]);
			  taxiReport.setPickupDateTime(new DateTime(formatter.parseDateTime(values[2])));
			  taxiReport.setPickupLatitude(values[6]);
			  taxiReport.setPickupLongitude(values[7]);
			  taxiReport.setDropoffLatitude(values[9]);
			  taxiReport.setDropoffDateTime(new DateTime(formatter.parseDateTime(values[3])));
			  taxiReport.setPaymentType(values[10]);
			  taxiReport.setSurchage(values[12]);
			  taxiReport.setTipAmount(values[13]);
			  taxiReport.setTollsAmount(values[14]);
			  taxiReport.setTotalAmount(values[15]);
			  taxiReport.setTripDistance(values[5]);
			  taxiReport.setTripTime(values[4]);
			  return taxiReport;
		  }
	};
	
	public static Function<TaxiReport, TimeAndErrorTuple> reportToTimeAndErrorTuple = (line) -> {
		  TimeAndErrorTuple timeAndErrorTuple = new TimeAndErrorTuple(line.getPickupDateTime().getHourOfDay(), line.getGPSError() ? 1 : 0);
		  return timeAndErrorTuple;
	};
}
