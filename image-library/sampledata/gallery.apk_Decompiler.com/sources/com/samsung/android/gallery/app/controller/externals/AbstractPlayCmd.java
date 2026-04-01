package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbstractPlayCmd extends BaseCommand {
    Activity mActivity;
    MediaItem mMediaItem;

    private void assertSharingImage(MediaItem mediaItem) {
        if (isAssertSharingImage(mediaItem)) {
            String str = this.TAG;
            Log.e(str, toString() + "#onExecute not permitted " + mediaItem);
            new InternalException("WrongPlayWithSharingImage ", toString() + "#onExecute not permitted with sharing of " + mediaItem).post();
        }
    }

    private boolean isAssertSharingImage(MediaItem mediaItem) {
        if (Features.isEnabled(Features.SUPPORT_SHARED_MOTION_PHOTO_PLAY)) {
            if (mediaItem == null || !mediaItem.isSharing() || !mediaItem.isImage() || mediaItem.isMotionPhoto()) {
                return false;
            }
            return true;
        } else if (mediaItem == null || !mediaItem.isSharing() || !mediaItem.isImage()) {
            return false;
        } else {
            return true;
        }
    }

    private void operate() {
        try {
            startActivity(createIntent(this.mMediaItem));
        } catch (ActivityNotFoundException | SecurityException e) {
            j.u(e, new StringBuilder("execute failed, e="), this.TAG);
            handleFail();
        }
    }

    public abstract Intent createIntent(MediaItem mediaItem);

    public void handleFail() {
        Utils.showToast(getContext(), (int) R.string.can_not_open_file_type_not_support);
    }

    public void init(Object... objArr) {
        this.mActivity = getActivity();
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        assertSharingImage(mediaItem);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        init(objArr);
        operate();
    }

    public void startActivity(Intent intent) {
        this.mActivity.startActivity(intent);
    }
}
