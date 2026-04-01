package com.samsung.android.gallery.module.lottie.recap.binder;

import B8.b;
import android.text.TextUtils;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum RecapDataVars {
    YEAR_NUM_YYYY((String) new d(0)),
    YEAR_NUM_YYYY_RECAP((String) new d(2)),
    MONTH_MMMM_RECAP((String) new d(8)),
    YEAR_SPOTLIGHT_ON_NUM_YYYY((String) new d(14)),
    MONTH_SPOTLIGHT_ON_MMMM((String) new d(19)),
    YEAR_NUM_AND_N_QUARTER((String) new d(21)),
    YEAR_CAPTURED_IN_YYYY((String) new d(22)),
    MONTH_CAPTURED_IN_MMMM((String) new d(23)),
    BEST_MOMENTS_IN_YYYY((String) new d(24)),
    BEST_MOMENTS_IN_MMMM((String) new d(25)),
    PLACES_IN_YYYY((String) new d(7)),
    QUARTER_RANGE_YYYY_N_MMM_MMM((String) new d(10)),
    QUARTER_RANGE_MMM_MMM((String) new d(20)),
    QUARTER_RANGE_MMM_MMM_YEAR_RECAP((String) new d(26)),
    IMAGES_COUNT((String) new d(27)),
    VIDEOS_COUNT((String) new d(28)),
    FILES_COUNT((String) new d(29)),
    PEOPLE_AND_PET_COUNT((String) new f(0)),
    LOCATION_COUNT((String) new f(1)),
    TOTAL_DAYS((String) new d(1)),
    MOST_ACTIVE_DAY_MMDD((String) new d(3)),
    MOST_ACTIVE_DAY_MMM((String) new d(4)),
    FROM_MMM((String) new d(5)),
    FROM_MMMM((String) new d(6)),
    FILE_MONTH_FULL_TEXT((String) new e(0)),
    FILE_YEAR_YYYY_NUM((String) new e(1)),
    FILE_MMDD_NUM((String) new e(2)),
    FILE_YYYY_MM_DD_NUM((String) new e(3)),
    FILE_DAY_SIMPLE_ENG_TEXT((String) new e(4)),
    WITH_FILE_0_PEOPLE_NAME((String) new e(5)),
    FILE_0_PEOPLE_NAME((String) new e(6)),
    FILE_1_PEOPLE_NAME((String) new e(7)),
    FILE_2_PEOPLE_NAME((String) new e(8)),
    PLACE_NAME_CAPITAL((String) new d(9)),
    PLACE_NAME_MAX13((String) new e(9)),
    BEST_PLACE_NAME_CAPITAL((String) new e(10)),
    FILE_PLACE_NAME_CAPITAL((String) new e(11)),
    VISITED_FOR_DD_IN_YYYY((String) new d(11)),
    VISITED_DD_DAYS((String) new d(12)),
    NEW_LOCATION_COUNT((String) new d(13)),
    TOTAL_PLACE_COUNT((String) new d(15)),
    TOTAL_PLACE_COUNT_PLACES((String) new d(16)),
    STORY_TITLE((String) new e(12)),
    STORY_DURATION((String) new d(17)),
    __END__((String) new d(18));
    
    boolean commaSeparatedFormat;
    String defaultValue;
    String jsonVar;
    int stringFormatId;
    public Function<AnalyzedData, String> supplier;
    public BiFunction<AnalyzedData, RecapTextLayer, String> supplier2;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Constants {
        static ArrayList<String> mPlaces;

        static {
            mPlaces = new ArrayList<>();
        }

        public static void buildPlaces(ArrayList<String> arrayList) {
            if (arrayList == null || arrayList.isEmpty()) {
                mPlaces.add("Favorite Places");
                mPlaces.add("Year in Places");
                mPlaces.add("Best Places");
                mPlaces.add("Journey Log");
                mPlaces.add("Travel Spot");
                mPlaces.add("My Journey");
                mPlaces.add("Day in Life");
                mPlaces.add("Year Highlights");
                mPlaces.add("Year in Photos");
                mPlaces.add("Life in Photos");
                mPlaces.add("Snapshots");
                mPlaces.add("Photo Diary");
                mPlaces.add("Special moment");
                return;
            }
            mPlaces.addAll(arrayList);
        }
    }

    private RecapDataVars(Function<AnalyzedData, String> function) {
        this.supplier = function;
    }

    public static String formatWithCommas(String str) {
        StringBuilder sb2 = new StringBuilder(str);
        for (int length = sb2.length() - 3; length > 0; length -= 3) {
            sb2.insert(length, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        return sb2.toString();
    }

    private static long getImageTime(RecapTextLayer recapTextLayer) {
        return getTargetFile(recapTextLayer).mUsedItem.getDateLocal();
    }

    /* access modifiers changed from: private */
    public static String getJsonVar(AnalyzedData analyzedData, String str, String str2) {
        String optString = analyzedData.optString(str, str2);
        if ("__EmPtY__".equals(optString)) {
            optString = " ";
        }
        if (!TextUtils.isEmpty(optString)) {
            return optString;
        }
        return " ";
    }

    private static String getQuantityString(int i2, int i7, Object... objArr) {
        return AppResources.getQuantityString(i2, i7, objArr);
    }

    /* access modifiers changed from: private */
    public static String getString(int i2, Object... objArr) {
        return AppResources.getString(i2, objArr);
    }

    private static RecapImage getTargetFile(RecapTextLayer recapTextLayer) {
        RecapImage orElse = recapTextLayer.parent.mImages.values().stream().min(Comparator.comparingInt(new b(4))).orElse((Object) null);
        if (orElse != null && orElse.mUsedItem != null) {
            return orElse;
        }
        throw new IllegalStateException("getTargetFile fail. " + orElse);
    }

    /* access modifiers changed from: private */
    public static void updatePeopleName(RecapTextLayer recapTextLayer, int i2) {
        String str;
        RecapImage orElse = recapTextLayer.parent.mImages.values().stream().filter(new b(i2, 12)).findFirst().orElse((Object) null);
        if (orElse == null || (str = orElse.peopleName) == null) {
            recapTextLayer.targetValue = " ";
            return;
        }
        String subStringByDrawWidth = StringCompat.subStringByDrawWidth(str, (float) recapTextLayer.fontSp, (float) recapTextLayer.maxWidthDp, recapTextLayer.noEllipsis);
        recapTextLayer.targetValue = subStringByDrawWidth;
        if (recapTextLayer.useFirstNameOnly && subStringByDrawWidth.contains(" ")) {
            recapTextLayer.targetValue = recapTextLayer.targetValue.split(" ")[0];
        }
    }

    public boolean isPeople() {
        if (equals(FILE_0_PEOPLE_NAME) || equals(FILE_1_PEOPLE_NAME) || equals(FILE_2_PEOPLE_NAME) || equals(WITH_FILE_0_PEOPLE_NAME)) {
            return true;
        }
        return false;
    }

    private RecapDataVars(BiFunction<AnalyzedData, RecapTextLayer, String> biFunction) {
        this.supplier2 = biFunction;
    }
}
