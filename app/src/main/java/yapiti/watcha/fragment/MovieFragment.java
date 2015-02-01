package yapiti.watcha.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.GridLayoutManager;
import org.lucasr.twowayview.widget.SpacingItemDecoration;
import org.lucasr.twowayview.widget.TwoWayView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.activity.DetailActivity;
import yapiti.watcha.adapter.CoverAdapter;
import yapiti.watcha.request.MovieRequest;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * interface.
 */
public class MovieFragment extends BaseFragment {
    @InjectView(R.id.recycler_view) TwoWayView twoWayView;
    private CoverAdapter adapter;
    private ItemClickSupport support;


    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter=new CoverAdapter(getActivity());
//        for(int i=0; i<40; i++) {
//            Movie movie=new Movie();
//            movie.setTitle("Yolo"+i);
//            movie.setAuthor("Author " + i);
//            movie.setDescription("Omne omne et tunica circumdedit accepimus qua muros agebatur ablatis armatis ad Caesarem inopinum Caesarem peremptum res iurandi fallaciis sed et statim qua extra omne circumdedit iurandi confirmans communi iurandi.");
//            movie.setCover(Uri.parse("http://lorempixel.com/400/70"+((int) (Math.random()*5))));
//            movie.getSeances().add(new Seance(new Date()));
//
//            adapter.add(movie);
//        }

        manager.execute(new MovieRequest(), new ListenGet());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        twoWayView.setAdapter(adapter);
        twoWayView.setLayoutManager(new GridLayoutManager(TwoWayLayoutManager.Orientation.VERTICAL, 2, 1));

        int dimen=(int) getResources().getDimension(R.dimen.activity_vertical_margin);
        twoWayView.addItemDecoration(new SpacingItemDecoration(dimen, dimen));


        support=ItemClickSupport.addTo(twoWayView);
        support.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int i, long l) {
                ImageView imageView=ButterKnife.findById(view, R.id.image);
                Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();

                Intent intent= DetailActivity.newIntent(getActivity(), adapter.get(i), bitmap);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Pair<View, String>[] pairs=new Pair[2];

                    TextView textView=ButterKnife.findById(view, R.id.title);

                    pairs[0]=new Pair<View, String>(textView, "transition_title");
                    pairs[1]=new Pair<View, String>(imageView, "transition_cover");

                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairs).toBundle();

                    getActivity().startActivity(intent, bundle);
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }

    private class ListenGet implements RequestListener<MovieRequest.Result> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(MovieRequest.Result o) {
            adapter.addAll(o.getList());
            Log.d("adapter", "size" +adapter.getItemCount());
        }
    }
}
