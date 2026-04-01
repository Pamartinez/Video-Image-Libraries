package com.samsung.android.gallery.app.ui.list.search.category.location;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import ic.l;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import l5.C0482a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchLocationFragment extends CategoryFragment {
    private final List<RecyclerView.ViewHolder> mPreparedHolders = new ArrayList();
    private RecyclerView.RecycledViewPool mViewPool;

    private void createViewPool(LocationCategoryDummyAdapter locationCategoryDummyAdapter, int i2) {
        ThreadPool.getInstance().submit(new C0482a(this, i2, locationCategoryDummyAdapter, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$createViewPool$1(int i2, LocationCategoryDummyAdapter locationCategoryDummyAdapter, ThreadPool.JobContext jobContext) {
        int i7 = 0;
        while (true) {
            if (i7 >= i2) {
                break;
            }
            try {
                RecyclerView.ViewHolder createViewHolder = locationCategoryDummyAdapter.createViewHolder(getListView(), 3);
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
    public static /* synthetic */ void lambda$onHandleEvent$0(SearchLocationItemAdapter searchLocationItemAdapter) {
        searchLocationItemAdapter.onDataChanged();
        searchLocationItemAdapter.notifyDataSetChanged();
    }

    private boolean supportDefaultTransition() {
        return !ArgumentsUtil.getArgs(getLocationKey()).getBoolean("searchToolbar", false);
    }

    public void bindView(View view) {
        super.bindView(view);
        ViewMarginUtils.setHorizontalPadding(getListView(), 0);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager gridLayoutManager;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            gridLayoutManager = new SearchLocationCategoryLayoutManager(getContext(), getMaxColumnSize());
        } else {
            gridLayoutManager = new GridLayoutManager(getContext(), getMaxColumnSize());
        }
        gridLayoutManager.setSpanSizeLookup(createSpanSizeLookUp(gridLayoutManager));
        return gridLayoutManager;
    }

    public CategoryPresenter createPresenter(ICategoryView iCategoryView) {
        return new CategoryPresenter<ICategoryView>(this.mBlackboard, iCategoryView) {
            public boolean skipInitMenu() {
                return !PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85;
            }
        };
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(final GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                SearchLocationItemAdapter adapter = SearchLocationFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getHintStartSpan(i2, i7);
                }
                Log.w(SearchLocationFragment.this.TAG, "span index 0 (null adapter)");
                return 0;
            }

            public int getSpanSize(int i2) {
                SearchLocationItemAdapter adapter = SearchLocationFragment.this.getAdapter();
                if (adapter == null || !adapter.isDivider(i2)) {
                    return 1;
                }
                return gridLayoutManager.getSpanCount();
            }
        };
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

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 8029) {
            Optional.ofNullable(getAdapter()).ifPresent(new l(23));
        }
        return super.onHandleEvent(eventMessage);
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
        createViewPool(new LocationCategoryDummyAdapter(getListView()), 30 - this.mViewPool.getRecycledViewCount(3));
    }

    public void setViewPoolSize(RecyclerView.RecycledViewPool recycledViewPool) {
        recycledViewPool.setMaxRecycledViews(3, 30);
    }

    public boolean supportEnterDefaultTransition() {
        return supportDefaultTransition();
    }

    public boolean supportExitDefaultTransition() {
        return supportDefaultTransition();
    }

    public SearchLocationItemAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SearchLocationItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public SearchLocationItemAdapter getAdapter() {
        return (SearchLocationItemAdapter) super.getAdapter();
    }
}
