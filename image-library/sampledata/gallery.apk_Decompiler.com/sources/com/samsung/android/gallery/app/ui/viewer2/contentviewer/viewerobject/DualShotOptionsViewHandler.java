package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.DualShotState;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.viewer.DualPhoto;
import com.samsung.android.gallery.module.viewer.DualPhotoManager;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import g6.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import o4.a;
import o6.p;
import t8.e;
import u7.C0520a;
import v7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DualShotOptionsViewHandler extends ViewerObject {
    private static final boolean SUPPORT_DUAL_PHOTO_PREVIEW = PocFeatures.DUAL_PHOTO_PREVIEW;
    private TextView mCloseUpButton;
    private DualPhoto mDualPhoto;
    private DualPhotoManager mDualPhotoManager;
    private View mView;
    private TextView mWideButton;

    private void downloadSharedDualPhoto() {
        int i2;
        Context context = this.mModel.getContext();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (context == null || eventContext == null) {
            Log.d(this.TAG, "download shared dualPhoto failed");
            return;
        }
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            i2 = R.string.you_need_to_download_the_image_to_view_it_on_your_tablet;
        } else {
            i2 = R.string.you_need_to_download_the_image_to_view_it_on_your_phone;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", context.getString(R.string.download_image_q)).appendArg("description", context.getString(i2)).appendArg("option1", context.getString(R.string.ok)).build()).setOnDataCompleteListener(new p(19, this)).start();
    }

    private DualPhoto getDualPhoto() {
        if (this.mDualPhoto == null) {
            DualPhoto dualPhoto = new DualPhoto(this.mModel.getMediaItem());
            this.mDualPhoto = dualPhoto;
            dualPhoto.setCoverImage(this.mModel.getBitmap());
        }
        return this.mDualPhoto;
    }

    private synchronized DualPhotoManager getDualPhotoManager(MediaItem mediaItem) {
        try {
            if (this.mDualPhotoManager == null) {
                this.mDualPhotoManager = new DualPhotoManager(this.mModel.getBlackboard(), mediaItem);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mDualPhotoManager;
    }

    private boolean isWide(MediaItem mediaItem) {
        if (SUPPORT_DUAL_PHOTO_PREVIEW) {
            return getDualPhoto().isWide();
        }
        if (mediaItem == null || !getDualPhotoManager(mediaItem).isWide()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadSharedDualPhoto$3(EventContext eventContext, ArrayList arrayList) {
        int i2 = 0;
        if (arrayList != null) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        if (i2 == 1) {
            ArrayList arrayList2 = new ArrayList();
            Collections.addAll(arrayList2, new MediaItem[]{this.mModel.getMediaItem()});
            new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_DOWNLOAD_CONTENTS, arrayList2, Boolean.TRUE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDualPhotoChanged$2() {
        setEnabled(true, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnClickListener$0(Consumer consumer, View view) {
        View view2 = this.mView;
        if (view2 != null && view2.getAlpha() == 1.0f) {
            consumer.accept(Boolean.FALSE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnClickListener$1(Consumer consumer, View view) {
        View view2 = this.mView;
        if (view2 != null && view2.getAlpha() == 1.0f) {
            consumer.accept(Boolean.TRUE);
        }
    }

    /* access modifiers changed from: private */
    public void onDualPhotoChanged(boolean z, boolean z3) {
        Log.d(this.TAG, "onDualPhotoChanged", Boolean.valueOf(z), Boolean.valueOf(z3));
        if (z) {
            updateMode(this.mModel.getMediaItem(), z3);
        }
        ThreadUtil.postOnUiThreadDelayed(new e(13, this), 1000);
    }

    /* access modifiers changed from: private */
    public void onViewReady(Object... objArr) {
        View view = objArr[0];
        this.mView = view;
        this.mCloseUpButton = (TextView) view.findViewById(R.id.option_closeup);
        this.mWideButton = (TextView) this.mView.findViewById(R.id.option_wideangle);
        float dimensionPixelSize = (float) this.mView.getResources().getDimensionPixelSize(R.dimen.live_focus_button_font_size);
        FontUtils.resizeUpToLarge(this.mView.getContext(), this.mCloseUpButton, dimensionPixelSize);
        FontUtils.resizeUpToLarge(this.mView.getContext(), this.mWideButton, dimensionPixelSize);
        setOnClickListener(new a(21, this));
    }

    private void resetDualPhotoManager() {
        if (SUPPORT_DUAL_PHOTO_PREVIEW) {
            this.mDualPhoto = null;
        } else {
            this.mDualPhotoManager = null;
        }
    }

    private void setEnabled(boolean z, boolean z3) {
        boolean z7;
        float f;
        if (this.mView != null) {
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (!z || mediaItem == null || TrashData.isTrash(mediaItem)) {
                z7 = false;
            } else {
                z7 = true;
            }
            this.mView.setEnabled(z7);
            float f5 = 1.0f;
            if (z7) {
                f = 0.45f;
            } else {
                f = 1.0f;
            }
            if (!z7) {
                f5 = 0.45f;
            }
            if (z3) {
                new AlphaAnimator(this.mView, f, f5).setDuration(StatusCodes.INPUT_MISSING).start();
            } else {
                this.mView.setAlpha(f5);
            }
        }
    }

    private void setOnClickListener(Consumer<Boolean> consumer) {
        ViewUtils.setOnClickListener(this.mCloseUpButton, new l(this, consumer, 0));
        ViewUtils.setOnClickListener(this.mWideButton, new l(this, consumer, 1));
    }

    /* access modifiers changed from: private */
    public void updateDualPhotoView(boolean z) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            DualPhotoManager dualPhotoManager = getDualPhotoManager(mediaItem);
            if (mediaItem.isCloudOnly()) {
                Utils.showToast(this.mModel.getContext(), (int) R.string.download_picture_to_change_view);
            } else if (mediaItem.isSharing()) {
                if (!dualPhotoManager.isTheSameWithCurrentMode(z)) {
                    downloadSharedDualPhoto();
                }
            } else if (!dualPhotoManager.isRunning() && dualPhotoManager.changeDualPhoto(z, new f(12, this))) {
                this.mModel.getBlackboard().erase(MediaItemUtil.getViewerBitmapKey(mediaItem));
                ThumbnailLoader.getInstance().removeCache(mediaItem);
                setEnabled(false, true);
            }
        }
    }

    private void updateMode(MediaItem mediaItem, boolean z) {
        DualShotState dualShotState;
        if (mediaItem != null) {
            DetailsData of2 = DetailsData.of(mediaItem);
            if (z) {
                dualShotState = DualShotState.Wide;
            } else {
                dualShotState = DualShotState.CloseUp;
            }
            of2.dualShotState = dualShotState;
        }
        TextView textView = this.mCloseUpButton;
        int i2 = R.color.live_focus_toggle_selected_text_color;
        ViewUtils.setTextColor(textView, z ? R.color.live_focus_toggle_unselected_text_color : R.color.live_focus_toggle_selected_text_color);
        TextView textView2 = this.mWideButton;
        if (!z) {
            i2 = R.color.live_focus_toggle_unselected_text_color;
        }
        ViewUtils.setTextColor(textView2, i2);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.DUAL_SHOT_OPTIONS_VIEW, new C0520a(1, this));
    }

    public void initialize() {
        super.initialize();
        this.mActionInvoker.invoke(ViewerAction.DUAL_SHOT_OPTIONS_VIEW_INFLATE, new Object[0]);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (mediaItem2.getDateModified() != mediaItem.getDateModified()) {
            resetDualPhotoManager();
            updateMode(mediaItem, isWide(mediaItem));
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateMode(this.mModel.getMediaItem(), isWide(this.mModel.getMediaItem()));
        setEnabled(true, false);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        resetDualPhotoManager();
    }
}
