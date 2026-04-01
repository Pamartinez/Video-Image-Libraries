package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.view.View;
import com.samsung.android.gallery.widget.NoItemView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchSuggestionView {
    View getEmptyView();

    NoItemView getNoItemView();

    Object getSuggesterData() {
        return null;
    }

    SuggesterType getSuggesterType();

    boolean isRelationshipEnabled(String str);
}
