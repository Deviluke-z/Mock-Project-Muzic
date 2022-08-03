package minhduc.deviluke.muzic.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import minhduc.deviluke.muzic.R;

public class IntroActivity extends AppCompatActivity {

  ImageView ivAppIcon;
  TextView tvAppName;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_intro);

    ivAppIcon = findViewById(R.id.ivAppIcon);
    tvAppName = findViewById(R.id.tvAppName);

    Animation animationUtils = AnimationUtils.loadAnimation(this, R.anim.anim_ltr);
    ivAppIcon.startAnimation(animationUtils);
    tvAppName.startAnimation(animationUtils);

    new Handler().postDelayed(() -> {
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();
    }, 3000);
  }
}
