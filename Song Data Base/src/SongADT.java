// interface for HashTable implementation
import java.util.NoSuchElementException;

public interface SongADT {

  public boolean put(Song song);

  public Song get(String song) throws NoSuchElementException;

  public String songsOneArtist(String artist); // get first song object of that artist, null if not
                                               // found

  public Song remove(String song); // return the Song object being removed, null if not found

  public int numberOfSongs(); // return # of songs in list

  public String allSongs(); // String representation of all songs and details except the link

  public int clearList(); // remove all songs from the list, 0 if list is empty

}

