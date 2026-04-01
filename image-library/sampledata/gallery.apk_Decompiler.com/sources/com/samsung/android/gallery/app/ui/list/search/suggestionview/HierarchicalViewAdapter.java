package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import A.a;
import I5.d;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HierarchicalViewAdapter extends GalleryListAdapter<HierarchicalViewHolder> {
    private static final int ITEM_LAYOUT_ID;
    private final Object LOCK = new Object();
    private final List<HierarchicalItem> mList = new ArrayList();
    private final IHierarchicalView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HierarchicalItem {
        private final String mKeyword;
        private final String mTitle;

        public HierarchicalItem(String str, String str2) {
            this.mKeyword = str;
            this.mTitle = str2;
        }

        public String getKeyword() {
            return this.mKeyword;
        }

        public String getTitle() {
            return this.mTitle;
        }
    }

    static {
        int i2;
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
            i2 = R.layout.recycler_item_hierarchical_keyword_layout_oneui8;
        } else {
            i2 = R.layout.recycler_item_hierarchical_keyword_layout;
        }
        ITEM_LAYOUT_ID = i2;
    }

    public HierarchicalViewAdapter(IHierarchicalView iHierarchicalView) {
        super(iHierarchicalView.getBlackboard());
        this.mView = iHierarchicalView;
    }

    private HierarchicalViewHolder createViewHolderInternal(View view) {
        HierarchicalViewHolder hierarchicalViewHolder = new HierarchicalViewHolder(view);
        hierarchicalViewHolder.itemView.setOnClickListener(new c(this, hierarchicalViewHolder));
        return hierarchicalViewHolder;
    }

    private boolean fromInstantSearch(String str) {
        return ArgumentsUtil.getArgValue(str, "from_instant_search", false);
    }

    private boolean hasSelectedFilter(String str) {
        return !TextUtils.isEmpty(ArgumentsUtil.getArgValue(str, "SelectedFilter", (String) null));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createViewHolderInternal$0(HierarchicalViewHolder hierarchicalViewHolder, View view) {
        synchronized (this.LOCK) {
            try {
                if (hierarchicalViewHolder.getViewPosition() < this.mList.size()) {
                    onItemClicked(this.mList.get(hierarchicalViewHolder.getViewPosition()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemClicked$3(HierarchicalItem hierarchicalItem) {
        Blackboard blackboard = this.mBlackBoard;
        if (blackboard != null) {
            String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(blackboard);
            if (hasSelectedFilter(readLocationKeyCurrent)) {
                String title = hierarchicalItem.getTitle();
                FilterResultsEntity filterResultsEntity = new FilterResultsEntity(title, "key_word");
                filterResultsEntity.addRawLabel(title);
                if (fromInstantSearch(readLocationKeyCurrent)) {
                    this.mBlackBoard.postEvent(EventMessage.obtain(8016, filterResultsEntity));
                } else {
                    this.mBlackBoard.postEvent(EventMessage.obtain(8517, filterResultsEntity));
                }
            } else {
                String keyword = hierarchicalItem.getKeyword();
                String title2 = hierarchicalItem.getTitle();
                this.mBlackBoard.postEvent(EventMessage.obtain(1066, new UriBuilder(readLocationKeyCurrent).appendArg("sub", title2).appendArg("title", title2).appendArg("term", "key_word").appendArg("collect_keyword", keyword).appendArg("collect_type", SearchWordCollector.Type.HIERARCHICAL_SUGGESTION.toString()).build()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$2(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mList.add(new HierarchicalItem(str, str));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setData$1(IHierarchicalView iHierarchicalView) {
        iHierarchicalView.onDataLoaded(this.mList.isEmpty());
    }

    private void onItemClicked(HierarchicalItem hierarchicalItem) {
        Optional.ofNullable(hierarchicalItem).ifPresent(new d(this));
    }

    private void parse(Object obj) {
        try {
            if (Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST)) {
                ((ArrayList) obj).forEach(new d(this, 1));
                return;
            }
            for (Map.Entry entry : ((HashMap) obj).entrySet()) {
                this.mList.add(new HierarchicalItem((String) entry.getKey(), (String) entry.getValue()));
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("parse failed. : "), this.TAG);
        }
    }

    public int getItemCount() {
        int min;
        synchronized (this.LOCK) {
            min = Math.min(this.mList.size(), 10);
        }
        return min;
    }

    public void setData(Object obj) {
        synchronized (this.LOCK) {
            this.mList.clear();
            parse(obj);
        }
        Optional.ofNullable(this.mView).ifPresent(new d(this, 0));
    }

    public HierarchicalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return createViewHolderInternal(LayoutInflater.from(viewGroup.getContext()).inflate(ITEM_LAYOUT_ID, viewGroup, false));
    }

    public void onBindViewHolder(HierarchicalViewHolder hierarchicalViewHolder, int i2) {
        synchronized (this.LOCK) {
            super.onBindViewHolder(hierarchicalViewHolder, i2);
            hierarchicalViewHolder.setTitle(this.mList.get(i2).getTitle());
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
