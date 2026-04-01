package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import A4.C0385u;
import Ad.C0720a;
import Bb.g;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.AddTagBaseCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagViaDialogCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagViaEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.tag.MyTagUpdater;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.tag.MyTagClickListener;
import com.samsung.android.gallery.widget.tag.MyTagView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.stream.Collectors;
import q8.a;
import qa.e;
import v7.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagHandler extends ViewerObject implements FragmentLifeCycle, MyTagClickListener, AddTagBaseCmd.OnMyTagListener {
    private View mContainer;
    private boolean mIsFirstSlideUp = true;
    private View.OnLayoutChangeListener mLayoutChangeListener;
    private int mPrevDetailsState = 4;
    private MyTagView mTagView;
    private CoordinatorLayout mViewerLayout;

    private void addLayoutChangeListener() {
        if (isTagButtonVisible()) {
            if (this.mLayoutChangeListener == null) {
                this.mLayoutChangeListener = new g(12, this);
            }
            this.mContainer.addOnLayoutChangeListener(this.mLayoutChangeListener);
        }
    }

    private void bindView() {
        if (isTaggable()) {
            View findViewById = this.mViewerLayout.findViewById(R.id.tag_layout);
            if (ViewUtils.isViewStub(findViewById)) {
                this.mTagView = (MyTagView) ((ViewStub) findViewById).inflate();
            }
            MyTagView myTagView = this.mTagView;
            if (myTagView != null) {
                myTagView.initMyTagView(new C0385u(27, this), this);
                if (this.mModel.isSingleTakenShot()) {
                    this.mTagView.setTagVisibility();
                }
            }
        }
    }

    private Size getSourceSize() {
        if (this.mModel.getMediaItem() == null || this.mModel.getMediaItem().getSourceSize() == null) {
            return new Size(1024, 1024);
        }
        return this.mModel.getMediaItem().getSourceSize();
    }

    private int[] getTargetViewSideLength() {
        int[] iArr = new int[2];
        Size sourceSize = getSourceSize();
        View view = this.mContainer;
        float width = ((float) sourceSize.getWidth()) / ((float) sourceSize.getHeight());
        float width2 = ((float) view.getWidth()) / ((float) view.getHeight());
        if (width > width2) {
            iArr[1] = (int) ((((float) view.getHeight()) - (((float) view.getWidth()) / width)) / 2.0f);
        } else if (width < width2) {
            iArr[0] = (int) ((((float) view.getWidth()) - (((float) view.getHeight()) * width)) / 2.0f);
        }
        iArr[1] = iArr[1] + ((int) (((float) DeviceInfo.getDisplayHeight(view.getContext())) / 2.0f));
        return iArr;
    }

    private boolean isResetVisibleDetails(float f) {
        if ((this.mModel.getDetailsState() != 3 || this.mPrevDetailsState != this.mModel.getDetailsState()) && f == 0.0f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isTagButtonVisible() {
        return BottomSheetState.Details.isExpanded(this.mModel);
    }

    private boolean isTaggable() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || mediaItem.isBroken() || mediaItem.isPostProcessing() || mediaItem.isUriItem() || mediaItem.isSharing() || mediaItem.isMtp() || FileUtils.isEmptyDummyImage(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    private boolean isUpdateTagButton() {
        if (!this.mModel.getContainerModel().isTableMode()) {
            return false;
        }
        if (BottomSheetState.Details.isClosed(this.mModel) || BottomSheetState.Details.isPartialExpanded(this.mModel)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addLayoutChangeListener$2(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (!isTagButtonVisible()) {
            return;
        }
        if (i2 != i11 || i8 != i13 || i10 != i14) {
            updateMargin();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onTagDeleteClickListener$3(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public void onBottomSheetSlide(Object... objArr) {
        updateTagButton(isResetVisibleDetails(objArr[2].floatValue()));
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        int intValue = objArr[0].intValue();
        if (objArr[1].booleanValue()) {
            this.mPrevDetailsState = intValue;
            if (isUpdateTagButton()) {
                updateTagButton(true);
            }
            addLayoutChangeListener();
        }
        updateMediaItem();
    }

    private void removeLayoutChangeListener() {
        this.mContainer.removeOnLayoutChangeListener(this.mLayoutChangeListener);
        this.mLayoutChangeListener = null;
    }

    private void updateChangedTagVisibility() {
        if (!this.mIsFirstSlideUp && this.mTagView != null && isTagButtonVisible() != ViewUtils.isVisible(this.mTagView)) {
            this.mTagView.resetTagVisibility();
        }
    }

    private void updateMargin() {
        View view;
        if (this.mTagView != null && (view = this.mContainer) != null) {
            int translationY = (int) view.getTranslationY();
            if (this.mModel.getContainerModel().isTableMode()) {
                int[] targetViewSideLength = getTargetViewSideLength();
                MyTagView myTagView = this.mTagView;
                int i2 = targetViewSideLength[0];
                myTagView.updatePosition(i2, i2, targetViewSideLength[1] - translationY);
                return;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mContainer.getLayoutParams();
            int i7 = marginLayoutParams.bottomMargin - translationY;
            if (ResourceCompat.isLandscape(this.mModel.getContext())) {
                i7 += this.mModel.getContainerModel().getSystemInsets().bottom;
            }
            this.mTagView.updatePosition(marginLayoutParams.leftMargin, marginLayoutParams.rightMargin, i7);
        }
    }

    private void updateMediaItem() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        MyTagView myTagView = this.mTagView;
        if (myTagView != null && mediaItem != null) {
            myTagView.setMediaItem(mediaItem);
        }
    }

    private void updateTagButton(boolean z) {
        if (this.mIsFirstSlideUp) {
            this.mIsFirstSlideUp = false;
            bindView();
        }
        updateMargin();
        MyTagView myTagView = this.mTagView;
        if (myTagView != null && z) {
            myTagView.resetTagVisibility();
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new p(this, 0));
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new p(this, 1));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new p(this, 2));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new p(this, 3));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        updateMediaItem();
        if (isTagButtonVisible() && !MediaItemUtil.equals(this.mModel.getMediaItem(), mediaItem)) {
            updateMargin();
        }
    }

    public void onApplyWindowInsets() {
        updateMargin();
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mIsFirstSlideUp = true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateChangedTagVisibility();
    }

    public void onExecuteTagEditor(ArrayList<MediaItem> arrayList) {
        String str;
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            Log.d(this.TAG, "execute tag editor failed : mediaItem is null");
            return;
        }
        if (Features.isEnabled(Features.SUPPORT_TAG_EDITOR)) {
            AddTagViaEditorCmd addTagViaEditorCmd = new AddTagViaEditorCmd(this.mModel.getMediaItem(), arrayList, this);
            ArrayList arrayList2 = new ArrayList(arrayList);
            if (mediaItem.isImage()) {
                str = ContentUri.getUriString(mediaItem);
            } else {
                str = null;
            }
            addTagViaEditorCmd.execute(eventContext, arrayList2, str);
        } else {
            new AddTagViaDialogCmd(mediaItem, arrayList, this).execute(eventContext, new Object[0]);
        }
        postAnalyticsLog(AnalyticsScreenId.SCREEN_DETAILS_NORMAL, AnalyticsEventId.EVENT_MENU_ADD_TAG);
    }

    public void onSelectDone(ArrayList<String> arrayList) {
        MyTagView myTagView = this.mTagView;
        if (myTagView != null) {
            myTagView.updateTagData(arrayList);
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateChangedTagVisibility();
        updateMargin();
    }

    public void onTagClickListener(MediaItem mediaItem) {
        String title = mediaItem.getTitle();
        UriBuilder appendArg = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Category/MyTag", title)).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, "My tags").appendArg("sub", title);
        getBlackboard().post("command://MoveURL", appendArg.appendArg("title", "#" + title).appendArg("term", "usertag").build());
    }

    public void onTagDeleteClickListener(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = (ArrayList) arrayList.stream().map(new a(17)).collect(Collectors.toCollection(new C0720a(1)));
        Iterator it = ((ArrayList) arrayList2.stream().filter(new e(13)).collect(Collectors.toCollection(new C0720a(1)))).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (arrayList4.contains(str.toLowerCase(Locale.getDefault()))) {
                arrayList.remove(str);
            } else {
                arrayList3.add(str);
            }
        }
        postAnalyticsLog(AnalyticsScreenId.SCREEN_DETAILS_NORMAL, AnalyticsEventId.EVENT_DETAILS_REMOVE_TAG);
        new MyTagUpdater(this.mModel.getContext(), getBlackboard(), this.mModel.getMediaItem(), arrayList3, arrayList).execute(new Void[0]);
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        addLayoutChangeListener();
        this.mPrevDetailsState = this.mModel.getDetailsState();
    }

    public void onViewDetached() {
        super.onViewDetached();
        ViewUtils.setVisibility(this.mTagView, 8);
        this.mPrevDetailsState = 4;
        removeLayoutChangeListener();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        removeLayoutChangeListener();
    }
}
