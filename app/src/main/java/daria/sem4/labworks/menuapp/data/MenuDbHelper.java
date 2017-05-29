package daria.sem4.labworks.menuapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Table;

/**
 * Created by Daria on 26.05.2017.
 */

public class MenuDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = MenuDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "menuapp.db";
    private static final int DATABASE_VERSION = 8;

    private String[] tablesProjection = {
            MenuContract.TableEntry._ID,
            MenuContract.TableEntry.COLUMN_PERSONS,
            //MenuContract.TableEntry.COLUMN_WAITER,
    };

    public void uploadTables(ArrayList<Table> tables) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                MenuContract.TableEntry.TABLE_NAME,
                tablesProjection,
                null, null, null, null, null);

        int idColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry._ID);
        int personsColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry.COLUMN_PERSONS);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idColumnIndex);
            int persons = cursor.getInt(personsColumnIndex);
            tables.add(new Table(id, persons));
        }

        cursor.close();
    }

    public MenuDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables table
        String SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.TableEntry.TABLE_NAME + " ("
                + MenuContract.TableEntry._ID + " INTEGER PRIMARY KEY, "
                + MenuContract.TableEntry.COLUMN_PERSONS + " INTEGER NOT NULL);";
                //+ MenuContract.TableEntry.COLUMN_WAITER + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);

        // create orders table
        SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.OrderEntry.TABLE_NAME + " ("
                + MenuContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MenuContract.OrderEntry.COLUMN_TABLE_ID + " INTEGER NOT NULL, "
                + MenuContract.OrderEntry.COLUMN_ITEM + " INTEGER NOT NULL, "
                + MenuContract.OrderEntry.COLUMN_NUMBER + " INTEGER NOT NULL DEFAULT 1);";
        db.execSQL(SQL_CREATE_TABLE);

        // create items table
        SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.ItemEntry.TABLE_NAME + " ("
                + MenuContract.ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MenuContract.ItemEntry.COLUMN_TYPE_ID + " INTEGER NOT NULL DEFAULT 1, "
                + MenuContract.ItemEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + MenuContract.ItemEntry.COLUMN_WEIGHT + " INTEGER NOT NULL, "
                + MenuContract.ItemEntry.COLUMN_PRICE + " FLOAT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Update from version " + oldVersion + " to version " + newVersion);
        dropAllTables(db);
        onCreate(db);
    }

    private void dropAllTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + MenuContract.TableEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MenuContract.OrderEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MenuContract.ItemEntry.TABLE_NAME);
    }

    public boolean addNewTable(int tableNumber, int persons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MenuContract.TableEntry._ID, tableNumber);
        values.put(MenuContract.TableEntry.COLUMN_PERSONS, persons);

        // if result != -1 => success
        return  !(db.insert(MenuContract.TableEntry.TABLE_NAME, null, values) == -1);
    }

    // TODO: delete all info from tables!!!
    public void deleteTable(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                MenuContract.TableEntry.TABLE_NAME,
                MenuContract.TableEntry._ID + " = " + id,
                null);
    }
}
