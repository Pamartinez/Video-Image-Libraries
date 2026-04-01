package S2;

import U2.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a implements g, Comparable {
    /* renamed from: c */
    public final int compareTo(a aVar) {
        Class<?> cls = getClass();
        Class<?> cls2 = aVar.getClass();
        if (cls != cls2) {
            return cls.getName().compareTo(cls2.getName());
        }
        return d(aVar);
    }

    public abstract int d(a aVar);

    public abstract String e();
}
