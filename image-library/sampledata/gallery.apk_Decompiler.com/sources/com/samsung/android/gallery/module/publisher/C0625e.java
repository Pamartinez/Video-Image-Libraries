package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* renamed from: com.samsung.android.gallery.module.publisher.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0625e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ QueryParams f;

    public /* synthetic */ C0625e(Cursor[] cursorArr, int i2, QueryParams queryParams) {
        this.d = i2;
        this.e = cursorArr;
        this.f = queryParams;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                AlbumDataPublisher.lambda$publishAlbumsData$16(this.e, this.f);
                return;
            case 1:
                AlbumDataPublisher.lambda$publishAlbumsData$19(this.e, this.f);
                return;
            case 2:
                AlbumDataPublisher.lambda$publishAlbumsData$20(this.e, this.f);
                return;
            case 3:
                AlbumDataPublisher.lambda$publishAlbumFileData$6(this.e, this.f);
                return;
            case 4:
                AlbumDataPublisher.lambda$publishAlbumFileData$8(this.e, this.f);
                return;
            case 5:
                AlbumDataPublisher.lambda$publishAlbumFileData$10(this.e, this.f);
                return;
            case 6:
                ListDataPublisher.lambda$publishTimelineData$3(this.e, this.f);
                return;
            case 7:
                ListDataPublisher.lambda$publishTimelineData$5(this.e, this.f);
                return;
            case 8:
                ListDataPublisher.lambda$publishTimelineFakeData$0(this.e, this.f);
                return;
            case 9:
                SearchDataPublisher.lambda$publishScreenShotFiles$18(this.e, 6, this.f);
                return;
            case 10:
                SearchDataPublisher.lambda$publishAllScreenShotFiles$20(this.e, this.f);
                return;
            case 11:
                SearchDataPublisher.lambda$publishAllScreenShotFiles$22(this.e, this.f);
                return;
            case 12:
                VirtualAlbumDataPublisher.lambda$publishRepairData$8(this.e, this.f);
                return;
            case 13:
                VirtualAlbumDataPublisher.lambda$publishRepairData$10(this.e, this.f);
                return;
            case 14:
                VirtualAlbumDataPublisher.lambda$publishVideoData$0(this.e, this.f);
                return;
            case 15:
                VirtualAlbumDataPublisher.lambda$publishVideoData$1(this.e, this.f);
                return;
            case 16:
                VirtualAlbumDataPublisher.lambda$publishRepairData$3(this.e, this.f);
                return;
            case 17:
                VirtualAlbumDataPublisher.lambda$publishRepairData$4(this.e, this.f);
                return;
            case 18:
                VirtualAlbumDataPublisher.lambda$publishRepairData$6(this.e, this.f);
                return;
            default:
                VirtualAlbumDataPublisher.lambda$publishRepairData$7(this.e, this.f);
                return;
        }
    }
}
