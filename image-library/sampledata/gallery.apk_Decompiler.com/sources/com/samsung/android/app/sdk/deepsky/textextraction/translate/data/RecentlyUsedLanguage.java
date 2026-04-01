package com.samsung.android.app.sdk.deepsky.textextraction.translate.data;

import Ae.b;
import Tf.n;
import android.content.Context;
import android.content.SharedPreferences;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/RecentlyUsedLanguage;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "separator", "", "splitToMutableList", "(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "", "isSource", "", "getRecentlyUsedLanguages", "(Z)Ljava/util/List;", "currentSourceLang", "currentTargetLang", "Lme/x;", "updateLanguages", "(Ljava/lang/String;Ljava/lang/String;)V", "Landroid/content/Context;", "sourceLanguages", "Ljava/util/List;", "targetLanguages", "Landroid/content/SharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RecentlyUsedLanguage {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final List<String> sourceLanguages;
    private final List<String> targetLanguages;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/RecentlyUsedLanguage$Companion;", "", "<init>", "()V", "TAG", "", "MAX_RECENTLY_USED_LANGUAGE_COUNT", "", "RECENTLY_USED_LANGUAGE_SEPARATOR", "RECENTLY_USED_LANGUAGE_PREFERENCE", "RECENTLY_USED_SOURCE_LANGUAGES", "RECENTLY_USED_TARGET_LANGUAGES", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public RecentlyUsedLanguage(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        SharedPreferences sharedPreferences2 = context2.getSharedPreferences("com.samsung.android.app.deepsky.RECENTLY_USED_LANGUAGES", 0);
        j.d(sharedPreferences2, "getSharedPreferences(...)");
        this.sharedPreferences = sharedPreferences2;
        String str = "";
        String string = sharedPreferences2.getString("RECENTLY_USED_SOURCE_LANGUAGES", str);
        string = string == null ? str : string;
        String string2 = sharedPreferences2.getString("RECENTLY_USED_TARGET_LANGUAGES", str);
        str = string2 != null ? string2 : str;
        List<String> splitToMutableList = splitToMutableList(string, ";");
        this.sourceLanguages = splitToMutableList;
        List<String> splitToMutableList2 = splitToMutableList(str, ";");
        this.targetLanguages = splitToMutableList2;
        String R02 = C1194l.R0(splitToMutableList, (String) null, (String) null, (String) null, (b) null, 63);
        LibLogger.i("RecentlyUsedLanguage", "Recently used source languages: \"" + R02 + "\"");
        String R03 = C1194l.R0(splitToMutableList2, (String) null, (String) null, (String) null, (b) null, 63);
        LibLogger.i("RecentlyUsedLanguage", "Recently used target languages: \"" + R03 + "\"");
    }

    private final List<String> splitToMutableList(String str, String str2) {
        if (str.length() == 0) {
            return new ArrayList();
        }
        return C1194l.m1(n.K0(str, new String[]{str2}));
    }

    public final List<String> getRecentlyUsedLanguages(boolean z) {
        List<String> list;
        if (z) {
            list = this.sourceLanguages;
        } else {
            list = this.targetLanguages;
        }
        return C1194l.h1(list, 5);
    }

    public final void updateLanguages(String str, String str2) {
        j.e(str, "currentSourceLang");
        j.e(str2, "currentTargetLang");
        if (!str.equals("Auto")) {
            List<String> list = this.sourceLanguages;
            list.remove(str);
            list.add(0, str);
        }
        List<String> list2 = this.targetLanguages;
        list2.remove(str2);
        list2.add(0, str2);
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.putString("RECENTLY_USED_SOURCE_LANGUAGES", C1194l.R0(this.sourceLanguages, ";", (String) null, (String) null, (b) null, 62));
        edit.putString("RECENTLY_USED_TARGET_LANGUAGES", C1194l.R0(this.targetLanguages, ";", (String) null, (String) null, (b) null, 62));
        edit.apply();
    }
}
