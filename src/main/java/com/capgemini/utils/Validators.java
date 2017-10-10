package com.capgemini.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.capgemini.model.*;

public class Validators {

    public static boolean emailMatcher(String email) {//Email validator
        final Pattern pattern = Pattern.compile("[_a-zA-Z0-9-]+\\@+[a-zA-Z0-9-.]+\\.+[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
        Matcher e = pattern.matcher(email);
        return e.find();
    }

    public static boolean phoneMatcher(String phoneNumber) {//PhoneNumber validator
        final Pattern pattern = Pattern.compile("\\+([0-9]){1,6}-([0-9]){10,15}", Pattern.CASE_INSENSITIVE);
        Matcher p = pattern.matcher(phoneNumber);
        return p.find();
    }

    public static boolean nameMatcher(String firstName, String lastName) {//Names validator
        final Pattern pattern = Pattern.compile("([aA-zZ.]){1,50}", Pattern.CASE_INSENSITIVE);
        Matcher ln = pattern.matcher(lastName);
        Matcher fn = pattern.matcher(firstName);
        return ln.find()&& fn.find();
    }
}


//   "Guest{" +
//            "id=" + id +
//            ", firstName='" + firstName + '\'' +
//            ", lastName='" + lastName + '\'' +
//            ", address='" + address + '\'' +
//            ", postalCode='" + postalCode + '\'' +
//            ", city='" + city + '\'' +
//            ", country='" + country + '\'' +
//            ", phoneNumber='" + phoneNumber + '\'' +
//            ", email='" + email + '\'' +
//            '}';

//   return "Booking{" +
//                    ", bookingNr=" + bookingNr +
//            ", guest=" + guest +
//            ", room=" + room +
//            ", startDate=" + startDate +
//            ", endDate=" + endDate +
//            ", checkedIn=" + checkedIn +
//            '}';



