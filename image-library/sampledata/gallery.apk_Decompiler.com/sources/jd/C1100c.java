package jd;

import Ae.c;
import com.samsung.android.vexfwk.extensions.ScopedFunctionsKt;
import se.C1271c;

/* renamed from: jd.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1100c extends C1271c {
    public Object d;
    public /* synthetic */ Object e;
    public int f;

    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        this.f |= Integer.MIN_VALUE;
        return ScopedFunctionsKt.suspendAlso(null, (c) null, this);
    }
}
