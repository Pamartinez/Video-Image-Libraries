package com.samsung.android.gallery.module.lottie.recap.data.parser;

import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnalyzedData {
    boolean devTest;
    public int fromMonth;
    public String fromMonthAbbrString = "";
    public String fromMonthFullString = "";
    public boolean fromPreview;
    public int keyMomentsDayCount;
    public String keyMomentsLocation;
    HashMap<Long, JSON_CONTENTS> mContentsCache = new HashMap<>();
    final JSONObject mData;
    public final ArrayList<RecapImageCandidate> mFaceCandidates = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mImageCandidates = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mKeyMomentsCandidates = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mLocationCandidates = new ArrayList<>();
    public ArrayList<String> mLocationNames = new ArrayList<>();
    public final ArrayList<ArrayList<RecapImageCandidate>> mMonthlyBestCandidates = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mMostActiveDayContents = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mNewLocationCandidates = new ArrayList<>();
    public ArrayList<String> mNewLocationNames = new ArrayList<>();
    ArrayList<Long> mTopActiveDays = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mTopActiveDaysContents = new ArrayList<>();
    public final ArrayList<ArrayList<RecapImageCandidate>> mTopFrequentFacesCandidates = new ArrayList<>();
    public final ArrayList<RecapImageCandidate> mWithTopFrequentFacesCandidates = new ArrayList<>();
    public long mostActiveDay;
    public String mostActiveDayBasicIsoDate = "";
    public int mostActiveDayCount;
    public int newLocationCount;
    public String quarter;
    public long timestamp;
    public int toMonth;
    public String toMonthAbbrString = "";
    public int totalContents;
    public int totalDays;
    public int totalFaces;
    public int totalImages;
    public int totalPeople;
    public int totalPet;
    public int totalPlaceCount;
    public int totalVideos;
    public int type;
    public int version;
    public int year;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class JSON_CONTENTS {
        public String city;
        public long contents;
        public boolean hasPeoplePetTag;
        public boolean hasSceneryTag;
        public int height;
        public boolean isNew;
        public final ArrayList<FaceData> mFaces = new ArrayList<>();
        public int orientation;
        public int uniqueDays;
        String value;
        public int width;

        public JSON_CONTENTS(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.contents = jSONObject.optLong("contents");
                this.width = jSONObject.optInt("width");
                this.height = jSONObject.optInt("height");
                this.orientation = jSONObject.optInt("orientation");
                this.isNew = jSONObject.optBoolean("isNew");
                this.city = jSONObject.optString(BuddyContract.Address.CITY);
                this.uniqueDays = jSONObject.optInt("uniqueDays");
                this.value = jSONObject.optString("value");
                this.hasPeoplePetTag = jSONObject.optBoolean("hasPeoplePetTag");
                this.hasSceneryTag = jSONObject.optBoolean("hasSceneryTag");
                JSONArray optJSONArray = jSONObject.optJSONArray("faces");
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                        FaceData faceData = new FaceData();
                        faceData.name = optJSONObject.optString("name");
                        faceData.recommendedId = optJSONObject.optLong("recommendedId");
                        faceData.personId = optJSONObject.optLong(PersonBundleWrapper.BUNDLE_KEY_PERSON_ID);
                        faceData.orientation = this.orientation;
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("facePos");
                        if (optJSONArray2 != null) {
                            faceData.facePos[0] = optJSONArray2.optDouble(0);
                            faceData.facePos[1] = optJSONArray2.optDouble(1);
                            faceData.facePos[2] = optJSONArray2.optDouble(2);
                            faceData.facePos[3] = optJSONArray2.optDouble(3);
                        }
                        this.mFaces.add(faceData);
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public AnalyzedData(JSONObject jSONObject) {
        this.mData = jSONObject;
    }

    public int getType() {
        return this.type;
    }

    public boolean isDevTest() {
        return this.devTest;
    }

    public String optString(String str, String str2) {
        return this.mData.optString(str, str2);
    }
}
