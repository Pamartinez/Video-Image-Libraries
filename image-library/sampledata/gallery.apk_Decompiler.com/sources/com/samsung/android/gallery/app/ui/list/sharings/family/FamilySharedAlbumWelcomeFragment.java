package com.samsung.android.gallery.app.ui.list.sharings.family;

import B4.c;
import M4.d;
import N5.a;
import N5.b;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.appcompat.widget.SeslProgressBar;
import com.samsung.android.gallery.app.controller.externals.CreateSamsungFamilyGroupCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSetupWizardType;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.StartSharingServiceSetupWizardCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.module.account.FamilyGroupHelper;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FamilySharedAlbumWelcomeFragment<V extends IMvpBaseView, P extends MvpBasePresenter> extends MvpBaseFragment<V, P> {
    private boolean mFromPicker;
    private boolean mFromSamsungAccount;
    private TextView mGetStartedButton;
    private TextView mLaterButton;
    private SeslProgressBar mProgress;
    private TextView mQuotaDescriptionView;
    private View mScrollView;
    private View mTitle;

    private void applyQuotaValueOnDescriptionView() {
        this.mQuotaDescriptionView.setText(getString(R.string.space_for_so_many_memories_description, 5, 6));
    }

    private void createFamilyAlbum() {
        MdeSharingService.getInstance().connectSessionAsync(2, new ConnectListener() {
            public void onFailure(int i2) {
                StringCompat access$100 = FamilySharedAlbumWelcomeFragment.this.TAG;
                Log.sh(access$100, "connectSession - onFailure - " + i2);
                if (i2 == 3 || !MdeAuthHelper.isReadyToUseShareService()) {
                    new StartSharingServiceSetupWizardCmd().execute(FamilySharedAlbumWelcomeFragment.this.mPresenter, RequestSetupWizardType.SETUP_SHARING_SERVICE);
                }
                FamilySharedAlbumWelcomeFragment.this.destroyFragment();
            }

            public void onSuccess() {
                new RequestSharedAlbumCmd().execute(FamilySharedAlbumWelcomeFragment.this.mPresenter, RequestCmdType.REQUEST_CREATE_FAMILY_SPACE, null);
            }
        });
    }

    private void createGetStartedButton(View view) {
        TextView textView = (TextView) view.findViewById(R.id.button_get_started);
        this.mGetStartedButton = textView;
        textView.setOnClickListener(new b(this, 1));
        ViewUtils.setAccessibilityRoleDescription(this.mGetStartedButton, R.string.speak_button);
    }

    private void createLaterButton(View view) {
        TextView textView = (TextView) view.findViewById(R.id.button_later);
        this.mLaterButton = textView;
        textView.setOnClickListener(new b(this, 0));
        ViewUtils.setAccessibilityRoleDescription(this.mLaterButton, R.string.speak_button);
    }

    /* access modifiers changed from: private */
    public void destroyFragment() {
        finishFragment();
        if (this.mFromSamsungAccount) {
            moveToSharings();
        } else if (this.mFromPicker) {
            finishFragment();
            stopSelections();
        }
    }

    private void dismissProgressDialog() {
        this.mProgress.setVisibility(4);
    }

    private void finishActivity() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new d(19));
    }

    private void finishFragment() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new d(22));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGetStartedButton$0(View view) {
        onGetStartedButtonClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createLaterButton$1(View view) {
        onLaterButtonClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetStartedButtonClicked$2(boolean z) {
        if (z) {
            createFamilyAlbum();
        } else {
            launchAccountForCreation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetStartedButtonClicked$3() {
        ThreadUtil.postOnUiThread(new c((Object) this, FamilyGroupHelper.hasFamilyGroup(getContext()), 13));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandleEvent$5() {
        dismissProgressDialog();
        destroyFragment();
    }

    private void launchAccountForCreation() {
        new CreateSamsungFamilyGroupCmd().execute(this.mPresenter, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void loadArgument(String str) {
        this.mFromSamsungAccount = ArgumentsUtil.getArgValue(str, "from_samsung_account", false);
        this.mFromPicker = ArgumentsUtil.getArgValue(str, "from_picker", false);
    }

    private void moveToSharings() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new d(20));
    }

    private boolean needToDelayDestroy(int i2) {
        if (this.mFromSamsungAccount || i2 != 6014) {
            return false;
        }
        return true;
    }

    private void onGetStartedButtonClicked() {
        postAnalyticsLog(AnalyticsEventId.EVENT_FAMILY_SHARED_ALBUM_GET_STARTED);
        SimpleThreadPool.getInstance().execute(new a(this, 1));
    }

    private void onLaterButtonClicked() {
        postAnalyticsLog(AnalyticsEventId.EVENT_FAMILY_SHARED_ALBUM_LATER);
        if (this.mFromSamsungAccount) {
            finishActivity();
        } else {
            finishFragment();
        }
    }

    private void showProgressDialog() {
        this.mProgress.setVisibility(0);
        this.mGetStartedButton.setText("");
        this.mGetStartedButton.setAlpha(0.4f);
        this.mLaterButton.setAlpha(0.4f);
        this.mLaterButton.setOnClickListener((View.OnClickListener) null);
    }

    private void stopSelections() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new d(21));
    }

    public P createPresenter(V v) {
        return new MvpBasePresenter(this.mBlackboard, v) {
            public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_family_album_welcome;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_FAMILY_ALBUM_WELCOME.toString();
    }

    public void initView(View view) {
        this.mQuotaDescriptionView = (TextView) view.findViewById(R.id.quota_description);
        applyQuotaValueOnDescriptionView();
        this.mTitle = view.findViewById(R.id.title);
        this.mScrollView = view.findViewById(R.id.scrollView);
        this.mProgress = (SeslProgressBar) view.findViewById(R.id.progress);
        createGetStartedButton(view);
        createLaterButton(view);
    }

    public boolean needToRegisterInsetListener() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (SdkConfig.atLeast(SdkConfig.GED.P) && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            adjustContentAreaMargin(view, windowInsets, isApplyWindowInsets(windowInsets));
        }
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Optional.ofNullable(getArguments()).map(new L5.b(20)).ifPresent(new K5.a(15, this));
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ((ViewGroup.MarginLayoutParams) this.mTitle.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.family_album_welcome_page_title_bottom_margin);
        ((ViewGroup.MarginLayoutParams) this.mScrollView.getLayoutParams()).setMargins(getResources().getDimensionPixelOffset(R.dimen.family_album_welcome_page_body_side_margin), getResources().getDimensionPixelOffset(R.dimen.family_album_welcome_page_title_top_margin), getResources().getDimensionPixelOffset(R.dimen.family_album_welcome_page_body_side_margin), 0);
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        long j2;
        int i2 = eventMessage.what;
        switch (i2) {
            case 6012:
                createFamilyAlbum();
                return true;
            case 6013:
                showProgressDialog();
                return true;
            case 6014:
            case 6015:
                a aVar = new a(this, 0);
                if (needToDelayDestroy(i2)) {
                    j2 = 2000;
                } else {
                    j2 = 0;
                }
                ThreadUtil.postOnUiThreadDelayed(aVar, j2);
                return false;
            default:
                return super.onHandleEvent(eventMessage);
        }
    }

    public void registerWindowInsetListener(List<View> list) {
        if (needToRegisterInsetListener()) {
            list.add(getView());
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportFloatingUi() {
        return false;
    }
}
