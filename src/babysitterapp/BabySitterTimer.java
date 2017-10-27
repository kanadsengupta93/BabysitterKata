package babysitterapp;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BabySitterTimer {
	
	public static void main(String[] args) {
		System.out.println("Welcome to my babysitter app");
		Scanner scanner = new Scanner(System.in);
		int arrivalhour = getArrivalBedtimeHour(scanner, 0);
		int arrivalminute = getMinute(scanner);
		LocalTime arrivaltime = gettime(arrivalhour, arrivalminute);
		int bedtimehour = getArrivalBedtimeHour(scanner, 1);
		int bedtimeminute = getMinute(scanner);
		LocalTime bedtime = gettime(bedtimehour, bedtimeminute);
		while (bedtime.isBefore(arrivaltime)) {
			bedtimehour = getArrivalBedtimeHour(scanner, 2);
			bedtimeminute = getMinute(scanner);
			bedtime = gettime(bedtimehour, bedtimeminute);
  }
		
		int departurehour = getDepartureHour(scanner);
		int departureminute = getMinute(scanner);
		LocalTime departuretime = gettime(departurehour, departureminute);

		while (departuretime.isBefore(bedtime) && departuretime.isBefore(LocalTime.MIDNIGHT) || (departuretime.isAfter(LocalTime.of(4, 0)) && departuretime.isBefore(LocalTime.of(17, 0)))) {
			System.out.println("please enter a departure time after the bedtime or a departure time before 4 AM");
			departurehour = getDepartureHour(scanner);
			departureminute = getMinute(scanner);
			departuretime = gettime(departurehour, departureminute);
  }
		int fare = 0;
		int arrivalwagehours = getBedtimeHours(arrivaltime, bedtime);
		int bedtimefare = getArrivaltoBedtimeFares(arrivalwagehours);
		if (departuretime.isAfter(LocalTime.of(0, 1)) && departuretime.isBefore(LocalTime.of(4, 1))) {
			int bedtimehours = getBedtimeHoursDepartureAfterMidnight(bedtime);
			fare = bedtimefare + getFareDepartureBeforeMidnight(bedtimehours);
			int departurehours = getDepartureHoursAfterMidnight(departuretime);
			fare += getFareDepartureAfterMidnight(departurehours);
  } 
		else {
			int departurehours = getDepartureHoursBeforeMidnight(bedtime, departuretime);
			fare = bedtimefare + getFareDepartureBeforeMidnight(departurehours);
		}
	   	System.out.println("The fare for the night is "+ fare);
}

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

	public static int getFareDepartureBeforeMidnight(int hours) {
  // TODO Auto-generated method stub
		return 8 * hours;
 }
 
	public static int getFareDepartureAfterMidnight(int hours) {
		return 16 * hours;
 }




}