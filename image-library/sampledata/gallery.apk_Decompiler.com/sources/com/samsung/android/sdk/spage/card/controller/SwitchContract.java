package com.samsung.android.sdk.spage.card.controller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwitchContract {
    public static final String TYPE = "Switch";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Event {
        public static final String OFF = "SPAGE_ON_SWITCH_OFF";
        public static final String ON = "SPAGE_ON_SWITCH_ON";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Flag {
        public static final int DISABLE = 0;
        public static final int ENABLE = 1;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface State {
        public static final int OFF = 0;
        public static final int ON = 1;
    }
}
