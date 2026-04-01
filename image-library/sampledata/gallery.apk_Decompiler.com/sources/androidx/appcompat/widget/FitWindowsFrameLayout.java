package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FitWindowsFrameLayout extends FrameLayout {
    private FitWindowsViewGroup$OnFitSystemWindowsListener mListener;

    public FitWindowsFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean fitSystemWindows(Rect rect) {
        FitWindowsViewGroup$OnFitSystemWindowsListener fitWindowsViewGroup$OnFitSystemWindowsListener = this.mListener;
        if (fitWindowsViewGroup$OnFitSystemWindowsListener != null) {
            fitWindowsViewGroup$OnFitSystemWindowsListener.onFitSystemWindows(rect);
        }
        return super.fitSystemWindows(rect);
    }

    public void setOnFitSystemWindowsListener(FitWindowsViewGroup$OnFitSystemWindowsListener fitWindowsViewGroup$OnFitSystemWindowsListener) {
        this.mListener = fitWindowsViewGroup$OnFitSystemWindowsListener;
    }
}
