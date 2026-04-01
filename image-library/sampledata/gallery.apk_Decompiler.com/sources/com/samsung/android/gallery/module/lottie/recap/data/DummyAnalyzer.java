package com.samsung.android.gallery.module.lottie.recap.data;

import B8.k;
import E5.b;
import X9.a;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sum.core.functional.g;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DummyAnalyzer {
    public int fromMonth;
    HashMap<Long, JSONObject> mContents = new HashMap<>();
    public ArrayList<String> mLocationNames = new ArrayList<>();
    public int toMonth;
    public int totalDays;
    public int totalFaces;
    public int totalImages;
    public int totalPeople;
    public int totalPlaceCount;
    public int totalVideos;
    public int year;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDataJson$3(HashMap hashMap, StringJoiner stringJoiner, MediaItem mediaItem) {
        String basicIsoDate = TimeUtil.getBasicIsoDate(mediaItem.getDateTaken());
        AtomicInteger atomicInteger = (AtomicInteger) hashMap.get(basicIsoDate);
        boolean z = true;
        if (atomicInteger == null) {
            hashMap.put(basicIsoDate, new AtomicInteger(1));
        } else {
            atomicInteger.incrementAndGet();
        }
        stringJoiner.add(mediaItem.getFileId() + "");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contents", mediaItem.getFileId());
            jSONObject.put("width", mediaItem.getFileId());
            jSONObject.put("height", mediaItem.getFileId());
            jSONObject.put("orientation", mediaItem.getFileId());
            jSONObject.put("timeStamp", mediaItem.getDateTaken());
            if (RandomNumber.nextInt(10) != 1) {
                z = false;
            }
            jSONObject.put("isNew", z);
            this.mContents.put(Long.valueOf(mediaItem.getFileId()), jSONObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createDataJson$5(AtomicInteger atomicInteger, JSONArray jSONArray, JSONArray jSONArray2, AtomicInteger atomicInteger2, JSONArray jSONArray3, JSONObject jSONObject) {
        if (jSONObject.has("dev_face_name") && atomicInteger.get() > 0) {
            boolean z = false;
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    if (jSONObject.getString("dev_face_name").equals(jSONArray.getJSONObject(i2).get("dev_face_name"))) {
                        z = true;
                    }
                } catch (JSONException unused) {
                }
            }
            if (!z) {
                atomicInteger.getAndDecrement();
                jSONArray.put(jSONObject);
                return;
            }
            jSONArray2.put(jSONObject);
        } else if (!jSONObject.has("hasSceneryTag") || atomicInteger2.get() <= 0) {
            jSONArray2.put(jSONObject);
        } else {
            atomicInteger2.getAndDecrement();
            jSONArray3.put(jSONObject);
        }
    }

    private void scanFace(String str) {
        Cursor rawQuery = new SecMpQueryExecutor().rawQuery("select sec_media_id, faces_view.recommended_id,person_id, persons.name, faces_view.pos_ratio from faces_view left outer join persons on faces_view.person_id=persons._id where sec_media_id in (__VAR_SEC_MEDIA_ID__) and pos_ratio is not null and pos_ratio!='' group by  recommended_id,pos_ratio ".replace("__VAR_SEC_MEDIA_ID__", str), "tt");
        if (rawQuery != null) {
            try {
                int count = rawQuery.getCount();
                this.totalPeople = count;
                this.totalFaces = count;
                while (rawQuery.moveToNext()) {
                    JSONObject jSONObject = new JSONObject();
                    long j2 = rawQuery.getLong(0);
                    jSONObject.put("recommendedId", rawQuery.getLong(1));
                    String string = rawQuery.getString(3);
                    jSONObject.put("name", string);
                    String[] split = rawQuery.getString(4).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(split[0]);
                    jSONArray.put(split[1]);
                    jSONArray.put(split[2]);
                    jSONArray.put(split[3]);
                    jSONObject.put("facePos", jSONArray);
                    JSONObject jSONObject2 = this.mContents.get(Long.valueOf(j2));
                    if (jSONObject2 != null) {
                        if (!jSONObject2.has("faces")) {
                            jSONObject2.put("faces", new JSONArray());
                        }
                        JSONArray jSONArray2 = jSONObject2.getJSONArray("faces");
                        if (!TextUtils.isEmpty(string)) {
                            jSONObject2.put("dev_face_name", string);
                            jSONArray2.put(0, jSONObject);
                        } else {
                            jSONArray2.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
            return;
        }
        return;
        throw th;
    }

    private void scanPlaces(String str) {
        SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
        Cursor rawQuery = secMpQueryExecutor.rawQuery("Select locality from location_view where locale='" + Locale.getDefault() + "' and sec_media_id in(" + str + ") group by locality order by count(*) desc", "tt");
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                try {
                    this.mLocationNames.add(rawQuery.getString(0));
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        this.totalPlaceCount = this.mLocationNames.size();
        SecMpQueryExecutor secMpQueryExecutor2 = new SecMpQueryExecutor();
        Cursor rawQuery2 = secMpQueryExecutor2.rawQuery("Select sec_media_id, locality from location_view where locale='" + Locale.getDefault() + "' and sec_media_id in(" + str + ")", "tt");
        if (rawQuery2 != null) {
            while (rawQuery2.moveToNext()) {
                try {
                    long j2 = rawQuery2.getLong(0);
                    String string = rawQuery2.getString(1);
                    JSONObject jSONObject = this.mContents.get(Long.valueOf(j2));
                    if (jSONObject != null) {
                        jSONObject.put(BuddyContract.Address.CITY, string);
                    }
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
        }
        if (rawQuery2 != null) {
            rawQuery2.close();
            return;
        }
        return;
        throw th;
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057 A[Catch:{ all -> 0x004b, all -> 0x0068 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e A[Catch:{ all -> 0x004b, all -> 0x0068 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0017 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void scanScene(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "select sec_media_id, group_concat(scene_name) td from scene where sec_media_id in (__VAR_SEC_MEDIA_ID__) and scene_name is not null group by sec_media_id "
            java.lang.String r1 = "__VAR_SEC_MEDIA_ID__"
            java.lang.String r6 = r0.replace(r1, r6)
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r0 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor
            r0.<init>()
            java.lang.String r1 = "tt"
            android.database.Cursor r6 = r0.rawQuery((java.lang.String) r6, (java.lang.String) r1)
            if (r6 == 0) goto L_0x006d
        L_0x0017:
            boolean r0 = r6.moveToNext()     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x006d
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ all -> 0x004b }
            r0.<init>()     // Catch:{ all -> 0x004b }
            r0 = 0
            long r1 = r6.getLong(r0)     // Catch:{ all -> 0x004b }
            java.util.HashMap<java.lang.Long, org.json.JSONObject> r3 = r5.mContents     // Catch:{ all -> 0x004b }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x004b }
            java.lang.Object r1 = r3.get(r1)     // Catch:{ all -> 0x004b }
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0017
            r2 = 1
            java.lang.String r3 = r6.getString(r2)     // Catch:{ all -> 0x004b }
            java.lang.String r4 = "people"
            boolean r4 = r3.contains(r4)     // Catch:{ all -> 0x004b }
            if (r4 != 0) goto L_0x004d
            java.lang.String r4 = "pet"
            boolean r4 = r3.contains(r4)     // Catch:{ all -> 0x004b }
            if (r4 == 0) goto L_0x004e
            goto L_0x004d
        L_0x004b:
            r5 = move-exception
            goto L_0x0064
        L_0x004d:
            r0 = r2
        L_0x004e:
            java.lang.String r2 = "scenery"
            boolean r2 = r3.contains(r2)     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x005c
            java.lang.String r3 = "hasPeoplePetTag"
            r1.put(r3, r0)     // Catch:{ all -> 0x004b }
        L_0x005c:
            if (r2 == 0) goto L_0x0017
            java.lang.String r0 = "hasSceneryTag"
            r1.put(r0, r2)     // Catch:{ all -> 0x004b }
            goto L_0x0017
        L_0x0064:
            r6.close()     // Catch:{ all -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r6 = move-exception
            r5.addSuppressed(r6)
        L_0x006c:
            throw r5
        L_0x006d:
            if (r6 == 0) goto L_0x0072
            r6.close()
        L_0x0072:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.lottie.recap.data.DummyAnalyzer.scanScene(java.lang.String):void");
    }

    public String createDataJson(ArrayList<MediaItem> arrayList) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", 999);
        jSONObject.put("totalContents", arrayList.size());
        this.totalImages = (int) arrayList.stream().filter(new e(11)).count();
        int size = arrayList.size();
        int i2 = this.totalImages;
        this.totalVideos = size - i2;
        jSONObject.put("totalImages", i2);
        jSONObject.put("totalVideos", this.totalVideos);
        long dateTaken = arrayList.stream().max(Comparator.comparingLong(new b(15))).get().getDateTaken();
        long dateTaken2 = arrayList.stream().min(Comparator.comparingLong(new b(16))).get().getDateTaken();
        this.year = TimeUtil.getYearInt(dateTaken);
        this.fromMonth = UnsafeCast.toInt(TimeUtil.getBasicIsoDate(dateTaken2).substring(4, 6));
        this.toMonth = UnsafeCast.toInt(TimeUtil.getBasicIsoDate(dateTaken).substring(4, 6));
        jSONObject.put("year", this.year);
        jSONObject.put("fromMonth", this.fromMonth);
        jSONObject.put("toMonth", this.toMonth);
        HashMap hashMap = new HashMap();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new g(this, hashMap, stringJoiner));
        int size2 = hashMap.size();
        this.totalDays = size2;
        jSONObject.put("totalDays", size2);
        Map.Entry entry = (Map.Entry) hashMap.entrySet().stream().max(new Comparator<Map.Entry<String, AtomicInteger>>() {
            public int compare(Map.Entry<String, AtomicInteger> entry, Map.Entry<String, AtomicInteger> entry2) {
                return Integer.compare(entry.getValue().get(), entry2.getValue().get());
            }
        }).get();
        String str = (String) entry.getKey();
        jSONObject.put("mostActiveDayCount", ((AtomicInteger) entry.getValue()).get());
        jSONObject.put("mostActiveDay", TimeUtil.getExifTimeInMillis(str.substring(0, 4) + NumericEnum.SEP + str.substring(4, 6) + NumericEnum.SEP + str.substring(6, 8) + " 00:00:00"));
        jSONObject.put("keyMomentsDayCount", 3);
        String stringJoiner2 = stringJoiner.toString();
        scanPlaces(stringJoiner2);
        int i7 = this.totalPlaceCount;
        if (i7 > 0) {
            jSONObject.put("totalLocations", i7);
            if (this.totalPlaceCount > 2) {
                jSONObject.put("newLocations", 2);
            }
        }
        JSONArray jSONArray = new JSONArray();
        this.mLocationNames.forEach(new a(jSONArray, 4));
        jSONObject.put("totalLocationNames", jSONArray);
        scanFace(stringJoiner2);
        int i8 = this.totalFaces;
        if (i8 > 0) {
            jSONObject.put("totalFaces", i8);
        }
        int i10 = this.totalPeople;
        if (i10 > 0) {
            jSONObject.put("totalPeople", i10);
        }
        scanScene(stringJoiner2);
        JSONArray jSONArray2 = new JSONArray();
        JSONArray jSONArray3 = new JSONArray();
        JSONArray jSONArray4 = new JSONArray();
        AtomicInteger atomicInteger = new AtomicInteger(10);
        this.mContents.values().forEach(new k(new AtomicInteger(10), jSONArray4, jSONArray2, atomicInteger, jSONArray3, 18));
        if (jSONArray2.length() == 0) {
            if (jSONArray3.length() == 0) {
                jSONArray3 = jSONArray4;
            }
            jSONObject.put("contents", jSONArray3);
        } else {
            jSONObject.put("contents", jSONArray2);
            jSONObject.put("locationContents", jSONArray3);
            jSONObject.put("faceContents", jSONArray4);
        }
        return jSONObject.toString();
    }
}
