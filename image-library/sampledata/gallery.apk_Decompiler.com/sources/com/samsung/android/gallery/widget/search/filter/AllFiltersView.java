package com.samsung.android.gallery.widget.search.filter;

import D6.a;
import Qb.e;
import Tb.b;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.root.FilterOnlyThem;
import com.samsung.android.gallery.module.search.root.FilterPriority;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.search.root.PeopleFilterResultsEntity;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AllFiltersView {
    private Blackboard mBlackboard;
    private AlertDialog mDialog;
    private boolean mIsRtl = Features.isEnabled(Features.IS_RTL);
    private Runnable mOnlyThemClickedListener;
    private final FilterSubListView[] mSubList = new FilterSubListView[4];
    private ArrayList<FilterResultsEntity> mTotalEntities;
    private View mView;

    /* JADX WARNING: type inference failed for: r0v2, types: [java.util.function.Consumer, java.lang.Object] */
    private void bindData() {
        ArrayList<FilterResultsEntity> arrayList = this.mTotalEntities;
        if (arrayList != null) {
            Iterator<FilterResultsEntity> it = arrayList.iterator();
            while (it.hasNext()) {
                FilterResultsEntity next = it.next();
                this.mSubList[FilterPriority.getListPriority(next)].addEntity(next);
            }
            Arrays.stream(this.mSubList).forEach(new Object());
        }
    }

    private FilterResultsEntity createOnlyThem(FilterOnlyThem filterOnlyThem) {
        PeopleFilterResultsEntity peopleFilterResultsEntity = new PeopleFilterResultsEntity(AppResources.getString(R$string.only_them), filterOnlyThem.getKeys());
        peopleFilterResultsEntity.addCategory("only_them");
        peopleFilterResultsEntity.setSelected(ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "people_only_them", false));
        return peopleFilterResultsEntity;
    }

    /* access modifiers changed from: private */
    public void dismissDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private int getEndPadding(Resources resources) {
        return resources.getDimensionPixelOffset(R$dimen.search_filter_result_item_margin_end) + resources.getDimensionPixelOffset(R$dimen.search_filter_result_height);
    }

    private void initListView(View view) {
        for (int i2 = 0; i2 < 4; i2++) {
            this.mSubList[i2] = new FilterSubListView(this.mBlackboard, view, i2, new e(19, this));
            if (FilterPriority.isOnlyThemPriority(i2)) {
                this.mSubList[i2].setOnlyThemClickedListener(this.mOnlyThemClickedListener);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$1(View view) {
        float f;
        View inflate = LayoutInflater.from(this.mView.getContext()).inflate(R$layout.search_all_filters_popup_view, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R$id.item_arrow);
        if (imageView != null) {
            if (this.mIsRtl) {
                f = 270.0f;
            } else {
                f = 90.0f;
            }
            imageView.setRotation(f);
            imageView.setOnClickListener(new b(this, 1));
        }
        initListView(inflate);
        bindData();
        this.mDialog = new AlertDialog.Builder(this.mView.getContext()).setView(inflate).create();
        if (SdkConfig.atLeast(SdkConfig.SEM.B) && !Features.isEnabled(Features.IS_THEME_INSTALLED) && !Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
            this.mDialog.seslSetBackgroundBlurEnabled(true);
        }
        this.mDialog.show();
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), AnalyticsEventId.EVENT_SEARCH_GOTO_ALL_FILTERS.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$updateSelectedFilters$3(FilterResultsEntity filterResultsEntity, FilterResultsEntity filterResultsEntity2) {
        return filterResultsEntity2.getCount() - filterResultsEntity.getCount();
    }

    private void updateSelectedFilters() {
        Supplier supplier = (Supplier) this.mBlackboard.read("data://user/SearchToolbarSelectedFilters");
        if (supplier != null) {
            ArrayList arrayList = (ArrayList) supplier.get();
            arrayList.forEach(new T3.e(7));
            this.mTotalEntities.addAll(arrayList);
            this.mTotalEntities.sort(new FilterPriority().thenComparing(new a(8)));
        }
    }

    public void bindView(View view) {
        float f;
        View findViewById = view.findViewById(R$id.all_filters);
        this.mView = findViewById;
        if (findViewById instanceof ViewStub) {
            this.mView = ((ViewStub) findViewById).inflate();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R$id.recycler_list);
            ViewMarginUtils.setEndPadding(recyclerView, getEndPadding(this.mView.getContext().getResources()));
            recyclerView.setClipToPadding(false);
        }
        View view2 = this.mView;
        if (view2 != null) {
            view2.setVisibility(0);
            View view3 = this.mView;
            if (view3 instanceof ImageView) {
                if (this.mIsRtl) {
                    f = 90.0f;
                } else {
                    f = 270.0f;
                }
                view3.setRotation(f);
            }
            this.mView.setOnClickListener(new b(this, 0));
        }
    }

    public boolean hasSubFilter() {
        if (this.mTotalEntities.size() > 1) {
            return true;
        }
        return false;
    }

    public void init(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    public void setData(FilterResultsEntry filterResultsEntry, FilterOnlyThem filterOnlyThem) {
        ArrayList<FilterResultsEntity> arrayList;
        if (filterResultsEntry != null) {
            ArrayList<FilterResultsEntity> allItems = filterResultsEntry.getAllItems();
        } else {
            arrayList = new ArrayList<>();
        }
        this.mTotalEntities = arrayList;
        if (filterOnlyThem != null) {
            arrayList.add(createOnlyThem(filterOnlyThem));
        }
        updateSelectedFilters();
    }

    public void setOnlyThemClickedListener(Runnable runnable) {
        this.mOnlyThemClickedListener = runnable;
    }
}
