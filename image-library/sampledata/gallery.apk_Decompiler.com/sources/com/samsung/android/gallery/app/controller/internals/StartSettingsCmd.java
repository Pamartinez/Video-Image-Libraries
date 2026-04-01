package com.samsung.android.gallery.app.controller.internals;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSettingsCmd extends BaseCommand {
    private void startPopoverSettings(Intent intent, Object... objArr) {
        PopoverUtils.Anchor anchor;
        if (objArr != null && objArr.length > 0) {
            String str = objArr[0];
            if (str instanceof String) {
                intent.putExtra("position_guide_key", str);
                if (objArr.length > 1) {
                    PopoverUtils.Anchor anchor2 = objArr[1];
                    if (anchor2 instanceof PopoverUtils.Anchor) {
                        anchor = anchor2;
                        PopoverUtils.startActivity(getContext(), intent, anchor);
                    }
                }
            } else if (str instanceof PopoverUtils.Anchor) {
                anchor = (PopoverUtils.Anchor) str;
                PopoverUtils.startActivity(getContext(), intent, anchor);
            }
        }
        anchor = null;
        PopoverUtils.startActivity(getContext(), intent, anchor);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SETTINGS.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.SettingActivity");
        intent.addFlags(PropertyOptions.DELETE_EXISTING);
        try {
            startPopoverSettings(intent, objArr);
        } catch (ActivityNotFoundException unused) {
            showToast(getActivity().getResources().getString(R.string.can_not_access_function));
            Log.e(this.TAG, "SettingActivity not found");
        }
    }
}
