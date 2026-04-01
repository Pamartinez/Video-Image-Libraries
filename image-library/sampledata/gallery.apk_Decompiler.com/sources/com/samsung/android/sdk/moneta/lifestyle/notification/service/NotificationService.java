package com.samsung.android.sdk.moneta.lifestyle.notification.service;

import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/notification/service/NotificationService;", "", "getAverageDailyNotificationCount", "", "timeRange", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "getAverageRemainingNotificationCount", "getFastestReactionNotifications", "", "", "getTopReactionNotifications", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface NotificationService {
    int getAverageDailyNotificationCount(TimeRange timeRange);

    int getAverageRemainingNotificationCount(TimeRange timeRange);

    List<String> getFastestReactionNotifications(TimeRange timeRange);

    List<String> getTopReactionNotifications(TimeRange timeRange);
}
