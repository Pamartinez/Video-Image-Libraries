package com.samsung.android.gallery.app.ui.list.stories.spotify;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnTouchListener {
    public final /* synthetic */ SpotifySlideshowAdapter d;

    public /* synthetic */ b(SpotifySlideshowAdapter spotifySlideshowAdapter) {
        this.d = spotifySlideshowAdapter;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.d.onTouch(view, motionEvent);
    }
}
