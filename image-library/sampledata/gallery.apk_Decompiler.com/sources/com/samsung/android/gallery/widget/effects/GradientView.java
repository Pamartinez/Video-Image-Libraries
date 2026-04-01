package com.samsung.android.gallery.widget.effects;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$styleable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GradientView extends View {
    private int mEdgeEndColor;
    private final Paint mEdgePaint = new Paint();
    private int mEdgeStartColor;
    private float mEdgeWidth;
    private int mEndColor;
    private boolean mHasEdge = false;
    private final Paint mPaint = new Paint();
    private float mPercent;
    private int mStartColor;
    private boolean mStartFromTop = false;

    public GradientView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttributes(context, attributeSet);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        boolean isTerminated;
        boolean isTerminated2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GradientView);
        boolean z = false;
        try {
            int color = obtainStyledAttributes.getColor(R$styleable.GradientView_gradientStartColor, getResources().getColor(R$color.default_background, (Resources.Theme) null));
            this.mStartColor = color;
            this.mEndColor = color & 16777215;
            this.mPercent = obtainStyledAttributes.getFloat(R$styleable.GradientView_gradientRatio, 0.3f);
            this.mStartFromTop = obtainStyledAttributes.getBoolean(R$styleable.GradientView_gradientStartFromTop, false);
            this.mHasEdge = obtainStyledAttributes.getBoolean(R$styleable.GradientView_hasEdgeGradient, false);
            int color2 = obtainStyledAttributes.getColor(R$styleable.GradientView_edgeGradientStartColor, getResources().getColor(R$color.default_fw_background, (Resources.Theme) null));
            this.mEdgeStartColor = color2;
            this.mEdgeEndColor = color2 & 16777215;
            this.mEdgeWidth = obtainStyledAttributes.getDimension(R$styleable.GradientView_edgeGradientWidth, 0.0f);
            if (obtainStyledAttributes instanceof AutoCloseable) {
                ((AutoCloseable) obtainStyledAttributes).close();
                return;
            } else if (obtainStyledAttributes instanceof ExecutorService) {
                ExecutorService executorService = (ExecutorService) obtainStyledAttributes;
                if (executorService != ForkJoinPool.commonPool() && !(isTerminated2 = executorService.isTerminated())) {
                    executorService.shutdown();
                    while (!isTerminated2) {
                        try {
                            isTerminated2 = executorService.awaitTermination(1, TimeUnit.DAYS);
                        } catch (InterruptedException unused) {
                            if (!z) {
                                executorService.shutdownNow();
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
                return;
            } else {
                obtainStyledAttributes.recycle();
                return;
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void draw(Canvas canvas) {
        float f;
        float f5;
        Canvas canvas2;
        super.draw(canvas);
        int save = canvas.save();
        float height = (float) getHeight();
        if (this.mStartFromTop) {
            f = height * this.mPercent;
            f5 = 0.0f;
        } else {
            f5 = height;
            f = (1.0f - this.mPercent) * height;
        }
        int i2 = this.mStartColor;
        int i7 = this.mEndColor;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.mPaint.setShader(new LinearGradient(0.0f, f5, 0.0f, f, i2, i7, tileMode));
        float min = Math.min(f5, f);
        float max = Math.max(f5, f);
        if (this.mHasEdge) {
            this.mEdgePaint.setShader(new LinearGradient(0.0f, f5, 0.0f, f, this.mEdgeStartColor, this.mEdgeEndColor, tileMode));
            canvas2 = canvas;
            canvas2.drawRect(0.0f, min, this.mEdgeWidth, max, this.mEdgePaint);
            canvas2.drawRect(this.mEdgeWidth, min, ((float) getWidth()) - this.mEdgeWidth, max, this.mPaint);
            canvas2.drawRect(((float) getWidth()) - this.mEdgeWidth, min, (float) getWidth(), max, this.mEdgePaint);
        } else {
            canvas2 = canvas;
            canvas2.drawRect(0.0f, min, (float) getWidth(), max, this.mPaint);
        }
        canvas2.restoreToCount(save);
    }
}
