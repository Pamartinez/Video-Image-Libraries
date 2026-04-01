package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ChapterFrame extends Id3Frame {
    public final String chapterId;
    public final long endOffset;
    public final int endTimeMs;
    public final long startOffset;
    public final int startTimeMs;
    private final Id3Frame[] subFrames;

    public ChapterFrame(String str, int i2, int i7, long j2, long j3, Id3Frame[] id3FrameArr) {
        super("CHAP");
        this.chapterId = str;
        this.startTimeMs = i2;
        this.endTimeMs = i7;
        this.startOffset = j2;
        this.endOffset = j3;
        this.subFrames = id3FrameArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ChapterFrame.class == obj.getClass()) {
            ChapterFrame chapterFrame = (ChapterFrame) obj;
            if (this.startTimeMs == chapterFrame.startTimeMs && this.endTimeMs == chapterFrame.endTimeMs && this.startOffset == chapterFrame.startOffset && this.endOffset == chapterFrame.endOffset && Objects.equals(this.chapterId, chapterFrame.chapterId) && Arrays.equals(this.subFrames, chapterFrame.subFrames)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7 = (((((((527 + this.startTimeMs) * 31) + this.endTimeMs) * 31) + ((int) this.startOffset)) * 31) + ((int) this.endOffset)) * 31;
        String str = this.chapterId;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        return i7 + i2;
    }
}
