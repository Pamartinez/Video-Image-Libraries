package com.samsung.android.gallery.module.lottie.recap.template.functions;

import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateColorChanger {
    HashMap<String, RecapBgLayer> mBackgrounds;
    HashMap<String, RecapTextLayer> mDynamicColorText;

    public RecapTemplateColorChanger(HashMap<String, RecapBgLayer> hashMap, HashMap<String, RecapTextLayer> hashMap2) {
        this.mBackgrounds = hashMap;
        this.mDynamicColorText = hashMap2;
    }

    private void updateSolidLayer(JSONObject jSONObject) {
        RecapBgLayer recapBgLayer = this.mBackgrounds.get(jSONObject.getString("nm"));
        if (recapBgLayer != null) {
            jSONObject.put("sc", recapBgLayer.targetColor);
        }
    }

    private void updateTextLayer(JSONObject jSONObject) {
        String string = jSONObject.getString("nm");
        RecapTextLayer recapTextLayer = this.mDynamicColorText.get(string);
        if (recapTextLayer != null) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("t").getJSONObject("d").getJSONArray("k").getJSONObject(0).getJSONObject("s");
            JSONArray fcColorArray = getFcColorArray(recapTextLayer.targetColor);
            jSONObject2.put("fc", fcColorArray);
            Log.i("RecapColorChanger", "update fc color for " + string + " " + recapTextLayer.targetColor + "/" + fcColorArray);
        }
    }

    public JSONArray getFcColorArray(String str) {
        JSONArray jSONArray = new JSONArray();
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        int parseInt = Integer.parseInt(str.substring(0, 2), 16);
        int parseInt2 = Integer.parseInt(str.substring(2, 4), 16);
        int parseInt3 = Integer.parseInt(str.substring(4, 6), 16);
        jSONArray.put((double) (((float) parseInt) / 255.0f));
        jSONArray.put((double) (((float) parseInt2) / 255.0f));
        jSONArray.put((double) (((float) parseInt3) / 255.0f));
        return jSONArray;
    }

    public String scan(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            updateLayerAttributes((JSONArray) jSONObject.get("layers"));
            JSONArray jSONArray = (JSONArray) jSONObject.get("assets");
            int length = jSONArray.length();
            int i2 = 0;
            while (i2 < length) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    if (jSONObject2.has("layers")) {
                        updateLayerAttributes((JSONArray) jSONObject2.get("layers"));
                    }
                    i2++;
                } catch (IndexOutOfBoundsException unused) {
                    return jSONObject.toString();
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e((CharSequence) "RecapColorChanger", "scanLayers ", (Throwable) e);
            Log.e("RecapColorChanger", "fail scanLayers");
            return str;
        }
    }

    public void updateLayerAttributes(JSONArray jSONArray) {
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            int i7 = jSONObject.getInt("ty");
            if (i7 == 1) {
                updateSolidLayer(jSONObject);
            } else if (i7 == 5) {
                updateTextLayer(jSONObject);
            }
        }
    }
}
