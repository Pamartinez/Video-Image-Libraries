package com.samsung.android.sdk.globalpostprocmgr;

import android.content.Context;
import android.database.Cursor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.util.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GPPServiceDBUtil {
    private static final Map<String, Integer> FEATURE_NAME_TO_ID_MAP = new HashMap<String, Integer>() {
        {
            put("FEATURE_TASK_WATERMARK", 1);
            put("FEATURE_TASK_STARTRAIL", 2);
            put("FEATURE_TASK_VIDEOCLIPPER", 3);
        }
    };
    private static final String TAG = "GPPServiceDBUtil";
    private static final HashMap<Integer, Boolean> mFeatureCache = new HashMap<>();
    private static final HashMap<Integer, Set<String>> mFeatureLibListCache = new HashMap<>();
    private static final HashMap<Integer, Set<String>> mFeatureParamCache = new HashMap<>();

    public static Set<String> queryDBForFeatureParamList(Context context, int i2) {
        Cursor query;
        Throwable th;
        HashMap<Integer, Set<String>> hashMap = mFeatureParamCache;
        if (hashMap.containsKey(Integer.valueOf(i2))) {
            return hashMap.get(Integer.valueOf(i2));
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            query = context.getContentResolver().query(IGPPDBInterface.FEATURE_SUPPORT_TABLE_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            String string = query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IFeatureSupportColumns.FIELD_FEATURE_NAME));
                            String string2 = query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IFeatureSupportColumns.FIELD_FEATURE_PARAM_LIST));
                            Set set = (Set) new Gson().fromJson(string2, new TypeToken<Set<String>>() {
                            }.getType());
                            Log.d(TAG, string + ": " + string2, new Object[0]);
                            Map<String, Integer> map = FEATURE_NAME_TO_ID_MAP;
                            if (map.containsKey(string)) {
                                mFeatureParamCache.put(map.get(string), set);
                            }
                        } while (query.moveToNext());
                        Log.d(TAG, "queryDBForFeatureParamList: Loaded DB in " + (System.currentTimeMillis() - currentTimeMillis) + " ms", new Object[0]);
                        query.close();
                        return mFeatureParamCache.get(Integer.valueOf(i2));
                    }
                }
            }
            Log.e(TAG, "Cursor is Empty", new Object[0]);
            Set<String> set2 = Collections.EMPTY_SET;
            if (query != null) {
                query.close();
            }
            return set2;
        } catch (Exception e) {
            Log.e(TAG, "queryDBForFeatureParamList Exception: " + e.getLocalizedMessage(), new Object[0]);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static boolean queryDBForFeatureSupport(Context context, int i2) {
        Cursor query;
        Throwable th;
        boolean z;
        HashMap<Integer, Boolean> hashMap = mFeatureCache;
        if (hashMap.size() > 0) {
            return Boolean.TRUE.equals(hashMap.get(Integer.valueOf(i2)));
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            query = context.getContentResolver().query(IGPPDBInterface.FEATURE_SUPPORT_TABLE_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            String string = query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IFeatureSupportColumns.FIELD_FEATURE_NAME));
                            if (query.getInt(query.getColumnIndexOrThrow(IGPPDBInterface.IFeatureSupportColumns.FIELD_FEATURE_SUPPORTED)) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            Log.d(TAG, string + ": " + z, new Object[0]);
                            Map<String, Integer> map = FEATURE_NAME_TO_ID_MAP;
                            if (map.containsKey(string)) {
                                mFeatureCache.put(map.get(string), Boolean.valueOf(z));
                            }
                        } while (query.moveToNext());
                        Log.d(TAG, "queryDBForFeatureSupport: Loaded DB in " + (System.currentTimeMillis() - currentTimeMillis) + " ms", new Object[0]);
                        query.close();
                        return Boolean.TRUE.equals(mFeatureCache.get(Integer.valueOf(i2)));
                    }
                }
            }
            Log.e(TAG, "Cursor is Empty", new Object[0]);
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "queryDBForFeatureSupport Exception: " + e.getLocalizedMessage(), new Object[0]);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static Set<String> queryDBForSolutionLibList(Context context, int i2) {
        Cursor query;
        Throwable th;
        HashMap<Integer, Set<String>> hashMap = mFeatureLibListCache;
        if (hashMap.containsKey(Integer.valueOf(i2))) {
            return hashMap.get(Integer.valueOf(i2));
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            query = context.getContentResolver().query(IGPPDBInterface.FEATURE_SUPPORT_TABLE_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            String string = query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IFeatureSupportColumns.FIELD_FEATURE_NAME));
                            String string2 = query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IFeatureSupportColumns.FIELD_FEATURE_SOLUTION_LIB_LIST));
                            Set set = (Set) new Gson().fromJson(string2, new TypeToken<Set<String>>() {
                            }.getType());
                            Log.d(TAG, string + ": " + string2, new Object[0]);
                            Map<String, Integer> map = FEATURE_NAME_TO_ID_MAP;
                            if (map.containsKey(string)) {
                                mFeatureLibListCache.put(map.get(string), set);
                            }
                        } while (query.moveToNext());
                        Log.d(TAG, "queryDBForFeatureParamList: Loaded DB in " + (System.currentTimeMillis() - currentTimeMillis) + " ms", new Object[0]);
                        query.close();
                        return mFeatureLibListCache.get(Integer.valueOf(i2));
                    }
                }
            }
            Log.e(TAG, "Cursor is Empty", new Object[0]);
            Set<String> set2 = Collections.EMPTY_SET;
            if (query != null) {
                query.close();
            }
            return set2;
        } catch (Exception e) {
            Log.e(TAG, "queryDBForFeatureParamList Exception: " + e.getLocalizedMessage(), new Object[0]);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }
}
