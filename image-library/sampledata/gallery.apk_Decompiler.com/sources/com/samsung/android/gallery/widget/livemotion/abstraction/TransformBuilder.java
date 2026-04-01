package com.samsung.android.gallery.widget.livemotion.abstraction;

import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TransformBuilder {
    List<PageTransform> buildNext(ViewPager2 viewPager2);

    void changeTheme(EffectTheme effectTheme);

    int getDuration();

    Interpolator getInterpolator();

    HashMap<ThumbnailInterface, KenBurnsInfo> getKenBurnsInfo(MediaData mediaData, int i2);

    float getPageInOutDelay();

    boolean isSimpleSlide() {
        return false;
    }

    void prepare(RecyclerView.ViewHolder viewHolder);

    TransformBuilder setDuration(int i2);

    TransformBuilder setPeopleData(PeopleData peopleData, int i2);
}
