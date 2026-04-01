package com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu;

import B7.d;
import android.graphics.ColorFilter;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.FavoriteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.cover.ScoverState;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FavoriteMenuAnimDelegate extends AbsVuDelegate<IVuContainerView> {
    private Runnable mRunnable;

    public FavoriteMenuAnimDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void doRunnable() {
        Runnable runnable = this.mRunnable;
        if (runnable != null) {
            runnable.run();
            this.mRunnable = null;
        }
    }

    private AnimatedVectorDrawable getDrawables(Drawable drawable) {
        return (AnimatedVectorDrawable) ((LayerDrawable) drawable).getDrawable(0);
    }

    private void removeColorFilter(ViewGroup viewGroup, Integer num) {
        View findViewWithTag;
        if (viewGroup != null && num != null && (findViewWithTag = viewGroup.findViewWithTag(num)) != null) {
            View findViewById = findViewWithTag.findViewById(R.id.fast_option_img_icon);
            if (findViewById instanceof ImageView) {
                ((ImageView) findViewById).getDrawable().setColorFilter((ColorFilter) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void resetAVD(ViewerMenuItem viewerMenuItem, AnimatedVectorDrawable animatedVectorDrawable) {
        int i2;
        if (viewerMenuItem.isDim()) {
            i2 = 115;
        } else {
            i2 = ScoverState.TYPE_NFC_SMART_COVER;
        }
        animatedVectorDrawable.setAlpha(i2);
        animatedVectorDrawable.clearAnimationCallbacks();
        animatedVectorDrawable.reset();
    }

    /* access modifiers changed from: private */
    public void startFavoriteVI(Object... objArr) {
        ConcurrentHashMap<Class<?>, ViewerMenuItem> enabledMenuMap = ((ContainerModel) this.mModel).getEnabledMenuMap();
        Objects.requireNonNull(enabledMenuMap);
        final ViewerMenuItem viewerMenuItem = enabledMenuMap.get(FavoriteMenuItem.class);
        if (viewerMenuItem == null) {
            Log.e(this.TAG, "menuItem is null");
            return;
        }
        this.mRunnable = objArr[0];
        final AnimatedVectorDrawable drawables = getDrawables(viewerMenuItem.getDrawable());
        if (drawables == null) {
            doRunnable();
            Log.e(this.TAG, "There is no staticDrawable or animateDrawable");
        } else if (drawables.isRunning()) {
            resetAVD(viewerMenuItem, drawables);
        } else {
            removeColorFilter(((ContainerModel) this.mModel).getFastOptionView(), Integer.valueOf(viewerMenuItem.getMenuId()));
            drawables.registerAnimationCallback(new Animatable2.AnimationCallback() {
                public void onAnimationEnd(Drawable drawable) {
                    super.onAnimationEnd(drawable);
                    FavoriteMenuAnimDelegate.this.doRunnable();
                    FavoriteMenuAnimDelegate.this.resetAVD(viewerMenuItem, drawables);
                }
            });
            drawables.start();
        }
    }

    public void onDestroy() {
        doRunnable();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.START_FAVORITE_ICON_ANIMATION, new d(24, this));
    }
}
