package com.samsung.android.gallery.app.ui.list.search.pdc;

import A4.B;
import Ad.C0720a;
import C3.o;
import Fa.C0571z;
import K3.b;
import android.database.Cursor;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.creature.PersonUnlinkCmd;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipMultiplePickerCmd;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import o5.C0496a;
import q6.e;
import q8.a;
import u5.C0519a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdcSearchDelegate {
    private PdcResultAdapter mAdapter;
    private View mContainer;
    private LinkedHashMap<String, List<Long>> mFilteredMap = new LinkedHashMap<>();
    private boolean mIsDataLoaded;
    private boolean mIsFiltered;
    private boolean mIsTagged;
    private GridLayoutManager mLayoutManager;
    private final AtomicBoolean mNotifyRelationshipChanged = new AtomicBoolean(false);
    private Runnable mOnDataLoadedListener;
    private final CategoryPropertyHelper mPropertyHelper;
    private String mRelationshipJsonKey = "";
    private LinkedHashMap<String, List<Long>> mRelationshipRecommendMap;
    private int mSpanCount;
    private final ISearchPicturesView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FilteredResult {
        FILTERED,
        NO_ITEM,
        SKIPPED
    }

    public PdcSearchDelegate(ISearchPicturesView iSearchPicturesView) {
        this.mView = iSearchPicturesView;
        this.mPropertyHelper = CategoryPropertyHelper.getInstance("location://search/fileList/SuggestedCreature", true);
        updateSpanCount();
    }

    /* access modifiers changed from: private */
    public void addRelationship() {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaItem> it = getSelectedItems().iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            this.mIsTagged = true;
            arrayList.add(Long.valueOf(PeopleDataManager.addName(next.getSubCategory(), createPersonData(next, this.mRelationshipJsonKey))));
        }
        if (!arrayList.isEmpty()) {
            PeopleDataManager.setAsEssential(arrayList);
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) && !getSelectedItems().isEmpty()) {
            new PersonUnlinkCmd().execute(this.mView.getEventContext(), getSelectedItems());
        }
        String relationshipTypes = RelationshipKeySet.getRelationshipTypes(this.mRelationshipJsonKey);
        if (relationshipTypes != null) {
            SimpleThreadPool.getInstance().execute(new e(13, this, relationshipTypes));
        }
        ThreadUtil.postOnUiThread(new C0519a(this, 2));
    }

    private CreatureNameData createPersonData(MediaItem mediaItem, String str) {
        String[] parseRelationshipEntry = RelationshipKeySet.parseRelationshipEntry(str, true);
        if (parseRelationshipEntry == null) {
            return null;
        }
        return new CreatureNameData.Builder("People", CreatureNameData.ContactType.TAGGED).setName(CreatureData.computeNameIfAbsent(mediaItem, new b(parseRelationshipEntry, 3))).setRelationship(parseRelationshipEntry[0]).build();
    }

    private void findCurrentJsonKey() {
        this.mRelationshipJsonKey = null;
        if (!this.mRelationshipRecommendMap.isEmpty()) {
            Iterator<String> it = this.mRelationshipRecommendMap.keySet().iterator();
            if (it.hasNext()) {
                this.mRelationshipJsonKey = it.next();
            }
        }
    }

    private void finishRelationshipRecommend() {
        Blackboard blackboard = this.mView.getBlackboard();
        blackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        if (this.mIsTagged) {
            blackboard.postBroadcastEvent(EventMessage.obtain(103, 11, 0, (Object) null));
        }
    }

    private List<Long> getCurrentIdList() {
        return (List) Optional.ofNullable(this.mFilteredMap.get(this.mRelationshipJsonKey)).orElse(new ArrayList());
    }

    private String getPeopleIds(List<Long> list) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        list.forEach(new B5.e(stringJoiner, 13));
        return stringJoiner.toString();
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_SEARCH_RELATIONSHIP_SUGGESTION.toString();
    }

    private ArrayList<String> getSelectedPeopleUnifiedIds() {
        return (ArrayList) getSelectedItems().stream().filter(new C0571z(18)).map(new a(12)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private boolean handleEmptyList() {
        if (!needToShowFullList()) {
            return false;
        }
        showFullListWithoutRecommended();
        return true;
    }

    private void initListView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.suggested_creature_recycler_view);
        PdcResultAdapter pdcResultAdapter = new PdcResultAdapter(this.mView.getContext());
        this.mAdapter = pdcResultAdapter;
        recyclerView.setAdapter(pdcResultAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mView.getActivity(), this.mSpanCount);
        this.mLayoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private boolean isEmpty(HashMap<String, List<Long>> hashMap) {
        List list = hashMap.get(this.mRelationshipJsonKey);
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addRelationship$1(String str) {
        PersonalDataCore.getInstance(this.mView.getBlackboard()).sendRelationShipFeedback(this.mView.getContext(), str, getSelectedPeopleUnifiedIds());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        showFullListWithoutRecommended();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$createPersonData$3(String[] strArr) {
        return strArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFilterMap$8(HashSet hashSet, String str, List list) {
        ArrayList arrayList;
        if (list != null) {
            Stream stream = list.stream();
            Objects.requireNonNull(hashSet);
            arrayList = (ArrayList) stream.filter(new o(hashSet, 3)).collect(Collectors.toCollection(new C0720a(1)));
        } else {
            arrayList = new ArrayList();
        }
        this.mFilteredMap.put(str, arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateListData$6() {
        LinkedHashMap<String, List<Long>> linkedHashMap;
        if (this.mIsFiltered) {
            linkedHashMap = this.mFilteredMap;
        } else {
            linkedHashMap = this.mRelationshipRecommendMap;
        }
        Cursor query = DbCompat.query("mp://PeopleNoRelationship", new B(getPeopleIds(linkedHashMap.get(this.mRelationshipJsonKey)), 26));
        try {
            ArrayList arrayList = new ArrayList();
            if (query != null && query.moveToFirst()) {
                do {
                    arrayList.add(MediaItemBuilder.create(query));
                } while (query.moveToNext());
            }
            this.mAdapter.setData(arrayList, this.mNotifyRelationshipChanged.getAndSet(true));
            ThreadUtil.postOnUiThread(new e(12, this, arrayList));
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public void moveToNextRelationship() {
        this.mRelationshipRecommendMap.remove(this.mRelationshipJsonKey);
        this.mFilteredMap.remove(this.mRelationshipJsonKey);
        if (this.mRelationshipRecommendMap.isEmpty()) {
            finishRelationshipRecommend();
        } else {
            onDataChanged();
        }
    }

    private void onDataChanged() {
        findCurrentJsonKey();
        if (this.mRelationshipJsonKey == null) {
            onRecycled();
            return;
        }
        boolean handleEmptyList = handleEmptyList();
        ViewUtils.setVisibleOrGone(this.mContainer, !handleEmptyList);
        if (!handleEmptyList) {
            updateListData();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onListDataLoaded */
    public void lambda$updateListData$5(ArrayList<MediaItem> arrayList) {
        this.mIsDataLoaded = true;
        if (arrayList.isEmpty()) {
            onRecycled();
        } else {
            this.mAdapter.notifyDataSetChanged();
        }
        Runnable runnable = this.mOnDataLoadedListener;
        if (runnable != null) {
            runnable.run();
        }
        AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_TOTAL_NUM_SUGGESTIONS.toString(), (long) arrayList.size());
    }

    private void updateAdapter(List<Long> list) {
        PdcResultAdapter pdcResultAdapter = this.mAdapter;
        if (pdcResultAdapter != null) {
            pdcResultAdapter.filterData((ArrayList) list.stream().map(new L5.b(5)).collect(Collectors.toCollection(new C0720a(1))));
        }
    }

    private void updateFilterMap(ArrayList<String> arrayList) {
        this.mFilteredMap = new LinkedHashMap<>();
        this.mRelationshipRecommendMap.forEach(new A9.a(24, this, (HashSet) arrayList.stream().mapToLong(new E5.b(7)).boxed().collect(Collectors.toCollection(new ld.b(7)))));
    }

    private void updateListData() {
        this.mIsDataLoaded = false;
        SimpleThreadPool.getInstance().submit(new C0519a(this, 0));
    }

    private void updateSpanCount() {
        if (this.mView.getContext() != null) {
            this.mSpanCount = this.mPropertyHelper.getColumnCount(this.mView.getContext())[0];
        }
    }

    public void bindView(View view) {
        this.mContainer = view;
        initListView(view);
        ViewUtils.setOnClickListener(this.mContainer.findViewById(R.id.move_to_full_list_button), new C0496a(21, this));
    }

    public String currentRelationshipType() {
        return RelationshipKeySet.getRelationshipTypes(this.mRelationshipJsonKey);
    }

    public void fillFilterData() {
        if (!this.mIsFiltered) {
            this.mIsFiltered = true;
            this.mFilteredMap = new LinkedHashMap<>(this.mRelationshipRecommendMap);
        }
    }

    public FilteredResult filterData(ArrayList<String> arrayList) {
        if (this.mIsFiltered) {
            return FilteredResult.SKIPPED;
        }
        this.mIsFiltered = true;
        updateFilterMap(arrayList);
        List<Long> currentIdList = getCurrentIdList();
        updateAdapter(currentIdList);
        if (currentIdList.isEmpty()) {
            return FilteredResult.NO_ITEM;
        }
        return FilteredResult.FILTERED;
    }

    public ArrayList<MediaItem> getCurrentItems() {
        PdcResultAdapter pdcResultAdapter = this.mAdapter;
        if (pdcResultAdapter != null) {
            return pdcResultAdapter.getAllItems();
        }
        return new ArrayList<>();
    }

    public String getRelationshipName() {
        return RelationshipKeySet.getRelationshipName(this.mContainer.getContext(), RelationshipKeySet.getRelationshipTypes(this.mRelationshipJsonKey));
    }

    public ArrayList<MediaItem> getSelectedItems() {
        PdcResultAdapter pdcResultAdapter = this.mAdapter;
        if (pdcResultAdapter != null) {
            return pdcResultAdapter.getSelectedItems();
        }
        return new ArrayList<>();
    }

    public void handleNoResult() {
        this.mIsFiltered = true;
        showFullListWithoutRecommended();
    }

    public void handleResolutionChanged() {
        updateSpanCount();
        GridLayoutManager gridLayoutManager = this.mLayoutManager;
        if (gridLayoutManager != null) {
            gridLayoutManager.setSpanCount(this.mSpanCount);
        }
        PdcResultAdapter pdcResultAdapter = this.mAdapter;
        if (pdcResultAdapter != null) {
            pdcResultAdapter.handleResolutionChanged(this.mView.getContext());
        }
    }

    public boolean isFiltered() {
        return this.mIsFiltered;
    }

    public boolean isListLoaded() {
        return this.mIsDataLoaded;
    }

    public boolean needToShowFullList() {
        if (isEmpty(this.mRelationshipRecommendMap)) {
            return true;
        }
        if (!this.mIsFiltered || !isEmpty(this.mFilteredMap)) {
            return false;
        }
        return true;
    }

    public void onDoneClicked() {
        if (getSelectedItems().isEmpty()) {
            moveToNextRelationship();
        } else {
            SimpleThreadPool.getInstance().submit(new C0519a(this, 1));
        }
        AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_DONE.toString(), (long) this.mAdapter.getSelectedItems().size());
    }

    public void onRecycled() {
        ViewUtils.setVisibleOrGone(this.mContainer, false);
        PdcResultAdapter pdcResultAdapter = this.mAdapter;
        if (pdcResultAdapter != null) {
            pdcResultAdapter.onRecycled();
        }
    }

    public void setData(LinkedHashMap<String, List<Long>> linkedHashMap, ArrayList<String> arrayList) {
        this.mRelationshipRecommendMap = linkedHashMap;
        if (arrayList != null) {
            filterData(arrayList);
        }
        onDataChanged();
    }

    public void setOnDataLoadedListener(Runnable runnable) {
        this.mOnDataLoadedListener = runnable;
    }

    public void setOnSelectedFaceChangedListener(Consumer<ArrayList<MediaItem>> consumer) {
        PdcResultAdapter pdcResultAdapter = this.mAdapter;
        if (pdcResultAdapter != null) {
            pdcResultAdapter.setOnSelectedFaceChangedListener(consumer);
        }
    }

    public void showFullListWithoutRecommended() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(this.mRelationshipJsonKey, new ArrayList());
        new RelationshipMultiplePickerCmd().execute(this.mView.getEventContext(), linkedHashMap);
        AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_VIEW_MORE_OPTION.toString());
    }

    public void skipRelationship(boolean z) {
        this.mIsTagged = z | this.mIsTagged;
        moveToNextRelationship();
    }
}
