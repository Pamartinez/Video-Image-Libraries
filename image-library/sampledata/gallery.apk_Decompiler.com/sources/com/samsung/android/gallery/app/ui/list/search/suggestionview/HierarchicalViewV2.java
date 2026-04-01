package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import H3.l;
import I4.b;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HierarchicalViewV2 extends SuggesterView implements IHierarchicalView {
    private HierarchicalViewAdapter mAdapter;

    public HierarchicalViewV2(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    private void inflate() {
        if (this.mSuggesterLayout != null) {
            return;
        }
        if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM) {
            this.mSuggesterLayout = (LinearLayout) LayoutInflater.from(this.mEventContext.getContext()).inflate(R.layout.hierarchical_suggestion_flexbox_layout, (ViewGroup) null, false);
        } else {
            this.mSuggesterLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.hierarchical_suggestion_layout_v2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEmptyViewPadding$0() {
        super.updateEmptyViewPadding();
    }

    public void bind() {
        if (this.mEventContext.getContext() != null) {
            inflate();
            FlexBoxListView flexBoxListView = (FlexBoxListView) this.mSuggesterLayout.findViewById(R.id.flex_box_list);
            flexBoxListView.setLayoutManager(new FlexboxLayoutManager(this.mEventContext.getApplicationContext()));
            flexBoxListView.setAdapter(this.mAdapter);
            if (this.mAdapter.getItemCount() > 0) {
                show();
            }
            Consumer<Boolean> consumer = this.mOnBindCompleted;
            if (consumer != null) {
                consumer.accept(Boolean.TRUE);
            }
        }
    }

    public Blackboard getBlackboard() {
        return this.mEventContext.getBlackboard();
    }

    public boolean needUpdatePadding() {
        if (SuggesterView.USE_SUGGESTER_VIEW_IN_CUSTOM_NO_ITEM || !super.needUpdatePadding()) {
            return false;
        }
        return true;
    }

    public void onDataLoaded(boolean z) {
        if (z) {
            hide();
        } else {
            show();
        }
    }

    public void refresh() {
        Optional.ofNullable(this.mAdapter).ifPresent(new b(4));
    }

    public void setData(Object obj) {
        if (this.mEventContext.getContext() != null) {
            if (this.mAdapter == null) {
                this.mAdapter = new HierarchicalViewAdapter(this);
            }
            this.mAdapter.setData(obj);
        }
    }

    public void updateEmptyViewPadding() {
        LinearLayout linearLayout = this.mSuggesterLayout;
        if (linearLayout != null) {
            linearLayout.post(new l(9, this));
        }
    }
}
