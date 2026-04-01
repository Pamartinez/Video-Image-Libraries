package com.samsung.android.gallery.app.ui.dialog.switchable;

import android.widget.TextView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Confirm extends LayoutType {
    private TextView mDescription;

    private void bindDescription() {
        if (this.mDescription == null) {
            this.mDescription = (TextView) this.mLayout.findViewById(R.id.confirm_description);
        }
    }

    public void bindViewInternal() {
        bindDescription();
    }

    public int getLayoutId() {
        return R.id.switchable_confirm_layout;
    }

    public int getTypeInteger() {
        return 0;
    }

    public void initViews() {
        String string = getString("title");
        String string2 = getString("description");
        if (string != null && string2 == null) {
            string2 = string;
            string = null;
        }
        setTitle(string, true);
        initTextView(this.mDescription, string2);
    }
}
