package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelperImpl;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleUpdateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ToggleState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.AppCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CopyAllCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ShareCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SimpleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ToggleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.TranslateCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.LockScreenHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.ClipboardUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import l3.a;
import l3.b;
import me.x;
import ne.v;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 f2\u00020\u0001:\u0001fB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u000e¢\u0006\u0004\b\u001e\u0010\u0015J\u0017\u0010 \u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001fH\u0002¢\u0006\u0004\b \u0010!J\u0017\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020'H\u0002¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020'H\u0002¢\u0006\u0004\b*\u0010)J\u0017\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020+H\u0002¢\u0006\u0004\b-\u0010.J\u001f\u00100\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020+2\u0006\u0010/\u001a\u00020\fH\u0002¢\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u000202H\u0002¢\u0006\u0004\b3\u00104J\u0017\u00107\u001a\u00020\u000e2\u0006\u00106\u001a\u000205H\u0002¢\u0006\u0004\b7\u00108J\u001f\u00109\u001a\u00020\u000e2\u0006\u00106\u001a\u0002052\u0006\u0010/\u001a\u00020\fH\u0002¢\u0006\u0004\b9\u0010:R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010;R \u0010=\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\f0<8\u0002X\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u001a\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00160?8\u0002X\u0004¢\u0006\u0006\n\u0004\b@\u0010AR$\u0010C\u001a\u0004\u0018\u00010B8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR$\u0010J\u001a\u0004\u0018\u00010I8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR$\u0010Q\u001a\u0004\u0018\u00010P8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR$\u0010X\u001a\u0004\u0018\u00010W8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R(\u0010`\u001a\b\u0012\u0004\u0012\u00020_0^8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\bb\u0010c\"\u0004\bd\u0010e¨\u0006g"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "getSize", "()I", "", "isEmpty", "()Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "capsule", "Lme/x;", "addCapsule", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;)V", "", "getArrangedCapsuleList", "()Ljava/util/List;", "clearAllCapsules", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleUpdateListener;", "listener", "registerListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleUpdateListener;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;", "event", "onCapsuleEvent", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;)V", "turnOffTranslateCapsule", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnClick;", "onClickEvent", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnClick;)V", "", "text", "Landroid/content/Intent;", "createShareIntent", "(Ljava/lang/String;)Landroid/content/Intent;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnToggled;", "onToggleEvent", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnToggled;)V", "onTranslateEvent", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;", "turnedOnCapsule", "turnOffOtherCapsules", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;)V", "other", "turnOffIfToggleOn", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnExpanded;", "onExpandedEvent", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnExpanded;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "expandedCapsule", "collapseOtherCapsules", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;)V", "collapseIfExpanded", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;)V", "Landroid/content/Context;", "", "capsuleMap", "Ljava/util/Map;", "", "updateListeners", "Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;", "capsuleListener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;", "getCapsuleListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;", "setCapsuleListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;", "translateCapsuleListener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;", "getTranslateCapsuleListener", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;", "setTranslateCapsuleListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;", "translateCapsuleDelegate", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;", "getTranslateCapsuleDelegate", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;", "setTranslateCapsuleDelegate", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;)V", "Landroid/app/PendingIntent;", "sharePendingIntent", "Landroid/app/PendingIntent;", "getSharePendingIntent", "()Landroid/app/PendingIntent;", "setSharePendingIntent", "(Landroid/app/PendingIntent;)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "ignoredActionTypes", "Ljava/util/Set;", "getIgnoredActionTypes", "()Ljava/util/Set;", "setIgnoredActionTypes", "(Ljava/util/Set;)V", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleManager implements UnifiedCapsuleListener {
    public static final Companion Companion = new Companion((e) null);
    private CapsuleHelper.CapsuleListener capsuleListener;
    private final Map<String, Capsule> capsuleMap = new LinkedHashMap();
    private final Context context;
    private Set<? extends CapsuleActionType> ignoredActionTypes = v.d;
    private PendingIntent sharePendingIntent;
    private CapsuleHelperImpl.TranslateCapsuleDelegate translateCapsuleDelegate;
    private TranslateCapsuleListener translateCapsuleListener;
    private final List<CapsuleUpdateListener> updateListeners = new ArrayList();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public CapsuleManager(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final void collapseIfExpanded(ExpandableCapsule expandableCapsule, Capsule capsule) {
        if (capsule instanceof ExpandableCapsule) {
            ExpandableCapsule expandableCapsule2 = (ExpandableCapsule) capsule;
            if (!j.a(expandableCapsule2.getId(), expandableCapsule.getId()) && j.a(expandableCapsule2.getCurrentState(), ExpandState.Expanded.INSTANCE)) {
                expandableCapsule2.collapse();
            }
        }
    }

    private final void collapseOtherCapsules(ExpandableCapsule expandableCapsule) {
        for (Capsule collapseIfExpanded : this.capsuleMap.values()) {
            collapseIfExpanded(expandableCapsule, collapseIfExpanded);
        }
    }

    private final Intent createShareIntent(String str) {
        Intent intent;
        IntentSender intentSender;
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("text/plain");
        intent2.removeExtra("android.intent.extra.TEXT");
        intent2.putExtra("android.intent.extra.TEXT", str);
        PendingIntent pendingIntent = this.sharePendingIntent;
        if (pendingIntent == null || !(this.context instanceof Activity)) {
            intent = Intent.createChooser(intent2, (CharSequence) null);
        } else {
            if (pendingIntent != null) {
                intentSender = pendingIntent.getIntentSender();
            } else {
                intentSender = null;
            }
            intent = Intent.createChooser(intent2, (CharSequence) null, intentSender);
        }
        intent.setFlags(268435456);
        return intent;
    }

    private final void onClickEvent(CapsuleEvent.OnClick onClick) {
        CapsuleHelper.CapsuleListener capsuleListener2 = this.capsuleListener;
        if (capsuleListener2 != null) {
            capsuleListener2.onClick(onClick.getCapsule().getActionType());
        }
        CapsuleActionType actionType = onClick.getCapsule().getActionType();
        LibLogger.i("CapsuleManager", "onClick " + actionType);
        SimpleCapsule capsule = onClick.getCapsule();
        if (capsule instanceof CopyAllCapsule) {
            LockScreenHelper.requestDismissKeyguard$default(LockScreenHelper.INSTANCE, this.context, new a(this, onClick, 0), (Ae.a) null, 4, (Object) null);
        } else if (capsule instanceof ShareCapsule) {
            LockScreenHelper.requestDismissKeyguard$default(LockScreenHelper.INSTANCE, this.context, new a(this, onClick, 1), (Ae.a) null, 4, (Object) null);
        } else if (capsule instanceof AppCapsule) {
            x xVar = (x) ((AppCapsule) onClick.getCapsule()).getAction().invoke();
        }
    }

    /* access modifiers changed from: private */
    public static final x onClickEvent$lambda$8(CapsuleManager capsuleManager, CapsuleEvent.OnClick onClick) {
        ClipboardUtil.INSTANCE.copyClipboard(capsuleManager.context, ((CopyAllCapsule) onClick.getCapsule()).getText());
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x onClickEvent$lambda$9(CapsuleManager capsuleManager, CapsuleEvent.OnClick onClick) {
        capsuleManager.context.startActivity(capsuleManager.createShareIntent(((ShareCapsule) onClick.getCapsule()).getText()));
        return x.f4917a;
    }

    private final void onExpandedEvent(CapsuleEvent.OnExpanded onExpanded) {
        onExpanded.getCapsule().setCurrentState(onExpanded.getState());
        ExpandState state = onExpanded.getState();
        if (state instanceof ExpandState.Expanded) {
            collapseOtherCapsules(onExpanded.getCapsule());
        } else if (!(state instanceof ExpandState.Collapsed)) {
            throw new RuntimeException();
        }
    }

    private final void onToggleEvent(CapsuleEvent.OnToggled onToggled) {
        onToggled.getCapsule().setCurrentState(onToggled.getState());
        ToggleState state = onToggled.getState();
        if (state instanceof ToggleState.On) {
            CapsuleHelper.CapsuleListener capsuleListener2 = this.capsuleListener;
            if (capsuleListener2 != null) {
                capsuleListener2.onClick(onToggled.getCapsule().getActionType());
            }
            CapsuleActionType actionType = onToggled.getCapsule().getActionType();
            LibLogger.i("CapsuleManager", "onToggleOn " + actionType);
            turnOffOtherCapsules(onToggled.getCapsule());
        } else if (state instanceof ToggleState.Off) {
            CapsuleActionType actionType2 = onToggled.getCapsule().getActionType();
            LibLogger.i("CapsuleManager", "onToggleOff " + actionType2);
        } else {
            throw new RuntimeException();
        }
        if (onToggled.getCapsule() instanceof TranslateCapsule) {
            onTranslateEvent(onToggled);
        }
    }

    private final void onTranslateEvent(CapsuleEvent.OnToggled onToggled) {
        ToggleState state = onToggled.getState();
        if (state instanceof ToggleState.On) {
            LockScreenHelper.INSTANCE.requestDismissKeyguard(this.context, new b(this, 0), new b(this, 1));
        } else if (state instanceof ToggleState.Off) {
            CapsuleHelperImpl.TranslateCapsuleDelegate translateCapsuleDelegate2 = this.translateCapsuleDelegate;
            if (translateCapsuleDelegate2 != null) {
                translateCapsuleDelegate2.onTranslateOff();
            }
            TranslateCapsuleListener translateCapsuleListener2 = this.translateCapsuleListener;
            if (translateCapsuleListener2 != null) {
                translateCapsuleListener2.onTranslateOff();
            }
        } else {
            throw new RuntimeException();
        }
    }

    /* access modifiers changed from: private */
    public static final x onTranslateEvent$lambda$12(CapsuleManager capsuleManager) {
        CapsuleHelperImpl.TranslateCapsuleDelegate translateCapsuleDelegate2 = capsuleManager.translateCapsuleDelegate;
        if (translateCapsuleDelegate2 != null) {
            translateCapsuleDelegate2.onTranslateOn();
        }
        TranslateCapsuleListener translateCapsuleListener2 = capsuleManager.translateCapsuleListener;
        if (translateCapsuleListener2 != null) {
            translateCapsuleListener2.onTranslateOn();
        }
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x onTranslateEvent$lambda$13(CapsuleManager capsuleManager) {
        capsuleManager.turnOffTranslateCapsule();
        return x.f4917a;
    }

    private final void turnOffIfToggleOn(ToggleCapsule toggleCapsule, Capsule capsule) {
        if (capsule instanceof ToggleCapsule) {
            ToggleCapsule toggleCapsule2 = (ToggleCapsule) capsule;
            if (!j.a(toggleCapsule2.getId(), toggleCapsule.getId()) && j.a(toggleCapsule2.getCurrentState(), ToggleState.On.INSTANCE)) {
                toggleCapsule2.toggle();
            }
        }
    }

    private final void turnOffOtherCapsules(ToggleCapsule toggleCapsule) {
        for (Capsule turnOffIfToggleOn : this.capsuleMap.values()) {
            turnOffIfToggleOn(toggleCapsule, turnOffIfToggleOn);
        }
    }

    public final void addCapsule(Capsule capsule) {
        j.e(capsule, "capsule");
        if (this.capsuleMap.containsKey(capsule.getId())) {
            String id = capsule.getId();
            LibLogger.w("CapsuleManager", "addCapsule capsule with same id(" + id + ") already exists");
        } else if (this.ignoredActionTypes.contains(capsule.getActionType())) {
            CapsuleActionType actionType = capsule.getActionType();
            LibLogger.w("CapsuleManager", "addCapsule capsuleActionType " + actionType + " is ignored");
        } else {
            this.capsuleMap.put(capsule.getId(), capsule);
            CapsuleActionType actionType2 = capsule.getActionType();
            LibLogger.i("CapsuleManager", "addCapsule " + actionType2 + " is added");
            CapsuleHelper.CapsuleListener capsuleListener2 = this.capsuleListener;
            if (capsuleListener2 != null) {
                capsuleListener2.onAdd(capsule.getActionType());
            }
        }
    }

    public final void clearAllCapsules() {
        this.capsuleMap.clear();
    }

    /* JADX WARNING: type inference failed for: r1v6, types: [com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule] */
    /* JADX WARNING: type inference failed for: r3v6, types: [com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SingleActionCapsule] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule> getArrangedCapsuleList() {
        /*
            r14 = this;
            java.util.Map<java.lang.String, com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule> r14 = r14.capsuleMap
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r14, r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>(r14)
            java.util.Collection r14 = r0.values()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r14 = r14.iterator()
        L_0x001b:
            boolean r2 = r14.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r14.next()
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule r2 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule) r2
            boolean r4 = r2 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SingleActionCapsule
            if (r4 == 0) goto L_0x002f
            r3 = r2
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SingleActionCapsule r3 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SingleActionCapsule) r3
        L_0x002f:
            if (r3 == 0) goto L_0x001b
            r1.add(r3)
            goto L_0x001b
        L_0x0035:
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager$getArrangedCapsuleList$$inlined$sortedByDescending$1 r14 = new com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager$getArrangedCapsuleList$$inlined$sortedByDescending$1
            r14.<init>()
            java.util.List r11 = ne.C1194l.g1(r1, r14)
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CompositeCapsule r4 = new com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CompositeCapsule
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 63
            r13 = 0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            java.lang.String r14 = r4.getId()
            r0.put(r14, r4)
            java.util.List r14 = r4.getChildren()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.Iterator r14 = r14.iterator()
        L_0x005d:
            boolean r1 = r14.hasNext()
            if (r1 == 0) goto L_0x0071
            java.lang.Object r1 = r14.next()
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule r1 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule) r1
            java.lang.String r1 = r1.getId()
            r0.remove(r1)
            goto L_0x005d
        L_0x0071:
            java.util.Collection r14 = r0.values()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager$getArrangedCapsuleList$$inlined$sortedByDescending$2 r0 = new com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager$getArrangedCapsuleList$$inlined$sortedByDescending$2
            r0.<init>()
            java.util.List r14 = ne.C1194l.g1(r14, r0)
            r0 = r14
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r1 = r0.iterator()
        L_0x0087:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00a3
            java.lang.Object r2 = r1.next()
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule r2 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule) r2
            boolean r4 = r2 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule
            if (r4 == 0) goto L_0x009a
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule r2 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule) r2
            goto L_0x009b
        L_0x009a:
            r2 = r3
        L_0x009b:
            if (r2 == 0) goto L_0x0087
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState$Collapsed r4 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState.Collapsed.INSTANCE
            r2.setCurrentState(r4)
            goto L_0x0087
        L_0x00a3:
            java.util.Iterator r0 = r0.iterator()
        L_0x00a7:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00b9
            java.lang.Object r1 = r0.next()
            r2 = r1
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule r2 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule) r2
            boolean r2 = r2 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule
            if (r2 == 0) goto L_0x00a7
            goto L_0x00ba
        L_0x00b9:
            r1 = r3
        L_0x00ba:
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule r1 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule) r1
            boolean r0 = r1 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule
            if (r0 == 0) goto L_0x00c3
            r3 = r1
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule r3 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule) r3
        L_0x00c3:
            if (r3 == 0) goto L_0x00ca
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState$Expanded r0 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState.Expanded.INSTANCE
            r3.setCurrentState(r0)
        L_0x00ca:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleManager.getArrangedCapsuleList():java.util.List");
    }

    public final int getSize() {
        return this.capsuleMap.size();
    }

    public final boolean isEmpty() {
        return this.capsuleMap.isEmpty();
    }

    public void onCapsuleEvent(CapsuleEvent capsuleEvent) {
        j.e(capsuleEvent, "event");
        if (capsuleEvent instanceof CapsuleEvent.OnClick) {
            onClickEvent((CapsuleEvent.OnClick) capsuleEvent);
        } else if (capsuleEvent instanceof CapsuleEvent.OnToggled) {
            onToggleEvent((CapsuleEvent.OnToggled) capsuleEvent);
        } else if (capsuleEvent instanceof CapsuleEvent.OnExpanded) {
            onExpandedEvent((CapsuleEvent.OnExpanded) capsuleEvent);
        } else {
            throw new RuntimeException();
        }
        for (CapsuleUpdateListener onCapsuleUpdate : this.updateListeners) {
            onCapsuleUpdate.onCapsuleUpdate(capsuleEvent);
        }
    }

    public final void registerListener(CapsuleUpdateListener capsuleUpdateListener) {
        j.e(capsuleUpdateListener, "listener");
        if (!this.updateListeners.contains(capsuleUpdateListener)) {
            this.updateListeners.add(capsuleUpdateListener);
        }
    }

    public final void setCapsuleListener(CapsuleHelper.CapsuleListener capsuleListener2) {
        this.capsuleListener = capsuleListener2;
    }

    public final void setTranslateCapsuleDelegate(CapsuleHelperImpl.TranslateCapsuleDelegate translateCapsuleDelegate2) {
        this.translateCapsuleDelegate = translateCapsuleDelegate2;
    }

    public final void setTranslateCapsuleListener(TranslateCapsuleListener translateCapsuleListener2) {
        this.translateCapsuleListener = translateCapsuleListener2;
    }

    public final void turnOffTranslateCapsule() {
        for (Capsule capsule : this.capsuleMap.values()) {
            if (capsule instanceof TranslateCapsule) {
                TranslateCapsule translateCapsule = (TranslateCapsule) capsule;
                if (j.a(translateCapsule.getCurrentState(), ToggleState.On.INSTANCE)) {
                    translateCapsule.toggle();
                }
            }
        }
    }
}
