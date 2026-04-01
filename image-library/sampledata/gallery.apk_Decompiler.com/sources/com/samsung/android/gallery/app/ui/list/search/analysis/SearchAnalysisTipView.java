package com.samsung.android.gallery.app.ui.list.search.analysis;

import B4.c;
import a6.C0419b;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.RoundedRelativeLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAnalysisTipView {
    private final Consumer<Boolean> mCallback;
    private final View mHeaderView;
    private boolean mIsButtonClicked;
    private boolean mIsCreature;
    private boolean mIsInitState;
    private RoundedRelativeLayout mTipCardLayout;
    private final IBaseListView mView;

    public SearchAnalysisTipView(IBaseListView iBaseListView, View view) {
        this(iBaseListView, view, (Consumer<Boolean>) null);
    }

    private void checkAndUpdateAnalysisTip() {
        SimpleThreadPool.getInstance().execute(new C0451a(23, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndUpdateAnalysisTip$1() {
        ThreadUtil.postOnUiThread(new c((Object) this, SearchAnalysisHelper.checkAnalysisTipCondition(this.mView.getLocationKey()), 22));
    }

    /* access modifiers changed from: private */
    public void onButtonClick(View view) {
        this.mIsButtonClicked = true;
        if (this.mIsCreature) {
            PreferenceCache.SearchCreatureAnalysisTipClosed.setBoolean(true);
        } else {
            PreferenceCache.SearchNormalAnalysisTipClosed.setBoolean(true);
        }
        ViewUtils.setVisibleOrGone(this.mTipCardLayout, false);
    }

    private void update() {
        this.mIsInitState = false;
        if (!PickerUtil.isPickerMode(this.mView.getBlackboard())) {
            if (!LocationKey.isSearchKeyword(this.mView.getLocationKey()) && (!LocationKey.isSearchCategoryCreature(this.mView.getLocationKey()) || !this.mIsCreature)) {
                return;
            }
            if (this.mIsCreature) {
                updateCreatureTip();
            } else {
                updateNormalTip();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateAnalysisTipVisibility */
    public void lambda$checkAndUpdateAnalysisTip$0(boolean z) {
        if (this.mIsCreature) {
            updateCreaturePref(z);
        } else {
            updateNormalPref(z);
        }
        ViewUtils.setVisibleOrGone(this.mTipCardLayout, z);
        Consumer<Boolean> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
    }

    private void updateCreaturePref(boolean z) {
        if (z) {
            PreferenceCache preferenceCache = PreferenceCache.SearchCreatureAnalysisTipTime;
            if (preferenceCache.getLong() == -1) {
                preferenceCache.setLong(System.currentTimeMillis());
            }
        } else if (PreferenceCache.SearchCreatureAnalysisTipTime.getLong() != -1) {
            PreferenceCache.SearchCreatureAnalysisTipClosed.setBoolean(true);
        }
    }

    private void updateCreatureTip() {
        if (PreferenceCache.SearchCreatureAnalysisTipClosed.getBoolean()) {
            Log.d("SearchAnalysisTipView", "skip update creature tip.");
            Consumer<Boolean> consumer = this.mCallback;
            if (consumer != null) {
                consumer.accept(Boolean.FALSE);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = PreferenceCache.SearchCreatureAnalysisTipTime.getLong();
        if (j2 == -1 || 604800000 + j2 >= currentTimeMillis) {
            checkAndUpdateAnalysisTip();
            return;
        }
        Log.d("SearchAnalysisTipView", "skip update creature tip.", Long.valueOf(j2), Long.valueOf(currentTimeMillis));
        Consumer<Boolean> consumer2 = this.mCallback;
        if (consumer2 != null) {
            consumer2.accept(Boolean.FALSE);
        }
    }

    private void updateNormalPref(boolean z) {
        if (z) {
            PreferenceCache preferenceCache = PreferenceCache.SearchNormalAnalysisTipTime;
            if (preferenceCache.getLong() == -1) {
                preferenceCache.setLong(System.currentTimeMillis());
            }
        } else if (PreferenceCache.SearchNormalAnalysisTipTime.getLong() != -1) {
            PreferenceCache.SearchNormalAnalysisTipClosed.setBoolean(true);
        }
    }

    private void updateNormalTip() {
        if (PreferenceCache.SearchNormalAnalysisTipClosed.getBoolean()) {
            Log.d("SearchAnalysisTipView", "skip update normal tip.");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = PreferenceCache.SearchNormalAnalysisTipTime.getLong();
        if (j2 == -1 || 604800000 + j2 >= currentTimeMillis) {
            checkAndUpdateAnalysisTip();
        } else {
            Log.d("SearchAnalysisTipView", "skip update normal tip.", Long.valueOf(j2), Long.valueOf(currentTimeMillis));
        }
    }

    public void dataChanged() {
        update();
    }

    public void init() {
        init(false);
    }

    public boolean isButtonClicked() {
        return this.mIsButtonClicked;
    }

    public boolean isInitState() {
        return this.mIsInitState;
    }

    public boolean isVisible() {
        return ViewUtils.isVisible(this.mTipCardLayout);
    }

    public void setEnable(boolean z) {
        ViewUtils.setAllViewEnabled(this.mTipCardLayout, z, true);
    }

    public SearchAnalysisTipView(IBaseListView iBaseListView, View view, Consumer<Boolean> consumer) {
        this.mIsInitState = true;
        this.mView = iBaseListView;
        this.mHeaderView = view;
        this.mCallback = consumer;
    }

    public void init(boolean z) {
        View view = this.mHeaderView;
        if (view != null) {
            this.mIsCreature = z;
            this.mTipCardLayout = (RoundedRelativeLayout) view.findViewById(R.id.tip_card_layout);
            ((TextView) this.mHeaderView.findViewById(R.id.headerContent)).setText(this.mIsCreature ? R.string.search_analysis_header_title_for_creature : R.string.search_analysis_header_title);
            TextView textView = (TextView) this.mHeaderView.findViewById(R.id.ok_button);
            SeApiCompat.setButtonShapeEnabled(textView);
            textView.setOnClickListener(new C0419b(15, this));
            ViewUtils.setVisibleOrGone(this.mTipCardLayout, false);
        }
    }
}
