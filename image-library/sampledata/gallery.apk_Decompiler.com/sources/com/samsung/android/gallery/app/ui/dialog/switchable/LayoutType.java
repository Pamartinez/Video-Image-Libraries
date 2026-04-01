package com.samsung.android.gallery.app.ui.dialog.switchable;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.DialogTitle;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import q8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LayoutType {
    private static final HashMap<Integer, LayoutType> sMap = new HashMap<>();
    protected Bundle mArguments;
    protected View mLayout;
    private DialogTitle mTitle;

    private void bindLayout(ViewGroup viewGroup) {
        this.mLayout = viewGroup.findViewById(getLayoutId());
    }

    private void bindTitle(ViewGroup viewGroup) {
        if (this.mTitle == null) {
            this.mTitle = (DialogTitle) viewGroup.findViewById(R.id.dialog_title);
        }
    }

    private void bindView(ViewGroup viewGroup) {
        bindLayout(viewGroup);
        bindTitle(viewGroup);
        bindViewInternal();
    }

    public static void clear() {
        sMap.clear();
    }

    public static LayoutType createLayoutType(int i2) {
        if (i2 == 0) {
            return new Confirm();
        }
        if (i2 == 1) {
            return new Progress();
        }
        if (i2 != 2) {
            return new Unknown();
        }
        return new FileOperation();
    }

    public static LayoutType get(int i2) {
        return sMap.computeIfAbsent(Integer.valueOf(i2), new a(28));
    }

    private void publishInternal(SwitchableDialog.PublishInfo publishInfo) {
        publishInfo.mBlackboard.post(publishInfo.mPublishKey, publishInfo.mConfirm);
        postAnalyticsLog(publishInfo);
    }

    public abstract void bindViewInternal();

    public boolean equals(Object obj) {
        if (!(obj instanceof LayoutType) || getTypeInteger() != ((LayoutType) obj).getTypeInteger()) {
            return false;
        }
        return true;
    }

    public String[] getButtonStrings(Resources resources) {
        return new String[]{resources.getString(R.string.cancel), getString("option1"), getString("option2")};
    }

    public View getLayout() {
        return this.mLayout;
    }

    public abstract int getLayoutId();

    public String getString(String str) {
        return this.mArguments.getString(str, (String) null);
    }

    public abstract int getTypeInteger();

    public void initLayout(ViewGroup viewGroup, Bundle bundle) {
        initValues(bundle);
        bindView(viewGroup);
        initViews();
    }

    public void initTextView(TextView textView, String str) {
        boolean z = false;
        if (str != null) {
            textView.setText(str);
            textView.setVisibility(0);
        }
        if (str != null) {
            z = true;
        }
        ViewUtils.setVisibleOrGone(textView, z);
    }

    public void initValues(Bundle bundle) {
        this.mArguments = bundle;
    }

    public abstract void initViews();

    public boolean isCancelable() {
        return true;
    }

    public void onCancel(SwitchableDialog.PublishInfo publishInfo) {
        publishInternal(publishInfo);
    }

    public boolean onCancelClicked(SwitchableDialog.PublishInfo publishInfo) {
        publishInternal(publishInfo);
        return true;
    }

    public void onOption1Clicked(SwitchableDialog.PublishInfo publishInfo) {
        publishInternal(publishInfo);
    }

    public void onOption2Clicked(SwitchableDialog.PublishInfo publishInfo) {
        publishInternal(publishInfo);
    }

    public void postAnalyticsLog(SwitchableDialog.PublishInfo publishInfo) {
        if (publishInfo.mEventId != null) {
            AnalyticsLogger.getInstance().postLog(publishInfo.mScreenId, publishInfo.mEventId);
        }
    }

    public void setTitle(String str, boolean z) {
        boolean z3;
        if (str != null) {
            this.mTitle.setText(str);
        }
        DialogTitle dialogTitle = this.mTitle;
        if (str != null || !z) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewUtils.setVisibleOrGone(dialogTitle, z3);
    }
}
