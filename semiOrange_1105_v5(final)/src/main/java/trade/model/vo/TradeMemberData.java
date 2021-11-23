package trade.model.vo;

import java.util.ArrayList;

public class TradeMemberData {

	private Trade t;
	private TradeMember tm;
	private ArrayList<PostFrom> pf;

	public TradeMemberData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TradeMemberData(Trade t, TradeMember tm, ArrayList<PostFrom> pf) {
		super();
		this.t = t;
		this.tm = tm;
		this.pf = pf;
	}

	public Trade getT() {
		return t;
	}

	public void setT(Trade t) {
		this.t = t;
	}

	public TradeMember getTm() {
		return tm;
	}

	public void setTm(TradeMember tm) {
		this.tm = tm;
	}

	public ArrayList<PostFrom> getPf() {
		return pf;
	}

	public void setPf(ArrayList<PostFrom> pf) {
		this.pf = pf;
	}

}
