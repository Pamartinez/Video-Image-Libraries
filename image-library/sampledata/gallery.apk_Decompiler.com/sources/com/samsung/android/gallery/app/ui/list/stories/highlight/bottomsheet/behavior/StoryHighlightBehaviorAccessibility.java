package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior;

import A4.Y;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightBehaviorAccessibility {
    private int expandHalfwayActionId = -1;
    private Map<View, Integer> importantForAccessibilityMap;
    StoryHighlightBehavior mBehavior;
    private boolean updateImportantForAccessibilityOnSiblings = false;

    public <V extends View> StoryHighlightBehaviorAccessibility(StoryHighlightBehavior storyHighlightBehavior) {
        this.mBehavior = storyHighlightBehavior;
    }

    private int addAccessibilityActionForHalfExpandedState(View view, int i2) {
        return ViewCompat.addAccessibilityAction(view, view.getResources().getString(i2), createAccessibilityViewCommandForState(6));
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int i2) {
        return new Y((Object) this, i2, 6);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createAccessibilityViewCommandForState$0(int i2, View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        this.mBehavior.setState(i2);
        return true;
    }

    private void replaceAccessibilityActionForState(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.replaceAccessibilityAction(view, accessibilityActionCompat, (CharSequence) null, createAccessibilityViewCommandForState(i2));
    }

    public View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i2));
            if (findScrollingChild != null) {
                return findScrollingChild;
            }
        }
        return null;
    }

    public void updateAccessibilityActions(View view) {
        if (view != null) {
            ViewCompat.removeAccessibilityAction(view, 524288);
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, MediaDefs.Meta.SEF.SEF_MIN_SIZE);
            int i2 = this.expandHalfwayActionId;
            if (i2 != -1) {
                ViewCompat.removeAccessibilityAction(view, i2);
            }
            if (!this.mBehavior.isFitToContents() && this.mBehavior.state != 6) {
                this.expandHalfwayActionId = addAccessibilityActionForHalfExpandedState(view, R.string.bottomsheet_action_expand_halfway);
            }
            if (this.mBehavior.state != 5) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            int i7 = this.mBehavior.state;
            if (i7 == 3) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 6);
            } else if (i7 == 6) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 5);
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    public void updateImportantForAccessibility(View view, boolean z) {
        Integer num;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                if (this.importantForAccessibilityMap != null) {
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = coordinatorLayout.getChildAt(i2);
                        if (childAt != view) {
                            if (z) {
                                this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                                if (this.updateImportantForAccessibilityOnSiblings) {
                                    ViewCompat.setImportantForAccessibility(childAt, 4);
                                }
                            } else if (this.updateImportantForAccessibilityOnSiblings && (num = this.importantForAccessibilityMap.get(childAt)) != null) {
                                ViewCompat.setImportantForAccessibility(childAt, num.intValue());
                            }
                        }
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = null;
                } else if (this.updateImportantForAccessibilityOnSiblings) {
                    view.sendAccessibilityEvent(8);
                }
            }
        }
    }
}
