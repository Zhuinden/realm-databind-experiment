package com.zhuinden.realmdatabind.realm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhuinden.realmdatabind.BR;
import com.zhuinden.realmdatabind.R;
import com.zhuinden.realmdatabind.realm.objects.Post;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public class RealmPostAdapter extends RealmRecyclerViewAdapter<Post, RealmPostViewHolder> {
    public RealmPostAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Post> data) {
        super(context, data, true);
    }

    @Override
    public RealmPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_post, parent, false);
        return new RealmPostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RealmPostViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(BR.post, getItem(position));
    }
}
