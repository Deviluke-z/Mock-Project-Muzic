package minhduc.deviluke.muzic.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentHomeBinding;
import minhduc.deviluke.muzic.model.SampleItemModel;
import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.service.MusicPlayer;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding mBindings;
  private final List<SampleItemModel> mSampleItemList1 = new ArrayList<>();
  private final List<SampleItemModel> mSampleItemList2 = new ArrayList<>();
  private RecentPlayedAdapter mRecentPlayedAdapter = new RecentPlayedAdapter();
  
  public HomeFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBindings = FragmentHomeBinding.inflate(inflater, container, false);
    mBindings.ivMenu.setOnClickListener(v -> mBindings.drawerLayout.openDrawer(GravityCompat.START));

    // setup fake Hot Recommend recycler view
    SampleItemModel sampleItemModel1 = new SampleItemModel(R.drawable.ic_sample_song);
    for (int i = 0; i < 5; i++) {
      mSampleItemList1.add(sampleItemModel1);
    }

    HotRecommendAdapter mHotRecommendAdapter = new HotRecommendAdapter();
    mHotRecommendAdapter.setListHotRecommend(mSampleItemList1);

    mBindings.rvHotRecommend.setLayoutManager(new LinearLayoutManager(
        requireActivity(),
        LinearLayoutManager.HORIZONTAL,
        false));
    mBindings.rvHotRecommend.setAdapter(mHotRecommendAdapter);

    // setup fake Playlist recycler view
    SampleItemModel sampleItemModel2 = new SampleItemModel(R.drawable.ic_sample_playlist);

    for (int i = 0; i < 7; i++) {
      mSampleItemList2.add(sampleItemModel2);
    }

    PlaylistAdapter mPlaylistAdapter = new PlaylistAdapter();
    mPlaylistAdapter.setListPlaylist(mSampleItemList2);

    mBindings.rvPlaylist.setLayoutManager(new LinearLayoutManager(
        requireActivity(),
        LinearLayoutManager.HORIZONTAL,
        false
    ));
    mBindings.rvPlaylist.setAdapter(mPlaylistAdapter);
    
    // recent play recycler view
    MusicPlayer mMusicPlayer = MusicPlayer.getInstance(requireContext());
    mBindings.rvRecentPlayed.setLayoutManager(new LinearLayoutManager(
      requireActivity(),
      LinearLayoutManager.VERTICAL,
      false
    ));
    mBindings.rvRecentPlayed.setAdapter(mRecentPlayedAdapter);
    mMusicPlayer.getListRecentPlay().observe(requireActivity(), new Observer<List<SongModel>>() {
      @Override
      public void onChanged(List<SongModel> songModels) {
        mRecentPlayedAdapter.setListSong(songModels);
      }
    });
    
    return mBindings.getRoot();
  }
}