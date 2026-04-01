package f3;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.DeepSky;
import com.samsung.android.app.sdk.deepsky.textextraction.TextExtractionProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Ae.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ a(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public final Object invoke() {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 0:
                return DeepSky.suggestionRequestByLazy_delegate$lambda$1(context);
            case 1:
                return DeepSky.donationByLazy_delegate$lambda$3(context);
            case 2:
                return DeepSky.contributionByLazy_delegate$lambda$5(context);
            case 3:
                return DeepSky.nudgeSuggestionByLazy_delegate$lambda$7(context);
            case 4:
                return DeepSky.searchByLazy_delegate$lambda$9(context);
            case 5:
                return DeepSky.smartWidgetByLazy_delegate$lambda$11(context);
            default:
                return TextExtractionProvider.textExtractionByLazy_delegate$lambda$2(context);
        }
    }
}
