package com.samsung.android.gallery.database.dal.mp.helper;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.AlbumFilesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.AlbumsImpl;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.PeoplePetImpl;
import com.samsung.android.gallery.database.dal.mp.impl.PicturesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.VirtualAlbumsImpl;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpHelper extends BaseImpl {
    public MpHelper() {
        super(new QueryParams());
    }

    private VirtualAlbumsImpl newInstanceOfVirtualAlbums() {
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            this.mParams.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
        }
        return new VirtualAlbumsImpl(this.mParams);
    }

    public int getAlbumCount(MediaType mediaType) {
        return new AlbumsImpl(this.mParams).getAlbumCount(mediaType);
    }

    public int[] getAlbumVirtualFavoriteCount() {
        return newInstanceOfVirtualAlbums().getAlbumVirtualFavoriteCount();
    }

    public int[] getAlbumVirtualRecentCount() {
        return newInstanceOfVirtualAlbums().getAlbumVirtualRecentCount(this.mParams.minFileId);
    }

    public int[] getAlbumVirtualVideoCount() {
        return newInstanceOfVirtualAlbums().getAlbumVirtualVideoCount();
    }

    public int getHiddenPeopleCount() {
        return new PeoplePetImpl(this.mParams).getHiddenPeopleCount();
    }

    public int getHiddenPetsCount() {
        return new PeoplePetImpl(this.mParams).getHiddenPetCount();
    }

    public int getPeopleCount() {
        return new PeoplePetImpl(this.mParams).getPeopleCount();
    }

    public int getPeopleNoRelationshipCount() {
        return new PeoplePetImpl(this.mParams).getPeopleNoRelationshipCount();
    }

    public int getPetsCount() {
        return new PeoplePetImpl(this.mParams).getPetCount();
    }

    public int getPositionByAlbum(int[] iArr, long j2, long j3, long j8) {
        return new AlbumFilesImpl(this.mParams).getPositionByAlbum(iArr, j2, j3, j8);
    }

    public int[] getSearchClusterPicturesCount() {
        return new CategoriesImpl(this.mParams).getSearchClusterPicturesCount();
    }

    public int getTimelineFileCount() {
        return new PicturesImpl(this.mParams).getTimelineFileCount();
    }

    public int getVirtualRecentShareCount() {
        return newInstanceOfVirtualAlbums().getVirtualRecentShareCount();
    }

    public String tag() {
        return "MpHelper";
    }

    public MpHelper(QueryParams queryParams) {
        super(queryParams);
    }

    public int[] getAlbumCount() {
        return new AlbumsImpl(this.mParams).getAlbumCount();
    }
}
