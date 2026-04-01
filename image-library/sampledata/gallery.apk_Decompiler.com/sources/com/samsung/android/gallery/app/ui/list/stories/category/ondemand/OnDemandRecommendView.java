package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import D5.a;
import a6.h;
import a6.i;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandRecommendView<V extends IMvpBaseView> implements OnDemandFloatingView {
    private OnDemandFloatingItemAdapter mAdapter;
    private final Blackboard mBlackboard;
    private View mLayout;
    private OnDemandRecommendPresenter mPresenter;
    private View mRecommendContainer;
    private RecyclerView mRecommendListView;
    private final V mView;

    public OnDemandRecommendView(V v) {
        this.mView = v;
        this.mBlackboard = v.getBlackboard();
    }

    private void bindView(View view) {
        View findViewById = view.findViewById(R.id.floating_recommend_root_view);
        this.mLayout = findViewById;
        this.mRecommendContainer = findViewById.findViewById(R.id.recommend_view_container);
        RecyclerView recyclerView = (RecyclerView) this.mLayout.findViewById(R.id.floating_recommend_list);
        this.mRecommendListView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mLayout.getContext(), 1, false));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$0() {
        ViewUtils.setVisibleOrGone(this.mLayout, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setData$1(int i2, OnDemandFloatingItem onDemandFloatingItem) {
        onRecommendItemClick(onDemandFloatingItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startRecommendSpringAnimation$3() {
        for (int i2 = 0; i2 < this.mRecommendListView.getChildCount(); i2++) {
            AnimatorSet animatorSet = new AnimatorSet();
            View childAt = this.mRecommendListView.getChildAt(i2);
            ViewUtils.setVisibleOrInvisible(childAt, false);
            PropertyAnimator withStartAction = new TranslationAnimator(childAt).fromYRelative((float) (childAt.getHeight() * (this.mRecommendListView.getChildCount() - i2))).setDuration(StatusCodes.INPUT_MISSING).withStartAction(new a(childAt, 2));
            withStartAction.setStartDelay(180);
            withStartAction.setInterpolator(new OvershootInterpolator(1.2f));
            ViewUtils.setAlpha(childAt, 0.0f);
            PropertyAnimator duration = new AlphaAnimator(childAt).alpha(1.0f).setDuration(StatusCodes.INPUT_MISSING);
            duration.setStartDelay(180);
            duration.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SUGGEST_KEYWORD_FADE_IN));
            animatorSet.playTogether(new Animator[]{withStartAction, duration});
            animatorSet.start();
        }
    }

    private boolean shouldHistoryVisible() {
        OnDemandFloatingItemAdapter onDemandFloatingItemAdapter = this.mAdapter;
        if (onDemandFloatingItemAdapter == null || onDemandFloatingItemAdapter.isEmpty()) {
            return false;
        }
        return true;
    }

    private void updateRecommendVisibility() {
        int i2;
        View view = this.mRecommendContainer;
        if (shouldHistoryVisible()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public Context getContext() {
        return this.mView.getContext();
    }

    public void hide() {
        this.mLayout.animate().alpha(0.0f).setDuration(150).setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SUGGEST_KEYWORD_FADE_IN)).withEndAction(new h(this, 1)).start();
    }

    public void init(ViewGroup viewGroup) {
        bindView(viewGroup);
        this.mPresenter = new OnDemandRecommendPresenter(this.mView, this);
    }

    public void onDestroy() {
        OnDemandRecommendPresenter onDemandRecommendPresenter = this.mPresenter;
        if (onDemandRecommendPresenter != null) {
            onDemandRecommendPresenter.onDestroy();
        }
    }

    public void onRecommendItemClick(OnDemandFloatingItem onDemandFloatingItem) {
        OnDemandRecommendPresenter onDemandRecommendPresenter = this.mPresenter;
        if (onDemandRecommendPresenter != null) {
            onDemandRecommendPresenter.onRecommendItemClick(onDemandFloatingItem);
        }
    }

    public void setData(List<?> list) {
        if (this.mLayout.getContext() != null) {
            if (this.mAdapter == null) {
                OnDemandFloatingItemAdapter onDemandFloatingItemAdapter = new OnDemandFloatingItemAdapter();
                this.mAdapter = onDemandFloatingItemAdapter;
                onDemandFloatingItemAdapter.setOnItemClickListener(new i(this));
                this.mRecommendListView.setAdapter(this.mAdapter);
            }
            this.mAdapter.setData(list);
            this.mAdapter.notifyDataSetChanged();
            updateRecommendVisibility();
        }
    }

    public void show(boolean z) {
        updateRecommendVisibility();
        this.mLayout.setVisibility(0);
        this.mLayout.setAlpha(0.0f);
        this.mLayout.animate().alpha(1.0f).setDuration(300).setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SUGGEST_KEYWORD_FADE_IN)).start();
        if (z) {
            startRecommendSpringAnimation();
        }
    }

    public void startRecommendSpringAnimation() {
        this.mRecommendListView.post(new h(this, 0));
    }
}
