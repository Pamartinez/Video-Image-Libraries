package com.samsung.android.gallery.app.controller.creature;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RequestRuntimePermissionCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EditCreatureNameWrapper {
    public static void execute(EventContext eventContext, String str, MediaItem mediaItem) {
        new EditCreatureNameCmd().execute(eventContext, str, mediaItem);
    }

    public static boolean hasRuntimeContactsPermission(EventContext eventContext) {
        return RuntimePermissionUtil.hasPermission(eventContext.getContext(), RuntimePermissionUtil.CONTACTS_PERMISSION_GROUP);
    }

    public static boolean hasRuntimeContactsPermissionResult(EventContext eventContext) {
        if (eventContext.getContext().checkSelfPermission(RuntimePermissionUtil.CONTACTS_PERMISSION_GROUP[0]) == 0) {
            return false;
        }
        return !shouldShowRequestContactPermissionRationale(eventContext);
    }

    public static void requestRuntimePermission(EventContext eventContext, int i2) {
        requestRuntimePermission(eventContext, i2, (View) null);
    }

    private static boolean shouldShowRequestContactPermissionRationale(EventContext eventContext) {
        return eventContext.getActivity().shouldShowRequestPermissionRationale(RuntimePermissionUtil.CONTACTS_PERMISSION_GROUP[0]);
    }

    public static void requestRuntimePermission(EventContext eventContext, int i2, View view) {
        new RequestRuntimePermissionCmd().execute(eventContext, RuntimePermissionUtil.CONTACTS_PERMISSION_GROUP, Integer.valueOf(i2), Integer.valueOf(R.string.permission_function_edit_people_tag), null, view);
    }
}
