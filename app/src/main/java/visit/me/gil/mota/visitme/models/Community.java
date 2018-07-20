package visit.me.gil.mota.visitme.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by mota on 16/4/2018.
 */

public class Community implements Parcelable {
    private String _id;
    private String name;
    private String kind;
    private String image;
    private String status;
    private Address address;
    private HashMap<String, Boolean> state;

    public Community() {
        state = new HashMap<>();
    }

    protected Community(Parcel in) {
        _id = in.readString();
        name = in.readString();
        kind = in.readString();
        state = new HashMap<>();
    }

    public static final Creator<Community> CREATOR = new Creator<Community>() {
        @Override
        public Community createFromParcel(Parcel in) {
            return new Community(in);
        }

        @Override
        public Community[] newArray(int size) {
            return new Community[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeString(kind);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status != null ? status : "PENDING";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address.getFullAddress();
    }

    public void setState(String state, boolean value) {
        if (this.state == null)
            this.state = new HashMap<>();
        this.state.put(state, value);
    }

    public boolean is(String state) {
        if(this.state == null)
            this.state = new HashMap<>();
        return this.state.containsKey(state) ? this.state.get(state) : false;
    }
}
