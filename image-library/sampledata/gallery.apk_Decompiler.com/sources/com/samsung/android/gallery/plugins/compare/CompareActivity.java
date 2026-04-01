package com.samsung.android.gallery.plugins.compare;

import C3.g;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.tag.TagEditor;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.trash.helper.TrashProgressListener;
import com.samsung.android.gallery.plugins.R$color;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$menu;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.plugins.compare.GridView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import qa.e;
import u4.C0518a;
import y5.a;
import ya.C0717a;
import ya.b;
import ya.c;
import ya.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CompareActivity extends AppCompatActivity {
    private ListAdapter mAdapter;
    private ImageButton mAddBtn;
    private HashMap<Long, Bitmap> mBitmaps;
    private final Blackboard mBlackboard;
    private GridView mGridView;
    ArrayList<MediaItem> mItems;
    /* access modifiers changed from: private */
    public LinearLayoutManager mLayoutManager;
    private ArrayList<PhotoViewHolder> mPhotoViewHolderList = new ArrayList<>();
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    ArrayList<MediaItem> mSelectedItems;
    private boolean mShareTouches;
    private int[] photoViewIds;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {
        int count;

        public ListAdapter(int i2) {
            this.count = i2;
        }

        /* access modifiers changed from: private */
        public void onClick(View view) {
            ViewHolder viewHolder = (ViewHolder) CompareActivity.this.mRecyclerView.findContainingViewHolder(view);
            if (viewHolder != null) {
                MediaItem mediaItem = CompareActivity.this.mItems.get(viewHolder.getLayoutPosition());
                if (CompareActivity.this.mSelectedItems.contains(mediaItem)) {
                    if (CompareActivity.this.mSelectedItems.size() > 2) {
                        CompareActivity.this.mSelectedItems.remove(mediaItem);
                    } else {
                        Utils.showToast(view.getContext(), "min 2");
                        return;
                    }
                } else if (CompareActivity.this.mSelectedItems.size() < 4) {
                    CompareActivity.this.mSelectedItems.add(mediaItem);
                } else {
                    Utils.showToast(view.getContext(), "max 4");
                    return;
                }
                updateViewHolders();
                CompareActivity.this.updateGridViews();
            }
        }

        private void updateViewHolders() {
            int findLastVisibleItemPosition = CompareActivity.this.mLayoutManager.findLastVisibleItemPosition();
            for (int findFirstVisibleItemPosition = CompareActivity.this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                MediaItem mediaItem = CompareActivity.this.mItems.get(findFirstVisibleItemPosition);
                ViewHolder viewHolder = (ViewHolder) CompareActivity.this.mRecyclerView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition);
                if (viewHolder != null) {
                    viewHolder.select(CompareActivity.this.mSelectedItems.contains(mediaItem), CompareActivity.this.mSelectedItems.indexOf(mediaItem));
                }
            }
        }

        public int getItemCount() {
            return this.count;
        }

        public void resize(int i2) {
            this.count = i2;
            notifyDataSetChanged();
        }

        public void onBindViewHolder(final ViewHolder viewHolder, int i2) {
            viewHolder.iv = (ImageView) viewHolder.itemView.findViewById(R$id.itemImage);
            viewHolder.tv = (TextView) viewHolder.itemView.findViewById(R$id.tv);
            ImageView imageView = viewHolder.iv;
            ViewUtils.setViewShape(imageView, 1, (float) ResourceCompat.dpToPixel(imageView.getResources(), 22.0f));
            ViewUtils.setViewShape(viewHolder.tv, 0, 0.0f);
            final MediaItem mediaItem = CompareActivity.this.mItems.get(i2);
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new ThumbnailLoadedListener() {
                /* access modifiers changed from: private */
                public static /* synthetic */ void lambda$onLoaded$0(ViewHolder viewHolder, Bitmap bitmap, MediaItem mediaItem) {
                    viewHolder.iv.setImageBitmap(bitmap);
                    viewHolder.iv.setScaleType(ImageView.ScaleType.MATRIX);
                    viewHolder.iv.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(viewHolder.iv, true).withCropRect((Rect) null).withOrientation(mediaItem.getOrientation()).withOrientationTag(mediaItem.getOrientationTag())));
                    Log.d("CompareActivity", "setThumb for " + viewHolder.getLayoutPosition(), bitmap);
                }

                public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
                    ViewHolder viewHolder = viewHolder;
                    viewHolder.iv.post(new d(viewHolder, bitmap, mediaItem));
                }
            });
            viewHolder.itemView.setOnClickListener(new c(this));
            viewHolder.select(CompareActivity.this.mSelectedItems.contains(mediaItem), CompareActivity.this.mSelectedItems.indexOf(mediaItem));
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.compare_item_layout, viewGroup, false));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PhotoViewHolder {
        ImageButton deleteBtn;
        ImageButton favoriteBtn;
        int idx;
        CompareImageView imageView;
        ViewGroup root;

        public PhotoViewHolder(int i2, ViewGroup viewGroup) {
            this.idx = i2;
            this.root = viewGroup;
            this.imageView = (CompareImageView) viewGroup.findViewById(R$id.image_view);
            this.favoriteBtn = (ImageButton) viewGroup.findViewById(R$id.favorite);
            this.deleteBtn = (ImageButton) viewGroup.findViewById(R$id.delete);
        }

        public void setFavouriteIcon(boolean z) {
            if (z) {
                this.favoriteBtn.setImageResource(R$drawable.gallery_ic_detail_favorite_on);
            } else {
                this.favoriteBtn.setImageResource(R$drawable.gallery_ic_detail_favorite_off);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public ViewHolder(View view) {
            super(view);
        }

        public void select(boolean z, int i2) {
            Drawable drawable = null;
            if (z) {
                drawable = this.iv.getResources().getDrawable(R$drawable.albums_highlighted_border_grid_max, (Resources.Theme) null);
            }
            ViewUtils.setShapeBorder(this.iv, drawable);
            if (z) {
                ViewUtils.setText(this.tv, Integer.toString(i2 + 1));
                ViewUtils.setVisibility(this.tv, 0);
                return;
            }
            ViewUtils.setVisibility(this.tv, 8);
        }
    }

    public CompareActivity() {
        Blackboard instance = Blackboard.getInstance(toString());
        this.mBlackboard = instance;
        this.mBitmaps = new HashMap<>();
        this.mShareTouches = true;
        this.photoViewIds = new int[]{R$id.image_view1, R$id.image_view2, R$id.image_view3, R$id.image_view4};
        this.mItems = new ArrayList<>();
        this.mSelectedItems = new ArrayList<>();
        instance.publish("data://activity", this);
    }

    private void delete(MediaItem mediaItem) {
        TrashLogType trashLogType;
        TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(this);
        deleteHelper.setProgressListener(1, new TrashProgressListener() {
            public void onComplete() {
            }

            public void onProgress(int i2) {
            }
        });
        deleteHelper.deleteItem(mediaItem);
        deleteHelper.done();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)) {
            trashLogType = TrashLogType.MOVE_TO_TRASH_SINGLE;
        } else {
            trashLogType = TrashLogType.DELETE_SINGLE;
        }
        deleteHelper.dump(trashLogType, "CompareActivity");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindToolbar$14(Toolbar toolbar, View view) {
        toolbar.postDelayed(new d(this, 0), 120);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$bindToolbar$15(MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.link) {
            boolean z = this.mShareTouches;
            this.mShareTouches = !z;
            if (!z) {
                this.mGridView.setInterceptTouchEventListener(new b(this));
                menuItem.setIcon(R$drawable.tw_ic_ab_similar_view_on);
                return true;
            }
            this.mGridView.setInterceptTouchEventListener((GridView.InterceptTouchEventListener) null);
            menuItem.setIcon(R$drawable.tw_ic_ab_similar_view_off);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$10() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.mLayoutManager = linearLayoutManager;
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onActivityResult$5(MediaItem mediaItem) {
        return !this.mItems.contains(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onActivityResult$6(MediaItem mediaItem) {
        if (this.mSelectedItems.size() < 2 && !this.mSelectedItems.contains(mediaItem)) {
            this.mSelectedItems.add(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onActivityResult$7(ArrayList arrayList) {
        this.mItems.clear();
        this.mItems.addAll(arrayList);
        this.mSelectedItems.removeIf(new h3.b(10, this));
        if (this.mSelectedItems.size() < 2) {
            this.mItems.forEach(new a(3, this));
        }
        this.mAdapter.resize(this.mItems.size());
        updateGridViews();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onActivityResult$8(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        UriItemLoader.loadFromContentUris(arrayList, arrayList2);
        preloadBitmaps(arrayList2);
        ThreadUtil.postOnUiThread(new C0717a(this, arrayList2, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(PhotoViewHolder photoViewHolder, View view) {
        MediaItem mediaItem = this.mSelectedItems.get(photoViewHolder.idx);
        boolean isFavourite = mediaItem.isFavourite();
        boolean z = !isFavourite;
        Log.i("CompareActivity", "favorite click : " + photoViewHolder.idx, Boolean.valueOf(z), mediaItem);
        if (mediaItem.isCloudOnly()) {
            SamsungCloudCompat.setFavorite(this, mediaItem.getCloudServerId(), mediaItem.getCloudServerPath(), isFavourite);
        } else if (mediaItem.isPostProcessing()) {
            new TagEditor().setFavoriteToSecMp(mediaItem, z);
        }
        new TagEditor().setFavorite(mediaItem, z);
        photoViewHolder.setFavouriteIcon(z);
        mediaItem.setFavourite(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(MediaItem mediaItem, DialogInterface dialogInterface, int i2) {
        delete(mediaItem);
        if (this.mAdapter.count - 1 < 2) {
            finish();
        }
        this.mItems.remove(mediaItem);
        this.mSelectedItems.remove(mediaItem);
        ListAdapter listAdapter = this.mAdapter;
        listAdapter.resize(listAdapter.count - 1);
        updateGridViews();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(PhotoViewHolder photoViewHolder, View view) {
        MediaItem mediaItem = this.mSelectedItems.get(photoViewHolder.idx);
        Log.i("CompareActivity", "delete click : " + photoViewHolder.idx, mediaItem);
        new AlertDialog.Builder(this).setMessage(R$string.move_this_image_to_the_trash).setPositiveButton(R$string.move_to_trash, (DialogInterface.OnClickListener) new g(8, this, mediaItem)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new Ba.a(7)).create().show();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateGridViews$11(ArrayList arrayList, PhotoViewHolder photoViewHolder) {
        if (!arrayList.contains(photoViewHolder.imageView)) {
            photoViewHolder.root.setVisibility(8);
        } else {
            photoViewHolder.root.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateGridViews$12(CompareImageView compareImageView) {
        compareImageView.resetScaleAndCenter();
        compareImageView.invalidate();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateGridViews$13() {
        ArrayList arrayList = new ArrayList();
        int size = this.mSelectedItems.size();
        Log.d("CompareActivity", C0086a.i(size, "updateGridViews : "), Integer.valueOf(this.mGridView.getWidth()), Integer.valueOf(this.mGridView.getHeight()));
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(this.mPhotoViewHolderList.get(i2).imageView);
        }
        if (size == 2) {
            resetSpecForSingleColumn();
            this.mGridView.setColumnCount(1);
        } else if (size == 3) {
            resetSpecForSingleColumn();
            this.mGridView.setColumnCount(1);
        } else if (size == 4) {
            updateLp(0, 0, 0);
            updateLp(1, 0, 1);
            updateLp(2, 1, 0);
            updateLp(3, 1, 1);
            this.mGridView.setColumnCount(2);
        }
        this.mPhotoViewHolderList.forEach(new a(0, arrayList));
        Iterator<MediaItem> it = this.mSelectedItems.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            this.mPhotoViewHolderList.get(this.mSelectedItems.indexOf(next)).setFavouriteIcon(next.isFavourite());
            CompareImageView compareImageView = (CompareImageView) arrayList.remove(0);
            compareImageView.setImage(next, this.mBitmaps.get(Long.valueOf(next.getFileId())));
            compareImageView.setOnSizeChangeListener(new c(compareImageView, 0));
            compareImageView.post(new c(compareImageView, 1));
            Log.d("CompareActivity", "final View : ", compareImageView);
        }
        this.mGridView.requestLayout();
    }

    /* access modifiers changed from: private */
    public void load() {
        String stringExtra = getIntent().getStringExtra("data-key");
        if (stringExtra == null) {
            Log.e("CompareActivity", "invalid data key");
            return;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) Blackboard.getApplicationInstance().pop(stringExtra, null);
        if (mediaItemArr != null) {
            Stream filter = Arrays.stream(mediaItemArr).filter(new e(21));
            ArrayList<MediaItem> arrayList = this.mItems;
            Objects.requireNonNull(arrayList);
            filter.forEach(new B8.d(arrayList, 23));
        }
        if (this.mItems.size() < 2) {
            Log.e("CompareActivity", "invalid items");
            return;
        }
        ArrayList<MediaItem> arrayList2 = this.mSelectedItems;
        ArrayList<MediaItem> arrayList3 = this.mItems;
        arrayList2.addAll(arrayList3.subList(0, Math.min(2, arrayList3.size())));
        preloadBitmaps(this.mSelectedItems);
        this.mAdapter = new ListAdapter(this.mItems.size());
        this.mRecyclerView.post(new d(this, 3));
        updateGridViews();
        preloadBitmaps(this.mItems);
    }

    /* access modifiers changed from: private */
    public boolean onTouchInterceptedEvent(MotionEvent motionEvent) {
        Iterator<PhotoViewHolder> it = this.mPhotoViewHolderList.iterator();
        while (it.hasNext()) {
            PhotoViewHolder next = it.next();
            if (ViewUtils.isVisible(next.root)) {
                if (motionEvent.getPointerCount() == 1 && motionEvent.getAction() == 0) {
                    if (ViewUtils.isTouchedOnViewRaw(next.favoriteBtn, motionEvent)) {
                        next.favoriteBtn.callOnClick();
                    } else if (ViewUtils.isTouchedOnViewRaw(next.deleteBtn, motionEvent)) {
                        next.deleteBtn.callOnClick();
                    }
                }
                next.imageView.dispatchTouchEvent(motionEvent);
            }
        }
        return true;
    }

    private void preloadBitmaps(ArrayList<MediaItem> arrayList) {
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (!this.mBitmaps.containsKey(Long.valueOf(next.getFileId()))) {
                this.mBitmaps.put(Long.valueOf(next.getFileId()), BitmapUtils.getDecodedBitmap(next.getPath(), BitmapOptionsFactory.of(next).adjustInSampleSize(BitmapSizeHolder.size())));
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateGridViews() {
        HashMap<Long, Bitmap> hashMap = this.mBitmaps;
        if (hashMap != null && !hashMap.isEmpty()) {
            this.mGridView.post(new d(this, 2));
        }
    }

    public void bindToolbar(Toolbar toolbar) {
        toolbar.setBackgroundColor(toolbar.getContext().getColor(R$color.viewer_tool_bar_background_color));
        toolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_detailview);
        toolbar.setNavigationOnClickListener(new C0518a(2, this, toolbar));
        toolbar.setTitle(R$string.compare_images);
        toolbar.inflateMenu(R$menu.menu_compare_activity);
        toolbar.setOnMenuItemClickListener(new b(this));
    }

    public void onActivityResult(int i2, int i7, Intent intent) {
        super.onActivityResult(i2, i7, intent);
        Log.i("CompareActivity", "onActivityResult : ", Integer.valueOf(i2), Integer.valueOf(i7), intent);
        if (i2 == 2316 && intent != null) {
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("selectedItems");
            if (arrayList == null || arrayList.size() < 2) {
                Utils.showToast((Context) this, "please select 2 mores");
                return;
            }
            Log.i("CompareActivity", "result : " + Arrays.toString(arrayList.toArray()));
            SimpleThreadPool.getInstance().execute(new C0717a(this, arrayList, 0));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("CompareActivity", "onConfigurationChanged", Integer.valueOf(configuration.orientation), Integer.valueOf(configuration.densityDpi), Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels));
    }

    public void onCreate(Bundle bundle) {
        TimeTickLog timeTickLog = new TimeTickLog();
        super.onCreate(bundle);
        timeTickLog.tick();
        setContentView(R$layout.compare_activity_layout);
        timeTickLog.tick();
        int i2 = 0;
        while (true) {
            int[] iArr = this.photoViewIds;
            if (i2 >= iArr.length) {
                break;
            }
            PhotoViewHolder photoViewHolder = new PhotoViewHolder(i2, (ViewGroup) findViewById(iArr[i2]));
            this.mPhotoViewHolderList.add(photoViewHolder);
            photoViewHolder.favoriteBtn.setOnClickListener(new b(this, photoViewHolder, 0));
            photoViewHolder.deleteBtn.setOnClickListener(new b(this, photoViewHolder, 1));
            i2++;
        }
        this.mRecyclerView = (RecyclerView) findViewById(R$id.list);
        this.mGridView = (GridView) findViewById(R$id.gridView);
        this.mAddBtn = (ImageButton) findViewById(R$id.add_button);
        bindToolbar((Toolbar) findViewById(R$id.toolbar));
        this.mPhotoViewHolderList.forEach(new a(1, new AtomicInteger(0)));
        ThumbnailLoader.getInstance().open();
        SimpleThreadPool.getInstance().execute(new d(this, 1));
        Iterator<PhotoViewHolder> it = this.mPhotoViewHolderList.iterator();
        while (it.hasNext()) {
            CompareImageView compareImageView = it.next().imageView;
            compareImageView.setZoomEnabled(true);
            compareImageView.blockRegionDecoding(true);
            compareImageView.setMotionControl(compareImageView.createDefaultMotionControl(compareImageView.getParent()), (OnViewerExitGestureListener) null);
        }
        this.mGridView.setInterceptTouchEventListener(new b(this));
        this.mAddBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.samsung.intent.action.MULTIPLE_PICK");
                intent.setType("image/*");
                intent.putExtra("multi-pick", true);
                StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                CompareActivity.this.mItems.forEach(new a(2, stringJoiner));
                intent.putExtra("picker_preselected_sem_id", stringJoiner.toString());
                intent.putExtra("pick-max-item", 20);
                CompareActivity.this.startActivityForResult(intent, 2316);
            }
        });
        ViewUtils.setViewShape(this.mAddBtn, 1, (float) ResourceCompat.dpToPixel(getResources(), 22.0f));
        timeTickLog.tick();
        setResult(0);
        Log.d("CompareActivity", "onCreate", timeTickLog.summary());
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.reset(toString());
    }

    public void onResume() {
        super.onResume();
        updateGridViews();
    }

    public void resetSpecForSingleColumn() {
        for (int i2 = 0; i2 < 4; i2++) {
            ViewGroup viewGroup = this.mPhotoViewHolderList.get(i2).root;
            GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) viewGroup.getLayoutParams();
            GridLayout.Alignment alignment = GridLayout.FILL;
            layoutParams.rowSpec = GridLayout.spec(i2, 1, alignment, 1.0f);
            layoutParams.columnSpec = GridLayout.spec(0, 1, alignment, 1.0f);
            viewGroup.setLayoutParams(layoutParams);
        }
    }

    public void updateLp(int i2, int i7, int i8) {
        GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) this.mPhotoViewHolderList.get(i2).root.getLayoutParams();
        GridLayout.Alignment alignment = GridLayout.FILL;
        layoutParams.rowSpec = GridLayout.spec(i7, 1, alignment, 1.0f);
        layoutParams.columnSpec = GridLayout.spec(i8, 1, alignment, 1.0f);
    }
}
