package com.samsung.android.gallery.app.controller.internals;

import O3.y;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartCropImageCmd extends BaseCommand {
    private boolean isTooSmallForCrop(MediaItem mediaItem) {
        int width = mediaItem.getWidth();
        int height = mediaItem.getHeight();
        if (width <= 0 || height <= 0 || Math.min(width, height) > 16) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onCropResult(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                Intent intent = (Intent) arrayList.get(0);
                intent.putExtra("result_from_crop", true);
                Activity activity = (Activity) getContext();
                activity.setResult(-1, intent);
                activity.finish();
            } catch (ClassCastException e) {
                String str = this.TAG;
                Log.e(str, "Unexpected result delivered." + e.toString());
                ((Activity) getContext()).finish();
            }
        }
    }

    private void publishCropData(MediaItem mediaItem) {
        getBlackboard().publish(DataKey.DATA("location://cropView"), mediaItem);
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (isTooSmallForCrop(mediaItem)) {
            showToast(getActivity().getResources().getString(R.string.image_too_small));
            return;
        }
        boolean booleanValue = objArr[1].booleanValue();
        boolean booleanValue2 = objArr[2].booleanValue();
        boolean booleanValue3 = objArr[3].booleanValue();
        boolean booleanValue4 = objArr[4].booleanValue();
        UriBuilder appendArg = new UriBuilder("data://user/Crop").appendArg("pick-from-gallery", booleanValue).appendArg("FromStoryCover", booleanValue2).appendArg("FromSharedAlbumCover", booleanValue3).appendArg("FromAlbumCover", booleanValue4).appendArg("is-get-rect-result", objArr[5].booleanValue());
        if (mediaItem.isSharing()) {
            appendArg.appendArg("key-shared-item-id", MediaItemMde.getItemId(mediaItem));
        } else {
            Uri uri = ContentUri.getUri(mediaItem);
            if (uri != null) {
                appendArg.appendArg("content_uri", uri.toString());
            }
        }
        if (booleanValue4 || booleanValue3) {
            appendArg.appendArg("aspectX", 360).appendArg("aspectY", 360);
        } else if (booleanValue2) {
            int[] preferCoverCropRatio = StoryHelper.getPreferCoverCropRatio(mediaItem);
            appendArg.appendArg("aspectX", preferCoverCropRatio[0]).appendArg("aspectY", preferCoverCropRatio[1]);
        }
        appendArg.appendArg("FromGalleryPicker", true);
        String build = appendArg.build();
        publishCropData(mediaItem);
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(build).setOnDataCompleteListener(new y(0, this)).start();
    }
}
