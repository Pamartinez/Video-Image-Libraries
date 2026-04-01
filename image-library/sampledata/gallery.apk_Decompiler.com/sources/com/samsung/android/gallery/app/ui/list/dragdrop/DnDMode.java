package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.app.Activity;
import android.content.Context;
import android.view.DragEvent;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.SystemUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DnDMode {
    public static final boolean sSupportTwoHandedTouch = SdkConfig.atLeast(SdkConfig.SEM.T_MR5);
    private DragAndDrop mDragAndDrop;
    private int mMode = 0;

    public DnDMode(Context context) {
        if (DeviceInfo.isDexOnPc(context)) {
            this.mMode |= 1;
        }
        if (isSamsungFlowSmartViewEnabled(context)) {
            this.mMode |= 4;
        }
        if (SystemUi.isInMultiWindowMode((Activity) context)) {
            this.mMode |= 16;
        }
        if (YourPhone.isConnected(context)) {
            this.mMode |= 8;
        }
        if (DeviceInfo.isDexMode(context)) {
            this.mMode |= 2;
        }
        if (isMultiControlEnabled(context)) {
            this.mMode |= 32;
        }
        if (sSupportTwoHandedTouch) {
            this.mMode |= 64;
        }
    }

    public static DnDMode injectCustomMode(DragAndDrop dragAndDrop) {
        return new DnDMode(dragAndDrop);
    }

    private boolean isMultiControlEnabled(Context context) {
        if (SeApiCompat.getSettingsSystemInt(context, "input_sharing_started", 0) == 1) {
            return true;
        }
        return false;
    }

    private boolean isSamsungFlowSmartViewEnabled(Context context) {
        if (!Features.isEnabled(Features.USE_SAMSUNG_FLOW) || SeApiCompat.getSettingsSystemInt(context, "smartview_dnd_played", 0) != 1) {
            return false;
        }
        return true;
    }

    private boolean isSet(int i2) {
        if ((this.mMode & i2) > 0) {
            return true;
        }
        return false;
    }

    public boolean dropPossible(DragEvent dragEvent) {
        if (getHandler().isDragStartedFromGallery(dragEvent) || getHandler().isValidDropEvent(dragEvent)) {
            return true;
        }
        return false;
    }

    public DragAndDrop getHandler() {
        if (this.mDragAndDrop == null) {
            if (isNormal()) {
                this.mDragAndDrop = new NormalDragAndDrop();
            } else if (isDexOnPC()) {
                this.mDragAndDrop = new DexOnPCDragAndDrop();
            } else if (isSamsungFlow()) {
                this.mDragAndDrop = new SamsungFlowDragAndDrop();
            } else if (isYourPhone()) {
                this.mDragAndDrop = new MSYourPhoneDragAndDrop();
            } else if (isDex()) {
                this.mDragAndDrop = new DexDragAndDrop();
            } else if (isMultiControl()) {
                this.mDragAndDrop = new MultiControlDragAndDrop();
            } else if (isTwoHandedMode()) {
                this.mDragAndDrop = new TwoHandedDragAndDrop();
            } else if (isMultiWindow()) {
                this.mDragAndDrop = new MultiWindowDragAndDrop();
            } else {
                this.mDragAndDrop = new DummyDragAndDrop();
            }
        }
        return this.mDragAndDrop;
    }

    public boolean isDex() {
        return isSet(2);
    }

    public boolean isDexOnPC() {
        return isSet(1);
    }

    public boolean isGallerySolelyMode() {
        if (isNormal() || isTwoHandedOnly()) {
            return true;
        }
        return false;
    }

    public boolean isMultiControl() {
        return isSet(32);
    }

    public boolean isMultiWindow() {
        return isSet(16);
    }

    public boolean isNormal() {
        if (this.mMode == 0) {
            return true;
        }
        return false;
    }

    public boolean isSamsungFlow() {
        return isSet(4);
    }

    public boolean isTwoHandedMode() {
        return isSet(64);
    }

    public boolean isTwoHandedOnly() {
        if ((this.mMode ^ 64) == 0) {
            return true;
        }
        return false;
    }

    public boolean isYourPhone() {
        return isSet(8);
    }

    public String toString() {
        if (isNormal()) {
            return "Normal mode";
        }
        return "External mode";
    }

    private DnDMode(DragAndDrop dragAndDrop) {
        this.mDragAndDrop = dragAndDrop;
        if (sSupportTwoHandedTouch) {
            this.mMode = 64;
        }
    }
}
