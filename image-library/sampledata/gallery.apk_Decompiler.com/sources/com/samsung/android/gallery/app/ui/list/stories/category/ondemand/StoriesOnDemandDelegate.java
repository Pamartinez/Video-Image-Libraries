package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import A.a;
import B8.g;
import J5.c;
import U5.b;
import a6.m;
import a6.o;
import a6.p;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.EffectItemBuilder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.module.story.ondemand.OnDemandResultData;
import com.samsung.android.gallery.module.story.ondemand.StoryGenApi;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesOnDemandDelegate {
    private long mBackPressedTime;
    private final Context mContext;
    private long mExecuteTime;
    private final AtomicBoolean mIsCanceled = new AtomicBoolean(false);
    private StoryGenApi mOnDemandStory;
    private final ProcessingViewManager mProcessingManager;
    private SearchToolbarDelegate mSearchToolbarDelegate;
    private final IOnDemandFloatingView mView;

    public StoriesOnDemandDelegate(IOnDemandFloatingView iOnDemandFloatingView) {
        this.mView = iOnDemandFloatingView;
        this.mContext = iOnDemandFloatingView.getContext();
        this.mProcessingManager = new ProcessingViewManager(iOnDemandFloatingView);
    }

    private void cancel() {
        Log.i("StoriesOnDemandDelegate", "onCanceled");
        this.mIsCanceled.set(true);
        StoryGenApi storyGenApi = this.mOnDemandStory;
        if (storyGenApi != null) {
            storyGenApi.cancel();
            this.mOnDemandStory = null;
        }
        searchToolbarHandleInternalEvent(SearchToolbarEvent.obtain(106, Boolean.FALSE));
    }

    private Bundle createGenBundle(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("prompt", str);
        bundle.putBoolean("forceExec", z);
        bundle.putBoolean("usePDC", SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability());
        return bundle;
    }

    public static AnalyticsDetail getAnalyticsErrorDetail(OnDemandResultData onDemandResultData) {
        if (!OnDemandResultData.valid(onDemandResultData)) {
            return AnalyticsDetail.EVENT_DETAIL_ONDEMAND_STORY_CREATE_FAIL;
        }
        if (onDemandResultData.isTimedOut()) {
            return AnalyticsDetail.EVENT_DETAIL_ONDEMAND_STORY_CREATE_FAIL_REASON_TIMEOUT;
        }
        if (onDemandResultData.existSimilarStories()) {
            return AnalyticsDetail.EVENT_DETAIL_ONDEMAND_STORY_CREATE_FAIL_REASON_EXIST_SIMILAR_STORY;
        }
        if (onDemandResultData.isFail()) {
            return AnalyticsDetail.EVENT_DETAIL_ONDEMAND_STORY_CREATE_FAIL;
        }
        return null;
    }

    private Bitmap getBitmap(MediaItem mediaItem) {
        Bitmap bitmap;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.LARGE_KIND;
        Bitmap loadThumbnailSync = instance.loadThumbnailSync(mediaItem, thumbKind);
        if (loadThumbnailSync.getHeight() * loadThumbnailSync.getWidth() < thumbKind.volume()) {
            if (mediaItem.isImage() || mediaItem.isCloudOnly()) {
                bitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), 0, 992, mediaItem.isQuramDecodable());
            } else if (mediaItem.isVideo()) {
                bitmap = getVideoThumbnail(mediaItem, mediaItem.getVideoThumbStartTime());
                if (bitmap != null) {
                    bitmap = BitmapUtils.resize(bitmap, Math.min(992, Math.max(bitmap.getWidth(), bitmap.getHeight())));
                }
            } else {
                bitmap = null;
            }
            if (bitmap != null) {
                return bitmap;
            }
        }
        return loadThumbnailSync;
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_ONDEMAND_STORY_VIEW.toString();
    }

    private Bitmap getVideoThumbnail(MediaItem mediaItem, long j2) {
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (MediaItemStory.isLiveEffectItem(mediaItem)) {
                mediaMetadataRetriever.setDataSource(this.mView.getContext(), ContentUri.getUri(mediaItem));
            } else {
                mediaMetadataRetriever.setDataSource(mediaItem.getPath());
            }
            Bitmap videoFrameAtTime = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, 2);
            mediaMetadataRetriever.close();
            return videoFrameAtTime;
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to get video thumbnail="), "StoriesOnDemandDelegate");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void handleConfirmRelation(OnDemandResultData onDemandResultData) {
        Log.i("StoriesOnDemandDelegate", "handleConfirmRelation", Logger.getEncodedString(onDemandResultData.getRelationData()));
        ProcessingViewManager processingViewManager = this.mProcessingManager;
        Objects.requireNonNull(processingViewManager);
        ThreadUtil.postOnUiThread(new m(processingViewManager, 1));
        if (new MpHelper().getPeopleNoRelationshipCount() == 0) {
            showFailView((String) null);
            Log.d("StoriesOnDemandDelegate", "no people data");
            return;
        }
        this.mView.getBlackboard().publish("data://user/storyGenQuery", onDemandResultData.getQuery());
        String validRelationshipData = RelationshipKeySet.getValidRelationshipData(onDemandResultData.getRelationData());
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(validRelationshipData)) {
            arrayList.addAll(RelationshipKeySet.parseJsonRelationship(validRelationshipData));
        }
        if (arrayList.isEmpty()) {
            Log.w("StoriesOnDemandDelegate", "loaded relation data is empty");
            showFailView((String) null);
        } else if (this.mContext != null) {
            c cVar = new c(10, this);
            PersonalDataCore.getInstance(this.mView.getBlackboard()).loadCandidatePeopleBy(this.mContext, arrayList, new b(15, this, cVar), cVar);
        }
    }

    private void handleFail(OnDemandResultData onDemandResultData) {
        Log.i("StoriesOnDemandDelegate", "handleFail", Integer.valueOf(onDemandResultData.getResultType()), Boolean.valueOf(isRunning()));
        searchToolbarHandleInternalEvent(SearchToolbarEvent.obtain(106, Boolean.FALSE));
        ThreadUtil.postOnUiThread(new p(this, onDemandResultData, 0));
    }

    private void handleSimilarGroup(OnDemandResultData onDemandResultData) {
        List<Long> similarGroups = onDemandResultData.getSimilarGroups();
        if (similarGroups == null || similarGroups.isEmpty()) {
            Log.e("StoriesOnDemandDelegate", "handleSimilarGroup skip. empty group");
            return;
        }
        Log.i("StoriesOnDemandDelegate", "handleSimilarGroup", Integer.valueOf(similarGroups.size()));
        loadAndLaunchStory(similarGroups.get(0).longValue(), false);
        Utils.showToast(this.mView.getContext(), onDemandResultData.getResultMessage(), 0);
    }

    private void handleSuccess(OnDemandResultData onDemandResultData) {
        Log.i("StoriesOnDemandDelegate", "handleSuccess");
        loadAndLaunchStory(onDemandResultData.getStoryId(), true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$continueOnDemand$0(String str) {
        startOnDemand(str, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$handleConfirmRelation$3() {
        boolean z;
        if (!this.mView.isActive() || this.mIsCanceled.get()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleFail$6(OnDemandResultData onDemandResultData) {
        showFailView(onDemandResultData.getResultMessage());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleFail$7(OnDemandResultData onDemandResultData) {
        this.mProcessingManager.onFail(new p(this, onDemandResultData, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAndLaunchStory$8(ArrayList arrayList, boolean z) {
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1138, new Object[]{arrayList, Boolean.valueOf(z)}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAndLaunchStory$9(MediaItem mediaItem, Bitmap bitmap, ArrayList arrayList, boolean z) {
        this.mProcessingManager.onSuccess(mediaItem, bitmap, new g((Object) this, (Object) arrayList, z, 7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadedCandidatePeopleBy$5(Supplier supplier, Map map) {
        if (!((Boolean) supplier.get()).booleanValue()) {
            searchToolbarHandleInternalEvent(SearchToolbarEvent.obtain(102, map));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showFailView$10(String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.mContext.getString(R.string.fail_generate_story_result_try_again);
        }
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1146, 0, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startOnDemand$2(String str, boolean z, String str2) {
        StoryGenApi storyGenApi = this.mOnDemandStory;
        if (storyGenApi != null) {
            storyGenApi.generate(createGenBundle(str, z), new b(14, this, str2));
        }
    }

    private void loadAndLaunchStory(long j2, boolean z) {
        MediaItem mediaItem;
        TimeTickLog timeTickLog = new TimeTickLog();
        ArrayList<MediaItem> loadItems = loadItems(String.valueOf(j2));
        timeTickLog.tick();
        if (!loadItems.isEmpty()) {
            boolean hasLiveEffectData = MediaItemStory.hasLiveEffectData(loadItems.get(0));
            MediaItem mediaItem2 = loadItems.get(0);
            if (hasLiveEffectData) {
                mediaItem = EffectItemBuilder.buildLiveEffectItem(mediaItem2);
            } else {
                mediaItem = mediaItem2;
            }
            MediaItem mediaItem3 = mediaItem;
            Bitmap bitmap = getBitmap(mediaItem3);
            timeTickLog.tick();
            Log.d("StoriesOnDemandDelegate", "loadAndLaunchStory" + Logger.vt(Long.valueOf(j2), Integer.valueOf(loadItems.size()), bitmap, Boolean.valueOf(isRunning()), timeTickLog));
            ThreadUtil.postOnUiThread(new C4.c(this, mediaItem3, bitmap, (ArrayList) loadItems, z));
            return;
        }
        Log.e((CharSequence) "StoriesOnDemandDelegate", "launchStory failed. null story", Long.valueOf(j2), Boolean.valueOf(this.mProcessingManager.isRunning()));
        if (this.mProcessingManager.isRunning()) {
            ProcessingViewManager processingViewManager = this.mProcessingManager;
            Objects.requireNonNull(processingViewManager);
            ThreadUtil.postOnUiThread(new m(processingViewManager, 1));
        }
    }

    private ArrayList<MediaItem> loadItems(String str) {
        Cursor query;
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        try {
            query = DbCompat.query(new QueryParams(DbKey.STORIES).setStoryIds(str));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadItems failed. e="), "StoriesOnDemandDelegate");
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loggingOnDemandResult(OnDemandResultData onDemandResultData) {
        String analyticsSuccessDetail = OnDemandResultData.getAnalyticsSuccessDetail(onDemandResultData);
        if (analyticsSuccessDetail != null) {
            this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_CREATE_SUCCESS, analyticsSuccessDetail);
        }
        AnalyticsDetail analyticsErrorDetail = getAnalyticsErrorDetail(onDemandResultData);
        if (analyticsErrorDetail != null) {
            this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_CREATE_ERROR, analyticsErrorDetail.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onLoadedCandidatePeopleBy */
    public void lambda$handleConfirmRelation$4(Map<String, List<Long>> map, Supplier<Boolean> supplier) {
        ThreadUtil.runOnUiThread(new R6.c(this, supplier, map, 26));
    }

    /* access modifiers changed from: private */
    /* renamed from: onOnDemandResult */
    public void lambda$startOnDemand$1(String str, OnDemandResultData onDemandResultData) {
        if (this.mIsCanceled.get()) {
            Log.i("StoriesOnDemandDelegate", "canceled");
            return;
        }
        ThreadUtil.assertBgThread("generate callback on worker thread");
        Log.i("StoriesOnDemandDelegate", "generate completed" + Logger.vt(onDemandResultData, str, Long.valueOf(this.mExecuteTime)));
        if (!OnDemandResultData.valid(onDemandResultData)) {
            if (!this.mView.isDestroyed()) {
                showFailView((String) null);
                ProcessingViewManager processingViewManager = this.mProcessingManager;
                Objects.requireNonNull(processingViewManager);
                ThreadUtil.postOnUiThread(new m(processingViewManager, 1));
            }
        } else if (onDemandResultData.needConfirmRelation()) {
            handleConfirmRelation(onDemandResultData);
        } else if (onDemandResultData.isFail()) {
            handleFail(onDemandResultData);
        } else if (onDemandResultData.existSimilarStories()) {
            handleSimilarGroup(onDemandResultData);
        } else {
            handleSuccess(onDemandResultData);
        }
        loggingOnDemandResult(onDemandResultData);
    }

    private void searchToolbarHandleInternalEvent(SearchToolbarEvent searchToolbarEvent) {
        SearchToolbarDelegate searchToolbarDelegate = this.mSearchToolbarDelegate;
        if (searchToolbarDelegate != null) {
            searchToolbarDelegate.handleInternalEvent(searchToolbarEvent);
        }
    }

    private void showFailView(String str) {
        ThreadUtil.runOnUiThread(new o(this, str, 1));
    }

    public void continueOnDemand(String str) {
        ThreadUtil.postOnUiThreadDelayed(new o(this, str, 0), 300);
    }

    public boolean isRunning() {
        return this.mProcessingManager.isRunning();
    }

    public boolean onBackPressed() {
        Object obj;
        this.mBackPressedTime = System.currentTimeMillis();
        StoryGenApi storyGenApi = this.mOnDemandStory;
        if (storyGenApi != null) {
            obj = Integer.valueOf(storyGenApi.hashCode());
        } else {
            obj = "";
        }
        Log.d("StoriesOnDemandDelegate", "onBackPressed", obj, Boolean.valueOf(isRunning()));
        cancel();
        if (!isRunning()) {
            return false;
        }
        this.mProcessingManager.hide();
        return true;
    }

    public void onConfigurationChanged() {
        this.mProcessingManager.onConfigurationChanged();
        searchToolbarHandleInternalEvent(SearchToolbarEvent.obtain(111, Boolean.TRUE));
    }

    public void onDestroy() {
        Log.i("StoriesOnDemandDelegate", "onDestroy");
        cancel();
        this.mProcessingManager.release();
        this.mView.getBlackboard().erase("data://user/storyGenQuery");
    }

    public void onDestroyView() {
        Log.i("StoriesOnDemandDelegate", "onDestroyView");
        cancel();
        if (isRunning()) {
            this.mProcessingManager.hide();
        }
    }

    public void onPause() {
        this.mProcessingManager.onPause();
    }

    public void onResume() {
        this.mProcessingManager.onResume();
        if (!this.mView.getBlackboard().isEmpty("data://user/storyGenQuery")) {
            this.mView.getBlackboard().erase("data://user/storyGenQuery");
            searchToolbarHandleInternalEvent(SearchToolbarEvent.obtain(107, (String) this.mView.getBlackboard().pop("data://user/storyGenQuery", "")));
        }
    }

    public void setSearchToolbarDelegate(SearchToolbarDelegate searchToolbarDelegate) {
        this.mSearchToolbarDelegate = searchToolbarDelegate;
    }

    public void startOnDemand(String str) {
        startOnDemand(str, false);
    }

    private void startOnDemand(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Log.e("StoriesOnDemandDelegate", "generate skip. empty query");
        } else if (isRunning()) {
            Log.e("StoriesOnDemandDelegate", "generate skip. running generate");
        } else if (System.currentTimeMillis() - this.mBackPressedTime < 500) {
            Log.e("StoriesOnDemandDelegate", "generate skip. back pressed just before");
        } else {
            this.mIsCanceled.set(false);
            this.mExecuteTime = System.currentTimeMillis();
            StoryGenApi create = StoryGenApi.create();
            this.mOnDemandStory = create;
            String valueOf = String.valueOf(create.hashCode());
            Log.i("StoriesOnDemandDelegate", "generate start", valueOf, Logger.getEncodedString(str));
            this.mProcessingManager.show(valueOf, str);
            SimpleThreadPool.getInstance().execute(new F8.a((Object) this, (Object) str, z, (Object) valueOf, 6));
        }
    }
}
