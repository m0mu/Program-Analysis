package com.example.momo.permission_analysis;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import android.content.pm.ApplicationInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewDebug;

/**
 * Created by Juned on 4/15/2017.
 */

public class ApkInfoExtractor {

    Context context1;

    public ApkInfoExtractor(Context context2) {

        context1 = context2;
    }

    public List<String> GetAllInstalledApkInfo() {

        List<String> ApkPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);

        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context1.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {

            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if (!isSystemPackage(resolveInfo)) {

                ApkPackageName.add(activityInfo.applicationInfo.packageName);
            }
        }

        return ApkPackageName;

    }

    public boolean isSystemPackage(ResolveInfo resolveInfo) {

        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIconByPackageName(String ApkTempPackageName) {

        Drawable drawable;

        try {
            drawable = context1.getPackageManager().getApplicationIcon(ApkTempPackageName);

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

            drawable = ContextCompat.getDrawable(context1, R.mipmap.ic_launcher);
        }
        return drawable;
    }

    public String GetAppName(String ApkPackageName) {

        String Name = "";

        ApplicationInfo applicationInfo;

        PackageManager packageManager = context1.getPackageManager();

        try {

            applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);

            if (applicationInfo != null) {

                Name = (String) packageManager.getApplicationLabel(applicationInfo);
            }

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return Name;
    }

    public String GetPermissions(String Permission, String ApkPackageName) {

        PackageManager packageManager = context1.getPackageManager();


        if (packageManager.checkPermission(Permission, ApkPackageName) == PackageManager.PERMISSION_GRANTED)
            {
                return ("Permission Granted!");
            }
        else
            {
                return ("Permission Denied!");

            }

        }

        public List<String> Get_Granted_Permissions(String appPackage)
        {
            List<String> granted = new ArrayList<String>();
            try {
                PackageInfo pi = context1.getPackageManager().getPackageInfo(appPackage, PackageManager.GET_PERMISSIONS);
                for (int i = 0; i < pi.requestedPermissions.length; i++) {
                    if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
                        granted.add(pi.requestedPermissions[i]);
                    }
                }
            } catch (Exception e) {
            }
            return granted;
        }

        public String Get_Permission_Description (String package_Name)
        {
            PackageManager pkg = context1.getPackageManager();
            try {
                PermissionInfo pinfo = context1.getPackageManager().getPermissionInfo(package_Name, PackageManager.GET_META_DATA);
                try{
                    return pinfo.loadDescription(pkg).toString();
                }
                catch (NullPointerException e1)
                {
                    return pinfo.loadLabel(pkg).toString();
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return null;

//            try {
//                PermissionInfo pinfo = context1.getPackageManager().getPermissionInfo(package_Name, PackageManager.GET_META_DATA);
//            }catch (PackageManager.NameNotFoundException e)
//            {
//
//            }
//            String return_desc = pinfo.loadDescription(pkg).toString();
//            if(return_desc.equals(""))
//            {
//                return pinfo.loadLabel(pkg).toString();
//
//            }
//            return  return_desc;
        }
}


