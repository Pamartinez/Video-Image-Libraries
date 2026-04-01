package com.samsung.android.gallery.module.story.ondemand;

import H.b;
import O3.l;
import bc.C0584a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandSuggestionDataManager {
    private List<String> mData = Collections.synchronizedList(new ArrayList());
    final List<Runnable> mDataChangedListeners = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LazyHolder {
        static final OnDemandSuggestionDataManager sInstance = new OnDemandSuggestionDataManager();
    }

    public static OnDemandSuggestionDataManager getInstance() {
        return LazyHolder.sInstance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$0(List list, long j2) {
        this.mData = Collections.synchronizedList(list);
        notifyChanged();
        Log.i("OnDemandSuggestDataMgr", "update " + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$1() {
        ThreadUtil.runOnUiThread(new b(this, StoryGenApi.create().getQuerySuggestion(2), System.currentTimeMillis(), 6));
    }

    private void notifyChanged() {
        this.mDataChangedListeners.forEach(new l(0));
    }

    private boolean support() {
        return OnDemandStory.getInstance().isEnabled();
    }

    public List<String> getData() {
        return this.mData;
    }

    public void init() {
        if (support() && this.mData.isEmpty()) {
            update();
        }
    }

    public void registerDataChangedListener(Runnable runnable) {
        if (runnable != null) {
            if (!this.mDataChangedListeners.contains(runnable)) {
                this.mDataChangedListeners.add(runnable);
            }
            runnable.run();
        }
    }

    public void unregisterDataChangedListener(Runnable runnable) {
        this.mDataChangedListeners.remove(runnable);
    }

    public void update() {
        if (support()) {
            SimpleThreadPool.getInstance().execute(new C0584a(20, this));
        }
    }
}
