package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.LanguageSelectListener;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelperImpl$initTargetLangChangeLayout$1$1$1", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/LanguageSelectListener;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "selectedLanguage", "Lme/x;", "onLanguageSelected", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TranslateLanguageHelperImpl$initTargetLangChangeLayout$1$1$1 implements LanguageSelectListener {
    final /* synthetic */ TranslateLanguageHelperImpl this$0;

    public TranslateLanguageHelperImpl$initTargetLangChangeLayout$1$1$1(TranslateLanguageHelperImpl translateLanguageHelperImpl) {
        this.this$0 = translateLanguageHelperImpl;
    }

    public void onLanguageSelected(OnDeviceLanguage onDeviceLanguage) {
        String str;
        String access$getTargetLanguage$p = this.this$0.targetLanguage;
        TranslateLanguageHelperImpl translateLanguageHelperImpl = this.this$0;
        if (onDeviceLanguage == null || (str = onDeviceLanguage.getCode()) == null) {
            str = this.this$0.targetLanguage;
        }
        translateLanguageHelperImpl.targetLanguage = str;
        if (!j.a(access$getTargetLanguage$p, this.this$0.targetLanguage)) {
            this.this$0.imageTranslator.removeCache();
        }
        TranslateLanguageHelperImpl.updateLanguage$default(this.this$0, false, 1, (Object) null);
        TranslateLanguageListener access$getTranslateLanguageListener$p = this.this$0.translateLanguageListener;
        if (access$getTranslateLanguageListener$p != null) {
            access$getTranslateLanguageListener$p.onTargetSelected(this.this$0.targetLanguage);
        }
    }
}
