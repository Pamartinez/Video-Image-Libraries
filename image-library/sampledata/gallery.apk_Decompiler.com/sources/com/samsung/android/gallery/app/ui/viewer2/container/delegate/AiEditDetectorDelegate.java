package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import B7.d;
import Ia.a;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.module.aiedit.PortraitDetector;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiEditDetectorDelegate extends AbsVuDelegate<IVuContainerView> {
    public static final boolean SUPPORT;
    private boolean mBottomSheetExpanded;

    static {
        boolean z;
        if (RemasterDetector.SUPPORT_UNIFIED || PortraitDetector.SUPPORT) {
            z = true;
        } else {
            z = false;
        }
        SUPPORT = z;
    }

    public AiEditDetectorDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (RemasterDetector.SUPPORT_UNIFIED && objArr.length > 1 && objArr[1].booleanValue()) {
            int intValue = objArr[0].intValue();
            if (intValue == 4) {
                this.mBottomSheetExpanded = false;
                RemasterDetector.getInstance().releaseOnStop();
            } else if (intValue == 2) {
                if (!this.mBottomSheetExpanded) {
                    RemasterDetector.getInstance().init();
                }
            } else if (intValue == 3 || intValue == 6) {
                this.mBottomSheetExpanded = true;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        if (RemasterDetector.SUPPORT_UNIFIED) {
            RemasterDetector.getInstance().open();
        } else if (PortraitDetector.SUPPORT) {
            PortraitDetector.getInstance().open();
        }
    }

    public void onDestroy() {
        if (RemasterDetector.SUPPORT_UNIFIED) {
            RemasterDetector.getInstance().close();
        } else if (PortraitDetector.SUPPORT) {
            PortraitDetector.getInstance().close();
        }
    }

    public void onStop() {
        if (RemasterDetector.SUPPORT_UNIFIED) {
            RemasterDetector.getInstance().releaseOnStop();
        } else if (PortraitDetector.SUPPORT) {
            PortraitDetector.getInstance().releaseOnStop();
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        if (RemasterDetector.SUPPORT_UNIFIED) {
            actionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new d(14, this));
            actionInvoker.add(ViewerAction.PREPARE_EDITOR, new a(1));
        }
    }
}
