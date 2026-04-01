package androidx.appcompat.app;

import N2.j;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.reflect.view.inputmethod.SeslInputMethodManagerReflector;
import bd.i;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    private static final boolean IS_PRE_LOLLIPOP = false;
    private static final boolean sCanReturnDifferentContext = (!"robolectric".equals(Build.FINGERPRINT));
    private static final SimpleArrayMap<String, Integer> sLocalNightModes = new SimpleArrayMap<>();
    private static final int[] sWindowBackgroundStyleable = {16842836};
    ActionBar mActionBar;
    private View mActionBarTargetView;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private int mActivityHandlesConfigFlags;
    private boolean mActivityHandlesConfigFlagsChecked;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    private AppCompatWindowCallback mAppCompatWindowCallback;
    private AutoNightModeManager mAutoBatteryNightModeManager;
    private AutoNightModeManager mAutoTimeNightModeManager;
    private OnBackInvokedCallback mBackCallback;
    private boolean mBaseContextAttached;
    private boolean mClosingActionMenu;
    final Context mContext;
    private boolean mCreated;
    private DecorContentParent mDecorContentParent;
    boolean mDestroyed;
    private OnBackInvokedDispatcher mDispatcher;
    private Configuration mEffectiveConfiguration;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes;
    boolean mHasActionBar;
    final Object mHost;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    boolean mIsFloating;
    private boolean mIsIgnoreRemoveSystemTopInset;
    private LayoutIncludeDetector mLayoutIncludeDetector;
    private int mLocalNightMode;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private int mThemeResId;
    private CharSequence mTitle;
    private TextView mTitleView;
    Window mWindow;
    boolean mWindowNoTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ActionBarMenuCallback {
        View onCreatePanelView(int i2);

        boolean onPreparePanel(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        public ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.checkCloseActionMenu(menuBuilder);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback = AppCompatDelegateImpl.this.getWindowCallback();
            if (windowCallback == null) {
                return true;
            }
            windowCallback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public void onDestroyActionMode(final ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.mActionModePopup != null) {
                appCompatDelegateImpl.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.mActionModeView != null) {
                appCompatDelegateImpl2.endOnGoingFadeAnimation();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.mFadeAnim = ViewCompat.animate(appCompatDelegateImpl3.mActionModeView).alpha(0.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View view) {
                        ActionBarContextView actionBarContextView = AppCompatDelegateImpl.this.mActionModeView;
                        if (actionBarContextView != null) {
                            actionBarContextView.setVisibility(8);
                            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                            PopupWindow popupWindow = appCompatDelegateImpl.mActionModePopup;
                            if (popupWindow != null) {
                                popupWindow.dismiss();
                            } else if (appCompatDelegateImpl.mActionModeView.getParent() instanceof View) {
                                ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                            }
                            AppCompatDelegateImpl.this.mActionModeView.killMode();
                            AppCompatDelegateImpl.this.mFadeAnim.setListener((ViewPropertyAnimatorListener) null);
                            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                            appCompatDelegateImpl2.mFadeAnim = null;
                            ViewCompat.requestApplyInsets(appCompatDelegateImpl2.mSubDecor);
                        }
                        if (actionMode.seslIsSetSetNullActionModeViewOnDestroy()) {
                            AppCompatDelegateImpl.this.mActionModeView = null;
                            Log.i("AppCompatDelegate", "Reset ActionModeView by request");
                        }
                    }
                });
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            AppCompatCallback appCompatCallback = appCompatDelegateImpl4.mAppCompatCallback;
            if (appCompatCallback != null) {
                appCompatCallback.onSupportActionModeFinished(appCompatDelegateImpl4.mActionMode);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.mActionMode = null;
            ViewCompat.requestApplyInsets(appCompatDelegateImpl5.mSubDecor);
            AppCompatDelegateImpl.this.updateBackInvokedCallbackState();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            ViewCompat.requestApplyInsets(AppCompatDelegateImpl.this.mSubDecor);
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static boolean isPowerSaveMode(PowerManager powerManager) {
            return powerManager.isPowerSaveMode();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static void generateConfigDelta_locale(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (!locales.equals(locales2)) {
                configuration3.setLocales(locales2);
                configuration3.locale = configuration2.locale;
            }
        }

        public static LocaleListCompat getLocales(Configuration configuration) {
            return LocaleListCompat.forLanguageTags(configuration.getLocales().toLanguageTags());
        }

        public static void setDefaultLocales(LocaleListCompat localeListCompat) {
            LocaleList.setDefault(LocaleList.forLanguageTags(localeListCompat.toLanguageTags()));
        }

        public static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
            configuration.setLocales(LocaleList.forLanguageTags(localeListCompat.toLanguageTags()));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static void generateConfigDelta_colorMode(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i2 = configuration.colorMode & 3;
            int i7 = configuration2.colorMode;
            if (i2 != (i7 & 3)) {
                configuration3.colorMode |= i7 & 3;
            }
            int i8 = configuration.colorMode & 12;
            int i10 = configuration2.colorMode;
            if (i8 != (i10 & 12)) {
                configuration3.colorMode |= i10 & 12;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api33Impl {
        public static OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }

        public static OnBackInvokedCallback registerOnBackPressedCallback(Object obj, AppCompatDelegateImpl appCompatDelegateImpl) {
            Objects.requireNonNull(appCompatDelegateImpl);
            d dVar = new d(appCompatDelegateImpl);
            i.h(obj).registerOnBackInvokedCallback(1000000, dVar);
            return dVar;
        }

        public static void unregisterOnBackInvokedCallback(Object obj, Object obj2) {
            i.h(obj).unregisterOnBackInvokedCallback(i.f(obj2));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AppCompatWindowCallback extends WindowCallbackWrapper {
        private ActionBarMenuCallback mActionBarCallback;
        private boolean mDispatchKeyEventBypassEnabled;
        private boolean mOnContentChangedBypassEnabled;
        private boolean mOnPanelClosedBypassEnabled;

        public AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        /* JADX INFO: finally extract failed */
        public boolean bypassDispatchKeyEvent(Window.Callback callback, KeyEvent keyEvent) {
            try {
                this.mDispatchKeyEventBypassEnabled = true;
                boolean dispatchKeyEvent = callback.dispatchKeyEvent(keyEvent);
                this.mDispatchKeyEventBypassEnabled = false;
                return dispatchKeyEvent;
            } catch (Throwable th) {
                this.mDispatchKeyEventBypassEnabled = false;
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        public void bypassOnContentChanged(Window.Callback callback) {
            try {
                this.mOnContentChangedBypassEnabled = true;
                callback.onContentChanged();
                this.mOnContentChangedBypassEnabled = false;
            } catch (Throwable th) {
                this.mOnContentChangedBypassEnabled = false;
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        public void bypassOnPanelClosed(Window.Callback callback, int i2, Menu menu) {
            try {
                this.mOnPanelClosedBypassEnabled = true;
                callback.onPanelClosed(i2, menu);
                this.mOnPanelClosedBypassEnabled = false;
            } catch (Throwable th) {
                this.mOnPanelClosedBypassEnabled = false;
                throw th;
            }
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (this.mDispatchKeyEventBypassEnabled) {
                return getWrapped().dispatchKeyEvent(keyEvent);
            }
            if (AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
                return true;
            }
            return false;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.onKeyShortcut(keyEvent.getKeyCode(), keyEvent)) {
                return true;
            }
            return false;
        }

        public void onContentChanged() {
            if (this.mOnContentChangedBypassEnabled) {
                getWrapped().onContentChanged();
            }
        }

        public boolean onCreatePanelMenu(int i2, Menu menu) {
            if (i2 != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i2, menu);
            }
            return false;
        }

        public View onCreatePanelView(int i2) {
            View onCreatePanelView;
            ActionBarMenuCallback actionBarMenuCallback = this.mActionBarCallback;
            if (actionBarMenuCallback == null || (onCreatePanelView = actionBarMenuCallback.onCreatePanelView(i2)) == null) {
                return super.onCreatePanelView(i2);
            }
            return onCreatePanelView;
        }

        public boolean onMenuOpened(int i2, Menu menu) {
            super.onMenuOpened(i2, menu);
            AppCompatDelegateImpl.this.onMenuOpened(i2);
            return true;
        }

        public void onPanelClosed(int i2, Menu menu) {
            if (this.mOnPanelClosedBypassEnabled) {
                getWrapped().onPanelClosed(i2, menu);
                return;
            }
            super.onPanelClosed(i2, menu);
            AppCompatDelegateImpl.this.onPanelClosed(i2);
        }

        public boolean onPreparePanel(int i2, View view, Menu menu) {
            MenuBuilder menuBuilder;
            if (menu instanceof MenuBuilder) {
                menuBuilder = (MenuBuilder) menu;
            } else {
                menuBuilder = null;
            }
            if (i2 == 0 && menuBuilder == null) {
                return false;
            }
            boolean z = true;
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            ActionBarMenuCallback actionBarMenuCallback = this.mActionBarCallback;
            if (actionBarMenuCallback == null || !actionBarMenuCallback.onPreparePanel(i2)) {
                z = false;
            }
            if (!z) {
                z = super.onPreparePanel(i2, view, menu);
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
            }
            return z;
        }

        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            MenuBuilder menuBuilder;
            PanelFeatureState panelState = AppCompatDelegateImpl.this.getPanelState(0, true);
            if (panelState == null || (menuBuilder = panelState.menu) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i2);
            } else {
                super.onProvideKeyboardShortcuts(list, menuBuilder, i2);
            }
        }

        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        public void setActionBarCallback(ActionBarMenuCallback actionBarMenuCallback) {
            this.mActionBarCallback = actionBarMenuCallback;
        }

        public final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback callback) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.mContext, callback);
            androidx.appcompat.view.ActionMode startSupportActionMode = AppCompatDelegateImpl.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }

        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            if (!AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() || i2 != 0) {
                return super.onWindowStartingActionMode(callback, i2);
            }
            return startAsSupportActionMode(callback);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AutoBatteryNightModeManager extends AutoNightModeManager {
        private final PowerManager mPowerManager;

        public AutoBatteryNightModeManager(Context context) {
            super();
            this.mPowerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        public IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        public int getApplyableNightMode() {
            if (Api21Impl.isPowerSaveMode(this.mPowerManager)) {
                return 2;
            }
            return 1;
        }

        public void onChange() {
            AppCompatDelegateImpl.this.applyDayNight();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public abstract class AutoNightModeManager {
        private BroadcastReceiver mReceiver;

        public AutoNightModeManager() {
        }

        public void cleanup() {
            BroadcastReceiver broadcastReceiver = this.mReceiver;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.mContext.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.mReceiver = null;
            }
        }

        public abstract IntentFilter createIntentFilterForBroadcastReceiver();

        public abstract int getApplyableNightMode();

        public abstract void onChange();

        public void setup() {
            cleanup();
            IntentFilter createIntentFilterForBroadcastReceiver = createIntentFilterForBroadcastReceiver();
            if (createIntentFilterForBroadcastReceiver != null && createIntentFilterForBroadcastReceiver.countActions() != 0) {
                if (this.mReceiver == null) {
                    this.mReceiver = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            AutoNightModeManager.this.onChange();
                        }
                    };
                }
                AppCompatDelegateImpl.this.mContext.registerReceiver(this.mReceiver, createIntentFilterForBroadcastReceiver);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AutoTimeNightModeManager extends AutoNightModeManager {
        private final TwilightManager mTwilightManager;

        public AutoTimeNightModeManager(TwilightManager twilightManager) {
            super();
            this.mTwilightManager = twilightManager;
        }

        public IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        public int getApplyableNightMode() {
            if (this.mTwilightManager.isNight()) {
                return 2;
            }
            return 1;
        }

        public void onChange() {
            AppCompatDelegateImpl.this.applyDayNight();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        private boolean isOutOfBounds(int i2, int i7) {
            if (i2 < -5 || i7 < -5 || i2 > getWidth() + 5 || i7 > getHeight() + 5) {
                return true;
            }
            return false;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
                return true;
            }
            return false;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !isOutOfBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.closePanel(0);
            return true;
        }

        public void setBackgroundResource(int i2) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i2));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        int windowAnimations;

        /* renamed from: x  reason: collision with root package name */
        int f982x;
        int y;

        public PanelFeatureState(int i2) {
            this.featureId = i2;
        }

        public MenuView getListMenuView(MenuPresenter.Callback callback) {
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                ListMenuPresenter listMenuPresenter2 = new ListMenuPresenter(this.listPresenterContext, R$layout.sesl_list_menu_item_layout);
                this.listMenuPresenter = listMenuPresenter2;
                listMenuPresenter2.setCallback(callback);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        public boolean hasPanelItems() {
            if (this.shownPanelView == null) {
                return false;
            }
            if (this.createdPanelView == null && this.listMenuPresenter.getAdapter().getCount() <= 0) {
                return false;
            }
            return true;
        }

        public void setMenu(MenuBuilder menuBuilder) {
            ListMenuPresenter listMenuPresenter2;
            MenuBuilder menuBuilder2 = this.menu;
            if (menuBuilder != menuBuilder2) {
                if (menuBuilder2 != null) {
                    menuBuilder2.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder;
                if (menuBuilder != null && (listMenuPresenter2 = this.listMenuPresenter) != null) {
                    menuBuilder.addMenuPresenter(listMenuPresenter2);
                }
            }
        }

        public void setStyle(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            }
            newTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i7 = typedValue.resourceId;
            if (i7 != 0) {
                newTheme.applyStyle(i7, true);
            } else {
                newTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.listPresenterContext = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.background = obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        public PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            boolean z3;
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            if (rootMenu != menuBuilder) {
                z3 = true;
            } else {
                z3 = false;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z3) {
                menuBuilder = rootMenu;
            }
            PanelFeatureState findMenuPanel = appCompatDelegateImpl.findMenuPanel(menuBuilder);
            if (findMenuPanel == null) {
                return;
            }
            if (z3) {
                AppCompatDelegateImpl.this.callOnPanelClosed(findMenuPanel.featureId, findMenuPanel, rootMenu);
                AppCompatDelegateImpl.this.closePanel(findMenuPanel, true);
                return;
            }
            AppCompatDelegateImpl.this.closePanel(findMenuPanel, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback;
            if (menuBuilder != menuBuilder.getRootMenu()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.mHasActionBar || (windowCallback = appCompatDelegateImpl.getWindowCallback()) == null || AppCompatDelegateImpl.this.mDestroyed) {
                return true;
            }
            windowCallback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    public AppCompatDelegateImpl(Activity activity, AppCompatCallback appCompatCallback) {
        this(activity, (Window) null, appCompatCallback, activity);
    }

    private boolean applyApplicationSpecificConfig(boolean z) {
        return applyApplicationSpecificConfig(z, true);
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
        View decorView = this.mWindow.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = R$styleable.AppCompatTheme_windowFixedWidthMajor;
        if (obtainStyledAttributes.hasValue(i2)) {
            obtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i7 = R$styleable.AppCompatTheme_windowFixedWidthMinor;
        if (obtainStyledAttributes.hasValue(i7)) {
            obtainStyledAttributes.getValue(i7, contentFrameLayout.getFixedWidthMinor());
        }
        int i8 = R$styleable.AppCompatTheme_windowFixedHeightMajor;
        if (obtainStyledAttributes.hasValue(i8)) {
            obtainStyledAttributes.getValue(i8, contentFrameLayout.getFixedHeightMajor());
        }
        int i10 = R$styleable.AppCompatTheme_windowFixedHeightMinor;
        if (obtainStyledAttributes.hasValue(i10)) {
            obtainStyledAttributes.getValue(i10, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void attachToWindow(Window window) {
        if (this.mWindow == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof AppCompatWindowCallback)) {
                AppCompatWindowCallback appCompatWindowCallback = new AppCompatWindowCallback(callback);
                this.mAppCompatWindowCallback = appCompatWindowCallback;
                window.setCallback(appCompatWindowCallback);
                TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mContext, (AttributeSet) null, sWindowBackgroundStyleable);
                Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
                if (drawableIfKnown != null) {
                    window.setBackgroundDrawable(drawableIfKnown);
                }
                obtainStyledAttributes.recycle();
                this.mWindow = window;
                if (Build.VERSION.SDK_INT >= 33 && this.mDispatcher == null) {
                    setOnBackInvokedDispatcher((OnBackInvokedDispatcher) null);
                    return;
                }
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    private int calculateNightMode() {
        int i2 = this.mLocalNightMode;
        if (i2 != -100) {
            return i2;
        }
        return AppCompatDelegate.getDefaultNightMode();
    }

    private void cleanupAutoManagers() {
        AutoNightModeManager autoNightModeManager = this.mAutoTimeNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.cleanup();
        }
        AutoNightModeManager autoNightModeManager2 = this.mAutoBatteryNightModeManager;
        if (autoNightModeManager2 != null) {
            autoNightModeManager2.cleanup();
        }
    }

    private void closeAllPanels() {
        int i2;
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr != null) {
            i2 = panelFeatureStateArr.length;
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i7];
            if (panelFeatureState != null) {
                closePanel(panelFeatureState, true);
            }
        }
    }

    private Configuration createOverrideAppConfiguration(Context context, int i2, LocaleListCompat localeListCompat, Configuration configuration, boolean z) {
        int i7;
        if (i2 == 1) {
            i7 = 16;
        } else if (i2 == 2) {
            i7 = 32;
        } else if (z) {
            i7 = 0;
        } else {
            i7 = context.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i7 | (configuration2.uiMode & -49);
        if (localeListCompat != null) {
            setConfigurationLocales(configuration2, localeListCompat);
        }
        return configuration2;
    }

    private ViewGroup createSubDecor() {
        ViewGroup viewGroup;
        Context context;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        int i2 = R$styleable.AppCompatTheme_windowActionBar;
        if (obtainStyledAttributes.hasValue(i2)) {
            if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
                requestWindowFeature(1);
            } else if (obtainStyledAttributes.getBoolean(i2, false)) {
                requestWindowFeature(108);
            }
            if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                requestWindowFeature(109);
            }
            if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                requestWindowFeature(10);
            }
            this.mIsFloating = obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
            int i7 = R$styleable.AppCompatTheme_ignoreRemoveSystemTopInset;
            if (obtainStyledAttributes.hasValue(i7)) {
                this.mIsIgnoreRemoveSystemTopInset = obtainStyledAttributes.getBoolean(i7, false);
            }
            obtainStyledAttributes.recycle();
            ensureWindow();
            this.mWindow.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.mContext);
            if (this.mWindowNoTitle) {
                viewGroup = this.mOverlayActionMode ? (ViewGroup) from.inflate(R$layout.sesl_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(R$layout.sesl_screen_simple, (ViewGroup) null);
            } else if (this.mIsFloating) {
                viewGroup = (ViewGroup) from.inflate(R$layout.sesl_dialog_title, (ViewGroup) null);
                this.mOverlayActionBar = false;
                this.mHasActionBar = false;
            } else if (this.mHasActionBar) {
                TypedValue typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    context = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
                } else {
                    context = this.mContext;
                }
                viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.sesl_screen_toolbar, (ViewGroup) null);
                DecorContentParent decorContentParent = (DecorContentParent) viewGroup.findViewById(R$id.decor_content_parent);
                this.mDecorContentParent = decorContentParent;
                decorContentParent.setWindowCallback(getWindowCallback());
                if (this.mOverlayActionBar) {
                    this.mDecorContentParent.initFeature(109);
                }
                if (this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if (this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                ViewCompat.setOnApplyWindowInsetsListener(viewGroup, new OnApplyWindowInsetsListener() {
                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
                        int updateStatusGuard = AppCompatDelegateImpl.this.updateStatusGuard(windowInsetsCompat, (Rect) null);
                        if (systemWindowInsetTop != updateStatusGuard) {
                            windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), updateStatusGuard, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                        }
                        return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                    }
                });
                if (this.mDecorContentParent == null) {
                    this.mTitleView = (TextView) viewGroup.findViewById(R$id.title);
                }
                ViewUtils.makeOptionalFitsSystemWindows(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.mWindow.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground((Drawable) null);
                    }
                }
                this.mWindow.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() {
                    public void onDetachedFromWindow() {
                        AppCompatDelegateImpl.this.dismissPopups();
                    }

                    public void onAttachedFromWindow() {
                    }
                });
                return viewGroup;
            }
            StringBuilder sb2 = new StringBuilder("AppCompat does not support the current theme features: { windowActionBar: ");
            sb2.append(this.mHasActionBar);
            sb2.append(", windowActionBarOverlay: ");
            sb2.append(this.mOverlayActionBar);
            sb2.append(", android:windowIsFloating: ");
            sb2.append(this.mIsFloating);
            sb2.append(", windowActionModeOverlay: ");
            sb2.append(this.mOverlayActionMode);
            sb2.append(", windowNoTitle: ");
            throw new IllegalArgumentException(j.h(sb2, this.mWindowNoTitle, " }"));
        }
        obtainStyledAttributes.recycle();
        Log.e("AppCompatDelegate", "createSubDecor: mContext = " + this.mContext);
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = createSubDecor();
            CharSequence title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                DecorContentParent decorContentParent = this.mDecorContentParent;
                if (decorContentParent != null) {
                    decorContentParent.setWindowTitle(title);
                } else if (peekSupportActionBar() != null) {
                    peekSupportActionBar().setWindowTitle(title);
                } else {
                    TextView textView = this.mTitleView;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
            applyFixedSizeWindow();
            onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState panelState = getPanelState(0, false);
            if (this.mDestroyed) {
                return;
            }
            if (panelState == null || panelState.menu == null) {
                invalidatePanelMenu(108);
            }
        }
    }

    private void ensureWindow() {
        if (this.mWindow == null) {
            Object obj = this.mHost;
            if (obj instanceof Activity) {
                attachToWindow(((Activity) obj).getWindow());
            }
        }
        if (this.mWindow == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private static Configuration generateConfigDelta(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (!(configuration2 == null || configuration.diff(configuration2) == 0)) {
            float f = configuration.fontScale;
            float f5 = configuration2.fontScale;
            if (f != f5) {
                configuration3.fontScale = f5;
            }
            int i2 = configuration.mcc;
            int i7 = configuration2.mcc;
            if (i2 != i7) {
                configuration3.mcc = i7;
            }
            int i8 = configuration.mnc;
            int i10 = configuration2.mnc;
            if (i8 != i10) {
                configuration3.mnc = i10;
            }
            Api24Impl.generateConfigDelta_locale(configuration, configuration2, configuration3);
            int i11 = configuration.touchscreen;
            int i12 = configuration2.touchscreen;
            if (i11 != i12) {
                configuration3.touchscreen = i12;
            }
            int i13 = configuration.keyboard;
            int i14 = configuration2.keyboard;
            if (i13 != i14) {
                configuration3.keyboard = i14;
            }
            int i15 = configuration.keyboardHidden;
            int i16 = configuration2.keyboardHidden;
            if (i15 != i16) {
                configuration3.keyboardHidden = i16;
            }
            int i17 = configuration.navigation;
            int i18 = configuration2.navigation;
            if (i17 != i18) {
                configuration3.navigation = i18;
            }
            int i19 = configuration.navigationHidden;
            int i20 = configuration2.navigationHidden;
            if (i19 != i20) {
                configuration3.navigationHidden = i20;
            }
            int i21 = configuration.orientation;
            int i22 = configuration2.orientation;
            if (i21 != i22) {
                configuration3.orientation = i22;
            }
            int i23 = configuration.screenLayout & 15;
            int i24 = configuration2.screenLayout;
            if (i23 != (i24 & 15)) {
                configuration3.screenLayout |= i24 & 15;
            }
            int i25 = configuration.screenLayout & 192;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 192)) {
                configuration3.screenLayout |= i26 & 192;
            }
            int i27 = configuration.screenLayout & 48;
            int i28 = configuration2.screenLayout;
            if (i27 != (i28 & 48)) {
                configuration3.screenLayout |= i28 & 48;
            }
            int i29 = configuration.screenLayout & 768;
            int i30 = configuration2.screenLayout;
            if (i29 != (i30 & 768)) {
                configuration3.screenLayout |= i30 & 768;
            }
            Api26Impl.generateConfigDelta_colorMode(configuration, configuration2, configuration3);
            int i31 = configuration.uiMode & 15;
            int i32 = configuration2.uiMode;
            if (i31 != (i32 & 15)) {
                configuration3.uiMode |= i32 & 15;
            }
            int i33 = configuration.uiMode & 48;
            int i34 = configuration2.uiMode;
            if (i33 != (i34 & 48)) {
                configuration3.uiMode |= i34 & 48;
            }
            int i35 = configuration.screenWidthDp;
            int i36 = configuration2.screenWidthDp;
            if (i35 != i36) {
                configuration3.screenWidthDp = i36;
            }
            int i37 = configuration.screenHeightDp;
            int i38 = configuration2.screenHeightDp;
            if (i37 != i38) {
                configuration3.screenHeightDp = i38;
            }
            int i39 = configuration.smallestScreenWidthDp;
            int i40 = configuration2.smallestScreenWidthDp;
            if (i39 != i40) {
                configuration3.smallestScreenWidthDp = i40;
            }
            int i41 = configuration.densityDpi;
            int i42 = configuration2.densityDpi;
            if (i41 != i42) {
                configuration3.densityDpi = i42;
            }
        }
        return configuration3;
    }

    private int getActivityHandlesConfigChangesFlags(Context context) {
        if (!this.mActivityHandlesConfigFlagsChecked && (this.mHost instanceof Activity)) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return 0;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(context, this.mHost.getClass()), 269221888);
                if (activityInfo != null) {
                    this.mActivityHandlesConfigFlags = activityInfo.configChanges;
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
                this.mActivityHandlesConfigFlags = 0;
            }
        }
        this.mActivityHandlesConfigFlagsChecked = true;
        return this.mActivityHandlesConfigFlags;
    }

    private AutoNightModeManager getAutoBatteryNightModeManager(Context context) {
        if (this.mAutoBatteryNightModeManager == null) {
            this.mAutoBatteryNightModeManager = new AutoBatteryNightModeManager(context);
        }
        return this.mAutoBatteryNightModeManager;
    }

    private AutoNightModeManager getAutoTimeNightModeManager(Context context) {
        if (this.mAutoTimeNightModeManager == null) {
            this.mAutoTimeNightModeManager = new AutoTimeNightModeManager(TwilightManager.getInstance(context));
        }
        return this.mAutoTimeNightModeManager;
    }

    private void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            Object obj = this.mHost;
            if (obj instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar((Activity) this.mHost, this.mOverlayActionBar);
            } else if (obj instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar((Dialog) this.mHost);
            }
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }

    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.createdPanelView;
        if (view != null) {
            panelFeatureState.shownPanelView = view;
            return true;
        } else if (panelFeatureState.menu == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            View view2 = (View) panelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
            panelFeatureState.shownPanelView = view2;
            if (view2 != null) {
                return true;
            }
            return false;
        }
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        panelFeatureState.setStyle(getActionBarThemedContext());
        panelFeatureState.decorView = new ListMenuDecorView(panelFeatureState.listPresenterContext);
        panelFeatureState.gravity = 81;
        return true;
    }

    private boolean initializePanelMenu(PanelFeatureState panelFeatureState) {
        Resources.Theme theme;
        Context context = this.mContext;
        int i2 = panelFeatureState.featureId;
        if ((i2 == 0 || i2 == 108) && this.mDecorContentParent != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme2 = context.getTheme();
            theme2.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme = context.getResources().newTheme();
                theme.setTo(theme2);
                theme.applyStyle(typedValue.resourceId, true);
                theme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme2.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
                theme = null;
            }
            if (typedValue.resourceId != 0) {
                if (theme == null) {
                    theme = context.getResources().newTheme();
                    theme.setTo(theme2);
                }
                theme.applyStyle(typedValue.resourceId, true);
            }
            if (theme != null) {
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme);
                context = contextThemeWrapper;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.setCallback(this);
        panelFeatureState.setMenu(menuBuilder);
        return true;
    }

    private void invalidatePanelMenu(int i2) {
        this.mInvalidatePanelMenuFeatures = (1 << i2) | this.mInvalidatePanelMenuFeatures;
        if (!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    private boolean isFloatingToolbarActionModeView() {
        View view;
        ActionBarContextView actionBarContextView = this.mActionModeView;
        if (actionBarContextView == null || (view = (View) actionBarContextView.getParent()) == null || view.getId() != view.getContext().getResources().getIdentifier("sesl_floating_toolbar_layout", "id", view.getContext().getPackageName())) {
            return false;
        }
        return true;
    }

    private boolean onKeyDownPanel(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelState = getPanelState(i2, true);
        if (!panelState.isOpen) {
            return preparePanel(panelState, keyEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean onKeyUpPanel(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            androidx.appcompat.view.ActionMode r0 = r4.mActionMode
            r1 = 0
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.getPanelState(r5, r0)
            if (r5 != 0) goto L_0x0043
            androidx.appcompat.widget.DecorContentParent r5 = r4.mDecorContentParent
            if (r5 == 0) goto L_0x0043
            boolean r5 = r5.canShowOverflowMenu()
            if (r5 == 0) goto L_0x0043
            android.content.Context r5 = r4.mContext
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L_0x0043
            androidx.appcompat.widget.DecorContentParent r5 = r4.mDecorContentParent
            boolean r5 = r5.isOverflowMenuShowing()
            if (r5 != 0) goto L_0x003c
            boolean r5 = r4.mDestroyed
            if (r5 != 0) goto L_0x0062
            boolean r5 = r4.preparePanel(r2, r6)
            if (r5 == 0) goto L_0x0062
            androidx.appcompat.widget.DecorContentParent r5 = r4.mDecorContentParent
            boolean r0 = r5.showOverflowMenu()
            goto L_0x0068
        L_0x003c:
            androidx.appcompat.widget.DecorContentParent r5 = r4.mDecorContentParent
            boolean r0 = r5.hideOverflowMenu()
            goto L_0x0068
        L_0x0043:
            boolean r5 = r2.isOpen
            if (r5 != 0) goto L_0x0064
            boolean r3 = r2.isHandled
            if (r3 == 0) goto L_0x004c
            goto L_0x0064
        L_0x004c:
            boolean r5 = r2.isPrepared
            if (r5 == 0) goto L_0x0062
            boolean r5 = r2.refreshMenuContent
            if (r5 == 0) goto L_0x005b
            r2.isPrepared = r1
            boolean r5 = r4.preparePanel(r2, r6)
            goto L_0x005c
        L_0x005b:
            r5 = r0
        L_0x005c:
            if (r5 == 0) goto L_0x0062
            r4.openPanel(r2, r6)
            goto L_0x0068
        L_0x0062:
            r0 = r1
            goto L_0x0068
        L_0x0064:
            r4.closePanel(r2, r0)
            r0 = r5
        L_0x0068:
            if (r0 == 0) goto L_0x0085
            android.content.Context r4 = r4.mContext
            android.content.Context r4 = r4.getApplicationContext()
            java.lang.String r5 = "audio"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.media.AudioManager r4 = (android.media.AudioManager) r4
            if (r4 == 0) goto L_0x007e
            r4.playSoundEffect(r1)
            return r0
        L_0x007e:
            java.lang.String r4 = "AppCompatDelegate"
            java.lang.String r5 = "Couldn't get audio manager"
            android.util.Log.w(r4, r5)
        L_0x0085:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onKeyUpPanel(int, android.view.KeyEvent):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openPanel(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r12, android.view.KeyEvent r13) {
        /*
            r11 = this;
            boolean r0 = r12.isOpen
            if (r0 != 0) goto L_0x00f6
            boolean r0 = r11.mDestroyed
            if (r0 == 0) goto L_0x000a
            goto L_0x00f6
        L_0x000a:
            int r0 = r12.featureId
            if (r0 != 0) goto L_0x0020
            android.content.Context r0 = r11.mContext
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.screenLayout
            r0 = r0 & 15
            r1 = 4
            if (r0 != r1) goto L_0x0020
            return
        L_0x0020:
            android.view.Window$Callback r0 = r11.getWindowCallback()
            r1 = 1
            if (r0 == 0) goto L_0x0035
            int r2 = r12.featureId
            androidx.appcompat.view.menu.MenuBuilder r3 = r12.menu
            boolean r0 = r0.onMenuOpened(r2, r3)
            if (r0 != 0) goto L_0x0035
            r11.closePanel(r12, r1)
            return
        L_0x0035:
            android.content.Context r0 = r11.mContext
            java.lang.String r2 = "window"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            if (r0 != 0) goto L_0x0044
            goto L_0x00f6
        L_0x0044:
            boolean r13 = r11.preparePanel(r12, r13)
            if (r13 != 0) goto L_0x004c
            goto L_0x00f6
        L_0x004c:
            android.view.ViewGroup r13 = r12.decorView
            r2 = -2
            if (r13 == 0) goto L_0x0067
            boolean r3 = r12.refreshDecorView
            if (r3 == 0) goto L_0x0056
            goto L_0x0067
        L_0x0056:
            android.view.View r13 = r12.createdPanelView
            if (r13 == 0) goto L_0x00ca
            android.view.ViewGroup$LayoutParams r13 = r13.getLayoutParams()
            if (r13 == 0) goto L_0x00ca
            int r13 = r13.width
            r3 = -1
            if (r13 != r3) goto L_0x00ca
            r4 = r3
            goto L_0x00cb
        L_0x0067:
            if (r13 != 0) goto L_0x0075
            boolean r13 = r11.initializePanelDecor(r12)
            if (r13 == 0) goto L_0x00f6
            android.view.ViewGroup r13 = r12.decorView
            if (r13 != 0) goto L_0x0084
            goto L_0x00f6
        L_0x0075:
            boolean r3 = r12.refreshDecorView
            if (r3 == 0) goto L_0x0084
            int r13 = r13.getChildCount()
            if (r13 <= 0) goto L_0x0084
            android.view.ViewGroup r13 = r12.decorView
            r13.removeAllViews()
        L_0x0084:
            boolean r13 = r11.initializePanelContent(r12)
            if (r13 == 0) goto L_0x00f4
            boolean r13 = r12.hasPanelItems()
            if (r13 != 0) goto L_0x0091
            goto L_0x00f4
        L_0x0091:
            android.view.View r13 = r12.shownPanelView
            android.view.ViewGroup$LayoutParams r13 = r13.getLayoutParams()
            if (r13 != 0) goto L_0x009e
            android.view.ViewGroup$LayoutParams r13 = new android.view.ViewGroup$LayoutParams
            r13.<init>(r2, r2)
        L_0x009e:
            int r3 = r12.background
            android.view.ViewGroup r4 = r12.decorView
            r4.setBackgroundResource(r3)
            android.view.View r3 = r12.shownPanelView
            android.view.ViewParent r3 = r3.getParent()
            boolean r4 = r3 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x00b6
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            android.view.View r4 = r12.shownPanelView
            r3.removeView(r4)
        L_0x00b6:
            android.view.ViewGroup r3 = r12.decorView
            android.view.View r4 = r12.shownPanelView
            r3.addView(r4, r13)
            android.view.View r13 = r12.shownPanelView
            boolean r13 = r13.hasFocus()
            if (r13 != 0) goto L_0x00ca
            android.view.View r13 = r12.shownPanelView
            r13.requestFocus()
        L_0x00ca:
            r4 = r2
        L_0x00cb:
            r13 = 0
            r12.isHandled = r13
            android.view.WindowManager$LayoutParams r3 = new android.view.WindowManager$LayoutParams
            int r6 = r12.f982x
            int r7 = r12.y
            r9 = 8519680(0x820000, float:1.1938615E-38)
            r10 = -3
            r5 = -2
            r8 = 1002(0x3ea, float:1.404E-42)
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            int r13 = r12.gravity
            r3.gravity = r13
            int r13 = r12.windowAnimations
            r3.windowAnimations = r13
            android.view.ViewGroup r13 = r12.decorView
            r0.addView(r13, r3)
            r12.isOpen = r1
            int r12 = r12.featureId
            if (r12 != 0) goto L_0x00f6
            r11.updateBackInvokedCallbackState()
            return
        L_0x00f4:
            r12.refreshDecorView = r1
        L_0x00f6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.openPanel(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i7) {
        MenuBuilder menuBuilder;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.isPrepared || preparePanel(panelFeatureState, keyEvent)) && (menuBuilder = panelFeatureState.menu) != null) {
            z = menuBuilder.performShortcut(i2, keyEvent, i7);
        }
        if (z && (i7 & 1) == 0 && this.mDecorContentParent == null) {
            closePanel(panelFeatureState, true);
        }
        return z;
    }

    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        boolean z;
        int i2;
        boolean z3;
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        DecorContentParent decorContentParent3;
        if (this.mDestroyed) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (!(panelFeatureState2 == null || panelFeatureState2 == panelFeatureState)) {
            closePanel(panelFeatureState2, false);
        }
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback != null) {
            panelFeatureState.createdPanelView = windowCallback.onCreatePanelView(panelFeatureState.featureId);
        }
        int i7 = panelFeatureState.featureId;
        if (i7 == 0 || i7 == 108) {
            z = true;
        } else {
            z = false;
        }
        if (z && (decorContentParent3 = this.mDecorContentParent) != null) {
            decorContentParent3.setMenuPrepared();
        }
        if (panelFeatureState.createdPanelView == null && (!z || !(peekSupportActionBar() instanceof ToolbarActionBar))) {
            MenuBuilder menuBuilder = panelFeatureState.menu;
            if (menuBuilder == null || panelFeatureState.refreshMenuContent) {
                if (menuBuilder == null && (!initializePanelMenu(panelFeatureState) || panelFeatureState.menu == null)) {
                    return false;
                }
                if (z && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                    }
                    this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.stopDispatchingItemsChanged();
                if (!windowCallback.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
                    panelFeatureState.setMenu((MenuBuilder) null);
                    if (z && (decorContentParent2 = this.mDecorContentParent) != null) {
                        decorContentParent2.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                    }
                    return false;
                }
                panelFeatureState.refreshMenuContent = false;
            }
            panelFeatureState.menu.stopDispatchingItemsChanged();
            Bundle bundle = panelFeatureState.frozenActionViewState;
            if (bundle != null) {
                panelFeatureState.menu.restoreActionViewStates(bundle);
                panelFeatureState.frozenActionViewState = null;
            }
            if (!windowCallback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                if (z && (decorContentParent = this.mDecorContentParent) != null) {
                    decorContentParent.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.startDispatchingItemsChanged();
                return false;
            }
            if (keyEvent != null) {
                i2 = keyEvent.getDeviceId();
            } else {
                i2 = -1;
            }
            if (KeyCharacterMap.load(i2).getKeyboardType() != 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            panelFeatureState.qwertyMode = z3;
            panelFeatureState.menu.setQwertyMode(z3);
            panelFeatureState.menu.startDispatchingItemsChanged();
        }
        panelFeatureState.isPrepared = true;
        panelFeatureState.isHandled = false;
        this.mPreparedPanel = panelFeatureState;
        return true;
    }

    private void reopenMenu(boolean z) {
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent == null || !decorContentParent.canShowOverflowMenu() || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() && !this.mDecorContentParent.isOverflowMenuShowPending())) {
            PanelFeatureState panelState = getPanelState(0, true);
            panelState.refreshDecorView = true;
            closePanel(panelState, false);
            openPanel(panelState, (KeyEvent) null);
            return;
        }
        Window.Callback windowCallback = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing() && z) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!this.mDestroyed) {
                windowCallback.onPanelClosed(108, getPanelState(0, true).menu);
            }
        } else if (windowCallback != null && !this.mDestroyed) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState panelState2 = getPanelState(0, true);
            MenuBuilder menuBuilder = panelState2.menu;
            if (menuBuilder != null && !panelState2.refreshMenuContent && windowCallback.onPreparePanel(0, panelState2.createdPanelView, menuBuilder)) {
                windowCallback.onMenuOpened(108, panelState2.menu);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private int sanitizeWindowFeatureId(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i2 != 9) {
            return i2;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    private boolean shouldInheritContext(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.mWindow.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || ((View) viewParent).isAttachedToWindow()) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private AppCompatActivity tryUnwrapContext() {
        Context context = this.mContext;
        while (context != null) {
            if (!(context instanceof AppCompatActivity)) {
                if (!(context instanceof ContextWrapper)) {
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                return (AppCompatActivity) context;
            }
        }
        return null;
    }

    private void updateActivityConfiguration(Configuration configuration) {
        Activity activity = (Activity) this.mHost;
        if (activity instanceof LifecycleOwner) {
            if (((LifecycleOwner) activity).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                activity.onConfigurationChanged(configuration);
            }
        } else if (this.mCreated && !this.mDestroyed) {
            activity.onConfigurationChanged(configuration);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateAppConfiguration(int r22, androidx.core.os.LocaleListCompat r23, boolean r24) {
        /*
            r21 = this;
            r0 = r21
            android.content.Context r1 = r0.mContext
            r4 = 0
            r5 = 0
            r2 = r22
            r3 = r23
            android.content.res.Configuration r1 = r0.createOverrideAppConfiguration(r1, r2, r3, r4, r5)
            android.content.Context r2 = r0.mContext
            int r2 = r0.getActivityHandlesConfigChangesFlags(r2)
            android.content.res.Configuration r4 = r0.mEffectiveConfiguration
            if (r4 != 0) goto L_0x0022
            android.content.Context r4 = r0.mContext
            android.content.res.Resources r4 = r4.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
        L_0x0022:
            int r5 = r4.uiMode
            r5 = r5 & 48
            int r6 = r1.uiMode
            r6 = r6 & 48
            androidx.core.os.LocaleListCompat r9 = r0.getConfigurationLocales(r4)
            if (r3 != 0) goto L_0x0032
            r10 = 0
            goto L_0x0037
        L_0x0032:
            androidx.core.os.LocaleListCompat r7 = r0.getConfigurationLocales(r1)
            r10 = r7
        L_0x0037:
            r19 = 0
            if (r5 == r6) goto L_0x003e
            r7 = 512(0x200, float:7.175E-43)
            goto L_0x0040
        L_0x003e:
            r7 = r19
        L_0x0040:
            if (r10 == 0) goto L_0x004a
            boolean r8 = r9.equals(r10)
            if (r8 != 0) goto L_0x004a
            r7 = r7 | 8196(0x2004, float:1.1485E-41)
        L_0x004a:
            int r8 = ~r2
            r8 = r8 & r7
            r20 = 1
            if (r8 == 0) goto L_0x005f
            if (r24 == 0) goto L_0x005f
            boolean r8 = r0.mBaseContextAttached
            if (r8 == 0) goto L_0x005f
            boolean r8 = sCanReturnDifferentContext
            if (r8 != 0) goto L_0x0062
            boolean r11 = r0.mCreated
            if (r11 == 0) goto L_0x005f
            goto L_0x0062
        L_0x005f:
            r1 = r7
            goto L_0x00fe
        L_0x0062:
            java.lang.Object r11 = r0.mHost
            boolean r12 = r11 instanceof android.app.Activity
            if (r12 == 0) goto L_0x005f
            android.app.Activity r11 = (android.app.Activity) r11
            boolean r11 = r11.isChild()
            if (r11 != 0) goto L_0x005f
            int r11 = android.os.Build.VERSION.SDK_INT
            r12 = 31
            if (r11 < r12) goto L_0x008d
            r11 = r7 & 8192(0x2000, float:1.14794E-41)
            if (r11 == 0) goto L_0x008d
            java.lang.Object r11 = r0.mHost
            android.app.Activity r11 = (android.app.Activity) r11
            android.view.Window r11 = r11.getWindow()
            android.view.View r11 = r11.getDecorView()
            int r1 = r1.getLayoutDirection()
            r11.setLayoutDirection(r1)
        L_0x008d:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            r5 = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            r11 = r2 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x009d
            r11 = r20
            goto L_0x009f
        L_0x009d:
            r11 = r19
        L_0x009f:
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)
            r12 = r2 & 4
            if (r12 == 0) goto L_0x00aa
            r12 = r20
            goto L_0x00ac
        L_0x00aa:
            r12 = r19
        L_0x00ac:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r13 = r2 & 8192(0x2000, float:1.14794E-41)
            if (r13 == 0) goto L_0x00b7
            r13 = r20
            goto L_0x00b9
        L_0x00b7:
            r13 = r19
        L_0x00b9:
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)
            boolean r14 = r0.mBaseContextAttached
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
            boolean r15 = r0.mCreated
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r15)
            java.lang.Boolean r16 = java.lang.Boolean.valueOf(r5)
            java.lang.Object r5 = r0.mHost
            android.content.Context r4 = r0.mContext
            android.content.Context r4 = r4.getApplicationContext()
            android.content.res.Resources r4 = r4.getResources()
            android.content.res.Configuration r18 = r4.getConfiguration()
            r17 = r7
            r7 = r1
            r1 = r17
            r17 = r5
            java.lang.Object[] r4 = new java.lang.Object[]{r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18}
            java.lang.String r5 = "updateAppConfiguration attempting to recreate Activity [currentNightMode:%s, newNightMode:%s, currentLocales:%s, newLocales:%s, activityHandlingNightModeChanges:%s, activityHandlingLocalesChanges:%s, activityHandlingLayoutDirectionChanges:%s, baseContextAttached:%s, created:%s, canReturnDifferentContext:%s, host:%s], application configuration [%s]"
            java.lang.String r4 = java.lang.String.format(r5, r4)
            java.lang.String r5 = "AppCompatDelegate"
            android.util.Log.d(r5, r4)
            java.lang.Object r4 = r0.mHost
            android.app.Activity r4 = (android.app.Activity) r4
            androidx.core.app.ActivityCompat.recreate(r4)
            r4 = r20
            goto L_0x0100
        L_0x00fe:
            r4 = r19
        L_0x0100:
            if (r4 != 0) goto L_0x0112
            if (r1 == 0) goto L_0x0112
            r2 = r2 & r1
            if (r2 != r1) goto L_0x010b
            r2 = r20
        L_0x0109:
            r4 = 0
            goto L_0x010e
        L_0x010b:
            r2 = r19
            goto L_0x0109
        L_0x010e:
            r0.updateResourcesConfiguration(r6, r10, r2, r4)
            goto L_0x0114
        L_0x0112:
            r20 = r4
        L_0x0114:
            if (r20 == 0) goto L_0x0132
            java.lang.Object r2 = r0.mHost
            boolean r4 = r2 instanceof androidx.appcompat.app.AppCompatActivity
            if (r4 == 0) goto L_0x0132
            r4 = r1 & 512(0x200, float:7.175E-43)
            if (r4 == 0) goto L_0x0127
            androidx.appcompat.app.AppCompatActivity r2 = (androidx.appcompat.app.AppCompatActivity) r2
            r4 = r22
            r2.onNightModeChanged(r4)
        L_0x0127:
            r1 = r1 & 4
            if (r1 == 0) goto L_0x0132
            java.lang.Object r1 = r0.mHost
            androidx.appcompat.app.AppCompatActivity r1 = (androidx.appcompat.app.AppCompatActivity) r1
            r1.onLocalesChanged(r3)
        L_0x0132:
            if (r10 == 0) goto L_0x0145
            android.content.Context r1 = r0.mContext
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            androidx.core.os.LocaleListCompat r1 = r0.getConfigurationLocales(r1)
            r0.setDefaultLocalesForLocaleList(r1)
        L_0x0145:
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.updateAppConfiguration(int, androidx.core.os.LocaleListCompat, boolean):boolean");
    }

    private void updateResourcesConfiguration(int i2, LocaleListCompat localeListCompat, boolean z, Configuration configuration) {
        Resources resources = this.mContext.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & -49);
        if (localeListCompat != null) {
            setConfigurationLocales(configuration2, localeListCompat);
        }
        resources.updateConfiguration(configuration2, (DisplayMetrics) null);
        int i7 = this.mThemeResId;
        if (i7 != 0) {
            this.mContext.setTheme(i7);
            this.mContext.getTheme().applyStyle(this.mThemeResId, true);
        }
        if (z && (this.mHost instanceof Activity)) {
            updateActivityConfiguration(configuration2);
        }
    }

    private void updateStatusGuardColor(View view) {
        int i2;
        if ((ViewCompat.getWindowSystemUiVisibility(view) & SerializeOptions.SORT) != 0) {
            i2 = ContextCompat.getColor(this.mContext, R$color.abc_decor_view_status_guard_light);
        } else {
            i2 = ContextCompat.getColor(this.mContext, R$color.abc_decor_view_status_guard);
        }
        view.setBackgroundColor(i2);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public boolean applyDayNight() {
        return applyApplicationSpecificConfig(true);
    }

    public Context attachBaseContext2(Context context) {
        Context context2;
        AppCompatDelegateImpl appCompatDelegateImpl;
        Configuration configuration;
        this.mBaseContextAttached = true;
        int mapNightMode = mapNightMode(context, calculateNightMode());
        if (AppCompatDelegate.isAutoStorageOptedIn(context)) {
            AppCompatDelegate.syncRequestedAndStoredLocales(context);
        }
        LocaleListCompat calculateApplicationLocales = calculateApplicationLocales(context);
        if (context instanceof android.view.ContextThemeWrapper) {
            appCompatDelegateImpl = this;
            context2 = context;
            try {
                ((android.view.ContextThemeWrapper) context2).applyOverrideConfiguration(appCompatDelegateImpl.createOverrideAppConfiguration(context2, mapNightMode, calculateApplicationLocales, (Configuration) null, false));
                return context2;
            } catch (IllegalStateException unused) {
            }
        } else {
            appCompatDelegateImpl = this;
            context2 = context;
            if (context2 instanceof ContextThemeWrapper) {
                try {
                    ((ContextThemeWrapper) context2).applyOverrideConfiguration(appCompatDelegateImpl.createOverrideAppConfiguration(context2, mapNightMode, calculateApplicationLocales, (Configuration) null, false));
                    return context2;
                } catch (IllegalStateException unused2) {
                }
            }
            if (!sCanReturnDifferentContext) {
                return super.attachBaseContext2(context2);
            }
            Configuration configuration2 = new Configuration();
            configuration2.uiMode = -1;
            configuration2.fontScale = 0.0f;
            Configuration configuration3 = context2.createConfigurationContext(configuration2).getResources().getConfiguration();
            Configuration configuration4 = context2.getResources().getConfiguration();
            configuration3.uiMode = configuration4.uiMode;
            if (!configuration3.equals(configuration4)) {
                configuration = generateConfigDelta(configuration3, configuration4);
            } else {
                configuration = null;
            }
            Configuration createOverrideAppConfiguration = appCompatDelegateImpl.createOverrideAppConfiguration(context2, mapNightMode, calculateApplicationLocales, configuration, true);
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context2, R$style.Theme_AppCompat_Empty);
            contextThemeWrapper.applyOverrideConfiguration(createOverrideAppConfiguration);
            try {
                if (context2.getTheme() != null) {
                    ResourcesCompat.ThemeCompat.rebase(contextThemeWrapper.getTheme());
                }
            } catch (NullPointerException unused3) {
            }
            return super.attachBaseContext2(contextThemeWrapper);
        }
    }

    public LocaleListCompat calculateApplicationLocales(Context context) {
        LocaleListCompat requestedAppLocales;
        if (Build.VERSION.SDK_INT >= 33 || (requestedAppLocales = AppCompatDelegate.getRequestedAppLocales()) == null) {
            return null;
        }
        LocaleListCompat configurationLocales = getConfigurationLocales(context.getApplicationContext().getResources().getConfiguration());
        LocaleListCompat combineLocalesIfOverlayExists = LocaleOverlayHelper.combineLocalesIfOverlayExists(requestedAppLocales, configurationLocales);
        if (combineLocalesIfOverlayExists.isEmpty()) {
            return configurationLocales;
        }
        return combineLocalesIfOverlayExists;
    }

    public void callOnPanelClosed(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.mPanels;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.menu;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.isOpen) && !this.mDestroyed) {
            this.mAppCompatWindowCallback.bypassOnPanelClosed(this.mWindow.getCallback(), i2, menu);
        }
    }

    public void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback windowCallback = getWindowCallback();
            if (windowCallback != null && !this.mDestroyed) {
                windowCallback.onPanelClosed(108, menuBuilder);
            }
            this.mClosingActionMenu = false;
        }
    }

    public void closePanel(int i2) {
        closePanel(getPanelState(i2, true), true);
    }

    public View createView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        if (this.mAppCompatViewInflater == null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
            String string = obtainStyledAttributes.getString(R$styleable.AppCompatTheme_viewInflaterClass);
            obtainStyledAttributes.recycle();
            if (string == null) {
                this.mAppCompatViewInflater = new AppCompatViewInflater();
            } else {
                try {
                    this.mAppCompatViewInflater = (AppCompatViewInflater) this.mContext.getClassLoader().loadClass(string).getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.mAppCompatViewInflater = new AppCompatViewInflater();
                }
            }
        }
        boolean z3 = IS_PRE_LOLLIPOP;
        boolean z7 = false;
        if (z3) {
            if (this.mLayoutIncludeDetector == null) {
                this.mLayoutIncludeDetector = new LayoutIncludeDetector();
            }
            if (this.mLayoutIncludeDetector.detect(attributeSet)) {
                z = true;
                return this.mAppCompatViewInflater.createView(view, str, context, attributeSet, z, z3, true, VectorEnabledTintResources.shouldBeUsed());
            } else if (!(attributeSet instanceof XmlPullParser)) {
                z7 = shouldInheritContext((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z7 = true;
            }
        }
        z = z7;
        return this.mAppCompatViewInflater.createView(view, str, context, attributeSet, z, z3, true, VectorEnabledTintResources.shouldBeUsed());
    }

    public void dismissPopups() {
        MenuBuilder menuBuilder;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.dismissPopups();
        }
        if (this.mActionModePopup != null) {
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            if (this.mActionModePopup.isShowing()) {
                try {
                    this.mActionModePopup.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.mActionModePopup = null;
        }
        endOnGoingFadeAnimation();
        PanelFeatureState panelState = getPanelState(0, false);
        if (panelState != null && (menuBuilder = panelState.menu) != null) {
            menuBuilder.close();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.mHost;
        if (((obj instanceof KeyEventDispatcher.Component) || (obj instanceof AppCompatDialog)) && (decorView = this.mWindow.getDecorView()) != null && KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.mAppCompatWindowCallback.bypassDispatchKeyEvent(this.mWindow.getCallback(), keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 0) {
            return onKeyDown(keyCode, keyEvent);
        }
        return onKeyUp(keyCode, keyEvent);
    }

    public void doInvalidatePanelMenu(int i2) {
        PanelFeatureState panelState;
        PanelFeatureState panelState2 = getPanelState(i2, true);
        if (panelState2.menu != null) {
            Bundle bundle = new Bundle();
            panelState2.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelState2.frozenActionViewState = bundle;
            }
            panelState2.menu.stopDispatchingItemsChanged();
            panelState2.menu.clear();
        }
        panelState2.refreshMenuContent = true;
        panelState2.refreshDecorView = true;
        if ((i2 == 108 || i2 == 0) && this.mDecorContentParent != null && (panelState = getPanelState(0, false)) != null) {
            panelState.isPrepared = false;
            preparePanel(panelState, (KeyEvent) null);
        }
    }

    public void endOnGoingFadeAnimation() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mFadeAnim;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
    }

    public PanelFeatureState findMenuPanel(Menu menu) {
        int i2;
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr != null) {
            i2 = panelFeatureStateArr.length;
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i7];
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    public <T extends View> T findViewById(int i2) {
        ensureSubDecor();
        return this.mWindow.findViewById(i2);
    }

    public final Context getActionBarThemedContext() {
        Context context;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            context = supportActionBar.getThemedContext();
        } else {
            context = null;
        }
        if (context == null) {
            return this.mContext;
        }
        return context;
    }

    public LocaleListCompat getConfigurationLocales(Configuration configuration) {
        return Api24Impl.getLocales(configuration);
    }

    public Context getContextForDelegate() {
        return this.mContext;
    }

    public int getLocalNightMode() {
        return this.mLocalNightMode;
    }

    public MenuInflater getMenuInflater() {
        Context context;
        if (this.mMenuInflater == null) {
            initWindowDecorActionBar();
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                context = actionBar.getThemedContext();
            } else {
                context = this.mContext;
            }
            this.mMenuInflater = new SupportMenuInflater(context);
        }
        return this.mMenuInflater;
    }

    public PanelFeatureState getPanelState(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[(i2 + 1)];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.mPanels = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    public final CharSequence getTitle() {
        Object obj = this.mHost;
        if (obj instanceof Activity) {
            return ((Activity) obj).getTitle();
        }
        return this.mTitle;
    }

    public final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public void invalidateOptionsMenu() {
        if (peekSupportActionBar() != null && !getSupportActionBar().invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    public int mapNightMode(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 != 0) {
                if (!(i2 == 1 || i2 == 2)) {
                    if (i2 == 3) {
                        return getAutoBatteryNightModeManager(context).getApplyableNightMode();
                    }
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
            } else if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                return -1;
            } else {
                return getAutoTimeNightModeManager(context).getApplyableNightMode();
            }
        }
        return i2;
    }

    public boolean onBackPressed() {
        boolean z = this.mLongPressBackDown;
        this.mLongPressBackDown = false;
        PanelFeatureState panelState = getPanelState(0, false);
        if (panelState == null || !panelState.isOpen) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
            if (inputMethodManager == null || !SeslInputMethodManagerReflector.isInputMethodShown(inputMethodManager)) {
                androidx.appcompat.view.ActionMode actionMode = this.mActionMode;
                if (actionMode != null) {
                    actionMode.finish();
                    return true;
                }
                ActionBar supportActionBar = getSupportActionBar();
                if (supportActionBar == null || !supportActionBar.collapseActionView()) {
                    return false;
                }
                return true;
            }
            inputMethodManager.hideSoftInputFromWindow(this.mWindow.getDecorView().getWindowToken(), 0);
            return true;
        }
        if (!z) {
            closePanel(panelState, true);
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        ActionBar supportActionBar;
        if (this.mHasActionBar && this.mSubDecorInstalled && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.onConfigurationChanged(configuration);
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        applyApplicationSpecificConfig(false, false);
    }

    public void onCreate(Bundle bundle) {
        String str;
        this.mBaseContextAttached = true;
        applyApplicationSpecificConfig(false);
        ensureWindow();
        Object obj = this.mHost;
        if (obj instanceof Activity) {
            try {
                str = NavUtils.getParentActivityName((Activity) obj);
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                ActionBar peekSupportActionBar = peekSupportActionBar();
                if (peekSupportActionBar == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    peekSupportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
            AppCompatDelegate.addActiveDelegate(this);
        }
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        this.mCreated = true;
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mHost
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L_0x0009
            androidx.appcompat.app.AppCompatDelegate.removeActivityDelegate(r3)
        L_0x0009:
            boolean r0 = r3.mInvalidatePanelMenuPosted
            if (r0 == 0) goto L_0x0018
            android.view.Window r0 = r3.mWindow
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.mInvalidatePanelMenuRunnable
            r0.removeCallbacks(r1)
        L_0x0018:
            r0 = 1
            r3.mDestroyed = r0
            int r0 = r3.mLocalNightMode
            r1 = -100
            if (r0 == r1) goto L_0x0045
            java.lang.Object r0 = r3.mHost
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0045
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L_0x0045
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.Integer> r0 = sLocalNightModes
            java.lang.Object r1 = r3.mHost
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.mLocalNightMode
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L_0x0054
        L_0x0045:
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.Integer> r0 = sLocalNightModes
            java.lang.Object r1 = r3.mHost
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L_0x0054:
            androidx.appcompat.app.ActionBar r0 = r3.mActionBar
            if (r0 == 0) goto L_0x005b
            r0.onDestroy()
        L_0x005b:
            r3.cleanupAutoManagers()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onDestroy():void");
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        boolean z = true;
        if (i2 == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.mLongPressBackDown = z;
        } else if (i2 == 82) {
            onKeyDownPanel(0, keyEvent);
            return true;
        }
        return false;
    }

    public boolean onKeyShortcut(int i2, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.onKeyShortcut(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.mPreparedPanel;
        if (panelFeatureState == null || !performPanelShortcut(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.mPreparedPanel == null) {
                PanelFeatureState panelState = getPanelState(0, true);
                preparePanel(panelState, keyEvent);
                boolean performPanelShortcut = performPanelShortcut(panelState, keyEvent.getKeyCode(), keyEvent, 1);
                panelState.isPrepared = false;
                if (performPanelShortcut) {
                    return true;
                }
            }
            return false;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (panelFeatureState2 != null) {
            panelFeatureState2.isHandled = true;
        }
        return true;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            if (i2 == 82) {
                onKeyUpPanel(0, keyEvent);
                return true;
            }
        } else if (onBackPressed()) {
            return true;
        }
        return false;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState findMenuPanel;
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback == null || this.mDestroyed || (findMenuPanel = findMenuPanel(menuBuilder.getRootMenu())) == null) {
            return false;
        }
        return windowCallback.onMenuItemSelected(findMenuPanel.featureId, menuItem);
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(true);
    }

    public void onMenuOpened(int i2) {
        ActionBar supportActionBar;
        if (i2 == 108 && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.dispatchMenuVisibilityChanged(true);
        }
    }

    public void onPanelClosed(int i2) {
        if (i2 == 108) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        } else if (i2 == 0) {
            PanelFeatureState panelState = getPanelState(i2, true);
            if (panelState.isOpen) {
                closePanel(panelState, false);
            }
        }
    }

    public void onPostCreate(Bundle bundle) {
        ensureSubDecor();
    }

    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    public void onStart() {
        applyApplicationSpecificConfig(true, false);
    }

    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
        closeAllPanels();
    }

    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    public boolean requestWindowFeature(int i2) {
        int sanitizeWindowFeatureId = sanitizeWindowFeatureId(i2);
        if (this.mWindowNoTitle && sanitizeWindowFeatureId == 108) {
            return false;
        }
        if (this.mHasActionBar && sanitizeWindowFeatureId == 1) {
            this.mHasActionBar = false;
        }
        if (sanitizeWindowFeatureId == 1) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mWindowNoTitle = true;
            return true;
        } else if (sanitizeWindowFeatureId == 2) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureProgress = true;
            return true;
        } else if (sanitizeWindowFeatureId == 5) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureIndeterminateProgress = true;
            return true;
        } else if (sanitizeWindowFeatureId == 10) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionMode = true;
            return true;
        } else if (sanitizeWindowFeatureId == 108) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
        } else if (sanitizeWindowFeatureId != 109) {
            return this.mWindow.requestFeature(sanitizeWindowFeatureId);
        } else {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionBar = true;
            return true;
        }
    }

    public void setConfigurationLocales(Configuration configuration, LocaleListCompat localeListCompat) {
        Api24Impl.setLocales(configuration, localeListCompat);
    }

    public void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public void setDefaultLocalesForLocaleList(LocaleListCompat localeListCompat) {
        Api24Impl.setDefaultLocales(localeListCompat);
    }

    public void setLocalNightMode(int i2) {
        if (this.mLocalNightMode != i2) {
            this.mLocalNightMode = i2;
            if (this.mBaseContextAttached) {
                applyDayNight();
            }
        }
    }

    public void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher) {
        OnBackInvokedCallback onBackInvokedCallback;
        super.setOnBackInvokedDispatcher(onBackInvokedDispatcher);
        OnBackInvokedDispatcher onBackInvokedDispatcher2 = this.mDispatcher;
        if (!(onBackInvokedDispatcher2 == null || (onBackInvokedCallback = this.mBackCallback) == null)) {
            Api33Impl.unregisterOnBackInvokedCallback(onBackInvokedDispatcher2, onBackInvokedCallback);
            this.mBackCallback = null;
        }
        if (onBackInvokedDispatcher == null) {
            Object obj = this.mHost;
            if ((obj instanceof Activity) && ((Activity) obj).getWindow() != null) {
                this.mDispatcher = Api33Impl.getOnBackInvokedDispatcher((Activity) this.mHost);
                updateBackInvokedCallbackState();
            }
        }
        this.mDispatcher = onBackInvokedDispatcher;
        updateBackInvokedCallbackState();
    }

    public void setSupportActionBar(Toolbar toolbar) {
        if (this.mHost instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (!(supportActionBar instanceof WindowDecorActionBar)) {
                this.mMenuInflater = null;
                if (supportActionBar != null) {
                    supportActionBar.onDestroy();
                }
                this.mActionBar = null;
                if (toolbar != null) {
                    ToolbarActionBar toolbarActionBar = new ToolbarActionBar(toolbar, getTitle(), this.mAppCompatWindowCallback);
                    this.mActionBar = toolbarActionBar;
                    this.mAppCompatWindowCallback.setActionBarCallback(toolbarActionBar.mMenuCallback);
                    toolbar.setBackInvokedCallbackEnabled(true);
                    Window window = this.mWindow;
                    if (window != null) {
                        window.setCallback(this.mAppCompatWindowCallback);
                    }
                } else {
                    this.mAppCompatWindowCallback.setActionBarCallback((ActionBarMenuCallback) null);
                }
                invalidateOptionsMenu();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    public void setTheme(int i2) {
        this.mThemeResId = i2;
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
            peekSupportActionBar().setWindowTitle(charSequence);
        } else {
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    public final boolean shouldAnimateActionModeView() {
        ViewGroup viewGroup;
        if (!this.mSubDecorInstalled || (viewGroup = this.mSubDecor) == null || !viewGroup.isLaidOut()) {
            return false;
        }
        return true;
    }

    public boolean shouldRegisterBackInvokedCallback() {
        if (this.mDispatcher == null) {
            return false;
        }
        PanelFeatureState panelState = getPanelState(0, false);
        if ((panelState == null || !panelState.isOpen) && this.mActionMode == null) {
            return false;
        }
        return true;
    }

    public androidx.appcompat.view.ActionMode startSupportActionMode(ActionMode.Callback callback) {
        AppCompatCallback appCompatCallback;
        if (callback != null) {
            androidx.appcompat.view.ActionMode actionMode = this.mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                androidx.appcompat.view.ActionMode startActionMode = supportActionBar.startActionMode(actionModeCallbackWrapperV9);
                this.mActionMode = startActionMode;
                if (!(startActionMode == null || (appCompatCallback = this.mAppCompatCallback) == null)) {
                    appCompatCallback.onSupportActionModeStarted(startActionMode);
                }
            }
            if (this.mActionMode == null) {
                this.mActionMode = startSupportActionModeFromWindow(actionModeCallbackWrapperV9);
            }
            updateBackInvokedCallbackState();
            return this.mActionMode;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.appcompat.view.ActionMode startSupportActionModeFromWindow(androidx.appcompat.view.ActionMode.Callback r9) {
        /*
            r8 = this;
            r8.endOnGoingFadeAnimation()
            androidx.appcompat.view.ActionMode r0 = r8.mActionMode
            if (r0 == 0) goto L_0x000a
            r0.finish()
        L_0x000a:
            boolean r0 = r9 instanceof androidx.appcompat.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9
            if (r0 != 0) goto L_0x0014
            androidx.appcompat.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9 r0 = new androidx.appcompat.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9
            r0.<init>(r9)
            r9 = r0
        L_0x0014:
            androidx.appcompat.app.AppCompatCallback r0 = r8.mAppCompatCallback
            r1 = 0
            if (r0 == 0) goto L_0x0022
            boolean r2 = r8.mDestroyed
            if (r2 != 0) goto L_0x0022
            androidx.appcompat.view.ActionMode r0 = r0.onWindowStartingSupportActionMode(r9)     // Catch:{ AbstractMethodError -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r0 = r1
        L_0x0023:
            if (r0 == 0) goto L_0x0029
            r8.mActionMode = r0
            goto L_0x01d4
        L_0x0029:
            androidx.appcompat.widget.ActionBarContextView r0 = r8.mActionModeView
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x014d
            boolean r0 = r8.mIsFloating
            if (r0 == 0) goto L_0x00b6
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.Context r4 = r8.mContext
            android.content.res.Resources$Theme r4 = r4.getTheme()
            int r5 = androidx.appcompat.R$attr.actionBarTheme
            r4.resolveAttribute(r5, r0, r3)
            int r5 = r0.resourceId
            if (r5 == 0) goto L_0x0068
            android.content.Context r5 = r8.mContext
            android.content.res.Resources r5 = r5.getResources()
            android.content.res.Resources$Theme r5 = r5.newTheme()
            r5.setTo(r4)
            int r4 = r0.resourceId
            r5.applyStyle(r4, r3)
            androidx.appcompat.view.ContextThemeWrapper r4 = new androidx.appcompat.view.ContextThemeWrapper
            android.content.Context r6 = r8.mContext
            r4.<init>((android.content.Context) r6, (int) r2)
            android.content.res.Resources$Theme r6 = r4.getTheme()
            r6.setTo(r5)
            goto L_0x006a
        L_0x0068:
            android.content.Context r4 = r8.mContext
        L_0x006a:
            androidx.appcompat.widget.ActionBarContextView r5 = new androidx.appcompat.widget.ActionBarContextView
            r5.<init>(r4)
            r8.mActionModeView = r5
            android.widget.PopupWindow r5 = new android.widget.PopupWindow
            int r6 = androidx.appcompat.R$attr.actionModePopupWindowStyle
            r5.<init>(r4, r1, r6)
            r8.mActionModePopup = r5
            r6 = 2
            androidx.core.widget.PopupWindowCompat.setWindowLayoutType(r5, r6)
            android.widget.PopupWindow r5 = r8.mActionModePopup
            androidx.appcompat.widget.ActionBarContextView r6 = r8.mActionModeView
            r5.setContentView(r6)
            android.widget.PopupWindow r5 = r8.mActionModePopup
            r6 = -1
            r5.setWidth(r6)
            android.content.res.Resources$Theme r5 = r4.getTheme()
            int r6 = androidx.appcompat.R$attr.actionBarSize
            r5.resolveAttribute(r6, r0, r3)
            int r0 = r0.data
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            int r0 = android.util.TypedValue.complexToDimensionPixelSize(r0, r4)
            androidx.appcompat.widget.ActionBarContextView r4 = r8.mActionModeView
            r4.setContentHeight(r0)
            android.widget.PopupWindow r0 = r8.mActionModePopup
            r4 = -2
            r0.setHeight(r4)
            androidx.appcompat.app.AppCompatDelegateImpl$6 r0 = new androidx.appcompat.app.AppCompatDelegateImpl$6
            r0.<init>()
            r8.mShowActionModePopup = r0
            goto L_0x014d
        L_0x00b6:
            android.view.ViewGroup r0 = r8.mSubDecor
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r4 = r0.getResources()
            java.lang.String r5 = "sesl_floating_toolbar_layout"
            java.lang.String r6 = r0.getPackageName()
            java.lang.String r7 = "id"
            int r4 = r4.getIdentifier(r5, r7, r6)
            android.view.View r5 = r8.mActionBarTargetView
            if (r5 == 0) goto L_0x00d7
            android.view.View r4 = r5.findViewById(r4)
            r8.mActionBarTargetView = r1
            goto L_0x00dd
        L_0x00d7:
            android.view.ViewGroup r5 = r8.mSubDecor
            android.view.View r4 = r5.findViewById(r4)
        L_0x00dd:
            if (r4 != 0) goto L_0x00f3
            android.content.res.Resources r4 = r0.getResources()
            java.lang.String r5 = "collapsing_toolbar"
            java.lang.String r6 = r0.getPackageName()
            int r4 = r4.getIdentifier(r5, r7, r6)
            android.view.ViewGroup r5 = r8.mSubDecor
            android.view.View r4 = r5.findViewById(r4)
        L_0x00f3:
            if (r4 != 0) goto L_0x0109
            android.content.res.Resources r4 = r0.getResources()
            java.lang.String r5 = "sesl_toolbar_container"
            java.lang.String r0 = r0.getPackageName()
            int r0 = r4.getIdentifier(r5, r7, r0)
            android.view.ViewGroup r4 = r8.mSubDecor
            android.view.View r4 = r4.findViewById(r0)
        L_0x0109:
            if (r4 != 0) goto L_0x0116
            android.view.ViewGroup r0 = r8.mSubDecor
            int r5 = androidx.appcompat.R$id.action_mode_bar_stub
            android.view.View r0 = r0.findViewById(r5)
            androidx.appcompat.widget.ViewStubCompat r0 = (androidx.appcompat.widget.ViewStubCompat) r0
            goto L_0x012d
        L_0x0116:
            boolean r0 = r8.mOverlayActionMode
            if (r0 == 0) goto L_0x0125
            android.view.ViewGroup r0 = r8.mSubDecor
            int r5 = androidx.appcompat.R$id.action_mode_bar_stub
            android.view.View r0 = r0.findViewById(r5)
            androidx.appcompat.widget.ViewStubCompat r0 = (androidx.appcompat.widget.ViewStubCompat) r0
            goto L_0x012d
        L_0x0125:
            int r0 = androidx.appcompat.R$id.action_mode_bar_stub
            android.view.View r0 = r4.findViewById(r0)
            androidx.appcompat.widget.ViewStubCompat r0 = (androidx.appcompat.widget.ViewStubCompat) r0
        L_0x012d:
            if (r0 == 0) goto L_0x0143
            android.content.Context r4 = r8.getActionBarThemedContext()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0.setLayoutInflater(r4)
            android.view.View r0 = r0.inflate()
            androidx.appcompat.widget.ActionBarContextView r0 = (androidx.appcompat.widget.ActionBarContextView) r0
            r8.mActionModeView = r0
            goto L_0x014d
        L_0x0143:
            int r0 = androidx.appcompat.R$id.action_mode_bar
            android.view.View r0 = r4.findViewById(r0)
            androidx.appcompat.widget.ActionBarContextView r0 = (androidx.appcompat.widget.ActionBarContextView) r0
            r8.mActionModeView = r0
        L_0x014d:
            androidx.appcompat.widget.ActionBarContextView r0 = r8.mActionModeView
            if (r0 == 0) goto L_0x01d4
            r8.endOnGoingFadeAnimation()
            androidx.appcompat.widget.ActionBarContextView r0 = r8.mActionModeView
            r0.killMode()
            androidx.appcompat.view.StandaloneActionMode r0 = new androidx.appcompat.view.StandaloneActionMode
            androidx.appcompat.widget.ActionBarContextView r4 = r8.mActionModeView
            android.content.Context r4 = r4.getContext()
            androidx.appcompat.widget.ActionBarContextView r5 = r8.mActionModeView
            android.widget.PopupWindow r6 = r8.mActionModePopup
            if (r6 != 0) goto L_0x0168
            goto L_0x0169
        L_0x0168:
            r3 = r2
        L_0x0169:
            r0.<init>(r4, r5, r9, r3)
            android.view.Menu r3 = r0.getMenu()
            boolean r9 = r9.onCreateActionMode(r0, r3)
            if (r9 == 0) goto L_0x01d2
            r0.invalidate()
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            r9.initForMode(r0)
            r8.mActionMode = r0
            boolean r9 = r8.shouldAnimateActionModeView()
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r9 == 0) goto L_0x01a3
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            r1 = 0
            r9.setAlpha(r1)
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            androidx.core.view.ViewPropertyAnimatorCompat r9 = androidx.core.view.ViewCompat.animate(r9)
            androidx.core.view.ViewPropertyAnimatorCompat r9 = r9.alpha(r0)
            r8.mFadeAnim = r9
            androidx.appcompat.app.AppCompatDelegateImpl$7 r0 = new androidx.appcompat.app.AppCompatDelegateImpl$7
            r0.<init>()
            r9.setListener(r0)
            goto L_0x01c2
        L_0x01a3:
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            r9.setAlpha(r0)
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            r9.setVisibility(r2)
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            android.view.ViewParent r9 = r9.getParent()
            boolean r9 = r9 instanceof android.view.View
            if (r9 == 0) goto L_0x01c2
            androidx.appcompat.widget.ActionBarContextView r9 = r8.mActionModeView
            android.view.ViewParent r9 = r9.getParent()
            android.view.View r9 = (android.view.View) r9
            androidx.core.view.ViewCompat.requestApplyInsets(r9)
        L_0x01c2:
            android.widget.PopupWindow r9 = r8.mActionModePopup
            if (r9 == 0) goto L_0x01d4
            android.view.Window r9 = r8.mWindow
            android.view.View r9 = r9.getDecorView()
            java.lang.Runnable r0 = r8.mShowActionModePopup
            r9.post(r0)
            goto L_0x01d4
        L_0x01d2:
            r8.mActionMode = r1
        L_0x01d4:
            androidx.appcompat.view.ActionMode r9 = r8.mActionMode
            if (r9 == 0) goto L_0x01df
            androidx.appcompat.app.AppCompatCallback r0 = r8.mAppCompatCallback
            if (r0 == 0) goto L_0x01df
            r0.onSupportActionModeStarted(r9)
        L_0x01df:
            r8.updateBackInvokedCallbackState()
            androidx.appcompat.view.ActionMode r8 = r8.mActionMode
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.startSupportActionModeFromWindow(androidx.appcompat.view.ActionMode$Callback):androidx.appcompat.view.ActionMode");
    }

    public void updateBackInvokedCallbackState() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean shouldRegisterBackInvokedCallback = shouldRegisterBackInvokedCallback();
            if (shouldRegisterBackInvokedCallback && this.mBackCallback == null) {
                this.mBackCallback = Api33Impl.registerOnBackPressedCallback(this.mDispatcher, this);
            } else if (!shouldRegisterBackInvokedCallback && (onBackInvokedCallback = this.mBackCallback) != null) {
                Api33Impl.unregisterOnBackInvokedCallback(this.mDispatcher, onBackInvokedCallback);
                this.mBackCallback = null;
            }
        }
    }

    public final int updateStatusGuard(WindowInsetsCompat windowInsetsCompat, Rect rect) {
        int i2;
        boolean z;
        int i7;
        int i8;
        boolean z3;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i10;
        int i11 = 0;
        if (windowInsetsCompat != null) {
            i2 = windowInsetsCompat.getSystemWindowInsetTop();
        } else if (rect != null) {
            i2 = rect.top;
        } else {
            i2 = 0;
        }
        ActionBarContextView actionBarContextView = this.mActionModeView;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mActionModeView.getLayoutParams();
            boolean z7 = true;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect rect2 = this.mTempRect1;
                Rect rect3 = this.mTempRect2;
                if (windowInsetsCompat == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                }
                ViewUtils.computeFitSystemWindows(this.mSubDecor, rect2, rect3);
                int i12 = rect2.top;
                int i13 = rect2.left;
                int i14 = rect2.right;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this.mSubDecor);
                if (rootWindowInsets == null) {
                    i7 = 0;
                } else {
                    i7 = rootWindowInsets.getSystemWindowInsetLeft();
                }
                if (rootWindowInsets == null) {
                    i8 = 0;
                } else {
                    i8 = rootWindowInsets.getSystemWindowInsetRight();
                }
                if (marginLayoutParams2.topMargin == i12 && marginLayoutParams2.leftMargin == i13 && marginLayoutParams2.rightMargin == i14) {
                    z3 = false;
                } else {
                    marginLayoutParams2.topMargin = i12;
                    marginLayoutParams2.leftMargin = i13;
                    marginLayoutParams2.rightMargin = i14;
                    z3 = true;
                }
                if (i12 <= 0 || this.mStatusGuard != null) {
                    View view = this.mStatusGuard;
                    if (!(view == null || ((marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams()).height == (i10 = marginLayoutParams2.topMargin) && marginLayoutParams.leftMargin == i7 && marginLayoutParams.rightMargin == i8))) {
                        marginLayoutParams.height = i10;
                        marginLayoutParams.leftMargin = i7;
                        marginLayoutParams.rightMargin = i8;
                        this.mStatusGuard.setLayoutParams(marginLayoutParams);
                    }
                } else {
                    View view2 = new View(this.mContext);
                    this.mStatusGuard = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams2.topMargin, 51);
                    layoutParams.leftMargin = i7;
                    layoutParams.rightMargin = i8;
                    this.mSubDecor.addView(this.mStatusGuard, -1, layoutParams);
                }
                View view3 = this.mStatusGuard;
                if (view3 == null) {
                    z7 = false;
                }
                if (z7 && view3.getVisibility() != 0) {
                    updateStatusGuardColor(this.mStatusGuard);
                }
                if (!this.mOverlayActionMode && z7 && !this.mIsIgnoreRemoveSystemTopInset) {
                    i2 = 0;
                }
                View findViewById = findViewById(16908290);
                if (findViewById instanceof ContentFrameLayout) {
                    if (findViewById.getPaddingTop() != 0) {
                        marginLayoutParams2.topMargin = 0;
                    }
                    if (findViewById.getPaddingRight() != 0) {
                        marginLayoutParams2.rightMargin = 0;
                    }
                    if (findViewById.getPaddingLeft() != 0) {
                        marginLayoutParams2.leftMargin = 0;
                    }
                }
                if (isFloatingToolbarActionModeView()) {
                    marginLayoutParams2.topMargin = 0;
                    marginLayoutParams2.rightMargin = 0;
                    marginLayoutParams2.leftMargin = 0;
                }
                z = z7;
                z7 = z3;
            } else if (marginLayoutParams2.topMargin != 0) {
                marginLayoutParams2.topMargin = 0;
                z = false;
            } else {
                z = false;
                z7 = false;
            }
            if (z7) {
                this.mActionModeView.setLayoutParams(marginLayoutParams2);
                View view4 = this.mStatusGuard;
                if (view4 != null) {
                    ViewGroup.LayoutParams layoutParams2 = view4.getLayoutParams();
                    if (layoutParams2.height != i2) {
                        layoutParams2.height = i2;
                        this.mStatusGuard.setLayoutParams(layoutParams2);
                    }
                }
            }
        }
        View view5 = this.mStatusGuard;
        if (view5 != null) {
            if (!z) {
                i11 = 8;
            }
            view5.setVisibility(i11);
        }
        return i2;
    }

    public AppCompatDelegateImpl(Dialog dialog, AppCompatCallback appCompatCallback) {
        this(dialog.getContext(), dialog.getWindow(), appCompatCallback, dialog);
    }

    private boolean applyApplicationSpecificConfig(boolean z, boolean z3) {
        if (this.mDestroyed) {
            return false;
        }
        int calculateNightMode = calculateNightMode();
        int mapNightMode = mapNightMode(this.mContext, calculateNightMode);
        LocaleListCompat calculateApplicationLocales = Build.VERSION.SDK_INT < 33 ? calculateApplicationLocales(this.mContext) : null;
        if (!z3 && calculateApplicationLocales != null) {
            calculateApplicationLocales = getConfigurationLocales(this.mContext.getResources().getConfiguration());
        }
        boolean updateAppConfiguration = updateAppConfiguration(mapNightMode, calculateApplicationLocales, z);
        if (calculateNightMode == 0) {
            getAutoTimeNightModeManager(this.mContext).setup();
        } else {
            AutoNightModeManager autoNightModeManager = this.mAutoTimeNightModeManager;
            if (autoNightModeManager != null) {
                autoNightModeManager.cleanup();
            }
        }
        if (calculateNightMode == 3) {
            getAutoBatteryNightModeManager(this.mContext).setup();
            return updateAppConfiguration;
        }
        AutoNightModeManager autoNightModeManager2 = this.mAutoBatteryNightModeManager;
        if (autoNightModeManager2 != null) {
            autoNightModeManager2.cleanup();
        }
        return updateAppConfiguration;
    }

    public void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        DecorContentParent decorContentParent;
        if (!z || panelFeatureState.featureId != 0 || (decorContentParent = this.mDecorContentParent) == null || !decorContentParent.isOverflowMenuShowing()) {
            WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
            if (!(windowManager == null || !panelFeatureState.isOpen || (viewGroup = panelFeatureState.decorView) == null)) {
                if (viewGroup.isAttachedToWindow()) {
                    windowManager.removeView(panelFeatureState.decorView);
                }
                if (z) {
                    callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, (Menu) null);
                }
            }
            panelFeatureState.isPrepared = false;
            panelFeatureState.isHandled = false;
            panelFeatureState.isOpen = false;
            panelFeatureState.shownPanelView = null;
            panelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel == panelFeatureState) {
                this.mPreparedPanel = null;
            }
            if (panelFeatureState.featureId == 0) {
                updateBackInvokedCallbackState();
                return;
            }
            return;
        }
        checkCloseActionMenu(panelFeatureState.menu);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        r4 = sLocalNightModes;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private AppCompatDelegateImpl(android.content.Context r4, android.view.Window r5, androidx.appcompat.app.AppCompatCallback r6, java.lang.Object r7) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 0
            r3.mFadeAnim = r0
            r1 = 1
            r3.mHandleNativeActionModes = r1
            r1 = -100
            r3.mLocalNightMode = r1
            androidx.appcompat.app.AppCompatDelegateImpl$2 r2 = new androidx.appcompat.app.AppCompatDelegateImpl$2
            r2.<init>()
            r3.mInvalidatePanelMenuRunnable = r2
            r2 = 0
            r3.mIsIgnoreRemoveSystemTopInset = r2
            r3.mActionBarTargetView = r0
            r3.mContext = r4
            r3.mAppCompatCallback = r6
            r3.mHost = r7
            int r4 = r3.mLocalNightMode
            if (r4 != r1) goto L_0x0037
            boolean r4 = r7 instanceof android.app.Dialog
            if (r4 == 0) goto L_0x0037
            androidx.appcompat.app.AppCompatActivity r4 = r3.tryUnwrapContext()
            if (r4 == 0) goto L_0x0037
            androidx.appcompat.app.AppCompatDelegate r4 = r4.getDelegate()
            int r4 = r4.getLocalNightMode()
            r3.mLocalNightMode = r4
        L_0x0037:
            int r4 = r3.mLocalNightMode
            if (r4 != r1) goto L_0x005e
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.Integer> r4 = sLocalNightModes
            java.lang.Class r6 = r7.getClass()
            java.lang.String r6 = r6.getName()
            java.lang.Object r6 = r4.get(r6)
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 == 0) goto L_0x005e
            int r6 = r6.intValue()
            r3.mLocalNightMode = r6
            java.lang.Class r6 = r7.getClass()
            java.lang.String r6 = r6.getName()
            r4.remove(r6)
        L_0x005e:
            if (r5 == 0) goto L_0x0063
            r3.attachToWindow(r5)
        L_0x0063:
            androidx.appcompat.widget.AppCompatDrawableManager.preload()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.<init>(android.content.Context, android.view.Window, androidx.appcompat.app.AppCompatCallback, java.lang.Object):void");
    }

    public void setContentView(int i2) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i2, viewGroup);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onSubDecorInstalled(ViewGroup viewGroup) {
    }
}
