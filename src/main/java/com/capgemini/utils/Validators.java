package com.capgemini.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validators {
    
    String message = "Input is correct";
    Pattern pattern = Pattern.compile("POLL\\s+([ADM]{10})\\s+(yes|no\\s+([a-z. ]+))",Pattern.CASE_INSENSITIVE);

    Matcher m = pattern.matcher(message);

}

