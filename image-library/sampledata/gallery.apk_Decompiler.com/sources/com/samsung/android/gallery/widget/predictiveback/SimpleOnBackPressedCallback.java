package com.samsung.android.gallery.widget.predictiveback;

import O3.l;
import Pb.a;
import Pb.d;
import androidx.activity.BackEventCompat;
import androidx.fragment.app.FragmentManager;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleOnBackPressedCallback implements FragmentManager.SeslOnBackPressedCallback {
    private OnBackCancelledListener mBackCancelledListener = null;
    private OnBackPressedListener mBackPressedListener = null;
    private OnBackStartedListener mBackStartedListener = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnBackCancelledListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnBackPressedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnBackProgressedListener {
        void onBackProgressed(BackEventCompat backEventCompat);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnBackStartedListener {
    }

    /* access modifiers changed from: private */
    public static void lambda$handleOnBackStarted$0(BackEventCompat backEventCompat, OnBackStartedListener onBackStartedListener) {
        ((a) onBackStartedListener).f2863a.lambda$new$0(backEventCompat);
    }

    public void handleOnBackCancelled() {
        Optional.ofNullable(this.mBackCancelledListener).ifPresent(new l(17));
    }

    public void handleOnBackPressed() {
        Optional.ofNullable(this.mBackPressedListener).ifPresent(new l(16));
    }

    public void handleOnBackProgressed(BackEventCompat backEventCompat) {
        Optional.ofNullable((Object) null).ifPresent(new d(backEventCompat, 0));
    }

    public void handleOnBackStarted(BackEventCompat backEventCompat) {
        Optional.ofNullable(this.mBackStartedListener).ifPresent(new d(backEventCompat, 1));
    }

    public SimpleOnBackPressedCallback setOnBackCancelledListener(OnBackCancelledListener onBackCancelledListener) {
        this.mBackCancelledListener = onBackCancelledListener;
        return this;
    }

    public SimpleOnBackPressedCallback setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.mBackPressedListener = onBackPressedListener;
        return this;
    }

    public SimpleOnBackPressedCallback setOnBackStartedListener(OnBackStartedListener onBackStartedListener) {
        this.mBackStartedListener = onBackStartedListener;
        return this;
    }
}
