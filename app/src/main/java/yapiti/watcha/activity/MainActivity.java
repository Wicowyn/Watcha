package yapiti.watcha.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.fragment.MovieFragment;
import yapiti.watcha.fragment.SeanceFragment;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.view_pager) ViewPager viewPager;
    @InjectView(R.id.tabs) PagerSlidingTabStrip slidingTabStrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        slidingTabStrip.setViewPager(viewPager);
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
                    fragment= SeanceFragment.newInstance();
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

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Films";
                case 1:
                    return "Seances";
                default:
                    throw new IllegalStateException("Unsupported");
            }
        }
    }
}
