package trade.model.vo;

public class TradeMember {

	private int manner;
	private String filepath;

	public TradeMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TradeMember(int manner, String filepath) {
		super();
		this.manner = manner;
		this.filepath = filepath;
	}

	public int getManner() {
		return manner;
	}

	public void setManner(int manner) {
		this.manner = manner;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
