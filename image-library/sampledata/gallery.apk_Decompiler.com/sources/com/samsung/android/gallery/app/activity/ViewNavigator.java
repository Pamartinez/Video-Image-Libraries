package com.samsung.android.gallery.app.activity;

import C3.q;
import android.os.Bundle;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.controller.delegate.DatePickerDelegate;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewNavigator extends Subscriber {
    protected IGalleryActivityView mActivityView;
    protected ViewNavigatorController mDefaultController = createDefaultController();

    public ViewNavigator(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard);
        this.mActivityView = iGalleryActivityView;
    }

    /* access modifiers changed from: private */
    public void clearPreloadedData(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller clearPreloadedData");
        } else {
            getViewNavigatorController().clearPreloadedData(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        DatePickerDelegate.create(this.mActivityView.getActivity(), this.mBlackboard, bundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        this.mActivityView.clearBackStackImmediate();
    }

    /* access modifiers changed from: private */
    public void onActivityReenter(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onActivityResult");
        } else {
            getViewNavigatorController().onActivityReenter(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onActivityResult(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onActivityResult");
        } else {
            getViewNavigatorController().onActivityResult(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onCropImage(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onCropImage");
        } else {
            getViewNavigatorController().onCropImage(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onCurrent(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onCurrent");
            this.mBlackboard.erase("data://on_location_moving");
            return;
        }
        getViewNavigatorController().onCurrent(obj, bundle);
        this.mBlackboard.erase("data://on_location_moving");
    }

    /* access modifiers changed from: private */
    public void onDialog(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onDialog");
        } else {
            getViewNavigatorController().onDialog(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onHandleBroadcastEvent(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onHandleBroadcastEvent");
        } else {
            getViewNavigatorController().onHandleBroadcastEvent(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onHandleEvent(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onHandleEvent");
        } else if (this.mActivityView != null) {
            getViewNavigatorController().onHandleEvent(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onLocationEdit(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onLocationEdit");
        } else {
            getViewNavigatorController().onLocationEdit(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onMove(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onMove");
        } else {
            getViewNavigatorController().onMove(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onMoveUrl(Object obj, Bundle bundle) {
        if (Logger.isAllowPrivateLog()) {
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "onMoveUrl {" + obj + "}");
        }
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onMoveUrl");
            this.mBlackboard.erase("data://on_location_moving");
            return;
        }
        getViewNavigatorController().onMoveUrl(obj, bundle);
    }

    /* access modifiers changed from: private */
    public void onMoveUrlDirect(Object obj, Bundle bundle) {
        this.mBlackboard.publish("data://on_location_moving", Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public void onMoveView(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onMoveView");
        } else {
            getViewNavigatorController().onMoveView(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onPicker(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onPicker");
        } else {
            getViewNavigatorController().onPicker(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onRemove(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onRemove");
        } else {
            getViewNavigatorController().onRemoveUrl(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onShowDialog(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onShowDialog");
        } else {
            getViewNavigatorController().onShowDialog(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onTagEdit(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onTagEdit");
        } else {
            getViewNavigatorController().onTagEdit(obj, bundle);
        }
    }

    public ViewNavigatorController createDefaultController() {
        return new ViewNavigatorController(this.mBlackboard, this.mActivityView);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("location://variable/currentv1", new q(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_create", new q(this, 2)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_reenter", new q(this, 3)));
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_result", new q(this, 4)));
        arrayList.add(new SubscriberInfo("command://MoveURL", new q(this, 5)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://MoveURL", new q(this, 6)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://MoveCMD", new q(this, 7)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://RemoveURL", new q(this, 8)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://UiEvent", new q(this, 9)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://UiBroadcastEvent", new q(this, 10)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ShowDialog", new q(this, 11)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/dialog/#"), new q(this, 12)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/pick/#"), new q(this, 13)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/move/#"), new q(this, 14)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/EditLocation"), new q(this, 15)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/Crop"), new q(this, 16)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/fromTagEditor"), new q(this, 17)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/Date"), new q(this, 18)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ClearPreloadedData", new q(this, 19)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ClearBackStackFragments", new q(this, 1)).setWorkingOnUI());
    }

    public void dump(PrintWriter printWriter) {
        getViewNavigatorController().dump(printWriter);
    }

    public ViewNavigatorController getViewNavigatorController() {
        return this.mDefaultController;
    }

    public void onActivityCreate(Object obj, Bundle bundle) {
        if (getViewNavigatorController() == null) {
            Log.e(this.TAG, "null controller onActivityCreate");
        } else {
            getViewNavigatorController().onActivityCreate(obj, bundle);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mActivityView = null;
        ViewNavigatorController viewNavigatorController = this.mDefaultController;
        if (viewNavigatorController != null) {
            viewNavigatorController.onDestroy();
            this.mDefaultController = null;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onDestroy " + this);
    }
}
