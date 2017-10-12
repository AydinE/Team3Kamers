package com.capgemini.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Validators {

    public static boolean emailMatcher(String email) {//Email validator
        final Pattern pattern = Pattern.compile("[_a-zA-Z0-9-]+\\@+[a-zA-Z0-9-.]+\\.+[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
        Matcher e = pattern.matcher(email);
        return e.find();
    }

    public static boolean phoneMatcher(String phoneNumber) {//PhoneNumber validator
        final Pattern pattern = Pattern.compile("([0-9]){10,15}", Pattern.CASE_INSENSITIVE);
        Matcher p = pattern.matcher(phoneNumber);
        return p.find();
    }

    public static boolean nameMatcher(String firstName, String lastName) {//Names validator
        final Pattern pattern = Pattern.compile("([a-zA-Z\\p{L}]){1,50}");
        Matcher fn = pattern.matcher(firstName);
        Matcher ln = pattern.matcher(lastName);
        return fn.find() && ln.find();
    }

    public static boolean postalCodeAdressMatcher(String postcode, String adress) {
        final Pattern pattern = Pattern.compile("([aA-zZ0-9.]){1,50}", Pattern.CASE_INSENSITIVE);
        Matcher postalCodeMatcher = pattern.matcher(postcode);
        Matcher addressMatcher = pattern.matcher(adress);
        return postalCodeMatcher.find() && addressMatcher.find();
    }

    public static boolean countryCityMatcher(String country, String city) {
        final Pattern pattern = Pattern.compile("([aA-zZ.]){1,50}", Pattern.CASE_INSENSITIVE);
        Matcher countryMatcher = pattern.matcher(country);
        Matcher cityMatcher = pattern.matcher(city);
        return countryMatcher.find() && cityMatcher.find();
    }
}


