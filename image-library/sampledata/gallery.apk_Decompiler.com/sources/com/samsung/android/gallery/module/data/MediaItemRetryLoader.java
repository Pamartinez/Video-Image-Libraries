package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaItemRetryLoader {
    private static final Handler mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("MediaItemReLo"));
    int mDelayMsec;
    private Consumer<MediaItem> mFailCallback;
    GroupType mGroupType;
    Consumer<QueryParams> mParamsConsumer;
    int mReloadCounter;
    Function<MediaItem, Boolean> mResultChecker;
    Function<MediaItem, Boolean> mRetryChecker;
    Consumer<MediaItem> mSuccess;

    public MediaItemRetryLoader(int i2, int i7) {
        this.mReloadCounter = i2;
        this.mDelayMsec = i7;
    }

    private boolean isNotExpected(MediaItem mediaItem) {
        Function<MediaItem, Boolean> function = this.mResultChecker;
        if (function == null || function.apply(mediaItem).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean isRetryAble(MediaItem mediaItem) {
        Function<MediaItem, Boolean> function = this.mRetryChecker;
        if (function == null || function.apply(mediaItem).booleanValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(Uri uri) {
        boolean z = true;
        this.mReloadCounter--;
        MediaItem query = query(uri);
        if (query != null && !isNotExpected(query)) {
            Log.i("MediaItemRetryLoader", "load : " + uri, Integer.valueOf(this.mReloadCounter));
            Consumer<MediaItem> consumer = this.mSuccess;
            if (consumer != null) {
                consumer.accept(query);
            }
        } else if (this.mReloadCounter <= 0 || !isRetryAble(query)) {
            if (query != null) {
                z = false;
            }
            Log.i("MediaItemRetryLoader", "load failed", Boolean.valueOf(z));
            Consumer<MediaItem> consumer2 = this.mFailCallback;
            if (consumer2 != null) {
                consumer2.accept(query);
            }
        } else {
            Log.i("MediaItemRetryLoader", "retry : " + this.mReloadCounter);
            load(uri, this.mDelayMsec);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadSync$1(MediaItem[] mediaItemArr, CountDownLatch countDownLatch, MediaItem mediaItem) {
        mediaItemArr[0] = mediaItem;
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$query$3(Uri uri, QueryParams queryParams) {
        queryParams.addUri(uri);
        GroupType groupType = this.mGroupType;
        if (groupType != null) {
            queryParams.setGroupTypes(groupType);
        }
        Consumer<QueryParams> consumer = this.mParamsConsumer;
        if (consumer != null) {
            consumer.accept(queryParams);
        }
        queryParams.addDataStamp();
    }

    private void load(Uri uri, int i2) {
        mHandler.postDelayed(new h(1, this, uri), (long) i2);
    }

    private MediaItem loadSync(Uri uri) {
        if (this.mSuccess == null) {
            int i2 = (this.mReloadCounter * this.mDelayMsec) + StatusCodes.INPUT_MISSING;
            MediaItem[] mediaItemArr = new MediaItem[1];
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.mSuccess = new o(4, mediaItemArr, countDownLatch);
            this.mFailCallback = new f(2, countDownLatch);
            load(uri, 0);
            try {
                countDownLatch.await((long) i2, TimeUnit.MILLISECONDS);
                return mediaItemArr[0];
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new AssertionError("do not set  SuccessCallback for sync");
        }
    }

    private MediaItem query(Uri uri) {
        Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new o(5, this, uri));
        if (query != null) {
            try {
                if (query.getCount() > 0 && query.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(query);
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

    public void getMediaItemFromFileId(long j2) {
        if (this.mSuccess != null) {
            load(MediaUri.getSecMediaUri().buildUpon().appendPath(String.valueOf(j2)).build(), 0);
            return;
        }
        throw new AssertionError("have to call setSuccessCallback");
    }

    public MediaItem getMediaItemFromFileIdSync(long j2) {
        return loadSync(MediaUri.getSecMediaUri().buildUpon().appendPath(String.valueOf(j2)).build());
    }

    public void getMediaItemFromUri(Uri uri) {
        if (this.mSuccess != null) {
            load(uri, 0);
            return;
        }
        throw new AssertionError("have to call setSuccessCallback");
    }

    public MediaItemRetryLoader setFailCallback(Consumer<MediaItem> consumer) {
        this.mFailCallback = consumer;
        return this;
    }

    public MediaItemRetryLoader setGroupType(GroupType groupType) {
        this.mGroupType = groupType;
        return this;
    }

    public MediaItemRetryLoader setResultChecker(Function<MediaItem, Boolean> function) {
        this.mResultChecker = function;
        return this;
    }

    public MediaItemRetryLoader setRetryChecker(Function<MediaItem, Boolean> function) {
        this.mRetryChecker = function;
        return this;
    }

    public MediaItemRetryLoader setSuccessCallback(Consumer<MediaItem> consumer) {
        this.mSuccess = consumer;
        return this;
    }

    public MediaItemRetryLoader updateQueryParams(Consumer<QueryParams> consumer) {
        this.mParamsConsumer = consumer;
        return this;
    }
}
