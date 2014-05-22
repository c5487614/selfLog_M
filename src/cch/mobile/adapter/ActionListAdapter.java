package cch.mobile.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cch.mobile.util.DBUtil;
import cch.mobile.util.FormatUtil;
import cch.selflog.R;
import cch.selflog.busi.DailyInfoBusi;
import cch.selflog.model.DailyInfo;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ActionListAdapter extends BaseAdapter {

	private List<Map<String, Object>> list;
	private Context context;
	private int resId;
	private String[] froms;
	private int[] tos;
	public ActionListAdapter(Context c,List<Map<String,Object>> list,int r,String[] f,int[] t){
		this.context = c;
		this.resId = r;
		this.froms = f;
		this.tos = t;
		this.list = list;
	}
	public ActionListAdapter(Context c,int r,String[] f,int[] t){
		this.context = c;
		this.resId = r;
		this.froms = f;
		this.tos = t;
		this.list = this.getDailyInfo();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View v, ViewGroup vg) {
		// TODO Auto-generated method stub
		ViewHolder vholder = null;
		if(v==null){
			v = LayoutInflater.from(context).inflate(resId, null);
			vholder = new ViewHolder();
			for(int i = 0; i< this.list.size();i++){
				int j = 0;
				TextView tv = (TextView) v.findViewById(tos[0]);
				vholder.tvId = tv; 
				tv = (TextView) v.findViewById(tos[1]);
				vholder.tvfeeDate = tv;
				tv = (TextView) v.findViewById(tos[2]);
				vholder.tvitemName = tv;
				tv = (TextView) v.findViewById(tos[3]);
				vholder.tvFee = tv;
				tv = (TextView) v.findViewById(tos[4]);
				vholder.tvPersonName = tv;
				v.setTag(vholder);
			}
			int id = Integer.parseInt(list.get(position).get("id").toString());
			View btnDelete = v.findViewById(R.id.btn_item_delete);
			if(btnDelete instanceof Button){
				OnDeleteClick deleteClick = new OnDeleteClick(id);
				btnDelete.setOnClickListener(deleteClick);
			}
			View btnCommit = v.findViewById(R.id.btn_item_commit);
			if(btnCommit instanceof Button){
				OnCommitClick commitClick = new OnCommitClick(id);
				btnCommit.setOnClickListener(commitClick);
			}
		}else{
			vholder = (ViewHolder) v.getTag();
			vholder.resetHolder();
		}
		vholder.tvId.setText(list.get(position).get(froms[0]).toString());
		vholder.tvfeeDate.setText(list.get(position).get(froms[1]).toString());
		vholder.tvitemName.setText(list.get(position).get(froms[2]).toString());
		vholder.tvFee.setText(list.get(position).get(froms[3]).toString());
		vholder.tvPersonName.setText(list.get(position).get(froms[4]).toString());
		return v;
	}
	
	public void refresh(){
		this.list = getDailyInfo();
		this.notifyDataSetChanged();
	}
	private List<Map<String, Object>> getDailyInfo(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		DailyInfoBusi dialyInfoBusi = new DailyInfoBusi(context);
		
		List<DailyInfo> listDailyInfo = dialyInfoBusi.getDailyInfo();
		for (DailyInfo dailyInfo : listDailyInfo) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", dailyInfo.getId());
			item.put("feeDate", FormatUtil.dateFormat("yyyy-MM-dd", dailyInfo.getFeeDate()));
			item.put("itemName", dailyInfo.getItemName());
			item.put("fee", dailyInfo.getFee());
			item.put("personName", dailyInfo.getPersonName());
			list.add(item);
		}
		return list;
	}

}
class ViewHolder{
	public TextView tvId;
	public TextView tvfeeDate;
	public TextView tvitemName;
	public TextView tvFee;
	public TextView tvPersonName;
	
	public void resetHolder(){
		this.tvId.setText(null);
		this.tvfeeDate.setText(null);
		this.tvitemName.setText(null);
		this.tvFee.setText(null);
		this.tvPersonName.setText(null);
	}
}
