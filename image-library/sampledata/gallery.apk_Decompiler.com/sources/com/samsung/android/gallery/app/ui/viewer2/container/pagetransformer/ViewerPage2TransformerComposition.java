package com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerPage2TransformerComposition implements ViewPager2.PageTransformer {
    private final ArrayList<ViewPager2.PageTransformer> mPageTransformerList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        ViewerPage2TransformerComposition instance = new ViewerPage2TransformerComposition(0);

        public static Builder create() {
            return new Builder();
        }

        public Builder addTransformer(ViewPager2.PageTransformer pageTransformer) {
            this.instance.addTransformer(pageTransformer);
            return this;
        }

        public ViewPager2.PageTransformer build() {
            ViewerPage2TransformerComposition viewerPage2TransformerComposition = this.instance;
            this.instance = null;
            return viewerPage2TransformerComposition;
        }
    }

    public /* synthetic */ ViewerPage2TransformerComposition(int i2) {
        this();
    }

    /* access modifiers changed from: private */
    public void addTransformer(ViewPager2.PageTransformer pageTransformer) {
        this.mPageTransformerList.add(pageTransformer);
    }

    public void transformPage(View view, float f) {
        Iterator<ViewPager2.PageTransformer> it = this.mPageTransformerList.iterator();
        while (it.hasNext()) {
            it.next().transformPage(view, f);
        }
    }

    private ViewerPage2TransformerComposition() {
        this.mPageTransformerList = new ArrayList<>();
    }
}
