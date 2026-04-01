package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import A4.C0369d;
import H7.A;
import K5.a;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IScreenshotFilterCustomView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.ScreenshotFilterCustomPresenter;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterCustomDataManager;
import com.samsung.android.gallery.settings.helper.SettingLayoutUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TriConsumer;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenshotFilterCustomFragment<V extends IScreenshotFilterCustomView, P extends ScreenshotFilterCustomPresenter> extends BaseListFragment<V, P> implements IScreenshotFilterCustomView {
    private final ItemTouchHelper.Callback mCallback;
    private TextView mDescriptionView;
    /* access modifiers changed from: private */
    public int mDragStartPos;
    private Button mReorderButton;
    private View mScrollView;
    /* access modifiers changed from: private */
    public final ItemTouchHelper mTouchHelper;
    private final TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> touchCallback = new TriConsumer<RecyclerView.ViewHolder, View, MotionEvent>() {
        public void accept(RecyclerView.ViewHolder viewHolder, View view, MotionEvent motionEvent) {
            ScreenshotFilterCustomFragment.this.mTouchHelper.startDrag(viewHolder);
        }
    };

    public ScreenshotFilterCustomFragment() {
        AnonymousClass1 r0 = new ItemTouchHelper.Callback() {
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                Log.d(ScreenshotFilterCustomFragment.this.TAG, "onItemDropped", Integer.valueOf(ScreenshotFilterCustomFragment.this.mDragStartPos), Integer.valueOf(absoluteAdapterPosition));
                Optional.ofNullable(ScreenshotFilterCustomFragment.this.getAdapter()).ifPresent(new a(viewHolder));
                ScreenShotFilterCustomDataManager.saveChangedOrderData(ScreenshotFilterCustomFragment.this.mDragStartPos, absoluteAdapterPosition);
                Blackboard.publishGlobal("command:///ScreenshotFilterOrderChanged", new Object[]{ScreenshotFilterCustomFragment.this.mMediaData.read(absoluteAdapterPosition), Boolean.FALSE});
            }

            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int i2;
                if (ScreenshotFilterCustomFragment.this.getAdapter().isReorderMode()) {
                    i2 = 3;
                } else {
                    i2 = 0;
                }
                return ItemTouchHelper.Callback.makeMovementFlags(i2, 0);
            }

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                ScreenshotFilterCustomFragment.this.getAdapter().notifyItemMoved(viewHolder.getAbsoluteAdapterPosition(), viewHolder2.getAbsoluteAdapterPosition());
                return true;
            }

            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
                super.onSelectedChanged(viewHolder, i2);
                if (i2 == 2 && viewHolder != null) {
                    ScreenshotFilterCustomFragment.this.mDragStartPos = viewHolder.getAbsoluteAdapterPosition();
                    Log.d(ScreenshotFilterCustomFragment.this.TAG, "onDragStarted", Integer.valueOf(viewHolder.getAbsoluteAdapterPosition()));
                }
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
            }
        };
        this.mCallback = r0;
        setLocationKey("location://albums/AlbumSetting/ScreenShotFilterCustom");
        this.mTouchHelper = new ItemTouchHelper(r0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$0(View view) {
        getAdapter().changeReorderState(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMainLayoutPaddingHorizontal$1(FragmentActivity fragmentActivity) {
        ViewMarginUtils.setHorizontalPadding(this.mScrollView, SettingLayoutUtils.getPreferencePadding(fragmentActivity));
        ViewMarginUtils.setHorizontalPadding(this.mDescriptionView, SettingLayoutUtils.getPreferencePadding(fragmentActivity));
    }

    private void updateMainLayoutPaddingHorizontal() {
        Optional.ofNullable(getActivity()).ifPresent(new a(23, this));
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new ScreenshotFilterCustomViewAdapter(this, getLocationKey(), this.touchCallback);
    }

    public int getLayoutId() {
        return R.layout.fragment_screenshot_filter_customize_layout;
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        this.mTouchHelper.attachToRecyclerView(this.mRecyclerView);
    }

    public boolean onBackPressed() {
        if (!getAdapter().isReorderMode()) {
            return super.onBackPressed();
        }
        getAdapter().changeReorderState(false);
        return true;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        this.mScrollView = view.findViewById(R.id.scroll_view);
        this.mReorderButton = (Button) view.findViewById(R.id.reorder_button);
        this.mDescriptionView = (TextView) view.findViewById(R.id.screenshot_filter_custom_description);
        this.mReorderButton.setOnClickListener(new A(18, this));
        ViewUtils.setViewShape(this.mRecyclerView, 1, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.search_customize_setting_item_radius));
        updateMainLayoutPaddingHorizontal();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateMainLayoutPaddingHorizontal();
        setBackgroundAndSystemUiBarColor(true);
    }

    public void onStart() {
        super.onStart();
        setBackgroundAndSystemUiBarColor(true);
    }

    public void onStop() {
        super.onStop();
        setBackgroundAndSystemUiBarColor(false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setBackgroundAndSystemUiBarColor(true);
    }

    public void setBackgroundAndSystemUiBarColor(boolean z) {
        int i2;
        Context context = getContext();
        if (context != null) {
            if (z) {
                i2 = R.color.default_fw_background;
            } else {
                i2 = R.color.default_background;
            }
            int color = context.getColor(i2);
            this.mBlackboard.post("command://ChangeActivityBgColor", Integer.valueOf(color));
            ViewUtils.setBackgroundColor(this.mAppBarLayout, color);
            Optional.ofNullable(getToolbar()).ifPresent(new C0369d(color, 11));
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public ScreenshotFilterCustomPresenter createPresenter(IScreenshotFilterCustomView iScreenshotFilterCustomView) {
        return new ScreenshotFilterCustomPresenter(this.mBlackboard, iScreenshotFilterCustomView);
    }

    public ScreenshotFilterCustomViewAdapter getAdapter() {
        return (ScreenshotFilterCustomViewAdapter) this.mListAdapter;
    }
}
