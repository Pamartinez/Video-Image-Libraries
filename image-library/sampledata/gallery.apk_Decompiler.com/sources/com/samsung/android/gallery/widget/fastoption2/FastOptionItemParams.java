package com.samsung.android.gallery.widget.fastoption2;

import android.graphics.drawable.Drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionItemParams {
    private final Drawable drawable;
    private final int groupId;
    private final boolean isDisabledDim;
    private final boolean isEnabledDim;
    private final boolean isFastOptionMenu;
    private final boolean isShowText;
    private final int menuId;
    private final String title;
    private final int titleRes;
    private final int widthRes;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public Drawable drawable;
        /* access modifiers changed from: private */
        public int groupId;
        /* access modifiers changed from: private */
        public boolean isDisabledDim;
        /* access modifiers changed from: private */
        public boolean isEnabledDim;
        /* access modifiers changed from: private */
        public boolean isFastOptionMenu;
        /* access modifiers changed from: private */
        public boolean isShowText;
        /* access modifiers changed from: private */
        public int menuId;
        /* access modifiers changed from: private */
        public String title;
        /* access modifiers changed from: private */
        public int titleRes;
        /* access modifiers changed from: private */
        public int widthRes;

        public /* synthetic */ Builder(int i2) {
            this();
        }

        public FastOptionItemParams build() {
            return new FastOptionItemParams(this, 0);
        }

        public Builder setDisabledDim(boolean z) {
            this.isDisabledDim = z;
            return this;
        }

        public Builder setDrawable(Drawable drawable2) {
            this.drawable = drawable2;
            return this;
        }

        public Builder setEnabledDim(boolean z) {
            this.isEnabledDim = z;
            return this;
        }

        public Builder setFastOptionMenu(boolean z) {
            this.isFastOptionMenu = z;
            return this;
        }

        public Builder setGroupId(int i2) {
            this.groupId = i2;
            return this;
        }

        public Builder setMenuId(int i2) {
            this.menuId = i2;
            return this;
        }

        public Builder setShowText(boolean z) {
            this.isShowText = z;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setTitleRes(int i2) {
            this.titleRes = i2;
            return this;
        }

        public Builder setWidthRes(int i2) {
            this.widthRes = i2;
            return this;
        }

        private Builder() {
            this.isFastOptionMenu = false;
            this.isDisabledDim = false;
            this.isEnabledDim = false;
            this.isShowText = false;
            this.title = null;
            this.widthRes = -1;
        }
    }

    public /* synthetic */ FastOptionItemParams(Builder builder, int i2) {
        this(builder);
    }

    public static Builder builder() {
        return new Builder(0);
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public int getMenuId() {
        return this.menuId;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public int getWidthRes() {
        return this.widthRes;
    }

    public boolean isDisabledDim() {
        return this.isDisabledDim;
    }

    public boolean isEnabledDim() {
        return this.isEnabledDim;
    }

    public boolean isFastOptionMenu() {
        return this.isFastOptionMenu;
    }

    public boolean isShowText() {
        return this.isShowText;
    }

    private FastOptionItemParams(Builder builder) {
        this.isFastOptionMenu = builder.isFastOptionMenu;
        this.isDisabledDim = builder.isDisabledDim;
        this.isEnabledDim = builder.isEnabledDim;
        this.isShowText = builder.isShowText;
        this.groupId = builder.groupId;
        this.menuId = builder.menuId;
        this.titleRes = builder.titleRes;
        this.drawable = builder.drawable;
        this.title = builder.title;
        this.widthRes = builder.widthRes;
    }
}
