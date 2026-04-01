package com.samsung.android.gallery.support.observable;

import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObservableArrayList<T> extends CopyOnWriteArrayList<T> implements ObservableList<T> {
    private transient ObservableList.OnListChangedCallback mListeners;

    public ObservableArrayList(T[] tArr) {
        super.addAll(Arrays.asList(tArr));
    }

    private void notifyAdd(int i2, int i7) {
        ObservableList.OnListChangedCallback onListChangedCallback = this.mListeners;
        if (onListChangedCallback != null) {
            onListChangedCallback.onItemRangeInserted(this, i2, i7);
        }
    }

    private void notifyRemove(int i2, int i7) {
        ObservableList.OnListChangedCallback onListChangedCallback = this.mListeners;
        if (onListChangedCallback != null) {
            onListChangedCallback.onItemRangeRemoved(this, i2, i7);
        }
    }

    public boolean add(T t) {
        super.add(t);
        notifyAdd(size() - 1, 1);
        return true;
    }

    public boolean addAll(Collection<? extends T> collection) {
        int size = size();
        boolean addAll = super.addAll(collection);
        if (addAll) {
            notifyAdd(size, size() - size);
        }
        return addAll;
    }

    public void clear() {
        int size = size();
        super.clear();
        if (size != 0) {
            notifyRemove(0, size);
        }
    }

    public T remove(int i2) {
        T remove = super.remove(i2);
        notifyRemove(i2, 1);
        return remove;
    }

    public void removeOnListChangedCallback() {
        this.mListeners = null;
    }

    public T set(int i2, T t) {
        T t3 = super.set(i2, t);
        ObservableList.OnListChangedCallback onListChangedCallback = this.mListeners;
        if (onListChangedCallback != null) {
            onListChangedCallback.onItemRangeChanged(this, i2, 1);
            return t3;
        }
        Log.w("ObservableList", "set w/o Noti : " + i2 + ", 1");
        return t3;
    }

    public void setOnListChangedCallback(ObservableList.OnListChangedCallback onListChangedCallback) {
        this.mListeners = onListChangedCallback;
    }

    public void add(int i2, T t) {
        super.add(i2, t);
        notifyAdd(i2, 1);
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    public boolean addAll(int i2, Collection<? extends T> collection) {
        boolean addAll = super.addAll(i2, collection);
        if (addAll) {
            notifyAdd(i2, collection.size());
        }
        return addAll;
    }
}
