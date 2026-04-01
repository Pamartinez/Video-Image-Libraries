package com.samsung.android.gallery.widget.photoview;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.widget.utils.PinnedEdgeProperty;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PhotoViewPositionControl {
    static final boolean SDK_LESS_S = SdkConfig.lessThan(SdkConfig.GED.S);
    private Consumer<PointF> mOnPositionChangeListener;
    private OnScaleChangeListener mOnScaleChangeListener;
    private PointF mPosition;
    private float mScale;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnScaleChangeListener {
    }

    private int getPinnedEdgeWidth(Context context) {
        if (!SDK_LESS_S || !PinnedEdgeProperty.getInstance().isLeftPinnedEdge(context)) {
            return 0;
        }
        return PinnedEdgeProperty.getInstance().getWidth(context);
    }

    public Rect getCropRectOnImage(int i2, int i7, int i8, int i10) {
        int viewToSourceX = (int) viewToSourceX(0.0f);
        int viewToSourceX2 = (int) viewToSourceX((float) i2);
        int viewToSourceY = (int) viewToSourceY(0.0f);
        int viewToSourceY2 = (int) viewToSourceY((float) i7);
        if (viewToSourceX < 0) {
            viewToSourceX = 0;
        }
        if (viewToSourceX2 <= i8) {
            i8 = viewToSourceX2;
        }
        if (viewToSourceY < 0) {
            viewToSourceY = 0;
        }
        if (viewToSourceY2 <= i10) {
            i10 = viewToSourceY2;
        }
        return new Rect(viewToSourceX, viewToSourceY, i8, i10);
    }

    public Rect getCropRectOnView(float f, int i2, int i7, int i8, int i10) {
        int i11;
        int i12 = (int) (((float) i8) * f);
        int i13 = (int) (f * ((float) i10));
        int i14 = 0;
        if (i2 > i12) {
            int i15 = i2 / 2;
            int i16 = i12 / 2;
            i11 = i15 - i16;
            i2 = i15 + i16;
        } else {
            i11 = 0;
        }
        if (i7 > i13) {
            int i17 = i7 / 2;
            int i18 = i13 / 2;
            i14 = i17 - i18;
            i7 = i17 + i18;
        }
        return new Rect(i11, i14, i2, i7);
    }

    public RectF getDisplayRect(Context context, int i2, int i7) {
        if (getPosition() == null) {
            return null;
        }
        float x9 = (float) (((int) getX()) + getPinnedEdgeWidth(context));
        return new RectF(x9, getY(), (((float) i2) * getScale()) + x9, (((float) i7) * getScale()) + getY());
    }

    public PointF getPosition() {
        return this.mPosition;
    }

    public float getScale() {
        return this.mScale;
    }

    public float getX() {
        PointF pointF = this.mPosition;
        if (pointF != null) {
            return pointF.x;
        }
        return 0.0f;
    }

    public float getY() {
        PointF pointF = this.mPosition;
        if (pointF != null) {
            return pointF.y;
        }
        return 0.0f;
    }

    public void init() {
        this.mPosition = new PointF(0.0f, 0.0f);
    }

    public void offset(float f, float f5) {
        PointF pointF = this.mPosition;
        if (pointF != null) {
            pointF.offset(f, f5);
        }
    }

    public void reset() {
        this.mPosition = null;
        this.mScale = 0.0f;
    }

    public void setOnPositionChangeListener(Consumer<PointF> consumer) {
        this.mOnPositionChangeListener = consumer;
    }

    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        this.mOnScaleChangeListener = onScaleChangeListener;
    }

    public void setPosition(PointF pointF) {
        PointF pointF2 = this.mPosition;
        if (pointF2 != null) {
            pointF2.set(pointF);
            Consumer<PointF> consumer = this.mOnPositionChangeListener;
            if (consumer != null) {
                consumer.accept(this.mPosition);
            }
        }
    }

    public void setScale(float f) {
        if (this.mScale != f) {
            this.mScale = f;
            OnScaleChangeListener onScaleChangeListener = this.mOnScaleChangeListener;
            if (onScaleChangeListener != null) {
                ((PhotoView) ((n) onScaleChangeListener).d).onScaleChanged(f);
            }
        }
    }

    public PointF sourceToViewCoord(PointF pointF) {
        return sourceToViewCoord(pointF.x, pointF.y, new PointF());
    }

    public void sourceToViewRect(Rect rect, Rect rect2) {
        rect2.set(Math.round(sourceToViewX((float) rect.left)), Math.round(sourceToViewY((float) rect.top)), Math.round(sourceToViewX((float) rect.right)), Math.round(sourceToViewY((float) rect.bottom)));
    }

    public float sourceToViewX(float f) {
        if (getPosition() == null) {
            return Float.NaN;
        }
        return (f * getScale()) + getX();
    }

    public float sourceToViewY(float f) {
        if (getPosition() == null) {
            return Float.NaN;
        }
        return (f * getScale()) + getY();
    }

    public boolean tileVisible(Tile tile, int i2, int i7) {
        float viewToSourceX = viewToSourceX(0.0f);
        float viewToSourceX2 = viewToSourceX((float) i2);
        float viewToSourceY = viewToSourceY(0.0f);
        float viewToSourceY2 = viewToSourceY((float) i7);
        Rect rect = tile.sRect;
        if (viewToSourceX > ((float) rect.right) || ((float) rect.left) > viewToSourceX2 || viewToSourceY > ((float) rect.bottom) || ((float) rect.top) > viewToSourceY2) {
            return false;
        }
        return true;
    }

    public PointF viewToSourceCoord(PointF pointF) {
        return viewToSourceCoord(pointF.x, pointF.y, new PointF());
    }

    public float viewToSourceX(float f) {
        PointF pointF = this.mPosition;
        if (pointF == null) {
            return Float.NaN;
        }
        return (f - pointF.x) / getScale();
    }

    public float viewToSourceY(float f) {
        PointF pointF = this.mPosition;
        if (pointF == null) {
            return Float.NaN;
        }
        return (f - pointF.y) / getScale();
    }

    private PointF sourceToViewCoord(float f, float f5, PointF pointF) {
        if (getPosition() == null) {
            return null;
        }
        pointF.set(sourceToViewX(f), sourceToViewY(f5));
        return pointF;
    }

    public PointF viewToSourceCoord(float f, float f5) {
        return viewToSourceCoord(f, f5, new PointF());
    }

    private PointF viewToSourceCoord(float f, float f5, PointF pointF) {
        if (getPosition() == null) {
            return null;
        }
        pointF.set(viewToSourceX(f), viewToSourceY(f5));
        return pointF;
    }

    public void setPosition(float f, float f5, boolean z) {
        Consumer<PointF> consumer;
        PointF pointF = this.mPosition;
        if (pointF != null) {
            pointF.set(f, f5);
            if (z && (consumer = this.mOnPositionChangeListener) != null) {
                consumer.accept(this.mPosition);
            }
        }
    }
}
