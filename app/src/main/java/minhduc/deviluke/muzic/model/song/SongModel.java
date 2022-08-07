package minhduc.deviluke.muzic.model.song;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "song_table")
public class SongModel {

  @PrimaryKey(autoGenerate = true)
  private int mId;

  @ColumnInfo(name = "song_title")
  private String mSongTitle;

  @ColumnInfo(name = "song_artist")
  private String mSongArtist;

  @ColumnInfo(name = "song_thumbnail")
  private int mSongThumbnail;

  @ColumnInfo(name = "song_duration")
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

  public void setSongDuration(int mSongDuration) {
    this.mSongDuration = mSongDuration;
  }

}
