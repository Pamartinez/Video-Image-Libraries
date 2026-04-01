package androidx.media3.extractor.text.ttml;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.text.TextEmphasisSpan;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class TtmlRenderUtil {
    public static void applyStylesToSpan(Spannable spannable, int i2, int i7, TtmlStyle ttmlStyle, TtmlNode ttmlNode, Map<String, TtmlStyle> map, int i8) {
        TtmlNode findRubyTextNode;
        int i10;
        TtmlStyle resolveStyle;
        int i11;
        if (ttmlStyle.getStyle() != -1) {
            spannable.setSpan(new StyleSpan(ttmlStyle.getStyle()), i2, i7, 33);
        }
        if (ttmlStyle.isLinethrough()) {
            spannable.setSpan(new StrikethroughSpan(), i2, i7, 33);
        }
        if (ttmlStyle.isUnderline()) {
            spannable.setSpan(new UnderlineSpan(), i2, i7, 33);
        }
        if (ttmlStyle.hasFontColor()) {
            SpanUtil.addOrReplaceSpan(spannable, new ForegroundColorSpan(ttmlStyle.getFontColor()), i2, i7, 33);
        }
        if (ttmlStyle.hasBackgroundColor()) {
            SpanUtil.addOrReplaceSpan(spannable, new BackgroundColorSpan(ttmlStyle.getBackgroundColor()), i2, i7, 33);
        }
        if (ttmlStyle.getFontFamily() != null) {
            SpanUtil.addOrReplaceSpan(spannable, new TypefaceSpan(ttmlStyle.getFontFamily()), i2, i7, 33);
        }
        if (ttmlStyle.getTextEmphasis() != null) {
            TextEmphasis textEmphasis = (TextEmphasis) Assertions.checkNotNull(ttmlStyle.getTextEmphasis());
            int i12 = textEmphasis.markShape;
            if (i12 == -1) {
                if (i8 == 2 || i8 == 1) {
                    i12 = 3;
                } else {
                    i12 = 1;
                }
                i11 = 1;
            } else {
                i11 = textEmphasis.markFill;
            }
            int i13 = textEmphasis.position;
            if (i13 == -2) {
                i13 = 1;
            }
            SpanUtil.addOrReplaceSpan(spannable, new TextEmphasisSpan(i12, i11, i13), i2, i7, 33);
        }
        int rubyType = ttmlStyle.getRubyType();
        if (rubyType == 2) {
            TtmlNode findRubyContainerNode = findRubyContainerNode(ttmlNode, map);
            if (!(findRubyContainerNode == null || (findRubyTextNode = findRubyTextNode(findRubyContainerNode, map)) == null)) {
                if (findRubyTextNode.getChildCount() != 1 || findRubyTextNode.getChild(0).text == null) {
                    Log.i("TtmlRenderUtil", "Skipping rubyText node without exactly one text child.");
                } else {
                    String str = (String) Util.castNonNull(findRubyTextNode.getChild(0).text);
                    TtmlStyle resolveStyle2 = resolveStyle(findRubyTextNode.style, findRubyTextNode.getStyleIds(), map);
                    if (resolveStyle2 != null) {
                        i10 = resolveStyle2.getRubyPosition();
                    } else {
                        i10 = -1;
                    }
                    if (i10 == -1 && (resolveStyle = resolveStyle(findRubyContainerNode.style, findRubyContainerNode.getStyleIds(), map)) != null) {
                        i10 = resolveStyle.getRubyPosition();
                    }
                    spannable.setSpan(new RubySpan(str, i10), i2, i7, 33);
                }
            }
        } else if (rubyType == 3 || rubyType == 4) {
            spannable.setSpan(new DeleteTextSpan(), i2, i7, 33);
        }
        if (ttmlStyle.getTextCombine()) {
            SpanUtil.addOrReplaceSpan(spannable, new HorizontalTextInVerticalContextSpan(), i2, i7, 33);
        }
        int fontSizeUnit = ttmlStyle.getFontSizeUnit();
        if (fontSizeUnit == 1) {
            SpanUtil.addOrReplaceSpan(spannable, new AbsoluteSizeSpan((int) ttmlStyle.getFontSize(), true), i2, i7, 33);
        } else if (fontSizeUnit == 2) {
            SpanUtil.addOrReplaceSpan(spannable, new RelativeSizeSpan(ttmlStyle.getFontSize()), i2, i7, 33);
        } else if (fontSizeUnit == 3) {
            SpanUtil.addInheritedRelativeSizeSpan(spannable, ttmlStyle.getFontSize() / 100.0f, i2, i7, 33);
        }
    }

    public static String applyTextElementSpacePolicy(String str) {
        return str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    public static void endParagraph(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length >= 0 && spannableStringBuilder.charAt(length) != 10) {
            spannableStringBuilder.append(10);
        }
    }

    private static TtmlNode findRubyContainerNode(TtmlNode ttmlNode, Map<String, TtmlStyle> map) {
        while (ttmlNode != null) {
            TtmlStyle resolveStyle = resolveStyle(ttmlNode.style, ttmlNode.getStyleIds(), map);
            if (resolveStyle != null && resolveStyle.getRubyType() == 1) {
                return ttmlNode;
            }
            ttmlNode = ttmlNode.parent;
        }
        return null;
    }

    private static TtmlNode findRubyTextNode(TtmlNode ttmlNode, Map<String, TtmlStyle> map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(ttmlNode);
        while (!arrayDeque.isEmpty()) {
            TtmlNode ttmlNode2 = (TtmlNode) arrayDeque.pop();
            TtmlStyle resolveStyle = resolveStyle(ttmlNode2.style, ttmlNode2.getStyleIds(), map);
            if (resolveStyle != null && resolveStyle.getRubyType() == 3) {
                return ttmlNode2;
            }
            for (int childCount = ttmlNode2.getChildCount() - 1; childCount >= 0; childCount--) {
                arrayDeque.push(ttmlNode2.getChild(childCount));
            }
        }
        return null;
    }

    public static TtmlStyle resolveStyle(TtmlStyle ttmlStyle, String[] strArr, Map<String, TtmlStyle> map) {
        int i2 = 0;
        if (ttmlStyle == null) {
            if (strArr == null) {
                return null;
            }
            if (strArr.length == 1) {
                return map.get(strArr[0]);
            }
            if (strArr.length > 1) {
                TtmlStyle ttmlStyle2 = new TtmlStyle();
                int length = strArr.length;
                while (i2 < length) {
                    ttmlStyle2.chain(map.get(strArr[i2]));
                    i2++;
                }
                return ttmlStyle2;
            }
        } else if (strArr != null && strArr.length == 1) {
            return ttmlStyle.chain(map.get(strArr[0]));
        } else {
            if (strArr != null && strArr.length > 1) {
                int length2 = strArr.length;
                while (i2 < length2) {
                    ttmlStyle.chain(map.get(strArr[i2]));
                    i2++;
                }
            }
        }
        return ttmlStyle;
    }
}
