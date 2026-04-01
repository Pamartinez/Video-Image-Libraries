package com.samsung.android.sdk.spage.card;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ControllerDataContract {

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaPlay {
        public static final String TYPE = "MediaPlay";

        @Deprecated
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface Event {
            public static final String NEXT = "SPAGE_ON_MEDIA_NEXT";
            public static final String PAUSE = "SPAGE_ON_MEDIA_PAUSE";
            public static final String PLAY = "SPAGE_ON_MEDIA_PLAY";
            public static final String PREV = "SPAGE_ON_MEDIA_PREV";
        }

        @Deprecated
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface Flag {
            public static final int DISABLE_NEXT_BUTTON = 1;
            public static final int DISABLE_PLAY_BUTTON = 2;
            public static final int DISABLE_PREV_BUTTON = 4;
        }

        @Deprecated
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface State {
            public static final int PAUSED = 0;
            public static final int PLAYING = 1;
        }
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Navigation {
        public static final String TYPE = "Navigation";

        @Deprecated
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface Event {
            public static final String NEXT = "SPAGE_ON_NAVI_NEXT";
            public static final String PREV = "SPAGE_ON_NAVI_PREV";
        }

        @Deprecated
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface State {
            public static final int HIDE_ALL = 0;
            public static final int SHOW_ALL = 3;
            public static final int SHOW_NEXT = 2;
            public static final int SHOW_PREV = 1;
        }
    }
}
