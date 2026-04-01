package androidx.emoji2.text;

import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class UnprecomputeTextOnModificationSpannable implements Spannable {
    private Spannable mDelegate;
    private boolean mSafeToWrite = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CharSequenceHelper_API24 {
        public static IntStream chars(CharSequence charSequence) {
            return charSequence.chars();
        }

        public static IntStream codePoints(CharSequence charSequence) {
            return charSequence.codePoints();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrecomputedTextDetector {
        public abstract boolean isPrecomputedText(CharSequence charSequence);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrecomputedTextDetector_28 extends PrecomputedTextDetector {
        public boolean isPrecomputedText(CharSequence charSequence) {
            if (!(charSequence instanceof PrecomputedText)) {
                return false;
            }
            return true;
        }
    }

    public UnprecomputeTextOnModificationSpannable(Spannable spannable) {
        this.mDelegate = spannable;
    }

    private void ensureSafeWrites() {
        Spannable spannable = this.mDelegate;
        if (!this.mSafeToWrite && precomputedTextDetector().isPrecomputedText(spannable)) {
            this.mDelegate = new SpannableString(spannable);
        }
        this.mSafeToWrite = true;
    }

    public static PrecomputedTextDetector precomputedTextDetector() {
        return new PrecomputedTextDetector_28();
    }

    public char charAt(int i2) {
        return this.mDelegate.charAt(i2);
    }

    public IntStream chars() {
        return CharSequenceHelper_API24.chars(this.mDelegate);
    }

    public IntStream codePoints() {
        return CharSequenceHelper_API24.codePoints(this.mDelegate);
    }

    public int getSpanEnd(Object obj) {
        return this.mDelegate.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.mDelegate.getSpanFlags(obj);
    }

    public int getSpanStart(Object obj) {
        return this.mDelegate.getSpanStart(obj);
    }

    public <T> T[] getSpans(int i2, int i7, Class<T> cls) {
        return this.mDelegate.getSpans(i2, i7, cls);
    }

    public Spannable getUnwrappedSpannable() {
        return this.mDelegate;
    }

    public int length() {
        return this.mDelegate.length();
    }

    public int nextSpanTransition(int i2, int i7, Class cls) {
        return this.mDelegate.nextSpanTransition(i2, i7, cls);
    }

    public void removeSpan(Object obj) {
        ensureSafeWrites();
        this.mDelegate.removeSpan(obj);
    }

    public void setSpan(Object obj, int i2, int i7, int i8) {
        ensureSafeWrites();
        this.mDelegate.setSpan(obj, i2, i7, i8);
    }

    public CharSequence subSequence(int i2, int i7) {
        return this.mDelegate.subSequence(i2, i7);
    }

    public String toString() {
        return this.mDelegate.toString();
    }

    public UnprecomputeTextOnModificationSpannable(CharSequence charSequence) {
        this.mDelegate = new SpannableString(charSequence);
    }
}
