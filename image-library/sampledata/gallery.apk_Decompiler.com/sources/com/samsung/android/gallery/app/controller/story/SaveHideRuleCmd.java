package com.samsung.android.gallery.app.controller.story;

import A4.C0368c;
import C3.p;
import D6.a;
import O3.o;
import T8.C0578a;
import U3.c;
import android.util.Log;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveHideRuleCmd extends BaseCommand {
    private MediaItem[] mHideItems;
    private HashMap<String, MediaItem> mHideSceneCache;
    private MediaItem[] mUnHideItems;

    private boolean addHideItems(MediaItem[] mediaItemArr) {
        if (mediaItemArr.length <= 0) {
            return false;
        }
        try {
            if (DbCompat.storyApi().addHideSceneRule(mediaItemArr, (String[]) Arrays.stream(mediaItemArr).map(new o(22)).toArray(new C0578a(6)))) {
                return true;
            }
            Log.w(this.TAG, "fail to addHideItems");
            return false;
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    private boolean changedItems(MediaItem[] mediaItemArr, MediaItem[] mediaItemArr2, HashMap<String, MediaItem> hashMap) {
        if (mediaItemArr.length == 0 && mediaItemArr2.length == 0 && !hashMap.isEmpty() && getHandler().getSelectedItemCount() == hashMap.size()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void executeHide(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            Object[] objArr = (Object[]) arrayList.get(0);
            if (((Boolean) objArr[0]).booleanValue()) {
                SimpleThreadPool.getInstance().execute(new C0368c(this, ((Integer) objArr[1]).intValue(), 16));
                postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_SCENE_DONE);
            }
        }
    }

    private Comparator<MediaItem> getHideItemComparator() {
        return new a(10);
    }

    private void hideOperation() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            showHideSceneOptionDialog();
        } else {
            SimpleThreadPool.getInstance().execute(new c(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$addHideItems$1(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeHide$8(int i2) {
        saveHideScene(this.mHideItems, this.mUnHideItems, i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$getHideItemComparator$9(MediaItem mediaItem, MediaItem mediaItem2) {
        int i2 = HideRuleData.of(mediaItem).hideRuleId;
        int i7 = HideRuleData.of(mediaItem2).hideRuleId;
        if (i2 != i7) {
            return Integer.compare(i2, i7);
        }
        long fileId = mediaItem.getFileId();
        long fileId2 = mediaItem2.getFileId();
        if (fileId != fileId2) {
            return Long.compare(fileId, fileId2);
        }
        return Long.compare(mediaItem.getDateTaken(), mediaItem2.getDateTaken());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideOperation$2() {
        saveHideScene(this.mHideItems, this.mUnHideItems, 0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$showHideSceneOptionDialog$4(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$showHideSceneOptionDialog$5(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showHideSceneOptionDialog$6() {
        saveHideScene(this.mHideItems, this.mUnHideItems, 0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$showHideSceneOptionDialog$7(int i2) {
        return new MediaItem[i2];
    }

    private boolean removeUnHideItems(MediaItem[] mediaItemArr) {
        if (mediaItemArr.length > 0) {
            int[] iArr = new int[mediaItemArr.length];
            for (int i2 = 0; i2 < mediaItemArr.length; i2++) {
                iArr[i2] = HideRuleData.of(mediaItemArr[i2]).hideRuleId;
            }
            if (DbCompat.storyApi().removeHideRule(iArr)) {
                return true;
            }
            Log.w(this.TAG, "fail to removeUnHideItems");
        }
        return false;
    }

    private void saveHideScene(MediaItem[] mediaItemArr, MediaItem[] mediaItemArr2, int i2) {
        boolean z;
        if (mediaItemArr.length > 0) {
            z = addHideItems(mediaItemArr);
        } else {
            z = false;
        }
        if (mediaItemArr2.length > 0) {
            z |= removeUnHideItems(mediaItemArr2);
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            z |= updateHideOption(i2);
        }
        if (z) {
            publishHideRuleChanged();
        }
        BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
    }

    private void showHideSceneOptionDialog() {
        int i2;
        MediaItem[] mediaItemArr;
        if (changedItems(this.mUnHideItems, this.mHideItems, this.mHideSceneCache)) {
            HashMap hashMap = new HashMap(this.mHideSceneCache);
            Arrays.stream(this.mUnHideItems).forEach(new p(8, hashMap));
            List list = (List) Arrays.stream(this.mHideItems).collect(Collectors.toList());
            Collections.reverse(list);
            mediaItemArr = (MediaItem[]) Stream.concat(list.stream(), hashMap.values().stream().sorted(getHideItemComparator().reversed())).toArray(new C0578a(3));
            i2 = 1;
        } else {
            mediaItemArr = (MediaItem[]) this.mHideSceneCache.values().stream().sorted(getHideItemComparator().reversed()).toArray(new C0578a(4));
            i2 = HideRuleData.of(mediaItemArr[0]).hideRuleIndividual;
        }
        if (mediaItemArr.length != 0 || this.mHideSceneCache.isEmpty()) {
            getBlackboard().publish("data://user/hideSceneHiddenItems", Arrays.stream(mediaItemArr).limit(4).toArray(new C0578a(5)));
            DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/HideSceneSelection").appendArg("count", mediaItemArr.length).appendArg("option1", i2).build()).setOnDataCompleteListener(new U3.a(5, this)).start();
            return;
        }
        SimpleThreadPool.getInstance().execute(new c(this, 1));
    }

    private boolean updateHideOption(int i2) {
        int i7;
        ArrayList arrayList = new ArrayList();
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            boolean atLeastSystem = SdkConfig.atLeastSystem(SdkConfig.SEM.B_MR5);
            StoryApi storyApi = DbCompat.storyApi();
            if (atLeastSystem) {
                i7 = 3;
            } else {
                i7 = 2;
            }
            arrayList.addAll(storyApi.getHideRuleIds(Arrays.asList(new Integer[]{1, Integer.valueOf(i7)})));
        }
        if (DbCompat.storyApi().updateHideRuleOption(arrayList, i2)) {
            return true;
        }
        Log.w(this.TAG, "fail to updateHideOptionItems");
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mHideItems = objArr[0];
        this.mUnHideItems = objArr[1];
        this.mHideSceneCache = objArr[2];
        hideOperation();
    }

    public void publishHideRuleChanged() {
        Blackboard.postGlobal("command://event/DataChanged", EventMessage.obtain(1036, 1, 0, (Object) null));
    }
}
