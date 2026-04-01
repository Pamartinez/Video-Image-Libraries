package com.samsung.android.gallery.app.controller;

import F3.C0395a;
import H3.c;
import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataCollectionDelegate implements InstantSubscriberListener {
    private final Blackboard mBlackboard;
    private String mCurrentKey;
    private final ArrayList<Object> mData = new ArrayList<>();
    private final ArrayList<String> mDataKey = new ArrayList<>();
    private OnDataUpdateListener mDataUpdateListener;
    private EventContext mHandler;
    private final ArrayList<String> mKey = new ArrayList<>();
    private OnDataCompletionListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataCompletionListener {
        void onDataCompleted(EventContext eventContext, ArrayList<Object> arrayList);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataUpdateListener {
    }

    private DataCollectionDelegate(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public static DataCollectionDelegate getInitInstance(EventContext eventContext) {
        return getInstance(eventContext.getBlackboard()).clear().setContext(eventContext);
    }

    public static DataCollectionDelegate getInstance(Blackboard blackboard) {
        return (DataCollectionDelegate) blackboard.computeIfAbsent("data://DataCollectionDelegate", new C0395a(blackboard, 0));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DataCollectionDelegate lambda$getInstance$0(Blackboard blackboard, String str) {
        return new DataCollectionDelegate(blackboard);
    }

    private synchronized void next() {
        String str;
        try {
            if (this.mDataKey.isEmpty()) {
                str = null;
            } else {
                str = this.mDataKey.remove(0);
            }
            this.mCurrentKey = str;
            if (str == null) {
                notifyListener(this.mData);
            } else if (this.mHandler != null) {
                getBlackboard().subscribeOnUi(ArgumentsUtil.removeArgs(str), this);
                getBlackboard().post(CommandKey.DATA_REQUEST(str), (Object) null);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private void notifyListener(ArrayList<Object> arrayList) {
        OnDataCompletionListener onDataCompletionListener = this.mListener;
        if (onDataCompletionListener != null) {
            onDataCompletionListener.onDataCompleted(this.mHandler, arrayList);
        }
        clear();
    }

    private void unsubscribe() {
        if (this.mCurrentKey != null) {
            getBlackboard().unsubscribe(this.mCurrentKey, this);
            this.mCurrentKey = null;
        }
    }

    public DataCollectionDelegate clear() {
        Log.i("DataCollectionDelegate", "clear : " + this.mCurrentKey);
        this.mHandler = null;
        this.mDataUpdateListener = null;
        this.mListener = null;
        this.mData.clear();
        this.mKey.clear();
        this.mDataKey.clear();
        unsubscribe();
        return this;
    }

    public Object getCollectedData(String str) {
        int indexOf = this.mKey.indexOf(str);
        if (indexOf >= 0) {
            return this.mData.get(indexOf);
        }
        return null;
    }

    public void onNotify(Object obj, Bundle bundle) {
        OnDataUpdateListener onDataUpdateListener = this.mDataUpdateListener;
        if (onDataUpdateListener != null) {
            ((c) onDataUpdateListener).d.handleUpdate(this.mCurrentKey, obj);
        }
        if (obj == null) {
            notifyListener((ArrayList<Object>) null);
            return;
        }
        this.mKey.add(this.mCurrentKey);
        this.mData.add(obj);
        next();
    }

    public void replace(String str) {
        unsubscribe();
        this.mDataKey.add(0, str);
        next();
    }

    public DataCollectionDelegate setContext(EventContext eventContext) {
        this.mHandler = eventContext;
        return this;
    }

    public DataCollectionDelegate setOnDataCompleteListener(OnDataCompletionListener onDataCompletionListener) {
        this.mListener = onDataCompletionListener;
        return this;
    }

    public DataCollectionDelegate setOnDataUpdateListener(OnDataUpdateListener onDataUpdateListener) {
        this.mDataUpdateListener = onDataUpdateListener;
        return this;
    }

    public DataCollectionDelegate setRequestData(String... strArr) {
        this.mData.clear();
        this.mDataKey.clear();
        this.mDataKey.addAll(Arrays.asList(strArr));
        return this;
    }

    public void start() {
        next();
    }
}
