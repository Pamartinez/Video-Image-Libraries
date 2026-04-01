package com.samsung.android.gallery.module.microsoft;

import G9.a;
import N2.j;
import android.content.ClipDescription;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class YourPhone {
    private static final ConcurrentHashMap<String, Boolean> sYourPhoneConnected = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ErrorType {
        NONE,
        STORAGE,
        ETC
    }

    public static void clearConnection(Context context) {
        if (context != null) {
            sYourPhoneConnected.remove(context.toString());
        }
    }

    public static void clearConnections() {
        sYourPhoneConnected.clear();
    }

    private static boolean computeConnection(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        Bundle bundle = null;
        try {
            bundle = context.getContentResolver().call(Uri.parse("content://com.samsung.android.mdx.windowslink.ExportedInteractor.Provider"), "InteractorRequest@interactor_enabled", context.getPackageName(), (Bundle) null);
        } catch (IllegalArgumentException | NullPointerException e) {
            j.u(e, new StringBuilder("computeConnection failed. e="), "MsYourPhone");
        }
        boolean isYourPhoneMirroringEnabled = isYourPhoneMirroringEnabled(bundle);
        boolean isYourPhoneDragAndDropEnabled = isYourPhoneDragAndDropEnabled(bundle);
        Log.d("MsYourPhone", "computeConnection {" + Logger.getSimpleName((Object) context) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isYourPhoneMirroringEnabled + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isYourPhoneDragAndDropEnabled + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        if (!isYourPhoneMirroringEnabled || !isYourPhoneDragAndDropEnabled) {
            return false;
        }
        return true;
    }

    public static boolean isConnected(Context context) {
        if (context == null || !sYourPhoneConnected.computeIfAbsent(context.toString(), new a(context, 0)).booleanValue()) {
            return false;
        }
        return true;
    }

    public static boolean isValidMimeType(Context context, DragEvent dragEvent) {
        if (!isConnected(context)) {
            return true;
        }
        try {
            ClipDescription clipDescription = dragEvent.getClipDescription();
            for (int i2 = 0; i2 < clipDescription.getMimeTypeCount(); i2++) {
                String mimeType = clipDescription.getMimeType(i2);
                if (!mimeType.startsWith("image/") && !mimeType.startsWith("video/")) {
                    return false;
                }
            }
            return true;
        } catch (NullPointerException unused) {
            return true;
        }
    }

    private static boolean isYourPhoneDragAndDropEnabled(Bundle bundle) {
        if (bundle == null || !bundle.getBoolean("interactor_drag_and_drop_enabled", false)) {
            return false;
        }
        return true;
    }

    private static boolean isYourPhoneMirroringEnabled(Bundle bundle) {
        if (bundle == null || !bundle.getBoolean("interactor_mirroring_enabled", false)) {
            return false;
        }
        return true;
    }

    public static boolean isYourPhonePath(String str) {
        if (str == null || !str.contains("com.microsoft.appmanager.PublicContentTransferContentProvider")) {
            return false;
        }
        return true;
    }
}
