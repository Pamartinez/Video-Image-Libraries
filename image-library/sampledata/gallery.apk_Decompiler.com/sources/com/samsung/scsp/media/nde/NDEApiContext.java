package com.samsung.scsp.media.nde;

import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.util.StringUtil;
import com.samsung.scsp.media.Media;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NDEApiContext {
    private boolean hasNDEUploadRequest = false;
    private String originalBinaryHash;
    private long originalBinarySize;
    private final String originalLocalPath;
    private String originalUrl;
    private String photoId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface NDEApiContextParams {
        public static final String HAS_NDE_UPLOAD_REQUEST = "nde_ctxt_has_nde_upload_request";
        public static final String ORIGINAL_BINARY_HASH = "nde_ctxt_original_binary_hash";
        public static final String ORIGINAL_BINARY_SIZE = "nde_ctxt_original_binary_size";
        public static final String ORIGINAL_PATH_TO_UPLOAD = "nde_ctxt_original_path_to_upload";
        public static final String ORIGINAL_URL = "nde_ctxt_original_url";
        public static final String PHOTO_ID = "nde_ctxt_photo_id";
    }

    public NDEApiContext(Media media) {
        this.originalBinaryHash = media.originalBinaryHash;
        this.originalBinarySize = ((Long) Optional.ofNullable(media.originalBinarySize).orElse(0L)).longValue();
        this.originalLocalPath = media.originalLocalPath;
        this.originalUrl = "";
        this.photoId = media.photoId;
    }

    public void configureApiContext(SCHashMap sCHashMap) {
        if (hasNDERequest()) {
            sCHashMap.put(NDEApiContextParams.HAS_NDE_UPLOAD_REQUEST, Boolean.valueOf(this.hasNDEUploadRequest));
            sCHashMap.put(NDEApiContextParams.ORIGINAL_BINARY_HASH, this.originalBinaryHash);
            sCHashMap.put(NDEApiContextParams.ORIGINAL_BINARY_SIZE, Long.valueOf(this.originalBinarySize));
            sCHashMap.put(NDEApiContextParams.ORIGINAL_PATH_TO_UPLOAD, this.originalLocalPath);
            sCHashMap.put(NDEApiContextParams.ORIGINAL_URL, this.originalUrl);
            sCHashMap.put(NDEApiContextParams.PHOTO_ID, this.photoId);
        }
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public boolean hasNDERequest() {
        return !StringUtil.isEmpty(this.originalBinaryHash);
    }

    public boolean hasNDEUpload() {
        return this.hasNDEUploadRequest;
    }

    public void setNdeUploadRequest(boolean z) {
        this.hasNDEUploadRequest = z;
    }

    public void setOriginalBinaryInfo(String str, long j2) {
        this.originalBinaryHash = str;
        this.originalBinarySize = j2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("NDEParams{hasNDEUploadRequest=");
        sb2.append(this.hasNDEUploadRequest);
        sb2.append(", originalLocalPath='");
        sb2.append(this.originalLocalPath);
        sb2.append("', originalBinaryHash='");
        sb2.append(this.originalBinaryHash);
        sb2.append("', originalBinarySize=");
        sb2.append(this.originalBinarySize);
        sb2.append(", originalUrl='");
        sb2.append(this.originalUrl);
        sb2.append("', photoId='");
        return C0212a.p(sb2, this.photoId, "'}");
    }

    public NDEApiContext(SCHashMap sCHashMap) {
        boolean z = false;
        Boolean asBoolean = sCHashMap.getAsBoolean(NDEApiContextParams.HAS_NDE_UPLOAD_REQUEST);
        if (asBoolean != null && asBoolean.booleanValue()) {
            z = true;
        }
        this.hasNDEUploadRequest = z;
        this.originalBinaryHash = sCHashMap.getAsString(NDEApiContextParams.ORIGINAL_BINARY_HASH);
        this.originalBinarySize = ((Long) Optional.ofNullable(sCHashMap.getAsLong(NDEApiContextParams.ORIGINAL_BINARY_SIZE)).orElse(0L)).longValue();
        this.originalLocalPath = sCHashMap.getAsString(NDEApiContextParams.ORIGINAL_PATH_TO_UPLOAD);
        this.originalUrl = sCHashMap.getAsString(NDEApiContextParams.ORIGINAL_URL);
        this.photoId = sCHashMap.getAsString(NDEApiContextParams.PHOTO_ID);
    }
}
