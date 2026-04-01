package com.samsung.android.gallery.support.shotmode;

import c0.C0086a;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShotMode {
    public final int iconRes;
    final ArrayList<Integer> mRecordingModes;
    final ArrayList<Integer> mRecordingModesForSearch = new ArrayList<>();
    final ArrayList<Integer> mSefTypes;
    final ArrayList<Integer> mSefTypesForSearch = new ArrayList<>();
    public final int mediaType;
    public final int titleRes;
    public final int titleResForCapture;
    public final String type;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        int iconRes = -1;
        int mediaType;
        HashMap<Integer, Boolean> recordingModeVars = new HashMap<>();
        ArrayList<Integer> recordingModes = new ArrayList<>();
        HashMap<Integer, Boolean> sefTypeVars = new HashMap<>();
        ArrayList<Integer> sefTypes = new ArrayList<>();
        int titleRes = -1;
        int titleResForCapture = -1;
        String type;

        public Builder(String str, int i2) {
            this.type = str;
            this.mediaType = i2;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$0(ShotMode shotMode, Integer num, Boolean bool) {
            if (bool.booleanValue()) {
                shotMode.mSefTypesForSearch.add(num);
            } else {
                shotMode.mSefTypesForSearch.remove(num);
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$1(ShotMode shotMode, Integer num, Boolean bool) {
            if (bool.booleanValue()) {
                shotMode.mRecordingModesForSearch.add(num);
            } else {
                shotMode.mRecordingModesForSearch.remove(num);
            }
        }

        public Builder appendRecordingMode(int i2) {
            this.recordingModes.add(Integer.valueOf(i2));
            return this;
        }

        public Builder appendSefType(int i2) {
            this.sefTypes.add(Integer.valueOf(i2));
            return this;
        }

        public Builder appendSefTypeIf(boolean z, int... iArr) {
            if (z) {
                for (int valueOf : iArr) {
                    this.sefTypes.add(Integer.valueOf(valueOf));
                }
            }
            return this;
        }

        public ShotMode build() {
            ShotMode shotMode = new ShotMode(this.type, this.mediaType, this.titleRes, this.titleResForCapture, this.iconRes, this.sefTypes, this.recordingModes);
            shotMode.mSefTypesForSearch.addAll(this.sefTypes);
            this.sefTypeVars.forEach(new a(shotMode, 0));
            shotMode.mRecordingModesForSearch.addAll(this.recordingModes);
            this.recordingModeVars.forEach(new a(shotMode, 1));
            return shotMode;
        }

        public Builder excludeRecordingModeForSearchIf(boolean z, int i2) {
            if (z) {
                this.recordingModeVars.put(Integer.valueOf(i2), Boolean.FALSE);
            }
            return this;
        }

        public Builder includeSefTypeForSearchIf(boolean z, int i2) {
            if (z) {
                this.sefTypeVars.put(Integer.valueOf(i2), Boolean.TRUE);
            }
            return this;
        }

        public Builder setIcon(int i2) {
            this.iconRes = i2;
            return this;
        }

        public Builder setTitle(int i2) {
            this.titleRes = i2;
            return this;
        }

        public Builder setTitleForCaptureMode(int i2) {
            this.titleResForCapture = i2;
            return this;
        }

        public Builder appendRecordingMode(Collection<Integer> collection) {
            this.recordingModes.addAll(collection);
            return this;
        }

        public Builder appendSefType(int... iArr) {
            for (int valueOf : iArr) {
                this.sefTypes.add(Integer.valueOf(valueOf));
            }
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShotModeHolder {
        static final HashMap<String, String> sShotModeMap = new HashMap<String, String>() {
            {
                put("image", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_NORMAL_IMAGE.toString());
                AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_VIDEO;
                put("video", analyticsEventId.toString());
                put("video_collage", analyticsEventId.toString());
                AnalyticsEventId analyticsEventId2 = AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_HYPERLAPSE;
                put("slow_motion", analyticsEventId2.toString());
                put("hyperlapse", analyticsEventId2.toString());
                put("fast_motion", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_FAST_MOTION.toString());
                put("super_slow_mo", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_SUPER_SLOW_MOTION.toString());
                put("360_video", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_360_VIDEO.toString());
                put("panorama", AnalyticsDetail.EVENT_DETAIL_VIEW_PLAY_ICON_PANORAMA.toString());
                AnalyticsEventId analyticsEventId3 = AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_SELECTIVE_FOCUS;
                put("selective_focus", analyticsEventId3.toString());
                put("GIF", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_GIF.toString());
                put("virtual_shot", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_VIRTUAL_SHOT.toString());
                put("shot_and_more", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_SHOT_AND_MORE.toString());
                put("360_photo", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_360_PHOTO.toString());
                put("motion_photo", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_MOTION_PHOTO.toString());
                AnalyticsEventId analyticsEventId4 = AnalyticsEventId.EVENT_DETAIL_VIEW_LIVE_FOCUS;
                put("live_focus", analyticsEventId4.toString());
                put("Dual capture", analyticsEventId4.toString());
                put("Selfie focus", analyticsEventId3.toString());
                put("sticker", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_STICKER.toString());
                put("selfie", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_SELFIE.toString());
                put("burst_shot", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_BURSTSHOT.toString());
                put("Single Taken", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_SINGLE_TAKE.toString());
                put("Similar photo", AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_SIMILAR_PHOTO.toString());
            }
        };
    }

    public ShotMode(String str, int i2, int i7, int i8, int i10, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
        this.type = str;
        this.mediaType = i2;
        this.titleRes = i7;
        this.titleResForCapture = i8;
        this.iconRes = i10;
        this.mSefTypes = arrayList;
        this.mRecordingModes = arrayList2;
    }

    public static String getShotModeEventId(String str) {
        String str2 = ShotModeHolder.sShotModeMap.get(str);
        if (str2 != null) {
            return str2;
        }
        return "";
    }

    public boolean contains(int i2) {
        return this.mSefTypes.contains(Integer.valueOf(i2));
    }

    public ArrayList<Integer> getRecordingModes() {
        return this.mRecordingModes;
    }

    public ArrayList<Integer> getRecordingModesForSearch() {
        return this.mRecordingModesForSearch;
    }

    public ArrayList<Integer> getSefTypes() {
        return this.mSefTypes;
    }

    public ArrayList<Integer> getSefTypesForSearch() {
        return this.mSefTypesForSearch;
    }

    public boolean isImage() {
        if (this.mediaType == 1) {
            return true;
        }
        return false;
    }

    public boolean isVideo() {
        if (this.mediaType == 3) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ShotMode{");
        sb2.append(this.type);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.mediaType, "}");
    }
}
