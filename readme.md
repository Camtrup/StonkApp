[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/blob/master/Stonk/ui/src/main/java/ui/StonkApp.java)

![](https://i.ibb.co/qxM02Nc/fb2bfc2bf0ed4c569cdf5b8168878d1b.png)

# STONK APP

**Stonk** is a stock ["Paper Trading"](https://www.investopedia.com/terms/p/papertrade.asp) application, where the stock prices get updated in real time. Trading with virtual money is a great way for beginners to learn how to trade in the financial markets, but also to experienced traders that want to test their trading strategies without any risk.

Our initial thought was to pull the stock prices using an API, but we decided against it as we needed a service that was free-of-charge. Therefore we get the stocks current price by web-scraping from marketwatch.com. We use .json files for saving information such as username, password and your balance. The reasoning behind this is that more advanced systems is not needed for a game like Stonk.
  

## Project file
You can find more information about the code in the project file.

[Stonk>src>main>java>stonk...](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/src/main/java/stonk)

## FXML and json files

[Stonk>src>main>resources>stonk...](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2135/gr2135/-/tree/master/Stonk/src/main/resources/stonk)



## Project architecture

Core: 
<a href="https://ibb.co/m5qkn3G"><img src="https://i.ibb.co/qYsKHvd/core.png" width=650px height=150px alt="core" border="0"></a>

Controllers:
<a href="https://ibb.co/vVPt07B"><img src="https://i.ibb.co/mbqxfLB/controller.png" width=650px height=150px alt="controller" border="0"></a>

Fxml files:
<a href="https://ibb.co/QDkT74X"><img src="https://i.ibb.co/jJzQ9SH/fxml.png" width=650px height=150px alt="fxml" border="0"></a>