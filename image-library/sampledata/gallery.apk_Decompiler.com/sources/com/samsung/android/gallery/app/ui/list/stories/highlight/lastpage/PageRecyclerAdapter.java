package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageFactory;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageRecyclerAdapter extends RecyclerView.Adapter<PageHolder> {
    private final ArrayList<PageItem> mItems = new ArrayList<>();
    private final View mRootParent;
    private final IStoryHighlightView mView;

    public PageRecyclerAdapter(IStoryHighlightView iStoryHighlightView, View view) {
        this.mView = iStoryHighlightView;
        this.mRootParent = view;
        setHasStableIds(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setData$0(ArrayList arrayList) {
        ArrayList arrayList2;
        if (this.mItems.size() != arrayList.size()) {
            swapData(arrayList);
            notifyDataSetChanged();
            arrayList2 = null;
        } else {
            ArrayList arrayList3 = new ArrayList(this.mItems);
            swapData(arrayList);
            arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                PageItem pageItem = this.mItems.get(i2);
                if (!((PageItem) arrayList3.get(i2)).equalItems(pageItem)) {
                    arrayList2.add(i2 + "/" + pageItem.name());
                    notifyItemChanged(i2);
                }
            }
        }
        Log.d("PagePagerAdapter", "setData", Integer.valueOf(this.mItems.size()), arrayList2);
    }

    private void swapData(ArrayList<PageItem> arrayList) {
        this.mItems.clear();
        this.mItems.addAll(arrayList);
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public long getItemId(int i2) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return -1;
        }
        return (long) this.mItems.get(i2).getType();
    }

    public int getItemViewType(int i2) {
        if (this.mItems.isEmpty() || i2 < 0) {
            return -1;
        }
        return this.mItems.get(i2).getType();
    }

    public boolean isEmpty() {
        return this.mItems.isEmpty();
    }

    public void reset() {
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public void setData(ArrayList<PageItem> arrayList) {
        ThreadUtil.runOnUiThread(new e(2, this, arrayList));
    }

    public PageHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return PageFactory.createPage(this.mView, viewGroup, this.mRootParent, i2);
    }

    public void onViewRecycled(PageHolder pageHolder) {
        pageHolder.recycle();
    }

    public void onBindViewHolder(PageHolder pageHolder, int i2) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            Log.e((CharSequence) "PagePagerAdapter", "onBindViewHolder error due to out of range", Integer.valueOf(i2), Integer.valueOf(this.mItems.size()));
        } else {
            pageHolder.bind(this.mItems.get(i2));
        }
    }

    public void onBindViewHolder(PageHolder pageHolder, int i2, List<Object> list) {
        if (list.contains(Event.UPDATE_TITLE.toString())) {
            pageHolder.updateTitle();
        } else {
            super.onBindViewHolder(pageHolder, i2, list);
        }
    }
}
