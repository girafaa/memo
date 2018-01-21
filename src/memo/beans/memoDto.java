package memo.beans;

public class memoDto {
	private int no;
	private String id;
	private String day;
	private String lastupdate;
	private String detail;
	@Override
	public String toString() {
		return "memoDto [no=" + no + ", id=" + id + ", day=" + day + ", lastupdate=" + lastupdate + ", detail=" + detail
				+ "]";
	}
	public memoDto() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
}
