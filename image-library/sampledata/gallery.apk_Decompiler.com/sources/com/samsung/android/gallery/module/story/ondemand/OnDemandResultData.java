package com.samsung.android.gallery.module.story.ondemand;

import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandResultData {
    private final List<Long> extraLong;
    private final String query;
    private final String relationData;
    private final int resultType;
    private final List<Long> similarGroups;
    private final long storyId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum OnDemandStoryReturns {
        LESS_CONTENTS_TO_CREATE(-2),
        TIMEOUT(-1),
        FAIL_GENERAL(0),
        STORY_CREATED(1),
        EXIST_SIMILAR_STORIES(2),
        NEED_TO_CONFIRM_RELATION(3);
        
        final int code;

        private OnDemandStoryReturns(int i2) {
            this.code = i2;
        }

        public int getCode() {
            return this.code;
        }
    }

    public OnDemandResultData(Bundle bundle) {
        ArrayList arrayList;
        ArrayList arrayList2;
        bundle = bundle == null ? new Bundle() : bundle;
        String string = bundle.getString("KEY_GEN_RESULT_QUERY");
        this.query = TextUtils.isEmpty(string) ? "" : string;
        this.storyId = bundle.getLong("KEY_GEN_RESULT_STORY_ID", -1);
        this.resultType = bundle.getInt("KEY_GEN_RESULT_TYPE", -174921387);
        this.relationData = parseEntityResult(bundle.getString("KEY_GEN_RESULT_RELATION_DATA"));
        ArrayList arrayList3 = (ArrayList) bundle.getSerializable("KEY_GEN_RESULT_SIMILAR_GROUP");
        if (arrayList3 == null) {
            arrayList = new ArrayList();
        }
        this.similarGroups = arrayList;
        ArrayList arrayList4 = (ArrayList) bundle.getSerializable("KEY_GEN_RESULT_EXTRA_LONG");
        if (arrayList4 == null) {
            arrayList2 = new ArrayList();
        }
        this.extraLong = arrayList2;
    }

    public static String getAnalyticsSuccessDetail(OnDemandResultData onDemandResultData) {
        List<Long> list;
        if (onDemandResultData == null || onDemandResultData.resultType != OnDemandStoryReturns.STORY_CREATED.getCode() || (list = onDemandResultData.extraLong) == null || list.isEmpty()) {
            return null;
        }
        return "" + onDemandResultData.extraLong.get(0);
    }

    private String parseEntityResult(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new JSONObject(str).getJSONArray("relation").toString();
            }
            return "";
        } catch (JSONException e) {
            Log.w("OnDemandResultData", "parseEntityResult " + e);
            return "";
        }
    }

    public static boolean valid(OnDemandResultData onDemandResultData) {
        if (onDemandResultData == null || onDemandResultData.resultType == -174921387) {
            return false;
        }
        return true;
    }

    public boolean existSimilarStories() {
        if (this.resultType == OnDemandStoryReturns.EXIST_SIMILAR_STORIES.getCode()) {
            return true;
        }
        return false;
    }

    public String getQuery() {
        return this.query;
    }

    public String getRelationData() {
        return this.relationData;
    }

    public String getResultMessage() {
        if (existSimilarStories()) {
            return AppResources.getString(R$string.fail_generate_story_result_similar_story_already_created);
        }
        if (isFail()) {
            return AppResources.getString(R$string.fail_generate_story_result_try_again);
        }
        return "";
    }

    public int getResultType() {
        return this.resultType;
    }

    public List<Long> getSimilarGroups() {
        return this.similarGroups;
    }

    public long getStoryId() {
        return this.storyId;
    }

    public boolean isFail() {
        if (this.resultType <= OnDemandStoryReturns.FAIL_GENERAL.getCode()) {
            return true;
        }
        return false;
    }

    public boolean isTimedOut() {
        if (this.resultType == OnDemandStoryReturns.TIMEOUT.getCode()) {
            return true;
        }
        return false;
    }

    public boolean needConfirmRelation() {
        if (this.resultType == OnDemandStoryReturns.NEED_TO_CONFIRM_RELATION.getCode()) {
            return true;
        }
        return false;
    }

    public String toDebugString() {
        StringBuilder sb2 = new StringBuilder("ODS{story=");
        sb2.append(this.storyId);
        sb2.append(",type=");
        sb2.append(this.resultType);
        sb2.append(",relation=");
        sb2.append(this.relationData);
        sb2.append(",similar=");
        sb2.append(StringCompat.toString(this.similarGroups));
        sb2.append(",query=");
        return C0212a.p(sb2, this.query, "}");
    }

    public String toString() {
        return "ODS{story=" + this.storyId + ",type=" + this.resultType + ",relation=" + Logger.getEncodedString(this.relationData) + ",similar=" + StringCompat.toString(this.similarGroups) + "}";
    }
}
