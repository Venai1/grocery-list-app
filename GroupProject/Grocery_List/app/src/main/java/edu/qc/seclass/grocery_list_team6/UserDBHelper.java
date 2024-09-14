package edu.qc.seclass.grocery_list_team6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocery_list.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_GROCERY = "grocery";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_LIST_NAME = "list_name";

    private static final String TABLE_LIST = "grocery_list";
    private static final String COLUMN_LIST_ID = "id";


    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createGroceryTable = "CREATE TABLE " + TABLE_GROCERY +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_TYPE + " TEXT," +
                COLUMN_QUANTITY + " INTEGER," +
                COLUMN_LIST_NAME + " TEXT" +
                ")";

        String createListTable = "CREATE TABLE " + TABLE_LIST +
                "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LIST_NAME + " TEXT" +
                ")";

        db.execSQL(createGroceryTable);
        db.execSQL(createListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

    public void addGrocery(Grocery grocery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, grocery.getName());
        // values.put(COLUMN_TYPE, grocery.getType());
        values.put(COLUMN_QUANTITY, grocery.getQty());
        // values.put(COLUMN_LIST_NAME, grocery.getListName());
        db.insert(TABLE_GROCERY, null, values);
        db.close();
    }

    public List<Grocery> getAllGroceries() {
        List<Grocery> groceryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GROCERY, null);
        if (cursor.moveToFirst()) {
            do {
                Grocery grocery = new Grocery();
                grocery.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                // grocery.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
                grocery.setQty(String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))));
                // grocery.setListName(cursor.getString(cursor.getColumnIndex(COLUMN_LIST_NAME)));
                groceryList.add(grocery);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return groceryList;
    }

    public void saveGroceryList(String listName, List<Grocery> groceryList) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete existing items associated with the list name
        db.delete(TABLE_GROCERY, COLUMN_LIST_NAME + " = ?", new String[]{listName});

        // Insert the updated list
        for (Grocery item : groceryList) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_LIST_NAME, listName);
            values.put(COLUMN_NAME, item.getName());
            values.put(COLUMN_QUANTITY, item.getQty());
            db.insert(TABLE_GROCERY, null, values);
        }

        db.close();
    }

}