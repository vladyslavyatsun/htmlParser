import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by challenger on 2/28/18.
 */
public class SiteObject {

    public static final String EMAIL_KEY = "EMAIL";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String LOGIN_NAME_KEY = "LOGIN_NAME";
    public static final String FIRST_NAME_KEY = "FIRST_NAME";
    public static final String LAST_NAME_KEY = "LAST_NAME";
    public static final String ZIP_KEY = "ZIP";
    public static final String CITY_KEY = "CITY";
    public static final String COUNTRY_KEY = "COUNTRY";
    public static final String PHONE_KEY = "PHONE";

    public static final String ADDITIONAL_KEY = "ADDITIONAL";


    public static final Pattern EMAIL_REGEX = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD_REGEX = Pattern.compile("^Qwerty123.*", Pattern.CASE_INSENSITIVE);
    public static final Pattern LOGIN_NAME_REGEX = Pattern.compile("^Lname.*", Pattern.CASE_INSENSITIVE);
    public static final Pattern FIRST_NAME_REGEX = Pattern.compile("^John.*", Pattern.CASE_INSENSITIVE);
    public static final Pattern LAST_NAME_REGEX = Pattern.compile("^Doe.*", Pattern.CASE_INSENSITIVE);
    public static final Pattern ZIP_REGEX = Pattern.compile("^12345$", Pattern.CASE_INSENSITIVE);
    public static final Pattern CITY_REGEX = Pattern.compile("^(label=)?(new york|NY)$", Pattern.CASE_INSENSITIVE);
    public static final Pattern COUNTRY_REGEX = Pattern.compile("^(label=)?(USA|United States)$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PHONE_REGEX = Pattern.compile("^1-?1-?1-?1-?1-?1-?1-?1-?1-?1-?1$", Pattern.CASE_INSENSITIVE);

    public static final Pattern ADDITIONAL_REGEX = Pattern.compile("^test$", Pattern.CASE_INSENSITIVE);

    public String site;
    public ArrayList<TableElement> signUpTableElements;
    public ArrayList<TableElement> logInTableElements;
    public ArrayList<TableElement> checkOutTableElements;

    private Matcher matcher;

    SiteObject(String site) {
        this.site = site;
        this.signUpTableElements = new ArrayList<>();
        this.logInTableElements = new ArrayList<>();
        this.checkOutTableElements = new ArrayList<>();
    }

    public HashMap<String, List<String>> getLogInAttributes() {
        HashMap<String, List<String>> result = new HashMap();
        result.put(EMAIL_KEY, getAttributesForTypeField(logInTableElements, EMAIL_REGEX));
        result.put(PASSWORD_KEY, getAttributesForTypeField(logInTableElements, PASSWORD_REGEX));
        result.put(LOGIN_NAME_KEY, getAttributesForTypeField(logInTableElements, LOGIN_NAME_REGEX));
        result.put(ADDITIONAL_KEY, getAttributesForTypeField(logInTableElements, ADDITIONAL_REGEX));
        return result;
    }

    public HashMap<String, List<String>> getSignUpAttributes() {
        HashMap<String, List<String>> result = new HashMap();

        result.put(EMAIL_KEY, getAttributesForTypeField(signUpTableElements, EMAIL_REGEX));
        result.put(PASSWORD_KEY, getAttributesForTypeField(signUpTableElements, PASSWORD_REGEX));
        result.put(LOGIN_NAME_KEY, getAttributesForTypeField(signUpTableElements, LOGIN_NAME_REGEX));
        result.put(FIRST_NAME_KEY, getAttributesForTypeField(signUpTableElements, FIRST_NAME_REGEX));
        result.put(LAST_NAME_KEY, getAttributesForTypeField(signUpTableElements, LAST_NAME_REGEX));
        result.put(ZIP_KEY, getAttributesForTypeField(signUpTableElements, ZIP_REGEX));

        List<String> cityAttributes = new ArrayList<>();
        cityAttributes.addAll(getAttributesForTypeField(signUpTableElements, CITY_REGEX));
        cityAttributes.addAll(getAttributesForSelectField(signUpTableElements, CITY_REGEX));
        result.put(CITY_KEY, cityAttributes);

        List<String> countryAttributes = new ArrayList<>();
        countryAttributes.addAll(getAttributesForTypeField(signUpTableElements, COUNTRY_REGEX));
        countryAttributes.addAll(getAttributesForSelectField(signUpTableElements, COUNTRY_REGEX));
        result.put(COUNTRY_KEY, countryAttributes);

        result.put(PHONE_KEY, getAttributesForTypeField(signUpTableElements, PHONE_REGEX));

        result.put(ADDITIONAL_KEY, getAttributesForTypeField(signUpTableElements, ADDITIONAL_REGEX));

        return result;
    }

    private List<String> getAttributesForTypeField(List<TableElement> elements, Pattern pattern) {
        ArrayList<String> result = new ArrayList<>();
        for (TableElement element : elements) {
            if(element.action.equalsIgnoreCase("type") && this.validate(element.value, pattern)) {
                result.addAll(element.attributes);
            }
        }
        return result;
    }

    private List<String> getAttributesForSelectField(List<TableElement> elements, Pattern pattern) {
        ArrayList<String> result = new ArrayList<>();
        for (TableElement element : elements) {
            if(element.action.equalsIgnoreCase("select") && this.validate(element.value, pattern)) {
                result.addAll(element.attributes);
            }
        }
        return result;
    }

    public boolean validate(final String value, Pattern pattern) {

        matcher = pattern.matcher(value);
        return matcher.matches();

    }
}
