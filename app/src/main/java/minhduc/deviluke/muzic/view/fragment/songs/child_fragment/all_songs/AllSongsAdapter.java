package minhduc.deviluke.muzic.view.fragment.songs.child_fragment.all_songs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.FragmentAllSongsBinding;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.ViewHolder> {
  
  LayoutInflater layoutInflater;
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }
    
    FragmentAllSongsBinding binding = DataBindingUtil.inflate(
      layoutInflater,
      R.layout.item_recent_played,
      parent,
      false
    );
    
    
    return null;
  }
  
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  
  }
  
  @Override
  public int getItemCount() {
    return 0;
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {
  
    FragmentAllSongsBinding mBindings;
    
    public ViewHolder(@NonNull FragmentAllSongsBinding binding) {
      super(binding.getRoot());
      this.mBindings = binding;
    }
  }
}
