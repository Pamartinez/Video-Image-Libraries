package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchCreatureSelectForRelation extends MediaDataSearchCategory {
    private final ArrayList<String> mChildLocationKey;
    private final MediaData.OnDataChangeListener mChildMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            if (MediaDataSearchCreatureSelectForRelation.this.getDataVersion() > 0) {
                MediaDataSearchCreatureSelectForRelation.this.notifyChanged();
            } else {
                Log.e(MediaDataSearchCreatureSelectForRelation.this.TAG, "ChildDataChanged skip. data not ready");
            }
        }

        public void onDataRangeInserted(int i2, int i7) {
            onDataChanged();
        }
    };
    private final ConcurrentHashMap<String, MediaData> mChildMediaDataMap = new ConcurrentHashMap<>();

    public MediaDataSearchCreatureSelectForRelation(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str, z);
        ArrayList<String> arrayList = new ArrayList<>();
        this.mChildLocationKey = arrayList;
        arrayList.add(new UriBuilder("location://search/fileList/SuggestedCreature").appendArg("people_ids", ArgumentsUtil.getArgValue(str, "people_ids")).build());
        arrayList.forEach(new k0(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(String str) {
        BlackboardUtils.publishDataRequest(this.mBlackboard, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaData lambda$onCreate$1(String str, String str2) {
        return MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(String str) {
        this.mChildMediaDataMap.computeIfAbsent(ArgumentsUtil.removeArgs(str), new m0(1, this, str)).register(this.mChildMediaDataChangeListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDestroy$3(String str, MediaData mediaData) {
        mediaData.unregister(this.mChildMediaDataChangeListener);
        mediaData.close();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reopenChildMediaData$5(String str, MediaData mediaData) {
        mediaData.unregister(this.mChildMediaDataChangeListener);
        mediaData.close();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaData lambda$reopenChildMediaData$6(String str, String str2) {
        return MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reopenChildMediaData$7(String str) {
        BlackboardUtils.publishDataRequest(this.mBlackboard, str);
        this.mChildMediaDataMap.computeIfAbsent(ArgumentsUtil.removeArgs(str), new m0(0, this, str)).register(this.mChildMediaDataChangeListener);
    }

    private void reopenChildMediaData(String str) {
        this.mChildLocationKey.clear();
        this.mChildMediaDataMap.forEach(new l0(this, 1));
        this.mChildMediaDataMap.clear();
        this.mChildLocationKey.add(new UriBuilder("location://search/fileList/SuggestedCreature").appendArg("people_ids", ArgumentsUtil.getArgValue(str, "people_ids")).build());
        this.mChildLocationKey.forEach(new k0(this, 1));
    }

    public MediaData getChildMediaData(String str) {
        return this.mChildMediaDataMap.get(str);
    }

    public SearchSorter getSearchSorter() {
        return new SearchCreatureSorter(this.mLocationKey);
    }

    public void notifyChanged(boolean z) {
        super.notifyChanged(z);
    }

    public void onCreate() {
        super.onCreate();
        this.mChildLocationKey.forEach(new k0(this, 0));
    }

    public void onDestroy() {
        this.mChildMediaDataMap.forEach(new l0(this, 0));
        this.mChildMediaDataMap.clear();
        super.onDestroy();
    }

    public void reopen(String str) {
        reopenChildMediaData(str);
        super.reopen(str);
    }

    public void swap(Cursor[] cursorArr) {
        super.swap(cursorArr);
        this.mChildMediaDataMap.forEach(new Z(2));
    }
}
