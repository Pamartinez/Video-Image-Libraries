package X2;

import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends r {
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final String f933c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p(String str, HashMap hashMap) {
        super(s.PASSKEY);
        this.b = 1;
        this.f933c = str;
        String str2 = (String) hashMap.get(5);
    }

    public final String a() {
        switch (this.b) {
            case 0:
                return this.f933c;
            case 1:
                return this.f933c;
            case 2:
                return this.f933c;
            default:
                return this.f933c;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p(String str, int i2) {
        super(s.ISBN);
        this.b = i2;
        switch (i2) {
            case 2:
                super(s.PRODUCT);
                this.f933c = str;
                return;
            case 3:
                super(s.TEXT);
                this.f933c = str;
                return;
            default:
                this.f933c = str;
                return;
        }
    }
}
