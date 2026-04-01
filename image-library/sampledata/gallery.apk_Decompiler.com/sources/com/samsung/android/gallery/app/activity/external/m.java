package com.samsung.android.gallery.app.activity.external;

import android.content.Context;
import android.widget.Toast;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Consumer {
    public final /* synthetic */ String d;

    public /* synthetic */ m(String str) {
        this.d = str;
    }

    public final void accept(Object obj) {
        Toast.makeText((Context) obj, this.d, 1).show();
    }
}
