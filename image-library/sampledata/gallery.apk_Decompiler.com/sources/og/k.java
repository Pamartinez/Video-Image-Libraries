package og;

import Dd.C0731b;
import Dd.C0732c;
import Dd.C0734e;
import Fd.C0744a;
import He.C0748d;
import He.F;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.G;
import Hf.d0;
import Ke.C0779b;
import Mf.e;
import N2.j;
import Ne.i;
import Oe.g;
import Qe.A;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0826p;
import Qe.C0827q;
import Qe.C0831v;
import Qe.V;
import Re.h;
import Sf.r;
import Te.Q;
import Te.u;
import Tf.n;
import Tf.v;
import We.C0892d;
import We.C0893e;
import a.C0068a;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.PathParser;
import androidx.core.view.ViewCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.context.sdk.samsunganalytics.internal.connection.a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import com.samsung.scsp.media.file.FileApiContract;
import g2.C0197a;
import ig.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import jf.C1112l;
import jg.b;
import jg.d;
import kotlin.jvm.internal.y;
import ne.C1192j;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.x;
import o1.C0246a;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import x2.C0339f;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k implements d, b {
    public static j d = null;
    public static long e = 0;
    public static int f = -1;

    public static float A(String[] strArr, int i2) {
        float parseFloat = Float.parseFloat(strArr[i2]);
        if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    public static String B(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
        } catch (Exception e7) {
            StringBuilder k = j.k("failed to get system properties : ", str, ", error : ");
            k.append(e7.getMessage());
            C0068a.K(k.toString());
            return "";
        }
    }

    public static int D(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    public static boolean E(String str, String str2) {
        if (!str.startsWith(str2.concat("(")) || !str.endsWith(")")) {
            return false;
        }
        return true;
    }

    public static boolean F(Context context) {
        SharedPreferences E = c.E(context);
        if (C0246a.O(1, Long.valueOf(E.getLong("quota_reset_date", 0)))) {
            E.edit().putLong("quota_reset_date", System.currentTimeMillis()).putInt("data_used", 0).putInt("wifi_used", 0).apply();
        }
        return C0246a.O(E.getInt("rint", 1), Long.valueOf(E.getLong("policy_received_date", 0)));
    }

    /* JADX WARNING: type inference failed for: r8v14, types: [Qe.i] */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c1, code lost:
        if (kotlin.jvm.internal.j.a(((jf.C1111k) r6).f4646i, "java/lang/Object") != false) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0115, code lost:
        if (xf.C1353d.g(r0).equals(xf.C1353d.g(r2)) == false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0117, code lost:
        r8 = r9.getType();
        kotlin.jvm.internal.j.d(r8, "getType(...)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x012c, code lost:
        return (jf.C1113m) Gd.a.R(com.samsung.context.sdk.samsunganalytics.internal.sender.c.J(r8), jf.v.k, r1);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static jf.C1113m G(Qe.C0831v r8, Te.Q r9) {
        /*
            java.lang.String r0 = "f"
            kotlin.jvm.internal.j.e(r8, r0)
            r0 = r8
            Te.m r0 = (Te.C0852m) r0
            qf.g r0 = r0.getName()
            java.lang.String r0 = r0.b()
            java.lang.String r1 = "remove"
            boolean r0 = kotlin.jvm.internal.j.a(r0, r1)
            Qf.c r1 = Qf.c.d
            r2 = 0
            java.lang.String r3 = "getType(...)"
            java.lang.String r4 = "getValueParameters(...)"
            r5 = 1
            if (r0 == 0) goto L_0x00c4
            java.util.List r0 = r8.B()
            int r0 = r0.size()
            if (r0 != r5) goto L_0x00c4
            Qe.d r0 = xf.C1353d.k(r8)
            Qe.l r0 = r0.g()
            boolean r0 = r0 instanceof bf.C0918c
            if (r0 != 0) goto L_0x00c4
            boolean r0 = Ne.i.z(r8)
            if (r0 == 0) goto L_0x003e
            goto L_0x00c4
        L_0x003e:
            Qe.v r0 = r8.a()
            java.util.List r0 = r0.B()
            kotlin.jvm.internal.j.d(r0, r4)
            java.lang.Object r0 = ne.C1194l.b1(r0)
            Te.Q r0 = (Te.Q) r0
            Te.S r0 = (Te.S) r0
            Hf.x r0 = r0.getType()
            kotlin.jvm.internal.j.d(r0, r3)
            jf.v r6 = jf.v.k
            java.lang.Object r0 = Gd.a.R(r0, r6, r1)
            jf.m r0 = (jf.C1113m) r0
            boolean r7 = r0 instanceof jf.C1112l
            if (r7 == 0) goto L_0x0067
            jf.l r0 = (jf.C1112l) r0
            goto L_0x0068
        L_0x0067:
            r0 = r2
        L_0x0068:
            if (r0 == 0) goto L_0x006d
            yf.c r0 = r0.f4647i
            goto L_0x006e
        L_0x006d:
            r0 = r2
        L_0x006e:
            yf.c r7 = yf.C1359c.INT
            if (r0 == r7) goto L_0x0073
            goto L_0x00c4
        L_0x0073:
            Qe.v r0 = Ze.C0898e.a(r8)
            if (r0 != 0) goto L_0x007a
            goto L_0x00c4
        L_0x007a:
            Qe.v r7 = r0.a()
            java.util.List r7 = r7.B()
            kotlin.jvm.internal.j.d(r7, r4)
            java.lang.Object r7 = ne.C1194l.b1(r7)
            Te.Q r7 = (Te.Q) r7
            Te.S r7 = (Te.S) r7
            Hf.x r7 = r7.getType()
            kotlin.jvm.internal.j.d(r7, r3)
            java.lang.Object r6 = Gd.a.R(r7, r6, r1)
            jf.m r6 = (jf.C1113m) r6
            Qe.l r0 = r0.g()
            java.lang.String r7 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.j.d(r0, r7)
            qf.e r0 = xf.C1353d.h(r0)
            qf.c r7 = Ne.p.f3558K
            qf.e r7 = r7.i()
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x00c4
            boolean r0 = r6 instanceof jf.C1111k
            if (r0 == 0) goto L_0x00c4
            jf.k r6 = (jf.C1111k) r6
            java.lang.String r0 = r6.f4646i
            java.lang.String r6 = "java/lang/Object"
            boolean r0 = kotlin.jvm.internal.j.a(r0, r6)
            if (r0 == 0) goto L_0x00c4
            goto L_0x0117
        L_0x00c4:
            java.util.List r0 = r8.B()
            int r0 = r0.size()
            if (r0 == r5) goto L_0x00cf
            goto L_0x012d
        L_0x00cf:
            Qe.l r0 = r8.g()
            boolean r5 = r0 instanceof Qe.C0816f
            if (r5 == 0) goto L_0x00da
            Qe.f r0 = (Qe.C0816f) r0
            goto L_0x00db
        L_0x00da:
            r0 = r2
        L_0x00db:
            if (r0 != 0) goto L_0x00de
            goto L_0x012d
        L_0x00de:
            java.util.List r8 = r8.B()
            kotlin.jvm.internal.j.d(r8, r4)
            java.lang.Object r8 = ne.C1194l.b1(r8)
            Te.Q r8 = (Te.Q) r8
            Te.S r8 = (Te.S) r8
            Hf.x r8 = r8.getType()
            Hf.M r8 = r8.s0()
            Qe.i r8 = r8.g()
            boolean r4 = r8 instanceof Qe.C0816f
            if (r4 == 0) goto L_0x0100
            r2 = r8
            Qe.f r2 = (Qe.C0816f) r2
        L_0x0100:
            if (r2 != 0) goto L_0x0103
            goto L_0x012d
        L_0x0103:
            Ne.l r8 = Ne.i.t(r0)
            if (r8 == 0) goto L_0x012d
            qf.c r8 = xf.C1353d.g(r0)
            qf.c r0 = xf.C1353d.g(r2)
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x012d
        L_0x0117:
            Te.S r9 = (Te.S) r9
            Hf.x r8 = r9.getType()
            kotlin.jvm.internal.j.d(r8, r3)
            Hf.c0 r8 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.J(r8)
            jf.v r9 = jf.v.k
            java.lang.Object r8 = Gd.a.R(r8, r9, r1)
            jf.m r8 = (jf.C1113m) r8
            return r8
        L_0x012d:
            Te.S r9 = (Te.S) r9
            Hf.x r8 = r9.getType()
            kotlin.jvm.internal.j.d(r8, r3)
            jf.v r9 = jf.v.k
            java.lang.Object r8 = Gd.a.R(r8, r9, r1)
            jf.m r8 = (jf.C1113m) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: og.k.G(Qe.v, Te.Q):jf.m");
    }

    public static void H(j jVar) {
        if (jVar.f != null || jVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!jVar.d) {
            synchronized (k.class) {
                try {
                    long j2 = e + 8192;
                    if (j2 <= 65536) {
                        e = j2;
                        jVar.f = d;
                        jVar.f5017c = 0;
                        jVar.b = 0;
                        d = jVar;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static final C0774x I(C0774x xVar, ArrayList arrayList) {
        G g;
        d0 d0Var;
        xVar.e0().size();
        arrayList.size();
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            eVar.getClass();
            C0774x xVar2 = eVar.f3538c;
            C0774x xVar3 = eVar.b;
            V v = eVar.f3537a;
            If.d.f3459a.b(xVar3, xVar2);
            if (kotlin.jvm.internal.j.a(xVar3, xVar2) || v.t() == (d0Var = d0.IN_VARIANCE)) {
                g = new G(xVar3);
            } else if (i.E(xVar3) && v.t() != d0Var) {
                d0 d0Var2 = d0.OUT_VARIANCE;
                if (d0Var2 == v.t()) {
                    d0Var2 = d0.INVARIANT;
                }
                g = new G(xVar2, d0Var2);
            } else if (xVar2 == null) {
                i.a(141);
                throw null;
            } else if (!i.x(xVar2) || !xVar2.u0()) {
                d0 d0Var3 = d0.OUT_VARIANCE;
                if (d0Var3 == v.t()) {
                    d0Var3 = d0.INVARIANT;
                }
                g = new G(xVar2, d0Var3);
            } else {
                if (d0Var == v.t()) {
                    d0Var = d0.INVARIANT;
                }
                g = new G(xVar3, d0Var);
            }
            arrayList2.add(g);
        }
        return C0754c.q(xVar, arrayList2, (h) null, 6);
    }

    public static TypedValue J(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean K(Context context, int i2, boolean z) {
        TypedValue J4 = J(context, i2);
        if (J4 == null || J4.type != 18) {
            return z;
        }
        if (J4.data != 0) {
            return true;
        }
        return false;
    }

    public static int L(Context context, int i2, int i7) {
        TypedValue J4 = J(context, i2);
        if (J4 == null || J4.type != 16) {
            return i7;
        }
        return J4.data;
    }

    public static TimeInterpolator M(Context context, int i2, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type == 3) {
            String valueOf = String.valueOf(typedValue.string);
            if (!E(valueOf, "cubic-bezier") && !E(valueOf, FileApiContract.Parameter.PATH)) {
                return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
            }
            if (E(valueOf, "cubic-bezier")) {
                String[] split = valueOf.substring(13, valueOf.length() - 1).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (split.length == 4) {
                    return PathInterpolatorCompat.create(A(split, 0), A(split, 1), A(split, 2), A(split, 3));
                }
                throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
            } else if (E(valueOf, FileApiContract.Parameter.PATH)) {
                return PathInterpolatorCompat.create(PathParser.createPathFromPathData(valueOf.substring(5, valueOf.length() - 1)));
            } else {
                throw new IllegalArgumentException("Invalid motion easing type: ".concat(valueOf));
            }
        } else {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
    }

    public static TypedValue N(Context context, int i2, String str) {
        TypedValue J4 = J(context, i2);
        if (J4 != null) {
            return J4;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", new Object[]{str, context.getResources().getResourceName(i2)}));
    }

    public static void O(ViewGroup viewGroup, float f5) {
        Drawable background = viewGroup.getBackground();
        if (background instanceof C0340g) {
            ((C0340g) background).j(f5);
        }
    }

    public static void P(View view, C0340g gVar) {
        C0197a aVar = gVar.d.b;
        if (aVar != null && aVar.f1730a) {
            float f5 = 0.0f;
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                f5 += ViewCompat.getElevation((View) parent);
            }
            C0339f fVar = gVar.d;
            if (fVar.l != f5) {
                fVar.l = f5;
                gVar.o();
            }
        }
    }

    public static void Q(ViewGroup viewGroup) {
        Drawable background = viewGroup.getBackground();
        if (background instanceof C0340g) {
            P(viewGroup, (C0340g) background);
        }
    }

    public static j R() {
        synchronized (k.class) {
            try {
                j jVar = d;
                if (jVar == null) {
                    return new j();
                }
                d = jVar.f;
                jVar.f = null;
                e -= 8192;
                return jVar;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static String S(String str) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt < 'A' || charAt > 'Z') {
                i2++;
            } else {
                char[] charArray = str.toCharArray();
                while (i2 < length) {
                    char c5 = charArray[i2];
                    if (c5 >= 'A' && c5 <= 'Z') {
                        charArray[i2] = (char) (c5 ^ ' ');
                    }
                    i2++;
                }
                return String.valueOf(charArray);
            }
        }
        return str;
    }

    public static String T(String str) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt < 'a' || charAt > 'z') {
                i2++;
            } else {
                char[] charArray = str.toCharArray();
                while (i2 < length) {
                    char c5 = charArray[i2];
                    if (c5 >= 'a' && c5 <= 'z') {
                        charArray[i2] = (char) (c5 ^ ' ');
                    }
                    i2++;
                }
                return String.valueOf(charArray);
            }
        }
        return str;
    }

    public static C1235b U(C1236c cVar) {
        kotlin.jvm.internal.j.e(cVar, "topLevelFqName");
        C1236c e7 = cVar.e();
        C1240g f5 = cVar.f();
        kotlin.jvm.internal.j.d(f5, "shortName(...)");
        return new C1235b(e7, f5);
    }

    public static void V(Context context, C0732c cVar, t1.i iVar, C0744a aVar, C0734e eVar) {
        C0068a.e("Build policy client, trid: " + cVar.f3331a.substring(0, 7) + ", uv: " + cVar.f3332c);
        SharedPreferences E = c.E(context);
        a aVar2 = a.GET_POLICY;
        HashMap hashMap = new HashMap();
        hashMap.put("pkn", context.getPackageName());
        hashMap.put("dm", (String) aVar.f);
        if (!TextUtils.isEmpty((String) aVar.f3375h)) {
            hashMap.put(HeaderSetup.Key.MCC, (String) aVar.f3375h);
        }
        if (!TextUtils.isEmpty((String) aVar.f3376i)) {
            hashMap.put(HeaderSetup.Key.MNC, (String) aVar.f3376i);
        }
        hashMap.put("uv", cVar.f3332c);
        hashMap.put("sv", C0731b.b);
        hashMap.put("tid", cVar.f3331a);
        String valueOf = String.valueOf(System.currentTimeMillis());
        hashMap.put("ts", valueOf);
        hashMap.put("hc", L2.a.y(cVar.f3331a + valueOf + Od.a.f3608a));
        String B = B("ro.csc.sales_code");
        if (!TextUtils.isEmpty(B)) {
            hashMap.put(IdentityApiContract.Parameter.CSC, B);
        }
        String B9 = B("ro.csc.countryiso_code");
        if (!TextUtils.isEmpty(B9)) {
            hashMap.put("cc", B9);
        }
        B0.a aVar3 = new B0.a(aVar2, hashMap, E, (Ed.a) eVar);
        iVar.getClass();
        t1.i.d(aVar3);
    }

    public static void W(Context context, int i2, int i7) {
        SharedPreferences E = c.E(context);
        if (i2 == 1) {
            E.edit().putInt("wifi_used", E.getInt("wifi_used", 0) + i7).apply();
        } else if (i2 == 0) {
            E.edit().putInt("data_used", c.E(context).getInt("data_used", 0) + i7).apply();
        }
    }

    public static final String f(Method method) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(method.getName());
        Class[] parameterTypes = method.getParameterTypes();
        kotlin.jvm.internal.j.d(parameterTypes, "getParameterTypes(...)");
        sb2.append(C1192j.s0(parameterTypes, "", "(", ")", C0779b.m, 24));
        Class<?> returnType = method.getReturnType();
        kotlin.jvm.internal.j.d(returnType, "getReturnType(...)");
        sb2.append(C0892d.b(returnType));
        return sb2.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0215  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final Mf.a g(Hf.C0774x r12) {
        /*
            java.lang.String r0 = "type"
            kotlin.jvm.internal.j.e(r12, r0)
            boolean r0 = Hf.C0754c.l(r12)
            if (r0 == 0) goto L_0x0051
            Hf.B r0 = Hf.C0754c.m(r12)
            Mf.a r0 = g(r0)
            Hf.B r1 = Hf.C0754c.E(r12)
            Mf.a r1 = g(r1)
            Mf.a r2 = new Mf.a
            java.lang.Object r3 = r0.f3535a
            Hf.x r3 = (Hf.C0774x) r3
            Hf.B r3 = Hf.C0754c.m(r3)
            java.lang.Object r4 = r1.f3535a
            Hf.x r4 = (Hf.C0774x) r4
            Hf.B r4 = Hf.C0754c.E(r4)
            Hf.c0 r3 = Hf.C0754c.f(r3, r4)
            Hf.c0 r3 = Hf.C0754c.i(r3, r12)
            java.lang.Object r0 = r0.b
            Hf.x r0 = (Hf.C0774x) r0
            Hf.B r0 = Hf.C0754c.m(r0)
            java.lang.Object r1 = r1.b
            Hf.x r1 = (Hf.C0774x) r1
            Hf.B r1 = Hf.C0754c.E(r1)
            Hf.c0 r0 = Hf.C0754c.f(r0, r1)
            Hf.c0 r12 = Hf.C0754c.i(r0, r12)
            r2.<init>(r3, r12)
            return r2
        L_0x0051:
            Hf.M r0 = r12.s0()
            Hf.M r1 = r12.s0()
            boolean r1 = r1 instanceof uf.C1317b
            r2 = 3
            r3 = 2
            java.lang.String r4 = "getType(...)"
            if (r1 == 0) goto L_0x00c3
            java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor"
            kotlin.jvm.internal.j.c(r0, r1)
            uf.b r0 = (uf.C1317b) r0
            Hf.P r0 = r0.a()
            Hf.x r1 = r0.b()
            kotlin.jvm.internal.j.d(r1, r4)
            boolean r4 = r12.u0()
            Hf.x r1 = Hf.a0.h(r1, r4)
            Hf.d0 r4 = r0.a()
            int[] r5 = Mf.c.f3536a
            int r4 = r4.ordinal()
            r4 = r5[r4]
            if (r4 == r3) goto L_0x00b5
            if (r4 != r2) goto L_0x00a1
            Mf.a r0 = new Mf.a
            Ne.i r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.z(r12)
            Hf.B r2 = r2.n()
            boolean r12 = r12.u0()
            Hf.x r12 = Hf.a0.h(r2, r12)
            r0.<init>(r12, r1)
            return r0
        L_0x00a1:
            java.lang.AssertionError r12 = new java.lang.AssertionError
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Only nontrivial projections should have been captured, not: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r12.<init>(r0)
            throw r12
        L_0x00b5:
            Mf.a r0 = new Mf.a
            Ne.i r12 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.z(r12)
            Hf.B r12 = r12.o()
            r0.<init>(r1, r12)
            return r0
        L_0x00c3:
            java.util.List r1 = r12.e0()
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0221
            java.util.List r1 = r12.e0()
            int r1 = r1.size()
            java.util.List r5 = r0.getParameters()
            int r5 = r5.size()
            if (r1 == r5) goto L_0x00e1
            goto L_0x0221
        L_0x00e1:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.List r6 = r12.e0()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.List r0 = r0.getParameters()
            java.lang.String r7 = "getParameters(...)"
            kotlin.jvm.internal.j.d(r0, r7)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r0 = ne.C1194l.r1(r6, r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x0104:
            boolean r6 = r0.hasNext()
            r7 = 1
            if (r6 == 0) goto L_0x01e0
            java.lang.Object r6 = r0.next()
            me.i r6 = (me.i) r6
            java.lang.Object r8 = r6.d
            Hf.P r8 = (Hf.P) r8
            java.lang.Object r6 = r6.e
            Qe.V r6 = (Qe.V) r6
            kotlin.jvm.internal.j.b(r6)
            Hf.d0 r9 = r6.t()
            r10 = 0
            if (r9 == 0) goto L_0x01da
            if (r8 == 0) goto L_0x01d4
            Hf.X r11 = Hf.X.b
            boolean r11 = r8.c()
            if (r11 == 0) goto L_0x0138
            Hf.d0 r9 = Hf.d0.OUT_VARIANCE
            if (r9 == 0) goto L_0x0132
            goto L_0x0140
        L_0x0132:
            r12 = 37
            Hf.X.a(r12)
            throw r10
        L_0x0138:
            Hf.d0 r10 = r8.a()
            Hf.d0 r9 = Hf.X.b(r9, r10)
        L_0x0140:
            int[] r10 = Mf.c.f3536a
            int r9 = r9.ordinal()
            r9 = r10[r9]
            if (r9 == r7) goto L_0x0183
            if (r9 == r3) goto L_0x0169
            if (r9 != r2) goto L_0x0163
            Mf.e r7 = new Mf.e
            Ne.i r9 = xf.C1353d.e(r6)
            Hf.B r9 = r9.n()
            Hf.x r10 = r8.b()
            kotlin.jvm.internal.j.d(r10, r4)
            r7.<init>(r6, r9, r10)
            goto L_0x0196
        L_0x0163:
            Dd.a r12 = new Dd.a
            r12.<init>()
            throw r12
        L_0x0169:
            Mf.e r7 = new Mf.e
            Hf.x r9 = r8.b()
            kotlin.jvm.internal.j.d(r9, r4)
            Ne.i r10 = xf.C1353d.e(r6)
            Hf.B r10 = r10.o()
            java.lang.String r11 = "getNullableAnyType(...)"
            kotlin.jvm.internal.j.d(r10, r11)
            r7.<init>(r6, r9, r10)
            goto L_0x0196
        L_0x0183:
            Mf.e r7 = new Mf.e
            Hf.x r9 = r8.b()
            kotlin.jvm.internal.j.d(r9, r4)
            Hf.x r10 = r8.b()
            kotlin.jvm.internal.j.d(r10, r4)
            r7.<init>(r6, r9, r10)
        L_0x0196:
            boolean r6 = r8.c()
            if (r6 == 0) goto L_0x01a4
            r1.add(r7)
            r5.add(r7)
            goto L_0x0104
        L_0x01a4:
            Hf.x r6 = r7.b
            Mf.a r6 = g(r6)
            java.lang.Object r8 = r6.f3535a
            Hf.x r8 = (Hf.C0774x) r8
            java.lang.Object r6 = r6.b
            Hf.x r6 = (Hf.C0774x) r6
            Hf.x r9 = r7.f3538c
            Mf.a r9 = g(r9)
            java.lang.Object r10 = r9.f3535a
            Hf.x r10 = (Hf.C0774x) r10
            java.lang.Object r9 = r9.b
            Hf.x r9 = (Hf.C0774x) r9
            Mf.e r11 = new Mf.e
            Qe.V r7 = r7.f3537a
            r11.<init>(r7, r6, r10)
            Mf.e r6 = new Mf.e
            r6.<init>(r7, r8, r9)
            r1.add(r11)
            r5.add(r6)
            goto L_0x0104
        L_0x01d4:
            r12 = 36
            Hf.X.a(r12)
            throw r10
        L_0x01da:
            r12 = 35
            Hf.X.a(r12)
            throw r10
        L_0x01e0:
            boolean r0 = r1.isEmpty()
            r2 = 0
            if (r0 == 0) goto L_0x01e9
        L_0x01e7:
            r7 = r2
            goto L_0x0208
        L_0x01e9:
            java.util.Iterator r0 = r1.iterator()
        L_0x01ed:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x01e7
            java.lang.Object r3 = r0.next()
            Mf.e r3 = (Mf.e) r3
            r3.getClass()
            If.l r4 = If.d.f3459a
            Hf.x r6 = r3.b
            Hf.x r3 = r3.f3538c
            boolean r3 = r4.b(r6, r3)
            if (r3 != 0) goto L_0x01ed
        L_0x0208:
            Mf.a r0 = new Mf.a
            if (r7 == 0) goto L_0x0215
            Ne.i r1 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.z(r12)
            Hf.B r1 = r1.n()
            goto L_0x0219
        L_0x0215:
            Hf.x r1 = I(r12, r1)
        L_0x0219:
            Hf.x r12 = I(r12, r5)
            r0.<init>(r1, r12)
            return r0
        L_0x0221:
            Mf.a r0 = new Mf.a
            r0.<init>(r12, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: og.k.g(Hf.x):Mf.a");
    }

    public static long[] h(long[]... jArr) {
        boolean z;
        long j2 = 0;
        for (long[] length : jArr) {
            j2 += (long) length.length;
        }
        int i2 = (int) j2;
        if (j2 == ((long) i2)) {
            z = true;
        } else {
            z = false;
        }
        F.k(z, "the total number of elements (%s) in the arrays must fit in an int", j2);
        long[] jArr2 = new long[i2];
        int i7 = 0;
        for (long[] jArr3 : jArr) {
            System.arraycopy(jArr3, 0, jArr2, i7, jArr3.length);
            i7 += jArr3.length;
        }
        return jArr2;
    }

    public static String j(String str, Object[] objArr) {
        if (str == null) {
            return "null";
        }
        if (objArr.length == 0) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(100);
        sb2.append(str);
        for (float[] fArr : objArr) {
            sb2.append(" : ");
            if (fArr instanceof float[]) {
                sb2.append(Arrays.toString(fArr));
            } else {
                sb2.append(fArr);
            }
        }
        return sb2.toString();
    }

    public static g k(Oe.c cVar, boolean z) {
        String lowerCase;
        kotlin.jvm.internal.j.e(cVar, "functionClass");
        List list = cVar.n;
        g gVar = new g(cVar, (g) null, C0813c.DECLARATION, z);
        u v02 = cVar.v0();
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (((V) next).t() != d0.IN_VARIANCE) {
                break;
            }
            arrayList.add(next);
        }
        r q12 = C1194l.q1(arrayList);
        ArrayList arrayList2 = new ArrayList(C1196n.w0(q12, 10));
        Iterator it = q12.iterator();
        while (true) {
            Sf.b bVar = (Sf.b) it;
            if (bVar.e.hasNext()) {
                x xVar = (x) bVar.next();
                int i2 = xVar.f4950a;
                V v = (V) xVar.b;
                String b = v.getName().b();
                kotlin.jvm.internal.j.d(b, "asString(...)");
                if (b.equals("T")) {
                    lowerCase = "instance";
                } else if (b.equals("E")) {
                    lowerCase = "receiver";
                } else {
                    lowerCase = b.toLowerCase(Locale.ROOT);
                    kotlin.jvm.internal.j.d(lowerCase, "toLowerCase(...)");
                }
                V v6 = v;
                g gVar2 = gVar;
                C1240g e7 = C1240g.e(lowerCase);
                B i7 = v6.i();
                kotlin.jvm.internal.j.d(i7, "getDefaultType(...)");
                arrayList2.add(new Q(gVar2, (Q) null, i2, Re.g.f3692a, e7, i7, false, false, false, (C0774x) null, Qe.Q.f3662a));
                gVar = gVar2;
            } else {
                g gVar3 = gVar;
                B i8 = ((V) C1194l.T0(list)).i();
                A a7 = A.ABSTRACT;
                C0826p pVar = C0827q.e;
                C1202t tVar = C1202t.d;
                gVar.J0((u) null, v02, tVar, tVar, arrayList2, i8, a7, pVar);
                g gVar4 = gVar;
                gVar4.f3795A = true;
                return gVar4;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v2, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v3, types: [o1.a, java.lang.Object] */
    public static C0246a l(int i2) {
        if (i2 == 0) {
            return new Object();
        }
        if (i2 != 1) {
            return new Object();
        }
        return new Object();
    }

    public static void m(String str, String str2, Object... objArr) {
        Log.d(str, j(str2, objArr));
    }

    public static boolean n(C0812b bVar, C0812b bVar2) {
        kotlin.jvm.internal.j.e(bVar, "superDescriptor");
        kotlin.jvm.internal.j.e(bVar2, "subDescriptor");
        if (!(bVar2 instanceof bf.g) || !(bVar instanceof C0831v)) {
            return false;
        }
        bf.g gVar = (bf.g) bVar2;
        gVar.B().size();
        C0831v vVar = (C0831v) bVar;
        vVar.B().size();
        List B = gVar.a().B();
        kotlin.jvm.internal.j.d(B, "getValueParameters(...)");
        List B9 = vVar.a().B();
        kotlin.jvm.internal.j.d(B9, "getValueParameters(...)");
        Iterator it = C1194l.r1(B, B9).iterator();
        while (it.hasNext()) {
            me.i iVar = (me.i) it.next();
            Q q = (Q) iVar.d;
            Q q10 = (Q) iVar.e;
            kotlin.jvm.internal.j.b(q);
            boolean z = G((C0831v) bVar2, q) instanceof C1112l;
            kotlin.jvm.internal.j.b(q10);
            if (z != (G(vVar, q10) instanceof C1112l)) {
                return true;
            }
        }
        return false;
    }

    public static boolean v(String str, String str2) {
        char c5;
        int length = str.length();
        if (str == str2) {
            return true;
        }
        if (length == str2.length()) {
            int i2 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                char charAt2 = str2.charAt(i2);
                if (charAt == charAt2 || ((c5 = (char) ((charAt | ' ') - 'a')) < 26 && c5 == ((char) ((charAt2 | ' ') - 'a')))) {
                    i2++;
                }
            }
            return true;
        }
        return false;
    }

    public static final C0893e w(Annotation[] annotationArr, C1236c cVar) {
        Annotation annotation;
        kotlin.jvm.internal.j.e(annotationArr, "<this>");
        kotlin.jvm.internal.j.e(cVar, "fqName");
        int length = annotationArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                annotation = null;
                break;
            }
            annotation = annotationArr[i2];
            if (kotlin.jvm.internal.j.a(C0892d.a(C0068a.A(C0068a.w(annotation))).a(), cVar)) {
                break;
            }
            i2++;
        }
        if (annotation != null) {
            return new C0893e(annotation);
        }
        return null;
    }

    public static final gg.a x(gg.e eVar, jg.a aVar, String str) {
        gg.a aVar2;
        Ae.b bVar;
        kotlin.jvm.internal.j.e(eVar, "<this>");
        kotlin.jvm.internal.j.e(aVar, "decoder");
        eVar.getClass();
        kotlin.jvm.internal.j.e(aVar, "decoder");
        gg.a aVar3 = (gg.a) eVar.d.get(str);
        if (aVar3 == null) {
            kotlin.jvm.internal.j.e(aVar, "decoder");
            B0.a y = aVar.y();
            C0748d a7 = eVar.a();
            y.getClass();
            kotlin.jvm.internal.j.e(a7, "baseClass");
            Map map = (Map) ((Map) y.g).get(a7);
            if (map != null) {
                aVar2 = (gg.a) map.get(str);
            } else {
                aVar2 = null;
            }
            if (aVar2 == null) {
                aVar2 = null;
            }
            if (aVar2 != null) {
                aVar3 = aVar2;
            } else {
                Object obj = ((Map) y.f34h).get(a7);
                if (y.d(1, obj)) {
                    bVar = (Ae.b) obj;
                } else {
                    bVar = null;
                }
                if (bVar != null) {
                    aVar3 = (gg.a) bVar.invoke(str);
                } else {
                    aVar3 = null;
                }
            }
        }
        if (aVar3 != null) {
            return aVar3;
        }
        kg.Q.g(str, eVar.a());
        throw null;
    }

    public static C1235b y(String str, boolean z) {
        String str2;
        kotlin.jvm.internal.j.e(str, "string");
        int A02 = n.A0(str, '`', 0, 6);
        if (A02 == -1) {
            A02 = str.length();
        }
        int E02 = n.E0(A02, 4, str, "/");
        String str3 = "";
        if (E02 == -1) {
            str2 = v.s0(str, "`", str3);
        } else {
            String substring = str.substring(0, E02);
            kotlin.jvm.internal.j.d(substring, "substring(...)");
            String r0 = v.r0(substring, '/', '.');
            String substring2 = str.substring(E02 + 1);
            kotlin.jvm.internal.j.d(substring2, "substring(...)");
            str2 = v.s0(substring2, "`", str3);
            str3 = r0;
        }
        return new C1235b(new C1236c(str3), new C1236c(str2), z);
    }

    public static final ArrayList z(Annotation[] annotationArr) {
        kotlin.jvm.internal.j.e(annotationArr, "<this>");
        ArrayList arrayList = new ArrayList(annotationArr.length);
        for (Annotation eVar : annotationArr) {
            arrayList.add(new C0893e(eVar));
        }
        return arrayList;
    }

    public abstract void X(int i2, int i7, byte[] bArr);

    public abstract void o(f fVar, int i2, boolean z);

    public abstract void p(double d2);

    public abstract void q(f fVar, int i2);

    public abstract void r(int i2, int i7, f fVar);

    public abstract void s(f fVar, int i2, long j2);

    public abstract void t(f fVar, int i2, gg.a aVar, Object obj);

    public abstract void u(f fVar, int i2, String str);
}
