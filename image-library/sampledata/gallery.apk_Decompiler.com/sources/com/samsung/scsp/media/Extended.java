package com.samsung.scsp.media;

import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.scsp.common.PageReader;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ApiControl;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.listeners.ListenersHolder;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.media.api.extended.constant.MediaExtendedApiContract;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Extended {
    /* access modifiers changed from: private */
    public ApiControl apiControl;
    private Logger logger = Logger.get(getClass().getSimpleName());
    private SContextHolder scontextHolder;

    public Extended(SContextHolder sContextHolder, ApiControl apiControl2) {
        this.scontextHolder = sContextHolder;
        this.apiControl = apiControl2;
    }

    public MediaExtended deleteData(MediaExtended mediaExtended, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForExtendedDeleteData(mediaExtended);
        this.logger.i("deleteData()");
        ApiContext create = ApiContext.create(this.scontextHolder, "DELETE_DATA");
        create.parameters.put("photoId", mediaExtended.photoId);
        create.parameters.put(MediaExtendedApiContract.Parameter.EXT_ID, mediaExtended.extId);
        create.parameters.put("clientTimestamp", mediaExtended.clientTimestamp);
        return (MediaExtended) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public MediaExtendedList deleteDataSet(List<MediaExtended> list, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForExtendedDeleteDataSet(list);
        this.logger.i("deleteDataSet()");
        ApiContext create = ApiContext.create(this.scontextHolder, "DELETE_DATA_SET");
        JsonArray jsonArray = new JsonArray();
        for (MediaExtended next : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("photoId", next.photoId);
            jsonObject.addProperty(MediaExtendedApiContract.Parameter.EXT_ID, next.extId);
            jsonObject.addProperty("clientTimestamp", (Number) next.clientTimestamp);
            jsonArray.add((JsonElement) jsonObject);
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("list", jsonArray);
        create.payload = jsonObject2.toString();
        return (MediaExtendedList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public MediaExtendedList getChanges(long j2, String str, int i2, NetworkStatusListener networkStatusListener) {
        return getChanges(j2, str, i2, true, networkStatusListener);
    }

    public MediaExtendedList getChangesWithOutPaging(long j2, int i2, NetworkStatusListener networkStatusListener) {
        return getChangesWithOutPaging(j2, i2, true, networkStatusListener);
    }

    public MediaExtendedList getDataSet(List<MediaExtended> list, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForExtendedGetDataSet(list);
        this.logger.i("getDataSet()");
        ApiContext create = ApiContext.create(this.scontextHolder, "GET_DATA_SET");
        JsonArray jsonArray = new JsonArray();
        for (MediaExtended next : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(MediaExtendedApiContract.Parameter.EXT_ID, next.extId);
            jsonObject.addProperty("photoId", next.photoId);
            jsonArray.add((JsonElement) jsonObject);
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("list", jsonArray);
        create.payload = jsonObject2.toString();
        return (MediaExtendedList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public MediaExtended updateData(MediaExtended mediaExtended, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForExtendedUpdateData(mediaExtended);
        this.logger.i("updateData()");
        ApiContext create = ApiContext.create(this.scontextHolder, "UPDATE_DATA");
        create.parameters.put(MediaExtendedApiContract.Parameter.EXT_ID, mediaExtended.extId);
        create.parameters.put("photoId", mediaExtended.photoId);
        create.payload = new Gson().toJson((Object) mediaExtended);
        return (MediaExtended) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    public MediaExtendedList uploadDataSet(List<MediaExtended> list, String str, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForExtendedUploadData(list, str);
        this.logger.i("uploadDataSet()");
        ApiContext create = ApiContext.create(this.scontextHolder, "CREATE_DATA");
        create.parameters.put("photoId", str);
        create.payload = new Gson().toJson((Object) new MediaExtendedList(list));
        return (MediaExtendedList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }

    private MediaExtendedList getChangesWithOutPaging(long j2, int i2, boolean z, final NetworkStatusListener networkStatusListener) {
        Logger logger2 = this.logger;
        logger2.i("getChanges(), " + j2 + ArcCommonLog.TAG_COMMA + i2);
        final ApiContext create = ApiContext.create(this.scontextHolder, "GET_CHANGES_WITHOUT_PAGING");
        if (i2 > 0) {
            create.parameters.put("count", Integer.valueOf(i2));
        }
        create.parameters.put("modifiedAfter", Long.valueOf(j2));
        create.parameters.put("includeDetail", Boolean.valueOf(z));
        final MediaExtendedList[] mediaExtendedListArr = new MediaExtendedList[1];
        return (MediaExtendedList) new PageReader() {
            /* access modifiers changed from: private */
            public static /* synthetic */ void lambda$read$0(ApiContext apiContext, MediaExtendedList[] mediaExtendedListArr, MediaExtendedList mediaExtendedList) {
                if (mediaExtendedList.hasNextPage()) {
                    apiContext.parameters.put("pageToken", mediaExtendedList.getNextPageToken());
                }
                mediaExtendedListArr[0].addAll(mediaExtendedList);
            }

            public MediaExtendedList read() {
                mediaExtendedListArr[0] = new MediaExtendedList((PageReader<MediaExtendedList>) this);
                Listeners listeners = new Listeners();
                listeners.networkStatusListener = networkStatusListener;
                listeners.responseListener = new a(create, mediaExtendedListArr, 0);
                Extended.this.apiControl.execute(create, listeners);
                return mediaExtendedListArr[0];
            }
        }.read();
    }

    public MediaExtendedList getChanges(long j2, int i2, NetworkStatusListener networkStatusListener) {
        return getChanges(j2, (String) null, i2, true, networkStatusListener);
    }

    private MediaExtendedList getChanges(long j2, String str, int i2, boolean z, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForExtendedGetChanges(j2);
        Logger logger2 = this.logger;
        StringBuilder p6 = C0086a.p(j2, "getChanges(), ", ArcCommonLog.TAG_COMMA, str);
        p6.append(ArcCommonLog.TAG_COMMA);
        p6.append(i2);
        logger2.i(p6.toString());
        ApiContext create = ApiContext.create(this.scontextHolder, "GET_CHANGES");
        if (!TextUtils.isEmpty(str)) {
            create.parameters.put("pageToken", str);
        }
        if (i2 > 0) {
            create.parameters.put("count", Integer.valueOf(i2));
        }
        create.parameters.put("modifiedAfter", Long.valueOf(j2));
        create.parameters.put("includeDetail", Boolean.valueOf(z));
        return (MediaExtendedList) C0086a.g(ListenersHolder.create(networkStatusListener, (ProgressListener) null), this.apiControl, create);
    }
}
