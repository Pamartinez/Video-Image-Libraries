package com.samsung.android.gallery.module.search.autocomplete;

import N2.j;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import i.C0212a;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpAutoCompleteDataLoader extends AutoCompleteDataLoader {
    private Blackboard mBlackboard;
    private final MediaData.SimpleDataChangeListener mDataChanged = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            if (!MpAutoCompleteDataLoader.this.mIsCancelled) {
                MpAutoCompleteDataLoader.this.onLoadDone();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsCancelled;
    private MediaData mMediaDataFilter;
    private BiConsumer<ArrayList<AutoCompleteItem>, String> mOnLoadDoneCallback;

    /* access modifiers changed from: private */
    public void onLoadDone() {
        if (this.mOnLoadDoneCallback == null) {
            Log.s(this.TAG, "onLoadDone is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        String p6 = C0212a.p(new StringBuilder(), this.TAG, ".loadInBackground");
        try {
            this.mMediaDataFilter.acquireReadLock(p6);
            int count = this.mMediaDataFilter.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                MediaItem read = this.mMediaDataFilter.read(i2);
                if (read != null) {
                    arrayList.add(new AutoCompleteItem(read));
                }
            }
        } catch (Exception unused) {
            this.mMediaDataFilter.releaseReadLock(p6);
        }
        if (!Thread.currentThread().isInterrupted()) {
            this.mOnLoadDoneCallback.accept(arrayList, this.mLastRequestedKeyword);
        }
        String str = this.TAG;
        Log.s(str, "AutoComplete result" + Logger.vt(j.g(new StringBuilder("s:"), arrayList), Long.valueOf(currentTimeMillis)));
    }

    public void cancelLastRequest() {
        this.mIsCancelled = true;
    }

    public void initMediaData(Blackboard blackboard) {
        this.mBlackboard = blackboard;
        MediaData open = MediaDataFactory.getInstance(blackboard).open("location://search/AutoComplete");
        this.mMediaDataFilter = open;
        open.register(this.mDataChanged);
    }

    public void load(String str, String str2, boolean z, BiConsumer<ArrayList<AutoCompleteItem>, String> biConsumer) {
        if (!Thread.currentThread().isInterrupted()) {
            this.mIsCancelled = false;
            this.mLastRequestedKeyword = str2;
            String str3 = this.TAG;
            Log.s(str3, "load requested [" + Logger.getEncodedString(this.mLastRequestedKeyword) + "]");
            this.mBlackboard.post("command://event/KeywordChanged", this.mLastRequestedKeyword);
            this.mOnLoadDoneCallback = biConsumer;
        }
    }

    public void release() {
        MediaData mediaData = this.mMediaDataFilter;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChanged);
            this.mMediaDataFilter.close();
            this.mMediaDataFilter = null;
        }
        this.mOnLoadDoneCallback = null;
    }
}
