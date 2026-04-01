package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import android.net.Uri;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001:\u0001!J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0011H&¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0015H&¢\u0006\u0004\b\u0017\u0010\u0018J=\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u0019H'¢\u0006\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;", "listener", "Lme/x;", "setCapsuleListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;)V", "turnOffTranslate", "()V", "", "isVisible", "setCapsuleLayoutVisibility", "(Z)V", "Landroid/net/Uri;", "icon", "", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/SimpleCapsuleClickListener;", "addCapsule", "(Landroid/net/Uri;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/SimpleCapsuleClickListener;)V", "imageUri", "Lcom/google/gson/JsonObject;", "data", "addActionCapsule", "(Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "", "entityBackgroundColor", "entityTintColor", "toggleOnBackgroundColor", "toggleOnTintColor", "rippleColor", "updateCapsuleColor", "(IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "CapsuleListener", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CapsuleHelper {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "capsuleActionType", "Lme/x;", "onClick", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;)V", "onAdd", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CapsuleListener {
        void onAdd(CapsuleActionType capsuleActionType);

        void onClick(CapsuleActionType capsuleActionType);
    }

    void addActionCapsule(Uri uri, JsonObject jsonObject);

    void addCapsule(Uri uri, String str, SimpleCapsuleClickListener simpleCapsuleClickListener);

    void setCapsuleLayoutVisibility(boolean z);

    void setCapsuleListener(CapsuleListener capsuleListener);

    void turnOffTranslate();

    void updateCapsuleColor(int i2, int i7, Integer num, Integer num2, Integer num3);
}
