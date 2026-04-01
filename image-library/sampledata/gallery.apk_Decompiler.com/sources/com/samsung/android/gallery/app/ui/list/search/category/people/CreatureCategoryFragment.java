package com.samsung.android.gallery.app.ui.list.search.category.people;

import B5.e;
import B8.g;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.dragdrop.CreatureDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureCategoryView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import l4.b;
import l5.C0482a;
import m7.c;
import n5.C0492a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCategoryFragment<V extends ICreatureCategoryView, P extends CreatureCategoryPresenter> extends CategoryFragment<V, P> implements ICreatureCategoryView {
    private SearchAnalysisTipView mAnalysisTipHeader;
    private LinearLayout mAnalysisTipLayout;
    private ICategoryHeaderView mHeaderViewDelegate;
    private String mInitialTop5;
    private final List<RecyclerView.ViewHolder> mPreparedHolders = new ArrayList();
    private RecyclerView.RecycledViewPool mViewPool;

    public CreatureCategoryFragment() {
        createHeaderView();
    }

    private void createHeaderView() {
        this.mHeaderViewDelegate = new CreatureCategoryHeaderContainer(this);
    }

    private GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                CreatureCategoryItemAdapter adapter = CreatureCategoryFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getStartSpan(i2, i7);
                }
                return 0;
            }

            public int getSpanSize(int i2) {
                CreatureCategoryItemAdapter adapter = CreatureCategoryFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getSpanSize(i2);
                }
                return 1;
            }
        };
    }

    private void createViewPool(CreatureCategoryDummyAdapter creatureCategoryDummyAdapter, int i2) {
        ThreadPool.getInstance().submit(new C0482a(this, i2, creatureCategoryDummyAdapter, 1));
    }

    private void editTop5(int i2, MediaItem mediaItem) {
        if (i2 != 1) {
            ArrayList<String> top5Items = getTop5Items();
            String subCategory = mediaItem.getSubCategory();
            if (i2 <= top5Items.size()) {
                if (!top5Items.contains(subCategory)) {
                    StringCompat stringCompat = this.TAG;
                    Log.w(stringCompat, "There is no item [" + subCategory + "] in Top5Items {" + top5Items + "}");
                    return;
                }
                top5Items.remove(subCategory);
            } else if (top5Items.contains(subCategory)) {
                StringCompat stringCompat2 = this.TAG;
                Log.w(stringCompat2, "There is already included item [" + subCategory + "] in Top5Items {" + top5Items + "}");
                return;
            } else if (top5Items.size() > 5) {
                Utils.showToast(getContext(), (int) R.string.cant_add_more_than_5);
                return;
            } else {
                top5Items.add(subCategory);
            }
            publishFakeTop5(getConcatenatedTop5(top5Items));
            SimpleThreadPool.getInstance().execute(new C0492a(this, 0));
        }
    }

    private String getConcatenatedTop5(ArrayList<String> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 2));
        return stringJoiner.toString();
    }

    private ArrayList<String> getTop5Items() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getPriorityItems() == null) {
            return new ArrayList<>();
        }
        return this.mMediaData.getPriorityItems();
    }

    private void handleLongClick(ListViewHolder listViewHolder, int i2) {
        GalleryListView listView = getListView();
        if (!listView.isSelected(i2)) {
            listView.select(i2, true);
            listViewHolder.setChecked(true);
            if (listView.getSelectedItemCount() == 1) {
                ThreadUtil.postOnUiThreadDelayed(new C0492a(this, 1), 100);
            }
        }
        stopAutoDrag();
    }

    private void initHeaderView() {
        this.mHeaderViewDelegate.initView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$createViewPool$0(int i2, CreatureCategoryDummyAdapter creatureCategoryDummyAdapter, ThreadPool.JobContext jobContext) {
        int i7 = 0;
        while (true) {
            if (i7 >= i2) {
                break;
            }
            try {
                RecyclerView.ViewHolder createViewHolder = creatureCategoryDummyAdapter.createViewHolder(getListView(), 2);
                synchronized (this.mPreparedHolders) {
                    if (getAdapter() == null) {
                        this.mPreparedHolders.add(createViewHolder);
                    } else {
                        this.mViewPool.putRecycledView(createViewHolder);
                    }
                }
            } catch (Exception e) {
                try {
                    StringCompat stringCompat = this.TAG;
                    Log.e(stringCompat, "createViewPool failed : e=" + e.getMessage());
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
            i7++;
        }
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.publish("debug://TimeInflateQuery", Long.valueOf(System.currentTimeMillis()));
        }
        Boolean bool = Boolean.TRUE;
        Trace.endSection();
        return bool;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$editTop5$2() {
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onSelectionModeChanged$1(boolean z, CreatureCategoryItemAdapter creatureCategoryItemAdapter) {
        creatureCategoryItemAdapter.setEnableAllContentsButton(!z);
        creatureCategoryItemAdapter.notifyItemRangeChanged(0, 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTop5$3(boolean z, String str) {
        SearchMyQuery instance = SearchMyQuery.getInstance();
        if (z) {
            str = this.mInitialTop5;
        }
        instance.updateTop5(str);
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
    }

    private void performHaptic(int i2) {
        if (Features.isEnabled(Features.SUPPORT_DC_HAPTIC)) {
            SeApiCompat.performHapticFeedback(getApplicationContext(), i2);
        }
    }

    private void publishFakeTop5(String str) {
        this.mBlackboard.publish("data:///SearchTop5Creature", str);
    }

    private void updateTop5(boolean z) {
        String str = (String) this.mBlackboard.pop("data:///SearchTop5Creature", "");
        if (!TextUtils.equals(this.mInitialTop5, str)) {
            SimpleThreadPool.getInstance().execute(new g((Object) this, z, (Object) str, 15));
        }
    }

    private void updateTop5ViewVisibility() {
        boolean z;
        ICategoryHeaderView iCategoryHeaderView = this.mHeaderViewDelegate;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || !hasTop5() || isSelectionMode()) {
            z = false;
        } else {
            z = true;
        }
        iCategoryHeaderView.setTop5ViewEnable(z);
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_TOTAL_NUM, (long) getTop5Items().size());
    }

    public void bindView(View view) {
        super.bindView(view);
        getListView().setClipChildren(false);
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new CreatureDragAndDropDelegate(this);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryLayoutManagerV2] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.recyclerview.widget.RecyclerView.LayoutManager createLayoutManager() {
        /*
            r3 = this;
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85
            if (r0 == 0) goto L_0x0012
            com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryLayoutManagerV2 r0 = new com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryLayoutManagerV2
            android.content.Context r1 = r3.getContext()
            int r2 = r3.getMaxColumnSize()
            r0.<init>(r1, r2)
            goto L_0x001f
        L_0x0012:
            com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryLayoutManager r0 = new com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryLayoutManager
            android.content.Context r1 = r3.getContext()
            int r2 = r3.getMaxColumnSize()
            r0.<init>(r1, r2)
        L_0x001f:
            androidx.recyclerview.widget.GridLayoutManager$SpanSizeLookup r1 = r3.createSpanSizeLookUp(r0)
            r0.setSpanSizeLookup(r1)
            java.util.ArrayList r3 = r3.getTop5Items()
            int r3 = r3.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.setTop5Count(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment.createLayoutManager():androidx.recyclerview.widget.RecyclerView$LayoutManager");
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CreatureCategoryItemAdapter(this, getLocationKey(), this.mHeaderViewDelegate.get(), galleryListView, this.mPropertyHelper, true);
    }

    public void editTop5Done() {
        updateTop5(false);
        ICategoryHeaderView iCategoryHeaderView = this.mHeaderViewDelegate;
        if (iCategoryHeaderView != null) {
            iCategoryHeaderView.onBackPressed();
        }
    }

    public void enableEditMode(boolean z) {
        if (z) {
            String concatenatedTop5 = getConcatenatedTop5(getTop5Items());
            publishFakeTop5(concatenatedTop5);
            this.mInitialTop5 = concatenatedTop5;
            postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_EDIT_TOP5);
        }
        updateToolbar(false);
        invalidateOptionsMenu();
        Optional.ofNullable(getAdapter()).ifPresent(new c(9));
    }

    public void executeAddFaces() {
        ((CreatureCategoryPresenter) this.mPresenter).executeAddFaces();
    }

    public MediaData getMediaData(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        if (LocationKey.isSearchCategoryCreatureMatch(removeArgs)) {
            return this.mMediaData;
        }
        return this.mMediaData.getChildMediaData(removeArgs);
    }

    public int getTotalSelectableCount() {
        return (getItemCount() - (getAdapter().supportHeader() ? 1 : 0)) - (getAdapter().hasFooter() ? 1 : 0);
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        if (getAdapter() != null) {
            getAdapter().getCreatureLayoutManager().initDimen(getContext());
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (getAdapter() != null) {
            getAdapter().initDimens();
        }
    }

    public boolean hasHiddenCreature() {
        return ((CreatureCategoryPresenter) this.mPresenter).hasHiddenCreature();
    }

    public boolean hasOnlyMe() {
        if (getTop5Items().size() == 1) {
            return true;
        }
        return false;
    }

    public boolean hasTop5() {
        return !getTop5Items().isEmpty();
    }

    public void initView(View view) {
        super.initView(view);
        initHeaderView();
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        getListView().setRecycledViewPool(this.mViewPool);
        synchronized (this.mPreparedHolders) {
            try {
                for (RecyclerView.ViewHolder putRecycledView : this.mPreparedHolders) {
                    this.mViewPool.putRecycledView(putRecycledView);
                }
                this.mPreparedHolders.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isTop5Changed() {
        String str = (String) this.mBlackboard.read("data:///SearchTop5Creature", "");
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, this.mInitialTop5)) {
            return false;
        }
        return true;
    }

    public boolean isTop5EditMode() {
        ICategoryHeaderView iCategoryHeaderView = this.mHeaderViewDelegate;
        if (iCategoryHeaderView == null || !iCategoryHeaderView.isTop5EditMode()) {
            return false;
        }
        return true;
    }

    public boolean onBackPressed() {
        ICategoryHeaderView iCategoryHeaderView = this.mHeaderViewDelegate;
        if (iCategoryHeaderView == null || !iCategoryHeaderView.onBackPressed()) {
            return super.onBackPressed();
        }
        updateTop5(true);
        return true;
    }

    public void onDataChangedOnUi() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((CreatureCategoryPresenter) p6).checkHiddenCreature();
        }
        if (getAdapter() != null) {
            getAdapter().getCreatureLayoutManager().setTop5Count(Integer.valueOf(getTop5Items().size()));
        }
        updateTop5ViewVisibility();
        this.mHeaderViewDelegate.dataChanged();
        super.onDataChangedOnUi();
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        AnalyticsEventId analyticsEventId;
        long j2;
        if (isTop5EditMode()) {
            editTop5(i2, mediaItem);
            return;
        }
        super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        if (getTop5Items().size() >= i2) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_SELECT_TOP5;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_VIEW_PEOPLE_TYPE;
        }
        if (TextUtils.isEmpty(mediaItem.getTitle())) {
            j2 = 1;
        } else {
            j2 = 0;
        }
        postAnalyticsLog(analyticsEventId, j2);
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (this.mPickerMode || isTop5EditMode()) {
            return true;
        }
        if (isAutoDragPossible()) {
            handleLongClick(listViewHolder, i2);
            MediaItem[] selectedItems = getSelectedItems();
            if (selectedItems != null) {
                this.mDragAndDropDelegate.startDrag(selectedItems, listViewHolder);
                performHaptic(14);
                return false;
            }
        }
        performHaptic(100);
        return super.onListItemLongClick(listViewHolder, i2, mediaItem);
    }

    public void onSelectMeDone() {
        Optional.ofNullable(this.mHeaderViewDelegate).ifPresent(new c(8));
    }

    public void onSelectionModeChanged(boolean z) {
        updateTop5ViewVisibility();
        Optional.ofNullable(getAdapter()).ifPresent(new b(z, 2));
        this.mHeaderViewDelegate.onSelectionModeChanged(z);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mViewPool == null) {
            SynchronizedViewPool synchronizedViewPool = new SynchronizedViewPool();
            this.mViewPool = synchronizedViewPool;
            setViewPoolSize(synchronizedViewPool);
            preloadViewPool();
        }
    }

    public void preloadViewPool() {
        super.preloadViewPool();
        createViewPool(new CreatureCategoryDummyAdapter(getListView()), 30 - this.mViewPool.getRecycledViewCount(2));
    }

    public void setCreatureCategoryEmptyView() {
        int i2;
        int i7;
        super.setCreatureCategoryEmptyView();
        if (PreferenceFeatures.OneUi8x.SUPPORT_SEARCH_ANALYSIS_TIP_HEADER && isEmptyViewShowing() && this.mAnalysisTipLayout == null) {
            LinearLayout linearLayout = (LinearLayout) this.mEmptyView.findViewById(R.id.search_analysis_tip);
            this.mAnalysisTipLayout = linearLayout;
            if (this.mAnalysisTipHeader == null) {
                this.mAnalysisTipHeader = new SearchAnalysisTipView(this, linearLayout);
            }
            this.mAnalysisTipHeader.init(true);
            this.mAnalysisTipHeader.dataChanged();
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            LinearLayout linearLayout2 = this.mSettingButton;
            if (linearLayout2 != null) {
                TextView textView = (TextView) linearLayout2.findViewById(R.id.unhide_setting_text);
                if (IdentityCreatureUtil.isPetRecognized()) {
                    i7 = R.string.add_people_and_pets_button;
                } else {
                    i7 = R.string.add_people_button;
                }
                ViewUtils.setText(textView, i7);
            }
            View view = this.mEmptyView;
            if (view != null) {
                TextView textView2 = (TextView) view.findViewById(R.id.description);
                if (IdentityCreatureUtil.isPetRecognized()) {
                    i2 = R.string.add_people_and_pets_to_automatically_group;
                } else {
                    i2 = R.string.add_people_to_automatically_group;
                }
                ViewUtils.setText(textView2, i2);
            }
            GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
            if (galleryAppBarLayout != null) {
                galleryAppBarLayout.seslSetAllowStateToHide(false);
            }
        }
        ((CreatureCategoryPresenter) this.mPresenter).updateToolbar(getToolbar());
    }

    public void setViewPoolSize(RecyclerView.RecycledViewPool recycledViewPool) {
        recycledViewPool.setMaxRecycledViews(2, 30);
    }

    public boolean showDebugInfo() {
        return false;
    }

    public boolean supportSelection() {
        return true;
    }

    public CreatureCategoryPresenter createPresenter(ICreatureCategoryView iCreatureCategoryView) {
        return new CreatureCategoryPresenter(this.mBlackboard, iCreatureCategoryView);
    }

    public CreatureCategoryItemAdapter getAdapter() {
        return (CreatureCategoryItemAdapter) super.getAdapter();
    }
}
