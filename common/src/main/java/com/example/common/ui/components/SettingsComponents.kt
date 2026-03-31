package com.example.common.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.theme.LocalLibraryColors

// ── Section wrapper ──────────────────────────────────────────────────────────

/** Labeled card-style section used on the Settings screen. */
@Composable
fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    val colors = LocalLibraryColors.current
    Text(
        text       = title.uppercase(),
        fontSize   = 11.sp,
        fontWeight = FontWeight.SemiBold,
        color      = colors.listSecondText,
        modifier   = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 4.dp)
    )
    Surface(
        shape          = RoundedCornerShape(16.dp),
        color          = colors.cardBackground,
        tonalElevation = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
    }
}

// ── Toggle row ───────────────────────────────────────────────────────────────

/** Row with a title, subtitle, and a Switch — used for boolean preference toggles. */
@Composable
fun SettingsToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val colors = LocalLibraryColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text       = title,
                fontSize   = 15.sp,
                fontWeight = FontWeight.Medium,
                color      = colors.listFirstText
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text       = subtitle,
                fontSize   = 12.sp,
                color      = colors.listSecondText,
                lineHeight = 16.sp
            )
        }
        Spacer(Modifier.width(12.dp))
        Switch(
            checked         = checked,
            onCheckedChange = onCheckedChange,
            colors          = SwitchDefaults.colors(
                checkedThumbColor = colors.cardBackground,
                checkedTrackColor = colors.primary
            )
        )
    }
}

// ── Action button ────────────────────────────────────────────────────────────

/** Icon + title + subtitle tappable card — used for Backup and Restore actions. */
@Composable
fun SettingsActionButton(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    val colors = LocalLibraryColors.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, colors.primary.copy(alpha = 0.45f), RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        color          = colors.primary.copy(alpha = 0.10f),
        shape          = RoundedCornerShape(14.dp),
        tonalElevation = 0.dp
    ) {
        Row(
            modifier          = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector        = icon,
                contentDescription = null,
                tint               = colors.primary,
                modifier           = Modifier.size(26.dp)
            )
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = title,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = colors.primary
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text       = subtitle,
                    fontSize   = 11.sp,
                    color      = colors.listSecondText,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

