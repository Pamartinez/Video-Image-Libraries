package com.samsung.android.sdk.spage.card.base;

import android.util.Log;
import com.samsung.android.sdk.spage.card.base.JsonFieldData;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class JsonFieldData<T extends JsonFieldData> implements FieldData {
    private final JSONObject mData = new JSONObject();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listable {
        JSONObject toJSONObject();
    }

    public String getData() {
        return this.mData.toString();
    }

    public T put(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.mData.put(str, jSONObject);
                return this;
            } catch (JSONException e) {
                Log.d("JsonFieldData", e.getMessage());
                return this;
            }
        } else {
            throw new IllegalArgumentException(C0212a.A(str, " value is null"));
        }
    }

    public T putList(String str, List<? extends Listable> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Listable jSONObject : list) {
                arrayList.add(jSONObject.toJSONObject());
            }
            try {
                this.mData.put(str, new JSONArray(arrayList));
                return this;
            } catch (JSONException e) {
                Log.d("JsonFieldData", e.getMessage());
                return this;
            }
        } else {
            throw new IllegalArgumentException(C0212a.A(str, " value is null"));
        }
    }

    public void remove(String str) {
        if (this.mData.has(str)) {
            this.mData.remove(str);
        }
    }

    public T put(String str, String str2) {
        if (str2 != null) {
            try {
                this.mData.put(str, str2);
                return this;
            } catch (JSONException e) {
                Log.d("JsonFieldData", e.getMessage());
                return this;
            }
        } else {
            throw new IllegalArgumentException(C0212a.A(str, " value is null"));
        }
    }

    public T put(String str, int i2) {
        try {
            this.mData.put(str, i2);
            return this;
        } catch (JSONException e) {
            Log.d("JsonFieldData", e.getMessage());
            return this;
        }
    }

    public T put(String str, List<String> list) {
        if (list != null) {
            try {
                this.mData.put(str, new JSONArray(list));
                return this;
            } catch (JSONException e) {
                Log.d("JsonFieldData", e.getMessage());
                return this;
            }
        } else {
            throw new IllegalArgumentException(C0212a.A(str, " value is null"));
        }
    }
}
