package com.samsung.android.gallery.widget.remaster;

import android.app.Activity;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout;
import com.samsung.android.gallery.widget.utils.SystemUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterLayoutFactory {

    /* renamed from: com.samsung.android.gallery.widget.remaster.RemasterLayoutFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$remaster$AbstractRemasterLayout$LayoutType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout$LayoutType[] r0 = com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout.LayoutType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$remaster$AbstractRemasterLayout$LayoutType = r0
                com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout$LayoutType r1 = com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout.LayoutType.NORMAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$remaster$AbstractRemasterLayout$LayoutType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout$LayoutType r1 = com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout.LayoutType.FOLD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$remaster$AbstractRemasterLayout$LayoutType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout$LayoutType r1 = com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout.LayoutType.TABLE_MODE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.remaster.RemasterLayoutFactory.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean isTableState(Activity activity) {
        FoldStateManager instance;
        if (!Features.isEnabled(Features.SUPPORT_TABLE_MODE) || (instance = FoldStateManager.getInstance(Blackboard.getInstance(activity.toString()))) == null || !instance.isTableMode() || DeviceInfo.isDexMode(activity) || SystemUi.isInMultiWindowMode(activity)) {
            return false;
        }
        return true;
    }

    public AbstractRemasterLayout create(Activity activity, ViewGroup viewGroup) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$remaster$AbstractRemasterLayout$LayoutType[getLayoutType(activity).ordinal()];
        if (i2 == 1) {
            return new NormalRemasterLayout(activity, viewGroup);
        }
        if (i2 == 2) {
            return new FoldRemasterLayout(activity, viewGroup);
        }
        if (i2 == 3) {
            return new TableModeRemasterLayout(activity, viewGroup);
        }
        throw new IllegalStateException("Layout type is incorrect");
    }

    public AbstractRemasterLayout.LayoutType getLayoutType(Activity activity) {
        if (isTableState(activity)) {
            return AbstractRemasterLayout.LayoutType.TABLE_MODE;
        }
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES) || AppResources.getBoolean(R$bool.supportFoldRemaster)) {
            return AbstractRemasterLayout.LayoutType.FOLD;
        }
        return AbstractRemasterLayout.LayoutType.NORMAL;
    }
}
