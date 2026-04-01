package F0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements A0.a {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ c f179a;

    public /* synthetic */ a(c cVar) {
        this.f179a = cVar;
    }

    public final void a() {
        boolean z;
        c cVar = this.f179a;
        if (cVar.r.l() == 1.0f) {
            z = true;
        } else {
            z = false;
        }
        if (z != cVar.f190x) {
            cVar.f190x = z;
            cVar.f188o.invalidateSelf();
        }
    }
}
