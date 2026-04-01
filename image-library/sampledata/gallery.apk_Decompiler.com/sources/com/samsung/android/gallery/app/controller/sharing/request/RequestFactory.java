package com.samsung.android.gallery.app.controller.sharing.request;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RequestFactory {
    private static final HashMap<RequestCmdType, CreateRequestInstance> REQUEST_MAP;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CreateRequestInstance {
        AbsRequest create(EventContext eventContext, Object[] objArr);
    }

    static {
        HashMap<RequestCmdType, CreateRequestInstance> hashMap = new HashMap<>();
        REQUEST_MAP = hashMap;
        hashMap.put(RequestCmdType.REQUEST_SYNC, new a(0));
        hashMap.put(RequestCmdType.REQUEST_LEAVE_GROUP, new a(2));
        hashMap.put(RequestCmdType.REQUEST_DELETE_GROUP, new a(9));
        hashMap.put(RequestCmdType.REQUEST_CREATE_FAMILY_SPACE, new a(10));
        hashMap.put(RequestCmdType.REQUEST_DELETE_FAMILY_SPACE, new a(12));
        hashMap.put(RequestCmdType.REQUEST_DELETE_SPACE, new a(13));
        hashMap.put(RequestCmdType.REQUEST_UPDATE_SPACE, new a(14));
        hashMap.put(RequestCmdType.REQUEST_ADD_CONTENTS, new a(15));
        hashMap.put(RequestCmdType.REQUEST_DELETE_CONTENTS, new a(16));
        hashMap.put(RequestCmdType.REQUEST_DOWNLOAD_CONTENTS, new a(17));
        hashMap.put(RequestCmdType.REQUEST_STREAMING_VIDEO, new a(11));
        hashMap.put(RequestCmdType.REQUEST_INVITATION_SYNC, new a(18));
        hashMap.put(RequestCmdType.REQUEST_INVITATION_ACCEPTANCE, new a(19));
        hashMap.put(RequestCmdType.REQUEST_INVITATION_REJECTION, new a(20));
        hashMap.put(RequestCmdType.REQUEST_CHANGE_SPACE_COVER, new a(21));
        if (Features.isEnabled(Features.SUPPORT_SHARED_MOTION_PHOTO_PLAY) || Features.isEnabled(Features.SUPPORT_SHARED_GIF_PLAY)) {
            hashMap.put(RequestCmdType.REQUEST_DOWNLOAD_FOR_PLAY_IN_SHARING, new a(22));
        }
        if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER)) {
            hashMap.put(RequestCmdType.REQUEST_DOWNLOAD_COVER, new a(23));
        }
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            hashMap.put(RequestCmdType.REQUEST_MY_QUOTA, new a(24));
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
                hashMap.put(RequestCmdType.REQUEST_FAMILY_QUOTA, new a(25));
            }
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            hashMap.put(RequestCmdType.REQUEST_DOWNLOAD_CONTENTS_FOR_SHARING_EDIT, new a(1));
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
            hashMap.put(RequestCmdType.REQUEST_MOVE_TO_TRASH, new a(3));
            hashMap.put(RequestCmdType.REQUEST_RESTORE_FROM_TRASH, new a(4));
            hashMap.put(RequestCmdType.REQUEST_DELETE_FROM_TRASH, new a(5));
            hashMap.put(RequestCmdType.REQUEST_EMPTY_FROM_TRASH, new a(6));
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY) {
            hashMap.put(RequestCmdType.REQUEST_CREATE_STORY, new a(7));
        }
        if (Features.isEnabled(Features.SUPPORT_SORT_SHARINGS)) {
            hashMap.put(RequestCmdType.REQUEST_SORT_SHARINGS, new a(8));
        }
    }

    public static AbsRequest createRequest(EventContext eventContext, Object[] objArr, RequestCmdType requestCmdType) {
        CreateRequestInstance createRequestInstance = REQUEST_MAP.get(requestCmdType);
        if (createRequestInstance != null) {
            return createRequestInstance.create(eventContext, objArr);
        }
        throw new IllegalArgumentException("cmdType n= " + requestCmdType);
    }
}
