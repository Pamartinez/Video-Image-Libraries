package com.samsung.android.gallery.app.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddCloudItemToSharedAlbumDialog extends SimpleConfirmDialog {
    private int getDescriptionRes(int i2, int i7) {
        if (i2 == 1) {
            if (i7 == 1) {
                if (isTablet()) {
                    return R.string.add_cloud_image_to_shared_album_description_to_tablet;
                }
                return R.string.add_cloud_image_to_shared_album_description_to_phone;
            } else if (isTablet()) {
                return R.string.add_cloud_video_to_shared_album_description_to_tablet;
            } else {
                return R.string.add_cloud_video_to_shared_album_description_to_phone;
            }
        } else if (isTablet()) {
            return R.string.add_cloud_items_to_shared_album_description_to_tablet;
        } else {
            return R.string.add_cloud_items_to_shared_album_description_to_phone;
        }
    }

    private boolean isTablet() {
        return Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    }

    public String getPublishKey() {
        return "data://user/dialog/AddCloudItemToSharedAlbum";
    }

    public void loadArguments(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            this.mDismissAsCancel = BundleWrapper.getBoolean(bundle, "dismissAsCancel", false);
            int i2 = BundleWrapper.getInt(bundle, "cloudItemCount");
            int i7 = BundleWrapper.getInt(bundle, "imageCount");
            this.mTitle = context.getResources().getQuantityString(R.plurals.add_cloud_items_to_shared_album_title, i2, new Object[]{Integer.valueOf(i2)});
            this.mDescription = context.getResources().getString(getDescriptionRes(i2, i7));
            this.mOption1 = context.getResources().getString(R.string.add);
            this.mEventIds[0] = BundleWrapper.getString(bundle, "option1EventId", (String) null);
            this.mScreenId = BundleWrapper.getString(bundle, "screenId", (String) null);
        }
    }
}
