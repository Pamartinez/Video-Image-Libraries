package com.samsung.android.gallery.settings.ui;

import A4.B;
import D3.i;
import E9.a;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseFragment extends Fragment implements IBaseView {
    protected final String TAG = getClass().getSimpleName();
    private Blackboard mBlackboard;
    private final BasePresenter mPresenter = createPresenter();
    private boolean mViewActive;

    private ActionBar getActionBar() {
        return (ActionBar) Optional.ofNullable((AppCompatActivity) getActivity()).map(new i(20)).orElse((Object) null);
    }

    private String getBlackboardTag() {
        try {
            return getActivity().toString();
        } catch (Exception unused) {
            Log.e(this.TAG, "fail to refer activity");
            return null;
        }
    }

    private Toolbar getToolbar() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            return (Toolbar) blackboard.read("data://settings/toolbar");
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$0(ActionBar actionBar) {
        actionBar.setTitle(getTitleId());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    public BasePresenter createPresenter() {
        return new BasePresenter(this);
    }

    public /* bridge */ /* synthetic */ Activity getActivity() {
        return getActivity();
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public CollapsingToolbarLayout getCollapsingToolbar() {
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            return (CollapsingToolbarLayout) toolbar.getRootView().findViewById(R$id.toolbar_layout);
        }
        return null;
    }

    public FloatingToolbarLayout getFloatingToolbarLayout() {
        Toolbar toolbar = getToolbar();
        if (toolbar == null || !(toolbar.getParent() instanceof FloatingToolbarLayout)) {
            return null;
        }
        return (FloatingToolbarLayout) toolbar.getParent();
    }

    public abstract int getLayoutId();

    public BasePresenter getPresenter() {
        return this.mPresenter;
    }

    public String getScreenId() {
        return null;
    }

    public abstract int getTitleId();

    public boolean isActive() {
        if (getView() != null) {
            return true;
        }
        return false;
    }

    public boolean isDestroyed() {
        return !this.mViewActive;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        String blackboardTag = getBlackboardTag();
        if (blackboardTag != null) {
            this.mBlackboard = Blackboard.getInstance(blackboardTag);
        }
        this.mViewActive = true;
        this.mPresenter.onAttach(this.mBlackboard);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter.onCreate();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        bindViews((ViewGroup) inflate);
        return inflate;
    }

    public void onDestroy() {
        this.mViewActive = false;
        this.mPresenter.onDestroy();
        super.onDestroy();
    }

    public void onDetach() {
        this.mPresenter.onDetach();
        super.onDetach();
    }

    public boolean onHandleBroadcastEvent(Object obj, Bundle bundle) {
        return false;
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        return false;
    }

    public void onResume() {
        super.onResume();
        Log.d(this.TAG, "onResume");
        Optional.ofNullable(getActionBar()).ifPresent(new a(5, this));
    }

    public final void postAnalyticsLog(String str) {
        postAnalyticsLog(str, (Long) null);
    }

    public final void postEvent(EventMessage eventMessage) {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.postEvent(eventMessage);
        }
    }

    public void updateFragment() {
        try {
            getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commitNowAllowingStateLoss();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "updateFragment failed. e=", e.getMessage());
        }
    }

    public final void updateToolbarTitle(String str) {
        Optional.ofNullable(getCollapsingToolbar()).ifPresent(new B(str, 2));
    }

    public final void postAnalyticsLog(String str, Long l) {
        String screenId = getScreenId();
        if (screenId == null) {
            Log.e(this.TAG, "invalid screenId");
        } else if (l == null) {
            AnalyticsLogger.getInstance().postLog(screenId, str);
        } else {
            AnalyticsLogger.getInstance().postLog(screenId, str, l.longValue());
        }
    }

    public void bindViews(ViewGroup viewGroup) {
    }
}
