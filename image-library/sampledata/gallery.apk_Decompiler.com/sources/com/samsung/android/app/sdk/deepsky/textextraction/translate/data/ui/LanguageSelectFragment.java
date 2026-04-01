package com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui;

import B1.a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.DrawUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.ViewHelperUtils;
import com.samsung.android.vexfwk.imagetranslation.LanguagePackInstaller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1196n;
import ne.y;
import o5.C0496a;
import u4.C0518a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 D2\u00020\u0001:\u0001DBG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0017\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u001b\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001c\u0010\u0016J\u000f\u0010\u001d\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u001d\u0010\u0019J\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b!\u0010\"J-\u0010)\u001a\u0004\u0018\u00010\u00122\u0006\u0010$\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010%2\b\u0010(\u001a\u0004\u0018\u00010'H\u0016¢\u0006\u0004\b)\u0010*J\u0019\u0010,\u001a\u00020+2\b\u0010(\u001a\u0004\u0018\u00010'H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u00100\u001a\u00020\u00142\u0006\u0010/\u001a\u00020.H\u0016¢\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u00020\u00142\u0006\u00103\u001a\u000202H\u0016¢\u0006\u0004\b4\u00105R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00106R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00107R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u00108R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u00109R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010:R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\r\u0010;R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010?\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0004¢\u0006\u0006\n\u0004\bB\u0010C¨\u0006E"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/LanguageSelectFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "langPackManager", "", "isSourceLanguageSelection", "", "currentSelectedLanguage", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;", "dialogTheme", "", "recentlyUsedLanguages", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/LanguageSelectListener;", "onLanguageSelectListener", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;ZLjava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;Ljava/util/List;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/LanguageSelectListener;)V", "Landroid/view/View;", "view", "Lme/x;", "updateDialogLocation", "(Landroid/view/View;)V", "setContent", "addAutoAndRecentlyUsedLanguages", "()V", "addDivider", "addInstalledLanguages", "addAddLanguageButton", "checkSelectedLanguage", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "lang", "Landroid/widget/LinearLayout;", "createLanguageItem", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;)Landroid/widget/LinearLayout;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;", "Landroid/app/Dialog;", "onCreateDialog", "(Landroid/os/Bundle;)Landroid/app/Dialog;", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "Landroid/content/DialogInterface;", "dialog", "onDismiss", "(Landroid/content/DialogInterface;)V", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "Z", "Ljava/lang/String;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;", "Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/LanguageSelectListener;", "selectedLanguage", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "selectableLanguageGroup", "Landroid/widget/LinearLayout;", "Landroid/content/res/ColorStateList;", "languageColorStateList", "Landroid/content/res/ColorStateList;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LanguageSelectFragment extends DialogFragment {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private final String currentSelectedLanguage;
    private final TranslateDialogTheme dialogTheme;
    private final boolean isSourceLanguageSelection;
    private final LangPackManager langPackManager;
    private final ColorStateList languageColorStateList;
    private final LanguageSelectListener onLanguageSelectListener;
    private final List<String> recentlyUsedLanguages;
    private LinearLayout selectableLanguageGroup;
    private OnDeviceLanguage selectedLanguage;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/LanguageSelectFragment$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public LanguageSelectFragment(Context context2, LangPackManager langPackManager2, boolean z, String str, TranslateDialogTheme translateDialogTheme, List<String> list, LanguageSelectListener languageSelectListener) {
        j.e(context2, "context");
        j.e(langPackManager2, "langPackManager");
        j.e(str, "currentSelectedLanguage");
        j.e(translateDialogTheme, "dialogTheme");
        j.e(list, "recentlyUsedLanguages");
        this.context = context2;
        this.langPackManager = langPackManager2;
        this.isSourceLanguageSelection = z;
        this.currentSelectedLanguage = str;
        this.dialogTheme = translateDialogTheme;
        this.recentlyUsedLanguages = list;
        this.onLanguageSelectListener = languageSelectListener;
        this.languageColorStateList = new ColorStateList(new int[][]{new int[]{16842913}, new int[]{-16842913}}, new int[]{context2.getColor(R$color.lang_select_checked_button_color), translateDialogTheme.getTextColor()});
    }

    private final void addAddLanguageButton(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.lang_select_add_languages_button);
        linearLayout.setOnClickListener(new C0496a(27, linearLayout));
    }

    /* access modifiers changed from: private */
    public static final void addAddLanguageButton$lambda$8$lambda$7(LinearLayout linearLayout, View view) {
        Intent intent = new Intent(LanguagePackInstaller.LANGUAGE_PACK_SETTINGS_ACTION);
        intent.putExtra("package", linearLayout.getContext().getPackageName());
        linearLayout.getContext().startActivity(intent);
    }

    private final void addAutoAndRecentlyUsedLanguages() {
        LinearLayout linearLayout;
        if (this.isSourceLanguageSelection && (linearLayout = this.selectableLanguageGroup) != null) {
            linearLayout.addView(createLanguageItem(OnDeviceLanguage.Companion.getAUTO()));
        }
        for (String onDeviceLanguage : this.recentlyUsedLanguages) {
            OnDeviceLanguage onDeviceLanguage2 = new OnDeviceLanguage(onDeviceLanguage, "", (String) null, (String) null, false, (String) null, 60, (e) null);
            LinearLayout linearLayout2 = this.selectableLanguageGroup;
            if (linearLayout2 != null) {
                linearLayout2.addView(createLanguageItem(onDeviceLanguage2));
            }
        }
    }

    private final void addDivider() {
        LinearLayout linearLayout = this.selectableLanguageGroup;
        if (linearLayout != null) {
            View view = new View(this.context);
            view.setBackgroundResource(R$drawable.line_dotted);
            DrawUtil drawUtil = DrawUtil.INSTANCE;
            Context context2 = view.getContext();
            j.d(context2, "getContext(...)");
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(-1, drawUtil.convertDpToPx(context2, Float.valueOf(8.0f)));
            Resources resources = view.getContext().getResources();
            int i2 = R$dimen.language_dialog_divider_margin_horizontal;
            int dimensionPixelSize = resources.getDimensionPixelSize(i2);
            Resources resources2 = view.getContext().getResources();
            int i7 = R$dimen.language_dialog_divider_margin_vertical;
            layoutParams.setMargins(dimensionPixelSize, resources2.getDimensionPixelSize(i7), view.getContext().getResources().getDimensionPixelSize(i2), view.getContext().getResources().getDimensionPixelSize(i7));
            view.setLayoutParams(layoutParams);
            linearLayout.addView(view);
        }
    }

    private final void addInstalledLanguages() {
        LinearLayout linearLayout;
        for (OnDeviceLanguage next : this.langPackManager.getAllInstalledOnDeviceLangList()) {
            if (!this.recentlyUsedLanguages.contains(next.getCode()) && (linearLayout = this.selectableLanguageGroup) != null) {
                linearLayout.addView(createLanguageItem(next));
            }
        }
    }

    private final void checkSelectedLanguage() {
        LinearLayout linearLayout = this.selectableLanguageGroup;
        if (linearLayout != null) {
            Ge.e Z = a.Z(0, linearLayout.getChildCount());
            ArrayList arrayList = new ArrayList(C1196n.w0(Z, 10));
            Iterator it = Z.iterator();
            while (it.hasNext()) {
                arrayList.add(linearLayout.getChildAt(((y) it).nextInt()));
            }
            ArrayList<LinearLayout> arrayList2 = new ArrayList<>();
            for (Object next : arrayList) {
                if (next instanceof LinearLayout) {
                    arrayList2.add(next);
                }
            }
            ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList2, 10));
            for (LinearLayout linearLayout2 : arrayList2) {
                linearLayout2.setBackground(this.context.getResources().getDrawable(this.dialogTheme.getItemBackground(), (Resources.Theme) null));
                TextView textView = (TextView) linearLayout2.findViewById(R$id.language_item_text);
                if (j.a(textView.getTag(), this.currentSelectedLanguage)) {
                    textView.setTextColor(getResources().getColor(R$color.lang_select_checked_button_color));
                    ((ImageView) linearLayout2.findViewById(R$id.language_item_select_checkmark)).setVisibility(0);
                }
                arrayList3.add(x.f4917a);
            }
        }
    }

    private final LinearLayout createLanguageItem(OnDeviceLanguage onDeviceLanguage) {
        String str;
        int i2;
        boolean a7 = j.a(onDeviceLanguage.getCode(), this.currentSelectedLanguage);
        View inflate = LayoutInflater.from(this.context).inflate(R$layout.language_item, (ViewGroup) null);
        j.c(inflate, "null cannot be cast to non-null type android.widget.LinearLayout");
        LinearLayout linearLayout = (LinearLayout) inflate;
        TextView textView = (TextView) linearLayout.findViewById(R$id.language_item_text);
        LanguageUtil languageUtil = LanguageUtil.INSTANCE;
        Context context2 = textView.getContext();
        j.d(context2, "getContext(...)");
        textView.setText(languageUtil.getDisplayNameFromLangCode(context2, onDeviceLanguage.getCode()));
        textView.setTag(onDeviceLanguage.getCode());
        if (a7) {
            str = textView.getContext().getString(R$string.selected);
        } else {
            str = textView.getContext().getString(R$string.not_selected);
        }
        j.b(str);
        CharSequence text = textView.getText();
        textView.setContentDescription(text + ArcCommonLog.TAG_COMMA + str);
        if (a7) {
            i2 = linearLayout.getResources().getColor(R$color.lang_select_checked_button_color);
        } else {
            i2 = linearLayout.getResources().getColor(R$color.textextraction_dialog_content_action_text_color);
        }
        textView.setTextColor(i2);
        linearLayout.setOnClickListener(new C0518a(1, this, onDeviceLanguage));
        ViewHelperUtils.INSTANCE.addActionAccessibilityDelegate(linearLayout, a7);
        return linearLayout;
    }

    /* access modifiers changed from: private */
    public static final void createLanguageItem$lambda$15$lambda$14(LanguageSelectFragment languageSelectFragment, OnDeviceLanguage onDeviceLanguage, View view) {
        languageSelectFragment.selectedLanguage = onDeviceLanguage;
        languageSelectFragment.dismiss();
    }

    private final void setContent(View view) {
        this.selectableLanguageGroup = (LinearLayout) view.findViewById(R$id.lang_select_group);
        addAutoAndRecentlyUsedLanguages();
        addDivider();
        addInstalledLanguages();
        checkSelectedLanguage();
        addAddLanguageButton(view);
        updateDialogLocation(view);
    }

    private final void updateDialogLocation(View view) {
        WindowManager.LayoutParams layoutParams;
        Window window;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.language_select_dialog_inner_layout);
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R$dimen.language_select_popup_height);
        linearLayout.measure(0, 0);
        if (linearLayout.getMeasuredHeight() > dimensionPixelSize) {
            linearLayout.getLayoutParams().height = dimensionPixelSize;
            linearLayout.requestLayout();
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            Dialog dialog = getDialog();
            if (dialog == null || (window = dialog.getWindow()) == null) {
                layoutParams = null;
            } else {
                layoutParams = window.getAttributes();
            }
            if (layoutParams != null) {
                layoutParams.x = arguments.getInt("ANCHOR_X") - this.context.getResources().getDimensionPixelSize(R$dimen.language_dialog_margin);
            }
            if (layoutParams != null) {
                layoutParams.y = this.context.getResources().getDimensionPixelSize(R$dimen.language_select_popup_height_offset) + (arguments.getInt("ANCHOR_Y") - Math.min(linearLayout.getMeasuredHeight(), dimensionPixelSize));
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        j.e(configuration, "newConfig");
        LibLogger.i("LanguageSelectFragment", "onConfigurationChanged called");
        dismiss();
        super.onConfigurationChanged(configuration);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(this.context);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(8388659);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.requestFeature(1);
            window.addFlags(256);
        }
        return dialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        j.e(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R$layout.language_select_dialog_popup, viewGroup, false);
        ((LinearLayout) inflate.findViewById(R$id.language_select_dialog_inner_layout)).setBackground(inflate.getContext().getDrawable(this.dialogTheme.getDialogBackground()));
        ((LinearLayout) inflate.findViewById(R$id.lang_select_add_languages_button)).setBackground(inflate.getContext().getDrawable(this.dialogTheme.getItemBackground()));
        ((TextView) inflate.findViewById(R$id.add_languages_text_view)).setTextColor(this.dialogTheme.getTextColor());
        setContent(inflate);
        return inflate;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        j.e(dialogInterface, "dialog");
        LanguageSelectListener languageSelectListener = this.onLanguageSelectListener;
        if (languageSelectListener != null) {
            languageSelectListener.onLanguageSelected(this.selectedLanguage);
        }
        super.onDismiss(dialogInterface);
    }
}
