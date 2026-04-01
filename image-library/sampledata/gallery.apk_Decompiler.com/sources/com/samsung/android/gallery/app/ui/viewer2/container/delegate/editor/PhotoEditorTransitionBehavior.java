package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import a6.g;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.IPhotoEditorBehavior;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoEditorTransitionBehavior implements IPhotoEditorBehavior {
    private final EditorInfo mInfo;
    private boolean mIsTransitionReentered;
    private IPhotoEditorBehavior.OnHidePhotoEditor mListener;
    private boolean mPhotoEditorLaunched;
    private boolean mReturnTransitionCanceled;

    public PhotoEditorTransitionBehavior(EditorInfo editorInfo) {
        this.mInfo = editorInfo;
    }

    public void cancelReturnTransition(boolean z) {
        this.mReturnTransitionCanceled = z;
    }

    public void hidePhotoEditor() {
        if (!this.mPhotoEditorLaunched || !this.mIsTransitionReentered) {
            Log.at("PhotoEditorTransBehavior", "hidePhotoEditor skip" + Logger.v(Boolean.valueOf(this.mPhotoEditorLaunched), Boolean.valueOf(this.mIsTransitionReentered)));
        } else {
            Log.at("PhotoEditorTransBehavior", "hidePhotoEditor -> startReturningAppTransition");
            ((PhotoEditorTransitionHandler) ((g) this.mListener).e).onHidePhotoEditor(!this.mReturnTransitionCanceled);
            Context appContext = AppResources.getAppContext();
            if (this.mReturnTransitionCanceled && appContext != null) {
                appContext.sendBroadcast(new Intent("com.sec.android.mimage.photoretouching.action.hideimage"));
            }
            this.mPhotoEditorLaunched = false;
            this.mReturnTransitionCanceled = false;
        }
        this.mInfo.mUri = null;
    }

    public void preparePhotoEditor(Context context, IPhotoEditorBehavior.OnHidePhotoEditor onHidePhotoEditor) {
        this.mListener = onHidePhotoEditor;
        this.mPhotoEditorLaunched = true;
        setTransitionReentered(false);
    }

    public void setTransitionReentered(boolean z) {
        this.mIsTransitionReentered = z;
    }
}
