package com.samsung.android.gallery.app.controller.internals;

import J6.c;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.media.ImageTranscodeHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageTranscodeCmd extends BaseCommand {
    private void execute(String str, String str2) {
        SimpleThreadPool.getInstance().execute(new c(this, str, str2, 18));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e((CharSequence) this.TAG, "failed to execute -> ", Logger.getEncodedString(str), Logger.getEncodedString(str2));
            return;
        }
        String transcode = new ImageTranscodeHelper(str, str2).transcode();
        if (transcode != null) {
            MediaScanner.scanFile(transcode, (MediaScannerListener) null);
            Utils.showToast(getContext(), (int) R.string.image_converted);
            return;
        }
        Utils.showToast(getContext(), (int) R.string.couldnt_convert_image);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_CONVERT_FROM_HEIF_TO_JPEG.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr != null && objArr.length >= 2) {
            String str = objArr[0];
            if (str instanceof String) {
                String str2 = str;
                String str3 = objArr[1];
                if (str3 instanceof String) {
                    execute(str2, str3);
                    return;
                }
            }
        }
        String str4 = this.TAG;
        if (objArr == null) {
            objArr = new Object[]{"null"};
        }
        Log.e((CharSequence) str4, "onExecute failed. wrong argument", objArr);
    }
}
