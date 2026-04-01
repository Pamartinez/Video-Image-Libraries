package com.samsung.android.sdk.spage.card.controller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CheckboxContract {
    public static final String TYPE = "Checkbox";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Event {
        public static final String CHECKED = "SPAGE_ON_CHECKBOX_CHECKED";
        public static final String UNCHECKED = "SPAGE_ON_CHECKBOX_UNCHECKED";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Flag {
        public static final int DISABLE = 0;
        public static final int ENABLE = 1;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface State {
        public static final int CHECKED = 1;
        public static final int UNCHECKED = 0;
    }
}
