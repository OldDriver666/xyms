package com.fise.model.dto.appmarket;

import java.util.ArrayList;

public class ApkInfo extends com.fise.model.dto.entity.ApkInfo{
    public ApkInfo() {
        super();
    }

    public ApkInfo(com.fise.model.dto.entity.ApkInfo apkInfo) {
        super.setVersionCode(apkInfo.getVersionCode());
        super.setVersionName(apkInfo.getVersionName());
        super.setPackageName(apkInfo.getPackageName());
        super.setMinSdkVersion(apkInfo.getMinSdkVersion());
        super.setUsesPermissions(apkInfo.getUsesPermissions());
        super.setSdkVersion(apkInfo.getSdkVersion());
        super.setTargetSdkVersion(apkInfo.getTargetSdkVersion());
        super.setApplicationLable(apkInfo.getApplicationLable());
        super.setApplicationIcons(apkInfo.getApplicationIcons());
        super.setApplicationIcon(apkInfo.getApplicationIcon());
        super.setImpliedFeatures(apkInfo.getImpliedFeatures());
        super.setFeatures(apkInfo.getFeatures());
        super.setLaunchableActivity(apkInfo.getLaunchableActivity());
        ArrayList<String> locales = apkInfo.getLocales();
        locales.remove("--_--");
        super.setLocales(locales);
        super.setLabels(apkInfo.getLabels());
        super.setNativeCodes(apkInfo.getNativeCodes());
    }

}
