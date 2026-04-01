package com.samsung.android.gallery.app.ui.list.stories.highlight.filter;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.effectfilter.ImageFilterApplier;
import com.samsung.android.gallery.support.renderscript.RenderScriptCompat;
import java.util.HashMap;
import java.util.function.BiConsumer;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryFilterApplier extends ImageFilterApplier {
    private final IStoryHighlightView mView;

    public StoryFilterApplier(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HashMap lambda$createLutMap$0(String str) {
        return new HashMap();
    }

    public void apply(Bitmap bitmap, boolean z, BiConsumer<Bitmap, Filter> biConsumer) {
        if (!this.mView.isDestroyed()) {
            if (this.mView.getOptions().supportFilter()) {
                super.apply(bitmap, z, biConsumer);
            } else {
                biConsumer.accept(bitmap, this.mFilter);
            }
        }
    }

    public HashMap<Filter, RenderScriptCompat.LutInfo> createLutMap() {
        return (HashMap) this.mView.getBlackboard().computeIfAbsent("data://user/filter_lut_map", new e(16));
    }

    public void destroy() {
        this.mView.getBlackboard().pop("data://user/filter_lut_map");
    }

    public void setImageFilter(Filter filter) {
        if (this.mView.getOptions().supportFilter()) {
            super.setImageFilter(filter);
        }
    }

    public void apply(Bitmap bitmap, Filter filter, int i2, boolean z, BiConsumer<Bitmap, Filter> biConsumer) {
        if (!this.mView.isDestroyed()) {
            if (this.mView.getOptions().supportFilter()) {
                super.apply(bitmap, filter, i2, z, biConsumer);
            } else {
                biConsumer.accept(bitmap, filter);
            }
        }
    }
}
