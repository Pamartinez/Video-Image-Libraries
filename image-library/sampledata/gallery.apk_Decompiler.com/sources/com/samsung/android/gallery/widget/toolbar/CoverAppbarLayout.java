package com.samsung.android.gallery.widget.toolbar;

import A.a;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CoverAppbarLayout extends GalleryAppBarLayout {
    private ViewGroup mCoverContainer;
    private Consumer<Boolean> mCoverVisibilityChangeListener;
    private int mCurrentCollapsedHeight;
    private int mCustomHeightResId;
    private boolean mExpanded;
    private boolean mKeepExpand;

    public CoverAppbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private boolean almostExpended() {
        if (Math.abs(getTotalScrollRange() - getVisibleHeight()) < 10) {
            return true;
        }
        return false;
    }

    private boolean isCustomHeight() {
        if (this.mCustomHeightResId > 0) {
            return true;
        }
        return false;
    }

    private void updateCollapsedHeight() {
        int height = getHeight() - getTotalScrollRange();
        if (height != this.mCurrentCollapsedHeight) {
            this.mCurrentCollapsedHeight = height;
            seslSetCollapsedHeight((float) height);
        }
    }

    public int getCustomHeight() {
        return getResources().getDimensionPixelSize(this.mCustomHeightResId);
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean almostExpended = almostExpended();
        super.onConfigurationChanged(configuration);
        updateInternalHeightAndOffset();
        setExpanded(almostExpended, false);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mCoverContainer = (ViewGroup) findViewById(R$id.cover_container);
    }

    public void onMultiWindowModeChanged(boolean z) {
        if (!this.mKeepExpand) {
            super.onMultiWindowModeChanged(z);
        }
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
        boolean z;
        Consumer<Boolean> consumer;
        super.onOffsetChanged(appBarLayout, i2);
        updateCollapsedHeight();
        if (getVisibleHeight() > 10) {
            z = true;
        } else {
            z = false;
        }
        if (this.mExpanded != z) {
            this.mExpanded = z;
            if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW && (consumer = this.mCoverVisibilityChangeListener) != null) {
                consumer.accept(Boolean.valueOf(z));
            }
        }
    }

    public void setAlphaOnCoverView(float f) {
        ViewGroup viewGroup = this.mCoverContainer;
        if (viewGroup != null) {
            viewGroup.setAlpha(f);
        }
    }

    public void setAttr(Context context, AttributeSet attributeSet) {
        super.setAttr(context, attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoverAppbarLayout);
            this.mKeepExpand = obtainStyledAttributes.getBoolean(R$styleable.CoverAppbarLayout_keepExpand, false);
            this.mCustomHeightResId = obtainStyledAttributes.getResourceId(R$styleable.CoverAppbarLayout_customHeight, 0);
            if (ResourceCompat.isLandscape(context)) {
                setExpanded(true);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setCoverView(View view) {
        ViewGroup viewGroup = this.mCoverContainer;
        if (viewGroup != null) {
            if (viewGroup.getChildCount() > 0) {
                ViewUtils.removeAllViews(this.mCoverContainer);
            }
            this.mCoverContainer.addView(view);
            this.mExpanded = !seslIsCollapsed();
        }
    }

    public void setOnCoverVisibilityChangeListener(Consumer<Boolean> consumer) {
        this.mCoverVisibilityChangeListener = consumer;
    }

    public void updateInternalHeightAndOffset() {
        if (isCustomHeight()) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) getLayoutParams();
            layoutParams.height = getCustomHeight();
            setLayoutParams(layoutParams);
            AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) layoutParams.getBehavior();
            if (behavior != null && behavior.a() < 0) {
                behavior.c(-layoutParams.height);
                a.w(new StringBuilder("force update topAndBottomOffset : "), layoutParams.height, this.TAG);
            }
        }
    }
}
