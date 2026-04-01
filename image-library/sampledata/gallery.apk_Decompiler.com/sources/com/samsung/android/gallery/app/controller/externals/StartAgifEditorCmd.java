package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartAgifEditorCmd extends BaseCommand {
    private Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent("com.sec.android.mimage.photoretouching.motionphoto");
        intent.putExtra("baseImage", ContentUri.getUri(mediaItem));
        intent.putExtra("file_path", mediaItem.getPath());
        handleSharedItemEdit(mediaItem, intent);
        setIntentWithCommonExtra(intent);
        return intent;
    }

    public boolean handleSharedItemEdit(MediaItem mediaItem, Intent intent) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT_GIF) || !super.handleSharedItemEdit(mediaItem, intent)) {
            return false;
        }
        return true;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            getActivity().startActivity(createIntent(objArr[0]));
        } catch (ActivityNotFoundException e) {
            guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
            j.p(e, new StringBuilder("startActivity failed e="), this.TAG);
        }
    }
}
