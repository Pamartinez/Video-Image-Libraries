package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.renderer;

import B1.a;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\nJ\u001d\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000eJ)\u0010\u0015\u001a\u00020\b2\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00112\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0002X\u0004¢\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020!8\u0002X\u0004¢\u0006\u0006\n\u0004\b%\u0010#¨\u0006'"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/BackgroundRenderer;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Canvas;", "canvas", "Lme/x;", "drawDimForSelectableBlocks", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/RectF;", "imageRect", "drawDimForTranslation", "(Landroid/graphics/Canvas;Landroid/graphics/RectF;)V", "drawSelectionBackground", "drawTranslationBackground", "", "", "Landroid/graphics/Point;", "highlightPolyList", "updateHighlightPath", "(Ljava/util/List;Landroid/graphics/RectF;)V", "Landroid/content/Context;", "", "isDimEnabled", "Z", "()Z", "setDimEnabled", "(Z)V", "Landroid/graphics/Path;", "highlightPath", "Landroid/graphics/Path;", "Landroid/graphics/Paint;", "clearPaint", "Landroid/graphics/Paint;", "highlightPaint", "transparentPaint", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BackgroundRenderer {
    public static final Companion Companion = new Companion((e) null);
    private final Paint clearPaint;
    private final Context context;
    private final Paint highlightPaint;
    private Path highlightPath = new Path();
    private boolean isDimEnabled;
    private final Paint transparentPaint;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/BackgroundRenderer$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public BackgroundRenderer(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Paint.Style style = Paint.Style.FILL_AND_STROKE;
        paint.setStyle(style);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Resources resources = context2.getResources();
        int i2 = R$dimen.textextraction_bg_corner_radius;
        paint.setPathEffect(new CornerPathEffect((float) resources.getDimensionPixelSize(i2)));
        this.clearPaint = paint;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(style);
        paint2.setColor(context2.getResources().getColor(R$color.textextraction_clear_color, (Resources.Theme) null));
        paint2.setPathEffect(new CornerPathEffect((float) context2.getResources().getDimensionPixelSize(i2)));
        this.highlightPaint = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(0);
        this.transparentPaint = paint3;
    }

    private final void drawDimForSelectableBlocks(Canvas canvas) {
        Canvas canvas2 = canvas;
        int saveLayer = canvas2.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (Paint) null);
        canvas2.drawColor(this.context.getColor(R$color.textextraction_dim_color));
        canvas2.drawPath(this.highlightPath, this.clearPaint);
        canvas2.drawPath(this.highlightPath, this.highlightPaint);
        canvas2.restoreToCount(saveLayer);
    }

    private final void drawDimForTranslation(Canvas canvas, RectF rectF) {
        canvas.drawColor(this.context.getColor(R$color.textextraction_dim_color));
        canvas.drawRect(rectF, this.transparentPaint);
    }

    public final void drawSelectionBackground(Canvas canvas) {
        j.e(canvas, "canvas");
        canvas.drawColor(0);
        if (this.isDimEnabled) {
            drawDimForSelectableBlocks(canvas);
        }
    }

    public final void drawTranslationBackground(Canvas canvas, RectF rectF) {
        j.e(canvas, "canvas");
        j.e(rectF, "imageRect");
        canvas.drawColor(0);
        if (this.isDimEnabled) {
            drawDimForTranslation(canvas, rectF);
        }
    }

    public final void setDimEnabled(boolean z) {
        this.isDimEnabled = z;
    }

    public final void updateHighlightPath(List<Point[]> list, RectF rectF) {
        j.e(list, "highlightPolyList");
        j.e(rectF, "imageRect");
        float min = Math.min(rectF.left + 1.0f, rectF.right - 1.0f);
        float f = rectF.left;
        if (min < f) {
            min = f;
        }
        float max = Math.max(f + 1.0f, rectF.right - 1.0f);
        float f5 = rectF.right;
        if (max > f5) {
            max = f5;
        }
        float min2 = Math.min(rectF.top + 1.0f, rectF.bottom - 1.0f);
        float f8 = rectF.top;
        if (min2 < f8) {
            min2 = f8;
        }
        float max2 = Math.max(f8 + 1.0f, rectF.bottom - 1.0f);
        float f10 = rectF.bottom;
        if (max2 > f10) {
            max2 = f10;
        }
        this.highlightPath.reset();
        for (Point[] pointArr : list) {
            Path path = new Path();
            path.moveTo(a.k((float) pointArr[0].x, min, max), a.k((float) pointArr[0].y, min2, max2));
            path.lineTo(a.k((float) pointArr[1].x, min, max), a.k((float) pointArr[1].y, min2, max2));
            path.lineTo(a.k((float) pointArr[2].x, min, max), a.k((float) pointArr[2].y, min2, max2));
            path.lineTo(a.k((float) pointArr[3].x, min, max), a.k((float) pointArr[3].y, min2, max2));
            path.lineTo(a.k((float) pointArr[0].x, min, max), a.k((float) pointArr[0].y, min2, max2));
            path.close();
            this.highlightPath.addPath(path);
        }
    }
}
