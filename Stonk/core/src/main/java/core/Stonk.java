package core;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Class Stonk.
 */
public class Stonk {

  private String ticker;
  private float price;
  private String name;
  private String priceChange;
  private int count;

  public Stonk(String ticker, int count){
    scrapeStockInfo(ticker, count);
  }

  
  private void scrapeStockInfo(String ticker, int count){
    if (ticker == null) {
      throw new IllegalArgumentException("Could not find stock");
    }
    if (ticker.equals("")) {
      throw new IllegalArgumentException("Cannot be blank");
    }
    this.ticker = ticker.toLowerCase();
    
    String link = "https://www.marketwatch.com/investing/stock/" + ticker;
    Document doc = null;
    try {
      doc = Jsoup.connect(link).cookie("AMCVS_CB68E4BA55144CAA0A4C98A5%40AdobeOrg", "1").get();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    
    try {
      this.name = (doc.select("h1.company__name").first().text());
      this.price = Float.parseFloat(doc.select("h2.intraday__price:contains(.)").first().text().replaceAll("[^\\.0123456789]",""));
      this.priceChange = doc.select("span.change--percent--q").first().text();
      this.count = count;
    } catch (NullPointerException e) {
      String tick = doc.select(".results table tbody tr td a").first().text();
      this.scrapeStockInfo(tick, count);
    }
  }

  


  //Used for generating bought stocks from the database;
  public Stonk(String ticker, float price, int count, String name, String priceChange){
    this.price = price;
    this.ticker = ticker;
    this.count = count;
    this.name = name;
    this.priceChange = priceChange;
  }

  public int getCount(){
    return count;
  }
  private void setCount(int count){
    this.count = count;
  }

  public void setNewCount(Stonk s){
    if(!s.getTicker().equals(this.getTicker())){
      throw new IllegalArgumentException("Stocks are not alike, cant set new count");
    }
    int newCount = this.getCount() - s.getCount();
    this.setCount(newCount);
  }

  public void setNewAverage(Stonk s){
      if(!s.getTicker().equals(this.getTicker())){
        throw new IllegalArgumentException("Stocks are not alike, cant set average");
      }
      int newCount = this.getCount() + s.getCount();
      float newPrice = ((this.getCount() * this.getPrice()) + (s.getCount() * s.getPrice()))/newCount;
      this.setCount(newCount);
      this.setPrice(newPrice);
  }

  private void setPrice(float price){
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }

  public String getTicker() {
    return ticker;
  }

  public String getPriceChange() {
    return priceChange;
  }
}
