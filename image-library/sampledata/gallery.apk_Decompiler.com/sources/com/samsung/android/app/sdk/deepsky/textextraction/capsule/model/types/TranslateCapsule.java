package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.Prioritized;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ToggleState;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BE\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/TranslateCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/Prioritized;", "id", "", "title", "actionType", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "icon", "Landroid/net/Uri;", "listener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "currentState", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "priority", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;I)V", "getId", "()Ljava/lang/String;", "getTitle", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getIcon", "()Landroid/net/Uri;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getCurrentState", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "setCurrentState", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;)V", "getPriority", "()I", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TranslateCapsule extends ToggleCapsule implements Prioritized {
    private final CapsuleActionType actionType;
    private ToggleState currentState;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final int priority;
    private final String title;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TranslateCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, ToggleState toggleState, int i2, int i7, e eVar) {
        this((i7 & 1) != 0 ? "TRANSLATE" : str, str2, capsuleActionType, uri, unifiedCapsuleListener, (i7 & 32) != 0 ? ToggleState.Off.INSTANCE : toggleState, (i7 & 64) != 0 ? 3 : i2);
    }

    public CapsuleActionType getActionType() {
        return this.actionType;
    }

    public ToggleState getCurrentState() {
        return this.currentState;
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

    public void setCurrentState(ToggleState toggleState) {
        j.e(toggleState, "<set-?>");
        this.currentState = toggleState;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TranslateCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, ToggleState toggleState, int i2) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener, toggleState);
        j.e(str, "id");
        j.e(str2, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(uri, "icon");
        j.e(unifiedCapsuleListener, "listener");
        j.e(toggleState, "currentState");
        this.id = str;
        this.title = str2;
        this.actionType = capsuleActionType;
        this.icon = uri;
        this.listener = unifiedCapsuleListener;
        this.currentState = toggleState;
        this.priority = i2;
    }
}
