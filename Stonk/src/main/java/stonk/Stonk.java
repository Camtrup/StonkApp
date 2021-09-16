package stonk;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stonk {

    String ticker;
    double price;


    public void getTickerPrice(String ticker) throws IOException{
        String link = "https://www.marketwatch.com/investing/stock/" + ticker;
        Document doc = Jsoup.connect(link)
                .cookie("AMCVS_CB68E4BA55144CAA0A4C98A5%40AdobeOrg","1")
                .get();

        try {
            System.out.println(doc.select("bg-quote.value").first().text());
        }
        catch (NullPointerException e){
            ticker = doc.select(".results table tbody tr td a").first().text();
            getTickerPrice(ticker);
        }

    }


    public static void main(String[] args) throws IOException {
        Stonk s = new Stonk();
        s.getTickerPrice("apple");
    }
}

