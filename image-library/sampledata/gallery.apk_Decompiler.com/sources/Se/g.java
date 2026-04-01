package se;

import qe.C1227c;
import qe.C1232h;
import qe.C1233i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g extends C1269a {
    public g(C1227c cVar) {
        super(cVar);
        if (cVar != null && cVar.getContext() != C1233i.d) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    public C1232h getContext() {
        return C1233i.d;
    }
}
