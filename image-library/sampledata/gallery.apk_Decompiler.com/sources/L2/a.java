package L2;

import Ae.b;
import Gf.m;
import Hf.C0770t;
import Hf.C0774x;
import Hf.C0776z;
import Hf.G;
import Hf.I;
import Hf.P;
import Hf.S;
import Hf.T;
import Hf.d0;
import L1.d;
import Le.g;
import Qe.C0816f;
import Qe.V;
import Tf.n;
import a.C0068a;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.appfunctions.schema.internal.dependencies.C0106p;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.framework.core.util.HashUtil;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;
import ig.f;
import ig.h;
import ig.l;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import jf.C1108h;
import me.i;
import me.j;
import ne.C1188f;
import ne.C1192j;
import ne.C1196n;
import qf.C1235b;
import qf.C1238e;
import sf.C1283j;
import sf.C1288o;
import uf.C1316a;
import uf.C1318c;
import xf.C1353d;
import yf.C1358b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {
    public static final void A(Object obj) {
        if (obj instanceof j) {
            throw ((j) obj).d;
        }
    }

    public static String B(int i2) {
        char[] cArr = new char[2];
        for (int i7 = 0; i7 < 2; i7++) {
            cArr[1 - i7] = Character.forDigit(i2 & 15, 16);
            i2 >>= 4;
        }
        return new String(cArr);
    }

    public static String D(int i2) {
        char[] cArr = new char[4];
        for (int i7 = 0; i7 < 4; i7++) {
            cArr[3 - i7] = Character.forDigit(i2 & 15, 16);
            i2 >>= 4;
        }
        return new String(cArr);
    }

    public static String E(int i2) {
        char[] cArr = new char[8];
        for (int i7 = 0; i7 < 8; i7++) {
            cArr[7 - i7] = Character.forDigit(i2 & 15, 16);
            i2 >>= 4;
        }
        return new String(cArr);
    }

    public static String F(long j2) {
        char[] cArr = new char[16];
        for (int i2 = 0; i2 < 16; i2++) {
            cArr[15 - i2] = Character.forDigit(((int) j2) & 15, 16);
            j2 >>= 4;
        }
        return new String(cArr);
    }

    public static void G(String str, Object obj, boolean z) {
        if (!z) {
            throw new RuntimeException(d.r(str, obj));
        }
    }

    public static C1283j H(b bVar) {
        kotlin.jvm.internal.j.e(bVar, "changeOptions");
        C1288o oVar = new C1288o();
        bVar.invoke(oVar);
        oVar.f5099a = true;
        return new C1283j(oVar);
    }

    public static T I(T t) {
        if (!(t instanceof C0770t)) {
            return new S(t, 1);
        }
        C0770t tVar = (C0770t) t;
        V[] vArr = tVar.b;
        ArrayList A02 = C1192j.A0(tVar.f3451c, vArr);
        ArrayList arrayList = new ArrayList(C1196n.w0(A02, 10));
        Iterator it = A02.iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            arrayList.add(k((P) iVar.d, (V) iVar.e));
        }
        return new C0770t(vArr, (P[]) arrayList.toArray(new P[0]), true);
    }

    public static void J(Parcel parcel, Parcelable parcelable) {
        if (parcelable != null) {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public static final int L(String str, byte[] bArr, int i2, int i7) {
        byte[] bytes = str.getBytes(C0106p.f1226a);
        int length = bytes.length;
        if (length - i2 <= i7) {
            System.arraycopy(bytes, 0, bArr, i2, length);
            return i2 + length;
        }
        throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kg.X a(java.lang.String r5) {
        /*
            ig.d r0 = ig.d.l
            boolean r1 = Tf.n.C0(r5)
            if (r1 != 0) goto L_0x006c
            java.lang.Object r1 = kg.Y.f4685a
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0012:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0066
            java.lang.Object r2 = r1.next()
            He.d r2 = (He.C0748d) r2
            java.lang.String r2 = r2.o()
            kotlin.jvm.internal.j.b(r2)
            java.lang.String r2 = kg.Y.a(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "kotlin."
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            boolean r3 = r5.equalsIgnoreCase(r3)
            if (r3 != 0) goto L_0x0044
            boolean r3 = r5.equalsIgnoreCase(r2)
            if (r3 != 0) goto L_0x0044
            goto L_0x0012
        L_0x0044:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "\n                The name of serial descriptor should uniquely identify associated serializer.\n                For serial name "
            java.lang.String r3 = " there already exist "
            java.lang.StringBuilder r5 = N2.j.k(r1, r5, r3)
            java.lang.String r1 = kg.Y.a(r2)
            r5.append(r1)
            java.lang.String r1 = "Serializer.\n                Please refer to SerialDescriptor documentation for additional information.\n            "
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = Tf.o.l0(r5)
            r0.<init>(r5)
            throw r0
        L_0x0066:
            kg.X r1 = new kg.X
            r1.<init>(r5, r0)
            return r1
        L_0x006c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Blank serial names are prohibited"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: L2.a.a(java.lang.String):kg.X");
    }

    public static final String b(Object[] objArr, int i2, int i7, C1188f fVar) {
        StringBuilder sb2 = new StringBuilder((i7 * 3) + 2);
        sb2.append("[");
        for (int i8 = 0; i8 < i7; i8++) {
            if (i8 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            C1188f fVar2 = objArr[i2 + i8];
            if (fVar2 == fVar) {
                sb2.append("(this Collection)");
            } else {
                sb2.append(fVar2);
            }
        }
        sb2.append("]");
        String sb3 = sb2.toString();
        kotlin.jvm.internal.j.d(sb3, "toString(...)");
        return sb3;
    }

    public static void c(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (colorStateList == null || !colorStateList.isStateful()) {
                DrawableCompat.setTintList(drawable, colorStateList);
            } else {
                int[] drawableState = textInputLayout.getDrawableState();
                int[] drawableState2 = checkableImageButton.getDrawableState();
                int length = drawableState.length;
                int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
                System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
                DrawableCompat.setTintList(drawable, ColorStateList.valueOf(colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor())));
            }
            if (mode != null) {
                DrawableCompat.setTintMode(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public static final h e(String str, C0068a aVar, f[] fVarArr, b bVar) {
        kotlin.jvm.internal.j.e(str, "serialName");
        if (n.C0(str)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        } else if (!aVar.equals(l.d)) {
            ig.a aVar2 = new ig.a(str);
            bVar.invoke(aVar2);
            return new h(str, aVar, aVar2.f4602c.size(), C1192j.x0(fVarArr), aVar2);
        } else {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead");
        }
    }

    public static h f(String str, C0068a aVar, f[] fVarArr) {
        kotlin.jvm.internal.j.e(str, "serialName");
        if (n.C0(str)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        } else if (!aVar.equals(l.d)) {
            ig.a aVar2 = new ig.a(str);
            return new h(str, aVar, aVar2.f4602c.size(), C1192j.x0(fVarArr), aVar2);
        } else {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead");
        }
    }

    /* JADX WARNING: type inference failed for: r6v10, types: [kotlin.jvm.internal.u, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v4, types: [se.c] */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        if (r6.v().equals(r5) == false) goto L_0x007e;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.io.Serializable g(Yf.g r4, Yf.h r5, se.C1271c r6) {
        /*
            boolean r0 = r6 instanceof Yf.k
            if (r0 == 0) goto L_0x0013
            r0 = r6
            Yf.k r0 = (Yf.k) r0
            int r1 = r0.f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f = r1
            goto L_0x0018
        L_0x0013:
            Yf.k r0 = new Yf.k
            r0.<init>(r6)
        L_0x0018:
            java.lang.Object r6 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            kotlin.jvm.internal.u r4 = r0.d
            A(r6)     // Catch:{ all -> 0x0029 }
            goto L_0x004b
        L_0x0029:
            r5 = move-exception
            goto L_0x004f
        L_0x002b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0033:
            A(r6)
            kotlin.jvm.internal.u r6 = new kotlin.jvm.internal.u
            r6.<init>()
            Yf.e r2 = new Yf.e     // Catch:{ all -> 0x004d }
            r2.<init>((Yf.h) r5, (kotlin.jvm.internal.u) r6)     // Catch:{ all -> 0x004d }
            r0.d = r6     // Catch:{ all -> 0x004d }
            r0.f = r3     // Catch:{ all -> 0x004d }
            java.lang.Object r4 = r4.collect(r2, r0)     // Catch:{ all -> 0x004d }
            if (r4 != r1) goto L_0x004b
            return r1
        L_0x004b:
            r4 = 0
            return r4
        L_0x004d:
            r5 = move-exception
            r4 = r6
        L_0x004f:
            java.lang.Object r4 = r4.d
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            if (r4 == 0) goto L_0x005b
            boolean r6 = r4.equals(r5)
            if (r6 != 0) goto L_0x007d
        L_0x005b:
            qe.h r6 = r0.getContext()
            Vf.y r0 = Vf.C0887y.e
            qe.f r6 = r6.get(r0)
            Vf.e0 r6 = (Vf.C0867e0) r6
            if (r6 == 0) goto L_0x007e
            Vf.n0 r6 = (Vf.n0) r6
            boolean r0 = r6.E()
            if (r0 != 0) goto L_0x0072
            goto L_0x007e
        L_0x0072:
            java.util.concurrent.CancellationException r6 = r6.v()
            boolean r6 = r6.equals(r5)
            if (r6 != 0) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            throw r5
        L_0x007e:
            if (r4 != 0) goto L_0x0081
            return r5
        L_0x0081:
            boolean r6 = r5 instanceof java.util.concurrent.CancellationException
            if (r6 == 0) goto L_0x0089
            He.F.e(r4, r5)
            throw r4
        L_0x0089:
            He.F.e(r5, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: L2.a.g(Yf.g, Yf.h, se.c):java.io.Serializable");
    }

    public static long h(long j2, long j3) {
        boolean z;
        long j8 = j2 + j3;
        boolean z3 = false;
        if ((j2 ^ j3) < 0) {
            z = true;
        } else {
            z = false;
        }
        if ((j2 ^ j8) >= 0) {
            z3 = true;
        }
        if (z || z3) {
            return j8;
        }
        throw new ArithmeticException(C0212a.o(N2.j.j(j2, "overflow: checkedAdd(", ArcCommonLog.TAG_COMMA), j3, ")"));
    }

    public static ImageView.ScaleType j(int i2) {
        if (i2 == 0) {
            return ImageView.ScaleType.FIT_XY;
        }
        if (i2 == 1) {
            return ImageView.ScaleType.FIT_START;
        }
        if (i2 == 2) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        if (i2 == 3) {
            return ImageView.ScaleType.FIT_END;
        }
        if (i2 == 5) {
            return ImageView.ScaleType.CENTER_CROP;
        }
        if (i2 != 6) {
            return ImageView.ScaleType.CENTER;
        }
        return ImageView.ScaleType.CENTER_INSIDE;
    }

    public static final P k(P p6, V v) {
        if (v == null || p6.a() == d0.INVARIANT) {
            return p6;
        }
        if (v.t() != p6.a()) {
            C1318c cVar = new C1318c(p6);
            I.e.getClass();
            return new G((C0774x) new C1316a(p6, cVar, false, I.f));
        } else if (!p6.c()) {
            return new G(p6.b());
        } else {
            Gf.b bVar = m.e;
            kotlin.jvm.internal.j.d(bVar, "NO_LOCKS");
            return new G((C0774x) new C0776z(bVar, new uf.d(0, p6)));
        }
    }

    public static final j l(Throwable th) {
        kotlin.jvm.internal.j.e(th, "exception");
        return new j(th);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return r0 + ((long) r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m(long r9, long r11, java.math.RoundingMode r13) {
        /*
            r13.getClass()
            long r0 = r9 / r11
            long r2 = r11 * r0
            long r2 = r9 - r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0010
            return r0
        L_0x0010:
            long r9 = r9 ^ r11
            r7 = 63
            long r9 = r9 >> r7
            int r9 = (int) r9
            r10 = 1
            r9 = r9 | r10
            int[] r7 = I2.d.f349a
            int r8 = r13.ordinal()
            r7 = r7[r8]
            switch(r7) {
                case 1: goto L_0x0054;
                case 2: goto L_0x005b;
                case 3: goto L_0x004f;
                case 4: goto L_0x0051;
                case 5: goto L_0x004b;
                case 6: goto L_0x0028;
                case 7: goto L_0x0028;
                case 8: goto L_0x0028;
                default: goto L_0x0022;
            }
        L_0x0022:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L_0x0028:
            long r2 = java.lang.Math.abs(r2)
            long r10 = java.lang.Math.abs(r11)
            long r10 = r10 - r2
            long r2 = r2 - r10
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0047
            java.math.RoundingMode r10 = java.math.RoundingMode.HALF_UP
            if (r13 == r10) goto L_0x0051
            java.math.RoundingMode r10 = java.math.RoundingMode.HALF_EVEN
            if (r13 != r10) goto L_0x0046
            r10 = 1
            long r10 = r10 & r0
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 == 0) goto L_0x0046
            goto L_0x0051
        L_0x0046:
            return r0
        L_0x0047:
            if (r10 <= 0) goto L_0x004a
            goto L_0x0051
        L_0x004a:
            return r0
        L_0x004b:
            if (r9 <= 0) goto L_0x004e
            goto L_0x0051
        L_0x004e:
            return r0
        L_0x004f:
            if (r9 >= 0) goto L_0x0053
        L_0x0051:
            long r9 = (long) r9
            long r0 = r0 + r9
        L_0x0053:
            return r0
        L_0x0054:
            if (r6 != 0) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            r10 = 0
        L_0x0058:
            a.C0068a.j(r10)
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: L2.a.m(long, long, java.math.RoundingMode):long");
    }

    public static long n(long j2, long j3) {
        C0068a.i("a", j2);
        C0068a.i("b", j3);
        if (j2 == 0) {
            return j3;
        }
        if (j3 == 0) {
            return j2;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2);
        long j8 = j2 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j3);
        long j10 = j3 >> numberOfTrailingZeros2;
        while (j8 != j10) {
            long j11 = j8 - j10;
            long j12 = (j11 >> 63) & j11;
            long j13 = (j11 - j12) - j12;
            j10 += j12;
            j8 = j13 >> Long.numberOfTrailingZeros(j13);
        }
        return j8 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static final int o(g gVar) {
        kotlin.jvm.internal.j.e(gVar, "<this>");
        return gVar.a().size();
    }

    public static String p(ClassLoader classLoader, Class cls) {
        try {
            Field declaredField = cls.getDeclaredField(FileApiContract.Parameter.PATH);
            declaredField.setAccessible(true);
            return (String) declaredField.get(classLoader);
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException unused) {
            String obj = classLoader.toString();
            int lastIndexOf = obj.lastIndexOf(91);
            if (lastIndexOf != -1) {
                obj = obj.substring(lastIndexOf + 1);
            }
            int indexOf = obj.indexOf(93);
            if (indexOf == -1) {
                return obj;
            }
            return obj.substring(0, indexOf);
        }
    }

    public static Matrix q(int i2, int i7, int i8, int i10, int i11) {
        boolean z;
        int i12;
        Matrix matrix = new Matrix();
        if (i11 != 0) {
            if (i11 % 90 != 0) {
                Log.d("ImageUtil", "Rotation of %d % 90 != 0");
            }
            matrix.postTranslate(((float) (-i2)) / 2.0f, ((float) (-i7)) / 2.0f);
            matrix.postRotate((float) i11);
        }
        if ((Math.abs(i11) + 90) % MOCRLang.KHMER == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i12 = i7;
        } else {
            i12 = i2;
        }
        if (!z) {
            i2 = i7;
        }
        if (!(i12 == i8 && i2 == i10)) {
            matrix.postScale(((float) i8) / ((float) i12), ((float) i10) / ((float) i2));
        }
        if (i11 != 0) {
            matrix.postTranslate(((float) i8) / 2.0f, ((float) i10) / 2.0f);
        }
        return matrix;
    }

    public static File[] r(String str) {
        int lastIndexOf;
        ArrayList arrayList = new ArrayList();
        if (str.startsWith("dexPath=")) {
            int indexOf = str.indexOf(44);
            if (indexOf == -1) {
                str = str.substring(8);
            } else {
                str = str.substring(8, indexOf);
            }
        }
        for (String str2 : str.split(NumericEnum.SEP)) {
            if (str2.startsWith("/data/app/") && (lastIndexOf = str2.lastIndexOf(".apk")) == str2.length() - 4) {
                int indexOf2 = str2.indexOf("-");
                if (indexOf2 != -1) {
                    lastIndexOf = indexOf2;
                }
                File file = new File(C0212a.l("/data/data/", str2.substring(10, lastIndexOf)));
                if (file.isDirectory() && file.canWrite()) {
                    File file2 = new File(file, "cache");
                    if ((file2.exists() || file2.mkdir()) && file2.isDirectory() && file2.canWrite()) {
                        arrayList.add(file2);
                    }
                }
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public static String s(int i2, int i7, int i8) {
        StringBuffer stringBuffer = new StringBuffer(80);
        int i10 = (~i7) & i2;
        int i11 = i2 & i7;
        if ((i11 & 1) != 0) {
            stringBuffer.append("|public");
        }
        if ((i11 & 2) != 0) {
            stringBuffer.append("|private");
        }
        if ((i11 & 4) != 0) {
            stringBuffer.append("|protected");
        }
        if ((i11 & 8) != 0) {
            stringBuffer.append("|static");
        }
        if ((i11 & 16) != 0) {
            stringBuffer.append("|final");
        }
        if ((i11 & 32) != 0) {
            if (i8 == 1) {
                stringBuffer.append("|super");
            } else {
                stringBuffer.append("|synchronized");
            }
        }
        if ((i11 & 64) != 0) {
            if (i8 == 3) {
                stringBuffer.append("|bridge");
            } else {
                stringBuffer.append("|volatile");
            }
        }
        if ((i11 & 128) != 0) {
            if (i8 == 3) {
                stringBuffer.append("|varargs");
            } else {
                stringBuffer.append("|transient");
            }
        }
        if ((i11 & 256) != 0) {
            stringBuffer.append("|native");
        }
        if ((i11 & 512) != 0) {
            stringBuffer.append("|interface");
        }
        if ((i11 & 1024) != 0) {
            stringBuffer.append("|abstract");
        }
        if ((i11 & 2048) != 0) {
            stringBuffer.append("|strictfp");
        }
        if ((i11 & 4096) != 0) {
            stringBuffer.append("|synthetic");
        }
        if ((i11 & SerializeOptions.SORT) != 0) {
            stringBuffer.append("|annotation");
        }
        if ((i11 & 16384) != 0) {
            stringBuffer.append("|enum");
        }
        if ((65536 & i11) != 0) {
            stringBuffer.append("|constructor");
        }
        if ((i11 & 131072) != 0) {
            stringBuffer.append("|declared_synchronized");
        }
        if (i10 != 0 || stringBuffer.length() == 0) {
            stringBuffer.append('|');
            stringBuffer.append(D(i10));
        }
        return stringBuffer.substring(1);
    }

    public static boolean t(Bundle bundle) {
        if (bundle.getString(GroupContract.Group.SERVICE_ID, "").isEmpty()) {
            D1.f.L("Service ID has to be set");
            return false;
        } else if (bundle.getString("serviceVersion", "").isEmpty()) {
            D1.f.L("No service version");
            return false;
        } else if (bundle.getString("sdkVersion", "").isEmpty()) {
            D1.f.L("No SDK version");
            return false;
        } else if (bundle.getString("sdkType", "").isEmpty()) {
            D1.f.L("No SDK type");
            return false;
        } else if (bundle.getString("serviceAgreeType", "").isEmpty()) {
            D1.f.L("You have to agree to terms and conditions");
            return false;
        } else {
            String string = bundle.getString("serviceAgreeType", "");
            D1.f.A("Agreement value: " + string);
            if (!"D".equals(string) && !"S".equals(string)) {
                D1.f.L("Undefined agreement: " + string);
                return false;
            } else if (!"D".equals(string) || bundle.getString("deviceId", "").isEmpty()) {
                return true;
            } else {
                D1.f.L("You can't use setDeviceId API if you used setAgree as Diagnostic agreement");
                return false;
            }
        }
    }

    public static void u(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int[] drawableState = textInputLayout.getDrawableState();
            int[] drawableState2 = checkableImageButton.getDrawableState();
            int length = drawableState.length;
            int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
            System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
            int colorForState = colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor());
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTintList(mutate, ColorStateList.valueOf(colorForState));
            checkableImageButton.setImageDrawable(mutate);
        }
    }

    public static final void v(Object[] objArr, int i2, int i7) {
        kotlin.jvm.internal.j.e(objArr, "<this>");
        while (i2 < i7) {
            objArr[i2] = null;
            i2++;
        }
    }

    public static long w(long j2, long j3) {
        boolean z;
        boolean z3;
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(~j3) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j2);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        long j8 = ((j2 ^ j3) >>> 63) + Long.MAX_VALUE;
        boolean z7 = false;
        if (numberOfLeadingZeros < 64) {
            z = true;
        } else {
            z = false;
        }
        int i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i2 < 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (j3 == Long.MIN_VALUE) {
            z7 = true;
        }
        if (!z && !(z7 & z3)) {
            long j10 = j2 * j3;
            if (i2 == 0 || j10 / j2 == j3) {
                return j10;
            }
        }
        return j8;
    }

    public static void x(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean z;
        boolean hasOnClickListeners = ViewCompat.hasOnClickListeners(checkableImageButton);
        boolean z3 = false;
        int i2 = 1;
        if (onLongClickListener != null) {
            z = true;
        } else {
            z = false;
        }
        if (hasOnClickListeners || z) {
            z3 = true;
        }
        checkableImageButton.setFocusable(z3);
        checkableImageButton.setClickable(hasOnClickListeners);
        checkableImageButton.setPressable(hasOnClickListeners);
        checkableImageButton.setLongClickable(z);
        if (!z3) {
            i2 = 2;
        }
        ViewCompat.setImportantForAccessibility(checkableImageButton, i2);
    }

    public static String y(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(HashUtil.SHA256);
            instance.update(str.getBytes(StandardCharsets.UTF_8));
            return String.format(Locale.US, "%064x", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            C0068a.J("failed to hash : " + e.getMessage());
            return null;
        }
    }

    public static final String z(C0816f fVar, String str) {
        String str2;
        kotlin.jvm.internal.j.e(fVar, "classDescriptor");
        String str3 = Pe.d.f3633a;
        C1238e i2 = C1353d.g(fVar).i();
        kotlin.jvm.internal.j.d(i2, "toUnsafe(...)");
        C1235b f = Pe.d.f(i2);
        if (f != null) {
            str2 = C1358b.e(f);
        } else {
            str2 = Gd.a.j(fVar, C1108h.d);
        }
        kotlin.jvm.internal.j.e(str2, "internalName");
        return str2 + '.' + str;
    }

    public abstract int K(String str, byte[] bArr, int i2, int i7);

    public abstract String d();
}
