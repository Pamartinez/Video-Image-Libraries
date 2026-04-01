package com.samsung.android.gallery.module.lottie.recap.data.parser;

import A4.B;
import Z8.c;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapDataParserImplV0 extends RecapDataParser {
    private void buildImageCandidatesReal(JSONObject jSONObject) {
        loadImageCandidatesByArrayId(jSONObject, "contents", this.d.mImageCandidates);
        loadImageCandidatesByArrayId(jSONObject, "faceContents", this.d.mFaceCandidates);
        loadImageCandidatesByArrayId(jSONObject, "locationContents", this.d.mLocationCandidates);
        loadImageCandidatesByArrayId(jSONObject, "newLocationContents", this.d.mNewLocationCandidates);
        loadImageCandidatesByArrayId(jSONObject, "keyMoments", this.d.mKeyMomentsCandidates);
        loadImageCandidatesByArrayId(jSONObject, "topActiveDaysContents", this.d.mTopActiveDaysContents);
        loadImageCandidatesByArrayId(jSONObject, "mostActiveDayContents", this.d.mMostActiveDayContents);
        loadImageCandidatesByArrayId(jSONObject, "topFrequentFacesContents", this.d.mWithTopFrequentFacesCandidates);
        loadImageCandidatesByDoubleArray(jSONObject, "monthlyBestContents", this.d.mMonthlyBestCandidates);
        loadImageCandidatesByDoubleArray(jSONObject, "topFrequentFaces", this.d.mTopFrequentFacesCandidates);
    }

    private void fillLongArray(String str, ArrayList<Long> arrayList) {
        JSONArray optJSONArray = this.d.mData.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(Long.valueOf(optJSONArray.optLong(i2)));
            }
        }
    }

    private void fillStringArray(String str, ArrayList<String> arrayList) {
        JSONArray optJSONArray = this.d.mData.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(optJSONArray.optString(i2));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadImageCandidates$1(String str, QueryParams queryParams) {
        queryParams.setImageOnly().setLimit(500);
        queryParams.setFileIds(str);
        queryParams.setSortBy(0);
    }

    private void loadImageCandidates(String str, ArrayList<RecapImageCandidate> arrayList, String str2) {
        if (!TextUtils.isEmpty(str)) {
            Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new B(str, 27));
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        do {
                            MediaItem load = MediaItemLoader.load(query);
                            arrayList.add(new RecapImageCandidate(load, str2).applyPreLoaded(this.d.mContentsCache.get(Long.valueOf(load.getFileId()))));
                        } while (query.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (query != null) {
                query.close();
            }
            sortMediaItemsByFileIds(arrayList, str);
            return;
        }
        return;
        throw th;
    }

    private void loadImageCandidatesByArrayId(JSONObject jSONObject, String str, ArrayList<RecapImageCandidate> arrayList) {
        loadImageCandidates(readContentsIds(jSONObject.optJSONArray(str)), arrayList, str);
        int count = (int) arrayList.stream().filter(new e(14)).count();
        int size = arrayList.size() - count;
        CharSequence charSequence = this.TAG;
        StringBuilder u = C0212a.u("loadImageCandidates-", str, " : v(", count, ") + h(");
        u.append(size);
        u.append(") = ");
        u.append(arrayList.size());
        Log.i(charSequence, u.toString());
    }

    private void loadImageCandidatesByDoubleArray(JSONObject jSONObject, String str, ArrayList<ArrayList<RecapImageCandidate>> arrayList) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                ArrayList arrayList2 = new ArrayList();
                String readContentsIds = readContentsIds(optJSONArray.getJSONArray(i2));
                loadImageCandidates(readContentsIds, arrayList2, str + i2);
                arrayList.add(arrayList2);
            }
        }
    }

    private String readContentsIds(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (!jSONArray.isNull(i2)) {
                String trim = jSONArray.getString(i2).trim();
                if (trim.startsWith("[")) {
                    return readContentsIds(new JSONArray(trim));
                }
                AnalyzedData.JSON_CONTENTS json_contents = new AnalyzedData.JSON_CONTENTS(trim);
                this.d.mContentsCache.put(Long.valueOf(json_contents.contents), json_contents);
                stringJoiner.add(json_contents.contents + "");
            }
        }
        return stringJoiner.toString();
    }

    private static void sortMediaItemsByFileIds(ArrayList<RecapImageCandidate> arrayList, String str) {
        arrayList.sort(new c(8, List.of(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX))));
    }

    public AnalyzedData parse(JSONObject jSONObject) {
        AnalyzedData analyzedData = new AnalyzedData(jSONObject);
        this.d = analyzedData;
        analyzedData.type = jSONObject.optInt("type");
        try {
            AnalyzedData analyzedData2 = this.d;
            if (analyzedData2.type == 999) {
                analyzedData2.devTest = true;
                int optInt = jSONObject.optInt("userSelectedType");
                if (optInt > 0) {
                    jSONObject.put("type", optInt);
                    this.d.type = optInt;
                }
            }
            this.d.fromPreview = jSONObject.optBoolean("preview");
            buildImageCandidatesReal(jSONObject);
            this.d.year = jSONObject.optInt("year");
            this.d.fromMonth = jSONObject.optInt("fromMonth");
            AnalyzedData analyzedData3 = this.d;
            int i2 = analyzedData3.fromMonth;
            if (i2 > 0) {
                analyzedData3.fromMonthAbbrString = TimeUtil.getAbbrMonth(i2);
                AnalyzedData analyzedData4 = this.d;
                analyzedData4.fromMonthFullString = TimeUtil.getFullMonth(analyzedData4.fromMonth);
            }
            this.d.toMonth = jSONObject.optInt("toMonth");
            AnalyzedData analyzedData5 = this.d;
            int i7 = analyzedData5.toMonth;
            if (i7 > 0) {
                analyzedData5.toMonthAbbrString = TimeUtil.getAbbrMonth(i7);
            }
            AnalyzedData analyzedData6 = this.d;
            if (analyzedData6.type == 3286) {
                if (analyzedData6.fromMonth > 0) {
                    analyzedData6.quarter = ((this.d.fromMonth / 4) + 1) + "Q";
                } else {
                    CharSequence charSequence = this.TAG;
                    Log.e(charSequence, "wrong quarter : " + this.d.fromMonth);
                    this.d.quarter = "";
                }
            }
            this.d.mostActiveDay = jSONObject.optLong("mostActiveDay");
            AnalyzedData analyzedData7 = this.d;
            long j2 = analyzedData7.mostActiveDay;
            if (j2 != 0) {
                analyzedData7.mostActiveDayBasicIsoDate = TimeUtil.getBasicIsoUtcDate(j2);
            }
            this.d.totalFaces = Math.min(9999, jSONObject.optInt("totalFaces"));
            this.d.totalPeople = Math.min(9999, jSONObject.optInt("totalPeople"));
            AnalyzedData analyzedData8 = this.d;
            analyzedData8.totalPet = analyzedData8.totalFaces - analyzedData8.totalPeople;
            analyzedData8.totalDays = jSONObject.optInt("totalDays");
            this.d.totalImages = Math.min(99999, jSONObject.optInt("totalImages"));
            this.d.totalVideos = Math.min(99999, jSONObject.optInt("totalVideos"));
            AnalyzedData analyzedData9 = this.d;
            analyzedData9.totalContents = Math.min(99999, analyzedData9.totalImages + analyzedData9.totalVideos);
            this.d.totalPlaceCount = Math.min(999, jSONObject.optInt("totalLocations"));
            this.d.newLocationCount = Math.min(999, jSONObject.optInt("newLocations"));
            this.d.keyMomentsDayCount = jSONObject.optInt("keyMomentsDayCount");
            this.d.keyMomentsLocation = jSONObject.optString("keyMomentsLocation");
            this.d.version = jSONObject.optInt("version");
            this.d.timestamp = jSONObject.optLong("timestamp");
            fillStringArray("totalLocationNames", this.d.mLocationNames);
            fillStringArray("newLocationNames", this.d.mNewLocationNames);
            this.d.mostActiveDayCount = jSONObject.optInt("mostActiveDayCount");
            fillLongArray("topActiveDays", this.d.mTopActiveDays);
        } catch (JSONException unused) {
        }
        return this.d;
    }
}
