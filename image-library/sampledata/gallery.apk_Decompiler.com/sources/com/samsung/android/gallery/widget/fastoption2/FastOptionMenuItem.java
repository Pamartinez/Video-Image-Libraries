package com.samsung.android.gallery.widget.fastoption2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionMenuItem {
    private final int mGroupId;
    private final int mMenuId;
    private final String mTitle;
    private final int mTitleResId;

    public FastOptionMenuItem(FastOptionItemParams fastOptionItemParams) {
        this.mTitleResId = fastOptionItemParams.getTitleRes();
        this.mMenuId = fastOptionItemParams.getMenuId();
        this.mTitle = fastOptionItemParams.getTitle();
        this.mGroupId = fastOptionItemParams.getGroupId();
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    public int getMenuId() {
        return this.mMenuId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getTitleResId() {
        return this.mTitleResId;
    }
}
