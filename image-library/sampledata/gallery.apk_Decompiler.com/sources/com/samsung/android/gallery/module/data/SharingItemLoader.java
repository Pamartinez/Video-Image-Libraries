package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import android.database.StaleDataException;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SharingItemLoader {
    public static MediaItem loadGroup(Cursor cursor) {
        MediaItem createSharingGroup;
        synchronized (cursor) {
            try {
                createSharingGroup = SharingMediaItemBuilder.createSharingGroup(cursor);
            } catch (StaleDataException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return createSharingGroup;
    }

    public static MediaItem loadGroupMember(Cursor cursor) {
        MediaItem createSharingGroupMember;
        synchronized (cursor) {
            try {
                createSharingGroupMember = SharingMediaItemBuilder.createSharingGroupMember(cursor);
            } catch (StaleDataException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return createSharingGroupMember;
    }

    public static MediaItem loadInvitationListItem(Cursor cursor) {
        MediaItem createSharingInvitationListItem;
        synchronized (cursor) {
            try {
                createSharingInvitationListItem = SharingMediaItemBuilder.createSharingInvitationListItem(cursor);
            } catch (StaleDataException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return createSharingInvitationListItem;
    }

    public static MediaItem loadItem(Cursor cursor, int i2) {
        synchronized (cursor) {
            try {
                if (cursor.moveToPosition(i2)) {
                    MediaItem createSharingItem = SharingMediaItemBuilder.createSharingItem(cursor);
                    return createSharingItem;
                }
                Log.e("SharingItemLoader", "fail move : " + cursor + ", p=" + i2);
                return null;
            } catch (StaleDataException e) {
                throw e;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static MediaItem loadPrimitiveItem(Cursor cursor) {
        try {
            return SharingMediaItemBuilder.createPrimitive(cursor);
        } catch (StaleDataException e) {
            MediaItemLoader.debugStaleDataException(e, cursor);
            throw e;
        }
    }

    public static MediaItem loadSpace(Cursor cursor, int i2) {
        synchronized (cursor) {
            try {
                if (cursor.moveToPosition(i2)) {
                    MediaItem createSharingAlbum = SharingMediaItemBuilder.createSharingAlbum(cursor);
                    return createSharingAlbum;
                }
            } catch (StaleDataException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
            } catch (Throwable th) {
                throw th;
            }
        }
        return null;
    }

    public static MediaItem loadSpace(Cursor cursor) {
        MediaItem createSharingAlbum;
        synchronized (cursor) {
            try {
                createSharingAlbum = SharingMediaItemBuilder.createSharingAlbum(cursor);
            } catch (StaleDataException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return createSharingAlbum;
    }

    public static MediaItem loadItem(Cursor cursor) {
        MediaItem createSharingItem;
        synchronized (cursor) {
            try {
                createSharingItem = SharingMediaItemBuilder.createSharingItem(cursor);
            } catch (StaleDataException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return createSharingItem;
    }
}
