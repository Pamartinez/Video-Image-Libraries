package com.samsung.android.gallery.app.ui.viewer2.container.delegate.input;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyboardFocusHandler {
    private final IVuContainerView mContainer;
    private final FocusableIdListHandler mIdListHandler = new FocusableIdListHandler();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FocusableIdListHandler {
        private final ArrayList<FocusableId> mIdList = new ArrayList<>();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class FocusableId {
            final int mId;
            final View mParentView;

            public FocusableId(int i2, View view) {
                this.mId = i2;
                this.mParentView = view;
            }

            public View getView() {
                View view = this.mParentView;
                if (view != null) {
                    return view.findViewById(this.mId);
                }
                return null;
            }
        }

        /* access modifiers changed from: private */
        public boolean requestFocusInternal(int i2) {
            if (i2 >= this.mIdList.size()) {
                return false;
            }
            View view = this.mIdList.get(i2).getView();
            if (ViewUtils.isVisible(view)) {
                return view.requestFocus();
            }
            return false;
        }

        public boolean add(int i2, View view) {
            return this.mIdList.add(new FocusableId(i2, view));
        }

        public int findPosition(View view) {
            while (true) {
                if (view == null) {
                    return 0;
                }
                for (int i2 = 0; i2 < this.mIdList.size(); i2++) {
                    if (view.getId() == this.mIdList.get(i2).mId) {
                        return i2;
                    }
                }
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    view = (View) parent;
                } else {
                    view = null;
                }
            }
        }

        public boolean requestFocus(int i2, boolean z) {
            if (i2 >= this.mIdList.size()) {
                return requestFocusInternal(0);
            }
            while (i2 < this.mIdList.size() && i2 >= 0) {
                if (requestFocusInternal(i2)) {
                    return true;
                }
                if (z) {
                    i2++;
                } else {
                    i2--;
                }
            }
            return false;
        }
    }

    public KeyboardFocusHandler(IVuContainerView iVuContainerView) {
        this.mContainer = iVuContainerView;
        addFocusableId();
    }

    private void addFocusableId() {
        ViewPager2 viewPager = ((ContainerModel) this.mContainer.getModel()).getViewPager();
        View findViewById = getRootView().findViewById(R.id.main_layout);
        View findViewById2 = getRootView().findViewById(R.id.viewer_container_layout);
        if (viewPager == null || findViewById == null || findViewById2 == null) {
            Log.w("KeyboardFocusHandler", "cannot make focusableId list. view is null");
        } else if (((ContainerModel) this.mContainer.getModel()).isFlipCoverGallery()) {
            this.mIdListHandler.add(R.id.toolbar, findViewById);
            this.mIdListHandler.add(R.id.flip_cover_menu_container, findViewById2);
            this.mIdListHandler.add(R.id.content_container, viewPager);
        } else {
            this.mIdListHandler.add(R.id.toolbar, findViewById);
            this.mIdListHandler.add(R.id.quick_crop_button, viewPager);
            this.mIdListHandler.add(R.id.group_count_stub, viewPager);
            this.mIdListHandler.add(R.id.document_scan_button, viewPager);
            this.mIdListHandler.add(R.id.text_extraction_button, viewPager);
            this.mIdListHandler.add(R.id.shot_mode_button, viewPager);
            this.mIdListHandler.add(R.id.video_controller_view, viewPager);
            this.mIdListHandler.add(R.id.play_audio_icon_stub, viewPager);
            this.mIdListHandler.add(R.id.fast_option_view, findViewById);
            this.mIdListHandler.add(R.id.content_container, viewPager);
        }
    }

    private View findFocusedView() {
        View findFocus = getRootView().findFocus();
        if (findFocus != null) {
            return findFocus;
        }
        return getRootView().findViewById(R.id.toolbar);
    }

    private ViewGroup getRootView() {
        return (ViewGroup) this.mContainer.getView();
    }

    public boolean forceFocusToTopView() {
        return this.mIdListHandler.requestFocusInternal(0);
    }

    public boolean next() {
        View findFocusedView = findFocusedView();
        return this.mIdListHandler.requestFocus(this.mIdListHandler.findPosition(findFocusedView) + 1, true);
    }

    public boolean prev() {
        View findFocusedView = findFocusedView();
        return this.mIdListHandler.requestFocus(this.mIdListHandler.findPosition(findFocusedView) - 1, false);
    }
}
