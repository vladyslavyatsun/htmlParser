import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by challenger on 2/28/18.
 */
public class TableElement {

    public String action;
    public ArrayList<String> attributes;
    public String value;

    TableElement(Element element) {
        Elements cols = element.select("td");
        this.action = cols.get(0).text();
        this.value = cols.get(2).text();
        this.attributes = new ArrayList<>();

        Elements options = cols.get(1).select("option");

        Pattern pattern = Pattern.compile("(id|name)=([a-z0-9._%+-]+|'[a-z0-9._%+-]+')");

        for (Element option : options) {
            Matcher matcher = pattern.matcher(option.text());
            if (matcher.find()) {
                attributes.add(matcher.group());
            }
        }
    }
}
