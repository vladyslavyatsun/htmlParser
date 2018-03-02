import java.io.File;
import java.util.ArrayList;

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
                System.out.println(object.getLogInAttributes());
                siteObjects.add(object);
            }
        }
    }
}

