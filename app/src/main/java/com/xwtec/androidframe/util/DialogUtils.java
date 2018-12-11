package com.xwtec.androidframe.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.xwtec.androidframe.manager.Constant;

/**
 * @author ayy
 * @date 2017/1/11.
 * 自定义dialog类
 */
public class DialogUtils {

    private static DialogUtils instance;

    public static DialogUtils getInstance() {
        if (instance == null) {
            synchronized (DialogUtils.class) {
                if (instance == null) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 设置权限弹窗
     */
    public static void showSettingPermissionDialog(final Context context, final Activity activity, String msg, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                        intent.setData(uri);
                        activity.startActivityForResult(intent, Constant.SETTING_MUST_PERMISSION_CODE);
                    }
                })
                .setNegativeButton("取消", cancelListener)
                .setCancelable(false)
                .setMessage(msg)
                .show();
    }

    /**
     * 拒绝权限后的弹窗
     */
    public static void showDeniedDialog(final Context context, String msg, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setPositiveButton("授权", positiveListener)
                .setNegativeButton("取消", cancelListener)
                .setCancelable(false)
                .setMessage(msg)
                .show();
    }

}
