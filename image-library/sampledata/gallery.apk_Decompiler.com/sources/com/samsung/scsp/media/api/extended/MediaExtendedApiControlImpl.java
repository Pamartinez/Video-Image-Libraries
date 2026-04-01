package com.samsung.scsp.media.api.extended;

import com.samsung.scsp.framework.core.api.AbstractApiControl;
import com.samsung.scsp.framework.core.api.ApiClass;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.Requests;
import com.samsung.scsp.framework.core.listeners.Listeners;

@ApiClass(MediaExtendedApiImpl.class)
@Requests({"CREATE_DATA", "UPDATE_DATA", "DELETE_DATA", "DELETE_DATA_SET", "GET_CHANGES", "GET_DATA_SET"})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaExtendedApiControlImpl extends AbstractApiControl {
    public MediaExtendedApiControlImpl() {
        this.api = new MediaExtendedApiImpl();
        add(new AbstractApiControl.Request("GET_CHANGES_WITHOUT_PAGING") {
            public void execute(ApiContext apiContext, Listeners listeners) {
                apiContext.name = "GET_CHANGES";
                super.execute(apiContext, listeners);
            }
        });
        add(new AbstractApiControl.Request("GET_DATA_SET"));
    }
}
