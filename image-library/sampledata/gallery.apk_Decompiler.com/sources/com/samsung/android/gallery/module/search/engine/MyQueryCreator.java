package com.samsung.android.gallery.module.search.engine;

import Ad.C0720a;
import B5.e;
import D6.a;
import S3.d;
import T8.C0578a;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MyQueryCreator {
    public static void createTop5(Blackboard blackboard, MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            String top5Candidates = getTop5Candidates(blackboard, mediaItem.getSubCategory(), "recommended_id", CreatureData.of(mediaItem).creatureName, mediaItem.getCount());
            String subCategory = mediaItem.getSubCategory();
            if (!TextUtils.isEmpty(top5Candidates)) {
                subCategory = C0212a.B(subCategory, GlobalPostProcInternalPPInterface.SPLIT_REGEX, top5Candidates);
            }
            createTop5(subCategory);
        }
    }

    private static ArrayList<FilterResultsEntity> filterCreatureEntity(ArrayList<FilterResultsEntity> arrayList) {
        return (ArrayList) arrayList.stream().filter(new d(15)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private static ArrayList<String> getFacetTermsFields() {
        String str;
        ArrayList<String> arrayList = new ArrayList<>();
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            str = "recommended_id";
        } else {
            str = "persontag";
        }
        arrayList.add(str);
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            arrayList.add("pet_recommended_id");
        }
        return arrayList;
    }

    public static String getTop5Candidates(Blackboard blackboard, String str, String str2, String str3, int i2) {
        Context context;
        String str4;
        if (!PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE || (context = (Context) blackboard.read("data://app_context")) == null) {
            return "";
        }
        Bundle filterResultsBundle = SearchEngineFactory.create(context).getFilterResultsBundle(new SearchFilter.Builder(str).term(str2).setMaxFacetTermsSize(10).setFacetTermsFields(getFacetTermsFields()).build(context), i2);
        if (filterResultsBundle != null) {
            str4 = filterResultsBundle.getString("FilterResults");
        } else {
            str4 = null;
        }
        FilterResultsEntry build = new FilterResultsEntry.Builder(blackboard).setMinCreatureItemCount(1).extract(str4, str3, i2, false, (LinkedHashMap<String, String>) null).setMaxFilterNum(20).setSortByCount(true).build();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Iterator<FilterResultsEntity> it = sortCreatureEntity(build).iterator();
        int i7 = 0;
        while (it.hasNext()) {
            FilterResultsEntity next = it.next();
            i7++;
            if (i7 > 5) {
                break;
            }
            stringJoiner.add(next.getMediaItem().getSubCategory());
        }
        String stringJoiner2 = stringJoiner.toString();
        Log.v("NewMyQuery", "getTop5Candidates : " + stringJoiner2);
        return stringJoiner2;
    }

    private static boolean isCreatureEntityValid(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.getCount() >= 30 || CreatureData.hasName(mediaItem)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$createTop5$0(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$filterCreatureEntity$2(FilterResultsEntity filterResultsEntity) {
        if (!filterResultsEntity.isCreature() || !isCreatureEntityValid(filterResultsEntity.getMediaItem())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortCreatureEntity$1(FilterResultsEntity filterResultsEntity, FilterResultsEntity filterResultsEntity2) {
        int compare;
        MediaItem mediaItem = filterResultsEntity.getMediaItem();
        MediaItem mediaItem2 = filterResultsEntity2.getMediaItem();
        boolean hasName = CreatureData.hasName(mediaItem);
        boolean hasName2 = CreatureData.hasName(mediaItem2);
        if (!hasName && !hasName2) {
            compare = Integer.compare(mediaItem.getCount(), mediaItem2.getCount());
        } else if (hasName && hasName2) {
            compare = Integer.compare(mediaItem.getCount(), mediaItem2.getCount());
        } else if (hasName) {
            return -1;
        } else {
            return 1;
        }
        return -compare;
    }

    public static ArrayList<FilterResultsEntity> sortCreatureEntity(FilterResultsEntry filterResultsEntry) {
        ArrayList<FilterResultsEntity> arrayList = new ArrayList<>();
        if (!PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE || filterResultsEntry == null) {
            return arrayList;
        }
        return (ArrayList) filterCreatureEntity(filterResultsEntry.getAllItems()).stream().sorted(new a(11)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public static void createTop5(String str) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            String[] strArr = (String[]) Arrays.stream(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).distinct().toArray(new C0578a(9));
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            Arrays.stream(strArr).forEach(new e(stringJoiner, 2));
            SearchMyQuery.getInstance().insert("Top5", strArr.length, stringJoiner.toString(), SearchMyQuery.CREATE_TYPE.TOP5, (MediaItem) null);
        }
    }
}
