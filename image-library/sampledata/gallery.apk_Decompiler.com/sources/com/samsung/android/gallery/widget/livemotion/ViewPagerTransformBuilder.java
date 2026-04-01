package com.samsung.android.gallery.widget.livemotion;

import A.a;
import I4.g;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;
import com.samsung.android.gallery.widget.livemotion.abstraction.TransformBuilder;
import com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme;
import com.samsung.android.gallery.widget.livemotion.theme.Transition;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformAlpha;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformFaceCircle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerTransformBuilder implements TransformBuilder {
    private int mDuration;
    private final IDuration mDurationMeasure;
    private final AtomicInteger mFaceCircleRepeated = new AtomicInteger();
    private final AtomicBoolean mFirstAnim = new AtomicBoolean(true);
    private int mOrientation;
    private PeopleData mPeopleData;
    private KenBurnTheme mTheme = KenBurnTheme.Relaxing;
    private Transition.TYPE mTransition = Transition.TYPE.FAST_FADE_IN;

    public ViewPagerTransformBuilder(IDuration iDuration) {
        this.mDurationMeasure = iDuration;
    }

    private boolean allowFaceCircle() {
        boolean z;
        int i2 = this.mFaceCircleRepeated.get();
        int i7 = 0;
        if (this.mPeopleData == null || i2 >= 2 || RandomNumber.nextInt(2) != 0) {
            z = false;
        } else {
            z = true;
        }
        AtomicInteger atomicInteger = this.mFaceCircleRepeated;
        if (z) {
            i7 = i2 + 1;
        }
        atomicInteger.set(i7);
        return z;
    }

    private boolean applyScaleUp(ViewPager2 viewPager2) {
        if (this.mFirstAnim.getAndSet(false)) {
            return true;
        }
        View transformLayout = getTransformLayout(viewPager2);
        if (transformLayout == null || transformLayout.getScaleX() != 1.0f) {
            return false;
        }
        return true;
    }

    private float getFaceCircleDelay(ArrayList<PageTransform> arrayList) {
        Iterator<PageTransform> it = arrayList.iterator();
        while (it.hasNext()) {
            PageTransform next = it.next();
            if ((next instanceof PageTransformAlpha) && !next.isVisiblePage()) {
                return next.getDelay();
            }
        }
        return 0.0f;
    }

    private MediaItem getMediaItem(ViewPager2 viewPager2, int i2) {
        ListViewHolder viewHolder = getViewHolder(viewPager2, i2);
        if (viewHolder != null) {
            return viewHolder.getMediaItem();
        }
        return null;
    }

    private ListViewHolder getViewHolder(ViewPager2 viewPager2, int i2) {
        try {
            return (ListViewHolder) ((RecyclerView) viewPager2.getChildAt(0)).findViewHolderForLayoutPosition(i2);
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to find itemView="), "TAG");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepare$0(ListViewHolder listViewHolder, PageTransform pageTransform) {
        if (pageTransform.hasTargetView()) {
            pageTransform.prepare(listViewHolder.itemView);
        } else if (!(pageTransform instanceof PageTransformAlpha)) {
            pageTransform.prepare(listViewHolder.getDecoView(35));
        }
    }

    private ArrayList<PageTransform> withFaceCircleForm(ArrayList<PageTransform> arrayList) {
        if (allowFaceCircle()) {
            float faceCircleDelay = getFaceCircleDelay(arrayList);
            if (faceCircleDelay != 0.0f) {
                arrayList.add(new PageTransformFaceCircle(1, this.mPeopleData, this.mOrientation).setDelay(faceCircleDelay));
            }
        }
        return arrayList;
    }

    public List<PageTransform> buildNext(ViewPager2 viewPager2) {
        MediaItem mediaItem;
        int currentItem = viewPager2.getCurrentItem();
        if (applyScaleUp(viewPager2)) {
            mediaItem = null;
        } else {
            mediaItem = getMediaItem(viewPager2, currentItem);
        }
        MediaItem mediaItem2 = getMediaItem(viewPager2, currentItem + 1);
        this.mTransition = this.mTheme.getTransitionType(mediaItem2);
        ArrayList<PageTransform> buildNext = this.mTheme.buildNext(mediaItem, mediaItem2, this.mDurationMeasure);
        withFaceCircleForm(buildNext);
        return buildNext;
    }

    public void changeTheme(EffectTheme effectTheme) {
        this.mTheme = KenBurnTheme.valueOf(effectTheme.name());
        Log.d("ViewPagerTransformBuilder", "changeTheme = " + this.mTheme);
    }

    public int getDuration() {
        return this.mDuration;
    }

    public Interpolator getInterpolator() {
        return this.mTheme.getInterpolator(getPageInOutDelay());
    }

    public HashMap<ThumbnailInterface, KenBurnsInfo> getKenBurnsInfo(MediaData mediaData, int i2) {
        int i7;
        if (i2 != -1) {
            i7 = Math.min(mediaData.getCount(), i2);
        } else {
            i7 = mediaData.getCount();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        MediaItem mediaItem = null;
        int i8 = 0;
        while (i8 < i7) {
            MediaItem read = mediaData.read(i8);
            i8++;
            if (i8 < i7) {
                mediaItem = mediaData.read(i8);
            }
            if (read != null) {
                this.mTheme.buildKenBurnsInfo(linkedHashMap, read, mediaItem, this.mDurationMeasure);
            }
        }
        return linkedHashMap;
    }

    public float getPageInOutDelay() {
        return 1.0f - (((float) Math.max(this.mTransition.getTransitionDuration(), 250)) / ((float) getDuration()));
    }

    public View getTransformLayout(ViewPager2 viewPager2) {
        try {
            ListViewHolder viewHolder = getViewHolder(viewPager2, viewPager2.getCurrentItem());
            if (viewHolder != null) {
                return viewHolder.getDecoView(35);
            }
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to getTransformLayout="), "ViewPagerTransformBuilder");
            return null;
        }
    }

    public void prepare(RecyclerView.ViewHolder viewHolder) {
        ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
        if (listViewHolder != null && !this.mFirstAnim.get()) {
            this.mTheme.prepare(listViewHolder.getMediaItem()).forEach(new g(listViewHolder, 2));
        }
    }

    public TransformBuilder setDuration(int i2) {
        this.mDuration = i2;
        return this;
    }

    public TransformBuilder setPeopleData(PeopleData peopleData, int i2) {
        this.mPeopleData = peopleData;
        this.mOrientation = i2;
        return this;
    }
}
