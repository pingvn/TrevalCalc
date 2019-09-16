package ru.pingvn.trevalcalc.DataModel;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Treval extends RealmObject {
    private int mid;

    @PrimaryKey
    private String mName;
    private RealmList<Direction> mDirection = new RealmList<>();
    private RealmList<Tourist> mTurists = new RealmList<>();

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public RealmList<Direction> getmDirection() {
        return mDirection;
    }

    public void setmDirection(RealmList<Direction> mDirection) {
        this.mDirection.clear();
        this.mDirection.addAll(mDirection);
    }

    public RealmList<Tourist> getmTurists() {
        return mTurists;
    }

    public void setmTurists(RealmList<Tourist> mTurists) {
        this.mTurists.clear();
        this.mTurists.addAll(mTurists);
    }
}
