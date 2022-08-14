package minhduc.deviluke.muzic.view.fragment.songs.view_pager.playlists;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.ItemFakePlaylistViewPagerBinding;
import minhduc.deviluke.muzic.model.SampleItemModel;

public class FakeAdapter1 extends RecyclerView.Adapter<FakeAdapter1.ViewHolder> {
  
  LayoutInflater layoutInflater;
  
  public void setListSampleItem(List<SampleItemModel> mListSampleItem) {
    this.mListSampleItem = mListSampleItem;
  }
  
  List<SampleItemModel> mListSampleItem;
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }
    
    ItemFakePlaylistViewPagerBinding binding = DataBindingUtil.inflate(
      layoutInflater,
      R.layout.item_fake_playlist_view_pager,
      parent, false
    );
    return new ViewHolder(binding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    SampleItemModel sampleItemModel = mListSampleItem.get(position);
    holder.mBindings.ivFakePlaylist.setImageResource(sampleItemModel.getImage());
  }
  
  @Override
  public int getItemCount() {
    return mListSampleItem.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {
    ItemFakePlaylistViewPagerBinding mBindings;
    
    public ViewHolder(@NonNull ItemFakePlaylistViewPagerBinding binding) {
      super(binding.getRoot());
      this.mBindings = binding;
    }
  }
}
