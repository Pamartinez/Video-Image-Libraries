package com.samsung.android.gallery.app.ui.list.hover;

import A.a;
import B8.f;
import I3.c;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.ContentFrameLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumItemCmd;
import com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.HoverStatusManagerCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewListData;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewListView;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HoverHandler {
    private final Blackboard mBlackboard;
    private int mEndPosition;
    private final EventContext mEventContext;
    private int mFirstLoadedItemCount;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                HoverHandler.this.removeHoverPreview();
            }
        }
    };
    private final HoverAlbumTargetKeyFinder mHoverAlbumTargetKeyFinder;
    private final HoverDataLoader mHoverDataLoader;
    private final AtomicInteger mHoverInvalidReason = new AtomicInteger(0);
    private HoverPreviewListData mHoverPreviewListData;
    private HoverPreviewListView<HoverPreviewListData> mHoverPreviewListView;
    private final HoverStatusManagerCompat mHoverStatusManager;
    private ViewGroup mHoverViewContainer;
    private final HoverViewSizeAndPosition mHoverViewSizeAndPosition = new HoverViewSizeAndPosition();
    private boolean mIsLoadCompleted;
    private boolean mIsLoadingHoverPreview;
    private HoverParams mParams;
    private ViewGroup mParentOfHoverPreview;
    private int mPointerIconType;
    private ListViewHolder mSelectedViewHolder;
    private int mStartPosition;
    private long mStartTime = 0;
    private int mToolType = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DataLoadCallback {
    }

    public HoverHandler(EventContext eventContext, Blackboard blackboard) {
        this.mEventContext = eventContext;
        this.mBlackboard = blackboard;
        this.mHoverStatusManager = SeApiCompat.getHoverStatusManager(Features.isEnabled(Features.IS_AFW_MODE));
        this.mHoverAlbumTargetKeyFinder = new HoverAlbumTargetKeyFinder(blackboard);
        this.mHoverDataLoader = new HoverDataLoader(blackboard);
    }

    private int calcStartPosition(boolean z) {
        if (z) {
            int i2 = this.mEndPosition + 1;
            if (i2 >= this.mHoverDataLoader.getCount()) {
                return 0;
            }
            return i2;
        }
        int max = Math.max(this.mStartPosition - this.mFirstLoadedItemCount, 0);
        if (max != this.mStartPosition) {
            return max;
        }
        int i7 = this.mFirstLoadedItemCount;
        return ((this.mHoverDataLoader.getCount() - 1) / i7) * i7;
    }

    private boolean checkEvent(Context context, MotionEvent motionEvent) {
        boolean z;
        HoverStatusManagerCompat hoverStatusManagerCompat = this.mHoverStatusManager;
        if (hoverStatusManagerCompat == null) {
            return true;
        }
        boolean isPenHovering = hoverStatusManagerCompat.isPenHovering(context);
        boolean isFingerAirView = this.mHoverStatusManager.isFingerAirView(context);
        boolean isMouseHovering = this.mHoverStatusManager.isMouseHovering(context);
        if (isPenHovering || isFingerAirView || isMouseHovering) {
            z = true;
        } else {
            z = false;
        }
        int toolType = motionEvent.getToolType(0);
        this.mToolType = toolType;
        if (!z) {
            return true;
        }
        if (!isPenHovering && toolType == 2) {
            return true;
        }
        if (!isFingerAirView && toolType == 1) {
            return true;
        }
        if (isMouseHovering || toolType != 3) {
            return !isPenHovering;
        }
        return true;
    }

    private boolean checkPositionInView(View view, float f, float f5) {
        if (view == null || f < ((float) view.getLeft()) || f > ((float) view.getRight()) || f5 < ((float) view.getTop()) || f5 > ((float) view.getBottom())) {
            return false;
        }
        return true;
    }

    private boolean enterHover(ListViewHolder listViewHolder) {
        Log.d("HoverHandler", "enterHover {" + this.mIsLoadingHoverPreview + "}");
        ListViewHolder listViewHolder2 = this.mSelectedViewHolder;
        if (listViewHolder2 != null && listViewHolder2 != listViewHolder) {
            this.mHandler.sendEmptyMessage(1);
        } else if (this.mIsLoadingHoverPreview) {
            return false;
        }
        this.mStartTime = System.currentTimeMillis();
        return false;
    }

    private boolean exitHover(Context context) {
        Log.d("HoverHandler", "exitHover {" + this.mIsLoadCompleted + "}");
        if (this.mPointerIconType != 20001) {
            setPointerIcon(this.mParentOfHoverPreview, 20001);
        }
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, (Object) null), 200);
        return false;
    }

    private boolean findHoverView(View view, float f, float f5) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        float f8 = f - ((float) iArr[0]);
        float f10 = f5 - ((float) iArr[1]);
        if (f8 < ((float) rect.left) || f8 > ((float) rect.right) || f10 < ((float) rect.top) || f10 > ((float) rect.bottom)) {
            return false;
        }
        return true;
    }

    private boolean findTargetView(View view, View view2, float f, float f5) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof ViewGroup) {
                    if (childAt.getVisibility() == 0 && ((ViewGroup) childAt).getChildCount() > 0 && findTargetView(childAt, view2, f, f5)) {
                        return true;
                    }
                } else if (childAt == view2 && checkPositionInView(childAt, f, f5)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getAndSetPointerIconTypeByButtonState(ViewGroup viewGroup, MotionEvent motionEvent) {
        int i2;
        if (motionEvent.getButtonState() == 32) {
            if (this.mPointerIconType != 20021) {
                setPointerIcon(viewGroup, 20021);
            }
        } else if (this.mPointerIconType == 20021) {
            this.mStartTime = -1;
            if (this.mHoverPreviewListView != null) {
                i2 = 20010;
            } else {
                i2 = 20001;
            }
            setPointerIcon(viewGroup, i2);
        }
        return this.mPointerIconType;
    }

    private Object[] getParentInfoOfListView(ListViewHolder listViewHolder, ViewGroup viewGroup) {
        View view = (View) listViewHolder.itemView.getParent();
        View view2 = null;
        int i2 = 0;
        int i7 = 0;
        if (viewGroup != null) {
            while (view != null && viewGroup != view) {
                i2 += view.getLeft();
                i7 += view.getTop();
                view2 = view;
                view = (View) view.getParent();
            }
        }
        return new Object[]{view2, Integer.valueOf(i2), Integer.valueOf(i7)};
    }

    private ViewGroup getTopParent(ViewGroup viewGroup) {
        while (viewGroup != null && hasMoreParent(viewGroup.getParent())) {
            viewGroup = (ViewGroup) viewGroup.getParent();
        }
        return viewGroup;
    }

    private boolean hasMoreParent(ViewParent viewParent) {
        if (viewParent == null || (viewParent instanceof ContentFrameLayout)) {
            return false;
        }
        return true;
    }

    private void initializeHoverPreviewVariable() {
        this.mSelectedViewHolder = null;
        this.mIsLoadingHoverPreview = false;
        this.mStartTime = -1;
        this.mIsLoadCompleted = false;
    }

    private void initializeListViewListener() {
        this.mHoverPreviewListView.setOnRemoveListener((HoverPreviewListView.OnRemoveListener) null);
        this.mHoverPreviewListView.setOnItemClickListener((HoverPreviewListView.OnItemClickListener) null);
        this.mHoverPreviewListView.setOnShareClickListener((HoverPreviewViewHolder.OnShareClickListener) null);
        this.mHoverPreviewListView.setOnDeleteClickListener((HoverPreviewViewHolder.OnDeleteClickListener) null);
    }

    private void initializeValuesForCreateHoverView(ListViewHolder listViewHolder, ArrayList<MediaItem> arrayList, ViewGroup viewGroup) {
        this.mParentOfHoverPreview = viewGroup;
        this.mSelectedViewHolder = listViewHolder;
        HoverPreviewListData hoverPreviewListData = new HoverPreviewListData(arrayList);
        this.mHoverPreviewListData = hoverPreviewListData;
        int count = hoverPreviewListData.getCount();
        this.mFirstLoadedItemCount = count;
        this.mStartPosition = 0;
        this.mEndPosition = count - 1;
    }

    private boolean isEmptyAlbum(MediaItem mediaItem) {
        if (mediaItem.getCount() == 0 || (mediaItem.getCount() == 1 && FileUtils.isEmptyDummyImage(mediaItem.getPath()))) {
            return true;
        }
        return false;
    }

    private boolean isHoverValid(ListViewHolder listViewHolder, MotionEvent motionEvent, HoverParams hoverParams) {
        if (getAndSetPointerIconTypeByButtonState(hoverParams.getContainer(), motionEvent) == 20021) {
            if (this.mIsLoadingHoverPreview) {
                onRemoveHoverPreview();
            }
            if (this.mHoverInvalidReason.getAndSet(3) != 3) {
                Log.d("HoverHandler", "isHoverInvalid. pen select");
            }
            return false;
        } else if (checkEvent(hoverParams.getContext(), motionEvent)) {
            if (this.mHoverInvalidReason.getAndSet(4) != 4) {
                Log.d("HoverHandler", "isHoverInvalid. invalid hover event");
            }
            return false;
        } else {
            MediaItem item = hoverParams.getItem();
            if (item == null) {
                if (this.mHoverInvalidReason.getAndSet(1) != 1) {
                    Log.d("HoverHandler", "isHoverInvalid item is null");
                }
                return false;
            } else if (!listViewHolder.hasBitmap()) {
                if (this.mHoverInvalidReason.getAndSet(2) != 2) {
                    Log.d("HoverHandler", "isHoverInvalid. no bitmap. " + MediaItemUtil.getSimpleLog(item));
                }
                return false;
            } else if (!hoverParams.isAlbum() || !isEmptyAlbum(item)) {
                this.mHoverInvalidReason.set(0);
                return true;
            } else {
                if (this.mHoverInvalidReason.getAndSet(5) != 5) {
                    Log.d("HoverHandler", "isHoverInvalid. empty album. " + MediaItemUtil.getSimpleLog(item));
                }
                return false;
            }
        }
    }

    private boolean isValidState(ArrayList<MediaItem> arrayList) {
        if (arrayList.isEmpty()) {
            Log.d("HoverHandler", "isValidState false. no data");
            removeHoverPreview();
            return false;
        } else if (this.mIsLoadingHoverPreview) {
            return true;
        } else {
            Log.d("HoverHandler", "isValidState false. not loading");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$loadHoverPreviewPopup$1(Context context, ListViewHolder listViewHolder, ViewGroup viewGroup, ThreadPool.JobContext jobContext) {
        this.mHoverDataLoader.load(this.mHoverAlbumTargetKeyFinder.find(this.mParams.getLocationKey(), this.mParams.getItem(), this.mParams.getDataPosition()), this.mParams, new f((Object) this, (Object) context, (Object) listViewHolder, (Object) viewGroup, 3));
        return null;
    }

    private void loadHoverPreviewPopup(ListViewHolder listViewHolder, MotionEvent motionEvent, ViewGroup viewGroup) {
        Log.d("HoverHandler", "loadHoverPreviewPopup action=" + motionEvent.getAction());
        this.mIsLoadingHoverPreview = true;
        Context context = this.mParams.getContext();
        if (context == null) {
            Log.e("HoverHandler", "context null, skip loadHoverPreviewPopup");
        } else {
            ThreadPool.getInstance().submit(new c(this, context, listViewHolder, viewGroup, 3));
        }
    }

    private boolean moveHover(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            long j2 = this.mStartTime;
            if (j2 == -1 || currentTimeMillis - j2 < 700) {
                if (j2 == -1) {
                    this.mStartTime = System.currentTimeMillis();
                }
                return false;
            } else if (!this.mIsLoadingHoverPreview) {
                return true;
            } else {
                if (this.mHandler.hasMessages(1)) {
                    this.mHandler.removeMessages(1);
                }
                return false;
            }
        } else {
            if (this.mIsLoadingHoverPreview && currentTimeMillis - this.mStartTime >= 4000) {
                this.mHandler.sendEmptyMessage(1);
            }
            return false;
        }
    }

    private void onBindHoverView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hover_preview_layout, this.mParentOfHoverPreview, true);
        this.mHoverViewContainer = (ViewGroup) inflate.findViewById(R.id.hover_preview_container);
        this.mHoverPreviewListView = (HoverPreviewListView) inflate.findViewById(R.id.hover_preview_recycle_view);
    }

    /* access modifiers changed from: private */
    /* renamed from: onDataLoaded */
    public void lambda$loadHoverPreviewPopup$0(Context context, ListViewHolder listViewHolder, ArrayList<MediaItem> arrayList, ViewGroup viewGroup) {
        if (isValidState(arrayList)) {
            Object[] parentInfoOfListView = getParentInfoOfListView(listViewHolder, viewGroup);
            if (((View) parentInfoOfListView[0]) == null) {
                Log.d("HoverHandler", "onDataLoaded failed. null parent");
                removeHoverPreview();
                return;
            }
            Log.d("HoverHandler", "onDataLoaded show");
            initializeValuesForCreateHoverView(listViewHolder, arrayList, viewGroup);
            onBindHoverView(context);
            onPreparedHoverView(parentInfoOfListView);
            setPointerIcon(this.mParentOfHoverPreview, 20010);
        }
    }

    /* access modifiers changed from: private */
    public void onHoverPreviewDeleteClick(int i2) {
        Log.d("HoverHandler", "onHoverPreviewDeleteClick");
        MediaItem item = this.mParams.getItem();
        if (item.isSharing()) {
            new DeleteSharedAlbumItemCmd().execute(this.mEventContext, AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DELETE, new MediaItem[]{item});
        } else if (TrashData.isTrash(item)) {
            EmptyTrashCmd emptyTrashCmd = new EmptyTrashCmd();
            EventContext eventContext = this.mEventContext;
            Boolean bool = Boolean.FALSE;
            emptyTrashCmd.execute(eventContext, new MediaItem[]{item}, bool, bool);
        } else {
            new DeleteCmd().execute(this.mEventContext, new MediaItem[]{item});
        }
        removeHoverPreview();
    }

    /* access modifiers changed from: private */
    public void onHoverPreviewItemClick(Context context, int i2, MediaItem mediaItem, boolean z, boolean z3) {
        Log.d("HoverHandler", "onHoverPreviewItemClick {" + z + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        if (z) {
            int i7 = this.mStartPosition;
            if (i2 + i7 >= 0) {
                onHoverPreviewItemClickAsync(i2 + i7, mediaItem);
            }
        } else if (this.mParams.isAlbum()) {
            int calcStartPosition = calcStartPosition(z3);
            ArrayList arrayList = new ArrayList();
            int i8 = 0;
            for (int i10 = calcStartPosition; i10 < Math.min(this.mHoverDataLoader.getCount(), this.mFirstLoadedItemCount + calcStartPosition); i10++) {
                arrayList.add(this.mHoverDataLoader.read(i10));
                i8++;
            }
            if (this.mStartPosition != calcStartPosition && arrayList.size() != 0) {
                this.mStartPosition = calcStartPosition;
                this.mEndPosition = (calcStartPosition + i8) - 1;
                this.mHoverPreviewListData.updateListData(arrayList);
                this.mHoverPreviewListView.updateData(this.mHoverPreviewListData, 0, arrayList.size());
            }
        }
    }

    private void onHoverPreviewItemClickAsync(int i2, MediaItem mediaItem) {
        a.k(i2, "onHoverPreviewItemClickAsync p=", "HoverHandler");
        String locationKey = this.mParams.getLocationKey();
        if (!this.mParams.isAlbum()) {
            i2 = this.mParams.getDataPosition();
        }
        if (this.mParams.isAlbum()) {
            if (this.mParams.getLocationKey() == null || this.mParams.getItem() == null) {
                Log.d("HoverHandler", "onHoverPreviewItemClickAsync failed");
                return;
            }
            locationKey = ArgumentsUtil.appendArgs(this.mHoverAlbumTargetKeyFinder.find(this.mParams.getLocationKey(), this.mParams.getItem(), this.mParams.getDataPosition()), "fixed_return_position_hover", String.valueOf(this.mParams.getDataPosition()));
        }
        Context context = this.mSelectedViewHolder.itemView.getContext();
        if (context != null) {
            this.mHoverStatusManager.playHapticSound(context, this.mToolType);
        }
        saveSharedTransitionData(this.mSelectedViewHolder, mediaItem, false);
        new VuLauncher(this.mBlackboard).launch(locationKey, i2, mediaItem);
        removeHoverPreview();
    }

    /* access modifiers changed from: private */
    public void onHoverPreviewShareClick(int i2) {
        Log.d("HoverHandler", "onHoverPreviewShareClick");
        MediaItem item = this.mParams.getItem();
        item.setExtra(ExtrasID.HOVER_DATA_POSITION, Integer.valueOf(this.mParams.getDataPosition()));
        ShareProvider.clearExtendedShareList(this.mEventContext.getActivity());
        new ShareViaCmd().execute(this.mEventContext, new MediaItem[]{item}, null);
        removeHoverPreview();
    }

    /* access modifiers changed from: private */
    public void onLoadCompleted() {
        Log.d("HoverHandler", "onLoadCompleted");
        this.mIsLoadCompleted = true;
        ViewGroup viewGroup = this.mHoverViewContainer;
        if (viewGroup != null) {
            viewGroup.setVisibility(0);
        }
    }

    private void onPreparedHoverView(Object[] objArr) {
        int i2 = 0;
        View view = objArr[0];
        Point point = new Point(this.mSelectedViewHolder.itemView.getLeft() + objArr[1].intValue(), this.mSelectedViewHolder.itemView.getTop() + objArr[2].intValue());
        Rect rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), this.mParentOfHoverPreview.getBottom());
        if (!this.mParams.getItem().isVideo()) {
            i2 = this.mParams.getItem().getOrientation();
        }
        int i7 = i2;
        if (this.mParams.isAlbum()) {
            this.mHoverViewSizeAndPosition.setForAlbum(this.mHoverViewContainer, this.mSelectedViewHolder, point, rect);
        } else {
            this.mHoverViewSizeAndPosition.set(this.mHoverViewContainer, this.mSelectedViewHolder, point, rect, i7);
        }
        this.mHoverPreviewListView.setDataAndSize(this.mHoverPreviewListData, this.mParams.isAlbum(), LocationKey.isSuggestionViewList(this.mParams.getLocationKey()), this.mParams.isHideOption(), this.mHoverViewContainer.getLayoutParams().width, this.mHoverViewContainer.getLayoutParams().height);
        setListViewListener();
    }

    /* access modifiers changed from: private */
    public void onRemoveHoverPreview() {
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, (Object) null), 200);
    }

    private void saveSharedTransitionData(ListViewHolder listViewHolder, MediaItem mediaItem, boolean z) {
        if (z) {
            SharedTransition.addSharedElement(this.mBlackboard, listViewHolder.getImage(), SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, listViewHolder.getBitmap()));
        }
    }

    private void setListViewListener() {
        this.mHoverPreviewListView.setOnLoadCompleteListener(new V4.a(this));
        this.mHoverPreviewListView.setOnRemoveListener(new V4.a(this));
        this.mHoverPreviewListView.setOnItemClickListener(new V4.a(this));
        this.mHoverPreviewListView.setOnShareClickListener(new V4.a(this));
        this.mHoverPreviewListView.setOnDeleteClickListener(new V4.a(this));
    }

    private void setPointerIcon(ViewGroup viewGroup, int i2) {
        if (viewGroup != null) {
            SeApiCompat.getHoverViewCompat().setPointerIcon(viewGroup.getRootView(), 2, PointerIcon.getSystemIcon(viewGroup.getContext(), i2));
            this.mPointerIconType = i2;
        }
    }

    public boolean onHover(ListViewHolder listViewHolder, MotionEvent motionEvent, HoverParams hoverParams) {
        boolean z;
        ViewGroup topParent = getTopParent(hoverParams.getContainer());
        if (!isHoverValid(listViewHolder, motionEvent, hoverParams)) {
            if (this.mHoverInvalidReason.get() == 3) {
                setPointerIcon(topParent, 20001);
                Log.d("HoverHandler", "reset pointer icon");
            }
            return false;
        }
        float x9 = motionEvent.getX();
        float y = motionEvent.getY();
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (this.mIsLoadCompleted && findHoverView(this.mHoverPreviewListView, rawX, rawY)) {
            return false;
        }
        ListViewHolder listViewHolder2 = this.mSelectedViewHolder;
        if ((listViewHolder2 == null || listViewHolder2 == listViewHolder) && findTargetView(listViewHolder.itemView, listViewHolder.getImage(), x9, y)) {
            z = true;
        } else {
            z = false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 9) {
                return enterHover(listViewHolder);
            }
            if (action == 10) {
                return exitHover(hoverParams.getContext());
            }
        } else if (!moveHover(z)) {
            return false;
        }
        this.mParams = hoverParams;
        if (!this.mIsLoadingHoverPreview) {
            loadHoverPreviewPopup(listViewHolder, motionEvent, topParent);
        }
        return true;
    }

    public void recycle() {
        this.mHoverDataLoader.release();
    }

    public void removeHoverPreview() {
        if (this.mParentOfHoverPreview != null) {
            Log.d("HoverHandler", "removeHoverPreview type=" + this.mPointerIconType + ", parent=" + Logger.toSimpleString((View) this.mParentOfHoverPreview));
            if (this.mPointerIconType != 20001) {
                setPointerIcon(this.mParentOfHoverPreview, 20001);
            }
            ViewUtils.removeView(this.mParentOfHoverPreview, this.mHoverViewContainer);
            this.mParentOfHoverPreview = null;
        }
        if (this.mHoverPreviewListView != null) {
            initializeListViewListener();
            this.mHoverPreviewListView.recycle();
            this.mHoverPreviewListView = null;
            this.mHoverViewContainer = null;
        }
        HoverPreviewListData hoverPreviewListData = this.mHoverPreviewListData;
        if (hoverPreviewListData != null) {
            hoverPreviewListData.recycle();
            this.mHoverPreviewListData = null;
        }
        this.mHoverAlbumTargetKeyFinder.onRemoveHoverPreview();
        this.mHoverDataLoader.release();
        initializeHoverPreviewVariable();
    }
}
