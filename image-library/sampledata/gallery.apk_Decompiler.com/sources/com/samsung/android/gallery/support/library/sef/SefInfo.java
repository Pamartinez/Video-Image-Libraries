package com.samsung.android.gallery.support.library.sef;

import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.util.HashMap;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SefInfo {
    SOUND_SHOT_META_INFO(2048, "SoundShot_Meta_Info"),
    AUTO_ENHANCE_INFO(2240, "Auto_Enhance_Info"),
    AUTO_ENHANCE_IMAGE_UNPROCESSED(0, "Auto_Enhance_Unprocessed"),
    AUTO_ENHANCE_IMAGE_PROCESSED(0, "Auto_Enhance_Processed"),
    TAG_SHOT_INFO(2448, "Tag_Shot_Info"),
    BURST_SHOT_INFO(2528, "Burst_Shot_Info"),
    BURST_SHOT_BEST_PHOTO_INFO(2529, "BurstShot_Best_Photo_Info"),
    IMAGE_UTC_DATA(2561, "Image_UTC_Data"),
    MOTION_PHOTO_DATA(2608, "MotionPhoto_Data"),
    MOTION_PHOTO_VERSION(2609, "MotionPhoto_Version"),
    WATERMARK_INFO(3201, "Watermark_Info"),
    DUAL_SHOT_IMAGE_CLOSEUP(1, "DualShot_1"),
    DUAL_SHOT_IMAGE_WIDE(1, "DualShot_2"),
    DUAL_SHOT_IMAGE_LIVE_FOCUS(1, "DualShot_3"),
    DUAL_SHOT_INFO(2736, "DualShot_Meta_Info"),
    DUAL_SHOT_DEPTH_MAP_D(2737, "DualShot_DepthMap"),
    DUAL_SHOT_ZOOM_INOUT(2752, "ZoomInOut_Info"),
    DUAL_SHOT_ONLY(2768, "DualShot_Only_Info"),
    DUAL_SHOT_EXTRA_INFO(2739, "DualShot_Extra_Info"),
    SUPER_SLOW_MOTION_DATA(2816, "Super_SlowMotion_Data"),
    SLOW_MOTION_DATA(2192, "SlowMotion_Data"),
    FAST_MOTION_DATA(2208, "FastMotion_Data"),
    HIGHLIGHT_VIDEO_DATA(2224, "HighlightVideo_Data"),
    MOTION_PHOTO_AUTO_PLAY(2611, "MotionPhoto_AutoPlay"),
    HYPER_LAPSE_DATA(3328, "Hyperlapse_Data"),
    HYPER_LAPSE_DATA_SPEED(3328, "HyperLapse_Data_Speed"),
    CAMERA_STICKER_INFO(2864, "Camera_Sticker_Info"),
    CAMERA_STICKER_BGM_D(0, "Camera_Sticker_BGM"),
    SINGLE_SHOT_BOKEH_INFO(2880, "SingleShot_Meta_Info"),
    SINGLE_SHOT_DEPTH_MAP_D(2881, "SingleShot_DepthMap"),
    POST_PROCESSING_STATUS(2928, "PostProcess_Status"),
    SINGLE_TAKE_SHOT_INFO(2945, "Single_Take_Camera_Info"),
    SINGLE_TAKE_BEST_PHOTO_INFO(2946, "Single_Take_Camera_Representive_Info"),
    PE_RE_EDIT_DATA(2977, "PhotoEditor_Re_Edit_Data"),
    VE_RE_EDIT_DATA(2977, "VideoEditor_Re_Edit_Data"),
    ORIGINAL_PATH_HASH_KEY(2977, "Original_Path_Hash_Key"),
    COPY_AVAILABLE_EDIT_INFO(2978, "Copy_Available_Edit_Info"),
    SINGLE_RELIGHTING_BOKEH_INFO(3008, "Single_Relighting_Bokeh_Info"),
    DUAL_RELIGHTING_BOKEH_INFO(3024, "Dual_Relighting_Bokeh_Info"),
    REMASTER_INFO(3056, "Remaster_Info"),
    SCREEN_CAPTURE_INFO(3153, "Samsung_Capture_Info"),
    CAMERA_CAPTURE_MODE_INFO(3169, "Camera_Capture_Mode_Info"),
    PRO_WHITE_BALANCE_INFO(3185, "Pro_White_Balance_Info"),
    COLOR_DISPLAY_P3(3265, "Color_Display_P3"),
    PHOTO_HDR_INFO(3282, "Photo_HDR_Info"),
    CAMERA_SCENE_INFO(MediaDefs.Meta.SEF.SEF_KEY_TYPE_CAMERA_SCENE_INFO, MediaDefs.Meta.SEF.SEF_KEY_NAME_CAMERA_SCENE_INFO),
    PE_GENERATED_IMAGE_TYPE(3473, "PEg_Info"),
    LIVE_EFFECT(3505, "3DPhoto_Info"),
    VIRTUAL_APERTURE_EXPOSURE_INFO(3601, "VirtualAperture_Exposure_Info"),
    VIDEO_EDITED_UTC_OFFSET(3649, "Video_Edited_UTC_Offset"),
    RECAP_SAVED_VIDEO(3680, "Story_Recap_Info"),
    EOF(0, "");
    
    public final int keyCode;
    public final String keyName;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Data {
        public static final byte[] BURST_SHOT_BEST_PHOTO_DATA = null;

        static {
            BURST_SHOT_BEST_PHOTO_DATA = "BurstShotBestPhoto".getBytes();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataHolder {
        static final HashMap<String, SefInfo> map = null;

        static {
            map = new HashMap<String, SefInfo>() {
                {
                    Stream.of(SefInfo.values()).filter(new b(0)).forEach(new a(1, this));
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ boolean lambda$new$0(SefInfo sefInfo) {
                    if (sefInfo.keyCode > 1) {
                        return true;
                    }
                    return false;
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$new$1(SefInfo sefInfo) {
                    put(sefInfo.keyName, sefInfo);
                }
            };
        }
    }

    private SefInfo(int i2, String str) {
        this.keyCode = i2;
        this.keyName = str;
    }

    public static SefInfo of(String str) {
        return DataHolder.map.get(str);
    }

    public static int toKeyCode(String str) {
        SefInfo of2 = of(str);
        if (of2 != null) {
            return of2.keyCode;
        }
        return 0;
    }

    public boolean equals(String str) {
        return this.keyName.equals(str);
    }
}
