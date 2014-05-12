package cch.selflog;

import java.util.ArrayList;
import java.util.List;

import cch.mobile.util.DBUtil;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MiscActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		u_init();
	}

	private void u_init(){
//		MiscListAdapter simpleAdapter = new MiscListAdapter(this,R.layout.misc,
//				new String[]{"id"},
//				new int[]{R.id.tbox_misc_action});
//		this.setListAdapter(simpleAdapter);
		ListView listview = this.getListView();
		listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		TextView tv = (TextView)v;
		if(tv.getText().equals("数据库初始化")){
			Context c = v.getContext();
			DBUtil db = DBUtil.getInstance(c,c.getString(R.string.databaseName));
			db.exeSQL(c.getString(R.string.dropdatabasescript));
			db.exeSQL(c.getString(R.string.databasescript));
			Toast.makeText(this, "数据库初始化成功！", 500).show();
			
		}else{
			Toast.makeText(this, "test", 500).show();
		}
	}

	private List<String> getData(){
		List<String> list = new ArrayList<String>();
		list.add("数据库初始化");
		list.add("批量提交");
		return list;
	}
}
