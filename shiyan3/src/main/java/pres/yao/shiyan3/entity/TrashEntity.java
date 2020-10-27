package pres.yao.shiyan3.entity;

import android.os.Parcel;
import android.os.Parcelable;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ClassName TranshEntity
 * @Description TOOD
 * Date 2020/10/26 21:41
 **/
@Entity
public class TrashEntity implements Parcelable{
    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String explain;
    private String contain;

    @Override
    public String toString() {
        return "TrashEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", explain='" + explain + '\'' +
                ", contain='" + contain + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }

    private String tip;
    @Generated(hash = 1875285388)
    public TrashEntity(Long id, String name, String explain, String contain,
            String tip) {
        this.id = id;
        this.name = name;
        this.explain = explain;
        this.contain = contain;
        this.tip = tip;
    }
    @Generated(hash = 67470412)
    public TrashEntity() {
    }

    protected TrashEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        explain = in.readString();
        contain = in.readString();
        tip = in.readString();
    }

    public static final Creator<TrashEntity> CREATOR = new Creator<TrashEntity>() {
        @Override
        public TrashEntity createFromParcel(Parcel in) {
            return new TrashEntity(in);
        }

        @Override
        public TrashEntity[] newArray(int size) {
            return new TrashEntity[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getExplain() {
        return this.explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public String getContain() {
        return this.contain;
    }
    public void setContain(String contain) {
        this.contain = contain;
    }
    public String getTip() {
        return this.tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(explain);
        dest.writeString(contain);
        dest.writeString(tip);
    }
}
