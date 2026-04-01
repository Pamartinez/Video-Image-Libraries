package com.samsung.android.gallery.app.ui.abstraction;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultEntryAnim {
    /* access modifiers changed from: private */
    public Consumer<Boolean> mCallback;
    /* access modifiers changed from: private */
    public boolean mEnterWithTransition;

    public Animation createAnimation(Context context, int i2) {
        Log.d("DefaultEntryAnim", "create defaultEnterTransition Animation");
        Animation loadAnimation = AnimationUtils.loadAnimation(context, i2);
        if (loadAnimation == null) {
            return null;
        }
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            private final Runnable initializationCallback = new a(0, this);

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$$0() {
                Log.e("DefaultEntryAnim", "defaultEnterTransition cancelled");
                DefaultEntryAnim.this.mEnterWithTransition = false;
            }

            /* JADX WARNING: type inference failed for: r2v3, types: [java.util.function.Consumer, java.lang.Object] */
            public void onAnimationEnd(Animation animation) {
                DefaultEntryAnim.this.mEnterWithTransition = false;
                ThreadUtil.removeCallbackOnUiThread(this.initializationCallback);
                Optional.ofNullable(DefaultEntryAnim.this.mCallback).ifPresent(new Object());
            }

            public void onAnimationStart(Animation animation) {
                DefaultEntryAnim.this.mEnterWithTransition = true;
                ThreadUtil.postOnUiThreadDelayed(this.initializationCallback, 600);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        return loadAnimation;
    }

    public boolean isEnterTransitionState() {
        return this.mEnterWithTransition;
    }

    public void setAnimationEndListener(Consumer<Boolean> consumer) {
        this.mCallback = consumer;
    }
}
