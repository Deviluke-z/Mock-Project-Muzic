package minhduc.deviluke.muzic.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.viewmodel.SongViewModel;

public class MusicPlayer {
  public int position = 0;
  private MediaPlayer mMediaPlayer;
  private static MusicPlayer instance;
  
  private Context mContext;

  private List<SongModel> mListSong = new ArrayList<>();
  
  private MusicPlayer(Context context) {
    this.mContext = context;
    mMediaPlayer = new MediaPlayer();}
  
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
  
  public List<SongModel> setListSong() {
    return mListSong;
  }
  
  public int getPosition() {
    return position;
  }
  
  public void setPosition(int position) {
    this.position = position;
    play(mListSong.get(position).getUri());
  }
  
  public void play(Uri songUri) {
    if (!mMediaPlayer.isPlaying()) {
      try {
        mMediaPlayer.setDataSource(mContext, songUri);
        mMediaPlayer.prepare();
        mMediaPlayer.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      Toast.makeText(mContext, "A song had been playing", Toast.LENGTH_SHORT).show();
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
  }
  
  public void previous() {
  }
  
  public void stop() {
    if (mMediaPlayer.isPlaying()) {
      mMediaPlayer.stop();
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
