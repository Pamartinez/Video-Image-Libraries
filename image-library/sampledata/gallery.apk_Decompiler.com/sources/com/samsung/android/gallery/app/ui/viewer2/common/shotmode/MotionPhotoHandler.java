package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.content.Intent;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayMotionPhotoByVpCmd;
import com.samsung.android.gallery.app.controller.externals.PlayMotionPhotoCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestDownloadImageCmd;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MotionPhotoHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem.isSharing() && !MediaItemMde.hasDownloadMotionPhotoPath(mediaItem)) {
            new RequestDownloadImageCmd().execute(eventContext, mediaItem, Boolean.TRUE);
        } else if (PlayMotionPhotoByVpCmd.ENABLED || PreferenceFeatures.isEnabled(PreferenceFeatures.GalleryMotionPhotoPlayer)) {
            try {
                String str = "data://intent/motion-photo/" + mediaItem.getComplexHashCode();
                Blackboard.getApplicationInstance().publish(str, new Object[]{mediaItem, (Bitmap) eventContext.getBlackboard().read(MediaItemUtil.getViewerBitmapKey(mediaItem))});
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.plugins.motionphoto.MotionPhotoActivity");
                intent.putExtra("data-key", str);
                eventContext.getActivity().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                new PlayMotionPhotoByVpCmd().execute(eventContext, mediaItem);
            }
        } else if (!PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER) {
            new PlayMotionPhotoCmd().execute(eventContext, mediaItem);
        }
    }

    public int getTitleId() {
        return R.string.view_motion_photo;
    }

    public boolean isEditable() {
        return !SdkConfig.atLeast(SdkConfig.GED.R);
    }

    public boolean isVisible(MediaItem mediaItem) {
        if (mediaItem == null) {
            return true;
        }
        Optional ofNullable = Optional.ofNullable(DetailsData.of(mediaItem).motionPhotoViewMode);
        MotionPhotoViewMode motionPhotoViewMode = MotionPhotoViewMode.ON;
        if (ofNullable.orElse(motionPhotoViewMode) == motionPhotoViewMode) {
            return true;
        }
        return false;
    }

    public boolean support(MediaItem mediaItem) {
        if (!mediaItem.isShotMode("motion_photo") || VideoPropData.of(mediaItem).longExposure || Features.isEnabled(Features.IS_SEP_LITE)) {
            return false;
        }
        return true;
    }

    public boolean supportExecuteInSharing() {
        return Features.isEnabled(Features.SUPPORT_SHARED_MOTION_PHOTO_PLAY);
    }

    public boolean supportUpsm() {
        return false;
    }
}
