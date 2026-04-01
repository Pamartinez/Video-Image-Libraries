package com.samsung.android.gallery.module.cloud;

import Sd.B;
import Sd.e;
import Sd.y;
import Sd.z;
import com.samsung.android.gallery.module.cloud.SamsungCloud2Impl;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((SamsungCloud2Status) obj2).onAvailabilityChanged((y) obj);
                return;
            case 1:
                ((SamsungCloud2Status) obj2).onSyncStatusChanged((B) obj);
                return;
            case 2:
                ((SamsungCloud2Status) obj2).onSyncSettingChanged((z) obj);
                return;
            case 3:
                ((SamsungCloud2Status) obj2).onAlbumSyncDataChanged((e) obj);
                return;
            default:
                ((SamsungCloud2Impl.DownloadCancellerImpl) obj2).requestCancelDownload((String) obj);
                return;
        }
    }
}
