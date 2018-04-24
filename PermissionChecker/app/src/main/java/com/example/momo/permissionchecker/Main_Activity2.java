package com.example.momo.permissionchecker;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
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
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;

public class Main_Activity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    List<String> all_permissions = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);


        String applicationPackageName = getIntent().getStringExtra("ApplicationPackageName");
        ApkInfoExtractor apk = new ApkInfoExtractor(Main_Activity2.this);
        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setImageDrawable(apk.getAppIconByPackageName(applicationPackageName));

//        TextView textView = (TextView) findViewById(R.id.Permission);
//        textView.setText(getIntent().getStringExtra("Permission"));
        final String perm = apk.GetPermissions(Manifest.permission.CAMERA, applicationPackageName);
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

        all_permissions = apk.Get_Granted_Permissions(applicationPackageName);

        int count = 0;
        for (String permission: all_permissions)
        {
            all_permissions.set(count, permission.replace("android.permission.",""));
            count ++;

        }

        final CheckedTextView ctv = (CheckedTextView) findViewById(R.id.checkedTextView1);
        if (perm.equalsIgnoreCase( "Permission Granted!"))
        {
            ctv.setText(perm);
            ctv.setChecked(true);

        }
//                if (ctv.isChecked())
//                    ctv.setChecked(false);
        else {
            ctv.setText(perm);
        }

        ctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctv.isChecked()) {
                    ctv.setText("Permission Denied!");
                    ctv.setChecked(false);
                }
                else
                {
                    ctv.setText("Permission Granted!");
                    ctv.setChecked(true);
                }

            }
        });

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

