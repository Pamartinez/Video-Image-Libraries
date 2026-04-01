package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import com.samsung.android.gallery.app.controller.story.StoryReorderCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderListener;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import e5.C0451a;
import ic.l;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReorderCallback implements ReorderListener {
    private final AtomicBoolean mActive = new AtomicBoolean();
    private final Blackboard mBlackboard;
    private final IBaseListView mView;

    public ReorderCallback(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
        this.mBlackboard = iBaseListView.getBlackboard();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drop$1() {
        StoryUpdateNotifier.getInstance().notifyStory(AppResources.getAppContext(), true);
        this.mBlackboard.post("command:///RefreshWithoutDelay", (Object) null);
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
    }

    public void begin() {
        Log.d("ReorderCallback", "begin");
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, true);
        this.mActive.set(true);
    }

    public void dragStart() {
        Log.d("ReorderCallback", "dragStart");
        Optional.ofNullable(this.mBlackboard).ifPresent(new l(13));
    }

    public void drop(int i2, int i7, boolean z) {
        Log.d("ReorderCallback", "drop", Integer.valueOf(i2), Integer.valueOf(i7), Boolean.valueOf(z));
        new StoryReorderCmd().execute(this.mView.getEventContext(), new Object[0]);
        ThreadUtil.postOnBgThreadDelayed(new C0451a(29, this), 200);
        this.mActive.set(false);
    }

    public void end() {
        Log.d("ReorderCallback", "end");
        if (this.mActive.getAndSet(false)) {
            BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
        }
    }

    public void reorder(int i2, int i7) {
        Log.d("ReorderCallback", "reorder", Integer.valueOf(i2), Integer.valueOf(i7));
        MediaData mediaData = this.mView.getMediaData((String) null);
        if (mediaData != null) {
            mediaData.reorderData(i2, i7);
        } else {
            Log.e("ReorderCallback", "no data");
        }
    }
}
