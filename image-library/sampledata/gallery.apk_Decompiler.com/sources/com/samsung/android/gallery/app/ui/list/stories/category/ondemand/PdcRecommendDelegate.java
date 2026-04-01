package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import B5.c;
import Ba.h;
import K3.b;
import W8.C0579a;
import a6.j;
import a6.k;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipMultiplePickerCmd;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdcRecommendDelegate<V extends IMvpBaseView> {
    private PdcRecommendAdapter mAdapter;
    private boolean mAllTagged;
    private View mContent;
    private Listener mListener;
    private ViewGroup mParent;
    private RecyclerView mRecyclerView;
    private ViewGroup mRootView;
    private TextView mTitle;
    private final IMvpBaseView mView;
    private boolean mVisible;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onClosed(boolean z);

        void onLaunchFullList();

        void onVisibilityChanged(boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PdcRecommendLayoutManager extends LinearLayoutManager {
        private final RecyclerView recyclerView;

        public PdcRecommendLayoutManager(RecyclerView recyclerView2) {
            super(recyclerView2.getContext(), 0, false);
            this.recyclerView = recyclerView2;
        }

        public void addView(View view, int i2) {
            super.addView(view, i2);
            updateChildLayout(view);
        }

        public void updateChildLayout(View view) {
            int i2;
            int itemCount;
            ViewUtils.setViewSize(view, Integer.valueOf(view.getResources().getDimensionPixelOffset(R.dimen.stories_category_pdc_recommend_item_width)), (Integer) null);
            RecyclerView.ViewHolder childViewHolder = this.recyclerView.getChildViewHolder(view);
            if (childViewHolder != null) {
                int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(R.dimen.search_category_people_item_vertical_margin_v71);
                int dimensionPixelOffset2 = view.getResources().getDimensionPixelOffset(R.dimen.stories_category_pdc_recommend_item_horizontal_space);
                if (childViewHolder.getLayoutPosition() != 0 || (itemCount = getItemCount() * ((dimensionPixelOffset2 * 2) + view.getLayoutParams().width)) >= getWidth()) {
                    i2 = dimensionPixelOffset2;
                } else {
                    i2 = ((getWidth() - itemCount) / 2) + dimensionPixelOffset2;
                }
                ViewMarginUtils.setMarginRelative(view, Integer.valueOf(i2), Integer.valueOf(dimensionPixelOffset), Integer.valueOf(dimensionPixelOffset2), Integer.valueOf(dimensionPixelOffset));
            }
        }
    }

    public PdcRecommendDelegate(V v) {
        this.mView = v;
    }

    private void apply() {
        boolean z;
        PdcRecommendAdapter pdcRecommendAdapter = this.mAdapter;
        if (pdcRecommendAdapter != null) {
            ArrayList<String> selectedCreatures = pdcRecommendAdapter.getSelectedCreatures();
            ArrayList<MediaItem> selectedItems = this.mAdapter.getSelectedItems();
            String currentRelationKey = this.mAdapter.getCurrentRelationKey();
            Blackboard blackboard = this.mView.getBlackboard();
            if (!this.mAllTagged || selectedCreatures.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            this.mAllTagged = z;
            if (!selectedCreatures.isEmpty()) {
                SimpleThreadPool.getInstance().execute(new c(this, selectedItems, currentRelationKey, blackboard, selectedCreatures, 20));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: bindRecommendList */
    public void lambda$loadAndBind$4(HashMap<String, ArrayList<MediaItem>> hashMap, Map<String, List<Long>> map) {
        if (!this.mView.isDestroyed()) {
            show(true, "bindRecommendList");
            this.mAdapter.setData(hashMap, map);
            moveToNextItem();
        }
    }

    private void bindView() {
        if (this.mRootView == null) {
            ViewGroup viewGroup = (ViewGroup) inflateLayout(this.mParent);
            this.mRootView = viewGroup;
            this.mParent.addView(viewGroup, 0);
            initView(this.mParent);
        }
    }

    private CreatureNameData createPersonData(MediaItem mediaItem, String str) {
        String[] parseRelationshipEntry = RelationshipKeySet.parseRelationshipEntry(str, true);
        if (parseRelationshipEntry == null) {
            return null;
        }
        return new CreatureNameData.Builder("People", CreatureNameData.ContactType.TAGGED).setName(CreatureData.computeNameIfAbsent(mediaItem, new b(parseRelationshipEntry, 1))).setRelationship(parseRelationshipEntry[0]).build();
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_ONDEMAND_STORY_VIEW.toString();
    }

    private View inflateLayout(ViewGroup viewGroup) {
        return C0086a.d(viewGroup, R.layout.stories_category_floating_pdc_recommend_layout, viewGroup, false);
    }

    private void initList(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pdc_recommend_list_parent).findViewById(R.id.pdc_recommend_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new PdcRecommendLayoutManager(recyclerView));
        PdcRecommendAdapter pdcRecommendAdapter = new PdcRecommendAdapter();
        this.mAdapter = pdcRecommendAdapter;
        pdcRecommendAdapter.setItemSelectListener(new U9.b(23, this));
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void initView(View view) {
        this.mContent = view.findViewById(R.id.content);
        this.mTitle = (TextView) view.findViewById(R.id.recommend_title);
        ViewUtils.setOnClickListener(view.findViewById(R.id.show_full_list_btn), new k(this, 0));
        ViewUtils.setOnClickListener(view.findViewById(R.id.apply_btn), new k(this, 1));
        ViewUtils.setOnClickListener(view.findViewById(R.id.close_btn), new k(this, 2));
        updateBackground(this.mContent);
        initList(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$apply$8(ArrayList arrayList, String str, Blackboard blackboard, ArrayList arrayList2) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            CreatureNameData createPersonData = createPersonData((MediaItem) arrayList.get(i2), str);
            if (createPersonData != null) {
                PeopleDataManager.addName(((MediaItem) arrayList.get(i2)).getSubCategory(), createPersonData);
            }
        }
        String relationshipTypes = RelationshipKeySet.getRelationshipTypes(str);
        if (relationshipTypes != null) {
            SimpleThreadPool.getInstance().execute(new R6.c(blackboard, relationshipTypes, arrayList2, 24));
        }
        Log.d("PdcRecommendDelegate", "apply", Logger.vt(Logger.getEncodedString(str), Long.valueOf(currentTimeMillis)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$createPersonData$9(String[] strArr) {
        return strArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidateView$1() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeAllViews();
            this.mRecyclerView.getRecycledViewPool().clear();
        }
        PdcRecommendAdapter pdcRecommendAdapter = this.mAdapter;
        if (pdcRecommendAdapter != null) {
            pdcRecommendAdapter.notifyDataSetChanged();
        }
        updateLayout();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$loadAndBind$3(java.util.LinkedHashMap r2, java.lang.String r3, java.util.List r4) {
        /*
            if (r4 == 0) goto L_0x003e
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L_0x003e
            java.lang.String r0 = ","
            java.lang.String r4 = android.text.TextUtils.join(r0, r4)
            A4.B r0 = new A4.B
            r1 = 20
            r0.<init>(r4, r1)
            java.lang.String r4 = "mp://PeopleNoRelationship"
            android.database.Cursor r4 = com.samsung.android.gallery.database.dal.DbCompat.query(r4, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r4 == 0) goto L_0x0035
            boolean r1 = r4.moveToFirst()
            if (r1 == 0) goto L_0x0035
        L_0x0028:
            com.samsung.android.gallery.module.data.MediaItem r1 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r4)
            r0.add(r1)
            boolean r1 = r4.moveToNext()
            if (r1 != 0) goto L_0x0028
        L_0x0035:
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x003e
            r2.put(r3, r0)
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate.lambda$loadAndBind$3(java.util.LinkedHashMap, java.lang.String, java.util.List):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAndBind$5(Map map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        map.forEach(new h(23, linkedHashMap));
        ThreadUtil.runOnUiThread(new R6.c(this, linkedHashMap, shiftUpRecommendItems(linkedHashMap, map), 25));
    }

    private void launchFullList() {
        Optional.ofNullable(this.mListener).ifPresent(new C0579a(8));
        new RelationshipMultiplePickerCmd().execute(this.mView.getEventContext(), this.mAdapter.getRemainedRelations());
        show(false, "launch full list");
    }

    private void loadAndBind(Map<String, List<Long>> map) {
        if (map != null && !map.isEmpty()) {
            SimpleThreadPool.getInstance().execute(new U5.c(25, this, map));
        }
    }

    private void moveToNextItem() {
        if (!this.mAdapter.hasMoreItems()) {
            show(false, "self confirm");
            this.mView.getBlackboard().postBroadcastEvent(EventMessage.obtain(1139, Boolean.valueOf(this.mAllTagged)));
        } else if (this.mAdapter.next()) {
            updateRelationName();
        } else {
            launchFullList();
        }
    }

    /* access modifiers changed from: private */
    public void onApplyClicked(View view) {
        if (this.mAdapter != null) {
            apply();
            moveToNextItem();
            this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_PDC_POPUP_NEXT);
        }
    }

    /* access modifiers changed from: private */
    public void onCloseClicked(View view) {
        show(false, "Close");
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onClosed(false);
        }
        this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_PDC_POPUP_CLOSE);
    }

    /* access modifiers changed from: private */
    public void onItemSelected(boolean z) {
        AnalyticsDetail analyticsDetail;
        IMvpBaseView iMvpBaseView = this.mView;
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_ONDEMAND_STORY_PDC_POPUP_SELECT_PEOPLE;
        if (z) {
            analyticsDetail = AnalyticsDetail.ON;
        } else {
            analyticsDetail = AnalyticsDetail.OFF;
        }
        iMvpBaseView.postAnalyticsLog(analyticsEventId, analyticsDetail.toString());
    }

    /* access modifiers changed from: private */
    public void onShowFullClicked(View view) {
        Map<String, List<Long>> map;
        PdcRecommendAdapter pdcRecommendAdapter = this.mAdapter;
        if (pdcRecommendAdapter != null) {
            map = pdcRecommendAdapter.getRemainedRelations();
        } else {
            map = null;
        }
        if (map != null) {
            Optional.ofNullable(this.mListener).ifPresent(new C0579a(8));
            new RelationshipMultiplePickerCmd().execute(this.mView.getEventContext(), map);
        } else {
            Log.e("PdcRecommendDelegate", "no recommend map");
        }
        this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_PDC_POPUP_VIEW_MORE);
    }

    private LinkedHashMap<String, List<Long>> shiftUpRecommendItems(LinkedHashMap<String, ArrayList<MediaItem>> linkedHashMap, Map<String, List<Long>> map) {
        LinkedHashMap<String, List<Long>> linkedHashMap2 = new LinkedHashMap<>();
        linkedHashMap.keySet().forEach(new U5.b(13, linkedHashMap2, map));
        linkedHashMap2.putAll(map);
        return linkedHashMap2;
    }

    private void show(boolean z, String str) {
        Listener listener;
        if (this.mVisible != z) {
            this.mVisible = z;
            if (z) {
                this.mAllTagged = true;
                bindView();
            }
            Log.d("PdcRecommendDelegate", C0212a.m("show [", str, "]"), Boolean.valueOf(z));
            ViewUtils.setVisibleOrGone(this.mRootView, z);
            if (!(this.mRootView == null || (listener = this.mListener) == null)) {
                listener.onVisibilityChanged(z);
            }
            if (z) {
                ViewUtils.post(this.mRootView, new j(this, 1));
            }
        }
    }

    private void updateBackground(View view) {
        if (view == null) {
            return;
        }
        if (Features.isEnabled(Features.IS_THEME_INSTALLED) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
            view.setBackground(view.getResources().getDrawable(R.color.default_background, (Resources.Theme) null));
        } else {
            view.setBackground(view.getResources().getDrawable(R.color.on_demand_story_pdc_bg_color, (Resources.Theme) null));
        }
    }

    /* access modifiers changed from: private */
    public void updateLayout() {
        if (ViewUtils.isVisible(this.mRootView)) {
            Resources resources = this.mRootView.getResources();
            int width = (this.mRootView.getWidth() - this.mRootView.getPaddingStart()) - this.mRootView.getPaddingEnd();
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.stories_category_pdc_recommend_item_width);
            if (this.mView.isInMultiWindowMode() || ResourceCompat.isLandscape((View) this.mRootView)) {
                width = Math.min((((resources.getDimensionPixelOffset(R.dimen.stories_category_pdc_recommend_item_horizontal_space) * 2) + dimensionPixelOffset) * 3) + (resources.getDimensionPixelOffset(R.dimen.stories_category_pdc_recommend_list_horizontal_space) * 2), width);
            }
            ViewUtils.setViewSize(this.mContent, Integer.valueOf(width), -2);
            this.mRootView.requestLayout();
            Log.d("PdcRecommendDelegate", "updateLayout", Integer.valueOf(width), Integer.valueOf(dimensionPixelOffset));
        }
    }

    private void updateRelationName() {
        TextView textView = this.mTitle;
        ViewUtils.setText(textView, String.format(textView.getContext().getString(R.string.relationship_suggestion_header), new Object[]{this.mAdapter.getRelationName()}));
    }

    public void handleDensityChange() {
        invalidateView();
    }

    public boolean handleInternalEvent(SearchToolbarEvent searchToolbarEvent) {
        if (searchToolbarEvent.what != 102) {
            return false;
        }
        loadAndBind((Map) searchToolbarEvent.obj);
        return true;
    }

    public void invalidateView() {
        if (visible()) {
            ThreadUtil.postOnUiThreadDelayed(new j(this, 0), 100);
        }
    }

    public void onApplyInset() {
        updateLayout();
    }

    public void onAttached(ViewGroup viewGroup) {
        this.mParent = (ViewGroup) viewGroup.findViewById(R.id.floating_content_parent);
    }

    public void onDestroy() {
        this.mListener = null;
        Optional.ofNullable(this.mAdapter).ifPresent(new d(2));
        SeApiCompat.removeBlur(this.mContent);
    }

    public void setFocusChanged(boolean z) {
        if (!z && visible()) {
            show(false, "setFocusChanged");
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public boolean visible() {
        return ViewUtils.isVisible(this.mRootView);
    }
}
