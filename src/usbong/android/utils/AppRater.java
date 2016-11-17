/*
 * Copyright 2016 Usbong Social Systems, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//Reference: http://stackoverflow.com/questions/14514579/how-to-implement-rate-it-feature-in-android-app;
//last accessed: 20161117
//answer by: Raghav Sood

package usbong.android.utils;

import usbong.android.pagtsing.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppRater {
    private static String APP_TITLE;//= "App Name";// App Name
    //private static String TITLE = "PagTsing";
	private final static String APP_PNAME = UsbongUtils.myPackageName;//"com.example.name";// Package Name
/*
    private final static int DAYS_UNTIL_PROMPT = 3;//Min number of days
    private final static int LAUNCHES_UNTIL_PROMPT = 3;//Min number of launches
*/
    private static SharedPreferences prefs;
    
//    public static void app_launched(Context mContext) {
      public static void showRateDialog(Context mContext) {

    	APP_TITLE = mContext.getString(R.string.app_name).replace("Usbong ", "");
    	
        prefs = mContext.getSharedPreferences("app_rater", 0);
        if (prefs.getBoolean("dont_show_again", false)) { 
        	return;
        }
        else {
            showRateDialog(mContext, prefs.edit());        	
        }
        
/*
        SharedPreferences.Editor editor = prefs.edit();
        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch + 
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);
            }
        }
        editor.commit();
*/
    }   

/*
    public static void showRateDialog(final Context mContext) {//, final SharedPreferences.Editor editor) {    	
        SharedPreferences prefs = mContext.getSharedPreferences("app_rater", 0);
        final SharedPreferences.Editor editor = prefs.edit();
*/
      public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {    	      
    	AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("How many stars would you give '" + APP_TITLE +"'?");
        dialog.setMessage("What do you think? Please share your opinion with others on Google Play.");
        dialog.setIcon(R.drawable.pagtsing_icon); //change this to something generic
        /*
        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(mContext);
        tv.setText("If you enjoy using " + APP_TITLE + ", please take a moment to rate it. Thanks for your support!");
        tv.setWidth(240);
        tv.setPadding(4, 0, 4, 10);
        ll.addView(tv);
*/
        
        dialog.setPositiveButton("Rate.",
	        new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                if (editor != null) {
	                    editor.putBoolean("dont_show_again", true);
	                    editor.commit();
	                }
	            	mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
	            	dialog.dismiss();
	            }
	        });
        
        dialog.setNeutralButton("Later.",
	        new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	dialog.dismiss();
	            }
        });

        
		// cancel button
        dialog.setNegativeButton("No, thanks.",
	        new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                if (editor != null) {
	                    editor.putBoolean("dont_show_again", true);
	                    editor.commit();
	                }
	            	dialog.dismiss();
	            }
	    });
        
/*        
        Button b1 = new Button(mContext);
        b1.setText("Rate " + APP_TITLE);
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
                dialog.dismiss();
            }
        });        
        ll.addView(b1);

        Button b2 = new Button(mContext);
        b2.setText("Remind me later");
        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ll.addView(b2);

        Button b3 = new Button(mContext);
        b3.setText("No, thanks");
        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dont_show_again", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        ll.addView(b3);
*/
//        dialog.setContentView(ll);        
        dialog.show();        
    }
}