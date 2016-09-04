package com.zhuinden.realmdatabind.realm.databind;

import io.realm.RealmChangeListener;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public interface RealmDataBinding {
    interface Factory {
        RealmChangeListener create();
    }

    RealmDataBinding.Factory FACTORY = () -> element -> {
        if(element instanceof RealmDataBinding) {
            ((RealmDataBinding)element).notifyChange();
        }
    };

    void notifyChange();
}
