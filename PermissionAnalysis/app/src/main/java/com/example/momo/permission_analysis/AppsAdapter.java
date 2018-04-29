package com.example.momo.permission_analysis;


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
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
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

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder>{

    Context context1;
    List<String> stringList;


//    public android.support.v7.widget.RecyclerView.Adapter send_Context_and_List(Context context, List<String> list)
//    {
//        context1 = context;
//        stringList = list;
//
//
//    }

    public AppsAdapter(Context context, List<String> list){

        context1 = context;

        stringList = list;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public ImageView imageView;
        public TextView textView_App_Name;
//        public TextView textView_App_Package_Name;
        public TextView textView_score;

        public ViewHolder (View view){

            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view);
            imageView = (ImageView) view.findViewById(R.id.imageview);
            textView_App_Name = (TextView) view.findViewById(R.id.Apk_Name);
//            textView_App_Package_Name = (TextView) view.findViewById(R.id.Apk_Package_Name);
            textView_score = (TextView) view.findViewById(R.id.Score);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view2 = LayoutInflater.from(context1).inflate(R.layout.cardview_layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view2);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){

        final ApkInfoExtractor apkInfoExtractor = new ApkInfoExtractor(context1);

        final String ApplicationPackageName = (String) stringList.get(position);
        final String ApplicationLabelName = apkInfoExtractor.GetAppName(ApplicationPackageName);
        final Drawable drawable = apkInfoExtractor.getAppIconByPackageName(ApplicationPackageName);


//        final String perm =  apkInfoExtractor.GetPermissions(Manifest.permission.CAMERA, ApplicationPackageName);


        viewHolder.textView_App_Name.setText(ApplicationLabelName);

//        viewHolder.textView_App_Package_Name.setText(ApplicationPackageName);

        viewHolder.imageView.setImageDrawable(drawable);

        Main_Activity2 m2 = new Main_Activity2();
//        viewHolder.textView_score.setText(m2.score);



        //Adding click listener on CardView to open clicked application directly from here .
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = context1.getPackageManager().getLaunchIntentForPackage(ApplicationPackageName);
                if(intent != null){

//                    String type = pass_this_name.getClass().getName();


//                    MainActivity test1 = new MainActivity();
//                    String test2 = test1.get_name(pass_this_name);

//                    MainActivity test3 = new MainActivity();
//                    String test4 = test3.get_name(pass_this_name);
                    Intent intent1 = new Intent(context1, Main_Activity2.class);

//                    intent1.putExtra("Permission", perm);

                    //// TEST
                    intent1.putExtra("ApplicationPackageName", ApplicationPackageName);

                    //// TEST


                    context1.startActivity(intent1);

                }
                else {

                    Toast.makeText(context1,ApplicationPackageName + " Error, Please Try Again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount(){

        return stringList.size();
    }

//    public String send_name() {
//
//        Main_Activity2 test = new Main_Activity2();
//        String test2 = test.get_name(pass_this_name);
//        return test2;
//    }
}