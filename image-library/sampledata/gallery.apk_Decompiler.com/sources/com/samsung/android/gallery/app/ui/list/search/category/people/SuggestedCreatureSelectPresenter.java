package com.samsung.android.gallery.app.ui.list.search.category.people;

import A4.C0381p;
import A4.S;
import Ad.C0720a;
import B5.e;
import C3.C0392b;
import K3.b;
import T8.C0578a;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.PersonUnlinkCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.people.ISuggestedCreatureSelectView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedCreatureSelectPresenter<V extends ISuggestedCreatureSelectView> extends CreatureSelectPresenter<V> {
    private boolean mAllTagged = true;
    private String mRelationShipName;
    private String mRelationshipJsonKey = "";
    private final LinkedHashMap<String, List<Long>> mRelationshipRecommendMap = ((LinkedHashMap) this.mBlackboard.pop("data://user/PersonRelationshipCandidateMap", new LinkedHashMap()));
    private boolean mTagged;

    public SuggestedCreatureSelectPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private CreatureNameData createPersonData(MediaItem mediaItem, String str) {
        String[] parseRelationshipEntry = RelationshipKeySet.parseRelationshipEntry(str, true);
        if (parseRelationshipEntry == null) {
            return null;
        }
        return new CreatureNameData.Builder("People", CreatureNameData.ContactType.TAGGED).setName(CreatureData.computeNameIfAbsent(mediaItem, new b(parseRelationshipEntry, 2))).setRelationship(parseRelationshipEntry[0]).build();
    }

    private String findJsonRelationshipJsonKey() {
        if (this.mRelationshipRecommendMap.isEmpty()) {
            return "";
        }
        Iterator<String> it = this.mRelationshipRecommendMap.keySet().iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return "";
    }

    private String getPeopleIds(List<Long> list) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        list.forEach(new e(stringJoiner, 8));
        return stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public void handleNextRelationship() {
        this.mRelationshipRecommendMap.remove(this.mRelationshipJsonKey);
        if (this.mRelationshipRecommendMap.isEmpty()) {
            notifyWithFinish();
        } else {
            reopen();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$createPersonData$6(String[] strArr) {
        return strArr[1];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$selectCreatures$1(ThreadPool.JobContext jobContext) {
        setAsRelation((ArrayList) this.mSelectedCreatures.stream().filter(new C0464a(14)).collect(Collectors.toCollection(new C0720a(1))));
        ThreadUtil.runOnUiThread(new k6.b(18, this));
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setAsRelation$2(String str, MediaItem mediaItem) {
        if (mediaItem == null || !TextUtils.equals(str, mediaItem.getSubCategory())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAsRelation$3(MediaItem mediaItem, ArrayList arrayList, ArrayList arrayList2, CreatureNameData creatureNameData) {
        arrayList.add(Long.valueOf(PeopleDataManager.addName(mediaItem.getSubCategory(), creatureNameData)));
        arrayList2.add(mediaItem);
        if (PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE && RelationshipKeySet.RELATIONSHIP_PRESET_LEGACY_MAP.containsKey(creatureNameData.getRelationship())) {
            CustomRelationshipKeySet.getInstance().addDataFrom(creatureNameData.getRelationship(), true);
        }
        this.mTagged = true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$setAsRelation$4(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAsRelation$5(String str, ArrayList arrayList) {
        PersonalDataCore.getInstance(getBlackboard()).sendRelationShipFeedback(getContext(), str, arrayList);
    }

    private void notifyWithFinish() {
        sendBroadcastEvent();
        ((ISuggestedCreatureSelectView) this.mView).finish();
    }

    private void refreshLocationKey(String str) {
        this.mBlackboard.publish("location://variable/currentv1", str);
        ((ISuggestedCreatureSelectView) this.mView).setLocationKey(str);
        setLocationKey(str);
    }

    private void reopen() {
        List arrayList = new ArrayList();
        String str = "";
        if (!this.mRelationshipRecommendMap.isEmpty()) {
            Iterator<String> it = this.mRelationshipRecommendMap.keySet().iterator();
            if (it.hasNext()) {
                String next = it.next();
                this.mRelationshipJsonKey = next;
                str = RelationshipKeySet.getRelationshipTypes(next);
                arrayList = this.mRelationshipRecommendMap.get(this.mRelationshipJsonKey);
            }
            ((ISuggestedCreatureSelectView) this.mView).updateSetAsRelationMenu();
        }
        refreshLocationKey(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(getLocationKey(), "people_ids", getPeopleIds(arrayList)), "relationship_name", str));
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
    }

    private void setAsRelation(ArrayList<String> arrayList) {
        boolean z;
        SuggestedCreatureSelectPresenter suggestedCreatureSelectPresenter;
        this.mRelationshipJsonKey = findJsonRelationshipJsonKey();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) Arrays.stream(this.getAllItems()).filter(new C0392b(it.next(), 22)).findFirst().orElse((Object) null);
            if (mediaItem != null) {
                suggestedCreatureSelectPresenter = this;
                Optional.ofNullable(this.createPersonData(mediaItem, this.mRelationshipJsonKey)).ifPresent(new S(suggestedCreatureSelectPresenter, mediaItem, arrayList3, arrayList2, 16));
            } else {
                suggestedCreatureSelectPresenter = this;
            }
            this = suggestedCreatureSelectPresenter;
        }
        SuggestedCreatureSelectPresenter suggestedCreatureSelectPresenter2 = this;
        if (!arrayList3.isEmpty()) {
            PeopleDataManager.setAsEssential(arrayList3);
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) && !arrayList2.isEmpty()) {
            new PersonUnlinkCmd().execute(((ISuggestedCreatureSelectView) suggestedCreatureSelectPresenter2.mView).getEventContext(), arrayList2.stream().toArray(new C0578a(22)));
        }
        if (!suggestedCreatureSelectPresenter2.mAllTagged || arrayList.isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        suggestedCreatureSelectPresenter2.mAllTagged = z;
        String relationshipTypes = RelationshipKeySet.getRelationshipTypes(suggestedCreatureSelectPresenter2.mRelationshipJsonKey);
        if (relationshipTypes != null) {
            SimpleThreadPool.getInstance().execute(new C0235b(suggestedCreatureSelectPresenter2, relationshipTypes, arrayList, 2));
        }
    }

    public MenuHandler createMenuHandler() {
        return new MenuHandler() {
            public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
                return ((ISuggestedCreatureSelectView) SuggestedCreatureSelectPresenter.this.mView).onMenuItemSelected(menuItem);
            }
        };
    }

    public MediaItem[] getAllItems() {
        return (MediaItem[]) Stream.concat(Arrays.stream(((ISuggestedCreatureSelectView) this.mView).getHeaderItems()), Arrays.stream(((ISuggestedCreatureSelectView) this.mView).getAllItems())).toArray(new C0578a(21));
    }

    public String getDefaultToolbarTitle(Context context) {
        return context.getString(R.string.relationship_suggestion_header, new Object[]{this.mRelationShipName});
    }

    public int getDividerButtonHeight() {
        Context context = getContext();
        if (context != null) {
            return context.getResources().getDimensionPixelOffset(R.dimen.divider_button_layout_height);
        }
        return 0;
    }

    public int getRelationShipRecommendMapSize() {
        return this.mRelationshipRecommendMap.size();
    }

    public boolean isAnyoneTagged() {
        return this.mTagged;
    }

    public boolean isFromStoriesCategory() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "fromStoriesCategory", false);
    }

    public void onListHeaderItemClick(MediaItem mediaItem) {
        if (this.mSelectedCreatures.contains(mediaItem.getSubCategory())) {
            unselect(mediaItem);
        } else {
            select(mediaItem);
        }
        updateToolbar(getToolbar());
        ((ISuggestedCreatureSelectView) this.mView).invalidateOptionsMenu();
    }

    public void onLocationKeyAssigned() {
        super.onLocationKeyAssigned();
        this.mRelationShipName = RelationshipKeySet.getRelationshipName(getContext(), ArgumentsUtil.getArgValue(getLocationKey(), "relationship_name", ""));
    }

    public void selectCreatures() {
        ThreadPool.getInstance().submit(new C0381p(11, this));
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_SEARCH_RELATIONSHIP_SUGGESTION_FULL.toString(), AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_DONE.toString(), (long) this.mSelectedCreatures.size());
    }

    public void sendBroadcastEvent() {
        int i2;
        boolean z = false;
        if (ArgumentsUtil.getArgValue(getLocationKey(), "fromRelationshipPreview", false)) {
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(8015, Boolean.valueOf(this.mTagged)));
            return;
        }
        Blackboard blackboard = this.mBlackboard;
        if (this.mTagged) {
            i2 = 11;
        } else {
            i2 = -1;
        }
        blackboard.postBroadcastEvent(EventMessage.obtain(103, i2, 0, (Object) null));
        if (ArgumentsUtil.getArgValue(getLocationKey(), "fromStoriesCategory", false)) {
            Blackboard blackboard2 = this.mBlackboard;
            if (this.mAllTagged && !getSelectedCreatures().isEmpty()) {
                z = true;
            }
            blackboard2.postBroadcastEvent(EventMessage.obtain(1139, Boolean.valueOf(z)));
        }
    }

    public void skipCurrentRelationship() {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_SEARCH_RELATIONSHIP_SUGGESTION_FULL.toString(), AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_NOT_NOW.toString());
        this.mRelationshipJsonKey = findJsonRelationshipJsonKey();
        handleNextRelationship();
    }

    public boolean skipInitMenu() {
        return false;
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$updateOptionsMenuAction$0(MenuItem menuItem) {
                menuItem.setEnabled(!((ISuggestedCreatureSelectView) SuggestedCreatureSelectPresenter.this.mView).getSelectedCreatures().isEmpty());
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                boolean z;
                super.updateOptionsMenuAction(menu, selectionMode);
                boolean isLandscape = ((ISuggestedCreatureSelectView) SuggestedCreatureSelectPresenter.this.mView).isLandscape();
                if (!isLandscape || SuggestedCreatureSelectPresenter.this.isFromStoriesCategory()) {
                    z = false;
                } else {
                    z = true;
                }
                setMenuVisibility(menu, (int) R.id.action_later, z);
                setMenuVisibility(menu, (int) R.id.action_set_as_relation, isLandscape);
                if (isLandscape) {
                    Optional.ofNullable(menu.findItem(R.id.action_set_as_relation)).ifPresent(new d(this));
                }
            }
        };
    }
}
