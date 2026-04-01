package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.ISearchSuggestionView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterType;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import g6.g;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import k6.b;
import m7.c;
import n0.C0235b;
import n4.C0491c;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCategoryHeaderContainer implements ICategoryHeaderView, ISearchSuggestionView {
    private SearchAnalysisTipView mAnalysisTipHeader;
    private View mHeaderView;
    private boolean mPickerMode;
    private SuggesterType mSuggesterType = SuggesterType.NORMAL;
    private SuggesterView mSuggesterView;
    private Top5HeaderDelegate mTop5Header;
    private final ICreatureCategoryView mView;

    public CreatureCategoryHeaderContainer(ICreatureCategoryView iCreatureCategoryView) {
        this.mView = iCreatureCategoryView;
    }

    private void bindFaceMergeSuggestionIfNeeded() {
        if (this.mSuggesterView == null && !this.mPickerMode && !isAlreadyCheckedMergeCreatureMultiple()) {
            SimpleThreadPool.getInstance().execute(new b(12, this));
        }
    }

    private void bindSuggestionMeTagViewIfNeeded() {
        SuggesterType suggesterType;
        if (!this.mPickerMode) {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            ArrayList<MediaItem> findMeCreatures = findMeCreatures(atomicInteger);
            if (this.mSuggesterView == null) {
                GalleryPreference instance = GalleryPreference.getInstance();
                PreferenceName preferenceName = PreferenceName.SEARCH_ME_TAGGED;
                boolean loadBoolean = instance.loadBoolean(preferenceName, false);
                if (!loadBoolean && !this.mView.hasTop5()) {
                    int size = findMeCreatures.size();
                    if (atomicInteger.get() > 0) {
                        suggesterType = getSuggestedType(size);
                    } else {
                        suggesterType = SuggesterType.NORMAL;
                    }
                    this.mSuggesterType = suggesterType;
                    if (suggesterType == SuggesterType.NORMAL) {
                        if (size == 1) {
                            MyQueryCreator.createTop5(this.mView.getBlackboard(), findMeCreatures.get(0));
                            GalleryPreference.getInstance().saveState(preferenceName, true);
                            return;
                        }
                        return;
                    } else if (this.mView.getEventContext() != null) {
                        GalleryPreference.getInstance().saveState(preferenceName, true);
                        SuggesterView create = SuggesterView.create(this, this.mView.getEventContext());
                        this.mSuggesterView = create;
                        Optional.ofNullable(create).ifPresent(new C0491c(1, this, findMeCreatures));
                    }
                }
                C0212a.x("bindSuggestionMeTagViewIfNeeded : ", "CreatureCategoryHeaderContainer", loadBoolean);
            } else if (atomicInteger.get() == 0) {
                this.mSuggesterView.hide();
                this.mSuggesterView = null;
            }
        }
    }

    private List<Pair<String, MediaItem>> checkMergeList(List<Pair<String, List<String>>> list) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb2 = new StringBuilder();
        for (Pair next : list) {
            if (!hashSet.contains(Long.valueOf(Long.parseLong((String) next.first)))) {
                Iterator<MediaItem> it = PeopleDataManager.loadItemByGroupIds((List) next.second).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaItem next2 = it.next();
                    long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(next2.getSubCategory());
                    if (!hashSet.contains(Long.valueOf(unifiedIdentityId))) {
                        hashSet.add(Long.valueOf(Long.parseLong((String) next.first)));
                        hashSet.add(Long.valueOf(unifiedIdentityId));
                        arrayList.add(new Pair((String) next.first, next2));
                        sb2.append("[" + ((String) next.first) + NumericEnum.SEP + unifiedIdentityId + "]");
                        break;
                    }
                }
                if (arrayList.size() >= 10) {
                    break;
                }
            }
        }
        Log.v("CreatureCategoryHeaderContainer", "checkMergeList (checked): ", sb2);
        return arrayList;
    }

    private String composeRelationshipMeData() {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Contract.QUERY, this.mView.getContext().getString(R.string.relationship_me));
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put("me");
            jSONObject.put("type", jSONArray2);
            jSONArray.put(jSONObject);
            return jSONArray.toString();
        } catch (Exception e) {
            Log.w("CreatureCategoryHeaderContainer", "composeRelationshipData failed : " + e);
            return "[{\"query\":\"" + this.mView.getContext().getString(R.string.relationship_me) + "\",\"type\":[\"me\"]}]";
        }
    }

    private ArrayList<MediaItem> findMeCreatures(AtomicInteger atomicInteger) {
        MediaData mediaData;
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        ICreatureCategoryView iCreatureCategoryView = this.mView;
        if (!(iCreatureCategoryView == null || (mediaData = iCreatureCategoryView.getMediaData(iCreatureCategoryView.getLocationKey())) == null)) {
            int count = mediaData.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                MediaItem read = mediaData.read(i2);
                if (read != null && read.isPeople()) {
                    atomicInteger.getAndIncrement();
                    if ("me".equals(CreatureData.of(read).relationshipType)) {
                        arrayList.add(read);
                    }
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Long> getAllUnifiedIds() {
        ArrayList<Long> arrayList = new ArrayList<>();
        ICreatureCategoryView iCreatureCategoryView = this.mView;
        MediaData mediaData = iCreatureCategoryView.getMediaData(iCreatureCategoryView.getLocationKey());
        if (mediaData != null) {
            int count = mediaData.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                MediaItem read = mediaData.read(i2);
                if (read != null) {
                    arrayList.add(Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(read.getSubCategory())));
                }
            }
        }
        return arrayList;
    }

    private int getLayoutId() {
        return R.layout.creature_category_header_layout;
    }

    private SuggesterType getSuggestedType(int i2) {
        if (i2 == 0) {
            return SuggesterType.TAG_ME_SUGGESTION;
        }
        if (i2 > 1) {
            return SuggesterType.SELECT_ME_SUGGESTION;
        }
        return SuggesterType.NORMAL;
    }

    private void initAnalysisTipHeader() {
        if (this.mAnalysisTipHeader == null) {
            ViewUtils.setVisibility(this.mHeaderView.findViewById(R.id.search_analysis_tip_header_layout), 0);
            this.mAnalysisTipHeader = new SearchAnalysisTipView(this.mView, this.mHeaderView, new g(27, this));
        }
        this.mAnalysisTipHeader.init(true);
    }

    private void initTop5View() {
        if (this.mTop5Header == null) {
            ICreatureCategoryView iCreatureCategoryView = this.mView;
            View view = this.mHeaderView;
            boolean z = this.mPickerMode;
            Objects.requireNonNull(iCreatureCategoryView);
            this.mTop5Header = new Top5HeaderDelegate(iCreatureCategoryView, view, z, new a(iCreatureCategoryView));
        }
        this.mTop5Header.init();
    }

    private boolean isAlreadyCheckedMergeCreatureMultiple() {
        return ((Boolean) this.mView.getBlackboard().read("data:///MergerCreatureMultipleChecked", Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFaceMergeSuggestionIfNeeded$1(SuggesterView suggesterView, List list) {
        if (this.mView.isActive()) {
            suggesterView.setData(list);
            suggesterView.setBackgroundResource(R.color.default_background);
            suggesterView.bind();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFaceMergeSuggestionIfNeeded$2(List list, SuggesterView suggesterView) {
        ThreadUtil.runOnUiThread(new C0235b(this, suggesterView, list, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFaceMergeSuggestionIfNeeded$3() {
        List<Pair<String, MediaItem>> checkMergeList = checkMergeList(PeopleDataManager.getFaceClusterMergeAllData(getAllUnifiedIds()));
        if (checkMergeList.isEmpty()) {
            Log.d("CreatureCategoryHeaderContainer", "no face merge suggestion existed");
        } else if (this.mView.getEventContext() != null) {
            this.mView.getBlackboard().publish("data:///MergerCreatureMultipleChecked", Boolean.TRUE);
            this.mSuggesterType = SuggesterType.FACE_CLUSTER_MERGE_SUGGESTION;
            SuggesterView create = SuggesterView.create(this, this.mView.getEventContext());
            this.mSuggesterView = create;
            Optional.ofNullable(create).ifPresent(new C0491c(2, this, checkMergeList));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSuggestionMeTagViewIfNeeded$6(ArrayList arrayList, SuggesterView suggesterView) {
        if (this.mSuggesterType == SuggesterType.TAG_ME_SUGGESTION) {
            suggesterView.setData(composeRelationshipMeData());
        } else {
            suggesterView.setData(arrayList.toArray(new MediaItem[0]));
        }
        suggesterView.setBackgroundResource(R.color.default_background);
        suggesterView.bind();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAnalysisTipHeader$0(Boolean bool) {
        if (!bool.booleanValue()) {
            setSuggestHeader(false);
        }
    }

    private void setSuggestHeader(boolean z) {
        SearchAnalysisTipView searchAnalysisTipView = this.mAnalysisTipHeader;
        if (searchAnalysisTipView != null && (searchAnalysisTipView.isVisible() || this.mAnalysisTipHeader.isButtonClicked())) {
            return;
        }
        if (z) {
            if (this.mSuggesterType != SuggesterType.FACE_CLUSTER_MERGE_SUGGESTION) {
                Optional.ofNullable(this.mSuggesterView).ifPresent(new c(10));
            }
            bindFaceMergeSuggestionIfNeeded();
            return;
        }
        bindSuggestionMeTagViewIfNeeded();
        if (this.mSuggesterView == null && !this.mPickerMode) {
            bindFaceMergeSuggestionIfNeeded();
        }
    }

    public void dataChanged() {
        SearchAnalysisTipView searchAnalysisTipView = this.mAnalysisTipHeader;
        if (searchAnalysisTipView != null) {
            searchAnalysisTipView.dataChanged();
        }
    }

    public View get() {
        return this.mHeaderView;
    }

    public View getEmptyView() {
        return this.mHeaderView;
    }

    public NoItemView getNoItemView() {
        return null;
    }

    public SuggesterType getSuggesterType() {
        return this.mSuggesterType;
    }

    public void initView() {
        this.mHeaderView = LayoutInflater.from(this.mView.getActivity()).inflate(getLayoutId(), (ViewGroup) null);
        this.mPickerMode = PickerUtil.isPickerMode(this.mView.getBlackboard());
        if (PreferenceFeatures.OneUi8x.SUPPORT_SEARCH_ANALYSIS_TIP_HEADER) {
            initAnalysisTipHeader();
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            initTop5View();
        }
    }

    public boolean isRelationshipEnabled(String str) {
        if (TextUtils.isEmpty(str) || this.mView.isEmptyViewShowing()) {
            return false;
        }
        return true;
    }

    public boolean isTop5EditMode() {
        Top5HeaderDelegate top5HeaderDelegate = this.mTop5Header;
        if (top5HeaderDelegate == null || !top5HeaderDelegate.isEditMode()) {
            return false;
        }
        return true;
    }

    public boolean onBackPressed() {
        Top5HeaderDelegate top5HeaderDelegate = this.mTop5Header;
        if (top5HeaderDelegate == null || !top5HeaderDelegate.onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onSelectMeDone() {
        Optional.ofNullable(this.mSuggesterView).ifPresent(new c(10));
    }

    public void onSelectionModeChanged(boolean z) {
        Optional.ofNullable(this.mSuggesterView).ifPresent(new l4.b(z, 3));
        Optional.ofNullable(this.mAnalysisTipHeader).ifPresent(new l4.b(z, 4));
    }

    public void setTop5ViewEnable(boolean z) {
        Top5HeaderDelegate top5HeaderDelegate = this.mTop5Header;
        if (top5HeaderDelegate != null) {
            top5HeaderDelegate.updateTop5Visibility(z);
        }
        SearchAnalysisTipView searchAnalysisTipView = this.mAnalysisTipHeader;
        if (searchAnalysisTipView == null || !searchAnalysisTipView.isInitState()) {
            setSuggestHeader(z);
        }
    }
}
