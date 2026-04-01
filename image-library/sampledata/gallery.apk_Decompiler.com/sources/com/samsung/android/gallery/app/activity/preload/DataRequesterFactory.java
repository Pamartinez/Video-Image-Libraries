package com.samsung.android.gallery.app.activity.preload;

import com.samsung.android.gallery.app.activity.preload.abstraction.IDataRequester;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataRequesterFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LaunchType {
        NORMAL,
        PICKER
    }

    public IDataRequester create(LaunchType launchType, Blackboard blackboard) {
        if (launchType == LaunchType.NORMAL) {
            return new DataRequester(blackboard);
        }
        if (launchType == LaunchType.PICKER) {
            return new PickerDataRequester(blackboard);
        }
        throw new IllegalArgumentException("Unsupported LaunchType.");
    }
}
