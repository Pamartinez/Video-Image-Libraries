package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import A4.C0369d;
import A4.C0378m;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerDragNDropDelegate extends AbsVuDelegate<IVuContainerView> {
    public ViewerDragNDropDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private int getCurrentPosition() {
        return ((ContainerModel) this.mModel).getPosition();
    }

    private int getStartPosition(DragEvent dragEvent) {
        return ClipDataUtils.getIntExtra(dragEvent.getClipData(), "captured_position");
    }

    private Uri getTargetMediaItemUri(ClipData clipData) {
        MediaItem mediaItem;
        Uri uri;
        Context context = ((IVuContainerView) this.mView).getContext();
        if (context == null) {
            return null;
        }
        boolean z = false;
        if (clipData == null || clipData.getItemCount() != 1) {
            String str = this.TAG;
            if (clipData == null) {
                z = true;
            }
            Log.w((CharSequence) str, "invalid clip data", Boolean.valueOf(z));
            return null;
        }
        ClipDescription description = clipData.getDescription();
        if (description == null || !description.hasMimeType("image/*")) {
            String str2 = this.TAG;
            if (description == null) {
                z = true;
            }
            Log.w((CharSequence) str2, "invalid clip description", Boolean.valueOf(z));
            return null;
        }
        String stringExtra = ClipDataUtils.getStringExtra(clipData, "captured_file_path");
        if (stringExtra == null) {
            mediaItem = null;
        } else if (!FileUtils.exists(stringExtra)) {
            Log.w((CharSequence) this.TAG, "captured file not exist", Logger.getEncodedString(stringExtra));
            return null;
        } else {
            mediaItem = UriItemLoader.createUriItem(context, new SecureFile(stringExtra));
        }
        if (mediaItem != null) {
            uri = ContentUri.getUri(mediaItem);
        } else {
            uri = clipData.getItemAt(0).getUri();
        }
        if (uri == null || TextUtils.isEmpty(uri.getUserInfo())) {
            return uri;
        }
        Log.w((CharSequence) this.TAG, "invalid user uri", Logger.getEncodedString(uri.getUserInfo()), Logger.getEncodedString((Object) uri));
        return null;
    }

    private boolean handleDrop(ClipData clipData) {
        MediaItem mediaItem;
        EventContext eventContext = ((IVuContainerView) this.mView).getEventContext();
        if (eventContext != null) {
            mediaItem = eventContext.getCurrentItem();
        } else {
            mediaItem = null;
        }
        if (isUneditable(mediaItem)) {
            Log.w(this.TAG, "skip handle drop, uneditable image");
            return false;
        }
        Uri targetMediaItemUri = getTargetMediaItemUri(clipData);
        if (targetMediaItemUri != null) {
            this.mActionInvoker.invoke(ViewerAction.ZOOM_TO_MIN_SCALE, new Object[0]);
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
            ActionInvoker actionInvoker = this.mActionInvoker;
            ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
            Boolean bool = Boolean.FALSE;
            actionInvoker.invoke(viewerAction, bool);
            new StartSimplePhotoEditorCmd().execute(eventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_sticker, null, bool, new ModeInfo(1, targetMediaItemUri));
            return true;
        }
        Log.e(this.TAG, "skip paste image. null item or uri");
        return false;
    }

    private boolean isUneditable(MediaItem mediaItem) {
        ViewerMenuDelegate viewerMenuDelegate;
        if (mediaItem == null || !mediaItem.isImage() || mediaItem.isCloudOnly() || mediaItem.isGif() || (viewerMenuDelegate = (ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class)) == null) {
            return true;
        }
        return !viewerMenuDelegate.isMenuEnabled(EditMenuItem.class);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onBindView$0(View view, DragEvent dragEvent) {
        if (BlackboardUtils.isViewerDragShrinkToCamera(this.mBlackboard)) {
            return false;
        }
        int action = dragEvent.getAction();
        if (action == 1) {
            return true;
        }
        if (action == 3) {
            ClipData clipData = dragEvent.getClipData();
            int startPosition = getStartPosition(dragEvent);
            try {
                if (startPosition != getCurrentPosition()) {
                    return handleDrop(clipData);
                }
                sendObjectCaptureResetCallbackIfExist(startPosition);
            } finally {
                sendObjectCaptureResetCallbackIfExist(startPosition);
            }
        } else if (action == 4) {
            this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_CLIP_DRAG_END, new Object[0]);
        }
        return false;
    }

    private void sendObjectCaptureResetCallbackIfExist(int i2) {
        Optional.ofNullable(this.mBlackboard.pop("data://object_capture_reset")).ifPresent(new C0369d(i2, 27));
    }

    public void onBindView(View view) {
        view.setOnDragListener(new C0378m(3, this));
    }
}
