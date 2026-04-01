package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import A4.C0381p;
import D6.a;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.IMergeCreatureDialogView;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerAdapter;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurParams;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureAdapter<V extends IMergeCreatureDialogView> extends CreaturePickerAdapter<V> {
    private final Comparator<MediaItem> mComparator = new a(29);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RoundedCornerViewHolder extends ImageTitleViewHolder {
        private View mTitleArea;

        public RoundedCornerViewHolder(View view, int i2) {
            super(view, i2);
            setThumbnailShape(1, (float) view.getResources().getDimensionPixelOffset(R.dimen.merge_creature_dialog_thumb_radius));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bindImage$0() {
            int width = this.mImageView.getWidth();
            int height = this.mImageView.getHeight();
            if (width > 0 && height > 0) {
                this.mImageView.setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumCover(new BlurParams.Builder(width, height).setPercent(0.5f).setPivotPercent(0.25f).build()));
            }
        }

        public void bind(MediaItem mediaItem) {
            int i2;
            super.bind(mediaItem);
            View view = this.mTitleArea;
            if (TextUtils.isEmpty(getTitleText(mediaItem))) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            ViewUtils.setVisibility(view, i2);
        }

        public void bindImage(Bitmap bitmap) {
            super.bindImage(bitmap);
            if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || Build.VERSION.SDK_INT < 31) {
                return;
            }
            if (ViewUtils.isVisible(this.mTitleArea)) {
                this.mImageView.post(new a(this));
            } else {
                this.mImageView.setRenderEffect((RenderEffect) null);
            }
        }

        public void bindView(View view) {
            super.bindView(view);
            this.mTitleArea = view.findViewById(R.id.title_area);
        }
    }

    public MergeCreatureAdapter(V v) {
        super(v);
    }

    private boolean isHeaderItem(MediaItem mediaItem, MediaItem mediaItem2) {
        return mediaItem.getSubCategory().equals(mediaItem2.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0() {
        ((IMergeCreatureDialogView) this.mView).onDestroyDialog();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Object lambda$load$2(com.samsung.android.gallery.support.threadpool.ThreadPool.JobContext r6) {
        /*
            r5 = this;
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r6 = r5.mList
            monitor-enter(r6)
            V r0 = r5.mView     // Catch:{ all -> 0x001f }
            com.samsung.android.gallery.app.ui.dialog.creature.merge.IMergeCreatureDialogView r0 = (com.samsung.android.gallery.app.ui.dialog.creature.merge.IMergeCreatureDialogView) r0     // Catch:{ all -> 0x001f }
            com.samsung.android.gallery.module.data.MediaItem r0 = r0.getHeaderItem()     // Catch:{ all -> 0x001f }
            r1 = 0
            if (r0 == 0) goto L_0x007e
            com.samsung.android.gallery.support.utils.Features r2 = com.samsung.android.gallery.support.utils.Features.SUPPORT_PET_CLUSTER     // Catch:{ all -> 0x001f }
            boolean r2 = com.samsung.android.gallery.support.utils.Features.isEnabled(r2)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0021
            boolean r2 = r0.isPet()     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0021
            java.lang.String r2 = "mp://Pets"
            goto L_0x0029
        L_0x001f:
            r5 = move-exception
            goto L_0x0080
        L_0x0021:
            boolean r2 = r0.isPeople()     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0069
            java.lang.String r2 = "mp://People"
        L_0x0029:
            r6.e r3 = new r6.e     // Catch:{ all -> 0x001f }
            r4 = 5
            r3.<init>(r4)     // Catch:{ all -> 0x001f }
            android.database.Cursor r2 = com.samsung.android.gallery.database.dal.DbCompat.query(r2, r3)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x005c
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0052 }
            if (r3 == 0) goto L_0x005c
        L_0x003b:
            com.samsung.android.gallery.module.data.MediaItem r3 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r2)     // Catch:{ all -> 0x0052 }
            boolean r4 = r5.isHeaderItem(r3, r0)     // Catch:{ all -> 0x0052 }
            if (r4 == 0) goto L_0x0046
            goto L_0x004b
        L_0x0046:
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r4 = r5.mList     // Catch:{ all -> 0x0052 }
            r4.add(r3)     // Catch:{ all -> 0x0052 }
        L_0x004b:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x0052 }
            if (r3 != 0) goto L_0x003b
            goto L_0x005c
        L_0x0052:
            r5 = move-exception
            r2.close()     // Catch:{ all -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ all -> 0x001f }
        L_0x005b:
            throw r5     // Catch:{ all -> 0x001f }
        L_0x005c:
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ all -> 0x001f }
        L_0x0061:
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r0 = r5.mList     // Catch:{ all -> 0x001f }
            java.util.Comparator<com.samsung.android.gallery.module.data.MediaItem> r5 = r5.mComparator     // Catch:{ all -> 0x001f }
            r0.sort(r5)     // Catch:{ all -> 0x001f }
            goto L_0x007e
        L_0x0069:
            java.lang.String r0 = "MergeCreatureAdapter"
            java.lang.String r2 = "load failed : mismatch creature type, dismiss dialog!"
            com.samsung.android.gallery.support.utils.Log.w(r0, r2)     // Catch:{ all -> 0x001f }
            o6.t r0 = new o6.t     // Catch:{ all -> 0x001f }
            r2 = 27
            r0.<init>(r2, r5)     // Catch:{ all -> 0x001f }
            r2 = 500(0x1f4, double:2.47E-321)
            com.samsung.android.gallery.support.utils.ThreadUtil.postOnUiThreadDelayed(r0, r2)     // Catch:{ all -> 0x001f }
            monitor-exit(r6)     // Catch:{ all -> 0x001f }
            return r1
        L_0x007e:
            monitor-exit(r6)     // Catch:{ all -> 0x001f }
            return r1
        L_0x0080:
            monitor-exit(r6)     // Catch:{ all -> 0x001f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureAdapter.lambda$load$2(com.samsung.android.gallery.support.threadpool.ThreadPool$JobContext):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$3(MediaItem mediaItem, MediaItem mediaItem2) {
        String title = mediaItem.getTitle();
        String title2 = mediaItem2.getTitle();
        int count = mediaItem.getCount();
        int count2 = mediaItem2.getCount();
        if (count == 0 || count2 == 0) {
            return title.compareToIgnoreCase(title2);
        }
        int compare = Integer.compare(count2, count);
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(title2)) {
            if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(title2)) {
                if (TextUtils.isEmpty(title)) {
                    return 1;
                }
                return -1;
            }
        } else if (compare == 0) {
            return title.compareToIgnoreCase(title2);
        }
        return compare;
    }

    public int getItemViewLayoutId() {
        return R.layout.recycler_item_merge_creature_layout;
    }

    public void load() {
        ThreadPool.getInstance().submit(new C0381p(14, this));
    }

    public ImageTitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new RoundedCornerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemViewLayoutId(), viewGroup, false), i2);
    }
}
