package com.samsung.android.gallery.app.ui.viewer2.details.items;

import Ad.C0720a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.details.DetailsView;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsItemFactory {
    static final ArrayList<DetailsListConstructor> DETAILS_ITEM_LIST = new ArrayList<DetailsListConstructor>() {
        {
            add(new t(0));
            add(new t(15));
            add(new t(16));
            if (Features.isEnabled(Features.SUPPORT_LOCATION) && !Features.isEnabled(Features.IS_UPSM)) {
                if (Features.isEnabled(Features.IS_USA_DEVICE)) {
                    add(new t(1));
                } else {
                    add(new t(2));
                }
            }
            add(new t(3));
            add(new t(4));
            if (Features.isEnabled(Features.SUPPORT_GO_TO_CAPTURED_PATH)) {
                add(new t(5));
            }
            add(new t(6));
            add(new t(7));
            add(new t(8));
            if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL) {
                add(new t(9));
                add(new t(10));
            }
            if (Features.isEnabled(Features.SUPPORT_LIGHTROOM) || Features.isEnabled(Features.SUPPORT_MEITU_IMAGE_TO_IMAGE)) {
                add(new t(11));
            }
            if (Features.isEnabled(Features.SUPPORT_C2PA)) {
                add(new t(12));
            } else if (PreferenceFeatures.isEnabled(PreferenceFeatures.MoreInfoGeneratedImage)) {
                add(new t(13));
            }
            if (PocFeatures.isEnabled(PocFeatures.MoreInfoExif)) {
                add(new t(14));
            }
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DetailsListConstructor extends BiFunction<DetailsView, EventContext, DetailsItem> {
        DetailsItem apply(DetailsView detailsView, EventContext eventContext);
    }

    public static ArrayList<DetailsItem> create(DetailsView detailsView, EventContext eventContext) {
        return (ArrayList) DETAILS_ITEM_LIST.stream().map(new s(detailsView, eventContext)).collect(Collectors.toCollection(new C0720a(1)));
    }
}
