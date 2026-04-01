package com.samsung.android.gallery.module.dataset.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CursorReader {
    protected final String TAG = tag();
    private final int idxFileId;
    private final Cursor mCursor;
    protected final Semaphore mLock = new Semaphore(1);

    public CursorReader(Cursor cursor) {
        this.mCursor = cursor;
        this.idxFileId = cursor.getColumnIndex("__absID");
    }

    private List<Long> readAllDataId(int i2) {
        if (this.mCursor.isClosed()) {
            Log.e(this.TAG, "readAllDataId : cursor closed");
            return new ArrayList(0);
        }
        int count = this.mCursor.getCount();
        ArrayList arrayList = new ArrayList(count);
        if (count <= 0) {
            return arrayList;
        }
        try {
            this.mLock.acquire();
            if (this.mCursor.moveToFirst()) {
                do {
                    arrayList.add(Long.valueOf(this.mCursor.getLong(i2)));
                } while (this.mCursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.mLock.release();
            throw th;
        }
        this.mLock.release();
        return arrayList;
    }

    public MediaItem createMediaItem(int i2) {
        return createMediaItemInternal(i2);
    }

    public final MediaItem createMediaItemInternal(int i2) {
        try {
            this.mLock.acquire();
            MediaItem mediaItem = getMediaItem(this.mCursor, i2);
            this.mLock.release();
            return mediaItem;
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.mLock.release();
            return null;
        } catch (SQLiteException e7) {
            e7.printStackTrace();
            this.mLock.release();
            return null;
        } catch (Throwable th) {
            this.mLock.release();
            throw th;
        }
    }

    public MediaItem getMediaItem(Cursor cursor, int i2) {
        return MediaItemLoader.loadNoLock(cursor, i2);
    }

    public MediaItem getPrimitiveMediaItem(Cursor cursor) {
        return MediaItemLoader.getPrimitiveMediaItem(cursor);
    }

    public MediaItem loadAndGetPrimitive(int i2) {
        try {
            this.mLock.acquire();
            if (this.mCursor.moveToPosition(i2)) {
                MediaItem primitiveMediaItem = getPrimitiveMediaItem(this.mCursor);
                this.mLock.release();
                return primitiveMediaItem;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.mLock.release();
            throw th;
        }
        this.mLock.release();
        return null;
    }

    public List<Long> readAllFileId() {
        return readAllDataId(this.idxFileId);
    }

    public String tag() {
        return "CursorReader";
    }
}
