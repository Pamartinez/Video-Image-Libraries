package ge;

import He.F;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r1 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f4543a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final Collection f4544c;
    public final Collection d;
    public final int e;
    public final v1 f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f4545h;

    public r1(List list, Collection collection, Collection collection2, v1 v1Var, boolean z, boolean z3, boolean z7, int i2) {
        boolean z9;
        boolean z10;
        boolean z11;
        this.b = list;
        F.n(collection, "drainedSubstreams");
        this.f4544c = collection;
        this.f = v1Var;
        this.d = collection2;
        this.g = z;
        this.f4543a = z3;
        this.f4545h = z7;
        this.e = i2;
        boolean z12 = false;
        if (!z3 || list == null) {
            z9 = true;
        } else {
            z9 = false;
        }
        F.t(z9, "passThrough should imply buffer is null");
        if (!z3 || v1Var != null) {
            z10 = true;
        } else {
            z10 = false;
        }
        F.t(z10, "passThrough should imply winningSubstream != null");
        if (!z3 || ((collection.size() == 1 && collection.contains(v1Var)) || (collection.size() == 0 && v1Var.b))) {
            z11 = true;
        } else {
            z11 = false;
        }
        F.t(z11, "passThrough should imply winningSubstream is drained");
        F.t((!z || v1Var != null) ? true : z12, "cancelled should imply committed");
    }

    public final r1 a(v1 v1Var) {
        boolean z;
        Collection unmodifiableCollection;
        F.t(!this.f4545h, "hedging frozen");
        if (this.f == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "already committed");
        Collection collection = this.d;
        if (collection == null) {
            unmodifiableCollection = Collections.singleton(v1Var);
        } else {
            ArrayList arrayList = new ArrayList(collection);
            arrayList.add(v1Var);
            unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
        }
        return new r1(this.b, this.f4544c, unmodifiableCollection, this.f, this.g, this.f4543a, this.f4545h, this.e + 1);
    }

    public final r1 b(v1 v1Var) {
        ArrayList arrayList = new ArrayList(this.d);
        arrayList.remove(v1Var);
        return new r1(this.b, this.f4544c, Collections.unmodifiableCollection(arrayList), this.f, this.g, this.f4543a, this.f4545h, this.e);
    }

    public final r1 c(v1 v1Var, v1 v1Var2) {
        ArrayList arrayList = new ArrayList(this.d);
        arrayList.remove(v1Var);
        arrayList.add(v1Var2);
        return new r1(this.b, this.f4544c, Collections.unmodifiableCollection(arrayList), this.f, this.g, this.f4543a, this.f4545h, this.e);
    }

    public final r1 d(v1 v1Var) {
        v1Var.b = true;
        Collection collection = this.f4544c;
        if (!collection.contains(v1Var)) {
            return this;
        }
        ArrayList arrayList = new ArrayList(collection);
        arrayList.remove(v1Var);
        return new r1(this.b, Collections.unmodifiableCollection(arrayList), this.d, this.f, this.g, this.f4543a, this.f4545h, this.e);
    }

    public final r1 e(v1 v1Var) {
        boolean z;
        List list;
        boolean z3 = true;
        F.t(!this.f4543a, "Already passThrough");
        boolean z7 = v1Var.b;
        Collection collection = this.f4544c;
        if (!z7) {
            if (collection.isEmpty()) {
                collection = Collections.singletonList(v1Var);
            } else {
                ArrayList arrayList = new ArrayList(collection);
                arrayList.add(v1Var);
                collection = Collections.unmodifiableCollection(arrayList);
            }
        }
        Collection collection2 = collection;
        v1 v1Var2 = this.f;
        if (v1Var2 != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (v1Var2 != v1Var) {
                z3 = false;
            }
            F.t(z3, "Another RPC attempt has already committed");
            list = null;
        } else {
            list = this.b;
        }
        return new r1(list, collection2, this.d, this.f, this.g, z, this.f4545h, this.e);
    }
}
