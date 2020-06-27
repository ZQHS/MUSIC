package Entity;

public class Music {
	private int musicid;
	private String music;
	private String authorName;
	private String musicAdd;
	
	public Music() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public Music(int musicid, String music, String authorName, String musicAdd) {
		super();
		this.musicid = musicid;
		this.music = music;
		this.authorName = authorName;
		this.musicAdd = musicAdd;
	}
	
	public int getMusicid() {
		return musicid;
	}

	public void setMusicid(int musicid) {
		this.musicid = musicid;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getMusicAdd() {
		return musicAdd;
	}

	public void setMusicAdd(String musicAdd) {
		this.musicAdd = musicAdd;
	}

	@Override
	public String toString() {
		return "Music [musicid=" + musicid + ", music=" + music + ", authorName=" + authorName + ", musicAdd="
				+ musicAdd + "]";
	}

}
