package com.samsung.android.gallery.app.ui.viewer2.unlock;

import H7.A;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.databinding.UnlockFragmentLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnlockViewerHolder extends AbsViewerHolder<UnlockFragmentLayoutBinding> {
    public UnlockViewerHolder(UnlockFragmentLayoutBinding unlockFragmentLayoutBinding, AbsViewerHolder.StateListener stateListener) {
        super(unlockFragmentLayoutBinding, stateListener);
    }

    private String getUnlockText() {
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            return this.mModel.getContext().getString(R.string.unlock_device_to_view_tablet);
        }
        return this.mModel.getContext().getString(R.string.unlock_device_to_view);
    }

    /* access modifiers changed from: private */
    public void onLockIconClick(View view) {
        this.mActionInvoker.invoke(ViewerAction.REQUEST_UNLOCK_SCREEN, new Object[0]);
    }

    private void updateLayout() {
        Resources resources = this.itemView.getResources();
        if (resources != null) {
            ViewUtils.setHeight(((UnlockFragmentLayoutBinding) this.mViewBinding).unlockScreenContainer, resources.getDimensionPixelOffset(R.dimen.unlock_container_height));
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        ((UnlockFragmentLayoutBinding) this.mViewBinding).lockIconLayout.setOnClickListener(new A(28, this));
        ((UnlockFragmentLayoutBinding) this.mViewBinding).unlockDescriptionTextView.setText(getUnlockText());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateLayout();
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateLayout();
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mActionInvoker.invoke(ViewerAction.REQUEST_UPDATE_SCREEN_MODE_ON_PAGE_SELECTED, new Object[0]);
    }
}
