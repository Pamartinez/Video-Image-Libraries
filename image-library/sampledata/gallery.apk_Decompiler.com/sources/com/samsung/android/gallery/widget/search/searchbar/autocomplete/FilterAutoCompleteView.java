package com.samsung.android.gallery.widget.search.searchbar.autocomplete;

import U5.b;
import android.app.Activity;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterAutoCompleteView {
    private FilterAutoCompleteAdapter mAdapter;
    protected final Blackboard mBlackboard;
    private RecyclerView mRecyclerView;

    public FilterAutoCompleteView(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private void initView() {
        View inflateContainer;
        this.mAdapter = createAdapter();
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity != null && (inflateContainer = inflateContainer(readActivity)) != null) {
            RecyclerView recyclerView = (RecyclerView) inflateContainer.findViewById(R$id.autocomplete_recycler_view);
            this.mRecyclerView = recyclerView;
            Optional.ofNullable(recyclerView).ifPresent(new b(6, this, readActivity));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(Activity activity, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(createLayoutManager(activity));
        recyclerView.setBackgroundColor(activity.getResources().getColor(getBackgroundColorResId(), activity.getTheme()));
        recyclerView.setAdapter(this.mAdapter);
    }

    public FilterAutoCompleteAdapter createAdapter() {
        return new FilterAutoCompleteAdapter(this.mBlackboard);
    }

    public LinearLayoutManager createLayoutManager(Activity activity) {
        return new LinearLayoutManager(activity);
    }

    public void dismiss() {
        ViewUtils.setVisibility(this.mRecyclerView, 8);
    }

    public int getBackgroundColorResId() {
        return R$color.default_background;
    }

    public View inflateContainer(Activity activity) {
        return ViewUtils.inflateViewStub(activity.findViewById(R$id.autocomplete_list_view_container));
    }

    public void onDataChanged(ArrayList<AutoCompleteItem> arrayList, String str) {
        if (this.mAdapter == null) {
            initView();
        }
        this.mAdapter.setData(arrayList, str);
        updateRecyclerView(!arrayList.isEmpty());
    }

    public void updateRecyclerView(boolean z) {
        ViewUtils.setVisibleOrGone(this.mRecyclerView, z);
    }
}
