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
  private float priceChange;
  private int count;

  /**
   * scrapes the stock from marketwatch by running ScrapeStockInfo.
   */
  public Stonk(String ticker, int count) {
    scrapeStockInfo(ticker, count);
  }
  
  /**
   * Uses JSoup for scraping an Stock from marketwatch.
   *
   * @param ticker string
   * @param count int
   */
  private void scrapeStockInfo(String ticker, int count) {
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
      this.price = Float
          .parseFloat(doc.select("h2.intraday__price:contains(.)").first().text()
          .replaceAll("[^\\.0123456789]", ""));
      String change = doc.select("span.change--percent--q").first().text();
      change = change.replace("%", "");
      if (change.contains("-")) {
        this.priceChange = Float.parseFloat(change.substring(1)) * -1;
      } else {
        this.priceChange = Float.parseFloat(change);
      }
      this.count = count;
    } catch (NullPointerException e) {
      String tick = doc.select(".results table tbody tr td a").first().text();
      this.scrapeStockInfo(tick, count);
    }
  }

  /**
   * Used for generating bought stocks from database.
   *
   * @param ticker string.
   * @param price float.
   * @param count int.
   * @param name string.
   * @param priceChange change of price.
   */
  public Stonk(String ticker, float price, int count, String name, float priceChange) {
    this.price = price;
    this.ticker = ticker;
    this.count = count;
    this.name = name;
    this.priceChange = priceChange;
  }

  public int getCount() {
    return count;
  }

  private void setCount(int count) {
    this.count = count;
  }

  /**
   * Only fired when one removes a stock, hence (-). 
   *
   * @param s the stock.
   */
  public void setNewCount(Stonk s) {
    if (!s.getTicker().equals(this.getTicker())) {
      throw new IllegalArgumentException("Stocks are not alike, cant set new count");
    }
    int newCount = this.getCount() - s.getCount();
    this.setCount(newCount);
  }

  /**
   * Sets new average for stock.
   *
   * @param s which stock.
   */
  public void setNewAverage(Stonk s) {
    if (!s.getTicker().equals(this.getTicker())) {
      throw new IllegalArgumentException("Stocks are not alike, cant set average");
    }
    int newCount = this.getCount() + s.getCount();
    float newPrice = ((this.getCount() * this.getPrice())
        + (s.getCount() * s.getPrice())) / newCount;
    this.setCount(newCount);
    this.setPrice(newPrice);
  }

  private void setPrice(float price) {
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

  public float getPriceChange() {
    return priceChange;
  }
}
