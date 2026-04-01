package se;

import qe.C1227c;
import qe.C1232h;

/* renamed from: se.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1270b implements C1227c {
    public static final C1270b d = new Object();

    public final C1232h getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
