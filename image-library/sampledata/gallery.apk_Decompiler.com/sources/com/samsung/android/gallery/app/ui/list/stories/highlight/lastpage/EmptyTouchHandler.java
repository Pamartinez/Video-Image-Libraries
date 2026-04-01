package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import B2.i;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.gallery.widget.listview.TouchInterceptLinearLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;
import java.util.function.Predicate;
import o6.p;
import q6.C0508a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptyTouchHandler {
    private boolean mInsideTouching;
    private boolean mOutsideTouching;
    private final RecyclerView mPageRecyclerView;
    private final Consumer<Boolean> mTouchConsumer;
    private float mTouchDownX;
    private float mTouchDownY;
    private final TouchInterceptLinearLayout mTouchParent;

    public EmptyTouchHandler(View view, Consumer<Boolean> consumer) {
        TouchInterceptLinearLayout touchInterceptLinearLayout = (TouchInterceptLinearLayout) view;
        this.mTouchParent = touchInterceptLinearLayout;
        touchInterceptLinearLayout.setListener(new p(5, this));
        touchInterceptLinearLayout.setOnTouchListener(new i(29, this));
        this.mTouchConsumer = consumer;
        this.mPageRecyclerView = (RecyclerView) view.findViewById(R.id.page_recycler);
    }

    private boolean acceptArea(float f, float f5) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mPageRecyclerView.getLayoutManager();
        if (linearLayoutManager == null || linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
            RecyclerView.Adapter adapter = this.mPageRecyclerView.getAdapter();
            if (adapter == null || adapter.getItemCount() != 0) {
                return false;
            }
            return true;
        }
        PageHolder pageHolder = (PageHolder) this.mPageRecyclerView.findViewHolderForAdapterPosition(0);
        if (pageHolder != null) {
            RectF viewRect = ViewUtils.getViewRect(pageHolder.getContentView());
            if ((f5 <= viewRect.top || f >= viewRect.left) && f5 <= viewRect.bottom) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean childUnder(ViewGroup viewGroup, float f, float f5, Predicate<View> predicate) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (ViewUtils.isVisible(childAt) && ViewUtils.getViewRect(childAt).contains(f, f5)) {
                if (predicate.test(childAt)) {
                    return true;
                }
                if (childAt instanceof ViewGroup) {
                    return childUnder((ViewGroup) childAt, f, f5, predicate);
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isTouchableOutsideView(View view) {
        if (view.getId() == R.id.replay_button_layout || view.getId() == R.id.tool || view.getId() == R.id.save_story_layout || view.getId() == R.id.save_as_video_layout) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isTouchableViewInPager(View view) {
        if (view.getId() == R.id.content || view.getId() == R.id.related_list || view.getId() == R.id.collage_tool || view.getId() == R.id.last_page_save_story_layout) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isTouchableViewInRoot(View view) {
        if (view.getId() == R.id.page_recycler || view.getId() == R.id.replay_button_layout || view.getId() == R.id.tool || view.getId() == R.id.save_story_layout || view.getId() == R.id.save_story_layout_in_page || view.getId() == R.id.save_as_video_layout) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(View view, MotionEvent motionEvent) {
        return onOutsideTouchEvent(motionEvent);
    }

    /* access modifiers changed from: private */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 3) {
                    reset();
                    return false;
                } else if (actionMasked != 5) {
                    if (actionMasked != 6) {
                        return false;
                    }
                }
            }
            if (this.mInsideTouching && underThreshold(motionEvent.getX(), motionEvent.getY())) {
                this.mTouchConsumer.accept((Object) null);
                Log.d("EmptyTouchHandler", "touch inside up -- ", Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
            }
            reset();
            return false;
        }
        reset();
        if (!acceptArea(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        if (!childUnder(this.mTouchParent, motionEvent.getX(), motionEvent.getY(), new C0508a(this, 0))) {
            this.mOutsideTouching = true;
            Log.d("EmptyTouchHandler", "consume outsideTouch ", Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
            return true;
        } else if (childUnder(this.mTouchParent, motionEvent.getX(), motionEvent.getY(), new C0508a(this, 1)) || childUnder(this.mTouchParent, motionEvent.getX(), motionEvent.getY(), new C0508a(this, 2))) {
            return false;
        } else {
            this.mInsideTouching = true;
            this.mTouchDownX = motionEvent.getX();
            this.mTouchDownY = motionEvent.getY();
            Log.d("EmptyTouchHandler", "touch inside down ++ ", Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
            return false;
        }
    }

    private boolean onOutsideTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    return this.mOutsideTouching;
                }
                if (actionMasked == 3) {
                    reset();
                    return false;
                } else if (actionMasked != 5) {
                    if (actionMasked != 6) {
                        return false;
                    }
                }
            }
            boolean z = this.mOutsideTouching;
            if (z && underThreshold(motionEvent.getX(), motionEvent.getY())) {
                this.mTouchConsumer.accept(Boolean.TRUE);
                Log.d("EmptyTouchHandler", "outsideTouch up -- ", Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
            }
            reset();
            return z;
        }
        if (!this.mOutsideTouching) {
            return false;
        }
        this.mTouchDownX = motionEvent.getX();
        this.mTouchDownY = motionEvent.getY();
        Log.d("EmptyTouchHandler", "outsideTouch down ++ ", Boolean.TRUE, Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
        return this.mOutsideTouching;
    }

    private void reset() {
        this.mInsideTouching = false;
        this.mOutsideTouching = false;
        this.mTouchDownX = -1.0f;
        this.mTouchDownY = -1.0f;
    }

    private boolean underThreshold(float f, float f5) {
        if (Math.max(Math.abs(this.mTouchDownX - f), Math.abs(this.mTouchDownY - f5)) < 10.0f) {
            return true;
        }
        return false;
    }

    public void destroy() {
        this.mTouchParent.setListener((InterceptTouchListener) null);
    }
}
