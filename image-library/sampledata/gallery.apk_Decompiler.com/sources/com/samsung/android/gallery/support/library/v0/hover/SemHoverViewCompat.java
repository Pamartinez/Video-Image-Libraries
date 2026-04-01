package com.samsung.android.gallery.support.library.v0.hover;

import N2.j;
import android.view.PointerIcon;
import android.view.View;
import com.samsung.android.gallery.support.library.abstraction.HoverViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemHoverViewCompat extends HoverViewCompat {
    public void dismissPopup(View view) {
        if (view != null) {
            try {
                view.semGetHoverPopup(false).dismiss();
            } catch (Exception e) {
                j.C(e, new StringBuilder("dismissPopup failed e="), "SemHoverViewCompat");
            }
        }
    }

    public void setPointerIcon(View view, int i2, PointerIcon pointerIcon) {
        if (view != null) {
            try {
                view.semSetPointerIcon(i2, pointerIcon);
            } catch (Exception e) {
                j.C(e, new StringBuilder("setPointerIcon failed e="), "SemHoverViewCompat");
            }
        }
    }

    public void setPopupNoneType(View view) {
        if (view != null) {
            try {
                view.semSetHoverPopupType(0);
            } catch (Exception e) {
                j.C(e, new StringBuilder("setPopupToolTipType failed e="), "SemHoverViewCompat");
            }
        }
    }

    public void showPopup(View view) {
        if (view != null) {
            try {
                view.semGetHoverPopup(false).show();
            } catch (Exception e) {
                j.C(e, new StringBuilder("showPopup failed e="), "SemHoverViewCompat");
            }
        }
    }
}
