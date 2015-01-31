package yapiti.watcha.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.ListLayoutManager;
import org.lucasr.twowayview.widget.SpacingItemDecoration;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.activity.DetailActivity;
import yapiti.watcha.adapter.SeanceAdapter;
import yapiti.watcha.entity.Movie;
import yapiti.watcha.entity.Seance;
import yapiti.watcha.view.SeanceView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SeanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeanceFragment extends Fragment {
    @InjectView(R.id.recycler_view) TwoWayView twoWayView;
    private SeanceAdapter adapter;
    private ItemClickSupport support;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SeanceFragment.
     */
    public static SeanceFragment newInstance() {
        SeanceFragment fragment = new SeanceFragment();

        return fragment;
    }

    public SeanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter=new SeanceAdapter(getActivity());

        ArrayList<Movie> list=new ArrayList<>();
        for(int i=0; i<40; i++) {
            Movie movie=new Movie();
            movie.setTitle("Yolo"+i);
            movie.setAuthor("Author " + i);
            movie.setDescription("Omne omne et tunica circumdedit accepimus qua muros agebatur ablatis armatis ad Caesarem inopinum Caesarem peremptum res iurandi fallaciis sed et statim qua extra omne circumdedit iurandi confirmans communi iurandi.");
            movie.setCover(Uri.parse("http://lorempixel.com/400/700/"));

            for(int j=(int) (Math.random()*3); j>0; j--)
                movie.getSeances().add(new Seance(new Date((long) Math.random()*1422740489)));

            list.add(movie);
        }

        for(Movie movie : list){
            for(Seance seance : movie.getSeances()) {
                SeanceView.Holder holder=new SeanceView.Holder(movie, seance);
                adapter.add(holder);
            }
        }

        adapter.sort(new CompareHolder());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_seance, container, false);

        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        twoWayView.setLayoutManager(new ListLayoutManager(getActivity(), TwoWayLayoutManager.Orientation.VERTICAL));
        twoWayView.setAdapter(adapter);
        int dimen=(int) getResources().getDimension(R.dimen.spacing_movie);
        twoWayView.addItemDecoration(new SpacingItemDecoration(dimen, dimen));

        support= ItemClickSupport.addTo(twoWayView);
        support.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int i, long l) {
                ImageView imageView=ButterKnife.findById(view, R.id.image);
                Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();

                Intent intent= DetailActivity.newIntent(getActivity(), adapter.get(i).getMovie(), bitmap);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Pair<View, String>[] pairs=new Pair[3];

                    TextView textView=ButterKnife.findById(view, R.id.title);
                    TextView dateTextView=ButterKnife.findById(view, R.id.date);

                    pairs[0]=new Pair<View, String>(textView, "transition_title");
                    pairs[1]=new Pair<View, String>(imageView, "transition_cover");
                    pairs[2]=new Pair<View, String>(imageView, "transition_date");


                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairs).toBundle();

                    getActivity().startActivity(intent, bundle);
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    private class CompareHolder implements Comparator<SeanceView.Holder> {

        @Override
        public int compare(SeanceView.Holder lhs, SeanceView.Holder rhs) {
            return lhs.getSeance().getDate().compareTo(rhs.getSeance().getDate());
        }
    }
}
