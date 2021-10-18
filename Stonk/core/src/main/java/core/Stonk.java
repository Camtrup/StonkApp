package core;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stonk {

    private String ticker;
    private float price;
    private String name;
    private String priceChange;

    //Henter aksjeprisen til "ticker" som bruker søker på
    //Hvis "ticker" ikke er nøyaktig, får en en NullPointerException siden elementet i HTMLen = NULL
    //en blir en viderført til en liste med lignende aksjer, der velger en bare den første og satser på at det er riktig
    public void getStockInfo(String ticker) {
        if(ticker.equals(null)){
            throw new IllegalArgumentException("Could not find stock");
        }
        if(ticker == ""){
            throw new IllegalArgumentException("Cannot be blank");
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
            this.priceChange = doc.select("span.change--percent--q").first().child(0).text();
            // this.graph = (doc.select("mikey-chart")); // highcharts-8
        }
        catch (NullPointerException e){
            System.out.println(doc.select(".results table tbody tr td a").first().text());
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

    public String getPriceChange() {
        return priceChange;
    }

    // test if Stonk.java works
	@Override
	public String toString() {
        return "your stock " + name + " has a price of " + price;
    }


    public static void main(String[] args) {
        Stonk s = new Stonk();
        s.getStockInfo("google");
        System.out.println(s);
        System.out.println(s.ticker);
        System.out.println(s.price);
        System.out.println(s.priceChange);
    }
}

