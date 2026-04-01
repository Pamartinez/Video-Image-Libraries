package com.samsung.android.gallery.module.remotegallery;

import com.samsung.android.gallery.module.data.MediaItem;
import java.io.DataOutputStream;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ RemoteClient d;
    public final /* synthetic */ DataOutputStream e;

    public /* synthetic */ h(RemoteClient remoteClient, DataOutputStream dataOutputStream) {
        this.d = remoteClient;
        this.e = dataOutputStream;
    }

    public final void accept(Object obj) {
        this.d.lambda$uploadFiles$8(this.e, (MediaItem) obj);
    }
}
