
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class takes in two file names, reads the files, and return the
 * information in an ArrayList of Song.class instances
 * 
 *
 */
public class LoadSongList {
	private String songFileName;
	private String linkFileName;

	/**
	 * constructor that takes in two file names
	 * 
	 * @param songFileName the file name/ path for songs
	 * @param linkFileName the file name/ path for links
	 */
	public LoadSongList(String songFileName, String linkFileName) {
		this.songFileName = songFileName;
		this.linkFileName = linkFileName;
	}

	/**
	 * this method read through the txt files and store each line in a Song
	 * instance, then add it into the ArrayList
	 * 
	 * @return the ArrayList of Song instances
	 */
	public ArrayList<Song> load() {
		File file = new File(songFileName);
		File links = new File(linkFileName);
		ArrayList<Song> songList = new ArrayList<>();
		Scanner sc;
		Scanner fileSc;

		try {
			sc = new Scanner(file);
			fileSc = new Scanner(links);
			while (sc.hasNextLine()) {
				String song = sc.nextLine();
				if (song.equals(""))continue;
				String link = fileSc.nextLine();

				String[] array1 = song.split(" - ");

				if (array1.length != 2 && array1.length != 4 && array1.length != 3)
					songList.add(new Song(song, "none", "none", link, "none"));// the string might include multiple "-"
				if (array1.length == 2)
					songList.add(new Song(array1[1].trim(), array1[0].trim(), "none", link, "none"));// only song name and artist
				if (array1.length == 3)
					songList.add(new Song(array1[1].trim(), array1[0].trim(), array1[2].trim(), link, "none"));//song, name, artist, and version
				if (array1.length == 4)
					songList.add(
							new Song(array1[1].trim(), array1[0].trim(), array1[2].trim(), link, array1[3].trim()));// song, name, artist, version, and date
				}
			sc.close();
			fileSc.close();
			}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return songList;

	}
	}

