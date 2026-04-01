package com.samsung.scsp.media.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.api.AbstractApiControl;
import com.samsung.scsp.framework.core.api.ApiClass;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.Requests;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.util.StringUtil;
import com.samsung.scsp.media.Media;
import com.samsung.scsp.media.MediaApiSpec;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.file.FileApiContract;
import com.samsung.scsp.media.nde.NDEApiHelper;
import i.C0212a;
import java.lang.reflect.Type;

@ApiClass(MediaApiImpl.class)
@Requests({"GET_CHANGES", "CREATE_DATA", "UPDATE_DATA", "UPDATE_DATA_V2", "UPDATE_ORIENTATION", "UPDATE_LOCATION", "DELETE_DATA", "DOWNLOAD_BINARY", "DOWNLOAD_NDE_BINARY", "DOWNLOAD_THUMBNAIL", "CREATE_UPLOAD_URL", "UPLOAD_FILE", "UPLOAD_FILE_ONE_DRIVE", "ONE_DRIVE_URL_CHECK", "GET_DATA", "GET_LATEST_LIST", "GET_STREAM", "GET_DOWNLOAD_URL", "GET_DOWNLOAD_URL_HD_SCALED_VIDEO", "DOWNLOAD_HD_SCALED_VIDEO", "CHANGE_SERVICE_STATUS", "GET_SERVICE_STATUS", "RESTORE_PHOTOS", "DELETE_PHOTOS_IN_TRASH", "EMPTY_TRASH", "TRASH_LIST"})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaApiControlImpl extends AbstractApiControl {
    /* access modifiers changed from: private */
    public final OneDriveRangeUploadManager rangeUploadManager = new OneDriveRangeUploadManager(this.api);

    public MediaApiControlImpl() {
        add(new AbstractApiControl.Request(MediaApiSpec.Control.GET_CHANGES_WITHOUT_PAGING) {
            public void execute(ApiContext apiContext, Listeners listeners) {
                apiContext.name = "GET_CHANGES";
                super.execute(apiContext, listeners);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.UPLOAD) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$execute$0(ApiContext apiContext, Listeners listeners, SCHashMap sCHashMap) {
                if (sCHashMap.containsKey("location")) {
                    String asString = sCHashMap.getAsString("location");
                    if (MediaApiContract.Parameter.ONE_DRIVE.equals(asString)) {
                        MediaApiControlImpl.this.uploadToOneDrive(apiContext, sCHashMap, listeners);
                        return;
                    }
                    throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, C0212a.l("The location is invalid. Location: ", asString));
                }
            }

            public void execute(ApiContext apiContext, Listeners listeners) {
                apiContext.name = MediaApiSpec.CREATE_UPLOAD_URL;
                Listeners listeners2 = new Listeners();
                listeners2.networkStatusListener = listeners.networkStatusListener;
                listeners2.progressListener = listeners.progressListener;
                listeners2.responseListener = new a(this, apiContext, listeners, 0);
                MediaApiControlImpl.this.api.execute(apiContext, listeners2);
                apiContext.name = "CREATE_DATA";
                apiContext.payload = new Gson().toJson(apiContext.content, (Type) Media.class);
                MediaApiControlImpl.this.api.execute(apiContext, listeners);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.UPLOAD_RESUMABLE) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$execute$0(ApiContext apiContext, SCHashMap sCHashMap, Listeners listeners, SCHashMap sCHashMap2) {
                if (sCHashMap2.containsKey("location")) {
                    String asString = sCHashMap2.getAsString("location");
                    if (MediaApiContract.Parameter.ONE_DRIVE.equals(asString)) {
                        MediaApiControlImpl.this.rangeUploadManager.onCreatedUploadUrl(apiContext, sCHashMap, sCHashMap2);
                        MediaApiControlImpl.this.uploadToOneDrive(apiContext, sCHashMap2, listeners, sCHashMap);
                        return;
                    }
                    throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, C0212a.l("The location is invalid. Location: ", asString));
                }
            }

            public void execute(ApiContext apiContext, Listeners listeners) {
                Listeners listeners2;
                ApiContext apiContext2;
                AnonymousClass3 r22;
                SCHashMap onPrepareResumableUpload = MediaApiControlImpl.this.rangeUploadManager.onPrepareResumableUpload(apiContext, listeners);
                if (MediaApiControlImpl.this.rangeUploadManager.isCanResumable(onPrepareResumableUpload)) {
                    MediaApiControlImpl.this.uploadToOneDrive(apiContext, onPrepareResumableUpload, listeners, onPrepareResumableUpload);
                    r22 = this;
                    apiContext2 = apiContext;
                    listeners2 = listeners;
                } else {
                    apiContext.name = MediaApiSpec.CREATE_UPLOAD_URL;
                    Listeners listeners3 = new Listeners();
                    listeners3.networkStatusListener = listeners.networkStatusListener;
                    listeners3.progressListener = listeners.progressListener;
                    r22 = this;
                    apiContext2 = apiContext;
                    listeners2 = listeners;
                    listeners3.responseListener = new b(r22, apiContext2, onPrepareResumableUpload, listeners2, 0);
                    MediaApiControlImpl.this.api.execute(apiContext2, listeners3);
                }
                MediaApiControlImpl.this.rangeUploadManager.onCommitResumableUpload(apiContext2, onPrepareResumableUpload);
                apiContext2.name = "CREATE_DATA";
                apiContext2.payload = new Gson().toJson(apiContext2.content, (Type) Media.class);
                MediaApiControlImpl.this.api.execute(apiContext2, listeners2);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.UPDATE) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$execute$0(ApiContext apiContext, Listeners listeners, SCHashMap sCHashMap) {
                if (sCHashMap.containsKey("location")) {
                    String asString = sCHashMap.getAsString("location");
                    if (MediaApiContract.Parameter.ONE_DRIVE.equals(asString)) {
                        MediaApiControlImpl.this.uploadToOneDrive(apiContext, sCHashMap, listeners);
                        return;
                    }
                    throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, C0212a.l("The location is invalid. Location: ", asString));
                }
            }

            public void execute(ApiContext apiContext, Listeners listeners) {
                apiContext.name = MediaApiSpec.CREATE_UPLOAD_URL;
                Listeners listeners2 = new Listeners();
                listeners2.networkStatusListener = listeners.networkStatusListener;
                listeners2.progressListener = listeners.progressListener;
                listeners2.responseListener = new a(this, apiContext, listeners, 1);
                MediaApiControlImpl.this.api.execute(apiContext, listeners2);
                apiContext.name = "UPDATE_DATA";
                apiContext.payload = new Gson().toJson(apiContext.content, (Type) Media.class);
                MediaApiControlImpl.this.api.execute(apiContext, listeners);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.REVERT_WITH_UPDATE) {
            public void execute(ApiContext apiContext, Listeners listeners) {
                MediaApiControlImpl.this.handleResumableUpdate(apiContext, listeners, MediaApiSpec.UPDATE_DATA_V2);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.UPDATE_RESUMABLE) {
            public void execute(ApiContext apiContext, Listeners listeners) {
                String str;
                boolean isNDEUploadApi = new NDEApiHelper(apiContext.scontext).isNDEUploadApi(apiContext);
                MediaApiControlImpl mediaApiControlImpl = MediaApiControlImpl.this;
                if (isNDEUploadApi) {
                    str = MediaApiSpec.UPDATE_DATA_V2;
                } else {
                    str = "UPDATE_DATA";
                }
                mediaApiControlImpl.handleResumableUpdate(apiContext, listeners, str);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.DOWNLOAD_ORIGINAL_BINARY) {
            public void execute(ApiContext apiContext, Listeners listeners) {
                MediaApiControlImpl.this.downloadBinary(MediaApiSpec.GET_DOWNLOAD_URL, apiContext, listeners);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.DOWNLOAD_NDE_ORIGINAL_BINARY) {
            public void execute(ApiContext apiContext, Listeners listeners) {
                MediaApiControlImpl.this.downloadNDEOriginalBinary(MediaApiSpec.GET_DOWNLOAD_URL, apiContext, listeners);
            }
        });
        add(new AbstractApiControl.Request(MediaApiSpec.Control.DOWNLOAD_HD_SCALED_VIDEO) {
            public void execute(ApiContext apiContext, Listeners listeners) {
                apiContext.name = MediaApiSpec.DOWNLOAD_HD_SCALED_VIDEO;
                Listeners listeners2 = new Listeners();
                listeners2.responseListener = new c(apiContext, 0);
                MediaApiControlImpl.this.api.execute(apiContext, listeners2);
                apiContext.name = MediaApiSpec.DOWNLOAD_BINARY;
                MediaApiControlImpl.this.api.execute(apiContext, listeners);
            }
        });
    }

    /* access modifiers changed from: private */
    public void downloadBinary(String str, ApiContext apiContext, Listeners listeners) {
        apiContext.name = str;
        Listeners listeners2 = new Listeners();
        listeners2.responseListener = new c(apiContext, 1);
        this.api.execute(apiContext, listeners2);
        apiContext.name = MediaApiSpec.DOWNLOAD_BINARY;
        this.api.execute(apiContext, listeners);
    }

    /* access modifiers changed from: private */
    public void downloadNDEOriginalBinary(String str, ApiContext apiContext, Listeners listeners) {
        apiContext.name = str;
        NDEApiHelper nDEApiHelper = new NDEApiHelper(apiContext.scontext);
        Listeners listeners2 = new Listeners();
        listeners2.responseListener = new a(apiContext, nDEApiHelper, (Media) apiContext.content);
        this.api.execute(apiContext, listeners2);
        String asString = apiContext.parameters.getAsString(MediaApiContract.Parameter.ORIGINAL_URL);
        if (!StringUtil.isEmpty(asString)) {
            apiContext.parameters.put("url", asString);
            apiContext.name = MediaApiSpec.DOWNLOAD_NDE_BINARY;
            this.api.execute(apiContext, listeners);
            return;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "error to get itemOriginal download url");
    }

    /* access modifiers changed from: private */
    public void handleResumableUpdate(ApiContext apiContext, Listeners listeners, String str) {
        Listeners listeners2;
        ApiContext apiContext2;
        MediaApiControlImpl mediaApiControlImpl;
        SCHashMap onPrepareResumableUpload = this.rangeUploadManager.onPrepareResumableUpload(apiContext, listeners);
        if (this.rangeUploadManager.isCanResumable(onPrepareResumableUpload)) {
            uploadToOneDrive(apiContext, onPrepareResumableUpload, listeners, onPrepareResumableUpload);
            mediaApiControlImpl = this;
            apiContext2 = apiContext;
            listeners2 = listeners;
        } else {
            apiContext.name = MediaApiSpec.CREATE_UPLOAD_URL;
            Listeners listeners3 = new Listeners();
            listeners3.networkStatusListener = listeners.networkStatusListener;
            listeners3.progressListener = listeners.progressListener;
            mediaApiControlImpl = this;
            apiContext2 = apiContext;
            listeners2 = listeners;
            listeners3.responseListener = new b(mediaApiControlImpl, apiContext2, onPrepareResumableUpload, listeners2, 1);
            mediaApiControlImpl.api.execute(apiContext2, listeners3);
        }
        mediaApiControlImpl.rangeUploadManager.onCommitResumableUpload(apiContext2, onPrepareResumableUpload);
        apiContext2.name = str;
        apiContext2.payload = new Gson().toJson(apiContext2.content, (Type) Media.class);
        mediaApiControlImpl.api.execute(apiContext2, listeners2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$downloadNDEOriginalBinary$2(ApiContext apiContext, NDEApiHelper nDEApiHelper, Media media, JsonObject jsonObject) {
        apiContext.parameters.put("url", jsonObject.get("url").getAsString());
        nDEApiHelper.handleDownloadUrlResponse(apiContext, media, jsonObject);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleResumableUpdate$0(ApiContext apiContext, SCHashMap sCHashMap, Listeners listeners, SCHashMap sCHashMap2) {
        if (sCHashMap2.containsKey("location")) {
            String asString = sCHashMap2.getAsString("location");
            if (MediaApiContract.Parameter.ONE_DRIVE.equals(asString)) {
                this.rangeUploadManager.onCreatedUploadUrl(apiContext, sCHashMap, sCHashMap2);
                uploadToOneDrive(apiContext, sCHashMap2, listeners, sCHashMap);
                return;
            }
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, C0212a.l("The location is invalid. Location: ", asString));
        }
    }

    private void uploadFileToOneDrive(ApiContext apiContext, Listeners listeners, String str, String str2, long j2, long j3) {
        apiContext.name = MediaApiSpec.UPLOAD_FILE_ONE_DRIVE;
        apiContext.parameters.put("url", str);
        apiContext.parameters.put(FileApiContract.Parameter.PATH_TO_UPLOAD, str2);
        apiContext.parameters.put("size", Long.valueOf(j2));
        long j8 = j2 - j3;
        Listeners listeners2 = new Listeners();
        listeners2.networkStatusListener = listeners.networkStatusListener;
        do {
            apiContext.parameters.put(FileApiContract.Parameter.RANGE_START, Long.valueOf(j3));
            if (j8 > 62914560) {
                apiContext.parameters.put(FileApiContract.Parameter.LENGTH, 62914560L);
                j8 -= 62914560;
                j3 += 62914560;
            } else {
                apiContext.parameters.put(FileApiContract.Parameter.LENGTH, Long.valueOf(j8));
                j8 = 0;
            }
            this.api.execute(apiContext, listeners2);
        } while (j8 > 0);
    }

    /* access modifiers changed from: private */
    public void uploadToOneDrive(ApiContext apiContext, SCHashMap sCHashMap, Listeners listeners) {
        uploadToOneDrive(apiContext, sCHashMap, listeners, (SCHashMap) null);
    }

    /* access modifiers changed from: private */
    public void uploadToOneDrive(ApiContext apiContext, SCHashMap sCHashMap, Listeners listeners, SCHashMap sCHashMap2) {
        this.rangeUploadManager.onStartedResumableUpload(sCHashMap2);
        if (!this.rangeUploadManager.isDefaultUploadCompleted(sCHashMap2)) {
            uploadFileToOneDrive(apiContext, listeners, sCHashMap.getAsString("url"), apiContext.parameters.getAsString(FileApiContract.Parameter.PATH_TO_UPLOAD), apiContext.parameters.getAsLong("size").longValue(), this.rangeUploadManager.getDefaultRangeStart(sCHashMap2));
        }
        if (!this.rangeUploadManager.isItemOriginalUploadCompleted(sCHashMap2) && new NDEApiHelper(SContext.getInstance()).isNDEUploadApi(apiContext)) {
            uploadFileToOneDrive(apiContext, listeners, sCHashMap.getAsString(MediaApiContract.Parameter.ORIGINAL_URL), apiContext.parameters.getAsString(MediaApiContract.Parameter.ORIGINAL_PATH_TO_UPLOAD), apiContext.parameters.getAsLong(MediaApiContract.Parameter.ORIGINAL_BINARY_SIZE).longValue(), this.rangeUploadManager.getItemOriginalRangeStart(sCHashMap2));
        }
    }
}
