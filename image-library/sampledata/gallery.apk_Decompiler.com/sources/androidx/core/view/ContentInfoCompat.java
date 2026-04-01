package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContentInfo;
import androidx.core.util.Preconditions;
import i.C0212a;
import java.util.Objects;
import p.C0251a;
import x.C0318a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentInfoCompat {
    private final Compat mCompat;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final BuilderCompat mBuilderCompat;

        public Builder(ClipData clipData, int i2) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.mBuilderCompat = new BuilderCompat31Impl(clipData, i2);
            } else {
                this.mBuilderCompat = new BuilderCompatImpl(clipData, i2);
            }
        }

        public ContentInfoCompat build() {
            return this.mBuilderCompat.build();
        }

        public Builder setExtras(Bundle bundle) {
            this.mBuilderCompat.setExtras(bundle);
            return this;
        }

        public Builder setFlags(int i2) {
            this.mBuilderCompat.setFlags(i2);
            return this;
        }

        public Builder setLinkUri(Uri uri) {
            this.mBuilderCompat.setLinkUri(uri);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BuilderCompat {
        ContentInfoCompat build();

        void setExtras(Bundle bundle);

        void setFlags(int i2);

        void setLinkUri(Uri uri);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BuilderCompat31Impl implements BuilderCompat {
        private final ContentInfo.Builder mPlatformBuilder;

        public BuilderCompat31Impl(ClipData clipData, int i2) {
            this.mPlatformBuilder = C0318a.e(clipData, i2);
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(new Compat31Impl(this.mPlatformBuilder.build()));
        }

        public void setExtras(Bundle bundle) {
            this.mPlatformBuilder.setExtras(bundle);
        }

        public void setFlags(int i2) {
            this.mPlatformBuilder.setFlags(i2);
        }

        public void setLinkUri(Uri uri) {
            this.mPlatformBuilder.setLinkUri(uri);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BuilderCompatImpl implements BuilderCompat {
        ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        int mSource;

        public BuilderCompatImpl(ClipData clipData, int i2) {
            this.mClip = clipData;
            this.mSource = i2;
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }

        public void setExtras(Bundle bundle) {
            this.mExtras = bundle;
        }

        public void setFlags(int i2) {
            this.mFlags = i2;
        }

        public void setLinkUri(Uri uri) {
            this.mLinkUri = uri;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Compat {
        ClipData getClip();

        int getFlags();

        int getSource();

        ContentInfo getWrapped();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Compat31Impl implements Compat {
        private final ContentInfo mWrapped;

        public Compat31Impl(ContentInfo contentInfo) {
            this.mWrapped = C0251a.k(Preconditions.checkNotNull(contentInfo));
        }

        public ClipData getClip() {
            return this.mWrapped.getClip();
        }

        public int getFlags() {
            return this.mWrapped.getFlags();
        }

        public int getSource() {
            return this.mWrapped.getSource();
        }

        public ContentInfo getWrapped() {
            return this.mWrapped;
        }

        public String toString() {
            return "ContentInfoCompat{" + this.mWrapped + "}";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CompatImpl implements Compat {
        private final ClipData mClip;
        private final Bundle mExtras;
        private final int mFlags;
        private final Uri mLinkUri;
        private final int mSource;

        public CompatImpl(BuilderCompatImpl builderCompatImpl) {
            this.mClip = (ClipData) Preconditions.checkNotNull(builderCompatImpl.mClip);
            this.mSource = Preconditions.checkArgumentInRange(builderCompatImpl.mSource, 0, 5, "source");
            this.mFlags = Preconditions.checkFlagsArgument(builderCompatImpl.mFlags, 1);
            this.mLinkUri = builderCompatImpl.mLinkUri;
            this.mExtras = builderCompatImpl.mExtras;
        }

        public ClipData getClip() {
            return this.mClip;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public int getSource() {
            return this.mSource;
        }

        public ContentInfo getWrapped() {
            return null;
        }

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder("ContentInfoCompat{clip=");
            sb2.append(this.mClip.getDescription());
            sb2.append(", source=");
            sb2.append(ContentInfoCompat.sourceToString(this.mSource));
            sb2.append(", flags=");
            sb2.append(ContentInfoCompat.flagsToString(this.mFlags));
            String str2 = "";
            if (this.mLinkUri == null) {
                str = str2;
            } else {
                str = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
            }
            sb2.append(str);
            if (this.mExtras != null) {
                str2 = ", hasExtras";
            }
            return C0212a.p(sb2, str2, "}");
        }
    }

    public ContentInfoCompat(Compat compat) {
        this.mCompat = compat;
    }

    public static String flagsToString(int i2) {
        if ((i2 & 1) != 0) {
            return "FLAG_CONVERT_TO_PLAIN_TEXT";
        }
        return String.valueOf(i2);
    }

    public static String sourceToString(int i2) {
        if (i2 == 0) {
            return "SOURCE_APP";
        }
        if (i2 == 1) {
            return "SOURCE_CLIPBOARD";
        }
        if (i2 == 2) {
            return "SOURCE_INPUT_METHOD";
        }
        if (i2 == 3) {
            return "SOURCE_DRAG_AND_DROP";
        }
        if (i2 == 4) {
            return "SOURCE_AUTOFILL";
        }
        if (i2 != 5) {
            return String.valueOf(i2);
        }
        return "SOURCE_PROCESS_TEXT";
    }

    public static ContentInfoCompat toContentInfoCompat(ContentInfo contentInfo) {
        return new ContentInfoCompat(new Compat31Impl(contentInfo));
    }

    public ClipData getClip() {
        return this.mCompat.getClip();
    }

    public int getFlags() {
        return this.mCompat.getFlags();
    }

    public int getSource() {
        return this.mCompat.getSource();
    }

    public ContentInfo toContentInfo() {
        ContentInfo wrapped = this.mCompat.getWrapped();
        Objects.requireNonNull(wrapped);
        return C0251a.k(wrapped);
    }

    public String toString() {
        return this.mCompat.toString();
    }
}
