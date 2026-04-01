package com.samsung.android.app.sdk.deepsky.contract.suggestion.view.widget;

import com.samsung.android.app.sdk.deepsky.contract.suggestion.view.widget.SwipeDismissFrameLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SwipeDismissFrameLayout e;

    public /* synthetic */ a(SwipeDismissFrameLayout swipeDismissFrameLayout, int i2) {
        this.d = i2;
        this.e = swipeDismissFrameLayout;
    }

    public final void run() {
        int i2 = this.d;
        SwipeDismissFrameLayout swipeDismissFrameLayout = this.e;
        switch (i2) {
            case 0:
                SwipeDismissFrameLayout.MyOnDismissedListener.onDismissed$lambda$0(swipeDismissFrameLayout);
                return;
            default:
                SwipeDismissFrameLayout.MyOnSwipeProgressChangedListener.onSwipeCanceled$lambda$0(swipeDismissFrameLayout);
                return;
        }
    }
}
