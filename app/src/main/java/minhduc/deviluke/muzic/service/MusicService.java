package minhduc.deviluke.muzic.service;

import static minhduc.deviluke.muzic.service.MusicNotification.NOTIFICATION_MUSIC_CHANNEL;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.model.song.SongModel;

public class MusicService extends Service {
  
  private static final String ACTION_NAME = "ACTION_NAME";
  private static final int ACTION_PLAY = 1;
  private static final int ACTION_PAUSE = 2;
  private static final int ACTION_NEXT = 3;
  private static final int ACTION_PREVIOUS = 4;
  private static final int ACTION_CLOSE = 5;
  
  private MusicPlayer musicPlayer;
  private List<SongModel> mListSong;
  
  @Override
  public void onCreate() {
    super.onCreate();
    
    musicPlayer = MusicPlayer.getInstance(getApplicationContext());
    mListSong = musicPlayer.setListSong();
  }
  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    
    createNotification(mListSong.get(musicPlayer.getPosition()));
    
    int action = intent.getIntExtra(ACTION_NAME, -1);
    handleAction(action);
    
    Log.d("Debug", "Start Service");
    
    return START_NOT_STICKY;
  }
  
  private void handleAction(int action) {
    switch (action) {
      case ACTION_PLAY:
        musicPlayer.continuePlay();
        break;
      case ACTION_PAUSE:
        musicPlayer.pause();
        break;
      case ACTION_NEXT:
        break;
      case ACTION_PREVIOUS:
        break;
      case ACTION_CLOSE:
        musicPlayer.stop();
        stopSelf();
        stopForeground(true);
        break;
    }
  }
  
  private void createNotification(SongModel songModel) {
    RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
    
    remoteViews.setTextViewText(R.id.tvSongTitleNotification, songModel.getTitle());
    remoteViews.setTextViewText(R.id.tvSongArtistNotification, songModel.getArtist());
    
    remoteViews.setOnClickPendingIntent(R.id.ivPlay, getPendingIntent(ACTION_PLAY));
    remoteViews.setOnClickPendingIntent(R.id.ivPause, getPendingIntent(ACTION_PAUSE));
    remoteViews.setOnClickPendingIntent(R.id.ivPrevious, getPendingIntent(ACTION_PREVIOUS));
    remoteViews.setOnClickPendingIntent(R.id.ivNext, getPendingIntent(ACTION_NEXT));
    remoteViews.setOnClickPendingIntent(R.id.ivClose, getPendingIntent(ACTION_CLOSE));
    
    Notification notification = new NotificationCompat.Builder(getApplication(), NOTIFICATION_MUSIC_CHANNEL)
      .setSmallIcon(R.drawable.ic_launcher_foreground)
      .setCustomContentView(remoteViews)
      .build();
    
    startForeground(1, notification);
    Log.d("Debug", "Create Notification");
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
