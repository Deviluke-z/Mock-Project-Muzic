package minhduc.deviluke.muzic.view.fragment.songs.view_pager.all_songs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import minhduc.deviluke.muzic.databinding.FragmentAllSongsBinding;
import minhduc.deviluke.muzic.service.MusicPlayer;
import minhduc.deviluke.muzic.service.MusicService;
import minhduc.deviluke.muzic.viewmodel.SongViewModel;

public class AllSongsFragment extends Fragment implements AllSongsAdapter.AllSongAdapterListener {
  
  FragmentAllSongsBinding mBindings;
  AllSongsAdapter mAllSongsAdapter;
  SongViewModel mSongViewModel;
  MusicPlayer mMusicPlayer;
  
  HandlerThread handlerThread = new HandlerThread("Music Service");
  Handler handler;
  
  public AllSongsFragment() {
    // Required empty public constructor
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    handlerThread.start();
    handler = new Handler(handlerThread.getLooper());
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
    mAllSongsAdapter = new AllSongsAdapter(requireActivity(), this);
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
    
    handler.post(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(requireActivity(), MusicService.class);
        requireActivity().startService(intent);
      }
    });
  }
}