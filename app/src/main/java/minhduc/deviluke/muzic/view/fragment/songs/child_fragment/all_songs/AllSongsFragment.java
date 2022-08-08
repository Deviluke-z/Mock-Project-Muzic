package minhduc.deviluke.muzic.view.fragment.songs.child_fragment.all_songs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentAllSongsBinding;

public class AllSongsFragment extends Fragment {

  public AllSongsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  FragmentAllSongsBinding mBindings;
  
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    
    
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_all_songs, container, false);
  }
}