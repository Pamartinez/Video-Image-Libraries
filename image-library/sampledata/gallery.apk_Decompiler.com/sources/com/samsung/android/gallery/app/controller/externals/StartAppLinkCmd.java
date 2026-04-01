package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartAppLinkCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        String str = DetailsData.of(mediaItem).capturedUrl;
        if (!TextUtils.isEmpty(str)) {
            try {
                Uri parse = Uri.parse(str);
                Intent intent = new Intent(parse.getQueryParameter("action"));
                intent.setPackage(parse.getHost());
                intent.setType(mediaItem.getMimeType());
                intent.putExtra("android.intent.extra.STREAM", ContentUri.getUri(mediaItem));
                setIntentWithCommonExtra(intent);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                String str2 = this.TAG;
                Log.e(str2, "startApplication failed e=" + e.getMessage());
                Toast.makeText(getContext(), R.string.file_type_not_supported, 0).show();
            }
        }
    }
}
