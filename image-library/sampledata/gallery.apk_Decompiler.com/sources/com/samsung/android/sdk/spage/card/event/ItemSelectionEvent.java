package com.samsung.android.sdk.spage.card.event;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ItemSelectionEvent extends Event {
    public static final String EVENT_ITEM_SELECTED = "SPAGE_ON_ITEM_SELECTED";
    private static final String EXTRA_SELECTED_ITEM = "selectedItem";
    private static final String EXTRA_SELECTED_ITEM_INDEX = "selectedItemIndex";
    public static final String TYPE = "ItemSelectionEvent";
    private String mSelectedItem;
    private int mSelectedItemIndex;

    public ItemSelectionEvent(String str, Bundle bundle) {
        super(str, bundle);
    }

    public String getSelectedItem() {
        return this.mSelectedItem;
    }

    public int getSelectedItemIndex() {
        return this.mSelectedItemIndex;
    }

    public void initialize(Bundle bundle) {
        this.mSelectedItemIndex = bundle.getInt(EXTRA_SELECTED_ITEM_INDEX, -1);
        this.mSelectedItem = bundle.getString(EXTRA_SELECTED_ITEM, "");
    }
}
