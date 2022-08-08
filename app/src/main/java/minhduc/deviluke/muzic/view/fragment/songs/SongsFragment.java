package minhduc.deviluke.muzic.view.fragment.songs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBindings = FragmentSongsBinding.inflate(inflater, container, false);
    
    loadFragments(new AllSongsFragment());

    mBindings.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @SuppressLint("NonConstantResourceId")
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment;
        switch (tab.getId()) {
          case R.id.tabAllSongs:
            fragment = new AllSongsFragment();
            loadFragments(fragment);
            break;
          case R.id.tabPlaylists:
            fragment = new PlaylistsFragment();
            loadFragments(fragment);
            break;
          case R.id.tabAlbums:
            fragment = new AlbumsFragment();
            loadFragments(fragment);
            break;
          case R.id.tabArtists:
            fragment = new ArtistsFragment();
            loadFragments(fragment);
            break;
          case R.id.tabGenres:
            fragment = new GenresFragment();
            loadFragments(fragment);
            break;
        }
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });


    return mBindings.getRoot();
  }

  private void loadFragments(Fragment fragment) {
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.songFragmentContainer, fragment);
    transaction.addToBackStack(null).commit();
  }
}