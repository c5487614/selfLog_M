package cch.selflog.busi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.database.Cursor;
import cch.mobile.util.DBUtil;
import cch.mobile.util.FormatUtil;
import cch.mobile.util.HttpUtil;
import cch.selflog.R;
import cch.selflog.model.DailyInfo;

public class DailyInfoBusi {
	DBUtil db = null;
	String databasename;
	public DailyInfoBusi(Context c){
		databasename = c.getString(R.string.databaseName);
		db = DBUtil.getInstance(c,databasename);
	}
	public DailyInfoBusi(DBUtil db){
		this.db = db;
	}
	public void insert(DailyInfo model){
		String sql = "insert into action(personName,itemName,fee,feeDate,fillDate,PCInfo,isPaid,itemType,isCommited,comment)" +
					"values('%s','%s',%s,'%s','%s','%s','%s','%s','%s','%s');";
		String format = "yyyy-MM-dd";
		sql = String.format(sql, model.getPersonName(),model.getItemName(),model.getFee(),
				FormatUtil.dateFormat(format, model.getFeeDate()),FormatUtil.dateFormat("yyyy-MM-dd HH:mm:ss", model.getFillDate()),
				model.getPCInfo(),model.getIsPaid(),model.getItemType(),model.getIsCommited(),model.getComment());
		this.db.exeSQL(sql);
	}
	public void delete(int id){
		String sql ="delete from action where id = %s";
		sql = String.format(sql, id);
		this.db.exeSQL(sql);
		
	}
	public DailyInfo getOne(int id){
		Cursor cursor = this.db.getDB().query("action", new String[]{"id","personName","itemName","fee","feeDate","fillDate",
				"PCInfo","isPaid","itemType","isCommited","comment"}, 
				"id = ?", new String[]{"" + id}, null, null, null);
		cursor.moveToFirst();
		DailyInfo model = this.cursor2Model(cursor);
		return model;
	}
	public String commit2Web(DailyInfo model,String URI) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URI);
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("daily_feeDate",FormatUtil.dateFormat("yyyy-MM-dd",model.getFeeDate())));
		nvps.add(new BasicNameValuePair("daily_item",model.getItemName()));
		nvps.add(new BasicNameValuePair("daily_name",model.getPersonName()));
		nvps.add(new BasicNameValuePair("daily_money",FormatUtil.double2String(model.getFee())));
		nvps.add(new BasicNameValuePair("daily_fillDate",FormatUtil.dateFormat("yyyy-MM-dd",model.getFillDate())));
		nvps.add(new BasicNameValuePair("daily_isPaid",model.getIsPaid()));
		nvps.add(new BasicNameValuePair("daily_itemType",model.getItemType()));
		nvps.add(new BasicNameValuePair("daily_comment","Mobile:"+model.getComment()));
		nvps.add(new BasicNameValuePair("daily_PCInfo",model.getPCInfo()));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
		HttpResponse response = httpclient.execute(httpPost);
		String result = HttpUtil.response2String(response);
		return result;
		
	}
	public void updateIsCommited(DailyInfo model){
		String sql = "update action set isCommited = '"+model.getIsCommited()+"' where id = " + model.getId();
		this.db.exeSQL(sql);
	}
	public List<DailyInfo> getDailyInfo(){
		List<DailyInfo> list = new ArrayList<DailyInfo>();
		try{
			//String sql = "select id,personName,itemName,fee,feeDate,fillDate,PCInfo,isPaid,itemType,isCommited from action";
			Cursor cursor = this.db.getDB().query("action", new String[]{"id","personName","itemName","fee","feeDate","fillDate",
					"PCInfo","isPaid","itemType","isCommited","comment"}, 
					"isCommited = ?", new String[]{"N"}, null, null, null);
			while(cursor.moveToNext()){
				DailyInfo model = cursor2Model(cursor);
				list.add(model);
			}
			cursor.close();
			this.db.getDB().close();
		}catch(Exception ex){
			this.db.getDB().close();
		}
		return list;
	}
	private DailyInfo cursor2Model(Cursor cursor){
		DailyInfo model = new DailyInfo();
		model.setId(cursor.getInt(0));
		model.setPersonName(cursor.getString(1));
		model.setItemName(cursor.getString(2));
		model.setFee(cursor.getDouble(3));
		model.setFeeDate(FormatUtil.string2Date(cursor.getString(4)));
		model.setFillDate(FormatUtil.string2Date(cursor.getString(5)));
		model.setPCInfo(cursor.getString(6));
		model.setIsPaid(cursor.getString(7));
		model.setItemType(cursor.getString(8));
		model.setIsCommited(cursor.getString(9));
		model.setComment(cursor.getString(10));
		return model;
	}
}
