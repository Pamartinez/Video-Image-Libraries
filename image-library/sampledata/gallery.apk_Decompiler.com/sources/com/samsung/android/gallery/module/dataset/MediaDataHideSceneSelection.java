package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.database.StaleDataException;
import com.samsung.android.gallery.database.dal.abstraction.SortableMergeCursor;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataHideSceneSelection extends MediaDataCursor {
    public MediaDataHideSceneSelection(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private void sortCursor(Cursor[] cursorArr) {
        try {
            if (this.mLock.acquireWriteLock() && cursorArr != null && cursorArr.length > 0) {
                SortableMergeCursor sortableMergeCursor = cursorArr[0];
                if (sortableMergeCursor instanceof SortableMergeCursor) {
                    sortableMergeCursor.addOrderBy("__dateTaken", false).addOrderBy("__absID", false).orderBy();
                }
            }
        } catch (StaleDataException | IllegalStateException e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "swap > fail by destroy e=" + e.getMessage());
        } catch (Throwable th) {
            this.mLock.releaseWriteLock();
            throw th;
        }
        this.mLock.releaseWriteLock();
    }

    public void swap(Cursor[] cursorArr) {
        sortCursor(cursorArr);
        super.swap(cursorArr);
    }
}
