
import java.util.ArrayList;

/**
 * This class is the back end development of the Informative Music Database project. The HashTable
 * object stores Songs into a hash table specified by the SongADT interface, and via its parent, the
 * HashTableMap class.
 * 
 * @author Drew Paterno
 *
 */
public class HashTable extends HashTableMap<String, Song> implements SongADT {

  private ArrayList<Song> songs; // Unsorted, unhashed list of all song objects stored within the
                                 // HashTableMap.
  private ArrayList<String> names; // Unsorted, unhashed list of the names of songs stored within
                                   // the HashTableMap.

  /**
   * Default constructor. Specifies default file paths.
   */
  public HashTable() {
    this("SongList.txt", "LinkList.txt");
  }

  /**
   * Initializes ArrayLists, and hashes a list of songs into the HashTableMap.
   * 
   * @param songs Path of .txt file containing Songs to be stored in hashTable.
   * @param links Path of .txt file containing Spotify links of Songs to be stored.
   */
  public HashTable(String songs, String links) {
    super();
    this.songs = new ArrayList<Song>();
    this.names = new ArrayList<String>(); // Initializes ArrayLists.
    ArrayList<Song> songList = new LoadSongList(songs, links).load(); // Converts text files of
                                                                      // songs and links into an
                                                                      // ArrayList.
    for (Song i : songList) {
      this.put(i); // Propagates through ArrayList, hashes songs using HashTableMap.
    }
  }

  /**
   * Finds and returns all songs by a given artist.
   * 
   * @param artist Name of the artist to search for.
   * @return String containing all the songs by the given artist within the hashTable.
   */
  @Override
  public String songsOneArtist(String artist) {
    String toReturn = "";
    int counter = 0;
    for (Song element : songs)
      if (element.getArtist().equalsIgnoreCase(artist)) {
        toReturn += element.getName() + ", ";
        counter++;
      }
    if (toReturn.equals(""))
      return artist + " does not have any songs in the database.";
    else
      return artist + " has " + counter + " song(s) in the database:\n"
          + toReturn.substring(0, toReturn.length() - 2);
  }

  /**
   * Adds songs to hashTable using HashTableMap.
   * 
   * @param song Reference to the Song object to be inserted.
   * @return True if the key-value pair is added to the hashTable, false if the key already exists
   *         within the hash table.
   */
  public boolean put(Song song) {
    if (!names.contains(song.getName())) { // Checks to see if song is not in unsorted list.
      names.add(song.getName()); // Adds name of song to the unsorted list of song names.
      songs.add(song); // Adds song object to the unsorted list of song objects.
    }
    return super.put(song.getName(), song); // Inserts song into hashTable using HashTableMap.
  }

  /**
   * Removes a song from the hashTable given the name of the song.
   * 
   * @param song Name of the song to be removed.
   * @return Song object which was removed from the hashTable.
   */
  @Override
  public Song remove(String name) {
    if (names.contains(name)) { // Checks if song is in unsorted lists.
      songs.remove(names.indexOf(name)); // Removes name of song from the unsorted list of song names.
      names.remove(names.indexOf(name)); // Removes song object from the unsorted list of song objects.
    }
    return super.remove(name); // Removes song from hashTable using HashTableMap.
  }

  /**
   * Returns the number of songs stored within the hashTable.
   * 
   * @return Integer representation of how many songs are stored within the hashTable.
   */
  @Override
  public int numberOfSongs() {
    return size();
  }

  /**
   * Returns the name of every song stored within the hashTable.
   * 
   * @return String containing the name of every song stored within the hashTable.
   */
  @Override
  public String allSongs() {
    return names.toString();
  }

  /**
   * Removes all songs from hashTable.
   * 
   * @return The number of songs cleared.
   */
  @Override
  public int clearList() {
    int toReturn = size();
    names.clear(); // Clears song names ArrayList.
    songs.clear(); // Clears songs ArrayList.
    clear(); // Clears HashTable.
    return toReturn;
  }

}
