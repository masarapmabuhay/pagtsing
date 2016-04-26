/*
 * Copyright 2016 Michael Syson
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
package usbong.android.pagtsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import usbong.android.utils.UsbongConstants;
import usbong.android.utils.UsbongUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/*
 * This is Usbong's Main Menu activity. 
 */
public class UsbongMainActivity extends AppCompatActivity/*Activity*/ 
{	
	private Button startButton;
	private ImageView coverImage;
	private static UsbongMainActivity instance;
				
	public static String timeStamp;
	
//	private static Date startTime;	
	
	protected UsbongDecisionTreeEngineActivity myUsbongDecisionTreeEngineActivity;
	protected SettingsActivity mySettingsActivity;
	
	private static Activity myActivityInstance;
	private ProgressDialog myProgressDialog;
	
    private AlertDialog inAppSettingsDialog; //added by Mike, 20160417

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);

        //added by Mike, 27 Sept. 2015
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        myActivityInstance = this;
        
        //added by Mike, 25 Sept. 2015
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("UsbongKit");
//        getSupportActionBar().setDisplayUseLogoEnabled(true);        

//    	if (instance==null) { //comment this out, since the app seems to retain the instance even after we do a finish to GameActivity to close the app...
	        setContentView(R.layout.main);	        
	        instance = this;
//	    	startTime = new Date();
	    	
	        reset();
	        initMainMenuScreen();
    }
    
    public static UsbongMainActivity getInstance() {
    	return instance;
    }
    
    /*
     * Initialize this activity
     */
    public void init()
    {    	
    }
    
    public void initMainMenuScreen()
    {    		
/*    	
    	//added for Vincent, added by Mike, 4 Oct. 2015
		AlertDialog.Builder prompt = new AlertDialog.Builder(UsbongMainActivity.this);
		prompt.setTitle("Disclaimer");
		prompt.setMessage("This software is meant for alpha tesing only. By using this software, you agree that you are 'Vincent Licitri', and that you will not reproduce and distribute copies of this work or derivative works thereof in any medium, with or without modifications, and for commercial or non-commercial purposes, without the prior written permission of Usbong Social Systems, Inc.");
		prompt.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		prompt.show();
*/    	
    	startButton = (Button)findViewById(R.id.start_button);

		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {			
/*				
				reset(); //generate new timestamp
				Intent toUsbongDecisionTreeEngineActivityIntent = new Intent().setClass(UsbongMainActivity.getInstance(), UsbongDecisionTreeEngineActivity.class);
				toUsbongDecisionTreeEngineActivityIntent.putExtra("currScreen","0"); //make currScreen=0; meaning very first screen				
				startActivityForResult(toUsbongDecisionTreeEngineActivityIntent,1);
*/
/*				
				myActivityInstance.runOnUiThread(new Runnable() {
				    public void run() {
						if (myProgressDialog==null) {
							myProgressDialog = ProgressDialog.show(myActivityInstance, "Loading...",
									  "This takes only a short while.", true);								
						}
						else {
							if (!myProgressDialog.isShowing()) {
								myProgressDialog.show();								
							}
						}														
				    }
				});
*/				
/*
				//Reference: http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception;
				//last accessed: 9 Jan. 2014; answer by Dr.Luiji
				Thread thread = new Thread(new Runnable(){
				    @Override
				    public void run() {							    								    	
						try {							
							if (myProgressDialog==null) {
								myProgressDialog = ProgressDialog.show(myActivityInstance, "Loading...",
										  "This takes only a short while.", true);								
							}
							else {
								if (!myProgressDialog.isShowing()) {
									myProgressDialog.show();								
								}
							}														
						} catch (Exception e) {
//								myProgressDialog.dismiss();
					            e.printStackTrace();
						}
				    }
				});
				thread.start(); 											
*/				
/*				
				Looper.prepare();
				new CustomTask().execute((Void[])null);
*/				
/*				
				myProgressDialog = ProgressDialog.show(myActivityInstance, "Loading...",
						  "This takes only a short while.", true);								
*/
				reset(); //generate new timestamp
				Intent toUsbongDecisionTreeEngineActivityIntent = new Intent().setClass(UsbongMainActivity.getInstance(), UsbongDecisionTreeEngineActivity.class);
				toUsbongDecisionTreeEngineActivityIntent.putExtra("currScreen","0"); //make currScreen=0; meaning very first screen				
				startActivityForResult(toUsbongDecisionTreeEngineActivityIntent,1);
			}
    	});
/*		
    	coverImage = (ImageView)findViewById(R.id.cover_img);
    	Drawable d = getResources().getDrawable(getResources().getIdentifier("cover", "drawable", "usbong.android.kuto.alpha"));
    	coverImage.setImageDrawable(d);		        		        	
*/    	
    }
    
/*
    private class CustomTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... param) {
            //Do some work
			if (myProgressDialog==null) {
				myProgressDialog = ProgressDialog.show(myActivityInstance, "Loading...",
						  "This takes only a short while.", true);								
			}
			else {
				if (!myProgressDialog.isShowing()) {
					myProgressDialog.show();								
				}
			}														
            return null;
        }

        protected void onPostExecute(Void param) {
            //Print Toast or open dialog
        }
    }
*/    
    public void reset() {
    	UsbongUtils.generateDateTimeStamp(); //create a new timestamp for this "New Entry"
    }
/*
    private void showStatusDialog(String status)
	{
		AlertDialog.Builder prompt = new AlertDialog.Builder(UsbongMainActivity.this);
		prompt.setTitle("Notification");
		prompt.setMessage(status);
		prompt.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		prompt.show();
	}
*/	
    //added by Mike, 29 July 2015
    //Reference: http://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android;
    //last accessed: 29 Sept. 2015; answer by Nishant, 2 May 2012; edited by Daniel Nugent, 9 July 2015
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
            	if (myProgressDialog!=null) { 
            		myProgressDialog.dismiss();
            	}
//                String result=data.getStringExtra("result");
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }        
    }//onActivityResult

    //added by Mike, July 2, 2015
    @Override
	public void onBackPressed() {
    	//Reference: http://stackoverflow.com/questions/11495188/how-to-put-application-to-background
    	//; last accessed: 14 April 2015, answer by: JavaCoderEx
    	Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);    
    }
    
    //added by Mike, 25 Sept. 2015
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.title_menu, menu);
		return super.onCreateOptionsMenu(menu); 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{		
		switch(item.getItemId())
		{
			case(R.id.settings):
				//Reference: http://stackoverflow.com/questions/16954196/alertdialog-with-checkbox-in-android;
				//last accessed: 20160408; answer by: kamal; edited by: Empty2K12
				final CharSequence[] items = {UsbongConstants.AUTO_NARRATE_STRING, UsbongConstants.AUTO_PLAY_STRING, UsbongConstants.AUTO_LOOP_STRING};
				// arraylist to keep the selected items
				UsbongDecisionTreeEngineActivity.selectedSettingsItems=new ArrayList<Integer>();
				
				//check saved settings
				if (UsbongUtils.IS_IN_AUTO_NARRATE_MODE) {					
					UsbongDecisionTreeEngineActivity.selectedSettingsItems.add(UsbongConstants.AUTO_NARRATE);			
				}
				if (UsbongUtils.IS_IN_AUTO_PLAY_MODE) {
					UsbongDecisionTreeEngineActivity.selectedSettingsItems.add(UsbongConstants.AUTO_PLAY);	
					UsbongDecisionTreeEngineActivity.selectedSettingsItems.add(UsbongConstants.AUTO_NARRATE); //if AUTO_PLAY is checked, AUTO_NARRATE should also be checked
		    	}	        				
				if (UsbongUtils.IS_IN_AUTO_LOOP_MODE) {					
					UsbongDecisionTreeEngineActivity.selectedSettingsItems.add(UsbongConstants.AUTO_LOOP);			
				}
			    
				UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean = new boolean[items.length];
			    for(int k=0; k<items.length; k++) {
			    	UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean[k] = false;			    		
			    }
			    for(int i=0; i<UsbongDecisionTreeEngineActivity.selectedSettingsItems.size(); i++) {
			    	UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean[UsbongDecisionTreeEngineActivity.selectedSettingsItems.get(i)] = true;
			    }
			    		    
			    inAppSettingsDialog = new AlertDialog.Builder(this)
				.setTitle("Settings")
				.setMultiChoiceItems(items, UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean, new DialogInterface.OnMultiChoiceClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
				    	Log.d(">>>","onClick");

				    	if (isChecked) {
				            // If the user checked the item, add it to the selected items
				    		UsbongDecisionTreeEngineActivity.selectedSettingsItems.add(indexSelected);
				            if ((indexSelected==UsbongConstants.AUTO_PLAY) 
					        		&& !UsbongDecisionTreeEngineActivity.selectedSettingsItems.contains(UsbongConstants.AUTO_NARRATE)) {
				                final ListView list = inAppSettingsDialog.getListView();
				                list.setItemChecked(UsbongConstants.AUTO_NARRATE, true);
				            }				           
				        } else if (UsbongDecisionTreeEngineActivity.selectedSettingsItems.contains(indexSelected)) {
				        	if ((indexSelected==UsbongConstants.AUTO_NARRATE) 
				        		&& UsbongDecisionTreeEngineActivity.selectedSettingsItems.contains(UsbongConstants.AUTO_PLAY)) {
				                final ListView list = inAppSettingsDialog.getListView();
				                list.setItemChecked(indexSelected, false);
				        	}
				        	else {        	
					            // Else, if the item is already in the array, remove it
				        		UsbongDecisionTreeEngineActivity.selectedSettingsItems.remove(Integer.valueOf(indexSelected));
				        	}
				        }
				        
				        //updated selectedSettingsItemsInBoolean
					    for(int k=0; k<items.length; k++) {
					    	UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean[k] = false;			    		
					    }
					    for(int i=0; i<UsbongDecisionTreeEngineActivity.selectedSettingsItems.size(); i++) {
					    	UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean[UsbongDecisionTreeEngineActivity.selectedSettingsItems.get(i)] = true;
					    }
				    }
				}).setPositiveButton("OK", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int id) {
				    	 try {	    	
				 			InputStreamReader reader = UsbongUtils.getFileFromSDCardAsReader(UsbongUtils.BASE_FILE_PATH + "usbong.config");	
				 			BufferedReader br = new BufferedReader(reader);    		
				 	    	String currLineString;        	

				 	    	//write first to a temporary file
							PrintWriter out = UsbongUtils.getFileFromSDCardAsWriter(UsbongUtils.BASE_FILE_PATH + "usbong.config" +"TEMP");

				 	    	while((currLineString=br.readLine())!=null)
				 	    	{ 	
				 	    		Log.d(">>>", "currLineString: "+currLineString);
								if ((currLineString.contains("IS_IN_AUTO_NARRATE_MODE="))
								|| (currLineString.contains("IS_IN_AUTO_PLAY_MODE="))
								|| (currLineString.contains("IS_IN_AUTO_LOOP_MODE="))) {
									continue;
								}	
								else {
									out.println(currLineString);			 	    		
								}
				 	    	}	        				

							for (int i=0; i<items.length; i++) {
								Log.d(">>>>", i+"");
								if (UsbongDecisionTreeEngineActivity.selectedSettingsItemsInBoolean[i]==true) {
									if (i==UsbongConstants.AUTO_NARRATE) {
							    		out.println("IS_IN_AUTO_NARRATE_MODE=ON");
							    		UsbongUtils.IS_IN_AUTO_NARRATE_MODE=true;							
									}								
									else if (i==UsbongConstants.AUTO_PLAY) {
							    		out.println("IS_IN_AUTO_PLAY_MODE=ON");
							    		UsbongUtils.IS_IN_AUTO_PLAY_MODE=true;						
	/*						    		
							    		//if auto_play is ON, auto_narrate is also ON
							    		//however, it is possible to have auto_play OFF,
							    		//while auto_narrate is ON
							    		out.println("IS_IN_AUTO_NARRATE_MODE=ON");
							    		UsbongUtils.IS_IN_AUTO_NARRATE_MODE=true;
	*/						    									
									}	
									else if (i==UsbongConstants.AUTO_LOOP) {
							    		out.println("IS_IN_AUTO_LOOP_MODE=ON");
							    		UsbongUtils.IS_IN_AUTO_LOOP_MODE=true;						
									}
								}
								else {
									if (i==UsbongConstants.AUTO_NARRATE) {
							    		out.println("IS_IN_AUTO_NARRATE_MODE=OFF");
							    		UsbongUtils.IS_IN_AUTO_NARRATE_MODE=false;															
									}							
									else if (i==UsbongConstants.AUTO_PLAY) {
							    		out.println("IS_IN_AUTO_PLAY_MODE=OFF");
							    		UsbongUtils.IS_IN_AUTO_PLAY_MODE=false;	
									}
									else if (i==UsbongConstants.AUTO_LOOP) {
							    		out.println("IS_IN_AUTO_LOOP_MODE=OFF");
							    		UsbongUtils.IS_IN_AUTO_LOOP_MODE=false;	
									}
								}				
							}					
					    	out.close(); //remember to close
					    	
					    	//copy temp file to actual usbong.config file
				 			InputStreamReader reader2 = UsbongUtils.getFileFromSDCardAsReader(UsbongUtils.BASE_FILE_PATH + "usbong.config"+"TEMP");	
				 			BufferedReader br2 = new BufferedReader(reader2);    		
				 	    	String currLineString2;        	

				 	    	//write to actual usbong.config file
							PrintWriter out2 = UsbongUtils.getFileFromSDCardAsWriter(UsbongUtils.BASE_FILE_PATH + "usbong.config");

				 	    	while((currLineString2=br2.readLine())!=null)
				 	    	{ 	
								out2.println(currLineString2);			 	    		
				 	    	}			 	    	
				 	    	out2.close();
				 	    	
				 	    	UsbongUtils.deleteRecursive(new File(UsbongUtils.BASE_FILE_PATH + "usbong.config"+"TEMP"));
				 		}
				 		catch(Exception e) {
				 			e.printStackTrace();
				 		}			 		
				    }
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int id) {
				        //  Your code when user clicked on Cancel
				    }
				}).create();
				inAppSettingsDialog.show();
	/*			
			    	new AlertDialog.Builder(UsbongDecisionTreeEngineActivity.this).setTitle("Settings")
					.setMessage("Automatic voice-over narration:")
//					.setView(requiredFieldAlertStringTextView)
			    	.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							UsbongUtils.isInAutoVoiceOverNarration=true;
						}
			    	})
				    .setNegativeButton("Turn Off", new DialogInterface.OnClickListener() {					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							UsbongUtils.isInAutoVoiceOverNarration=false;
						}
					}).show();
	*/				
					return true;
			case(R.id.about):
		    	new AlertDialog.Builder(UsbongMainActivity.this).setTitle("About")
				.setMessage(UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.this,"credits.txt")) //don't add a '/', otherwise the file would not be found
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}