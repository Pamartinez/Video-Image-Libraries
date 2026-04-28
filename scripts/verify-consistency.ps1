#!/usr/bin/env pwsh
# Consistency Verification Script
# Checks for inconsistencies between image-library and video-library

param([switch]$Verbose)

$ErrorActionPreference = "Stop"
$script:FailureCount = 0
$script:CheckCount = 0

function Write-Success { param([string]$M) Write-Host "[OK] $M" -ForegroundColor Green }
function Write-Failure { param([string]$M) Write-Host "[FAIL] $M" -ForegroundColor Red; $script:FailureCount++ }
function Write-Info { param([string]$M) if ($Verbose) { Write-Host "[INFO] $M" -ForegroundColor Cyan } }
function Write-Section { param([string]$T) Write-Host "`n=== $T ===" -ForegroundColor Yellow }

$ImageViewModel = "image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt"
$VideoViewModel = "video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt"
$ImageScreen = "image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt"
$VideoScreen = "video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt"

Write-Host "`nConsistency Verification System`n--------------------------------`n" -ForegroundColor Magenta

Write-Section "Check 1: ViewModel Method Parity"

$Methods = @("showCreateAlbumDialog", "dismissCreateAlbumDialog", "showGroupNameForCreation", "startCreateAlbumPicker")

foreach ($m in $Methods) {
    $script:CheckCount++
    $ic = Get-Content $ImageViewModel -Raw
    $vc = Get-Content $VideoViewModel -Raw
    $im = [regex]::IsMatch($ic, "fun\s+$m\s*\(")
    $vm = [regex]::IsMatch($vc, "fun\s+$m\s*\(")

    if ($im -and $vm) {
        Write-Success "Method $m found in both ViewModels"
    } elseif (-not $im -and -not $vm) {
        Write-Info "Method $m not found in either"
    } else {
        Write-Failure "Method $m exists in only one ViewModel"
    }
}

Write-Section "Check 2: Duplicate Dialog Rendering"

$Dialogs = @("CreateAlbumDialog", "GroupNameDialog", "CreateFolderDialog")

foreach ($d in $Dialogs) {
    $ic = Get-Content $ImageScreen -Raw
    $icount = ([regex]::Matches($ic, "$d\s*\(")).Count
    $vc = Get-Content $VideoScreen -Raw
    $vcount = ([regex]::Matches($vc, "$d\s*\(")).Count

    $script:CheckCount += 2

    if ($icount -eq 1) {
        Write-Success "ImageListScreen: $d rendered once"
    } elseif ($icount -eq 0) {
        Write-Info "ImageListScreen: No $d"
    } else {
        Write-Failure "ImageListScreen: $d rendered $icount times"
    }

    if ($vcount -eq 1) {
        Write-Success "VideoListScreen: $d rendered once"
    } elseif ($vcount -eq 0) {
        Write-Info "VideoListScreen: No $d"
    } else {
        Write-Failure "VideoListScreen: $d rendered $vcount times"
    }
}

Write-Section "Check 3: State Property Parity"

$Props = @("dcimFolderNames", "existingGroupNames", "suggestedGroupName")

foreach ($p in $Props) {
    $script:CheckCount++
    $ic = Get-Content $ImageViewModel -Raw
    $vc = Get-Content $VideoViewModel -Raw
    $inI = $ic -match "val\s+$p\s*:"
    $inV = $vc -match "val\s+$p\s*:"

    if ($inI -and $inV) {
        Write-Success "Property $p exists in both"
    } elseif (-not $inI -and -not $inV) {
        Write-Info "Property $p missing in both"
    } else {
        Write-Failure "Property $p parity broken"
    }
}

Write-Host "`n--------------------------------" -ForegroundColor Yellow
Write-Host "Total Checks: $script:CheckCount"

if ($script:FailureCount -eq 0) {
    Write-Host "Status: PASSED`n" -ForegroundColor Green
    exit 0
} else {
    Write-Host "Status: FAILED (Failures: $script:FailureCount)`n" -ForegroundColor Red
    exit 1
}

