package com.samsung.android.gallery.app.ui.menu.popmenu;

import android.view.Menu;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PopupMenuParams {
    private final MediaItem mFocusedItem;
    private final int mInputType;
    private final boolean mIsPickerMode;
    private final boolean mIsSelectionMode;
    private final String mLocationKey;
    private final Menu mMenu;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public MediaItem focusedItem;
        /* access modifiers changed from: private */
        public int inputType;
        /* access modifiers changed from: private */
        public boolean isPickerMode;
        /* access modifiers changed from: private */
        public boolean isSelectionMode;
        /* access modifiers changed from: private */
        public String locationKey;
        /* access modifiers changed from: private */
        public final Menu menu;

        public Builder(Menu menu2) {
            this.menu = menu2;
        }

        public PopupMenuParams build() {
            return new PopupMenuParams(this, 0);
        }

        public Builder setFocusedItem(MediaItem mediaItem) {
            this.focusedItem = mediaItem;
            return this;
        }

        public Builder setInputType(int i2) {
            this.inputType = i2;
            return this;
        }

        public Builder setIsPickerMode(boolean z) {
            this.isPickerMode = z;
            return this;
        }

        public Builder setIsSelectionMode(boolean z) {
            this.isSelectionMode = z;
            return this;
        }

        public Builder setLocationKey(String str) {
            this.locationKey = str;
            return this;
        }
    }

    public /* synthetic */ PopupMenuParams(Builder builder, int i2) {
        this(builder);
    }

    public MediaItem getFocusedItem() {
        return this.mFocusedItem;
    }

    public int getInputType() {
        return this.mInputType;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public boolean isPickerMode() {
        return this.mIsPickerMode;
    }

    public boolean isSelectionMode() {
        return this.mIsSelectionMode;
    }

    private PopupMenuParams(Builder builder) {
        this.mMenu = builder.menu;
        this.mFocusedItem = builder.focusedItem;
        this.mLocationKey = builder.locationKey;
        this.mInputType = builder.inputType;
        this.mIsPickerMode = builder.isPickerMode;
        this.mIsSelectionMode = builder.isSelectionMode;
    }
}
