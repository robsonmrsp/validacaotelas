package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.List;

public class TestData {
	public static List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<Stock>();

		stocks.add(new Stock("Apple Inc.", "AAPL", 125.64, 123.43));
		stocks.add(new Stock("Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
		stocks.add(new Stock("Google Inc.", "GOOG", 516.2, 512.6));
		stocks.add(new Stock("Intel Corporation", "INTC", 21.36, 21.53));
		stocks.add(new Stock("Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
		stocks.add(new Stock("Microsoft Corporation", "MSFT", 29.56, 29.72));
		stocks.add(new Stock("Nokia Corporation (ADR)", "NOK", 27.83, 27.93));
		stocks.add(new Stock("Oracle Corporation", "ORCL", 18.73, 18.98));
		stocks.add(new Stock("Starbucks Corporation", "SBUX", 27.33, 27.36));
		stocks.add(new Stock("Yahoo! Inc.", "YHOO", 26.97, 27.29));
		stocks.add(new Stock("Applied Materials, Inc.", "AMAT", 18.4, 18.66));
		stocks.add(new Stock("Comcast Corporation", "CMCSA", 25.9, 26.4));
		stocks.add(new Stock("Sirius Satellite", "SIRI", 2.77, 2.74));

		stocks.add(new Stock("Tellabs, Inc.", "TLAB", 10.64, 10.75));
		stocks.add(new Stock("eBay Inc.", "EBAY", 30.43, 31.21));
		stocks.add(new Stock("Broadcom Corporation", "BRCM", 30.88, 30.48));
		stocks.add(new Stock("CMGI Inc.", "CMGI", 2.14, 2.13));
		stocks.add(new Stock("Amgen, Inc.", "AMGN", 56.22, 57.02));
		stocks.add(new Stock("Limelight Networks", "LLNW", 23, 22.11));
		stocks.add(new Stock("Amazon.com, Inc.", "AMZN", 72.47, 72.23));

		stocks.add(new Stock("E TRADE Financial Corporation", "ETFC", 24.32, 24.58));
		stocks.add(new Stock("AVANIR Pharmaceuticals", "AVNR", 3.7, 3.52));
		stocks.add(new Stock("Gemstar-TV Guide, Inc.", "GMST", 4.41, 4.55));
		stocks.add(new Stock("Akamai Technologies, Inc.", "AKAM", 43.08, 45.32));
		stocks.add(new Stock("Motorola, Inc.", "MOT", 17.74, 17.69));
		stocks.add(new Stock("Advanced Micro Devices, Inc.", "AMD", 13.77, 13.98));
		stocks.add(new Stock("General Electric Company", "GE", 36.8, 36.91));
		stocks.add(new Stock("Texas Instruments Incorporated", "TXN", 35.02, 35.7));
		stocks.add(new Stock("Qwest Communications", "Q", 9.9, 10.03));
		stocks.add(new Stock("Tyco International Ltd.", "TYC", 33.48, 33.26));
		stocks.add(new Stock("Pfizer Inc.", "PFE", 26.21, 26.19));
		stocks.add(new Stock("Time Warner Inc.", "TWX", 20.3, 20.45));
		stocks.add(new Stock("Sprint Nextel Corporation", "S", 21.85, 21.76));
		stocks.add(new Stock("Bank of America Corporation", "BAC", 49.92, 49.73));
		stocks.add(new Stock("Taiwan Semiconductor", "TSM", 10.4, 10.52));
		stocks.add(new Stock("AT&T Inc.", "T", 39.7, 39.66));
		stocks.add(new Stock("United States Steel Corporation", "X", 115.81, 114.62));
		stocks.add(new Stock("Exxon Mobil Corporation", "XOM", 81.77, 81.86));
		stocks.add(new Stock("Valero Energy Corporation", "VLO", 72.46, 72.6));
		stocks.add(new Stock("Micron Technology, Inc.", "MU", 12.02, 12.27));
		stocks.add(new Stock("Verizon Communications Inc.", "VZ", 42.5, 42.61));
		stocks.add(new Stock("Avaya Inc.", "AV", 16.96, 16.96));
		stocks.add(new Stock("The Home Depot, Inc.", "HD", 37.66, 37.79));

		stocks.add(new Stock("First Data Corporation", "FDC", 32.7, 32.65));
		return stocks;

	}

}
