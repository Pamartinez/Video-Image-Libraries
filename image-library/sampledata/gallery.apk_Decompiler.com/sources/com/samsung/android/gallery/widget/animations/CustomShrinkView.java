package com.samsung.android.gallery.widget.animations;

import android.graphics.Bitmap;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CustomShrinkView extends SimpleShrinkView {
    public CustomShrinkView(Blackboard blackboard) {
        super(blackboard);
    }

    public CustomShrinkView setTransitionInfo(TransitionInfo transitionInfo) {
        this.mTransitionInfo = transitionInfo;
        this.mMediaItem = transitionInfo.item;
        Bitmap bitmap = transitionInfo.bitmap;
        this.mBitmap = bitmap;
        if (bitmap != null && ObjectDictionary.getRefCounter(bitmap) > 0) {
            ObjectDictionary.increaseRefCounter(this.mBitmap);
            this.clearBitmapRefCount = true;
        }
        return this;
    }
}
