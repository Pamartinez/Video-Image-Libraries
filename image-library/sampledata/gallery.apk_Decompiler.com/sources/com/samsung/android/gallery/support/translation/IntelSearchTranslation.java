package com.samsung.android.gallery.support.translation;

import A.a;
import N2.j;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import com.samsung.android.gallery.support.search.IntelligentSearchConstants;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class IntelSearchTranslation implements ITranslation {
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> sTranslatedMap = new ConcurrentHashMap<>();
    private ContentResolver mContentResolver;
    private boolean mLoaded = false;
    private Resources mResources;

    private ContentResolver getContentResolver() {
        if (this.mContentResolver == null) {
            this.mContentResolver = AppResources.getAppContext().getContentResolver();
        }
        return this.mContentResolver;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, java.util.function.Function] */
    private static ConcurrentHashMap<String, String> getCurrentLanguageMap() {
        return sTranslatedMap.computeIfAbsent(Locale.getDefault().getLanguage(), new Object());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ConcurrentHashMap lambda$getCurrentLanguageMap$1(String str) {
        return new ConcurrentHashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$translateFromApiCallMultiple$0(Bundle bundle, ArrayList arrayList, ConcurrentHashMap concurrentHashMap, String str, Integer num) {
        String parseResult = parseResult(bundle, str);
        arrayList.set(num.intValue(), parseResult);
        if (parseResult != null) {
            concurrentHashMap.put(str, parseResult);
        }
    }

    private static String parseResult(Bundle bundle, String str) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null || stringArrayList.size() <= 0) {
            return null;
        }
        return stringArrayList.get(0);
    }

    private String translateFromApiCall(String str) {
        ConcurrentHashMap<String, String> currentLanguageMap = getCurrentLanguageMap();
        if (currentLanguageMap.containsKey(str)) {
            return currentLanguageMap.get(str);
        }
        try {
            String parseResult = parseResult(getContentResolver().call(IntelligentSearchConstants.MEDIA_URI, "get_alias_name_by_id", str, (Bundle) null), str);
            if (parseResult == null) {
                return parseResult;
            }
            currentLanguageMap.put(str, parseResult);
            return parseResult;
        } catch (Exception e) {
            j.s(e, new StringBuilder("translateFromApiCall failed \n e="), "IntelSearchTranslation");
            return null;
        }
    }

    private ArrayList<String> translateFromApiCallMultiple(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(new String[arrayList.size()]));
        ConcurrentHashMap<String, String> currentLanguageMap = getCurrentLanguageMap();
        HashMap hashMap = new HashMap();
        Iterator<String> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String next = it.next();
            if (currentLanguageMap.containsKey(next)) {
                arrayList2.set(i2, currentLanguageMap.get(next));
            } else {
                hashMap.put(next, Integer.valueOf(i2));
            }
            i2++;
        }
        if (hashMap.isEmpty()) {
            return arrayList2;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putStringArray("gallery_alias_ids", (String[]) hashMap.keySet().toArray(new String[0]));
            hashMap.forEach(new b(getContentResolver().call(IntelligentSearchConstants.MEDIA_URI, "get_alias_name_by_id", (String) null, bundle), arrayList2, currentLanguageMap));
            return arrayList2;
        } catch (Exception e) {
            j.s(e, new StringBuilder("translateFromApiCallMultiple failed \n e="), "IntelSearchTranslation");
            return arrayList2;
        }
    }

    private String translateFromResources(String str) {
        Resources resources = this.mResources;
        if (resources != null) {
            try {
                return this.mResources.getString(resources.getIdentifier(str.toLowerCase(), "string", BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES));
            } catch (Resources.NotFoundException unused) {
                Log.w("IntelSearchTranslation", "translateFromResources failed ");
                return null;
            }
        } else if (this.mLoaded) {
            return null;
        } else {
            Log.w("IntelSearchTranslation", "translateFromResources failed. not loaded");
            return null;
        }
    }

    public void loadMap(Context context) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
            this.mContentResolver = context.getContentResolver();
        } else {
            try {
                context.getContentResolver().call(IntelligentSearchConstants.PROVIDER_URI, "init", (String) null, (Bundle) null);
                this.mResources = context.getPackageManager().getResourcesForApplication(BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
                StringBuilder sb2 = new StringBuilder("loadMap {");
                if (this.mResources != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(z);
                sb2.append("} +");
                sb2.append(System.currentTimeMillis() - currentTimeMillis);
                Log.d("IntelSearchTranslation", sb2.toString());
            } catch (PackageManager.NameNotFoundException | IllegalArgumentException | NullPointerException e) {
                a.s(e, new StringBuilder("loadMap failed. e="), "IntelSearchTranslation");
            }
        }
        this.mLoaded = true;
    }

    public void releaseMap() {
        this.mLoaded = false;
        this.mResources = null;
        this.mContentResolver = null;
    }

    public String translate(String str) {
        if (str == null) {
            return null;
        }
        if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
            return translateFromApiCall(str);
        }
        return translateFromResources(str);
    }

    public ArrayList<String> translateMultiple(ArrayList<String> arrayList) {
        if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
            return translateFromApiCallMultiple(arrayList);
        }
        return null;
    }
}
