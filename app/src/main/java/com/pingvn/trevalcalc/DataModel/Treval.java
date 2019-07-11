package com.pingvn.trevalcalc.DataModel;



import io.realm.RealmList;
import io.realm.RealmObject;

public class Treval extends RealmObject {
    private int mid;
    private String mName;
    private Direction mDirection;
    private RealmList<Tourist> mTurists;

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

    public Direction getmDirection() {
        return mDirection;
    }

    public void setmDirection(Direction mDirection) {
        this.mDirection = mDirection;
    }

    public RealmList<Tourist> getmTurists() {
        return mTurists;
    }

    public void setmTurists(RealmList<Tourist> mTurists) {
        this.mTurists = mTurists;
    }
}
