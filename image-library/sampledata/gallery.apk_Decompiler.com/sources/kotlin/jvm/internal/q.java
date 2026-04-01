package kotlin.jvm.internal;

import He.C0747c;
import He.t;
import Ke.v0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q extends b implements t {
    private final boolean syntheticJavaProperty;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        boolean z = false;
        this.syntheticJavaProperty = (i2 & 2) == 2 ? true : z;
    }

    public C0747c compute() {
        if (this.syntheticJavaProperty) {
            return this;
        }
        return super.compute();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof q) {
            q qVar = (q) obj;
            if (!getOwner().equals(qVar.getOwner()) || !getName().equals(qVar.getName()) || !getSignature().equals(qVar.getSignature()) || !j.a(getBoundReceiver(), qVar.getBoundReceiver())) {
                return false;
            }
            return true;
        } else if (obj instanceof t) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    public int hashCode() {
        int hashCode = getName().hashCode();
        return getSignature().hashCode() + ((hashCode + (getOwner().hashCode() * 31)) * 31);
    }

    public String toString() {
        C0747c compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }

    public t getReflected() {
        if (!this.syntheticJavaProperty) {
            C0747c compute = compute();
            if (compute != this) {
                return (t) compute;
            }
            throw new v0(3);
        }
        throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
    }

    public q() {
        super(b.NO_RECEIVER, (Class) null, (String) null, (String) null, false);
        this.syntheticJavaProperty = false;
    }
}
