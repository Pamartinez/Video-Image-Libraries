package com.samsung.android.gallery.widget.dragdrop.clipdata;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadow;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowCreator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.function.Function;
import qa.e;
import vb.C0713b;
import vb.C0714c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipDataManager {
    private static final boolean SUPPORT_ADD_REMOVE = SdkConfig.atLeast(SdkConfig.SEM.T_MR5);
    private static final ClipDataManager sInstance = new ClipDataManager();
    private final String TAG = getClass().getSimpleName();
    private ClipData mClipData;
    private ClipDataCreator mClipDataCreator;
    private ArrayList<ClipData.Item> mClipDataRef;
    private Runnable mDeferredUpdate;
    private final DragShadowCreator mDragShadowCreator = new DragShadowCreator();

    private ClipDataManager() {
    }

    private void addItemAndUpdate(View view, MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            Uri uri = ContentUri.getUri(mediaItem);
            ArrayList<ClipData.Item> arrayList = this.mClipDataRef;
            if (arrayList != null && uri != null && view != null) {
                if (arrayList.stream().anyMatch(new C0713b(uri, 0))) {
                    String str = this.TAG;
                    Log.d(str, "addItem - item already added " + uri);
                } else if (this.mClipDataRef.size() >= 500) {
                    Toast.makeText(view.getContext(), R$string.two_handed_drag_and_drop_can_not_move_more_than_500_items, 0).show();
                    Log.d(this.TAG, "addItem - max count reached.");
                } else {
                    String str2 = this.TAG;
                    Log.d(str2, "addItem : " + uri);
                    this.mClipDataRef.add(new ClipData.Item(uri));
                    ClipDataCreator clipDataCreator = this.mClipDataCreator;
                    if (clipDataCreator != null) {
                        clipDataCreator.addExtraDataToClipData(view.getContext(), this.mClipDataRef, mediaItem);
                    }
                    if (z) {
                        lambda$removeItemAndUpdate$4(view);
                    } else {
                        this.mDeferredUpdate = new C0714c(this, view, 0);
                    }
                }
            }
        }
    }

    public static ClipDataManager getInstance() {
        return sInstance;
    }

    private int getStartCount(int i2) {
        ArrayList<ClipData.Item> arrayList = this.mClipDataRef;
        if (arrayList != null) {
            long count = arrayList.stream().filter(new e(15)).filter(new e(17)).count();
            if (count > 0) {
                return (int) count;
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getStartCount$0(ClipData.Item item) {
        if (item.getUri() != null) {
            return true;
        }
        return false;
    }

    private void removeItemAndUpdate(View view, MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            Uri uri = ContentUri.getUri(mediaItem);
            if (this.mClipDataRef != null && uri != null && view != null) {
                String str = this.TAG;
                Log.d(str, "remove item : " + uri);
                this.mClipDataRef.removeIf(new C0713b(uri, 1));
                ClipDataCreator clipDataCreator = this.mClipDataCreator;
                if (clipDataCreator != null) {
                    clipDataCreator.removeExtraDataFromClipData(this.mClipDataRef, mediaItem);
                }
                if (z) {
                    lambda$removeItemAndUpdate$4(view);
                } else {
                    this.mDeferredUpdate = new C0714c(this, view, 1);
                }
            }
        }
    }

    private void updateBadgeImage(View view) {
        Bitmap updatedBitmap = this.mDragShadowCreator.getUpdatedBitmap(view.getContext(), this.mClipDataRef);
        if (updatedBitmap != null) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setImageBitmap(updatedBitmap);
            view.updateDragShadow(new DragShadow(imageView, view.getContext(), updatedBitmap));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateClipData */
    public void lambda$removeItemAndUpdate$4(View view) {
        ClipData clipData = this.mClipData;
        if (clipData == null) {
            return;
        }
        if (clipData.getItemCount() == 0) {
            view.cancelDragAndDrop();
            return;
        }
        updateBadgeImage(view);
        SeApiCompat.updateClipData(view, this.mClipData);
    }

    public void addRemoveItemAndUpdate(View view, MediaItem mediaItem, boolean z) {
        if (SUPPORT_ADD_REMOVE) {
            if (z) {
                addItemAndUpdate(view, mediaItem, true);
            } else {
                removeItemAndUpdate(view, mediaItem, true);
            }
        }
    }

    public void addRemoveItemAndUpdateLater(View view, MediaItem mediaItem, boolean z) {
        if (SUPPORT_ADD_REMOVE) {
            if (z) {
                addItemAndUpdate(view, mediaItem, false);
            } else {
                removeItemAndUpdate(view, mediaItem, false);
            }
        }
    }

    public void clear() {
        this.mClipDataRef = null;
        this.mDeferredUpdate = null;
        this.mClipDataCreator = null;
    }

    public ClipData getClipData(Context context, ClipDataCreator clipDataCreator) {
        this.mClipDataCreator = clipDataCreator;
        ClipData clipData = clipDataCreator.get(context);
        this.mClipData = clipData;
        this.mClipDataRef = SeApiCompat.getClipDataItems(clipData);
        return this.mClipData;
    }

    public ClipData getClipDataForAlbum(Context context, ClipDataCreator clipDataCreator) {
        return clipDataCreator.get(context);
    }

    public ClipData getClipDataForViewer(Context context, MediaItem[] mediaItemArr, boolean z) {
        ClipDataCreator clipDataCreator;
        if (z) {
            clipDataCreator = new ClipDataCreatorDexImpl(mediaItemArr);
        } else {
            clipDataCreator = new ClipDataCreatorImpl(mediaItemArr);
        }
        return clipDataCreator.get(context);
    }

    public Bitmap getDragStartBitmap(ListViewHolder listViewHolder, int i2) {
        return this.mDragShadowCreator.getDragStartBitmap(this.mClipData, listViewHolder, getStartCount(i2));
    }

    public void onSelectAll(View view, Function<Integer, MediaItem> function, int i2) {
        if (SUPPORT_ADD_REMOVE) {
            int i7 = 0;
            while (true) {
                if (i7 >= i2) {
                    break;
                }
                addItemAndUpdate(view, function.apply(Integer.valueOf(i7)), false);
                ArrayList<ClipData.Item> arrayList = this.mClipDataRef;
                if (arrayList != null && arrayList.size() >= 500) {
                    Toast.makeText(view.getContext(), R$string.two_handed_drag_and_drop_can_not_move_more_than_500_items, 0).show();
                    break;
                }
                i7++;
            }
            runDeferredUpdate();
        }
    }

    public void onUnselectAll(View view, Function<Integer, MediaItem> function, int i2) {
        if (SUPPORT_ADD_REMOVE) {
            for (int i7 = 0; i7 < i2; i7++) {
                removeItemAndUpdate(view, function.apply(Integer.valueOf(i7)), false);
                ArrayList<ClipData.Item> arrayList = this.mClipDataRef;
                if (arrayList != null && arrayList.isEmpty()) {
                    break;
                }
            }
            runDeferredUpdate();
        }
    }

    public void runDeferredUpdate() {
        Runnable runnable = this.mDeferredUpdate;
        if (runnable != null) {
            runnable.run();
            this.mDeferredUpdate = null;
        }
    }
}
