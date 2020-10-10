
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.NoSuchElementException; 
public class SongFrontEnd {
  
  private Scanner input; 
  private Song mySong; 
  
  /*
   * method that lists the commands from, 1 to 7 and 10 the user can input and 
   * returns the int that the user put for the input
   * 
   * @param none
   * @return int number that the user input 
   * otherwise
   */
  public static int menu() {
    Scanner input = new Scanner(System.in); 
    System.out.println("============================"); 
    String welcome = "Welcome to your song database\n"; 
    String string1 = "1. Find song. Enter a song name\n"; 
    String string2 = "2. Check if artist exists. Enter an artist\n"; 
    String string3 = "3. Add a song: enter song name, artist, version, link, and date\n"; 
    String string4 = "4. Remove a song by name. Enter a song name\n"; 
    String string5 = "5. Print out all the names and values of all songs and number of songs\n"; 
    String string6 = "6. Find out how many songs are in the library\n"; 
    String string7 = "7. Clear all songs\n"; 
    String string10 = "10. Exit"; 
    System.out.println(welcome + string1 + string2 + string3 + string4 + string5 + string6 + string7 + string10);
    
    Integer number = 0; 
    boolean inputNotNull = true;
    while (inputNotNull) {
        String line = input.nextLine();
        number = null;
        try {
            number = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.err.println("Wrong input! Input only integer numbers please: ");
            continue;
        }
        inputNotNull = false;
    }

        return number; 
  }
  
  /*
   * method that gets song from songHashtable
   * 
   * @param songHashtable, songName
   * @return none
   */
  public static void getMySong(HashTable songHashtable, String songName) { 
    try {
      Song song = songHashtable.get(songName); 
      System.out.println("song: " + song); 
    } catch (NoSuchElementException e1) {
      System.out.println("Song not found"); 
    }
  }
  
  
  /*
   * this method calls the menu() method and handles the cases for the user input
   * 
   * @param args
   * @return none
   */
  public static void main(String[] args) {   
     // ArrayList<Song> songList= new ArrayList<>(); 
     // LoadSongList loadSongList = new LoadSongList("SongList.txt", "LinkList.txt"); 
     // songList = loadSongList.load(); 
     
     HashTable songHashtable = new HashTable("SongList.txt", "LinkList.txt"); 
     
     // for debug
     // System.err.println("ArayList size: " + songList.size()); 
     
    // for (int i = 0; i < songList.size(); i++) {
     //  songHashtable.put(songList.get(i).getName(), songList.get(i)); 
    // }
     
     // System.err.println("hash table size " + songHashtable.size()); 
     
     int choice = 0; 
    
      Song mySong; 
      
        while (choice != 10) { // while user chooses not to exit
          choice = menu(); 
          switch (choice) {
            case 1: 
              System.out.println("In case 1"); 
              Scanner input1 = new Scanner(System.in); 
              System.out.println("Enter a song name: "); 
              String stringInput1 = input1.nextLine(); 
              getMySong(songHashtable, stringInput1); 
              break; 
            case 2: 
              Scanner input2 = new Scanner(System.in); 
              System.out.println("You picked 2. Check if artist exists. Enter an artist"); 
              String stringInput2 = input2.nextLine(); 
              String artistSongList = songHashtable.songsOneArtist(stringInput2); 
              if (!artistSongList.equals(null)) {
                System.out.println("Artist: " + stringInput2 + "\n" + artistSongList); 
              }
              break; 
            case 3: 
              Scanner input3 = new Scanner(System.in);     
              
              System.out.println("You picked 3.");  
              System.out.println("Enter song name: "); 
              String name1 = input3.nextLine();  
              
              if (songHashtable.containsKey(name1)) {
                System.out.println("Song already exists"); 
                break; 
              }
              
              System.out.println("Enter artist: "); 
              String artist1 = input3.nextLine(); 
              System.out.println("Enter version: "); 
              String version1 = input3.nextLine(); 
              System.out.println("Enter link: "); 
              String link1 = input3.nextLine(); 
              System.out.println("Enter date: "); 
              String date1 = input3.nextLine(); 
              mySong = new Song(name1, artist1, version1, link1, date1); 
              boolean putSuccessful = songHashtable.put(mySong); 
              if (putSuccessful) {
                System.out.println("song successfully added"); 
              }
              break; 
            case 4: 
              Scanner input4 = new Scanner(System.in);      
              System.out.println("You picked 4. Remove a song by name. Enter a song name"); 
              String inputString = input4.nextLine(); 
              Song removedSong = songHashtable.remove(inputString); 
              if (removedSong == null) {
                System.out.println("Song not found"); 
              }
              else {
                System.out.println("removed song: " + removedSong); 
              }
              break; 
            case 5: 
              System.out.println("You picked 5. Print out all the names and values of all songs and number of songs"); 
              System.out.println(songHashtable.allSongs()); 
              break; 
            case 6: 
              System.out.println("You picked 6. Find out how many songs are in the library"); 
              System.out.println("Number of songs: " + songHashtable.size()); 
              break;
            case 7: 
              System.out.println("removed all " + songHashtable.clearList() + " songs from the song list"); 
              break; 
            case 10:              
              System.out.println("Goodbye. Have a good day"); 
              break; 
            default: 
              System.out.println("invalid input"); 
          }
      }


  
  }
}
