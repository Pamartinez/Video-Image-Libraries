package com.samsung.android.gallery.app.ui.dialog;

import android.content.res.Resources;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.exception.InternalException;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CopyOrMoveDialog extends SimpleConfirmDialog {
    private String getTitle(MediaItem[] mediaItemArr) {
        int i2;
        int i7;
        int length = mediaItemArr.length;
        int i8 = 0;
        if (length == 1) {
            Resources resources = getResources();
            if (mediaItemArr[0].isVideo()) {
                i7 = R.string.copy_or_move_1_video;
            } else {
                i7 = R.string.copy_or_move_1_image;
            }
            return resources.getString(i7);
        }
        int length2 = mediaItemArr.length;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            if (i10 < length2) {
                if (mediaItemArr[i10].isVideo()) {
                    i12++;
                } else {
                    i11++;
                }
                if (i11 > 0 && i12 > 0) {
                    i8 = length;
                    break;
                }
                i10++;
            } else {
                break;
            }
        }
        Resources resources2 = getResources();
        if (i8 > 0) {
            i2 = R.string.copy_or_move_n_items;
        } else if (i12 > 0) {
            i2 = R.string.copy_or_move_n_videos;
        } else {
            i2 = R.string.copy_or_move_n_images;
        }
        return resources2.getString(i2, new Object[]{Integer.valueOf(length)});
    }

    public String getPublishKey() {
        return "data://user/dialog/CopyOrMove";
    }

    public void loadArguments(Bundle bundle) {
        try {
            MediaItem[] mediaItemArr = (MediaItem[]) DataCollectionDelegate.getInstance(getBlackboard()).getCollectedData(bundle.getString("dataKey"));
            if (mediaItemArr != null && mediaItemArr.length > 0) {
                this.mTitle = getTitle(mediaItemArr);
            }
        } catch (ClassCastException e) {
            new InternalException(C0212a.p(new StringBuilder(), this.TAG, "fail to array items"), e.getMessage()).post();
        }
        this.mOption1 = getString(R.string.move);
        this.mOption2 = getString(R.string.copy);
    }
}
