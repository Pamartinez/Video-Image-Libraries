package com.samsung.android.gallery.widget.photoview;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.samsung.android.gallery.widget.debug.DebugDecodingInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DebugDelegate {
    private final DebugDecodingInfo mDebugDecodingInfo;
    private final Rect mDebugTileRect;
    boolean mDisabled = false;

    public DebugDelegate(PhotoView photoView) {
        DebugDecodingInfo debugDecodingInfo = new DebugDecodingInfo(photoView.getContext());
        this.mDebugDecodingInfo = debugDecodingInfo;
        debugDecodingInfo.createPaints();
        this.mDebugTileRect = new Rect();
    }

    private void drawDebugInfo(PhotoView photoView, Canvas canvas) {
        PhotoViewPositionControl photoViewPositionControl = photoView.mPosCtrl;
        this.mDebugDecodingInfo.init();
        this.mDebugDecodingInfo.drawDebugText(canvas, photoViewPositionControl.getScale(), photoView.minScale(), photoView.getMaxScale(), photoViewPositionControl.getPosition(), photoView.getCenter(), photoView.mImageProcessor.getOrientation(), "I");
        StringBuilder sb2 = new StringBuilder(32);
        sb2.append("Full");
        sb2.append(getTileInfo(1, photoView.mImageProcessor.getFullImageSampleSize(), photoView.mImageProcessor.getBitmap()));
        sb2.append(' ');
        if (photoView.mImageProcessor.getTileMap() != null) {
            int min = Math.min(photoView.mImageProcessor.getFullImageSampleSize(), photoView.calculateInSampleSize(photoViewPositionControl.getScale()));
            photoView.mImageProcessor.getTileMap().entrySet().stream().filter(new h(min, 1)).findFirst().ifPresent(new i(this, sb2, min));
        }
        sb2.append(" TAG=");
        sb2.append(photoView.TAG.getTag());
        this.mDebugDecodingInfo.drawDebug(canvas, sb2.toString());
        Rect rect = new Rect();
        photoView.getDrawingRect(rect);
        rect.left--;
        rect.top--;
        rect.right--;
        rect.bottom--;
        this.mDebugDecodingInfo.drawDebugLine(canvas, rect);
        this.mDebugDecodingInfo.drawDebugQuickCropText(canvas, photoView.getCropRectOnImage(), photoView.getCropRectOnView(), photoView.mImageProcessor.getWidth(false), photoView.mImageProcessor.getHeight(false), photoView.mImageProcessor.getOrientation());
        PhotoViewMotionControl photoViewMotionControl = photoView.mMotionControl;
        if (photoViewMotionControl != null) {
            ScaleAnimation scaleAnimation = photoViewMotionControl.getScaleAnimation();
            if (scaleAnimation != null) {
                this.mDebugDecodingInfo.drawAnimCenterCircle(canvas, photoViewPositionControl.sourceToViewCoord(photoView.getCenter()), photoViewPositionControl.sourceToViewCoord(scaleAnimation.getTargetSCenter()), photoViewPositionControl.sourceToViewCoord(scaleAnimation.getTargetSCenter()), (float) photoView.getWidth(), (float) photoView.getHeight());
            }
            if (photoViewMotionControl.getVCenterStart() != null) {
                this.mDebugDecodingInfo.drawVCenterCircle(canvas, photoViewMotionControl.getVCenterStart());
            }
        }
        this.mDebugDecodingInfo.resetDebugLinePaint();
    }

    private String getTileInfo(int i2, int i7, Bitmap bitmap) {
        String str;
        StringBuilder h5 = a.h(i2, i7, "[#", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (bitmap != null) {
            str = bitmap.getWidth() + "x" + bitmap.getHeight();
        } else {
            str = "0x0";
        }
        return C0212a.p(h5, str, "]");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$drawDebugInfo$0(int i2, Map.Entry entry) {
        if (((Integer) entry.getKey()).intValue() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$drawDebugInfo$1(Tile tile) {
        if (tile.bitmap != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.util.function.Function] */
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drawDebugInfo$3(StringBuilder sb2, int i2, Map.Entry entry) {
        sb2.append("Tile");
        sb2.append(getTileInfo(((List) entry.getValue()).size(), i2, (Bitmap) ((List) entry.getValue()).stream().filter(new j(0)).findFirst().map(new Object()).orElse((Object) null)));
    }

    public void disable(boolean z) {
        this.mDisabled = z;
    }

    public void draw(PhotoView photoView, Canvas canvas) {
        if (!this.mDisabled && this.mDebugDecodingInfo != null) {
            try {
                drawDebugInfo(photoView, canvas);
            } catch (NullPointerException unused) {
            }
        }
    }

    public void renderTileOnDraw(Canvas canvas, Tile tile) {
        if (!this.mDisabled) {
            if (!tile.loading && tile.bitmap != null) {
                this.mDebugTileRect.set(tile.vRect);
                this.mDebugTileRect.inset(50, 50);
                this.mDebugDecodingInfo.drawDebugLine(canvas, this.mDebugTileRect);
            } else if (tile.loading) {
                DebugDecodingInfo debugDecodingInfo = this.mDebugDecodingInfo;
                Rect rect = tile.vRect;
                debugDecodingInfo.drawLoading(canvas, (float) rect.left, (float) rect.top);
            }
            if (tile.visible) {
                this.mDebugDecodingInfo.drawISS(canvas, tile.sampleSize, tile.sRect, tile.vRect, tile.bitmap);
            }
        }
    }
}
