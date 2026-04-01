package com.samsung.android.gallery.app.ui.list.stories.pictures;

import android.view.MotionEvent;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesFragment;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements InterceptTouchListener {
    public final /* synthetic */ StoryPicturesFragment.ItemTouchCallBack d;

    public /* synthetic */ a(StoryPicturesFragment.ItemTouchCallBack itemTouchCallBack) {
        this.d = itemTouchCallBack;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d.lambda$onSwiped$0(motionEvent);
    }
}
