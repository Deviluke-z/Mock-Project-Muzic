package minhduc.deviluke.muzic.model.song;

import android.net.Uri;

public class SongModel {

  private String mTitle;
  private String mArtist;
  private Uri mUri;
  private Uri mThumbnailUri;
  private int mDuration;
  private int mSize;

  // constructor
  public SongModel(String mTitle, String mArtist, Uri mUri, Uri mThumbnailUri, int mDuration, int mSize) {
    this.mTitle = mTitle;
    this.mArtist = mArtist;
    this.mUri = mUri;
    this.mThumbnailUri = mThumbnailUri;
    this.mDuration = mDuration;
    this.mSize = mSize;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String mTitle) {
    this.mTitle = mTitle;
  }

  public String getArtist() {
    return mArtist;
  }

  public void setArtist(String mArtist) {
    this.mArtist = mArtist;
  }

  public Uri getUri() {
    return mUri;
  }

  public void setUri(Uri mUri) {
    this.mUri = mUri;
  }

  public Uri getThumbnailUri() {
    return mThumbnailUri;
  }

  public void setThumbnailUri(Uri mThumbnailUri) {
    this.mThumbnailUri = mThumbnailUri;
  }

  public int getDuration() {
    return mDuration;
  }

  public void setDuration(int mDuration) {
    this.mDuration = mDuration;
  }

  public int getSize() {
    return mSize;
  }

  public void setSize(int mSize) {
    this.mSize = mSize;
  }
}
