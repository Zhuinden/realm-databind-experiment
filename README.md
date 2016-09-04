# realm-databind-experiment
Making Realm work with Databinding.

Look at this beautiful RealmObject

``` java
package com.zhuinden.realmdatabind.realm.objects;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.zhuinden.realmdatabind.BR;
import com.zhuinden.realmdatabind.realm.databind.RealmDataBinding;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/*
 * Created by Zhuinden on 2016.09.04..
 */
public class Post extends RealmObject implements Observable, RealmDataBinding {
    @PrimaryKey
    private long id;

    private String text;

    @Ignore
    private transient PropertyChangeRegistry mCallbacks;

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        if(!isValid()) { // !isManaged() in Realm 2.0
            notifyPropertyChanged(BR.id);
        }
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if(!isValid()) { // !isManaged() in Realm 2.0
            notifyPropertyChanged(BR.text);
        }
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(callback);
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    @Override
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
}
```
