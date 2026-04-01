package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H extends C0131c implements I, RandomAccess {
    public final List e;

    static {
        new H();
    }

    public H(ArrayList arrayList) {
        super(true);
        this.e = arrayList;
    }

    public final Internal$ProtobufList a(int i2) {
        List list = this.e;
        if (i2 >= list.size()) {
            ArrayList arrayList = new ArrayList(i2);
            arrayList.addAll(list);
            return new H(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final void add(int i2, Object obj) {
        i();
        this.e.add(i2, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        return addAll(this.e.size(), collection);
    }

    public final List b() {
        return Collections.unmodifiableList(this.e);
    }

    public final I c() {
        if (this.d) {
            return new k0(this);
        }
        return this;
    }

    public final void clear() {
        i();
        this.e.clear();
        this.modCount++;
    }

    public final void f(ByteString byteString) {
        i();
        this.e.add(byteString);
        this.modCount++;
    }

    public final Object get(int i2) {
        List list = this.e;
        Object obj = list.get(i2);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String z = byteString.z();
            if (byteString.u()) {
                list.set(i2, z);
            }
            return z;
        }
        byte[] bArr = (byte[]) obj;
        String str = new String(bArr, D.f1578a);
        if (s0.f1629a.Q(0, bArr.length, bArr)) {
            list.set(i2, str);
        }
        return str;
    }

    public final Object n(int i2) {
        return this.e.get(i2);
    }

    public final Object remove(int i2) {
        i();
        Object remove = this.e.remove(i2);
        this.modCount++;
        if (remove instanceof String) {
            return (String) remove;
        }
        if (remove instanceof ByteString) {
            return ((ByteString) remove).z();
        }
        return new String((byte[]) remove, D.f1578a);
    }

    public final Object set(int i2, Object obj) {
        i();
        Object obj2 = this.e.set(i2, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 instanceof ByteString) {
            return ((ByteString) obj2).z();
        }
        return new String((byte[]) obj2, D.f1578a);
    }

    public final int size() {
        return this.e.size();
    }

    public H() {
        super(false);
        this.e = Collections.EMPTY_LIST;
    }

    public final boolean addAll(int i2, Collection collection) {
        i();
        if (collection instanceof I) {
            collection = ((I) collection).b();
        }
        boolean addAll = this.e.addAll(i2, collection);
        this.modCount++;
        return addAll;
    }

    public H(int i2) {
        this(new ArrayList(i2));
    }
}
