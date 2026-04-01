package com.samsung.android.gallery.module.dataset;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataTrash extends MediaDataTimeline2 {
    private final Consumer<Integer> mRecoveryCallback = new O(4, this);

    public MediaDataTrash(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Integer num) {
        Log.d("MediaDataTrash", "TrashRecoveryUpdated", num);
        requestData(this.mLocationReference);
    }

    public HashMap<Long, MediaItem> buildTempDataMapInternal(String str, BooleanSupplier booleanSupplier, Blackboard blackboard, String str2) {
        HashMap<Long, MediaItem> hashMap = new HashMap<>();
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            Cursor trashCursor = TrashProviderFactory.getInstance(appContext).getTrashCursor(str);
            if (trashCursor != null) {
                try {
                    if (trashCursor.moveToFirst()) {
                        while (true) {
                            MediaItem load = MediaItemLoader.load(trashCursor);
                            hashMap.put(Long.valueOf(load.getFileId()), load);
                            if (!trashCursor.moveToNext() || (booleanSupplier != null && booleanSupplier.getAsBoolean())) {
                                break;
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (trashCursor != null) {
                trashCursor.close();
            }
        }
        return hashMap;
        throw th;
    }

    public void onCreate() {
        super.onCreate();
    }

    public synchronized void onDataCursorChanged(Object obj, Bundle bundle) {
        super.onDataCursorChanged(obj, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
