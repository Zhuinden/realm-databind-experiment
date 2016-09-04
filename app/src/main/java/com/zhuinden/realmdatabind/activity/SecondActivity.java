package com.zhuinden.realmdatabind.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuinden.realmdatabind.R;
import com.zhuinden.realmdatabind.activity.base.RealmActivity;

import com.zhuinden.realmdatabind.databinding.ActivitySecondBinding;
import com.zhuinden.realmdatabind.realm.RealmPostAdapter;
import com.zhuinden.realmdatabind.realm.objects.Post;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public class SecondActivity
        extends RealmActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding activitySecondBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        RecyclerView recyclerView = activitySecondBinding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RealmPostAdapter(this, realm.where(Post.class).findAllAsync()));
    }
}
