package com.samsung.android.gallery.app.ui.list.picker.stories;

import Gb.a;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewAdapter;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPickerAdapter<V extends IStoriesPinchView> extends StoriesPinchViewAdapter<V> {
    public StoriesPickerAdapter(V v, String str, View view) {
        super(v, str, view);
        String str2;
        ArrayList<MediaItem> allData;
        if (PreferenceFeatures.OneUi6x.SUPPORT_PICKER_PRESELECTED) {
            LaunchIntent launchIntent = (LaunchIntent) this.mBlackBoard.read("data://launch_intent");
            if (launchIntent != null) {
                str2 = (String) launchIntent.getExtra("picker_preselected_sem_id", null);
            } else {
                str2 = null;
            }
            if (!TextUtils.isEmpty(str2)) {
                Set<Integer> set = (Set) Arrays.stream(str2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(18)).collect(Collectors.toSet());
                MediaData mediaData = ((IStoriesPinchView) this.mView).getMediaData((String) null);
                if (mediaData != null && (allData = mediaData.getAllData()) != null) {
                    List list = (List) allData.stream().map(new a(7)).collect(Collectors.toList());
                    for (Integer num : set) {
                        int intValue = num.intValue();
                        if (list.contains(num)) {
                            this.mClipBoard.add(Long.valueOf((long) intValue));
                        }
                    }
                }
            }
        }
    }
}
