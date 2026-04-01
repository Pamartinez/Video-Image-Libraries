package com.adobe.internal.xmp;

import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface XMPIterator extends Iterator {
    void skipSiblings();

    void skipSubtree();
}
