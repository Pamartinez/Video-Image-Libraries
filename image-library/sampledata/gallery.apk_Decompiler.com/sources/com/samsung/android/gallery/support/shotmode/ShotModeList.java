package com.samsung.android.gallery.support.shotmode;

import E9.b;
import Za.a;
import android.util.SparseArray;
import com.samsung.android.gallery.support.R$drawable;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.function.BiPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShotModeList {
    private static final boolean IS_ONE_UI_60 = SdkConfig.atLeast(SdkConfig.SEM.U);
    private static final boolean IS_ONE_UI_70;
    private static final boolean IS_ROS = SdkConfig.atLeast(SdkConfig.GED.R);
    private static final boolean IS_TOS = SdkConfig.atLeast(SdkConfig.GED.T);
    private static final boolean SUPPORT_3D_CAPTURE;
    private static final boolean SUPPORT_DUAL_REC = Features.isEnabled(Features.SUPPORT_DUAL_REC);
    private final SparseArray<ShotMode> indexRecMode;
    private final SparseArray<ShotMode> indexSefType;
    private final HashMap<String, ShotMode> indexTranslatedName;
    private final HashMap<String, ShotMode> indexType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final ShotModeList instance = new ShotModeList(0);
    }

    static {
        boolean atLeast = SdkConfig.atLeast(SdkConfig.SEM.V);
        IS_ONE_UI_70 = atLeast;
        SUPPORT_3D_CAPTURE = atLeast;
    }

    public /* synthetic */ ShotModeList(int i2) {
        this();
    }

    private void addSefImages() {
        int i2;
        add(new ShotMode.Builder("panorama", 1).appendSefType(2272).setTitle(R$string.panorama).setTitleForCaptureMode(R$string.camera_capture_mode_panorama).build());
        add(new ShotMode.Builder("GIF", 1).setTitle(R$string.gif).build());
        boolean z = IS_ROS;
        if (!z) {
            add(new ShotMode.Builder("selective_focus", 1).appendSefType(2112).setTitle(R$string.selective_focus).build());
            add(new ShotMode.Builder("virtual_shot", 1).appendSefType(2256).setTitle(R$string.virtual_shot).build());
        }
        add(new ShotMode.Builder("shot_and_more", 1).appendSefType(2096).setTitle(R$string.shot_n_more).build());
        add(new ShotMode.Builder("360_photo", 1).appendSefType(2640).setTitle(R$string.image_360).setIcon(R$drawable.gallery_ic_thumbnail_360_view).build());
        add(new ShotMode.Builder("motion_photo", 1).appendSefType(2608).setTitle(R$string.speak_motion_photo).setTitleForCaptureMode(R$string.camera_capture_mode_motion_photo).build());
        ShotMode.Builder appendSefTypeIf = new ShotMode.Builder("live_focus", 1).appendSefType(2736, 2752, 2880).appendSefTypeIf(z, 3008, 3024, 3040).appendSefTypeIf(IS_TOS, 3216, 3232);
        if (SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
            i2 = R$string.shot_mode_portrait;
        } else {
            i2 = R$string.live_focus;
        }
        add(appendSefTypeIf.setTitle(i2).setTitleForCaptureMode(R$string.camera_capture_mode_portrait).build());
        add(new ShotMode.Builder("Selfie focus", 1).appendSefType(2880).setTitle(R$string.live_focus).build());
        add(new ShotMode.Builder("Dual capture", 1).appendSefType(2752).setTitle(R$string.dual_capture).build());
        add(new ShotMode.Builder("sticker", 1).appendSefType(2864).setTitle(R$string.camera_mode_stickers).build());
        add(new ShotMode.Builder("selfie", 1).appendSefType(2304, 2320, 2416, 2417, 2432).includeSefTypeForSearchIf(z, 3008).setTitle(R$string.selfie).build());
        add(new ShotMode.Builder("burst_shot", 1).setTitle(StringResources.getBurstShotStringId(R$string.burst_shot)).setTitleForCaptureMode(StringResources.getBurstShotStringId(R$string.camera_capture_mode_burst_shot)).setIcon(R$drawable.gallery_ic_thumbnail_burst).build());
        add(new ShotMode.Builder("Similar photo", 1).setTitle(R$string.similar_photo).setIcon(R$drawable.gallery_ic_thumbnail_similar).build());
        if (IS_ONE_UI_60) {
            add(new ShotMode.Builder("document_scan", 1).appendSefType(2960).setTitle(R$string.scan).build());
            add(new ShotMode.Builder("pro", 1).appendSefType(2544).setTitle(R$string.pro).build());
            add(new ShotMode.Builder("raw", 1).setTitle(R$string.raw_shot).setIcon(R$drawable.gallery_ic_thumbnail_raw_icon).build());
        }
        if (SUPPORT_3D_CAPTURE) {
            add(new ShotMode.Builder("3d_capture_image", 1).appendSefType(3568, 3584).setTitle(R$string.capture_3d).build());
        }
    }

    private void addSefVideos() {
        int i2;
        int i7;
        boolean z = !PreferenceFeatures.Poc.SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH;
        add(new ShotMode.Builder("slow_motion", 3).appendRecordingMode((Collection<Integer>) RecordingMode.listOfSlowMoNds()).excludeRecordingModeForSearchIf(z, 101).setTitle(R$string.slow_motion).setTitleForCaptureMode(R$string.camera_capture_mode_slow_motion).setIcon(R$drawable.gallery_ic_thumbnail_slow_motion).build());
        add(new ShotMode.Builder("hyperlapse", 3).appendRecordingMode((Collection<Integer>) RecordingMode.listOfHyperLapse()).setTitle(R$string.hyper_motion).setTitleForCaptureMode(R$string.camera_capture_mode_hyperlapse).setIcon(R$drawable.gallery_ic_thumbnail_hyperlapse).build());
        if (!IS_ROS) {
            add(new ShotMode.Builder("fast_motion", 3).appendRecordingMode(2).setTitle(R$string.fast_motion).setIcon(R$drawable.gallery_ic_thumbnail_fast_motion).build());
        }
        ShotMode.Builder title = new ShotMode.Builder("video_collage", 3).appendRecordingMode(3).setTitle(R$string.clip_studio);
        int i8 = R$drawable.gallery_ic_thumbnail_video;
        add(title.setIcon(i8).build());
        add(new ShotMode.Builder("super_slow_mo", 3).appendRecordingMode((Collection<Integer>) RecordingMode.listOfSuperSlowMoNds()).excludeRecordingModeForSearchIf(z, 111).setTitle(R$string.super_slow_mo).setTitleForCaptureMode(R$string.camera_capture_mode_super_slow_motion).setIcon(R$drawable.gallery_ic_thumbnail_super_slow_motion).build());
        add(new ShotMode.Builder("360_video", 3).setTitle(R$string.video_360).setIcon(R$drawable.gallery_ic_thumbnail_360_video).build());
        ShotMode.Builder appendSefType = new ShotMode.Builder("directors_view", 3).appendSefType(3088);
        boolean z3 = SUPPORT_DUAL_REC;
        add(appendSefType.includeSefTypeForSearchIf(z3, 3312).setTitle(R$string.directors_view).setTitleForCaptureMode(R$string.camera_capture_mode_directors_view).setIcon(R$drawable.gallery_ic_thumbnail_directors_icon).build());
        ShotMode.Builder includeSefTypeForSearchIf = new ShotMode.Builder("Dual_Recording_Info", 3).appendSefType(3312).includeSefTypeForSearchIf(z3, 3088);
        if (z3) {
            i2 = R$string.dual_rec;
        } else {
            i2 = R$string.camera_capture_mode_dual_recording;
        }
        ShotMode.Builder icon = includeSefTypeForSearchIf.setTitle(i2).setIcon(getDualRectListThumbnailResId());
        if (z3) {
            i7 = R$string.dual_rec;
        } else {
            i7 = R$string.camera_capture_mode_dual_recording;
        }
        add(icon.setTitleForCaptureMode(i7).build());
        if (IS_ONE_UI_60) {
            add(new ShotMode.Builder("pro_video", 3).appendRecordingMode((Collection<Integer>) RecordingMode.listOfPro()).setTitle(R$string.pro_video).setIcon(i8).build());
            add(new ShotMode.Builder("portrait_video", 3).appendRecordingMode(24).appendRecordingMode(28).setTitle(R$string.portrait_video).setIcon(i8).build());
        }
        if (SUPPORT_3D_CAPTURE) {
            add(new ShotMode.Builder("3d_capture_video", 3).appendRecordingMode(29).setTitle(R$string.capture_3d).setIcon(i8).build());
        }
        if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_VIEWER)) {
            ShotMode.Builder appendSefType2 = new ShotMode.Builder("live_effect_video", 3).appendSefType(3505);
            int i10 = R$string.live_effect;
            add(appendSefType2.setTitle(i10).setTitleForCaptureMode(i10).build());
        }
        ShotMode.Builder appendRecordingMode = new ShotMode.Builder("apv_video", 3).appendRecordingMode((Collection<Integer>) RecordingMode.listOfApv());
        int i11 = R$string.apv_video;
        add(appendRecordingMode.setTitle(i11).setTitleForCaptureMode(i11).build());
        if (IS_ONE_UI_70) {
            add(new ShotMode.Builder("log_video", 3).appendRecordingMode((Collection<Integer>) RecordingMode.listOfLogVideo()).setTitle(R$string.camera_capture_mode_log_video).setIcon(R$drawable.gallery_ic_thumbnail_log_video).build());
        }
    }

    private ArrayList<ShotMode> findShotMode(HashMap<String, ShotMode> hashMap, String str, boolean z) {
        a aVar;
        ArrayList<ShotMode> arrayList = new ArrayList<>();
        if (!(hashMap == null || str == null)) {
            String lowerCase = str.toLowerCase();
            if (z) {
                aVar = new a(0);
            } else {
                aVar = new a(1);
            }
            hashMap.forEach(new b(aVar, lowerCase, arrayList, 2));
        }
        return arrayList;
    }

    private int getDualRectListThumbnailResId() {
        if (!SUPPORT_DUAL_REC) {
            return R$drawable.gallery_ic_thumbnail_video;
        }
        if (!SdkConfig.FirstApiLevel.LESS_THAN_U) {
            return R$drawable.gallery_ic_thumbnail_dualrec_icon;
        }
        return R$drawable.gallery_ic_thumbnail_directors_icon;
    }

    public static ShotModeList getInstance() {
        return LazyHolder.instance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$findShotMode$0(BiPredicate biPredicate, String str, ArrayList arrayList, String str2, ShotMode shotMode) {
        if (biPredicate.test(str2.toLowerCase(), str)) {
            arrayList.add(shotMode);
        }
    }

    public void add(ShotMode shotMode) {
        this.indexType.put(shotMode.type, shotMode);
        Iterator<Integer> it = shotMode.getSefTypes().iterator();
        while (it.hasNext()) {
            this.indexSefType.put(it.next().intValue(), shotMode);
        }
        Iterator<Integer> it2 = shotMode.getRecordingModes().iterator();
        while (it2.hasNext()) {
            this.indexRecMode.put(it2.next().intValue(), shotMode);
        }
        try {
            this.indexTranslatedName.put(AppResources.getString(shotMode.titleRes).toLowerCase(Locale.getDefault()), shotMode);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("add failed e="), "ShotModeList");
        }
    }

    public ArrayList<ShotMode> findByStringKeyword(String str, boolean z) {
        ArrayList<ShotMode> arrayList = new ArrayList<>();
        String replaceAll = str.trim().replaceAll("''", "'");
        arrayList.addAll(findShotMode(this.indexTranslatedName, replaceAll, z));
        arrayList.addAll(findShotMode(this.indexType, replaceAll, z));
        return arrayList;
    }

    public ShotMode getByRecMode(int i2) {
        return this.indexRecMode.get(i2);
    }

    public ShotMode getBySefValue(int i2) {
        return this.indexSefType.get(i2);
    }

    public ShotMode getByType(String str) {
        if (str == null) {
            return null;
        }
        return this.indexType.get(str);
    }

    private ShotModeList() {
        this.indexType = new HashMap<>();
        this.indexSefType = new SparseArray<>();
        this.indexRecMode = new SparseArray<>();
        this.indexTranslatedName = new HashMap<>();
        add(new ShotMode.Builder("Contents", 0).setTitle(R$string.contents).build());
        add(new ShotMode.Builder("image", 1).setTitle(R$string.image).build());
        add(new ShotMode.Builder("video", 3).setTitle(R$string.video).build());
        addSefImages();
        addSefVideos();
        add(new ShotMode.Builder("Single Taken", 1).setTitle(R$string.single_take).setTitleForCaptureMode(R$string.camera_capture_mode_single_take).setIcon(R$drawable.gallery_ic_thumbnail_sigletake).build());
        if (SUPPORT_3D_CAPTURE) {
            add(new ShotMode.Builder("3d_capture", 0).setTitle(R$string.capture_3d).build());
        }
    }
}
