package com.samsung.android.sdk.globalpostprocmgr;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Pair;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.util.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GlobalPostProcInternalPPInterface {
    public static final String SPLIT_REGEX = ",";
    private static final String TAG = "GlobalPostProcInternalPPInterface";
    private Context mContext;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SingletonHelper {
        /* access modifiers changed from: private */
        public static final GlobalPostProcInternalPPInterface INSTANCE = new GlobalPostProcInternalPPInterface(0);

        private SingletonHelper() {
        }
    }

    public /* synthetic */ GlobalPostProcInternalPPInterface(int i2) {
        this();
    }

    public static synchronized GlobalPostProcInternalPPInterface getInstance() {
        GlobalPostProcInternalPPInterface a7;
        synchronized (GlobalPostProcInternalPPInterface.class) {
            a7 = SingletonHelper.INSTANCE;
        }
        return a7;
    }

    public int deletePipeline(Context context, long j2) {
        if (context == null) {
            Log.e(TAG, "Context is null", new Object[0]);
            return -1;
        }
        try {
            return context.getContentResolver().delete(IGPPDBInterface.PIPELINE_TABLE_URI, "_id=?", new String[]{String.valueOf(j2)});
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Delete Pipelines Error: " + e, new Object[0]);
            return 0;
        }
    }

    public Pair<String, String> getPipelineInfo(Context context, String str) {
        Throwable th;
        Pair<String, String> pair;
        if (context == null) {
            return null;
        }
        Cursor query = context.getContentResolver().query(IGPPDBInterface.PIPELINE_TABLE_URI, (String[]) null, "name=?", new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            pair = new Pair<>(query.getString(query.getColumnIndexOrThrow("name")), query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IPipelineColumns.FIELD_PIPELINE_LIST)));
                        } while (query.moveToNext());
                        query.close();
                        return pair;
                    }
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        Log.e(TAG, "Cursor is Empty", new Object[0]);
        if (query != null) {
            query.close();
        }
        return null;
        throw th;
    }

    public ArrayList<PPPipelineData> getPipelineList(Context context) {
        Throwable th;
        if (context == null) {
            return null;
        }
        ArrayList<PPPipelineData> arrayList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(IGPPDBInterface.PIPELINE_TABLE_URI, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            arrayList.add(new PPPipelineData(query.getLong(query.getColumnIndexOrThrow("_id")), query.getString(query.getColumnIndexOrThrow("name")), query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IPipelineColumns.FIELD_PIPELINE_LIST)), query.getInt(query.getColumnIndexOrThrow("priority")), query.getLong(query.getColumnIndexOrThrow("version")), query.getLong(query.getColumnIndexOrThrow("update_time"))));
                        } while (query.moveToNext());
                        query.close();
                        return arrayList;
                    }
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        Log.e(TAG, "Cursor is Empty", new Object[0]);
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    public ArrayList<PPPluginData> getPluginList(Context context) {
        Throwable th;
        if (context == null) {
            return null;
        }
        ArrayList<PPPluginData> arrayList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(IGPPDBInterface.PLUGIN_TABLE_URI, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            arrayList.add(new PPPluginData(query.getLong(query.getColumnIndexOrThrow("_id")), query.getString(query.getColumnIndexOrThrow("name")), query.getInt(query.getColumnIndexOrThrow("type")), query.getString(query.getColumnIndexOrThrow("description")), query.getLong(query.getColumnIndexOrThrow("version")), query.getLong(query.getColumnIndexOrThrow("update_time"))));
                        } while (query.moveToNext());
                        query.close();
                        return arrayList;
                    }
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        Log.e(TAG, "Cursor is Empty", new Object[0]);
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    public ArrayList<PPRequestQueueData> getRequestQueueList(Context context, int i2) {
        Throwable th;
        if (context == null) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList<PPRequestQueueData> arrayList = new ArrayList<>();
        Cursor query = contentResolver.query(IGPPDBInterface.REQUEST_QUEUE_TABLE_URI, (String[]) null, "status=?", new String[]{String.valueOf(i2)}, (String) null);
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    if (query.moveToFirst()) {
                        do {
                            arrayList.add(new PPRequestQueueData(query.getLong(query.getColumnIndexOrThrow("_id")), query.getString(query.getColumnIndexOrThrow(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_PIPELINE_NAME)), query.getString(query.getColumnIndexOrThrow("file_path")), query.getLong(query.getColumnIndexOrThrow("sec_media_id")), query.getLong(query.getColumnIndexOrThrow("media_id")), query.getInt(query.getColumnIndexOrThrow("priority")), query.getLong(query.getColumnIndexOrThrow(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME)), query.getInt(query.getColumnIndexOrThrow("status")), query.getLong(query.getColumnIndexOrThrow("update_time"))));
                        } while (query.moveToNext());
                        query.close();
                        return arrayList;
                    }
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        Log.e(TAG, "Cursor is Empty", new Object[0]);
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    public boolean insertPipeline(Context context, String str, String str2) {
        if (getPipelineInfo(context, str) != null) {
            Log.e(TAG, "Pipeline is already Exist", new Object[0]);
            return false;
        }
        if (insertPipeline(context, new PPPipelineData(str, str2, 1, 1, System.currentTimeMillis())) != null) {
            return true;
        }
        return false;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public boolean updatePipeline(Context context, String str, String str2) {
        if (getPipelineInfo(context, str) == null) {
            Log.e(TAG, "Pipeline is Not Exist", new Object[0]);
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            String[] strArr = {str};
            ContentValues contentValue = new PPPipelineData(str, str2, 1, 1, System.currentTimeMillis()).getContentValue();
            contentValue.remove("_id");
            contentValue.remove("name");
            if (contentResolver.update(IGPPDBInterface.PIPELINE_TABLE_URI, contentValue, "name=?", strArr) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "Update Pipelines Error: " + e, new Object[0]);
            return false;
        }
    }

    private GlobalPostProcInternalPPInterface() {
    }

    public Uri insertPipeline(Context context, PPPipelineData pPPipelineData) {
        if (context == null || pPPipelineData == null) {
            Log.e(TAG, "Pipeline Data is null", new Object[0]);
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            ContentValues contentValue = pPPipelineData.getContentValue();
            contentValue.remove("_id");
            String str = TAG;
            Log.e(str, "Insert: " + contentValue, new Object[0]);
            return contentResolver.insert(IGPPDBInterface.PIPELINE_TABLE_URI, contentValue);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Insert Pipeline Error: " + e, new Object[0]);
            return null;
        }
    }

    public boolean deletePipeline(Context context, String str) {
        int i2;
        if (context == null) {
            Log.e(TAG, "Context is null", new Object[0]);
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            i2 = contentResolver.delete(IGPPDBInterface.PIPELINE_TABLE_URI, "name=?", new String[]{str});
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Delete Pipelines Error: " + e, new Object[0]);
            i2 = 0;
        }
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public int updatePipeline(Context context, long j2, PPPipelineData pPPipelineData) {
        if (context == null || pPPipelineData == null) {
            Log.e(TAG, "Context is null", new Object[0]);
            return -1;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            ContentValues contentValue = pPPipelineData.getContentValue();
            contentValue.remove("_id");
            return contentResolver.update(IGPPDBInterface.PIPELINE_TABLE_URI, contentValue, "_id=?", new String[]{String.valueOf(j2)});
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Update Pipelines Error: " + e, new Object[0]);
            return 0;
        }
    }
}
