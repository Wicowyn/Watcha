package yapiti.watcha.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.entity.Movie;
import yapiti.watcha.entity.Seance;
import yapiti.watcha.tools.ViewHelper;

/**
 * Created by yapiti on 31/01/15.
 */
public class SeanceView extends FrameLayout {
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.date) TextView info;
    @InjectView(R.id.image) ImageView image;
    @InjectView(R.id.description) TextView description;


    public SeanceView(Context context) {
        super(context);
        init();
    }

    public SeanceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SeanceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_seance, this, true);

        ButterKnife.inject(this);
    }

    public void update(final Holder entity){
        title.setText(entity.getMovie().getTitle());

        SimpleDateFormat df=new SimpleDateFormat("dd MMMM HH:mm");
        info.setText(df.format(entity.getSeance().getDate()));
        description.setText(entity.getMovie().getDescription());

        ViewHelper.waitLayoutLoad(image, new ViewHelper.LayoutChange() {
            @Override
            public void done(View view) {
                Picasso.with(getContext()).load(entity.getMovie().getCover())
                        .into(image);
            }
        });
    }

    public static class Holder {
        private Movie movie;
        private Seance seance;


        public Holder(Movie movie, Seance seance) {
            this.movie=movie;
            this.seance=seance;
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        public Seance getSeance() {
            return seance;
        }

        public void setSeance(Seance seance) {
            this.seance = seance;
        }
    }
}
