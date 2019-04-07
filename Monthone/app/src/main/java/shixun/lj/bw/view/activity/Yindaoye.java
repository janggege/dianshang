package shixun.lj.bw.view.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import shixun.lj.bw.R;

public class Yindaoye extends AppCompatActivity {
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindaoye);
        ImageView imageView = findViewById(R.id.yimage);
        getInitObjectAnimator(imageView);


    }

    private void getInitObjectAnimator(ImageView iv) {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(iv, "translationY", 0f, 500f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(iv, "rotation", 0f, 360f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);
        animatorSet = new AnimatorSet();
        animatorSet.play(translationY)
                .with(rotation)
                .before(alpha);
        animatorSet.setDuration(5000);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                startActivity(new Intent(Yindaoye.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
}
