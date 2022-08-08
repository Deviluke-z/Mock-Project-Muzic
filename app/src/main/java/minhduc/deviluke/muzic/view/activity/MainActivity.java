package minhduc.deviluke.muzic.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.LayoutMainActivityBinding;
import minhduc.deviluke.muzic.view.fragment.home.HomeFragment;
import minhduc.deviluke.muzic.view.fragment.settings.SettingsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.SongsFragment;

public class MainActivity extends AppCompatActivity {

  final String mStoragePermission = Manifest.permission.READ_EXTERNAL_STORAGE;
  LayoutMainActivityBinding layoutMainActivityBinding;
  ActivityResultLauncher<String> mStoragePermissionLauncher;

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
  }

  private void loadFragments(Fragment fragment) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragmentContainerView, fragment);
    transaction.addToBackStack(null).commit();
  }
}