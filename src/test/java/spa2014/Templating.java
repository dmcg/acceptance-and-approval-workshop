package spa2014;


import java.util.Map;

public class Templating {

    public static String substitute(String template, Map<String, String> templateVars) {
        String result = template;
        for (Map.Entry<String, String> keyValue : templateVars.entrySet()) {
            result = result.replace(keyValue.getKey(), keyValue.getValue());
        }
        return result;
    }
}
