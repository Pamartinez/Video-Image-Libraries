package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot;

import A4.C0372g;
import Ab.a;
import C7.b;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.viewer.RequestBurstShotSelectModeCmd;
import com.samsung.android.gallery.app.controller.viewer.RequestSimilarShotSelectModeCmd;
import com.samsung.android.gallery.app.controller.viewer.StartSingleTakenViewerCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupCountUi extends ViewerObject implements FragmentLifeCycle {
    private boolean mBlock = false;
    private TextView mCountTextView;
    private View mCountView;
    private ViewStub mViewStub;

    private void executeSingleTakeViewer() {
        ContentModel parentModel = this.mModel.getParentModel();
        if (parentModel != null) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_START_GROUP_ITEM_PANEL_VIEW, new Object[0]);
            this.mActionInvoker.invoke(ViewerAction.START_GROUP_ITEM_PANEL_VIEW, new Object[0]);
            new StartSingleTakenViewerCmd().execute(parentModel.getContainerModel().getEventContext(), Integer.valueOf(parentModel.getSubItemCurrentIndex()), Integer.valueOf(parentModel.getBestItemIndex()));
        }
    }

    private void initCountView() {
        ViewStub viewStub;
        if (this.mCountView == null && (viewStub = this.mViewStub) != null) {
            this.mCountView = viewStub.inflate();
        }
        View view = this.mCountView;
        if (view != null) {
            this.mCountTextView = (TextView) view.findViewById(R.id.count_view);
            View findViewById = this.mCountView.findViewById(R.id.select_pictures_icon);
            this.mCountView.setOnClickListener(new a(12, this));
            findViewById.setOnClickListener(new a(12, this));
            setTouchArea();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCountViewText$0() {
        ContentModel contentModel;
        boolean z;
        if (this.mCountView != null) {
            if (this.mModel.getParentModel() != null) {
                contentModel = this.mModel.getParentModel();
            } else {
                contentModel = this.mModel;
            }
            int subMediaItemCount = contentModel.getSubMediaItemCount();
            ViewUtils.setText(this.mCountTextView, StringCompat.toReadableCount(subMediaItemCount));
            View view = this.mCountView;
            if (subMediaItemCount > 0) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setVisibleOrGone(view, z);
        }
    }

    /* access modifiers changed from: private */
    public void onBindView(Object... objArr) {
        this.mViewStub = (ViewStub) objArr[0].findViewById(R.id.group_count_stub);
    }

    /* access modifiers changed from: private */
    public void onSelectClicked(View view) {
        long j2;
        if (this.mBlock) {
            Log.e(this.TAG, "onSelectClicked block");
            return;
        }
        MediaItem representativeItem = this.mModel.getRepresentativeItem();
        if (representativeItem != null) {
            if (representativeItem.getBestImage() == 1) {
                j2 = representativeItem.getFileId();
            } else {
                j2 = -1;
            }
            if (representativeItem.isSingleTakenShot()) {
                if (this.mModel.isPppLoading()) {
                    Log.e(this.TAG, "onSelect failed");
                    return;
                }
                executeSingleTakeViewer();
            } else if (representativeItem.isBurstShot()) {
                new RequestBurstShotSelectModeCmd().execute(this.mModel.getContainerModel().getEventContext(), representativeItem, Integer.valueOf(this.mModel.getSubItemCurrentIndex()), Long.valueOf(j2));
                this.mActionInvoker.invoke(ViewerAction.START_SELECTION_VIEW, new Object[0]);
            } else {
                new RequestSimilarShotSelectModeCmd().execute(this.mModel.getContainerModel().getEventContext(), representativeItem, Integer.valueOf(this.mModel.getSubItemCurrentIndex()), Long.valueOf(j2));
                this.mActionInvoker.invoke(ViewerAction.START_SELECTION_VIEW, new Object[0]);
            }
            this.mActionInvoker.invoke(ViewerAction.GROUP_COUNT_VIEW_SELECTED, new Object[0]);
            return;
        }
        throw new IllegalArgumentException("media item null");
    }

    /* access modifiers changed from: private */
    public void setCountViewText(Object... objArr) {
        this.mThread.runOnUiThread(new C0372g(17, this));
    }

    private void setTouchArea() {
        View view = this.mCountView;
        if (view != null) {
            int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.select_pictures_icon_container_side_margin);
            int dimensionPixelOffset = this.mCountView.getResources().getDimensionPixelOffset(R.dimen.horizontal_navigation_icon_extra_margin);
            ViewUtils.setTouchAreaComposite(this.mCountView, dimensionPixelSize, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
        }
    }

    private boolean unsupportedGroupCount(MediaItem mediaItem) {
        if (!mediaItem.isGroupShot() || LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            return true;
        }
        return false;
    }

    private void updateIcon(MediaItem mediaItem) {
        View view;
        int i2;
        if (mediaItem != null && (view = this.mCountView) != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.select_pictures_icon);
            if (mediaItem.isBurstShot() || mediaItem.isSimilarShot()) {
                if (mediaItem.isBurstShot()) {
                    i2 = R.drawable.gallery_ic_detail_burst_shot;
                } else {
                    i2 = R.drawable.gallery_ic_detail_similar_shot;
                }
                imageView.setImageResource(i2);
            } else if (mediaItem.isSingleTakenShot()) {
                Drawable drawable = imageView.getResources().getDrawable(R.drawable.gallery_ic_search_single_take, (Resources.Theme) null);
                drawable.setTint(imageView.getResources().getColor(R.color.select_pictures_button_view_color, (Resources.Theme) null));
                imageView.setImageDrawable(drawable);
            }
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new b(this, 0));
        this.mActionInvoker.add(ViewerAction.GROUP_SUB_ITEMS_UPDATED, new b(this, 1));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what == 3060) {
            this.mBlock = ((Boolean) eventMessage.obj).booleanValue();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "block group count button : " + this.mBlock);
        }
        return false;
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        this.mBlock = false;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        MediaItem representativeItem = this.mModel.getRepresentativeItem();
        if (representativeItem == null) {
            throw new IllegalArgumentException("media item null");
        } else if (unsupportedGroupCount(representativeItem)) {
            ViewUtils.setVisibility(this.mCountView, 8);
        } else {
            initCountView();
            updateIcon(representativeItem);
            ViewUtils.setText(this.mCountTextView, StringCompat.toReadableCount(representativeItem.getCount()));
            ViewUtils.setVisibility(this.mCountView, 0);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        setTouchArea();
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mBlock = false;
    }
}
