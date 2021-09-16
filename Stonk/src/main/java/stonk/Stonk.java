package stonk;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stonk {
    public void yeet(String ticker) throws IOException{
        String link = "https://www.marketwatch.com/investing/stock/" + ticker;
        Document doc = Jsoup.connect(link)
                .cookie("AMCVS_CB68E4BA55144CAA0A4C98A5%40AdobeOrg","1")
                .get();
        System.out.println(doc.select("bg-quote.value").first());
    }


    public static void main(String[] args) throws IOException {
        Stonk s = new Stonk();
        s.yeet("amc");
    }
}

