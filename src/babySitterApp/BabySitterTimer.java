package babySitterApp;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BabySitterTimer {

    public static void main(String[] args) {
        System.out.println("Welcome to my babysitter app");
        Scanner scanner = new Scanner(System.in);
        int arrivalhour = getArrivalBedtimeHour(scanner, 0);//get arrival hour
        int arrivalminute = getMinute(scanner);// get arrival minute
        LocalTime arrivaltime = gettime(arrivalhour, arrivalminute);//make arrival hour from hour and minute
        int bedtimehour = getArrivalBedtimeHour(scanner, 1);//getBedtimeHour
        int bedtimeminute = getMinute(scanner);//get bedtime minute
        LocalTime bedtime = gettime(bedtimehour, bedtimeminute);//set time from hour and minute
        while (bedtime.isBefore(arrivaltime)) { //while the bedtime is before arrival hour enter a valid time
            bedtimehour = getArrivalBedtimeHour(scanner, 2);
            bedtimeminute = getMinute(scanner);
            bedtime = gettime(bedtimehour, bedtimeminute);
        }

        int departurehour = getDepartureHour(scanner);// get departure hour
        int departureminute = getMinute(scanner);
        LocalTime departuretime = gettime(departurehour, departureminute);// set time
        boolean checkValidDepartureTime=checkIfDepartureTimeIsValid(bedtime,departuretime);// check if departure time is valid
        while (!checkValidDepartureTime) {// while its invalid keep asking for time
            System.out.println("please enter a departure time after the bedtime or a departure time before 4 AM");
            departurehour = getDepartureHour(scanner);
            departureminute = getMinute(scanner);
            departuretime = gettime(departurehour, departureminute);
            checkValidDepartureTime=checkIfDepartureTimeIsValid(bedtime,departuretime);
        }
        int fare = 0;//initial 0 fare
        int arrivalwagehours = getBedtimeHours(arrivaltime, bedtime); //get hours between arrival and bedtime
        int bedtimefare = getArrivaltoBedtimeFares(arrivalwagehours);//get fare
        if (departuretime.isAfter(LocalTime.of(0, 1)) && departuretime.isBefore(LocalTime.of(4, 1))) { 
        	//run these methods if the departure is after midnight
        	int bedtimehours = getBedtimeHoursDepartureAfterMidnight(bedtime);
        	// get hours before midnight    
            fare = bedtimefare + getFareDepartureBeforeMidnight(bedtimehours);
            //add to fare from bedtime with hours after midnight  
            int departurehours = getDepartureHoursAfterMidnight(departuretime);
            // get departure hours after midnight
            fare += getFareDepartureAfterMidnight(departurehours);
            //add to fare from departuretime with hours after midnight  

        }
        else {
        	//run these methods if the departure time is before midnight
            int departurehours = getDepartureHoursBeforeMidnight(bedtime, departuretime);
            //get departure hours
            fare = bedtimefare + getFareDepartureBeforeMidnight(departurehours);
            //calcuate fare
        }
        System.out.println("The fare for the night is "+ fare);
    //print out fare
    }

    //This method gets the hours for the arrival and bed time
    public static int getArrivalBedtimeHour(Scanner sc, int message) {
        int hour = -1;
        do {
            if (message == 0) {
                System.out.println("Please enter a arrival hour: ");
            }
            else if (message == 1) {
                System.out.println("Please enter a bedtime hour: ");
            }
            else if (message == 2) {
                System.out.println("Please enter a bedtime after your arrival time, enter your bedtime hour ");
            }
            if (sc.hasNextInt()) {
                hour = sc.nextInt();
            }
            else {
                System.out.println("I need an hour between 5 and midnight, please try again.");
                sc.nextLine();
            }
        }
        while (hour <= 4 || hour >= 12);
        return hour;
    }
    
    //This is the special method to get the departure hour
    public static int getDepartureHour(Scanner sc) {
        int hour = -1;
        do {
            System.out.println("Please enter your departure hour");
            if (sc.hasNextInt()) {
                hour = sc.nextInt();
            }
            else {
                System.out.println("I need a valid hour please");
                sc.nextLine();
            }
        }
        while (hour <= -1 || hour >= 12);
        return hour;
    }
   
    //This method gets the minute of the day
    public static int getMinute(Scanner sc) {
        int minute = -1;
        do {
            System.out.print("Please enter a valid minute: ");
            if (sc.hasNextInt()) {
                minute = sc.nextInt();
            }
            else {
                System.out.println("I need an minute, please try again.");
                sc.nextLine();
            }
        }
        while (minute <= -1 || minute >= 60);
        return minute;
    }

    //This method gets the fare from arrival time to bedtime
    public static int getArrivaltoBedtimeFares(int hours) {

        int bedtimefare = 12;
        bedtimefare *= (hours);
        return bedtimefare;
    }

    public static LocalTime gettime(int hour, int minute) {
        if (hour > 4) {
            hour += 12;}
        LocalTime time = LocalTime.of(hour, minute);
        return time;
    }

    //This method gets the hours between arrival time and bedtime
    public static int getBedtimeHours(LocalTime arrivalTime, LocalTime bedTime) {
        long minutes = ChronoUnit.MINUTES.between(arrivalTime, bedTime);
        Math.abs(minutes);
        int hours = 0;
        int module = (int)(minutes % 60);
        if (module >= 30) {
            hours = ((int) minutes / 60) + 1;
        }
        else {
            hours = (int) minutes / 60;
        }
        return hours;
    }

    //This method gets the departure hours if the departure time is before midnight
    public static int getDepartureHoursBeforeMidnight(LocalTime bedTime, LocalTime departureTime) {
        long minutes = ChronoUnit.MINUTES.between(bedTime, departureTime);
        Math.abs(minutes);
        int hours = 0;
        int module = (int)(minutes % 60);
        if (module >= 30) {
            hours = ((int) minutes / 60) + 1;
        }
        else {
            hours = (int) minutes / 60;
        }
        return hours;
    }

    //This method gets the departure hours after Midnight
    public static int getDepartureHoursAfterMidnight(LocalTime departureTime) {

        long minutes = ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, departureTime);
        Math.abs(minutes);
        int hours = 0;
        int module = (int)(minutes % 60);
        if (module >= 30) {
            hours = ((int) minutes / 60) + 1;
        }
        else {
            hours = (int) minutes / 60;
        }
        return hours;
    }

    //This method gets hours between bedtime and midnight
    public static int getBedtimeHoursDepartureAfterMidnight(LocalTime bedTime) {
        long minutes = ChronoUnit.MINUTES.between(bedTime, LocalTime.MAX);
        Math.abs(minutes);
        int hours = 0;
        int module = (int)(minutes % 60);
        if (module >= 29) {
            hours = ((int) minutes / 60) + 1;
        }
        else {
            hours = (int) minutes / 60;
        }
        return hours;
    }
     //This method returns fares of the time after bedtime before midnight
    public static int getFareDepartureBeforeMidnight(int hours) {
        return 8 * hours;
    }
    
    //This method gets the fare after midnight
    public static int getFareDepartureAfterMidnight(int hours) {
        return 16 * hours;
    }


	// This method checks if the departure time is valid

	public static boolean checkIfDepartureTimeIsValid(LocalTime bedTime, LocalTime departureTime) {
		boolean checkTime=true;
		

		if((bedTime.isAfter(departureTime))&&departureTime.isAfter(LocalTime.of(4,1))) {
			checkTime=false;
		}
		else if (departureTime.isAfter(LocalTime.of(4, 0)) && departureTime.isBefore(LocalTime.of(17, 0))) {
			checkTime=false;
		}
		
	return checkTime;
	
	}
	

	

}