package usbong.android.utils;

import java.util.ArrayList;

import usbong.android.pagtsing.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;

public class PurchaseLanguageBundleListAdapter extends BaseAdapter
{
	private int languageBundleListSize=2; //default is 2
    private ArrayList<String> defaultSkuList;
	private Activity myActivity;
	private String[][] languageBundleList;
    private IInAppBillingService mService; //added by Mike, 20160426	
	private Bundle buyIntentBundle; //added by Mike, 20160426	
	
	private TextView nameOfBundleText;
	private TextView priceOfBundleText;
	private Button buyButton;
	
	//added by Mike, 20160425
    private static final String MY_PURCHASED_ITEMS = "MyPurchasedItems";

	public PurchaseLanguageBundleListAdapter(Activity a, Bundle ownedItems, IInAppBillingService mS)
	{
		myActivity = a;
		mService = mS;
		
	    languageBundleList = new String[languageBundleListSize][2];
		languageBundleList[0][0] = "All Local Languages";
		languageBundleList[1][0] = "All Foreign Languages";
		languageBundleList[0][1] = UsbongConstants.DEFAULT_PRICE;
		languageBundleList[1][1] = UsbongConstants.DEFAULT_PRICE;

	    defaultSkuList = new ArrayList<String> ();
	    defaultSkuList.add(UsbongConstants.ALL_LOCAL_LANGUAGES_PRODUCT_ID);
	    defaultSkuList.add(UsbongConstants.ALL_FOREIGN_LANGUAGES_PRODUCT_ID);
		
		//added by Mike, 20160425
	    int response=-1;
	    if (ownedItems!=null) {
			response = ownedItems.getInt("RESPONSE_CODE");
	    }
	    else {
	        //Reference: http://stackoverflow.com/questions/23024831/android-shared-preferences-example
	        //; last accessed: 20150609
	        //answer by Elenasys
	        //added by Mike, 20160425
	        SharedPreferences prefs = myActivity.getSharedPreferences(MY_PURCHASED_ITEMS, android.content.Context.MODE_PRIVATE);
	        if (prefs!=null) {
				languageBundleList[0][1] = prefs.getString(UsbongConstants.ALL_LOCAL_LANGUAGES_PRODUCT_ID, UsbongConstants.DEFAULT_PRICE);
				languageBundleList[1][1] = prefs.getString(UsbongConstants.ALL_FOREIGN_LANGUAGES_PRODUCT_ID, UsbongConstants.DEFAULT_PRICE);
	        }
	    }

		if (response == 0) { //SUCCESS
		   ArrayList<String> ownedSkus =
		      ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
		   ArrayList<String>  purchaseDataList =
		      ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
		   ArrayList<String>  signatureList =
		      ownedItems.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
/*//not used
		   String continuationToken =
		      ownedItems.getString("INAPP_CONTINUATION_TOKEN");
*/
		   for (int i = 0; i < purchaseDataList.size(); ++i) {
		      String purchaseData = purchaseDataList.get(i);
		      String signature = signatureList.get(i);
		      String sku = ownedSkus.get(i);

		      // do something with this purchase information
		      // e.g. display the updated list of products owned by user
		      for (int k=0; k < defaultSkuList.size(); k++) {
			      if (sku.equals(defaultSkuList.get(k))) {
			    	  if (sku.contains("local")) {
		    			languageBundleList[0][1] = "Owned";
			    	  }
			    	  else { //foreign
			    		languageBundleList[1][1] = "Owned";			    		  
			    	  }
			      }
		      }
		   }
		   // if continuationToken != null, call getPurchases again
		   // and pass in the token to retrieve more items
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return languageBundleList.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return languageBundleList[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		LayoutInflater inflater = myActivity.getLayoutInflater();
		View view = null;
		try {
			if (convertView==null)
			{
				view = inflater.inflate(R.layout.purchase_language_bundle_list_selection, null);
			}
			else
			{
				view = convertView;
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		/*//commented out by Mike, 10 June 2015			
		ImageView image = (ImageView) view.findViewById(R.id.imageView1);
*//*
		ImageView banner_image = (ImageView) view.findViewById(R.id.banner_imageView);
*/
		
		nameOfBundleText = (TextView) view.findViewById(R.id.textView1);
		priceOfBundleText = (TextView) view.findViewById(R.id.textView2);		
		buyButton = (Button) view.findViewById(R.id.buyButton);
		
		nameOfBundleText.setText(languageBundleList[position][0]);
		priceOfBundleText.setText(languageBundleList[position][1]);
		
		if (languageBundleList[position][1].equals("Owned")) {
			buyButton.setFocusable(false);
			buyButton.setVisibility(Button.INVISIBLE);
		}
		else {
			buyButton.setFocusable(true);
			buyButton.setVisibility(Button.VISIBLE);			
		}
		
		final int pos = position;		
		buyButton.setOnClickListener(new OnClickListener() {           
			  @Override
			  public void onClick(View v) 
			  {
				  try {
				    if (mService==null) {
				    	UsbongUtils.initInAppBillingService(myActivity);
				    	mService = UsbongUtils.getInAppMService();
				    }
				    
				    UsbongUtils.generateDateTimeStamp();
				    if (mService!=null) {
						buyIntentBundle = mService.getBuyIntent(3, myActivity.getPackageName(),
								  defaultSkuList.get(pos), "inapp", UsbongUtils.getDateTimeStamp());
	
						PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
						myActivity.startIntentSenderForResult(pendingIntent.getIntentSender(),
								   1001, new Intent(), Integer.valueOf(0), Integer.valueOf(0),
								   Integer.valueOf(0));
				    }
				    else {				    					    	
				    	new AlertDialog.Builder(myActivity).setTitle("Connection Failure")
	            		.setMessage("Unable to connect to Google Play. Please make sure that you are connected to the internet.")
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {					
							@Override
							public void onClick(DialogInterface dialog, int which) {	            				
							}
						}).show();
				    }
				  } catch (RemoteException | SendIntentException e) {
						e.printStackTrace();
				  }				
//				  Log.d(">>>>","pressed!"+defaultSkuList.get(pos));
//				  Log.d(">>>>","pressed!"+languageBundleList[pos][0]);
			  }    
		});		
		
		return view;
	}
}