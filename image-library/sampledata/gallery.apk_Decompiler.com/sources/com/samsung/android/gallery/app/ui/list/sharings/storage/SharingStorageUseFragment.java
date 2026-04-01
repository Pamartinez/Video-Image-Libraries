package com.samsung.android.gallery.app.ui.list.sharings.storage;

import A4.C0382q;
import Fa.I;
import N3.c;
import R5.a;
import R5.b;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.ISharingsStorageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingStorageUseFragment extends BaseListFragment<ISharingsStorageView, SharingStorageUsePresenter> implements ISharingsStorageView {
    private ViewGroup mFamilyQuotaContainer;
    private TextView mFamilyQuotaDescription;
    private ListViewHolder mFamilyQuotaViewHolder;
    private ListViewHolder mFamilyTrashViewHolder;
    private TextView mQuotaDescription;

    private void bindFamilyItem(MediaItem mediaItem) {
        ListViewHolder listViewHolder = this.mFamilyQuotaViewHolder;
        if (listViewHolder != null) {
            listViewHolder.bind(mediaItem);
            this.mFamilyQuotaViewHolder.setImageUid(mediaItem.getPath());
            Optional.ofNullable(getAdapter()).ifPresent(new c(16, this, mediaItem));
        }
    }

    private long[] getQuotaSize(Object obj, String str) {
        Object[] objArr = (Object[]) obj;
        if (objArr == null || objArr.length <= 1) {
            Log.e((CharSequence) this.TAG, C0212a.A(str, " failed"), obj);
            return null;
        }
        long j2 = 0;
        long max = Math.max(((Long) objArr[0]).longValue(), 0);
        Long l = (Long) objArr[1];
        long longValue = l.longValue();
        Log.d(this.TAG, str, Long.valueOf(max), l);
        if (longValue <= 0) {
            longValue = 5368709120L;
        }
        if (objArr.length > 2) {
            j2 = ((Long) objArr[2]).longValue();
        }
        return new long[]{max, longValue, j2};
    }

    private void initFamilyQuotaDescription() {
        if (this.mFamilyQuotaDescription == null) {
            this.mFamilyQuotaDescription = (TextView) this.mFamilyQuotaContainer.findViewById(R.id.family_storage_use_description);
        }
    }

    private void initFamilyQuotaViewHolder(Context context) {
        int i2;
        if (this.mFamilyQuotaViewHolder == null) {
            ListViewHolder createViewHolder = new SharingStorageUseViewHolderFactory(context).createViewHolder(this.mFamilyQuotaContainer, 7);
            this.mFamilyQuotaViewHolder = createViewHolder;
            View rootView = createViewHolder.getRootView();
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
                i2 = 2;
            } else {
                i2 = 1;
            }
            ViewUtils.setViewShape(rootView, i2, (float) context.getResources().getDimensionPixelOffset(R.dimen.family_shared_usage_rounded_corner_radius));
            this.mFamilyQuotaViewHolder.setFakePosition(-2);
            this.mFamilyQuotaViewHolder.setOnItemClickListener(new b(this, 0));
            this.mFamilyQuotaContainer.addView(this.mFamilyQuotaViewHolder.getRootView());
        }
    }

    private void initFamilyQuotaViews() {
        Context context = getContext();
        if (context != null) {
            initFamilyQuotaDescription();
            initFamilyQuotaViewHolder(context);
            initFamilyTrashViewHolder(context);
        }
        bindFamilyItem(((SharingStorageUsePresenter) this.mPresenter).getFamilyItem());
    }

    private void initFamilyTrashViewHolder(Context context) {
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) && this.mFamilyTrashViewHolder == null) {
            ListViewHolder createViewHolder = new SharingStorageUseViewHolderFactory(context).createViewHolder(this.mFamilyQuotaContainer, -4);
            this.mFamilyTrashViewHolder = createViewHolder;
            ViewUtils.setViewShape(createViewHolder.getRootView(), 3, (float) context.getResources().getDimensionPixelOffset(R.dimen.family_shared_usage_rounded_corner_radius));
            this.mFamilyTrashViewHolder.setFakePosition(1);
            MediaItem mediaItem = new MediaItem();
            mediaItem.setTitle(context.getString(R.string.trash));
            this.mFamilyTrashViewHolder.bind(mediaItem);
            Optional.ofNullable(this.mFamilyTrashViewHolder.getImage()).ifPresent(new I(context, 2));
            this.mFamilyTrashViewHolder.setOnItemClickListener(new b(this, 1));
            this.mFamilyQuotaContainer.addView(this.mFamilyTrashViewHolder.getRootView());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFamilyItem$5(MediaItem mediaItem, BaseListViewAdapter baseListViewAdapter) {
        baseListViewAdapter.bindImageOnScrollIdle(this.mFamilyQuotaViewHolder, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initFamilyTrashViewHolder$4(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((SharingStorageUsePresenter) this.mPresenter).moveToTrash();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFamilyQuotaValues$2(long[] jArr) {
        String readableSize = StringCompat.toReadableSize((double) jArr[0]);
        String readableSize2 = StringCompat.toReadableSize((double) Math.max(0, jArr[0] - jArr[2]));
        String readableSize3 = StringCompat.toReadableSize((double) jArr[2]);
        ListViewHolder listViewHolder = this.mFamilyQuotaViewHolder;
        if (listViewHolder != null) {
            ViewUtils.setText(listViewHolder.getSubTitleView(), readableSize2);
        }
        ListViewHolder listViewHolder2 = this.mFamilyTrashViewHolder;
        if (listViewHolder2 != null) {
            ViewUtils.setText(listViewHolder2.getSubTitleView(), readableSize3);
        }
        ViewUtils.setText(this.mFamilyQuotaDescription, AppResources.getString(R.string.family_sharing_storage_use_description, readableSize, StringCompat.toReadableSizeInGB((double) jArr[1])));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMyQuotaValues$1(long[] jArr) {
        ViewUtils.setText(this.mQuotaDescription, AppResources.getString(R.string.sharing_storage_use_description, StringCompat.toReadableSize((double) jArr[0]), StringCompat.toReadableSizeInGB((double) jArr[1])));
    }

    private void setBackgroundAndSystemUiBarColor(boolean z) {
        int i2;
        Context context = getContext();
        if (context != null) {
            if (z) {
                i2 = R.color.default_fw_background;
            } else {
                i2 = R.color.default_background;
            }
            this.mBlackboard.post("command://ChangeActivityBgColor", Integer.valueOf(context.getColor(i2)));
        }
    }

    private boolean updateQuotaViewVisibility(View view) {
        boolean isQuotaPreferenceValueEqual = MdeSharingHelper.isQuotaPreferenceValueEqual(MdeSharingHelper.MDE_QUOTA_TYPE.LIMITED.ordinal());
        ViewUtils.setVisibleOrGone(view, isQuotaPreferenceValueEqual);
        return isQuotaPreferenceValueEqual;
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        adjustAppbarHeightOffset(windowInsets);
        Optional.ofNullable(this.mToolbar).ifPresent(new C0382q(windowInsets, 2));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mFamilyQuotaContainer = (ViewGroup) view.findViewById(R.id.family_storage_use_container);
        TextView textView = (TextView) view.findViewById(R.id.sharing_storage_use_description);
        this.mQuotaDescription = textView;
        textView.setText(view.getContext().getString(R.string.sharing_storage_use_description, new Object[]{0, 0}));
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SharingStorageUseAdapter(this, "location://sharing/albums/storageUse", galleryListView);
    }

    public int getLayoutId() {
        return R.layout.fragment_sharing_storage_use_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_STORAGE_USE_VIEW.toString();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setBackgroundAndSystemUiBarColor(true);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        ((SharingStorageUsePresenter) this.mPresenter).requestQuota();
    }

    public void onResume() {
        super.onResume();
        new RequestSharedAlbumCmd().execute(getEventContext(), RequestCmdType.REQUEST_SYNC, RequestSync.Types.SpaceWithQuota);
    }

    public void onStart() {
        super.onStart();
        setBackgroundAndSystemUiBarColor(true);
    }

    public void onStop() {
        super.onStop();
        setBackgroundAndSystemUiBarColor(false);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER;
    }

    public boolean supportSelection() {
        return false;
    }

    public void updateFamilyQuotaValues(Object obj) {
        if (!isDestroyed()) {
            ViewUtils.setVisibleOrGone(this.mFamilyQuotaContainer, ((SharingStorageUsePresenter) this.mPresenter).existFamilySharedAlbum());
            if (ViewUtils.isVisible(this.mFamilyQuotaContainer)) {
                initFamilyQuotaViews();
                Optional.ofNullable(getQuotaSize(obj, "updateFamilyQuotaValues")).ifPresent(new a(this, 1));
            }
        }
    }

    public void updateMyQuotaValues(Object obj) {
        if (!updateQuotaViewVisibility(this.mQuotaDescription)) {
            Log.d(this.TAG, "updateMyQuotaValues skip update: quota type is not LIMITED");
        } else {
            Optional.ofNullable(getQuotaSize(obj, "updateMyQuotaValues")).ifPresent(new a(this, 0));
        }
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }

    public SharingStorageUsePresenter createPresenter(ISharingsStorageView iSharingsStorageView) {
        return new SharingStorageUsePresenter(this.mBlackboard, iSharingsStorageView);
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
