package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import A4.B;
import B8.g;
import H3.i;
import I5.e;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameWrapper;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import k7.j;
import x6.f;
import x7.l;
import y5.a;
import z2.r;
import z5.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FaceClusterMergeDelegate {
    private final SubscriberListener mAssignedIdentityInfoListener;
    private final Blackboard mBlackboard;
    private final EventContext mEventContext;
    private final ArrayList<String> mExcludedIds = new ArrayList<>();
    private final ArrayList<String> mGroupIds = new ArrayList<>();
    private int mIndex = 0;
    private boolean mIsAllItemChecked;
    private MediaItem mLastMergedItem;
    private final ArrayList<MediaItem> mList = new ArrayList<>();
    private MergeListener mMergeListener;
    private String mSourceLocationKey;
    private String mTargetLocationKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MergeListener {
        void onCompletedMerge(MediaItem mediaItem, Bitmap bitmap, boolean z);

        void onLoadCompleted(boolean z, boolean z3);

        void onMerged();
    }

    public FaceClusterMergeDelegate(EventContext eventContext) {
        j jVar = new j(10, this);
        this.mAssignedIdentityInfoListener = jVar;
        this.mEventContext = eventContext;
        Blackboard blackboard = eventContext.getBlackboard();
        this.mBlackboard = blackboard;
        blackboard.subscribeOnUi("data://user/faceCluster/assignedIdentityInfo", jVar);
    }

    private void clear() {
        synchronized (this.mList) {
            this.mList.clear();
        }
        this.mIndex = 0;
        this.mIsAllItemChecked = false;
        Log.s("FaceClusterMergeDelegate", "clear()");
    }

    private String composeTargetKey(String str, MediaItem mediaItem) {
        String str2;
        UriBuilder appendArg = new UriBuilder(str).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, mediaItem.getCategory()).appendArg("sub", mediaItem.getSubCategory()).appendArg("title", mediaItem.getTitle()).appendArg("isNamed", CreatureData.hasName(mediaItem));
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            str2 = "recommended_id";
        } else {
            str2 = "persontag";
        }
        return appendArg.appendArg("term", str2).appendArg("prev_sub_category", ArgumentsUtil.getArgValue(this.mSourceLocationKey, "sub")).appendArg("people_from_visual_search", true).build();
    }

    private void executeMerge(ArrayList<Object> arrayList) {
        if (arrayList != null) {
            String[] strArr = (String[]) arrayList.get(0);
            executeMerge(strArr[0], strArr[1], true);
            String str = strArr[0];
            if (str != null) {
                SearchHistory.getInstance().deleteHistory(str.equals(ArgumentsUtil.getArgValue(this.mTargetLocationKey, "prev_sub_category")) ? this.mSourceLocationKey : this.mTargetLocationKey);
            }
        }
    }

    private MediaItem getMergeItem() {
        synchronized (this.mList) {
            try {
                if (this.mIndex < this.mList.size()) {
                    MediaItem mediaItem = this.mList.get(this.mIndex);
                    return mediaItem;
                }
                Log.se("FaceClusterMergeDelegate", "merge item is null");
                return null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private MediaItem getNext() {
        int i2 = this.mIndex + 1;
        this.mIndex = i2;
        if (i2 < this.mList.size()) {
            return this.mList.get(this.mIndex);
        }
        return null;
    }

    private void handleNextMergeItem(boolean z) {
        MediaItem next = getNext();
        if (next == null) {
            this.mIsAllItemChecked = true;
            executeLastMerge(z);
            return;
        }
        openNextMergePeople(next);
    }

    private void init(MediaItem mediaItem) {
        clear();
        this.mSourceLocationKey = this.mEventContext.getLocationKey();
        this.mLastMergedItem = MediaItemBuilder.cloneCreatureItem(mediaItem);
        loadRelatedFaceClusterData();
    }

    private boolean isEmpty() {
        return this.mGroupIds.isEmpty();
    }

    private boolean isMergeItemsLeft() {
        if (this.mIndex < this.mList.size() - 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeLastMerge$13(boolean z, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new g((Object) this, (Object) bitmap, z, 19));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMerge$8(boolean z, String str, Future future) {
        showMergeCompleteSnackBar();
        if (z) {
            this.mBlackboard.post("command://DismissDialog", str);
        }
        reloadLastMergedItem(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$handleMergeCancel$4(ThreadPool.JobContext jobContext) {
        MediaItem mergeItem = getMergeItem();
        if (mergeItem == null) {
            return null;
        }
        return PeopleDataManager.getGroupIds(mergeItem.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMergeCancel$5(Future future) {
        List<Long> list = (List) future.get();
        if (list != null) {
            for (Long longValue : list) {
                this.mExcludedIds.add(Long.toString(longValue.longValue()));
            }
            PeopleDataManager.updateFaceClusterExcludedSuggestion(this.mLastMergedItem.getSubCategory(), this.mExcludedIds);
            handleNextMergeItem(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadClusterItem$2(QueryParams queryParams) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Iterator<String> it = this.mGroupIds.iterator();
        while (it.hasNext()) {
            stringJoiner.add(it.next());
        }
        queryParams.mCreatureFaceGroupIds = stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$loadClusterItem$3(ThreadPool.JobContext jobContext) {
        Cursor query;
        MediaItem mediaItem;
        synchronized (this.mList) {
            try {
                query = DbCompat.query("mp://People", new a(4, this));
                if (query != null) {
                    if (query.moveToFirst()) {
                        do {
                            this.mList.add(MediaItemBuilder.cloneCreatureItem(MediaItemLoader.load(query)));
                        } while (query.moveToNext());
                    }
                }
                if (query != null) {
                    query.close();
                }
                if (this.mList.isEmpty()) {
                    mediaItem = null;
                } else {
                    mediaItem = this.mList.get(0);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaItem;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadRelatedFaceClusterData$1() {
        PeopleDataManager.getFaceClusterData(ArgumentsUtil.getArgValue(this.mSourceLocationKey, "sub"), this.mGroupIds, this.mExcludedIds);
        Log.s("FaceClusterMergeDelegate", "loadRelatedFaceClusterData : " + this.mGroupIds);
        this.mMergeListener.onLoadCompleted(true, isEmpty() ^ true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onAssignedIdentityInfo(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBackPressed$14(Bitmap bitmap) {
        lambda$executeLastMerge$12(bitmap, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBackPressed$15(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new f(7, this, bitmap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$reloadLastMergedItem$10(String str, ThreadPool.JobContext jobContext) {
        Cursor query = DbCompat.query("mp://People", new B(str, 28));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    this.mLastMergedItem = MediaItemBuilder.cloneCreatureItem(MediaItemLoader.load(query));
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reloadLastMergedItem$11(Future future) {
        if (isMergeItemsLeft()) {
            this.mMergeListener.onMerged();
        }
        handleNextMergeItem(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showMergePeopleChoiceDialog$6(EventContext eventContext, ArrayList arrayList) {
        executeMerge(arrayList);
    }

    private void loadRelatedFaceClusterData() {
        SimpleThreadPool.getInstance().execute(new l(9, this));
    }

    private void onAssignedIdentityInfo(Object obj) {
        executeMerge(this.mLastMergedItem.getSubCategory(), (String) obj, true);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCompletedMerge */
    public void lambda$executeLastMerge$12(Bitmap bitmap, boolean z) {
        this.mMergeListener.onCompletedMerge(this.mLastMergedItem, bitmap, z);
    }

    private void openNextMergePeople(MediaItem mediaItem) {
        this.mBlackboard.postEvent(EventMessage.obtain(1066, composeTargetKey(ArgumentsUtil.removeArgs(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard)), mediaItem)));
    }

    private void reloadLastMergedItem(String str) {
        ThreadPool.getInstance().submit(new e(10, this, str), new z5.a(this, 3));
    }

    private void setTargetLocationKey(String str) {
        this.mTargetLocationKey = str;
    }

    private void showMergeCompleteSnackBar() {
        View findViewById = BlackboardUtils.readActivity(this.mBlackboard).findViewById(R.id.content);
        int[] iArr = r.f2226I;
        r.j(findViewById, -1, -1, false, findViewById.getResources().getText(R.string.face_cluster_people_groups_merged)).l();
    }

    private void showMergePeopleChoiceDialog() {
        this.mBlackboard.publish("data:///MergeCreatureSource", this.mLastMergedItem);
        this.mBlackboard.publish("data:///MergeCreatureTarget", getMergeItem());
        this.mBlackboard.publish("data://user/faceCluster/UseSimpleMergeStyle", Boolean.TRUE);
        DataCollectionDelegate.getInitInstance(this.mEventContext).setRequestData(new UriBuilder("data://user/dialog/MergePeopleChoice").build()).setOnDataCompleteListener(new z5.a(this, 0)).start();
    }

    public void executeLastMerge(boolean z) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem = this.mLastMergedItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        String subCategory = mediaItem.getSubCategory();
        Objects.requireNonNull(subCategory);
        instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new D7.l(this, z));
    }

    public MediaItem getLastMergedItem() {
        return this.mLastMergedItem;
    }

    public void handleMergeCancel() {
        ThreadPool.getInstance().submit(new b(this, 0), new z5.a(this, 1));
    }

    public void handleMergeConfirm() {
        MediaItem mergeItem = getMergeItem();
        if (mergeItem != null) {
            boolean hasName = CreatureData.hasName(this.mLastMergedItem);
            boolean hasName2 = CreatureData.hasName(mergeItem);
            if (hasName || hasName2) {
                if (hasName && !hasName2) {
                    executeMerge(mergeItem.getSubCategory(), this.mLastMergedItem.getSubCategory(), false);
                    SearchHistory.getInstance().deleteHistory(this.mTargetLocationKey);
                } else if (!hasName) {
                    executeMerge(this.mLastMergedItem.getSubCategory(), mergeItem.getSubCategory(), false);
                    SearchHistory.getInstance().deleteHistory(this.mSourceLocationKey);
                } else {
                    showMergePeopleChoiceDialog();
                }
            } else if (mergeItem.isPet() || EditCreatureNameWrapper.hasRuntimeContactsPermission(this.mEventContext)) {
                this.mBlackboard.publish("data://user/faceCluster/isFromFaceMerge", Boolean.TRUE);
                EditCreatureNameWrapper.execute(this.mEventContext, this.mTargetLocationKey, mergeItem);
            } else {
                EditCreatureNameWrapper.requestRuntimePermission(this.mEventContext, 120);
            }
        }
    }

    public void loadClusterItem(FutureListener<MediaItem> futureListener) {
        Log.s("FaceClusterMergeDelegate", "loadClusterItem() start");
        ThreadPool.getInstance().submit(new b(this, 1), futureListener);
    }

    public void loadFaceCluster(String str, MediaItem mediaItem) {
        Log.s("FaceClusterMergeDelegate", "loadFaceCluster() start");
        if (this.mIsAllItemChecked) {
            Log.sw("FaceClusterMergeDelegate", "loadFaceCluster() : all item checked!");
        } else if (TextUtils.isEmpty(ArgumentsUtil.getArgValue(str, "prev_sub_category"))) {
            init(mediaItem);
        } else {
            setTargetLocationKey(str);
            this.mMergeListener.onLoadCompleted(false, !isEmpty());
        }
    }

    public boolean onBackPressed() {
        if (TextUtils.isEmpty(ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "prev_sub_category")) || ((Boolean) this.mBlackboard.pop("data:///NaviUpPressed", Boolean.FALSE)).booleanValue() || this.mLastMergedItem == null) {
            return false;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem = this.mLastMergedItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        String subCategory = mediaItem.getSubCategory();
        Objects.requireNonNull(subCategory);
        instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new z5.a(this, 2));
        return true;
    }

    public void onRequestPermissionResult() {
        this.mBlackboard.publish("data://user/faceCluster/isFromFaceMerge", Boolean.TRUE);
        EditCreatureNameWrapper.execute(this.mEventContext, this.mSourceLocationKey, this.mLastMergedItem);
    }

    public void openFaceClusterMergeData() {
        MediaItem mergeItem = getMergeItem();
        if (mergeItem != null) {
            this.mBlackboard.postEvent(EventMessage.obtain(1066, composeTargetKey(ArgumentsUtil.removeArgs(this.mSourceLocationKey), mergeItem)));
        }
    }

    public void relaunch() {
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        this.mBlackboard.post("command://MoveURL", this.mSourceLocationKey);
    }

    public void release() {
        this.mBlackboard.unsubscribe("data://user/faceCluster/assignedIdentityInfo", this.mAssignedIdentityInfoListener);
        clear();
    }

    public void reopenData(String str, String str2) {
        String appendArgs = ArgumentsUtil.appendArgs(this.mSourceLocationKey, "sub", str);
        if (!TextUtils.isEmpty(str2)) {
            appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(appendArgs, "title", str2), "isNamed", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(3001, 1, ArgumentsUtil.removeArgs(this.mSourceLocationKey).hashCode(), appendArgs));
        BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, true);
    }

    public void setMergeListener(MergeListener mergeListener) {
        this.mMergeListener = mergeListener;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FaceClusterMergeDelegate { size=");
        C0086a.A(sb2, this.mList, "|index=");
        sb2.append(this.mIndex);
        sb2.append("|allChecked=");
        return N2.j.h(sb2, this.mIsAllItemChecked, " }");
    }

    private void executeMerge(String str, String str2, boolean z) {
        ThreadPool.getInstance().submit(new e(str, str2), new i(this, z, str2));
    }
}
