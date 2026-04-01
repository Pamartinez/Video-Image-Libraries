package com.samsung.android.gallery.app.ui.list.suggestions;

import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.ISuggestionsView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SuggestionsViewAdapter<V extends ISuggestionsView> extends BaseListViewAdapter<V> {
    public SuggestionsViewAdapter(V v, String str) {
        super(v, str);
    }

    private int getIntelligenceCount() {
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        int i2 = 0;
        if (allData != null) {
            Iterator<MediaItem> it = allData.iterator();
            while (it.hasNext()) {
                if (MediaItemSuggest.isIntelligentGroup(it.next())) {
                    i2++;
                }
            }
        }
        return i2;
    }

    private boolean isFirstItem(int i2, int i7) {
        if ((!PreferenceFeatures.OneUi41.SHOW_SUGGESTION_HEADER || i2 - i7 != 0) && i2 != 0) {
            return false;
        }
        return true;
    }

    private boolean isLastItem(int i2, int i7) {
        if ((!PreferenceFeatures.OneUi41.SHOW_SUGGESTION_HEADER || i2 != i7 - 1) && i2 != getItemCount() - 1) {
            return false;
        }
        return true;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.XLARGE_NC_KIND;
    }

    public void hideNewBadge(int i2) {
        notifyItemChanged(i2, "hide_new_badge");
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new SuggestionsViewHolder(C0086a.d(viewGroup, R.layout.recycler_card_suggestions_layout, viewGroup, false), i2);
    }

    public boolean supportVideoPreview() {
        return false;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        SuggestionsViewHolder suggestionsViewHolder = (SuggestionsViewHolder) listViewHolder;
        suggestionsViewHolder.bind(this.mMediaData.getAllData().get(i2));
        int intelligenceCount = getIntelligenceCount();
        boolean isFirstItem = isFirstItem(i2, intelligenceCount);
        boolean isLastItem = isLastItem(i2, intelligenceCount);
        boolean z = false;
        if (isFirstItem && isLastItem) {
            suggestionsViewHolder.setRoundMode(15);
        } else if (isFirstItem) {
            suggestionsViewHolder.setRoundMode(3);
        } else if (isLastItem) {
            suggestionsViewHolder.setRoundMode(12);
        } else {
            suggestionsViewHolder.setRoundMode(0);
        }
        if (PreferenceFeatures.OneUi41.SHOW_SUGGESTION_HEADER) {
            suggestionsViewHolder.setGroupHeaderVisibility(isFirstItem);
            if (isLastItem && i2 != getItemCount() - 1) {
                z = true;
            }
            suggestionsViewHolder.updateBottomMargin(z);
        } else if (PreferenceFeatures.OneUi7x.IS_ONE_UI_70) {
            suggestionsViewHolder.updateTopPadding(isFirstItem);
        } else {
            suggestionsViewHolder.setDividerVisibility(!isLastItem);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("onConfigurationChanged")) {
            ((SuggestionsViewHolder) listViewHolder).onConfigurationChanged();
        } else if (list.contains("hide_new_badge")) {
            SuggestionsViewHolder suggestionsViewHolder = (SuggestionsViewHolder) listViewHolder;
            if (suggestionsViewHolder.getNewBadgeVisibility() == 0) {
                MediaItem mediaItemSync = getMediaItemSync(i2);
                if (MediaItemSuggest.isRemaster(mediaItemSync)) {
                    ((ISuggestionsView) this.mView).updateAutoItemStatus(mediaItemSync);
                }
                suggestionsViewHolder.hideNewBadge();
            }
        } else if (list.contains("update_new_badge")) {
            ((SuggestionsViewHolder) listViewHolder).updateNewBadgeVisibility(getMediaItemSync(i2));
            return;
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }
}
