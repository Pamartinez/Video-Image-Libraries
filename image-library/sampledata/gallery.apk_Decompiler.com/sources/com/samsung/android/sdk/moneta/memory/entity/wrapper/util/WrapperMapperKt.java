package com.samsung.android.sdk.moneta.memory.entity.wrapper.util;

import Ke.v0;
import com.samsung.android.sdk.moneta.memory.entity.activity.Activity;
import com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.MovingActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.PayingActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent;
import com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledTravel;
import com.samsung.android.sdk.moneta.memory.entity.activity.SleepingActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.SpeakingOnPhoneActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.StayingActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.TakingPicturesActivity;
import com.samsung.android.sdk.moneta.memory.entity.activity.WatchingVideoActivity;
import com.samsung.android.sdk.moneta.memory.entity.content.CalendarEvent;
import com.samsung.android.sdk.moneta.memory.entity.content.CallLog;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.content.Document;
import com.samsung.android.sdk.moneta.memory.entity.content.FourWEvent;
import com.samsung.android.sdk.moneta.memory.entity.content.Media;
import com.samsung.android.sdk.moneta.memory.entity.content.MediaSession;
import com.samsung.android.sdk.moneta.memory.entity.content.MobileApplication;
import com.samsung.android.sdk.moneta.memory.entity.content.WebPage;
import com.samsung.android.sdk.moneta.memory.entity.context.TravelState;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.unknown.UnknownActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.unknown.UnknownContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.EatingActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.ExercisingActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.ListeningToMusicActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.MovingActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.PayingActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.ScheduledEventWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.SleepingActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.SpeakingOnPhoneActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.StayingActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.TakingPicturesActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity.WatchingVideoActivityWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.CalendarEventWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.CallLogWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.DocumentWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.FourWEventWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.MediaSessionWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.MediaWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.MobileApplicationWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.WebPageWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context.TravelStateWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.EatingActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.ExercisingActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.ListeningToMusicActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.MovingActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.PayingActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.ScheduledEventWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.ScheduledTravelWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.SleepingActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.SpeakingOnPhoneActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.StayingActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.TakingPicturesActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity.WatchingVideoActivityWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.CallLogWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.DocumentWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.FourWEventWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.MediaSessionWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.MediaWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.MobileApplicationWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.WebPageWrapperV2;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0000\u001a\u00020\u0004*\u00020\u0005H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0007\u001a\f\u0010\u0000\u001a\u00020\u0006*\u00020\u0007H\u0007\u001a\f\u0010\u0003\u001a\u00020\b*\u00020\u0007H\u0007\u001a\f\u0010\u0000\u001a\u00020\t*\u00020\nH\u0007\u001a\f\u0010\u0003\u001a\u00020\u000b*\u00020\nH\u0007\u001a\f\u0010\u0000\u001a\u00020\f*\u00020\rH\u0007\u001a\f\u0010\u0003\u001a\u00020\u000e*\u00020\rH\u0007\u001a\f\u0010\u0000\u001a\u00020\u000f*\u00020\u0010H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0011*\u00020\u0010H\u0007\u001a\f\u0010\u0000\u001a\u00020\u0012*\u00020\u0013H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0014*\u00020\u0013H\u0007\u001a\f\u0010\u0000\u001a\u00020\u0015*\u00020\u0016H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0017*\u00020\u0016H\u0007\u001a\n\u0010\u0000\u001a\u00020\u0018*\u00020\u0019¨\u0006\u001a"}, d2 = {"toWrapperV1", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "toWrapperV2", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathNodeWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/GraphPathNodeWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathEdgeWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/GraphPathEdgeWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/AddressWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/AddressWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/PersonWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/PlaceWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/EngramWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/EngramWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/TravelStateWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WrapperMapperKt {
    public static final /* synthetic */ ActivityWrapper toWrapperV1(Activity activity) {
        j.e(activity, "<this>");
        if (activity instanceof TakingPicturesActivity) {
            return TakingPicturesActivityWrapperV1.Companion.fromActivity((TakingPicturesActivity) activity);
        }
        if (activity instanceof EatingActivity) {
            return EatingActivityWrapperV1.Companion.fromActivity((EatingActivity) activity);
        }
        if (activity instanceof ExercisingActivity) {
            return ExercisingActivityWrapperV1.Companion.fromActivity((ExercisingActivity) activity);
        }
        if (activity instanceof ListeningToMusicActivity) {
            return ListeningToMusicActivityWrapperV1.Companion.fromActivity((ListeningToMusicActivity) activity);
        }
        if (activity instanceof MovingActivity) {
            return MovingActivityWrapperV1.Companion.fromActivity((MovingActivity) activity);
        }
        if (activity instanceof PayingActivity) {
            return PayingActivityWrapperV1.Companion.fromActivity((PayingActivity) activity);
        }
        if (activity instanceof SleepingActivity) {
            return SleepingActivityWrapperV1.Companion.fromActivity((SleepingActivity) activity);
        }
        if (activity instanceof SpeakingOnPhoneActivity) {
            return SpeakingOnPhoneActivityWrapperV1.Companion.fromActivity((SpeakingOnPhoneActivity) activity);
        }
        if (activity instanceof StayingActivity) {
            return StayingActivityWrapperV1.Companion.fromActivity((StayingActivity) activity);
        }
        if (activity instanceof WatchingVideoActivity) {
            return WatchingVideoActivityWrapperV1.Companion.fromActivity((WatchingVideoActivity) activity);
        }
        if (activity instanceof ScheduledEvent) {
            return ScheduledEventWrapperV1.Companion.fromActivity((ScheduledEvent) activity);
        }
        throw new v0(activity.getClass().getSimpleName().concat(" mapper is not implemented"), 2);
    }

    public static final /* synthetic */ ActivityWrapper toWrapperV2(Activity activity) {
        j.e(activity, "<this>");
        if (activity instanceof TakingPicturesActivity) {
            return TakingPicturesActivityWrapperV2.Companion.fromActivity((TakingPicturesActivity) activity);
        }
        if (activity instanceof EatingActivity) {
            return EatingActivityWrapperV2.Companion.fromActivity((EatingActivity) activity);
        }
        if (activity instanceof ExercisingActivity) {
            return ExercisingActivityWrapperV2.Companion.fromActivity((ExercisingActivity) activity);
        }
        if (activity instanceof ListeningToMusicActivity) {
            return ListeningToMusicActivityWrapperV2.Companion.fromActivity((ListeningToMusicActivity) activity);
        }
        if (activity instanceof MovingActivity) {
            return MovingActivityWrapperV2.Companion.fromActivity((MovingActivity) activity);
        }
        if (activity instanceof PayingActivity) {
            return PayingActivityWrapperV2.Companion.fromActivity((PayingActivity) activity);
        }
        if (activity instanceof SleepingActivity) {
            return SleepingActivityWrapperV2.Companion.fromActivity((SleepingActivity) activity);
        }
        if (activity instanceof SpeakingOnPhoneActivity) {
            return SpeakingOnPhoneActivityWrapperV2.Companion.fromActivity((SpeakingOnPhoneActivity) activity);
        }
        if (activity instanceof StayingActivity) {
            return StayingActivityWrapperV2.Companion.fromActivity((StayingActivity) activity);
        }
        if (activity instanceof WatchingVideoActivity) {
            return WatchingVideoActivityWrapperV2.Companion.fromActivity((WatchingVideoActivity) activity);
        }
        if (activity instanceof ScheduledTravel) {
            return ScheduledTravelWrapperV2.Companion.fromActivity((ScheduledTravel) activity);
        }
        if (activity instanceof ScheduledEvent) {
            return ScheduledEventWrapperV2.Companion.fromActivity((ScheduledEvent) activity);
        }
        return UnknownActivityWrapper.Companion.fromActivity(activity);
    }

    public static final /* synthetic */ ContentWrapper toWrapperV1(Content content) {
        j.e(content, "<this>");
        if (content instanceof CallLog) {
            return CallLogWrapperV1.Companion.fromContent((CallLog) content);
        }
        if (content instanceof Document) {
            return DocumentWrapperV1.Companion.fromContent((Document) content);
        }
        if (content instanceof Media) {
            return MediaWrapperV1.Companion.fromContent((Media) content);
        }
        if (content instanceof MediaSession) {
            return MediaSessionWrapperV1.Companion.fromContent((MediaSession) content);
        }
        if (content instanceof MobileApplication) {
            return MobileApplicationWrapperV1.Companion.fromContent((MobileApplication) content);
        }
        if (content instanceof WebPage) {
            return WebPageWrapperV1.Companion.fromContent((WebPage) content);
        }
        if (content instanceof CalendarEvent) {
            return CalendarEventWrapperV1.Companion.fromContent((CalendarEvent) content);
        }
        if (content instanceof FourWEvent) {
            return FourWEventWrapperV1.Companion.fromContent((FourWEvent) content);
        }
        throw new v0(content.getClass().getSimpleName().concat(" mapper is not implemented"), 2);
    }

    public static final /* synthetic */ ContentWrapper toWrapperV2(Content content) {
        j.e(content, "<this>");
        if (content instanceof CallLog) {
            return CallLogWrapperV2.Companion.fromContent((CallLog) content);
        }
        if (content instanceof Document) {
            return DocumentWrapperV2.Companion.fromContent((Document) content);
        }
        if (content instanceof Media) {
            return MediaWrapperV2.Companion.fromContent((Media) content);
        }
        if (content instanceof MediaSession) {
            return MediaSessionWrapperV2.Companion.fromContent((MediaSession) content);
        }
        if (content instanceof MobileApplication) {
            return MobileApplicationWrapperV2.Companion.fromContent((MobileApplication) content);
        }
        if (content instanceof WebPage) {
            return WebPageWrapperV2.Companion.fromContent((WebPage) content);
        }
        if (content instanceof FourWEvent) {
            return FourWEventWrapperV2.Companion.fromContent((FourWEvent) content);
        }
        return UnknownContentWrapper.Companion.fromContent(content);
    }

    public static final TravelStateWrapperV1 toWrapperV1(TravelState travelState) {
        j.e(travelState, "<this>");
        return TravelStateWrapperV1.Companion.fromTravelState(travelState);
    }
}
