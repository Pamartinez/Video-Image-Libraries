package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.ClipData;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Size;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.menu.PasteClipboardMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.capture.ICaptureActionMode;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.sec.android.gallery3d.R;
import java.util.concurrent.ConcurrentHashMap;
import v7.C0529b;
import v7.C0530c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionModeHandler extends ViewerObject implements FragmentLifeCycle, ICaptureActionMode {
    private ActionMode mActionMode;
    private final ActionInvokeListener mBackKeyListener = new C0530c(this, 2);
    private boolean mEnabled = false;
    private float mInitX = -1.0f;
    private float mInitY = -1.0f;
    private PhotoView mPhotoView;

    private boolean isActionModeSupported() {
        ConcurrentHashMap<Class<?>, ViewerMenuItem> enabledMenuMap = this.mModel.getContainerModel().getEnabledMenuMap();
        if (enabledMenuMap != null) {
            return enabledMenuMap.containsKey(PasteClipboardMenuItem.class);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$1() {
        RectF displayRect = this.mPhotoView.getDisplayRect();
        if (displayRect != null && this.mInitX > 0.0f && this.mInitY > 0.0f) {
            Size displaySize = DeviceInfo.getDisplaySize(this.mModel.getContext());
            float width = (displayRect.width() * this.mInitX) + displayRect.left;
            float height = (displayRect.height() * this.mInitY) + displayRect.top;
            onStartActionMode(Float.valueOf(Math.min(Math.max(0.0f, width), (float) displaySize.getWidth())), Float.valueOf(Math.min(Math.max(0.0f, height), (float) displaySize.getHeight())));
        }
    }

    /* access modifiers changed from: private */
    public void onFinishActionMode(Object... objArr) {
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
            this.mActionMode = null;
            setExclusiveListener(false);
        }
    }

    /* access modifiers changed from: private */
    public void onStartActionMode(Object... objArr) {
        if (isActionModeSupported()) {
            float floatValue = objArr[0].floatValue();
            float floatValue2 = objArr[1].floatValue();
            PhotoView photoView = this.mPhotoView;
            if (photoView != null) {
                this.mActionMode = photoView.startActionMode(new ActionModeCallback(this.mModel, this.mActionInvoker, new C0529b(this, 1), floatValue, floatValue2), 99);
                this.mPhotoView.setCaptureActionMode(this);
                setExclusiveListener(true);
                setTouchRatio(floatValue, floatValue2);
                return;
            }
            return;
        }
        Utils.showToast(this.mModel.getContext(), (int) R.string.could_not_find_anything_to_clip);
    }

    private void setExclusiveListener(boolean z) {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        boolean hasExclusive = actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener);
        if (z && !hasExclusive) {
            this.mActionInvoker.addExclusive(viewerAction, this.mBackKeyListener, this.TAG);
        } else if (!z && hasExclusive) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        }
    }

    private void setTouchRatio(float f, float f5) {
        RectF displayRect = this.mPhotoView.getDisplayRect();
        if (displayRect != null) {
            this.mInitX = (f - displayRect.left) / displayRect.width();
            this.mInitY = (f5 - displayRect.top) / displayRect.height();
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new C0530c(this, 0));
        this.mActionInvoker.add(ViewerAction.START_ACTION_MODE, new C0530c(this, 1));
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        PhotoView photoView;
        if (this.mActionMode != null && (photoView = this.mPhotoView) != null) {
            photoView.post(new C0529b(this, 0));
        }
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (motionEvent.getAction() == 0) {
            if (this.mActionMode != null) {
                z = true;
            } else {
                z = false;
            }
            this.mEnabled = z;
            onFinishActionMode(new Object[0]);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        onFinishActionMode(new Object[0]);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActionModeCallback extends ActionMode.Callback2 {
        private final ActionInvoker mActionInvoker;
        private final Runnable mFinishCallback;
        private final ContentModel mModel;
        private final int mX;
        private final int mY;

        public ActionModeCallback(ContentModel contentModel, ActionInvoker actionInvoker, Runnable runnable, float f, float f5) {
            this.mModel = contentModel;
            this.mActionInvoker = actionInvoker;
            this.mFinishCallback = runnable;
            this.mX = (int) f;
            this.mY = (int) f5;
        }

        private Uri getClipboardUri() {
            ClipData primaryClip = new SafeClipboard(this.mModel.getContext()).getPrimaryClip();
            if (primaryClip != null) {
                return primaryClip.getItemAt(0).getUri();
            }
            return null;
        }

        private void postAnalyticsLog(int i2) {
            EventContext eventContext = this.mModel.getContainerModel().getEventContext();
            if (eventContext != null && i2 == R.id.action_paste) {
                AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_PASTE.toString());
            }
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.action_paste) {
                MediaItem mediaItem = this.mModel.getMediaItem();
                Uri clipboardUri = getClipboardUri();
                if (!(mediaItem == null || clipboardUri == null)) {
                    this.mActionInvoker.invoke(ViewerAction.ZOOM_TO_MIN_SCALE, new Object[0]);
                    this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
                    ActionInvoker actionInvoker = this.mActionInvoker;
                    ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
                    Boolean bool = Boolean.FALSE;
                    actionInvoker.invoke(viewerAction, bool);
                    new StartSimplePhotoEditorCmd().execute(this.mModel.getContainerModel().getEventContext(), this.mModel.getMediaItem(), StartSimplePhotoEditorCmd.PhotoEditorMode.spe_sticker, null, bool, new ModeInfo(1, clipboardUri));
                    postAnalyticsLog(itemId);
                }
            }
            this.mFinishCallback.run();
            return false;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle((CharSequence) null);
            actionMode.setSubtitle((CharSequence) null);
            actionMode.setTitleOptionalHint(true);
            this.mModel.getActivity().getMenuInflater().inflate(R.menu.menu_object_capture_action_mode, menu);
            return true;
        }

        public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            int i2 = this.mX;
            rect.left = i2;
            int i7 = this.mY;
            rect.top = i7;
            rect.right = i2;
            rect.bottom = i7;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
        }
    }
}
