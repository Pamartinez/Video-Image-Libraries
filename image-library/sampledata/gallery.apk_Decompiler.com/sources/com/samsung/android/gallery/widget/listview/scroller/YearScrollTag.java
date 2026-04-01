package com.samsung.android.gallery.widget.listview.scroller;

import A.a;
import B2.h;
import N2.j;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.listview.scroller.FastScroller;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class YearScrollTag {
    private final int mBottomMargin;
    private int mExtraMargin;
    private final int mMinimumGap;
    private int mRecyclerViewHeight;
    private int mRecyclerViewWidth;
    private int mScrollCount;
    int mScrollHeight;
    private int mThumbHeight;
    private final int mTopMargin;
    private int mTotalCount;
    private final LinkedList<YearData> mYearDataList = new LinkedList<>();
    private final YearViewPool mYearViewPool;

    public YearScrollTag(ViewGroup viewGroup, int i2, int i7) {
        this.mYearViewPool = new YearViewPool(((Activity) viewGroup.getContext()).getLayoutInflater(), (ViewGroup) viewGroup.getParent());
        this.mMinimumGap = viewGroup.getResources().getDimensionPixelOffset(R$dimen.fast_scroll_year_popup_minimum_gap);
        this.mTopMargin = i2;
        this.mBottomMargin = i7;
    }

    private int getPositionX(View view) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return this.mExtraMargin;
        }
        return (this.mRecyclerViewWidth - view.getWidth()) + this.mExtraMargin;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createTagViewAlphaAnimator$1(ValueAnimator valueAnimator) {
        this.mYearDataList.forEach(new a(((Float) valueAnimator.getAnimatedValue()).floatValue()));
    }

    private void removeAll() {
        Iterator<YearData> it = this.mYearDataList.iterator();
        while (it.hasNext()) {
            this.mYearViewPool.add(it.next().getView());
        }
        this.mYearDataList.clear();
        this.mScrollHeight = 0;
    }

    public boolean calculate(FastScroller fastScroller, int i2) {
        float f;
        float f5;
        Iterator<Pair<String, Integer>> it;
        int i7 = i2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.mExtraMargin = fastScroller.getExtraMargin();
            this.mRecyclerViewWidth = fastScroller.getRecyclerViewWidth();
            int recyclerViewHeight = fastScroller.getRecyclerViewHeight();
            int itemCount = fastScroller.getItemCount();
            FastScroller.LayoutInformer layoutInformer = fastScroller.getLayoutInformer();
            int verticalThumbHeight = fastScroller.getVerticalThumbHeight();
            if (this.mThumbHeight == verticalThumbHeight && this.mTotalCount == itemCount && this.mScrollCount == i7 && this.mYearDataList.size() > 0 && recyclerViewHeight == this.mRecyclerViewHeight) {
                return true;
            }
            this.mRecyclerViewHeight = recyclerViewHeight;
            this.mThumbHeight = verticalThumbHeight;
            this.mTotalCount = itemCount;
            this.mScrollCount = i7;
            removeAll();
            if (layoutInformer == null || i7 <= 0 || itemCount == 0 || this.mRecyclerViewWidth <= 0 || this.mRecyclerViewHeight <= 0) {
                return false;
            }
            int scrollStart = layoutInformer.getScrollStart() + this.mTopMargin;
            ArrayList<Pair<String, Integer>> dividerList = layoutInformer.getDividerList();
            if (dividerList == null) {
                return false;
            }
            int i8 = ((this.mRecyclerViewHeight - verticalThumbHeight) - this.mTopMargin) - this.mBottomMargin;
            if (this.mScrollHeight == i8) {
                return true;
            }
            this.mScrollHeight = i8;
            int measuredHeight = ViewUtils.getMeasuredHeight(this.mYearViewPool.peek());
            float thumbnailHeight = (((float) (fastScroller.getThumbnailHeight() - (measuredHeight * 2))) / 2.0f) + ((float) scrollStart);
            float f8 = (float) this.mTopMargin;
            float f10 = (float) i8;
            float f11 = (float) verticalThumbHeight;
            long j2 = currentTimeMillis;
            float f12 = f10 / (((((float) i7) - f8) - f11) - ((float) this.mBottomMargin));
            float f13 = f10 + thumbnailHeight;
            float f14 = (float) measuredHeight;
            float f15 = (f11 / 2.0f) - (f14 / 2.0f);
            Iterator<Pair<String, Integer>> it2 = dividerList.iterator();
            while (it2.hasNext()) {
                Pair next = it2.next();
                float f16 = f12;
                float f17 = f14;
                float max = (Math.max(0.0f, ((float) ((Integer) next.second).intValue()) - f8) * f16) + thumbnailHeight + f17;
                if (max < f13) {
                    it = it2;
                    f5 = f8;
                    f = thumbnailHeight;
                    this.mYearDataList.addFirst(new YearData(this.mYearViewPool.get(), (String) next.first, ((Integer) next.second).intValue(), max + f15));
                } else {
                    it = it2;
                    f5 = f8;
                    f = thumbnailHeight;
                }
                f12 = f16;
                f14 = f17;
                it2 = it;
                f8 = f5;
                thumbnailHeight = f;
            }
            StringBuilder h5 = a.h(itemCount, i7, "calculate {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            h5.append(this.mYearDataList.size());
            h5.append(",(");
            j.x(h5, this.mRecyclerViewHeight, GlobalPostProcInternalPPInterface.SPLIT_REGEX, i8, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            j.x(h5, scrollStart, GlobalPostProcInternalPPInterface.SPLIT_REGEX, measuredHeight, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            h5.append(verticalThumbHeight);
            h5.append(")} +");
            h5.append(System.currentTimeMillis() - j2);
            Log.d("YearScrollTag", h5.toString());
            return true;
        } catch (NullPointerException e) {
            Log.e("YearScrollTag", "calculate failed e=" + e.getMessage());
            removeAll();
            return false;
        }
    }

    public Animator createTagViewAlphaAnimator(boolean z) {
        float f;
        float f5 = 1.0f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f5 = 0.0f;
        }
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{f, f5}).setDuration(200);
        duration.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
        duration.addUpdateListener(new h(2, this));
        return duration;
    }

    public void destroy() {
        removeAll();
        this.mYearViewPool.destroy();
    }

    public void draw(int i2) {
        if (this.mYearDataList.size() > 0) {
            View view = this.mYearDataList.getFirst().getView();
            if (i2 == 0) {
                if (view.getVisibility() == 4) {
                    int positionX = getPositionX(view);
                    Iterator<YearData> it = this.mYearDataList.iterator();
                    float f = 2.14748365E9f;
                    while (it.hasNext()) {
                        YearData next = it.next();
                        View view2 = next.getView();
                        if (next.getY() + ((float) view2.getHeight()) + ((float) this.mMinimumGap) <= f) {
                            view2.setX((float) positionX);
                            view2.setVisibility(0);
                            f = next.getY();
                        }
                    }
                }
            } else if (view.getVisibility() == 0) {
                Iterator<YearData> it2 = this.mYearDataList.iterator();
                while (it2.hasNext()) {
                    it2.next().getView().setVisibility(4);
                }
            }
        }
    }

    public void resetScrollHeight() {
        this.mScrollHeight = 0;
    }
}
