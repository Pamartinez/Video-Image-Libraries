package com.samsung.scsp.media;

import com.google.gson.JsonObject;
import com.samsung.scsp.framework.core.api.Delete;
import com.samsung.scsp.framework.core.api.Get;
import com.samsung.scsp.framework.core.api.Post;
import com.samsung.scsp.framework.core.api.Property;
import com.samsung.scsp.framework.core.api.Put;
import com.samsung.scsp.media.api.job.FileUploadToOneDriveJobImpl;
import com.samsung.scsp.media.api.job.MediaCreateUploadUrlJobImpl;
import com.samsung.scsp.media.api.job.MediaDownloadHDScaledVideoJobImpl;
import com.samsung.scsp.media.api.job.MediaDownloadThumbnailJobImpl;
import com.samsung.scsp.media.api.job.MediaDownloadURLJobImpl;
import com.samsung.scsp.media.api.job.MediaGetChangesJobImpl;
import com.samsung.scsp.media.api.job.MediaGetLatestListJobImpl;
import com.samsung.scsp.media.api.job.MediaGetStreamJobImpl;
import com.samsung.scsp.media.api.job.MediaGetUrlHDScaledVideoJobImpl;
import com.samsung.scsp.media.api.job.MediaListTrashJobImpl;
import com.samsung.scsp.media.api.job.MediaUpdateDataJobImpl;
import com.samsung.scsp.media.api.job.MediaUpdateLocationJobImpl;
import com.samsung.scsp.media.api.job.MediaUpdateOrientationJobImpl;
import com.samsung.scsp.media.api.job.MediaUploadFileJobImpl;
import com.samsung.scsp.media.api.job.OneDriveUploadUrlCheckRangeJobImpl;
import com.samsung.scsp.media.file.DownloadBinaryToSignedUrlJobImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaApiSpec {
    public static final String API_BASE = "/media/v1/photos";
    public static final String API_BASE_V2 = "/media/v2/photos";
    public static final String API_CHANGES_SERVICE_STATUS = "/media/v1/photos/services";
    public static final String API_DELETE_PHOTOS_IN_TRASH = "/media/v1/photos/trashes/permanentlyDelete";
    public static final String API_EMPTY_TRASH = "/media/v1/photos/trashes";
    public static final String API_GET_SERVICE_STATUS = "/media/v1/photos/services";
    public static final String API_PATH_CREATE_DATA = "/media/v1/photos";
    public static final String API_PATH_DELETE_DATA = "/media/v1/photos/delete";
    public static final String API_PATH_DOWNLOAD_HD_SCALED_VIDEO = "/media/v2/photos/";
    public static final String API_PATH_DOWNLOAD_THUMBNAIL = "/media/v1/photos/";
    public static final String API_PATH_DOWNLOAD_URL = "/media/v1/photos/";
    public static final String API_PATH_GET_CHANGES = "/media/v2/photos/changes?";
    public static final String API_PATH_GET_DATA = "/media/v1/photos/get";
    public static final String API_PATH_GET_DOWNLOAD_URL_HD_SCALED_VIDEO = "/media/v1/photos/";
    public static final String API_PATH_GET_LATEST_LIST = "/media/v1/photos?";
    public static final String API_PATH_GET_STREAM = "/media/v1/photos/";
    public static final String API_PATH_LIST_TRASH = "/media/v1/photos/trashes?";
    public static final String API_PATH_UPDATE_DATA = "/media/v1/photos/";
    public static final String API_PATH_UPDATE_DATA_V2 = "/media/v2/photos/";
    public static final String API_PATH_UPDATE_LOCATION = "/media/v1/photos/";
    public static final String API_PATH_UPDATE_ORIENTATION = "/media/v1/photos/";
    public static final String API_PATH_UPLOAD_CHECK = "/media/v1/photos/binaries/createUploadURL";
    public static final String API_RESTORE_PHOTOS = "/media/v1/photos/trashes/restore";
    @Get(response = ServiceStatus.class, value = "/media/v1/photos/services")
    public static final String CHANGE_SERVICE_STATUS = "CHANGE_SERVICE_STATUS";
    @Post(response = Media.class, value = "/media/v1/photos")
    public static final String CREATE_DATA = "CREATE_DATA";
    @Post(jobImpl = MediaCreateUploadUrlJobImpl.class, value = "/media/v1/photos/binaries/createUploadURL")
    public static final String CREATE_UPLOAD_URL = "CREATE_UPLOAD_URL";
    @Post(response = MediaList.class, value = "/media/v1/photos/delete")
    public static final String DELETE_DATA = "DELETE_DATA";
    @Post(response = MediaList.class, value = "/media/v1/photos/trashes/permanentlyDelete")
    public static final String DELETE_PHOTOS_IN_TRASH = "DELETE_PHOTOS_IN_TRASH";
    @Get(jobImpl = DownloadBinaryToSignedUrlJobImpl.class)
    public static final String DOWNLOAD_BINARY = "DOWNLOAD_BINARY";
    @Get(jobImpl = MediaDownloadHDScaledVideoJobImpl.class, response = JsonObject.class, value = "/media/v2/photos/")
    public static final String DOWNLOAD_HD_SCALED_VIDEO = "DOWNLOAD_HD_SCALED_VIDEO";
    @Get(jobImpl = DownloadBinaryToSignedUrlJobImpl.class)
    public static final String DOWNLOAD_NDE_BINARY = "DOWNLOAD_NDE_BINARY";
    @Get(jobImpl = MediaDownloadThumbnailJobImpl.class, value = "/media/v1/photos/")
    public static final String DOWNLOAD_THUMBNAIL = "DOWNLOAD_THUMBNAIL";
    @Delete(response = Media.class, value = "/media/v1/photos/trashes")
    public static final String EMPTY_TRASH = "EMPTY_TRASH";
    @Get(jobImpl = MediaGetChangesJobImpl.class, properties = {Property.GzipEncoded}, response = MediaList.class, value = "/media/v2/photos/changes?")
    public static final String GET_CHANGES = "GET_CHANGES";
    @Post(response = MediaList.class, value = "/media/v1/photos/get")
    public static final String GET_DATA = "GET_DATA";
    @Get(jobImpl = MediaDownloadURLJobImpl.class, response = JsonObject.class, value = "/media/v1/photos/")
    public static final String GET_DOWNLOAD_URL = "GET_DOWNLOAD_URL";
    @Get(jobImpl = MediaGetUrlHDScaledVideoJobImpl.class, response = Media.class, value = "/media/v1/photos/")
    public static final String GET_DOWNLOAD_URL_HD_SCALED_VIDEO = "GET_DOWNLOAD_URL_HD_SCALED_VIDEO";
    @Get(jobImpl = MediaGetLatestListJobImpl.class, response = MediaList.class, value = "/media/v1/photos?")
    public static final String GET_LATEST_LIST = "GET_LATEST_LIST";
    @Get(response = ServiceStatus.class, value = "/media/v1/photos/services")
    public static final String GET_SERVICE_STATUS = "GET_SERVICE_STATUS";
    @Get(jobImpl = MediaGetStreamJobImpl.class, response = JsonObject.class, value = "/media/v1/photos/")
    public static final String GET_STREAM = "GET_STREAM";
    @Get(jobImpl = OneDriveUploadUrlCheckRangeJobImpl.class)
    public static final String ONEDRIVE_URL_CHECK = "ONE_DRIVE_URL_CHECK";
    @Post(response = MediaList.class, value = "/media/v1/photos/trashes/restore")
    public static final String RESTORE_PHOTOS = "RESTORE_PHOTOS";
    @Get(jobImpl = MediaListTrashJobImpl.class, properties = {Property.GzipEncoded}, response = MediaList.class, value = "/media/v1/photos/trashes?")
    public static final String TRASH_LIST = "TRASH_LIST";
    @Put(jobImpl = MediaUpdateDataJobImpl.class, response = Media.class, value = "/media/v1/photos/")
    public static final String UPDATE_DATA = "UPDATE_DATA";
    @Put(jobImpl = MediaUpdateDataJobImpl.class, response = Media.class, value = "/media/v2/photos/")
    public static final String UPDATE_DATA_V2 = "UPDATE_DATA_V2";
    @Post(jobImpl = MediaUpdateLocationJobImpl.class, response = Media.class, value = "/media/v1/photos/")
    public static final String UPDATE_LOCATION = "UPDATE_LOCATION";
    @Post(jobImpl = MediaUpdateOrientationJobImpl.class, response = Media.class, value = "/media/v1/photos/")
    public static final String UPDATE_ORIENTATION = "UPDATE_ORIENTATION";
    @Put(response = MediaUploadFileJobImpl.class)
    public static final String UPLOAD_FILE = "UPLOAD_FILE";
    @Put(jobImpl = FileUploadToOneDriveJobImpl.class)
    public static final String UPLOAD_FILE_ONE_DRIVE = "UPLOAD_FILE_ONE_DRIVE";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Control {
        public static final String DOWNLOAD_HD_SCALED_VIDEO = "CONTROL_DOWNLOAD_HD_SCALED_VIDEO";
        public static final String DOWNLOAD_NDE_ORIGINAL_BINARY = "CONTROL_DOWNLOAD_NDE_ORIGINAL_BINARY";
        public static final String DOWNLOAD_ORIGINAL_BINARY = "CONTROL_DOWNLOAD_ORIGINAL_BINARY";
        public static final String GET_CHANGES_WITHOUT_PAGING = "CONTROL_GET_CHANGES_WITHOUT_PAGING";
        public static final String REVERT_WITH_UPDATE = "CONTROL_REVERT_WITH_UPDATE";
        public static final String UPDATE = "CONTROL_UPDATE";
        public static final String UPDATE_RESUMABLE = "CONTROL_UPDATE_RESUMABLE";
        public static final String UPLOAD = "CONTROL_UPLOAD";
        public static final String UPLOAD_RESUMABLE = "CONTROL_UPLOAD_RESUMABLE";
    }
}
