package androidx.core.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BidiFormatter {
    static final BidiFormatter DEFAULT_LTR_INSTANCE;
    static final BidiFormatter DEFAULT_RTL_INSTANCE;
    static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final String LRM_STRING = Character.toString(8206);
    private static final String RLM_STRING = Character.toString(8207);
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        private static BidiFormatter getDefaultInstanceFromContext(boolean z) {
            if (z) {
                return BidiFormatter.DEFAULT_RTL_INSTANCE;
            }
            return BidiFormatter.DEFAULT_LTR_INSTANCE;
        }

        private void initialize(boolean z) {
            this.mIsRtlContext = z;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public BidiFormatter build() {
            if (this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC) {
                return getDefaultInstanceFromContext(this.mIsRtlContext);
            }
            return new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = new byte[1792];
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;

        static {
            for (int i2 = 0; i2 < 1792; i2++) {
                DIR_TYPE_CACHE[i2] = Character.getDirectionality(i2);
            }
        }

        public DirectionalityEstimator(CharSequence charSequence, boolean z) {
            this.text = charSequence;
            this.isHtml = z;
            this.length = charSequence.length();
        }

        private static byte getCachedDirectionality(char c5) {
            if (c5 < 1792) {
                return DIR_TYPE_CACHE[c5];
            }
            return Character.getDirectionality(c5);
        }

        private byte skipEntityBackward() {
            char charAt;
            int i2 = this.charIndex;
            do {
                int i7 = this.charIndex;
                if (i7 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i8 = i7 - 1;
                this.charIndex = i8;
                charAt = charSequence.charAt(i8);
                this.lastChar = charAt;
                if (charAt == '&') {
                    return 12;
                }
            } while (charAt != ';');
            this.charIndex = i2;
            this.lastChar = ';';
            return 13;
        }

        private byte skipEntityForward() {
            char charAt;
            do {
                int i2 = this.charIndex;
                if (i2 >= this.length) {
                    return 12;
                }
                CharSequence charSequence = this.text;
                this.charIndex = i2 + 1;
                charAt = charSequence.charAt(i2);
                this.lastChar = charAt;
            } while (charAt != ';');
            return 12;
        }

        private byte skipTagBackward() {
            char charAt;
            int i2 = this.charIndex;
            while (true) {
                int i7 = this.charIndex;
                if (i7 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i8 = i7 - 1;
                this.charIndex = i8;
                char charAt2 = charSequence.charAt(i8);
                this.lastChar = charAt2;
                if (charAt2 == '<') {
                    return 12;
                }
                if (charAt2 == '>') {
                    break;
                } else if (charAt2 == '\"' || charAt2 == '\'') {
                    do {
                        int i10 = this.charIndex;
                        if (i10 <= 0) {
                            break;
                        }
                        CharSequence charSequence2 = this.text;
                        int i11 = i10 - 1;
                        this.charIndex = i11;
                        charAt = charSequence2.charAt(i11);
                        this.lastChar = charAt;
                    } while (charAt != charAt2);
                }
            }
            this.charIndex = i2;
            this.lastChar = '>';
            return 13;
        }

        private byte skipTagForward() {
            char charAt;
            int i2 = this.charIndex;
            while (true) {
                int i7 = this.charIndex;
                if (i7 < this.length) {
                    CharSequence charSequence = this.text;
                    this.charIndex = i7 + 1;
                    char charAt2 = charSequence.charAt(i7);
                    this.lastChar = charAt2;
                    if (charAt2 == '>') {
                        return 12;
                    }
                    if (charAt2 == '\"' || charAt2 == '\'') {
                        do {
                            int i8 = this.charIndex;
                            if (i8 >= this.length) {
                                break;
                            }
                            CharSequence charSequence2 = this.text;
                            this.charIndex = i8 + 1;
                            charAt = charSequence2.charAt(i8);
                            this.lastChar = charAt;
                        } while (charAt != charAt2);
                    }
                } else {
                    this.charIndex = i2;
                    this.lastChar = '<';
                    return 13;
                }
            }
        }

        public byte dirTypeBackward() {
            char charAt = this.text.charAt(this.charIndex - 1);
            this.lastChar = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.charIndex--;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
                char c5 = this.lastChar;
                if (c5 == '>') {
                    return skipTagBackward();
                }
                if (c5 == ';') {
                    return skipEntityBackward();
                }
            }
            return cachedDirectionality;
        }

        public byte dirTypeForward() {
            char charAt = this.text.charAt(this.charIndex);
            this.lastChar = charAt;
            if (Character.isHighSurrogate(charAt)) {
                int codePointAt = Character.codePointAt(this.text, this.charIndex);
                this.charIndex = Character.charCount(codePointAt) + this.charIndex;
                return Character.getDirectionality(codePointAt);
            }
            this.charIndex++;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
                char c5 = this.lastChar;
                if (c5 == '<') {
                    return skipTagForward();
                }
                if (c5 == '&') {
                    return skipEntityForward();
                }
            }
            return cachedDirectionality;
        }

        public int getEntryDir() {
            this.charIndex = 0;
            int i2 = 0;
            int i7 = 0;
            int i8 = 0;
            while (this.charIndex < this.length && i2 == 0) {
                byte dirTypeForward = dirTypeForward();
                if (dirTypeForward != 0) {
                    if (dirTypeForward == 1 || dirTypeForward == 2) {
                        if (i8 == 0) {
                            return 1;
                        }
                    } else if (dirTypeForward != 9) {
                        switch (dirTypeForward) {
                            case 14:
                            case 15:
                                i8++;
                                i7 = -1;
                                continue;
                            case 16:
                            case 17:
                                i8++;
                                i7 = 1;
                                continue;
                            case 18:
                                i8--;
                                i7 = 0;
                                continue;
                        }
                    }
                } else if (i8 == 0) {
                    return -1;
                }
                i2 = i8;
            }
            if (i2 == 0) {
                return 0;
            }
            if (i7 != 0) {
                return i7;
            }
            while (this.charIndex > 0) {
                switch (dirTypeBackward()) {
                    case 14:
                    case 15:
                        if (i2 == i8) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i2 == i8) {
                            return 1;
                        }
                        break;
                    case 18:
                        i8++;
                        continue;
                }
                i8--;
            }
            return 0;
        }

        public int getExitDir() {
            this.charIndex = this.length;
            int i2 = 0;
            while (true) {
                int i7 = i2;
                while (this.charIndex > 0) {
                    byte dirTypeBackward = dirTypeBackward();
                    if (dirTypeBackward != 0) {
                        if (dirTypeBackward == 1 || dirTypeBackward == 2) {
                            if (i2 == 0) {
                                return 1;
                            }
                            if (i7 == 0) {
                            }
                        } else if (dirTypeBackward != 9) {
                            switch (dirTypeBackward) {
                                case 14:
                                case 15:
                                    if (i7 == i2) {
                                        return -1;
                                    }
                                    break;
                                case 16:
                                case 17:
                                    if (i7 == i2) {
                                        return 1;
                                    }
                                    break;
                                case 18:
                                    i2++;
                                    break;
                                default:
                                    if (i7 != 0) {
                                        break;
                                    } else {
                                        continue;
                                    }
                            }
                            i2--;
                        } else {
                            continue;
                        }
                    } else if (i2 == 0) {
                        return -1;
                    } else {
                        if (i7 == 0) {
                        }
                    }
                }
                return 0;
            }
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        DEFAULT_TEXT_DIRECTION_HEURISTIC = textDirectionHeuristicCompat;
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    public BidiFormatter(boolean z, int i2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.mIsRtlContext = z;
        this.mFlags = i2;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
    }

    private static int getEntryDir(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).getEntryDir();
    }

    private static int getExitDir(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).getExitDir();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static boolean isRtlLocale(Locale locale) {
        if (TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1) {
            return true;
        }
        return false;
    }

    private String markAfter(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        if (!this.mIsRtlContext && (isRtl || getExitDir(charSequence) == 1)) {
            return LRM_STRING;
        }
        if (!this.mIsRtlContext) {
            return "";
        }
        if (!isRtl || getExitDir(charSequence) == -1) {
            return RLM_STRING;
        }
        return "";
    }

    private String markBefore(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        if (!this.mIsRtlContext && (isRtl || getEntryDir(charSequence) == 1)) {
            return LRM_STRING;
        }
        if (!this.mIsRtlContext) {
            return "";
        }
        if (!isRtl || getEntryDir(charSequence) == -1) {
            return RLM_STRING;
        }
        return "";
    }

    public boolean getStereoReset() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, textDirectionHeuristicCompat, z).toString();
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && z) {
            spannableStringBuilder.append(markBefore(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (isRtl != this.mIsRtlContext) {
            spannableStringBuilder.append(isRtl ? (char) 8235 : 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append(8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append(markAfter(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence) {
        return unicodeWrap(charSequence, this.mDefaultTextDirectionHeuristicCompat, true);
    }
}
