package com.samsung.android.gallery.module.lottie.recap.data.parser;

import com.samsung.android.gallery.support.utils.Log;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecapDataCompat {
    private static final CharSequence TAG = "RecapDataCompat";

    private static RecapDataParser createParser(int i2) {
        if (i2 == 0) {
            return new RecapDataParserImplV0();
        }
        return null;
    }

    public static AnalyzedData parse(JSONObject jSONObject) {
        RecapDataParser createParser;
        int optInt = jSONObject.optInt("version");
        RecapDataParser createParser2 = createParser(optInt);
        Log.i(TAG, "input v=" + optInt + ", p=" + createParser2);
        if (createParser2 != null) {
            return createParser2.parse(jSONObject);
        }
        int i2 = optInt;
        do {
            i2--;
            if (i2 >= 0) {
                try {
                    createParser = createParser(i2);
                } catch (Error | Exception e) {
                    Log.e(TAG, "fail parse with compat : " + e);
                }
            } else {
                Log.i(TAG, "fail. v=" + optInt);
                return null;
            }
        } while (createParser == null);
        Log.i(TAG, "try parse with v=" + optInt + ", downV=" + i2 + ", compat p=" + createParser);
        return createParser.parse(jSONObject);
    }
}
