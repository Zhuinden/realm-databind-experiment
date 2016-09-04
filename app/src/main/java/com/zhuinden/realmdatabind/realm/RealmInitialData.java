package com.zhuinden.realmdatabind.realm;

import com.zhuinden.realmdatabind.realm.objects.Post;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Zhuinden on 2016.09.04..
 */
public class RealmInitialData
        implements Realm.Transaction {
    public static List<Post> posts = new ArrayList<>();

    static {
        Post post;
        post = new Post();
        post.setId(1L);
        post.setText("Blah");
        posts.add(post);

        post = new Post();
        post.setId(2L);
        post.setText("Bleh");
        posts.add(post);

        post = new Post();
        post.setId(3L);
        post.setText("Blaaah");
        posts.add(post);

        post = new Post();
        post.setId(4L);
        post.setText("Bleeeh");
        posts.add(post);

        post = new Post();
        post.setId(5L);
        post.setText("Blarghh");
        posts.add(post);
    }

    @Override
    public void execute(Realm realm) {
        for(Post post : posts) {
            realm.insertOrUpdate(post);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RealmInitialData;
    }

    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }
}
