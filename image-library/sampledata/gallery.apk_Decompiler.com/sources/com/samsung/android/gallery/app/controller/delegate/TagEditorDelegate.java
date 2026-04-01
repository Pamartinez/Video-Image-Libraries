package com.samsung.android.gallery.app.controller.delegate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.mobileservice.social.buddy.request.TargetAppListRequest;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TagEditorDelegate {
    private static Intent createIntent(ArrayList<MediaItem> arrayList, String str) {
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.service.tagservice", "com.samsung.android.service.tagservice.ui.tageditor.TagEditorActivity");
        intent.putExtra(TargetAppListRequest.BundleKey.APP_NAME, AppResources.getString(R.string.app_name));
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<MediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().getTitle());
            }
        }
        intent.putStringArrayListExtra("selected_tags", arrayList2);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("recognition_img_uri", str);
        }
        intent.addFlags(1);
        intent.addFlags(67108864);
        return intent;
    }

    public static void handleOnTagEditorResult(Blackboard blackboard, int i2, Intent intent) {
        if (i2 != -1) {
            blackboard.post("data://user/fromTagEditor", (Object) null);
        } else {
            blackboard.post("data://user/fromTagEditor", new Object[]{intent.getStringArrayListExtra("result_selected_tags")});
        }
    }

    public static void startTagEditor(Activity activity, Bundle bundle, Blackboard blackboard) {
        try {
            String string = BundleWrapper.getString(bundle, "selected_tags", "");
            activity.startActivityForResult(createIntent((ArrayList) blackboard.pop(string), BundleWrapper.getString(bundle, "content_uri", "")), 790);
        } catch (ActivityNotFoundException unused) {
            Log.d("TagEditorDelegate", "Activity Not Found !!!");
        }
    }
}
