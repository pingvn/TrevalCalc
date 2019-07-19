package com.pingvn.trevalcalc.DataModel;

import io.realm.RealmObject;

public class IDRealm extends RealmObject {

    private int midTreval;
    private int midTourist;
    private int midDirection;

    public int getMidTreval() {
        return midTreval;
    }

    public void setMidTreval(int midTreval) {
        this.midTreval = midTreval;
    }

    public int getMidTourist() {
        return midTourist;
    }

    public void setMidTourist(int midTourist) {
        this.midTourist = midTourist;
    }

    public int getMidDirection() {
        return midDirection;
    }

    public void setMidDirection(int midDirection) {
        this.midDirection = midDirection;
    }
}
