package Qe;

import c0.C0086a;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3655a;
    public String b;

    public /* synthetic */ B() {
        this.f3655a = 1;
    }

    public void a(String str) {
        this.b = C0212a.m("[605082][", str, "] ");
    }

    public String toString() {
        switch (this.f3655a) {
            case 0:
                return this.b;
            case 2:
                return C0086a.m(new StringBuilder("<"), this.b, '>');
            default:
                return super.toString();
        }
    }

    public /* synthetic */ B(String str, int i2) {
        this.f3655a = i2;
        this.b = str;
    }
}
