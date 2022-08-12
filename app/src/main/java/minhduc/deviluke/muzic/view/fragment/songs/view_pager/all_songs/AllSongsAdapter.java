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
import minhduc.deviluke.muzic.databinding.ItemAllSongsBinding;
import minhduc.deviluke.muzic.model.song.SongModel;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.ViewHolder> {

  LayoutInflater layoutInflater;
  private List<SongModel> mListSong = new ArrayList<>();
  private Context mContext;

  private CallbackOnMainActivity callbackOnMainActivity;

  public AllSongsAdapter() {
  }

  public AllSongsAdapter(Context mContext, CallbackOnMainActivity callbackOnMainActivity) {
    this.mContext = mContext;
    this.callbackOnMainActivity = callbackOnMainActivity;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }

    ItemAllSongsBinding binding = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.item_all_songs,
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
      holder.mBindings.ivSongThumbnail.setImageURI(thumbnailUri);

      // make sure every song has thumbnail
      if (holder.mBindings.ivSongThumbnail.getDrawable() == null) {
        holder.mBindings.ivSongThumbnail.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
      }
    }

    // click on play button to start music
    holder.mBindings.ivPlay.setOnClickListener(v -> {
      callbackOnMainActivity.onClickItem(position);
    });
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

  public interface CallbackOnMainActivity {
    void onClickItem(int position);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    ItemAllSongsBinding mBindings;

    public ViewHolder(@NonNull ItemAllSongsBinding binding) {
      super(binding.getRoot());
      this.mBindings = binding;
    }
  }
}
