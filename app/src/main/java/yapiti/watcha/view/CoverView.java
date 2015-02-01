package yapiti.watcha.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.R;
import yapiti.watcha.entity.Movie;
import yapiti.watcha.tools.ViewHelper;

/**
 * Created by yapiti on 31/01/15.
 */
public class CoverView extends FrameLayout {
    @InjectView(R.id.image) ImageView image;
    @InjectView(R.id.title) TextView title;


    public CoverView(Context context) {
        super(context);
        init();
    }

    public CoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_cover, this, true);
        ButterKnife.inject(this);
    }

    public void update(final Movie movie){
        title.setText(movie.getTitle());
        ViewHelper.waitLayoutLoad(image,  new ViewHelper.LayoutChange() {
            @Override
            public void done(View view) {
                Log.d("image", "w: "+image.getWidth()+" - "+image.getHeight());
                Picasso.with(getContext()).load(movie.getCover())
                        .resize(image.getWidth(), image.getHeight())
                        .centerCrop()
                        .into(image);
            }
        });
    }
}
