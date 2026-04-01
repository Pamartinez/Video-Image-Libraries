package com.samsung.android.gallery.widget.listview.noitem;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoItemVIShared extends NoItemVI {
    private View mCustomContainer;

    public NoItemVIShared(View view) {
        super(view);
    }

    private void initCustomContainerView(boolean z) {
        View findViewById = this.mRootView.findViewById(R$id.custom_container);
        this.mCustomContainer = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(0);
            if (!z) {
                this.mCustomContainer.setTranslationY((float) getPixelOffset(R$dimen.noitem_initial_translation_y));
                this.mCustomContainer.setAlpha(0.0f);
            }
        }
    }

    private void setCustomContainerBringToFront() {
        View view = this.mCustomContainer;
        if (view != null) {
            view.bringToFront();
            this.mCustomContainer.setVisibility(0);
        }
    }

    private void startCustomContainerAnimation() {
        boolean z;
        long j2;
        if (this.mCustomContainer != null) {
            TextView textView = this.mDescription;
            if (textView == null || textView.getVisibility() != 0) {
                z = false;
            } else {
                z = true;
            }
            setCustomContainerBringToFront();
            View view = this.mCustomContainer;
            if (z) {
                j2 = 100;
            } else {
                j2 = -1;
            }
            composeAnimation(view, j2);
        }
    }

    public void inflateChildView() {
        super.inflateChildView();
        initCustomContainerView(false);
    }

    public void showInitAnimation() {
        super.showInitAnimation();
        startCustomContainerAnimation();
    }
}
