package com.samsung.android.gallery.settings.ui;

import A.a;
import A4.O;
import D7.g;
import Fa.C0547a;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.settings.UpgradeManager;
import com.samsung.android.gallery.module.store.MarketHelper;
import com.samsung.android.gallery.settings.R$dimen;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$menu;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.helper.SettingLayoutUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import i.C0212a;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AboutFragment extends BaseFragment {
    private final AccessibilityDelegateCompat mAccessibilityDelegateCompat = new AccessibilityDelegateCompat() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            accessibilityNodeInfoCompat.setClickable(false);
        }
    };
    private TextView mAvailableTextView;
    private int mContainedButtonMinWidth;
    private int mLabsClickCount;
    private long mLabsLastClickedTime;
    private final MenuProvider mMenuProvider = new MenuProvider() {
        public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
            menu.clear();
            menuInflater.inflate(R$menu.menu_about_page, menu);
        }

        public boolean onMenuItemSelected(MenuItem menuItem) {
            if (menuItem.getItemId() != R$id.gallery_app_info) {
                return true;
            }
            new NavigateAppCmd((IBaseView) AboutFragment.this).startAppInfo(AboutFragment.this.getContext());
            return true;
        }
    };
    private TextView mOpenSourceTextView;
    private View mProgressBar;
    private TextView mTermsAndConditionsTextView;
    private Toast mToast;
    private TextView mUpdateButton;
    private int mUpgradeState;
    private TextView mVersionTextView;

    /* access modifiers changed from: private */
    public void allowDataUsageClicked() {
        MarketHelper.setUpgradeCheckRequired(true);
        onResume();
    }

    /* access modifiers changed from: private */
    public void denyDataUsageClicked() {
        MiscSettingPreference.AllowDataUsageForChn.setEnabled(false);
        MarketHelper.setUpgradeCheckRequired(false);
        onUpdateCheckCompleted(3);
    }

    private void initLayout() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleThreadPool.getInstance().execute(new g(11, this));
        if (!getPresenter().supportGalaxyApps()) {
            this.mProgressBar.setVisibility(8);
        } else if (isAllowDataUsageForChn()) {
            showDataUsageDialog();
        } else {
            new UpgradeManager(new O(24, this)).execute(getContext());
        }
        a.x(new StringBuilder("initLayout +"), currentTimeMillis, this.TAG);
    }

    private boolean isAllowDataUsageForChn() {
        if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || MiscSettingPreference.AllowDataUsageForChn.isEnabled()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindViews$0(View view) {
        new NavigateAppCmd((IBaseView) this).startOpenSourceLicense(view.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindViews$1(View view) {
        new NavigateAppCmd((IBaseView) this).startTermsAndConditions(view.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initLayout$3() {
        postEvent(EventMessage.obtain(7008, PackageMonitorCompat.getVersionName()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$2(FragmentActivity fragmentActivity) {
        fragmentActivity.addMenuProvider(this.mMenuProvider, getViewLifecycleOwner());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setLabsEnabling$4(View view, View view2) {
        if (!SettingPreference.GalleryLabs.support(view.getContext())) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLabsLastClickedTime < 1000) {
                this.mLabsClickCount++;
            } else {
                this.mLabsClickCount = 0;
            }
            this.mLabsLastClickedTime = currentTimeMillis;
            int i2 = this.mLabsClickCount;
            if (i2 == 9) {
                PocFeatures.setEnabled(PocFeatures.GalleryLabs, true);
                Blackboard.getApplicationInstance().post("global://setting/labs/enabling", Boolean.TRUE);
                Utils.showToast(getContext(), "[Labs] Gallery labs is enabled ", 1);
            } else if (i2 > 5) {
                Utils.showToast(getContext(), "[Labs] Gallery labs will be enabled -" + (9 - this.mLabsClickCount));
            }
        }
    }

    private void onDone(String str) {
        int i2;
        String str2 = this.TAG;
        Log.d(str2, "UpdateLayoutTask onPostExecute isAdded=" + isAdded());
        if (isAdded()) {
            this.mVersionTextView.setText(getString(R$string.version_name, str));
            TextView textView = this.mTermsAndConditionsTextView;
            if (getPresenter().supportTermsAndConditions()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            textView.setVisibility(i2);
            updateLayoutMargins();
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateCheckCompleted(int i2) {
        if (isActive()) {
            this.mUpgradeState = i2;
            this.mProgressBar.setVisibility(8);
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        this.mAvailableTextView.setText(R$string.your_application_is_up_to_date);
                        this.mUpdateButton.setVisibility(8);
                    }
                } else if (isAllowDataUsageForChn()) {
                    this.mAvailableTextView.setText(getString(R$string.check_for_app_updates));
                    this.mUpdateButton.setVisibility(0);
                    this.mUpdateButton.setText(R$string.try_again_button);
                }
                this.mAvailableTextView.setText(getString(R$string.check_for_app_updates));
                this.mUpdateButton.setVisibility(0);
                this.mUpdateButton.setText(R$string.try_again_button);
            } else {
                this.mAvailableTextView.setText(R$string.new_version_is_available);
                this.mUpdateButton.setVisibility(0);
            }
            if (!MarketHelper.isApkAvailable()) {
                Log.d(this.TAG, "onUpdateCheckCompleted hide");
                Blackboard.postEventGlobal(EventMessage.obtain(7007));
            } else if (i2 == 2) {
                Log.d(this.TAG, "onUpdateCheckCompleted show");
                Blackboard.postEventGlobal(EventMessage.obtain(7006));
            }
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateClicked(View view) {
        String str;
        boolean equals = this.mUpdateButton.getText().toString().equals(getResources().getString(R$string.try_again_button));
        if (!equals || !isAllowDataUsageForChn()) {
            if (equals) {
                str = AnalyticsEventId.EVENT_SETTING_ABOUT_GALLERY_RETRY.toString();
            } else {
                str = AnalyticsEventId.EVENT_SETTING_ABOUT_GALLERY_UPDATE.toString();
            }
            postAnalyticsLog(str);
            int i2 = this.mUpgradeState;
            if (i2 != 3) {
                if (i2 != 5) {
                    this.mUpdateButton.setVisibility(8);
                    MarketHelper.startGalaxyApps(getContext());
                    return;
                }
                this.mUpdateButton.setVisibility(8);
                this.mUpdateButton.setText(R$string.update);
                updateLayout(false);
            } else if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || (!NetworkUtils.isNetworkConnected(getContext()) && !NetworkUtils.isWifiConnected(getContext()))) {
                Toast toast = this.mToast;
                if (toast != null) {
                    toast.cancel();
                }
                Toast makeText = Toast.makeText(getContext(), R$string.no_network_connection_error, 0);
                this.mToast = makeText;
                makeText.show();
            } else {
                this.mUpdateButton.setVisibility(8);
                updateLayout(false);
            }
        } else {
            showDataUsageDialog();
        }
    }

    private void setLabsEnabling(View view) {
        ViewCompat.setAccessibilityDelegate(view, this.mAccessibilityDelegateCompat);
        view.setOnClickListener(new Ba.g(3, this, view));
    }

    private void showDataUsageDialog() {
        new DialogDelegate((IBasePreferenceView) null).showDataUsageDialog(getContext(), new DialogDelegate.OnDataUsageDialogButtonClickListener() {
            public void onNegativeClicked() {
                AboutFragment.this.denyDataUsageClicked();
            }

            public void onPositiveClicked() {
                AboutFragment.this.allowDataUsageClicked();
            }
        });
    }

    private void updateLayout(boolean z) {
        int i2;
        C0212a.x("updateLayout ", this.TAG, z);
        View view = this.mProgressBar;
        int i7 = 0;
        if (z) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        TextView textView = this.mAvailableTextView;
        if (!z) {
            i7 = 4;
        }
        textView.setVisibility(i7);
    }

    private void updateLayoutMargins() {
        float f;
        int i2;
        FragmentActivity activity = getActivity();
        if (isDestroyed() || activity == null) {
            Log.e(this.TAG, "updateLayoutMargins skip by destroyed");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean hasSideSpacing = SettingLayoutUtils.hasSideSpacing(activity);
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R$id.about_page_container);
        float width = (float) linearLayout.getWidth();
        if (hasSideSpacing) {
            f = 0.75f;
        } else {
            f = 0.8f;
        }
        int i7 = (int) (width * f);
        if (hasSideSpacing) {
            i2 = (int) (((float) linearLayout.getWidth()) * 0.6f);
        } else {
            i2 = this.mContainedButtonMinWidth;
        }
        if (getResources().getConfiguration().orientation == 1) {
            int i8 = DeviceInfo.getRealDisplayMetrics(activity).heightPixels;
            linearLayout.setPadding(0, (int) (((double) i8) * 0.05d), 0, (int) (((double) i8) * 0.15d));
        }
        this.mUpdateButton.measure(0, 0);
        this.mOpenSourceTextView.measure(0, 0);
        this.mTermsAndConditionsTextView.measure(0, 0);
        int asInt = Arrays.stream(new int[]{this.mOpenSourceTextView.getMeasuredWidth(), this.mTermsAndConditionsTextView.getMeasuredWidth()}).max().getAsInt();
        int max = Math.max(this.mUpdateButton.getMeasuredWidth(), i2);
        if (asInt > max) {
            max = Math.min(asInt, i7);
        }
        this.mUpdateButton.setWidth(max);
        this.mOpenSourceTextView.setWidth(max);
        this.mTermsAndConditionsTextView.setWidth(max);
        a.x(new StringBuilder("updateLayoutMargins +"), currentTimeMillis, this.TAG);
    }

    public void bindViews(ViewGroup viewGroup) {
        TextView textView = (TextView) viewGroup.findViewById(R$id.about_page_version);
        this.mVersionTextView = textView;
        setLabsEnabling(textView);
        TextView textView2 = (TextView) viewGroup.findViewById(R$id.about_page_update_button);
        this.mUpdateButton = textView2;
        textView2.setOnClickListener(new C0547a(this, 0));
        this.mAvailableTextView = (TextView) viewGroup.findViewById(R$id.about_page_available);
        this.mProgressBar = viewGroup.findViewById(R$id.about_progress);
        TextView textView3 = (TextView) viewGroup.findViewById(R$id.about_page_button_open_source);
        this.mOpenSourceTextView = textView3;
        textView3.setOnClickListener(new C0547a(this, 1));
        TextView textView4 = (TextView) viewGroup.findViewById(R$id.about_page_button_social_terms_and_conditions);
        this.mTermsAndConditionsTextView = textView4;
        textView4.setOnClickListener(new C0547a(this, 2));
        this.mContainedButtonMinWidth = viewGroup.getResources().getDimensionPixelSize(R$dimen.about_page_button_min_width);
    }

    public int getLayoutId() {
        return R$layout.fragment_about_page_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING_ABOUT_GALLERY.toString();
    }

    public int getTitleId() {
        return 0;
    }

    public void onConfigurationChanged(Configuration configuration) {
        initLayout();
        super.onConfigurationChanged(configuration);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Optional.ofNullable(getActivity()).ifPresent(new E9.a(4, this));
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        if (eventMessage.what != 7008) {
            return super.onHandleEvent(obj, bundle);
        }
        onDone((String) eventMessage.obj);
        return true;
    }

    public void onResume() {
        super.onResume();
        this.mUpgradeState = 4;
        initLayout();
        updateToolbarTitle("");
    }
}
