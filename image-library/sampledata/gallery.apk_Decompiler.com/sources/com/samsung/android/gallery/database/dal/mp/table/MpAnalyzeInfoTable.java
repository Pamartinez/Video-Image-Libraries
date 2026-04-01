package com.samsung.android.gallery.database.dal.mp.table;

import C3.p;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MpAnalyzeInfoTable {
    public static final boolean SUPPORT;
    public static final boolean SUPPORT_C2PA_TYPE;
    public static final boolean SUPPORT_CACHE;
    public static final boolean SUPPORT_MOTION_PHOTO_VIEW_MODE;
    public static final boolean SUPPORT_STORY;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProjectionHolder {
        static final List<String> projections = buildProjection(false);
        static final List<String> storyProjections = buildProjection(true);

        public static List<String> buildProjection(boolean z) {
            ArrayList arrayList = new ArrayList();
            if (MpAnalyzeInfoTable.SUPPORT) {
                ArrayList arrayList2 = new ArrayList();
                if (MpAnalyzeInfoTable.SUPPORT_MOTION_PHOTO_VIEW_MODE) {
                    arrayList2.add("'vm='||motionphoto_viewmode");
                }
                if (MpAnalyzeInfoTable.SUPPORT_C2PA_TYPE) {
                    arrayList2.add("'c2pa='||c2pa_type");
                }
                if (MpAnalyzeInfoTable.SUPPORT_STORY && z) {
                    arrayList2.add("'tsr='||total_smartcrop_ratio");
                    arrayList2.add("'tsdr='||total_smartcrop_device_ratio");
                    arrayList2.add("'mfc='||mfc_score");
                    arrayList2.add("'effect='||effect_type");
                }
                arrayList2.add("CASE WHEN ai_cache is null THEN '' ELSE 'cc='||hex(ai_cache) END");
                arrayList.add("(select concat_ws('\u001f'," + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2) + ") from analyze_info where A._id=sec_media_id) as __analyze_info");
                return arrayList;
            }
            if (MpAnalyzeInfoTable.SUPPORT_CACHE) {
                arrayList.add("A.ai_cache as __media_cache");
            }
            return arrayList;
        }
    }

    static {
        boolean z;
        boolean z3;
        boolean z7;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_MP_MEDIA_CACHE_TABLE);
        SUPPORT = isEnabled;
        boolean z9 = true;
        if (isEnabled || Features.isEnabled(Features.SUPPORT_MP_MEDIA_CACHE)) {
            z = true;
        } else {
            z = false;
        }
        SUPPORT_CACHE = z;
        if (!isEnabled || !Features.isEnabled(Features.SUPPORT_C2PA_DB)) {
            z3 = false;
        } else {
            z3 = true;
        }
        SUPPORT_C2PA_TYPE = z3;
        if (!isEnabled || !Features.isEnabled(Features.SUPPORT_MP_MOTION_PHOTO_VIEW_MODE)) {
            z7 = false;
        } else {
            z7 = true;
        }
        SUPPORT_MOTION_PHOTO_VIEW_MODE = z7;
        if (!isEnabled || !Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            z9 = false;
        }
        SUPPORT_STORY = z9;
    }

    public static List<String> getProjections() {
        return ProjectionHolder.projections;
    }

    public static List<String> getStoryProjections() {
        return ProjectionHolder.storyProjections;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$unpack$0(String str) {
        return !str.isEmpty();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$unpack$1(HashMap hashMap, String str) {
        if (str.startsWith("c2pa=")) {
            hashMap.put("__c2paType", Integer.valueOf(str.substring(5)));
        } else if (str.startsWith("vm=")) {
            hashMap.put("__motionPhotoViewMode", Integer.valueOf(str.substring(3)));
        } else if (str.startsWith("cc=")) {
            hashMap.put("__media_cache", str.substring(3));
        } else if (str.startsWith("tsr=")) {
            hashMap.put("__total_smartcrop_ratio", str.substring(4));
        } else if (str.startsWith("tsdr=")) {
            hashMap.put("__total_smartcrop_device_ratio", str.substring(5));
        } else if (str.startsWith("mfc=")) {
            hashMap.put("__mfc_score", str.substring(4));
        } else if (str.startsWith("effect=")) {
            hashMap.put("__effect_type", str.substring(7));
        }
    }

    public static Map<String, Object> unpack(String str) {
        HashMap hashMap = new HashMap();
        Arrays.stream(str.split("\u001f")).filter(new e(9)).forEach(new p(11, hashMap));
        return hashMap;
    }
}
