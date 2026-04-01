package com.samsung.scsp.media.api.extended;

import com.samsung.scsp.framework.core.api.Delete;
import com.samsung.scsp.framework.core.api.Get;
import com.samsung.scsp.framework.core.api.Post;
import com.samsung.scsp.framework.core.api.Property;
import com.samsung.scsp.framework.core.api.Put;
import com.samsung.scsp.media.MediaExtended;
import com.samsung.scsp.media.MediaExtendedList;
import com.samsung.scsp.media.api.extended.job.MediaExtendedCreateDataJobImpl;
import com.samsung.scsp.media.api.extended.job.MediaExtendedDeleteDataJobImpl;
import com.samsung.scsp.media.api.extended.job.MediaExtendedGetChangeJobImpl;
import com.samsung.scsp.media.api.extended.job.MediaExtendedUpdateDataJobImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaExtendedApiSpec {
    public static final String API_PATH_CREATE_DATA = "/media/v1/photos/extensions?";
    public static final String API_PATH_DELETE_DATA = "/media/v1/photos/extensions/";
    public static final String API_PATH_DELETE_DATA_SET = "/media/v1/photos/extensions/delete?";
    public static final String API_PATH_GET_CHANGES = "/media/v1/photos/extensions/changes?";
    public static final String API_PATH_GET_DATA_SET = "/media/v1/photos/extensions/get?";
    public static final String API_PATH_UPDATE_DATA = "/media/v1/photos/extensions/";
    @Post(jobImpl = MediaExtendedCreateDataJobImpl.class, response = MediaExtendedList.class, value = "/media/v1/photos/extensions?")
    public static final String CREATE_DATA = "CREATE_DATA";
    @Delete(jobImpl = MediaExtendedDeleteDataJobImpl.class, response = MediaExtended.class, value = "/media/v1/photos/extensions/")
    public static final String DELETE_DATA = "DELETE_DATA";
    @Post(response = MediaExtendedList.class, value = "/media/v1/photos/extensions/delete?")
    public static final String DELETE_DATA_SET = "DELETE_DATA_SET";
    @Get(jobImpl = MediaExtendedGetChangeJobImpl.class, properties = {Property.GzipEncoded}, response = MediaExtendedList.class, value = "/media/v1/photos/extensions/changes?")
    public static final String GET_CHANGES = "GET_CHANGES";
    @Post(properties = {Property.GzipEncoded}, response = MediaExtendedList.class, value = "/media/v1/photos/extensions/get?")
    public static final String GET_DATA_SET = "GET_DATA_SET";
    @Put(jobImpl = MediaExtendedUpdateDataJobImpl.class, response = MediaExtended.class, value = "/media/v1/photos/extensions/")
    public static final String UPDATE_DATA = "UPDATE_DATA";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Control {
        public static final String GET_CHANGES_WITHOUT_PAGING = "GET_CHANGES_WITHOUT_PAGING";
    }
}
