package com.samsung.android.gallery.bixby.bixby.search;

import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAlbumData {
    private final int mAlbumId;
    private final String mAlbumName;
    private final String mAlbumPath;
    private final AlbumType mAlbumType;
    private final boolean mIsEmptyAlbum;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public int mAlbumId = 0;
        /* access modifiers changed from: private */
        public String mAlbumName = null;
        /* access modifiers changed from: private */
        public String mAlbumPath = null;
        /* access modifiers changed from: private */
        public AlbumType mAlbumType = AlbumType.None;
        /* access modifiers changed from: private */
        public boolean mIsEmptyAlbum = false;

        public SearchAlbumData build() {
            return new SearchAlbumData(this, 0);
        }

        public Builder setAlbumId(int i2) {
            this.mAlbumId = i2;
            return this;
        }

        public Builder setAlbumName(String str) {
            this.mAlbumName = str;
            return this;
        }

        public Builder setAlbumPath(String str) {
            this.mAlbumPath = str;
            return this;
        }

        public Builder setAlbumType(AlbumType albumType) {
            this.mAlbumType = albumType;
            return this;
        }

        public Builder setEmptyAlbum() {
            this.mIsEmptyAlbum = true;
            return this;
        }
    }

    public /* synthetic */ SearchAlbumData(Builder builder, int i2) {
        this(builder);
    }

    public int getAlbumId() {
        return this.mAlbumId;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public String getAlbumPath() {
        return this.mAlbumPath;
    }

    public AlbumType getAlbumType() {
        return this.mAlbumType;
    }

    public boolean isEmptyAlbum() {
        return this.mIsEmptyAlbum;
    }

    public String toString() {
        return "SearchAlbumData{" + this.mAlbumId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mAlbumName + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mAlbumType + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mIsEmptyAlbum + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.getEncodedString(this.mAlbumPath) + "}";
    }

    private SearchAlbumData(Builder builder) {
        this.mAlbumId = builder.mAlbumId;
        this.mAlbumName = builder.mAlbumName;
        this.mAlbumPath = builder.mAlbumPath;
        this.mAlbumType = builder.mAlbumType;
        this.mIsEmptyAlbum = builder.mIsEmptyAlbum;
    }
}
