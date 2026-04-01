package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.directorsview;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.support.library.sef.SefFileSubType;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import k7.j;
import u7.C0520a;
import x7.l;
import y5.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DirectorsViewHandler extends ViewerObject {
    private static final boolean SUPPORT = PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW;
    private View mContainer;
    private TextView mMainTextView;
    private TextView mSubTextView;
    private View mTextDivider;
    private SubscriberListenerInfo mUpdateSubscriber;
    private CoordinatorLayout mViewerLayout;

    private void checkPareItem(MediaItem mediaItem, boolean z) {
        if (SUPPORT) {
            if (isDualVideo(mediaItem)) {
                boolean checkPairItem = DirectorsViewCache.getInstance().checkPairItem(mediaItem, z);
                setVisibility(checkPairItem, mediaItem);
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "findPairItem=" + mediaItem.getShotMode() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + checkPairItem + " , " + z);
                return;
            }
            setVisibility(false, mediaItem);
        }
    }

    private void inflate() {
        if (ViewUtils.isViewStub(this.mContainer)) {
            View inflate = ((ViewStub) this.mContainer).inflate();
            this.mContainer = inflate;
            this.mMainTextView = (TextView) inflate.findViewById(R.id.directors_view_main_textview);
            this.mSubTextView = (TextView) this.mContainer.findViewById(R.id.directors_view_sub_textview);
            this.mTextDivider = this.mContainer.findViewById(R.id.directors_view_text_divider);
            this.mActionInvoker.invoke(ViewerAction.UPDATE_DECOR_WIDGET_BG, this.mContainer);
        }
    }

    private boolean isDualVideo(MediaItem mediaItem) {
        if (!DirectorsViewCache.isDualVideo(mediaItem) || mediaItem.getDirectorsViewGroupId() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDirectorsUi$2() {
        checkPareItem(this.mModel.getMediaItem(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMainText$3(ShotMode shotMode) {
        this.mMainTextView.setText(shotMode.titleRes);
    }

    private void registerSubscriber() {
        if (this.mUpdateSubscriber == null) {
            this.mUpdateSubscriber = subscribe("data://user/directorsViewUpdated", new j(11, this));
        }
    }

    private void setVisibility(boolean z, MediaItem mediaItem) {
        if (z && isDualVideo(mediaItem)) {
            inflate();
            ViewUtils.setVisibility(this.mContainer, 0);
            ViewUtils.setVisibility(this.mSubTextView, 0);
            ViewUtils.setVisibility(this.mTextDivider, 0);
            updateMainText(mediaItem);
            updateSubText(mediaItem);
        } else if (!ViewUtils.isViewStub(this.mContainer)) {
            ViewUtils.setVisibility(this.mContainer, 8);
        }
    }

    /* access modifiers changed from: private */
    public void updateDirectorsUi(Object... objArr) {
        this.mThread.runOnUiThread(new l(12, this));
    }

    private void updateMainText(MediaItem mediaItem) {
        if (this.mMainTextView != null && mediaItem.getShotMode() != null) {
            Optional.ofNullable(ShotModeList.getInstance().getByType(mediaItem.getShotMode())).ifPresent(new a(5, this));
        }
    }

    private void updateSubText(MediaItem mediaItem) {
        int stringResId;
        if (this.mSubTextView != null && (stringResId = SefFileSubType.toStringResId(mediaItem.getSefFileSubType())) > 0) {
            this.mSubTextView.setText(stringResId);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new C0520a(7, this));
    }

    public void onInitialized() {
        this.mContainer = this.mViewerLayout.findViewById(R.id.directors_view_icon);
    }

    public void onViewAttached() {
        super.onViewAttached();
        if (SUPPORT) {
            registerSubscriber();
            checkPareItem(this.mModel.getMediaItem(), true);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        setVisibility(false, (MediaItem) null);
        SubscriberListenerInfo subscriberListenerInfo = this.mUpdateSubscriber;
        if (subscriberListenerInfo != null) {
            unsubscribe(subscriberListenerInfo);
            this.mUpdateSubscriber = null;
        }
    }
}
