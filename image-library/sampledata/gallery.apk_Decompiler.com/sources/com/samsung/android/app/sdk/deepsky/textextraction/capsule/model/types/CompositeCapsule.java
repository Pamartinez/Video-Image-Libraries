package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.Prioritized;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BU\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00038\u0016X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0015\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\t\u001a\u0004\u0018\u00010\b8\u0016X\u0004¢\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0016X\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\r\u001a\u00020\f8\u0016X\u0004¢\u0006\f\n\u0004\b\r\u0010\u001f\u001a\u0004\b \u0010!R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\"\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/CompositeCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/Prioritized;", "", "id", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "actionType", "Landroid/net/Uri;", "icon", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "listener", "", "priority", "", "children", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;ILjava/util/List;)V", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "Landroid/net/Uri;", "getIcon", "()Landroid/net/Uri;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "I", "getPriority", "()I", "Ljava/util/List;", "getChildren", "()Ljava/util/List;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CompositeCapsule extends Capsule implements Prioritized {
    private final CapsuleActionType actionType;
    private final List<Capsule> children;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final int priority;
    private final String title;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CompositeCapsule(java.lang.String r2, java.lang.String r3, com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r4, android.net.Uri r5, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener r6, int r7, java.util.List r8, int r9, kotlin.jvm.internal.e r10) {
        /*
            r1 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L_0x0006
            java.lang.String r2 = "COMPOSITE"
        L_0x0006:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x000c
            java.lang.String r3 = ""
        L_0x000c:
            r10 = r9 & 4
            if (r10 == 0) goto L_0x0012
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r4 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.OTHER
        L_0x0012:
            r10 = r9 & 8
            r0 = 0
            if (r10 == 0) goto L_0x0018
            r5 = r0
        L_0x0018:
            r10 = r9 & 16
            if (r10 == 0) goto L_0x001d
            r6 = r0
        L_0x001d:
            r9 = r9 & 32
            if (r9 == 0) goto L_0x0023
            r7 = 11
        L_0x0023:
            r9 = r7
            r10 = r8
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CompositeCapsule.<init>(java.lang.String, java.lang.String, com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType, android.net.Uri, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener, int, java.util.List, int, kotlin.jvm.internal.e):void");
    }

    public CapsuleActionType getActionType() {
        return this.actionType;
    }

    public final List<Capsule> getChildren() {
        return this.children;
    }

    public String getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CompositeCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, int i2, List<? extends Capsule> list) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener);
        j.e(str, "id");
        j.e(str2, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(list, "children");
        this.id = str;
        this.title = str2;
        this.actionType = capsuleActionType;
        this.icon = uri;
        this.listener = unifiedCapsuleListener;
        this.priority = i2;
        this.children = list;
    }
}
