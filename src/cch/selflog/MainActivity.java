package cch.selflog;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import cch.mobile.util.DBUtil;
import cch.mobile.util.FormatUtil;
import cch.mobile.util.SpinnerUtil;
import cch.selflog.busi.DailyInfoBusi;
import cch.selflog.model.DailyInfo;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
			u_init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void u_init() throws Exception{
    	EditText tbox_feeDate =(EditText) this.findViewById(R.id.tbox_feeDate);
    	String now = DateFormat.format("yyyy-MM-dd", new Date()).toString();
    	tbox_feeDate.setText(now);
    	
    	Button btn_cancel = (Button) this.findViewById(R.id.btn_cancel);
    	btn_cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
    		
    	});
    	//Spinner cbox_items = (Spinner) this.findViewById(R.id.cbox_item);
    	SpinnerUtil.SetSpinner(this, R.id.cbox_item, R.array.list_item);
    	SpinnerUtil.SetSpinner(this, R.id.cbox_personName, R.array.list_person);
    	SpinnerUtil.SetSpinner(this, R.id.cbox_itemType, R.array.list_feeType);
    	
    	Button btn_commit = (Button) this.findViewById(R.id.btn_commit);
    	btn_commit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DailyInfo model = new DailyInfo();
				model = view2Model();
				Context c = v.getContext();
				DailyInfoBusi dailyInfoBusi = new DailyInfoBusi(c);
				
				dailyInfoBusi.insert(model);
				Toast.makeText(v.getContext(), "²Ù×÷³É¹¦£¡", 500).show();
			}
    		
    	});
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.menu_db){
        	Toast.makeText(this, "Hello World!", 1000).show();
        }else if(id == R.id.menu_view){
        	Intent intent = new Intent();
        	intent.setClass(this, ActionListActivity.class);
        	this.startActivity(intent);
        }else if(id == R.id.menu_misc){
        	Intent intent = new Intent();
        	intent.setClass(this, MiscActivity.class);
        	this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private DailyInfo view2Model(){
    	DailyInfo model = new DailyInfo();
    	EditText tbox_fee = (EditText) this.findViewById(R.id.tbox_fee);
    	model.setFee(FormatUtil.parseDouble(tbox_fee.getText().toString()));
    	
    	EditText tbox_feeDate = (EditText) this.findViewById(R.id.tbox_feeDate);
    	model.setFeeDate(FormatUtil.string2Date((tbox_feeDate.getText().toString())));
    	
    	model.setFillDate(new Date());
    	model.setIsCommited("N");
    	model.setIsPaid("N");
    	
    	Spinner spinner_itemName = (Spinner) this.findViewById(R.id.cbox_item);
    	model.setItemName(spinner_itemName.getSelectedItem().toString());
    	
    	Spinner spinner_itemType = (Spinner) this.findViewById(R.id.cbox_itemType);
    	model.setItemType(spinner_itemType.getSelectedItem().toString());
    	
    	model.setPCInfo("android");
    	
    	Spinner spinner_personName = (Spinner) this.findViewById(R.id.cbox_personName);
    	model.setPersonName(spinner_personName.getSelectedItem().toString());
    	
    	EditText tbox_comment = (EditText) this.findViewById(R.id.tbox_comment);
    	model.setComment(tbox_comment.getText().toString());
    	
    	return model;
    }
}
