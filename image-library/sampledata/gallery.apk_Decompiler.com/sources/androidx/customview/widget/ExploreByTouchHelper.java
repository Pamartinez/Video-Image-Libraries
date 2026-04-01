package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.customview.widget.FocusStrategy;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>() {
        public void obtainBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
            accessibilityNodeInfoCompat.getBoundsInParent(rect);
        }
    };
    private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() {
        public AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, int i2) {
            return sparseArrayCompat.valueAt(i2);
        }

        public int size(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
            return sparseArrayCompat.size();
        }
    };
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    private final View mHost;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect = new int[2];
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MyNodeProvider extends AccessibilityNodeProviderCompat {
        public MyNodeProvider() {
        }

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i2) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i2));
        }

        public AccessibilityNodeInfoCompat findFocus(int i2) {
            int i7;
            if (i2 == 2) {
                i7 = ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId;
            } else {
                i7 = ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            }
            if (i7 == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(i7);
        }

        public boolean performAction(int i2, int i7, Bundle bundle) {
            return ExploreByTouchHelper.this.performAction(i2, i7, bundle);
        }
    }

    public ExploreByTouchHelper(View view) {
        if (view != null) {
            this.mHost = view;
            this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    private boolean clearAccessibilityFocus(int i2) {
        if (this.mAccessibilityFocusedVirtualViewId != i2) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(i2, 65536);
        return true;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        int i2 = this.mKeyboardFocusedVirtualViewId;
        if (i2 == Integer.MIN_VALUE || !onPerformActionForVirtualView(i2, 16, (Bundle) null)) {
            return false;
        }
        return true;
    }

    private AccessibilityEvent createEvent(int i2, int i7) {
        if (i2 != -1) {
            return createEventForChild(i2, i7);
        }
        return createEventForHost(i7);
    }

    private AccessibilityEvent createEventForChild(int i2, int i7) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i7);
        AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i2);
        obtain.getText().add(obtainAccessibilityNodeInfo.getText());
        obtain.setContentDescription(obtainAccessibilityNodeInfo.getContentDescription());
        obtain.setScrollable(obtainAccessibilityNodeInfo.isScrollable());
        obtain.setPassword(obtainAccessibilityNodeInfo.isPassword());
        obtain.setEnabled(obtainAccessibilityNodeInfo.isEnabled());
        obtain.setChecked(obtainAccessibilityNodeInfo.isChecked());
        onPopulateEventForVirtualView(i2, obtain);
        if (!obtain.getText().isEmpty() || obtain.getContentDescription() != null) {
            obtain.setClassName(obtainAccessibilityNodeInfo.getClassName());
            AccessibilityRecordCompat.setSource(obtain, this.mHost, i2);
            obtain.setPackageName(this.mHost.getContext().getPackageName());
            return obtain;
        }
        throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
    }

    private AccessibilityEvent createEventForHost(int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        this.mHost.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    private AccessibilityNodeInfoCompat createNodeForChild(int i2) {
        boolean z;
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName("android.view.View");
        Rect rect = INVALID_PARENT_BOUNDS;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        obtain.setParent(this.mHost);
        onPopulateNodeForVirtualView(i2, obtain);
        if (obtain.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.mTempParentRect);
        if (!this.mTempParentRect.equals(rect)) {
            int actions = obtain.getActions();
            if ((actions & 64) != 0) {
                throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            } else if ((actions & 128) == 0) {
                obtain.setPackageName(this.mHost.getContext().getPackageName());
                obtain.setSource(this.mHost, i2);
                if (this.mAccessibilityFocusedVirtualViewId == i2) {
                    obtain.setAccessibilityFocused(true);
                    obtain.addAction(128);
                } else {
                    obtain.setAccessibilityFocused(false);
                    obtain.addAction(64);
                }
                if (this.mKeyboardFocusedVirtualViewId == i2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    obtain.addAction(2);
                } else if (obtain.isFocusable()) {
                    obtain.addAction(1);
                }
                obtain.setFocused(z);
                this.mHost.getLocationOnScreen(this.mTempGlobalRect);
                obtain.getBoundsInScreen(this.mTempScreenRect);
                if (this.mTempScreenRect.equals(rect)) {
                    obtain.getBoundsInParent(this.mTempScreenRect);
                    if (obtain.mParentVirtualDescendantId != -1) {
                        AccessibilityNodeInfoCompat obtain2 = AccessibilityNodeInfoCompat.obtain();
                        for (int i7 = obtain.mParentVirtualDescendantId; i7 != -1; i7 = obtain2.mParentVirtualDescendantId) {
                            obtain2.setParent(this.mHost, -1);
                            obtain2.setBoundsInParent(INVALID_PARENT_BOUNDS);
                            onPopulateNodeForVirtualView(i7, obtain2);
                            obtain2.getBoundsInParent(this.mTempParentRect);
                            Rect rect2 = this.mTempScreenRect;
                            Rect rect3 = this.mTempParentRect;
                            rect2.offset(rect3.left, rect3.top);
                        }
                        obtain2.recycle();
                    }
                    this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                }
                if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                    this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                    if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                        obtain.setBoundsInScreen(this.mTempScreenRect);
                        if (isVisibleToUser(this.mTempScreenRect)) {
                            obtain.setVisibleToUser(true);
                        }
                    }
                }
                return obtain;
            } else {
                throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
        } else {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
    }

    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, obtain);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (obtain.getChildCount() <= 0 || arrayList.size() <= 0) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                obtain.addChild(this.mHost, ((Integer) arrayList.get(i2)).intValue());
            }
            return obtain;
        }
        throw new RuntimeException("Views cannot have both real and virtual children");
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes() {
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            sparseArrayCompat.put(((Integer) arrayList.get(i2)).intValue(), createNodeForChild(((Integer) arrayList.get(i2)).intValue()));
        }
        return sparseArrayCompat;
    }

    private void getBoundsInParent(int i2, Rect rect) {
        obtainAccessibilityNodeInfo(i2).getBoundsInParent(rect);
    }

    private static Rect guessPreviouslyFocusedRect(View view, int i2, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i2 == 17) {
            rect.set(width, 0, width, height);
            return rect;
        } else if (i2 == 33) {
            rect.set(0, height, width, height);
            return rect;
        } else if (i2 == 66) {
            rect.set(-1, 0, -1, height);
            return rect;
        } else if (i2 == 130) {
            rect.set(0, -1, width, -1);
            return rect;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
    }

    private boolean isVisibleToUser(Rect rect) {
        if (rect == null || rect.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent parent = this.mHost.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        if (parent != null) {
            return true;
        }
        return false;
    }

    private static int keyToDirection(int i2) {
        if (i2 == 19) {
            return 33;
        }
        if (i2 == 21) {
            return 17;
        }
        if (i2 != 22) {
            return 130;
        }
        return 66;
    }

    private boolean moveFocus(int i2, Rect rect) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2;
        SparseArrayCompat<AccessibilityNodeInfoCompat> allNodes = getAllNodes();
        int i7 = this.mKeyboardFocusedVirtualViewId;
        int i8 = Integer.MIN_VALUE;
        if (i7 == Integer.MIN_VALUE) {
            accessibilityNodeInfoCompat = null;
        } else {
            accessibilityNodeInfoCompat = allNodes.get(i7);
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = accessibilityNodeInfoCompat;
        boolean z = true;
        if (i2 == 1 || i2 == 2) {
            int i10 = i2;
            if (ViewCompat.getLayoutDirection(this.mHost) != 1) {
                z = false;
            }
            accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilityNodeInfoCompat3, i10, z, false);
        } else if (i2 == 17 || i2 == 33 || i2 == 66 || i2 == 130) {
            Rect rect2 = new Rect();
            int i11 = this.mKeyboardFocusedVirtualViewId;
            if (i11 != Integer.MIN_VALUE) {
                getBoundsInParent(i11, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                guessPreviouslyFocusedRect(this.mHost, i2, rect2);
            }
            accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilityNodeInfoCompat3, rect2, i2);
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        if (accessibilityNodeInfoCompat2 != null) {
            i8 = allNodes.keyAt(allNodes.indexOfValue(accessibilityNodeInfoCompat2));
        }
        return requestKeyboardFocusForVirtualView(i8);
    }

    private boolean performActionForChild(int i2, int i7, Bundle bundle) {
        if (i7 == 1) {
            return requestKeyboardFocusForVirtualView(i2);
        }
        if (i7 == 2) {
            return clearKeyboardFocusForVirtualView(i2);
        }
        if (i7 == 64) {
            return requestAccessibilityFocus(i2);
        }
        if (i7 != 128) {
            return onPerformActionForVirtualView(i2, i7, bundle);
        }
        return clearAccessibilityFocus(i2);
    }

    private boolean performActionForHost(int i2, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.mHost, i2, bundle);
    }

    private boolean requestAccessibilityFocus(int i2) {
        int i7;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled() || (i7 = this.mAccessibilityFocusedVirtualViewId) == i2) {
            return false;
        }
        if (i7 != Integer.MIN_VALUE) {
            clearAccessibilityFocus(i7);
        }
        this.mAccessibilityFocusedVirtualViewId = i2;
        this.mHost.invalidate();
        sendEventForVirtualView(i2, 32768);
        return true;
    }

    private void updateHoveredVirtualView(int i2) {
        int i7 = this.mHoveredVirtualViewId;
        if (i7 != i2) {
            this.mHoveredVirtualViewId = i2;
            sendEventForVirtualView(i2, 128);
            sendEventForVirtualView(i7, 256);
        }
    }

    public final boolean clearKeyboardFocusForVirtualView(int i2) {
        if (this.mKeyboardFocusedVirtualViewId != i2) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i2, false);
        sendEventForVirtualView(i2, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            if (action == 7 || action == 9) {
                int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
                updateHoveredVirtualView(virtualViewAt);
                if (virtualViewAt != Integer.MIN_VALUE) {
                    return true;
                }
            } else if (action != 10 || this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
                return false;
            } else {
                updateHoveredVirtualView(Integer.MIN_VALUE);
                return true;
            }
        }
        return false;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int i2 = 0;
        if (keyEvent.getAction() != 1) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                if (keyCode != 66) {
                    switch (keyCode) {
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                            if (keyEvent.hasNoModifiers()) {
                                int keyToDirection = keyToDirection(keyCode);
                                int repeatCount = keyEvent.getRepeatCount() + 1;
                                boolean z = false;
                                while (i2 < repeatCount && moveFocus(keyToDirection, (Rect) null)) {
                                    i2++;
                                    z = true;
                                }
                                return z;
                            }
                            break;
                        case 23:
                            break;
                    }
                }
                if (keyEvent.hasNoModifiers() && keyEvent.getRepeatCount() == 0) {
                    clickKeyboardFocusedVirtualView();
                    return true;
                }
            } else if (keyEvent.hasNoModifiers()) {
                return moveFocus(2, (Rect) null);
            } else {
                if (keyEvent.hasModifiers(1)) {
                    return moveFocus(1, (Rect) null);
                }
            }
        }
        return false;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    public abstract int getVirtualViewAt(float f, float f5);

    public abstract void getVisibleVirtualViews(List<Integer> list);

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i2, int i7) {
        ViewParent parent;
        if (i2 != Integer.MIN_VALUE && this.mManager.isEnabled() && (parent = this.mHost.getParent()) != null) {
            AccessibilityEvent createEvent = createEvent(i2, 2048);
            AccessibilityEventCompat.setContentChangeTypes(createEvent, i7);
            parent.requestSendAccessibilityEvent(this.mHost, createEvent);
        }
    }

    public AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i2) {
        if (i2 == -1) {
            return createNodeForHost();
        }
        return createNodeForChild(i2);
    }

    public final void onFocusChanged(boolean z, int i2, Rect rect) {
        int i7 = this.mKeyboardFocusedVirtualViewId;
        if (i7 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i7);
        }
        if (z) {
            moveFocus(i2, rect);
        }
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }

    public abstract boolean onPerformActionForVirtualView(int i2, int i7, Bundle bundle);

    public abstract void onPopulateNodeForVirtualView(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public boolean performAction(int i2, int i7, Bundle bundle) {
        seslNotifyPerformAction(i2, i7, bundle);
        if (i2 != -1) {
            return performActionForChild(i2, i7, bundle);
        }
        return performActionForHost(i7, bundle);
    }

    public final boolean requestKeyboardFocusForVirtualView(int i2) {
        int i7;
        if ((!this.mHost.isFocused() && !this.mHost.requestFocus()) || (i7 = this.mKeyboardFocusedVirtualViewId) == i2) {
            return false;
        }
        if (i7 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i7);
        }
        if (i2 == Integer.MIN_VALUE) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = i2;
        onVirtualViewKeyboardFocusChanged(i2, true);
        sendEventForVirtualView(i2, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i2, int i7) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        return parent.requestSendAccessibilityEvent(this.mHost, createEvent(i2, i7));
    }

    public void onPopulateEventForHost(AccessibilityEvent accessibilityEvent) {
    }

    public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public void onPopulateEventForVirtualView(int i2, AccessibilityEvent accessibilityEvent) {
    }

    public void onVirtualViewKeyboardFocusChanged(int i2, boolean z) {
    }
}
