package com.samsung.scsp.media;

import android.content.Context;
import com.samsung.scsp.framework.core.decorator.AbstractDecorator;
import com.samsung.scsp.framework.core.decorator.SdkConfig;
import com.samsung.scsp.framework.core.decorator.SdkFeature;
import com.samsung.scsp.media.api.MediaApiControlImpl;
import com.samsung.scsp.media.api.database.OneDriveDatabase;
import com.samsung.scsp.media.api.database.url.OneDriveUpdateUrlDbManager;
import com.samsung.scsp.media.api.database.url.OneDriveUploadUrlDbManager;
import com.samsung.scsp.media.api.extended.MediaExtendedApiControlImpl;

@SdkConfig(domain = SdkConfig.Domain.api, name = "com.samsung.scsp.media", version = "1.1.0000")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungCloudMedia extends AbstractDecorator {
    public final Extended extended = new Extended(this.scontextHolder, new MediaExtendedApiControlImpl());
    public final Files files = new Files(this.scontextHolder, this.apiControl);
    public final Trash trash = new Trash(this.scontextHolder, this.apiControl);

    public SamsungCloudMedia() {
        super(MediaApiControlImpl.class, new SdkFeature[0]);
    }

    public static void clear(Context context) {
        OneDriveDatabase[] oneDriveDatabaseArr = {new OneDriveUploadUrlDbManager(context), new OneDriveUpdateUrlDbManager(context)};
        for (int i2 = 0; i2 < 2; i2++) {
            oneDriveDatabaseArr[i2].clear();
        }
    }
}
