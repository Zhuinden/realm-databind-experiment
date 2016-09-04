package com.zhuinden.realmdatabind.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhuinden.realmdatabind.realm.RealmManager;
import com.zhuinden.realmdatabind.realm.RealmScopeListener;

import io.realm.Realm;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public class RealmActivity extends AppCompatActivity {
    protected Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        RealmManager.initializeRealmConfig(getApplicationContext());
        super.onCreate(savedInstanceState);
        RealmScopeListener realmScopeListener = (RealmScopeListener)getSupportFragmentManager().findFragmentByTag("SCOPE_LISTENER");
        if(realmScopeListener == null) {
            realmScopeListener = new RealmScopeListener();
            getSupportFragmentManager().beginTransaction().add(realmScopeListener, "SCOPE_LISTENER").commit();
        }
        realm = RealmManager.getRealm();
    }
}
