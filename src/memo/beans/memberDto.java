package memo.beans;

public class memberDto {
	private String id;
	private String pw;
	private String phone;
	
	@Override
	public String toString() {
		return "memberDto [id=" + id + ", pw=" + pw + ", phone=" + phone + "]";
	}	
	
	public memberDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	
	
	
}
