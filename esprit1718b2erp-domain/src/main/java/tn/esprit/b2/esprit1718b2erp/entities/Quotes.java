package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Quotes
 *
 */
@Entity

public class Quotes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String open;
	private String close;
	private String high;
	private String lastPrice;
	private String low;
	private String name;
	private String netChange;
	private String percentChange;
	private String symbol;
	private String tradeTimestamp;
	private String volume;
	
	private static final long serialVersionUID = 1L;

	public Quotes() {
		super();
	}
	
	

	public Quotes(int id, String open, String close, String high, String lastPrice, String low, String name,
			String netChange, String percentChange, String symbol, String tradeTimestamp, String volume) {
		super();
		this.id = id;
		this.open = open;
		this.close = close;
		this.high = high;
		this.lastPrice = lastPrice;
		this.low = low;
		this.name = name;
		this.netChange = netChange;
		this.percentChange = percentChange;
		this.symbol = symbol;
		this.tradeTimestamp = tradeTimestamp;
		this.volume = volume;
	}

	

	public Quotes(String open, String close, String high, String lastPrice, String low, String name, String netChange,
			String percentChange, String symbol, String tradeTimestamp, String volume) {
		super();
		this.open = open;
		this.close = close;
		this.high = high;
		this.lastPrice = lastPrice;
		this.low = low;
		this.name = name;
		this.netChange = netChange;
		this.percentChange = percentChange;
		this.symbol = symbol;
		this.tradeTimestamp = tradeTimestamp;
		this.volume = volume;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetChange() {
		return netChange;
	}

	public void setNetChange(String netChange) {
		this.netChange = netChange;
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTradeTimestamp() {
		return tradeTimestamp;
	}

	public void setTradeTimestamp(String tradeTimestamp) {
		this.tradeTimestamp = tradeTimestamp;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	
	
}
