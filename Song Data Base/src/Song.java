
public class Song {

/**
 * This class models and implements a song which has a name, a singer and year of release
 * This is an instantiable class that will be used to represent songs entering the Karoke line. 
 * Each song object will have a name, a singer and year of release.
 */

  private String name; // a String representation of the name of the song
  private String artist; // a String representation of the name of the singer
  private String version; // version
  private String link; // URL to access the song
  private String date; 
  
  /**
   * Creates a song object
   * 
   * @param song name, artist, genre and version
   */
  public Song(String name, String artist, String version, String link, String date) {
    this.name = name;
    this.artist = artist;
    this.link = link;
    this.date = date; 
    this.version = version; 
  }


  /**
   * Returns the string representation of the name of the song
   * 
   * @return the name of the song
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns the string representation of the name of the singer
   * 
   * @return the name of the singer
   */
  public String getArtist() {
    return artist;
  }
  
  
  /**
   * Returns the version of the song
   * 
   * @return the version of the song
   */
  public String getVersion() {
    return version;
  }
  
  /**
   * Returns the song Link of the song
   * 
   * @return the song Link of the song
   */
  public String getLink() {
    return link;
  }
  
  public String getDate() {
    return date; 
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public void setDate(String date) {
    this.date = date;
  }
 
  /**
   * Returns a String representation of this song in the following format:
   * "name, artist, genre, version
   * songLink"
   * For instance "Today, John Denver, Folk, 1970 v1
   * https://www.youtube.com/watch?v=u5nyALPgxro"
   * 
   * @return a String representation of this song
   * 
   */
  @Override
  public String toString() {
    String returnString = new String();
    returnString = name + ", " + artist + ", " + ", " + version + ", " + date + "\n";
    returnString = returnString + this.link + "\n";
    return (returnString);
  }
}

