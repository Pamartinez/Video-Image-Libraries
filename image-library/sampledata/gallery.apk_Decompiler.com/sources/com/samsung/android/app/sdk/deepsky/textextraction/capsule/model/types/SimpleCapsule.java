package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0010\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\u00078\u0016X\u0004¢\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0004¢\u0006\f\n\u0004\b\n\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SingleActionCapsule;", "", "id", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "actionType", "Landroid/net/Uri;", "icon", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "listener", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)V", "Lme/x;", "click", "()V", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "Landroid/net/Uri;", "getIcon", "()Landroid/net/Uri;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimpleCapsule extends SingleActionCapsule {
    private final CapsuleActionType actionType;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final String title;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener);
        j.e(str, "id");
        j.e(str2, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(uri, "icon");
        j.e(unifiedCapsuleListener, "listener");
        this.id = str;
        this.title = str2;
        this.actionType = capsuleActionType;
        this.icon = uri;
        this.listener = unifiedCapsuleListener;
    }

    public final void click() {
        getListener().onCapsuleEvent(new CapsuleEvent.OnClick(this));
    }

    public abstract CapsuleActionType getActionType();

    public abstract Uri getIcon();

    public abstract UnifiedCapsuleListener getListener();

    public abstract String getTitle();
}
