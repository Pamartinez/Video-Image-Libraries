package com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager;

import A.a;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.abstraction.delegate.NotReadyException;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.utils.DataCollectorOnScroll;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import k7.j;
import q7.C0510b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPositionDelegate extends AbsVuDelegate<IVuContainerView> {
    private final DataCollectorOnScroll mDataCollector = new DataCollectorOnScroll(this.mBlackboard);
    private FilmStripView mFilmStripView;
    /* access modifiers changed from: private */
    public ViewPager2 mViewPager;

    public ViewPositionDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private ValueAnimator createSmoothScrollAnimator(int i2) {
        if (Features.isEnabled(Features.IS_RTL)) {
            i2 = -i2;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, i2});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int previousValue = 0;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (ViewPositionDelegate.this.mViewPager != null) {
                    if (!ViewPositionDelegate.this.mViewPager.isFakeDragging()) {
                        Log.w(ViewPositionDelegate.this.TAG, "smoothScroll onAnimationUpdate update but not dragging");
                        ViewPositionDelegate.this.mViewPager.beginFakeDrag();
                    }
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    try {
                        ViewPositionDelegate.this.mViewPager.fakeDragBy(-((float) (intValue - this.previousValue)));
                        this.previousValue = intValue;
                    } catch (Exception e) {
                        Log.w((CharSequence) ViewPositionDelegate.this.TAG, "smoothScroll onAnimationUpdate failed", e.toString());
                    }
                }
            }
        });
        if (isAddListenerForDataCollector()) {
            ofInt.addListener(this.mDataCollector);
        }
        ofInt.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                String access$500 = ViewPositionDelegate.this.TAG;
                Log.d(access$500, "smoothScroll onAnimationUpdate cancel" + ViewPositionDelegate.this.mViewPager);
            }

            public void onAnimationEnd(Animator animator) {
                try {
                    String access$300 = ViewPositionDelegate.this.TAG;
                    Log.d(access$300, "smoothScroll onAnimationUpdate end " + ViewPositionDelegate.this.mViewPager);
                    if (ViewPositionDelegate.this.mViewPager != null) {
                        ViewPositionDelegate.this.mViewPager.endFakeDrag();
                    }
                } catch (Exception e) {
                    a.s(e, new StringBuilder("end fake drag fail\n"), ViewPositionDelegate.this.TAG);
                }
            }

            public void onAnimationStart(Animator animator) {
                String access$200 = ViewPositionDelegate.this.TAG;
                Log.d(access$200, "smoothScroll onAnimationUpdate start " + ViewPositionDelegate.this.mViewPager);
                if (ViewPositionDelegate.this.mViewPager != null) {
                    ViewPositionDelegate.this.mViewPager.beginFakeDrag();
                }
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        return ofInt;
    }

    /* access modifiers changed from: private */
    public void deleteScroll(Object... objArr) {
        if (objArr[1].booleanValue()) {
            smoothScroll(objArr[0].intValue(), (long) objArr[2].intValue(), (Interpolator) null);
        } else {
            scrollTo(objArr[0].intValue(), false);
        }
    }

    private boolean isAddListenerForDataCollector() {
        RecyclerView.Adapter adapter = this.mViewPager.getAdapter();
        if (adapter == null || adapter.getItemCount() >= 10000) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        this.mFilmStripView = (FilmStripView) objArr[0].findViewById(R.id.film_strip_view);
    }

    /* access modifiers changed from: private */
    public void onRequestFlingMove(Object... objArr) {
        RecyclerView.Adapter adapter = this.mViewPager.getAdapter();
        if (adapter == null) {
            Log.e(this.TAG, "onRequestFlingMove adapter is null");
        } else if (!this.mViewPager.isUserInputEnabled()) {
            Log.e(this.TAG, "onRequestFlingMove disabled");
        } else {
            boolean booleanValue = objArr[0].booleanValue();
            int position = ((ContainerModel) this.mModel).getPosition();
            int i2 = -1;
            if (!booleanValue ? Features.isEnabled(Features.IS_RTL) : !Features.isEnabled(Features.IS_RTL)) {
                i2 = 1;
            }
            int i7 = position + i2;
            int itemCount = adapter.getItemCount();
            if (i7 >= 0 && i7 < itemCount) {
                setViewPagerPos(i7, true);
            }
        }
    }

    /* access modifiers changed from: private */
    public void scrollByDirection(Object... objArr) {
        RecyclerView.Adapter adapter = this.mViewPager.getAdapter();
        boolean z = true;
        if (adapter == null || !this.mViewPager.isUserInputEnabled()) {
            String str = this.TAG;
            if (adapter != null) {
                z = false;
            }
            Log.e((CharSequence) str, "failed to scroll -> ", Boolean.valueOf(z), Boolean.valueOf(this.mViewPager.isUserInputEnabled()));
            return;
        }
        boolean booleanValue = objArr[0].booleanValue();
        int position = ((ContainerModel) this.mModel).getPosition();
        int itemCount = adapter.getItemCount();
        if (booleanValue) {
            int i2 = position + 1;
            if (i2 < itemCount) {
                scrollTo(i2, false);
            }
        } else if (position > 0) {
            scrollTo(position - 1, false);
        }
    }

    /* access modifiers changed from: private */
    public void scrollToUndoDeletePos(Object obj, Bundle bundle) {
        int intValue = ((Integer) obj).intValue();
        int position = ((ContainerModel) this.mModel).getPosition();
        String str = this.TAG;
        StringBuilder o2 = C0086a.o(intValue, "UNDO : onMoveTo : ", ArcCommonLog.TAG_COMMA);
        o2.append(((ContainerModel) this.mModel).getPosition());
        Log.d(str, o2.toString());
        if (intValue > 0 && intValue == position - 1) {
            scrollTo(intValue, true);
        }
    }

    private void smoothScroll(int i2, long j2) {
        smoothScroll(i2, j2, new AccelerateDecelerateInterpolator());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        if (PocFeatures.UNDO_DELETE) {
            arrayList.add(new SubscriberInfo("command://ViewerMoveTo", new j(7, this)).setWorkingOnUI());
        }
    }

    public void onBindView(View view) {
        this.mViewPager = ((ContainerModel) this.mModel).getViewPager();
        this.mFilmStripView = (FilmStripView) view.findViewById(R.id.film_strip_view);
    }

    public void onCreate(Bundle bundle) {
        ((ContainerModel) this.mModel).setInitialPos(ArgumentsUtil.getArgValue(DataKey.getViewerDataKey(((IVuContainerView) this.mView).getLocationKey()), Message.KEY_POSITION, 0));
    }

    public void onDestroy() {
        this.mViewPager = null;
        this.mFilmStripView = null;
        this.mDataCollector.destroy();
    }

    public void onEnterTransitionEnd() {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 == null || viewPager2.getAdapter() == null) {
            throw new NotReadyException("ViewPager adapter is null");
        } else if (((ContainerModel) this.mModel).getMediaData().isDataAvailable()) {
            setViewPagerPos(((ContainerModel) this.mModel).getInitialPos(), false);
            ((ContainerModel) this.mModel).setInitialPos(-1);
        }
    }

    public void onMediaDataChanged(int i2, int i7) {
        if (((ContainerModel) this.mModel).getInitialPos() >= 0 && this.mViewPager.getAdapter() != null) {
            setViewPagerPos(((ContainerModel) this.mModel).getInitialPos(), false);
            ((ContainerModel) this.mModel).setInitialPos(-1);
        }
    }

    public void scrollTo(int i2, boolean z) {
        FilmStripView filmStripView;
        setViewPagerPos(i2, z);
        if (!z && (filmStripView = this.mFilmStripView) != null) {
            filmStripView.scrollToPosition(i2);
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.SCROLL_TO, new C0510b(this, 0));
        actionInvoker.add(ViewerAction.FORCE_SWIPE, new C0510b(this, 1));
        actionInvoker.add(ViewerAction.SCROLL_BY_DIRECTION, new C0510b(this, 2));
        actionInvoker.add(ViewerAction.SET_VIEWPAGER_POS, new C0510b(this, 3));
        actionInvoker.add(ViewerAction.REQUEST_FLING_MOVE, new C0510b(this, 4));
        actionInvoker.add(ViewerAction.FILM_STRIP_LAYOUT_REPLACED, new C0510b(this, 5));
    }

    public void setViewPagerPos(int i2, boolean z) {
        String str = this.TAG;
        ((IVuContainerView) this.mView).printLog(str, "setViewPagerPos pos = " + i2 + "/" + z + "/" + this.mViewPager.isFakeDragging() + "/" + this.mViewPager.getScrollState());
        if (z) {
            smoothScroll(i2, 300);
        } else if (!this.mViewPager.isFakeDragging()) {
            this.mViewPager.setCurrentItem(i2, false);
        }
    }

    private void smoothScroll(int i2, long j2, Interpolator interpolator) {
        ValueAnimator createSmoothScrollAnimator = createSmoothScrollAnimator((i2 - this.mViewPager.getCurrentItem()) * this.mViewPager.getWidth());
        createSmoothScrollAnimator.setInterpolator(interpolator);
        createSmoothScrollAnimator.setDuration(j2);
        createSmoothScrollAnimator.start();
        Log.d(this.TAG, "smoothScroll onAnimationUpdate run");
    }

    /* access modifiers changed from: private */
    public void scrollTo(Object... objArr) {
        scrollTo(objArr[0].intValue(), objArr[1].booleanValue());
    }

    /* access modifiers changed from: private */
    public void setViewPagerPos(Object... objArr) {
        setViewPagerPos(objArr[0].intValue(), objArr[1].booleanValue());
    }
}
