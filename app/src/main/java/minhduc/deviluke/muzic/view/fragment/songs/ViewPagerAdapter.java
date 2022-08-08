package minhduc.deviluke.muzic.view.fragment.songs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.AlbumsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.all_songs.AllSongsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.ArtistsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.GenresFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.PlaylistsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
  
  
  public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }
  
  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 1:
        return new PlaylistsFragment();
      case 2:
        return new AlbumsFragment();
      case 3:
        return new ArtistsFragment();
      case 4:
        return new GenresFragment();
      default:
        return new AllSongsFragment();
    }
  }
  
  @Override
  public int getItemCount() {
    return 5;
  }
}
