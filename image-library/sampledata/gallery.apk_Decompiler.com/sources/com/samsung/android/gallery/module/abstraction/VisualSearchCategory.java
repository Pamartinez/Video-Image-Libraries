package com.samsung.android.gallery.module.abstraction;

import X3.C0413a;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VisualSearchCategory {
    static final ArrayList<String> DISABLE_KEY_IN_PICKER = new ArrayList<>();
    static final ArrayList<String> ORDERED_KEY = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AbsCustomHolder {
        public String pack(Collection<String> collection) {
            return (String) collection.stream().map(new g(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        }

        public List<String> unpack(String str) {
            return (List) Arrays.stream(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new g(0)).collect(Collectors.toList());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CollectionOptional {
        static final String value = GalleryPreference.getInstance().loadString("CollectionOptional", (String) null);

        public static void computeIfPresent(String str, Consumer<String> consumer) {
            String str2 = value;
            if (str2 != null && str2.contains(str)) {
                consumer.accept(str);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CustomEnabledHolder extends AbsCustomHolder {
        static final String KEY;
        static final CustomEnabledHolder instance = new CustomEnabledHolder();
        final Object LOCK = new Object();
        final HashSet<String> mDisabledSet;

        static {
            PreferenceName preferenceName;
            if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                preferenceName = PreferenceName.COLLECTION_CAT_DISABLED;
            } else {
                preferenceName = PreferenceName.SEARCH_CAT_DISABLED;
            }
            KEY = preferenceName.key();
        }

        public CustomEnabledHolder() {
            List<String> list;
            HashSet<String> hashSet = new HashSet<>();
            this.mDisabledSet = hashSet;
            String loadString = GalleryPreference.getInstance().loadString(KEY, "MyTag");
            if (TextUtils.isEmpty(loadString)) {
                list = null;
            } else {
                list = unpack(loadString);
            }
            if (list != null && !list.isEmpty()) {
                hashSet.addAll(list);
            }
        }

        public static CustomEnabledHolder getInstance() {
            return instance;
        }

        public boolean isEnabled(String str) {
            boolean z;
            synchronized (this.LOCK) {
                z = !this.mDisabledSet.contains(str);
            }
            return z;
        }

        public boolean setEnabled(String str, boolean z) {
            boolean z3;
            String str2;
            synchronized (this.LOCK) {
                if (z) {
                    try {
                        z3 = this.mDisabledSet.remove(str);
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                } else {
                    z3 = this.mDisabledSet.add(str);
                }
                if (z3) {
                    str2 = pack(this.mDisabledSet);
                } else {
                    str2 = null;
                }
            }
            if (str2 == null) {
                return true;
            }
            GalleryPreference.getInstance().saveState(KEY, str2);
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CustomHolder extends AbsCustomHolder {
        static final String KEY;
        static final CustomHolder instance = new CustomHolder();
        final Object LOCK = new Object();
        final ArrayList<String> mCustomList;

        static {
            PreferenceName preferenceName;
            if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                preferenceName = PreferenceName.COLLECTION_CAT_ORDER;
            } else {
                preferenceName = PreferenceName.SEARCH_CAT_ORDER;
            }
            KEY = preferenceName.key();
        }

        public CustomHolder() {
            ArrayList<String> arrayList = new ArrayList<>();
            this.mCustomList = arrayList;
            String loadString = GalleryPreference.getInstance().loadString(KEY, (String) null);
            if (TextUtils.isEmpty(loadString)) {
                arrayList.addAll(VisualSearchCategory.ORDERED_KEY);
                return;
            }
            arrayList.addAll(unpack(loadString));
            ensureValidity(arrayList);
        }

        public static CustomHolder getInstance() {
            return instance;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$ensureValidity$0(String str) {
            return !VisualSearchCategory.ORDERED_KEY.contains(str);
        }

        public void ensureValidity(List<String> list) {
            Iterator<String> it = VisualSearchCategory.ORDERED_KEY.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!list.contains(next)) {
                    list.add(next);
                }
            }
            if (list.removeIf(new h(0))) {
                Log.e("VisualSearchCategory", "ensureValidity invalid key removed");
            }
        }

        public List<String> listOf() {
            ArrayList arrayList;
            synchronized (this.LOCK) {
                arrayList = new ArrayList(this.mCustomList);
            }
            return arrayList;
        }

        public boolean update(List<String> list) {
            ensureValidity(list);
            String pack = pack(list);
            if (pack.equals(pack(listOf()))) {
                Log.d("VisualSearchCategory", "Custom#update skip");
                return false;
            }
            synchronized (this.LOCK) {
                this.mCustomList.clear();
                this.mCustomList.addAll(list);
            }
            GalleryPreference.getInstance().saveState(KEY, pack);
            return true;
        }
    }

    static {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            addStories();
            addScreenShot();
            addPeopleTag();
            addLocation();
            addDocuments();
            CollectionOptional.computeIfPresent("Activity", new f(0));
            CollectionOptional.computeIfPresent("Shot types", new f(1));
            CollectionOptional.computeIfPresent("My tags", new f(2));
        } else if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            addMyQuery();
            addPeopleTag();
            addLocation();
            addDocuments();
            addActivity();
            addShotMode();
            addMyTag();
        } else {
            addMyTag();
            addPeopleTag();
            addLocation();
            addShotMode();
            addDocuments();
            addOtherScene();
        }
    }

    /* access modifiers changed from: private */
    public static void addActivity() {
        ORDERED_KEY.add("location://search/fileList/Category/Activity");
    }

    private static void addDocuments() {
        if (!Features.isEnabled(Features.IS_JDM)) {
            ORDERED_KEY.add("location://search/fileList/Category/Documents");
        }
    }

    private static void addLocation() {
        if (Features.isEnabled(Features.SUPPORT_LOCATION)) {
            ORDERED_KEY.add("location://search/fileList/Category/Location");
        }
    }

    private static void addMyQuery() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY) {
            ORDERED_KEY.add("location://search/fileList/Category/MyQuery");
        }
    }

    /* access modifiers changed from: private */
    public static void addMyTag() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SearchTagInRecommendationView) || PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            ORDERED_KEY.add("location://search/fileList/Category/MyTag");
        }
    }

    private static void addOtherScene() {
        ORDERED_KEY.add("location://search/fileList/Category/Scene");
    }

    private static void addPeopleTag() {
        if (!Features.isEnabled(Features.IS_JDM) && Features.isEnabled(Features.SUPPORT_FACE_ENGINE)) {
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                ORDERED_KEY.add("location://search/fileList/Category/PeopleAndPets");
            } else {
                ORDERED_KEY.add("location://search/fileList/Category/People");
            }
        }
    }

    private static void addScreenShot() {
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
            ORDERED_KEY.add("location://search/fileList/Category/ScreenShot");
        }
    }

    /* access modifiers changed from: private */
    public static void addShotMode() {
        ORDERED_KEY.add("location://search/fileList/Category/ShotMode");
    }

    private static void addStories() {
        ORDERED_KEY.add("location://search/fileList/Category/Stories");
        DISABLE_KEY_IN_PICKER.add("location://search/fileList/Category/Stories");
    }

    public static boolean isEnabled(String str) {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return CustomEnabledHolder.getInstance().isEnabled(str);
        }
        return true;
    }

    public static void iterate(boolean z, BiConsumer<Integer, String> biConsumer) {
        int i2 = 0;
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            CustomHolder.getInstance().listOf().forEach(new C0413a(z, (BiConsumer) biConsumer, new AtomicInteger(0)));
            return;
        }
        Iterator<String> it = ORDERED_KEY.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (support(next, z)) {
                biConsumer.accept(Integer.valueOf(i2), next);
                i2++;
            }
        }
    }

    public static boolean iterateIfSuccess(boolean z, BiFunction<Integer, String, Boolean> biFunction) {
        List<String> list = ORDERED_KEY;
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            list = CustomHolder.getInstance().listOf();
        }
        int i2 = 0;
        for (String str : list) {
            if (support(str, z)) {
                int i7 = i2 + 1;
                if (!biFunction.apply(Integer.valueOf(i2), str).booleanValue()) {
                    return false;
                }
                i2 = i7;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$iterate$3(boolean z, BiConsumer biConsumer, AtomicInteger atomicInteger, String str) {
        if (support(str, z)) {
            biConsumer.accept(Integer.valueOf(atomicInteger.getAndIncrement()), str);
        }
    }

    public static List<String> listOf() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return CustomHolder.getInstance().listOf();
        }
        return ORDERED_KEY;
    }

    public static boolean setEnabled(String str, boolean z) {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return CustomEnabledHolder.getInstance().setEnabled(str, z);
        }
        return false;
    }

    public static boolean support(String str, boolean z) {
        if (z) {
            return !DISABLE_KEY_IN_PICKER.contains(str);
        }
        return true;
    }

    public static boolean update(List<String> list) {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return CustomHolder.getInstance().update(list);
        }
        return false;
    }
}
