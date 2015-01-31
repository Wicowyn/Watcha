package yapiti.watcha.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
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

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.adapter.CoverAdapter;
import yapiti.watcha.entity.Movie;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.recycler_view) TwoWayView twoWayView;
    private CoverAdapter adapter;
    private ItemClickSupport support;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        adapter=new CoverAdapter(this);
        twoWayView.setAdapter(adapter);
        twoWayView.setLayoutManager(new GridLayoutManager(TwoWayLayoutManager.Orientation.VERTICAL, 2, 1));

        int dimen=(int) getResources().getDimension(R.dimen.spacing_movie);
        twoWayView.addItemDecoration(new SpacingItemDecoration(dimen, dimen));


        for(int i=0; i<40; i++) {
            Movie movie=new Movie();
            movie.setTitle("Yolo"+i);
            movie.setAuthor("Author "+i);
            movie.setDescription("Omne omne et tunica circumdedit accepimus qua muros agebatur ablatis armatis ad Caesarem inopinum Caesarem peremptum res iurandi fallaciis sed et statim qua extra omne circumdedit iurandi confirmans communi iurandi.");
            movie.setCover(Uri.parse("http://lorempixel.com/400/700/"));

            adapter.add(movie);
        }

        support=ItemClickSupport.addTo(twoWayView);
        support.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int i, long l) {
                ImageView imageView=ButterKnife.findById(view, R.id.image);
                Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();

                Intent intent= DetailActivity.newIntent(MainActivity.this, adapter.get(i), bitmap);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Pair<View, String>[] pairs=new Pair[2];

                    TextView textView=ButterKnife.findById(view, R.id.title);

                    pairs[0]=new Pair<View, String>(textView, "transition_title");
                    pairs[1]=new Pair<View, String>(imageView, "transition_cover");

                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pairs).toBundle();

                    startActivity(intent, bundle);
                } else {
                    startActivity(intent);
                }
            }
        });
    }
}
