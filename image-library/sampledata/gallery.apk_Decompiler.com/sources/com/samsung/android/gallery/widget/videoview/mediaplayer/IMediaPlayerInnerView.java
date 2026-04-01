package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewParent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMediaPlayerInnerView {
    Context getContext();

    ViewParent getParent();

    ViewParent getParentView();

    PlayState getPlayState();

    int getRenderingPosition();

    float getScaleXInner();

    float getScaleYInner();

    ViewParent getTouchInteractionViewParent();

    View getView();

    Bitmap getViewBitmap();

    int getVisibility();

    float getXInner();

    float getYInner();

    void invalidate();

    boolean isInPlayState();

    boolean isPaused();

    boolean isPlaying();

    void pauseVideo();

    void requestLayout();

    void resumeVideo();

    void setAlpha(float f);

    void setBackgroundColor(int i2);

    void setScaleXInner(float f);

    void setScaleYInner(float f);

    void setVisibility(int i2);

    void setWillNotDraw(boolean z);

    void setXInner(float f);

    void setYInner(float f);

    void setCoverView(View view) {
    }
}
