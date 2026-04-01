package com.samsung.android.sdk.moneta.lifestyle.entertainment.service;

import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\t\u0010\u0007J&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u000b\u001a\u00020\nH¦@¢\u0006\u0004\b\u0012\u0010\u0013J$\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u000b\u001a\u00020\nH¦@¢\u0006\u0004\b\u0015\u0010\u0013J$\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00160\u000f2\u0006\u0010\u000b\u001a\u00020\nH¦@¢\u0006\u0004\b\u0017\u0010\u0013J$\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00160\u000f2\u0006\u0010\u000b\u001a\u00020\nH¦@¢\u0006\u0004\b\u0018\u0010\u0013¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/service/EntertainmentService;", "", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "timeRange", "", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Music;", "getTopMusicList", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Video;", "getTopVideoList", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "mediaType", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaArtist;", "getTopArtists", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lqe/c;)Ljava/lang/Object;", "", "Ljava/time/DayOfWeek;", "", "rankDayOfWeek", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;", "rankTimeOfDay", "", "rankDayOfWeekWithAverage", "rankTimeOfDayWithAverage", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EntertainmentService {
    Object getTopArtists(MediaType mediaType, TimeRange timeRange, C1227c cVar);

    Object getTopMusicList(TimeRange timeRange, C1227c cVar);

    Object getTopVideoList(TimeRange timeRange, C1227c cVar);

    Object rankDayOfWeek(MediaType mediaType, C1227c cVar);

    Object rankDayOfWeekWithAverage(MediaType mediaType, C1227c cVar);

    Object rankTimeOfDay(MediaType mediaType, C1227c cVar);

    Object rankTimeOfDayWithAverage(MediaType mediaType, C1227c cVar);
}
