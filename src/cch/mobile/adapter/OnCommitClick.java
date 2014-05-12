package cch.mobile.adapter;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cch.selflog.ActionListActivity;
import cch.selflog.R;
import cch.selflog.busi.DailyInfoBusi;
import cch.selflog.model.DailyInfo;

public class OnCommitClick implements OnClickListener {

	int id;
	public OnCommitClick(int id){
		this.id = id;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Context c = v.getContext();
		DailyInfoBusi dailyInfoBusi = new DailyInfoBusi(c);
		DailyInfo model = dailyInfoBusi.getOne(id);
		try {
			ActionListActivity actionlist = (ActionListActivity) v.getContext();
			String result = dailyInfoBusi.commit2Web(model,c.getString(R.string.serverURI));
			Log.d("CCH", result);
			model.setIsCommited("Y");
			dailyInfoBusi.updateIsCommited(model);
			actionlist.refresh();
			Toast.makeText(c, "提交成功!", 500).show();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Toast.makeText(c, e.getMessage(), 500).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(c, e.getMessage(), 500).show();
		}
//		dailyInfoBusi.
	}

}
