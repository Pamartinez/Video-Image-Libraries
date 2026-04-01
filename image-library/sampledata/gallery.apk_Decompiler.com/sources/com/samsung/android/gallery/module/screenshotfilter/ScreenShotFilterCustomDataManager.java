package com.samsung.android.gallery.module.screenshotfilter;

import Fa.C0561o;
import U9.a;
import U9.b;
import android.util.Pair;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ScreenShotFilterCustomDataManager {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Data extends C0212a {
        /* access modifiers changed from: private */
        public final boolean on;
        /* access modifiers changed from: private */
        public final String scene;

        public Data(String str, boolean z) {
            this.scene = str;
            this.on = z;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Data)) {
                return false;
            }
            Data data = (Data) obj;
            if (this.on != data.on || !Objects.equals(this.scene, data.scene)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            boolean z = this.on;
            return Objects.hashCode(this.scene) + (Boolean.hashCode(z) * 31);
        }

        public final String toString() {
            String[] strArr;
            Object[] objArr = {this.scene, Boolean.valueOf(this.on)};
            if ("scene;on".length() == 0) {
                strArr = new String[0];
            } else {
                strArr = "scene;on".split(";");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Data.class.getSimpleName());
            sb2.append("[");
            for (int i2 = 0; i2 < strArr.length; i2++) {
                sb2.append(strArr[i2]);
                sb2.append("=");
                sb2.append(objArr[i2]);
                if (i2 != strArr.length - 1) {
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
            }
            sb2.append("]");
            return sb2.toString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataHolder {
        private static final String KEY = PreferenceName.SCREENSHOT_FILTER_ORDER.key();
        private static final DataHolder instance = new DataHolder();
        final Object LOCK = new Object();
        private String data = GalleryPreference.getInstance().loadString(KEY, (String) null);

        public static DataHolder getInstance() {
            return instance;
        }

        public String read() {
            String str;
            synchronized (this.LOCK) {
                try {
                    if (this.data == null) {
                        this.data = GalleryPreference.getInstance().loadString(KEY, "");
                    }
                    str = this.data;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return str;
        }

        public void save(String str) {
            synchronized (this.LOCK) {
                this.data = str;
                GalleryPreference.getInstance().saveState(KEY, str);
            }
        }
    }

    private static ArrayList<Data> getDataArray() {
        String read = DataHolder.getInstance().read();
        if (read.isEmpty()) {
            Log.e("ScreenShotFilterCustomDataManager", "empty");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(read);
            ArrayList<Data> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                arrayList.add(new Data(jSONObject.getString("scene"), jSONObject.getBoolean("on")));
            }
            return arrayList;
        } catch (JSONException e) {
            Log.e((CharSequence) "ScreenShotFilterCustomDataManager", "getData error", (Throwable) e);
            return null;
        }
    }

    private static Map<String, Pair<Boolean, Integer>> getDataMap() {
        String read = DataHolder.getInstance().read();
        if (read.isEmpty()) {
            Log.e("ScreenShotFilterCustomDataManager", "empty");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(read);
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                Data data = new Data(jSONObject.getString("scene"), jSONObject.getBoolean("on"));
                hashMap.put(data.scene, new Pair(Boolean.valueOf(data.on), Integer.valueOf(i2)));
            }
            return hashMap;
        } catch (JSONException e) {
            Log.e((CharSequence) "ScreenShotFilterCustomDataManager", "getData error", (Throwable) e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$reorder$1(Map map, MediaItem mediaItem) {
        return !((Boolean) ((Pair) map.getOrDefault(mediaItem.getSubCategory(), new Pair(Boolean.TRUE, Integer.MAX_VALUE))).first).booleanValue();
    }

    private static String makeJSONString(ArrayList<Data> arrayList) {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<Data> it = arrayList.iterator();
            while (it.hasNext()) {
                Data next = it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scene", next.scene);
                jSONObject.put("on", next.on);
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (JSONException e) {
            Log.e((CharSequence) "ScreenShotFilterCustomDataManager", "saveState error", (Throwable) e);
            return "";
        }
    }

    public static void reorder(ArrayList<MediaItem> arrayList, boolean z) {
        Map<String, Pair<Boolean, Integer>> dataMap = getDataMap();
        if (dataMap != null) {
            try {
                arrayList.sort(Comparator.comparingInt(new a(0, dataMap)));
                if (!z) {
                    arrayList.removeIf(new C0561o(2, dataMap));
                } else {
                    arrayList.forEach(new b(0, dataMap));
                }
            } catch (Exception e) {
                Log.e((CharSequence) "ScreenShotFilterCustomDataManager", "reorderAndOnOff fail", (Throwable) e);
            }
        }
    }

    public static void saveChangedOrderData(int i2, int i7) {
        ArrayList<Data> dataArray = getDataArray();
        if (dataArray != null) {
            dataArray.add(i7, dataArray.remove(i2));
            saveDataInternal(dataArray);
        }
    }

    public static void saveData(ArrayList<MediaItem> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            arrayList2.add(new Data(next.getSubCategory(), ((Boolean) next.getExtra(ExtrasID.SCREENSHOT_FILTER_ON, Boolean.TRUE)).booleanValue()));
        }
        saveDataInternal(arrayList2);
    }

    private static void saveDataInternal(ArrayList<Data> arrayList) {
        DataHolder.getInstance().save(makeJSONString(arrayList));
    }
}
