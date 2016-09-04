package com.zhuinden.realmdatabind.util;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 https://github.com/jayway/khelg2015-5/blob/binding_to_a_adapter_solution_simon_zettervall/android/app/src/main/java/se/jayway/databinding/CustomViewHolder.java
 */
public class DataBindViewHolder
        extends RecyclerView.ViewHolder {
    private ViewDataBinding mViewDataBinding;

    public DataBindViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());

        mViewDataBinding = viewDataBinding;
        mViewDataBinding.executePendingBindings();
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }
}
