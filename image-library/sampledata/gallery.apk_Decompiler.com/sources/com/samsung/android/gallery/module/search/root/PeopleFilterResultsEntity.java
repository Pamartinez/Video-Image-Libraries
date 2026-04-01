package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.support.utils.Features;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleFilterResultsEntity extends FilterResultsEntity {
    protected boolean mIsOnlyThem;

    public PeopleFilterResultsEntity(String str, String str2) {
        super("", str2);
        this.mIsOnlyThem = false;
        addRawLabel(str);
    }

    public String getSelection() {
        if (this.mIsOnlyThem || this.mRawLabels.size() != 1) {
            return "face_count = ? ";
        }
        return super.getSelection();
    }

    public boolean isOnlyThem() {
        return this.mIsOnlyThem;
    }

    public boolean isPerson() {
        if (!Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY) || this.mIsOnlyThem) {
            return false;
        }
        return true;
    }

    public PeopleFilterResultsEntity(String str, ArrayList<String> arrayList) {
        this(str, arrayList.size());
    }

    public PeopleFilterResultsEntity(String str, int i2) {
        super(str, IntelligentSearchIndexFieldIcon.create("only_them", str));
        this.mIsOnlyThem = true;
        addRawLabel(String.valueOf(i2));
    }
}
