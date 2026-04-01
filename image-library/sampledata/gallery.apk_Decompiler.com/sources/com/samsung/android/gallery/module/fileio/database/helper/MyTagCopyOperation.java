package com.samsung.android.gallery.module.fileio.database.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.scsp.media.api.d;
import g6.g;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagCopyOperation {
    private static volatile MyTagCopyOperation sInstance;
    private final Map<Long, ArrayList<String>> mLoadedTagMap = new HashMap();
    private final Map<String, Map<String, Long[]>> mOperationMap = new ConcurrentHashMap();
    private final List<Long> mPreloadList = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UpdateTagTask extends AsyncTask<Void, Void, Void> {
        private Map<Long, ArrayList<String>> mLoadedTagMap;
        private Map<String, Long[]> mUpdateSet;

        public UpdateTagTask(Map<String, Long[]> map, Map<Long, ArrayList<String>> map2) {
            this.mUpdateSet = new HashMap(map);
            this.mLoadedTagMap = map2;
        }

        private ContentValues[] getContentValues(Map<Long, ArrayList<String>> map) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : this.mUpdateSet.entrySet()) {
                Long l = ((Long[]) next.getValue())[0];
                l.getClass();
                Optional.ofNullable(map.get(l)).ifPresent(new b(arrayList, 0, ((Long[]) next.getValue())[1].longValue()));
            }
            return (ContentValues[]) arrayList.toArray(new ContentValues[0]);
        }

        private int getKeyPathMap(String str) {
            return str.contains(Log.TAG_SEPARATOR) ? 1 : 0;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ArrayList lambda$doInBackground$2(Integer num) {
            return new ArrayList();
        }

        private void queryNewCreatedIdFromPaths(Map<Integer, ArrayList<String>> map) {
            boolean z;
            Cursor queryNewCreatedIdFromPaths;
            String str;
            for (Map.Entry next : map.entrySet()) {
                if (((Integer) next.getKey()).intValue() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                try {
                    queryNewCreatedIdFromPaths = new TagEditApi().queryNewCreatedIdFromPaths((ArrayList) next.getValue(), z);
                    if (queryNewCreatedIdFromPaths != null) {
                        if (queryNewCreatedIdFromPaths.getCount() > 0 && queryNewCreatedIdFromPaths.moveToFirst()) {
                            do {
                                long j2 = (long) queryNewCreatedIdFromPaths.getInt(0);
                                String string = queryNewCreatedIdFromPaths.getString(1);
                                Map<String, Long[]> map2 = this.mUpdateSet;
                                StringBuilder sb2 = new StringBuilder();
                                if (z) {
                                    str = Log.TAG_SEPARATOR;
                                } else {
                                    str = "";
                                }
                                sb2.append(str);
                                sb2.append(string);
                                Long[] lArr = map2.get(sb2.toString());
                                if (lArr != null && lArr.length > 1) {
                                    lArr[1] = Long.valueOf(j2);
                                }
                            } while (queryNewCreatedIdFromPaths.moveToNext());
                        }
                    }
                    if (queryNewCreatedIdFromPaths != null) {
                        queryNewCreatedIdFromPaths.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            return;
            throw th;
        }

        /* JADX WARNING: type inference failed for: r4v2, types: [java.lang.Object, java.util.function.Function] */
        public Void doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.mUpdateSet.entrySet()) {
                arrayList.add(((Long[]) next.getValue())[0]);
                ((ArrayList) hashMap.computeIfAbsent(Integer.valueOf(getKeyPathMap((String) next.getKey())), new Object())).add(((String) next.getKey()).replaceFirst(Log.TAG_SEPARATOR, ""));
            }
            queryNewCreatedIdFromPaths(hashMap);
            for (Map.Entry next2 : new TagEditApi().queryTagListFromIds((Long[]) arrayList.toArray(new Long[0])).entrySet()) {
                if (!((ArrayList) next2.getValue()).isEmpty()) {
                    this.mLoadedTagMap.put((Long) next2.getKey(), (ArrayList) next2.getValue());
                }
            }
            if (this.mLoadedTagMap.isEmpty()) {
                return null;
            }
            new TagEditApi().bulkInsertMyTags(getContentValues(this.mLoadedTagMap));
            return null;
        }

        public UpdateTagTask(FileItemInterface fileItemInterface, String str) {
            this.mUpdateSet = new HashMap();
            this.mLoadedTagMap = new HashMap();
            this.mUpdateSet.put(C0212a.p(new StringBuilder(), fileItemInterface.isCloudOnly() ? Log.TAG_SEPARATOR : "", str), new Long[]{Long.valueOf(fileItemInterface.getMediaId()), 0L});
        }
    }

    public static MyTagCopyOperation getInstance() {
        if (sInstance == null) {
            synchronized (MyTagCopyOperation.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new MyTagCopyOperation();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$addValues$0(String str) {
        return new ConcurrentHashMap();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$apply$1(Map map) {
        new UpdateTagTask((Map<String, Long[]>) map, this.mLoadedTagMap).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        map.clear();
    }

    public void addValues(String str, FileItemInterface fileItemInterface, String str2) {
        String str3;
        if (fileItemInterface != null && str2 != null) {
            Map computeIfAbsent = this.mOperationMap.computeIfAbsent(str, new d(29));
            if (fileItemInterface.isCloudOnly()) {
                str3 = Log.TAG_SEPARATOR;
            } else {
                str3 = "";
            }
            computeIfAbsent.put(str3.concat(str2), new Long[]{Long.valueOf(fileItemInterface.getMediaId()), 0L});
        }
    }

    public void apply(String str) {
        Optional.ofNullable(this.mOperationMap.get(str)).ifPresent(new g(2, this));
    }

    public void clear() {
        this.mPreloadList.clear();
        this.mLoadedTagMap.clear();
        this.mOperationMap.clear();
    }

    public void finishPreloadData() {
        if (!this.mPreloadList.isEmpty()) {
            this.mLoadedTagMap.putAll(new TagEditApi().queryTagListFromIds((Long[]) this.mPreloadList.toArray(new Long[0])));
            this.mPreloadList.clear();
        }
    }

    public void immediateApply(FileItemInterface fileItemInterface, String str) {
        if (fileItemInterface != null && str != null) {
            new UpdateTagTask(fileItemInterface, str).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    public void startPreloadData(FileItemInterface fileItemInterface) {
        if (fileItemInterface != null) {
            this.mPreloadList.add(Long.valueOf(fileItemInterface.getMediaId()));
            if (this.mPreloadList.size() >= 200) {
                finishPreloadData();
            }
        }
    }
}
