package com.samsung.android.gallery.app.ui.viewer2.remaster.focusview;

import O3.b;
import Qb.c;
import Qb.e;
import T7.a;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterFocusViewHandler extends ViewerObject implements FragmentLifeCycle {
    private RecyclerView mFocusListView;
    private FocusViewAdapter mFocusViewAdapter;
    private final Consumer<RectF> mItemClickedListener = new c(26, this);
    private View mViewerLayout;

    private FocusViewAdapter createAdapter(Bitmap bitmap) {
        FocusViewAdapter focusViewAdapter = new FocusViewAdapter(this.mModel.getRemasteredMediaItem(), bitmap);
        this.mFocusViewAdapter = focusViewAdapter;
        focusViewAdapter.setItemClickListener(this.mItemClickedListener);
        return this.mFocusViewAdapter;
    }

    private int getLayoutOrientation() {
        boolean z = AppResources.getBoolean(R.bool.supportFoldRemaster);
        boolean isLandscape = ResourceCompat.isLandscape(this.mViewerLayout);
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES) || z || !isLandscape || SystemUi.isInMultiWindowMode(this.mModel.getActivity())) {
            return 0;
        }
        return 1;
    }

    private void inflate() {
        RecyclerView recyclerView = (RecyclerView) ViewUtils.inflateViewStub(this.mViewerLayout.findViewById(R.id.remaster_focus_view));
        this.mFocusListView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mModel.getContext(), getLayoutOrientation(), false));
    }

    private boolean isEmptyData() {
        FocusViewAdapter focusViewAdapter;
        if (this.mFocusListView == null || (focusViewAdapter = this.mFocusViewAdapter) == null || focusViewAdapter.getItemCount() <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadRemasterFocusRoi$3() {
        MediaItem originMediaItem = this.mModel.getOriginMediaItem();
        MediaItem remasteredMediaItem = this.mModel.getRemasteredMediaItem();
        if (originMediaItem != null && originMediaItem.getPath() != null && remasteredMediaItem != null && remasteredMediaItem.getPath() != null) {
            onLoadedFocusData(new RemasterHelper(SeApiCompat.createVslMesDetectorCompat("")).getFocusRoiRectList(originMediaItem.isGif(), originMediaItem.getPath(), remasteredMediaItem.getPath()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4(RectF rectF) {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_SUGGEST_REMASTER_FOCUSED_RECT_CLICK.toString());
        }
        this.mActionInvoker.invoke(ViewerAction.ZOOM_WITH_TARGET_RECT, rectF);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadedFocusData$2(ArrayList arrayList, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new R6.c(this, arrayList, bitmap, 6));
    }

    private void loadRemasterFocusRoi() {
        if (LocationKey.isRemasterPictures(this.mModel.getContainerModel().getLocationKey())) {
            ThreadUtil.postOnBgThread(new e(18, this));
        }
    }

    /* access modifiers changed from: private */
    public void onLoadedFocusData(Object... objArr) {
        ArrayList arrayList = objArr[0];
        if (arrayList == null || arrayList.isEmpty()) {
            Log.d(this.TAG, "remaster focus roi is empty");
        } else if (this.mModel.getRemasteredMediaItem() == null) {
            Log.d(this.TAG, "remastered item is null");
        } else {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "loaded remaster focus roi : " + arrayList.size());
            ThumbnailLoader.getInstance().loadThumbnail(this.mModel.getRemasteredMediaItem(), ThumbKind.MEDIUM_KIND, new b(13, this, arrayList));
        }
    }

    /* access modifiers changed from: private */
    public void onReadyRemasterView(Object... objArr) {
        if (isEmptyData()) {
            loadRemasterFocusRoi();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onReadySourceImage */
    public void lambda$onLoadedFocusData$1(ArrayList<RectF> arrayList, Bitmap bitmap) {
        inflate();
        FocusViewAdapter createAdapter = createAdapter(bitmap);
        this.mFocusListView.setAdapter(createAdapter);
        createAdapter.setData(arrayList);
        this.mFocusListView.scheduleLayoutAnimation();
        if (arrayList != null && !arrayList.isEmpty()) {
            this.mActionInvoker.invoke(ViewerAction.ON_READY_FOCUS_ROI_IMAGES, new Object[0]);
        }
    }

    private void updateLayoutOrientation() {
        LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView = this.mFocusListView;
        if (recyclerView != null && (linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager()) != null) {
            linearLayoutManager.setOrientation(getLayoutOrientation());
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.ON_REMASTER_FOCUS_AREA_LOADED, new a(this, 1));
        this.mActionInvoker.add(ViewerAction.ON_READY_REMASTER_VIEW, new a(this, 2));
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayoutOrientation();
    }

    public void onViewAttached() {
        updateLayoutOrientation();
    }

    public void onViewDetached() {
        super.onViewDetached();
        FocusViewAdapter focusViewAdapter = this.mFocusViewAdapter;
        if (focusViewAdapter != null) {
            focusViewAdapter.resetSelectedItem();
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        if (this.mFocusListView != null) {
            FocusViewAdapter focusViewAdapter = this.mFocusViewAdapter;
            if (focusViewAdapter != null) {
                focusViewAdapter.recycle();
            }
            this.mFocusListView.setAdapter((RecyclerView.Adapter) null);
            this.mFocusListView = null;
        }
    }
}
