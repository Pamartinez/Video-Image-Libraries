package com.samsung.scsp.media;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.common.Holder;
import com.samsung.scsp.common.PageReader;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ApiControl;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.identity.ScspIdentity;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.listeners.ListenersHolder;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.framework.core.util.HashUtil;
import com.samsung.scsp.media.MediaApiSpec;
import com.samsung.scsp.media.MediaConstants;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.file.FileApiContract;
import com.samsung.scsp.media.nde.NDEApiHelper;
import java.lang.reflect.Type;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Files {
    /* access modifiers changed from: private */
    public ApiControl apiControl;
    private Logger logger = Logger.get(getClass().getSimpleName());
    private SContextHolder scontextHolder;

    /* renamed from: com.samsung.scsp.media.Files$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$scsp$media$MediaConstants$FileType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.scsp.media.MediaConstants$FileType[] r0 = com.samsung.scsp.media.MediaConstants.FileType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$scsp$media$MediaConstants$FileType = r0
                com.samsung.scsp.media.MediaConstants$FileType r1 = com.samsung.scsp.media.MediaConstants.FileType.ORIGINAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$scsp$media$MediaConstants$FileType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.scsp.media.MediaConstants$FileType r1 = com.samsung.scsp.media.MediaConstants.FileType.LOW     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$scsp$media$MediaConstants$FileType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.scsp.media.MediaConstants$FileType r1 = com.samsung.scsp.media.MediaConstants.FileType.THUMBNAIL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$scsp$media$MediaConstants$FileType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.scsp.media.MediaConstants$FileType r1 = com.samsung.scsp.media.MediaConstants.FileType.CACHE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.scsp.media.Files.AnonymousClass3.<clinit>():void");
        }
    }

    public Files(SContextHolder sContextHolder, ApiControl apiControl2) {
        this.scontextHolder = sContextHolder;
        this.apiControl = apiControl2;
    }

    private void changeServiceStatus(Boolean bool, Boolean bool2) {
        this.logger.i("changeServiceStatus() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.CHANGE_SERVICE_STATUS);
        JsonObject jsonObject = new JsonObject();
        if (bool != null) {
            jsonObject.addProperty(MediaApiContract.Parameter.ENABLED, bool);
        }
        if (bool2 != null) {
            jsonObject.addProperty(MediaApiContract.Parameter.ANALYSIS, bool2);
        }
        create.payload = jsonObject.toString();
        this.apiControl.execute(create, ListenersHolder.create().getListeners());
    }

    private void downloadBinary(String str, String str2, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        Logger logger2 = this.logger;
        logger2.i("downloadBinary() is called, " + str2);
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.Control.DOWNLOAD_ORIGINAL_BINARY);
        create.parameters.put("photoId", str);
        create.parameters.put("filePath", str2);
        Listeners listeners = new Listeners();
        listeners.networkStatusListener = networkStatusListener;
        listeners.progressListener = progressListener;
        this.apiControl.execute(create, listeners);
    }

    private void downloadItemOriginalBinary(Media media, String str, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        Logger logger2 = this.logger;
        logger2.i("downloadItemOriginalBinary() is called, " + str);
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.Control.DOWNLOAD_NDE_ORIGINAL_BINARY);
        create.parameters.put("photoId", media.photoId);
        create.parameters.put("filePath", str);
        create.content = media;
        this.apiControl.execute(create, ListenersHolder.create(networkStatusListener, progressListener).getListeners());
    }

    private void downloadThumbnail(String str, String str2, MediaConstants.FileType fileType, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        Logger logger2 = this.logger;
        logger2.i("downloadThumbnail() is called, " + fileType.getName() + ArcCommonLog.TAG_COMMA + str2);
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.DOWNLOAD_THUMBNAIL);
        create.parameters.put("photoId", str);
        create.parameters.put("filePath", str2);
        create.parameters.put("size", fileType.getName());
        this.apiControl.execute(create, ListenersHolder.create(networkStatusListener, progressListener).getListeners());
    }

    private Media executeUpdate(String str, Media media, NetworkStatusListener networkStatusListener, String str2) {
        Logger logger2 = this.logger;
        logger2.i("update: " + str2);
        VerifyParam.checkValidForUploadFile(str);
        VerifyParam.checkValidForUpdateData(media);
        ApiContext create = ApiContext.create(this.scontextHolder, str2);
        String generateValidationKey = HashUtil.generateValidationKey(media.hash, ScspIdentity.getToken(true));
        create.parameters.put(FileApiContract.Parameter.PATH_TO_UPLOAD, str);
        create.parameters.put("hash", media.hash);
        create.parameters.put(FileApiContract.Parameter.VALIDATION_KEY, generateValidationKey);
        create.parameters.put("size", media.size);
        create.parameters.put(FileApiContract.Parameter.CONTENT_TYPE, media.mimeType);
        create.parameters.put("photoId", media.photoId);
        create.content = media;
        new NDEApiHelper(create.scontext).onPrepareExecuteUpdate(create, media, str2);
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    private Media executeUpload(String str, Media media, NetworkStatusListener networkStatusListener, String str2) {
        this.logger.i(OpenSessionApi.ITEM_LIMIT_UPLOAD);
        VerifyParam.checkValidForUploadFile(str);
        VerifyParam.checkValidForUploadData(media);
        ApiContext create = ApiContext.create(this.scontextHolder, str2);
        String generateValidationKey = HashUtil.generateValidationKey(media.hash, ScspIdentity.getToken(true));
        create.parameters.put(FileApiContract.Parameter.PATH_TO_UPLOAD, str);
        create.parameters.put("hash", media.hash);
        create.parameters.put(FileApiContract.Parameter.VALIDATION_KEY, generateValidationKey);
        create.parameters.put("size", media.size);
        create.parameters.put(FileApiContract.Parameter.CONTENT_TYPE, media.mimeType);
        create.content = media;
        new NDEApiHelper(this.scontextHolder.scontext).onPrepareExecuteUpload(create, media);
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public Media createData(Media media, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForUploadData(media);
        this.logger.i("createData() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, "CREATE_DATA");
        create.payload = new Gson().toJson((Object) media, (Type) Media.class);
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public MediaList deleteData(List<Media> list, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForDeleteData(list);
        Logger logger2 = this.logger;
        logger2.i("deleteData(), size - " + list.size());
        ApiContext create = ApiContext.create(this.scontextHolder, "DELETE_DATA");
        create.parameters.put(MediaApiContract.Parameter.CLEAR, Boolean.FALSE);
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (Media next : list) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("photoId", next.photoId);
            jsonObject2.addProperty("clientTimestamp", (Number) next.clientTimestamp);
            jsonArray.add((JsonElement) jsonObject2);
        }
        jsonObject.add("list", jsonArray);
        create.payload = jsonObject.toString();
        return (MediaList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public void disableAnalysis() {
        changeServiceStatus((Boolean) null, Boolean.FALSE);
    }

    public void disableService() {
        changeServiceStatus(Boolean.FALSE, (Boolean) null);
    }

    public void downloadFile(String str, String str2, MediaConstants.FileType fileType, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForDownloadBinary(str, str2, fileType);
        int i2 = AnonymousClass3.$SwitchMap$com$samsung$scsp$media$MediaConstants$FileType[fileType.ordinal()];
        if (i2 == 1) {
            downloadBinary(str, str2, progressListener, networkStatusListener);
        } else if (i2 == 2 || i2 == 3 || i2 == 4) {
            downloadThumbnail(str, str2, fileType, progressListener, networkStatusListener);
        }
    }

    public void downloadHDVideo(String str, String str2, String str3, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForDownloadHDVideo(str);
        this.logger.i("downloadHDVideo() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.Control.DOWNLOAD_HD_SCALED_VIDEO);
        create.parameters.put("photoId", str);
        create.parameters.put("filePath", str2);
        if (str3 != null) {
            create.parameters.put("caller_package", str3);
        } else {
            create.parameters.put("caller_package", ContextFactory.getApplicationContext().getPackageName());
        }
        this.apiControl.execute(create, ListenersHolder.create(networkStatusListener, (ProgressListener) null).getListeners());
    }

    public void downloadItemOriginalFile(Media media, String str, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForDownloadItemOriginalBinary(media, str);
        downloadItemOriginalBinary(media, str, progressListener, networkStatusListener);
    }

    public void enableAnalysis() {
        changeServiceStatus((Boolean) null, Boolean.TRUE);
    }

    public void enableService() {
        changeServiceStatus(Boolean.TRUE, (Boolean) null);
    }

    public MediaList getChanges(MediaConstants.MediaType mediaType, String str, int i2, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForGetChanges(mediaType);
        Logger logger2 = this.logger;
        logger2.i("getChanges(), type = " + mediaType.getName() + ", nextChangePoint = " + str);
        ApiContext create = ApiContext.create(this.scontextHolder, "GET_CHANGES");
        if (!TextUtils.isEmpty(str)) {
            create.parameters.put(MediaApiContract.Parameter.CHANGE_POINT, str);
        } else {
            create.parameters.put(MediaApiContract.Parameter.CHANGE_POINT, "0");
        }
        if (i2 > 0) {
            create.parameters.put("count", Integer.valueOf(i2));
        }
        create.parameters.put("includeDetail", Boolean.TRUE);
        create.parameters.put(MediaApiContract.Parameter.EXCLUDE_DELETED, Boolean.FALSE);
        if (mediaType != MediaConstants.MediaType.ALL) {
            create.parameters.put("mediaType", mediaType.getName());
        }
        return (MediaList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public MediaList getChangesWithOutPaging(MediaConstants.MediaType mediaType, String str, int i2, final NetworkStatusListener networkStatusListener) {
        final ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.Control.GET_CHANGES_WITHOUT_PAGING);
        Logger logger2 = this.logger;
        logger2.i("getChangesWithOutPaging(), type = " + mediaType.getName());
        if (!TextUtils.isEmpty(str)) {
            create.parameters.put(MediaApiContract.Parameter.CHANGE_POINT, str);
        } else {
            create.parameters.put(MediaApiContract.Parameter.CHANGE_POINT, "0");
        }
        create.parameters.put("includeDetail", Boolean.TRUE);
        create.parameters.put(MediaApiContract.Parameter.EXCLUDE_DELETED, Boolean.FALSE);
        if (i2 > 0) {
            create.parameters.put("count", Integer.valueOf(i2));
        }
        if (mediaType != MediaConstants.MediaType.ALL) {
            create.parameters.put("mediaType", mediaType.getName());
        }
        final Holder holder = new Holder();
        return (MediaList) new PageReader() {
            /* access modifiers changed from: private */
            public static /* synthetic */ void lambda$read$0(ApiContext apiContext, Holder holder, MediaList mediaList) {
                if (mediaList.hasNext()) {
                    apiContext.parameters.put(MediaApiContract.Parameter.CHANGE_POINT, mediaList.getNextChangePoint());
                }
                ((MediaList) holder.get()).addAll(mediaList);
            }

            public MediaList read() {
                holder.hold(new MediaList((PageReader<MediaList>) this));
                Listeners listeners = new Listeners();
                NetworkStatusListener networkStatusListener = networkStatusListener;
                if (networkStatusListener != null) {
                    listeners.networkStatusListener = networkStatusListener;
                }
                listeners.responseListener = new b(create, holder, 0);
                Files.this.apiControl.execute(create, listeners);
                return (MediaList) holder.get();
            }
        }.read();
    }

    public MediaList getData(List<String> list, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForGetData(list);
        Logger logger2 = this.logger;
        logger2.i("getData(), count - " + list.size());
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.GET_DATA);
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (String addProperty : list) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("photoId", addProperty);
            jsonArray.add((JsonElement) jsonObject2);
        }
        jsonObject.add("list", jsonArray);
        create.payload = jsonObject.toString();
        return (MediaList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public String getDownloadUrl(String str, MediaConstants.FileType fileType, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForGetDownloadUrl(str, fileType);
        this.logger.i("getDownloadUrl() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.GET_DOWNLOAD_URL);
        create.parameters.put("photoId", str);
        create.parameters.put("download", fileType.getName());
        return ((JsonObject) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create)).get("url").getAsString();
    }

    public String getHDVideoDownloadUrl(String str, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForGetUrlHDVideo(str);
        this.logger.i("getHDVideoDownloadUrl() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.GET_DOWNLOAD_URL_HD_SCALED_VIDEO);
        create.parameters.put("photoId", str);
        return ((Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create)).downloadUrl;
    }

    public MediaList getLatestList(MediaConstants.MediaType mediaType, int i2, final NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForGetLatestList(mediaType);
        Logger logger2 = this.logger;
        logger2.i("getLatestList(), type = " + mediaType.getName());
        final ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.GET_LATEST_LIST);
        if (i2 > 0) {
            create.parameters.put("count", Integer.valueOf(i2));
        }
        SCHashMap sCHashMap = create.parameters;
        Boolean bool = Boolean.TRUE;
        sCHashMap.put("includeDetail", bool);
        create.parameters.put(MediaApiContract.Parameter.EXCLUDE_DELETED, bool);
        if (mediaType != MediaConstants.MediaType.ALL) {
            create.parameters.put("mediaType", mediaType.getName());
        }
        final Holder holder = new Holder();
        return (MediaList) new PageReader<MediaList>() {
            /* access modifiers changed from: private */
            public static /* synthetic */ void lambda$read$0(ApiContext apiContext, Holder holder, MediaList mediaList) {
                if (mediaList.hasNext()) {
                    apiContext.parameters.put("pageToken", mediaList.getNextPageToken());
                }
                ((MediaList) holder.get()).addAll(mediaList);
            }

            public MediaList read() {
                holder.hold(new MediaList((PageReader<MediaList>) this));
                Listeners listeners = new Listeners();
                listeners.networkStatusListener = networkStatusListener;
                listeners.responseListener = new b(create, holder, 1);
                Files.this.apiControl.execute(create, listeners);
                return (MediaList) holder.get();
            }
        }.read();
    }

    public ServiceStatus getServiceStatus() {
        this.logger.i("getServiceStatus() is called");
        return (ServiceStatus) C0086a.g(ListenersHolder.create(), this.apiControl, ApiContext.create(this.scontextHolder, MediaApiSpec.GET_SERVICE_STATUS));
    }

    public String getStream(String str, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForGetStream(str);
        this.logger.i("getStream() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.GET_STREAM);
        create.parameters.put("photoId", str);
        return ((JsonObject) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create)).get(MediaApiContract.Parameter.STREAMING_URL).getAsString();
    }

    public Media revertData(Media media, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForRevertData(media);
        this.logger.i("revertData() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.UPDATE_DATA_V2);
        media.originalBinaryHash = null;
        media.originalBinarySize = null;
        create.parameters.put("photoId", media.photoId);
        create.content = media;
        create.payload = new Gson().toJson((Object) media, (Type) Media.class);
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public Media revertWithUpdate(String str, Media media, NetworkStatusListener networkStatusListener) {
        return executeUpdate(str, media, networkStatusListener, MediaApiSpec.Control.REVERT_WITH_UPDATE);
    }

    public Media update(String str, Media media, NetworkStatusListener networkStatusListener) {
        return executeUpdate(str, media, networkStatusListener, MediaApiSpec.Control.UPDATE);
    }

    public Media updateData(Media media, NetworkStatusListener networkStatusListener) {
        String str;
        VerifyParam.checkValidForUpdateData(media);
        this.logger.i("updateData() is called");
        if (new NDEApiHelper(this.scontextHolder.scontext).isNDEMedia(media)) {
            str = MediaApiSpec.UPDATE_DATA_V2;
        } else {
            str = "UPDATE_DATA";
        }
        ApiContext create = ApiContext.create(this.scontextHolder, str);
        create.parameters.put("photoId", media.photoId);
        create.payload = new Gson().toJson((Object) media, (Type) Media.class);
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public Media updateLocation(String str, long j2, String str2, String str3, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForUpdateLocation(str, str2, str3);
        this.logger.i("updateLocation() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.UPDATE_LOCATION);
        create.parameters.put("photoId", str);
        create.parameters.put("latitude", str2);
        create.parameters.put("longitude", str3);
        create.parameters.put("clientTimestamp", Long.valueOf(j2));
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public Media updateOrientation(String str, long j2, String str2, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForUpdateOrientation(str, str2);
        this.logger.i("updateOrientation() is called");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.UPDATE_ORIENTATION);
        create.parameters.put("photoId", str);
        create.parameters.put("orientation", str2);
        create.parameters.put("clientTimestamp", Long.valueOf(j2));
        return (Media) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public Media updateResumable(String str, Media media, NetworkStatusListener networkStatusListener) {
        return executeUpdate(str, media, networkStatusListener, MediaApiSpec.Control.UPDATE_RESUMABLE);
    }

    public Media upload(String str, Media media, NetworkStatusListener networkStatusListener) {
        return executeUpload(str, media, networkStatusListener, MediaApiSpec.Control.UPLOAD);
    }

    public Media uploadResumable(String str, Media media, NetworkStatusListener networkStatusListener) {
        return executeUpload(str, media, networkStatusListener, MediaApiSpec.Control.UPLOAD_RESUMABLE);
    }

    public void downloadFile(String str, ParcelFileDescriptor parcelFileDescriptor, MediaConstants.FileType fileType, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForDownloadBinaryWithFD(str, parcelFileDescriptor, fileType);
        if (fileType == MediaConstants.FileType.ORIGINAL) {
            downloadBinary(str, parcelFileDescriptor, progressListener, networkStatusListener);
        }
    }

    private void downloadBinary(String str, ParcelFileDescriptor parcelFileDescriptor, ProgressListener progressListener, NetworkStatusListener networkStatusListener) {
        this.logger.i("downloadBinary() is called with fd");
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.Control.DOWNLOAD_ORIGINAL_BINARY);
        create.parameters.put("photoId", str);
        create.parameters.put("fileDescriptor", parcelFileDescriptor);
        this.apiControl.execute(create, ListenersHolder.create(networkStatusListener, progressListener).getListeners());
    }
}
