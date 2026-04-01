package X2;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends r {
    public final double b;

    /* renamed from: c  reason: collision with root package name */
    public final double f932c;
    public final double d;
    public final String e;

    public n(double d2, double d3, double d5, String str) {
        super(s.GEO);
        this.b = d2;
        this.f932c = d3;
        this.d = d5;
        this.e = str;
    }

    public final String a() {
        StringBuilder sb2 = new StringBuilder(20);
        sb2.append(this.b);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.f932c);
        double d2 = this.d;
        if (d2 > MapUtil.INVALID_LOCATION) {
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(d2);
            sb2.append('m');
        }
        String str = this.e;
        if (str != null) {
            sb2.append(" (");
            sb2.append(str);
            sb2.append(')');
        }
        return sb2.toString();
    }
}
