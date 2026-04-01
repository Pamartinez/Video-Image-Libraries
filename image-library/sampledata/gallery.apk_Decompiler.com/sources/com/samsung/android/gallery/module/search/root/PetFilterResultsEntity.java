package com.samsung.android.gallery.module.search.root;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PetFilterResultsEntity extends PeopleFilterResultsEntity {
    public PetFilterResultsEntity(String str, String str2) {
        super(str, str2);
    }

    public boolean isPerson() {
        return false;
    }

    public boolean isPet() {
        return !this.mIsOnlyThem;
    }
}
