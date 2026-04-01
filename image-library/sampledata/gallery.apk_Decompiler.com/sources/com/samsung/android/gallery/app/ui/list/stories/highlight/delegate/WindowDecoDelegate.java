package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.app.Activity;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.window.DecoViewController;
import com.sec.android.gallery3d.R;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WindowDecoDelegate extends Delegate {
    private WindowDecoController mWindowDecoController;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WindowDecoController extends DecoViewController {
        public WindowDecoController(Activity activity, GalleryToolbar galleryToolbar) {
            super(activity, galleryToolbar);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$setToolbarTranslucent$0() {
            updateOverflowIcon(0.0f, this.mToolbar);
            updateNavigationIcon(0.0f, this.mToolbar);
            updateSelectMenu(0.0f, this.mToolbar);
            updateMenuItemView(0.0f, this.mToolbar);
        }

        private void setToolbarTranslucent() {
            setToolbarBackground(0.0f);
            this.mToolbar.post(new t(4, this));
        }

        public int getToolbarBackgroundResId() {
            return R.drawable.memory_pictures_actionbar_background;
        }

        public void updateTransparency() {
            setToolbarTranslucent();
        }
    }

    public WindowDecoDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    private void updateTransparency() {
        this.mWindowDecoController.updateTransparency();
    }

    public void addListenEvent() {
        addEvent(Event.UPDATE_WINDOW_DECO);
    }

    public void handleEvent(Event event, Object... objArr) {
        if (!this.mView.isBackPressed() && event == Event.UPDATE_WINDOW_DECO) {
            updateTransparency();
        }
    }

    public void initView(View view) {
        this.mWindowDecoController = new WindowDecoController(this.mView.getActivity(), this.mView.getToolbar());
    }

    public void onResume() {
        super.onResume();
        updateTransparency();
    }

    public void setScreenMode() {
        super.setScreenMode();
        updateTransparency();
    }
}
