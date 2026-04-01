package com.samsung.android.gallery.module.lottie.recap.binder;

import c0.C0086a;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3032a;

    public /* synthetic */ d(int i2) {
        this.f3032a = i2;
    }

    public final Object apply(Object obj) {
        AnalyzedData analyzedData = (AnalyzedData) obj;
        switch (this.f3032a) {
            case 0:
                return RecapDataVars.getJsonVar(analyzedData, "year", (String) null);
            case 1:
                return RecapDataVars.getJsonVar(analyzedData, "totalDays", (String) null);
            case 2:
                return RecapDataVars.getString(R$string.pd_n_recap, Integer.valueOf(analyzedData.year));
            case 3:
                return analyzedData.mostActiveDayBasicIsoDate.substring(4, 8);
            case 4:
                return RecapDataVars.lambda$static$21(analyzedData);
            case 5:
                return analyzedData.fromMonthAbbrString;
            case 6:
                return analyzedData.fromMonthFullString;
            case 7:
                return RecapDataVars.getString(R$string.recap_places_in_yyyy, Integer.valueOf(analyzedData.year));
            case 8:
                return RecapDataVars.getString(R$string.ps_n_recap, analyzedData.fromMonthFullString);
            case 9:
                return RecapDataVars.lambda$static$33(analyzedData);
            case 10:
                return String.format(Locale.getDefault(), "%d\n%s-%s", new Object[]{Integer.valueOf(analyzedData.year), analyzedData.fromMonthAbbrString, analyzedData.toMonthAbbrString});
            case 11:
                return RecapDataVars.lambda$static$37(analyzedData);
            case 12:
                return RecapDataVars.lambda$static$38(analyzedData);
            case 13:
                return C0086a.l(new StringBuilder(), analyzedData.newLocationCount, "");
            case 14:
                return RecapDataVars.getString(R$string.recap_y_spotlight_on_pd, Integer.valueOf(analyzedData.year));
            case 15:
                return C0086a.l(new StringBuilder(), analyzedData.totalPlaceCount, "");
            case 16:
                return C0086a.l(new StringBuilder(), analyzedData.totalPlaceCount, "");
            case 17:
                return analyzedData.optString("story_duration", " ");
            case 18:
                return RecapDataVars.lambda$static$44(analyzedData);
            case 19:
                return RecapDataVars.getString(R$string.recap_y_spotlight_on_ps, analyzedData.fromMonthFullString);
            case 20:
                return Locale.getDefault();
            case 21:
                return RecapDataVars.lambda$static$5(analyzedData);
            case 22:
                return RecapDataVars.getString(R$string.recap_y_captured_in_xx, Integer.valueOf(analyzedData.year));
            case 23:
                return RecapDataVars.getString(R$string.recap_y_captured_in_ps, analyzedData.fromMonthFullString);
            case 24:
                return RecapDataVars.getString(R$string.recap_y_key_header, Integer.valueOf(analyzedData.year));
            case 25:
                return RecapDataVars.getString(R$string.recap_y_key_header_ps, analyzedData.fromMonthFullString);
            case 26:
                return RecapDataVars.getString(R$string.recap_q_closing_range, analyzedData.fromMonthAbbrString, analyzedData.toMonthAbbrString, Integer.valueOf(analyzedData.year));
            case 27:
                return RecapDataVars.formatWithCommas(RecapDataVars.getJsonVar(analyzedData, "totalImages", (String) null));
            case 28:
                return RecapDataVars.formatWithCommas(RecapDataVars.getJsonVar(analyzedData, "totalVideos", "0"));
            default:
                return C0086a.l(new StringBuilder(), analyzedData.totalContents, "");
        }
    }
}
