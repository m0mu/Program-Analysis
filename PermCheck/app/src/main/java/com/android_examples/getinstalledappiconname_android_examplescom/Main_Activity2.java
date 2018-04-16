package com.android_examples.getinstalledappiconname_android_examplescom;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;

public class Main_Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

//        AppsAdapter aps = new AppsAdapter(this, new ApkInfoExtractor(this).GetAllInstalledApkInfo());
        AppsAdapter aps = new AppsAdapter(getApplicationContext(), new ApkInfoExtractor(getApplicationContext()).GetAllInstalledApkInfo());



//        int perm = aps.context1.checkCallingOrSelfPermission(RECORD_AUDIO);

//        if(perm == 0) {
//            TextView textView = (TextView) findViewById(R.id.Permission);
//            textView.setText("CAN record");}
//        else { TextView textView = (TextView) findViewById(R.id.Permission);
//                textView.setText("can NOT record!");}


        
        
        if (PermissionChecker.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            //Read Phone state
            TextView textView = (TextView) findViewById(R.id.Permission);
            textView.setText("CAN record");
        } else {
            TextView textView = (TextView) findViewById(R.id.Permission);
            textView.setText("CAN NOT record");
        }
    }
}

