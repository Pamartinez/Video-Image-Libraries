package ge;

import ee.C0979l;

/* renamed from: ge.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1068z implements Runnable {
    public final C0979l d;

    public C1068z(C0979l lVar) {
        this.d = lVar;
    }

    public abstract void a();

    public final void run() {
        C0979l lVar = this.d;
        C0979l a7 = lVar.a();
        try {
            a();
        } finally {
            lVar.c(a7);
        }
    }
}
