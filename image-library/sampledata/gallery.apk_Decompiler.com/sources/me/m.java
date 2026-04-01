package me;

import Ae.a;
import java.io.Serializable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements f, Serializable {
    public a d;
    public volatile Object e = u.f4916a;
    public final Object f = this;

    public m(a aVar) {
        j.e(aVar, "initializer");
        this.d = aVar;
    }

    public final Object getValue() {
        Object obj;
        Object obj2 = this.e;
        u uVar = u.f4916a;
        if (obj2 != uVar) {
            return obj2;
        }
        synchronized (this.f) {
            obj = this.e;
            if (obj == uVar) {
                a aVar = this.d;
                j.b(aVar);
                obj = aVar.invoke();
                this.e = obj;
                this.d = null;
            }
        }
        return obj;
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
