package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.Prioritized;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BC\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/CopyAllCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/Prioritized;", "id", "", "title", "actionType", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "icon", "Landroid/net/Uri;", "listener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "priority", "", "text", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getIcon", "()Landroid/net/Uri;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getPriority", "()I", "getText", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CopyAllCapsule extends SimpleCapsule implements Prioritized {
    private final CapsuleActionType actionType;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final int priority;
    private final String text;
    private final String title;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CopyAllCapsule(java.lang.String r1, java.lang.String r2, com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r3, android.net.Uri r4, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener r5, int r6, java.lang.String r7, int r8, kotlin.jvm.internal.e r9) {
        /*
            r0 = this;
            r9 = r8 & 1
            if (r9 == 0) goto L_0x0006
            java.lang.String r1 = "COPY_ALL"
        L_0x0006:
            r8 = r8 & 32
            if (r8 == 0) goto L_0x000b
            r6 = 2
        L_0x000b:
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r2 = r0
            r3 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CopyAllCapsule.<init>(java.lang.String, java.lang.String, com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType, android.net.Uri, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener, int, java.lang.String, int, kotlin.jvm.internal.e):void");
    }

    public CapsuleActionType getActionType() {
        return this.actionType;
    }

    public Uri getIcon() {
        return this.icon;
    }

    public String getId() {
        return this.id;
    }

    public UnifiedCapsuleListener getListener() {
        return this.listener;
    }

    public int getPriority() {
        return this.priority;
    }

    public final String getText() {
        return this.text;
    }

    public String getTitle() {
        return this.title;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CopyAllCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, int i2, String str3) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener);
        j.e(str, "id");
        j.e(str2, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(uri, "icon");
        j.e(unifiedCapsuleListener, "listener");
        j.e(str3, "text");
        this.id = str;
        this.title = str2;
        this.actionType = capsuleActionType;
        this.icon = uri;
        this.listener = unifiedCapsuleListener;
        this.priority = i2;
        this.text = str3;
    }
}
