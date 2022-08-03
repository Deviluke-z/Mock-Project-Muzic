package minhduc.deviluke.muzic.model;

public class Song {
  private String mSongTitle;
  private String mSongArtist;
  private int mSongThumbnail;
  private int mSongDuration;

  public String getSongTitle() {
    return mSongTitle;
  }

  public void setSongTitle(String mSongTitle) {
    this.mSongTitle = mSongTitle;
  }

  public String getSongArtist() {
    return mSongArtist;
  }

  public void setSongArtist(String mSongArtist) {
    this.mSongArtist = mSongArtist;
  }

  public int getSongThumbnail() {
    return mSongThumbnail;
  }

  public void setSongThumbnail(int mSongThumbnail) {
    this.mSongThumbnail = mSongThumbnail;
  }

  public int getSongDuration() {
    return mSongDuration;
  }

  public void setmSongDuration(int mSongDuration) {
    this.mSongDuration = mSongDuration;
  }

}
