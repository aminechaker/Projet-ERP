package tn.esprit.b2.esprit1718b2erp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.ext.ParamConverter.Lazy;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Quotes;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;
/**
 * Session Bean implementation class MarketDataJson
 */
@Stateless
@LocalBean
public class MarketDataJson  implements MarketDataJsonRemote, MarketDataJsonLocal {

	public List<Quotes> chargerData() {
		String restYahooQuery = "https://marketdata.websol.barchart.com/getQuote.json?apikey=2b418cb562f5f2a440a2e53821fef368&symbols=TWTR,GOOGL,AAPL,NVDA,NFLX,WFC,STZ,CAT,NKE,MCD,FORD,ORCL,JNJ,SYF,MSFT,INTC,BA,CSCO,DAL,DIS,HD,NXPI,CVX,RIG,PBR";
		//String restYahooQuery = "https://marketdata.websol.barchart.com/getQuote.json?apikey=2b418cb562f5f2a440a2e53821fef368&symbols=TWTR";
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(restYahooQuery);
		List<Quotes> list = new ArrayList<>();
		try {
			HttpResponse response = client.execute(request);
			String jsonResponse = EntityUtils.toString(response.getEntity());
			JSONObject responseObj = new JSONObject(jsonResponse);
			JSONArray result = responseObj.getJSONArray("results");
			
					for (int i = 0; i < result.length(); i++) {
						JSONObject data = result.getJSONObject(i);
						Quotes quotes = new Quotes();
						quotes.setOpen(data.getDouble("open") + "");
						quotes.setClose(data.getDouble("close") + "");
						quotes.setHigh(data.getDouble("high") + "");
						quotes.setLastPrice(data.getDouble("lastPrice") + "");
						quotes.setLow(data.getDouble("low") + "");
						quotes.setName(data.getString("name") + "");
						quotes.setNetChange(data.getDouble("netChange") + "");
						quotes.setPercentChange(data.getDouble("percentChange") + "");
						quotes.setSymbol(data.getString("symbol") + "");
						quotes.setTradeTimestamp(data.getString("tradeTimestamp") + "");
						quotes.setVolume(data.getInt("volume") + "");
						list.add(quotes);
					}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
