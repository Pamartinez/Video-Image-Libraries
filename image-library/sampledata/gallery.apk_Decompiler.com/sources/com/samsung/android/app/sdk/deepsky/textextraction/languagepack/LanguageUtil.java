package com.samsung.android.app.sdk.deepsky.textextraction.languagepack;

import Tf.v;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1195m;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014R&\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00160\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LanguageUtil;", "", "<init>", "()V", "", "language", "country", "", "isCountryImportant", "(Ljava/lang/String;Ljava/lang/String;)Z", "Landroid/content/Context;", "context", "langCode", "getDisplayNameFromLangCode", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;", "createTargetLanguage", "()Ljava/lang/String;", "createSourceLanguage", "(Ljava/lang/String;)Ljava/lang/String;", "isNormalLanguage", "(Ljava/lang/String;)Z", "", "", "SUPPORTED_COUNTRIES_MAP", "Ljava/util/Map;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LanguageUtil {
    public static final LanguageUtil INSTANCE = new LanguageUtil();
    private static final Map<String, List<String>> SUPPORTED_COUNTRIES_MAP = z.b0(new i("zh", C1195m.q0("hk", "tw")), new i("pt", C0246a.e0("br")), new i("es", C1195m.q0("mx", "us")), new i("fr", C0246a.e0("ca")));

    private LanguageUtil() {
    }

    private final boolean isCountryImportant(String str, String str2) {
        List list = SUPPORTED_COUNTRIES_MAP.get(str);
        if (list != null) {
            return list.contains(str2);
        }
        return false;
    }

    public final String createSourceLanguage(String str) {
        j.e(str, "langCode");
        if (!isNormalLanguage(str) || str.length() <= 2) {
            return str;
        }
        Set<String> keySet = SUPPORTED_COUNTRIES_MAP.keySet();
        if (keySet == null || !keySet.isEmpty()) {
            for (String t02 : keySet) {
                if (v.t0(str, t02)) {
                    return str;
                }
            }
        }
        String substring = str.substring(0, 2);
        j.d(substring, "substring(...)");
        return substring;
    }

    public final String createTargetLanguage() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        j.d(country, "getCountry(...)");
        Locale locale = Locale.ROOT;
        String lowerCase = country.toLowerCase(locale);
        j.d(lowerCase, "toLowerCase(...)");
        j.b(language);
        if (isCountryImportant(language, lowerCase)) {
            String lowerCase2 = lowerCase.toLowerCase(locale);
            j.d(lowerCase2, "toLowerCase(...)");
            language = language.concat(lowerCase2);
        }
        LibLogger.i("LanguageUtil", "target language: \"" + language + "\"");
        j.d(language, "also(...)");
        return language;
    }

    public final String getDisplayNameFromLangCode(Context context, String str) {
        String str2;
        j.e(context, "context");
        j.e(str, "langCode");
        if (str.equals("Auto")) {
            String string = context.getString(R$string.lang_auto_detected);
            j.d(string, "getString(...)");
            return string;
        }
        if (str.length() == 4) {
            String substring = str.substring(0, 2);
            j.d(substring, "substring(...)");
            String substring2 = str.substring(2);
            j.d(substring2, "substring(...)");
            str2 = new Locale(substring, substring2).getDisplayName();
        } else {
            str2 = new Locale(str).getDisplayName();
        }
        j.b(str2);
        return str2;
    }

    public final boolean isNormalLanguage(String str) {
        j.e(str, "langCode");
        if (str.equals(C2paManifestList.UNKNOWN_VALUE) || str.equals("<neutral>")) {
            return false;
        }
        return true;
    }
}
