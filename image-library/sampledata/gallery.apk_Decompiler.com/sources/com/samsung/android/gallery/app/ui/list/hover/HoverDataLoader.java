package com.samsung.android.gallery.app.ui.list.hover;

import N2.j;
import U5.c;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HoverDataLoader {
    private final Object LOCK = new Object();
    private Blackboard mBlackboard;
    private MediaData mMediaData;
    private MediaData.SimpleDataChangeListener mOnDataChangedListener;

    public HoverDataLoader(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private void closeMediaData() {
        synchronized (this.LOCK) {
            try {
                MediaData mediaData = this.mMediaData;
                if (mediaData != null) {
                    Log.d("HoverDataLoader", "closeMediaData", Logger.getSimpleName((Object) mediaData));
                    MediaData.SimpleDataChangeListener simpleDataChangeListener = this.mOnDataChangedListener;
                    if (simpleDataChangeListener != null) {
                        this.mMediaData.unregister(simpleDataChangeListener);
                        this.mOnDataChangedListener = null;
                    }
                    this.mMediaData.close();
                    this.mMediaData = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void openMediaData(final String str, final HoverHandler.DataLoadCallback dataLoadCallback) {
        synchronized (this.LOCK) {
            final MediaData open = MediaDataFactory.getInstance(this.mBlackboard).create(str).open(str);
            this.mMediaData = open;
            Log.d("HoverDataLoader", "openMediaData", Logger.getSimpleName((Object) open), str);
            AnonymousClass1 r22 = new MediaData.SimpleDataChangeListener() {
                private final AtomicBoolean alreadyDataChanged = new AtomicBoolean(false);

                public void onDataChanged() {
                    if (!this.alreadyDataChanged.getAndSet(false)) {
                        ArrayList arrayList = new ArrayList();
                        Log.d("HoverDataLoader", "onDataChanged", Logger.getSimpleName((Object) open), str, Integer.valueOf(open.getCount()));
                        for (int i2 = 0; i2 < Math.min(open.getCount(), 9); i2++) {
                            arrayList.add(open.read(i2));
                        }
                        ThreadUtil.postOnUiThread(new b(dataLoadCallback, arrayList));
                        this.alreadyDataChanged.set(true);
                    }
                }
            };
            this.mOnDataChangedListener = r22;
            open.register(r22);
        }
    }

    public int getCount() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.getCount();
        }
        return 0;
    }

    public void load(String str, HoverParams hoverParams, HoverHandler.DataLoadCallback dataLoadCallback) {
        if (hoverParams.isAlbum()) {
            StringBuilder sb2 = new StringBuilder("loadMediaItems album{");
            sb2.append(hoverParams.getLocationKey());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(hoverParams.getDataPosition());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(str);
            j.y(sb2, "}", "HoverDataLoader");
            closeMediaData();
            openMediaData(str, dataLoadCallback);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(hoverParams.getItem());
        ThreadUtil.postOnUiThread(new c(5, dataLoadCallback, arrayList));
    }

    public MediaItem read(int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.read(i2);
        }
        return null;
    }

    public void release() {
        closeMediaData();
    }
}
