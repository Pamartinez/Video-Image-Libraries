package com.samsung.android.gallery.module.mde;

import D9.a;
import com.samsung.android.sdk.mobileservice.social.datasync.GallerySetting;
import com.samsung.android.sdk.mobileservice.social.datasync.result.DataSyncResult;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeDataSyncHelper {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestSpaceListSort$0(Consumer consumer, DataSyncResult dataSyncResult) {
        if (dataSyncResult.getStatus().getCode() != 1) {
            consumer.accept((Object) null);
        } else if (!dataSyncResult.getResponse().isEmpty()) {
            consumer.accept(((GallerySetting) dataSyncResult.getResponse().get(0).getData()).getSortType());
        }
    }

    public static void requestSpaceListSort(String str, Consumer<String> consumer) {
        MdeSharingService.getInstance().requestSpaceListSort(str, new a(0, consumer));
    }
}
