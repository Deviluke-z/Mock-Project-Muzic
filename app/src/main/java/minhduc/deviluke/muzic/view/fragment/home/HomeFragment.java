package minhduc.deviluke.muzic.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentHomeBinding;
import minhduc.deviluke.muzic.model.SampleItemModel;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding mBindings;
  private HotRecommendAdapter mHotRecommendAdapter;
  private PlaylistAdapter mPlaylistAdapter;
  private List<SampleItemModel> mSampleItemList1 = new ArrayList<>();
  private List<SampleItemModel> mSampleItemList2 = new ArrayList<>();
  private SampleItemModel sampleItemModel1, sampleItemModel2;

  public HomeFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBindings = FragmentHomeBinding.inflate(inflater, container, false);
    mBindings.ivMenu.setOnClickListener(v -> mBindings.drawerLayout.openDrawer(GravityCompat.START));

    // setup fake Hot Recommend recycler view
    sampleItemModel1 = new SampleItemModel(R.drawable.ic_sample_song);
    for (int i = 0; i < 5; i++) {
      mSampleItemList1.add(sampleItemModel1);
    }

    mHotRecommendAdapter = new HotRecommendAdapter();
    mHotRecommendAdapter.setListHotRecommend(mSampleItemList1);

    mBindings.rvHotRecommend.setLayoutManager(new LinearLayoutManager(
        requireActivity(),
        LinearLayoutManager.HORIZONTAL,
        false));
    mBindings.rvHotRecommend.setAdapter(mHotRecommendAdapter);

    // setup fake Playlist recycler view
    sampleItemModel2 = new SampleItemModel(R.drawable.ic_sample_playlist);

    for (int i = 0; i < 7; i++) {
      mSampleItemList2.add(sampleItemModel2);
    }

    mPlaylistAdapter = new PlaylistAdapter();
    mPlaylistAdapter.setListPlaylist(mSampleItemList2);

    mBindings.rvPlaylist.setLayoutManager(new LinearLayoutManager(
        requireActivity(),
        LinearLayoutManager.HORIZONTAL,
        false));
    mBindings.rvPlaylist.setAdapter(mPlaylistAdapter);


    return mBindings.getRoot();
  }
}