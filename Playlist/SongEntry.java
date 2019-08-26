
public class SongEntry {

	private String uniqueID;
	private String songName;
	private String artistName;
	int songLength;
	SongEntry nextNode;
	
	public SongEntry() {
		uniqueID = "none";
		songName = "none";
		artistName = "none";
		songLength = 0;
		nextNode = null;
	}
	
	public SongEntry(String uniqueId, String songName, String artistName, int songLength) {
		this.uniqueID = uniqueId;
		this.songName = songName;
		this.artistName = artistName;
		this.songLength = songLength;
	}
	
	public void insertAfter(SongEntry newNode) {
		SongEntry tmpNext;
		
		tmpNext = this.nextNode;
		this.nextNode = newNode;
		newNode.nextNode = tmpNext;
	}
	
	public void setNext(SongEntry nextNode) {
		this.nextNode = nextNode;
	}

	public String getID() {
		return uniqueID;
	}
	
	public String getSongName() {
		return songName;
	}
	
	public String getArtistName() {
		return artistName;
	}
	
	public int getSongLength() {
		return songLength;
	}
	
	public SongEntry getNext() {
		return nextNode;
	}
	
	public void printPlaylistSongs() {
		System.out.println("Unique ID: " + uniqueID + "\n" + 
				"Song Name: " + songName + "\n" + 
				"Artist Name: " + artistName + "\n" + 
				"Song Length (in seconds): " + songLength + "\n");
	}
}
