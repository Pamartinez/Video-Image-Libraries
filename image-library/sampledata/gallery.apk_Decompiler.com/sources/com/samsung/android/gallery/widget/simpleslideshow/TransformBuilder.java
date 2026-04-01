package com.samsung.android.gallery.widget.simpleslideshow;

import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransform;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TransformBuilder {
    List<PageTransform> buildNext();

    int getDurationTime() {
        return 3000;
    }

    float getPageInOutDelay();

    void recycle();

    void setNextPeopleData(PeopleData peopleData, int i2);
}
