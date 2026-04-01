package com.samsung.android.gallery.module.data;

import Fa.C0571z;
import M8.c;
import android.database.Cursor;
import android.database.StaleDataException;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemLoader {
    private static final AbsMediaItemLoader sLoaderChain = ((AbsMediaItemLoader) new ChainBuilder().append(new LocalDataLoader()).build());

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface LoadingStatusInformer {
    }

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMediaItemLoadingListener {
    }

    public static List<MediaItem> flatten(MediaItem[] mediaItemArr, Predicate<MediaItem> predicate) {
        return flatten((Collection<MediaItem>) Arrays.asList(mediaItemArr), predicate);
    }

    private static MediaItem getMediaItem(Cursor cursor, int i2) {
        MediaItem mediaItem;
        synchronized (cursor) {
            try {
                if (cursor.moveToPosition(i2)) {
                    mediaItem = getMediaItem(cursor);
                } else {
                    throw new AssertionError("fail to move cursor. cursor size=" + cursor.getCount() + ", request=" + i2);
                }
            } catch (StaleDataException e) {
                debugStaleDataException(e, cursor);
                throw e;
            }
        }
        return mediaItem;
    }

    public static MediaItem getPrimitiveMediaItem(Cursor cursor) {
        try {
            return sLoaderChain.getPrimitiveMediaItem(cursor);
        } catch (StaleDataException e) {
            debugStaleDataException(e, cursor);
            throw e;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$flatten$1(Predicate predicate, ArrayList arrayList, MediaItem mediaItem) {
        if (mediaItem.isGroupShot()) {
            Cursor groupCursor = DbCompatGroup.getGroupCursor(mediaItem);
            if (groupCursor != null) {
                try {
                    if (groupCursor.moveToFirst()) {
                        long fileId = mediaItem.getFileId();
                        do {
                            MediaItem create = MediaItemBuilder.create(groupCursor);
                            if (create.getFileId() != fileId) {
                                if (predicate != null) {
                                    if (predicate.test(create)) {
                                    }
                                }
                                create.resetGroupShotMode();
                                arrayList.add(create);
                            }
                        } while (groupCursor.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (groupCursor != null) {
                groupCursor.close();
            }
            mediaItem.resetGroupShotMode();
        }
        if (predicate == null || predicate.test(mediaItem)) {
            arrayList.add(mediaItem);
            return;
        }
        return;
        throw th;
    }

    public static MediaItem load(Cursor cursor) {
        return getMediaItem(cursor);
    }

    public static MediaItem loadMediaItem(Cursor cursor, int i2) {
        synchronized (cursor) {
            try {
                if (cursor.moveToPosition(i2)) {
                    MediaItem mediaItem = sLoaderChain.getMediaItem(cursor);
                    return mediaItem;
                }
                Log.e("MediaItemLoader", "cursor=" + cursor + ".\nfail to move cursor. s=" + cursor.getCount() + ", req=" + i2);
                return null;
            } catch (StaleDataException | IllegalStateException e) {
                Log.e("MediaItemLoader", "StaleDataException : " + cursor + ", e=" + e.getMessage());
                return null;
            }
        }
    }

    public static MediaItem loadNoLock(Cursor cursor, int i2) {
        if (cursor.moveToPosition(i2)) {
            return getMediaItem(cursor);
        }
        Log.e("MediaItemLoader", "fail move : " + cursor + ", p=" + i2);
        return null;
    }

    public static MediaItem loadSecMediaId(long j2) {
        Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new c(j2, 3));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    MediaItem load = load(query);
                    query.close();
                    return load;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
        throw th;
    }

    public static List<MediaItem> flatten(Collection<MediaItem> collection, Predicate<MediaItem> predicate) {
        ArrayList arrayList = new ArrayList();
        collection.stream().filter(new C0571z(4)).forEach(new o(3, predicate, arrayList));
        return arrayList;
    }

    public static MediaItem load(Cursor cursor, int i2) {
        return getMediaItem(cursor, i2);
    }

    private static MediaItem getMediaItem(Cursor cursor) {
        MediaItem mediaItem;
        synchronized (cursor) {
            try {
                mediaItem = sLoaderChain.getMediaItem(cursor);
            } catch (StaleDataException e) {
                debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaItem;
    }

    public static void debugStaleDataException(Exception exc, Cursor cursor) {
    }
}
