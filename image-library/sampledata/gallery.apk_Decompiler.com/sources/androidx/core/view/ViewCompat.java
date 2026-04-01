package androidx.core.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContentInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R$id;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewCompat {
    private static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
    private static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new Object();
    private static final AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();
    private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AccessibilityViewProperty<T> {
        private final int mContentChangeType;
        private final int mFrameworkMinimumSdk;
        private final int mTagKey;
        private final Class<T> mType;

        public AccessibilityViewProperty(int i2, Class<T> cls, int i7) {
            this(i2, cls, 0, i7);
        }

        private boolean frameworkAvailable() {
            if (Build.VERSION.SDK_INT >= this.mFrameworkMinimumSdk) {
                return true;
            }
            return false;
        }

        public boolean booleanNullToFalseEquals(Boolean bool, Boolean bool2) {
            boolean z;
            boolean z3;
            if (bool == null || !bool.booleanValue()) {
                z = false;
            } else {
                z = true;
            }
            if (bool2 == null || !bool2.booleanValue()) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z == z3) {
                return true;
            }
            return false;
        }

        public abstract T frameworkGet(View view);

        public abstract void frameworkSet(View view, T t);

        public T get(View view) {
            if (frameworkAvailable()) {
                return frameworkGet(view);
            }
            T tag = view.getTag(this.mTagKey);
            if (this.mType.isInstance(tag)) {
                return tag;
            }
            return null;
        }

        public void set(View view, T t) {
            if (frameworkAvailable()) {
                frameworkSet(view, t);
            } else if (shouldUpdate(get(view), t)) {
                ViewCompat.ensureAccessibilityDelegateCompat(view);
                view.setTag(this.mTagKey, t);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, this.mContentChangeType);
            }
        }

        public abstract boolean shouldUpdate(T t, T t3);

        public AccessibilityViewProperty(int i2, Class<T> cls, int i7, int i8) {
            this.mTagKey = i2;
            this.mType = cls;
            this.mContentChangeType = i7;
            this.mFrameworkMinimumSdk = i8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api20Impl {
        public static WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        public static void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static WindowInsetsCompat computeSystemWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, Rect rect) {
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                return WindowInsetsCompat.toWindowInsetsCompat(view.computeSystemWindowInsets(windowInsets, rect), view);
            }
            rect.setEmpty();
            return windowInsetsCompat;
        }

        public static ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        public static PorterDuff.Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        public static float getElevation(View view) {
            return view.getElevation();
        }

        public static String getTransitionName(View view) {
            return view.getTransitionName();
        }

        public static float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        public static float getZ(View view) {
            return view.getZ();
        }

        public static boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        public static void setElevation(View view, float f) {
            view.setElevation(f);
        }

        public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            AnonymousClass1 r0;
            if (onApplyWindowInsetsListener != null) {
                r0 = new View.OnApplyWindowInsetsListener() {
                    WindowInsetsCompat mLastInsets = null;

                    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                        this.mLastInsets = windowInsetsCompat;
                        return onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsetsCompat).toWindowInsets();
                    }
                };
            } else {
                r0 = null;
            }
            if (view.getTag(R$id.tag_compat_insets_dispatch) == null) {
                if (r0 != null) {
                    view.setOnApplyWindowInsetsListener(r0);
                } else {
                    view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback));
                }
            }
        }

        public static void setTransitionName(View view, String str) {
            view.setTransitionName(str);
        }

        public static void setTranslationZ(View view, float f) {
            view.setTranslationZ(f);
        }

        public static void setZ(View view, float f) {
            view.setZ(f);
        }

        public static void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static WindowInsetsCompat getRootWindowInsets(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets);
            windowInsetsCompat.setRootWindowInsets(windowInsetsCompat);
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
            return windowInsetsCompat;
        }

        public static void setScrollIndicators(View view, int i2, int i7) {
            view.setScrollIndicators(i2, i7);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static void setPointerIcon(View view, PointerIcon pointerIcon) {
            view.setPointerIcon(pointerIcon);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static int getImportantForAutofill(View view) {
            return view.getImportantForAutofill();
        }

        public static void setImportantForAutofill(View view, int i2) {
            view.setImportantForAutofill(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static CharSequence getAccessibilityPaneTitle(View view) {
            return view.getAccessibilityPaneTitle();
        }

        public static boolean isAccessibilityHeading(View view) {
            return view.isAccessibilityHeading();
        }

        public static boolean isScreenReaderFocusable(View view) {
            return view.isScreenReaderFocusable();
        }

        public static void setAccessibilityHeading(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        public static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        public static void setScreenReaderFocusable(View view, boolean z) {
            view.setScreenReaderFocusable(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static View.AccessibilityDelegate getAccessibilityDelegate(View view) {
            return view.getAccessibilityDelegate();
        }

        public static void saveAttributeDataForStyleable(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i7) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i2, i7);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static WindowInsets dispatchApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }

        public static CharSequence getStateDescription(View view) {
            return view.getStateDescription();
        }

        public static void setStateDescription(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api31Impl {
        public static String[] getReceiveContentMimeTypes(View view) {
            return view.getReceiveContentMimeTypes();
        }

        public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
            ContentInfo contentInfo = contentInfoCompat.toContentInfo();
            ContentInfo performReceiveContent = view.performReceiveContent(contentInfo);
            if (performReceiveContent == null) {
                return null;
            }
            if (performReceiveContent == contentInfo) {
                return contentInfoCompat;
            }
            return ContentInfoCompat.toContentInfoCompat(performReceiveContent);
        }
    }

    private static AccessibilityViewProperty<Boolean> accessibilityHeadingProperty() {
        return new AccessibilityViewProperty<Boolean>(R$id.tag_accessibility_heading, Boolean.class, 28) {
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(Api28Impl.isAccessibilityHeading(view));
            }

            public void frameworkSet(View view, Boolean bool) {
                Api28Impl.setAccessibilityHeading(view, bool.booleanValue());
            }

            public boolean shouldUpdate(Boolean bool, Boolean bool2) {
                return !booleanNullToFalseEquals(bool, bool2);
            }
        };
    }

    public static int addAccessibilityAction(View view, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
        int availableActionIdFromResources = getAvailableActionIdFromResources(view, charSequence);
        if (availableActionIdFromResources != -1) {
            addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(availableActionIdFromResources, charSequence, accessibilityViewCommand));
        }
        return availableActionIdFromResources;
    }

    @Deprecated
    public static ViewPropertyAnimatorCompat animate(View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap<>();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = sViewPropertyAnimatorMap.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = new ViewPropertyAnimatorCompat(view);
        sViewPropertyAnimatorMap.put(view, viewPropertyAnimatorCompat2);
        return viewPropertyAnimatorCompat2;
    }

    public static WindowInsetsCompat computeSystemWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, Rect rect) {
        return Api21Impl.computeSystemWindowInsets(view, windowInsetsCompat, rect);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets dispatchApplyWindowInsets = Api30Impl.dispatchApplyWindowInsets(view, windowInsets);
            if (!dispatchApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(dispatchApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        return false;
    }

    public static void ensureAccessibilityDelegateCompat(View view) {
        AccessibilityDelegateCompat accessibilityDelegate = getAccessibilityDelegate(view);
        if (accessibilityDelegate == null) {
            accessibilityDelegate = new AccessibilityDelegateCompat();
        }
        setAccessibilityDelegate(view, accessibilityDelegate);
    }

    @Deprecated
    public static int generateViewId() {
        return View.generateViewId();
    }

    public static AccessibilityDelegateCompat getAccessibilityDelegate(View view) {
        View.AccessibilityDelegate accessibilityDelegateInternal = getAccessibilityDelegateInternal(view);
        if (accessibilityDelegateInternal == null) {
            return null;
        }
        if (accessibilityDelegateInternal instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter) {
            return ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) accessibilityDelegateInternal).mCompat;
        }
        return new AccessibilityDelegateCompat(accessibilityDelegateInternal);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateInternal(View view) {
        return Api29Impl.getAccessibilityDelegate(view);
    }

    public static CharSequence getAccessibilityPaneTitle(View view) {
        return paneTitleProperty().get(view);
    }

    private static List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> getActionList(View view) {
        int i2 = R$id.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i2);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i2, arrayList2);
        return arrayList2;
    }

    private static int getAvailableActionIdFromResources(View view, CharSequence charSequence) {
        boolean z;
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(view);
        for (int i2 = 0; i2 < actionList.size(); i2++) {
            if (TextUtils.equals(charSequence, actionList.get(i2).getLabel())) {
                return actionList.get(i2).getId();
            }
        }
        int i7 = -1;
        int i8 = 0;
        while (true) {
            int[] iArr = ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            if (i8 >= iArr.length || i7 != -1) {
                return i7;
            }
            int i10 = iArr[i8];
            boolean z3 = true;
            for (int i11 = 0; i11 < actionList.size(); i11++) {
                if (actionList.get(i11).getId() != i10) {
                    z = true;
                } else {
                    z = false;
                }
                z3 &= z;
            }
            if (z3) {
                i7 = i10;
            }
            i8++;
        }
        return i7;
    }

    public static ColorStateList getBackgroundTintList(View view) {
        return Api21Impl.getBackgroundTintList(view);
    }

    public static PorterDuff.Mode getBackgroundTintMode(View view) {
        return Api21Impl.getBackgroundTintMode(view);
    }

    @Deprecated
    public static Display getDisplay(View view) {
        return view.getDisplay();
    }

    public static float getElevation(View view) {
        return Api21Impl.getElevation(view);
    }

    private static OnReceiveContentViewBehavior getFallback(View view) {
        if (view instanceof OnReceiveContentViewBehavior) {
            return (OnReceiveContentViewBehavior) view;
        }
        return NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    @Deprecated
    public static boolean getFitsSystemWindows(View view) {
        return view.getFitsSystemWindows();
    }

    @Deprecated
    public static int getImportantForAccessibility(View view) {
        return view.getImportantForAccessibility();
    }

    public static int getImportantForAutofill(View view) {
        return Api26Impl.getImportantForAutofill(view);
    }

    @Deprecated
    public static int getLayoutDirection(View view) {
        return view.getLayoutDirection();
    }

    @Deprecated
    public static int getMinimumHeight(View view) {
        return view.getMinimumHeight();
    }

    @Deprecated
    public static int getMinimumWidth(View view) {
        return view.getMinimumWidth();
    }

    public static String[] getOnReceiveContentMimeTypes(View view) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.getReceiveContentMimeTypes(view);
        }
        return (String[]) view.getTag(R$id.tag_on_receive_content_mime_types);
    }

    @Deprecated
    public static int getPaddingEnd(View view) {
        return view.getPaddingEnd();
    }

    @Deprecated
    public static int getPaddingStart(View view) {
        return view.getPaddingStart();
    }

    public static WindowInsetsCompat getRootWindowInsets(View view) {
        return Api23Impl.getRootWindowInsets(view);
    }

    public static CharSequence getStateDescription(View view) {
        return stateDescriptionProperty().get(view);
    }

    public static String getTransitionName(View view) {
        return Api21Impl.getTransitionName(view);
    }

    public static float getTranslationZ(View view) {
        return Api21Impl.getTranslationZ(view);
    }

    @Deprecated
    public static int getWindowSystemUiVisibility(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static float getZ(View view) {
        return Api21Impl.getZ(view);
    }

    public static boolean hasAccessibilityDelegate(View view) {
        if (getAccessibilityDelegateInternal(view) != null) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean hasOnClickListeners(View view) {
        return view.hasOnClickListeners();
    }

    @Deprecated
    public static boolean hasTransientState(View view) {
        return view.hasTransientState();
    }

    public static boolean isAccessibilityHeading(View view) {
        Boolean bool = accessibilityHeadingProperty().get(view);
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Deprecated
    public static boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }

    @Deprecated
    public static boolean isLaidOut(View view) {
        return view.isLaidOut();
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return Api21Impl.isNestedScrollingEnabled(view);
    }

    @Deprecated
    public static boolean isPaddingRelative(View view) {
        return view.isPaddingRelative();
    }

    public static boolean isScreenReaderFocusable(View view) {
        Boolean bool = screenReaderFocusableProperty().get(view);
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public static void notifyViewAccessibilityStateChangedIfNeeded(View view, int i2) {
        boolean z;
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            if (getAccessibilityPaneTitle(view) == null || !view.isShown() || view.getWindowVisibility() != 0) {
                z = false;
            } else {
                z = true;
            }
            int i7 = 32;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                if (!z) {
                    i7 = 2048;
                }
                obtain.setEventType(i7);
                obtain.setContentChangeTypes(i2);
                if (z) {
                    obtain.getText().add(getAccessibilityPaneTitle(view));
                    setImportantForAccessibilityIfNeeded(view);
                }
                view.sendAccessibilityEventUnchecked(obtain);
            } else if (i2 == 32) {
                AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(obtain2);
                obtain2.setEventType(32);
                obtain2.setContentChangeTypes(i2);
                obtain2.setSource(view);
                view.onPopulateAccessibilityEvent(obtain2);
                obtain2.getText().add(getAccessibilityPaneTitle(view));
                accessibilityManager.sendAccessibilityEvent(obtain2);
            } else if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                } catch (AbstractMethodError e) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e);
                }
            }
        }
    }

    public static void offsetLeftAndRight(View view, int i2) {
        view.offsetLeftAndRight(i2);
    }

    public static void offsetTopAndBottom(View view, int i2) {
        view.offsetTopAndBottom(i2);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets onApplyWindowInsets = Api20Impl.onApplyWindowInsets(view, windowInsets);
            if (!onApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(onApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    @Deprecated
    public static void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.unwrap());
    }

    private static AccessibilityViewProperty<CharSequence> paneTitleProperty() {
        return new AccessibilityViewProperty<CharSequence>(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28) {
            public CharSequence frameworkGet(View view) {
                return Api28Impl.getAccessibilityPaneTitle(view);
            }

            public void frameworkSet(View view, CharSequence charSequence) {
                Api28Impl.setAccessibilityPaneTitle(view, charSequence);
            }

            public boolean shouldUpdate(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.equals(charSequence, charSequence2);
            }
        };
    }

    @Deprecated
    public static boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        return view.performAccessibilityAction(i2, bundle);
    }

    public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + contentInfoCompat + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.performReceiveContent(view, contentInfoCompat);
        }
        OnReceiveContentListener onReceiveContentListener = (OnReceiveContentListener) view.getTag(R$id.tag_on_receive_content_listener);
        if (onReceiveContentListener == null) {
            return getFallback(view).onReceiveContent(contentInfoCompat);
        }
        ContentInfoCompat onReceiveContent = onReceiveContentListener.onReceiveContent(view, contentInfoCompat);
        if (onReceiveContent == null) {
            return null;
        }
        return getFallback(view).onReceiveContent(onReceiveContent);
    }

    @Deprecated
    public static void postInvalidateOnAnimation(View view) {
        view.postInvalidateOnAnimation();
    }

    @Deprecated
    public static void postOnAnimation(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    @Deprecated
    public static void postOnAnimationDelayed(View view, Runnable runnable, long j2) {
        view.postOnAnimationDelayed(runnable, j2);
    }

    public static void removeAccessibilityAction(View view, int i2) {
        removeActionWithId(i2, view);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    private static void removeActionWithId(int i2, View view) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(view);
        for (int i7 = 0; i7 < actionList.size(); i7++) {
            if (actionList.get(i7).getId() == i2) {
                actionList.remove(i7);
                return;
            }
        }
    }

    public static void replaceAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
        if (accessibilityViewCommand == null && charSequence == null) {
            removeAccessibilityAction(view, accessibilityActionCompat.getId());
        } else {
            addAccessibilityAction(view, accessibilityActionCompat.createReplacementAction(charSequence, accessibilityViewCommand));
        }
    }

    public static void requestApplyInsets(View view) {
        Api20Impl.requestApplyInsets(view);
    }

    public static void saveAttributeDataForStyleable(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i7) {
        Api29Impl.saveAttributeDataForStyleable(view, context, iArr, attributeSet, typedArray, i2, i7);
    }

    private static AccessibilityViewProperty<Boolean> screenReaderFocusableProperty() {
        return new AccessibilityViewProperty<Boolean>(R$id.tag_screen_reader_focusable, Boolean.class, 28) {
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(Api28Impl.isScreenReaderFocusable(view));
            }

            public void frameworkSet(View view, Boolean bool) {
                Api28Impl.setScreenReaderFocusable(view, bool.booleanValue());
            }

            public boolean shouldUpdate(Boolean bool, Boolean bool2) {
                return !booleanNullToFalseEquals(bool, bool2);
            }
        };
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        View.AccessibilityDelegate accessibilityDelegate;
        if (accessibilityDelegateCompat == null && (getAccessibilityDelegateInternal(view) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            accessibilityDelegateCompat = new AccessibilityDelegateCompat();
        }
        setImportantForAccessibilityIfNeeded(view);
        if (accessibilityDelegateCompat == null) {
            accessibilityDelegate = null;
        } else {
            accessibilityDelegate = accessibilityDelegateCompat.getBridge();
        }
        view.setAccessibilityDelegate(accessibilityDelegate);
    }

    public static void setAccessibilityHeading(View view, boolean z) {
        accessibilityHeadingProperty().set(view, Boolean.valueOf(z));
    }

    @Deprecated
    public static void setAccessibilityLiveRegion(View view, int i2) {
        view.setAccessibilityLiveRegion(i2);
    }

    public static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
        paneTitleProperty().set(view, charSequence);
        if (charSequence != null) {
            sAccessibilityPaneVisibilityManager.addAccessibilityPane(view);
        } else {
            sAccessibilityPaneVisibilityManager.removeAccessibilityPane(view);
        }
    }

    @Deprecated
    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        Api21Impl.setBackgroundTintList(view, colorStateList);
    }

    public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
        Api21Impl.setBackgroundTintMode(view, mode);
    }

    @Deprecated
    public static void setClipBounds(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static void setElevation(View view, float f) {
        Api21Impl.setElevation(view, f);
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    @Deprecated
    public static void setImportantForAccessibility(View view, int i2) {
        view.setImportantForAccessibility(i2);
    }

    private static void setImportantForAccessibilityIfNeeded(View view) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    public static void setImportantForAutofill(View view, int i2) {
        Api26Impl.setImportantForAutofill(view, i2);
    }

    @Deprecated
    public static void setLabelFor(View view, int i2) {
        view.setLabelFor(i2);
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        Api21Impl.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }

    @Deprecated
    public static void setPaddingRelative(View view, int i2, int i7, int i8, int i10) {
        view.setPaddingRelative(i2, i7, i8, i10);
    }

    public static void setPointerIcon(View view, PointerIconCompat pointerIconCompat) {
        Object obj;
        if (pointerIconCompat != null) {
            obj = pointerIconCompat.getPointerIcon();
        } else {
            obj = null;
        }
        Api24Impl.setPointerIcon(view, (PointerIcon) obj);
    }

    public static void setScrollIndicators(View view, int i2, int i7) {
        Api23Impl.setScrollIndicators(view, i2, i7);
    }

    public static void setStateDescription(View view, CharSequence charSequence) {
        stateDescriptionProperty().set(view, charSequence);
    }

    public static void setTransitionName(View view, String str) {
        Api21Impl.setTransitionName(view, str);
    }

    public static void setTranslationZ(View view, float f) {
        Api21Impl.setTranslationZ(view, f);
    }

    public static void setWindowInsetsAnimationCallback(View view, WindowInsetsAnimationCompat.Callback callback) {
        WindowInsetsAnimationCompat.setCallback(view, callback);
    }

    public static void setZ(View view, float f) {
        Api21Impl.setZ(view, f);
    }

    private static AccessibilityViewProperty<CharSequence> stateDescriptionProperty() {
        return new AccessibilityViewProperty<CharSequence>(R$id.tag_state_description, CharSequence.class, 64, 30) {
            public CharSequence frameworkGet(View view) {
                return Api30Impl.getStateDescription(view);
            }

            public void frameworkSet(View view, CharSequence charSequence) {
                Api30Impl.setStateDescription(view, charSequence);
            }

            public boolean shouldUpdate(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.equals(charSequence, charSequence2);
            }
        };
    }

    public static void stopNestedScroll(View view) {
        Api21Impl.stopNestedScroll(view);
    }

    public static void stopNestedScroll(View view, int i2) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(i2);
        } else if (i2 == 0) {
            stopNestedScroll(view);
        }
    }

    private static void addAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat) {
        ensureAccessibilityDelegateCompat(view);
        removeActionWithId(accessibilityActionCompat.getId(), view);
        getActionList(view).add(accessibilityActionCompat);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        private final WeakHashMap<View, Boolean> mPanesToVisible = new WeakHashMap<>();

        private void registerForLayoutCallback(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        private void unregisterForLayoutCallback(View view) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        public void addAccessibilityPane(View view) {
            boolean z;
            WeakHashMap<View, Boolean> weakHashMap = this.mPanesToVisible;
            if (!view.isShown() || view.getWindowVisibility() != 0) {
                z = false;
            } else {
                z = true;
            }
            weakHashMap.put(view, Boolean.valueOf(z));
            view.addOnAttachStateChangeListener(this);
            if (view.isAttachedToWindow()) {
                registerForLayoutCallback(view);
            }
        }

        public void onViewAttachedToWindow(View view) {
            registerForLayoutCallback(view);
        }

        public void removeAccessibilityPane(View view) {
            this.mPanesToVisible.remove(view);
            view.removeOnAttachStateChangeListener(this);
            unregisterForLayoutCallback(view);
        }

        public void onGlobalLayout() {
        }

        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ContentInfoCompat lambda$static$0(ContentInfoCompat contentInfoCompat) {
        return contentInfoCompat;
    }
}
