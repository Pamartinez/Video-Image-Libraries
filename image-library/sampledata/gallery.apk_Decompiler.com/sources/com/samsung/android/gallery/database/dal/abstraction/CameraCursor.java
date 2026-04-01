package com.samsung.android.gallery.database.dal.abstraction;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CameraCursor extends CursorWrapper {
    private static final HashMap<String, String> sAliasMap = new HashMap<String, String>() {
        {
            put("__absID", "_id");
            put("__fileMediaId", "media_id");
            put("__absPath", "_data");
            put("__volumeName", "volume_name");
            put("__Title", "title");
            put("__bucketID", "bucket_id");
            put("__albumName", "bucket_display_name");
            put("__size", IParameterKey.SIZE);
            put("__dateTaken", IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME);
            put("__dateModified", IParameterKey.DATE_MODIFIED);
            put("__orientation", "orientation");
            put("__sefFileType", "sef_file_type");
            put("__latitude", "latitude");
            put("__longitude", "longitude");
            put("__mimeType", "mime_type");
            put("__width", "width");
            put("__height", "height");
            put("__isDrm", "is_drm");
        }
    };

    public CameraCursor(Cursor cursor) {
        super(cursor);
    }

    public int getColumnIndex(String str) {
        return super.getColumnIndex(sAliasMap.getOrDefault(str, str));
    }

    public int getInt(int i2) {
        return super.getInt(i2);
    }
}
