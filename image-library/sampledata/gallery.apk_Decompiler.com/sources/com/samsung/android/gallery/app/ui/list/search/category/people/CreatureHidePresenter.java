package com.samsung.android.gallery.app.ui.list.search.category.people;

import Fa.G;
import I3.i;
import T3.a;
import android.content.Context;
import android.view.Menu;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import i4.C0468a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import l4.b;
import n5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureHidePresenter<V extends ICreatureSelectView> extends CreatureSelectPresenter<V> {
    protected HashMap<String, HashMap<Long, Boolean>> mChangedCreatureMap = new HashMap<>();

    public CreatureHidePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void changeItemProperty(MediaItem mediaItem, boolean z) {
        CreatureData.of(mediaItem).isCreatureHide = !z;
        Arrays.stream(getAllItems()).filter(new a(6, this, mediaItem)).findFirst().ifPresent(new b(z, 5));
    }

    private HashMap<Long, Boolean> getChangedMap(String str) {
        return this.mChangedCreatureMap.computeIfAbsent(str, new C0468a(28));
    }

    private long getUnifiedId(MediaItem mediaItem) {
        return IdentityCreatureUtil.getUnifiedIdentityId(mediaItem.getSubCategory());
    }

    private boolean isMatched(MediaItem mediaItem, long j2) {
        if (getUnifiedId(mediaItem) == j2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$changeItemProperty$9(MediaItem mediaItem, MediaItem mediaItem2) {
        return isMatched(mediaItem2, getUnifiedId(mediaItem));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$fillCreatureList$7(Map.Entry entry) {
        if (entry.getKey() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$fillCreatureList$8(ArrayList arrayList, ArrayList arrayList2, Map.Entry entry) {
        Long l = (Long) entry.getKey();
        if (l == null) {
            return;
        }
        if (((Boolean) entry.getValue()).booleanValue()) {
            arrayList.add(l);
        } else {
            arrayList2.add(l);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finish$12() {
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HashMap lambda$getChangedMap$2(String str) {
        return new HashMap();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$restoreChangedCreatureMapIfNeeded$3(Long l, MediaItem mediaItem) {
        if (mediaItem == null || getUnifiedId(mediaItem) != l.longValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreChangedCreatureMapIfNeeded$5(MediaItem[] mediaItemArr, Long l, Boolean bool) {
        if (l != null) {
            Arrays.stream(mediaItemArr).filter(new a(7, this, l)).forEach(new G(1, bool));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreChangedCreatureMapIfNeeded$6(MediaItem[] mediaItemArr, String str, HashMap hashMap) {
        if (hashMap != null) {
            hashMap.forEach(new c(this, mediaItemArr, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$saveChangedCreature$0(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ThreadPool.JobContext jobContext) {
        PeopleDataManager.hidePeople(arrayList, MyQueryUtil.getInterface());
        PeopleDataManager.unHidePeople(arrayList2);
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            PetDataManager.updatePetHideState(arrayList3, arrayList4);
        }
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveChangedCreature$1(Future future) {
        finish();
    }

    private void saveChangedCreature() {
        HashMap<Long, Boolean> changedMap = getChangedMap("People");
        HashMap<Long, Boolean> changedMap2 = getChangedMap("Pet");
        if (!changedMap.isEmpty() || !changedMap2.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            fillCreatureList(changedMap, arrayList, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            fillCreatureList(changedMap2, arrayList3, arrayList4);
            ThreadPool.getInstance().submit(new J3.a(this, arrayList, arrayList2, arrayList3, arrayList4, 3), new com.samsung.android.sdk.scs.ai.language.a(22, this));
            return;
        }
        finish();
    }

    public void fillCreatureList(HashMap<Long, Boolean> hashMap, ArrayList<Long> arrayList, ArrayList<Long> arrayList2) {
        hashMap.entrySet().stream().filter(new C0464a(12)).forEach(new i(arrayList, arrayList2, 3));
    }

    public void finish() {
        ThreadUtil.postOnUiThreadDelayed(new k6.b(14, this), 200);
    }

    public String getDefaultToolbarTitle(Context context) {
        return context.getString(R.string.select_items);
    }

    public int getSelectedCount() {
        return (int) Arrays.stream(getAllItems()).filter(new C0464a(11)).count();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 103) {
            return true;
        }
        if (i2 != 8001) {
            return super.handleEvent(eventMessage);
        }
        saveChangedCreature();
        return true;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        ((ICreatureSelectView) this.mView).getAdapter().onUpdateViewHolder(i2);
        long unifiedId = getUnifiedId(mediaItem);
        boolean z = CreatureData.of(mediaItem).isCreatureHide;
        String category = mediaItem.getCategory();
        HashMap<Long, Boolean> changedMap = getChangedMap(category);
        if (changedMap.containsKey(Long.valueOf(unifiedId))) {
            changedMap.remove(Long.valueOf(unifiedId));
        } else {
            changedMap.put(Long.valueOf(unifiedId), Boolean.valueOf(!z));
        }
        changeItemProperty(mediaItem, z);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "onListItemClickInternal {" + i2 + ArcCommonLog.TAG_COMMA + category + ArcCommonLog.TAG_COMMA + mediaItem.getSubCategory() + ArcCommonLog.TAG_COMMA + CreatureData.of(mediaItem).isCreatureHide + "}");
        updateToolbar(getToolbar());
    }

    public void restoreChangedCreatureMapIfNeeded() {
        MediaItem[] allItems;
        if (!this.mChangedCreatureMap.isEmpty() && (allItems = getAllItems()) != null && allItems.length != 0) {
            this.mChangedCreatureMap.forEach(new c(this, allItems, 1));
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                super.updateOptionsMenuAction(menu, selectionMode);
                setMenuVisibility(menu, (int) R.id.action_select, false);
            }
        };
    }
}
