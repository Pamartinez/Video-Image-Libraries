package rf;

import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends AbstractList implements RandomAccess, w {
    public static final L e = new L(new v());
    public final ArrayList d;

    public v() {
        this.d = new ArrayList();
    }

    public final void add(int i2, Object obj) {
        this.d.add(i2, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        return addAll(this.d.size(), collection);
    }

    public final List b() {
        return Collections.unmodifiableList(this.d);
    }

    public final L c() {
        return new L(this);
    }

    public final void clear() {
        this.d.clear();
        this.modCount++;
    }

    public final Object get(int i2) {
        ArrayList arrayList = this.d;
        Object obj = arrayList.get(i2);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof C1255e) {
            C1255e eVar = (C1255e) obj;
            String A10 = eVar.A();
            if (eVar.u()) {
                arrayList.set(i2, A10);
            }
            return A10;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = t.f5070a;
        try {
            String str = new String(bArr, "UTF-8");
            if (G.c(0, bArr.length, bArr) == 0) {
                arrayList.set(i2, str);
            }
            return str;
        } catch (UnsupportedEncodingException e7) {
            throw new RuntimeException("UTF-8 not supported?", e7);
        }
    }

    public final C1255e h(int i2) {
        C1255e eVar;
        ArrayList arrayList = this.d;
        Object obj = arrayList.get(i2);
        if (obj instanceof C1255e) {
            eVar = (C1255e) obj;
        } else if (obj instanceof String) {
            try {
                eVar = new x(((String) obj).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e7) {
                throw new RuntimeException("UTF-8 not supported?", e7);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            eVar = new x(bArr2);
        }
        if (eVar != obj) {
            arrayList.set(i2, eVar);
        }
        return eVar;
    }

    public final void l(x xVar) {
        this.d.add(xVar);
        this.modCount++;
    }

    public final Object remove(int i2) {
        Object remove = this.d.remove(i2);
        this.modCount++;
        if (remove instanceof String) {
            return (String) remove;
        }
        if (remove instanceof C1255e) {
            return ((C1255e) remove).A();
        }
        byte[] bArr = (byte[]) remove;
        byte[] bArr2 = t.f5070a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e7) {
            throw new RuntimeException("UTF-8 not supported?", e7);
        }
    }

    public final Object set(int i2, Object obj) {
        Object obj2 = this.d.set(i2, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 instanceof C1255e) {
            return ((C1255e) obj2).A();
        }
        byte[] bArr = (byte[]) obj2;
        byte[] bArr2 = t.f5070a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e7) {
            throw new RuntimeException("UTF-8 not supported?", e7);
        }
    }

    public final int size() {
        return this.d.size();
    }

    public v(w wVar) {
        this.d = new ArrayList(wVar.size());
        addAll(wVar);
    }

    public final boolean addAll(int i2, Collection collection) {
        if (collection instanceof w) {
            collection = ((w) collection).b();
        }
        boolean addAll = this.d.addAll(i2, collection);
        this.modCount++;
        return addAll;
    }
}
