package minhduc.deviluke.muzic.view.fragment.songs.view_pager.all_songs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import minhduc.deviluke.muzic.R;
import minhduc.deviluke.muzic.databinding.ItemRecentPlayedBinding;
import minhduc.deviluke.muzic.model.song.SongModel;
import minhduc.deviluke.muzic.viewmodel.SongViewModel;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.ViewHolder> {
  
  LayoutInflater layoutInflater;
  private List<SongModel> mListSong = new ArrayList<>();
  private Context mContext;
  
  private AllSongAdapterListener adapterListener;
  
  public AllSongsAdapter() {
  }
  
  public AllSongsAdapter(Context mContext, AllSongAdapterListener adapterListener) {
    this.mContext = mContext;
    this.adapterListener = adapterListener;
  }
  
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
    SongModel songModel = mListSong.get(position);
    holder.mBindings.tvSongTitle.setText(songModel.getTitle());
    holder.mBindings.tvSongArtist.setText(songModel.getArtist());

    Uri thumbnailUri = songModel.getThumbnailUri();
    if (thumbnailUri != null) {
      holder.mBindings.ivPlay.setImageURI(thumbnailUri);

      // make sure every song has thumbnail
      if (holder.mBindings.ivPlay.getDrawable() == null) {
        holder.mBindings.ivPlay.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
      }
    }
    
    holder.mBindings.ivPlay.setOnClickListener( v -> adapterListener.onClickItem(position));
  }
  
  @Override
  public int getItemCount() {
    Log.d("Debug", "Apdater: " + mListSong.size());
    if (mListSong == null) {
      return 0;
    }
    return mListSong.size();
  }
  
  @SuppressLint("NotifyDataSetChanged")
  public void initData(List<SongModel> mListSong) {
    this.mListSong = mListSong;
    notifyDataSetChanged();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {
    
    ItemRecentPlayedBinding mBindings;
    
    public ViewHolder(@NonNull ItemRecentPlayedBinding binding) {
      super(binding.getRoot());
      this.mBindings = binding;
    }
  }
  
  public interface AllSongAdapterListener {
    void onClickItem(int position);
  }
}
