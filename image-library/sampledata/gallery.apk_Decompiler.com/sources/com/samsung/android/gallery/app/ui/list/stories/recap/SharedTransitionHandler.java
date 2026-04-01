package com.samsung.android.gallery.app.ui.list.stories.recap;

import B4.c;
import Da.f;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedTransitionHandler {
    private Blackboard mBlackboard;
    private TransitionInfo mInfo;
    private ImageView mTransitionView;
    private final IRecapView mView;

    public SharedTransitionHandler(IRecapView iRecapView) {
        this.mView = iRecapView;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startTransition$1(SharedTransition.TransitionListener transitionListener) {
        if (!this.mView.isDestroyed()) {
            transitionListener.onEnterTransitionStartV2();
            transitionListener.onEnterTransitionEndV2();
            Log.d("SharedTransitionHandler", "transition failed, force end");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: prepareEnterInternal */
    public void lambda$bindView$0(boolean z) {
        MediaItem mediaItem;
        TransitionInfo transitionInfo;
        if (!z || (transitionInfo = this.mInfo) == null) {
            Bitmap bitmap = null;
            boolean z3 = false;
            if (this.mView.getMediaData() != null) {
                mediaItem = this.mView.getMediaData().read(0);
            } else {
                mediaItem = null;
            }
            if (mediaItem != null) {
                bitmap = ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.MEDIUM_KIND);
            }
            if (mediaItem != null) {
                z3 = true;
            }
            Log.d("SharedTransitionHandler", "prepareEnterInternal", Boolean.valueOf(z3), Logger.toString(bitmap));
            if (mediaItem != null && bitmap != null) {
                this.mTransitionView.setImageBitmap(bitmap);
                return;
            }
            return;
        }
        setTransitionInfo(transitionInfo.item, transitionInfo.bitmap);
        startTransition(this.mBlackboard, (SharedTransition.TransitionListener) this.mView);
    }

    private void setTransitionInfo(MediaItem mediaItem, Bitmap bitmap) {
        setTransitionName(mediaItem);
        this.mTransitionView.setImageBitmap(bitmap);
    }

    private void setTransitionName(MediaItem mediaItem) {
        SharedTransition.setTransitionName(this.mTransitionView, StorySharedTransitionHelper.getImageTransitionName(mediaItem));
    }

    private void startTransition(Blackboard blackboard, SharedTransition.TransitionListener transitionListener) {
        if (!SharedTransition.startPostponedEnterTransition(transitionListener, blackboard)) {
            ThreadUtil.postOnUiThread(new f(28, this, transitionListener));
        }
    }

    public boolean bindView(View view) {
        boolean z;
        this.mTransitionView = (ImageView) view.findViewById(R.id.transition_view);
        TransitionInfo transitionInfo = this.mInfo;
        if (transitionInfo == null || !transitionInfo.hasValidData()) {
            z = false;
        } else {
            z = true;
        }
        if (this.mTransitionView.getWidth() > 0) {
            lambda$bindView$0(z);
            return z;
        }
        this.mTransitionView.post(new c((Object) this, z, 9));
        return z;
    }

    public void onPrepareSharedTransitionV2(SharedTransition.TransitionListener transitionListener) {
        this.mInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        SharedTransition.onPrepareStories(this.mBlackboard, transitionListener, false);
    }

    public void prepareReturn(MediaItem mediaItem, int i2) {
        if (!this.mView.getOptions().useDefaultExitTransition() && i2 != -1 && mediaItem != null) {
            ViewUtils.setVisibleOrGone(this.mTransitionView, true);
            setTransitionName(mediaItem);
            this.mBlackboard.publish("data://story_transition_return_position", Integer.valueOf(i2));
        }
    }

    public void setBlackboard(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }
}
