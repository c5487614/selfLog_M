package cch.mobile.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import cch.selflog.R;

public class SpinnerUtil {
//	static int textViewResId = android.R.layout.simple_spinner_item;
//	static int dropViewResId = android.R.layout.simple_spinner_dropdown_item;
	public static void SetSpinner(Context context,Spinner spinner,int dataResId){
		
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, 
				dataResId, android.R.layout.simple_spinner_item);
    	arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(arrayAdapter);
	}
	
	public static void SetSpinner(Context context,int spinnerId,int dataResId) throws Exception{
		Activity a = (Activity)context;
		Spinner spinner = (Spinner) a.findViewById(spinnerId);
		if(spinner!=null){
			SetSpinner(context,spinner,dataResId);
		}else{
			throw new Exception("NULL");
		}
		
	}
}
