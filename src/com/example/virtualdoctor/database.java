package com.example.virtualdoctor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class database extends SQLiteOpenHelper {
	int id = 0;
	private static final String flag = null;
	private static String DB_PATH = "/data/data/com.example.virtualdoctor";
	private static String DB_NAME = "vmc.sqlite";

	static String Path = Environment.getExternalStorageDirectory().toString();

	public SQLiteDatabase checkDB;
	public boolean dbExist;
	private final Context myContext;

	public database(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;

		// db_writable= new DataBaseHelper(myContext).getWritableDatabase();
	}

	public synchronized void close() {
		if (checkDB != null)
			checkDB.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void createDataBase() throws IOException {
		dbExist = checkDataBase();
		if (!dbExist)
			copyDataBase();
	}

	public Boolean checkDataBase() {
		checkDB = null;
		try {
			String myPath = DB_PATH + "/" + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (SQLiteException e) {
		}
		return checkDB != null ? true : false;
	}

	// /////////////////copy database /////////////////
	private void copyDataBase() throws IOException {

		OutputStream databaseOutputStream = new FileOutputStream(
				"/data/data/com.example.virtualdoctor/vmc.sqlite");
		InputStream databaseInputStream;

		byte[] buffer = new byte[1024];

		databaseInputStream = myContext.getAssets().open("vmc.sqlite");

		while ((databaseInputStream.read(buffer)) > 0) {
			databaseOutputStream.write(buffer);
		}
		databaseInputStream.close();

		databaseOutputStream.flush();
		databaseOutputStream.close();

	}

	public void openDataBase() throws SQLException {

		String myPath = DB_PATH + "/" + DB_NAME;
		checkDB = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	public void CloseDataBase() {
		checkDB.close();
	}

	public ArrayList<String> get_names() {

		openDataBase();
		ArrayList<String> names = new ArrayList<String>();

		Cursor c1 = checkDB.rawQuery("SELECT Symptoms FROM vmc1", null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {
			names.add(c1.getString(0));
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return names;
	}

	public ArrayList<Integer> get_IDs() {

		openDataBase();
		ArrayList<Integer> ids = new ArrayList<Integer>();

		Cursor c1 = checkDB.rawQuery("SELECT SymptomsID FROM vmc1", null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {
			ids.add(c1.getInt(0));
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return ids;
	}

	public ArrayList<ClassDesease> get_desease_names(int syptm_1) {
		Log.d("checkde_ids", syptm_1 + " ");

		String syptm1 = "%," + syptm_1 + ",%";

		openDataBase();
		ArrayList<ClassDesease> names = new ArrayList<ClassDesease>();

		Cursor c1 = checkDB.rawQuery(
				"SELECT DiseaseID , Diseases FROM vddisease where Related_symptoms LIKE '"
						+ syptm1 + "'", null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {
			ClassDesease obj = new ClassDesease();
			obj.desease_id = c1.getInt(0);
			obj.desease_name = c1.getString(1);
			names.add(obj);
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return names;
	}

	public ArrayList<ClassDesease> get_desease_names(int syptm_1, int syptm_2) {
		Log.d("checkde_ids", syptm_1 + " " + syptm_2);

		String syptm1 = "%," + syptm_1 + ",%";
		String syptm2 = "%," + syptm_2 + ",%";

		openDataBase();
		ArrayList<ClassDesease> names = new ArrayList<ClassDesease>();

		Cursor c1 = checkDB.rawQuery(
				"SELECT DiseaseID , Diseases FROM vddisease where Related_symptoms LIKE '"
						+ syptm1 + "' or Related_symptoms LIKE '" + syptm2
						+ "'", null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {

			ClassDesease obj = new ClassDesease();
			obj.desease_id = c1.getInt(0);
			obj.desease_name = c1.getString(1);
			names.add(obj);
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return names;
	}

	public ArrayList<ClassDesease> get_desease_names(int syptm_1, int syptm_2,
			int syptm_3) {
		Log.d("checkde_ids", syptm_1 + " " + syptm_2);

		String syptm1 = "%," + syptm_1 + ",%";
		String syptm2 = "%," + syptm_2 + ",%";
		String syptm3 = "%," + syptm_3 + ",%";

		openDataBase();
		ArrayList<ClassDesease> names = new ArrayList<ClassDesease>();

		Cursor c1 = checkDB
				.rawQuery(
						"SELECT DiseaseID , Diseases FROM vddisease where (Related_symptoms LIKE '"
								+ syptm1 + "' and Related_symptoms LIKE '"
								+ syptm2 + "') or (Related_symptoms LIKE '"
								+ syptm2 + "' and Related_symptoms LIKE '"
								+ syptm3 + "') or (Related_symptoms LIKE '"
								+ syptm3 + "' and Related_symptoms LIKE '"
								+ syptm1 + "')", null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {

			ClassDesease obj = new ClassDesease();
			obj.desease_id = c1.getInt(0);
			obj.desease_name = c1.getString(1);
			names.add(obj);
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return names;
	}

	public String get_description_names(int id) {
		openDataBase();
		String desc = "";

		Cursor c1 = checkDB
				.rawQuery(
						"SELECT DiseaseDscirip FROM Ddescription where DiseaseID="
								+ id, null);
		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {
			desc = c1.getString(0);
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return desc;
	}

	public docname get_DocIDS(int id) {

		openDataBase();
		docname obj = new docname();
		Cursor c1 = checkDB.rawQuery("SELECT * FROM Ddescription where DiseaseID = " + id, null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {

			
			obj.doc_id = c1.getInt(0);
			obj.desc = c1.getString(1);
			obj.doc_name = c1.getString(2);
			
			c1.moveToNext();
		}

		c1.close();
		CloseDataBase();
		return obj;
	}
	
	public String get_Docnames(int id) {

		String doc_name = "";
		Cursor c1 = checkDB.rawQuery("SELECT Spcealist_name name FROM Docname where DocID = " + id, null);

		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {

		    doc_name = c1.getString(0);
			
			c1.moveToNext();
		}

		c1.close();
		return doc_name;
	}
	public String get_latitude(int id) {
		openDataBase();
		String latitude = "";

		Cursor c1 = checkDB
				.rawQuery(
						"SELECT latitude FROM spcialist where ID="
								+ id, null);
		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {
			latitude = c1.getString(0);
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return latitude;
	}

	public String get_longitude(int id) {
		openDataBase();
		String longitude = "";

		Cursor c1 = checkDB
				.rawQuery(
						"SELECT longitude FROM spcialist where ID="
								+ id, null);
		c1.moveToFirst();
		for (int i = 0; i < c1.getCount(); i++) {
			longitude = c1.getString(0);
			c1.moveToNext();
		}
		c1.close();
		CloseDataBase();
		return longitude;
	}

	}
	


