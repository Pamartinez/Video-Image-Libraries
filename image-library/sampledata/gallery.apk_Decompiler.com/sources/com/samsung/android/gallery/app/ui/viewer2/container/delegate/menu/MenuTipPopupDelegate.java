package com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu;

import B7.d;
import android.content.res.Configuration;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.widget.SemTipPopup;
import com.sec.android.gallery3d.R;
import p7.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuTipPopupDelegate extends AbsVuDelegate<IVuContainerView> {
    private SemTipPopup mTipPopup;

    public MenuTipPopupDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private boolean isValidItem() {
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        if (currentMediaItem == null || currentMediaItem.isBroken() || currentMediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showMenuTipPopup$0(int i2) {
        if (i2 == 0) {
            this.mTipPopup = null;
        }
    }

    /* access modifiers changed from: private */
    public void showMenuTipPopup(Object... objArr) {
        View view;
        int i2;
        String str;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        FastOptionView fastOptionView = ((ContainerModel) this.mModel).getFastOptionView();
        if (toolbar == null || fastOptionView == null) {
            Log.e((CharSequence) this.TAG, "cannot find toolbar or fastOptionView", toolbar, fastOptionView);
        } else if (currentViewer != null && BottomSheetState.isClosed(currentViewer.getModel()) && ((IVuContainerView) this.mView).isViewResumed() && isValidItem()) {
            int intValue = objArr[0].intValue();
            String str2 = objArr[1];
            View findFastOptionItemView = fastOptionView.findFastOptionItemView(intValue);
            if (findFastOptionItemView != null) {
                view = findFastOptionItemView.findViewById(R.id.fast_option_img_icon);
            } else {
                view = null;
            }
            if (view == null) {
                view = toolbar.findViewById(intValue);
                i2 = 2;
            } else {
                i2 = -1;
            }
            if (view == null || !view.isShown()) {
                String str3 = this.TAG;
                if (view == null) {
                    str = "null";
                } else {
                    str = "not shown";
                }
                Log.w(str3, "cannot find anchorView : ".concat(str));
                return;
            }
            SemTipPopup semTipPopup = new SemTipPopup(view, 0);
            this.mTipPopup = semTipPopup;
            semTipPopup.setExpanded(true);
            this.mTipPopup.setBackgroundColor(getContext().getColor(R.color.fastoption_details_tips_bg_color));
            this.mTipPopup.setOnStateChangeListener(new a(this));
            this.mTipPopup.setMessage(str2);
            this.mTipPopup.show(i2);
            Log.d(this.TAG, "show Menu Tip Popup", view.toString());
        }
    }

    public void dismiss() {
        SemTipPopup semTipPopup = this.mTipPopup;
        if (semTipPopup != null) {
            semTipPopup.dismiss(false);
        }
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        dismiss();
    }

    public void onDestroy() {
        dismiss();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.SHOW_MENU_TIP_POPUP, new d(25, this));
    }
}
