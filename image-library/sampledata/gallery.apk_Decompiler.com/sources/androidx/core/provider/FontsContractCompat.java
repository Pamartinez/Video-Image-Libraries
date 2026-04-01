package androidx.core.provider;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FontsContractCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FontFamilyResult {
        private final List<FontInfo[]> mFonts;
        private final int mStatusCode;

        @Deprecated
        public FontFamilyResult(int i2, FontInfo[] fontInfoArr) {
            this.mStatusCode = i2;
            this.mFonts = Collections.singletonList(fontInfoArr);
        }

        public static FontFamilyResult create(int i2, FontInfo[] fontInfoArr) {
            return new FontFamilyResult(i2, fontInfoArr);
        }

        public FontInfo[] getFonts() {
            return this.mFonts.get(0);
        }

        public List<FontInfo[]> getFontsWithFallbacks() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public boolean hasFallback() {
            if (this.mFonts.size() > 1) {
                return true;
            }
            return false;
        }

        public static FontFamilyResult create(int i2, List<FontInfo[]> list) {
            return new FontFamilyResult(i2, list);
        }

        public FontFamilyResult(int i2, List<FontInfo[]> list) {
            this.mStatusCode = i2;
            this.mFonts = list;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @Deprecated
        public FontInfo(Uri uri, int i2, int i7, boolean z, int i8) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i2;
            this.mWeight = i7;
            this.mItalic = z;
            this.mResultCode = i8;
        }

        public static FontInfo create(Uri uri, int i2, int i7, boolean z, int i8) {
            return new FontInfo(uri, i2, i7, z, i8);
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FontRequestCallback {
        public abstract void onTypefaceRequestFailed(int i2);

        public abstract void onTypefaceRetrieved(Typeface typeface);
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationSignal, FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) {
        return FontProvider.getFontFamilyResult(context, List.of(fontRequest), cancellationSignal);
    }

    public static Typeface requestFont(Context context, List<FontRequest> list, int i2, boolean z, int i7, Handler handler, FontRequestCallback fontRequestCallback) {
        CallbackWrapper callbackWrapper = new CallbackWrapper(fontRequestCallback, RequestExecutor.createHandlerExecutor(handler));
        if (!z) {
            return FontRequestWorker.requestFontAsync(context, list, i2, (Executor) null, callbackWrapper);
        }
        if (list.size() <= 1) {
            return FontRequestWorker.requestFontSync(context, list.get(0), callbackWrapper, i2, i7);
        }
        throw new IllegalArgumentException("Fallbacks with blocking fetches are not supported for performance reasons");
    }
}
