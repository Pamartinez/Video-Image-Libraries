package com.samsung.android.gallery.app.ui.list.mtp.pictures;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.mtp.MtpImportCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpMenuHandler extends MenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_import:
            case R.id.action_import_selection:
                new MtpImportCmd().execute(eventContext, menuItem.getTitle());
                return true;
            default:
                return false;
        }
    }
}
