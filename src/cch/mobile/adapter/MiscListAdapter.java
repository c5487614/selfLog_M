package cch.mobile.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MiscListAdapter extends BaseAdapter {

	private List<Map<String, Object>> list;
	private Context context;
	private int resId;
	private String[] froms;
	private int[] tos;
	public MiscListAdapter(Context c,List<Map<String,Object>> list,int r,String[] f,int[] t){
		this.context = c;
		this.resId = r;
		this.froms = f;
		this.tos = t;
		this.list = list;
	}
	public MiscListAdapter(Context c,int r,String[] f,int[] t){
		this.context = c;
		this.resId = r;
		this.froms = f;
		this.tos = t;
		this.list = this.getListItems();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View v, ViewGroup vg) {
		// TODO Auto-generated method stub
		if(v==null){
			v = LayoutInflater.from(context).inflate(resId, null);
			for(int i = 0; i< this.list.size();i++){
				for(int j = 0; j < tos.length; j++){
					TextView tv = (TextView) v.findViewById(tos[j]);
					tv.setText(list.get(position).get(froms[j]).toString());
					tv.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//if(position==0){}
						}
						
					});
				}
			}
		}else{
//			v.getTag()
		}
		return v;
	}

	private List<Map<String, Object>> getListItems(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "Database Initial");
		list.add(map);
		return list;
	}
}
