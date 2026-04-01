package ee;

import com.samsung.scsp.common.Header;

/* renamed from: ee.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0973f {
    public static final C0973f b = new C0973f(0);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4295a;

    public /* synthetic */ C0973f(int i2) {
        this.f4295a = i2;
    }

    public final String a() {
        switch (this.f4295a) {
            case 0:
                return "identity";
            default:
                return Header.GZIP;
        }
    }
}
