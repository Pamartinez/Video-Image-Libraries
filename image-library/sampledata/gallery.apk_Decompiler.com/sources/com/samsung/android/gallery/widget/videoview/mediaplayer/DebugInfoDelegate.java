package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.debug.DebugDecodingInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DebugInfoDelegate {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final DebugDecodingInfo mDebugDecodingInfo;
    private final IDelegateListener mDelegateListener;
    private int mOrientation;
    private final IMediaPlayerInnerView mView;

    public DebugInfoDelegate(IMediaPlayerInnerView iMediaPlayerInnerView, IDelegateListener iDelegateListener) {
        this.mView = iMediaPlayerInnerView;
        this.mDelegateListener = iDelegateListener;
        DebugDecodingInfo debugDecodingInfo = new DebugDecodingInfo(iMediaPlayerInnerView.getContext());
        this.mDebugDecodingInfo = debugDecodingInfo;
        debugDecodingInfo.createPaints();
    }

    private void drawDebugInfo(Canvas canvas) {
        if (this.mDebugDecodingInfo != null) {
            View view = this.mView.getView();
            this.mDebugDecodingInfo.createPaints();
            this.mDebugDecodingInfo.init();
            Canvas canvas2 = canvas;
            this.mDebugDecodingInfo.drawDebugText(canvas2, view.getScaleX(), this.mDelegateListener.getScaleDelegate().getScaleCoordinateInfo().startScale, 5.0f, new PointF(view.getX(), view.getY()), new PointF(((float) view.getWidth()) / 2.0f, ((float) view.getHeight()) / 2.0f), this.mOrientation, "V");
            DebugDecodingInfo debugDecodingInfo = this.mDebugDecodingInfo;
            debugDecodingInfo.drawDebug(canvas2, " DataPos=" + this.TAG.getTag());
            Rect rect = new Rect();
            view.getDrawingRect(rect);
            this.mDebugDecodingInfo.drawDebugLine(canvas2, rect);
        }
    }

    public void onDrawForeground(Canvas canvas) {
        drawDebugInfo(canvas);
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void setVideoOrientation(int i2) {
        this.mOrientation = i2;
    }
}
