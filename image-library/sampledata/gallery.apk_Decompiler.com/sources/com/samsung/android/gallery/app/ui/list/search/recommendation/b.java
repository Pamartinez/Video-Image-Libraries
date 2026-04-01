package com.samsung.android.gallery.app.ui.list.search.recommendation;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.recommendation.SuggestionKeywordAdapter;
import com.samsung.android.gallery.module.search.history.HistoryItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecyclerView.Adapter e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(RecyclerView.Adapter adapter, Object obj, int i2) {
        this.d = i2;
        this.e = adapter;
        this.f = obj;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                ((RecentlyHistoryListAdapter) this.e).lambda$setClickListeners$0((HistoryItem) this.f, view);
                return;
            default:
                ((SuggestionKeywordAdapter) this.e).lambda$onCreateViewHolder$0((SuggestionKeywordAdapter.ViewHolder) this.f, view);
                return;
        }
    }
}
