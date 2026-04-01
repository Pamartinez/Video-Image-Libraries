package com.adobe.internal.xmp;

import com.adobe.internal.xmp.properties.XMPAliasInfo;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface XMPSchemaRegistry {
    void deleteNamespace(String str);

    XMPAliasInfo findAlias(String str);

    XMPAliasInfo[] findAliases(String str);

    Map getAliases();

    String getNamespacePrefix(String str);

    String getNamespaceURI(String str);

    Map getNamespaces();

    Map getPrefixes();

    String registerNamespace(String str, String str2);

    XMPAliasInfo resolveAlias(String str, String str2);
}
