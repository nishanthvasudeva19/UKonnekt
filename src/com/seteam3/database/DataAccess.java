package com.seteam3.database;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataAccess {
	private static SQLiteDatabase db;
	private static DataSQLHelper dbHelper;
	public static final String KEY_PASSWORD = "LoggedIn";
	static Context mContext;
	
	public DataAccess(Context context){
		mContext = context;
	}
	
	public void open() throws SQLException{
		if(db!=null){
			db.close();
		}
		dbHelper = new DataSQLHelper(mContext);
	    db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public PasswordList cursorToPassword(Cursor cursor){
		PasswordList user = new PasswordList();
		user.setPassword(cursor.getString(cursor.getColumnIndex("LoggedIn")));
		return user;
	}
	
	public List<PasswordList> getPasswordList() {
		// TODO Auto-generated method stub
		List<PasswordList> PasswordListItems = new ArrayList<PasswordList>();
		Cursor cursor = db.rawQuery("select * from " + DataSQLHelper.PasswordsListTable ,null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			PasswordList contact = cursorToPassword(cursor);
			PasswordListItems.add(contact);
			cursor.moveToNext();
		}
		cursor.close();
		return PasswordListItems;
	}
	
	public void changePassword(String newPassword) {
		ContentValues cvalues = new ContentValues();
		cvalues.put(KEY_PASSWORD, newPassword);
		db.update(DataSQLHelper.PasswordsListTable, cvalues, null, null);
	}
}