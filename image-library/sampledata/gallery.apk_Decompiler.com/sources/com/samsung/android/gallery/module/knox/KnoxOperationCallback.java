package com.samsung.android.gallery.module.knox;

import android.content.Context;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface KnoxOperationCallback {
    void dismissProgressDialog();

    void showCloudSyncConfirmDialog(int i2, int i7, Consumer<Context> consumer);

    void showProgressDialog();
}
