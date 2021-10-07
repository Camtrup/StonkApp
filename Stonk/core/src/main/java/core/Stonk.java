package core;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stonk {

    private String ticker;
    private float price;
    private String name;
    private float priceChange;

    //Henter aksjeprisen til "ticker" som bruker søker på
    //Hvis "ticker" ikke er nøyaktig, får en en NullPointerException siden elementet i HTMLen = NULL
    //en blir en viderført til en liste med lignende aksjer, der velger en bare den første og satser på at det er riktig
    public void getStockInfo(String ticker) {
        if(ticker.equals(null)){
            throw new IllegalArgumentException("Ticker is null");
        }
        this.ticker = ticker;
        String link = "https://www.marketwatch.com/investing/stock/" + ticker;
        Document doc = null;
        try { 
            doc = Jsoup.connect(link)
                    .cookie("AMCVS_CB68E4BA55144CAA0A4C98A5%40AdobeOrg","1")
                    .get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            this.name = (doc.select("h1.company__name").first().text());
            this.price = Float.parseFloat(doc.select("bg-quote.value").first().text());
            this.priceChange = Float.parseFloat(doc.select("change--percent--q.value").first().text());
            // this.graph = (doc.select("mikey-chart")); // highcharts-8
        }
        catch (NullPointerException e){
            ticker = doc.select(".results table tbody tr td a").first().text();
            getStockInfo(ticker);
        }
    }
    
    public String getName(){
        return name;
    }
    public float getPrice(){
        return price;
    }
    public String getTicker(){
        return ticker;
    }

    
	@Override
	public String toString() {
        return "your stock " + name + " has a price of " + price;
    }


    public static void main(String[] args) {
        Stonk s = new Stonk();
        s.getStockInfo("GME");
        System.out.println(s);
        System.out.println(s.ticker);
        System.out.println(s.price);
        System.out.println(s.priceChange);
    }
}

