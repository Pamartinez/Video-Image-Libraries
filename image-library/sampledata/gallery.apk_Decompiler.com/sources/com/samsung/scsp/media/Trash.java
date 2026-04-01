package com.samsung.scsp.media;

import c0.C0086a;
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
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Trash {
    /* access modifiers changed from: private */
    public final ApiControl apiControl;
    private Logger logger = Logger.get("Trash");
    private final SContextHolder scontextHolder;

    public Trash(SContextHolder sContextHolder, ApiControl apiControl2) {
        this.apiControl = apiControl2;
        this.scontextHolder = sContextHolder;
    }

    public MediaList deletePhotos(List<Media> list, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForRestorePhotos(list);
        Logger logger2 = this.logger;
        logger2.i("deletePhotos(), size - " + list.size());
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.DELETE_PHOTOS_IN_TRASH);
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

    public void emptyTrash(NetworkStatusListener networkStatusListener) {
        this.logger.i("emptyTrash()");
        this.apiControl.execute(ApiContext.create(this.scontextHolder, MediaApiSpec.EMPTY_TRASH), ListenersHolder.create(networkStatusListener, (ProgressListener) null).getListeners());
    }

    public MediaList list() {
        this.logger.i("list()");
        final ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.TRASH_LIST);
        create.parameters.put("modifiedAfter", 0);
        final MediaList[] mediaListArr = new MediaList[1];
        return (MediaList) new PageReader() {
            /* access modifiers changed from: private */
            public static /* synthetic */ void lambda$read$0(ApiContext apiContext, MediaList[] mediaListArr, MediaList mediaList) {
                if (mediaList.hasNext()) {
                    apiContext.parameters.put("pageToken", mediaList.getNextPageToken());
                }
                mediaListArr[0].addAll(mediaList);
            }

            public MediaList read() {
                mediaListArr[0] = new MediaList((PageReader<MediaList>) this);
                Listeners listeners = new Listeners();
                listeners.responseListener = new a(create, mediaListArr, 1);
                Trash.this.apiControl.execute(create, listeners);
                return mediaListArr[0];
            }
        }.read();
    }

    public MediaList restorePhotos(List<Media> list, NetworkStatusListener networkStatusListener) {
        VerifyParam.checkValidForRestorePhotos(list);
        Logger logger2 = this.logger;
        logger2.i("restorePhotos(), size - " + list.size());
        ApiContext create = ApiContext.create(this.scontextHolder, MediaApiSpec.RESTORE_PHOTOS);
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
}
