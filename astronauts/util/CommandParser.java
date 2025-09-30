package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public static List<String> parse(String input) {
        List<String> parts = new ArrayList<>();

        Pattern pattern = Pattern.compile("\"([^\"]*)\"|\\S+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (matcher.group(1) != null) {

                parts.add(matcher.group(1));
            } else {

                parts.add(matcher.group());
            }
        }
        return parts;
    }
}