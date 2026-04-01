package com.samsung.android.gallery.widget.map;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.SearchView;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationSearchView extends SearchView {
    public LocationSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        initBackButton();
        initSearchTextView();
        initLayout();
    }

    private void initBackButton() {
        seslGetUpButton().setVisibility(0);
    }

    private void initLayout() {
        ViewMarginUtils.setStartMargin(findViewById(R$id.search_plate), 0);
    }

    private void initSearchTextView() {
        Utils.setCustomTextCursor((AutoCompleteTextView) findViewById(R$id.search_src_text), R$drawable.search_view_cursor);
    }
}
