package minhduc.deviluke.muzic.view.fragment.songs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.AlbumsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.AllSongsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.ArtistsFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.GenresFragment;
import minhduc.deviluke.muzic.view.fragment.songs.child_fragment.PlaylistsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

  public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
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
  public int getCount() {
    return 5;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    String title = "";
    switch (position) {
      default:
        title = "All Songs";
        break;
      case 1:
        title = "Playlists";
        break;
      case 2:
        title = "Albums";
        break;
      case 3:
        title = "Artists";
        break;
      case 4:
        title = "Genres";
        break;
    }
    return super.getPageTitle(position);
  }
}
