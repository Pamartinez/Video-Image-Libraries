package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4109a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f4109a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4109a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MediaBuffer) obj2).getFormat().contains((String) obj);
            default:
                return ((String) obj).equals((String) obj2);
        }
    }
}
