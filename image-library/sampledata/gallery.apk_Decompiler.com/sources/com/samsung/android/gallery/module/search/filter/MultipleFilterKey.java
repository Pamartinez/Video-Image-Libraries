package com.samsung.android.gallery.module.search.filter;

import android.text.TextUtils;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MultipleFilterKey {
    public static String build(String str, FilterResultsEntity filterResultsEntity) {
        String str2;
        boolean isOnlyThemActivated = isOnlyThemActivated(str);
        String multipleSelectedFilter = getMultipleSelectedFilter(ArgumentsUtil.getArgValue(str, "SelectedFilter", (String) null), filterResultsEntity, isOnlyThemActivated);
        if (TextUtils.isEmpty(multipleSelectedFilter)) {
            str2 = ArgumentsUtil.removeArg(str, "SelectedFilter");
        } else {
            str2 = ArgumentsUtil.appendArgs(str, "SelectedFilter", multipleSelectedFilter);
        }
        String str3 = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
        String appendArgs = ArgumentsUtil.appendArgs(str2, "RefreshFilterResults", str3);
        if (!filterResultsEntity.isOnlyThem()) {
            return appendArgs;
        }
        if (isOnlyThemActivated) {
            str3 = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        }
        return ArgumentsUtil.appendArgs(appendArgs, "people_only_them", str3);
    }

    private static String getMultipleSelectedFilter(String str, FilterResultsEntity filterResultsEntity, boolean z) {
        if (!filterResultsEntity.isOnlyThem() || !z) {
            return SearchFilterUtil.addSelectedFilter(str, filterResultsEntity);
        }
        return SearchFilterUtil.removeSelectedFilter(str, filterResultsEntity);
    }

    private static boolean isOnlyThemActivated(String str) {
        return ArgumentsUtil.getArgValue(str, "people_only_them", false);
    }

    public static String removeEntity(String str, FilterResultsEntity filterResultsEntity) {
        String removeSelectedFilter = SearchFilterUtil.removeSelectedFilter(ArgumentsUtil.getArgValue(str, "SelectedFilter", (String) null), filterResultsEntity);
        String removeArg = ArgumentsUtil.removeArg(str, "SelectedFilter");
        if (!TextUtils.isEmpty(removeSelectedFilter)) {
            return ArgumentsUtil.appendArgs(removeArg, "SelectedFilter", removeSelectedFilter);
        }
        return removeArg;
    }
}
