package com.samsung.android.gallery.module.remote.v2;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.HashSet;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MirroringState {
    private final Set<MirroringConnectionParam> mConnectionParams = new HashSet();
    private MirroringConnectionState mConnectionState = MirroringConnectionState.DISCONNECTED;

    private void hideDMR() {
        Log.rm("MirroringState", "hideDMR " + Logger.v(this.mConnectionState, this.mConnectionParams));
        Blackboard.postGlobal("global://remote2/event/dmr_status_updated", Boolean.FALSE);
    }

    private void hidePresentation() {
        Log.rm("MirroringState", "hidePresentation " + Logger.v(this.mConnectionState, this.mConnectionParams));
        Blackboard.postGlobal("global://remote2/event/hide_presentation", (Object) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isDMRConnected$1(MirroringConnectionParam mirroringConnectionParam) {
        if (mirroringConnectionParam == MirroringConnectionParam.DMR) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isPaused$0(MirroringConnectionParam mirroringConnectionParam) {
        if (mirroringConnectionParam == MirroringConnectionParam.PAUSED) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$supportHighRes$2(MirroringConnectionParam mirroringConnectionParam) {
        if (mirroringConnectionParam == MirroringConnectionParam.HIGH_RES) {
            return true;
        }
        return false;
    }

    private void showDMR() {
        Log.rm("MirroringState", "showDMR " + Logger.v(this.mConnectionState, this.mConnectionParams));
        Blackboard.postGlobal("global://remote2/event/dmr_status_updated", Boolean.TRUE);
    }

    private void showPresentation() {
        Log.rm("MirroringState", "showPresentation " + Logger.v(this.mConnectionState, this.mConnectionParams));
        Blackboard.postGlobal("global://remote2/event/show_presentation", (Object) null);
    }

    private void updateSmartViewStatus() {
        Blackboard.postGlobal("global://remote2/event/smart_view_status_updated", (Object) null);
    }

    public synchronized void addParam(MirroringConnectionParam mirroringConnectionParam) {
        try {
            if (this.mConnectionParams.add(mirroringConnectionParam)) {
                if (mirroringConnectionParam.isHidingParam()) {
                    hidePresentation();
                } else if (mirroringConnectionParam == MirroringConnectionParam.DMR) {
                    showDMR();
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized boolean isBackgroundPlaying() {
        boolean z;
        if (this.mConnectionState != MirroringConnectionState.CONNECTED || !this.mConnectionParams.stream().anyMatch(new a(0))) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized boolean isConnected() {
        boolean z;
        if (this.mConnectionState != MirroringConnectionState.CONNECTED || !this.mConnectionParams.stream().noneMatch(new a(1))) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized boolean isDMRConnected() {
        boolean z;
        if (this.mConnectionState != MirroringConnectionState.CONNECTED || !this.mConnectionParams.stream().anyMatch(new a(3))) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized boolean isPaused() {
        boolean z;
        if (this.mConnectionState != MirroringConnectionState.CONNECTED || !this.mConnectionParams.stream().anyMatch(new a(4))) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized void removeParam(MirroringConnectionParam mirroringConnectionParam) {
        try {
            if (this.mConnectionParams.remove(mirroringConnectionParam)) {
                if (mirroringConnectionParam.isHidingParam()) {
                    showPresentation();
                } else if (mirroringConnectionParam == MirroringConnectionParam.DMR) {
                    Blackboard.postGlobal("command://FinishDlnaActivity", (Object) null);
                    hideDMR();
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void setConnectionState(MirroringConnectionState mirroringConnectionState) {
        try {
            MirroringConnectionState mirroringConnectionState2 = this.mConnectionState;
            this.mConnectionState = mirroringConnectionState;
            MirroringConnectionState mirroringConnectionState3 = MirroringConnectionState.CONNECTED;
            if (mirroringConnectionState == mirroringConnectionState3) {
                showPresentation();
            } else if (mirroringConnectionState2 == mirroringConnectionState3 && mirroringConnectionState != mirroringConnectionState3) {
                this.mConnectionParams.clear();
                hidePresentation();
                updateSmartViewStatus();
                Blackboard.getApplicationInstance().post("global://remote2/event/disconnect_dmr_content", (Object) null);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized boolean supportHighRes() {
        boolean z;
        if (this.mConnectionState != MirroringConnectionState.CONNECTED || !this.mConnectionParams.stream().anyMatch(new a(2))) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public void updateParam(MirroringConnectionParam mirroringConnectionParam, boolean z) {
        if (z) {
            addParam(mirroringConnectionParam);
        } else {
            removeParam(mirroringConnectionParam);
        }
    }
}
