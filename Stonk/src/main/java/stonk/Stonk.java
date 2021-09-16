package stonk;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stonk {
    public void yeet(String ticker) throws IOException{
        String link = "https://finance.yahoo.com/quote/" + ticker;
        Document doc = Jsoup.connect(link+ticker).get();
        System.out.println(doc);
    }


    public static void main(String[] args) throws IOException {
        Stonk s = new Stonk();
        s.yeet("amc");
    }
}

