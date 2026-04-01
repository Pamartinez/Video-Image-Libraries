package androidx.profileinstaller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class WritableFileSection {
    final byte[] mContents;
    final int mExpectedInflateSize;
    final boolean mNeedsCompression;
    final FileSectionType mType;

    public WritableFileSection(FileSectionType fileSectionType, int i2, byte[] bArr, boolean z) {
        this.mType = fileSectionType;
        this.mExpectedInflateSize = i2;
        this.mContents = bArr;
        this.mNeedsCompression = z;
    }
}
