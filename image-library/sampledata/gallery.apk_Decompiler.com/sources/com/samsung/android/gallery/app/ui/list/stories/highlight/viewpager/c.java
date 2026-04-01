package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ StoryHighlightViewPagerV2.EndProgressHandler d;

    public /* synthetic */ c(StoryHighlightViewPagerV2.EndProgressHandler endProgressHandler) {
        this.d = endProgressHandler;
    }

    public final void run() {
        this.d.cancel();
    }
}
