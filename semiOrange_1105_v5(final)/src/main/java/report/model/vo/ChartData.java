

package report.model.vo;

public class ChartData {
  private String date;
  private int joincnt;
public ChartData() {
	super();
	// TODO Auto-generated constructor stub
}
public ChartData(String date, int joincnt) {
	super();
	this.date = date;
	this.joincnt = joincnt;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getJoincnt() {
	return joincnt;
}
public void setJoincnt(int joincnt) {
	this.joincnt = joincnt;
}
  
}