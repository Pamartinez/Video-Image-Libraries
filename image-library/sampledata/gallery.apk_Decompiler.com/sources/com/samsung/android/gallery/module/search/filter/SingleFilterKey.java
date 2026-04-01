package com.samsung.android.gallery.module.search.filter;

import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SingleFilterKey {
    public static String build(String str, FilterResultsEntity filterResultsEntity, int i2) {
        String str2;
        if (i2 > 0) {
            str2 = ArgumentsUtil.appendArgs(str, "SelectedFilter", getSelectedFilterObject(filterResultsEntity).toString());
        } else {
            str2 = ArgumentsUtil.removeArg(str, "SelectedFilter");
        }
        return ArgumentsUtil.appendArgs(str2, "RefreshFilterResults", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
    }

    private static JSONObject getSelectedFilterObject(FilterResultsEntity filterResultsEntity) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", filterResultsEntity.getName());
            jSONObject.put("selection", filterResultsEntity.getSelection());
            jSONObject.put("selection_args", filterResultsEntity.getSelectionArgs());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }
}
