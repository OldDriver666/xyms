package com.fise.model.dto.appmarket;

import com.fise.model.dto.utils.IconUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

public class ApkUtil {
    private static String sAaptPath;

    public ApkUtil() {

    }

    public static String getAaptPath() {

        return sAaptPath;

    }

    /**
     * 
     * 设置aapt路径
     *
     * 
     * 
     * @param aaptPath
     * 
     */

    public static void setAaptPath(String aaptPath) {

        sAaptPath = aaptPath;

    }

    /**
     * 
     * 获取apk信息
     *
     * 
     * 
     * @param apkPath
     * 
     * @return
     * 
     * @throws Exception
     * 
     */

    public static ApkInfo getApkInfo(String apkPath) throws Exception {

        com.fise.model.dto.utils.ApkUtil apkUtil = new com.fise.model.dto.utils.ApkUtil();

        apkUtil.setmAaptPath(sAaptPath);

        return new ApkInfo(apkUtil.getApkInfo(apkPath));

    }

    /**
     * 
     * 导出最大的apk图标
     *
     * 
     * 
     * @param apkPath
     * 
     * @return
     * 
     * @throws Exception
     * 
     */

    public static void extractIcon(String apkPath, String outputPath) throws Exception {

        ApkInfo apkInfo = getApkInfo(apkPath);

        IconUtil.extractFileFromApk(apkPath, getApplicationMaxIcon(apkInfo), outputPath);

    }

    /**
     * 
     * 获取最大的apk图标bytes
     *
     * 
     * 
     * @param apkPath
     * 
     * @return
     * 
     * @throws Exception
     * 
     */

    public static byte[] getIconBytes(String apkPath) throws Exception {

        ApkInfo apkInfo = getApkInfo(apkPath);

        InputStream is = IconUtil.extractFileFromApk(apkPath, getApplicationMaxIcon(apkInfo));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buff = new byte[128];

        int rc = 0;

        while ((rc = is.read(buff, 0, 128)) > 0) {

            bos.write(buff, 0, rc);

        }

        return bos.toByteArray();

    }

    private static String getApplicationMaxIcon(ApkInfo apkInfo) throws NumberFormatException {

        HashMap<String, String> icons = apkInfo.getApplicationIcons();

        if (apkInfo.getApplicationIcons().isEmpty()) {

            return apkInfo.getApplicationIcon();

        }

        int size = 0;

        for (String key : apkInfo.getApplicationIcons().keySet()) {

            int iconSize = Integer.parseInt(key.substring(17, key.length()));

            if (size < iconSize) {

                size = iconSize;

            }

        }

        return icons.get(com.fise.model.dto.utils.ApkUtil.APPLICATION_ICON + "-" + size);

    }

    /**
     * 
     * demo 获取apk文件的icon并写入磁盘指定位置
     *
     * 
     * 
     * @param args
     * 
     */

    public static void main(String[] args) {

        try {

            String aaptPath = "aapt";

            String apkpath = "demo.apk";

            if (args.length > 0) {

                apkpath = args[0];

            } else {

                System.out.println("Command: demo.apk [--aapt [aaptPath]] [--icon]");

                return;

            }

            int index = 1;

            if (args.length > 2 && args[1].equals("--aapt")) {

                aaptPath = args[2];

                index += 2;

            }

            ApkUtil.setAaptPath(aaptPath);

            ApkInfo apkInfo = ApkUtil.getApkInfo(apkpath);

            if (args.length > index && args[index].equals("--icon")) {

                ApkUtil.extractIcon(apkpath, "icon.png");

            } else {

                System.out.println(apkInfo);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
