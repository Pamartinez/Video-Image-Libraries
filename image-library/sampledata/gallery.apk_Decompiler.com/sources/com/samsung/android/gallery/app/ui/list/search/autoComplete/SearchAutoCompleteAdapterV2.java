package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import B2.i;
import Ba.g;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteViewV2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.sec.android.gallery3d.R;
import e6.C0453a;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompleteAdapterV2<V extends ISearchAutoCompleteViewV2> extends GalleryListAdapter<AutoCompleteListHolder> {
    private static final int ITEM_LAYOUT_ID = getItemLayoutId();
    private final LinkedList<AutoCompleteItem> mList = new LinkedList<>();
    private final View.OnTouchListener mOnTouchListener = new i(24, this);
    private final V mView;

    public SearchAutoCompleteAdapterV2(V v) {
        super(v.getBlackboard());
        this.mView = v;
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

    private AutoCompleteListHolder createViewHolderInternal(View view) {
        AutoCompleteListHolder autoCompleteListHolder = new AutoCompleteListHolder(view);
        autoCompleteListHolder.itemView.setOnClickListener(new g(22, this, autoCompleteListHolder));
        return autoCompleteListHolder;
    }

    private static int getItemLayoutId() {
        return R.layout.recycler_item_suggestion_keyword_layout_v2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createViewHolderInternal$0(AutoCompleteListHolder autoCompleteListHolder, View view) {
        synchronized (this.mList) {
            try {
                int adapterPosition = autoCompleteListHolder.getAdapterPosition();
                if (adapterPosition != -1 && !this.mList.isEmpty()) {
                    this.mView.onAutoCompleteKeywordClick(this.mList.get(adapterPosition));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.mBlackBoard.postEvent(EventMessage.obtain(8502));
        return false;
    }

    private boolean showDivider(int i2) {
        if (getItemCount() - 1 == i2 || getItemCount() == 1) {
            return false;
        }
        return true;
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mList) {
            isEmpty = this.mList.isEmpty();
        }
        return isEmpty;
    }

    public void setData(Object obj) {
        synchronized (this.mList) {
            this.mList.clear();
            this.mList.addAll((Collection) obj);
        }
        Optional.ofNullable(this.mView).ifPresent(new C0453a(24));
        notifyDataSetChanged();
    }

    public AutoCompleteListHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(ITEM_LAYOUT_ID, viewGroup, false);
        inflate.setOnTouchListener(this.mOnTouchListener);
        return createViewHolderInternal(inflate);
    }

    public void onBindViewHolder(AutoCompleteListHolder autoCompleteListHolder, int i2) {
        synchronized (this.mList) {
            super.onBindViewHolder(autoCompleteListHolder, i2);
            AutoCompleteItem autoCompleteItem = this.mList.get(i2);
            autoCompleteListHolder.setIcon(autoCompleteItem.getDrawable(autoCompleteListHolder.itemView.getContext()));
            autoCompleteListHolder.setTitle(autoCompleteItem.getTranslatedKeyword());
            autoCompleteListHolder.itemView.setOnTouchListener(this.mOnTouchListener);
            autoCompleteListHolder.setDividerVisible(showDivider(i2));
            TextViewUtils.highlightKeyword(getContext(), autoCompleteListHolder.getTitleView(), this.mView.getKeyword());
            applyRoundCorner(autoCompleteListHolder, i2);
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
