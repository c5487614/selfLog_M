package cch.mobile.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cch.selflog.ActionListActivity;
import cch.selflog.busi.DailyInfoBusi;

public class OnDeleteClick implements OnClickListener {

	private int id;
	public OnDeleteClick(int id){
		this.id = id;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

//		String sql = "delete from action where id = " + id;
		DailyInfoBusi dailyInfoBusi = new DailyInfoBusi(v.getContext());
		dailyInfoBusi.delete(id);
		ActionListActivity actionlist = (ActionListActivity) v.getContext();
		Toast.makeText(actionlist, "É¾³ý³É¹¦!", 500).show();
		actionlist.refresh();
		//actionlist
//		Log.d("CCH", v.getContext().toString());	
	}

}
