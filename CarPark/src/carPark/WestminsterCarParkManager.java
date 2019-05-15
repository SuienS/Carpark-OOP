package carPark;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterCarParkManager implements CarParkManager{
	
	static Vehicle[] parkedV=new Vehicle[20];
	static ArrayList<Vehicle> leftV = new ArrayList<Vehicle>();
	static int count=0;
	
	public static void main(String[] args) {
		//creating an object of the current class
		WestminsterCarParkManager m= new WestminsterCarParkManager();
		m.conMenu();
	}
	
	//console menu
	public void conMenu(){
		System.out.println("=====================================");
		System.out.println("    Westminster Car Park Manager");
		System.out.println("=====================================");
		System.out.println("1. Add a new Vehicle");
		System.out.println("2. Remove a Vehicle");
		System.out.println("3. Display parked Vehicles");
		System.out.println("4. Display percentages of each parked Vehicle type");
		System.out.println("5. Display last parked vehicle");
		System.out.println("6. Display vehicle parked for the longest time");
		System.out.println("7. Search vehicle by Date");
		System.out.println("8. Display charges for each parked vehicle");
		System.out.println("or press any other to exit...");
		Scanner scn = new Scanner(System.in);
		String i=scn.nextLine();

		//directs the user to required function
		switch(i){
		
		case "1":addVehicle();
				 confirm();
			break;
		case "2":delVehicle();
				 confirm();
			break;
		case "3":displayPrked();
				 confirm();
			break;
		case "4":displayPecentg();
		 		 confirm();
		 	break;
		case "5":firstNlast(1);
		 		 confirm();
		 	break; 	
		case "6":firstNlast(0);
		 		 confirm();
		 	break; 	
		case "7":search();
		 		 confirm();
		    break; 
		case "8":calChargers();
		 		 confirm();
		    break;
		
		 default:System.exit(0);
		    
		    
		}
	}
	
	//method to add vehicle to the carpark
	public void addVehicle(){
		Scanner scn = new Scanner(System.in);
		String lot,IDplate,vBrand,Type,Date,Time,Color,NumDoors,CargoVol,ECap;
		int lotNum;

		if(freeSpc()==20){
			System.out.println("Parking Full!!");
			conMenu();
		}
		
		
		
		System.out.println("1. Car");
		System.out.println("2. Van");
		System.out.println("3. Motor Bicycle");
		System.out.println("4. Other");
		do{
			System.out.println("Enter Vehicle type numbeer...");
			Type=scn.nextLine();
		}while(!isValid(1,Type));
		
		
		do{
			System.out.println("Enter lot number");
			lot=scn.nextLine();
		}while(!isValid(4,lot));
		
		lotNum=Integer.parseInt(lot)-1;
		
		do{
			System.out.println("Enter ID plate number:");
			IDplate=scn.nextLine();
		}while(!isValid(0,IDplate));
		
		System.out.println("Enter vehicle Brand:");
		vBrand=scn.nextLine();
		
		if(Type.equals("1")){
			
			parkedV[lotNum]=new Car();
			parkedV[lotNum].setIDplate(IDplate);
			parkedV[lotNum].setVbrand(vBrand);
			
			do{
				System.out.println("Enter number of doors of the car:");
				NumDoors=scn.nextLine();
			}while(!isValid(2,NumDoors));
			
			((Car) parkedV[lotNum]).setNumDoors(NumDoors);
			
			do{
				System.out.println("Enter the color of the car:");
				Color=scn.nextLine();
			}while(!isValid(3,Color));
			
			((Car) parkedV[lotNum]).setcColor(Color);
			
		}else if(Type.equals("2")){	
			parkedV[lotNum]=new Van();
			parkedV[lotNum].setIDplate(IDplate);
			parkedV[lotNum].setVbrand(vBrand);
			if(lotNum==19){
				if(parkedV[lotNum-1]==null){
					parkedV[lotNum-1]=new Van();
					parkedV[lotNum-1].setIDplate("--occupied--");
				}else{
					System.out.println("Space not enough for a Van..");
					conMenu();
				}	
			}else{
				if(parkedV[lotNum+1]==null){
					parkedV[lotNum+1]=new Van();
					parkedV[lotNum+1].setIDplate("--occupied--");
				}else{
					System.out.println("Space not enough for a Van..");
					conMenu();
				}
			}
			
			do{
				System.out.println("Enter cargo volume of the van (cm3):");
				CargoVol=scn.nextLine();
			}while(!isValid(2,CargoVol));
			
			((Van) parkedV[lotNum]).setCrgVol(CargoVol);
			
		}else if(Type.equals("3")){	
			parkedV[lotNum]=new Motorbike();
			parkedV[lotNum].setIDplate(IDplate);
			parkedV[lotNum].setVbrand(vBrand);
			do{
				System.out.println("Enter engine capacity of the bicycle (CC):");
				ECap=scn.nextLine();
			}while(!isValid(2,ECap));		
			((Motorbike) parkedV[lotNum]).setEngCapacity(ECap);
		}else{
			parkedV[lotNum]=new Other();
			parkedV[lotNum].setIDplate(IDplate);
			parkedV[lotNum].setVbrand(vBrand);
		}
		
		do{
			System.out.println("Enter in-time (hhmm):");
			Time=scn.nextLine();
		}while(!isValid(20,Time));
		parkedV[lotNum].setTimeIN(Time);
		
		do{
			System.out.println("Enter in-date (dd.mm.yyyy):");
			Date=scn.nextLine();
		}while(!isValid(6,Date));
		parkedV[lotNum].setDateIN(Date);		
		
		
			System.out.println("Available lots : "+(20-freeSpc()));
		
		
		
	}
	
	//get parked vehicles
	public static int freeSpc(){
		int pCount=0;
		int x=0;
		while(x<20){
			if(parkedV[x]!=null){
				pCount++;
			}
			x++;
		}
		
		return pCount;
	}
	
	//delete vehicle from the array
	public void delVehicle(){
		Scanner scn = new Scanner(System.in);
		String lot,Time,Date;
		int lotN=1;
		System.out.println("Lot NO.   ID Plate");
		System.out.println("======    ========");

		while(lotN<=20){
			if(parkedV[lotN-1]!=null){
				System.out.println(lotN+"          "+parkedV[lotN-1].getIDplate());
			}else{
				System.out.println(lotN+"          -----");
			}
			lotN++;
		}
		
		do{
			System.out.println("Enter lot number");
			lot=scn.nextLine();
		}while(!isValid(7,lot));
		
		do{
			System.out.println("Enter out-time (hhmm):");
			Time=scn.nextLine();
		}while(!isValid(20,Time));
		parkedV[Integer.parseInt(lot)-1].setTimeOUT(Time);
		
		do{
			System.out.println("Enter out-date (dd.mm.yyyy):");
			Date=scn.nextLine();
		}while(!isValid(6,Date));
		parkedV[Integer.parseInt(lot)-1].setDateOUT(Date);
		
		System.out.println("A \""+parkedV[Integer.parseInt(lot)-1].getClass().getName().substring(8)+"\" is leaving...");
		
		leftV.add(parkedV[Integer.parseInt(lot)-1]);
		if(Integer.parseInt(lot)!=20&&parkedV[Integer.parseInt(lot)].getClass().getName().substring(8).equals("Van")&&parkedV[Integer.parseInt(lot)].getIDplate().equals("--occupied--")){
			parkedV[Integer.parseInt(lot)]=null;
		}else if(parkedV[Integer.parseInt(lot)].getClass().getName().substring(8).equals("Van")){
			parkedV[Integer.parseInt(lot)-2]=null;
		}
		parkedV[Integer.parseInt(lot)-1]=null;
		
	}
	
	
	//validation for the inputs
	public boolean isValid(int x,String in){
		boolean validity = true;
		switch(x){
			case 0:if(!in.matches("[a-zA-Z0-9]+")){
				   	   System.err.println("Enter a valid input ");
				   	   validity=false;
				   }
				break;
			case 1:if(!in.matches("[1-4]")){
			       validity=false;
				   System.err.println("Enter a valid number ");
			   }
			break;
			case 2:if(!in.matches("[0-9]+")){
			       validity=false;
				   System.err.println("Enter a valid number ");
			   }
			break;
			
			case 3:if(!in.matches("[a-zA-Z]+")){
			   	   System.err.println("Enter a valid input ");
			   	   validity=false;
			   }
			break;
			
			case 4:if(!in.matches("[0-9]+")){
			   	   	System.err.println("Enter a valid input ");
			   	   	validity=false;
			   	   }else{
			   		   if(Integer.parseInt(in)>20||Integer.parseInt(in)<=0){
			   			   System.err.println("Enter a valid input between 1 and 20");
			   			   validity=false;
			   		   }else if(parkedV[Integer.parseInt(in)-1]!=null){
			   			   System.err.println("The lot is already filled");
			   			   validity=false;
			   		   }
			   	   }
			break;
			case 5:if(!in.matches("[0-9]+")||in.length()!=4){
			       validity=false;
				   System.err.println("Enter a valid time input ");
			   }
			break;
			case 6:if(!in.matches("\\d{2}.\\d{2}.\\d{4}")){
			       		validity=false;
			       		System.err.println("Enter a valid date ");
			   	   }else{
			   		   String[] arV=in.split("\\.");
			   		   if(Integer.parseInt(arV[1])>12||Integer.parseInt(arV[0])>311){
			   			   validity=false;
			   			   System.err.println("Enter a valid date ");
			   		   }
				   }
			break;
			case 7:if(!in.matches("[0-9]+")){
		   	   	System.err.println("Enter a valid input ");
		   	   	validity=false;
		   	   }else{
		   		   if(Integer.parseInt(in)>20||Integer.parseInt(in)<=0){
		   			   System.err.println("Enter a valid input between 1 and 20");
		   			   validity=false;
		   		   }else if(parkedV[Integer.parseInt(in)-1]==null){
		   			   System.err.println("The lot is already free");
		   			   validity=false;
		   		   }
		   	   }
		break;
			default:
		
		}
		
		
		return validity;
		
	}
	
	//display all the parked methods
	public void displayPrked(){
		int lotN=1;
		int ord=0;
		System.out.printf("%15s%15s%15s%15s\n","Lot number","ID Plate"," Vehcle type","in-Time");
		System.out.printf("%15s%15s%15s%15s\n","===========","===========","============","==========");
		
		while(ord<20){
			int fl=0;
			while(lotN<=20){
				if(parkedV[lotN-1]!=null&& !parkedV[lotN-1].getIDplate().equals("--occupied--")){
					System.out.printf("%15d%15s%15s%15s\n",lotN,parkedV[lotN-1].getIDplate(),parkedV[lotN-1].getClass().getName().substring(8),parkedV[lotN-1].getTimeIN());
					fl++;
				}
				lotN++;
			}
			if(fl==0){
				break;
			}
			ord++;
		}
	} 
	
	//display pecentages of the parked vehicles
	public void displayPecentg(){
		int lotN=0;
		long carCnt=0;
		long vanCnt=0;
		long bikeCnt=0;
		long otherCnt=0;


		while(lotN<20){
			if(parkedV[lotN]!=null&&parkedV[lotN].getIDplate().equals("--occupied--")){
				lotN++;
				continue;
			}
			if(parkedV[lotN]!=null){
				if(parkedV[lotN].getClass().getName().substring(8).equals("Car")){
					carCnt++;
				}else if(parkedV[lotN].getClass().getName().substring(8).equals("Van")){
					vanCnt++;
				}else if(parkedV[lotN].getClass().getName().substring(8).equals("Motorbike")){
					bikeCnt++;
				}else if(parkedV[lotN].getClass().getName().substring(8).equals("Other")){
					otherCnt++;
				}	
			}
			lotN++;
		}
		
		double carP=( carCnt / (carCnt+vanCnt+bikeCnt+otherCnt) )*100;
		double vanP=( vanCnt / (carCnt+vanCnt+bikeCnt+otherCnt) )*100;
		double bikeP=( bikeCnt / (carCnt+vanCnt+bikeCnt+otherCnt) )*100;
		double otherP=( otherCnt / (carCnt+vanCnt+bikeCnt+otherCnt) )*100;
		
		System.out.println("Percentages of the each vehicle type parked");
		System.out.println("===========================================");
		System.out.println("Car : "+carP+"%");
		System.out.println("Van : "+vanP+"%");
		System.out.println("Motorbike : "+bikeP+"%");
		System.out.println("Other : "+otherP+"%");
	} 
	
	//display first and the last parked vehicle
	public void firstNlast(int x){
		
		if(x==0){
			System.out.println("Vehicle parked for the longest time");
			System.out.println("===================================");
			int lotN=0;
			int low=Integer.MAX_VALUE;
			int found=0;
			while(lotN<20){
				if(parkedV[lotN]!=null&&parkedV[lotN].getIDplate().equals("--occupied--")){
					lotN++;
					continue;
				}
				if(parkedV[lotN]!=null){
					if(parkedV[lotN].count<low){
						low=parkedV[lotN].count;
						found=lotN;
					}
				}
				lotN++;
			}
			System.out.println("ID plate : "+parkedV[found].getIDplate());
			System.out.println("Vehicle type : "+parkedV[found].getClass().getName().substring(8));
			System.out.println("in-Time : "+parkedV[found].getTimeIN());
			System.out.println("in-Date : "+parkedV[found].getDateIN());
			
			
		}else{
			System.out.println("Vehicle last parked");
			System.out.println("===================");
			int lotN=0;
			int high=0;
			int found=0;
			while(lotN<20){
				if(parkedV[lotN]!=null&&parkedV[lotN].getIDplate().equals("--occupied--")){
					lotN++;
					continue;
				}
				if(parkedV[lotN]!=null){	
					if(parkedV[lotN].count>high){
						high=parkedV[lotN].count;
						found=lotN;
					}
				}
				lotN++;
			}
			System.out.println("ID plate : "+parkedV[found].getIDplate());
			System.out.println("Vehicle type : "+parkedV[found].getClass().getName().substring(8));
			System.out.println("in-Time : "+parkedV[found].getTimeIN());
			System.out.println("in-Date : "+parkedV[found].getDateIN());
		}
		
	}
	
	//search vehicle by the ID plate
	public void search(){
		Scanner scn = new Scanner(System.in);
		String sDate;
		do{
			System.out.println("Enter date to search...");
			sDate=scn.nextLine();
		}while(!isValid(6,sDate));
		System.out.println();
		System.out.printf("%15s%15s%15s\n","ID Plate"," Vehcle type","in-Time");
		System.out.printf("%15s%15s%15s\n","==========","===========","==========");
		int c=0;
		for(Vehicle date:leftV){
			if(date.getDateIN().equals(sDate)){
				c++;
				System.out.printf("%15s%15s%15s\n",date.getIDplate(),date.getClass().getName().substring(8),date.getTimeIN());	
			}
		}
		if(c==0){
			System.out.println("No vehicles found on that day");
		}
	}
	
	//calculate charges for the vehicles
	public void calChargers(){
		
		if(leftV.size()==0){
			System.out.println("No vehicle left-record found");
		}else{
			System.out.printf("%15s%15s\n","ID Plate"," Price");
			System.out.printf("%15s%15s\n","==========","===========");
			for(Vehicle v:leftV){
				double time=v.getTimeOUTCal().getHours( v.getTimeINCal() );
				double price=0;
				if(time>3){
					price=(time-3)+9;
				}else{
					price=time*3;
				}
				System.out.printf("%15s%13.2f\n",v.getIDplate(),price);
			}
		}
		
	}
	
	//confirmation dialog after every function
	public void confirm(){
		System.out.println("press Y to go to menu or any to exit...");
		Scanner scnIn = new Scanner(System.in);
		String input=scnIn.nextLine().toUpperCase();
		if(input.equals("Y")){
			WestminsterCarParkManager m= new WestminsterCarParkManager();
			m.conMenu();
		}else{
			System.exit(0);
		}
	}

}
