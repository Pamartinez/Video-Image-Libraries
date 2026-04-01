package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.graphics.shapes.CornerRounding;
import androidx.graphics.shapes.RoundedPolygonKt;
import androidx.graphics.shapes.Shapes_androidKt;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0011\u001a\u00020\u0010*\u00020\r2\b\b\u0001\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\n*\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u000e*\u00020\u0004H\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001b\u001a\u00020\u0004*\u00020\u0013¢\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\u0004*\u00020\u0013¢\u0006\u0004\b\u001d\u0010\u001cJ\u0011\u0010\u001e\u001a\u00020\u0004*\u00020\u000e¢\u0006\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/AnimationUtils;", "", "<init>", "()V", "", "size", "x", "y", "Landroid/graphics/Path;", "path", "Lme/x;", "getRoundedPolygonPath", "(FFFLandroid/graphics/Path;)V", "Landroid/content/Context;", "", "id", "", "readRawString", "(Landroid/content/Context;I)Ljava/lang/String;", "Landroid/graphics/Bitmap;", "checkAndRecycle", "(Landroid/graphics/Bitmap;)V", "convertPaintAlpha", "(F)I", "alpha", "applyAlpha", "(II)I", "halfWidth", "(Landroid/graphics/Bitmap;)F", "halfHeight", "half", "(I)F", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AnimationUtils {
    public static final AnimationUtils INSTANCE = new AnimationUtils();

    private AnimationUtils() {
    }

    public static final void getRoundedPolygonPath(float f, float f5, float f8, Path path) {
        j.e(path, FileApiContract.Parameter.PATH);
        Shapes_androidKt.toPath(RoundedPolygonKt.RoundedPolygon$default(8, f, f5, f8, new CornerRounding(100.0f, 1.0f), (List) null, 32, (Object) null), path);
        Matrix matrix = new Matrix();
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        matrix.postRotate(90.0f, rectF.centerX(), rectF.centerY());
        path.transform(matrix);
    }

    public final int applyAlpha(int i2, int i7) {
        return Color.argb(i7, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public final void checkAndRecycle(Bitmap bitmap) {
        j.e(bitmap, "<this>");
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public final int convertPaintAlpha(float f) {
        return (int) (f * ((float) ScoverState.TYPE_NFC_SMART_COVER));
    }

    public final float half(int i2) {
        return ((float) i2) / 2.0f;
    }

    public final float halfHeight(Bitmap bitmap) {
        j.e(bitmap, "<this>");
        return ((float) bitmap.getHeight()) / 2.0f;
    }

    public final float halfWidth(Bitmap bitmap) {
        j.e(bitmap, "<this>");
        return ((float) bitmap.getWidth()) / 2.0f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        B1.a.g(r0, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String readRawString(android.content.Context r1, int r2) {
        /*
            r0 = this;
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r1, r0)
            android.content.res.Resources r0 = r1.getResources()
            java.io.InputStream r0 = r0.openRawResource(r2)
            java.lang.String r1 = "openRawResource(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.nio.charset.Charset r1 = Tf.a.f3815a
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            r2.<init>(r0, r1)
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r1 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r1)
            java.lang.String r1 = D1.f.K(r0)     // Catch:{ all -> 0x0028 }
            r0.close()
            return r1
        L_0x0028:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002a }
        L_0x002a:
            r2 = move-exception
            B1.a.g(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.AnimationUtils.readRawString(android.content.Context, int):java.lang.String");
    }
}
