package me;

import Ae.a;
import java.io.Serializable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y implements f, Serializable {
    public a d;
    public Object e;

    public final Object getValue() {
        if (this.e == u.f4916a) {
            a aVar = this.d;
            j.b(aVar);
            this.e = aVar.invoke();
            this.d = null;
        }
        return this.e;
    }

    public final boolean isInitialized() {
        if (this.e != u.f4916a) {
            return true;
        }
        return false;
    }

    public final String toString() {
        if (isInitialized()) {
            return String.valueOf(getValue());
        }
        return "Lazy value not initialized yet.";
    }
}
