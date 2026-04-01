package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import Ae.a;
import O8.b;
import Sf.q;
import Tf.k;
import Tf.n;
import Tf.v;
import android.app.ActivityOptions;
import android.app.RemoteAction;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.action.ActionManager;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.UnifiedCapsuleListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ToggleState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.AiSuggestionCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.AppCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CopyAllCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.EntityCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ShareCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.TranslateCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.util.CapsuleUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.LockScreenHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.x;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 F2\u00020\u0001:\u0001FB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J/\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000e2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b \u0010!J\u001f\u0010$\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b$\u0010%J\u001f\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0(2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b*\u0010+J\u0017\u0010/\u001a\u00020.2\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0004\b/\u00100J\u001f\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0(2\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0004\b1\u00102J-\u00105\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u00104\u001a\u0002032\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b5\u00106J-\u0010<\u001a\u00020;2\u0006\u00107\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\"2\u0006\u00109\u001a\u0002082\u0006\u0010\r\u001a\u00020:¢\u0006\u0004\b<\u0010=J\u001f\u0010B\u001a\u0004\u0018\u00010A2\u0006\u0010>\u001a\u0002082\u0006\u0010@\u001a\u00020?¢\u0006\u0004\bB\u0010CR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010DR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010E¨\u0006G"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleFactory;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager;", "capsuleManager", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "imageTranslator", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;", "listener", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "createGeneralCapsules", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/TranslateCapsule;", "createTranslateCapsule", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/TranslateCapsule;", "", "text", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/CopyAllCapsule;", "createCopyAllCapsule", "(Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/CopyAllCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ShareCapsule;", "createShareCapsule", "(Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ShareCapsule;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "entityData", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/EntityCapsule;", "createEntityCapsules", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "actionType", "normalizeEntityText", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Ljava/lang/String;)Ljava/lang/String;", "Landroid/app/RemoteAction;", "action", "Lkotlin/Function0;", "Lme/x;", "createEntityCapsuleListener", "(Landroid/app/RemoteAction;)LAe/a;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionManager;", "actionManager", "", "canCreateAiSuggestionCapsule", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionManager;)Z", "createAiSuggestionCapsuleListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionManager;)LAe/a;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "textData", "createCapsules", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/UnifiedCapsuleListener;)Ljava/util/List;", "title", "Landroid/net/Uri;", "icon", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/SimpleCapsuleClickListener;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/AppCapsule;", "createAppCapsule", "(Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/SimpleCapsuleClickListener;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/AppCapsule;", "imageUri", "Lcom/google/gson/JsonObject;", "data", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/AiSuggestionCapsule;", "createAiSuggestionCapsule", "(Landroid/net/Uri;Lcom/google/gson/JsonObject;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/AiSuggestionCapsule;", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleFactory {
    public static final Companion Companion = new Companion((e) null);
    private final CapsuleManager capsuleManager;
    private final Context context;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleFactory$Companion;", "", "<init>", "()V", "TAG", "", "MAXIMUM_BLOCK_SIZE_FOR_GENERAL_CAPSULE", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CapsuleActionType.values().length];
            try {
                iArr[CapsuleActionType.ENTITY_WEBSITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CapsuleFactory(Context context2, CapsuleManager capsuleManager2) {
        j.e(context2, "context");
        j.e(capsuleManager2, "capsuleManager");
        this.context = context2;
        this.capsuleManager = capsuleManager2;
    }

    private final boolean canCreateAiSuggestionCapsule(ActionManager actionManager) {
        String classification = actionManager.getClassification();
        if (!ActionManager.Companion.isValidClassification(classification)) {
            LibLogger.i("CapsuleFactory", "Invalid classification: " + classification);
            return false;
        } else if (actionManager.isSupportAction()) {
            return true;
        } else {
            LibLogger.i("CapsuleFactory", "Unsupported action: " + classification);
            return false;
        }
    }

    private final a createAiSuggestionCapsuleListener(ActionManager actionManager) {
        return new k(1, this, actionManager);
    }

    /* access modifiers changed from: private */
    public static final x createAiSuggestionCapsuleListener$lambda$18(CapsuleFactory capsuleFactory, ActionManager actionManager) {
        LockScreenHelper.requestDismissKeyguard$default(LockScreenHelper.INSTANCE, capsuleFactory.context, new q(15, actionManager), (a) null, 4, (Object) null);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x createAiSuggestionCapsuleListener$lambda$18$lambda$17(ActionManager actionManager) {
        actionManager.runAction();
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x createAppCapsule$lambda$12(SimpleCapsuleClickListener simpleCapsuleClickListener) {
        ((b) simpleCapsuleClickListener).f2856a.lambda$addDocumentCapsule$5();
        return x.f4917a;
    }

    private final CopyAllCapsule createCopyAllCapsule(String str, UnifiedCapsuleListener unifiedCapsuleListener) {
        String string = this.context.getString(R$string.add_to_copy_all);
        j.d(string, "getString(...)");
        return new CopyAllCapsule((String) null, string, CapsuleActionType.COPY_ALL, CapsuleUtil.INSTANCE.getResourceUri$deepsky_sdk_textextraction_8_5_30_release(this.context, R$drawable.capsule_copy_all), unifiedCapsuleListener, 0, str, 33, (e) null);
    }

    private final a createEntityCapsuleListener(RemoteAction remoteAction) {
        return new k(2, this, remoteAction);
    }

    /* access modifiers changed from: private */
    public static final x createEntityCapsuleListener$lambda$11(CapsuleFactory capsuleFactory, RemoteAction remoteAction) {
        LockScreenHelper.requestDismissKeyguard$default(LockScreenHelper.INSTANCE, capsuleFactory.context, new q(16, remoteAction), (a) null, 4, (Object) null);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x createEntityCapsuleListener$lambda$11$lambda$10(RemoteAction remoteAction) {
        if (Build.VERSION.SDK_INT >= 34) {
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setPendingIntentBackgroundActivityStartMode(1);
            remoteAction.getActionIntent().send(makeBasic.toBundle());
        } else {
            remoteAction.getActionIntent().send();
        }
        return x.f4917a;
    }

    private final List<EntityCapsule> createEntityCapsules(EntityData entityData, UnifiedCapsuleListener unifiedCapsuleListener) {
        i iVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : entityData.getEntityList()) {
            EntityData.EntityInfo entityInfo = (EntityData.EntityInfo) next;
            CapsuleUtil capsuleUtil = CapsuleUtil.INSTANCE;
            CapsuleActionType convertEntityTypeToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release = capsuleUtil.convertEntityTypeToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release(EntityType.Companion.toEntityType(entityInfo.getType()), capsuleUtil.convertEntityActionIdToCapsuleActionId$deepsky_sdk_textextraction_8_5_30_release(entityInfo.getActionId()));
            Object obj = linkedHashMap.get(convertEntityTypeToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(convertEntityTypeToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release, obj);
            }
            ((List) obj).add(next);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (!((List) entry.getValue()).isEmpty()) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(z.Z(linkedHashMap2.size()));
        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
            Object key = entry2.getKey();
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object next2 : (List) entry2.getValue()) {
                if (hashSet.add(((EntityData.EntityInfo) next2).getText())) {
                    arrayList.add(next2);
                }
            }
            linkedHashMap3.put(key, arrayList);
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap3.size());
        for (Map.Entry entry3 : linkedHashMap3.entrySet()) {
            CapsuleActionType capsuleActionType = (CapsuleActionType) entry3.getKey();
            String obj2 = capsuleActionType.toString();
            CapsuleUtil capsuleUtil2 = CapsuleUtil.INSTANCE;
            String entityDescription$deepsky_sdk_textextraction_8_5_30_release = capsuleUtil2.getEntityDescription$deepsky_sdk_textextraction_8_5_30_release(this.context, capsuleActionType);
            Uri entityIcon$deepsky_sdk_textextraction_8_5_30_release = capsuleUtil2.getEntityIcon$deepsky_sdk_textextraction_8_5_30_release(this.context, capsuleActionType);
            ArrayList arrayList3 = new ArrayList();
            for (EntityData.EntityInfo entityInfo2 : (List) entry3.getValue()) {
                String normalizeEntityText = normalizeEntityText(capsuleActionType, entityInfo2.getText());
                if (entityInfo2.getAction() != null) {
                    iVar = new i(normalizeEntityText, createEntityCapsuleListener(entityInfo2.getAction()));
                } else {
                    iVar = null;
                }
                if (iVar != null) {
                    arrayList3.add(iVar);
                }
            }
            int Z = z.Z(C1196n.w0(arrayList3, 10));
            if (Z < 16) {
                Z = 16;
            }
            LinkedHashMap linkedHashMap4 = new LinkedHashMap(Z);
            for (Object next3 : arrayList3) {
                i iVar2 = (i) next3;
                String uuid = UUID.randomUUID().toString();
                j.d(uuid, "toString(...)");
                linkedHashMap4.put(uuid, next3);
            }
            arrayList2.add(new EntityCapsule(obj2, entityDescription$deepsky_sdk_textextraction_8_5_30_release, capsuleActionType, entityIcon$deepsky_sdk_textextraction_8_5_30_release, unifiedCapsuleListener, (ExpandState) null, linkedHashMap4, CapsuleUtil.INSTANCE.getCapsulePriority$deepsky_sdk_textextraction_8_5_30_release(capsuleActionType), 32, (e) null));
        }
        return arrayList2;
    }

    private final List<Capsule> createGeneralCapsules(OcrData ocrData, ImageTranslator imageTranslator, UnifiedCapsuleListener unifiedCapsuleListener) {
        ArrayList arrayList = new ArrayList();
        String ocrData2 = ocrData.toString();
        if (imageTranslator == null || !imageTranslator.isTranslationAvailable(ocrData)) {
            LibLogger.w("CapsuleFactory", "do not add Translate capsule due to translation unavailable");
        } else {
            arrayList.add(createTranslateCapsule(unifiedCapsuleListener));
        }
        if (ocrData.getBlockList().size() <= 10) {
            arrayList.add(createCopyAllCapsule(ocrData2, unifiedCapsuleListener));
        } else {
            LibLogger.w("CapsuleFactory", "do not add CopyAll capsule due to large block size");
        }
        arrayList.add(createShareCapsule(ocrData2, unifiedCapsuleListener));
        return arrayList;
    }

    private final ShareCapsule createShareCapsule(String str, UnifiedCapsuleListener unifiedCapsuleListener) {
        String string = this.context.getString(R$string.share);
        j.d(string, "getString(...)");
        return new ShareCapsule((String) null, string, CapsuleActionType.SHARE, CapsuleUtil.INSTANCE.getResourceUri$deepsky_sdk_textextraction_8_5_30_release(this.context, R$drawable.capsule_share), unifiedCapsuleListener, 0, str, 33, (e) null);
    }

    private final TranslateCapsule createTranslateCapsule(UnifiedCapsuleListener unifiedCapsuleListener) {
        String string = this.context.getString(R$string.translate);
        j.d(string, "getString(...)");
        return new TranslateCapsule((String) null, string, CapsuleActionType.TRANSLATE, CapsuleUtil.INSTANCE.getResourceUri$deepsky_sdk_textextraction_8_5_30_release(this.context, R$drawable.capsule_translate), unifiedCapsuleListener, (ToggleState) null, 0, 97, (e) null);
    }

    private final String normalizeEntityText(CapsuleActionType capsuleActionType, String str) {
        String obj = n.R0(v.s0(str, "\n", "")).toString();
        if (WhenMappings.$EnumSwitchMapping$0[capsuleActionType.ordinal()] == 1) {
            return v.s0(v.s0(obj, "http://", ""), "https://", "");
        }
        return obj;
    }

    public final AiSuggestionCapsule createAiSuggestionCapsule(Uri uri, JsonObject jsonObject) {
        j.e(uri, "imageUri");
        j.e(jsonObject, "data");
        ActionManager actionManager = new ActionManager(this.context, uri, jsonObject);
        if (!canCreateAiSuggestionCapsule(actionManager)) {
            return null;
        }
        CapsuleActionType convertCapsuleActionIdToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release = CapsuleUtil.INSTANCE.convertCapsuleActionIdToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release(actionManager.getClassification());
        String uuid = UUID.randomUUID().toString();
        j.d(uuid, "toString(...)");
        String title = actionManager.getTitle();
        Uri iconUri = actionManager.getIconUri();
        CapsuleManager capsuleManager2 = this.capsuleManager;
        Iterable e02 = C0246a.e0(new i(actionManager.getTitle(), createAiSuggestionCapsuleListener(actionManager)));
        int Z = z.Z(C1196n.w0(e02, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (Object next : e02) {
            i iVar = (i) next;
            String uuid2 = UUID.randomUUID().toString();
            j.d(uuid2, "toString(...)");
            linkedHashMap.put(uuid2, next);
        }
        return new AiSuggestionCapsule(uuid, title, convertCapsuleActionIdToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release, iconUri, capsuleManager2, (ExpandState) null, linkedHashMap, CapsuleUtil.INSTANCE.getCapsulePriority$deepsky_sdk_textextraction_8_5_30_release(convertCapsuleActionIdToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release), 32, (e) null);
    }

    public final AppCapsule createAppCapsule(String str, CapsuleActionType capsuleActionType, Uri uri, SimpleCapsuleClickListener simpleCapsuleClickListener) {
        j.e(str, "title");
        j.e(capsuleActionType, ActionHandler.ACTION_TYPE);
        j.e(uri, "icon");
        j.e(simpleCapsuleClickListener, "listener");
        String uuid = UUID.randomUUID().toString();
        j.d(uuid, "toString(...)");
        return new AppCapsule(uuid, str, capsuleActionType, uri, this.capsuleManager, 0, new q(14, simpleCapsuleClickListener), 32, (e) null);
    }

    public final List<Capsule> createCapsules(TextData textData, ImageTranslator imageTranslator, UnifiedCapsuleListener unifiedCapsuleListener) {
        Iterable iterable;
        j.e(textData, "textData");
        j.e(unifiedCapsuleListener, "listener");
        List<Capsule> createGeneralCapsules = createGeneralCapsules(textData.getOcrData(), imageTranslator, unifiedCapsuleListener);
        EntityData entityData = textData.getEntityData();
        if (entityData == null || (iterable = createEntityCapsules(entityData, unifiedCapsuleListener)) == null) {
            iterable = C1202t.d;
        }
        return C1194l.X0(iterable, createGeneralCapsules);
    }
}
