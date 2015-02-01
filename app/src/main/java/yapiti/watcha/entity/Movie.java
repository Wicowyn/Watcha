package yapiti.watcha.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

/**
 * Created by yapiti on 31/01/15.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.NONE)
public class Movie implements Parcelable {
    private String title;
    private Uri cover;
    private Uri bigCover;
    private String author;
    private String description;
    private ArrayList<Seance> seances =new ArrayList<>();


    public String getTitle() {
        return title;
    }

    @JsonProperty("name")
    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getCover() {
        return cover;
    }

    @JsonProperty("poster")
    @JsonDeserialize(using = UriDeserializer.class)
    public void setCover(Uri cover) {
        this.cover = cover;
    }

    public Uri getBigCover() {
        return bigCover;
    }

    @JsonProperty("cover")
    @JsonDeserialize(using = UriDeserializer.class)
    public void setBigCover(Uri bigCover) {
        this.bigCover = bigCover;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }

    public String getAuthor() {
        return author;
    }

    @JsonProperty("realisateur")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Movie() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.cover, 0);
        dest.writeParcelable(this.bigCover, 0);
        dest.writeString(this.author);
        dest.writeString(this.description);
        dest.writeList(this.seances);
    }

    private Movie(Parcel in) {
        this.title = in.readString();
        this.cover = in.readParcelable(Uri.class.getClassLoader());
        this.bigCover = in.readParcelable(Uri.class.getClassLoader());
        this.author = in.readString();
        this.description = in.readString();
        this.seances=new ArrayList<>();
        in.readList(seances, Seance.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
