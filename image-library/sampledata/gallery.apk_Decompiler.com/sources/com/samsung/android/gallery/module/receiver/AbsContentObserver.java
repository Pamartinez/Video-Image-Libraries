package com.samsung.android.gallery.module.receiver;

import A.a;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsContentObserver extends ContentObserver {
    final String TAG = getClass().getSimpleName();
    final ArrayList<Uri> WATCH_URIS = getWatchUris();

    public AbsContentObserver(Handler handler) {
        super(handler);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerObserver$0(Context context, Uri uri) {
        context.getContentResolver().registerContentObserver(uri, false, this);
    }

    public abstract ArrayList<Uri> getWatchUris();

    public void onChange(boolean z) {
        Log.d(this.TAG, "onChange", Boolean.valueOf(z));
    }

    public boolean registerObserver(Context context) {
        ArrayList<Uri> arrayList = this.WATCH_URIS;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        try {
            this.WATCH_URIS.forEach(new a(context, this));
            String str = this.TAG;
            Log.d(str, "registerObserver " + this);
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("registerObserver failed e="), this.TAG);
            unregisterObserver(context);
            return false;
        }
    }

    public final void unregisterObserver(Context context) {
        ArrayList<Uri> arrayList = this.WATCH_URIS;
        if (arrayList != null && !arrayList.isEmpty()) {
            try {
                context.getContentResolver().unregisterContentObserver(this);
            } catch (Exception e) {
                a.s(e, new StringBuilder("unregisterObserver failed e="), this.TAG);
            }
        }
    }
}
