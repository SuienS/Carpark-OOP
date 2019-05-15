package carPark;

public abstract class Vehicle {
	static int i=0;
	int count=0;
	private String IDplate;
	private String Vbrand;
	private DateTime dateTimeIN=new DateTime();
	private DateTime dateTimeOUT=new DateTime();
	
	//getter and setter methods for encapsulated field
	public String getIDplate() {
		return IDplate;
	}
	public void setIDplate(String iDplate) {
		IDplate = iDplate;
		count=i;
		i++;
	}
	public String getVbrand() {
		return Vbrand;
	}
	public void setVbrand(String vbrand) {
		Vbrand = vbrand;
	}
	public String getDateIN() {
		return dateTimeIN.Date;
	}
	public void setDateIN(String dateTime) {
		this.dateTimeIN.Date = dateTime;
	}
	public String getTimeIN() {
		return dateTimeIN.Time;
	}
	public void setTimeIN(String dateTime) {
		this.dateTimeIN.Time = dateTime;
	}
	
	public String getDateOUT() {
		return dateTimeOUT.Date;
	}
	public void setDateOUT(String dateTime) {
		this.dateTimeOUT.Date = dateTime;
	}
	public String getTimeOUT() {
		return dateTimeOUT.Time;
	}
	public void setTimeOUT(String dateTime) {
		this.dateTimeOUT.Time = dateTime;
	}

	public DateTime getTimeOUTCal() {
		return dateTimeOUT;
	}
	public DateTime getTimeINCal() {
		return dateTimeIN;
	}
}
