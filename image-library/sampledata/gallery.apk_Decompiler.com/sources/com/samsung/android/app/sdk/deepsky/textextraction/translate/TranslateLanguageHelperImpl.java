package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import android.content.Context;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import com.samsung.android.app.sdk.deepsky.textextraction.R$raw;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.RecentlyUsedLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.LanguageSelectFragment;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 X2\u00020\u0001:\u0001XB)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001b\u0010\u0016J\u000f\u0010\u001c\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001c\u0010\u0016J\u000f\u0010\u001d\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001d\u0010\u0016J\u000f\u0010\u001e\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001e\u0010\u0016J\u000f\u0010\u001f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001f\u0010\u0016J\u0019\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020 H\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u000eH\u0002¢\u0006\u0004\b$\u0010\u0016J\u000f\u0010&\u001a\u00020%H\u0002¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u000eH\u0002¢\u0006\u0004\b(\u0010\u0016J\u000f\u0010)\u001a\u00020\u000eH\u0002¢\u0006\u0004\b)\u0010\u0016J\u000f\u0010+\u001a\u00020*H\u0002¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u000eH\u0002¢\u0006\u0004\b-\u0010\u0016J\u000f\u0010.\u001a\u00020\u000eH\u0002¢\u0006\u0004\b.\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010/R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00100R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u00101R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u00102R\u0016\u00103\u001a\u00020%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u00104R\u0014\u00107\u001a\u0002068\u0002X\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020*8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0018\u0010B\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bB\u0010<R\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010F\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bF\u0010AR\u0018\u0010G\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010<R\u0018\u0010H\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bH\u0010ER\u0018\u0010I\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bI\u0010<R\u0018\u0010J\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bJ\u0010ER\u0018\u0010K\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bK\u0010<R\u0018\u0010L\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bL\u0010<R\u0018\u0010N\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010Q\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bS\u0010RR\u0016\u0010T\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bV\u0010W¨\u0006Y"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelperImpl;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "imageTranslator", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "langPackManager", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LanguageChangeListener;", "languageChangeListener", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LanguageChangeListener;)V", "Landroid/widget/LinearLayout;", "layout", "Lme/x;", "initLayout", "(Landroid/widget/LinearLayout;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;", "state", "invalidateLayout", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;)V", "onConfigurationChanged", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageListener;", "listener", "setListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageListener;)V", "initViewInternal", "initLangCodeButton", "initLangChangeLayout", "initSourceLangChangeLayout", "initTargetLangChangeLayout", "", "doTranslate", "updateLanguage", "(Z)V", "updateSourceLanguage", "", "getDisplaySourceLanguage", "()Ljava/lang/String;", "updateTargetLanguage", "updateLangCodeButton", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;", "getDefaultLayoutTheme", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;", "playAnimation", "cancelAnimation", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LanguageChangeListener;", "sourceLanguage", "Ljava/lang/String;", "targetLanguage", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/RecentlyUsedLanguage;", "recentlyUsedLanguages", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/RecentlyUsedLanguage;", "dialogTheme", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;", "langManageLayout", "Landroid/widget/LinearLayout;", "translateLanguageListener", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageListener;", "Landroid/widget/FrameLayout;", "outerLayout", "Landroid/widget/FrameLayout;", "langCodeButton", "Landroid/widget/TextView;", "langCodeTextView", "Landroid/widget/TextView;", "langChangeLayout", "langChangeSourceLayout", "langChangeSourceTextView", "langChangeTargetLayout", "langChangeTargetTextView", "langChangeSwapButton", "langChangeCloseButton", "Lcom/airbnb/lottie/LottieAnimationView;", "langChangeAnimationView", "Lcom/airbnb/lottie/LottieAnimationView;", "Landroidx/fragment/app/DialogFragment;", "sourceLangSelectFragment", "Landroidx/fragment/app/DialogFragment;", "targetLangSelectFragment", "layoutState", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;", "isAnimationPlayed", "Z", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TranslateLanguageHelperImpl implements TranslateLanguageHelper {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private TranslateDialogTheme dialogTheme;
    /* access modifiers changed from: private */
    public final ImageTranslator imageTranslator;
    private boolean isAnimationPlayed;
    private LottieAnimationView langChangeAnimationView;
    private LinearLayout langChangeCloseButton;
    private FrameLayout langChangeLayout;
    private LinearLayout langChangeSourceLayout;
    private TextView langChangeSourceTextView;
    private LinearLayout langChangeSwapButton;
    private LinearLayout langChangeTargetLayout;
    private TextView langChangeTargetTextView;
    private LinearLayout langCodeButton;
    private TextView langCodeTextView;
    private LinearLayout langManageLayout;
    private final LangPackManager langPackManager;
    private final LanguageChangeListener languageChangeListener;
    private LayoutState layoutState;
    private FrameLayout outerLayout;
    private final RecentlyUsedLanguage recentlyUsedLanguages;
    private DialogFragment sourceLangSelectFragment;
    /* access modifiers changed from: private */
    public String sourceLanguage = "Auto";
    private DialogFragment targetLangSelectFragment;
    /* access modifiers changed from: private */
    public String targetLanguage = LanguageUtil.INSTANCE.createTargetLanguage();
    /* access modifiers changed from: private */
    public TranslateLanguageListener translateLanguageListener;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelperImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState r1 = com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState.HIDDEN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState r1 = com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState.LANGUAGE_CODE_BUTTON     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState r1 = com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState.LANGUAGE_CHANGE_LAYOUT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl.WhenMappings.<clinit>():void");
        }
    }

    public TranslateLanguageHelperImpl(Context context2, ImageTranslator imageTranslator2, LangPackManager langPackManager2, LanguageChangeListener languageChangeListener2) {
        j.e(context2, "context");
        j.e(imageTranslator2, "imageTranslator");
        j.e(langPackManager2, "langPackManager");
        this.context = context2;
        this.imageTranslator = imageTranslator2;
        this.langPackManager = langPackManager2;
        this.languageChangeListener = languageChangeListener2;
        this.recentlyUsedLanguages = new RecentlyUsedLanguage(context2);
        this.dialogTheme = getDefaultLayoutTheme();
        this.layoutState = LayoutState.HIDDEN;
    }

    private final void cancelAnimation() {
        LottieAnimationView lottieAnimationView;
        if (this.isAnimationPlayed && (lottieAnimationView = this.langChangeAnimationView) != null) {
            lottieAnimationView.setVisibility(8);
        }
    }

    private final TranslateDialogTheme getDefaultLayoutTheme() {
        return new TranslateDialogTheme(R$drawable.lang_code_background, this.context.getColor(R$color.language_layout_text_color), R$drawable.lang_change_background, R$drawable.lang_close_btn_background, this.context.getColor(R$color.lang_change_close_tint_color), R$drawable.language_item_background, R$raw.translation_auto_detecting);
    }

    private final String getDisplaySourceLanguage() {
        String str = this.sourceLanguage;
        ImageTranslator imageTranslator2 = this.imageTranslator;
        if (j.a(str, "Auto")) {
            int size = imageTranslator2.getDetectedLanguageList().size();
            LibLogger.i("TranslateLanguageHelper", "detected lang number: " + size);
            if (size == 1) {
                return imageTranslator2.getMostDetectedLanguage();
            }
        }
        return str;
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initLangChangeLayout() {
        /*
            r4 = this;
            android.widget.FrameLayout r0 = r4.outerLayout
            r1 = 0
            if (r0 == 0) goto L_0x000e
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_layout
            android.view.View r0 = r0.findViewById(r2)
            android.widget.FrameLayout r0 = (android.widget.FrameLayout) r0
            goto L_0x000f
        L_0x000e:
            r0 = r1
        L_0x000f:
            r4.langChangeLayout = r0
            if (r0 == 0) goto L_0x0047
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_swap_button
            android.view.View r0 = r0.findViewById(r2)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            if (r0 == 0) goto L_0x0047
            w3.b r2 = new w3.b
            r3 = 0
            r2.<init>(r4, r3)
            r0.setOnClickListener(r2)
            android.content.Context r2 = r0.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.samsung.android.app.sdk.deepsky.textextraction.R$string.lang_select_swap
            java.lang.String r2 = r2.getString(r3)
            r0.setContentDescription(r2)
            android.content.Context r2 = r0.getContext()
            android.content.res.Resources r2 = r2.getResources()
            java.lang.String r2 = r2.getString(r3)
            r0.setTooltipText(r2)
            goto L_0x0048
        L_0x0047:
            r0 = r1
        L_0x0048:
            r4.langChangeSwapButton = r0
            android.widget.FrameLayout r0 = r4.langChangeLayout
            if (r0 == 0) goto L_0x0093
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_close_button
            android.view.View r0 = r0.findViewById(r2)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            if (r0 == 0) goto L_0x0093
            w3.b r2 = new w3.b
            r3 = 1
            r2.<init>(r4, r3)
            r0.setOnClickListener(r2)
            android.content.Context r2 = r0.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.samsung.android.app.sdk.deepsky.textextraction.R$string.lang_select_close
            java.lang.String r2 = r2.getString(r3)
            r0.setContentDescription(r2)
            android.content.Context r2 = r0.getContext()
            android.content.res.Resources r2 = r2.getResources()
            java.lang.String r2 = r2.getString(r3)
            r0.setTooltipText(r2)
            android.content.Context r2 = r0.getContext()
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r3 = r4.dialogTheme
            int r3 = r3.getCloseButtonBackground()
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r0.setBackground(r2)
            goto L_0x0094
        L_0x0093:
            r0 = r1
        L_0x0094:
            r4.langChangeCloseButton = r0
            android.widget.LinearLayout r0 = r4.langChangeSwapButton
            if (r0 == 0) goto L_0x00a9
            android.content.Context r2 = r4.context
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r3 = r4.dialogTheme
            int r3 = r3.getItemBackground()
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r0.setBackground(r2)
        L_0x00a9:
            android.widget.FrameLayout r0 = r4.langChangeLayout
            if (r0 == 0) goto L_0x0109
            android.content.Context r2 = r0.getContext()
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r3 = r4.dialogTheme
            int r3 = r3.getDialogBackground()
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r0.setBackground(r2)
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_target_more_button
            android.view.View r2 = r0.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            if (r2 == 0) goto L_0x00d7
            android.graphics.drawable.Drawable r2 = r2.getDrawable()
            if (r2 == 0) goto L_0x00d7
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r3 = r4.dialogTheme
            int r3 = r3.getTextColor()
            r2.setTint(r3)
        L_0x00d7:
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_source_more_button
            android.view.View r2 = r0.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            if (r2 == 0) goto L_0x00f0
            android.graphics.drawable.Drawable r2 = r2.getDrawable()
            if (r2 == 0) goto L_0x00f0
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r3 = r4.dialogTheme
            int r3 = r3.getTextColor()
            r2.setTint(r3)
        L_0x00f0:
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_close_icon
            android.view.View r0 = r0.findViewById(r2)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            if (r0 == 0) goto L_0x0109
            android.graphics.drawable.Drawable r0 = r0.getDrawable()
            if (r0 == 0) goto L_0x0109
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r2 = r4.dialogTheme
            int r2 = r2.getTextColor()
            r0.setTint(r2)
        L_0x0109:
            android.widget.FrameLayout r0 = r4.outerLayout
            if (r0 == 0) goto L_0x0116
            int r1 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_animation_view
            android.view.View r0 = r0.findViewById(r1)
            r1 = r0
            com.airbnb.lottie.LottieAnimationView r1 = (com.airbnb.lottie.LottieAnimationView) r1
        L_0x0116:
            r4.langChangeAnimationView = r1
            if (r1 == 0) goto L_0x0123
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r0 = r4.dialogTheme
            int r0 = r0.getTranslationAutoDetectingAnimation()
            r1.setAnimation((int) r0)
        L_0x0123:
            r4.initSourceLangChangeLayout()
            r4.initTargetLangChangeLayout()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl.initLangChangeLayout():void");
    }

    /* access modifiers changed from: private */
    public static final void initLangChangeLayout$lambda$7$lambda$6(TranslateLanguageHelperImpl translateLanguageHelperImpl, View view) {
        if (j.a(translateLanguageHelperImpl.sourceLanguage, "Auto")) {
            String mostDetectedLanguage = translateLanguageHelperImpl.imageTranslator.getMostDetectedLanguage();
            String str = translateLanguageHelperImpl.targetLanguage;
            translateLanguageHelperImpl.targetLanguage = mostDetectedLanguage;
            translateLanguageHelperImpl.sourceLanguage = str;
        } else {
            String str2 = translateLanguageHelperImpl.targetLanguage;
            translateLanguageHelperImpl.targetLanguage = translateLanguageHelperImpl.sourceLanguage;
            translateLanguageHelperImpl.sourceLanguage = str2;
        }
        translateLanguageHelperImpl.imageTranslator.removeCache();
        updateLanguage$default(translateLanguageHelperImpl, false, 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void initLangChangeLayout$lambda$9$lambda$8(TranslateLanguageHelperImpl translateLanguageHelperImpl, View view) {
        translateLanguageHelperImpl.invalidateLayout(LayoutState.LANGUAGE_CODE_BUTTON);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initLangCodeButton() {
        /*
            r4 = this;
            android.widget.FrameLayout r0 = r4.outerLayout
            r1 = 0
            if (r0 == 0) goto L_0x0036
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_code_button
            android.view.View r0 = r0.findViewById(r2)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            if (r0 == 0) goto L_0x0036
            w3.b r2 = new w3.b
            r3 = 2
            r2.<init>(r4, r3)
            r0.setOnClickListener(r2)
            android.content.Context r2 = r0.getContext()
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r3 = r4.dialogTheme
            int r3 = r3.getCodeBackground()
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r0.setBackground(r2)
            Ca.c r2 = new Ca.c
            r3 = 7
            r2.<init>(r3, r0)
            r0.setOnApplyWindowInsetsListener(r2)
            r0.requestApplyInsets()
            goto L_0x0037
        L_0x0036:
            r0 = r1
        L_0x0037:
            r4.langCodeButton = r0
            if (r0 == 0) goto L_0x0044
            int r1 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_code_text
            android.view.View r0 = r0.findViewById(r1)
            r1 = r0
            android.widget.TextView r1 = (android.widget.TextView) r1
        L_0x0044:
            r4.langCodeTextView = r1
            if (r1 == 0) goto L_0x0051
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r4 = r4.dialogTheme
            int r4 = r4.getTextColor()
            r1.setTextColor(r4)
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl.initLangCodeButton():void");
    }

    /* access modifiers changed from: private */
    public static final void initLangCodeButton$lambda$3$lambda$0(TranslateLanguageHelperImpl translateLanguageHelperImpl, View view) {
        TranslateLanguageListener translateLanguageListener2 = translateLanguageHelperImpl.translateLanguageListener;
        if (translateLanguageListener2 != null) {
            translateLanguageListener2.onCodeClicked();
        }
        translateLanguageHelperImpl.updateLanguage(false);
        translateLanguageHelperImpl.invalidateLayout(LayoutState.LANGUAGE_CHANGE_LAYOUT);
    }

    /* access modifiers changed from: private */
    public static final WindowInsets initLangCodeButton$lambda$3$lambda$2(LinearLayout linearLayout, View view, WindowInsets windowInsets) {
        boolean z;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i2;
        int i7;
        j.e(view, "view");
        j.e(windowInsets, "insets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars());
        j.d(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        j.d(insets2, "getInsets(...)");
        if (linearLayout.getResources().getConfiguration().getLayoutDirection() == 0) {
            z = true;
        } else {
            z = false;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginLayoutParams = null;
        }
        if (marginLayoutParams != null) {
            int dimensionPixelSize = linearLayout.getResources().getDimensionPixelSize(R$dimen.lang_code_button_margin);
            if (linearLayout.getResources().getConfiguration().orientation == 2) {
                if (z) {
                    i2 = dimensionPixelSize + insets.right;
                    i7 = insets2.right;
                } else {
                    i2 = dimensionPixelSize + insets.left;
                    i7 = insets2.left;
                }
                marginLayoutParams.setMarginEnd(i2 + i7);
            } else {
                marginLayoutParams.setMarginEnd(dimensionPixelSize);
            }
            view.setLayoutParams(marginLayoutParams);
        }
        return windowInsets;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initSourceLangChangeLayout() {
        /*
            r4 = this;
            android.widget.FrameLayout r0 = r4.langChangeLayout
            r1 = 0
            if (r0 == 0) goto L_0x0019
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_source_layout
            android.view.View r0 = r0.findViewById(r2)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            if (r0 == 0) goto L_0x0019
            w3.c r2 = new w3.c
            r3 = 1
            r2.<init>(r4, r0, r3)
            r0.setOnClickListener(r2)
            goto L_0x001a
        L_0x0019:
            r0 = r1
        L_0x001a:
            r4.langChangeSourceLayout = r0
            if (r0 == 0) goto L_0x0027
            int r1 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_source_text
            android.view.View r0 = r0.findViewById(r1)
            r1 = r0
            android.widget.TextView r1 = (android.widget.TextView) r1
        L_0x0027:
            r4.langChangeSourceTextView = r1
            if (r1 == 0) goto L_0x0034
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r4 = r4.dialogTheme
            int r4 = r4.getTextColor()
            r1.setTextColor(r4)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl.initSourceLangChangeLayout():void");
    }

    /* access modifiers changed from: private */
    public static final void initSourceLangChangeLayout$lambda$15$lambda$14(TranslateLanguageHelperImpl translateLanguageHelperImpl, LinearLayout linearLayout, View view) {
        int[] iArr = new int[2];
        FrameLayout frameLayout = translateLanguageHelperImpl.langChangeLayout;
        if (frameLayout != null) {
            frameLayout.getLocationOnScreen(iArr);
        }
        TranslateLanguageListener translateLanguageListener2 = translateLanguageHelperImpl.translateLanguageListener;
        if (translateLanguageListener2 != null) {
            translateLanguageListener2.onSourceExpanded();
        }
        Context context2 = linearLayout.getContext();
        j.d(context2, "getContext(...)");
        LanguageSelectFragment languageSelectFragment = new LanguageSelectFragment(context2, translateLanguageHelperImpl.langPackManager, true, translateLanguageHelperImpl.sourceLanguage, translateLanguageHelperImpl.dialogTheme, translateLanguageHelperImpl.recentlyUsedLanguages.getRecentlyUsedLanguages(true), new TranslateLanguageHelperImpl$initSourceLangChangeLayout$1$1$1(translateLanguageHelperImpl));
        Context context3 = linearLayout.getContext();
        j.c(context3, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        languageSelectFragment.show(((FragmentActivity) context3).getSupportFragmentManager(), "TranslateLanguageHelper");
        Bundle bundle = new Bundle();
        bundle.putInt("ANCHOR_X", iArr[0]);
        bundle.putInt("ANCHOR_Y", iArr[1]);
        languageSelectFragment.setArguments(bundle);
        translateLanguageHelperImpl.sourceLangSelectFragment = languageSelectFragment;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initTargetLangChangeLayout() {
        /*
            r4 = this;
            android.widget.FrameLayout r0 = r4.langChangeLayout
            r1 = 0
            if (r0 == 0) goto L_0x0019
            int r2 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_target_layout
            android.view.View r0 = r0.findViewById(r2)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            if (r0 == 0) goto L_0x0019
            w3.c r2 = new w3.c
            r3 = 0
            r2.<init>(r4, r0, r3)
            r0.setOnClickListener(r2)
            goto L_0x001a
        L_0x0019:
            r0 = r1
        L_0x001a:
            r4.langChangeTargetLayout = r0
            if (r0 == 0) goto L_0x0027
            int r1 = com.samsung.android.app.sdk.deepsky.textextraction.R$id.lang_change_target_text
            android.view.View r0 = r0.findViewById(r1)
            r1 = r0
            android.widget.TextView r1 = (android.widget.TextView) r1
        L_0x0027:
            r4.langChangeTargetTextView = r1
            if (r1 == 0) goto L_0x0034
            com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.TranslateDialogTheme r4 = r4.dialogTheme
            int r4 = r4.getTextColor()
            r1.setTextColor(r4)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl.initTargetLangChangeLayout():void");
    }

    /* access modifiers changed from: private */
    public static final void initTargetLangChangeLayout$lambda$20$lambda$19(TranslateLanguageHelperImpl translateLanguageHelperImpl, LinearLayout linearLayout, View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        TranslateLanguageListener translateLanguageListener2 = translateLanguageHelperImpl.translateLanguageListener;
        if (translateLanguageListener2 != null) {
            translateLanguageListener2.onTargetExpanded();
        }
        Context context2 = linearLayout.getContext();
        j.d(context2, "getContext(...)");
        LanguageSelectFragment languageSelectFragment = new LanguageSelectFragment(context2, translateLanguageHelperImpl.langPackManager, false, translateLanguageHelperImpl.targetLanguage, translateLanguageHelperImpl.dialogTheme, translateLanguageHelperImpl.recentlyUsedLanguages.getRecentlyUsedLanguages(false), new TranslateLanguageHelperImpl$initTargetLangChangeLayout$1$1$1(translateLanguageHelperImpl));
        Context context3 = linearLayout.getContext();
        j.c(context3, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        languageSelectFragment.show(((FragmentActivity) context3).getSupportFragmentManager(), "TranslateLanguageHelper");
        Bundle bundle = new Bundle();
        bundle.putInt("ANCHOR_X", iArr[0]);
        bundle.putInt("ANCHOR_Y", iArr[1]);
        languageSelectFragment.setArguments(bundle);
        translateLanguageHelperImpl.targetLangSelectFragment = languageSelectFragment;
    }

    private final void initViewInternal() {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.langManageLayout;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        LayoutInflater.from(this.context).inflate(R$layout.translate_lang_layout, this.langManageLayout);
        LinearLayout linearLayout2 = this.langManageLayout;
        if (linearLayout2 != null) {
            frameLayout = (FrameLayout) linearLayout2.findViewById(R$id.lang_outer_layout);
        } else {
            frameLayout = null;
        }
        this.outerLayout = frameLayout;
        initLangCodeButton();
        initLangChangeLayout();
        updateLanguage(false);
    }

    private final void playAnimation() {
        if (!this.isAnimationPlayed) {
            this.isAnimationPlayed = true;
            LottieAnimationView lottieAnimationView = this.langChangeAnimationView;
            if (lottieAnimationView != null) {
                lottieAnimationView.c();
            }
        }
    }

    private final void updateLangCodeButton() {
        String str;
        if (this.targetLanguage.length() == 4) {
            str = this.targetLanguage.substring(0, 2);
            j.d(str, "substring(...)");
        } else {
            str = this.targetLanguage;
        }
        if (j.a(str, "ko")) {
            str = "kr";
        }
        LibLogger.i("TranslateLanguageHelper", "update language code button text to \"" + str + "\"");
        String displayNameFromLangCode = LanguageUtil.INSTANCE.getDisplayNameFromLangCode(this.context, this.targetLanguage);
        LinearLayout linearLayout = this.langCodeButton;
        if (linearLayout != null) {
            linearLayout.setTooltipText(displayNameFromLangCode);
            linearLayout.setContentDescription(displayNameFromLangCode);
        }
        TextView textView = this.langCodeTextView;
        if (textView != null) {
            String upperCase = str.toUpperCase(Locale.ROOT);
            j.d(upperCase, "toUpperCase(...)");
            textView.setText(upperCase);
        }
    }

    private final void updateLanguage(boolean z) {
        updateSourceLanguage();
        updateTargetLanguage();
        updateLangCodeButton();
        this.recentlyUsedLanguages.updateLanguages(this.sourceLanguage, this.targetLanguage);
        LanguageChangeListener languageChangeListener2 = this.languageChangeListener;
        if (languageChangeListener2 != null) {
            languageChangeListener2.onLanguageChanged(z);
        }
    }

    public static /* synthetic */ void updateLanguage$default(TranslateLanguageHelperImpl translateLanguageHelperImpl, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = true;
        }
        translateLanguageHelperImpl.updateLanguage(z);
    }

    private final void updateSourceLanguage() {
        String displaySourceLanguage = getDisplaySourceLanguage();
        LibLogger.i("TranslateLanguageHelper", "update source language to \"" + displaySourceLanguage + "\"");
        TextView textView = this.langChangeSourceTextView;
        if (textView != null) {
            textView.setText(LanguageUtil.INSTANCE.getDisplayNameFromLangCode(this.context, displaySourceLanguage));
        }
        this.imageTranslator.setSourceLanguage(this.sourceLanguage);
    }

    private final void updateTargetLanguage() {
        String str = this.targetLanguage;
        LibLogger.i("TranslateLanguageHelper", "update target language to \"" + str + "\"");
        TextView textView = this.langChangeTargetTextView;
        if (textView != null) {
            textView.setText(LanguageUtil.INSTANCE.getDisplayNameFromLangCode(this.context, this.targetLanguage));
        }
        this.imageTranslator.setTargetLanguage(this.targetLanguage);
    }

    public void initLayout(LinearLayout linearLayout) {
        j.e(linearLayout, "layout");
        LibLogger.i("TranslateLanguageHelper", "initialize TranslateLanguageHelper layout");
        this.langManageLayout = linearLayout;
        this.isAnimationPlayed = false;
        initViewInternal();
        invalidateLayout(LayoutState.HIDDEN);
    }

    public void invalidateLayout(LayoutState layoutState2) {
        j.e(layoutState2, "state");
        LibLogger.i("TranslateLanguageHelper", "invalidateLayout with state " + layoutState2);
        this.layoutState = layoutState2;
        if (this.langManageLayout != null) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[layoutState2.ordinal()];
            if (i2 == 1) {
                LinearLayout linearLayout = this.langCodeButton;
                if (linearLayout != null) {
                    linearLayout.setVisibility(4);
                }
                FrameLayout frameLayout = this.langChangeLayout;
                if (frameLayout != null) {
                    frameLayout.setVisibility(4);
                }
                cancelAnimation();
            } else if (i2 == 2) {
                FrameLayout frameLayout2 = this.langChangeLayout;
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(4);
                }
                LinearLayout linearLayout2 = this.langCodeButton;
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(0);
                }
                cancelAnimation();
            } else if (i2 == 3) {
                LinearLayout linearLayout3 = this.langCodeButton;
                if (linearLayout3 != null) {
                    linearLayout3.setVisibility(4);
                }
                FrameLayout frameLayout3 = this.langChangeLayout;
                if (frameLayout3 != null) {
                    frameLayout3.setVisibility(0);
                }
                playAnimation();
            } else {
                throw new RuntimeException();
            }
        }
    }

    public void onConfigurationChanged() {
        if (this.langManageLayout != null) {
            initViewInternal();
            invalidateLayout(this.layoutState);
        }
    }

    public void setListener(TranslateLanguageListener translateLanguageListener2) {
        j.e(translateLanguageListener2, "listener");
        this.translateLanguageListener = translateLanguageListener2;
    }
}
