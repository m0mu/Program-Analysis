package com.android_examples.getinstalledappiconname_android_examplescom;



import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class AppsAdapter_2 extends RecyclerView.Adapter<AppsAdapter_2.ViewHolder>{

    Context context1;
    List<String> stringList;




//    public android.support.v7.widget.RecyclerView.Adapter send_Context_and_List(Context context, List<String> list)
//    {
//        context1 = context;
//        stringList = list;
//
//
//    }

    public AppsAdapter_2(Context context, List<String> list){

        context1 = context;

        stringList = list;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView textView_App_Package_Name;
        public TextView textView_perm_Name;
        public CheckedTextView chk;




        public ViewHolder (View view){

            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view_2);
            textView_App_Package_Name = (TextView) view.findViewById(R.id.Apk_Package_Name);
            textView_perm_Name = (TextView) view.findViewById(R.id.Permissions);
            chk = (CheckedTextView) view.findViewById(R.id.ctv);
        }
    }

    @Override
    public AppsAdapter_2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view2 = LayoutInflater.from(context1).inflate(R.layout.cardview_2,parent,false);

        ViewHolder viewHolder = new ViewHolder(view2);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        final String ApplicationPackageName = (String) stringList.get(position);//
        viewHolder.textView_App_Package_Name.setText(ApplicationPackageName);
        viewHolder.chk.setChecked(true);



        //Adding click listener on CardView to open clicked application directly from here .
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewHolder.chk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewHolder.chk.isChecked()) {
//                            viewHolder.chk.setText("Permission Denied!");
                            final Toast toast = Toast.makeText(context1,ApplicationPackageName+" Permission Denied!", Toast.LENGTH_SHORT);
                            toast.show();
                            viewHolder.chk.setChecked(false);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);

                            // ADD CODE TO REMOVE THE PERMISSION FROM THE MANIFEST
                            // OR REMOVE PERMISSION JAVA CODE
                            // One touch add permissions plugin








                        } else {
//                            viewHolder.chk.setText("Permission Granted!");
                            final Toast toast = Toast.makeText(context1,ApplicationPackageName+" Permission Granted!", Toast.LENGTH_SHORT);
                            toast.show();
                            viewHolder.chk.setChecked(true);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        }


                        // ADD CODE TO ADD THE PERMISSION TO THE MANIFEST


//                Intent intent = context1.getPackageManager().getLaunchIntentForPackage(ApplicationPackageName);
//                if(intent != null){
//                    Intent intent1 = new Intent(context1, Main_Activity2.class);
//                    //// TEST
//                    intent1.putExtra("ApplicationPackageName", ApplicationPackageName);
//                    //// TEST
//                    context1.startActivity(intent1);
//                }
//                else {
//
//                    Toast.makeText(context1,ApplicationPackageName + " Error, Please Try Again.", Toast.LENGTH_LONG).show();
//                }
                    }
                });
            }
        });
    };

    @Override
    public int getItemCount(){

        return stringList.size();
    }

}