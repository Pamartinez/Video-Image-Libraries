package com.samsung.android.sdk.moneta.lifestyle.common;

import java.util.List;
import kotlin.Metadata;
import ne.C1195m;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants;", "", "<init>", "()V", "Common", "HealthAndWellness", "Entertainment", "Social", "Notification", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentProviderConstants {
    public static final ContentProviderConstants INSTANCE = new ContentProviderConstants();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Common;", "", "<init>", "()V", "START_TIMESTAMP_PARAMETER_KEY", "", "END_TIMESTAMP_PARAMETER_KEY", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Common {
        public static final String END_TIMESTAMP_PARAMETER_KEY = "end_timestamp";
        public static final Common INSTANCE = new Common();
        public static final String START_TIMESTAMP_PARAMETER_KEY = "start_timestamp";

        private Common() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Entertainment;", "", "<init>", "()V", "Method", "ResultKey", "ParameterKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Entertainment {
        public static final Entertainment INSTANCE = new Entertainment();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Entertainment$Method;", "", "<init>", "()V", "GET_TOP_MUSIC_LIST", "", "GET_TOP_VIDEO_LIST", "GET_TOP_ARTISTS", "RANK_DAY_OF_WEEK", "RANK_TIME_OF_DAY", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Method {
            public static final String GET_TOP_ARTISTS = "getTopArtists";
            public static final String GET_TOP_MUSIC_LIST = "getTopMusicList";
            public static final String GET_TOP_VIDEO_LIST = "getTopVideoList";
            public static final Method INSTANCE = new Method();
            public static final String RANK_DAY_OF_WEEK = "rankEntertainmentDayOfWeek";
            public static final String RANK_TIME_OF_DAY = "rankEntertainmentTimeOfDay";

            private Method() {
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Entertainment$ParameterKey;", "", "<init>", "()V", "MEDIA_TYPE", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ParameterKey {
            public static final ParameterKey INSTANCE = new ParameterKey();
            public static final String MEDIA_TYPE = "media_type";

            private ParameterKey() {
            }
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Entertainment$ResultKey;", "", "<init>", "()V", "TOP_MUSIC_LIST", "", "TOP_VIDEO_LIST", "TOP_ARTISTS", "MORNING", "AFTERNOON", "EVENING", "LATE_NIGHT", "TIME_OF_DAY", "", "getTIME_OF_DAY", "()Ljava/util/List;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ResultKey {
            private static final String AFTERNOON = "AFTERNOON";
            private static final String EVENING = "EVENING";
            public static final ResultKey INSTANCE = new ResultKey();
            private static final String LATE_NIGHT = "LATE_NIGHT";
            private static final String MORNING = "MORNING";
            private static final List<String> TIME_OF_DAY = C1195m.q0(MORNING, AFTERNOON, EVENING, LATE_NIGHT);
            public static final String TOP_ARTISTS = "top_artists";
            public static final String TOP_MUSIC_LIST = "top_music_list";
            public static final String TOP_VIDEO_LIST = "top_video_list";

            private ResultKey() {
            }

            public final List<String> getTIME_OF_DAY() {
                return TIME_OF_DAY;
            }
        }

        private Entertainment() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$HealthAndWellness;", "", "<init>", "()V", "Method", "ResultKey", "ParameterKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HealthAndWellness {
        public static final HealthAndWellness INSTANCE = new HealthAndWellness();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$HealthAndWellness$Method;", "", "<init>", "()V", "GET_MOST_FREQUENT_EXERCISES", "", "GET_MOST_FREQUENT_EXERCISES_BY_DAY_OF_WEEK", "GET_MOST_FREQUENT_EXERCISES_BY_TIME_OF_DAY", "GET_FREQUENT_EXERCISES_BY_MONTH", "RANK_TIME_OF_DAY", "RANK_DAY_OF_WEEK", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Method {
            public static final String GET_FREQUENT_EXERCISES_BY_MONTH = "getFrequentExercisesByMonth";
            public static final String GET_MOST_FREQUENT_EXERCISES = "getMostFrequentExercises";
            public static final String GET_MOST_FREQUENT_EXERCISES_BY_DAY_OF_WEEK = "getMostFrequentExercisesByDayOfWeek";
            public static final String GET_MOST_FREQUENT_EXERCISES_BY_TIME_OF_DAY = "getMostFrequentExercisesByTimeOfDay";
            public static final Method INSTANCE = new Method();
            public static final String RANK_DAY_OF_WEEK = "rankHealthAndWellnessDayOfWeek";
            public static final String RANK_TIME_OF_DAY = "rankHealthAndWellnessTimeOfDay";

            private Method() {
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$HealthAndWellness$ParameterKey;", "", "<init>", "()V", "EXERCISE_GROUP_TYPE", "", "DAY_OF_WEEK", "TIME_OF_DAY", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ParameterKey {
            public static final String DAY_OF_WEEK = "day_of_week";
            public static final String EXERCISE_GROUP_TYPE = "exercise_group_type";
            public static final ParameterKey INSTANCE = new ParameterKey();
            public static final String TIME_OF_DAY = "time_of_day";

            private ParameterKey() {
            }
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$HealthAndWellness$ResultKey;", "", "<init>", "()V", "MOST_FREQUENT_EXERCISES", "", "MOST_FREQUENT_EXERCISES_BY_DAY_OF_WEEK", "MOST_FREQUENT_EXERCISES_BY_TIME_OF_DAY", "FREQUENT_EXERCISES_BY_MONTH", "MORNING", "AFTERNOON", "EVENING", "LATE_NIGHT", "TIME_OF_DAY", "", "getTIME_OF_DAY", "()Ljava/util/List;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ResultKey {
            private static final String AFTERNOON = "AFTERNOON";
            private static final String EVENING = "EVENING";
            public static final String FREQUENT_EXERCISES_BY_MONTH = "frequent_exercises_by_month";
            public static final ResultKey INSTANCE = new ResultKey();
            private static final String LATE_NIGHT = "LATE_NIGHT";
            private static final String MORNING = "MORNING";
            public static final String MOST_FREQUENT_EXERCISES = "most_frequent_exercises";
            public static final String MOST_FREQUENT_EXERCISES_BY_DAY_OF_WEEK = "most_frequent_exercises_by_day_of_week";
            public static final String MOST_FREQUENT_EXERCISES_BY_TIME_OF_DAY = "most_frequent_exercises_by_time_of_day";
            private static final List<String> TIME_OF_DAY = C1195m.q0(MORNING, AFTERNOON, EVENING, LATE_NIGHT);

            private ResultKey() {
            }

            public final List<String> getTIME_OF_DAY() {
                return TIME_OF_DAY;
            }
        }

        private HealthAndWellness() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Notification;", "", "<init>", "()V", "Method", "ParameterKey", "ResultKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Notification {
        public static final Notification INSTANCE = new Notification();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Notification$Method;", "", "<init>", "()V", "GET_AVERAGE_DAILY_NOTIFICATION_COUNT", "", "GET_AVERAGE_REMAINING_NOTIFICATION_COUNT", "GET_FASTEST_REACTION_NOTIFICATIONS", "GET_TOP_REACTION_NOTIFICATIONS", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Method {
            public static final String GET_AVERAGE_DAILY_NOTIFICATION_COUNT = "getAverageDailyNotificationCount";
            public static final String GET_AVERAGE_REMAINING_NOTIFICATION_COUNT = "getAverageRemainingNotificationCount";
            public static final String GET_FASTEST_REACTION_NOTIFICATIONS = "getFastestReactionNotifications";
            public static final String GET_TOP_REACTION_NOTIFICATIONS = "getTopReactionNotifications";
            public static final Method INSTANCE = new Method();

            private Method() {
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Notification$ParameterKey;", "", "<init>", "()V", "PACKAGE_NAME", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ParameterKey {
            public static final ParameterKey INSTANCE = new ParameterKey();
            public static final String PACKAGE_NAME = "package_name";

            private ParameterKey() {
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Notification$ResultKey;", "", "<init>", "()V", "DAILY_NOTIFICATION_COUNT", "", "REMAINING_NOTIFICATION_COUNT", "FASTEST_REACTION_NOTIFICATION_KEY_LIST", "TOP_REACTION_NOTIFICATION_KEY_LIST", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ResultKey {
            public static final String DAILY_NOTIFICATION_COUNT = "daily_notification_count";
            public static final String FASTEST_REACTION_NOTIFICATION_KEY_LIST = "fastest_reaction_notification_key_list";
            public static final ResultKey INSTANCE = new ResultKey();
            public static final String REMAINING_NOTIFICATION_COUNT = "remaining_notification_count";
            public static final String TOP_REACTION_NOTIFICATION_KEY_LIST = "top_reaction_notification_key_list";

            private ResultKey() {
            }
        }

        private Notification() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Social;", "", "<init>", "()V", "Method", "ResultKey", "ParameterKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Social {
        public static final Social INSTANCE = new Social();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Social$Method;", "", "<init>", "()V", "GET_MOST_CONTACT_PERSON_LIST", "", "GET_MOST_CONTACT_PERSON_LIST_FROM_ALL_CHANNELS", "GET_FREQUENT_CONTACT_PERSON_LIST", "GET_FREQUENT_CONTACT_PERSON_LIST_FROM_ALL_CHANNELS", "GET_FREQUENT_CONTACT_PERSON_LIST_BY_PREFERENCE_LEVEL", "GET_UPCOMING_CONTACT_PERSON_LIST", "GET_RECENT_CONTACT_PERSON_LIST", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Method {
            public static final String GET_FREQUENT_CONTACT_PERSON_LIST = "getFrequentContactPersonList";
            public static final String GET_FREQUENT_CONTACT_PERSON_LIST_BY_PREFERENCE_LEVEL = "getFrequentContactPersonListByPreferenceLevel";
            public static final String GET_FREQUENT_CONTACT_PERSON_LIST_FROM_ALL_CHANNELS = "getFrequentContactPersonListFromAllChannels";
            public static final String GET_MOST_CONTACT_PERSON_LIST = "getMostContactPersonList";
            public static final String GET_MOST_CONTACT_PERSON_LIST_FROM_ALL_CHANNELS = "getMostContactPersonListFromAllChannels";
            public static final String GET_RECENT_CONTACT_PERSON_LIST = "getRecentContactPersonList";
            public static final String GET_UPCOMING_CONTACT_PERSON_LIST = "getUpcomingContactPersonList";
            public static final Method INSTANCE = new Method();

            private Method() {
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Social$ParameterKey;", "", "<init>", "()V", "CHANNEL", "", "PREFERENCE_LEVEL", "MY_PHONE_NUMBER", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ParameterKey {
            public static final String CHANNEL = "channel";
            public static final ParameterKey INSTANCE = new ParameterKey();
            public static final String MY_PHONE_NUMBER = "my_phone_number";
            public static final String PREFERENCE_LEVEL = "preference_level";

            private ParameterKey() {
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/ContentProviderConstants$Social$ResultKey;", "", "<init>", "()V", "PERSON_LIST", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ResultKey {
            public static final ResultKey INSTANCE = new ResultKey();
            public static final String PERSON_LIST = "person_list";

            private ResultKey() {
            }
        }

        private Social() {
        }
    }

    private ContentProviderConstants() {
    }
}
