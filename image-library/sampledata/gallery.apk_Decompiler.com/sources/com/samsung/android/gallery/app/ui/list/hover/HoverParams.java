package com.samsung.android.gallery.app.ui.list.hover;

import O3.o;
import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HoverParams {
    private final ViewGroup mContainer;
    private final int mDataPosition;
    private final boolean mIsAlbum;
    private final boolean mIsHideOption;
    private final MediaItem mItem;
    private final String mLocationKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HoverParamsBuilder {
        /* access modifiers changed from: private */
        public ViewGroup container;
        /* access modifiers changed from: private */
        public int dataPosition;
        /* access modifiers changed from: private */
        public boolean isAlbum;
        /* access modifiers changed from: private */
        public boolean isHideOption;
        /* access modifiers changed from: private */
        public MediaItem item;
        /* access modifiers changed from: private */
        public final String locationKey;

        public HoverParamsBuilder(String str) {
            this.locationKey = str;
        }

        public HoverParams build() {
            return new HoverParams(this, 0);
        }

        public HoverParamsBuilder setAlbumType(boolean z) {
            this.isAlbum = z;
            return this;
        }

        public HoverParamsBuilder setContainer(ViewGroup viewGroup) {
            this.container = viewGroup;
            return this;
        }

        public HoverParamsBuilder setDataPosition(int i2) {
            this.dataPosition = i2;
            return this;
        }

        public HoverParamsBuilder setHideOption(boolean z) {
            this.isHideOption = z;
            return this;
        }

        public HoverParamsBuilder setItem(MediaItem mediaItem) {
            this.item = mediaItem;
            return this;
        }
    }

    public /* synthetic */ HoverParams(HoverParamsBuilder hoverParamsBuilder, int i2) {
        this(hoverParamsBuilder);
    }

    public ViewGroup getContainer() {
        return this.mContainer;
    }

    public Context getContext() {
        return (Context) Optional.ofNullable(this.mContainer).map(new o(28)).orElse((Object) null);
    }

    public int getDataPosition() {
        return this.mDataPosition;
    }

    public MediaItem getItem() {
        return this.mItem;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public boolean isAlbum() {
        return this.mIsAlbum;
    }

    public boolean isHideOption() {
        return this.mIsHideOption;
    }

    private HoverParams(HoverParamsBuilder hoverParamsBuilder) {
        this.mContainer = hoverParamsBuilder.container;
        this.mItem = hoverParamsBuilder.item;
        this.mDataPosition = hoverParamsBuilder.dataPosition;
        this.mIsAlbum = hoverParamsBuilder.isAlbum;
        this.mIsHideOption = hoverParamsBuilder.isHideOption;
        this.mLocationKey = hoverParamsBuilder.locationKey;
    }
}
