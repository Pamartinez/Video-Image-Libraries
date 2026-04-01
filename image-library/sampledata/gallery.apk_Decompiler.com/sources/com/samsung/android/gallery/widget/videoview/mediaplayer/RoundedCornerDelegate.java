package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.ViewTag;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RoundedCornerDelegate {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private RelativeLayout mCoverView;
    /* access modifiers changed from: private */
    public final int mRadios;
    private final IMediaPlayerInnerView mView;
    private boolean mViewReplaced = false;

    public RoundedCornerDelegate(IMediaPlayerInnerView iMediaPlayerInnerView, int i2) {
        this.mView = iMediaPlayerInnerView;
        this.mRadios = i2;
    }

    private void applyView(ViewGroup viewGroup, View view) {
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        viewGroup2.addView(viewGroup, viewGroup2.indexOfChild(view) + 1);
        viewGroup2.removeView(view);
        viewGroup.addView(view);
        viewGroup.setTag(ViewTag.MEDIA_COVER_ROUND_VIEW);
    }

    private RelativeLayout createCoverView(View view) {
        RelativeLayout relativeLayout = new RelativeLayout(view.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        relativeLayout.setGravity(17);
        layoutParams.addRule(13);
        relativeLayout.setLayoutParams(layoutParams);
        setCoverViewOutline(relativeLayout);
        this.mView.setCoverView(relativeLayout);
        return relativeLayout;
    }

    private void setCoverViewOutline(RelativeLayout relativeLayout) {
        relativeLayout.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) RoundedCornerDelegate.this.mRadios);
            }
        });
        relativeLayout.setClipToOutline(true);
    }

    public ViewParent getParentView() {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            return relativeLayout.getParent();
        }
        return this.mView.getParent();
    }

    public float getScaleX() {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            return relativeLayout.getScaleX();
        }
        return this.mView.getScaleXInner();
    }

    public float getScaleY() {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            return relativeLayout.getScaleY();
        }
        return this.mView.getScaleYInner();
    }

    public float getX() {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            return relativeLayout.getX();
        }
        return this.mView.getXInner();
    }

    public float getY() {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            return relativeLayout.getY();
        }
        return this.mView.getYInner();
    }

    public void onAttachedToWindow() {
        if (!this.mViewReplaced) {
            this.mViewReplaced = true;
            RelativeLayout createCoverView = createCoverView(this.mView.getView());
            this.mCoverView = createCoverView;
            applyView(createCoverView, this.mView.getView());
        }
    }

    public void onConfigurationChanged() {
        setCoverViewSize(-1, -1);
    }

    public void setCoverViewSize(int i2, int i7) {
        ViewUtils.setViewSize(this.mCoverView, Integer.valueOf(i2), Integer.valueOf(i7));
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void setScaleX(float f) {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            relativeLayout.setScaleX(f);
        } else {
            this.mView.setScaleXInner(f);
        }
    }

    public void setScaleY(float f) {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            relativeLayout.setScaleY(f);
        } else {
            this.mView.setScaleYInner(f);
        }
    }

    public void setX(float f) {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            relativeLayout.setX(f);
        } else {
            this.mView.setXInner(f);
        }
    }

    public void setY(float f) {
        RelativeLayout relativeLayout = this.mCoverView;
        if (relativeLayout != null) {
            relativeLayout.setY(f);
        } else {
            this.mView.getYInner();
        }
    }
}
