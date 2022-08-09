package minhduc.deviluke.muzic.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.viewmodel.SongViewModel;

public class MusicService extends Service {

  private static final String ACTION_NAME = "ACTION_NAME";
  private static final int ACTION_PLAY = 1;
  private static final int ACTION_PAUSE = 2;
  private static final int ACTION_NEXT = 3;
  private static final int ACTION_PREVIOUS = 4;
  private static final int ACTION_CLOSE = 5;

  private SongViewModel mSongViewModel;
  private MediaPlayer mediaPlayer;
  private List<SongModel> mListSong;
  private boolean mIsPlaying;

  @Override
  public void onCreate() {
    super.onCreate();

    initListSong(mSongViewModel.getLiveDataListSong());
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {

    createNotification();

    int action = intent.getIntExtra(ACTION_NAME, -1);
    handleAction(action);

    return START_NOT_STICKY;
  }


  private void initListSong(List<SongModel> songModelList) {
    mediaPlayer =
  }

  private void handleAction(int action) {
    switch (action) {
      case ACTION_PLAY:
        play();
        break;
      case ACTION_PAUSE:
        pause();
        break;
      case ACTION_NEXT:
        next();
        break;
      case ACTION_PREVIOUS:
        previous();
        break;
      case ACTION_CLOSE:
        stopSelf();
        stopForeground(true);
        break;
    }
  }

  private void play() {
    if (!mIsPlaying) {
      mediaPlayer.start();
      mIsPlaying = true;
    } else {
      Toast.makeText(this, "Music has been playing", Toast.LENGTH_SHORT).show();
    }
  }

  private void pause() {
    if (mIsPlaying) {
      mediaPlayer.pause();
      mIsPlaying = false;
    } else {
      Toast.makeText(this, "Nothing is playing at the present", Toast.LENGTH_SHORT).show();
    }
  }

  private void next() {
  }

  private void previous() {
  }

  private void createNotification() {
    RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);

    remoteViews.setOnClickPendingIntent(R.id.ivPrevious, getPendingIntent(ACTION_PREVIOUS));
    remoteViews.setOnClickPendingIntent(R.id.ivNext, getPendingIntent(ACTION_NEXT));
  }

  private PendingIntent getPendingIntent(int action) {
    Intent intent = new Intent(getApplicationContext(), MusicService.class);
    intent.putExtra(ACTION_NAME, action);
    return PendingIntent.getService(getApplicationContext(), action, intent,
        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
