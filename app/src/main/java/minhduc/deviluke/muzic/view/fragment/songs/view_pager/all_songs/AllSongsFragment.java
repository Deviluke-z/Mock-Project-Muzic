package minhduc.deviluke.muzic.view.fragment.songs.view_pager.all_songs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import minhduc.deviluke.muzic.databinding.FragmentAllSongsBinding;
import minhduc.deviluke.muzic.service.MusicPlayer;
import minhduc.deviluke.muzic.view.activity.MainActivity;
import minhduc.deviluke.muzic.viewmodel.SongViewModel;

public class AllSongsFragment extends Fragment implements AllSongsAdapter.CallbackOnMainActivity {
  
  FragmentAllSongsBinding mBindings;
  AllSongsAdapter mAllSongsAdapter;
  SongViewModel mSongViewModel;
  MusicPlayer mMusicPlayer;
  ActivityCallback mCallback;
  
  public AllSongsFragment() {
    // Required empty public constructor
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    mCallback = (MainActivity) context;
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mBindings = FragmentAllSongsBinding.inflate(inflater, container, false);
    mSongViewModel = new ViewModelProvider(requireActivity()).get(SongViewModel.class);
    mMusicPlayer = MusicPlayer.getInstance(requireContext());
    initRecycleView();
    setObserver();
    mSongViewModel.fetchSong();
    
    // Inflate the layout for this fragment
    return mBindings.getRoot();
  }
  
  private void initRecycleView() {
    mAllSongsAdapter = new AllSongsAdapter(requireContext(), this);
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
  
  @Override
  public void onClickItem(int position) {
    mMusicPlayer.setPosition(position);
    mCallback.OnSongClick(position);
  }
}