package com.samsung.android.gallery.app.ui.list.stories.header;

import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinView<V extends IStoriesView> extends BasePinView<IStoriesView> {
    private PinAnimHandler mPinAnimHandler;

    public StoriesPinView(V v) {
        super(v);
        Log.d(this.TAG, "create");
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        this.mPinAnimHandler.handleResolutionChange(i2);
    }

    public void hide() {
        this.mPinAnimHandler.hide();
    }

    public void initialize() {
        super.initialize();
        this.mPinAnimHandler = new PinAnimHandler(this.mRootView, this.mRecyclerView);
    }

    public void onDataChangedOnUi(boolean z) {
        if (getAdapter().getItemCount() <= 0) {
            ViewUtils.setVisibility(this.mRootView, 8);
            return;
        }
        PinAnimHandler pinAnimHandler = this.mPinAnimHandler;
        if (pinAnimHandler == null || !pinAnimHandler.isPinHiddenState()) {
            ViewUtils.setVisibility(this.mRootView, 0);
            getAdapter().resetPinAnimation();
        }
    }

    public void show() {
        if (this.mPinAnimHandler.pinAnimRequired()) {
            this.mView.getListView().scrollToPositionWithOffset(0, 0);
        }
        this.mPinAnimHandler.show();
    }
}
