package com.capgemini.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.capgemini.model.*;

public class Validators {

    {
        final String message = "Input is correct";
        final Pattern pattern = Pattern.compile("POLL\\s+([ADM]{10})\\s+(yes|no\\s+([a-z. ]+))", Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(message);
    }

    {
        final String message = "Email is not correct";
        final Pattern pattern = Pattern.compile("[_a-zA-Z0-9--.]*@[a-zA-Z0-9-]*\\.[a-zA-Z0-9-]*$", Pattern.CASE_INSENSITIVE);
        Matcher e = pattern.matcher(message);
    }

    {
        final String message = "Phonenumber is not correct";
        final Pattern pattern = Pattern.compile("\\+([0-9]){10,}$", Pattern.CASE_INSENSITIVE);
        Matcher p = pattern.matcher(message);
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



