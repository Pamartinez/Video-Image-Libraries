package com.samsung.android.gallery.module.utils;

import Y3.C0415b;
import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import i.C0212a;
import ua.C0711a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BlackboardUtils {
    public static void cancelAndEraseViewerBitmap(Blackboard blackboard, FileItemInterface fileItemInterface) {
        if (blackboard != null && fileItemInterface != null) {
            cancelAndEraseViewerBitmap(blackboard, "data://bitmap/viewer/" + fileItemInterface.getSimpleHashCode());
        }
    }

    public static void collectExternalDataChangedEvent(Blackboard blackboard, boolean z) {
        if (blackboard != null) {
            blackboard.post(new UriBuilder("command://CollectExternalDataChangeEvent").appendArg("enable", z).build(), (Object) null);
        }
    }

    @Deprecated
    public static void forceRefreshPicturesData(Blackboard blackboard, boolean z) {
        if (blackboard == null) {
            return;
        }
        if (!z) {
            blackboard.post("command:///RefreshWithoutDelay", (Object) null);
        } else {
            blackboard.postBroadcastEvent(EventMessage.obtain(101, 1, 0, (Object) null));
        }
    }

    public static void forceRefreshPicturesDataGlobal() {
        Blackboard.postGlobal("command:///RefreshWithoutDelay", (Object) null);
    }

    public static Runnable[] getAppTransitionCallback(Blackboard blackboard) {
        Runnable[] runnableArr;
        if (blackboard != null) {
            runnableArr = (Runnable[]) blackboard.pop("data://viewer_app_transition_callback");
        } else {
            runnableArr = null;
        }
        if (runnableArr != null) {
            return runnableArr;
        }
        return new Runnable[3];
    }

    public static DataKey.ShrinkType getViewerShrink(Blackboard blackboard) {
        if (blackboard != null) {
            return (DataKey.ShrinkType) blackboard.read("data://shrink_active", DataKey.ShrinkType.NONE);
        }
        return DataKey.ShrinkType.NONE;
    }

    public static boolean isRemasterServiceStarted() {
        return !Blackboard.getApplicationInstance().isEmpty("data://running_media_remaster");
    }

    public static boolean isViewerDragShrink(Blackboard blackboard) {
        if (getViewerShrink(blackboard) == DataKey.ShrinkType.DRAG) {
            return true;
        }
        return false;
    }

    public static boolean isViewerDragShrinkToCamera(Blackboard blackboard) {
        if (getViewerShrink(blackboard) == DataKey.ShrinkType.DRAG_CAMERA) {
            return true;
        }
        return false;
    }

    public static boolean isViewerShrink(Blackboard blackboard) {
        if (getViewerShrink(blackboard) != DataKey.ShrinkType.NONE) {
            return true;
        }
        return false;
    }

    public static boolean isViewerShrinkToCamera(Blackboard blackboard) {
        DataKey.ShrinkType viewerShrink = getViewerShrink(blackboard);
        if (viewerShrink == DataKey.ShrinkType.DRAG_CAMERA || viewerShrink == DataKey.ShrinkType.PINCH_CAMERA || viewerShrink == DataKey.ShrinkType.BACK_PRESSED_CAMERA) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishDataChangedToOtherActivities$0(String str, boolean z, String str2, Blackboard blackboard) {
        if (!str2.equals(str)) {
            refreshDataOnResume(blackboard, z);
        }
    }

    public static void postEventDataChanged(Blackboard blackboard, Object obj) {
        blackboard.post("command://event/DataChanged", obj);
    }

    public static void publishBackKeyEvent(Blackboard blackboard) {
        if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
            Activity activity = (Activity) blackboard.read("data://activity", null);
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getOnBackPressedDispatcher().onBackPressed();
                return;
            }
            return;
        }
        blackboard.post("command://simulateUserKey", 4);
    }

    public static void publishBackKeyEventWithDelay(Blackboard blackboard) {
        blackboard.post("command://simulateUserKeyWithDelay", 4);
    }

    public static void publishDataChangedToOtherActivities(Blackboard blackboard, boolean z) {
        Blackboard.getBlackboardMap().forEach(new C0711a(blackboard.getName(), z));
    }

    public static void publishDataRequest(Blackboard blackboard, String str) {
        if (str != null) {
            String removeArgs = ArgumentsUtil.removeArgs(str);
            String DATA_REQUEST = CommandKey.DATA_REQUEST(str);
            if (blackboard.isEmpty(DataKey.DATA_CURSOR(removeArgs))) {
                blackboard.publishIfEmpty(DATA_REQUEST, (Object) null);
            }
        }
    }

    public static boolean publishDataRequestForce(Blackboard blackboard, String str) {
        if (str == null) {
            return false;
        }
        String DATA_REQUEST = CommandKey.DATA_REQUEST(str);
        if (!blackboard.isEmpty(DATA_REQUEST)) {
            return false;
        }
        blackboard.erase(DataKey.DATA_CURSOR(str));
        blackboard.publish(DATA_REQUEST, (Object) null);
        return true;
    }

    public static String publishMediaItem(Blackboard blackboard, FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return null;
        }
        String str = "data://mediaItem/" + fileItemInterface.getSimpleHashCode();
        blackboard.publish(str, fileItemInterface);
        return str;
    }

    public static void publishRefreshOnResumeToAllActivities(boolean z) {
        Blackboard.getBlackboardMap().forEach(new C0415b(z, 1));
    }

    public static Activity readActivity(Blackboard blackboard) {
        if (blackboard != null) {
            return (Activity) blackboard.read("data://activity");
        }
        return null;
    }

    public static Context readAppContext(Blackboard blackboard) {
        return (Context) blackboard.read("data://app_context");
    }

    public static String readLocationKeyCurrent(Blackboard blackboard) {
        return (String) blackboard.read("location://variable/currentv1");
    }

    /* access modifiers changed from: private */
    public static void refreshDataOnResume(Blackboard blackboard, boolean z) {
        blackboard.publish(ArgumentsUtil.appendArgs("command://ForceRefreshOnResume", "force", String.valueOf(z)), Boolean.TRUE);
    }

    public static void removeDataChangeObservingDelay(Blackboard blackboard) {
        if (blackboard != null) {
            blackboard.post("command:///RefreshWithoutDelay", (Object) null);
        }
    }

    public static void removeOtherTabs(Blackboard blackboard, String str) {
        if (str == null) {
            return;
        }
        if (LocationKey.isTimeline(str) || "location://albums".equals(str) || "location://story/albums".equals(str) || "location://sharing/albums".equals(str)) {
            Log.d("BlackboardUtils", "remove other tabs");
            blackboard.publish("command://RemoveSiblingsFragments", new String[0]);
        }
    }

    public static void requestMediaDataReQuery(Blackboard blackboard) {
        if (blackboard != null) {
            blackboard.post("command://event/DataChanged", EventMessage.obtain(101, 1, 0, (Object) null));
        }
    }

    public static String requestViewerBitmap(Blackboard blackboard, FileItemInterface fileItemInterface, boolean z) {
        return requestViewerBitmap(blackboard, fileItemInterface, z, false);
    }

    public static Activity readActivity(String str) {
        if (str != null) {
            return readActivity(Blackboard.getInstance(str));
        }
        return null;
    }

    public static String requestViewerBitmap(Blackboard blackboard, FileItemInterface fileItemInterface, boolean z, boolean z3) {
        if (fileItemInterface == null) {
            return null;
        }
        String str = "data://bitmap/viewer/" + fileItemInterface.getSimpleHashCode();
        if (z3) {
            str = C0212a.A(str, "?isViewerEnter=true");
        }
        if (!blackboard.isEmpty(str)) {
            return null;
        }
        blackboard.publish(z ? CommandKey.DATA_REQUEST_URGENT(str) : CommandKey.DATA_REQUEST(str), fileItemInterface);
        return str;
    }

    public static void cancelAndEraseViewerBitmap(Blackboard blackboard, String str) {
        if (blackboard != null && str != null) {
            blackboard.post(CommandKey.DATA_REQUEST_CANCEL(str), (Object) null);
            blackboard.erase(str);
            Log.v("BlackboardUtils", "erase + cancel bitmap ".concat(str));
        }
    }
}
