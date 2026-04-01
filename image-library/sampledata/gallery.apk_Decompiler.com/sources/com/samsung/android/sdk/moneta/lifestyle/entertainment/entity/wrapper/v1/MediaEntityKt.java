package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaArtist;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.Music;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0002¨\u0006\u0007"}, d2 = {"toMusic", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Music;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;", "toVideo", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Video;", "toMediaArtist", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaArtist;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaEntityKt {
    public static final MediaArtist toMediaArtist(MediaEntity mediaEntity) {
        int i2;
        j.e(mediaEntity, "<this>");
        Bundle bundle = new Bundle();
        bundle.putInt("count", mediaEntity.getPreferences().getCount());
        MediaType.Companion companion = MediaType.Companion;
        MediaType mediaType = mediaEntity.getMediaType();
        if (mediaType != null) {
            i2 = mediaType.getValue();
        } else {
            i2 = 0;
        }
        return new MediaArtist(companion.fromValue(i2), mediaEntity.getArtist(), bundle);
    }

    public static final Music toMusic(MediaEntity mediaEntity) {
        j.e(mediaEntity, "<this>");
        Bundle bundle = new Bundle();
        bundle.putInt("count", mediaEntity.getPreferences().getCount());
        String title = mediaEntity.getTitle();
        String str = "";
        if (title == null) {
            title = str;
        }
        String artist = mediaEntity.getArtist();
        String album = mediaEntity.getAlbum();
        if (album != null) {
            str = album;
        }
        return new Music(title, artist, str, bundle);
    }

    public static final Video toVideo(MediaEntity mediaEntity) {
        j.e(mediaEntity, "<this>");
        Bundle bundle = new Bundle();
        bundle.putInt("count", mediaEntity.getPreferences().getCount());
        String title = mediaEntity.getTitle();
        if (title == null) {
            title = "";
        }
        return new Video(title, mediaEntity.getArtist(), bundle);
    }
}
