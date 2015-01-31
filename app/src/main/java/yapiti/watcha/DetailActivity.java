package yapiti.watcha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.transition.AutoTransition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yapiti.watcha.entity.Movie;
import yapiti.watcha.tools.ViewHelper;


public class DetailActivity extends ActionBarActivity {
    private Movie movie;

    @InjectView(R.id.image) ImageView imageView;
    @InjectView(R.id.title) TextView title;

    private static final String ARG_MAIL="entity";
    private static final String PRE_LOADING_IMAGE="pre_image";


    public static @NonNull
        Intent newIntent(@NonNull Context context, @NonNull Movie entity){
        Intent intent=new Intent(context, DetailActivity.class);

        intent.putExtra(ARG_MAIL, entity);

        return intent;
    }

    public static @NonNull Intent newIntent(@NonNull Context context, @NonNull Movie entity, Bitmap bitmap){
        Intent intent=newIntent(context, entity);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        intent.putExtra(PRE_LOADING_IMAGE, stream.toByteArray());

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            supportRequestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setEnterTransition(new AutoTransition());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movie=getIntent().getParcelableExtra(ARG_MAIL);

        ButterKnife.inject(this);

        title.setText(movie.getTitle());

        ViewHelper.waitLayoutLoad(imageView, new ViewHelper.LayoutChange() {
            @Override
            public void done(View view) {
                RequestCreator requestCreator=Picasso.with(DetailActivity.this)
                        .load(movie.getCover());

                byte[] array=getIntent().getByteArrayExtra(PRE_LOADING_IMAGE);

                if(array!=null){
                    requestCreator.placeholder(new BitmapDrawable(getResources(),
                            BitmapFactory.decodeByteArray(array, 0, array.length)));
                }

                requestCreator.into(imageView);
            }
        });
    }
}
