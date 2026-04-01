package com.samsung.android.vexfwk.param;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\u0018\u00002\u00020\u0001:\u0001)B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003BQ\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\r\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R\u001a\u0010\u000e\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014R\u001a\u0010\u000f\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0012\"\u0004\b(\u0010\u0014¨\u0006*"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo;", "", "<init>", "()V", "left", "", "top", "right", "bottom", "score", "", "tag", "Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag;", "height", "width", "trackId", "(IIIIFLcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag;III)V", "getLeft", "()I", "setLeft", "(I)V", "getTop", "setTop", "getRight", "setRight", "getBottom", "setBottom", "getScore", "()F", "setScore", "(F)V", "getTag", "()Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag;", "setTag", "(Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag;)V", "getHeight", "setHeight", "getWidth", "setWidth", "getTrackId", "setTrackId", "Tag", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkWineBoxInfo {
    private int bottom;
    private int height;
    private int left;
    private int right;
    private float score;
    private Tag tag;
    private int top;
    private int trackId;
    private int width;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag;", "", "mValue", "", "<init>", "(Ljava/lang/String;II)V", "getMValue", "()I", "WINE", "WINE_LABEL", "INVALID", "getValue", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Tag {
        WINE(0),
        WINE_LABEL(1),
        INVALID(-1);
        
        public static final Companion Companion = null;
        private final int mValue;

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag$Companion;", "", "<init>", "()V", "toTag", "Lcom/samsung/android/vexfwk/param/VexFwkWineBoxInfo$Tag;", "x", "", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            public final Tag toTag(String str) {
                j.e(str, "x");
                if (str.equals("Wine")) {
                    return Tag.WINE;
                }
                if (str.equals("wine_label")) {
                    return Tag.WINE_LABEL;
                }
                return Tag.INVALID;
            }

            private Companion() {
            }

            public final Tag toTag(int i2) {
                if (i2 == 0) {
                    return Tag.WINE;
                }
                if (i2 != 1) {
                    return Tag.INVALID;
                }
                return Tag.WINE_LABEL;
            }
        }

        static {
            Tag[] $values;
            $ENTRIES = c.t($values);
            Companion = new Companion((e) null);
        }

        private Tag(int i2) {
            this.mValue = i2;
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }

        public static final Tag toTag(int i2) {
            return Companion.toTag(i2);
        }

        public final int getMValue() {
            return this.mValue;
        }

        public final int getValue() {
            return this.mValue;
        }

        public static final Tag toTag(String str) {
            return Companion.toTag(str);
        }
    }

    public VexFwkWineBoxInfo() {
        this.tag = Tag.INVALID;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getRight() {
        return this.right;
    }

    public final float getScore() {
        return this.score;
    }

    public final Tag getTag() {
        return this.tag;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getTrackId() {
        return this.trackId;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setBottom(int i2) {
        this.bottom = i2;
    }

    public final void setHeight(int i2) {
        this.height = i2;
    }

    public final void setLeft(int i2) {
        this.left = i2;
    }

    public final void setRight(int i2) {
        this.right = i2;
    }

    public final void setScore(float f) {
        this.score = f;
    }

    public final void setTag(Tag tag2) {
        j.e(tag2, "<set-?>");
        this.tag = tag2;
    }

    public final void setTop(int i2) {
        this.top = i2;
    }

    public final void setTrackId(int i2) {
        this.trackId = i2;
    }

    public final void setWidth(int i2) {
        this.width = i2;
    }

    public VexFwkWineBoxInfo(int i2, int i7, int i8, int i10, float f, Tag tag2, int i11, int i12, int i13) {
        j.e(tag2, "tag");
        Tag.Companion companion = Tag.Companion;
        this.left = i2;
        this.top = i7;
        this.right = i8;
        this.bottom = i10;
        this.score = f;
        this.tag = tag2;
        this.height = i11;
        this.width = i12;
        this.trackId = i13;
    }
}
