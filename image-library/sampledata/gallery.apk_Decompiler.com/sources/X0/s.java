package x0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements u {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ w f2078a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2079c;

    public /* synthetic */ s(w wVar, int i2, int i7) {
        this.f2078a = wVar;
        this.b = i2;
        this.f2079c = i7;
    }

    public final void run() {
        w wVar = this.f2078a;
        C0332j jVar = wVar.d;
        int i2 = this.b;
        int i7 = this.f2079c;
        if (jVar == null) {
            wVar.f2095j.add(new s(wVar, i2, i7));
        } else {
            wVar.e.j((float) i2, ((float) i7) + 0.99f);
        }
    }
}
