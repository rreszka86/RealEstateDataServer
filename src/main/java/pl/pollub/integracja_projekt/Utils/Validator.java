package pl.pollub.integracja_projekt.Utils;

import java.util.regex.Pattern;

public class Validator {
    public static boolean validRequired(String string){
        if(string == null) return false;
        return !string.isBlank();
    }

    public static boolean validEmail(String string){
        return Pattern
                .compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
                .matcher(string)
                .matches();
    }

    public static boolean validPassword(String string){
        return Pattern
                .compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
                .matcher(string)
                .matches();
    }
}
