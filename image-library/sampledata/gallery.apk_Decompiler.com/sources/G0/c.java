package G0;

import A0.l;
import Af.p;
import Df.C0741g;
import Df.C0742h;
import Ed.a;
import F2.A;
import F2.C0040v;
import F2.G;
import F2.U;
import Ke.C0778a0;
import Ke.C0784d0;
import Ke.F;
import Ke.H;
import Ke.J;
import Ke.L;
import Ke.N;
import Ke.g0;
import Ke.v0;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0824n;
import Qe.C0831v;
import Qe.C0833x;
import Qe.K;
import Qe.Q;
import Te.B;
import Te.C0845f;
import Te.C0847h;
import Te.C0848i;
import Te.I;
import Te.u;
import Te.w;
import Te.y;
import Te.z;
import X1.b;
import android.graphics.Typeface;
import android.view.View;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import cf.d;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import df.C0954q;
import df.C0959v;
import ge.K1;
import ge.L1;
import gf.C1075f;
import h2.C0208c;
import i.C0212a;
import ig.f;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import jf.o;
import kf.C1118d;
import kf.C1119e;
import kotlin.jvm.internal.j;
import me.x;
import mg.i;
import ne.C1194l;
import o1.C0246a;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import rf.C;
import rf.C1255e;
import u2.C0286a;
import vf.C1326f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c implements C0742h, a, C0824n, OnApplyWindowInsetsListener, b, C0286a, Q, o {
    public final /* synthetic */ int d;
    public Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public static String k(String str, b bVar, boolean z) {
        String str2;
        if (z) {
            str2 = ".temp" + bVar.extension;
        } else {
            str2 = bVar.extension;
        }
        String replaceAll = str.replaceAll("\\W+", "");
        int length = 242 - str2.length();
        if (replaceAll.length() > length) {
            try {
                byte[] digest = MessageDigest.getInstance("MD5").digest(replaceAll.getBytes());
                StringBuilder sb2 = new StringBuilder();
                for (byte valueOf : digest) {
                    sb2.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
                }
                replaceAll = sb2.toString();
            } catch (NoSuchAlgorithmException unused) {
                replaceAll = replaceAll.substring(0, length);
            }
        }
        return C0212a.m("lottie_cache_", replaceAll, str2);
    }

    public C0816f A(We.o oVar) {
        We.o oVar2;
        C0954q qVar;
        p pVar;
        C0819i iVar;
        j.e(oVar, "javaClass");
        C1236c c5 = oVar.c();
        if (c5 == null || C1075f.SOURCE != null) {
            Class<?> declaringClass = oVar.f3891a.getDeclaringClass();
            if (declaringClass != null) {
                oVar2 = new We.o(declaringClass);
            } else {
                oVar2 = null;
            }
            if (oVar2 != null) {
                C0816f A10 = A(oVar2);
                if (A10 != null) {
                    pVar = A10.M();
                } else {
                    pVar = null;
                }
                if (pVar != null) {
                    iVar = pVar.c(oVar.e(), Ye.c.FROM_JAVA_LOADER);
                } else {
                    iVar = null;
                }
                if (iVar instanceof C0816f) {
                    return (C0816f) iVar;
                }
            } else if (!(c5 == null || (qVar = (C0954q) C1194l.N0(C0246a.e0(((d) this.e).c(c5.e())))) == null)) {
                C0959v vVar = qVar.n.d;
                vVar.getClass();
                return vVar.v(oVar.e(), oVar);
            }
        }
        return null;
    }

    public void B(Object obj) {
        L1.b((K1) this.e, obj);
    }

    public File C(String str, InputStream inputStream, b bVar) {
        FileOutputStream fileOutputStream;
        File file = new File(y(), k(str, bVar, true));
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    return file;
                }
            }
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }

    public Object D(C0831v vVar, Object obj) {
        x xVar = (x) obj;
        return new H((F) this.e, vVar);
    }

    public Object E(Te.Q q, Object obj) {
        return null;
    }

    public Object G(I i2, Object obj) {
        return D(i2, obj);
    }

    public void b(Typeface typeface) {
        C0208c cVar = (C0208c) this.e;
        if (cVar.o(typeface)) {
            cVar.i(false);
        }
    }

    public Object c(C0848i iVar, Object obj) {
        return D(iVar, obj);
    }

    public Object d(z zVar, Object obj) {
        return null;
    }

    public Object e(Te.H h5, Object obj) {
        int i2;
        x xVar = (x) obj;
        F f = (F) this.e;
        j.e(h5, "descriptor");
        int i7 = 0;
        if (h5.f3761x != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (h5.y != null) {
            i7 = 1;
        }
        int i8 = i2 + i7;
        if (h5.f3758j) {
            if (i8 == 0) {
                return new J(f, h5);
            }
            if (i8 == 1) {
                return new L(f, h5);
            }
            if (i8 == 2) {
                return new N(f, h5);
            }
        } else if (i8 == 0) {
            return new C0778a0(f, h5);
        } else {
            if (i8 == 1) {
                return new C0784d0(f, h5);
            }
            if (i8 == 2) {
                return new g0(f, h5);
            }
        }
        throw new v0("Unsupported property: " + h5, 0);
    }

    public Object f(y yVar, Object obj) {
        return null;
    }

    public Object g(Te.J j2, Object obj) {
        return D(j2, obj);
    }

    public Object h(w wVar, Object obj) {
        return null;
    }

    public void i(Object obj, String str) {
        ((ArrayList) this.e).add(str + "=" + obj);
    }

    public void j(C1255e eVar) {
        if (eVar.t()) {
            int size = eVar.size();
            int[] iArr = C.k;
            int binarySearch = Arrays.binarySearch(iArr, size);
            if (binarySearch < 0) {
                binarySearch = (-(binarySearch + 1)) - 1;
            }
            int i2 = iArr[binarySearch + 1];
            Stack stack = (Stack) this.e;
            if (stack.isEmpty() || ((C1255e) stack.peek()).size() >= i2) {
                stack.push(eVar);
                return;
            }
            int i7 = iArr[binarySearch];
            C1255e eVar2 = (C1255e) stack.pop();
            while (!stack.isEmpty() && ((C1255e) stack.peek()).size() < i7) {
                eVar2 = new C((C1255e) stack.pop(), eVar2);
            }
            C c5 = new C(eVar2, eVar);
            while (!stack.isEmpty()) {
                int[] iArr2 = C.k;
                int binarySearch2 = Arrays.binarySearch(iArr2, c5.e);
                if (binarySearch2 < 0) {
                    binarySearch2 = (-(binarySearch2 + 1)) - 1;
                }
                if (((C1255e) stack.peek()).size() >= iArr2[binarySearch2 + 1]) {
                    break;
                }
                c5 = new C((C1255e) stack.pop(), c5);
            }
            stack.push(c5);
        } else if (eVar instanceof C) {
            C c6 = (C) eVar;
            j(c6.f);
            j(c6.g);
        } else {
            String valueOf = String.valueOf(eVar.getClass());
            throw new IllegalArgumentException(C0212a.p(new StringBuilder(valueOf.length() + 49), "Has a new type of ByteString been created? Found ", valueOf));
        }
    }

    public Object l(u uVar, Object obj) {
        return null;
    }

    public Object m(f fVar, i iVar) {
        Object obj;
        j.e(fVar, "descriptor");
        Map map = (Map) ((ConcurrentHashMap) this.e).get(fVar);
        if (map != null) {
            obj = map.get(iVar);
        } else {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        return obj;
    }

    public jf.p o(C1240g gVar) {
        String b = gVar.b();
        if ("data".equals(b) || "filePartClassNames".equals(b)) {
            return new C1118d(this, 0);
        }
        if ("strings".equals(b)) {
            return new C1118d(this, 1);
        }
        return null;
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2;
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.e;
        collapsingToolbarLayout.getClass();
        if (ViewCompat.getFitsSystemWindows(collapsingToolbarLayout)) {
            windowInsetsCompat2 = windowInsetsCompat;
        } else {
            windowInsetsCompat2 = null;
        }
        if (!ObjectsCompat.equals(collapsingToolbarLayout.D, windowInsetsCompat2)) {
            collapsingToolbarLayout.D = windowInsetsCompat2;
            collapsingToolbarLayout.requestLayout();
        }
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    public void onResult(Object obj) {
        String str = (String) obj;
        Jd.c cVar = (Jd.c) this.e;
        cVar.f();
        cVar.e();
    }

    public o p(C1235b bVar, C1240g gVar) {
        return null;
    }

    public C0741g q(C1235b bVar) {
        C0741g q;
        j.e(bVar, "classId");
        Iterator it = C0833x.i((K) this.e, bVar.f5033a).iterator();
        while (it.hasNext()) {
            Qe.H h5 = (Qe.H) it.next();
            if ((h5 instanceof Ef.d) && (q = ((Ef.d) h5).m.q(bVar)) != null) {
                return q;
            }
        }
        return null;
    }

    public void s(C1240g gVar, Object obj) {
        String str;
        C1119e eVar = (C1119e) this.e;
        String b = gVar.b();
        if ("version".equals(b)) {
            if (obj instanceof int[]) {
                eVar.d = (int[]) obj;
            }
        } else if ("multifileClassName".equals(b)) {
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                str = null;
            }
            eVar.e = str;
        }
    }

    public File t(String str) {
        File file = new File(y(), k(str, b.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(y(), k(str, b.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        File file3 = new File(y(), k(str, b.GZIP, false));
        if (file3.exists()) {
            return file3;
        }
        return null;
    }

    public String toString() {
        switch (this.d) {
            case 11:
                return ((ArrayList) this.e).toString();
            case 14:
                StringBuilder sb2 = new StringBuilder();
                C0954q qVar = (C0954q) this.e;
                sb2.append(qVar);
                sb2.append(": ");
                sb2.append(((Map) D1.f.y(qVar.m, C0954q.q[0])).keySet());
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public void u(l lVar) {
        K1.j jVar = (K1.j) this.e;
        jVar.f383a = lVar;
        Iterator it = jVar.f384c.iterator();
        while (it.hasNext()) {
            ((C1.f) it.next()).b();
        }
        jVar.f384c.clear();
        jVar.b = null;
    }

    public Object v(C0847h hVar, Object obj) {
        return null;
    }

    public Object w(B b, Object obj) {
        return null;
    }

    public Object x(C0845f fVar, Object obj) {
        return null;
    }

    public File y() {
        File file = new File(((D9.b) this.e).e.getCacheDir(), "lottie_network_cache");
        if (file.isFile()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public void z(Object obj, Object obj2) {
        A a7 = (A) this.e;
        if (a7 == null) {
            a7 = A.a();
            this.e = a7;
        }
        F2.N n = (F2.N) a7.get(obj);
        if (n == null) {
            G g = U.e;
            C0040v.c(4, "expectedSize");
            n = new F2.N(4);
            A a10 = (A) this.e;
            if (a10 == null) {
                a10 = A.a();
                this.e = a10;
            }
            a10.put(obj, n);
        }
        n.b(obj2);
    }

    public /* synthetic */ c(int i2, boolean z) {
        this.d = i2;
    }

    public c(int i2) {
        this.d = i2;
        switch (i2) {
            case 16:
                this.e = new ConcurrentHashMap(16);
                return;
            case 17:
                this.e = new Stack();
                return;
            case 18:
                this.e = new HashSet();
                return;
            default:
                this.e = new ArrayList();
                return;
        }
    }

    public void n() {
    }

    public void I(C1240g gVar, C1326f fVar) {
    }

    public void r(C1240g gVar, C1235b bVar, C1240g gVar2) {
    }
}
