package com.samsung.android.gallery.module.search.autocomplete;

import android.database.Cursor;
import com.samsung.android.gallery.support.type.SubCategoryType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompleteItemComposer {
    final HashSet<String> titleKeySet = new HashSet<>();

    public AutoCompleteItem read(Cursor cursor) {
        AutoCompleteItem autoCompleteItem = new AutoCompleteItem(cursor);
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            if (this.titleKeySet.contains(autoCompleteItem.getTranslatedKeyword().toLowerCase())) {
                return null;
            }
            this.titleKeySet.add(autoCompleteItem.getTranslatedKeyword().toLowerCase());
            return autoCompleteItem;
        } else if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE_UNIFIED)) {
            return autoCompleteItem;
        } else {
            ArrayList<String> documentSubGroupList = SubCategoryType.getDocumentSubGroupList();
            documentSubGroupList.remove(SubCategoryType.getOtherDocumentsName());
            if (documentSubGroupList.contains(autoCompleteItem.getKeyword()) || this.titleKeySet.contains(autoCompleteItem.getTranslatedKeyword().toLowerCase())) {
                return null;
            }
            this.titleKeySet.add(autoCompleteItem.getTranslatedKeyword().toLowerCase());
            return autoCompleteItem;
        }
    }

    public ArrayList<AutoCompleteItem> readAll(Cursor cursor) {
        ArrayList<AutoCompleteItem> arrayList = new ArrayList<>();
        if (cursor == null || !cursor.moveToFirst()) {
            return arrayList;
        }
        do {
            AutoCompleteItem read = read(cursor);
            if (read != null) {
                arrayList.add(read);
            }
        } while (cursor.moveToNext());
        return arrayList;
    }
}
