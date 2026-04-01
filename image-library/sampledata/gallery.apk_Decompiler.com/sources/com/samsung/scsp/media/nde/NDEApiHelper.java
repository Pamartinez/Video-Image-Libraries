package com.samsung.scsp.media.nde;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.JsonObject;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.util.HashUtil;
import com.samsung.scsp.framework.core.util.StringUtil;
import com.samsung.scsp.media.Media;
import com.samsung.scsp.media.MediaApiSpec;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.io.File;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NDEApiHelper {
    private Logger logger = Logger.get(getClass().getSimpleName());
    private final SContext scontext;

    public NDEApiHelper(SContext sContext) {
        this.scontext = sContext;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onPrepareExecuteUpdate$1(NDEApiContext nDEApiContext) {
        return "onPrepareExecuteUpdate: NDE context - " + nDEApiContext;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onPrepareExecuteUpload$0(NDEApiContext nDEApiContext) {
        return "onPrepareExecuteUpload: NDE context - " + nDEApiContext;
    }

    public void handleCreateUploadUrlRequest(ApiContext apiContext, JsonObject jsonObject) {
        NDEApiContext nDEApiContext = new NDEApiContext(apiContext.parameters);
        if (nDEApiContext.hasNDERequest() && nDEApiContext.hasNDEUpload()) {
            jsonObject.addProperty(MediaApiContract.Parameter.ORIGINAL_BINARY_HASH, apiContext.parameters.getAsString(MediaApiContract.Parameter.ORIGINAL_BINARY_HASH));
            jsonObject.addProperty(MediaApiContract.Parameter.ORIGINAL_BINARY_SIZE, (Number) apiContext.parameters.getAsLong(MediaApiContract.Parameter.ORIGINAL_BINARY_SIZE));
        }
    }

    public void handleCreateUploadUrlResponse(HttpRequest httpRequest, JsonObject jsonObject, SCHashMap sCHashMap) {
        NDEApiContext nDEApiContext = new NDEApiContext(httpRequest.getParameters());
        Logger logger2 = this.logger;
        logger2.i("handleCreateUploadUrlResponse: has NDE : " + nDEApiContext.hasNDERequest() + ArcCommonLog.TAG_COMMA + nDEApiContext.hasNDEUpload() + ArcCommonLog.TAG_COMMA + nDEApiContext);
        if (nDEApiContext.hasNDERequest() && nDEApiContext.hasNDEUpload() && jsonObject.has(MediaApiContract.Parameter.ORIGINAL_URL)) {
            sCHashMap.put(MediaApiContract.Parameter.ORIGINAL_URL, jsonObject.get(MediaApiContract.Parameter.ORIGINAL_URL).getAsString());
        }
    }

    public void handleDownloadUrlResponse(ApiContext apiContext, Media media, JsonObject jsonObject) {
        this.logger.i("handleDownloadUrlResponse: ");
        if (StringUtil.isEmpty(media.originalBinaryHash)) {
            this.logger.i("handleDownloadUrlResponse: media record has no nde original hash");
        } else {
            Optional.ofNullable(jsonObject.get(MediaApiContract.Parameter.ORIGINAL_URL)).ifPresent(new b(13, apiContext));
        }
    }

    public boolean isNDEMedia(Media media) {
        return new NDEApiContext(media).hasNDERequest();
    }

    public boolean isNDEUploadApi(ApiContext apiContext) {
        return new NDEApiContext(apiContext.parameters).hasNDEUpload();
    }

    public void onPrepareExecuteUpdate(ApiContext apiContext, Media media, String str) {
        Logger logger2 = this.logger;
        logger2.i("onPrepareExecuteUpdate: " + str);
        if (str.equals(MediaApiSpec.Control.REVERT_WITH_UPDATE)) {
            media.originalBinaryHash = null;
            media.originalBinarySize = null;
        } else if (!StringUtil.isEmpty(media.originalLocalHash) && !StringUtil.isEmpty(media.originalLocalPath)) {
            try {
                File file = new File(media.originalLocalPath);
                String fileSHA256 = HashUtil.getFileSHA256(file);
                long length = file.length();
                if (StringUtil.isEmpty(media.originalBinaryHash) || !media.originalBinaryHash.equals(fileSHA256)) {
                    media.originalBinaryHash = fileSHA256;
                    media.originalBinarySize = Long.valueOf(length);
                    apiContext.parameters.put(MediaApiContract.Parameter.ORIGINAL_BINARY_HASH, media.originalBinaryHash);
                    apiContext.parameters.put(MediaApiContract.Parameter.ORIGINAL_BINARY_SIZE, media.originalBinarySize);
                    apiContext.parameters.put(MediaApiContract.Parameter.ORIGINAL_PATH_TO_UPLOAD, media.originalLocalPath);
                    NDEApiContext nDEApiContext = new NDEApiContext(media);
                    nDEApiContext.setNdeUploadRequest(true);
                    nDEApiContext.setOriginalBinaryInfo(media.originalBinaryHash, media.originalBinarySize.longValue());
                    nDEApiContext.configureApiContext(apiContext.parameters);
                    this.logger.d(new a(nDEApiContext, 1));
                    return;
                }
                Logger logger3 = this.logger;
                logger3.i("onPrepareExecuteUpdate: already synced media as nde type in server - " + media.photoId + " / " + media.originalBinaryHash);
            } catch (Throwable unused) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "itemOriginal file hash calculation fail");
            }
        }
    }

    public void onPrepareExecuteUpload(ApiContext apiContext, Media media) {
        this.logger.i("onPrepareExecuteUpload");
        if (!StringUtil.isEmpty(media.originalLocalHash) && !StringUtil.isEmpty(media.originalLocalPath)) {
            try {
                File file = new File(media.originalLocalPath);
                media.originalBinaryHash = HashUtil.getFileSHA256(file);
                media.originalBinarySize = Long.valueOf(file.length());
                apiContext.parameters.put(MediaApiContract.Parameter.ORIGINAL_BINARY_HASH, media.originalBinaryHash);
                apiContext.parameters.put(MediaApiContract.Parameter.ORIGINAL_BINARY_SIZE, media.originalBinarySize);
                apiContext.parameters.put(MediaApiContract.Parameter.ORIGINAL_PATH_TO_UPLOAD, media.originalLocalPath);
                NDEApiContext nDEApiContext = new NDEApiContext(media);
                nDEApiContext.setNdeUploadRequest(true);
                nDEApiContext.setOriginalBinaryInfo(media.originalBinaryHash, media.originalBinarySize.longValue());
                nDEApiContext.configureApiContext(apiContext.parameters);
                this.logger.d(new a(nDEApiContext, 0));
            } catch (Throwable unused) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "itemOriginal file hash calculation fail");
            }
        }
    }
}
