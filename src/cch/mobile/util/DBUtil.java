package cch.mobile.util;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	private Activity context;
	private String dbName;
	static SQLiteDatabase sqlitedb;
	static DBUtil m_instance;
	private DBUtil(Context a,String dbName){
		this.context = (Activity)a;
		this.dbName = dbName;
	}
	public static DBUtil getInstance(Context c,String dbName){
		if(m_instance==null){
			m_instance = new DBUtil(c,dbName);
		}
		return m_instance;
	}
	public SQLiteDatabase openDB(){
		return this.context.openOrCreateDatabase(dbName, Activity.MODE_PRIVATE, null);
	}
	public void exeSQL(String sql){
		SQLiteDatabase db = openDB();
		db.execSQL(sql);
		db.close();
	}
	public SQLiteDatabase getDB(){
		sqlitedb = openDB();
		return sqlitedb;
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		sqlitedb.close();
		
	}
}
