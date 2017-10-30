import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import org.junit.Test;

import babysitterapp.BabySitterTimer;


public class BabySitterTests {

    @Test
    public void HourshouldTakeUserInput() {
        BabySitterTimer sitter = new BabySitterTimer();
        Scanner scanner = new Scanner("5");
        assertEquals(5, sitter.getArrivalBedtimeHour(scanner, 0));
    }

    @Test
    public void MinuteshouldTakeUserInput() {
        BabySitterTimer sitter = new BabySitterTimer();
        Scanner scanner = new Scanner("59");
        assertEquals(59, sitter.getMinute(scanner));
    }

    @Test
    public void setArrivalTimeat6() {
        BabySitterTimer sitter = new BabySitterTimer();
        LocalTime arrivalTime = LocalTime.of(18, 00);

        assertEquals(arrivalTime, sitter.gettime(6, 0));
    }
    @Test
    public void setBedtimeat10() {
        BabySitterTimer sitter = new BabySitterTimer();
        LocalTime arrivalTime = LocalTime.of(22, 00);

        assertEquals(arrivalTime, sitter.gettime(10, 0));
    }

    @Test
    public void setDepartureTimeat6() {
        BabySitterTimer sitter = new BabySitterTimer();
        LocalTime arrivalTime = LocalTime.of(18, 00);

        assertEquals(arrivalTime, sitter.gettime(6, 0));
    }

    @Test
    public void setDeparturetimeat1() {
        BabySitterTimer sitter = new BabySitterTimer();
        LocalTime arrivalTime = LocalTime.of(1, 00);

        assertEquals(arrivalTime, sitter.gettime(1, 0));
    }

    @Test
    public void getFareLeaveatBedtime() {
        BabySitterTimer sitter = new BabySitterTimer();

        int getFare = sitter.getArrivaltoBedtimeFares(5);

        assertEquals(getFare, 60);
    }

    @Test
    public void getHoursBetweenArrivalandBedtimeat30mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getBedtimeHours(LocalTime.of(20, 0), LocalTime.of(23, 30));
        assertEquals(4, hours);
    }

    @Test
    public void getHoursBetweenArrivalandBedtimeat15mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getBedtimeHours(LocalTime.of(20, 0), LocalTime.of(23, 15));
        assertEquals(3, hours);
    }

    @Test
    public void getHoursBetweenArrivaltimeandBedtimeat45mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getBedtimeHours(LocalTime.of(20, 0), LocalTime.of(23, 45));
        assertEquals(4, hours);
    }

    @Test
    public void getHoursBetweenBedtimeandDepartueretimeat30mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getDepartureHoursBeforeMidnight(LocalTime.of(20, 0), LocalTime.of(23, 30));
        assertEquals(4, hours);
    }

    @Test
    public void getHoursBetweenBedtimeandDeparturetimeat15mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getDepartureHoursBeforeMidnight(LocalTime.of(20, 0), LocalTime.of(23, 15));
        assertEquals(3, hours);
    }

    @Test
    public void getHoursBetweenBedtimeandArrivalTimeat45mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getDepartureHoursBeforeMidnight(LocalTime.of(20, 0), LocalTime.of(23, 45));
        assertEquals(4, hours);
    }

    @Test
    public void getHoursBetweenMidnightandDepartureTimeat45mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getDepartureHoursAfterMidnight(LocalTime.of(3, 45));
        assertEquals(4, hours);
    }
    @Test
    public void getHoursBetweenMidnightandDepartureTime15mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getDepartureHoursAfterMidnight(LocalTime.of(3, 15));
        assertEquals(3, hours);
    }

    @Test
    public void getHoursBetweenMidnightandDepartureTimeat30mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getDepartureHoursAfterMidnight(LocalTime.of(3, 45));
        assertEquals(4, hours);
    }

    @Test
    public void getHoursBetweenBedtimeandMidnightat45mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getBedtimeHoursDepartureAfterMidnight(LocalTime.of(20, 45));
        assertEquals(3, hours);
    }

    @Test
    public void getHoursBetweenBedtimeandMidnightat15mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getBedtimeHoursDepartureAfterMidnight(LocalTime.of(20, 15));
        assertEquals(4, hours);
    }

    @Test
    public void getHoursBetweenBedtimeandMidnightat30mins() {
        BabySitterTimer sitter = new BabySitterTimer();
        int hours = sitter.getBedtimeHoursDepartureAfterMidnight(LocalTime.of(20, 30));
        assertEquals(4, hours);
    }

    @Test
    public void getFareBetweenBedtimeandWithDepartureBeforeMidnight() {
        BabySitterTimer sitter = new BabySitterTimer();
        int fare = sitter.getFareDepartureBeforeMidnight(2);
        assertEquals(16, fare);
    }
    
    @Test
    public void checkIfDepartureisValidat4am(){
    	 BabySitterTimer sitter = new BabySitterTimer();
         boolean time = sitter.checkIfDepartureTimeIsValid(LocalTime.of(23, 0),LocalTime.of(4, 0));
         assertEquals(time, true);
    
    }

    @Test
    public void checkIfDepartureisInvalidat401am(){
    	 BabySitterTimer sitter = new BabySitterTimer();
         boolean time = sitter.checkIfDepartureTimeIsValid(LocalTime.of(23, 0),LocalTime.of(4, 1));
         assertEquals(time, false);
    
    }
    @Test
    public void checkIfDepartureisInvalidwhenDepartureTimeIsBeforeBedTime(){
    	 BabySitterTimer sitter = new BabySitterTimer();
         boolean time = sitter.checkIfDepartureTimeIsValid(LocalTime.of(23, 0),LocalTime.of(22, 0));
         assertEquals(time, false);
    
    }

    @Test
    public void checkIfDepartureisvalidwhenDepartureTimeIsAfterBedTime(){
    	 BabySitterTimer sitter = new BabySitterTimer();
         boolean time = sitter.checkIfDepartureTimeIsValid(LocalTime.of(23, 0),LocalTime.of(1, 0));
         assertEquals(time,true);
    
    }

   

}