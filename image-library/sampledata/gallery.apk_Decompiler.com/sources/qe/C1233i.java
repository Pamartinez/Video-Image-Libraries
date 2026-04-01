package qe;

import Ae.c;
import java.io.Serializable;
import kotlin.jvm.internal.j;

/* renamed from: qe.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1233i implements C1232h, Serializable {
    public static final C1233i d = new Object();

    public final C1230f get(C1231g gVar) {
        j.e(gVar, "key");
        return null;
    }

    public final int hashCode() {
        return 0;
    }

    public final C1232h minusKey(C1231g gVar) {
        j.e(gVar, "key");
        return this;
    }

    public final C1232h plus(C1232h hVar) {
        j.e(hVar, "context");
        return hVar;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }

    public final Object fold(Object obj, c cVar) {
        return obj;
    }
}
