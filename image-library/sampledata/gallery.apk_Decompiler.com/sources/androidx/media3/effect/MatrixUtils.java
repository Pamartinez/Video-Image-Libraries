package androidx.media3.effect;

import F2.C0040v;
import F2.N;
import F2.U;
import android.graphics.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;
import i.C0212a;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MatrixUtils {
    private static final float[][] NDC_CUBE = {new float[]{1.0f, 0.0f, 0.0f, 1.0f}, new float[]{-1.0f, 0.0f, 0.0f, 1.0f}, new float[]{0.0f, 1.0f, 0.0f, 1.0f}, new float[]{0.0f, -1.0f, 0.0f, 1.0f}, new float[]{0.0f, 0.0f, 1.0f, 1.0f}, new float[]{0.0f, 0.0f, -1.0f, 1.0f}};

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: F2.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: F2.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: F2.Q} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static F2.U clipConvexPolygonToNdcRange(F2.U r12) {
        /*
            int r0 = r12.size()
            r1 = 3
            r2 = 0
            r3 = 1
            if (r0 < r1) goto L_0x000b
            r0 = r3
            goto L_0x000c
        L_0x000b:
            r0 = r2
        L_0x000c:
            java.lang.String r1 = "A polygon must have at least 3 vertices."
            androidx.media3.common.util.Assertions.checkArgument(r0, r1)
            F2.Q r0 = new F2.Q
            r1 = 4
            r0.<init>(r1)
            r0.c(r12)
            float[][] r12 = NDC_CUBE
            int r4 = r12.length
            r5 = r2
        L_0x001e:
            if (r5 >= r4) goto L_0x0079
            r6 = r12[r5]
            F2.y0 r0 = r0.f()
            F2.Q r7 = new F2.Q
            r7.<init>(r1)
            r8 = r2
        L_0x002c:
            int r9 = r0.g
            if (r8 >= r9) goto L_0x0075
            java.lang.Object r9 = r0.get(r8)
            float[] r9 = (float[]) r9
            int r10 = r0.g
            int r11 = r10 + r8
            int r11 = r11 - r3
            int r11 = r11 % r10
            java.lang.Object r10 = r0.get(r11)
            float[] r10 = (float[]) r10
            boolean r11 = isInsideClippingHalfSpace(r9, r6)
            if (r11 == 0) goto L_0x005f
            boolean r11 = isInsideClippingHalfSpace(r10, r6)
            if (r11 != 0) goto L_0x005b
            float[] r10 = computeIntersectionPoint(r6, r6, r10, r9)
            boolean r11 = java.util.Arrays.equals(r9, r10)
            if (r11 != 0) goto L_0x005b
            r7.a(r10)
        L_0x005b:
            r7.a(r9)
            goto L_0x0072
        L_0x005f:
            boolean r11 = isInsideClippingHalfSpace(r10, r6)
            if (r11 == 0) goto L_0x0072
            float[] r9 = computeIntersectionPoint(r6, r6, r10, r9)
            boolean r10 = java.util.Arrays.equals(r10, r9)
            if (r10 != 0) goto L_0x0072
            r7.a(r9)
        L_0x0072:
            int r8 = r8 + 1
            goto L_0x002c
        L_0x0075:
            int r5 = r5 + 1
            r0 = r7
            goto L_0x001e
        L_0x0079:
            F2.y0 r12 = r0.f()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.MatrixUtils.clipConvexPolygonToNdcRange(F2.U):F2.U");
    }

    private static float[] computeIntersectionPoint(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        boolean z;
        if (fArr2.length == 4) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "Expecting 4 plane parameters");
        float f = fArr[0];
        float f5 = fArr3[0];
        float f8 = fArr2[0];
        float f10 = fArr[1];
        float f11 = fArr3[1];
        float f12 = fArr2[1];
        float f13 = fArr[2];
        float f14 = fArr3[2];
        float f15 = fArr2[2];
        float f16 = ((f13 - f14) * f15) + ((f10 - f11) * f12) + ((f - f5) * f8);
        float f17 = fArr4[0];
        float f18 = (f17 - f5) * f8;
        float f19 = fArr4[1];
        float a7 = C0212a.a(f19, f11, f12, f18);
        float f20 = fArr4[2];
        float f21 = f16 / (((f20 - f14) * f15) + a7);
        return new float[]{C0212a.a(f17, f5, f21, f5), C0212a.a(f19, f11, f21, f11), C0212a.a(f20, f14, f21, f14), 1.0f};
    }

    public static Size configureAndGetOutputSize(int i2, int i7, List<GlMatrixTransformation> list) {
        boolean z;
        boolean z3 = true;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "inputWidth must be positive");
        if (i7 <= 0) {
            z3 = false;
        }
        Assertions.checkArgument(z3, "inputHeight must be positive");
        Size size = new Size(i2, i7);
        for (int i8 = 0; i8 < list.size(); i8++) {
            size = list.get(i8).configure(size.getWidth(), size.getHeight());
        }
        return size;
    }

    public static float[] getGlMatrixArray(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] matrix4x4Array = getMatrix4x4Array(fArr);
        float[] fArr2 = new float[16];
        android.opengl.Matrix.transposeM(fArr2, 0, matrix4x4Array, 0);
        return fArr2;
    }

    private static float[] getMatrix4x4Array(float[] fArr) {
        int i2;
        int i7;
        float[] fArr2 = new float[16];
        fArr2[10] = 1.0f;
        for (int i8 = 0; i8 < 3; i8++) {
            for (int i10 = 0; i10 < 3; i10++) {
                if (i8 == 2) {
                    i2 = 3;
                } else {
                    i2 = i8;
                }
                if (i10 == 2) {
                    i7 = 3;
                } else {
                    i7 = i10;
                }
                fArr2[(i2 * 4) + i7] = fArr[(i8 * 3) + i10];
            }
        }
        return fArr2;
    }

    private static boolean isInsideClippingHalfSpace(float[] fArr, float[] fArr2) {
        boolean z;
        if (fArr2.length == 4) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "Expecting 4 plane parameters");
        if ((fArr2[2] * fArr[2]) + (fArr2[1] * fArr[1]) + (fArr2[0] * fArr[0]) <= fArr2[3]) {
            return true;
        }
        return false;
    }

    public static U transformPoints(float[] fArr, U u) {
        C0040v.c(4, "initialCapacity");
        Object[] objArr = new Object[4];
        int i2 = 0;
        int i7 = 0;
        while (i2 < u.size()) {
            float[] fArr2 = new float[4];
            float[] fArr3 = fArr;
            android.opengl.Matrix.multiplyMV(fArr2, 0, fArr3, 0, (float[]) u.get(i2), 0);
            float f = fArr2[0];
            float f5 = fArr2[3];
            fArr2[0] = f / f5;
            fArr2[1] = fArr2[1] / f5;
            fArr2[2] = fArr2[2] / f5;
            fArr2[3] = 1.0f;
            int i8 = i7 + 1;
            int e = N.e(objArr.length, i8);
            if (e > objArr.length) {
                objArr = Arrays.copyOf(objArr, e);
            }
            objArr[i7] = fArr2;
            i2++;
            i7 = i8;
            fArr = fArr3;
            objArr = objArr;
        }
        return U.w(i7, objArr);
    }
}
