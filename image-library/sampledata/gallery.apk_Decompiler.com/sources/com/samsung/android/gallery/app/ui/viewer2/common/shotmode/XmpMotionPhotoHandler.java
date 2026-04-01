package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.content.Intent;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayMotionPhotoByVpCmd;
import com.samsung.android.gallery.app.controller.externals.PlayMotionPhotoCmd;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XmpMotionPhotoHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (PlayMotionPhotoByVpCmd.ENABLED || PreferenceFeatures.isEnabled(PreferenceFeatures.GalleryMotionPhotoPlayer)) {
            try {
                String str = "data://intent/motion-photo/" + mediaItem.getComplexHashCode();
                Blackboard.getApplicationInstance().publish(str, new Object[]{mediaItem, (Bitmap) eventContext.getBlackboard().read(MediaItemUtil.getViewerBitmapKey(mediaItem))});
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.motionphoto.MotionPhotoActivity");
                intent.putExtra("data-key", str);
                eventContext.getActivity().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                new PlayMotionPhotoByVpCmd().execute(eventContext, mediaItem);
            }
        } else {
            new PlayMotionPhotoCmd().execute(eventContext, mediaItem);
        }
    }

    public int getTitleId() {
        return R.string.view_motion_photo;
    }

    public String getType() {
        return XmpType.XmpMotionPhoto.name();
    }

    public boolean support(MediaItem mediaItem) {
        if (mediaItem.getXmpType() != XmpType.XmpMotionPhoto || Features.isEnabled(Features.IS_SEP_LITE)) {
            return false;
        }
        return true;
    }

    public boolean supportUpsm() {
        return false;
    }
}
