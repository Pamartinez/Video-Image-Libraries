package com.samsung.android.gallery.module.details;

import A.a;
import A4.C0371f;
import Fa.C0571z;
import N2.j;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsRelatedDataLoader extends DetailsDataLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreatureGroupConverter {
        private final ArrayList<MediaItem> creature;
        private StringJoiner gidJoiner;
        private ArrayList<List<Long>> groupIdsPerCreature;

        public CreatureGroupConverter(ArrayList<MediaItem> arrayList) {
            this.creature = arrayList;
            Collections.shuffle(arrayList);
        }

        private ArrayList<Long> getFaceGroupIds(MediaItem mediaItem) {
            String subCategory = mediaItem.getSubCategory();
            long identityId = IdentityCreatureUtil.getIdentityId(subCategory);
            ArrayList<Long> arrayList = new ArrayList<>();
            if (IdentityCreatureUtil.isAssignedCreature(subCategory)) {
                SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
                String rawQuery = getRawQuery(mediaItem, identityId);
                Cursor rawQuery2 = secMpQueryExecutor.rawQuery(rawQuery, "gidFromCreature:" + identityId);
                if (rawQuery2 != null) {
                    while (rawQuery2.moveToNext()) {
                        try {
                            arrayList.add(Long.valueOf(rawQuery2.getLong(0)));
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                }
                if (rawQuery2 != null) {
                    rawQuery2.close();
                }
                return arrayList;
            }
            arrayList.add(Long.valueOf(identityId));
            return arrayList;
            throw th;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$invoke$0(Long l) {
            this.gidJoiner.add(String.valueOf(l));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$invoke$1(MediaItem mediaItem) {
            ArrayList<Long> faceGroupIds = getFaceGroupIds(mediaItem);
            faceGroupIds.forEach(new d(1, this));
            this.groupIdsPerCreature.add(faceGroupIds);
        }

        public String getGroupIds() {
            return this.gidJoiner.toString();
        }

        public String getRawQuery(MediaItem mediaItem, long j2) {
            if (mediaItem.isPeople()) {
                return a.f("select distinct group_id from faces where person_id=", j2);
            }
            return a.f("select distinct group_id from cluster_rect where cluster_info_id=", j2);
        }

        public CreatureGroupConverter invoke() {
            this.gidJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            this.groupIdsPerCreature = new ArrayList<>();
            this.creature.stream().limit(2).forEach(new d(0, this));
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$queryRelatedCreatureFiles$0(String str, ArrayList arrayList, MediaItem mediaItem, QueryParams queryParams) {
        QueryParams queryParams2;
        queryParams.excludeScreenShots();
        queryParams.setStorageTypes(QueryParams.DbStorageType.LocalOnly);
        queryParams.mCreatureFaceGroupIds = str;
        if (arrayList.size() == 1) {
            long[] yearsAgo = TimeUtil.getYearsAgo(mediaItem.getDateTaken(), 1);
            queryParams.setCreationTimeLimit(yearsAgo[0] - 302400000, yearsAgo[1] + 302400000);
            queryParams2 = queryParams;
        } else {
            queryParams.mMinFaceCount = 1;
            queryParams2 = queryParams;
            queryParams2.excludeTakenTime(mediaItem.getDateTaken(), -12, 12);
        }
        queryParams2.setLimit(4).setOrder("__count desc, random()").excludeScreenShots().setExcludeFileIds(String.valueOf(mediaItem.getFileId()));
        queryParams2.printQuery();
    }

    public static void loadRelated(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        ThreadUtil.assertBgThread("loadRelated");
        if (support(mediaItem)) {
            DetailsData.of(mediaItem).setRelatedTitle(loadTitle(mediaItem));
            DetailsData.of(mediaItem).setRelated(loadRelated(mediaItem));
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.RELATED);
        }
    }

    private static String loadTitle(MediaItem mediaItem) {
        ArrayList<MediaItem> people = DetailsData.of(mediaItem).getPeople();
        if (people.size() == 1) {
            String str = CreatureData.of(people.get(0)).creatureName;
            if (TextUtils.isEmpty(str)) {
                return AppResources.getQuantityString(R$plurals.unname_year_ago, 1, 1);
            }
            return AppResources.getQuantityString(R$plurals.name_year_ago, 1, str, 1);
        } else if (people.size() > 1) {
            return AppResources.getString(R$string.more_times_together);
        } else {
            return "";
        }
    }

    private static Cursor queryFiles(MediaItem mediaItem, ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        return queryRelatedCreatureFiles(mediaItem, arrayList, new CreatureGroupConverter(arrayList).invoke().getGroupIds(), "mp://People/files");
    }

    private static Cursor queryRelatedCreatureFiles(MediaItem mediaItem, ArrayList<MediaItem> arrayList, String str, String str2) {
        return DbCompat.query(str2, new C0371f((Object) str, (Object) arrayList, mediaItem, 22));
    }

    public static void reloadRelated(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        ThreadUtil.assertBgThread("reloadRelated");
        if (support(mediaItem)) {
            DetailsData.of(mediaItem).setRelatedTitle(loadTitle(mediaItem));
            DetailsData.of(mediaItem).setRelated(reloadRelated(mediaItem));
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.RELATED);
        }
    }

    private static boolean support(MediaItem mediaItem) {
        if (!DetailsUtil.supportDbLoad(mediaItem) || Features.isEnabled(Features.IS_UPSM)) {
            return false;
        }
        return true;
    }

    private static ArrayList<MediaItem> loadRelated(MediaItem mediaItem) {
        ArrayList<MediaItem> people = DetailsData.of(mediaItem).getPeople();
        ArrayList<MediaItem> pets = DetailsData.of(mediaItem).getPets();
        if (people.isEmpty()) {
            return new ArrayList<>();
        }
        Cursor queryFiles = queryFiles(mediaItem, people, pets);
        if (queryFiles != null) {
            try {
                if (queryFiles.getCount() < 4) {
                    ArrayList<MediaItem> arrayList = new ArrayList<>();
                    queryFiles.close();
                    return arrayList;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        ArrayList<MediaItem> loadCursorData = DetailsUtil.loadCursorData(queryFiles);
        if (queryFiles != null) {
            queryFiles.close();
        }
        return loadCursorData;
        throw th;
    }

    private static ArrayList<MediaItem> reloadRelated(MediaItem mediaItem) {
        ArrayList<MediaItem> related = DetailsData.of(mediaItem).getRelated();
        DetailsUtil.emptyChangedFile(related);
        if (!related.stream().anyMatch(new C0571z(7))) {
            return related;
        }
        ArrayList<MediaItem> loadRelated = loadRelated(mediaItem);
        int size = loadRelated.size();
        DetailsUtil.removeDuplicated(loadRelated, related);
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < size; i2++) {
            MediaItem mediaItem2 = related.get(i2);
            if (mediaItem2 != null) {
                arrayList.add(mediaItem2);
            } else if (!loadRelated.isEmpty()) {
                MediaItem remove = loadRelated.remove(0);
                arrayList.add(remove);
                ThumbnailLoader.getInstance().loadThumbnailSync(remove, ThumbKind.MEDIUM_KIND);
            } else {
                StringBuilder sb2 = new StringBuilder("lastItems size is ");
                sb2.append(related.size());
                sb2.append(" but newItems size is ");
                sb2.append(loadRelated.size());
                sb2.append(" merged=");
                throw new IllegalStateException(j.g(sb2, arrayList));
            }
        }
        return arrayList;
    }
}
