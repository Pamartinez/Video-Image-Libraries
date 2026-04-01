package com.samsung.android.gallery.app.ui.list.search.editcreature;

import A4.Q;
import Ba.g;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.module.creature.CreatureNameDataLoaderFactory;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import o6.p;
import o6.t;
import q5.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RegisteredCreatureAdapter extends GalleryListAdapter<NameViewHolder> {
    private final ArrayList<CreatureNameData> mRegisteredCreature = new ArrayList<>();
    private final IEditCreatureNameView mView;

    public RegisteredCreatureAdapter(IEditCreatureNameView iEditCreatureNameView) {
        super(iEditCreatureNameView.getBlackboard());
        this.mView = iEditCreatureNameView;
    }

    /* access modifiers changed from: private */
    public void appendData(ArrayList<CreatureNameData> arrayList) {
        synchronized (this.mRegisteredCreature) {
            try {
                arrayList.removeIf(new C0464a(23));
                if (isMyProfile(arrayList)) {
                    this.mRegisteredCreature.addAll(0, arrayList);
                } else {
                    this.mRegisteredCreature.addAll(arrayList);
                }
                String str = this.TAG;
                Log.d(str, "appendData {" + arrayList.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mRegisteredCreature.size() + "}");
                ThreadUtil.postOnUiThread(new t(13, this));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private String getName(CreatureNameData creatureNameData) {
        if (creatureNameData.isMyProfile()) {
            return AppResources.getString(R.string.f2235me);
        }
        return creatureNameData.getName();
    }

    private boolean isMyProfile(ArrayList<CreatureNameData> arrayList) {
        if (arrayList.size() != 1 || !arrayList.get(0).isMyProfile()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(CreatureNameData creatureNameData, View view) {
        this.mView.onItemClicked(creatureNameData);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(CreatureNameData creatureNameData, NameViewHolder nameViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        String str = this.TAG;
        Log.d(str, "getView {" + creatureNameData + "} " + Logger.toSimpleString(bitmap));
        creatureNameData.setFaceBitmap(bitmap);
        nameViewHolder.bindImage(creatureNameData, bitmap);
    }

    public int getItemCount() {
        int size;
        synchronized (this.mRegisteredCreature) {
            size = this.mRegisteredCreature.size();
        }
        return size;
    }

    public void load(boolean z, List<Long> list, CreatureNameData.ContactType... contactTypeArr) {
        if (contactTypeArr == null || contactTypeArr.length == 0) {
            appendData(new ArrayList());
        } else {
            CreatureNameDataLoaderFactory.get(z, list).load(new p(4, this), contactTypeArr);
        }
    }

    public NameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new NameViewHolder(C0086a.d(viewGroup, R.layout.edit_name_list_view_holder, viewGroup, false));
    }

    public void onBindViewHolder(NameViewHolder nameViewHolder, int i2) {
        synchronized (this.mRegisteredCreature) {
            try {
                CreatureNameData creatureNameData = this.mRegisteredCreature.get(i2);
                nameViewHolder.bindItem(creatureNameData);
                nameViewHolder.setImage(creatureNameData);
                nameViewHolder.setName(getName(creatureNameData));
                nameViewHolder.itemView.setOnClickListener(new g(26, this, creatureNameData));
                if (creatureNameData.getFaceBitmap() == null && creatureNameData.hasValidPhoto()) {
                    ThumbnailInterface mediaItem = creatureNameData.getMediaItem();
                    ThumbnailLoader instance = ThumbnailLoader.getInstance();
                    ThumbKind thumbKind = ThumbKind.SMALL_NC_KIND;
                    Objects.requireNonNull(mediaItem);
                    instance.loadThumbnail(mediaItem, thumbKind, new i(mediaItem, 0), new Q((Object) this, (Object) creatureNameData, (Object) nameViewHolder, 24));
                }
                if (i2 == 0 && getItemCount() == 1) {
                    nameViewHolder.setRoundMode(15);
                } else if (i2 == 0) {
                    nameViewHolder.setRoundMode(3);
                } else if (i2 == getItemCount() - 1) {
                    nameViewHolder.setRoundMode(12);
                } else {
                    nameViewHolder.setRoundMode(0);
                }
                super.onBindViewHolder(nameViewHolder, i2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
