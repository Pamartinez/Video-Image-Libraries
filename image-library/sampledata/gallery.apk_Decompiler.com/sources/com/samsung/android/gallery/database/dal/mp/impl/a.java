package com.samsung.android.gallery.database.dal.mp.impl;

import com.samsung.android.gallery.database.dal.mp.impl.StoryHelperBaseImpl;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ StoryHelperBaseImpl.CmhNotifier d;

    public /* synthetic */ a(StoryHelperBaseImpl.CmhNotifier cmhNotifier) {
        this.d = cmhNotifier;
    }

    public final void accept(Object obj) {
        this.d.lambda$flushJobs$1((StoryHelperBaseImpl.CmhNotifier.Job) obj);
    }
}
