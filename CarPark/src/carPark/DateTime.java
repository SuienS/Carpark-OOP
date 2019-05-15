package carPark;

public class DateTime {
	public String Time,Date;
	
	
	//method to get parked time
	public double getHours(DateTime inTime){
		
		int mOut=Integer.parseInt(Time.substring(2));
		int hOut=Integer.parseInt(Time.substring(0,2));
		int mIn=Integer.parseInt(inTime.Time.substring(2));
		int hIn=Integer.parseInt(inTime.Time.substring(0,2));
		double mins=0;
		//calculating parked time
		if(Date.equals(inTime.Date)){
			mins=( (hOut*60)+mOut ) - ( (hIn*60)+mIn );
		}else if(!Date.substring(0, 2).equals(inTime.Date.substring(0, 2))){
			mins=( (hOut*60)+mOut ) - ( (hIn*60)+mIn ) + 24*60;
		}
		
		
		return Math.ceil( mins/60 );
	}

}
