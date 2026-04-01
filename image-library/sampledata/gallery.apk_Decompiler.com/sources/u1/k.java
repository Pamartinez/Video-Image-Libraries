package u1;

import t1.C0278c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends UnsupportedOperationException {
    public final C0278c d;

    public k(C0278c cVar) {
        this.d = cVar;
    }

    public final String getMessage() {
        return "Missing ".concat(String.valueOf(this.d));
    }
}
