package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types;

import Ae.a;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import me.x;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0010\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012&\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u0002\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f0\u000e0\r¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0010¢\u0006\u0004\b\u0016\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\b\u001a\u00020\u00078\u0016X\u0004¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0004¢\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b%\u0010&R\"\u0010\f\u001a\u00020\u000b8\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\b\f\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R:\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u0002\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f0\u000e0\r8\u0016X\u0004¢\u0006\f\n\u0004\b\u0011\u0010,\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "", "id", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "actionType", "Landroid/net/Uri;", "icon", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "listener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "currentState", "", "Lme/i;", "Lkotlin/Function0;", "Lme/x;", "contents", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;Ljava/util/Map;)V", "expand", "()V", "collapse", "contentId", "clickItem", "(Ljava/lang/String;)V", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "getActionType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "Landroid/net/Uri;", "getIcon", "()Landroid/net/Uri;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "getListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "getCurrentState", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "setCurrentState", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;)V", "Ljava/util/Map;", "getContents", "()Ljava/util/Map;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExpandableCapsule extends Capsule {
    private final CapsuleActionType actionType;
    private final Map<String, i> contents;
    private ExpandState currentState;
    private final Uri icon;
    private final String id;
    private final UnifiedCapsuleListener listener;
    private final String title;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpandableCapsule(String str, String str2, CapsuleActionType capsuleActionType, Uri uri, UnifiedCapsuleListener unifiedCapsuleListener, ExpandState expandState, Map<String, ? extends i> map) {
        super(str, str2, capsuleActionType, uri, unifiedCapsuleListener);
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
    }

    public final void clickItem(String str) {
        a aVar;
        j.e(str, EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID);
        i iVar = getContents().get(str);
        if (iVar != null && (aVar = (a) iVar.e) != null) {
            x xVar = (x) aVar.invoke();
        }
    }

    public final void collapse() {
        getListener().onCapsuleEvent(new CapsuleEvent.OnExpanded(this, ExpandState.Collapsed.INSTANCE));
    }

    public final void expand() {
        getListener().onCapsuleEvent(new CapsuleEvent.OnExpanded(this, ExpandState.Expanded.INSTANCE));
    }

    public abstract CapsuleActionType getActionType();

    public abstract Map<String, i> getContents();

    public abstract ExpandState getCurrentState();

    public abstract Uri getIcon();

    public abstract String getId();

    public abstract UnifiedCapsuleListener getListener();

    public abstract String getTitle();

    public abstract void setCurrentState(ExpandState expandState);
}
