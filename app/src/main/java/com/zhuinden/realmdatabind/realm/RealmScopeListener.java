package com.zhuinden.realmdatabind.realm;

import android.support.v4.app.Fragment;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public class RealmScopeListener
        extends Fragment {
    public RealmScopeListener() {
        setRetainInstance(true);
        RealmManager.incrementCount();
    }

    @Override
    public void onDestroy() {
        RealmManager.decrementCount();
        super.onDestroy();
    }
}
