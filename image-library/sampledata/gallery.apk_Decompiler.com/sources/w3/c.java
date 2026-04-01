package w3;

import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TranslateLanguageHelperImpl e;
    public final /* synthetic */ LinearLayout f;

    public /* synthetic */ c(TranslateLanguageHelperImpl translateLanguageHelperImpl, LinearLayout linearLayout, int i2) {
        this.d = i2;
        this.e = translateLanguageHelperImpl;
        this.f = linearLayout;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                TranslateLanguageHelperImpl.initTargetLangChangeLayout$lambda$20$lambda$19(this.e, this.f, view);
                return;
            default:
                TranslateLanguageHelperImpl.initSourceLangChangeLayout$lambda$15$lambda$14(this.e, this.f, view);
                return;
        }
    }
}
