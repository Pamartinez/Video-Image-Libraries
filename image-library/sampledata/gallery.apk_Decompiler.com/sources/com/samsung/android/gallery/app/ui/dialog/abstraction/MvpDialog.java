package com.samsung.android.gallery.app.ui.dialog.abstraction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.samsung.android.gallery.app.ui.dialog.abstraction.IMvpDialogView;
import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialogPresenter;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MvpDialog<V extends IMvpDialogView, P extends MvpDialogPresenter> extends BaseDialog implements IMvpDialogView {
    protected P mPresenter;
    private int mResolution;

    private int calculateResolution(Configuration configuration) {
        return (configuration.screenWidthDp * 10000) + configuration.screenHeightDp;
    }

    private void setSoftInputMode(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            dialog.getWindow().setSoftInputMode(getSoftInputMode());
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Dialog createDialog = super.createDialog(bundle);
        setSoftInputMode(createDialog);
        return createDialog;
    }

    public abstract P createDialogPresenter(V v);

    public void dismissDialog() {
        dismissAllowingStateLoss();
    }

    public /* bridge */ /* synthetic */ Activity getActivity() {
        return getActivity();
    }

    public abstract int getLayoutId();

    public int getSoftInputMode() {
        return 0;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPresenter = createDialogPresenter(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int calculateResolution = calculateResolution(configuration);
        if (this.mResolution != calculateResolution) {
            this.mResolution = calculateResolution;
            handleResolutionChanged();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.NoTitleDialogStyle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        bindViews(inflate);
        inflate.setFitsSystemWindows(true);
        return inflate;
    }

    public void onDestroyView() {
        this.mPresenter.onDestroyView();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        if (getShowsDialog()) {
            postAnalyticsLog();
        }
    }

    public void handleResolutionChanged() {
    }

    public void bindViews(View view) {
    }
}
