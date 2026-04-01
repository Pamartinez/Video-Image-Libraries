package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmListAdapter;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import j6.C0472a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmListView {
    private BgmListAdapter mAdapter;
    private int mExpandOffset;
    private View mHandlerView;
    RecyclerView mRecyclerView;
    private View mTipCard;
    private final IStoryHighlightView mView;

    public BgmListView(IStoryHighlightView iStoryHighlightView, View view, EffectTheme effectTheme, BgmListAdapter.Listener listener) {
        this.mView = iStoryHighlightView;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bgm_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        BgmListAdapter bgmListAdapter = new BgmListAdapter(listener);
        this.mAdapter = bgmListAdapter;
        bgmListAdapter.setTheme(effectTheme);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mHandlerView = view.findViewById(R.id.handler);
        this.mTipCard = view.findViewById(R.id.tip_card);
    }

    private int getListHeight() {
        return (((this.mRecyclerView.getRootView().getHeight() - WindowUtils.getSystemInsetsTop(this.mRecyclerView.getRootWindowInsets())) - WindowUtils.getSystemInsetsBottom(this.mRecyclerView.getRootWindowInsets())) - this.mExpandOffset) - this.mRecyclerView.getResources().getDimensionPixelOffset(R.dimen.stories_theme_list_height);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeight$0(ViewGroup.LayoutParams layoutParams) {
        layoutParams.height = (getListHeight() - this.mHandlerView.getMeasuredHeight()) - this.mTipCard.getMeasuredHeight();
        this.mRecyclerView.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeight$1(ViewGroup.LayoutParams layoutParams) {
        if (ViewUtils.isVisible(this.mTipCard)) {
            this.mTipCard.post(new C0472a(this, layoutParams, 1));
            return;
        }
        layoutParams.height = getListHeight() - this.mHandlerView.getMeasuredHeight();
        this.mRecyclerView.setLayoutParams(layoutParams);
    }

    public String getFocusedBgm() {
        BgmListAdapter bgmListAdapter = this.mAdapter;
        if (bgmListAdapter != null) {
            return bgmListAdapter.getFocusedBgm();
        }
        return "";
    }

    public void notifyItemChanged(String str) {
        this.mAdapter.notifyItemChanged(str);
    }

    public void onSelectTheme(EffectTheme effectTheme) {
        this.mAdapter.setTheme(effectTheme);
        this.mRecyclerView.scrollToPosition(Math.max(this.mAdapter.getFocusedIndex(), 0));
    }

    public void requestReplaceBgm(String str) {
        if (!str.equals(this.mView.getEventHandler().requestData(DataRequest.REQ_BGM_NAME))) {
            this.mAdapter.setFocus(str);
            BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
            bgmExtraInfo.bgmName = str;
            this.mView.getEventHandler().lambda$postEvent$0(Event.CHANGE_BGM, bgmExtraInfo);
            Log.d("BgmListView", "requestReplaceBgm=" + bgmExtraInfo);
        }
    }

    public void setExpandOffset(int i2) {
        this.mExpandOffset = i2;
    }

    public void setFocus(String str) {
        this.mAdapter.setFocus(str);
    }

    public void updateHeight(boolean z) {
        long j2;
        ViewGroup.LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
        View view = this.mHandlerView;
        C0472a aVar = new C0472a(this, layoutParams, 0);
        if (z) {
            j2 = 100;
        } else {
            j2 = 0;
        }
        view.postDelayed(aVar, j2);
    }
}
