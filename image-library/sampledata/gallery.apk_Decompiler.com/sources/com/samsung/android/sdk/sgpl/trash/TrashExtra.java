package com.samsung.android.sdk.sgpl.trash;

import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashExtra {
    public static String ALBUM = "__album";
    public static String ARTIST = "__artist";
    public static String AUDIO_CODEC_INFO = "__audioCodecInfo";
    public static String BEST_IMAGE = "__bestImage";
    public static String BURST_SHOT_GROUP_ID = "__burstGroupID";
    public static String CAPTURED_APP = "__capturedAPP";
    public static String CAPTURED_URL = "__capturedURL";
    public static String CAPTURE_FRAMERATE = "__captureFramerate";
    public static String CLOUD_ORIGINAL_SIZE = "__cloudOriginalSize";
    public static String CLOUD_REVISION = "__cloudRevision";
    public static String CLOUD_SERVER_PATH = "__cloudServerPath";
    public static String CLOUD_THUMB_PATH = "__cloudTP";
    public static String DATETIME = "__dateTime";
    public static String DATE_ADDED = "__dateAdded";
    public static String DATE_MODIFIED = "__dateModified";
    public static String DATE_RESTORED = "__dateRestored";
    public static String DATE_TAKEN = "__dateTaken";
    public static String FILE_DURATION = "__fileDuration";
    public static String GROUP_MEDIA_ID = "__GroupMediaID";
    public static String GROUP_TYPE = "__group_type";
    public static String HASH = "__hash";
    public static String ID = "__absID";
    public static String IS_360_VIDEO = "__is360Video";
    public static String IS_DRM = "__isDrm";
    public static String IS_FAVOURITE = "__isFavourite";
    public static String IS_HDR_10_VIDEO = "__isHdr10Video";
    public static String LATITUDE = "__latitude";
    public static String LC_THUMB_PATH = "__lcThumbPath";
    public static String LONGITUDE = "__longitude";
    public static String MIME_TYPE = "__mimeType";
    public static String ORIGINAL_FILE_HASH = "__origin_file_hash";
    public static String RECORDING_MODE = "RECORDING_MODE";
    public static String RECORDING_TYPE = "__recordingType";
    public static String RELATIVE_PATH = "__relativePath";
    public static String RESOLUTION = "__resolution";
    public static String SEF_FILE_SUB_TYPE = "__sefFileSubType";
    public static String SEF_FILE_TYPE = "__sefFileType";
    public static String SEF_FILE_TYPES = "__sefFileTypes";
    public static String SIZE = "__size";
    public static String VIDEO_CODEC_INFO = "__videoCodecInfo";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private HashMap<String, Object> mMap;

        public Builder(String str) {
            this.mMap = new HashMap<>();
            if (!TextUtils.isEmpty(str)) {
                this.mMap = (HashMap) fromJsonString(this.mMap.getClass(), str);
            }
        }

        public static <T> T fromJsonString(Class<T> cls, String str) {
            return new GsonBuilder().serializeNulls().setPrettyPrinting().create().fromJson(str, cls);
        }

        public static String toJsonString(Object obj, boolean z) {
            GsonBuilder disableHtmlEscaping = new GsonBuilder().serializeNulls().disableHtmlEscaping();
            if (z) {
                disableHtmlEscaping.setPrettyPrinting();
            }
            return disableHtmlEscaping.create().toJson(obj);
        }

        public Builder append(String str, Object obj) {
            this.mMap.put(str, obj);
            return this;
        }

        public String build() {
            return toJsonString(this.mMap, false);
        }

        public Builder remove(String str) {
            this.mMap.remove(str);
            return this;
        }

        public Builder() {
            this((String) null);
        }
    }
}
