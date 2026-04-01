package com.samsung.android.gallery.module.creature;

import B5.e;
import O3.o;
import S3.d;
import T8.C0578a;
import android.database.Cursor;
import android.graphics.RectF;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FacePosRatioHelper {
    private static final ConcurrentHashMap<Long, ArrayList<RectF>> sMap = new ConcurrentHashMap<>();

    public static void clear() {
        sMap.clear();
    }

    private static String getFaceRatioQuery(String str) {
        return C0212a.m("select sec_media_id, pos_ratio from (select sec_media_id, pos_ratio from faces union all select sec_media_id, pos_ratio from cluster_rect)where sec_media_id in (", str, ")");
    }

    public static ArrayList<RectF> getFaceRectList(long j2) {
        ConcurrentHashMap<Long, ArrayList<RectF>> concurrentHashMap = sMap;
        ArrayList<RectF> arrayList = concurrentHashMap.get(Long.valueOf(j2));
        if (arrayList != null) {
            return arrayList;
        }
        updateFaces(new Long[]{Long.valueOf(j2)});
        return concurrentHashMap.get(Long.valueOf(j2));
    }

    private static String idsToString(Long[] lArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Arrays.stream(lArr).forEach(new e(stringJoiner, 5));
        return stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$preload$1(Long l) {
        if (sMap.get(l) == null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Long[] lambda$preload$2(int i2) {
        return new Long[i2];
    }

    private static HashMap<Long, ArrayList<RectF>> loadFaceRatioMap(String str) {
        Cursor rawQuery;
        HashMap<Long, ArrayList<RectF>> hashMap = new HashMap<>();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery(getFaceRatioQuery(str), "loadFaces");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        putRectToHashMap(rawQuery, hashMap);
                    } while (rawQuery.moveToNext());
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return hashMap;
        } catch (Exception e) {
            Log.e((CharSequence) "FaceHelper", "preload failed", (Throwable) e);
            e.printStackTrace();
            return hashMap;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void preload(ArrayList<MediaItem> arrayList) {
        updateFaces((Long[]) arrayList.stream().map(new o(18)).filter(new d(11)).toArray(new C0578a(0)));
    }

    private static void putRectToHashMap(Cursor cursor, HashMap<Long, ArrayList<RectF>> hashMap) {
        long j2 = cursor.getLong(0);
        RectF stringToRectF = stringToRectF(cursor.getString(1));
        if (RectUtils.isValidRect(stringToRectF)) {
            ArrayList arrayList = hashMap.get(Long.valueOf(j2));
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(stringToRectF);
            hashMap.put(Long.valueOf(j2), arrayList);
        }
    }

    private static RectF stringToRectF(String str) {
        RectF rectF = new RectF();
        if (str != null && !str.isEmpty()) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            rectF.left = Float.parseFloat(stringTokenizer.nextToken());
            rectF.top = Float.parseFloat(stringTokenizer.nextToken());
            rectF.right = Float.parseFloat(stringTokenizer.nextToken());
            rectF.bottom = Float.parseFloat(stringTokenizer.nextToken());
        }
        return rectF;
    }

    private static void updateFaces(Long[] lArr) {
        if (lArr.length >= 1) {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap<Long, ArrayList<RectF>> loadFaceRatioMap = loadFaceRatioMap(idsToString(lArr));
            for (Long l : lArr) {
                l.longValue();
                ArrayList arrayList = loadFaceRatioMap.get(l);
                ConcurrentHashMap<Long, ArrayList<RectF>> concurrentHashMap = sMap;
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                concurrentHashMap.put(l, arrayList);
            }
            Log.d("FaceHelper", "preload {" + lArr.length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
