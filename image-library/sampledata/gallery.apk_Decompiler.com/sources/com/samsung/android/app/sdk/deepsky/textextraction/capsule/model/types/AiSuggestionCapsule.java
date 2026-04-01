package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.Prioritized;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002Bk\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012&\u0010\u0012\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00100\u000f0\u000e\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u00038\u0016X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\t\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0004¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b\"\u0010#R\"\u0010\r\u001a\u00020\f8\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\b\r\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R:\u0010\u0012\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00100\u000f0\u000e8\u0016X\u0004¢\u0006\f\n\u0004\b\u0012\u0010)\u001a\u0004\b*\u0010+R\u001a\u0010\u0014\u001a\u00020\u00138\u0016X\u0004¢\u0006\f\n\u0004\b\u0014\u0010,\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/AiSuggestionCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/Prioritized;", "", "id", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "actionType", "Landroid/net/Uri;", "icon", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "listener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "currentState", "", "Lme/i;", "Lkotlin/Function0;", "Lme/x;", "contents", "", "priority", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;Ljava/util/Map;I)V", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "Landroid/net/Uri;", "getIcon", "()Landroid/net/Uri;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "getCurrentState", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "setCurrentState", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;)V", "Ljava/util/Map;", "getContents", "()Ljava/util/Map;", "I", "getPriority", "()I", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AiSuggestionCapsule extends ExpandableCapsule implements Prioritized {
    private final CapsuleActionType actionType;
    private final Map<String, i> contents;
    private ExpandState currentState;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final int priority;
    private final String title;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ AiSuggestionCapsule(java.lang.String r12, java.lang.String r13, com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r14, android.net.Uri r15, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener r16, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState r17, java.util.Map r18, int r19, int r20, kotlin.jvm.internal.e r21) {
        /*
            r11 = this;
            r0 = r20
            r1 = r0 & 32
            if (r1 == 0) goto L_0x000a
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState$Collapsed r1 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState.Collapsed.INSTANCE
            r8 = r1
            goto L_0x000c
        L_0x000a:
            r8 = r17
        L_0x000c:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x001d
            r0 = 10
            r10 = r0
        L_0x0013:
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r9 = r18
            goto L_0x0020
        L_0x001d:
            r10 = r19
            goto L_0x0013
        L_0x0020:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.AiSuggestionCapsule.<init>(java.lang.String, java.lang.String, com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType, android.net.Uri, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState, java.util.Map, int, int, kotlin.jvm.internal.e):void");
    }

    public CapsuleActionType getActionType() {
        return this.actionType;
    }

    public Map<String, i> getContents() {
        return this.contents;
    }

    public ExpandState getCurrentState() {
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

    public void setCurrentState(ExpandState expandState) {
        j.e(expandState, "<set-?>");
        this.currentState = expandState;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AiSuggestionCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, ExpandState expandState, Map<String, ? extends i> map, int i2) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener, expandState, map);
        j.e(str, "id");
        j.e(str2, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(uri, "icon");
        j.e(unifiedCapsuleListener, "listener");
        j.e(expandState, "currentState");
        j.e(map, "contents");
        this.id = str;
        this.title = str2;
        this.actionType = capsuleActionType;
        this.icon = uri;
        this.listener = unifiedCapsuleListener;
        this.currentState = expandState;
        this.contents = map;
        this.priority = i2;
    }
}
