package POJOLearning;

public class CoinDcxRateFile {
	
	private String coin;
	private double priceInInr;
	private int volumeInInr;
	private String platform;
	
	
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public double getPriceInInr() {
		return priceInInr;
	}
	public void setPriceInInr(double priceInInr) {
		this.priceInInr = priceInInr; 
	}
	public int getVolumeInInr() {
		return volumeInInr;
	}
	public void setVolumeInInr(int volumeInInr) {
		this.volumeInInr = volumeInInr;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}


}
