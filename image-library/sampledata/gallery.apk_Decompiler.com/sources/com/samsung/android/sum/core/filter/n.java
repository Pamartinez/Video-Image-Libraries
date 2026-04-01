package com.samsung.android.sum.core.filter;

import android.content.Intent;
import android.os.Parcelable;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.service.LocalService;
import com.samsung.android.sum.core.types.nn.NNFW;
import com.samsung.o3dp.jpeg3dcontainer.model.GContainer;
import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.jpeg3dcontainer.model.Segment;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.scsp.error.ErrorSupplier;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4096a;

    public /* synthetic */ n(int i2) {
        this.f4096a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4096a) {
            case 0:
                return Optional.ofNullable(((ModelSelector.Item) obj).descriptorUpdater);
            case 1:
                return Arrays.toString((int[]) obj);
            case 2:
                return ((MediaBuffer) obj).getExtra("composer");
            case 3:
                return Request.lambda$new$0((Parcelable) obj);
            case 4:
                return Request.lambda$new$1((Parcelable) obj);
            case 5:
                return Request.lambda$getInputBuffer$2((List) obj);
            case 6:
                return Request.lambda$getOutputBuffer$3((List) obj);
            case 7:
                return ((List) obj).stream().findFirst();
            case 8:
                return Response.lambda$new$0((Parcelable) obj);
            case 9:
                return LocalService.lambda$onEvent$0((Parcelable) obj);
            case 10:
                return ((Package) obj).getName();
            case 11:
                return ((Intent) obj).getAction();
            case 12:
                return (NNFW) ((Map.Entry) obj).getValue();
            case 13:
                return ((GContainer.Item) obj).getMime();
            case 14:
                return ((GContainer.Item) obj).getSemantic();
            case 15:
                return ((GContainer.Item) obj).getLengthStr();
            case 16:
                return ((GContainer.Item) obj).getPadding();
            case 17:
                return ((GContainer.Item) obj).getLabel();
            case 18:
                return ((GContainer.Item) obj).getUri();
            case 19:
                return Jpeg3d.lambda$getGContainerSegment$2((Segment) obj);
            case 20:
                return ((Segment) obj).toBytes();
            case 21:
                return ((AnimationParams) obj).cameraEye;
            case 22:
                return ((AnimationParams) obj).cameraEye;
            case 23:
                return ((AnimationParams) obj).cameraUp;
            case 24:
                return Float.valueOf(((AnimationParams) obj).depthIntensity);
            case 25:
                return Float.valueOf(((AnimationParams) obj).fov);
            case 26:
                return Float.valueOf(((AnimationParams) obj).bokehIntensity);
            case 27:
                return Float.valueOf(((AnimationParams) obj).bokehFocusRange);
            case 28:
                return ErrorSupplier.lambda$static$0((Throwable) obj);
            default:
                return ((SCHashMap) obj).getAsLong(MediaApiContract.Parameter.ORIGINAL_UPLOAD_RANGE_START);
        }
    }
}
