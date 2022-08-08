package minhduc.deviluke.muzic.view.fragment.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.ItemRecentPlayedBinding;

public class RecentPlayedAdapter extends RecyclerView.Adapter<RecentPlayedAdapter.ViewHolder> {
  
  private LayoutInflater layoutInflater;
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }
    
    ItemRecentPlayedBinding binding = DataBindingUtil.inflate(
      layoutInflater,
      R.layout.item_recent_played,
      parent,
      false
    );
    
    return new ViewHolder(binding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  }
  
  @Override
  public int getItemCount() {
    return 0;
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {
    
    ItemRecentPlayedBinding mBindings;
    
    public ViewHolder(@NonNull ItemRecentPlayedBinding binding) {
      super(binding.getRoot());
      this.mBindings = binding;
    }
  }
}
