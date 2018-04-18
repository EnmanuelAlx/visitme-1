package visit.me.gil.mota.visitme.models;

/**
 * Created by mota on 16/4/2018.
 */

public class Community {
    private String _id;
    private String name;
    private String kind;

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
}
