package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.samsung.android.gallery.widget.utils.WindowUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChunkingBackgroundView extends FrameLayout {
    private int mAdaptedGravity = -1;
    private View mLeftOverlay;
    private View mRightOverlay;

    public ChunkingBackgroundView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        setTag("ChunkingBackgroundView");
        setFocusable(false);
        setClickable(false);
        this.mLeftOverlay = new View(context);
        this.mRightOverlay = new View(context);
        addView(this.mLeftOverlay);
        addView(this.mRightOverlay);
        this.mLeftOverlay.setVisibility(8);
        this.mRightOverlay.setVisibility(8);
    }

    private void updateLayout(View view, Rect rect) {
        FrameLayout.LayoutParams layoutParams;
        view.setVisibility(0);
        if (rect.left > 0 && this.mAdaptedGravity != 3) {
            layoutParams = new FrameLayout.LayoutParams(rect.left, -1, 3);
        } else if (rect.right <= 0 || this.mAdaptedGravity == 5) {
            layoutParams = new FrameLayout.LayoutParams(0, 0);
        } else {
            layoutParams = new FrameLayout.LayoutParams(rect.right, -1, 5);
        }
        this.mAdaptedGravity = layoutParams.gravity;
        view.setLayoutParams(layoutParams);
        view.requestLayout();
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.mAdaptedGravity = -1;
        updateLayout(this.mLeftOverlay, WindowUtils.getSystemInsets(windowInsets));
        updateLayout(this.mRightOverlay, WindowUtils.getSystemInsets(windowInsets));
        return windowInsets;
    }

    public void setBackgroundColor(int i2) {
        this.mLeftOverlay.setBackgroundColor(i2);
        this.mRightOverlay.setBackgroundColor(i2);
    }
}
