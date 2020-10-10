// --== CS400 File Header Information ==--
// Name: Andy Lin
// Email: <alin47@wisc.edu>
// Team: <NB>
// Role: <Test Engineer>
// TA: <Daniel Finer>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

/**
 * Ensures the Program works correctly by checking the functionality of all methods used by the
 * FrontEnd, BackEnd, and DataWrangler.
 * 
 * @author Andy Lin
 *
 */
public class TestSong {
  /**
   * Tests whether size() is initialized correctly and whether songsOneArtist() returns the correct
   * output when given an artist that exists and doesn't exist.
   * 
   * @return true if size() and songsOneArtist() performs the correct functionality, otherwise
   *         false.
   */
  public static boolean test1() {
    HashTable SongTable = new HashTable("SongList.txt", "LinkList.txt");
    if (SongTable.numberOfSongs() != 1404)
      return false; // 143 because there are duplicate songs.
    if (!SongTable.songsOneArtist("Queen").equals("Queen has 6 song(s) in the database:\n" + 
        "Another One Bites The Dust, Under Pressure, Bohemian Rhapsody, We Will Rock You, Somebody To Love, Crazy Little Thing Called Love"))
      return false;
    if (!SongTable.songsOneArtist("keshi").equals("keshi does not have any songs in the database.")) // checks input DNE.
      return false;
    if (!SongTable.songsOneArtist(null).equals("null does not have any songs in the database.")) //checks null input.
      return false;
    return true;
  }
  
  /**
   * Checks whether or not the put method works correctly when given an existing and non-existing
   * input. Also checks size is updated or not accordingly, and prints the newly added song to 
   * check if song was correctly added to database.
   * 
   * @return true if put performs the correct functionality, otherwise false.
   */
  public static boolean test2() {
    HashTable SongTable = new HashTable("SongList.txt", "LinkList.txt");
    SongTable.put(new Song("Righteous", "Juice Wrld", "None",
        "https://open.spotify.com/track/2U5WueTLIK5WJLD7mvDODv?si=GhF1wTmRTuGVBjdw5kjwqg", "2020")); 
    if (SongTable.numberOfSongs() != 1405) //checks size is updated.
      return false;
    if (!SongTable.songsOneArtist("Juice Wrld")
        .equals("Juice Wrld has 1 song(s) in the database:\n" + "Righteous")) //checks song is in database.
      return false;
    if (SongTable.put(new Song("Another One Bites The Dust", "Queen", "Remastered", 
        "https://open.spotify.com/track/57JVGBtBLCfHw2muk5416J", "2011")) != false) //checks existing song not added.
      return false;
    if (SongTable.numberOfSongs() != 1405) //checks size is not updated.
      return false;
    return true;
  }
  
  /**
   * Checks Whether or not the remove method works correctly when given an existing and 
   * non-existing string. Also checks size is updated or not accordingly, and if the song name still
   * exists after being removed.
   * 
   * @return true if remove performs the correct functionality, otherwise false.
   */
  public static boolean test3() {
    HashTable SongTable = new HashTable("SongList.txt", "LinkList.txt");
    if (!SongTable.remove("Whole Lotta Love").getName().equals("Whole Lotta Love")) //checks removed song name matches input.
      return false; 
    if (SongTable.numberOfSongs() != 1403) //checks size is updated.
      return false;
    if (SongTable.containsKey("Whole Lotta Love")) // checks database no longer contains removed song.
      return false;
    if ((SongTable.remove("Disturbia") != null)) //checks non-existing song name returns null.
      return false;
    if (SongTable.numberOfSongs() != 1403) //makes sure size is not updated.
      return false;
    return true;
  }
  /**
   * Checks whether or not clearList method deletes every song name and their appropriate properties.
   * Also ensures size is updated accordingly.
   * 
   * @return true if clearList performs correctly functionality, otherwise false.
   */
  public static boolean test4() {
    HashTable SongTable = new HashTable("SongList.txt", "LinkList.txt");
    if (SongTable.clearList() != 1404) //ensures 1404 songs are removed.
      return false;
    if (SongTable.size() != 0) // ensures size is updated correctly.
      return false;
    return true;
  }
  
  /**
   * Tests whether or not allSongs() correctly prints all songs.
   * 
   * @return true if allSongs() performs correctly functionality, otherwise false.
   */
  public static boolean test5() {
    HashTable SongTable = new HashTable("SongList.txt", "LinkList.txt"); //remove all songs since database too big to check.
    SongTable.clearList();
    SongTable.put(new Song("The Reaper", "Keshi", "none", "none", "none"));
    SongTable.put(new Song("3 Nights", "Dominic Fike", "none", "none", "none"));
    SongTable.put(new Song("Pretty Boy", "Joji", "none", "none", "none"));
    SongTable.put(new Song("Circles", "Post Malone", "none", "none", "none"));
    if (!SongTable.allSongs().equals("[The Reaper, 3 Nights, Pretty Boy, Circles]")) //checks if allSongs prints all songs in database.
      return false;
    return true;
  }
  
  /**
   * Tests whether or not LoadSongList will print error when given a non-existing file.
   * 
   * @return true if LoadSongList performs the correct functionality, otherwise false.
   */
  public static boolean test6() {
    LoadSongList test = new LoadSongList("Playlist.txt", "LinkList.txt"); //Playlist.txt DNE.
    test.load();
    return true;
  }
  
  /**
   * Checks whether or not Song object stores the correct artist, date, link, name, and version.
   * 
   * @return true if Song performs the correct functionality, otherwise false.
   */
  public static boolean test7() {
    Song testSong = new Song("The Reaper", "Keshi", "none",
        "https://open.spotify.com/track/4vHoOPIlILceWMPGY5XEmJ?si=P2ex_ZSlRjqBgWFKA3A8sA", "2018");
    if (!testSong.getArtist().equals("Keshi"))
      return false;
    if (!testSong.getDate().equals("2018"))
      return false;
    if (!testSong.getLink().equals(
        "https://" + "open.spotify.com/track/4vHoOPIlILceWMPGY5XEmJ?si=P2ex_ZSlRjqBgWFKA3A8sA"))
      return false;
    if (!testSong.getName().equals("The Reaper"))
      return false;
    if (!testSong.getVersion().equals("none"))
      return false;
    return true;
  }
  
  /**
   * Checks all test methods making sure they return true or print the correct messages.
   * 
   * @param args not used.
   */
  public static void main(String[] args) {
    System.out.println("Testing songsOneArtist():" + test1());
    System.out.println("Testing put():" + test2());
    System.out.println("Testing remove():" + test3());
    System.out.println("Testing ClearList()" + test4());
    System.out.println("Testing allSongs()" + test5());
    System.out.println("Testing LoadSongList() P.S The stack trace should be printed : "+ test6());
    System.out.println("Testing Song Class" + test7());
  }
}
