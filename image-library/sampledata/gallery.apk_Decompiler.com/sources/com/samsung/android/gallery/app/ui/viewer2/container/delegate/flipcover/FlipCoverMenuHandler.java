package com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover;

import a6.C0419b;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverMenuHandler {
    protected final String TAG = getClass().getSimpleName();
    protected final EventContext mEventContext;
    protected final ImageView mImageView;
    protected final ViewerMenuItem mMenuItem;
    protected final View mView;

    public FlipCoverMenuHandler(EventContext eventContext, ViewGroup viewGroup, ViewerMenuItem viewerMenuItem) {
        View inflate = LayoutInflater.from(eventContext.getContext()).inflate(getLayoutId(), viewGroup, false);
        this.mView = inflate;
        this.mMenuItem = viewerMenuItem;
        ImageView imageView = (ImageView) inflate.findViewById(R.id.flip_cover_menu_image);
        this.mImageView = imageView;
        this.mEventContext = eventContext;
        imageView.setImageDrawable(viewerMenuItem.getDrawable());
        inflate.setOnClickListener(getOnClickListener(viewerMenuItem));
        inflate.setContentDescription(AppResources.getString(viewerMenuItem.getTitleResId()));
        inflate.setTooltipText(AppResources.getString(viewerMenuItem.getTitleResId()));
        setDefaultAccessibilityDelegate();
    }

    private void setDefaultAccessibilityDelegate() {
        this.mView.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        this.mView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setRoleDescription(FlipCoverMenuHandler.this.mEventContext.getContext().getString(R.string.speak_button));
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                view.setHovered(true);
            }
        });
    }

    public int getLayoutId() {
        return R.layout.flip_cover_menu_button_layout;
    }

    public View.OnClickListener getOnClickListener(ViewerMenuItem viewerMenuItem) {
        Objects.requireNonNull(viewerMenuItem);
        return new C0419b(23, viewerMenuItem);
    }

    public View getView() {
        return this.mView;
    }

    public void onPageInvalidate() {
        updateVisibility();
    }

    public void onPageSelected() {
        updateVisibility();
    }

    public void updateVisibility() {
        ViewUtils.setVisibleOrGone(this.mView, this.mMenuItem.isValid(this.mEventContext.getCurrentItem(), this.mEventContext.getLocationKey()));
    }

    public void onDestroy() {
    }
}
