package com.samsung.android.gallery.app.ui.list.stories.recap.delegate;

import H6.a;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecorLayoutDelegate extends Delegate {
    private View mBottomDecorLayout;
    private View mTouchLayout;

    public DecorLayoutDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        onTouchLayoutEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        onTouchLayoutEnabled(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(View view) {
        this.mEventHandler.postEvent(Event.TOGGLE_OSD, new Object[0]);
    }

    private void onTouchLayoutEnabled(boolean z) {
        ViewUtils.setViewEnabled(this.mTouchLayout, z);
    }

    private void setBottomDecorVisibility(boolean z) {
        int i2;
        View view = this.mBottomDecorLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(view, i2);
    }

    private void updateLayout() {
        WindowInsets windowInsets;
        int i2;
        int i7;
        View view = this.mBottomDecorLayout;
        if (view != null) {
            windowInsets = view.getRootWindowInsets();
        } else {
            windowInsets = null;
        }
        View view2 = this.mBottomDecorLayout;
        int i8 = 0;
        if (!this.mView.isInMultiWindowMode()) {
            i2 = WindowUtils.getSystemInsetsBottom(windowInsets);
        } else {
            i2 = 0;
        }
        ViewMarginUtils.setBottomMargin(view2, i2);
        View view3 = this.mBottomDecorLayout;
        if (!this.mView.isInMultiWindowMode()) {
            i7 = WindowUtils.getSystemInsetsEnd(windowInsets);
        } else {
            i7 = 0;
        }
        ViewMarginUtils.setEndMargin(view3, i7);
        View view4 = this.mBottomDecorLayout;
        if (!this.mView.isInMultiWindowMode()) {
            i8 = WindowUtils.getSystemInsetsStart(windowInsets);
        }
        ViewMarginUtils.setStartMargin(view4, i8);
    }

    /* access modifiers changed from: private */
    public void updateVisibility(Object... objArr) {
        float f;
        boolean booleanValue = objArr[0].booleanValue();
        View view = this.mBottomDecorLayout;
        if (booleanValue) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        view.setAlpha(f);
        setBottomDecorVisibility(booleanValue);
    }

    public void addListenEvent() {
        addEvent(Event.ENABLE_BOTTOM_DECO, new a(this, 0));
        addEvent(Event.SLIDE_SHOW_DONE, new a(this, 1));
        addEvent(Event.REPLAY_VIDEO, new a(this, 2));
    }

    public void initView(View view) {
        View findViewById = view.findViewById(R.id.touch_layout);
        this.mTouchLayout = findViewById;
        findViewById.setOnClickListener(new Ab.a(24, this));
        View findViewById2 = view.findViewById(R.id.bottom_decor_container);
        this.mBottomDecorLayout = findViewById2;
        ViewUtils.setVisibility(findViewById2, 4);
        updateLayout();
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        updateLayout();
    }

    public void onDestroy() {
        View view = this.mTouchLayout;
        if (view != null) {
            view.setOnClickListener((View.OnClickListener) null);
        }
        this.mTouchLayout = null;
    }
}
