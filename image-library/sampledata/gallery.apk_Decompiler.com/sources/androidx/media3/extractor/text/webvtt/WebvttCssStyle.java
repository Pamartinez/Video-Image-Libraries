package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WebvttCssStyle {
    private int backgroundColor;
    private int bold = -1;
    private boolean combineUpright = false;
    private int fontColor;
    private String fontFamily = null;
    private float fontSize;
    private int fontSizeUnit = -1;
    private boolean hasBackgroundColor = false;
    private boolean hasFontColor = false;
    private int italic = -1;
    private int linethrough = -1;
    private int rubyPosition = -1;
    private Set<String> targetClasses = Collections.EMPTY_SET;
    private String targetId = "";
    private String targetTag = "";
    private String targetVoice = "";
    private int underline = -1;

    private static int updateScoreForMatch(int i2, String str, String str2, int i7) {
        if (str.isEmpty() || i2 == -1) {
            return i2;
        }
        if (str.equals(str2)) {
            return i2 + i7;
        }
        return -1;
    }

    public int getBackgroundColor() {
        if (this.hasBackgroundColor) {
            return this.backgroundColor;
        }
        throw new IllegalStateException("Background color not defined.");
    }

    public boolean getCombineUpright() {
        return this.combineUpright;
    }

    public int getFontColor() {
        if (this.hasFontColor) {
            return this.fontColor;
        }
        throw new IllegalStateException("Font color not defined");
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

    public int getRubyPosition() {
        return this.rubyPosition;
    }

    public int getSpecificityScore(String str, String str2, Set<String> set, String str3) {
        if (this.targetId.isEmpty() && this.targetTag.isEmpty() && this.targetClasses.isEmpty() && this.targetVoice.isEmpty()) {
            return TextUtils.isEmpty(str2) ? 1 : 0;
        }
        int updateScoreForMatch = updateScoreForMatch(updateScoreForMatch(updateScoreForMatch(0, this.targetId, str, 1073741824), this.targetTag, str2, 2), this.targetVoice, str3, 4);
        if (updateScoreForMatch == -1 || !set.containsAll(this.targetClasses)) {
            return 0;
        }
        return (this.targetClasses.size() * 4) + updateScoreForMatch;
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

    public WebvttCssStyle setBackgroundColor(int i2) {
        this.backgroundColor = i2;
        this.hasBackgroundColor = true;
        return this;
    }

    public WebvttCssStyle setBold(boolean z) {
        this.bold = z ? 1 : 0;
        return this;
    }

    public WebvttCssStyle setCombineUpright(boolean z) {
        this.combineUpright = z;
        return this;
    }

    public WebvttCssStyle setFontColor(int i2) {
        this.fontColor = i2;
        this.hasFontColor = true;
        return this;
    }

    public WebvttCssStyle setFontFamily(String str) {
        String str2;
        if (str == null) {
            str2 = null;
        } else {
            str2 = k.S(str);
        }
        this.fontFamily = str2;
        return this;
    }

    public WebvttCssStyle setFontSize(float f) {
        this.fontSize = f;
        return this;
    }

    public WebvttCssStyle setFontSizeUnit(int i2) {
        this.fontSizeUnit = i2;
        return this;
    }

    public WebvttCssStyle setItalic(boolean z) {
        this.italic = z ? 1 : 0;
        return this;
    }

    public WebvttCssStyle setRubyPosition(int i2) {
        this.rubyPosition = i2;
        return this;
    }

    public void setTargetClasses(String[] strArr) {
        this.targetClasses = new HashSet(Arrays.asList(strArr));
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public void setTargetTagName(String str) {
        this.targetTag = str;
    }

    public void setTargetVoice(String str) {
        this.targetVoice = str;
    }

    public WebvttCssStyle setUnderline(boolean z) {
        this.underline = z ? 1 : 0;
        return this;
    }
}
