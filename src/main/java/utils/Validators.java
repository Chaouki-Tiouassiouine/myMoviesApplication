package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean suitableMovieAgeMatcher(String suitableAgeMovie) { //kijken of de age wel een getal is tussen de 0 en 100
        final Pattern pattern = Pattern.compile("([0-9]){1,2}", Pattern.CASE_INSENSITIVE);
        Matcher p = pattern.matcher(suitableAgeMovie);
        return p.find();

    }
}
