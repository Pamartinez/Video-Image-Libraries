package com.samsung.scsp.media.api.database.url;

import android.content.Context;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveUploadUrlDbManager extends OneDriveUrlDbManager {
    public OneDriveUploadUrlDbManager(Context context) {
        super(context);
    }

    public String getTableName() {
        return OneDriveUrlReaderContract.Entry.UPLOAD_TABLE_NAME;
    }
}
