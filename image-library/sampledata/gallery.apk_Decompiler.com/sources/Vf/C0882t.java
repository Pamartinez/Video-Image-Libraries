package Vf;

import Ae.d;
import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.j;

/* renamed from: Vf.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0882t {

    /* renamed from: a  reason: collision with root package name */
    public final Object f3872a;
    public final C0872i b;

    /* renamed from: c  reason: collision with root package name */
    public final d f3873c;
    public final Object d;
    public final Throwable e;

    public C0882t(Object obj, C0872i iVar, d dVar, Object obj2, Throwable th) {
        this.f3872a = obj;
        this.b = iVar;
        this.f3873c = dVar;
        this.d = obj2;
        this.e = th;
    }

    public static C0882t a(C0882t tVar, C0872i iVar, CancellationException cancellationException, int i2) {
        Object obj = tVar.f3872a;
        if ((i2 & 2) != 0) {
            iVar = tVar.b;
        }
        C0872i iVar2 = iVar;
        d dVar = tVar.f3873c;
        Object obj2 = tVar.d;
        Throwable th = cancellationException;
        if ((i2 & 16) != 0) {
            th = tVar.e;
        }
        return new C0882t(obj, iVar2, dVar, obj2, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0882t)) {
            return false;
        }
        C0882t tVar = (C0882t) obj;
        if (j.a(this.f3872a, tVar.f3872a) && j.a(this.b, tVar.b) && j.a(this.f3873c, tVar.f3873c) && j.a(this.d, tVar.d) && j.a(this.e, tVar.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11 = 0;
        Object obj = this.f3872a;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        int i12 = i2 * 31;
        C0872i iVar = this.b;
        if (iVar == null) {
            i7 = 0;
        } else {
            i7 = iVar.hashCode();
        }
        int i13 = (i12 + i7) * 31;
        d dVar = this.f3873c;
        if (dVar == null) {
            i8 = 0;
        } else {
            i8 = dVar.hashCode();
        }
        int i14 = (i13 + i8) * 31;
        Object obj2 = this.d;
        if (obj2 == null) {
            i10 = 0;
        } else {
            i10 = obj2.hashCode();
        }
        int i15 = (i14 + i10) * 31;
        Throwable th = this.e;
        if (th != null) {
            i11 = th.hashCode();
        }
        return i15 + i11;
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.f3872a + ", cancelHandler=" + this.b + ", onCancellation=" + this.f3873c + ", idempotentResume=" + this.d + ", cancelCause=" + this.e + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0882t(Object obj, C0872i iVar, d dVar, CancellationException cancellationException, int i2) {
        this(obj, (i2 & 2) != 0 ? null : iVar, (i2 & 4) != 0 ? null : dVar, (Object) null, (Throwable) (i2 & 16) != 0 ? null : cancellationException);
    }
}
