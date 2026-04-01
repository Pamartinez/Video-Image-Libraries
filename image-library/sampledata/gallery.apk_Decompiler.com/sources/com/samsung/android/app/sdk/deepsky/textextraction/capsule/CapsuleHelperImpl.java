package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cg.n;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.AiSuggestionCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.CapsuleColor;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PackageHelper;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import eg.f;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 ]2\u00020\u0001:\u0003^_]B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000e\u0010\fJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\nJ\u000f\u0010\u0013\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\fJ\u0017\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\bH\u0016¢\u0006\u0004\b#\u0010\fJ'\u0010)\u001a\u00020\b2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*J\u001f\u0010.\u001a\u00020\b2\u0006\u0010+\u001a\u00020$2\u0006\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b.\u0010/J!\u00104\u001a\u00020\b2\u0006\u00101\u001a\u0002002\b\u00103\u001a\u0004\u0018\u000102H\u0007¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\bH\u0016¢\u0006\u0004\b6\u0010\fJ\r\u00107\u001a\u00020\b¢\u0006\u0004\b7\u0010\fJU\u0010@\u001a\u00020\b2\b\u00109\u001a\u0004\u0018\u0001082\b\u0010:\u001a\u0004\u0018\u0001082\b\u0010;\u001a\u0004\u0018\u0001082\b\u0010<\u001a\u0004\u0018\u0001082\b\u0010=\u001a\u0004\u0018\u0001082\b\u0010>\u001a\u0004\u0018\u0001082\b\u0010?\u001a\u0004\u0018\u000108H\u0016¢\u0006\u0004\b@\u0010AJ=\u0010@\u001a\u00020\b2\u0006\u0010=\u001a\u0002082\u0006\u0010>\u001a\u0002082\b\u0010;\u001a\u0004\u0018\u0001082\b\u0010<\u001a\u0004\u0018\u0001082\b\u0010?\u001a\u0004\u0018\u000108H\u0017¢\u0006\u0004\b@\u0010BR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020G8\u0002X\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010K\u001a\u00020J8\u0002X\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010P\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010S\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020\u00148\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010X\u001a\u00020W8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bX\u0010YR\u0018\u0010[\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b[\u0010\\¨\u0006`"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/ViewGroup;", "layout", "Lme/x;", "initLayout", "(Landroid/view/ViewGroup;)V", "drawCapsules", "()V", "setClientCapsuleLayoutVisibility", "removeAllViews", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "getDefaultCapsuleTheme", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "initCapsuleLayout", "invalidateCapsuleLayout", "", "isVisible", "setCapsuleLayoutVisibility", "(Z)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;", "listener", "setTranslateCapsuleListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;", "delegate", "setTranslateCapsuleDelegate", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;", "setCapsuleListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelper$CapsuleListener;)V", "turnOffTranslate", "Landroid/net/Uri;", "icon", "", "title", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/SimpleCapsuleClickListener;", "addCapsule", "(Landroid/net/Uri;Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/SimpleCapsuleClickListener;)V", "imageUri", "Lcom/google/gson/JsonObject;", "data", "addActionCapsule", "(Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "textData", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "imageTranslator", "initCapsules", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;)V", "onConfigurationChanged", "clearCapsuleLayout", "", "generalBackgroundColor", "generalTintColor", "toggleOnBackgroundColor", "toggleOnTintColor", "entityBackgroundColor", "entityTintColor", "rippleColor", "updateCapsuleColor", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "(IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager;", "capsuleManager", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleManager;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleFactory;", "capsuleFactory", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleFactory;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory;", "capsuleViewFactory", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleRenderer;", "capsuleRenderer", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleRenderer;", "clientCapsuleLayout", "Landroid/view/ViewGroup;", "Landroid/widget/LinearLayout;", "capsuleLayout", "Landroid/widget/LinearLayout;", "isCapsuleLayoutVisible", "Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$CapsuleState;", "capsuleState", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$CapsuleState;", "LVf/e0;", "drawingJob", "LVf/e0;", "Companion", "CapsuleState", "TranslateCapsuleDelegate", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleHelperImpl implements CapsuleHelper {
    public static final Companion Companion = new Companion((e) null);
    private final CapsuleFactory capsuleFactory;
    private LinearLayout capsuleLayout;
    private final CapsuleManager capsuleManager;
    private final CapsuleRenderer capsuleRenderer;
    private CapsuleState capsuleState = CapsuleState.HIDDEN;
    private final CapsuleViewFactory capsuleViewFactory;
    private ViewGroup clientCapsuleLayout;
    private final Context context;
    /* access modifiers changed from: private */
    public C0867e0 drawingJob;
    private boolean isCapsuleLayoutVisible = true;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$CapsuleState;", "", "<init>", "(Ljava/lang/String;I)V", "SHOWN", "HIDDEN", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CapsuleState {
        SHOWN,
        HIDDEN;

        static {
            CapsuleState[] $values;
            $ENTRIES = c.t($values);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$Companion;", "", "<init>", "()V", "TAG", "", "WALLET_PACKAGE_NAME", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;", "", "Lme/x;", "onTranslateOn", "()V", "onTranslateOff", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TranslateCapsuleDelegate {
        void onTranslateOff();

        void onTranslateOn();
    }

    public CapsuleHelperImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        CapsuleManager capsuleManager2 = new CapsuleManager(context2);
        this.capsuleManager = capsuleManager2;
        this.capsuleFactory = new CapsuleFactory(context2, capsuleManager2);
        CapsuleViewFactory capsuleViewFactory2 = new CapsuleViewFactory(context2, getDefaultCapsuleTheme());
        this.capsuleViewFactory = capsuleViewFactory2;
        CapsuleRenderer capsuleRenderer2 = new CapsuleRenderer(capsuleViewFactory2);
        this.capsuleRenderer = capsuleRenderer2;
        capsuleManager2.registerListener(capsuleRenderer2);
    }

    /* access modifiers changed from: private */
    public final void drawCapsules() {
        if (!this.isCapsuleLayoutVisible || this.capsuleState == CapsuleState.HIDDEN) {
            LibLogger.d("CapsuleHelper", "drawCapsules - capsule layout is not visible");
            return;
        }
        LinearLayout linearLayout = this.capsuleLayout;
        if (linearLayout != null) {
            this.capsuleRenderer.drawCapsules(this.capsuleManager.getArrangedCapsuleList(), linearLayout);
            setClientCapsuleLayoutVisibility();
        }
    }

    private final CapsuleColor getDefaultCapsuleTheme() {
        return new CapsuleColor(this.context.getColor(R$color.capsule_general_background), this.context.getColor(R$color.capsule_general_tint_color), this.context.getColor(R$color.capsule_toggle_on_background), this.context.getColor(R$color.capsule_toggle_on_tint_color), this.context.getColor(R$color.capsule_entity_background), this.context.getColor(R$color.capsule_entity_tint_color), this.context.getColor(R$color.capsule_ripple_background_color));
    }

    private final void initLayout(ViewGroup viewGroup) {
        LinearLayout linearLayout;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.context).inflate(R$layout.capsule_layout, viewGroup);
        this.clientCapsuleLayout = viewGroup2;
        if (viewGroup2 != null) {
            linearLayout = (LinearLayout) viewGroup2.findViewById(R$id.capsule_parent);
        } else {
            linearLayout = null;
        }
        this.capsuleLayout = linearLayout;
    }

    /* access modifiers changed from: private */
    public final void removeAllViews() {
        LinearLayout linearLayout = this.capsuleLayout;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
    }

    private final void setClientCapsuleLayoutVisibility() {
        if (!this.capsuleManager.isEmpty()) {
            int size = this.capsuleManager.getSize();
            LibLogger.d("CapsuleHelper", "CapsuleLayout visible: total " + size + " capsules");
            ViewGroup viewGroup = this.clientCapsuleLayout;
            if (viewGroup != null) {
                viewGroup.setVisibility(0);
                return;
            }
            return;
        }
        LibLogger.d("CapsuleHelper", "CapsuleLayout gone, capsuleContainer empty");
        ViewGroup viewGroup2 = this.clientCapsuleLayout;
        if (viewGroup2 != null) {
            viewGroup2.setVisibility(8);
        }
    }

    public void addActionCapsule(Uri uri, JsonObject jsonObject) {
        j.e(uri, "imageUri");
        j.e(jsonObject, "data");
        if (!PackageHelper.INSTANCE.isPackageExists(this.context, "com.samsung.android.spay")) {
            LibLogger.w("CapsuleHelper", "addActionCapsule ignored since wallet app does not exists");
            return;
        }
        LibLogger.i("CapsuleHelper", "addActionCapsule called");
        AiSuggestionCapsule createAiSuggestionCapsule = this.capsuleFactory.createAiSuggestionCapsule(uri, jsonObject);
        if (createAiSuggestionCapsule != null) {
            this.capsuleManager.addCapsule(createAiSuggestionCapsule);
            x xVar = (x) D.r(C1233i.d, new CapsuleHelperImpl$addActionCapsule$1$1(this, (C1227c) null));
        }
    }

    public void addCapsule(Uri uri, String str, SimpleCapsuleClickListener simpleCapsuleClickListener) {
        j.e(uri, "icon");
        j.e(str, "title");
        j.e(simpleCapsuleClickListener, "listener");
        LibLogger.i("CapsuleHelper", "addCapsule called with ".concat(str));
        this.capsuleManager.addCapsule(this.capsuleFactory.createAppCapsule(str, CapsuleActionType.OTHER, uri, simpleCapsuleClickListener));
    }

    public final void clearCapsuleLayout() {
        removeAllViews();
        this.capsuleState = CapsuleState.HIDDEN;
    }

    public void initCapsuleLayout(ViewGroup viewGroup) {
        j.e(viewGroup, "layout");
        LibLogger.i("CapsuleHelper", "initCapsuleLayout called");
        initLayout(viewGroup);
        this.capsuleManager.clearAllCapsules();
    }

    public final void initCapsules(TextData textData, ImageTranslator imageTranslator) {
        j.e(textData, "textData");
        if (textData.getOcrData().getBlockList().isEmpty()) {
            LibLogger.w("CapsuleHelper", "initCapsules - blockList is empty");
            return;
        }
        for (Capsule addCapsule : this.capsuleFactory.createCapsules(textData, imageTranslator, this.capsuleManager)) {
            this.capsuleManager.addCapsule(addCapsule);
        }
        this.capsuleState = CapsuleState.SHOWN;
    }

    public void invalidateCapsuleLayout() {
        LibLogger.i("CapsuleHelper", "invalidateCapsuleLayout called");
        f fVar = M.f3843a;
        this.drawingJob = D.n(D.a(n.f4030a), (C0886x) null, (C) null, new CapsuleHelperImpl$invalidateCapsuleLayout$1(this, (C1227c) null), 3);
    }

    public void onConfigurationChanged() {
        LibLogger.i("CapsuleHelper", "CapsuleHelper onConfigurationChanged called");
        ViewGroup viewGroup = this.clientCapsuleLayout;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            initLayout(viewGroup);
            drawCapsules();
        }
    }

    public void setCapsuleLayoutVisibility(boolean z) {
        LibLogger.i("CapsuleHelper", "setCapsuleLayoutVisibility " + z);
        this.isCapsuleLayoutVisible = z;
        invalidateCapsuleLayout();
    }

    public void setCapsuleListener(CapsuleHelper.CapsuleListener capsuleListener) {
        j.e(capsuleListener, "listener");
        this.capsuleManager.setCapsuleListener(capsuleListener);
    }

    public final void setTranslateCapsuleDelegate(TranslateCapsuleDelegate translateCapsuleDelegate) {
        j.e(translateCapsuleDelegate, "delegate");
        this.capsuleManager.setTranslateCapsuleDelegate(translateCapsuleDelegate);
    }

    public void setTranslateCapsuleListener(TranslateCapsuleListener translateCapsuleListener) {
        j.e(translateCapsuleListener, "listener");
        this.capsuleManager.setTranslateCapsuleListener(translateCapsuleListener);
    }

    public void turnOffTranslate() {
        this.capsuleManager.turnOffTranslateCapsule();
    }

    public void updateCapsuleColor(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7) {
        this.capsuleViewFactory.setCapsuleColor(new CapsuleColor(num != null ? num.intValue() : this.context.getColor(R$color.capsule_general_background), num2 != null ? num2.intValue() : this.context.getColor(R$color.capsule_general_tint_color), num3 != null ? num3.intValue() : this.context.getColor(R$color.capsule_toggle_on_background), num4 != null ? num4.intValue() : this.context.getColor(R$color.capsule_toggle_on_tint_color), num5 != null ? num5.intValue() : this.context.getColor(R$color.capsule_entity_background), num6 != null ? num6.intValue() : this.context.getColor(R$color.capsule_entity_tint_color), num7 != null ? num7.intValue() : this.context.getColor(R$color.capsule_ripple_background_color)));
    }

    public void updateCapsuleColor(int i2, int i7, Integer num, Integer num2, Integer num3) {
        updateCapsuleColor(Integer.valueOf(this.context.getColor(R$color.capsule_general_background)), Integer.valueOf(this.context.getColor(R$color.capsule_general_tint_color)), num, num2, Integer.valueOf(i2), Integer.valueOf(i7), num3);
    }
}
