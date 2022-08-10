package minhduc.deviluke.muzic.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import minhduc.deviluke.muzic.model.song.SongModel;

public class MusicPlayer {

  private static MusicPlayer instance;
  public int position = 0;
  public SongModel mCurrentSong;
  private MediaPlayer mMediaPlayer;
  private Context mContext;
  private List<SongModel> mListSong = new ArrayList<>();

  private MusicPlayer(Context context) {
    this.mContext = context;
    mMediaPlayer = new MediaPlayer();
  }

  public static MusicPlayer getInstance(Context context) {
    if (instance == null) {
      instance = new MusicPlayer(context);
    }
    return instance;
  }

  public void initListSong(List<SongModel> mListSong) {
    // init list song
    this.mListSong = mListSong;
  }

  // in-progress
  private void resetListSong(List<SongModel> mListSong) {
    mListSong.clear();
  }

  public List<SongModel> setListSong() {
    return mListSong;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;

    mCurrentSong = mListSong.get(position);
    play(mCurrentSong.getUri());
  }

  public void play(Uri songUri) {
    try {
      mMediaPlayer.reset();
      mMediaPlayer.setDataSource(mContext, songUri);
      mMediaPlayer.prepare();
      mMediaPlayer.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void continuePlay() {
    if (!mMediaPlayer.isPlaying()) {
      mMediaPlayer.start();
    } else {
      Toast.makeText(mContext, "A song is playing at the present", Toast.LENGTH_SHORT).show();
    }
  }

  public void pause() {
    if (mMediaPlayer.isPlaying()) {
      mMediaPlayer.pause();
    } else {
      Toast.makeText(mContext, "Nothing is playing at the present", Toast.LENGTH_SHORT).show();
    }
  }

  public void next() {
    if (mListSong.indexOf(mCurrentSong) == mListSong.size() - 1) {
      mMediaPlayer.reset();
      setPosition(0);
    } else {
      mMediaPlayer.reset();
      setPosition(mListSong.indexOf(mCurrentSong) + 1);
    }
  }

  public void previous() {
    if (mListSong.indexOf(mCurrentSong) == 0) {
      mMediaPlayer.reset();
      setPosition(mListSong.size() - 1);
    } else {
      mMediaPlayer.reset();
      setPosition(mListSong.indexOf(mCurrentSong) - 1);
    }
  }

  public void stop() {
    if (mMediaPlayer.isPlaying()) {
      mMediaPlayer.stop();
      mMediaPlayer.reset();
    } else {
      Toast.makeText(mContext, "Nothing is playing at the present", Toast.LENGTH_SHORT).show();
    }
  }

  public void seekTo(int progress) {
    mMediaPlayer.seekTo(progress);
  }

  public int getTotalDuration() {
    return mMediaPlayer.getDuration();
  }

  public int getRealTimeDuration() {
    return mMediaPlayer.getCurrentPosition();
  }
}
