package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandFloatingViewDelegate<V extends IMvpBaseView> {
    private final OnDemandRecommendView<V> mRecommendView;
    private ViewGroup mRootView;

    public OnDemandFloatingViewDelegate(V v) {
        this.mRecommendView = new OnDemandRecommendView<>(v);
    }

    private View inflateLayout(ViewGroup viewGroup) {
        return C0086a.d(viewGroup, R.layout.stories_category_floating_layout, viewGroup, false);
    }

    public View getRootView() {
        return this.mRootView;
    }

    public void onAttached(ViewGroup viewGroup) {
        if (this.mRootView == null) {
            ViewGroup viewGroup2 = (ViewGroup) inflateLayout(viewGroup);
            this.mRootView = viewGroup2;
            viewGroup.addView(viewGroup2, 0);
            this.mRecommendView.init(this.mRootView);
        }
    }

    public void onDestroy() {
        this.mRecommendView.onDestroy();
    }

    public void setHeight(int i2) {
        ViewUtils.setHeight(this.mRootView, i2);
    }

    public void setVisibility(boolean z, boolean z3) {
        if (z) {
            this.mRecommendView.show(z3);
        } else {
            this.mRecommendView.hide();
        }
    }
}
