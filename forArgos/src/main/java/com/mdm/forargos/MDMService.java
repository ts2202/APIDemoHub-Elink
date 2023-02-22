package com.mdm.forargos;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class MDMService extends Service {
    Context mContext;

    public MDMService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        mContext = getBaseContext();
        return new MDMBinder();
    }

    class MDMBinder extends IMDM.Stub {
//        @Override
//        public boolean setScreenLockNone() throws RemoteException {
//            return false;
//        }
//
//        @Override
//        public boolean setScreenLockSwipe() throws RemoteException {
//            return false;
//        }
//
//        @Override
//        public boolean setScreenLockPattern(String password) throws RemoteException {
//            return false;
//        }
//
//        @Override
//        public boolean setScreenLockPin(String password) throws RemoteException {
//            return false;
//        }
//
//        @Override
//        public boolean setScreenLockPassword(String password) throws RemoteException {
//            return false;
//        }
//
//        @Override
//        public boolean startPasswordInterface() throws RemoteException {
//            return false;
//        }
//
//        @Override
//        public boolean turnStolenMode(boolean onOff, String lockScreenPackage, String lockScreenActivity) throws RemoteException {
//            return false;
//        }

        @Override
        public boolean factoryReset() throws RemoteException {
            return false;
        }

        @Override
        public boolean lockSWUpdate(boolean lock) throws RemoteException {
            return false;
        }

        @Override
        public void setStatusBarDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setNavigationBarDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setBluetoothDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setBluetoothTetherDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setBluetoothFileShareDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setWifiDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setWifiP2PDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setWifiTetherDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setOTGDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setUSBDebugDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setUSBDataMode(int mode) throws RemoteException {

        }

        @Override
        public void setUsbTetherDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setGPSDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setMockLocationDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setCameraDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setMicDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setSDCardDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setScreenshotDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setApplicationDisabled(String packageName, boolean disabled) throws RemoteException {

        }

        @Override
        public void setDeviceManagerDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setFactoryResetDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setSafeModeBootDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void enableUninstallBlacklist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateUninstallBlacklist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableInstallBlacklist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateInstallBlacklist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableClearDataBlacklist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateClearDataBlacklist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableClearCacheBlacklist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateClearCacheBlacklist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableForceStopBlacklist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateForceStopBlacklist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void setMultiUserDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public boolean deleteNewUser() throws RemoteException {
            return false;
        }

        @Override
        public void clearLockPassword() throws RemoteException {

        }

        @Override
        public void setSMSDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setOutgoingCallDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setIncomingCallDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setCellDataDisabled(boolean disabled) throws RemoteException {

        }

        @Override
        public void setAllowAppInstallUnknowSource(String pkg, boolean allow) throws RemoteException {

        }

        @Override
        public void setAllowAppDisplayOverOtherApps(String pkg, boolean allow) throws RemoteException {

        }

        @Override
        public void enableWIFIBlacklist(String ssids) throws RemoteException {

        }

        @Override
        public void updateWIFIBlacklist(String ssids, boolean append) throws RemoteException {

        }

        @Override
        public void enableInstallWhitelist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateInstallWhitelist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableUninstallWhitelist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateUninstallWhitelist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableUrlBlacklist(String urls) throws RemoteException {

        }

        @Override
        public void updateUrlBlacklist(String urls, boolean append) throws RemoteException {

        }

        @Override
        public void enableIpBlacklist(String ips) throws RemoteException {

        }

        @Override
        public void updateIpBlacklist(String ips, boolean append) throws RemoteException {

        }

        @Override
        public void enableAppBlacklist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateAppBlacklist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableAppWhitelist(String pkgs) throws RemoteException {

        }

        @Override
        public void updateAppWhitelist(String pkgs, boolean append) throws RemoteException {

        }

        @Override
        public void enableIpWhitelist(String ips) throws RemoteException {

        }

        @Override
        public void updateIpWhitelist(String ips, boolean append) throws RemoteException {

        }

        @Override
        public void enableUrlWhitelist(String urls) throws RemoteException {

        }

        @Override
        public void updateUrlWhitelist(String urls, boolean append) throws RemoteException {

        }
    }
}