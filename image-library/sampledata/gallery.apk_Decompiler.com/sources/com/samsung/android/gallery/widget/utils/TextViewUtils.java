package com.samsung.android.gallery.widget.utils;

import A.a;
import N2.j;
import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.Pair;
import android.widget.TextView;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$color;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TextViewUtils {
    public static void applyGradientText(TextView textView, int... iArr) {
        if (textView != null && iArr != null && iArr.length > 0) {
            textView.getPaint().setShader(new LinearGradient(0.0f, 0.0f, textView.getPaint().measureText(textView.getText().toString()), textView.getTextSize(), iArr, (float[]) null, Shader.TileMode.CLAMP));
        }
    }

    private static int[] findHighlightRange(String str, String str2) {
        int indexOf = str.toLowerCase().indexOf(str2.toLowerCase());
        return new int[]{indexOf, str2.length() + indexOf};
    }

    private static String getPrefixForSpan(TextView textView, CharSequence charSequence, String str) {
        String prefixForSpan = SeApiCompat.getPrefixForSpan(textView, charSequence, str);
        if (prefixForSpan != null) {
            return prefixForSpan;
        }
        return str;
    }

    public static void highlightKeyword(Context context, TextView textView, String str) {
        highlightKeyword(context, textView, str, false);
    }

    public static void highlightKeywordIgnoreTag(Context context, TextView textView, String str) {
        highlightKeyword(context, textView, str, true);
    }

    private static void highlightSpan(Context context, TextView textView, String str, int i2, int i7) {
        if (i2 < 0 || i7 > str.length() || i7 < i2) {
            StringBuilder h5 = a.h(i2, i7, "Used wrong startIndex and endIndex -> [start]: ", " [end]: ", " [length]: ");
            h5.append(str.length());
            Log.e("TextViewUtils", h5.toString());
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getColor(R$color.search_matched_text_color)), i2, i7, 33);
        spannableStringBuilder.setSpan(new TypefaceSpan(FontTypefaceUtils.TextFont.ROBOTO_SEMI_BOLD.getTypeface()), i2, i7, 33);
        ViewUtils.setText(textView, (CharSequence) spannableStringBuilder);
    }

    private static void highlightSpanMultiple(Context context, TextView textView, String str, List<Pair<Integer, Integer>> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        for (Pair next : list) {
            int intValue = ((Integer) next.first).intValue();
            int intValue2 = ((Integer) next.second).intValue();
            if (intValue < 0 || intValue2 > str.length() || intValue2 < intValue) {
                StringBuilder h5 = a.h(intValue, intValue2, "Used wrong startIndex and endIndex -> [start]: ", " [end]: ", " [length]: ");
                h5.append(str.length());
                Log.e("TextViewUtils", h5.toString());
                ViewUtils.setText(textView, str);
                return;
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getColor(R$color.search_matched_text_color)), intValue, intValue2, 33);
            spannableStringBuilder.setSpan(new TypefaceSpan(FontTypefaceUtils.TextFont.ROBOTO_SEMI_BOLD.getTypeface()), intValue, intValue2, 33);
        }
        ViewUtils.setText(textView, (CharSequence) spannableStringBuilder);
    }

    private static void highlightKeyword(Context context, TextView textView, String str, boolean z) {
        CharSequence text = textView.getText();
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(str)) {
            Log.e("TextViewUtils", "Used invalid text or keyword -> [Text]: " + text + ", [Prefix]: " + str);
            return;
        }
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE_TAG_SEARCH) && str.startsWith("#") && str.length() > 1 && !z) {
            str = str.substring(1);
        }
        String charSequence = text.toString();
        int[] findHighlightRange = findHighlightRange(charSequence, getPrefixForSpan(textView, text, str.trim()));
        highlightSpan(context, textView, charSequence, findHighlightRange[0], findHighlightRange[1]);
    }

    public static void highlightKeyword(Context context, TextView textView, int i2, String... strArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : strArr) {
            arrayList2.add("'@" + str + "@'");
        }
        try {
            String string = context.getString(i2, arrayList2.toArray(new String[0]));
            int i7 = 0;
            for (int i8 = 0; i8 < strArr.length; i8++) {
                int indexOf = string.indexOf((String) arrayList2.get(i8)) - i7;
                arrayList.add(new Pair(Integer.valueOf(indexOf), Integer.valueOf(strArr[i8].length() + indexOf)));
                i7 += 4;
            }
            highlightSpanMultiple(context, textView, context.getString(i2, strArr), arrayList);
        } catch (Exception e) {
            j.v("highlightKeyword failed : ", e, "TextViewUtils");
            ViewUtils.setText(textView, context.getString(i2, strArr));
        }
    }
}
