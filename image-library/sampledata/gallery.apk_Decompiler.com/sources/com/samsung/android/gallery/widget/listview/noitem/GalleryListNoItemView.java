package com.samsung.android.gallery.widget.listview.noitem;

import Hb.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.listview.SimpleGestureListener;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryListNoItemView extends LinearLayout implements GestureDetector.OnGestureListener {
    private GalleryAppBarLayout mAppbar;
    private View mButtonLayout;
    private String mDescription;
    private View mEmptyViewText;
    private GestureDetector mGestureDetector;
    private String mLabel;
    private SimpleGestureListener mListener;
    private boolean mOngoingAnimation;

    public GalleryListNoItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void hideButtonLayout() {
        ViewUtils.setVisibility(this.mButtonLayout, 8);
    }

    private void hideEmptyViewText() {
        ViewUtils.setVisibility(this.mEmptyViewText, 8);
    }

    private void initDetector() {
        this.mGestureDetector = new GestureDetector(getContext(), this);
    }

    private void initLabel() {
        View view = this.mEmptyViewText;
        if (view instanceof NoItemView) {
            NoItemView noItemView = (NoItemView) view;
            Optional.ofNullable(this.mLabel).ifPresent(new a(noItemView, 0));
            Optional.ofNullable(this.mDescription).ifPresent(new a(noItemView, 1));
        }
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.fragment_album_pictures_emptyview, this, true);
        View findViewById = inflate.findViewById(R$id.create_button_layout);
        this.mButtonLayout = findViewById;
        ViewUtils.setAccessibilityRoleDescription(findViewById, R$string.speak_button);
        this.mEmptyViewText = inflate.findViewById(R$id.empty_view_text);
        initLabel();
    }

    private boolean isAppbarScrolling() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppbar;
        if (galleryAppBarLayout == null || !galleryAppBarLayout.isPartiallyVisible() || this.mAppbar.getTotalScrollRange() == this.mAppbar.getVisibleHeight()) {
            return false;
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.mOngoingAnimation || this.mGestureDetector.onTouchEvent(motionEvent)) {
            z = true;
        } else {
            z = false;
        }
        if (!this.mOngoingAnimation) {
            z |= super.dispatchTouchEvent(motionEvent);
        }
        if (z || this.mOngoingAnimation) {
            return true;
        }
        return false;
    }

    public void finishOnGoingAnimation() {
        setOngoingAnimation(false);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
        if (!(motionEvent == null || motionEvent2 == null || this.mListener == null || this.mOngoingAnimation || isAppbarScrolling())) {
            float x9 = motionEvent2.getX() - motionEvent.getX();
            if (Math.abs(x9) > Math.abs(motionEvent2.getY() - motionEvent.getY()) * 2.0f && Math.abs(x9) > 100.0f && Math.abs(f) > 50.0f) {
                SimpleGestureListener simpleGestureListener = this.mListener;
                if (simpleGestureListener == null) {
                    return true;
                }
                if (x9 > 0.0f) {
                    simpleGestureListener.onNoItemSwipeRight(motionEvent, motionEvent2);
                    return true;
                }
                simpleGestureListener.onNoItemSwipeLeft(motionEvent, motionEvent2);
                return true;
            }
        }
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void prepareOnGoingAnimation() {
        setOngoingAnimation(true);
        hideButtonLayout();
        hideEmptyViewText();
    }

    public void setAppbar(GalleryAppBarLayout galleryAppBarLayout) {
        this.mAppbar = galleryAppBarLayout;
    }

    public void setAttribute(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        boolean z;
        boolean isTerminated;
        try {
            obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.NoItemView);
            z = false;
            this.mLabel = obtainStyledAttributes.getString(R$styleable.NoItemView_label);
            this.mDescription = obtainStyledAttributes.getString(R$styleable.NoItemView_description);
            if (obtainStyledAttributes instanceof AutoCloseable) {
                ((AutoCloseable) obtainStyledAttributes).close();
                return;
            } else if (obtainStyledAttributes instanceof ExecutorService) {
                ExecutorService executorService = (ExecutorService) obtainStyledAttributes;
                if (executorService != ForkJoinPool.commonPool()) {
                    boolean isTerminated2 = executorService.isTerminated();
                    if (!isTerminated2) {
                        executorService.shutdown();
                        while (!isTerminated2) {
                            try {
                                isTerminated2 = executorService.awaitTermination(1, TimeUnit.DAYS);
                            } catch (InterruptedException unused) {
                                if (!z) {
                                    executorService.shutdownNow();
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            } else {
                obtainStyledAttributes.recycle();
                return;
            }
        } catch (Exception unused2) {
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void setGestureListener(SimpleGestureListener simpleGestureListener) {
        this.mListener = simpleGestureListener;
    }

    public void setOngoingAnimation(boolean z) {
        this.mOngoingAnimation = z;
    }

    public GalleryListNoItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOngoingAnimation = false;
        setAttribute(attributeSet);
        initView();
        initDetector();
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public void onShowPress(MotionEvent motionEvent) {
    }
}
