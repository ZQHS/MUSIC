package Entity;

public class Song {
	private String gqname;
	private String gdname;
	private String gsname;
	private String userid;
	private String gqadd;
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Song(String gqname, String gdname, String gsname, String userid, String gqadd) {
		super();
		this.gqname = gqname;
		this.gdname = gdname;
		this.gsname = gsname;
		this.userid = userid;
		this.gqadd = gqadd;
	}
	public String getGqname() {
		return gqname;
	}
	public void setGqname(String gqname) {
		this.gqname = gqname;
	}
	public String getGdname() {
		return gdname;
	}
	public void setGdname(String gdname) {
		this.gdname = gdname;
	}
	public String getGsname() {
		return gsname;
	}
	public void setGsname(String gsname) {
		this.gsname = gsname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGqadd() {
		return gqadd;
	}
	public void setGqadd(String gqadd) {
		this.gqadd = gqadd;
	}
	@Override
	public String toString() {
		return "Song [gqname=" + gqname + ", gdname=" + gdname + ", gsname=" + gsname + ", userid=" + userid
				+ ", gqadd=" + gqadd + "]";
	}
	

}
