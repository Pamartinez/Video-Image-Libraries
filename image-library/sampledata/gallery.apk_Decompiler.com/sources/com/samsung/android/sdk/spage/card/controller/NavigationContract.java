package com.samsung.android.sdk.spage.card.controller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NavigationContract {
    public static final String TYPE = "Navigation";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Event {
        public static final String NEXT = "SPAGE_ON_NAVI_NEXT";
        public static final String PREV = "SPAGE_ON_NAVI_PREV";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface State {
        public static final int HIDE_ALL = 0;
        public static final int SHOW_ALL = 3;
        public static final int SHOW_NEXT = 2;
        public static final int SHOW_PREV = 1;
    }
}
