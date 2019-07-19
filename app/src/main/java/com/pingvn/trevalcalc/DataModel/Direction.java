package com.pingvn.trevalcalc.DataModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Direction extends RealmObject {
    private int mid;

    @PrimaryKey
    private String mName;

    private String mInfo;
    private double mTicetCoast; // расходы на билеты
    private double mAccomodationCoast; // расходы на размещение
    private double mFoodCoast; //расходы на еду
    private double fare; //транспортные расходы

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

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public double getmTicetCoast() {
        return mTicetCoast;
    }

    public void setmTicetCoast(double mTicetCoast) {
        this.mTicetCoast = mTicetCoast;
    }

    public double getmAccomodationCoast() {
        return mAccomodationCoast;
    }

    public void setmAccomodationCoast(double mAccomodationCoast) {
        this.mAccomodationCoast = mAccomodationCoast;
    }

    public double getmFoodCoast() {
        return mFoodCoast;
    }

    public void setmFoodCoast(double mFoodCoast) {
        this.mFoodCoast = mFoodCoast;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
