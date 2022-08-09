package minhduc.deviluke.muzic.service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MusicNotification extends Application {

  public static final String NOTIFICATION_MUSIC_CHANNEL = "Music Notification";

  @Override
  public void onCreate() {
    super.onCreate();

    createNotification();
  }

  private void createNotification() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel notificationChannel = new NotificationChannel(
          NOTIFICATION_MUSIC_CHANNEL,
          "Music Foreground Service",
          NotificationManager.IMPORTANCE_DEFAULT
      );
      notificationChannel.setSound(null, null);
      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(notificationChannel);
    }
  }
}
