package edu.bu.cs755;

import org.joda.time.DateTime;

public class TaxiReport {
	private String ZERO_CORD = "0.000000";
	private String medallion;
	private String hackLicense;
	private DateTime pickupDateTime;
	private DateTime dropoffDateTime;
	private String tripTime;
	private String tripDistance;
	private String pickupLongitude;
	private String pickupLatitude;
	private String dropoffLongitude;
	private String dropoffLatitude;
	private String paymentType;
	private String fairAmount;
	private String surchage;
	private String tipAmount;
	private String tollsAmount;
	private String totalAmount;
	private Boolean GPSError = false;
	public Boolean getGPSError() {
		return GPSError;
	}

	public TaxiReport() {
		
	}
	
	@Override
	public String toString() {
		return "TaxiReport [medallion=" + medallion + ", hackLicense=" + hackLicense + ", pickupDateTime="
				+ pickupDateTime + ", dropoffDateTime=" + dropoffDateTime + ", tripTime=" + tripTime + ", tripDistance="
				+ tripDistance + ", pickupLongitude=" + pickupLongitude + ", pickupLatitude=" + pickupLatitude
				+ ", dropoffLongitude=" + dropoffLongitude + ", dropoffLatitude=" + dropoffLatitude + ", paymentType="
				+ paymentType + ", fairAmount=" + fairAmount + ", surchage=" + surchage + ", tipAmount=" + tipAmount
				+ ", tollsAmount=" + tollsAmount + ", totalAmount=" + totalAmount + "]";
	}

	public String getMedallion() {
		return medallion;
	}

	public void setMedallion(String medallion) {
		this.medallion = medallion;
	}

	public String getHackLicense() {
		return hackLicense;
	}

	public void setHackLicense(String hackLicense) {
		this.hackLicense = hackLicense;
	}

	public DateTime getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(DateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public DateTime getDropoffDateTime() {
		return dropoffDateTime;
	}

	public void setDropoffDateTime(DateTime dropoffDateTime) {
		this.dropoffDateTime = dropoffDateTime;
	}

	public String getTripTime() {
		return tripTime;
	}

	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}

	public String getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(String tripDistance) {
		this.tripDistance = tripDistance;
	}

	public String getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(String pickupLongitude) {
		if(pickupLongitude.equals(ZERO_CORD))
		{
			this.GPSError = true;
		}
		this.pickupLongitude = pickupLongitude;
	}

	public String getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(String pickupLatitude) {
		if(pickupLatitude.equals(ZERO_CORD))
		{
			this.GPSError = true;
		}
		this.pickupLatitude = pickupLatitude;
	}

	public String getDropoffLongitude() {
		return dropoffLongitude;
	}

	public void setDropoffLongitude(String dropoffLongitude) {
		if(dropoffLongitude.equals(ZERO_CORD))
		{
			this.GPSError = true;
		}
		this.dropoffLongitude = dropoffLongitude;
	}

	public String getDropoffLatitude() {
		return dropoffLatitude;
	}

	public void setDropoffLatitude(String dropoffLatitude) {
		if(dropoffLatitude.equals(ZERO_CORD))
		{
			this.GPSError = true;
		}
		this.dropoffLatitude = dropoffLatitude;
	}

	public String getPickupTypel() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getFairAmount() {
		return fairAmount;
	}

	public void setFairAmount(String fairAmount) {
		this.fairAmount = fairAmount;
	}

	public String getSurchage() {
		return surchage;
	}

	public void setSurchage(String surchage) {
		this.surchage = surchage;
	}

	public String getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(String tipAmount) {
		this.tipAmount = tipAmount;
	}

	public String getTollsAmount() {
		return tollsAmount;
	}

	public void setTollsAmount(String tollsAmount) {
		this.tollsAmount = tollsAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
