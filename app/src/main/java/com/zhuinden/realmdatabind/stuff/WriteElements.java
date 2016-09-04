package com.zhuinden.realmdatabind.stuff;

import android.os.Looper;
import android.util.Log;

import com.zhuinden.realmdatabind.realm.objects.Post;

import java.util.concurrent.Callable;

import bolts.Task;
import io.realm.Realm;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public class WriteElements {
    private static final String TAG = "WriteElements";

    public void write() {
        Task.callInBackground(new RealmCallable(writeFirst))
                .continueWithTask(ignored -> Task.call(new RealmCallable(writeSecond)))
                .continueWithTask(ignored -> Task.call(new RealmCallable(writeThird)))
                .continueWithTask(ignored -> Task.call(new RealmCallable(writeFourth)))
                .continueWithTask(ignored -> Task.call(new RealmCallable(writeFifth)));

    }

    private static class RealmCallable
            implements Callable<Void> {
        private Realm.Transaction transaction;

        public RealmCallable(Realm.Transaction transaction) {
            this.transaction = transaction;
        }

        @Override
        public Void call()
                throws Exception {
            if(Looper.myLooper() == Looper.getMainLooper()) {
                throw new IllegalStateException("Should not be on main thread!");
            }
            Realm realm = null;
            try {
                Thread.sleep(2500);
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(transaction);
                Log.i("THREAD", "Realm execute transaction done");
            } catch(InterruptedException e) {
                Log.e("THREAD", "Error", e);
            } finally {
                if(realm != null) {
                    realm.close();
                }
            }
            return null;
        }
    }

    private Realm.Transaction writeFirst = realm -> {
        Post post = realm.where(Post.class).equalTo("id", 1L).findFirst();
        post.setText("BLOOOOOOAEGH");
        Log.i(TAG, "BLOOOOOOAEGH");
    };

    private Realm.Transaction writeSecond = realm -> {
        Post post = realm.where(Post.class).equalTo("id", 2L).findFirst();
        post.setText("BLEEEEEEEEEH");
        Log.i(TAG, "BLEEEEEEEEEH");
    };

    private Realm.Transaction writeThird = realm -> {
        Post post = realm.where(Post.class).equalTo("id", 3L).findFirst();
        post.setText("BLOOOOOOOH");
        Log.i(TAG, "BLOOOOOOOH");
    };

    private Realm.Transaction writeFourth = realm -> {
        Post post = realm.where(Post.class).equalTo("id", 4L).findFirst();
        post.setText("BLAAAAAAAAAH");
        Log.i(TAG, "BLAAAAAAAAAH");
    };

    private Realm.Transaction writeFifth = realm -> {
        Post post = realm.where(Post.class).equalTo("id", 5L).findFirst();
        post.setText("William Shakespeare - Hamlet");
        Log.i(TAG, "William Shakespeare - Hamlet");
    };
}
