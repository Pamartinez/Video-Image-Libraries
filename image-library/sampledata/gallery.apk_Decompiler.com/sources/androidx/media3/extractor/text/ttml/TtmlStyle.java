package androidx.media3.extractor.text.ttml;

import android.text.Layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TtmlStyle {
    private int backgroundColor;
    private int bold = -1;
    private String extent;
    private int fontColor;
    private String fontFamily;
    private float fontSize;
    private int fontSizeUnit = -1;
    private boolean hasBackgroundColor;
    private boolean hasFontColor;
    private String id;
    private int italic = -1;
    private int linethrough = -1;
    private Layout.Alignment multiRowAlign;
    private String origin;
    private int rubyPosition = -1;
    private int rubyType = -1;
    private float shearPercentage = Float.MAX_VALUE;
    private Layout.Alignment textAlign;
    private int textCombine = -1;
    private TextEmphasis textEmphasis;
    private int underline = -1;

    private TtmlStyle inherit(TtmlStyle ttmlStyle, boolean z) {
        int i2;
        Layout.Alignment alignment;
        Layout.Alignment alignment2;
        String str;
        if (ttmlStyle != null) {
            if (!this.hasFontColor && ttmlStyle.hasFontColor) {
                setFontColor(ttmlStyle.fontColor);
            }
            if (this.bold == -1) {
                this.bold = ttmlStyle.bold;
            }
            if (this.italic == -1) {
                this.italic = ttmlStyle.italic;
            }
            if (this.fontFamily == null && (str = ttmlStyle.fontFamily) != null) {
                this.fontFamily = str;
            }
            if (this.linethrough == -1) {
                this.linethrough = ttmlStyle.linethrough;
            }
            if (this.underline == -1) {
                this.underline = ttmlStyle.underline;
            }
            if (this.rubyPosition == -1) {
                this.rubyPosition = ttmlStyle.rubyPosition;
            }
            if (this.textAlign == null && (alignment2 = ttmlStyle.textAlign) != null) {
                this.textAlign = alignment2;
            }
            if (this.multiRowAlign == null && (alignment = ttmlStyle.multiRowAlign) != null) {
                this.multiRowAlign = alignment;
            }
            if (this.textCombine == -1) {
                this.textCombine = ttmlStyle.textCombine;
            }
            if (this.fontSizeUnit == -1) {
                this.fontSizeUnit = ttmlStyle.fontSizeUnit;
                this.fontSize = ttmlStyle.fontSize;
            }
            if (this.textEmphasis == null) {
                this.textEmphasis = ttmlStyle.textEmphasis;
            }
            if (this.shearPercentage == Float.MAX_VALUE) {
                this.shearPercentage = ttmlStyle.shearPercentage;
            }
            if (this.origin == null) {
                this.origin = ttmlStyle.origin;
            }
            if (this.extent == null) {
                this.extent = ttmlStyle.extent;
            }
            if (z && !this.hasBackgroundColor && ttmlStyle.hasBackgroundColor) {
                setBackgroundColor(ttmlStyle.backgroundColor);
            }
            if (z && this.rubyType == -1 && (i2 = ttmlStyle.rubyType) != -1) {
                this.rubyType = i2;
            }
        }
        return this;
    }

    public TtmlStyle chain(TtmlStyle ttmlStyle) {
        return inherit(ttmlStyle, true);
    }

    public int getBackgroundColor() {
        if (this.hasBackgroundColor) {
            return this.backgroundColor;
        }
        throw new IllegalStateException("Background color has not been defined.");
    }

    public String getExtent() {
        return this.extent;
    }

    public int getFontColor() {
        if (this.hasFontColor) {
            return this.fontColor;
        }
        throw new IllegalStateException("Font color has not been defined.");
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public int getFontSizeUnit() {
        return this.fontSizeUnit;
    }

    public String getId() {
        return this.id;
    }

    public Layout.Alignment getMultiRowAlign() {
        return this.multiRowAlign;
    }

    public String getOrigin() {
        return this.origin;
    }

    public int getRubyPosition() {
        return this.rubyPosition;
    }

    public int getRubyType() {
        return this.rubyType;
    }

    public float getShearPercentage() {
        return this.shearPercentage;
    }

    public int getStyle() {
        int i2;
        int i7 = this.bold;
        if (i7 == -1 && this.italic == -1) {
            return -1;
        }
        int i8 = 0;
        if (i7 == 1) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (this.italic == 1) {
            i8 = 2;
        }
        return i2 | i8;
    }

    public Layout.Alignment getTextAlign() {
        return this.textAlign;
    }

    public boolean getTextCombine() {
        if (this.textCombine == 1) {
            return true;
        }
        return false;
    }

    public TextEmphasis getTextEmphasis() {
        return this.textEmphasis;
    }

    public boolean hasBackgroundColor() {
        return this.hasBackgroundColor;
    }

    public boolean hasFontColor() {
        return this.hasFontColor;
    }

    public boolean isLinethrough() {
        if (this.linethrough == 1) {
            return true;
        }
        return false;
    }

    public boolean isUnderline() {
        if (this.underline == 1) {
            return true;
        }
        return false;
    }

    public TtmlStyle setBackgroundColor(int i2) {
        this.backgroundColor = i2;
        this.hasBackgroundColor = true;
        return this;
    }

    public TtmlStyle setBold(boolean z) {
        this.bold = z ? 1 : 0;
        return this;
    }

    public TtmlStyle setExtent(String str) {
        this.extent = str;
        return this;
    }

    public TtmlStyle setFontColor(int i2) {
        this.fontColor = i2;
        this.hasFontColor = true;
        return this;
    }

    public TtmlStyle setFontFamily(String str) {
        this.fontFamily = str;
        return this;
    }

    public TtmlStyle setFontSize(float f) {
        this.fontSize = f;
        return this;
    }

    public TtmlStyle setFontSizeUnit(int i2) {
        this.fontSizeUnit = i2;
        return this;
    }

    public TtmlStyle setId(String str) {
        this.id = str;
        return this;
    }

    public TtmlStyle setItalic(boolean z) {
        this.italic = z ? 1 : 0;
        return this;
    }

    public TtmlStyle setLinethrough(boolean z) {
        this.linethrough = z ? 1 : 0;
        return this;
    }

    public TtmlStyle setMultiRowAlign(Layout.Alignment alignment) {
        this.multiRowAlign = alignment;
        return this;
    }

    public TtmlStyle setOrigin(String str) {
        this.origin = str;
        return this;
    }

    public TtmlStyle setRubyPosition(int i2) {
        this.rubyPosition = i2;
        return this;
    }

    public TtmlStyle setRubyType(int i2) {
        this.rubyType = i2;
        return this;
    }

    public TtmlStyle setShearPercentage(float f) {
        this.shearPercentage = f;
        return this;
    }

    public TtmlStyle setTextAlign(Layout.Alignment alignment) {
        this.textAlign = alignment;
        return this;
    }

    public TtmlStyle setTextCombine(boolean z) {
        this.textCombine = z ? 1 : 0;
        return this;
    }

    public TtmlStyle setTextEmphasis(TextEmphasis textEmphasis2) {
        this.textEmphasis = textEmphasis2;
        return this;
    }

    public TtmlStyle setUnderline(boolean z) {
        this.underline = z ? 1 : 0;
        return this;
    }
}
