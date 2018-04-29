package com.example.momo.permission_analysis;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;

public class Main_Activity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    List<String> all_permissions = new ArrayList<String>();


//    List<String> Protection_normal = Arrays.asList("ACCESS_LOCATION_EXTRA_COMMANDS","ACCESS_NETWORK_STATE","ACCESS_NOTIFICATION_POLICY", "ACCESS_WIFI_STATE", "BLUETOOTH", "BLUETOOTH_ADMIN", "BROADCAST_STICKY", "CHANGE_NETWORK_STATE", "CHANGE_WIFI_MULTICAST_STATE", "CHANGE_WIFI_STATE", "DISABLE_KEYGUARD", "EXPAND_STATUS_BAR", "GET_PACKAGE_SIZE", "INSTALL_SHORTCUT", "INTERNET", "KILL_BACKGROUND_PROCESSES", "MANAGE_OWN_CALLS", "MODIFY_AUDIO_SETTINGS", "NFC", "READ_SYNC_SETTINGS", "READ_SYNC_STATS", "RECEIVE_BOOT_COMPLETED", "REORDER_TASKS", "REQUEST_COMPANION_RUN_IN_BACKGROUND", "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND", "REQUEST_DELETE_PACKAGES", "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "REQUEST_INSTALL_PACKAGES", "SET_ALARM", "SET_WALLPAPER", "SET_WALLPAPER_HINTS", "TRANSMIT_IR", "USE_FINGERPRINT", "VIBRATE", "WAKE_LOCK", "WRITE_SYNC_SETTINGS");
//    List<String> Protection_signature = Arrays.asList("BIND_ACCESSIBILITY_SERVICE", "BIND_AUTOFILL_SERVICE", "BIND_CARRIER_SERVICES", "BIND_CHOOSER_TARGET_SERVICE", "BIND_CONDITION_PROVIDER_SERVICE", "BIND_DEVICE_ADMIN", "BIND_DREAM_SERVICE", "BIND_INCALL_SERVICE", "BIND_INPUT_METHOD", "BIND_MIDI_DEVICE_SERVICE", "BIND_NFC_SERVICE", "BIND_NOTIFICATION_LISTENER_SERVICE", "BIND_PRINT_SERVICE", "BIND_SCREENING_SERVICE", "BIND_TELECOM_CONNECTION_SERVICE", "BIND_TEXT_SERVICE", "BIND_TV_INPUT", "BIND_VISUAL_VOICEMAIL_SERVICE", "BIND_VOICE_INTERACTION", "BIND_VPN_SERVICE", "BIND_VR_LISTENER_SERVICE", "BIND_WALLPAPER", "CLEAR_APP_CACHE", "MANAGE_DOCUMENTS", "READ_VOICEMAIL", "REQUEST_INSTALL_PACKAGES", "SYSTEM_ALERT_WINDOW", "WRITE_SETTINGS", "WRITE_VOICEMAIL");
//    List<String> Protection_dangerous = Arrays.asList("READ_CALENDAR", "WRITE_CALENDAR", "CAMERA", "READ_CONTACTS", "WRITE_CONTACTS", "GET_ACCOUNTS", "ACCESS_FINE_LOCATION", "ACCESS_COARSE_LOCATION", "RECORD_AUDIO", "READ_PHONE_STATE", "READ_PHONE_NUMBERS", "CALL_PHONE", "ANSWER_PHONE_CALLS (must request at runtime)", "READ_CALL_LOG", "WRITE_CALL_LOG", "ADD_VOICEMAIL", "USE_SIP", "PROCESS_OUTGOING_CALLS", "ANSWER_PHONE_CALLS", "BODY_SENSORS", "SEND_SMS", "RECEIVE_SMS", "READ_SMS", "RECEIVE_WAP_PUSH", "RECEIVE_MMS", "READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE");

    List<String> Protection_normal = Arrays.asList("ACCESS_LOCATION_EXTRA_COMMANDS","ACCESS_NETWORK_STATE","ACCESS_NOTIFICATION_POLICY", "ACCESS_WIFI_STATE", "BLUETOOTH", "BLUETOOTH_ADMIN", "BROADCAST_STICKY", "CHANGE_NETWORK_STATE", "CHANGE_WIFI_MULTICAST_STATE", "CHANGE_WIFI_STATE", "DISABLE_KEYGUARD", "EXPAND_STATUS_BAR", "GET_PACKAGE_SIZE", "INSTALL_SHORTCUT", "INTERNET", "KILL_BACKGROUND_PROCESSES", "MANAGE_OWN_CALLS", "MODIFY_AUDIO_SETTINGS", "NFC", "READ_SYNC_SETTINGS", "READ_SYNC_STATS", "RECEIVE_BOOT_COMPLETED", "REORDER_TASKS", "REQUEST_COMPANION_RUN_IN_BACKGROUND", "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND", "REQUEST_DELETE_PACKAGES", "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "REQUEST_INSTALL_PACKAGES", "SET_ALARM", "SET_WALLPAPER", "SET_WALLPAPER_HINTS", "TRANSMIT_IR", "USE_FINGERPRINT", "VIBRATE", "WAKE_LOCK", "WRITE_SYNC_SETTINGS");
    List<String> Protection_signature = Arrays.asList("BIND_ACCESSIBILITY_SERVICE", "BIND_AUTOFILL_SERVICE", "BIND_CARRIER_SERVICES", "BIND_CHOOSER_TARGET_SERVICE", "BIND_CONDITION_PROVIDER_SERVICE", "BIND_DEVICE_ADMIN", "BIND_DREAM_SERVICE", "BIND_INCALL_SERVICE", "BIND_INPUT_METHOD", "BIND_MIDI_DEVICE_SERVICE", "BIND_NFC_SERVICE", "BIND_NOTIFICATION_LISTENER_SERVICE", "BIND_PRINT_SERVICE", "BIND_SCREENING_SERVICE", "BIND_TELECOM_CONNECTION_SERVICE", "BIND_TEXT_SERVICE", "BIND_TV_INPUT", "BIND_VISUAL_VOICEMAIL_SERVICE", "BIND_VOICE_INTERACTION", "BIND_VPN_SERVICE", "BIND_VR_LISTENER_SERVICE", "BIND_WALLPAPER", "CLEAR_APP_CACHE", "MANAGE_DOCUMENTS", "READ_VOICEMAIL", "REQUEST_INSTALL_PACKAGES", "SYSTEM_ALERT_WINDOW", "WRITE_SETTINGS", "WRITE_VOICEMAIL");
    List<String> Protection_dangerous = Arrays.asList("READ_CALENDAR", "WRITE_CALENDAR", "CAMERA", "READ_CONTACTS", "WRITE_CONTACTS", "GET_ACCOUNTS", "ACCESS_FINE_LOCATION", "ACCESS_COARSE_LOCATION", "RECORD_AUDIO", "READ_PHONE_STATE", "READ_PHONE_NUMBERS", "CALL_PHONE", "ANSWER_PHONE_CALLS (must request at runtime)", "READ_CALL_LOG", "WRITE_CALL_LOG", "ADD_VOICEMAIL", "USE_SIP", "PROCESS_OUTGOING_CALLS", "ANSWER_PHONE_CALLS", "BODY_SENSORS", "SEND_SMS", "RECEIVE_SMS", "READ_SMS", "RECEIVE_WAP_PUSH", "RECEIVE_MMS", "READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE");


    public String getScore (List<String> all_permissions, String app_pkg_name)
    {
        double count = 0;
        for (String permission : all_permissions)
        {
//            int last_index = app_pkg_name.lastIndexOf('.');
            if (Protection_normal.contains(permission.substring(permission.lastIndexOf('.')+1))) //36 permissions in total
            {
                count += 0.1;
            }
            if (Protection_signature.contains(permission.substring(permission.lastIndexOf('.')+1)))  //29 permissions in total
            {
                count += 0.1;
            }
            if (Protection_dangerous.contains(permission.substring(permission.lastIndexOf('.')+1))) //27 permissions in total
            {
                count += 1;
            }
        }
        count = Math.round(count * 100D) / 100D;
        return Double.toString(count);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);


        String applicationPackageName = getIntent().getStringExtra("ApplicationPackageName");
        ApkInfoExtractor apk = new ApkInfoExtractor(Main_Activity2.this);
        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setImageDrawable(apk.getAppIconByPackageName(applicationPackageName));
        TextView textView_app_name = (TextView) findViewById(R.id.pkg_name);
        textView_app_name.setText(apk.GetAppName(applicationPackageName));
        ImageView trashView = (ImageView) findViewById(R.id.trash_icon);
        trashView.setImageResource(R.drawable.trash);





//        TextView textView = (TextView) findViewById(R.id.Permission);
//        textView.setText(getIntent().getStringExtra("Permission"));
//        final String perm = apk.GetPermissions(Manifest.permission.CAMERA, applicationPackageName);
//        textView.setText(perm);


//        final CheckedTextView ctv = (CheckedTextView) findViewById(R.id.checkedTextView1);
//        if (perm.equalsIgnoreCase( "Permission Granted!"))
//        {
//            ctv.setText("Permission Granted!");
//            ctv.setChecked(true);
//        }
////                if (ctv.isChecked())
////                    ctv.setChecked(false);
//        else
//            ctv.setText("Permission Denied!");
//            ctv.setChecked(false);

        // CLEANING ALL PERMISSIONS!
        all_permissions = apk.Get_Granted_Permissions(applicationPackageName);
//
//        int count = 0;
//        for (String permission: all_permissions)
//        {
//            all_permissions.set(count, permission.replace("android.permission.",""));
//            count ++;
//
//        }

//        final CheckedTextView ctv = (CheckedTextView) findViewById(R.id.checkedTextView1);
//        if (perm.equalsIgnoreCase( "Permission Granted!"))
//        {
//            ctv.setText(perm);
//            ctv.setChecked(true);
//
//        }
////                if (ctv.isChecked())
////                    ctv.setChecked(false);
//        else {
//            ctv.setText(perm);
//        }
//
//        ctv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ctv.isChecked()) {
//                    ctv.setText("Permission Denied!");
//                    ctv.setChecked(false);
//                }
//                else
//                {
//                    ctv.setText("Permission Granted!");
//                    ctv.setChecked(true);
//                }
//
//            }
//        });

//        // Source code doesn't match byte code error in library for app icon
//        String icon = getIntent().getStringExtra("icon");
//        ApkInfoExtractor apk = new ApkInfoExtractor(main1.send_context);
//
//        ImageView imgView = (ImageView) findViewById(R.id.imageView);
//        imgView.setImageDrawable(apk.getAppIconByPackageName(icon));


        // Function for all granted permissions begin






        // Function for all granted permissions end




        //Testing begin for Recycler View

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_2);

        // Passing the column number 1 to show on line one column in each row.
        recyclerViewLayoutManager = new GridLayoutManager(Main_Activity2.this, 1);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new AppsAdapter_2(Main_Activity2.this, all_permissions);
        recyclerView.setAdapter(adapter);


        // Testing end for Recycler View

        }

}

