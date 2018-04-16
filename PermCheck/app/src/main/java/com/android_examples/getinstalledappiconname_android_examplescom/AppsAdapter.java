package com.android_examples.getinstalledappiconname_android_examplescom;

/**
 * Created by Juned on 4/14/2017.
 */

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder>{

    Context context1;
    List<String> stringList;

    public AppsAdapter(Context context, List<String> list){

        context1 = context;

        stringList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public ImageView imageView;
        public TextView textView_App_Name;
        public TextView textView_App_Package_Name;
        public TextView textView_perm_Name;

        public ViewHolder (View view){

            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view);
            imageView = (ImageView) view.findViewById(R.id.imageview);
            textView_App_Name = (TextView) view.findViewById(R.id.Apk_Name);
            textView_App_Package_Name = (TextView) view.findViewById(R.id.Apk_Package_Name);
            textView_perm_Name = (TextView) view.findViewById(R.id.Permissions);
        }
    }

    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view2 = LayoutInflater.from(context1).inflate(R.layout.cardview_layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view2);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){

        ApkInfoExtractor apkInfoExtractor = new ApkInfoExtractor(context1);

        final String ApplicationPackageName = (String) stringList.get(position);
        String ApplicationLabelName = apkInfoExtractor.GetAppName(ApplicationPackageName);
        Drawable drawable = apkInfoExtractor.getAppIconByPackageName(ApplicationPackageName);


        String perm =  apkInfoExtractor.GetPermissions(Manifest.permission.CAMERA, ApplicationPackageName);
        System.out.print(perm);


        viewHolder.textView_App_Name.setText(ApplicationLabelName);

        viewHolder.textView_App_Package_Name.setText(ApplicationPackageName);

        viewHolder.imageView.setImageDrawable(drawable);

        System.out.print(PermissionChecker.checkSelfPermission(context1, RECORD_AUDIO));
        System.out.print(PermissionChecker.checkSelfPermission(context1, Manifest.permission.RECORD_AUDIO));

        if(PermissionChecker.checkSelfPermission(context1, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            //Read Phone state)
            viewHolder.textView_perm_Name.setText(perm);
        }else{
            viewHolder.textView_perm_Name.setText(perm);
        }


//        if(perm == 0) { viewHolder.textView_perm_Name.setText("Can record audio!");}
//        else { viewHolder.textView_perm_Name.setText("Can not record audio!");}


        //Adding click listener on CardView to open clicked application directly from here .
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = context1.getPackageManager().getLaunchIntentForPackage(ApplicationPackageName);
                if(intent != null){

                    Intent intent1 = new Intent(context1, Main_Activity2.class);
                    context1.startActivity(intent1);

//                    context1.startActivity(intent);

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

}