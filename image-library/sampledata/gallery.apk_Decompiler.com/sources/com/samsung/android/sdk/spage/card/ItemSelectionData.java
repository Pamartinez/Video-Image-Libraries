package com.samsung.android.sdk.spage.card;

import com.samsung.android.sdk.spage.card.base.JsonFieldData;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ItemSelectionData extends JsonFieldData<ItemSelectionData> {
    private static final String KEY_ITEM_LIST = "itemList";
    private static final String KEY_SELECTED_ITEM = "selectedItem";

    public ItemSelectionData setItemList(int i2, List<String> list) {
        if (list == null || list.size() == 0 || list.contains((Object) null)) {
            throw new IllegalArgumentException("invalid itemList");
        } else if (i2 < list.size() && i2 >= 0) {
            return (ItemSelectionData) ((ItemSelectionData) put(KEY_ITEM_LIST, list)).put(KEY_SELECTED_ITEM, i2);
        } else {
            throw new IllegalArgumentException("selected item's selectedItemIndex is not valid");
        }
    }

    public ItemSelectionData setItemList(int i2, String... strArr) {
        if (strArr != null) {
            return setItemList(i2, (List<String>) Arrays.asList(strArr));
        }
        throw new IllegalArgumentException("items null");
    }
}
