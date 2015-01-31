package yapiti.watcha.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yapiti on 31/01/15.
 */
public class Movie implements Parcelable {
    private String title;
    private Uri cover;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getCover() {
        return cover;
    }

    public void setCover(Uri cover) {
        this.cover = cover;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.cover, 0);
    }

    public Movie() {
    }

    private Movie(Parcel in) {
        this.title = in.readString();
        this.cover = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
