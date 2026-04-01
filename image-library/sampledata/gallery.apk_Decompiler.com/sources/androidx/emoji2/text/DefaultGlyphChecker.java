package androidx.emoji2.text;

import android.text.TextPaint;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DefaultGlyphChecker implements EmojiCompat.GlyphChecker {
    private static final ThreadLocal<StringBuilder> sStringBuilder = new ThreadLocal<>();
    private final TextPaint mTextPaint;

    public DefaultGlyphChecker() {
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setTextSize(10.0f);
    }

    private static StringBuilder getStringBuilder() {
        ThreadLocal<StringBuilder> threadLocal = sStringBuilder;
        if (threadLocal.get() == null) {
            threadLocal.set(new StringBuilder());
        }
        return threadLocal.get();
    }

    public boolean hasGlyph(CharSequence charSequence, int i2, int i7, int i8) {
        StringBuilder stringBuilder = getStringBuilder();
        stringBuilder.setLength(0);
        while (i2 < i7) {
            stringBuilder.append(charSequence.charAt(i2));
            i2++;
        }
        return PaintCompat.hasGlyph(this.mTextPaint, stringBuilder.toString());
    }
}
