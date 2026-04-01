package com.samsung.android.gallery.bixby.bixby.util;

import N2.j;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.search.SearchInfo;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionHandlerUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ComparatorName {
        boolean compareName(String str, String str2);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.samsung.android.gallery.bixby.bixby.util.ActionHandlerUtil$ComparatorName] */
    private int checkExactName(String str, ArrayList<String> arrayList) {
        return findAndChoose(str, arrayList, new Object());
    }

    private int checkNameAfterDeleteSpace(String str, ArrayList<String> arrayList) {
        return findAndChoose(str, arrayList, new b(this, 2));
    }

    private int checkNameAfterDeleteSpecialChar(String str, ArrayList<String> arrayList) {
        return findAndChoose(str, arrayList, new b(this, 1));
    }

    private int checkNameUsingPattern(String str, ArrayList<String> arrayList) {
        return findAndChoose(str, arrayList, new b(this, 0));
    }

    /* access modifiers changed from: private */
    public boolean compareName(String str, String str2) {
        return str2.replace(" ", "").equalsIgnoreCase(str.replace(" ", ""));
    }

    /* access modifiers changed from: private */
    public boolean compareNameExceptSpecialChar(String str, String str2) {
        return replaceSpecialCharacter(str2.replace(" ", "")).equalsIgnoreCase(replaceSpecialCharacter(str.replace(" ", "")));
    }

    /* access modifiers changed from: private */
    public boolean compareNameUsingPattern(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.bxe("ActionHandlerUtil", j.d("[", str, "][", str2, "] : invalid"));
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        String lowerCase2 = str2.toLowerCase(Locale.getDefault());
        String[] split = lowerCase.split("[\\s\\t]");
        if (split.length == 0) {
            Log.bxe("ActionHandlerUtil", "[] : invalid");
            return false;
        }
        boolean z = true;
        for (String contains : split) {
            z &= lowerCase2.contains(contains);
        }
        return z;
    }

    private int findAndChoose(String str, ArrayList<String> arrayList, ComparatorName comparatorName) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = arrayList.get(i2);
            if (!TextUtils.isEmpty(str2) && comparatorName.compareName(str, str2)) {
                return i2;
            }
        }
        return -1;
    }

    private String replaceSpecialCharacter(String str) {
        StringBuilder sb2 = new StringBuilder();
        for (char c5 : str.toCharArray()) {
            if (!StringCompat.isSpecialCharacter(c5)) {
                sb2.append(c5);
            }
        }
        return sb2.toString();
    }

    public int getIndexOfMatched(String str, ArrayList<String> arrayList, boolean z) {
        int checkExactName = checkExactName(str, arrayList);
        if (checkExactName != -1) {
            return checkExactName;
        }
        int checkNameAfterDeleteSpace = checkNameAfterDeleteSpace(str, arrayList);
        if (checkNameAfterDeleteSpace != -1) {
            return checkNameAfterDeleteSpace;
        }
        if (z) {
            return checkNameUsingPattern(str, arrayList);
        }
        return checkNameAfterDeleteSpecialChar(str, arrayList);
    }

    public String getKeywordString(SearchInfo searchInfo) {
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        arrayList.add(searchInfo.getTime());
        if (searchInfo.isCountryFirst()) {
            arrayList.add(searchInfo.getCountry());
            arrayList.add(searchInfo.getLocation());
        } else {
            arrayList.add(searchInfo.getLocation());
            arrayList.add(searchInfo.getCountry());
        }
        arrayList.add(searchInfo.getPoi());
        arrayList.add(searchInfo.getTitle());
        arrayList.add(searchInfo.getTag());
        arrayList.add(searchInfo.getContentType());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!str.isEmpty()) {
                sb2.append(str);
                sb2.append(" ");
            }
        }
        return sb2.toString();
    }

    public String getTranslatedName(Context context, String str) {
        if ("animatedgif".equals(str)) {
            return "GIF";
        }
        Integer num = BixbyKey.CONTENT_TYPE_NAME.get(str);
        if (num != null) {
            return context.getString(num.intValue());
        }
        return "";
    }
}
