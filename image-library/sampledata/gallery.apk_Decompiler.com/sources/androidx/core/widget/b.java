package androidx.core.widget;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ NestedScrollView d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ b(NestedScrollView nestedScrollView, boolean z, boolean z3, boolean z7) {
        this.d = nestedScrollView;
        this.e = z;
        this.f = z3;
        this.g = z7;
    }

    public final void run() {
        this.d.lambda$seslSetFadingEdgeEnabled$3(this.e, this.f, this.g);
    }
}
