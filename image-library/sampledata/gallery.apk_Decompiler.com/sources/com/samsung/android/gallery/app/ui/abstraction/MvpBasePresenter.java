package com.samsung.android.gallery.app.ui.abstraction;

import F6.f;
import a6.C0419b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.InputBlock;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.o3dp.lib3dphotography.i;
import com.sec.android.gallery3d.R;
import e4.c;
import e4.d;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MvpBasePresenter<V extends IMvpBaseView> extends Subscriber implements EventContext, MenuProvider {
    protected static final InputBlock sInputBlock = new InputBlock();
    private Boolean mActivityBusy;
    private boolean mAddedMenuProvider = false;
    protected int mCurrentNaviUpResId = 0;
    private boolean mEnableOptionMenu = false;
    private boolean mIsResumed;
    private String mLocationKey;
    private MenuDataBinding mMenuDataBinding;
    private MenuHandler mMenuHandler;
    private Menu mOptionsMenu;
    /* access modifiers changed from: protected */
    public final V mView;
    private final View.OnClickListener onNavigationPressed = new C0419b(8, this);

    public MvpBasePresenter(Blackboard blackboard, V v) {
        super(blackboard);
        this.mView = v;
    }

    private void announceScreenLabel() {
        ThreadUtil.postOnBgThread(new c(this, 1));
    }

    /* access modifiers changed from: private */
    public void invalidateOptionsMenuInternal(Observable observable, Object obj) {
        this.mView.invalidateOptionsMenu();
    }

    private boolean isSupportedMenuItem(MenuItem menuItem) {
        boolean z;
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding == null || !menuDataBinding.hasItem(menuItem.getItemId())) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "not support item by this presenter " + menuItem.getItemId());
        }
        return z;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$announceScreenLabel$0() {
        Context context;
        if (!isDestroyed() && this.mView.isViewActive() && (context = getContext()) != null) {
            String labelForAccessibility = getLabelForAccessibility(context);
            if (!TextUtils.isEmpty(labelForAccessibility)) {
                SeApiCompat.announceVoiceAssistant(context, labelForAccessibility);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setNavigationUpClickListener$5(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(this.onNavigationPressed);
    }

    private void unregisterChosenComponentReceiver() {
        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) this.mBlackboard.pop("data://user/ChosenComponentReceiver");
        if (broadcastReceiver != null) {
            AndroidCompat.unregisterReceiver(getContext(), broadcastReceiver);
        }
    }

    public void clearMenu() {
        this.mMenuDataBinding = null;
        this.mMenuHandler = null;
    }

    public MenuDataBinding createMenuDataBinding() {
        return null;
    }

    public MenuHandler createMenuHandler() {
        return null;
    }

    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        MenuDataBinding menuDataBinding = this.mMenuDataBinding;
        if (menuDataBinding != null) {
            menuInflater.inflate(menuDataBinding.getId(), menu);
            this.mMenuDataBinding.setMenu(menu);
            setOptionsMenuAction(menu);
            this.mOptionsMenu = menu;
        }
    }

    public final void enableOptionsMenu(boolean z) {
        ThreadUtil.assertUiThread("enableOptionsMenu");
        if (this.mEnableOptionMenu != z) {
            this.mEnableOptionMenu = z;
            this.mView.invalidateOptionsMenu();
        }
    }

    public View getActivityToolBar() {
        return getActivity().findViewById(R.id.activity_toolbar);
    }

    public final Context getApplicationContext() {
        return this.mView.getApplicationContext();
    }

    public View getClipboardView() {
        return getActivity().findViewById(R.id.clipboard);
    }

    public final Context getContext() {
        return this.mView.getContext();
    }

    public final Intent getIntent() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity.getIntent();
        }
        return null;
    }

    public String getLabelForAccessibility(Context context) {
        return null;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public MenuDataBinding getMenuDataBinding() {
        return this.mMenuDataBinding;
    }

    public MenuHandler getMenuHandler() {
        return this.mMenuHandler;
    }

    public int getNaviUpResourceId() {
        return R.drawable.tw_ic_ab_back_mtrl_with_inset;
    }

    public String getScreenId() {
        return this.mView.getScreenId();
    }

    public Toolbar getToolbar() {
        return this.mView.getToolbar();
    }

    public final V getView() {
        return this.mView;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1056) {
            updateKnoxMenuVisibility();
            return true;
        } else if (i2 == 1100) {
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_MORE_OPTIONS);
            return true;
        } else if (i2 != 1147) {
            if (i2 != 3026) {
                return false;
            }
            this.mView.clearScreenLocked();
            return true;
        } else if (!eventMessage.obj.equals(this.mView.hashTag())) {
            return false;
        } else {
            initMenu();
            return true;
        }
    }

    public boolean hasOptionsMenu() {
        if (this.mMenuDataBinding != null) {
            return true;
        }
        return false;
    }

    public void initMenu() {
        Object obj;
        if (isMenuInitRequired()) {
            if (this.mMenuDataBinding == null) {
                setMenuDataBinding(createMenuDataBinding());
            }
            if (this.mMenuHandler == null) {
                this.mMenuHandler = createMenuHandler();
            }
            enableOptionsMenu(hasOptionsMenu());
            if (!this.mAddedMenuProvider) {
                getActivity().addMenuProvider(this);
                this.mAddedMenuProvider = true;
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("initMenu {");
            MenuDataBinding menuDataBinding = this.mMenuDataBinding;
            if (menuDataBinding == null) {
                obj = "null";
            } else {
                obj = Integer.valueOf(menuDataBinding.getId());
            }
            sb2.append(obj);
            sb2.append("}");
            Log.d(stringCompat, sb2.toString());
        }
    }

    public void initMenuOnViewResume() {
        Log.d(this.TAG, "initMenuOnViewResume");
        ThreadUtil.postOnUiThread(new c(this, 0));
    }

    public final boolean isActivityBusy() {
        if (this.mActivityBusy == null) {
            if (this.mBlackboard.isEmpty("lifecycle://on_thumbnail_load_done")) {
                return true;
            }
            this.mActivityBusy = Boolean.FALSE;
        }
        return this.mActivityBusy.booleanValue();
    }

    public boolean isDestroyed() {
        return this.mView.isDestroyed();
    }

    public final boolean isDexMode() {
        return this.mView.isDexMode();
    }

    public boolean isInputBlocked() {
        return sInputBlock.isBlocked();
    }

    public boolean isInputBlockedExceptBackKey() {
        return sInputBlock.isBlockedExceptBackKey();
    }

    public boolean isMenuInitRequired() {
        return !this.mView.isDestroyed();
    }

    public boolean isViewPaused() {
        return !this.mIsResumed;
    }

    public final void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mView.isViewActive() && this.mEnableOptionMenu && !this.mView.isDefaultExitTransitioning()) {
            createOptionsMenu(menu, menuInflater);
        }
    }

    public final boolean onMenuItemSelected(MenuItem menuItem) {
        Log.majorEvent("onMenuItemSelected : " + menuItem.getTitle());
        return this.mView.onOptionsItemSelected(menuItem, false);
    }

    public void onNavigationPressed(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
        Log.majorEvent("onNavigationPressed : " + Logger.getEncodedString(ThreadUtil.getCallStack()));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mMenuHandler == null || !isSupportedMenuItem(menuItem) || !this.mMenuHandler.onOptionsItemSelected(this, menuItem)) {
            return false;
        }
        return true;
    }

    public final void onPrepareMenu(Menu menu) {
        if (this.mView.isViewActive() && this.mEnableOptionMenu && !this.mView.isDefaultExitTransitioning()) {
            prepareOptionsMenu(menu);
            prepareBottomMenu(menu);
        }
    }

    public void onViewCreate() {
        onCreate();
    }

    public void onViewCreated(View view) {
        this.mView.initView(view);
    }

    public void onViewDestroy() {
        onDestroy();
        Menu menu = this.mOptionsMenu;
        if (menu != null) {
            clearOptionsMenuAction(menu);
            this.mOptionsMenu = null;
        }
        unregisterChosenComponentReceiver();
        if (this.mAddedMenuProvider) {
            getActivity().removeMenuProvider(this);
            this.mAddedMenuProvider = false;
        }
    }

    public void onViewPause() {
        this.mIsResumed = false;
    }

    public void onViewResume() {
        this.mIsResumed = true;
        if (this.mView.supportToolbar()) {
            initMenuOnViewResume();
        }
        announceScreenLabel();
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        this.mView.postAnalyticsLog(analyticsEventId);
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = this.mMenuDataBinding;
        if (menuDataBinding != null) {
            menuDataBinding.prepareOptionsMenu(menu);
        }
    }

    public void releaseInputBlocking(int i2) {
        sInputBlock.release(i2);
    }

    public void releaseToolbar(GalleryToolbar galleryToolbar) {
        if (!this.mView.useParentToolbar()) {
            galleryToolbar.removeOnClickListener(this.onNavigationPressed);
        }
    }

    public void resetCurrentNaviUpResId() {
        this.mCurrentNaviUpResId = 0;
    }

    public boolean setInputBlock(String str, int i2) {
        return sInputBlock.set(str, (long) i2);
    }

    public final void setLocationKey(String str) {
        setLocationKeyOnly(str);
        onLocationKeyAssigned();
    }

    public final void setLocationKeyOnly(String str) {
        if (str != null) {
            this.mLocationKey = str;
        }
    }

    public final void setMenuDataBinding(MenuDataBinding menuDataBinding) {
        this.mMenuDataBinding = menuDataBinding;
        setOptionsMenuCallback(new d(this));
    }

    public final void setMenuHandler(MenuHandler menuHandler) {
        this.mMenuHandler = menuHandler;
    }

    public void setNavigationUpButton(Toolbar toolbar) {
        if (this.mCurrentNaviUpResId != getNaviUpResourceId()) {
            int naviUpResourceId = getNaviUpResourceId();
            this.mCurrentNaviUpResId = naviUpResourceId;
            toolbar.setNavigationIcon(naviUpResourceId);
            toolbar.setNavigationContentDescription((int) R.string.navigate_up);
        }
    }

    public void setNavigationUpClickListener(Toolbar toolbar) {
        if ((!this.mView.isDrawerMode() || !this.mView.isDisplayWithDrawer()) && !this.mView.useParentToolbar() && toolbar.getNavigationIcon() != null) {
            toolbar.post(new i(4, this, toolbar));
        }
    }

    public final void setOptionsMenuCallback(Observer observer) {
        MenuDataBinding menuDataBinding = this.mMenuDataBinding;
        if (menuDataBinding != null) {
            menuDataBinding.setPropertyChangedCallback(observer);
        }
    }

    public String toString() {
        return hashTag();
    }

    public void updateKnoxMenuVisibility() {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            menuDataBinding.setVisible((int) R.id.action_move_to_knox, (BooleanSupplier) new f(11));
            menuDataBinding.setVisible((int) R.id.action_remove_from_knox, (BooleanSupplier) new f(12));
            menuDataBinding.setVisible((int) R.id.action_move_to_secure_folder, (BooleanSupplier) new f(13));
            menuDataBinding.setVisible((int) R.id.action_remove_from_secure_folder, (BooleanSupplier) new f(14));
        }
    }

    public void updateMenuVisibility(int i2, boolean z, boolean z3) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null && menuDataBinding.setVisible(i2, z) && z3) {
            this.mView.invalidateOptionsMenu();
        }
    }

    public final FragmentActivity getActivity() {
        return this.mView.getActivity();
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str) {
        this.mView.postAnalyticsLog(analyticsEventId, str);
    }

    public boolean setInputBlock(String str) {
        return sInputBlock.set(str, 200);
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str, String str2) {
        this.mView.postAnalyticsLog(analyticsEventId, str, str2);
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, long j2) {
        this.mView.postAnalyticsLog(analyticsEventId, j2);
    }

    public final void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId) {
        this.mView.postAnalyticsLog(str, analyticsEventId);
    }

    public final void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, long j2) {
        this.mView.postAnalyticsLog(str, analyticsEventId, j2);
    }

    public void onLocationKeyAssigned() {
    }

    public void onViewAttach() {
    }

    public void onViewStart() {
    }

    public void onViewStop() {
    }

    public void clearOptionsMenuAction(Menu menu) {
    }

    public void onHiddenChanged(boolean z) {
    }

    public void prepareBottomMenu(Menu menu) {
    }

    public void setOptionsMenuAction(Menu menu) {
    }

    public void updateToolbar(Toolbar toolbar) {
    }
}
