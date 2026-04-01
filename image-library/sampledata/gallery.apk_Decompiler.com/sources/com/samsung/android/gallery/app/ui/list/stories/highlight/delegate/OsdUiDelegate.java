package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import A.a;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.concurrent.atomic.AtomicBoolean;
import o6.m;
import o6.u;
import o6.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OsdUiDelegate extends Delegate {
    private final AtomicBoolean mFistOsdSet = new AtomicBoolean(false);
    private int mFlag = 6;
    private View mToolbarGradient;

    public OsdUiDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    private boolean diffDecoUiFlag(int i2, int i7) {
        if ((i7 & 4) != (i2 & 4)) {
            return true;
        }
        return false;
    }

    private boolean diffSystemUiFlag(int i2, int i7) {
        if ((i7 & 2) != (i2 & 2)) {
            return true;
        }
        return false;
    }

    private int getFlags(boolean z, boolean z3) {
        int i2;
        if (z) {
            i2 = 3;
        } else {
            i2 = 1;
        }
        if (z3) {
            return i2 | 4;
        }
        return i2;
    }

    private int getOsdFlags() {
        boolean z = false;
        if (isBottomSheetVisible()) {
            if ((this.mFlag & 4) != 0) {
                z = true;
            }
            return getFlags(true, z);
        } else if (isRelatedViewVisible()) {
            return getFlags(true, false);
        } else {
            if (isSystemUiVisibleOnly()) {
                return getFlags(false, false);
            }
            return this.mFlag;
        }
    }

    private int getOsdOnFlag() {
        return getFlags(true, !isRelatedViewVisible());
    }

    private String getState(int i2, int i7) {
        String str;
        String str2;
        StringBuilder s = C0212a.s(a.d(i2, i7, "", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        if ((i7 & 2) != 0) {
            str = "S";
        } else {
            str = "s";
        }
        s.append(str);
        StringBuilder s5 = C0212a.s(s.toString());
        if ((i7 & 4) != 0) {
            str2 = "D";
        } else {
            str2 = "d";
        }
        s5.append(str2);
        return s5.toString();
    }

    /* access modifiers changed from: private */
    public void initializeUiState(Object... objArr) {
        ThreadUtil.postOnUiThread(new v(this, 1));
    }

    private boolean isBottomSheetVisible() {
        return ((Boolean) this.mEventHandler.requestData(DataRequest.LIST_VIEW_VISIBLE, Boolean.FALSE)).booleanValue();
    }

    private boolean isOsdOn() {
        if ((this.mFlag & 2) != 0) {
            return true;
        }
        return false;
    }

    private boolean isRelatedViewVisible() {
        return this.mEventHandler.isShowingRelatedView();
    }

    private boolean isSystemUiVisible() {
        if ((this.mFlag & 2) != 0) {
            return true;
        }
        return false;
    }

    private boolean isSystemUiVisibleOnly() {
        int i2 = this.mFlag;
        if ((i2 & 2) == 0 || (i2 & 4) != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$0(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(isSystemUiVisible());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeUiState$1() {
        if (!this.mFistOsdSet.getAndSet(true)) {
            setOsdOnOff(1, false);
            this.mEventHandler.lambda$postEvent$0(Event.UPDATE_WINDOW_DECO, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        this.mEventHandler.postEvent(Event.UPDATE_OSD, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void setOnOffOsd(Object... objArr) {
        int i2;
        if (objArr[0].booleanValue()) {
            i2 = getOsdOnFlag();
        } else {
            i2 = 1;
        }
        Log.d(this.TAG, "setOnOffOsd", objArr[0], getState(this.mFlag, i2));
        setOsdOnOff(i2, true);
        this.mEventHandler.lambda$postEvent$0(Event.HIDE_GUIDE_DECO, new Object[0]);
    }

    private void setOsdOnOff(int i2, boolean z) {
        boolean z3;
        int i7 = this.mFlag;
        if (i7 != i2) {
            boolean z7 = false;
            if (diffSystemUiFlag(i7, i2)) {
                if ((i2 & 2) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                setSystemUiVisible(z3);
            }
            if (diffDecoUiFlag(this.mFlag, i2)) {
                if ((i2 & 4) != 0) {
                    z7 = true;
                }
                updateDecoView(z7, z);
            }
            this.mFlag = i2;
        }
    }

    private void setSystemUiVisible(boolean z) {
        if (!this.mView.isInMultiWindowMode()) {
            this.mView.handlePostEvent(Event.FRAGMENT_NAVIGATION_VISIBLE, Boolean.valueOf(z));
        }
    }

    /* access modifiers changed from: private */
    public void setSystemUiVisibleForce() {
        boolean z;
        if ((this.mFlag & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        setSystemUiVisible(z);
    }

    /* access modifiers changed from: private */
    public void toggleOsd(Object... objArr) {
        int i2;
        if (!isOsdOn()) {
            i2 = getOsdOnFlag();
        } else {
            i2 = 1;
        }
        Log.d(this.TAG, "toggleOsd", Boolean.valueOf(isOsdOn()), getState(this.mFlag, i2));
        setOsdOnOff(i2, true);
        this.mEventHandler.lambda$postEvent$0(Event.HIDE_GUIDE_DECO, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void updateDecoUiVisibility(Object... objArr) {
        int i2;
        Boolean bool = objArr[0];
        boolean booleanValue = bool.booleanValue();
        int i7 = this.mFlag;
        if (booleanValue) {
            i2 = 4;
        } else {
            i2 = -5;
        }
        int i8 = i2 & i7;
        Log.d(this.TAG, "updateDeco", bool, getState(i7, i8));
        setOsdOnOff(i8, true);
    }

    private void updateDecoView(boolean z, boolean z3) {
        if (!this.mView.isDestroyed()) {
            this.mEventHandler.lambda$postEvent$0(Event.ENABLE_BOTTOM_DECO, Boolean.valueOf(z), Boolean.valueOf(z3));
            ViewUtils.setVisibleOrGone(this.mView.getToolbar(), z);
            ViewUtils.setVisibleOrGone(this.mToolbarGradient, z);
        }
    }

    /* access modifiers changed from: private */
    public void updateOsd(Object... objArr) {
        int osdFlags = getOsdFlags();
        if (this.mView.isBackPressed() || this.mView.isDestroyed()) {
            Log.d(this.TAG, "ignore updateOsd while destroying");
            return;
        }
        Log.d(this.TAG, "updateOsd", getState(this.mFlag, osdFlags));
        setOsdOnOff(osdFlags, true);
    }

    public void addListenEvent() {
        addEvent(Event.ENTER_TRANSITION_START, new u(this, 0));
        addEvent(Event.ENTER_TRANSITION_END, new u(this, 0));
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED, new u(this, 1));
        addEvent(Event.TOGGLE_OSD, new u(this, 2));
        addEvent(Event.ON_OFF_OSD, new u(this, 3));
        addEvent(Event.UPDATE_OSD, new u(this, 4));
        addEvent(Event.UPDATE_DECO_UI_VISIBILITY, new u(this, 5));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.IS_OSD_VISIBLE, new m(1, this));
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        ThreadUtil.postOnUiThread(new v(this, 0));
    }

    public void initView(View view) {
        super.initView(view);
        this.mToolbarGradient = view.findViewById(R.id.toolbar_gradient_view);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        setSystemUiVisibleForce();
    }

    public void setScreenMode() {
        if (this.mFistOsdSet.get()) {
            this.mEventHandler.lambda$postEvent$0(Event.UPDATE_WINDOW_DECO, new Object[0]);
        }
    }
}
