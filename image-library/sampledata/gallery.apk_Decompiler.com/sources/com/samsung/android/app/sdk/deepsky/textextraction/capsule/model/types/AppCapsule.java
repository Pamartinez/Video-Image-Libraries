package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import Ae.a;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.Prioritized;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BI\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00038\u0016X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010\r\u001a\u00020\f8\u0016X\u0004¢\u0006\f\n\u0004\b\r\u0010 \u001a\u0004\b!\u0010\"R\u001f\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010#\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/AppCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/Prioritized;", "", "id", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "actionType", "Landroid/net/Uri;", "icon", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "listener", "", "priority", "Lkotlin/Function0;", "Lme/x;", "action", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;ILAe/a;)V", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "Landroid/net/Uri;", "getIcon", "()Landroid/net/Uri;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "I", "getPriority", "()I", "LAe/a;", "getAction", "()LAe/a;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppCapsule extends SimpleCapsule implements Prioritized {
    private final a action;
    private final CapsuleActionType actionType;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final int priority;
    private final String title;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AppCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, int i2, a aVar, int i7, e eVar) {
        this(str, str2, capsuleActionType, uri, unifiedCapsuleListener, (i7 & 32) != 0 ? 10 : i2, aVar);
    }

    public final a getAction() {
        return this.action;
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

    public String getTitle() {
        return this.title;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, int i2, a aVar) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener);
        j.e(str, "id");
        j.e(str2, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(uri, "icon");
        j.e(unifiedCapsuleListener, "listener");
        j.e(aVar, "action");
        this.id = str;
        this.title = str2;
        this.actionType = capsuleActionType;
        this.icon = uri;
        this.listener = unifiedCapsuleListener;
        this.priority = i2;
        this.action = aVar;
    }
}
