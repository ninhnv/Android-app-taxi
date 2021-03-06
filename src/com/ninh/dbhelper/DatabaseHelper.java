package com.ninh.dbhelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	String DB_PATH = null;
	private static String DB_NAME = "data";
	private SQLiteDatabase myDatabase;
	private final Context myContext;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
	}

	public void createDatabase() {
		boolean dbExist = checkDatabase();
		if (dbExist) {

		} else {
			this.getReadableDatabase();
			try {
				loadDataBase();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Error("Error load database");
			}
		}
	}

	private boolean checkDatabase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (SQLiteException e) {

		}
		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	private void loadDataBase() throws IOException {
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() {
		String myPath = DB_PATH + DB_NAME;
//		myDatabase = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
		myDatabase = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public synchronized void close() {

		if (myDatabase != null)
			myDatabase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	public Cursor query(String table, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
//		return myDatabase.query("englishcommon", null, "category=?", new String[] {"(v)"}, null, null, null,
//				null);
		return myDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);

	}
	
	public Cursor rawQuery(String sql, String[] selectionArgs)
	{
		return myDatabase.rawQuery(sql, selectionArgs);
	}
	
	public List<String> getAllLabels() {
		List<String> labels = new ArrayList<String>();
		try {
			String selectQuery = "SELECT * FROM tx_city ";
//			myDatabase=this.getWritableDatabase();
//			myDatabase = this.getReadableDatabase();
			String myPath = DB_PATH + DB_NAME;
			createDatabase();
			myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//			myDatabase = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
			Cursor cursor = myDatabase.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					labels.add(cursor.getString(1));
				} while (cursor.moveToNext());
			}
			cursor.close();
			myDatabase.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return labels;
    }
}
