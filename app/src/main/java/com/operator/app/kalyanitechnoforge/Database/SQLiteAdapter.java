package com.operator.app.kalyanitechnoforge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 */
public class SQLiteAdapter
{
    public static final String MYDATABASE_NAME = "Employee DATA";
    public static final String MYDATABASE_TABLE = "MY_TABLE";
    public static final int MYDATABASE_VERSION = 1;
    public static final String KEY_CONTENT = "Content";
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private final ArrayList<EmployeeData> EmployeeDatalist= new ArrayList<EmployeeData>();
   // private final ArrayList<EmployeeData> EmployeeDatalist = new ArrayList<EmployeeData>();
   private final ArrayList<Status> Statuslist = new ArrayList<Status>();
    public SQLiteAdapter(Context c) {
        context = c;
    }
    public SQLiteAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
                MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
                MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqLiteHelper.close();
    }

    public long insertEmployeeData(String Employee_Id, String Employee_Name, String Employee_Mobileno, String Employee_Email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Employee_Id", Employee_Id);
        contentValues.put("Employee_Name", Employee_Name);
        contentValues.put("Employee_Mobileno", Employee_Mobileno);
       contentValues.put("Employee_Email", Employee_Email);


        return sqLiteDatabase.insert("EmployeeData", null, contentValues);
    }


    public long insertStatus(String Business_Id, String Business_Info_Status, String Contact_Info_Status, String Business_Keyword_Status, String Upload_Photo_Status) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Business_Id", Business_Id);
        contentValues.put("Business_Info_Status", Business_Info_Status);
        contentValues.put("Contact_Info_Status", Contact_Info_Status);
        contentValues.put("Business_Keyword_Status", Business_Keyword_Status);
        contentValues.put("Upload_Photo_Status", Upload_Photo_Status);

        return sqLiteDatabase.insert("status", null, contentValues);
    }




    public ArrayList<EmployeeData> retrieveAllUser() {

        EmployeeDatalist.clear();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from EmployeeData;", null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    EmployeeData contact = new EmployeeData();
                    contact.setEmployee_Id(cursor.getString(0));
                    contact.setEmployee_Name(cursor.getString(1));
                    contact.setEmployee_Mobileno(cursor.getString(2));
                    contact.setEmployee_Email(cursor.getString(3));

                    EmployeeDatalist.add(contact);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return  EmployeeDatalist;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }finally {
            cursor.close();
        }

        return EmployeeDatalist;
    }



    public ArrayList<Status> retrieveAllStatus() {

        Statuslist.clear();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from status;", null);
        try {
            if (cursor.moveToFirst()) {
                do
                {
                    Status contact = new Status();
                    contact.setBusiness_Id(cursor.getString(0));
                    contact.setBusiness_Info_Status(cursor.getString(1));
                    contact.setContact_Info_Status(cursor.getString(2));
                    contact.setBusiness_Keyword_Status(cursor.getString(3));
                    contact.setUpload_Photo_Status(cursor.getString(4));
                    Statuslist.add(contact);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return  Statuslist;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }finally {
            cursor.close();
        }

        return Statuslist;
    }




    public ArrayList<EmployeeData> retrieveAllEmployeeData() {
        EmployeeDatalist.clear();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from EmployeeData;", null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    EmployeeData data = new EmployeeData();
                    data.setEmployee_Id(cursor.getString(0));
                    data.setEmployee_Name(cursor.getString(1));
                    data.setEmployee_Mobileno(cursor.getString(2));
                    data.setEmployee_Email(cursor.getString(3));

                    EmployeeDatalist.add(data);

                } while (cursor.moveToNext());
            }

            cursor.close();
            return     EmployeeDatalist;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_data", "" + e);
        }finally {
            cursor.close();
        }

        return EmployeeDatalist;
    }


    public int Get_Total_EmployeeData()
    {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from EmployeeData;", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public int Get_Total_StatusData() {


        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from status;", null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void update_Business_Info(String Bill_Type)
    {
        sqLiteDatabase.execSQL("update status set Business_Info_Status='" + Bill_Type + "'");
    }

    public void update_Contact_Info(String Bill_Type)
    {
        sqLiteDatabase.execSQL("update status set Contact_Info_Status='" + Bill_Type + "'");
    }
    public void update_Business_Keyword(String Bill_Type)
    {
        sqLiteDatabase.execSQL("update status set Business_Keyword_Status='" + Bill_Type + "'");
    }
    public void update_Upload_Photo(String Bill_Type)
    {
        sqLiteDatabase.execSQL("update status set Upload_Photo_Status='" + Bill_Type + "'");
    }



    public int deleteEmployeeData() {

        int k = sqLiteDatabase.delete("EmployeeData", null, null);
        return k;
    }

    public int deleteStatusData() {

        int k = sqLiteDatabase.delete("status", null, null);
        return k;
    }






    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            //store user data


            db.execSQL("create table EmployeeData(Employee_Id text,Employee_Name text,Employee_Mobileno text,Employee_City text,Employee_Email text,Image text);");
            db.execSQL("create table status(Business_Id text,Business_Info_Status text,Contact_Info_Status text,Business_Keyword_Status text,Upload_Photo_Status text);");

            Log.d("Log", "Database Created");
            System.out.println("DataBase");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
        @Override
        public synchronized void close() {
            if(sqLiteDatabase != null){
                sqLiteDatabase.close();
                super.close();
            }
        }
    }
}