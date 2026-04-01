package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TypefaceEmojiSpan extends EmojiSpan {
    private static Paint sDebugPaint;
    private TextPaint mWorkingPaint;

    public TypefaceEmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        super(typefaceEmojiRasterizer);
    }

    private TextPaint applyCharacterSpanStyles(CharSequence charSequence, int i2, int i7, Paint paint) {
        if (charSequence instanceof Spanned) {
            CharacterStyle[] characterStyleArr = (CharacterStyle[]) ((Spanned) charSequence).getSpans(i2, i7, CharacterStyle.class);
            if (characterStyleArr.length != 0) {
                if (!(characterStyleArr.length == 1 && characterStyleArr[0] == this)) {
                    TextPaint textPaint = this.mWorkingPaint;
                    if (textPaint == null) {
                        textPaint = new TextPaint();
                        this.mWorkingPaint = textPaint;
                    }
                    textPaint.set(paint);
                    for (CharacterStyle updateDrawState : characterStyleArr) {
                        updateDrawState.updateDrawState(textPaint);
                    }
                    return textPaint;
                }
            }
            if (paint instanceof TextPaint) {
                return (TextPaint) paint;
            }
            return null;
        } else if (paint instanceof TextPaint) {
            return (TextPaint) paint;
        } else {
            return null;
        }
    }

    private static Paint getDebugPaint() {
        if (sDebugPaint == null) {
            TextPaint textPaint = new TextPaint();
            sDebugPaint = textPaint;
            textPaint.setColor(EmojiCompat.get().getEmojiSpanIndicatorColor());
            sDebugPaint.setStyle(Paint.Style.FILL);
        }
        return sDebugPaint;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i7, float f, int i8, int i10, int i11, Paint paint) {
        int i12 = i8;
        int i13 = i11;
        Paint paint2 = paint;
        TextPaint applyCharacterSpanStyles = applyCharacterSpanStyles(charSequence, i2, i7, paint2);
        if (!(applyCharacterSpanStyles == null || applyCharacterSpanStyles.bgColor == 0)) {
            drawBackground(canvas, applyCharacterSpanStyles, f, f + ((float) getWidth()), (float) i12, (float) i13);
        }
        TextPaint textPaint = applyCharacterSpanStyles;
        if (EmojiCompat.get().isEmojiSpanIndicatorEnabled()) {
            canvas.drawRect(f, (float) i12, f + ((float) getWidth()), (float) i13, getDebugPaint());
        }
        TypefaceEmojiRasterizer typefaceRasterizer = getTypefaceRasterizer();
        float f5 = (float) i10;
        if (textPaint == null) {
            textPaint = paint2;
        }
        typefaceRasterizer.draw(canvas, f, f5, textPaint);
    }

    public void drawBackground(Canvas canvas, TextPaint textPaint, float f, float f5, float f8, float f10) {
        int color = textPaint.getColor();
        Paint.Style style = textPaint.getStyle();
        textPaint.setColor(textPaint.bgColor);
        textPaint.setStyle(Paint.Style.FILL);
        float f11 = f10;
        TextPaint textPaint2 = textPaint;
        canvas.drawRect(f, f8, f5, f11, textPaint2);
        textPaint2.setStyle(style);
        textPaint2.setColor(color);
    }
}
