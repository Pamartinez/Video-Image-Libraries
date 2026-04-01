package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PublisherFactory {
    private static SearchDataPublisher createSearchDataPublisher(Blackboard blackboard) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) {
            return new SearchDataPublisherV2(blackboard);
        }
        return new SearchDataPublisher(blackboard);
    }

    private static StoriesDataPublisher createStoriesDataPublisher(Blackboard blackboard) {
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            return new StoriesDataPublisherV2(blackboard);
        }
        return new StoriesDataPublisher(blackboard);
    }

    public static void subscribeGeneric(ArrayList<Subscriber> arrayList, Blackboard blackboard) {
        arrayList.add(new ListDataPublisher(blackboard));
        arrayList.add(new AlbumDataPublisher(blackboard));
        arrayList.add(new SharingsDataPublisher(blackboard));
        arrayList.add(createSearchDataPublisher(blackboard));
        arrayList.add(createStoriesDataPublisher(blackboard));
        arrayList.add(new BadgeDataPublisher(blackboard));
        arrayList.add(new VirtualAlbumDataPublisher(blackboard));
        arrayList.add(new MapDataPublisher(blackboard));
        arrayList.add(new BitmapDataPublisher(blackboard));
        arrayList.add(new DataChangeEventPublisher(blackboard));
        if (SdkConfig.range(SdkConfig.GED.R, SdkConfig.GED.T)) {
            arrayList.add(new LifeCycleReLoader(blackboard));
        }
    }
}
