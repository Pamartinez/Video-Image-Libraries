package com.samsung.android.gallery.app.ui.list.stories.highlight;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ StoryToTimeline d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ ViewPagerHolder g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2556h;

    public /* synthetic */ c(StoryToTimeline storyToTimeline, Bitmap bitmap, Bitmap bitmap2, ViewPagerHolder viewPagerHolder, MediaItem mediaItem) {
        this.d = storyToTimeline;
        this.e = bitmap;
        this.f = bitmap2;
        this.g = viewPagerHolder;
        this.f2556h = mediaItem;
    }

    public final void run() {
        this.d.lambda$handleJump$0(this.e, this.f, this.g, this.f2556h);
    }
}
