package com.samsung.android.gallery.module.details;

import D6.a;
import I3.i;
import M8.c;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsDbQueryDataLoader extends DetailsDataLoader {
    private static final boolean SUPPORT_PEOPLE_AND_PET = PreferenceFeatures.isEnabled(PreferenceFeatures.RelatedPeopleAndPet);
    private static final Comparator<MediaItem> mCreatureComparator = new a(23);

    private static String getCreatureTitle(MediaItem mediaItem) {
        String str;
        if (TextUtils.isEmpty(mediaItem.getTitle())) {
            CreatureData of2 = CreatureData.of(mediaItem);
            if (TextUtils.isEmpty(of2.creatureName)) {
                str = RelationshipKeySet.getRelationshipName(AppResources.getAppContext(), of2.relationshipType);
            } else {
                str = of2.creatureName;
            }
            mediaItem.setTitle(str);
        }
        if (mediaItem.getTitle() == null) {
            return "";
        }
        return mediaItem.getTitle();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setPeopleAndPets$1(ArrayList arrayList, ArrayList arrayList2, MediaItem mediaItem) {
        if (mediaItem.isPeople()) {
            arrayList.add(mediaItem);
        } else if (mediaItem.isPet()) {
            arrayList2.add(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$4(MediaItem mediaItem, MediaItem mediaItem2) {
        String creatureTitle = getCreatureTitle(mediaItem);
        String creatureTitle2 = getCreatureTitle(mediaItem2);
        int i2 = -Integer.compare(mediaItem.getCount(), mediaItem2.getCount());
        if (TextUtils.isEmpty(creatureTitle) || TextUtils.isEmpty(creatureTitle2)) {
            if (!TextUtils.isEmpty(creatureTitle) || !TextUtils.isEmpty(creatureTitle2)) {
                if (TextUtils.isEmpty(creatureTitle)) {
                    return 1;
                }
                return -1;
            }
        } else if (i2 == 0) {
            return creatureTitle.compareToIgnoreCase(creatureTitle2);
        }
        return i2;
    }

    public static void loadCreatures(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        ArrayList<MediaItem> arrayList;
        String str;
        ArrayList<MediaItem> arrayList2;
        ThreadUtil.assertBgThread("loadCreatures");
        if (support(mediaItem)) {
            if (mediaItem.isVideo()) {
                ArrayList<MediaItem> loadRelatedRepresentItem = PeopleDataManager.loadRelatedRepresentItem(mediaItem.getFileId());
                if (SUPPORT_PEOPLE_AND_PET) {
                    arrayList2 = PetDataManager.loadRelatedRepresentItem(mediaItem.getFileId());
                } else {
                    arrayList2 = new ArrayList<>();
                }
                arrayList = new ArrayList<>(loadRelatedRepresentItem);
                arrayList.addAll(arrayList2);
                DetailsData.of(mediaItem).setPeople(loadRelatedRepresentItem);
                DetailsData.of(mediaItem).setPets(arrayList2);
            } else {
                if (SUPPORT_PEOPLE_AND_PET) {
                    str = "mp://PeopleAndPets";
                } else {
                    str = "mp://People";
                }
                Cursor query = DbCompat.query(str, new b(mediaItem, 0));
                try {
                    arrayList = DetailsUtil.loadCursorData(query);
                    setPeopleAndPets(mediaItem, arrayList);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            arrayList.sort(mCreatureComparator);
            DetailsData.of(mediaItem).setCreatures(arrayList);
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.CREATURES);
            return;
        }
        return;
        throw th;
    }

    public static void loadStory(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        ThreadUtil.assertBgThread("loadStory");
        if (support(mediaItem)) {
            Cursor query = DbCompat.query(DbKey.STORIES, new c(DbCompat.storyApi().getCmhFileId(mediaItem.getFileId()), 4));
            try {
                DetailsData.of(mediaItem).setStory(DetailsUtil.loadCursorData(query));
                DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.STORY);
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    public static void loadTag(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        ThreadUtil.assertBgThread("loadTag");
        if (support(mediaItem)) {
            Cursor query = DbCompat.query("mp://myTag", new b(mediaItem, 1));
            try {
                DetailsData.of(mediaItem).setUserTags(DetailsUtil.loadCursorData(query));
                DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.TAG);
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    private static void setPeopleAndPets(MediaItem mediaItem, ArrayList<MediaItem> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList.forEach(new i(arrayList2, arrayList3, 2));
        DetailsData.of(mediaItem).setPeople(arrayList2);
        DetailsData.of(mediaItem).setPets(arrayList3);
    }

    private static boolean support(MediaItem mediaItem) {
        if (!DetailsUtil.supportDbLoad(mediaItem) || Features.isEnabled(Features.IS_UPSM)) {
            return false;
        }
        return true;
    }
}
