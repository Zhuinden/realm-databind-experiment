package com.zhuinden.realmdatabind.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.zhuinden.realmdatabind.activity.base.RealmActivity;
import com.zhuinden.realmdatabind.databinding.ActivityMainBinding;
import com.zhuinden.realmdatabind.realm.databind.RealmDataBinding;
import com.zhuinden.realmdatabind.realm.objects.Post;
import com.zhuinden.realmdatabind.R;
import com.zhuinden.realmdatabind.stuff.WriteElements;

public class MainActivity
        extends RealmActivity {
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        post = realm.where(Post.class).equalTo("id", 1L).findFirst();
        post.addChangeListener(RealmDataBinding.FACTORY.create());
        binding.setPost(post);
        binding.setActivity(this);

        //IGNORE THIS LINE
        setupWriteFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(post != null) {
            if(post.isValid()) {
                post.removeChangeListeners();
            }
        }
    }

    public void startNextActivity(View view) {
        Log.i("MAINACTIVITY", "START NEXT ACTIVITY");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    // IGNORE THIS
    private void setupWriteFragment() {
        WriteFragment writeFragment = (WriteFragment) getSupportFragmentManager().findFragmentByTag("WRITE_FRAGMENT");
        if(writeFragment == null) {
            writeFragment = new WriteFragment();
            getSupportFragmentManager().beginTransaction().add(writeFragment, "WRITE_FRAGMENT").commit();
        }
    }

    public static class WriteFragment extends Fragment {
        public WriteFragment() {
            setRetainInstance(true);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            new WriteElements().write();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    }
}
