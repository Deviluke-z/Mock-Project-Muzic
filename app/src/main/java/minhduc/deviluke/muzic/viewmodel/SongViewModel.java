package minhduc.deviluke.muzic.viewmodel;

import android.app.Application;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.service.MusicPlayer;

// to query songs
public class SongViewModel extends AndroidViewModel {

  private final MutableLiveData<List<SongModel>> mLiveDataListSong = new MutableLiveData<>();

  private MusicPlayer mMusicPlayer = MusicPlayer.getInstance(getApplication());

  public SongViewModel(@NonNull Application application) {
    super(application);
  }

  // 1. query all songs in device + init list song to Music Player
  public void fetchSong() {
    Uri mMediaStoreUri;
    List<SongModel> mListSong = new ArrayList<>();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      mMediaStoreUri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
    } else {
      mMediaStoreUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }

    // projection a.k.a table
    String[] mSongProjection = new String[]{
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.DISPLAY_NAME,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.SIZE,
        MediaStore.Audio.Media.ALBUM_ID
    };

    // sorting
    String mSorting = MediaStore.Video.Media.DISPLAY_NAME + " ASC";

    // query songs
    try (Cursor cursor = getApplication().getContentResolver().query(
        mMediaStoreUri, mSongProjection, null, null, mSorting
    )) {

      int mSongIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
      int mSongTitleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
      int mSongArtistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
      int mSongDurationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
      int mSongSizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);
      int mSongThumbnailColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);

      // clear previous loader before running new loader
      while (cursor.moveToNext()) {
        long mSongId = cursor.getLong(mSongIdColumn);
        String mSongTitle = cursor.getString(mSongTitleColumn);
        String mSongArtist = cursor.getString(mSongArtistColumn);
        int mSongDuration = cursor.getInt(mSongDurationColumn);
        int mSongSize = cursor.getInt(mSongSizeColumn);
        long mSongThumbnail = cursor.getLong(mSongThumbnailColumn);

        // song uri
        Uri songUri = ContentUris.withAppendedId(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            mSongId
        );

        // song thumbnail
        Uri thumbnailUri = ContentUris.withAppendedId(
            Uri.parse("content://media/external/audio/albumart"),
            mSongThumbnail
        );

        // song
        SongModel songModel = new SongModel(
            mSongTitle, mSongArtist, songUri, thumbnailUri, mSongDuration, mSongSize
        );

        // add song to list
        mListSong.add(songModel);
        Log.d("Debug", mSongTitle);
      }
      cursor.close();

      // create list song for Music Player
      mMusicPlayer.initListSong(mListSong);

      // post value
      mLiveDataListSong.postValue(mListSong);
      Log.d("Debug", "" + mListSong.size());
    }
  }

  public LiveData<List<SongModel>> getLiveDataListSong() {
    return mLiveDataListSong;
  }
  // end 1
}
