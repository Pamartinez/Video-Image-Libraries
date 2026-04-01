package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import B2.i;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompleteAdapter<V extends ISearchAutoCompleteView> extends BaseListViewAdapter<V> {
    private final View.OnTouchListener mOnTouchListener = new i(23, this);

    public SearchAutoCompleteAdapter(V v, String str) {
        super(v, str);
    }

    private void applyRoundCorner(ListViewHolder listViewHolder, int i2) {
        if (i2 == 0 && getItemCount() == 1) {
            listViewHolder.setRoundMode(15);
        } else if (i2 == 0) {
            listViewHolder.setRoundMode(3);
        } else if (i2 == getItemCount() - 1) {
            listViewHolder.setRoundMode(12);
        } else {
            listViewHolder.setRoundMode(0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.mBlackBoard.postEvent(EventMessage.obtain(8502));
        return false;
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ImageTitleViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_suggestion_keyword_layout, viewGroup, false), i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("updateSideMargin")) {
            int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen.search_key_suggest_item_icon_padding);
            ViewMarginUtils.setHorizontalMargin(listViewHolder.getImage(), dimensionPixelOffset);
            ViewMarginUtils.setMarginRelative(listViewHolder.getTitleView(), (Integer) null, (Integer) null, Integer.valueOf(dimensionPixelOffset), (Integer) null);
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        listViewHolder.itemView.setOnTouchListener(this.mOnTouchListener);
        TextViewUtils.highlightKeyword(getContext(), listViewHolder.getTitleView(), ((ISearchAutoCompleteView) this.mView).getKeyword());
        applyRoundCorner(listViewHolder, i2);
    }
}
