package yapiti.watcha.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.GridLayoutManager;
import org.lucasr.twowayview.widget.SpacingItemDecoration;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.adapter.CoverAdapter;
import yapiti.watcha.entity.Movie;
import yapiti.watcha.entity.Seance;
import yapiti.watcha.fragment.MovieFragment;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment;

            switch (i){
                case 0:
                    fragment= MovieFragment.newInstance();
                    break;
                case 1:
                    fragment= MovieFragment.newInstance();
                    break;
                default:
                    throw new IllegalStateException("Unsupported");
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
