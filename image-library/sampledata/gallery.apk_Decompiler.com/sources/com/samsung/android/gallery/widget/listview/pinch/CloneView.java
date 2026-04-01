package com.samsung.android.gallery.widget.listview.pinch;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloneView extends View {
    private View mSource;

    public CloneView(Context context) {
        super(context);
    }

    public void onDraw(Canvas canvas) {
        View view = this.mSource;
        if (view != null) {
            view.draw(canvas);
        }
    }

    public void setSource(View view) {
        this.mSource = view;
    }
}
