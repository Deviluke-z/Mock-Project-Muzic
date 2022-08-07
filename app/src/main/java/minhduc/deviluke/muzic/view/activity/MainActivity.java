package minhduc.deviluke.muzic.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.LayoutMainActivityBinding;
import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.view.fragment.home.HomeFragment;
import minhduc.deviluke.muzic.view.fragment.settings.SettingsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.SongsFragment;

public class MainActivity extends AppCompatActivity {
  
  LayoutMainActivityBinding layoutMainActivityBinding;
  ActivityResultLauncher<String> storagePermission;
  
  @SuppressLint("NonConstantResourceId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    layoutMainActivityBinding =
      DataBindingUtil.setContentView(this, R.layout.layout_main_activity);
  
  
    //request for the 1st time
    storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    // storage permission
    storagePermission = registerForActivityResult(
      new ActivityResultContracts.RequestPermission(),
      result -> {
        if (result) {
          // permission was granted
          fetchSongs();
        } else {
          // respond due to user action
          respondOnPermission();
        }
      });
    
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
  }
  
  private void fetchSongs() {
//    List<SongModel> mListSong = new ArrayList<>();
//    Uri mSongDatabaseUri;
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//      mSongDatabaseUri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
//    } else {
//      mSongDatabaseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//    }
//
//    // projection
//    String[] projection = new String[]{
//      MediaStore.Audio.Media._ID,
//      MediaStore.Audio.Media.DISPLAY_NAME,
//      MediaStore.Audio.Media.DURATION,
//      MediaStore.Audio.Media.SIZE,
//      MediaStore.Audio.Media.ALBUM
//    };
//
//    // sorting
//    String mSorting = MediaStore.Audio.Media.DATE_ADDED + " DESC";
//
//    // query
//    try (Cursor cursor = getContentResolver().query(mSongDatabaseUri, projection, null, null)) {
//
//    }
  }
  
  private void respondOnPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
      == PackageManager.PERMISSION_GRANTED) {
      fetchSongs();
    } else if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
      // show alert dialog
      new AlertDialog.Builder(this)
        .setTitle("Permission Needed")
        .setMessage("Please allow storage permission to fetch the database")
        .setPositiveButton(
          "OK",
          (dialog, which) -> storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        .setNegativeButton(
          "NO BRO",
          (dialog, which) -> Toast.makeText(
            getApplicationContext(),
            "Oke bro, you deny this app",
            Toast.LENGTH_SHORT).show())
        .show();
    } else {
      Toast.makeText(
        getApplicationContext(),
        "Oke bro, you deny this app",
        Toast.LENGTH_SHORT).show();
    }
  }
  
  private void loadFragments(Fragment fragment) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragmentContainerView, fragment);
    transaction.addToBackStack(null).commit();
  }
}