package androidx.graphics.shapes;

import android.graphics.Path;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0003\u001a\u00020\u0001*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a%\u0010\t\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/graphics/shapes/RoundedPolygon;", "Landroid/graphics/Path;", "path", "toPath", "(Landroidx/graphics/shapes/RoundedPolygon;Landroid/graphics/Path;)Landroid/graphics/Path;", "", "Landroidx/graphics/shapes/Cubic;", "cubics", "Lme/x;", "pathFromCubics", "(Landroid/graphics/Path;Ljava/util/List;)V", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Shapes_androidKt {
    private static final void pathFromCubics(Path path, List<? extends Cubic> list) {
        path.rewind();
        int size = list.size();
        boolean z = true;
        for (int i2 = 0; i2 < size; i2++) {
            Cubic cubic = (Cubic) list.get(i2);
            if (z) {
                path.moveTo(cubic.getAnchor0X(), cubic.getAnchor0Y());
                z = false;
            }
            path.cubicTo(cubic.getControl0X(), cubic.getControl0Y(), cubic.getControl1X(), cubic.getControl1Y(), cubic.getAnchor1X(), cubic.getAnchor1Y());
        }
        path.close();
    }

    public static final Path toPath(RoundedPolygon roundedPolygon, Path path) {
        j.e(roundedPolygon, "<this>");
        j.e(path, FileApiContract.Parameter.PATH);
        pathFromCubics(path, roundedPolygon.getCubics());
        return path;
    }
}
