// IMDM.aidl
package com.mdm.forargos;

// Declare any non-default types here with import statements

interface IMDM {
    // boolean setScreenLockNone();
    // boolean setScreenLockSwipe();
    // boolean setScreenLockPattern(String password);
    // boolean setScreenLockPin(String password);
    // boolean setScreenLockPassword(String password);
    // boolean startPasswordInterface();
    // boolean turnStolenMode(boolean onOff, String lockScreenPackage, String lockScreenActivity);
    boolean factoryReset();
    // String getWIFIMAC();
    // String getBTMAC();
    // String getSN();
    // String getIMEI();
    // boolean isBluetoothLocked();
    // boolean lockBluetooth(boolean lock);
    // boolean isBluetoothTetheringLocked();
    // boolean lockBluetoothTethering(boolean lock);
    // boolean isWifiLocked();
    // boolean lockWifi(boolean lock);
    // boolean isWifiDirectLocked();
    // boolean lockWifiDirect(boolean lock);
    // boolean isWifiTetheringLocked();
    // boolean lockWifiTethering(boolean lock);
    // boolean isUSBLocked();
    // boolean lockUSB(boolean lock);
    // boolean isUSBTetheringLocked();
    // boolean lockUSBTethering(boolean lock);
    // boolean isGPSLocked();
    // boolean lockGPS(boolean lock);
    // boolean isMockLocationLocked();
    // boolean lockMockLocation(boolean lock);
    // boolean isMicLocked();
    // boolean lockMic(boolean lock);
    // boolean isCameraLocked();
    // boolean lockCamera(boolean lock);
    // boolean isSDCardLocked();
    // boolean isScreenCaptureLocked();
    // boolean lockScreenCapture(boolean lock);
    // boolean isApplicationLocked(String pkgName);
    // boolean lockApplication(String pkgName, boolean lock);
    // boolean isApplicationInstallLocked(String pkgName);
    // boolean lockApplicationInstall(String pkgName, boolean lock);
    // boolean isApplicationUninstallLocked(String pkgName);
    // boolean lockApplicationUninstall(String pkgName, boolean lock);
    // void installApplication(String apkPath);
    // void uninstallApplication(String pkgName);
    // boolean isApplicationForceStopLocked(String pkgName);
    // boolean lockApplicationForceStop(String pkgName, boolean lock);
    // boolean isApplicationDataRemoveLocked(String pkgName);
    // boolean lockApplicationDataRemove(String pkgName, boolean lock);
    // boolean isApplicationCacheRemoveLocked(String pkgName);
    // boolean lockApplicationCacheRemove(String pkgName, boolean lock);
    // boolean isAdminLocked();
    // boolean setAdminLocked(boolean lock);
    // boolean isFactoryResetLocked();
    // boolean lockFactoryReset(boolean lock);
    // boolean isSWUpdateLocked();
    boolean lockSWUpdate(boolean lock);
    // boolean isCellData();
    // boolean lockCellData(boolean lock);
    // private String deduplication(String srcStr);
    // private String deduplication(List<String> srcList);
    void setBluetoothDisabled(boolean disabled);
    // boolean getBluetoothDisabled();
    void setBluetoothTetherDisabled(boolean disabled);
    // boolean getBluetoothTetherDisabled();
    void setBluetoothFileShareDisabled(boolean disabled);
    // boolean getBluetoothFileShareDisabled();
    void setWifiDisabled(boolean disabled);
    // boolean getWifiDisabled();
    void setWifiP2PDisabled(boolean disabled);
    // boolean getWifiP2PDisabled();
    void setWifiTetherDisabled(boolean disabled);
    // boolean getWifiTetherDisabled();
    void setOTGDisabled(boolean disabled);
    // boolean getOTGDisabled();
    void setUSBDebugDisabled(boolean disabled);
    // boolean getUSBDebugDisabled();
    // void setUSBDataDisabled(boolean disabled);
    // boolean getUSBDataDisabled();
    void setUSBDataMode(int mode);
    // void setUSBDataMode(String mode);
    void setUsbTetherDisabled(boolean disabled);
    // boolean getUsbTetherDisabled();
    void setGPSDisabled(boolean disabled);
    // boolean getGPSDisabled();
    void setMockLocationDisabled(boolean disabled);
    // boolean getMockLocationDisabled();
    void setCameraDisabled(boolean disabled);
    // boolean getCameraDisabled();
    void setMicDisabled(boolean disabled);
    // boolean getMicDisabled();
    void setSDCardDisabled(boolean disabled);
    // boolean getSDCardDisabled();
    void setScreenshotDisabled(boolean disabled);
    // boolean getScreenshotDisabled();
    void setApplicationDisabled(String packageName, boolean disabled);
    // boolean getApplicationDisabled(String packageName);
    // void setApplicationsDisabled(String packageNames, boolean disabled);
    // String getDisabledApplications();
    // void setInstallDisabled(String packageName, boolean disabled);
    // boolean getInstallDisabled(String packageName);
    // void setUninstallDisabled(String packageName, boolean disabled);
    // boolean getUninstallDisabled(String packageName);
    // void silenceInstall(String apkPath);
    // void silenceUninstall(String packageName);
    // void setClearAppDataDisabled(String packageName, boolean disabled);
    // boolean getClearAppDataDisabled(String packageName);
    // void setClearAppCacheDisabled(String packageName, boolean disabled);
    // boolean getClearAppCacheDisabled(String packageName);
    // void setForceStopDisabled(String packageName, boolean disabled);
    // boolean getForceStopDisabled(String packageName);
    void setDeviceManagerDisabled(boolean disabled);
    // boolean getDeviceManagerDisabled();
    void setFactoryResetDisabled(boolean disabled);
    // boolean getFactoryResetDisabled();
    void setSafeModeBootDisabled(boolean disabled);
    // boolean getSafeModeBootDisabled();
    // void disableUninstallBlacklist();
    void enableUninstallBlacklist(String pkgs);
    // String queryUninstallBlacklist();
    // void removeUninstallBlacklist(String pkgs);
    // void insertUninstallBlacklist(String pkgs);
    void updateUninstallBlacklist(String pkgs, boolean append);
    // void disableInstallBlacklist();
    void enableInstallBlacklist(String pkgs);
    // String queryInstallBlacklist();
    // void removeInstallBlacklist(String pkgs);
    // void insertInstallBlacklist(String pkgs);
    void updateInstallBlacklist(String pkgs, boolean append);
    // void disableClearDataBlacklist();
    void enableClearDataBlacklist(String pkgs);
    // String queryClearDataBlacklist();
    // void removeClearDataBlacklist(String pkgs);
    // void insertClearDataBlacklist(String pkgs);
    void updateClearDataBlacklist(String pkgs, boolean append);
    // void disableClearCacheBlacklist();
    void enableClearCacheBlacklist(String pkgs);
    // String queryClearCacheBlacklist();
    // void removeClearCacheBlacklist(String pkgs);
    // void insertClearCacheBlacklist(String pkgs);
    void updateClearCacheBlacklist(String pkgs, boolean append);
    // void disableForceStopBlacklist();
    void enableForceStopBlacklist(String pkgs);
    // String queryForceStopBlacklist();
    // void removeForceStopBlacklist(String pkgs);
    // void insertForceStopBlacklist(String pkgs);
    void updateForceStopBlacklist(String pkgs, boolean append);
    void setMultiUserDisabled(boolean disabled);
    // boolean getMultiUserDisabled();
    boolean deleteNewUser();
    void clearLockPassword();
    void setSMSDisabled(boolean disabled);
    // boolean getSMSDisabled();
    void setOutgoingCallDisabled(boolean disabled);
    // boolean getOutgoingCallDisabled();
    void setIncomingCallDisabled(boolean disabled);
    // boolean getIncomingCallDisabled();
    void setCellDataDisabled(boolean disabled);
    // boolean getCellDataDisabled();
    // void grantAppAllPermission(String packageName);
    void setAllowAppInstallUnknowSource(String pkg, boolean allow);
    void setAllowAppDisplayOverOtherApps(String pkg, boolean allow);
    // void disableWIFIBlacklist();
    void enableWIFIBlacklist(String ssids);
    // String queryWIFIBlacklist();
    // void removeWIFIBlacklist(String ssids);
    // void insertWIFIBlacklist(String ssids);
    void updateWIFIBlacklist(String ssids, boolean append);
    // void disableInstallWhitelist();
    void enableInstallWhitelist(String pkgs);
    // String queryInstallWhitelist();
    // void removeInstallWhitelist(String pkgs);
    // void insertInstallWhitelist(String pkgs);
    void updateInstallWhitelist(String pkgs, boolean append);
    // void disableUninstallWhitelist();
    void enableUninstallWhitelist(String pkgs);
    // String queryUninstallWhitelist();
    // void removeUninstallWhitelist(String pkgs);
    // void insertUninstallWhitelist(String pkgs);
    void updateUninstallWhitelist(String pkgs, boolean append);
    // void enableWebBlacklist(String links);
    // String queryWebBlacklist();
    // void removeWebBlacklist(String links);
    // void insertWebBlacklist(String links);
    // void disableWebBlacklist();
    // void disableUrlBlacklist();
    void enableUrlBlacklist(String urls);
    // String queryUrlBlacklist();
    // void removeUrlBlacklist(String urls);
    // void insertUrlBlacklist(String urls);
    void updateUrlBlacklist(String urls, boolean append);
    // void disableIpBlacklist();
    void enableIpBlacklist(String ips);
    // String queryIpBlacklist();
    // void removeIpBlacklist(String ips);
    // void insertIpBlacklist(String ips);
    void updateIpBlacklist(String ips, boolean append);
    // void disableAppBlacklist();
    void enableAppBlacklist(String pkgs);
    // String queryAppBlacklist();
    // void removeAppBlacklist(String pkgs);
    // void insertAppBlacklist(String pkgs);
    void updateAppBlacklist(String pkgs, boolean append);
    // void disableWebWhitelist();
    // void disableAppWhitelist();
    void enableAppWhitelist(String pkgs);
    // String queryAppWhitelist();
    // void removeAppWhitelist(String pkgs);
    // void insertAppWhitelist(String pkgs);
    void updateAppWhitelist(String pkgs, boolean append);
    void enableIpWhitelist(String ips);
    // String queryIpWhitelist();
    // void removeIpWhitelist(String ips);
    // void insertIpWhitelist(String ips);
    void updateIpWhitelist(String ips, boolean append);
    void enableUrlWhitelist(String urls);
    // void removeUrlWhitelist(String urls);
    // void insertUrlWhitelist(String urls);
    void updateUrlWhitelist(String urls, boolean append);
    // void enableWebWhitelist(String links);
    // String queryWebWhitelist();
    // void removeWebWhitelist(String links);
    // void insertWebWhitelist(String links);
    // String queryDnslist();
    // void removeDnslist(String ips);
    // void insertDnslist(String ips);
    // private void updateDnslist(String ips, boolean append);
    // void disableFirewall();
    // void setUrlBlackList(String urls, String apps);
    // String displayUrlBlacklist();
    // void setUrlWhiteList(String urls, String apps);
    // String displayUrlWhitelist();
}