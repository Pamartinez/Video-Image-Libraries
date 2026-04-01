package com.samsung.android.sesl;

import K.e;
import L2.a;
import android.graphics.RenderEffect;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sesl.renderEffectImageFilter.ImageFilterParams$GradientBlur;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\u0007J\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u0007J\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0007J\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0007J\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0007J\u0015\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0007¢\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002XD¢\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010%8\u0002X\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u0004\u0018\u00010\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010+\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b-\u0010,R\u0016\u0010.\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010,R\u0016\u0010/\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u0010,R\u0016\u00100\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b0\u0010,R\u0016\u00101\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b1\u0010,R\u0016\u00102\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b2\u0010,R\u0016\u00103\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u0010,R\u0016\u00104\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b4\u0010,R\u0016\u00105\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b5\u0010,R\u0016\u00106\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u0010,R\u0016\u00107\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b7\u0010,R\u0016\u00108\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b8\u0010,R\u0016\u00109\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b9\u0010,R\u0016\u0010:\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b:\u0010,R\u0016\u0010;\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b;\u0010,R\u0016\u0010<\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b<\u0010,R\u0016\u0010=\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b=\u0010,R\u0016\u0010>\u001a\u0004\u0018\u00010*8\u0002X\u0004¢\u0006\u0006\n\u0004\b>\u0010,¨\u0006?"}, d2 = {"Lcom/samsung/android/sesl/REImageFilter;", "", "<init>", "()V", "", "radius", "setBlurRadius", "(F)Lcom/samsung/android/sesl/REImageFilter;", "saturation", "setProportionalSaturation", "curveLevel", "setCurveLevel", "maxX", "setCurveMaxX", "minX", "setCurveMinX", "maxY", "setCurveMaxY", "minY", "setCurveMinY", "", "enable", "setDither", "(Z)Lcom/samsung/android/sesl/REImageFilter;", "Lcom/samsung/android/sesl/renderEffectImageFilter/ImageFilterParams$GradientBlur;", "obj", "setGradientBlur", "(Lcom/samsung/android/sesl/renderEffectImageFilter/ImageFilterParams$GradientBlur;)Lcom/samsung/android/sesl/REImageFilter;", "", "width", "height", "Landroid/graphics/RenderEffect;", "build", "(II)Landroid/graphics/RenderEffect;", "", "TAG", "Ljava/lang/String;", "Ljava/lang/Class;", "imageFilterClass", "Ljava/lang/Class;", "instance", "Ljava/lang/Object;", "Ljava/lang/reflect/Method;", "getRenderNodeMethod", "Ljava/lang/reflect/Method;", "setBlurRadiusMethod", "setProportionalSaturationMethod", "setCurveLevelMethod", "setCurveMaxXMethod", "setCurveMinXMethod", "setCurveMaxYMethod", "setCurveMinYMethod", "setDitherMethod", "setBlurPresetMethod", "setSizeMethod", "buildMethod", "clearMethod", "toStringMethod", "printParamsMethod", "setGradientBlurMethod", "setGradientColorMethod", "setOffsetMethod", "setBGColorAdjustmentMethod", "graphic-solution_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class REImageFilter {
    private final String TAG = "REImageFilter";
    private final Method buildMethod;
    private final Method clearMethod;
    private final Method getRenderNodeMethod;
    private final Class<?> imageFilterClass;
    private final Object instance;
    private final Method printParamsMethod;
    private final Method setBGColorAdjustmentMethod;
    private final Method setBlurPresetMethod;
    private final Method setBlurRadiusMethod;
    private final Method setCurveLevelMethod;
    private final Method setCurveMaxXMethod;
    private final Method setCurveMaxYMethod;
    private final Method setCurveMinXMethod;
    private final Method setCurveMinYMethod;
    private final Method setDitherMethod;
    private final Method setGradientBlurMethod;
    private final Method setGradientColorMethod;
    private final Method setOffsetMethod;
    private final Method setProportionalSaturationMethod;
    private final Method setSizeMethod;
    private final Method toStringMethod;

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0145 A[Catch:{ all -> 0x0149 }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x016c A[Catch:{ all -> 0x0170 }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0191 A[Catch:{ all -> 0x0195 }] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x01b6 A[Catch:{ all -> 0x01ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x01db A[Catch:{ all -> 0x01df }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x01fc A[Catch:{ all -> 0x0200 }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x021e A[Catch:{ all -> 0x0222 }] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x022e  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x023f A[Catch:{ all -> 0x0243 }] */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A[Catch:{ all -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0258 A[Catch:{ all -> 0x0270 }] */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x027c  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x0285 A[Catch:{ all -> 0x029f }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x02c0 A[Catch:{ all -> 0x02c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x02d0  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x02e5 A[Catch:{ all -> 0x02e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0067 A[Catch:{ all -> 0x006b }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008c A[Catch:{ all -> 0x0090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00b1 A[Catch:{ all -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00d6 A[Catch:{ all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00fb A[Catch:{ all -> 0x00ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0120 A[Catch:{ all -> 0x0124 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public REImageFilter() {
        /*
            r13 = this;
            java.lang.Class r1 = java.lang.Integer.TYPE
            java.lang.Class r2 = java.lang.Float.TYPE
            r13.<init>()
            java.lang.String r0 = "REImageFilter"
            r13.TAG = r0
            java.lang.String r0 = "com.samsung.android.graphics.RenderEffectImageFilter"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ all -> 0x0012 }
            goto L_0x0017
        L_0x0012:
            r0 = move-exception
            me.j r0 = L2.a.l(r0)
        L_0x0017:
            boolean r3 = r0 instanceof me.j
            r4 = 0
            if (r3 == 0) goto L_0x001d
            r0 = r4
        L_0x001d:
            java.lang.Class r0 = (java.lang.Class) r0
            r13.imageFilterClass = r0
            if (r0 == 0) goto L_0x002c
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r4)     // Catch:{ Exception -> 0x002c }
            java.lang.Object r0 = r0.newInstance(r4)     // Catch:{ Exception -> 0x002c }
            goto L_0x002d
        L_0x002c:
            r0 = r4
        L_0x002d:
            r13.instance = r0
            r3 = 1
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x0048
            java.lang.String r5 = "getRenderNode"
            java.lang.Class<android.graphics.Bitmap> r6 = android.graphics.Bitmap.class
            java.lang.Class[] r6 = new java.lang.Class[]{r6}     // Catch:{ all -> 0x0046 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x0048
            r0.setAccessible(r3)     // Catch:{ all -> 0x0046 }
            goto L_0x004e
        L_0x0046:
            r0 = move-exception
            goto L_0x004a
        L_0x0048:
            r0 = r4
            goto L_0x004e
        L_0x004a:
            me.j r0 = L2.a.l(r0)
        L_0x004e:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x0053
            r0 = r4
        L_0x0053:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.getRenderNodeMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x006d
            java.lang.String r5 = "setBlurRadius"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x006b }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x006d
            r0.setAccessible(r3)     // Catch:{ all -> 0x006b }
            goto L_0x0073
        L_0x006b:
            r0 = move-exception
            goto L_0x006f
        L_0x006d:
            r0 = r4
            goto L_0x0073
        L_0x006f:
            me.j r0 = L2.a.l(r0)
        L_0x0073:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x0078
            r0 = r4
        L_0x0078:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setBlurRadiusMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x0092
            java.lang.String r5 = "setProportionalSaturation"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x0090 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x0092
            r0.setAccessible(r3)     // Catch:{ all -> 0x0090 }
            goto L_0x0098
        L_0x0090:
            r0 = move-exception
            goto L_0x0094
        L_0x0092:
            r0 = r4
            goto L_0x0098
        L_0x0094:
            me.j r0 = L2.a.l(r0)
        L_0x0098:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x009d
            r0 = r4
        L_0x009d:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setProportionalSaturationMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x00b5 }
            if (r0 == 0) goto L_0x00b7
            java.lang.String r5 = "setCurveLevel"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x00b5 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x00b5 }
            if (r0 == 0) goto L_0x00b7
            r0.setAccessible(r3)     // Catch:{ all -> 0x00b5 }
            goto L_0x00bd
        L_0x00b5:
            r0 = move-exception
            goto L_0x00b9
        L_0x00b7:
            r0 = r4
            goto L_0x00bd
        L_0x00b9:
            me.j r0 = L2.a.l(r0)
        L_0x00bd:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x00c2
            r0 = r4
        L_0x00c2:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setCurveLevelMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x00da }
            if (r0 == 0) goto L_0x00dc
            java.lang.String r5 = "setCurveMaxX"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x00da }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x00da }
            if (r0 == 0) goto L_0x00dc
            r0.setAccessible(r3)     // Catch:{ all -> 0x00da }
            goto L_0x00e2
        L_0x00da:
            r0 = move-exception
            goto L_0x00de
        L_0x00dc:
            r0 = r4
            goto L_0x00e2
        L_0x00de:
            me.j r0 = L2.a.l(r0)
        L_0x00e2:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x00e7
            r0 = r4
        L_0x00e7:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setCurveMaxXMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x00ff }
            if (r0 == 0) goto L_0x0101
            java.lang.String r5 = "setCurveMinX"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x00ff }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x00ff }
            if (r0 == 0) goto L_0x0101
            r0.setAccessible(r3)     // Catch:{ all -> 0x00ff }
            goto L_0x0107
        L_0x00ff:
            r0 = move-exception
            goto L_0x0103
        L_0x0101:
            r0 = r4
            goto L_0x0107
        L_0x0103:
            me.j r0 = L2.a.l(r0)
        L_0x0107:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x010c
            r0 = r4
        L_0x010c:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setCurveMinXMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0124 }
            if (r0 == 0) goto L_0x0126
            java.lang.String r5 = "setCurveMaxY"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x0124 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0124 }
            if (r0 == 0) goto L_0x0126
            r0.setAccessible(r3)     // Catch:{ all -> 0x0124 }
            goto L_0x012c
        L_0x0124:
            r0 = move-exception
            goto L_0x0128
        L_0x0126:
            r0 = r4
            goto L_0x012c
        L_0x0128:
            me.j r0 = L2.a.l(r0)
        L_0x012c:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x0131
            r0 = r4
        L_0x0131:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setCurveMaxYMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x014b
            java.lang.String r5 = "setCurveMinY"
            java.lang.Class[] r6 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x0149 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x014b
            r0.setAccessible(r3)     // Catch:{ all -> 0x0149 }
            goto L_0x0151
        L_0x0149:
            r0 = move-exception
            goto L_0x014d
        L_0x014b:
            r0 = r4
            goto L_0x0151
        L_0x014d:
            me.j r0 = L2.a.l(r0)
        L_0x0151:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x0156
            r0 = r4
        L_0x0156:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setCurveMinYMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0170 }
            if (r0 == 0) goto L_0x0172
            java.lang.String r5 = "setDither"
            java.lang.Class r6 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0170 }
            java.lang.Class[] r6 = new java.lang.Class[]{r6}     // Catch:{ all -> 0x0170 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0170 }
            if (r0 == 0) goto L_0x0172
            r0.setAccessible(r3)     // Catch:{ all -> 0x0170 }
            goto L_0x0178
        L_0x0170:
            r0 = move-exception
            goto L_0x0174
        L_0x0172:
            r0 = r4
            goto L_0x0178
        L_0x0174:
            me.j r0 = L2.a.l(r0)
        L_0x0178:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x017d
            r0 = r4
        L_0x017d:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setDitherMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0195 }
            if (r0 == 0) goto L_0x0197
            java.lang.String r5 = "setBlurPreset"
            java.lang.Class[] r6 = new java.lang.Class[]{r1}     // Catch:{ all -> 0x0195 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0195 }
            if (r0 == 0) goto L_0x0197
            r0.setAccessible(r3)     // Catch:{ all -> 0x0195 }
            goto L_0x019d
        L_0x0195:
            r0 = move-exception
            goto L_0x0199
        L_0x0197:
            r0 = r4
            goto L_0x019d
        L_0x0199:
            me.j r0 = L2.a.l(r0)
        L_0x019d:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x01a2
            r0 = r4
        L_0x01a2:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setBlurPresetMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x01ba }
            if (r0 == 0) goto L_0x01bc
            java.lang.String r5 = "setSize"
            java.lang.Class[] r6 = new java.lang.Class[]{r1, r1}     // Catch:{ all -> 0x01ba }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x01ba }
            if (r0 == 0) goto L_0x01bc
            r0.setAccessible(r3)     // Catch:{ all -> 0x01ba }
            goto L_0x01c2
        L_0x01ba:
            r0 = move-exception
            goto L_0x01be
        L_0x01bc:
            r0 = r4
            goto L_0x01c2
        L_0x01be:
            me.j r0 = L2.a.l(r0)
        L_0x01c2:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x01c7
            r0 = r4
        L_0x01c7:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setSizeMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x01df }
            if (r0 == 0) goto L_0x01e1
            java.lang.String r5 = "build"
            java.lang.Class[] r6 = new java.lang.Class[]{r1, r1}     // Catch:{ all -> 0x01df }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x01df }
            if (r0 == 0) goto L_0x01e1
            r0.setAccessible(r3)     // Catch:{ all -> 0x01df }
            goto L_0x01e7
        L_0x01df:
            r0 = move-exception
            goto L_0x01e3
        L_0x01e1:
            r0 = r4
            goto L_0x01e7
        L_0x01e3:
            me.j r0 = L2.a.l(r0)
        L_0x01e7:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x01ec
            r0 = r4
        L_0x01ec:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.buildMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0200 }
            if (r0 == 0) goto L_0x0202
            java.lang.String r5 = "clear"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r4)     // Catch:{ all -> 0x0200 }
            if (r0 == 0) goto L_0x0202
            r0.setAccessible(r3)     // Catch:{ all -> 0x0200 }
            goto L_0x0208
        L_0x0200:
            r0 = move-exception
            goto L_0x0204
        L_0x0202:
            r0 = r4
            goto L_0x0208
        L_0x0204:
            me.j r0 = L2.a.l(r0)
        L_0x0208:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x020d
            r0 = r4
        L_0x020d:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.clearMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0222 }
            if (r0 == 0) goto L_0x0224
            java.lang.String r5 = "toString"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r4)     // Catch:{ all -> 0x0222 }
            if (r0 == 0) goto L_0x0224
            r0.setAccessible(r3)     // Catch:{ all -> 0x0222 }
            goto L_0x022a
        L_0x0222:
            r0 = move-exception
            goto L_0x0226
        L_0x0224:
            r0 = r4
            goto L_0x022a
        L_0x0226:
            me.j r0 = L2.a.l(r0)
        L_0x022a:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x022f
            r0 = r4
        L_0x022f:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.toStringMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x0245
            java.lang.String r5 = "printParams"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r4)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x0245
            r0.setAccessible(r3)     // Catch:{ all -> 0x0243 }
            goto L_0x024b
        L_0x0243:
            r0 = move-exception
            goto L_0x0247
        L_0x0245:
            r0 = r4
            goto L_0x024b
        L_0x0247:
            me.j r0 = L2.a.l(r0)
        L_0x024b:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x0250
            r0 = r4
        L_0x0250:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.printParamsMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x0270 }
            if (r0 == 0) goto L_0x0272
            java.lang.String r5 = "setGradientBlur"
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ all -> 0x0270 }
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0270 }
            r7 = r6
            r8 = r6
            r9 = r6
            r10 = r6
            java.lang.Class[] r6 = new java.lang.Class[]{r6, r7, r8, r9, r10, r11}     // Catch:{ all -> 0x0270 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x0270 }
            if (r0 == 0) goto L_0x0272
            r0.setAccessible(r3)     // Catch:{ all -> 0x0270 }
            goto L_0x0278
        L_0x0270:
            r0 = move-exception
            goto L_0x0274
        L_0x0272:
            r0 = r4
            goto L_0x0278
        L_0x0274:
            me.j r0 = L2.a.l(r0)
        L_0x0278:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x027d
            r0 = r4
        L_0x027d:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setGradientBlurMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x029f }
            if (r0 == 0) goto L_0x02a1
            java.lang.String r5 = "setGradientColor"
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ all -> 0x029f }
            java.lang.Class r7 = java.lang.Float.TYPE     // Catch:{ all -> 0x029f }
            java.lang.Class<int[]> r12 = int[].class
            r8 = r7
            r9 = r7
            r10 = r7
            r11 = r7
            java.lang.Class[] r6 = new java.lang.Class[]{r6, r7, r8, r9, r10, r11, r12}     // Catch:{ all -> 0x029f }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch:{ all -> 0x029f }
            if (r0 == 0) goto L_0x02a1
            r0.setAccessible(r3)     // Catch:{ all -> 0x029f }
            goto L_0x02a7
        L_0x029f:
            r0 = move-exception
            goto L_0x02a3
        L_0x02a1:
            r0 = r4
            goto L_0x02a7
        L_0x02a3:
            me.j r0 = L2.a.l(r0)
        L_0x02a7:
            boolean r5 = r0 instanceof me.j
            if (r5 == 0) goto L_0x02ac
            r0 = r4
        L_0x02ac:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setGradientColorMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x02c4 }
            if (r0 == 0) goto L_0x02c6
            java.lang.String r5 = "setOffset"
            java.lang.Class[] r1 = new java.lang.Class[]{r1, r2}     // Catch:{ all -> 0x02c4 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r1)     // Catch:{ all -> 0x02c4 }
            if (r0 == 0) goto L_0x02c6
            r0.setAccessible(r3)     // Catch:{ all -> 0x02c4 }
            goto L_0x02cc
        L_0x02c4:
            r0 = move-exception
            goto L_0x02c8
        L_0x02c6:
            r0 = r4
            goto L_0x02cc
        L_0x02c8:
            me.j r0 = L2.a.l(r0)
        L_0x02cc:
            boolean r1 = r0 instanceof me.j
            if (r1 == 0) goto L_0x02d1
            r0 = r4
        L_0x02d1:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            r13.setOffsetMethod = r0
            java.lang.Class<?> r0 = r13.imageFilterClass     // Catch:{ all -> 0x02e9 }
            if (r0 == 0) goto L_0x02eb
            java.lang.String r1 = "setBGColorAdjustment"
            java.lang.Class[] r2 = new java.lang.Class[]{r2, r2, r2}     // Catch:{ all -> 0x02e9 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r2)     // Catch:{ all -> 0x02e9 }
            if (r0 == 0) goto L_0x02eb
            r0.setAccessible(r3)     // Catch:{ all -> 0x02e9 }
            goto L_0x02f1
        L_0x02e9:
            r0 = move-exception
            goto L_0x02ed
        L_0x02eb:
            r0 = r4
            goto L_0x02f1
        L_0x02ed:
            me.j r0 = L2.a.l(r0)
        L_0x02f1:
            boolean r1 = r0 instanceof me.j
            if (r1 == 0) goto L_0x02f6
            goto L_0x02f7
        L_0x02f6:
            r4 = r0
        L_0x02f7:
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4
            r13.setBGColorAdjustmentMethod = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.REImageFilter.<init>():void");
    }

    public final RenderEffect build(int i2, int i7) {
        RenderEffect renderEffect;
        Object obj;
        RenderEffect renderEffect2 = null;
        try {
            Method method = this.buildMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Integer.valueOf(i2), Integer.valueOf(i7)});
            } else {
                obj = null;
            }
            if (e.p(obj)) {
                renderEffect = e.e(obj);
            } else {
                renderEffect = null;
            }
        } catch (Throwable th) {
            renderEffect = a.l(th);
        }
        Throwable a7 = k.a(renderEffect);
        if (a7 == null) {
            renderEffect2 = renderEffect;
        } else {
            a7.printStackTrace();
        }
        return e.e(renderEffect2);
    }

    public final REImageFilter setBlurRadius(float f) {
        Object obj;
        try {
            Method method = this.setBlurRadiusMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setCurveLevel(float f) {
        Object obj;
        try {
            Method method = this.setCurveLevelMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setCurveMaxX(float f) {
        Object obj;
        try {
            Method method = this.setCurveMaxXMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setCurveMaxY(float f) {
        Object obj;
        try {
            Method method = this.setCurveMaxYMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setCurveMinX(float f) {
        Object obj;
        try {
            Method method = this.setCurveMinXMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setCurveMinY(float f) {
        Object obj;
        try {
            Method method = this.setCurveMinYMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setDither(boolean z) {
        Object obj;
        try {
            Method method = this.setDitherMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Boolean.valueOf(z)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }

    public final REImageFilter setGradientBlur(ImageFilterParams$GradientBlur imageFilterParams$GradientBlur) {
        Object obj;
        j.e(imageFilterParams$GradientBlur, "obj");
        try {
            float f = imageFilterParams$GradientBlur.direction;
            if (((double) f) % 90.0d == MapUtil.INVALID_LOCATION) {
                Method method = this.setGradientBlurMethod;
                if (method != null) {
                    obj = method.invoke(this.instance, new Object[]{Float.valueOf(f), Float.valueOf(imageFilterParams$GradientBlur.percent), Float.valueOf(imageFilterParams$GradientBlur.pivotPercent), Float.valueOf(imageFilterParams$GradientBlur.f1677p1), Float.valueOf(imageFilterParams$GradientBlur.f1678p2), Integer.valueOf(imageFilterParams$GradientBlur.overlayType.getNumericType())});
                } else {
                    obj = null;
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    a7.printStackTrace();
                }
                return this;
            }
            throw new Exception("Degree only support 0, 90, 180, 270");
        } catch (Throwable th) {
            obj = a.l(th);
        }
    }

    public final REImageFilter setProportionalSaturation(float f) {
        Object obj;
        try {
            Method method = this.setProportionalSaturationMethod;
            if (method != null) {
                obj = method.invoke(this.instance, new Object[]{Float.valueOf(f)});
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            a7.printStackTrace();
        }
        return this;
    }
}
