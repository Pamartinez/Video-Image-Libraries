package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StoryHighlightViewPagerAdapter.BlurBinder f2560a;
    public final /* synthetic */ MediaItem b;

    public /* synthetic */ a(StoryHighlightViewPagerAdapter.BlurBinder blurBinder, MediaItem mediaItem) {
        this.f2560a = blurBinder;
        this.b = mediaItem;
    }

    public final void accept(Object obj, Object obj2) {
        this.f2560a.bindBlurBitmap(this.b, (Bitmap) obj);
    }
}
