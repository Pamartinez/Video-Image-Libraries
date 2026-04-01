package com.samsung.android.gallery.app.ui.list.pictures;

import C3.C0391a;
import android.os.Bundle;
import c4.C0438h;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sum.core.descriptor.b;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesPresenter<V extends IPicturesView> extends BaseListPresenter<V> {
    public PicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(Object obj, Bundle bundle) {
        Optional.ofNullable(((IPicturesView) this.mView).getAdapter()).ifPresent(new C0438h(28));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleEvent$1(PicturesViewAdapter picturesViewAdapter) {
        if (picturesViewAdapter.checkLocationAuthChanged()) {
            picturesViewAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$2(PicturesViewAdapter picturesViewAdapter) {
        Log.st(this.TAG, "handleEvent#EVENT_VIEWER_SHRINK_END {-1}");
        picturesViewAdapter.sizeDownThumbnail(-1);
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        if (PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO) {
            arrayList.add(new SubscriberInfo("global://event/non_destruction/dataChanged", new C0391a(25, this)).setWorkingOnUI());
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public int getCurrentViewDepth() {
        return ((IPicturesView) this.mView).getListView().getDepth();
    }

    public int getMaxDepth() {
        return ((IPicturesView) this.mView).getListView().getMaxDepth();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 1026) {
            if (i2 != 1057) {
                if (i2 == 1058) {
                    Optional.ofNullable(((IPicturesView) this.mView).getAdapter()).ifPresent(new C0438h(27));
                    return true;
                } else if (i2 != 3016) {
                    if (i2 != 3017) {
                        return super.handleEvent(eventMessage);
                    }
                    Optional.ofNullable(((IPicturesView) this.mView).getAdapter()).ifPresent(new b(16, this));
                    if (eventMessage.what == 3017) {
                        this.mBlackboard.postEvent(EventMessage.obtain(1135));
                        return true;
                    }
                } else if (((Boolean) this.mBlackboard.read("data://user/favoriteUpdatged", Boolean.FALSE)).booleanValue()) {
                    int findLastVisibleItemPosition = ((IPicturesView) this.mView).getLayoutManager().findLastVisibleItemPosition();
                    for (int findFirstVisibleItemPosition = ((IPicturesView) this.mView).getLayoutManager().findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                        ListViewHolder listViewHolder = (ListViewHolder) ((IPicturesView) this.mView).getListView().findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                        if (listViewHolder == null) {
                            Log.w(this.TAG, "fail to update media item since holder is null");
                        } else {
                            ((IPicturesView) this.mView).getAdapter().rebindMediaItemToHolder(listViewHolder);
                            listViewHolder.updateDecoItemViews();
                        }
                    }
                    this.mBlackboard.replace("data://user/favoriteUpdatged", Boolean.FALSE);
                    return true;
                }
            } else if (((IPicturesView) this.mView).isViewActive() && ((IPicturesView) this.mView).supportDeleteAnimation()) {
                Optional.ofNullable(((IPicturesView) this.mView).getAdapter()).ifPresent(new C0438h(26));
            }
            return true;
        }
        Optional.ofNullable(((IPicturesView) this.mView).getAdapter()).ifPresent(new C0438h(25));
        return true;
    }

    public boolean isAllowItemClick() {
        if (((IPicturesView) this.mView).useAdvancedMouseDragAndDrop()) {
            Blackboard blackboard = this.mBlackboard;
            Boolean bool = Boolean.FALSE;
            if (((Boolean) blackboard.pop("data://motion_event_tool_type_mouse", bool)).booleanValue()) {
                return ((Boolean) this.mBlackboard.pop("data://gesture_on_double_tapped", bool)).booleanValue();
            }
        }
        return super.isAllowItemClick();
    }

    public boolean isSelectMenuEnabled() {
        PicturesViewAdapter adapter = ((IPicturesView) this.mView).getAdapter();
        if (adapter == null || adapter.isMonthForViewing() || adapter.checkIfEmpty()) {
            return false;
        }
        return true;
    }

    public String makeSubtitle(int i2, int i7) {
        return StringResources.getCountString(i2, i7);
    }

    public final void onExpandItemClick(int i2, MediaItem mediaItem) {
        if (setInputBlock(this.TAG + "_onExpandItemClick") && i2 >= 0) {
            Blackboard blackboard = this.mBlackboard;
            Boolean bool = Boolean.FALSE;
            if (((Boolean) blackboard.read("data://on_location_moving", bool)).booleanValue()) {
                Log.e(this.TAG, "skip OnListItemClick. on location moving");
                return;
            }
            postAnalyticsLog(AnalyticsEventId.EVENT_TOUCH_EXPAND_BUTTON);
            if (((IPicturesView) this.mView).getAdapter().getSelectableChecker() != null) {
                this.mBlackboard.publish("data://user/pick/ItemChecker", ((IPicturesView) this.mView).getAdapter().getSelectableChecker());
            }
            this.mBlackboard.post("command://HideBottomBar", bool);
            new VuLauncher(this.mBlackboard).addTrueArgument("from_expand").launch(getViewerLocationKey(), i2, mediaItem);
        }
    }

    public final void onLocationItemClick(String str) {
        if (setInputBlock(this.TAG + "_onLocationItemClick")) {
            onLocationItemClickInternal(str);
        }
    }

    public void onLocationItemClickInternal(String str) {
        this.mBlackboard.post("command://MoveURL", str);
        postAnalyticsLog(AnalyticsEventId.EVENT_TOUCH_LOCATION_TAG);
    }

    public void onRequestBeamData(Object obj, Bundle bundle) {
        if (isSelectionMode() && PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(getSelectedItemCount());
            if (getSelectedItemCount() <= 500) {
                objArr[1] = getSelectedItems();
            }
            this.mBlackboard.publish("data://user/Beam", objArr);
        }
    }

    public void onViewChanged(Object obj, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onViewChanged " + obj);
        changeViewDepth(((IPicturesView) this.mView).getListView(), ((Integer) obj).intValue());
    }

    public void updateViewHolderMargin() {
        if (((IPicturesView) this.mView).getAdapter() != null) {
            int findLastVisibleItemPosition = ((IPicturesView) this.mView).getLayoutManager().findLastVisibleItemPosition();
            for (int findFirstVisibleItemPosition = ((IPicturesView) this.mView).getLayoutManager().findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                ListViewHolder listViewHolder = (ListViewHolder) ((IPicturesView) this.mView).getListView().findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                if (listViewHolder == null) {
                    Log.w(this.TAG, "fail to update media item since holder is null");
                } else {
                    ((IPicturesView) this.mView).getAdapter().setViewHolderMargin(listViewHolder);
                }
            }
        }
    }
}
