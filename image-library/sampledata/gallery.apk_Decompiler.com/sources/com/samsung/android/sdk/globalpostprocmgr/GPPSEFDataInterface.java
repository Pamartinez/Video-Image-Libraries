package com.samsung.android.sdk.globalpostprocmgr;

import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.globalpostprocmgr.util.Log;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GPPSEFDataInterface {
    private static final String TAG = "GPPSEFDataInterface";
    private static ArrayList<GPPSEFData> mSefDataList = new ArrayList<>();

    public static boolean addSEFData(String str, String str2, byte[] bArr, int i2, int i7) {
        return mSefDataList.add(new GPPSEFData(str, str2, "", bArr, i2, i7, false));
    }

    public static JSONObject convertSEFDataListToJson(ArrayList<GPPSEFData> arrayList) {
        JSONObject jSONObject = new JSONObject();
        if (arrayList != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<GPPSEFData> it = arrayList.iterator();
            while (it.hasNext()) {
                GPPSEFData next = it.next();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(GPPInterfaceKey.JSON_KEY_SEF_SOURCE_FILE_PATH, next.getFileName());
                jSONObject2.put("key", next.getKeyName());
                jSONObject2.put(GPPInterfaceKey.JSON_KEY_SEF_DATA_FILE_PATH, next.getDataFileName());
                jSONObject2.put("data", new String(next.getData(), StandardCharsets.UTF_8));
                jSONObject2.put(GPPInterfaceKey.JSON_KEY_DATA_TYPE, next.getDataType());
                jSONObject2.put(GPPInterfaceKey.JSON_KEY_OPTION, next.getOption());
                jSONObject2.put(GPPInterfaceKey.JSON_KEY_IS_FILE_INSERT, next.isFileInsert());
                String str = TAG;
                Log.e(str, "Set Data Size: " + next.getData().length, new Object[0]);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(GPPInterfaceKey.JSON_KEY_SEF_INFO_LIST, jSONArray);
            arrayList.clear();
        }
        return jSONObject;
    }

    public static ArrayList<GPPSEFData> getSEFDataArrayList() {
        return mSefDataList;
    }

    public static ArrayList<GPPSEFData> requestByteToSEFDataList(byte[] bArr) {
        ArrayList<GPPSEFData> arrayList = new ArrayList<>();
        try {
            String str = TAG;
            Charset charset = StandardCharsets.UTF_8;
            Log.e(str, "TEST : ".concat(new String(bArr, charset)), new Object[0]);
            JSONArray jSONArray = new JSONObject(new String(bArr, charset)).getJSONArray(GPPInterfaceKey.JSON_KEY_SEF_INFO_LIST);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                GPPSEFData gPPSEFData = new GPPSEFData();
                gPPSEFData.setFileName(jSONObject.getString(GPPInterfaceKey.JSON_KEY_SEF_SOURCE_FILE_PATH));
                gPPSEFData.setKeyName(jSONObject.getString("key"));
                gPPSEFData.setDataFileName(jSONObject.getString(GPPInterfaceKey.JSON_KEY_SEF_DATA_FILE_PATH));
                gPPSEFData.setData(jSONObject.getString("data").getBytes(StandardCharsets.UTF_8));
                gPPSEFData.setDataType(jSONObject.getInt(GPPInterfaceKey.JSON_KEY_DATA_TYPE));
                gPPSEFData.setOption(jSONObject.getInt(GPPInterfaceKey.JSON_KEY_OPTION));
                gPPSEFData.setFileInsert(jSONObject.getBoolean(GPPInterfaceKey.JSON_KEY_IS_FILE_INSERT));
                Log.e(TAG, "Get Data Size: " + gPPSEFData.getData().length, new Object[0]);
                arrayList.add(gPPSEFData);
            }
            return arrayList;
        } catch (JSONException e) {
            Log.e(TAG, "Json Object Error: " + e, new Object[0]);
            return arrayList;
        }
    }

    public static boolean addSEFData(String str, String str2, String str3, int i2, int i7) {
        return mSefDataList.add(new GPPSEFData(str, str2, str3, (byte[]) null, i2, i7, true));
    }
}
