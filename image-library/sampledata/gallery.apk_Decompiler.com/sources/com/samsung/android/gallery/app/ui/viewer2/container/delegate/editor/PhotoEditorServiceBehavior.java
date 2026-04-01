package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import android.content.Context;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.IPhotoEditorBehavior;
import com.samsung.android.gallery.module.viewer.PhotoEditorManager;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoEditorServiceBehavior implements IPhotoEditorBehavior {
    private final EditorInfo mInfo;
    private PhotoEditorManager mPhotoEditorManager;

    public PhotoEditorServiceBehavior(EditorInfo editorInfo) {
        this.mInfo = editorInfo;
    }

    private boolean isPhotoEditorServiceBound() {
        PhotoEditorManager photoEditorManager = this.mPhotoEditorManager;
        if (photoEditorManager == null || !photoEditorManager.isServiceBound()) {
            return false;
        }
        return true;
    }

    public void destroyPhotoEditor() {
        if (isPhotoEditorServiceBound()) {
            this.mPhotoEditorManager.destroy();
            this.mPhotoEditorManager = null;
        }
    }

    public void hidePhotoEditor() {
        if (isPhotoEditorServiceBound() && this.mPhotoEditorManager.isLaunched()) {
            Log.d("PhotoEditorSvcBehavior", "Edited image available, hide photo editor view");
            this.mPhotoEditorManager.hideImage();
        }
        this.mInfo.mUri = null;
    }

    public void preparePhotoEditor(Context context, IPhotoEditorBehavior.OnHidePhotoEditor onHidePhotoEditor) {
        if (context != null) {
            Log.d("PhotoEditorSvcBehavior", "PhotoEditorManager created " + this.mPhotoEditorManager);
            destroyPhotoEditor();
            PhotoEditorManager photoEditorManager = new PhotoEditorManager(context);
            this.mPhotoEditorManager = photoEditorManager;
            photoEditorManager.create();
        }
    }
}
