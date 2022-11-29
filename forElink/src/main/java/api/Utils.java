package api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Utils {
    private final Context mContext;
    protected final String ipPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    protected final String ipBlockPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)";

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

    public Utils(Context context) {
        mContext = context;
    }

    public String getWIFIMAC() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.wifi.mac");
    }

    public void launchSetting() {
        launchApp("com.android.settings","com.android.settings.Settings");
    }

    public void launchFileManager() {
        launchApp("com.android.documentsui","com.android.documentsui.files.FilesActivity");
    }

    private void launchApp(String pkg, String cls) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName(pkg, cls));
        mContext.startActivity(intent);
    }

    public void resetFactory() {
        Intent intent = new Intent("reset.factory");
        intent.setPackage("com.android.inputdevices");
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
     * 应用运行黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableRunningBlacklist() {
        enableRunningBlacklist(null);
    }

    public void enableRunningBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.running.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryRunningBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.running.blacklist");
    }

    public void removeRunningBlacklist(String pkgs) {
        if (queryRunningBlacklist() == null) return;
        List<String> whitelist = Arrays.asList(queryRunningBlacklist().split(","));
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
        updateRunningBlacklist(pkgs, false);
    }

    public void insertRunningBlacklist(String pkgs) {
        updateRunningBlacklist(pkgs, true);
    }

    private void updateRunningBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.running.blacklist");
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

    public void setAutoPoweron(boolean enable, int hour, int minute) {
        Intent intent = new Intent("alarm.set.poweron.action");
        intent.setComponent(new ComponentName("com.android.settings",
                "com.unisoc.settings.timerpower.SetAlarmReceiver"));
        intent.putExtra("set_enabled", enable);
        intent.putExtra("set_hour", hour);
        intent.putExtra("set_minutes", minute);
        mContext.sendBroadcast(intent);
    }

    public void setAutoPoweron(boolean enable) {
        setAutoPoweron(enable, -1, -1);
    }

    public void setAutoPoweroff(boolean enable, int hour, int minute) {
        Intent intent = new Intent("alarm.set.shutdown.action");
        intent.setComponent(new ComponentName("com.android.settings",
                "com.unisoc.settings.timerpower.SetAlarmReceiver"));
        intent.putExtra("set_enabled", enable);
        intent.putExtra("set_hour", hour);
        intent.putExtra("set_minutes", minute);
        mContext.sendBroadcast(intent);
    }

    public void setAutoPoweroff(boolean enable) {
        setAutoPoweroff(enable, -1, -1);
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
