package cch.selflog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import cch.mobile.adapter.ActionListAdapter;
import cch.mobile.util.DBUtil;
import cch.mobile.util.FormatUtil;
import cch.selflog.busi.DailyInfoBusi;
import cch.selflog.model.DailyInfo;

public class ActionListActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		u_init();
	}
	private void u_init(){
		
//		List<Map<String, Object>> list = getDailyInfo();
//		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.action_list_item,
//				new String[]{"id","feeDate","itemName","fee","personName"},
//				new int[]{R.id.tbox_item_id,R.id.tbox_item_feeDate,R.id.tbox_item_itemName,R.id.tbox_item_fee,R.id.tbox_item_personName});
		ActionListAdapter simpleAdapter = new ActionListAdapter(this,R.layout.action_list_item,
				new String[]{"id","feeDate","itemName","fee","personName"},
				new int[]{R.id.tbox_item_id,R.id.tbox_item_feeDate,R.id.tbox_item_itemName,R.id.tbox_item_fee,R.id.tbox_item_personName});
		this.setListAdapter(simpleAdapter);
		//this.getListView().seton
		//simpleAdapter.getView(position, convertView, parent).
		
	}
	public void refresh(){
		ActionListAdapter adapter = (ActionListAdapter)this.getListAdapter();
		adapter.refresh();
	}
	
	
	
}
