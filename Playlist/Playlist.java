import java.util.*;

public class Playlist {
	static Scanner input = new Scanner(System.in);
	static String id = "none";
	static String listName = "none";
	static String name = "none";
	static String artist = "none";
	static int length = 0;
	static int songCount = 0; // count the length of the nodes
	static SongEntry song = null;
	static List<SongEntry> songList = new ArrayList<SongEntry>();
	
	public static void main(String[] args) {
		System.out.println("Enter playlist's title:\n");
		listName = input.nextLine();
		printMenu();
		
		while (input.hasNext()) {
			String userChoice = input.nextLine();
			
			if (userChoice == null || userChoice.equals("") || userChoice.equals("\n")) {
				continue;
			}
			
			if (userChoice.equals("q")) {
				return;
			}
			
			if (userChoice.equals("a")) {
				addSong();
			}
			
			if (userChoice.equals("d")) {
				removeSong();
			}
			
			if (userChoice.equals("c")) {
				changePositionOfSong();
			}
			
			if (userChoice.equals("s")) {
				outputSongsByArtist();
			}
			
			if (userChoice.equals("t")) {
				outputTotalTimeOfPlayList();
			}
			
			if (userChoice.equals("o")) {
				fullPlaylist();
			}
			printMenu();
		}
	}

	public static void printMenu() {
		System.out.println(listName + " PLAYLIST MENU\n" + 
				"a - Add song\n" + 
				"d - Remove song\n" + 
				"c - Change position of song\n" + 
				"s - Output songs by specific artist\n" + 
				"t - Output total time of playlist (in seconds)\n" + 
				"o - Output full playlist\n" + 
				"q - Quit\n" + 
				"\n" + 
				"Choose an option:");
	}
	
	public static void fullPlaylist() {
		System.out.println(listName + " - OUTPUT FULL PLAYLIST");
		
		if (songList.size() == 0) {
			System.out.println("Playlist is empty\n");
		}
		else {
			for (int i = 0; i < songList.size(); i++) {
				System.out.println(i+1+".");
				songList.get(i).printPlaylistSongs();
			}
		}
	}
	
	public static void addSong() {
		System.out.println("ADD SONG");
		System.out.println("Enter song's unique ID:");
		id = input.nextLine();
		System.out.println("Enter song's name:");
		name = input.nextLine();
		System.out.println("Enter artist's name:");
		artist = input.nextLine();
		System.out.println("Enter song's length (in seconds):");
		length = input.nextInt();
		System.out.println();
		song = new SongEntry(id, name, artist, length);
		songList.add(song);
	}
	
	public static void removeSong() {
		System.out.println("REMOVE SONG");
		System.out.println("Enter song's unique ID:");
		id = input.nextLine();

		SongEntry songEntry = new SongEntry();
		
		for (int i = 0; i < songList.size(); i ++) {
			songEntry = songList.get(i);
			if (songEntry.getID().equals(id)) {
				break;
			}
			else {
				songEntry = null;
			}
		}
		
		if(songEntry != null) {
			System.out.println("\"" + songEntry.getSongName() + "\" removed.\n");
			songList.remove(songEntry);
		}
	}
	
	public static void outputTotalTimeOfPlayList() {
		System.out.println("OUTPUT TOTAL TIME OF PLAYLIST (IN SECONDS)");
		int totalTime = 0;
		SongEntry songEntry = new SongEntry();
		for(int i = 0; i < songList.size(); i++) {
			songEntry = songList.get(i);
			totalTime += songEntry.getSongLength();
		}

		System.out.println("Total time: " + totalTime + " seconds\n");
	}

	public static void outputSongsByArtist() {
		System.out.println("OUTPUT SONGS BY SPECIFIC ARTIST");
		System.out.println("Enter artist's name:");
		artist = input.nextLine();
		SongEntry songEntry = new SongEntry();
		for (int i = 0; i < songList.size(); i++) {
			songEntry = songList.get(i);
			if (songEntry.getArtistName().equals(artist)) {
				System.out.println((i + 1) + ".");
				songEntry.printPlaylistSongs();
			}
		}
	}

	public static void changePositionOfSong() {
		
		int oldPosition = 0;
		int newPosition = 0;
		
		System.out.println("CHANGE POSITION OF SONG");
		System.out.println("Enter song's current position:");
		oldPosition = Integer.parseInt(input.nextLine());
		
		System.out.println("Enter new position for song:");
		newPosition = Integer.parseInt(input.nextLine());
        
        SongEntry oldSongEntry = songList.get(oldPosition - 1);
        
        // when newPosition < oldPosition, oldPosition + 1
        if (newPosition < oldPosition) {
            oldPosition++;
        }
        else {
        	// add oldSongEntry to newPosition
        	newPosition++;
        }
        
        // add oldSongEntry to newPosition
        songList.add(newPosition - 1, oldSongEntry);
        
        //remove oldSongEntry
        songList.remove(oldPosition - 1);
        
        newPosition = newPosition - 1;
        
        System.out.println("\"" + oldSongEntry.getSongName() + "\" moved to position " + newPosition + "\n");
    }

}
