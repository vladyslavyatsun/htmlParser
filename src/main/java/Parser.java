import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by challenger on 2/28/18.
 */
public class Parser {

    public SiteObject pars(File file)
    {
        SiteObject siteObject = null;
        try {
            ArrayList<TableElement> tableElements = new ArrayList<>();
            Document doc = Jsoup.parse(file, null);
            Element head = doc.head();

            String site = head.select("title").get(0).text();
            siteObject = new SiteObject(site);

            Element body = doc.body();
            Iterator<Element> rowIterator = body.select("tr").iterator();

            String elementsBlock = "";

            while (rowIterator.hasNext()) {
                Element row = rowIterator.next();


                if (row.childNodeSize() == 4) {
                    tableElements.add(new TableElement(row));
                }
                if (row.childNodeSize() == 1 || !rowIterator.hasNext()) {
                    if (elementsBlock.toLowerCase().contains("checkout")) {
                        siteObject.checkOutTableElements = (ArrayList<TableElement>) tableElements.clone();
                    } else if (elementsBlock.toLowerCase().contains("signup")) {
                        siteObject.signUpTableElements = (ArrayList<TableElement>) tableElements.clone();
                    } else if (elementsBlock.toLowerCase().contains("login")) {
                        siteObject.logInTableElements = (ArrayList<TableElement>) tableElements.clone();
                    }

                    tableElements.clear();
                    elementsBlock = row.select("td").get(0).text();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteObject;
    }
}
