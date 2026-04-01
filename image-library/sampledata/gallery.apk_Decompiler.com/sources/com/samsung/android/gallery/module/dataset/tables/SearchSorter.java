package com.samsung.android.gallery.module.dataset.tables;

import D6.a;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.type.SubCategoryType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.Comparator;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSorter extends AbstractSorter {
    private static final HashMap<String, CategorySorter> SORTER_MAP = new HashMap<>();
    private final CategorySorter mCategorySorter;
    private final ArrayMap<String, Integer> mColumnMap = new ArrayMap<>();
    protected final Comparator<AbstractSorter.SortData> mComparator = new a(13);
    protected final Comparator<AbstractSorter.SortData> mDocumentComparator = new a(16);
    protected final Comparator<AbstractSorter.SortData> mOrderTitleCountComparator = new a(14);
    protected final Comparator<AbstractSorter.SortData> mTitleCountComparator = new a(15);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CategorySorter {
        String mSortValue1;
        String mSortValue2;
        String mSortValue3;

        public /* synthetic */ CategorySorter(SearchSorter searchSorter, int i2) {
            this();
        }

        public Comparator<AbstractSorter.SortData> getComparator() {
            return SearchSorter.this.mComparator;
        }

        public String[] parseSortingValue(Cursor cursor) {
            String string = cursor.getString(SearchSorter.this.getColumnIndex("__subCategory", cursor));
            String translatedString = TranslationManager.getInstance().getTranslatedString(SearchSorter.this.mLocationKey, string);
            this.mSortValue1 = translatedString;
            this.mSortValue2 = string;
            return new String[]{translatedString, string};
        }

        private CategorySorter() {
            this.mSortValue1 = null;
            this.mSortValue2 = null;
            this.mSortValue3 = null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CreaturePrioritySorter extends PeopleAndPetsSorter {
        public /* synthetic */ CreaturePrioritySorter(SearchSorter searchSorter, int i2) {
            this();
        }

        public String[] parseSortingValue(Cursor cursor) {
            String str;
            String str2;
            int i2;
            String string = cursor.getString(SearchSorter.this.getColumnIndex("__creatureName", cursor));
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && TextUtils.isEmpty(string)) {
                String string2 = cursor.getString(SearchSorter.this.getColumnIndex("__creatureRelationship", cursor));
                if (!TextUtils.isEmpty(string2)) {
                    string = RelationshipKeySet.getRelationshipName(AppResources.getAppContext(), string2);
                }
            }
            String valueOf = String.valueOf(cursor.getInt(SearchSorter.this.getColumnIndex("__count", cursor)));
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
                str2 = String.valueOf(cursor.getInt(SearchSorter.this.getColumnIndex("__creatureFaceRecommendedID", cursor)));
                int f = SearchSorter.this.getColumnIndex("__creatureType", cursor);
                if (f < 0) {
                    i2 = 0;
                } else {
                    i2 = cursor.getInt(f);
                }
                str = String.valueOf(i2);
            } else {
                str = null;
                str2 = null;
            }
            return new String[]{string, valueOf, str2, str, null};
        }

        private CreaturePrioritySorter() {
            super(SearchSorter.this, 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DocumentSorter extends CategorySorter {
        public /* synthetic */ DocumentSorter(SearchSorter searchSorter, int i2) {
            this();
        }

        public Comparator<AbstractSorter.SortData> getComparator() {
            return SearchSorter.this.mDocumentComparator;
        }

        public String[] parseSortingValue(Cursor cursor) {
            String string = cursor.getString(SearchSorter.this.getColumnIndex("__subCategory", cursor));
            this.mSortValue1 = TranslationManager.getInstance().getTranslatedString(SearchSorter.this.mLocationKey, string);
            String string2 = cursor.getString(SearchSorter.this.getColumnIndex("__count", cursor));
            this.mSortValue2 = string2;
            return new String[]{this.mSortValue1, string2, string};
        }

        private DocumentSorter() {
            super(SearchSorter.this, 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PeopleAndPetsSorter extends PeopleSorter {
        public /* synthetic */ PeopleAndPetsSorter(SearchSorter searchSorter, int i2) {
            this();
        }

        public Comparator<AbstractSorter.SortData> getComparator() {
            return SearchSorter.this.mTitleCountComparator;
        }

        private PeopleAndPetsSorter() {
            super(SearchSorter.this, 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PeopleSorter extends CategorySorter {
        public /* synthetic */ PeopleSorter(SearchSorter searchSorter, int i2) {
            this();
        }

        public Comparator<AbstractSorter.SortData> getComparator() {
            return SearchSorter.this.mTitleCountComparator;
        }

        public String[] parseSortingValue(Cursor cursor) {
            String string = cursor.getString(SearchSorter.this.getColumnIndex("__creatureName", cursor));
            this.mSortValue1 = string;
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && TextUtils.isEmpty(string)) {
                String string2 = cursor.getString(SearchSorter.this.getColumnIndex("__creatureRelationship", cursor));
                if (!TextUtils.isEmpty(string2)) {
                    this.mSortValue1 = RelationshipKeySet.getRelationshipName(AppResources.getAppContext(), string2);
                }
            }
            String string3 = cursor.getString(SearchSorter.this.getColumnIndex("__count", cursor));
            this.mSortValue2 = string3;
            return new String[]{this.mSortValue1, string3};
        }

        private PeopleSorter() {
            super(SearchSorter.this, 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ShotModeSorter extends CategorySorter {
        public /* synthetic */ ShotModeSorter(SearchSorter searchSorter, int i2) {
            this();
        }

        private String getShotModeTitle(Cursor cursor, String str) {
            int i2 = cursor.getInt(SearchSorter.this.getColumnIndex("__sefFileType", cursor));
            int i7 = cursor.getInt(SearchSorter.this.getColumnIndex("__recordingMode", cursor));
            ShotMode byType = ShotModeList.getInstance().getByType(str);
            if (!TextUtils.isEmpty(str)) {
                byType = ShotModeList.getInstance().getByType(str);
            } else if (i2 > 0) {
                byType = ShotModeList.getInstance().getBySefValue(i2);
            } else if (i7 > 0) {
                byType = ShotModeList.getInstance().getByRecMode(i7);
            }
            if (byType != null) {
                return AppResources.getString(byType.titleRes);
            }
            return str;
        }

        public Comparator<AbstractSorter.SortData> getComparator() {
            return SearchSorter.this.mTitleCountComparator;
        }

        public String[] parseSortingValue(Cursor cursor) {
            this.mSortValue1 = getShotModeTitle(cursor, cursor.getString(SearchSorter.this.getColumnIndex("__subCategory", cursor)));
            String string = cursor.getString(SearchSorter.this.getColumnIndex("__count", cursor));
            this.mSortValue2 = string;
            return new String[]{this.mSortValue1, string};
        }

        private ShotModeSorter() {
            super(SearchSorter.this, 0);
        }
    }

    public SearchSorter(String str) {
        super(str);
        this.mCategorySorter = createCategorySorter(str);
    }

    /* access modifiers changed from: private */
    public int getColumnIndex(String str, Cursor cursor) {
        if (this.mColumnMap.containsKey(str)) {
            return this.mColumnMap.get(str).intValue();
        }
        int columnIndex = cursor.getColumnIndex(str);
        this.mColumnMap.put(str, Integer.valueOf(columnIndex));
        return columnIndex;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ CategorySorter lambda$createCategorySorter$4(String str) {
        if (LocationKey.isSearchCategoryPeopleMatch(str)) {
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                return new CreaturePrioritySorter(this, 0);
            }
            return new PeopleSorter(this, 0);
        } else if (LocationKey.isSearchCategoryPeopleAndPetsMatch(str)) {
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                return new CreaturePrioritySorter(this, 0);
            }
            return new PeopleAndPetsSorter(this, 0);
        } else if (LocationKey.isSearchSuggestedCreature(str) || LocationKey.isSearchCategoryCreatureSelectMatch(str) || "location://search/fileList/Category/CreatureMultiPick".equals(str)) {
            return new CreaturePrioritySorter(this, 0);
        } else {
            if (LocationKey.isSearchCategoryPeopleHideMatch(str) || LocationKey.isSearchCategoryPeopleSelectMatch(str) || LocationKey.isSearchCategoryPeopleAndPetsHideMatch(str) || LocationKey.isSearchCategoryHiddenPeopleMatch(str) || LocationKey.isSearchCategoryHiddenPeopleAndPetsMatch(str) || LocationKey.isSearchCategoryPeopleSelectForRelation(str) || LocationKey.isSearchPeopleClusterMatch(str) || LocationKey.isSelectMeAll(str)) {
                return new PeopleSorter(this, 0);
            }
            if ("location://search/fileList/Category/ShotMode".equals(str)) {
                return new ShotModeSorter(this, 0);
            }
            if ("location://search/fileList/Category/Documents".equals(str)) {
                return new DocumentSorter(this, 0);
            }
            return new CategorySorter(this, 0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$0(AbstractSorter.SortData sortData, AbstractSorter.SortData sortData2) {
        String str = ((String[]) sortData.getSortingValue())[0];
        String str2 = ((String[]) sortData2.getSortingValue())[0];
        String str3 = ((String[]) sortData.getSortingValue())[1];
        if ("video".equals(((String[]) sortData2.getSortingValue())[1]) || TextUtils.isEmpty(str)) {
            return 1;
        }
        if ("video".equals(str3) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        return str.compareToIgnoreCase(str2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$1(AbstractSorter.SortData sortData, AbstractSorter.SortData sortData2) {
        int compare;
        String str = ((String[]) sortData.getSortingValue())[0];
        String str2 = ((String[]) sortData2.getSortingValue())[0];
        String str3 = ((String[]) sortData.getSortingValue())[1];
        String str4 = ((String[]) sortData2.getSortingValue())[1];
        String str5 = ((String[]) sortData.getSortingValue())[4];
        String str6 = ((String[]) sortData2.getSortingValue())[4];
        if (str5 != null && str6 != null && (compare = Double.compare(Double.parseDouble(str5), Double.parseDouble(str6))) != 0) {
            return compare;
        }
        if (str3 == null || str4 == null) {
            return str.compareToIgnoreCase(str2);
        }
        int i2 = -Double.compare(Double.parseDouble(str3), Double.parseDouble(str4));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                if (TextUtils.isEmpty(str)) {
                    return 1;
                }
                return -1;
            }
        } else if (i2 == 0) {
            return str.compareToIgnoreCase(str2);
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$2(AbstractSorter.SortData sortData, AbstractSorter.SortData sortData2) {
        String str = ((String[]) sortData.getSortingValue())[0];
        String str2 = ((String[]) sortData2.getSortingValue())[0];
        String str3 = ((String[]) sortData.getSortingValue())[1];
        String str4 = ((String[]) sortData2.getSortingValue())[1];
        if (str3 == null || str4 == null) {
            return str.compareToIgnoreCase(str2);
        }
        int i2 = -Double.compare(Double.parseDouble(str3), Double.parseDouble(str4));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                if (TextUtils.isEmpty(str)) {
                    return 1;
                }
                return -1;
            }
        } else if (i2 == 0) {
            return str.compareToIgnoreCase(str2);
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$3(AbstractSorter.SortData sortData, AbstractSorter.SortData sortData2) {
        String str = ((String[]) sortData.getSortingValue())[0];
        String str2 = ((String[]) sortData2.getSortingValue())[0];
        String str3 = ((String[]) sortData.getSortingValue())[1];
        String str4 = ((String[]) sortData2.getSortingValue())[1];
        String str5 = ((String[]) sortData.getSortingValue())[2];
        String str6 = ((String[]) sortData2.getSortingValue())[2];
        if (str3 == null || str4 == null) {
            return str.compareToIgnoreCase(str2);
        }
        int i2 = -Double.compare(Double.parseDouble(str3), Double.parseDouble(str4));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                if (TextUtils.isEmpty(str)) {
                    return 1;
                }
                return -1;
            }
        } else if (SubCategoryType.getOtherDocumentsName().equals(str5)) {
            return 1;
        } else {
            if (SubCategoryType.getOtherDocumentsName().equals(str6)) {
                return -1;
            }
            if (i2 == 0) {
                return str.compareToIgnoreCase(str2);
            }
        }
        return i2;
    }

    public CategorySorter createCategorySorter(String str) {
        return SORTER_MAP.computeIfAbsent(str, new A5.a(28, this));
    }

    public void sort(Cursor cursor) {
        TimeTickLog timeTickLog = new TimeTickLog("Category sorting : " + this.mLocationKey);
        this.mColumnMap.clear();
        synchronized (this.mSortedList) {
            try {
                this.mSortedList.clear();
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            this.mSortedList.add(new AbstractSorter.SortData(cursor.getPosition(), this.mCategorySorter.parseSortingValue(cursor)));
                        } while (cursor.moveToNext());
                    }
                }
                this.mSortedList.sort(this.mCategorySorter.getComparator());
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        timeTickLog.tock(0);
    }
}
