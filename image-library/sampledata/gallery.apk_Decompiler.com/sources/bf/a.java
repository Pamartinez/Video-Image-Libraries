package Bf;

import D0.h;
import Hf.C0774x;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import jf.o;
import jf.p;
import qf.C1235b;
import qf.C1240g;
import vf.C1326f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a implements e, h, Re.a, p {
    public final /* synthetic */ int d;
    public final Object e;

    public a(List list) {
        this.d = 1;
        this.e = list;
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 1 || i2 == 2) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 1 || i2 == 2) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1 || i2 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
        } else {
            objArr[0] = "receiverType";
        }
        if (i2 == 1) {
            objArr[1] = "getType";
        } else if (i2 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
        } else {
            objArr[1] = "getOriginal";
        }
        if (!(i2 == 1 || i2 == 2)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 1 || i2 == 2) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static /* synthetic */ void x0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 1) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 1) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        if (i2 != 1) {
            objArr[0] = "annotations";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        }
        if (i2 != 1) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        } else {
            objArr[1] = "getAnnotations";
        }
        if (i2 != 1) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 != 1) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public void A0(Object obj, boolean z) {
        Set set = (Set) this.e;
        int size = set.size();
        if (z) {
            set.add(obj);
            if (size == 0) {
                y0();
            }
        } else if (set.remove(obj) && size == 1) {
            z0();
        }
    }

    public abstract void B0(String[] strArr);

    public o O(C1235b bVar) {
        return null;
    }

    public void e0(Object obj) {
        if (obj instanceof String) {
            ((ArrayList) this.e).add((String) obj);
        }
    }

    public Re.h getAnnotations() {
        Re.h hVar = (Re.h) this.e;
        if (hVar != null) {
            return hVar;
        }
        x0(1);
        throw null;
    }

    public C0774x getType() {
        C0774x xVar = (C0774x) this.e;
        if (xVar != null) {
            return xVar;
        }
        w0(1);
        throw null;
    }

    public void n() {
        B0((String[]) ((ArrayList) this.e).toArray(new String[0]));
    }

    public List s0() {
        return (List) this.e;
    }

    public String toString() {
        switch (this.d) {
            case 1:
                StringBuilder sb2 = new StringBuilder();
                List list = (List) this.e;
                if (!list.isEmpty()) {
                    sb2.append("values=");
                    sb2.append(Arrays.toString(list.toArray()));
                }
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public boolean u0() {
        List list = (List) this.e;
        if (list.isEmpty() || (list.size() == 1 && ((K0.a) list.get(0)).c())) {
            return true;
        }
        return false;
    }

    public abstract void y0();

    public abstract void z0();

    public a(Re.h hVar) {
        this.d = 2;
        if (hVar != null) {
            this.e = hVar;
        } else {
            x0(0);
            throw null;
        }
    }

    public a(C0774x xVar) {
        this.d = 0;
        if (xVar != null) {
            this.e = xVar;
        } else {
            w0(0);
            throw null;
        }
    }

    public a(int i2) {
        this.d = i2;
        switch (i2) {
            case 4:
                this.e = new ArrayList();
                return;
            default:
                this.e = Collections.newSetFromMap(new IdentityHashMap());
                return;
        }
    }

    public void K(C1326f fVar) {
    }

    public void L(C1235b bVar, C1240g gVar) {
    }
}
