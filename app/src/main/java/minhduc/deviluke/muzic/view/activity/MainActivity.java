package minhduc.deviluke.muzic.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.LayoutMainActivityBinding;
import minhduc.deviluke.muzic.service.MusicPlayer;
import minhduc.deviluke.muzic.service.MusicService;
import minhduc.deviluke.muzic.view.fragment.home.HomeFragment;
import minhduc.deviluke.muzic.view.fragment.settings.SettingsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.SongsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.view_pager.all_songs.ActivityCallback;
import minhduc.deviluke.muzic.view.fragment.songs.view_pager.all_songs.AllSongsAdapter;

public class MainActivity extends AppCompatActivity
  implements AllSongsAdapter.CallbackOnMainActivity,
  MusicService.OnNotificationClick,
  ActivityCallback {
  
  final String mStoragePermission = Manifest.permission.READ_EXTERNAL_STORAGE;
  ActivityResultLauncher<String> mStoragePermissionLauncher;
  
  LayoutMainActivityBinding layoutMainActivityBinding;
  
  private MusicPlayer mMusicPlayer;
  private MusicService mMusicService;
  
  HandlerThread handlerThread = new HandlerThread("Music Service");
  Handler handler;
  
  private boolean bound = false;
  
  @SuppressLint("NonConstantResourceId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    layoutMainActivityBinding =
      DataBindingUtil.setContentView(this, R.layout.layout_main_activity);
    
    // storage permission
    mStoragePermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
      if (granted) {
        Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(getApplicationContext(), "Oke bro, you deny this app", Toast.LENGTH_SHORT).show();
      }
    });
    
    // request for the 1st time when running app
    mStoragePermissionLauncher.launch(mStoragePermission);
    
    // bottom navigation
    loadFragments(new HomeFragment());
    
    layoutMainActivityBinding.bottomNavigation.setItemIconTintList(null);
    layoutMainActivityBinding.bottomNavigation.setOnItemSelectedListener(item -> {
      Fragment fragment;
      switch (item.getItemId()) {
        case R.id.itemHome:
          fragment = new HomeFragment();
          loadFragments(fragment);
          break;
        case R.id.itemSongs:
          fragment = new SongsFragment();
          loadFragments(fragment);
          break;
        case R.id.itemSettings:
          fragment = new SettingsFragment();
          loadFragments(fragment);
          break;
      }
      
      return false;
    });
    
    mMusicPlayer = MusicPlayer.getInstance(this);
    
    layoutMainActivityBinding.mediaController.godFather.setVisibility(View.GONE);
  
    handlerThread.start();
    handler = new Handler(handlerThread.getLooper());
  }
  
  private void loadFragments(Fragment fragment) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragmentContainerView, fragment);
    transaction.addToBackStack(null).commit();
  }
  
  
  @Override
  public void onClickItem(int position) {
    layoutMainActivityBinding.mediaController.godFather.setVisibility(View.VISIBLE);
    
    UpdateMediaControllerUI(position);
  
    handler.post(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        startService(intent);
      }
    });
  }
  
  private ServiceConnection serviceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      Log.i("Debug_MainActivity", "onServiceConnected: ");
      MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
      mMusicService = binder.getServices();
      bound = true;
      mMusicService.setServiceCallback(MainActivity.this);
    }
    
    @Override
    public void onServiceDisconnected(ComponentName name) {
      bound = false;
      Log.i("Debug_MainActivity", "onServiceDisconnected: ");
    }
  };
  
  @SuppressLint("UseCompatLoadingForDrawables")
  private void UpdateMediaControllerUI(int position) {
    layoutMainActivityBinding.mediaController.tvSongTitle.setText(
      mMusicPlayer.setListSong().get(position).getTitle()
    );
    
    layoutMainActivityBinding.mediaController.tvSongArtist.setText(
      mMusicPlayer.setListSong().get(position).getArtist()
    );
    
    Uri thumbnailUri = mMusicPlayer.setListSong().get(mMusicPlayer.getPosition()).getThumbnailUri();
    if (thumbnailUri != null) {
      layoutMainActivityBinding.mediaController.ivSongThumbnail.setImageURI(thumbnailUri);
      
      // make sure every song has thumbnail
      if (layoutMainActivityBinding.mediaController.ivSongThumbnail.getDrawable() == null) {
        layoutMainActivityBinding.mediaController.ivSongThumbnail.setImageResource(
          R.drawable.ic_baseline_play_circle_outline_24
        );
      }
    }
  
    layoutMainActivityBinding.mediaController.ivPlayPause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
  }
  
  @Override
  public void OnSongClick(int position) {
    onClickItem(position);
  }
  
  @Override
  public void UpdateMediaControllerFromService(int action, int position) {
    switch (action) {
      case 1:
      case 3:
      case 4:
        UpdateMediaControllerUI(position);
        break;
      case 2:
        UpdateMediaControllerUI(position);
        layoutMainActivityBinding.mediaController.ivPlayPause.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
        break;
      case 5:
        layoutMainActivityBinding.mediaController.godFather.setVisibility(View.GONE);
        break;
    }
  }
  
  @Override
  public void UnbindService() {
    unbindService(serviceConnection);
  }
}