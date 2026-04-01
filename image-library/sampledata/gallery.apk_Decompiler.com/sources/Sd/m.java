package Sd;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends w {
    public final boolean e;

    public m(Bundle bundle) {
        super(bundle);
        String string = bundle.getString("SERVICE_ID", (String) null);
        bundle.getString("USER_ID", (String) null);
        this.e = !TextUtils.isEmpty(string);
    }
}
