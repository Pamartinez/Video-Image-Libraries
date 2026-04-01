package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartImage360EditorCmd extends BaseCommand {
    private Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.mimage.gear360editor", "com.sec.android.mimage.gear360editor.PE360");
        intent.putExtra("input", mediaItem.getPath());
        intent.setData(ContentUri.getUri(mediaItem));
        return intent;
    }

    private void startSimplePhotoEditor(EventContext eventContext, MediaItem mediaItem) {
        new StartSimplePhotoEditorCmd().execute(eventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_crop, null, Boolean.TRUE);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        try {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                Runnable runnable = objArr[1];
                if (runnable != null) {
                    runnable.run();
                }
                startSimplePhotoEditor(eventContext, mediaItem);
                return;
            }
            getActivity().startActivity(createIntent(mediaItem));
        } catch (Exception e) {
            a.s(e, new StringBuilder("startActivity failed e="), this.TAG);
            startSimplePhotoEditor(eventContext, mediaItem);
        }
    }
}
