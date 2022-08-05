package minhduc.deviluke.muzic.view.fragment.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.ItemPlaylistSampleBinding;
import minhduc.deviluke.muzic.model.SampleItemModel;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

  private LayoutInflater layoutInflater;
  private List<SampleItemModel> mSampleItemList;

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }

    ItemPlaylistSampleBinding binding = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.item_playlist_sample,
        parent,
        false
    );

    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    SampleItemModel sampleItemModel = mSampleItemList.get(position);
    holder.mBinding.ivPlaylist.setImageResource(sampleItemModel.getImage());
  }

  @Override
  public int getItemCount() {
    if (mSampleItemList == null) {
      return 0;
    }
    return mSampleItemList.size();
  }

  public void setListPlaylist(List<SampleItemModel> sampleItemModelList) {
    this.mSampleItemList = sampleItemModelList;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ItemPlaylistSampleBinding mBinding;


    public ViewHolder(@NonNull ItemPlaylistSampleBinding binding) {
      super(binding.getRoot());
      this.mBinding = binding;
    }
  }
}
