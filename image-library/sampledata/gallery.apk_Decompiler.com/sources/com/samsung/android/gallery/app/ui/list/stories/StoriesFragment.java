package com.samsung.android.gallery.app.ui.list.stories;

import O3.l;
import Qb.e;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.StoriesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesFragment<V extends IStoriesView, P extends StoriesPresenter> extends StoriesBaseFragment<V, P> implements IStoriesView {
    protected BasePinView<IStoriesView> mStoriesPinView;

    private void hidePinView() {
        if (isSelectionMode() && this.mStoriesPinView != null) {
            Log.d(this.TAG, "moveToScrollOnBottomLine hide pin");
            this.mStoriesPinView.hide();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestXlargeThumbnail$0() {
        Optional.ofNullable(getAdapter()).ifPresent(new l(27));
    }

    private void requestXlargeThumbnail() {
        ThreadUtil.postponeOnUiThread(new e(8, this));
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoriesViewAdapter(this, getLocationKey(), getStoriesPinRootView());
    }

    public void createPinPopupMenu(ViewGroup viewGroup, PointF pointF, MediaItem mediaItem) {
        createPopupMenu(viewGroup, pointF, mediaItem);
    }

    public BasePinView<IStoriesView> createStoriesPinView() {
        return new StoriesPinView(this);
    }

    public void enterSelectionMode(int i2) {
        super.enterSelectionMode(i2);
        hidePinView();
    }

    public int getLayoutId() {
        return R.layout.fragment_stories_layout;
    }

    public int[] getPinchColumn() {
        return this.mPinchColumnArray;
    }

    public final View getStoriesPinRootView() {
        BasePinView<IStoriesView> basePinView = this.mStoriesPinView;
        if (basePinView != null) {
            return basePinView.getView();
        }
        return null;
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateLayout();
        BasePinView<IStoriesView> basePinView = this.mStoriesPinView;
        if (basePinView != null) {
            basePinView.handleResolutionChange(i2);
        }
    }

    public void initView(View view) {
        super.initView(view);
        if (supportPinView() && this.mStoriesPinView == null) {
            this.mStoriesPinView = createStoriesPinView();
        }
    }

    public boolean isDrawerSliding() {
        return ((StoriesPresenter) this.mPresenter).isDrawerSliding();
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        if (i2 != 0 || getAdapter() == null || !getAdapter().supportHeader()) {
            return super.isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2);
        }
        return false;
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.stories_column_count);
    }

    public void onDataRangeChangedOnUi(int i2, int i7) {
        StoriesViewAdapter adapter = getAdapter();
        if (adapter != null) {
            i2 = adapter.getViewPosition(i2);
        }
        super.onDataRangeChangedOnUi(i2, i7);
        if (adapter != null) {
            getAdapter().checkPreviewCandidate();
            requestXlargeThumbnail();
        }
    }

    public void onDataRangeInserted(int i2, int i7) {
        StoriesViewAdapter adapter = getAdapter();
        if (adapter != null) {
            i2 = adapter.getViewPosition(i2);
        }
        super.onDataRangeInserted(i2, i7);
    }

    public void onDestroy() {
        super.onDestroy();
        BasePinView<IStoriesView> basePinView = this.mStoriesPinView;
        if (basePinView != null) {
            basePinView.destroy();
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 1057) {
            if (i2 != 1058) {
                return super.onHandleEvent(eventMessage);
            }
            StoriesViewAdapter adapter = getAdapter();
            if (adapter == null) {
                return true;
            }
            adapter.onAbortDelete();
            return true;
        } else if (!isViewActive() || getAdapter() == null) {
            return true;
        } else {
            Object obj = eventMessage.obj;
            if (obj == null) {
                getAdapter().onPrepareDelete(getLayoutManager());
                return true;
            }
            getAdapter().onPrepareDelete(getLayoutManager(), (ArrayList) obj);
            return true;
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        return super.onListItemLongClick(listViewHolder, i2, mediaItem);
    }

    public void onListItemMenuClick(View view, PointF pointF, MediaItem mediaItem) {
        ((StoriesPresenter) this.mPresenter).onListItemMenuClickInternal(view, pointF, mediaItem);
    }

    public boolean onOptionsItemMenuSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_share_cover || menuItem.getItemId() == R.id.action_save_cover) {
            ((StoriesPresenter) this.mPresenter).stopAllPreview(false);
        }
        return ((StoriesPresenter) this.mPresenter).onOptionsItemSelected(menuItem);
    }

    public boolean onPostViewCreated() {
        requestXlargeThumbnail();
        return super.onPostViewCreated();
    }

    public void onStart() {
        super.onStart();
        updateLayout();
    }

    public void onViewCreated(View view, Bundle bundle) {
        boolean isViewReady = isViewReady();
        super.onViewCreated(view, bundle);
        if (isViewReady) {
            requestXlargeThumbnail();
        }
    }

    public boolean supportAutoDrag() {
        return false;
    }

    public boolean supportPinView() {
        if (PreferenceFeatures.OneUi40.SUPPORT_STORIES_REMINDER || PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN) {
            return true;
        }
        return false;
    }

    public boolean supportShareSheet() {
        return false;
    }

    public void updateLayout() {
        ((StoriesLayoutManager) getLayoutManager()).initDimen(getContext());
        ((StoriesPresenter) this.mPresenter).updateLayout();
    }

    public void updateListViewBottomPadding(int i2) {
        super.updateListViewBottomPadding(i2);
        if (i2 >= 0) {
            hidePinView();
        }
    }

    public boolean useCustomScrollbar() {
        return false;
    }

    public GalleryGridLayoutManager createLayoutManager() {
        return new StoriesLayoutManager(this, getListView(), getMaxColumnSize());
    }

    public StoriesPresenter<IStoriesView> createPresenter(IStoriesView iStoriesView) {
        return new StoriesPresenter<>(this.mBlackboard, iStoriesView);
    }

    public StoriesViewAdapter getAdapter() {
        return (StoriesViewAdapter) this.mListAdapter;
    }

    public GalleryGridLayoutManager getLayoutManager() {
        ((StoriesLayoutManager) super.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i2) {
                StoriesViewAdapter adapter = StoriesFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getSpanSize(i2);
                }
                return 1;
            }
        });
        return (StoriesLayoutManager) super.getLayoutManager();
    }

    public BasePinView<IStoriesView> getPinView() {
        return this.mStoriesPinView;
    }

    public void createDragAndDropDelegate() {
    }
}
