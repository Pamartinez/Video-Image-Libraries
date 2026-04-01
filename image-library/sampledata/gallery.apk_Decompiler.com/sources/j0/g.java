package J0;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import java.io.Closeable;
import x0.C0323a;
import x0.C0326d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final D1.g f363a = new D1.g(2);
    public static final D1.g b = new D1.g(3);

    /* renamed from: c  reason: collision with root package name */
    public static final D1.g f364c = new D1.g(4);
    public static final D1.g d = new D1.g(5);
    public static final float e = ((float) (Math.sqrt(2.0d) / 2.0d));

    public static void a(float f, float f5, float f8, Path path) {
        C0323a aVar = C0326d.f2049a;
        PathMeasure pathMeasure = (PathMeasure) f363a.get();
        Path path2 = (Path) b.get();
        Path path3 = (Path) f364c.get();
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (!(f == 1.0f && f5 == 0.0f) && length >= 1.0f && ((double) Math.abs((f5 - f) - 1.0f)) >= 0.01d) {
            float f10 = f * length;
            float f11 = f5 * length;
            float f12 = f8 * length;
            float min = Math.min(f10, f11) + f12;
            float max = Math.max(f10, f11) + f12;
            if (min >= length && max >= length) {
                min = (float) f.c(min, length);
                max = (float) f.c(max, length);
            }
            if (min < 0.0f) {
                min = (float) f.c(min, length);
            }
            if (max < 0.0f) {
                max = (float) f.c(max, length);
            }
            int i2 = (min > max ? 1 : (min == max ? 0 : -1));
            if (i2 == 0) {
                path.reset();
                return;
            }
            if (i2 >= 0) {
                min -= length;
            }
            path2.reset();
            pathMeasure.getSegment(min, max, path2, true);
            if (max > length) {
                path3.reset();
                pathMeasure.getSegment(0.0f, max % length, path3, true);
                path2.addPath(path3);
            } else if (min < 0.0f) {
                path3.reset();
                pathMeasure.getSegment(min + length, length, path3, true);
                path2.addPath(path3);
            }
            path.set(path2);
        }
    }

    public static void b(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e7) {
            throw e7;
        } catch (Exception unused) {
        }
    }

    public static float c() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float d(Matrix matrix) {
        float[] fArr = (float[]) d.get();
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        float f = e;
        fArr[2] = f;
        fArr[3] = f;
        matrix.mapPoints(fArr);
        return (float) Math.hypot((double) (fArr[2] - fArr[0]), (double) (fArr[3] - fArr[1]));
    }

    public static Bitmap e(Bitmap bitmap, int i2, int i7) {
        if (bitmap.getWidth() == i2 && bitmap.getHeight() == i7) {
            return bitmap;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i7, true);
        bitmap.recycle();
        return createScaledBitmap;
    }

    public static void f(Canvas canvas, RectF rectF, Paint paint) {
        C0323a aVar = C0326d.f2049a;
        canvas.saveLayer(rectF, paint);
    }
}
