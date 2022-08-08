package minhduc.deviluke.muzic.view.fragment.songs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;

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
    
    viewPagerAdapter =  new ViewPagerAdapter(requireActivity());
    mBindings.viewPager.setAdapter(viewPagerAdapter);
    
    new TabLayoutMediator(mBindings.tabLayout, mBindings.viewPager, (tab, position) -> {
      switch (position) {
        case 0:
          tab.setText("All Songs");
          break;
        case 1:
          tab.setText("Playlists");
          break;
        case 2:
          tab.setText("Albums");
          break;
        case 3:
          tab.setText("Artists");
          break;
        case 4:
          tab.setText("Genres");
          break;
      }
    }).attach();

    return mBindings.getRoot();
  }
}