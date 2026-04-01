package Sd;

import A.a;
import android.os.Bundle;
import android.util.Log;
import com.samsung.scsp.framework.core.api.Response;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class w {

    /* renamed from: a  reason: collision with root package name */
    public final int f3720a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3721c;
    public final String d;

    public w() {
        this.f3720a = 1;
        this.f3721c = "";
        this.b = 20000000;
        this.d = "";
    }

    public final boolean a() {
        if (this.f3720a == 1) {
            return true;
        }
        return false;
    }

    public w(int i2, String str) {
        this.f3720a = 2;
        this.f3721c = "";
        this.b = i2;
        this.d = str;
    }

    public w(Bundle bundle) {
        if (bundle == null) {
            this.b = 90000000;
            this.d = "result bundle is null.";
            this.f3720a = 2;
            this.f3721c = "";
        } else {
            this.b = bundle.getInt("rcode", 90000000);
            this.d = bundle.getString(Response.RMSG, "no rmsg from bundle.");
            this.f3720a = bundle.getInt("result", 2);
            this.f3721c = bundle.getString("value", "");
        }
        int i2 = this.f3720a;
        int i7 = this.b;
        String str = this.d;
        String str2 = this.f3721c;
        StringBuilder h5 = a.h(i2, i7, "result = ", ", rcode = ", ", rmsg = ");
        h5.append(str);
        h5.append(", value = ");
        h5.append(str2);
        Log.i("[SCG-SDK][0.0.0019]", h5.toString());
    }
}
