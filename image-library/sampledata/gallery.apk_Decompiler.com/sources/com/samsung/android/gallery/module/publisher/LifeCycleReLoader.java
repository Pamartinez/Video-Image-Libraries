package com.samsung.android.gallery.module.publisher;

import M9.g;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LifeCycleReLoader extends Subscriber {
    private static String TAG = "LifeCycleReLoader";
    protected volatile String mDataStamp;

    public LifeCycleReLoader(Blackboard blackboard) {
        super(blackboard);
    }

    private boolean isDataChanged() {
        if (this.mDataStamp == null || this.mDataStamp.equals(queryDataStamp())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onDataChanged(Object obj, Bundle bundle) {
        String str = TAG;
        Log.d(str, "onDataChanged " + obj);
        if ((obj instanceof EventMessage) && ((EventMessage) obj).what == 101) {
            updateDataStamp(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onPause(Object obj, Bundle bundle) {
        updateDataStamp(obj, bundle);
        Log.d(TAG, "onPause");
    }

    /* access modifiers changed from: private */
    public void onResume(Object obj, Bundle bundle) {
        if (isDataChanged()) {
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(101, 1, 0, TAG));
            Log.d(TAG, "onResume {changed}");
        } else if (this.mDataStamp != null) {
            Log.d(TAG, "onResume {same}");
        }
        this.mDataStamp = null;
    }

    private String queryDataStamp() {
        Cursor query;
        String string;
        String str = "no item";
        try {
            query = DbCompat.query(DbKey.FILES_DATA_STAMP);
            if (query != null) {
                if (query.moveToFirst() && (string = query.getString(0)) != null) {
                    str = string;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            Log.e((CharSequence) TAG, "queryDataStamp failed", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        String str2 = TAG;
        Log.d(str2, "DataStamp{" + str + "}");
        return str;
        throw th;
    }

    private void updateDataStamp(Object obj, Bundle bundle) {
        this.mDataStamp = queryDataStamp();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_pause", new g(this, 0)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_resume", new g(this, 1)));
        arrayList.add(new SubscriberInfo("command://event/DataChanged", new g(this, 2)));
    }
}
