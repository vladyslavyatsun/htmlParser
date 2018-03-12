import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by challenger on 2/28/18.
 */
public class Main {
    public static void main(String[] args) {

        Parser parser = new Parser();

        ArrayList<SiteObject> siteObjects = new ArrayList<>();
        File folder = new File("/Users/challenger/Downloads/data/");
        File [] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                SiteObject object = parser.pars(file);
                HashMap<String, List<String>> attributes = object.getCheckOutAttributes();


                for (TableElement element : object.checkOutTableElements) {
                    if (element.action.equalsIgnoreCase("type"))
                    {
                        System.out.println(element.attributes);
                    }
                }

//                System.out.print(object.site+ "  ");
//                for (Object key : attributes.keySet()) {
//                    List value = attributes.get(key);
//                    if (!value.isEmpty()) {
//                        System.out.print(key +":"+ value +" ");
//                    }
//                }
//                System.out.println();

                siteObjects.add(object);
                break;
            }
        }
    }
}

