package com.samsung.android.gallery.support.translation;

import V8.a;
import a6.g;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.support.type.ActionDetectedType;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.type.ColorDetectedType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TranslationManager {
    private static final boolean FEATURE_SCS_SEARCH = Features.isEnabled(Features.SUPPORT_SCS_SEARCH);
    private static volatile TranslationManager sInstance;
    private final ITranslation mCloudTranslator = new SCloudTranslation();
    private final ITranslation mIntelligentSearchTranslator;
    private final AtomicBoolean mLoaded = new AtomicBoolean(false);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DummyIntelSearchTranslation implements ITranslation {
        public DummyIntelSearchTranslation() {
            Log.e("TranslationManager", "IntelSearchTranslation not supported {" + Features.isEnabled(Features.SUPPORT_SCS_SEARCH) + '}');
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TranslationCategoryHolder {
        static final HashSet<String> set;

        static {
            HashSet<String> hashSet = new HashSet<>();
            set = hashSet;
            hashSet.add("Documents");
            hashSet.add("Documents All");
            hashSet.add("Expressions");
            hashSet.add("Other Documents");
            hashSet.add("Others");
            hashSet.add("Suggestion keywords");
            hashSet.add("Recent");
            hashSet.add("Things Scenery");
            hashSet.add("Things");
            hashSet.add("Scenery");
            hashSet.add("expressionstag");
            hashSet.add("scenetag");
            hashSet.add("recording_mode");
            hashSet.add("sef_file_type");
            hashSet.add(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
            hashSet.add("color_detected");
            hashSet.add("action_detected");
            hashSet.add("location://search/fileList/Category/Expressions");
            hashSet.add("location://search/fileList/Category/Documents");
            hashSet.add("location://search/fileList/Category/Scene");
            hashSet.add("location://search/fileList/Category/Things");
            hashSet.add("location://search/fileList/Category/Scenery");
            hashSet.add("location://search/fileList/Category/Activity");
        }

        public static boolean contains(String str) {
            return set.contains(str);
        }
    }

    private TranslationManager() {
        ITranslation iTranslation;
        if (Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            iTranslation = new IntelSearchTranslation();
        } else {
            iTranslation = new DummyIntelSearchTranslation();
        }
        this.mIntelligentSearchTranslator = iTranslation;
        SystemEnvironment.addObserver("TranslationManager", new g(10, this), 2);
    }

    public static TranslationManager getInstance() {
        if (sInstance == null) {
            synchronized (TranslationManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new TranslationManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public void onLocaleChanged(Context context) {
        if (this.mLoaded.get()) {
            Log.d("TranslationManager", "onLocaleChanged", Locale.getDefault());
            this.mCloudTranslator.loadMap(context);
        }
    }

    private String translate(String str, String str2) {
        if (FEATURE_SCS_SEARCH) {
            if ("color_detected".equals(str)) {
                return AppResources.getString(ColorDetectedType.getTitleResId(str2).intValue());
            }
            if ("action_detected".equals(str)) {
                return AppResources.getString(ActionDetectedType.getTitleResId(str2).intValue());
            }
        }
        String translate = this.mIntelligentSearchTranslator.translate(str2);
        if (TextUtils.isEmpty(translate)) {
            translate = (String) Optional.ofNullable(CategoryType.VISUAL_SEARCH_SUB_CATEGORY_STRING_MAP.get(str2)).map(new a(25)).orElse((Object) null);
        }
        if (TextUtils.isEmpty(translate)) {
            return this.mCloudTranslator.translate(str2);
        }
        return translate;
    }

    private ArrayList<String> translateMultiple(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> translateMultiple = this.mIntelligentSearchTranslator.translateMultiple(arrayList);
        if (translateMultiple != null) {
            Iterator it = ((ArrayList) translateMultiple.clone()).iterator();
            int i2 = 0;
            while (it.hasNext()) {
                String str = (String) it.next();
                if (TextUtils.isEmpty(str)) {
                    str = (String) Optional.ofNullable(CategoryType.VISUAL_SEARCH_SUB_CATEGORY_STRING_MAP.get(arrayList.get(i2))).map(new a(25)).orElse((Object) null);
                }
                if (TextUtils.isEmpty(str)) {
                    str = this.mCloudTranslator.translate(arrayList.get(i2));
                }
                arrayList2.add(i2, str);
                i2++;
            }
        }
        return arrayList2;
    }

    public String getEngString(Context context, String str) {
        String english = this.mCloudTranslator.getEnglish(str);
        if (english != null && english.length() != 0 && !english.equals(str)) {
            return english;
        }
        for (Map.Entry next : CategoryType.VISUAL_SEARCH_SUB_CATEGORY_STRING_MAP.entrySet()) {
            if (str.compareToIgnoreCase(context.getString(((Integer) next.getValue()).intValue()).replaceAll("-\n", "")) == 0) {
                return (String) next.getKey();
            }
        }
        return english;
    }

    public String getTranslatedString(String str, String str2) {
        if (TranslationCategoryHolder.contains(str)) {
            return translate(str, str2);
        }
        return str2;
    }

    public ArrayList<String> getTranslatedStringMultiple(String str, ArrayList<String> arrayList) {
        if (TranslationCategoryHolder.contains(str)) {
            return translateMultiple(arrayList);
        }
        return arrayList;
    }

    public boolean isTranslationMapLoaded() {
        return this.mLoaded.get();
    }

    public void loadTranslationMap(Context context) {
        if (!this.mLoaded.getAndSet(true)) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mIntelligentSearchTranslator.loadMap(context);
            this.mCloudTranslator.loadMap(context);
            A.a.A(new Object[]{Logger.getSimpleName((Object) this.mIntelligentSearchTranslator), Logger.getSimpleName((Object) this.mCloudTranslator), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadTranslationMap"), "TranslationManager");
        }
    }

    public void release() {
        Log.d("TranslationManager", "release");
        this.mCloudTranslator.releaseMap();
        this.mIntelligentSearchTranslator.releaseMap();
        this.mLoaded.set(false);
    }

    public String translateWithIntelligentSearch(String str) {
        String translate = this.mIntelligentSearchTranslator.translate(str);
        if (TextUtils.isEmpty(translate)) {
            return str;
        }
        return translate;
    }
}
