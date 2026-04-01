package com.samsung.android.gallery.app.activity.external;

import A.a;
import A4.C0372g;
import A4.Q;
import A9.b;
import D3.j;
import D3.k;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesViewNavigatorDelegate {
    final Blackboard mBlackboard;
    final LaunchIntent mLaunchIntent;

    public StoriesViewNavigatorDelegate(LaunchIntent launchIntent, Blackboard blackboard) {
        this.mLaunchIntent = launchIntent;
        this.mBlackboard = blackboard;
    }

    private void finishByFailure() {
        Utils.showToast(AppResources.getAppContext(), (int) R.string.story_not_found);
        ThreadUtil.postOnUiThread(new C0372g(25, this));
    }

    private int getStoryId(String str) {
        int storyId = this.mLaunchIntent.getStoryId();
        if (storyId >= 0 || TextUtils.isEmpty(str)) {
            return storyId;
        }
        int argValue = ArgumentsUtil.getArgValue(str, "id", -1);
        Log.d("StoriesViewNavigatorDelegate", "location", Integer.valueOf(argValue), str);
        return argValue;
    }

    private static int getStoryIdByUgci(String str, int i2) {
        int storyIdByUgci;
        if (TextUtils.isEmpty(str) || (storyIdByUgci = DbCompat.storyApi().getStoryIdByUgci(str)) <= 0) {
            return i2;
        }
        return storyIdByUgci;
    }

    private String getStoryUgci() {
        return this.mLaunchIntent.getStoryUgci();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishByFailure$4() {
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandle$1(AtomicInteger atomicInteger, AtomicReference atomicReference, long j2) {
        atomicInteger.set(getStoryIdByUgci(getStoryUgci(), atomicInteger.get()));
        Cursor query = DbCompat.query(DbKey.STORIES, new k(atomicInteger, 0));
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    atomicReference.set(MediaItemBuilder.create(query));
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        a.x(new StringBuilder("load header +"), j2, "StoriesViewNavigatorDelegate");
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandle$2(AtomicReference atomicReference, AtomicInteger atomicInteger) {
        MediaItem mediaItem = (MediaItem) atomicReference.get();
        if (mediaItem == null || mediaItem.isEmpty()) {
            Log.d("StoriesViewNavigatorDelegate", "finish because of no data ");
            finishByFailure();
            return;
        }
        preloadBitmap(mediaItem);
        if (MediaItemStory.isRecap(mediaItem)) {
            moveToRecap(mediaItem);
        } else {
            moveToStoryHighlight(mediaItem, atomicInteger.get());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadBitmap$3(TimeTickLog timeTickLog, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        timeTickLog.tick("load");
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            preloadBlurBitmap(mediaItem, bitmap);
            timeTickLog.tick("blur");
        }
        Log.d("StoriesViewNavigatorDelegate", "[preloadBitmap]" + Logger.vt(Logger.toSimpleString(bitmap)), Long.valueOf(timeTickLog.tock(0)));
    }

    private void moveToRecap(MediaItem mediaItem) {
        this.mBlackboard.post("command://MoveURL", new StoryUriBuilder(this.mBlackboard, mediaItem, true, "location://recap").withFromLocation("location://story/albums").appendArg("media_item", BlackboardUtils.publishMediaItem(this.mBlackboard, mediaItem)).build());
    }

    private void moveToStoryHighlight(MediaItem mediaItem, int i2) {
        this.mBlackboard.post("command://MoveURL", new StoryUriBuilder(this.mBlackboard, (ThumbnailInterface) mediaItem).withStoryId(i2).appendArg("fromNobody", Boolean.TRUE).build());
    }

    private void preloadBitmap(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new Q((Object) this, (Object) new TimeTickLog("preloadBitmap"), (Object) mediaItem, 1));
        }
    }

    private void preloadBlurBitmap(MediaItem mediaItem, Bitmap bitmap) {
        Bitmap bitmap2;
        int i2;
        if (bitmap != null) {
            bitmap2 = BitmapUtils.blurAfterResize(AppResources.getAppContext(), bitmap, 64);
        } else {
            bitmap2 = null;
        }
        if (bitmap2 != null) {
            if (mediaItem.isBroken() || mediaItem.isVideo()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            this.mBlackboard.publish(LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(mediaItem.getFileId())), BitmapUtils.rotateBitmap(bitmap2, i2));
        }
    }

    public void onHandle(String str) {
        AtomicInteger atomicInteger = new AtomicInteger(getStoryId(str));
        AtomicReference atomicReference = new AtomicReference();
        Log.d("StoriesViewNavigatorDelegate", "launch storyId", Integer.valueOf(getStoryId(str)));
        long currentTimeMillis = System.currentTimeMillis();
        new LatchBuilder("LaunchStoryPictures").addWorker(new j((Object) this, (Object) atomicInteger, (Serializable) atomicReference, currentTimeMillis, 0)).setPostExecutor((Runnable) new b(this, atomicReference, atomicInteger, 8)).setTimeout(2000).start();
        a.A(new Object[]{MediaItemUtil.getSimpleLog((MediaItem) atomicReference.get()), Long.valueOf(currentTimeMillis)}, new StringBuilder("launchStory"), "StoriesViewNavigatorDelegate");
    }
}
