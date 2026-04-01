package com.samsung.android.gallery.module.lottie.recap.data.parser;

import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecapDataParser {
    protected final CharSequence TAG = getClass().getSimpleName();
    AnalyzedData d;

    public abstract AnalyzedData parse(JSONObject jSONObject);
}
