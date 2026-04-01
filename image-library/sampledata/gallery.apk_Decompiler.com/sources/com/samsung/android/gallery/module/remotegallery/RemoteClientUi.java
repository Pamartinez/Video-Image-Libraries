package com.samsung.android.gallery.module.remotegallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RemoteClientUi {
    public static void start(String str, Blackboard blackboard) {
        String build = new UriBuilder("location://albums/fileList").appendArg("id", 0).appendArg("type", 5).appendArg("shortcut_album", false).build();
        MediaItem mediaItem = new MediaItem();
        mediaItem.setDisplayName("remote://" + str);
        mediaItem.setAlbumID(0);
        mediaItem.setAlbumType(AlbumType.MyAlbum);
        blackboard.publish("data://albums/current", mediaItem);
        blackboard.post("command://MoveURL", build);
    }

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("app://com.sec.android.gallery3d/location/albums/fileList?id=0&shortcut_album=true&remoteIp=" + str));
        activity.startActivity(intent);
    }
}
