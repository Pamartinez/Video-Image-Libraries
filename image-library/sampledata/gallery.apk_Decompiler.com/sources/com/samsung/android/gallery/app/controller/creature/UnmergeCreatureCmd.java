package com.samsung.android.gallery.app.controller.creature;

import A4.C0387w;
import Ad.C0720a;
import E5.b;
import Fb.h;
import Gb.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnmergeCreatureCmd extends BaseCommand {
    private MediaItem mMediaItem;

    private void forceReload() {
        getBlackboard().postBroadcastEvent(EventMessage.obtain(103, 10, 0, (Object) null));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$undoMerge$2(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$undoMerge$5(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$undoMerge$6(String str) {
        getBlackboard().postEvent(EventMessage.obtain(8035, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$undoMerge$7(EventContext eventContext) {
        Object extra;
        ArrayList<MediaItem> arrayList;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && (extra = mediaItem.getExtra(ExtrasID.CREATURE_FACE_GROUP_IDS)) != null) {
            List list = (List) extra;
            if (this.mMediaItem.isPet()) {
                PetDataManager.unmerge(this.mMediaItem.getSubCategory(), list, CreatureData.of(this.mMediaItem).creatureName, MyQueryUtil.getInterface());
            } else {
                PeopleDataManager.unmerge(this.mMediaItem.getSubCategory(), list, CreatureData.of(this.mMediaItem).creatureName, CreatureData.of(this.mMediaItem).relationshipType, false, MyQueryUtil.getInterface());
                new PersonUnlinkCmd().execute(eventContext, new MediaItem[]{this.mMediaItem});
            }
            if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
                ArrayList arrayList2 = (ArrayList) list.stream().map(new a(11)).collect(Collectors.toCollection(new C0720a(1)));
                if (this.mMediaItem.isPet()) {
                    arrayList = PetDataManager.loadItems(arrayList2);
                } else {
                    arrayList = PeopleDataManager.loadItems(arrayList2);
                }
                if (!arrayList.isEmpty()) {
                    long[] array = arrayList.stream().map(new a(13)).map(new a(14)).mapToLong(new b(1)).toArray();
                    String[] strArr = (String[]) arrayList.stream().map(new a(15)).toArray(new C0387w(16));
                    AutoAlbumHelper.getInstance().updateUnmergedRecommendedId(this.mMediaItem.getSubCategory(), array, strArr);
                    DbCompat.storyApi().updateUnmergedCreatureToHideRule(this.mMediaItem.getSubCategory(), (String[]) arrayList.stream().map(new a(12)).toArray(new C0387w(15)), strArr);
                }
            }
            forceReload();
            ThreadUtil.postOnUiThreadDelayed(new H.a(12, this, getHandler().getLocationKey()), 200);
        }
    }

    /* access modifiers changed from: private */
    public void undoMerge(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(this.TAG, "undoMerge canceled");
        } else {
            SimpleThreadPool.getInstance().execute(new H.a(13, this, eventContext));
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_CREATURE_UNMERGE_FACE_GROUP.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        if (mediaItem != null && mediaItem.isCreature()) {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getContext().getString(R.string.unmerge_face_group_q)).appendArg("description", getContext().getString(R.string.unmerge_faces_confirm_description)).appendArg("option2", getContext().getString(R.string.unmerge_button)).appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new h(20, this)).start();
        }
    }
}
