package minhduc.deviluke.muzic.view.fragment.songs.view_pager.all_songs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentAllSongsBinding;
import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.viewmodel.SongViewModel;

public class AllSongsFragment extends Fragment {

  public AllSongsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  FragmentAllSongsBinding mBindings;
  AllSongsAdapter mAllSongsAdapter;
  SongViewModel mSongViewModel;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    
    mBindings = FragmentAllSongsBinding.inflate(inflater, container, false);
    mSongViewModel = new ViewModelProvider(requireActivity()).get(SongViewModel.class);
    initRecycleView();
    setObserver();
    mSongViewModel.fetchSong();
    
    // Inflate the layout for this fragment
    return mBindings.getRoot();
  }
  
  private void initRecycleView() {
    mAllSongsAdapter = new AllSongsAdapter();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
      requireActivity(),
      LinearLayoutManager.VERTICAL,
      false
    );
    mBindings.rvAllSong.setLayoutManager(linearLayoutManager);
    mBindings.rvAllSong.setAdapter(mAllSongsAdapter);
  }
  
  private void setObserver() {
    mSongViewModel.getLiveDataListSong().observe(requireActivity(), songModels -> {
        mAllSongsAdapter.initData(songModels);
        Log.d("Debug", "" + songModels.size());
      }
    );
  }
}