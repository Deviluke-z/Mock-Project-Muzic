package minhduc.deviluke.muzic.view.fragment.songs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentSongsBinding;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.AlbumsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.AllSongsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.ArtistsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.GenresFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.PlaylistsFragment;

public class SongsFragment extends Fragment {

  public SongsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  FragmentSongsBinding mBindings;
  ViewPagerAdapter viewPagerAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBindings = FragmentSongsBinding.inflate(inflater, container, false);

    return mBindings.getRoot();
  }
}