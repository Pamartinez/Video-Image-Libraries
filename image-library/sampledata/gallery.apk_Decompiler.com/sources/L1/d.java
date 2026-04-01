package L1;

import Ae.c;
import He.C0748d;
import Hf.C0774x;
import L2.a;
import Le.g;
import Ne.q;
import Qe.C0816f;
import Qe.C0819i;
import Qe.V;
import Vf.C0883u;
import Vf.D;
import a.C0068a;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.view.View;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.appfunctions.schema.internal.dependencies.f0;
import com.google.android.gms.maps.model.LatLng;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sum.core.message.Message;
import i.C0212a;
import ig.b;
import ig.f;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import jf.s;
import kg.Z;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import me.h;
import me.m;
import me.u;
import pf.e;
import qe.C1227c;
import qe.C1232h;
import qe.C1233i;
import re.C1245a;
import re.C1246b;
import re.C1247c;
import se.C1269a;
import se.C1271c;
import tf.C1305i;
import w1.r;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d {
    public static a d;

    public static String A(f0 f0Var) {
        StringBuilder sb2 = new StringBuilder(f0Var.r());
        for (int i2 = 0; i2 < f0Var.r(); i2++) {
            byte p6 = f0Var.p(i2);
            if (p6 == 34) {
                sb2.append("\\\"");
            } else if (p6 == 39) {
                sb2.append("\\'");
            } else if (p6 != 92) {
                switch (p6) {
                    case 7:
                        sb2.append("\\a");
                        break;
                    case 8:
                        sb2.append("\\b");
                        break;
                    case 9:
                        sb2.append("\\t");
                        break;
                    case 10:
                        sb2.append("\\n");
                        break;
                    case 11:
                        sb2.append("\\v");
                        break;
                    case 12:
                        sb2.append("\\f");
                        break;
                    case 13:
                        sb2.append("\\r");
                        break;
                    default:
                        if (p6 >= 32 && p6 <= 126) {
                            sb2.append((char) p6);
                            break;
                        } else {
                            sb2.append('\\');
                            sb2.append((char) (((p6 >>> 6) & 3) + 48));
                            sb2.append((char) (((p6 >>> 3) & 7) + 48));
                            sb2.append((char) ((p6 & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb2.append("\\\\");
            }
        }
        return sb2.toString();
    }

    public static void B(Bundle bundle, Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            Parcelable z = z(bundle, "MapOptions");
            if (z != null) {
                D(bundle2, "MapOptions", z);
            }
            Parcelable z3 = z(bundle, "StreetViewPanoramaOptions");
            if (z3 != null) {
                D(bundle2, "StreetViewPanoramaOptions", z3);
            }
            Parcelable z7 = z(bundle, "camera");
            if (z7 != null) {
                D(bundle2, "camera", z7);
            }
            if (bundle.containsKey(Message.KEY_POSITION)) {
                bundle2.putString(Message.KEY_POSITION, bundle.getString(Message.KEY_POSITION));
            }
            if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    public static void D(Bundle bundle, String str, Parcelable parcelable) {
        ClassLoader classLoader = d.class.getClassLoader();
        r.b(classLoader);
        bundle.setClassLoader(classLoader);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(classLoader);
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    /* JADX WARNING: type inference failed for: r4v7, types: [kotlin.jvm.internal.k] */
    /* JADX WARNING: type inference failed for: r0v4, types: [se.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object a(Xf.r r4, Ae.a r5, se.C1271c r6) {
        /*
            boolean r0 = r6 instanceof Xf.p
            if (r0 == 0) goto L_0x0013
            r0 = r6
            Xf.p r0 = (Xf.p) r0
            int r1 = r0.f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f = r1
            goto L_0x0018
        L_0x0013:
            Xf.p r0 = new Xf.p
            r0.<init>(r6)
        L_0x0018:
            java.lang.Object r6 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.jvm.internal.k r4 = r0.d
            r5 = r4
            Ae.a r5 = (Ae.a) r5
            L2.a.A(r6)     // Catch:{ all -> 0x002c }
            goto L_0x006b
        L_0x002c:
            r4 = move-exception
            goto L_0x0071
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            L2.a.A(r6)
            qe.h r6 = r0.getContext()
            Vf.y r2 = Vf.C0887y.e
            qe.f r6 = r6.get(r2)
            if (r6 != r4) goto L_0x0075
            r6 = r5
            kotlin.jvm.internal.k r6 = (kotlin.jvm.internal.k) r6     // Catch:{ all -> 0x002c }
            r0.d = r6     // Catch:{ all -> 0x002c }
            r0.f = r3     // Catch:{ all -> 0x002c }
            Vf.l r6 = new Vf.l     // Catch:{ all -> 0x002c }
            qe.c r0 = m(r0)     // Catch:{ all -> 0x002c }
            r6.<init>(r3, r0)     // Catch:{ all -> 0x002c }
            r6.r()     // Catch:{ all -> 0x002c }
            Df.b r0 = new Df.b     // Catch:{ all -> 0x002c }
            r2 = 11
            r0.<init>(r2, r6)     // Catch:{ all -> 0x002c }
            Xf.q r4 = (Xf.q) r4     // Catch:{ all -> 0x002c }
            r4.Y(r0)     // Catch:{ all -> 0x002c }
            java.lang.Object r4 = r6.q()     // Catch:{ all -> 0x002c }
            if (r4 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r5.invoke()
            me.x r4 = me.x.f4917a
            return r4
        L_0x0071:
            r5.invoke()
            throw r4
        L_0x0075:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "awaitClose() can only be invoked from the producer context"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: L1.d.a(Xf.r, Ae.a, se.c):java.lang.Object");
    }

    public static void b(g gVar, Object[] objArr) {
        j.e(objArr, "args");
        if (a.o(gVar) != objArr.length) {
            StringBuilder sb2 = new StringBuilder("Callable expects ");
            sb2.append(a.o(gVar));
            sb2.append(" arguments, but ");
            throw new IllegalArgumentException(C0086a.l(sb2, objArr.length, " were provided."));
        }
    }

    public static int c(int i2, int i7) {
        boolean z;
        long j2 = ((long) i2) + ((long) i7);
        int i8 = (int) j2;
        if (j2 == ((long) i8)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return i8;
        }
        throw new ArithmeticException(A.a.d(i2, i7, "overflow: checkedAdd(", ArcCommonLog.TAG_COMMA, ")"));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: Af.p} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Af.p d(java.lang.String r3, java.util.Collection r4) {
        /*
            java.lang.String r0 = "message"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "types"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = ne.C1196n.w0(r4, r1)
            r0.<init>(r1)
            java.util.Iterator r4 = r4.iterator()
        L_0x001c:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r4.next()
            Hf.x r1 = (Hf.C0774x) r1
            Af.p r1 = r1.A()
            r0.add(r1)
            goto L_0x001c
        L_0x0030:
            Qf.f r4 = He.F.N(r0)
            int r0 = r4.d
            r1 = 1
            if (r0 == 0) goto L_0x0052
            r2 = 0
            if (r0 == r1) goto L_0x004a
            Af.a r0 = new Af.a
            Af.p[] r2 = new Af.p[r2]
            java.lang.Object[] r2 = r4.toArray(r2)
            Af.p[] r2 = (Af.p[]) r2
            r0.<init>(r3, r2)
            goto L_0x0054
        L_0x004a:
            java.lang.Object r3 = r4.get(r2)
            r0 = r3
            Af.p r0 = (Af.p) r0
            goto L_0x0054
        L_0x0052:
            Af.o r0 = Af.o.b
        L_0x0054:
            int r3 = r4.d
            if (r3 > r1) goto L_0x0059
            return r0
        L_0x0059:
            Af.l r3 = new Af.l
            r3.<init>(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: L1.d.d(java.lang.String, java.util.Collection):Af.p");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [kf.e, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ba, code lost:
        if (r2 != kf.C1116b.MULTIFILE_CLASS_PART) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00be, code lost:
        if (r0.g != null) goto L_0x00c1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dd A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Ve.b e(java.lang.Class r14) {
        /*
            java.lang.String r0 = "klass"
            kotlin.jvm.internal.j.e(r14, r0)
            kf.e r0 = new kf.e
            r0.<init>()
            r1 = 0
            r0.d = r1
            r0.e = r1
            r2 = 0
            r0.f = r2
            r0.g = r1
            r0.f4659h = r1
            r0.f4660i = r1
            r0.f4661j = r1
            r0.k = r1
            java.lang.annotation.Annotation[] r3 = r14.getDeclaredAnnotations()
            ig.i r3 = kotlin.jvm.internal.j.g(r3)
        L_0x0024:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r3.next()
            java.lang.annotation.Annotation r4 = (java.lang.annotation.Annotation) r4
            kotlin.jvm.internal.j.b(r4)
            He.d r5 = a.C0068a.w(r4)
            java.lang.Class r5 = a.C0068a.A(r5)
            qf.b r6 = We.C0892d.a(r5)
            qf.c r7 = r6.a()
            qf.c r8 = Ze.x.f3963a
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x0051
            G0.e r6 = new G0.e
            r6.<init>((java.lang.Object) r0)
            goto L_0x0080
        L_0x0051:
            qf.c r8 = Ze.x.f3968o
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0061
            B1.b r6 = new B1.b
            r7 = 15
            r6.<init>(r7, r0)
            goto L_0x0080
        L_0x0061:
            boolean r7 = kf.C1119e.l
            if (r7 == 0) goto L_0x0066
            goto L_0x007f
        L_0x0066:
            kf.b r7 = r0.f4661j
            if (r7 == 0) goto L_0x006b
            goto L_0x007f
        L_0x006b:
            java.util.HashMap r7 = kf.C1119e.m
            java.lang.Object r6 = r7.get(r6)
            kf.b r6 = (kf.C1116b) r6
            if (r6 == 0) goto L_0x007f
            r0.f4661j = r6
            G0.c r6 = new G0.c
            r7 = 15
            r6.<init>((int) r7, (java.lang.Object) r0)
            goto L_0x0080
        L_0x007f:
            r6 = r1
        L_0x0080:
            if (r6 == 0) goto L_0x0024
            Gd.a.b0(r6, r4, r5)
            goto L_0x0024
        L_0x0086:
            Ve.b r3 = new Ve.b
            pf.f r4 = pf.f.g
            kf.b r5 = r0.f4661j
            if (r5 == 0) goto L_0x00da
            int[] r5 = r0.d
            if (r5 != 0) goto L_0x0093
            goto L_0x00da
        L_0x0093:
            pf.f r8 = new pf.f
            int[] r5 = r0.d
            int r6 = r0.f
            r6 = r6 & 8
            if (r6 == 0) goto L_0x009e
            r2 = 1
        L_0x009e:
            r8.<init>(r2, r5)
            boolean r2 = r8.b(r4)
            if (r2 != 0) goto L_0x00ae
            java.lang.String[] r2 = r0.g
            r0.f4660i = r2
            r0.g = r1
            goto L_0x00c1
        L_0x00ae:
            kf.b r2 = r0.f4661j
            kf.b r4 = kf.C1116b.CLASS
            if (r2 == r4) goto L_0x00bc
            kf.b r4 = kf.C1116b.FILE_FACADE
            if (r2 == r4) goto L_0x00bc
            kf.b r4 = kf.C1116b.MULTIFILE_CLASS_PART
            if (r2 != r4) goto L_0x00c1
        L_0x00bc:
            java.lang.String[] r2 = r0.g
            if (r2 != 0) goto L_0x00c1
            goto L_0x00da
        L_0x00c1:
            java.lang.String[] r2 = r0.k
            if (r2 == 0) goto L_0x00c8
            pf.a.a(r2)
        L_0x00c8:
            ee.P r6 = new ee.P
            kf.b r7 = r0.f4661j
            java.lang.String[] r9 = r0.g
            java.lang.String[] r10 = r0.f4660i
            java.lang.String[] r11 = r0.f4659h
            java.lang.String r12 = r0.e
            int r13 = r0.f
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            goto L_0x00db
        L_0x00da:
            r6 = r1
        L_0x00db:
            if (r6 != 0) goto L_0x00de
            return r1
        L_0x00de:
            r3.<init>(r14, r6)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: L1.d.e(java.lang.Class):Ve.b");
    }

    public static C1227c f(c cVar, C1227c cVar2, C1227c cVar3) {
        if (cVar instanceof C1269a) {
            return ((C1269a) cVar).create(cVar2, cVar3);
        }
        C1232h context = cVar3.getContext();
        if (context == C1233i.d) {
            return new C1246b(cVar, cVar3, cVar2);
        }
        return new C1247c(cVar3, context, cVar, cVar2);
    }

    public static int g(int i2, int i7) {
        RoundingMode roundingMode = RoundingMode.CEILING;
        roundingMode.getClass();
        if (i7 != 0) {
            int i8 = i2 / i7;
            int i10 = i2 - (i7 * i8);
            if (i10 == 0) {
                return i8;
            }
            boolean z = true;
            int i11 = ((i2 ^ i7) >> 31) | 1;
            switch (I2.c.f348a[roundingMode.ordinal()]) {
                case 1:
                    if (i10 != 0) {
                        z = false;
                    }
                    C0068a.j(z);
                    return i8;
                case 2:
                    return i8;
                case 3:
                    if (i11 >= 0) {
                        return i8;
                    }
                    break;
                case 4:
                    break;
                case 5:
                    if (i11 <= 0) {
                        return i8;
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    int abs = Math.abs(i10);
                    int abs2 = abs - (Math.abs(i7) - abs);
                    if (abs2 == 0) {
                        RoundingMode roundingMode2 = RoundingMode.HALF_UP;
                        RoundingMode roundingMode3 = RoundingMode.HALF_EVEN;
                        return i8;
                    } else if (abs2 <= 0) {
                        return i8;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            return i8 + i11;
        }
        throw new ArithmeticException("/ by zero");
    }

    public static s h(com.samsung.context.sdk.samsunganalytics.internal.sender.c cVar) {
        if (cVar instanceof e) {
            e eVar = (e) cVar;
            String str = eVar.d;
            String str2 = eVar.e;
            j.e(str, "name");
            j.e(str2, "desc");
            return new s(str.concat(str2));
        } else if (cVar instanceof pf.d) {
            pf.d dVar = (pf.d) cVar;
            String str3 = dVar.d;
            String str4 = dVar.e;
            j.e(str3, "name");
            j.e(str4, "desc");
            return new s(str3 + '#' + str4);
        } else {
            throw new RuntimeException();
        }
    }

    public static boolean j(int i2, int[] iArr) {
        if (((1 << (i2 & 31)) & iArr[i2 >> 5]) != 0) {
            return true;
        }
        return false;
    }

    public static final C0748d k(f fVar) {
        j.e(fVar, "<this>");
        if (fVar instanceof b) {
            b bVar = (b) fVar;
            return null;
        } else if (fVar instanceof Z) {
            return k(((Z) fVar).f4686a);
        } else {
            return null;
        }
    }

    public static final int l(int i2, int i7, int i8) {
        if (i8 > 0) {
            if (i2 < i7) {
                int i10 = i7 % i8;
                if (i10 < 0) {
                    i10 += i8;
                }
                int i11 = i2 % i8;
                if (i11 < 0) {
                    i11 += i8;
                }
                int i12 = (i10 - i11) % i8;
                if (i12 < 0) {
                    i12 += i8;
                }
                return i7 - i12;
            }
        } else if (i8 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else if (i2 > i7) {
            int i13 = -i8;
            int i14 = i2 % i13;
            if (i14 < 0) {
                i14 += i13;
            }
            int i15 = i7 % i13;
            if (i15 < 0) {
                i15 += i13;
            }
            int i16 = (i14 - i15) % i13;
            if (i16 < 0) {
                i16 += i13;
            }
            return i16 + i7;
        }
        return i7;
    }

    public static C1227c m(C1227c cVar) {
        C1271c cVar2;
        C1227c intercepted;
        j.e(cVar, "<this>");
        if (cVar instanceof C1271c) {
            cVar2 = (C1271c) cVar;
        } else {
            cVar2 = null;
        }
        if (cVar2 == null || (intercepted = cVar2.intercepted()) == null) {
            return cVar;
        }
        return intercepted;
    }

    public static String n(Nf.d dVar, bf.g gVar) {
        if (!dVar.a(gVar)) {
            return dVar.getDescription();
        }
        return null;
    }

    public static boolean o(int i2) {
        int i7;
        if (i2 < -1) {
            return false;
        }
        if (i2 == -1 || (i7 = i2 & ScoverState.TYPE_NFC_SMART_COVER) == 0 || i7 == 255 || (i2 & 65280) == 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [me.f, me.l, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v6, types: [me.y, me.f, java.lang.Object] */
    public static me.f p(h hVar, Ae.a aVar) {
        u uVar = u.f4916a;
        j.e(hVar, "mode");
        int i2 = me.g.f4915a[hVar.ordinal()];
        if (i2 == 1) {
            return new m(aVar);
        }
        if (i2 == 2) {
            ? obj = new Object();
            obj.d = aVar;
            obj.e = uVar;
            return obj;
        } else if (i2 == 3) {
            ? obj2 = new Object();
            obj2.d = aVar;
            obj2.e = uVar;
            return obj2;
        } else {
            throw new RuntimeException();
        }
    }

    public static m q(Ae.a aVar) {
        j.e(aVar, "initializer");
        return new m(aVar);
    }

    public static String r(String str, Object... objArr) {
        int indexOf;
        String str2;
        String valueOf = String.valueOf(str);
        int i2 = 0;
        for (int i7 = 0; i7 < objArr.length; i7++) {
            Object obj = objArr[i7];
            if (obj == null) {
                str2 = "null";
            } else {
                try {
                    str2 = obj.toString();
                } catch (Exception e) {
                    String str3 = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str3, e);
                    StringBuilder k = N2.j.k("<", str3, " threw ");
                    k.append(e.getClass().getName());
                    k.append(">");
                    str2 = k.toString();
                }
            }
            objArr[i7] = str2;
        }
        StringBuilder sb2 = new StringBuilder((objArr.length * 16) + valueOf.length());
        int i8 = 0;
        while (i2 < objArr.length && (indexOf = valueOf.indexOf("%s", i8)) != -1) {
            sb2.append(valueOf, i8, indexOf);
            sb2.append(objArr[i2]);
            i8 = indexOf + 2;
            i2++;
        }
        sb2.append(valueOf, i8, valueOf.length());
        if (i2 < objArr.length) {
            sb2.append(" [");
            sb2.append(objArr[i2]);
            for (int i10 = i2 + 1; i10 < objArr.length; i10++) {
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(objArr[i10]);
            }
            sb2.append(']');
        }
        return sb2.toString();
    }

    public static int s(int i2) {
        boolean z;
        RoundingMode roundingMode = RoundingMode.UNNECESSARY;
        if (i2 > 0) {
            boolean z3 = true;
            switch (I2.c.f348a[roundingMode.ordinal()]) {
                case 1:
                    if (i2 > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (((i2 - 1) & i2) != 0) {
                        z3 = false;
                    }
                    C0068a.j(z & z3);
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                case 5:
                    return 32 - Integer.numberOfLeadingZeros(i2 - 1);
                case 6:
                case 7:
                case 8:
                    int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i2);
                    return (31 - numberOfLeadingZeros) + ((~(~((-1257966797 >>> numberOfLeadingZeros) - i2))) >>> 31);
                default:
                    throw new AssertionError();
            }
            return 31 - Integer.numberOfLeadingZeros(i2);
        }
        throw new IllegalArgumentException(C0212a.j(i2, "x (", ") must be > 0"));
    }

    public static K1.a t(LatLng latLng, float f) {
        try {
            a aVar = d;
            r.c(aVar, "CameraUpdateFactory is not initialized");
            Parcel c5 = aVar.c();
            H1.d.c(c5, latLng);
            c5.writeFloat(f);
            Parcel b = aVar.b(c5, 9);
            C1.a d2 = C1.b.d(b.readStrongBinder());
            b.recycle();
            return new K1.a(d2);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static final boolean u(C0774x xVar) {
        V v;
        C0819i g = xVar.s0().g();
        if (g != null && ((C1305i.b(g) && C1305i.f(g) && !C1353d.g((C0816f) g).equals(q.f3576h)) || C1305i.h(xVar))) {
            return true;
        }
        C0819i g3 = xVar.s0().g();
        if (g3 instanceof V) {
            v = (V) g3;
        } else {
            v = null;
        }
        if (v != null && u(com.samsung.context.sdk.samsunganalytics.internal.sender.c.F(v))) {
            return true;
        }
        return false;
    }

    public static final void v(Rect rect, View view) {
        view.setLeft(rect.left);
        view.setTop(rect.top);
        view.setRight(rect.right);
        view.setBottom(rect.bottom);
    }

    public static final Object w(cg.r rVar, cg.r rVar2, c cVar) {
        Object obj;
        Object I6;
        try {
            y.c(2, cVar);
            obj = cVar.invoke(rVar2, rVar);
        } catch (Throwable th) {
            obj = new C0883u(th, false);
        }
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (obj == aVar || (I6 = rVar.I(obj)) == D.e) {
            return aVar;
        }
        if (!(I6 instanceof C0883u)) {
            return D.u(I6);
        }
        throw ((C0883u) I6).f3874a;
    }

    public static Object x(Object obj, c cVar, C1227c cVar2) {
        Object obj2;
        j.e(cVar, "<this>");
        C1232h context = cVar2.getContext();
        if (context == C1233i.d) {
            obj2 = new se.g(cVar2);
        } else {
            obj2 = new C1271c(cVar2, context);
        }
        y.c(2, cVar);
        return cVar.invoke(obj, obj2);
    }

    public static void y(Parcel parcel, Parcelable parcelable) {
        if (parcelable != null) {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public static Parcelable z(Bundle bundle, String str) {
        ClassLoader classLoader = d.class.getClassLoader();
        r.b(classLoader);
        bundle.setClassLoader(classLoader);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(classLoader);
        return bundle2.getParcelable(str);
    }
}
