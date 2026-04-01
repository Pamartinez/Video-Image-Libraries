package com.samsung.android.gallery.module.cloud.scpm;

import com.samsung.android.gallery.module.cloud.scpm.GotoLink;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((GotoLink.GotoLinkData) obj2).lambda$load$3((String) obj);
                return;
            case 1:
                ((GotoLink.GotoLinkData) obj2).lambda$load$4((String) obj);
                return;
            case 2:
                ((GotoLink.GotoLinkData) obj2).lambda$load$5((String) obj);
                return;
            default:
                ((HashMap) obj2).put(((String[]) obj)[0], ((String[]) obj)[1]);
                return;
        }
    }
}
