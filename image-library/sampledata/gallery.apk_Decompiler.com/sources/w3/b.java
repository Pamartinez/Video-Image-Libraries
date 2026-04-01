package w3;

import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TranslateLanguageHelperImpl e;

    public /* synthetic */ b(TranslateLanguageHelperImpl translateLanguageHelperImpl, int i2) {
        this.d = i2;
        this.e = translateLanguageHelperImpl;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        TranslateLanguageHelperImpl translateLanguageHelperImpl = this.e;
        switch (i2) {
            case 0:
                TranslateLanguageHelperImpl.initLangChangeLayout$lambda$7$lambda$6(translateLanguageHelperImpl, view);
                return;
            case 1:
                TranslateLanguageHelperImpl.initLangChangeLayout$lambda$9$lambda$8(translateLanguageHelperImpl, view);
                return;
            default:
                TranslateLanguageHelperImpl.initLangCodeButton$lambda$3$lambda$0(translateLanguageHelperImpl, view);
                return;
        }
    }
}
