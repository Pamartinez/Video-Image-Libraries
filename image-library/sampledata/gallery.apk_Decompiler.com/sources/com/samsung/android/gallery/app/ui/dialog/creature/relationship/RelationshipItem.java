package com.samsung.android.gallery.app.ui.dialog.creature.relationship;

import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RelationshipItem {
    boolean mIsCustom;
    /* access modifiers changed from: package-private */
    public boolean mIsData = true;
    int mItemViewType;
    String mName;
    /* access modifiers changed from: package-private */
    public String mTitle;
    String mType;

    private RelationshipItem(String str, String str2, int i2) {
        this.mName = str;
        this.mType = str2;
        this.mItemViewType = i2;
        if (i2 == 1) {
            this.mIsCustom = true;
        } else if (i2 == 0) {
            this.mIsData = false;
        }
        this.mTitle = this.mIsCustom ? CustomRelationshipKeySet.getInstance().getTranslatedName(str) : str;
    }

    public static RelationshipItem create(String str, String str2) {
        return new RelationshipItem(str, str2, 2);
    }

    public static RelationshipItem createAddButton(String str) {
        return new RelationshipItem(str, "<manual_add_button>", 0);
    }

    public static RelationshipItem createCustom(String str) {
        return new RelationshipItem(str, C0212a.l("<custom>", str), 1);
    }
}
