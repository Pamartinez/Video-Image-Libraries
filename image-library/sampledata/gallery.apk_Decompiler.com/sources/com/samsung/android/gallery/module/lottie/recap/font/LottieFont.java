package com.samsung.android.gallery.module.lottie.recap.font;

import android.graphics.Typeface;
import android.os.Build;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;
import java.util.HashSet;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LottieFont {
    private static final HashMap<String, Typeface> FONT_MAP = new HashMap<String, Typeface>() {
        HashSet<String> sLog = new HashSet<>();

        {
            put("NotoSerif-Black", LottieFont.noto_serif_black_font);
            put("sec-400", LottieFont.sec_400_font);
            put("sec-500", LottieFont.sec_500_medium_font);
            put("sec-600", LottieFont.sec_600_semiBold_font);
            put("sec-700", LottieFont.sec_700_bold_font);
            put("sec-800", LottieFont.sec_800_extra_bold_font);
            put("sec-900", LottieFont.sec_900_black_font);
        }

        public Typeface get(Object obj) {
            String str;
            Typeface typeface = (Typeface) super.get(obj);
            if (!this.sLog.contains(String.valueOf(obj))) {
                if (Build.VERSION.SDK_INT < 34) {
                    str = null;
                } else if (typeface == null) {
                    str = " undefined font. use sec600";
                } else {
                    str = typeface.getSystemFontFamilyName() + "-" + typeface.getWeight() + "/bold=" + typeface.isBold();
                }
                Log.d(LottieFont.TAG, "getFont [" + obj + "] -> " + str);
                this.sLog.add(String.valueOf(obj));
            }
            return typeface;
        }
    };
    /* access modifiers changed from: private */
    public static final CharSequence TAG = "LottieFont";
    /* access modifiers changed from: private */
    public static final Typeface noto_serif_black_font = Typeface.create(Typeface.createFromFile("/system/fonts/NotoSerif-Bold.ttf"), 900, false);
    /* access modifiers changed from: private */
    public static final Typeface sec_400_font;
    /* access modifiers changed from: private */
    public static final Typeface sec_500_medium_font;
    /* access modifiers changed from: private */
    public static final Typeface sec_600_semiBold_font;
    /* access modifiers changed from: private */
    public static final Typeface sec_700_bold_font;
    /* access modifiers changed from: private */
    public static final Typeface sec_800_extra_bold_font;
    public static final Typeface sec_900_black_font;
    private static final Typeface sec_normal;

    static {
        Typeface create = Typeface.create("sec", 0);
        sec_normal = create;
        sec_400_font = Typeface.create(create, 400, false);
        sec_500_medium_font = Typeface.create(create, 500, false);
        sec_600_semiBold_font = Typeface.create(create, 600, false);
        sec_700_bold_font = Typeface.create(create, 700, false);
        sec_800_extra_bold_font = Typeface.create(create, 800, false);
        sec_900_black_font = Typeface.create(create, 900, false);
    }

    public static void bind(w wVar) {
        HashMap<String, Typeface> hashMap = FONT_MAP;
        if (hashMap != wVar.f2096o) {
            wVar.f2096o = hashMap;
            wVar.invalidateSelf();
        }
    }

    public static void bind(LottieAnimationView lottieAnimationView) {
        lottieAnimationView.setFontMap(FONT_MAP);
    }
}
