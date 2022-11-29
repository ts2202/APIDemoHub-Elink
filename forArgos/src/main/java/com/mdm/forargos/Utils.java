package com.mdm.forargos;

public interface Utils {

    /**
     * 禁用锁屏
     */
    public boolean setScreenLockNone();

    public boolean setScreenLockSwipe();

    public boolean setScreenLockPattern(String password);

    public boolean setScreenLockPin(String password);

    public boolean setScreenLockPassword(String password);

    public boolean startPasswordInterface();

    public boolean turnStolenMode(boolean onOff, String lockScreenPackage, String lockScreenActivity);

    public boolean factoryReset();

    public String getWIFIMAC();

    public String getBTMAC();

    public String getSN();

    public String getIMEI();

    public boolean isBluetoothLocked();

    public boolean lockBluetooth(boolean lock);

    public boolean isBluetoothTetheringLocked();

    public boolean lockBluetoothTethering(boolean lock);

    public boolean isWifiLocked();

    public boolean lockWifi(boolean lock);

    public boolean isWifiDirectLocked();

    public boolean lockWifiDirect(boolean lock);

    public boolean isWifiTetheringLocked();

    public boolean lockWifiTethering(boolean lock);

    public boolean isUSBLocked();

    public boolean lockUSB(boolean lock);

    public boolean isUSBTetheringLocked();

    public boolean lockUSBTethering(boolean lock);

    public boolean isGPSLocked();

    public boolean lockGPS(boolean lock);

    public boolean isMockLocationLocked();

    public boolean lockMockLocation(boolean lock);

    public boolean isMicLocked();

    public boolean lockMic(boolean lock);

    public boolean isCameraLocked();

    public boolean lockCamera(boolean lock);

    public boolean isSDCardLocked();

    public boolean lockSDCard(boolean lock);

    public boolean isScreenCaptureLocked();

    public boolean lockScreenCapture(boolean lock);

    public boolean isApplicationLocked(String pkgName);

    public boolean lockApplication(String pkgName, boolean lock);

    public boolean isApplicationInstallLocked(String pkgName);

    public boolean lockApplicationInstall(String pkgName, boolean lock);

    public boolean isApplicationUninstallLocked(String pkgName);

    public boolean lockApplicationUninstall(String pkgName, boolean lock);

    public void installApplication(String apkPath);

    public void uninstallApplication(String pkgName);

    public boolean isApplicationForceStopLocked(String pkgName);

    public boolean lockApplicationForceStop(String pkgName, boolean lock);

    public boolean isApplicationDataRemoveLocked(String pkgName);

    public boolean lockApplicationDataRemove(String pkgName, boolean lock);

    public boolean isApplicationCacheRemoveLocked(String pkgName);

    public boolean lockApplicationCacheRemove(String pkgName, boolean lock);

    public boolean isAdminLocked();

    public boolean setAdminLocked(boolean lock);

    public boolean isFactoryResetLocked();

    public boolean lockFactoryReset(boolean lock);

    public boolean isSWUpdateLocked();

    public boolean lockSWUpdate(boolean lock);

}
