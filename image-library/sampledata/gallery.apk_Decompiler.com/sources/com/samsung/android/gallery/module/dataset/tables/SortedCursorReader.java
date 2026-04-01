package com.samsung.android.gallery.module.dataset.tables;

import android.database.Cursor;
import android.database.StaleDataException;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.comparator.ComparatorEx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SortedCursorReader<T extends ComparatorEx<MediaItem>> extends CursorReader {
    private final int mCount;
    private final ArrayList<MediaItem> mMediaItems = new ArrayList<>();

    public SortedCursorReader(Cursor cursor) {
        super(cursor);
        this.mCount = cursor.getCount();
        loadCursor();
    }

    private void loadCursor() {
        for (int i2 = 0; i2 < this.mCount; i2++) {
            this.mMediaItems.add(createMediaItemInternal(i2));
        }
    }

    public MediaItem createMediaItem(int i2) {
        try {
            this.mLock.acquire();
            if (i2 >= 0 && i2 < this.mCount) {
                MediaItem mediaItem = this.mMediaItems.get(i2);
                this.mLock.release();
                return mediaItem;
            }
        } catch (StaleDataException | InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.mLock.release();
            throw th;
        }
        this.mLock.release();
        return null;
    }

    public MediaItem loadAndGetPrimitive(int i2) {
        return createMediaItem(i2);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, java.util.function.Function] */
    public List<Long> readAllFileId() {
        try {
            this.mLock.acquire();
            List<Long> list = (List) this.mMediaItems.stream().map(new Object()).collect(Collectors.toList());
            this.mLock.release();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            this.mLock.release();
            return new ArrayList();
        } catch (Throwable th) {
            this.mLock.release();
            throw th;
        }
    }

    public void sort(T t) {
        if (t != null) {
            t.prepare(this.mMediaItems);
            Collections.sort(this.mMediaItems, t);
            t.complete(this.mMediaItems);
        }
    }

    public String tag() {
        return "SortedCursorReader";
    }
}
