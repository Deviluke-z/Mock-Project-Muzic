package minhduc.deviluke.muzic.view.fragment.songs.view_pager.playlists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentPlaylistsBinding;
import minhduc.deviluke.muzic.model.SampleItemModel;
import minhduc.deviluke.muzic.view.fragment.home.PlaylistAdapter;

public class PlaylistsFragment extends Fragment {
  
  public PlaylistsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private FragmentPlaylistsBinding mBindings;
  private FakeAdapter1 mPlaylistsAdapter = new FakeAdapter1();
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mBindings = FragmentPlaylistsBinding.inflate(inflater, container, false);
    initFakeRecycleView();
    initFakeRecycleView2();
    
    // Inflate the layout for this fragment
    return mBindings.getRoot();
  }
  
  private void initFakeRecycleView2() {
    SampleItemModel sampleItemModel = new SampleItemModel(R.drawable.img_sample_playlist);
    List<SampleItemModel> mListSampleItem1 = new ArrayList<>();
    
    for (int i = 0; i < 7; i++) {
      mListSampleItem1.add(sampleItemModel);
    }
  
    PlaylistAdapter mPlaylistAdapter = new PlaylistAdapter();
    mPlaylistAdapter.setListPlaylist(mListSampleItem1);
  
    mBindings.rvPlaylist.setLayoutManager(new LinearLayoutManager(
      requireActivity(),
      LinearLayoutManager.HORIZONTAL,
      false
    ));
    mBindings.rvPlaylist.setAdapter(mPlaylistAdapter);
  }
  
  private void initFakeRecycleView() {
    SampleItemModel sampleItemModel1 = new SampleItemModel(R.drawable.img_top_track);
    SampleItemModel sampleItemModel2 = new SampleItemModel(R.drawable.img_latest_add);
    SampleItemModel sampleItemModel3 = new SampleItemModel(R.drawable.img_history);
    SampleItemModel sampleItemModel4 = new SampleItemModel(R.drawable.img_favourites);
  
    List<SampleItemModel> mListSampleItem = new ArrayList<>();
    mListSampleItem.add(sampleItemModel1);
    mListSampleItem.add(sampleItemModel2);
    mListSampleItem.add(sampleItemModel3);
    mListSampleItem.add(sampleItemModel4);
    
    mPlaylistsAdapter.setListSampleItem(mListSampleItem);
    
    mBindings.rvFakePlaylist.setAdapter(mPlaylistsAdapter);
  }
}