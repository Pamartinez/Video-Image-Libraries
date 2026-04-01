package com.samsung.android.gallery.module.aiedit;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BooleanSupplier;
import k7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiEditBixbyRequest {
    public final boolean autoSave;
    public final Bitmap bitmap;
    public final BixbyKey.ErrorCallback callbackMsg;
    public final MediaItem item;
    public boolean supportAutoSave;
    public final AiEditType type;
    public final BooleanSupplier validSupplier;

    public AiEditBixbyRequest(AiEditType aiEditType, Bitmap bitmap2, MediaItem mediaItem, boolean z, BooleanSupplier booleanSupplier, BixbyKey.ErrorCallback errorCallback) {
        this.type = aiEditType;
        this.bitmap = bitmap2;
        this.item = mediaItem;
        this.autoSave = z;
        this.validSupplier = booleanSupplier;
        this.callbackMsg = errorCallback;
    }

    public void acceptMsg(String str) {
        BixbyKey.ErrorCallback errorCallback = this.callbackMsg;
        if (errorCallback != null) {
            ((d) errorCallback).f2657a.lambda$handleAiEdit$2(str);
        }
    }

    public boolean isValid() {
        BooleanSupplier booleanSupplier = this.validSupplier;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            return false;
        }
        return true;
    }

    public void setSupportAutoSave(boolean z) {
        this.supportAutoSave = z;
    }
}
