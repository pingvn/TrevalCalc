package ru.pingvn.trevalcalc.DataModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tourist extends RealmObject {
    private int mid;
    @PrimaryKey
    private String mName;

    private double mCredit;
    private double mDeposite;

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

    public double getmCredit() {
        return mCredit;
    }

    public void setmCredit(double mCredit) {
        this.mCredit = mCredit;
    }

    public double getmDeposite() {
        return mDeposite;
    }

    public void setmDeposite(double mDeposite) {
        this.mDeposite = mDeposite;
    }
}
