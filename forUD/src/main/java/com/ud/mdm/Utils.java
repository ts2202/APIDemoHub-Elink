package com.ud.mdm;

import static android.content.Context.BATTERY_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Utils {

    protected final String ipPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    protected final String ipBlockPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)";

    protected Context mContext;

    public Utils(Context context) {
        mContext = context;
    }

    protected String deduplication(String srcStr) {
        if (srcStr == null || "".equals(srcStr))
            return "";
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>(Arrays.asList(srcStr.split(",")));
        set.remove("null");
        for (String str : set) {
            tgtStr.append(str);
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        } else return "";
    }

    protected String deduplication(List<String> srcList) {
        if (srcList.size() < 1)
            return "";
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>(srcList);
        set.remove("null");
        for (String str : set) {
            tgtStr.append(str);
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        } else return "";
    }

    public int getBatteryCapacity() {
        BatteryManager batteryManager = (BatteryManager) mContext.getSystemService(BATTERY_SERVICE);
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    public void setSystemTime(long millisecond) {
        Intent intent = new Intent("modify.system.time");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("millisecond", millisecond);
        mContext.sendBroadcast(intent);
    }

    /**
     * 护眼模式
     *
     * @param enabled 护眼模式启用与否
     */
    public void setEyesCareEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.blue.filter");
        } else {
            intent.setAction("disable.blue.filter");
        }
//        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getEyesCareEnabled() {
        return Settings.Secure.getInt(
                mContext.getContentResolver(), "night_display_activated", 0) == 1;
    }

    public void setVolumeKeyDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.volume.press");
        } else {
            intent.setAction("enable.volume.press");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getVolumeKeyDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.volume.key", 1) == 0;
    }

    public void setPowerKeyShortDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.power.short.press");
        } else {
            intent.setAction("enable.power.short.press");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getPowerKeyShortDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.power.key.short", 1) == 0;
    }

    public void setFactoryResetDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.factory.reset");
        } else {
            intent.setAction("enable.factory.reset");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getFactoryResetDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.factory.reset", 1) == 0;
    }

    public void setGPSDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.gps");
        } else {
            intent.setAction("enable.gps");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getGPSDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "gps.module.enable", 1) == 0;
    }

    public void rebootDevice() {
        Intent intent = new Intent("reboot.device");
        intent.setPackage("com.android.shell");
        mContext.sendBroadcast(intent);
    }

    public void shutdownDevice() {
        Intent intent = new Intent("reboot.device");
        intent.putExtra("reboot_mode", "-p");
        intent.setPackage("com.android.shell");
        mContext.sendBroadcast(intent);
    }

    public void setApplicationDisabled(String packageName, boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.application");
        } else {
            intent.setAction("enable.application");
        }
        intent.putExtra("pkg_name", packageName);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getApplicationDisabled(String packageName) {
        return Arrays.asList(getDisabledApplications().split(",")).contains(packageName);
    }

    public void setApplicationsDisabled(String packageNames, boolean disabled) {
        for (String packageName : packageNames.split(",")) {
            setApplicationDisabled(packageName, disabled);
        }
    }

    public String getDisabledApplications() {
        String disabledApps = "";
        PackageManager packageManager = mContext.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (!info.applicationInfo.enabled) {
                if ("com.sprd.powersavemodelauncher".equals(info.packageName)
                        || "com.android.nfc".equals(info.packageName))
                    continue;
                if (disabledApps.equals("")) disabledApps = info.packageName;
                else disabledApps += "," + info.packageName;
            }
        }
        return disabledApps;
    }

    public void setBackDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("back", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setHomeDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("home", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setRecentDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("recent", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setStatusBarExpandDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("expand", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setStatusBarNotifyDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("notify", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void silenceInstall(String apkPath) {
        Intent intent = new Intent("install.application");
        intent.putExtra("path_name", apkPath);
        intent.setPackage("com.android.packageinstaller");
        mContext.sendBroadcast(intent);
    }

    public void silenceUninstall(String packageName) {
        Intent intent = new Intent("uninstall.application");
        intent.putExtra("pkg_name", packageName);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用安装白名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableInstallWhitelist() {
        enableInstallWhitelist(null);
    }

    public void enableInstallWhitelist(String pkgs) {
        Intent intent = new Intent("update.app.install.whitelist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryInstallWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.install.whitelist");
    }

    public void removeInstallWhitelist(String pkgs) {
        List<String> whitelist = Arrays.asList(queryInstallWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (whitelist.contains(pkg))
                mWhiteList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mWhiteList.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateInstallWhitelist(pkgs, false);
    }

    public void insertInstallWhitelist(String pkgs) {
        updateInstallWhitelist(pkgs, true);
    }

    private void updateInstallWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.install.whitelist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用卸载白名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableUninstallWhitelist() {
        enableUninstallWhitelist(null);
    }

    public void enableUninstallWhitelist(String pkgs) {
        Intent intent = new Intent("update.app.uninstall.whitelist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUninstallWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.uninstall.whitelist");
    }

    public void removeUninstallWhitelist(String pkgs) {
        List<String> whitelist = Arrays.asList(queryUninstallWhitelist().split(","));
        List<String> Whitelist = new ArrayList<>(whitelist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (whitelist.contains(pkg))
                Whitelist.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(Whitelist);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(Whitelist.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateUninstallWhitelist(pkgs, false);
    }

    public void insertUninstallWhitelist(String pkgs) {
        updateUninstallWhitelist(pkgs, true);
    }

    private void updateUninstallWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.uninstall.whitelist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用卸载黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableUninstallBlacklist() {
        enableUninstallBlacklist(null);
    }

    public void enableUninstallBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.uninstall.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUninstallBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.uninstall.blacklist");
    }

    public void removeUninstallBlacklist(String pkgs) {
        List<String> blacklist = Arrays.asList(queryUninstallBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (blacklist.contains(pkg))
                mBlackList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mBlackList.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateUninstallBlacklist(pkgs, false);
    }

    public void insertUninstallBlacklist(String pkgs) {
        updateUninstallBlacklist(pkgs, true);
    }

    private void updateUninstallBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.uninstall.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }


    /**
     * 停用防火墙
     */
    public void disableFirewall() {
        enableIpWhitelist(null);
    }

    /**
     * 应用上网白名单 禁用/启用/查询/删除/插入 更新(private)
     */

    public void disableAppWhitelist() {
        enableAppWhitelist(null);
    }

    public void enableAppWhitelist(String pkgs) {
        Intent intent;
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent = new Intent("disable.app.whitelist");
        } else {
            intent = new Intent("update.app.whitelist");
            intent.putExtra("app_list", pkgs);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);

    }

    public String queryAppWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.whitelist");
    }

    public void removeAppWhitelist(String pkgs) {
        if (queryAppWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryAppWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (whitelist.contains(pkg))
                mWhiteList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mWhiteList.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateAppWhitelist(pkgs, false);
    }

    public void insertAppWhitelist(String pkgs) {
        updateAppWhitelist(pkgs, true);

    }

    public void updateAppWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.whitelist");
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }


    /**
     * 网络IP白名单 启用/查询/删除/插入 更新(private)
     *
     * @param ips ip列表,逗号(,)分隔
     */
    public void enableIpWhitelist(String ips) {
        Intent intent;
        if (null == ips /*|| "".equals(ips)*/) {
            intent = new Intent("disable.firewall");
        } else {
            intent = new Intent("update.ip.whitelist");
            intent.putExtra("ip_list", ips);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryIpWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "ip.whitelist");
    }

    public void removeIpWhitelist(String ips) {
        if (queryIpWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryIpWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] ipList = ips.split(",");
        for (String ip : ipList) {
            if (whitelist.contains(ip))
                mWhiteList.remove(ip);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder ipsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            ipsBuilder.append(mWhiteList.get(i)).append(",");
        }
        ips = ipsBuilder.toString();

        if (ips.endsWith(",")) {
            ips = ips.substring(0, ips.length() - 1);
        }
        updateIpWhitelist(ips, false);
    }

    public void insertIpWhitelist(String ips) {
        updateIpWhitelist(ips, true);
    }

    private void updateIpWhitelist(String ips, boolean append) {
        Intent intent = new Intent("update.ip.whitelist");
        intent.putExtra("ip_list", deduplication(ips));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 网络域名白名单 启用/查询/删除/插入 更新(private)
     *
     * @param urls 域名列表,逗号(,)分隔
     */
    public void enableUrlWhitelist(String urls) {
        Intent intent;
        if (null == urls /*|| "".equals(urls)*/) {
            intent = new Intent("disable.firewall");
        } else {
            intent = new Intent("update.url.whitelist");
            intent.putExtra("url_list", urls);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUrlWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "url.whitelist");
    }

    public void removeUrlWhitelist(String urls) {
        if (queryUrlWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryUrlWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] urlList = urls.split(",");
        for (String url : urlList) {
            if (whitelist.contains(url))
                mWhiteList.remove(url);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder urlsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            urlsBuilder.append(mWhiteList.get(i)).append(",");
        }
        urls = urlsBuilder.toString();

        if (urls.endsWith(",")) {
            urls = urls.substring(0, urls.length() - 1);
        }
        updateUrlWhitelist(urls, false);
    }

    public void insertUrlWhitelist(String urls) {
        updateUrlWhitelist(urls, true);
    }

    private void updateUrlWhitelist(String urls, boolean append) {
        Intent intent = new Intent("update.url.whitelist");
        intent.putExtra("url_list", deduplication(urls));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 网络白名单 自动匹配 启用/查询/删除/插入
     *
     * @param links 0. 各个参数之间逗号分隔
     *              1. 通过正则表达式过滤IP,格式包括255.255.255.255 和 255.255.255.255/32
     *              2. 其余则认为是域名模糊匹配,如www.google.com/ditu.google.cn可直接用google
     */
    public void enableWebWhitelist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            enableIpWhitelist(ips.toString());
        } else {
            enableIpWhitelist("");
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            enableUrlWhitelist(urls.toString());
        } else {
            enableUrlWhitelist("");
        }

    }

    public String queryWebWhitelist() {
        return deduplication(queryIpWhitelist() + "," + queryUrlWhitelist());
    }

    public void removeWebWhitelist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            removeIpWhitelist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            removeUrlWhitelist(urls.toString());
        }
    }

    public void insertWebWhitelist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            insertIpWhitelist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            insertUrlWhitelist(urls.toString());
        }
    }
}
