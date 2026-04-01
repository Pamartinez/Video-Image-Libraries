package com.samsung.android.gallery.module.clipboard;

import A.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClipboardPasteHelper {
    private static final Uri MCF_CDCP_CONTENT_PROVIDER_URI = Uri.parse("content://com.samsung.android.honeyboard.provider.CDCPContentProvider");
    private static final Uri MCF_CONTINUITY_PROVIDER_URI = Uri.parse("content://com.samsung.android.mcfds.ContinuityProvider");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PasteResult {
        private final String mResult;
        private final boolean mSuccess;

        public PasteResult(boolean z, String str) {
            this.mSuccess = z;
            this.mResult = str;
        }

        public String getResult() {
            return this.mResult;
        }

        public boolean isSuccess() {
            return this.mSuccess;
        }
    }

    public static MediaItem getMediaItemFromFileProvider(Uri uri, String str, long j2) {
        try {
            MediaItem mediaItem = new MediaItem();
            mediaItem.setDisplayName(str);
            mediaItem.setFileSize(j2);
            mediaItem.setMimeType(FileType.getMimeType(str, (String) null));
            mediaItem.setMediaType(UriItemLoader.getMediaType(mediaItem.getMimeType(), false));
            if (mediaItem.getMediaType() == MediaType.Unsupported) {
                Log.e((CharSequence) "ClipboardPasteHelper", "not support mimeType skipped", Logger.getEncodedString(str));
                return null;
            }
            mediaItem.setStorageType(StorageType.UriItem);
            mediaItem.setPath(uri.toString());
            return mediaItem;
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to get media item from file provider, e="), "ClipboardPasteHelper");
            return null;
        }
    }

    public static MediaItem getMediaItemFromJSONObject(JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                Log.e("ClipboardPasteHelper", "unable to get media item from json object, invalid arguments");
                return null;
            } catch (Exception e) {
                a.s(e, new StringBuilder("unable to get media item from json object, e="), "ClipboardPasteHelper");
            }
        } else {
            String string = jSONObject.getString("relativePath");
            if (!jSONObject.getBoolean("isFolder") && !TextUtils.isEmpty(string)) {
                return getMediaItemFromFileProvider(Uri.parse(jSONObject.getString(OCRServiceConstant.KEY_PARAM_URI)), FileUtils.getNameFromPath(string), jSONObject.getLong("size"));
            }
            return null;
        }
    }

    public static PasteResult getPasteResult(Context context, byte b, int i2) {
        boolean z;
        if (context == null || b == 0 || i2 == -1) {
            Log.e("ClipboardPasteHelper", "unable to get paste result, invalid arguments");
            return null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle bundle = new Bundle();
            bundle.putByte("KEY_REMOTE_PASTE_RESULT_CLIP_ID", b);
            bundle.putInt("KEY_REMOTE_PASTE_RESULT_DEVICE_ID", i2);
            Bundle call = context.getContentResolver().call(MCF_CONTINUITY_PROVIDER_URI, "METHOD_GET_REMOTE_PASTE_RESULT", (String) null, bundle);
            Log.d("ClipboardPasteHelper", "paste result", Logger.vt(call, Long.valueOf(currentTimeMillis)));
            if (call == null) {
                return new PasteResult(false, (String) null);
            }
            if (call.getByte("KEY_REMOTE_PASTE_RESULT_CODE") == 0) {
                z = true;
            } else {
                z = false;
            }
            return new PasteResult(z, call.getString("KEY_REMOTE_PASTE_RESULT_STRING"));
        } catch (Exception e) {
            Log.e("ClipboardPasteHelper", "unable to get paste result, e=" + e.getMessage());
            return new PasteResult(false, (String) null);
        }
    }

    public static Bundle getUriList(Context context, Uri uri) {
        boolean z;
        if (context == null || uri == null) {
            Log.e("ClipboardPasteHelper", "unable to get uri list, invalid arguments");
            return null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle bundle = new Bundle();
            bundle.putParcelable("KEY_GET_URI_LIST_CLIP_ID", uri);
            Bundle call = context.getContentResolver().call(MCF_CDCP_CONTENT_PROVIDER_URI, "METHOD_GET_URI_LIST", (String) null, bundle);
            if (call != null) {
                z = true;
            } else {
                z = false;
            }
            Log.d("ClipboardPasteHelper", "get uri list", Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            return call;
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to get uri list, e="), "ClipboardPasteHelper");
            return null;
        }
    }
}
