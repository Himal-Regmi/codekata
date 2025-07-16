package documenttablereader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class DocumentUtil {
    public static Element getTableElementFromDocument(String filePath) throws IOException {
            Document doc = Jsoup.connect(filePath).get();
            return doc.select("table").first();
    }
}
