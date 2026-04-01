package com.samsung.android.gallery.module.service.message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOperationMsgParams {
    private final String mAlbumPath;
    private final int mFailCount;
    private final int mImageCount;
    private final boolean mIsCopy;
    private final boolean mIsMove;
    private final boolean mIsRename;
    private final int mOperateCount;
    private final int mSuccessCount;
    private final int mTotalCount;
    private final int mVideoCount;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileOperationMsgParamsBuilder {
        /* access modifiers changed from: private */
        public String mAlbumPath;
        /* access modifiers changed from: private */
        public int mFailCount;
        /* access modifiers changed from: private */
        public int mImageCount;
        /* access modifiers changed from: private */
        public boolean mIsCopy;
        /* access modifiers changed from: private */
        public boolean mIsMove;
        /* access modifiers changed from: private */
        public boolean mIsRename;
        /* access modifiers changed from: private */
        public int mOperateCount;
        /* access modifiers changed from: private */
        public int mSuccessCount;
        /* access modifiers changed from: private */
        public int mTotalCount;
        /* access modifiers changed from: private */
        public int mVideoCount;

        public /* synthetic */ FileOperationMsgParamsBuilder(int i2) {
            this();
        }

        public FileOperationMsgParams build() {
            return new FileOperationMsgParams(this, 0);
        }

        public FileOperationMsgParamsBuilder setAlbumPath(String str) {
            this.mAlbumPath = str;
            return this;
        }

        public FileOperationMsgParamsBuilder setFailCount(int i2) {
            this.mFailCount = i2;
            return this;
        }

        public FileOperationMsgParamsBuilder setImageCount(int i2) {
            this.mImageCount = i2;
            return this;
        }

        public FileOperationMsgParamsBuilder setIsCopy(boolean z) {
            this.mIsCopy = z;
            return this;
        }

        public FileOperationMsgParamsBuilder setIsMove(boolean z) {
            this.mIsMove = z;
            return this;
        }

        public FileOperationMsgParamsBuilder setIsRename(boolean z) {
            this.mIsRename = z;
            return this;
        }

        public FileOperationMsgParamsBuilder setOperateCount(int i2) {
            this.mOperateCount = i2;
            return this;
        }

        public FileOperationMsgParamsBuilder setSuccessCount(int i2) {
            this.mSuccessCount = i2;
            return this;
        }

        public FileOperationMsgParamsBuilder setTotalCount(int i2) {
            this.mTotalCount = i2;
            return this;
        }

        public FileOperationMsgParamsBuilder setVideoCount(int i2) {
            this.mVideoCount = i2;
            return this;
        }

        private FileOperationMsgParamsBuilder() {
            this.mIsCopy = false;
            this.mIsMove = false;
            this.mIsRename = false;
            this.mImageCount = 0;
            this.mVideoCount = 0;
            this.mOperateCount = 0;
            this.mSuccessCount = 0;
            this.mFailCount = 0;
            this.mTotalCount = 0;
            this.mAlbumPath = null;
        }
    }

    public /* synthetic */ FileOperationMsgParams(FileOperationMsgParamsBuilder fileOperationMsgParamsBuilder, int i2) {
        this(fileOperationMsgParamsBuilder);
    }

    public static FileOperationMsgParamsBuilder builder() {
        return new FileOperationMsgParamsBuilder(0);
    }

    public String getAlbumPath() {
        return this.mAlbumPath;
    }

    public int getFailCount() {
        return this.mFailCount;
    }

    public int getImageCount() {
        return this.mImageCount;
    }

    public int getOperateCount() {
        return this.mOperateCount;
    }

    public int getSuccessCount() {
        return this.mSuccessCount;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public int getVideoCount() {
        return this.mVideoCount;
    }

    public boolean isCopy() {
        return this.mIsCopy;
    }

    public boolean isMove() {
        return this.mIsMove;
    }

    public boolean isRename() {
        return this.mIsRename;
    }

    private FileOperationMsgParams(FileOperationMsgParamsBuilder fileOperationMsgParamsBuilder) {
        this.mIsCopy = fileOperationMsgParamsBuilder.mIsCopy;
        this.mIsMove = fileOperationMsgParamsBuilder.mIsMove;
        this.mIsRename = fileOperationMsgParamsBuilder.mIsRename;
        this.mImageCount = fileOperationMsgParamsBuilder.mImageCount;
        this.mVideoCount = fileOperationMsgParamsBuilder.mVideoCount;
        this.mOperateCount = fileOperationMsgParamsBuilder.mOperateCount;
        this.mSuccessCount = fileOperationMsgParamsBuilder.mSuccessCount;
        this.mFailCount = fileOperationMsgParamsBuilder.mFailCount;
        this.mTotalCount = fileOperationMsgParamsBuilder.mTotalCount;
        this.mAlbumPath = fileOperationMsgParamsBuilder.mAlbumPath;
    }
}
