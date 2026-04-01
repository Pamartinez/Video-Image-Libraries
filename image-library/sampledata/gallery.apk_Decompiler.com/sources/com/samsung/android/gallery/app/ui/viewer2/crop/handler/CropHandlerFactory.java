package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import Gb.a;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CropHandlerFactory {
    public static CropHandler create(Intent intent, MediaItem mediaItem, Bundle bundle, Bitmap bitmap, boolean z) {
        Uri outputFromClipData;
        ClipData clipData = intent.getClipData();
        if (!(clipData == null || bundle.getParcelable("output") != null || (outputFromClipData = getOutputFromClipData(clipData)) == null)) {
            intent.putExtra("output", outputFromClipData);
            bundle.putParcelable("output", outputFromClipData);
        }
        if (bundle.getParcelable("output") != null) {
            if (z) {
                return new CropGifHandler(intent, mediaItem);
            }
            if (bundle.getBoolean("is-caller-id", false)) {
                return new CropCallerIdHandler(intent, mediaItem).setBitmap(bitmap);
            }
            return new CropUriHandler(intent, mediaItem).setBitmap(bitmap);
        } else if (bundle.getBoolean("return-data", false)) {
            return new CropDataHandler(intent, mediaItem).setBitmap(bitmap);
        } else {
            if (bundle.getBoolean("is-get-rect-result", false)) {
                return new CropRectHandler(intent, mediaItem).setBitmap(bitmap);
            }
            return new CropMpHandler(intent, mediaItem).setBitmap(bitmap);
        }
    }

    private static Uri getOutputFromClipData(ClipData clipData) {
        ClipDescription description;
        if (clipData != null && clipData.getItemCount() > 0 && (description = clipData.getDescription()) != null && description.hasMimeType("text/uri-list") && "output".equals(description.getLabel())) {
            Uri uri = (Uri) Optional.ofNullable(clipData.getItemAt(0)).map(new a(20)).orElse((Object) null);
            if (MediaUri.isFileContentUri(uri)) {
                return uri;
            }
        }
        return null;
    }
}
