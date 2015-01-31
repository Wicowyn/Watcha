package yapiti.watcha.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by yapiti on 31/01/15.
 */
public class Seance implements Parcelable {
    private Date date;


    public Seance(Date date) {
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date != null ? date.getTime() : -1);
    }

    public Seance() {
    }

    private Seance(Parcel in) {
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
    }

    public static final Parcelable.Creator<Seance> CREATOR = new Parcelable.Creator<Seance>() {
        public Seance createFromParcel(Parcel source) {
            return new Seance(source);
        }

        public Seance[] newArray(int size) {
            return new Seance[size];
        }
    };
}
