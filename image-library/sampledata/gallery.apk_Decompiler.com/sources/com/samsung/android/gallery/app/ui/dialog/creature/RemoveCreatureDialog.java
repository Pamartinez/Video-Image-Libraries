package com.samsung.android.gallery.app.ui.dialog.creature;

import B8.e;
import Fa.C0558l;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import n4.C0491c;
import o4.a;
import o5.C0496a;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveCreatureDialog extends BaseDialog {
    private CreatureAdapter mAdapter;
    private final View.OnClickListener mOnClickListener = new C0496a(17, this);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreatureAdapter extends RecyclerView.Adapter<ViewHolderCreature> {
        private final String TAG = getClass().getSimpleName();
        private final ArrayList<MediaItem> mData;
        private final Consumer<MediaItem> mRemoveAllConsumer;
        private final ArrayList<MediaItem> mRemovedData;

        public CreatureAdapter(ArrayList<MediaItem> arrayList, Consumer<MediaItem> consumer) {
            ArrayList<MediaItem> arrayList2 = new ArrayList<>();
            this.mData = arrayList2;
            this.mRemovedData = new ArrayList<>();
            arrayList2.addAll(arrayList);
            this.mRemoveAllConsumer = consumer;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadOrDecode$1(ViewHolderCreature viewHolderCreature, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
            onBindImage(viewHolderCreature, bitmap);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onCreateViewHolder$0(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
            this.mRemovedData.add(mediaItem);
            MediaItem remove = this.mData.remove(i2);
            notifyItemRemoved(i2);
            if (this.mData.isEmpty()) {
                this.mRemoveAllConsumer.accept(remove);
            }
        }

        private void loadOrDecode(ViewHolderCreature viewHolderCreature, MediaItem mediaItem) {
            if (viewHolderCreature != null) {
                ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
                viewHolderCreature.setThumbKind(thumbKind);
                Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
                if (memCache != null) {
                    onBindImage(viewHolderCreature, memCache);
                    return;
                }
                ThumbnailLoader instance = ThumbnailLoader.getInstance();
                Objects.requireNonNull(mediaItem);
                instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new b(this, viewHolderCreature));
            }
        }

        private void onBindImage(ViewHolderCreature viewHolderCreature, Bitmap bitmap) {
            ThreadUtil.runOnUiThread(new c(viewHolderCreature, bitmap));
        }

        public int getItemCount() {
            return this.mData.size();
        }

        public ArrayList<MediaItem> getRemovedData() {
            return this.mRemovedData;
        }

        public ViewHolderCreature onCreateViewHolder(ViewGroup viewGroup, int i2) {
            ViewHolderCreature viewHolderCreature = new ViewHolderCreature(C0086a.d(viewGroup, R.layout.recycler_item_remove_people_dialog, viewGroup, false), i2);
            viewHolderCreature.setOnItemClickListener(new a(this));
            return viewHolderCreature;
        }

        public void onBindViewHolder(ViewHolderCreature viewHolderCreature, int i2) {
            MediaItem mediaItem = this.mData.get(i2);
            viewHolderCreature.bind(mediaItem);
            loadOrDecode(viewHolderCreature, mediaItem);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolderCreature extends ImageTitleViewHolder {
        public ViewHolderCreature(View view, int i2) {
            super(view, i2);
            setThumbnailShape(0, 0.0f);
        }

        public void bind(MediaItem mediaItem) {
            String str;
            super.bind(mediaItem);
            if (TextUtils.isEmpty(mediaItem.getTitle())) {
                str = "?";
            } else {
                str = mediaItem.getTitle();
            }
            this.mTitleText.setText(str);
            this.mTitleText.setVisibility(0);
        }
    }

    private View createBodyView(Context context) {
        View inflate = View.inflate(context, R.layout.remove_people_dialog_body_layout, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        CreatureAdapter creatureAdapter = new CreatureAdapter((ArrayList) getBlackboard().pop("data://details/creature", new ArrayList()), new C0491c(15, this, context));
        this.mAdapter = creatureAdapter;
        recyclerView.setAdapter(creatureAdapter);
        return inflate;
    }

    private View createTitleView(Context context) {
        int i2;
        View inflate = View.inflate(context, R.layout.remove_people_dialog_title_layout, (ViewGroup) null);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.RelatedPeopleAndPet)) {
            TextView textView = (TextView) inflate.findViewById(R.id.header_title);
            if (IdentityCreatureUtil.isPetRecognized()) {
                i2 = R.string.visual_search_category_people_and_pets;
            } else {
                i2 = R.string.people;
            }
            textView.setText(i2);
            FontUtils.resizeUpToLarge(context, textView, (float) context.getResources().getDimensionPixelSize(R.dimen.remove_creature_dialog_up_to_large_text_size));
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.save_btn);
        ViewUtils.setOnClickListener(textView2, this.mOnClickListener);
        FontUtils.resizeUpToLarge(context, textView2, (float) context.getResources().getDimensionPixelSize(R.dimen.remove_creature_dialog_up_to_large_text_size));
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBodyView$0(Context context, MediaItem mediaItem) {
        dismiss();
        showConfirmDialog(context, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4(View view) {
        removeAndPost();
        dismiss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeAndPost$3() {
        this.mAdapter.getRemovedData().forEach(new a(12, this));
        getBlackboard().post("data://user/dialog/RemoveCreature", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showConfirmDialog$2(DialogInterface dialogInterface, int i2) {
        removeAndPost();
        dialogInterface.dismiss();
    }

    private void removeAndPost() {
        SimpleThreadPool.getInstance().execute(new t(21, this));
    }

    /* access modifiers changed from: private */
    public void removeTo(MediaItem mediaItem) {
        if (mediaItem.isPeople()) {
            PeopleDataManager.removeTo(mediaItem, mediaItem.getSubCategory());
        } else {
            PetDataManager.removeTo(mediaItem, mediaItem.getSubCategory());
        }
    }

    private void showConfirmDialog(Context context, MediaItem mediaItem) {
        new AlertDialog.Builder(context).setTitle((int) R.string.remove_last_face_tag).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new Ba.a(5)).setPositiveButton((int) R.string.button_remove, (DialogInterface.OnClickListener) new C0558l(20, this)).show();
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            Log.e(this.TAG, "createDialog failed null context");
            return super.createDialog(bundle);
        }
        AlertDialog create = new AlertDialog.Builder(context).setCustomTitle(createTitleView(context)).setView(createBodyView(context)).create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public void onCancel(DialogInterface dialogInterface) {
    }
}
