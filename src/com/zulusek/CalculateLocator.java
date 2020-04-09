package com.zulusek;

import java.util.Locale;
import java.util.Scanner;

public class CalculateLocator {
    private double latitude;
    private double longitude;

    public CalculateLocator(){
        Scanner scanner = new Scanner(System.in);

        Double longitude;
        do {
            System.out.println("Input Longitude (-180 | +180):");
            longitude = scanner.nextDouble();
        } while (isLongitudeValid(longitude));

        Double latitude;
        do {
            System.out.println("Input Latitude (-90 | +90):");
            latitude = scanner.nextDouble();
        } while (isLatitudeValid(latitude));

        setLatitude(latitude);
        setLongitude(longitude);

        System.out.println(calculateLocator(longitude, latitude));

    }

    private double getLatitude() {
        return latitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private boolean isLongitudeValid(double longitude) {
        if (longitude >= -180 && longitude <= 180) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isLatitudeValid(double latitude) {
        if (latitude >= -90 && latitude <= 90) {
            return false;
        } else {
            return true;
        }
    }


    private String calculateLocator(double longitude, double latitude){

        String alphabet[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        double lo = longitude + 180;
        double la = latitude + 90;

        // calculating firs pair (AA-RR)
        double firstPairLongitude = (lo/20);
        String firstPairLongitudeLetter = alphabet[(int)firstPairLongitude];
        double firstPairLongitudeReminder = (lo % 20);

         double firstPairLatitude = (la / 10);
        String firstPairLatitudeLetter = alphabet[(int)firstPairLatitude];
        double firstPairLatitudeReminder = (la % 10);

        // calculating second pair (00-99)
        int secondPairLongitudeDigit = ((int)firstPairLongitudeReminder / 2);
        double secondPairLongitudeReminder = firstPairLongitudeReminder % 2;

        int secondPairLatitudeDigit = ((int)firstPairLatitudeReminder / 1);
        double secondPairLatitudeRemidner = firstPairLatitudeReminder % 1;

        //  third pair (aa-xx)
        double thirdPairLongitude = secondPairLongitudeReminder / 0.083333;
        String thirdPairLongitudeLetter = alphabet[(int)thirdPairLongitude];
        double thirdPairLongitudeReminder = secondPairLongitudeReminder % 0.083333;

        double thirdPairLatitude = secondPairLatitudeRemidner / 0.0416665;
        String thirdPairLatitudeLetter = alphabet[(int)thirdPairLatitude];
        double thirdPairLatitudeReminder = secondPairLatitudeRemidner % 0.0416665;

        //  fourth pair (00-99)
        double fourthPairLongitude = thirdPairLongitudeReminder / 0.008333;
        int fourthPairLongitudeDigit = (int)fourthPairLongitude;

        double fourthPairLatitude = thirdPairLatitudeReminder / 0.004166;
        int fourthPairLatitudeDigit = (int)fourthPairLatitude;

        String str = firstPairLongitudeLetter + firstPairLatitudeLetter ;
        Locale locale = Locale.getDefault();  //TODO: how it works? dive into it | https://javatutorialhq.com/java/lang/string-class-tutorial/touppercase-locale-method-example/
        String upperCaseLetters = str.toUpperCase(locale);

        return upperCaseLetters + secondPairLongitudeDigit + secondPairLatitudeDigit + thirdPairLongitudeLetter + thirdPairLatitudeLetter + fourthPairLongitudeDigit + fourthPairLatitudeDigit;
    }

}
