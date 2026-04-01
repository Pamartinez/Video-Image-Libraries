package kotlin.jvm.internal;

import He.C0747c;
import He.C0751g;
import Ke.v0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g extends b implements f, C0751g {
    private final int arity;
    private final int flags;

    public g(int i2) {
        this(i2, b.NO_RECEIVER, (Class) null, (String) null, (String) null, 0);
    }

    public C0747c computeReflected() {
        return v.f4727a.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (!getName().equals(gVar.getName()) || !getSignature().equals(gVar.getSignature()) || this.flags != gVar.flags || this.arity != gVar.arity || !j.a(getBoundReceiver(), gVar.getBoundReceiver()) || !j.a(getOwner(), gVar.getOwner())) {
                return false;
            }
            return true;
        } else if (obj instanceof C0751g) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    public int getArity() {
        return this.arity;
    }

    public int hashCode() {
        int i2;
        if (getOwner() == null) {
            i2 = 0;
        } else {
            i2 = getOwner().hashCode() * 31;
        }
        return getSignature().hashCode() + ((getName().hashCode() + i2) * 31);
    }

    public boolean isExternal() {
        return getReflected().isExternal();
    }

    public boolean isInfix() {
        return getReflected().isInfix();
    }

    public boolean isInline() {
        return getReflected().isInline();
    }

    public boolean isOperator() {
        return getReflected().isOperator();
    }

    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    public String toString() {
        C0747c compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }

    public g(int i2, Object obj) {
        this(i2, obj, (Class) null, (String) null, (String) null, 0);
    }

    public C0751g getReflected() {
        C0747c compute = compute();
        if (compute != this) {
            return (C0751g) compute;
        }
        throw new v0(3);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(int i2, Object obj, Class cls, String str, String str2, int i7) {
        super(obj, cls, str, str2, (i7 & 1) == 1);
        this.arity = i2;
        this.flags = 0;
    }
}
