package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareVideoWithConversionCmd extends BaseCommand {
    private File getConvertedFile(MediaItem mediaItem) {
        File externalFilesDir = getContext().getExternalFilesDir(".share");
        if (externalFilesDir != null && FileUtils.makeDirectoryIfAbsent(externalFilesDir)) {
            return new File(getFilePath(mediaItem, externalFilesDir.getAbsolutePath()));
        }
        throw new IOException();
    }

    private String getFilePath(MediaItem mediaItem, String str) {
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(FileUtils.getBaseName(getOriginalPath(mediaItem)));
        s.append(O3DPConstant.MP4_EXTENSION);
        return s.toString();
    }

    private String getOriginalPath(MediaItem mediaItem) {
        if (mediaItem.isCloudOnly()) {
            return mediaItem.getCloudData2();
        }
        return mediaItem.getPath();
    }

    private boolean isAllowingShareDirectly(File file) {
        if (!file.exists() || file.getAbsolutePath().equals(Blackboard.getApplicationInstance().read("data://running_video_conversion", null))) {
            return false;
        }
        return true;
    }

    private void startConversionService(EventContext eventContext, MediaItem[] mediaItemArr, ConvertingUsageType convertingUsageType) {
        getBlackboard().publish("data://user/selected", mediaItemArr);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.VideoConversionService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("location_key", eventContext.getLocationKey());
        intent.putExtra("usage_type", convertingUsageType.ordinal());
        getContext().startService(intent);
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem == null) {
                Log.e(this.TAG, "Unable to operate. item is null");
                return;
            }
            try {
                ConvertingUsageType convertingUsageType = objArr[1];
                File convertedFile = getConvertedFile(mediaItem);
                boolean isEmpty = Blackboard.getApplicationInstance().isEmpty("data://running_video_conversion");
                if (isAllowingShareDirectly(convertedFile)) {
                    MediaItem createUriItem = UriItemLoader.createUriItem(getContext(), convertedFile);
                    if (convertingUsageType == ConvertingUsageType.COMMON_SHARE) {
                        createUriItem.setExtra(ExtrasID.ORIGINAL_URI, ContentUri.getUri(mediaItem));
                        new ShareViaCmd().execute(eventContext, new MediaItem[]{createUriItem}, null);
                    }
                } else if (!isEmpty) {
                    showToast((int) R.string.video_conversion_is_already_running);
                } else {
                    startConversionService(eventContext, new MediaItem[]{mediaItem}, convertingUsageType);
                }
            } catch (IOException unused) {
                Log.e(this.TAG, "Cannot make directory");
            }
        }
    }
}
