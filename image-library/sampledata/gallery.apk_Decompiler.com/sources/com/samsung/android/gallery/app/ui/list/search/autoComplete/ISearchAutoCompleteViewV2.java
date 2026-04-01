package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchAutoCompleteViewV2 extends IMvpBaseView {
    String getKeyword();

    void onAutoCompleteKeywordClick(AutoCompleteItem autoCompleteItem);

    void onAutoCompletePublished(ArrayList<AutoCompleteItem> arrayList);

    void onLoadAutoCompleteItems();
}
