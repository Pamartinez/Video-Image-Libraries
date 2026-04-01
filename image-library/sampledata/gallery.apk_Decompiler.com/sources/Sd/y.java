package Sd;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends w {

    /* renamed from: j  reason: collision with root package name */
    public static final x[] f3722j = x.values();
    public final x e;
    public final boolean f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final long f3723h;

    /* renamed from: i  reason: collision with root package name */
    public final List f3724i;

    public y(Bundle bundle) {
        super(bundle);
        int i2 = bundle.getInt("SERVICE_AVAILABILITY", 0);
        x[] xVarArr = f3722j;
        if (i2 < xVarArr.length) {
            this.e = xVarArr[i2];
        } else {
            this.e = x.ServiceNotAvailable;
        }
        this.f = bundle.getBoolean("isShowTips", false);
        this.g = bundle.getBoolean("isGracePeriod", false);
        this.f3723h = bundle.getLong("oneDriveEndDate", 0);
        this.f3724i = (List) Optional.ofNullable(bundle.getStringArrayList("extras")).orElse(new ArrayList());
    }
}
