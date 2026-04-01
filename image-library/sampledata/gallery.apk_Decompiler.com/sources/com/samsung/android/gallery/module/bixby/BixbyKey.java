package com.samsung.android.gallery.module.bixby;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.StringResources;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BixbyKey {
    public static final LinkedHashMap<String, Integer> CONTENT_TYPE_NAME = new LinkedHashMap<String, Integer>() {
        {
            put("all", Integer.valueOf(R$string.contents));
            put("burstshot", Integer.valueOf(StringResources.getBurstShotStringId(R$string.burst_shot)));
            put("dualcamera", Integer.valueOf(R$string.dual_camera));
            put("dualshot", Integer.valueOf(R$string.dual_capture));
            put("fastmotion", Integer.valueOf(R$string.fast_motion));
            put("hyperlapse", Integer.valueOf(R$string.hyper_motion));
            put("image", Integer.valueOf(R$string.image));
            put("livefocus", Integer.valueOf(R$string.live_focus));
            put("portrait", Integer.valueOf(R$string.shot_mode_portrait));
            put("motionphoto", Integer.valueOf(R$string.speak_motion_photo));
            put("panorama", Integer.valueOf(R$string.panorama));
            put("selectivefocus", Integer.valueOf(R$string.selective_focus));
            put("selfie", Integer.valueOf(R$string.selfie));
            put("shotandmore", Integer.valueOf(R$string.shot_n_more));
            put("singletake", Integer.valueOf(R$string.single_take));
            put("slowmotion", Integer.valueOf(R$string.slow_motion));
            put("soundandshot", Integer.valueOf(R$string.sound_shot));
            put("stickermode", Integer.valueOf(R$string.camera_mode_stickers));
            put("superslow", Integer.valueOf(R$string.super_slow_mo));
            put("video", Integer.valueOf(R$string.video));
            put("videocollage", Integer.valueOf(R$string.clip_studio));
            put("virtualshot", Integer.valueOf(R$string.virtual_shot));
            put("360image", Integer.valueOf(R$string.image_360));
            put("360video", Integer.valueOf(R$string.video_360));
        }
    };
    private static final LinkedHashMap<Integer, String> IMAGE_TYPE_NAME = new LinkedHashMap<Integer, String>() {
        {
            put(2272, "panorama");
            put(2112, "selectivefocus");
            put(2256, "virtualshot");
            put(2096, "shotandmore");
            put(2640, "360image");
            put(2608, "motionphoto");
            put(2736, "livefocus");
            put(2880, "selfie");
            put(2752, "dualshot");
            put(2864, "stickermode");
            put(2304, "selfie");
            put(2320, "selfie");
            put(2416, "selfie");
            put(2417, "selfie");
            put(2432, "selfie");
        }
    };
    private static final LinkedHashMap<String, String> VIDEO_TYPE_NAME = new LinkedHashMap<String, String>() {
        {
            put("video", "video");
            put("360_video", "360video");
            put("hyperlapse", "hyperlapse");
            put("fast_motion", "fastmotion");
            put("slow_motion", "slowmotion");
            put("video_collage", "videocollage");
            put("super_slow_mo", "superslow");
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ErrorCallback {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ScreenType {
        UNKNOWN(0),
        VIEWER(1),
        PICTURES(2),
        ALBUMS(3),
        STORIES(4),
        STORY_PICTURES(5),
        SEARCH_RESULT(6),
        SEARCH_NO_RESULT(7),
        ALBUM_PICTURES(8);
        
        private final int mValue;

        private ScreenType(int i2) {
            this.mValue = i2;
        }

        public int toInt() {
            return this.mValue;
        }
    }

    public static String getImageContentType(int i2) {
        LinkedHashMap<Integer, String> linkedHashMap = IMAGE_TYPE_NAME;
        if (linkedHashMap.get(Integer.valueOf(i2)) != null) {
            return linkedHashMap.get(Integer.valueOf(i2));
        }
        return "image";
    }

    public static String getVideoContentType(String str) {
        LinkedHashMap<String, String> linkedHashMap = VIDEO_TYPE_NAME;
        if (linkedHashMap.get(str) != null) {
            return linkedHashMap.get(str);
        }
        return "video";
    }
}
