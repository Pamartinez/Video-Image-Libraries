package androidx.preference;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreferenceRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
    final AccessibilityDelegateCompat mDefaultItemDelegate = super.getItemDelegate();
    final AccessibilityDelegateCompat mItemDelegate = new AccessibilityDelegateCompat() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Preference item;
            PreferenceRecyclerViewAccessibilityDelegate.this.mDefaultItemDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            int childAdapterPosition = PreferenceRecyclerViewAccessibilityDelegate.this.mRecyclerView.getChildAdapterPosition(view);
            RecyclerView.Adapter adapter = PreferenceRecyclerViewAccessibilityDelegate.this.mRecyclerView.getAdapter();
            if ((adapter instanceof PreferenceGroupAdapter) && (item = ((PreferenceGroupAdapter) adapter).getItem(childAdapterPosition)) != null) {
                item.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
            }
        }

        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            return PreferenceRecyclerViewAccessibilityDelegate.this.mDefaultItemDelegate.performAccessibilityAction(view, i2, bundle);
        }
    };
    final RecyclerView mRecyclerView;

    public PreferenceRecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        super(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }
}
