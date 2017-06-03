package daria.sem4.labworks.menuapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import daria.sem4.labworks.menuapp.POJOs.Item;
import daria.sem4.labworks.menuapp.POJOs.Table;

/**
 * Created by Daria on 26.05.2017.
 */

public class MenuDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = MenuDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "menuapp.db";
    private static final int DATABASE_VERSION = 10;

    private String[] tablesProjection = {
            MenuContract.TableEntry._ID,
            MenuContract.TableEntry.COLUMN_PERSONS,
            //MenuContract.TableEntry.COLUMN_WAITER,
            MenuContract.TableEntry.COLUMN_OPEN_ORDERS,
    };
    private String[] itemsProjection = {
            MenuContract.ItemEntry._ID,
            MenuContract.ItemEntry.COLUMN_TYPE_ID,
            MenuContract.ItemEntry.COLUMN_NAME,
            MenuContract.ItemEntry.COLUMN_WEIGHT,
            MenuContract.ItemEntry.COLUMN_PRICE,
    };

    // ----------------- UPLOAD INFO FROM DB -----------------
    public void uploadTables(ArrayList<Table> tables) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                MenuContract.TableEntry.TABLE_NAME,
                tablesProjection,
                null, null, null, null, null);

        int idColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry._ID);
        int personsColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry.COLUMN_PERSONS);
        int ordersColumnIndex = cursor.getColumnIndex(MenuContract.TableEntry.COLUMN_OPEN_ORDERS);

        while (cursor.moveToNext()) {
            long id = cursor.getInt(idColumnIndex);
            int persons = cursor.getInt(personsColumnIndex);
            int orders = cursor.getInt(ordersColumnIndex);
            tables.add(new Table(id, persons, orders));
        }

        db.close();
        cursor.close();
    }

    public void uploadItems(ArrayList<Item> items) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                MenuContract.ItemEntry.TABLE_NAME,
                itemsProjection,
                null, null, null, null, null);

        int idColumnIndex = cursor.getColumnIndex(MenuContract.ItemEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(MenuContract.ItemEntry.COLUMN_NAME);
        // TODO: category
        int weightColumnIndex = cursor.getColumnIndex(MenuContract.ItemEntry.COLUMN_WEIGHT);
        int priceColumnIndex = cursor.getColumnIndex(MenuContract.ItemEntry.COLUMN_PRICE);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(idColumnIndex);
            String name = cursor.getString(nameColumnIndex);
            int weight = cursor.getInt(weightColumnIndex);
            float price = cursor.getFloat(priceColumnIndex);
            items.add(new Item(id, name, price, weight));
        }

        db.close();
        cursor.close();
    }
    // ----------------- UPLOAD INFO FROM DB -----------------

    public MenuDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables table
        String SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.TableEntry.TABLE_NAME + " ("
                + MenuContract.TableEntry._ID + " INTEGER PRIMARY KEY, "
                + MenuContract.TableEntry.COLUMN_PERSONS + " INTEGER NOT NULL, "
                //+ MenuContract.TableEntry.COLUMN_WAITER + " TEXT NOT NULL);";
                + MenuContract.TableEntry.COLUMN_OPEN_ORDERS + " INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_TABLE);

        // create orders table
        SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.OrderEntry.TABLE_NAME + " ("
                + MenuContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MenuContract.OrderEntry.COLUMN_TABLE_ID + " INTEGER NOT NULL, "
                + MenuContract.OrderEntry.COLUMN_ORDER_ID + " INTEGER NOT NULL, "
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

// -------------- TABLE --------------
    public long addNewTable(int tableNumber, int persons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MenuContract.TableEntry._ID, tableNumber);
        values.put(MenuContract.TableEntry.COLUMN_PERSONS, persons);

        // if result != -1 => success
        final long id = db.insert(MenuContract.TableEntry.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    // TODO: delete all info from tables!!!
    public void deleteTable(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                MenuContract.TableEntry.TABLE_NAME,
                MenuContract.TableEntry._ID + " = " + id,
                null);
        db.close();
    }
// -------------- TABLE --------------

// -------------- ITEM --------------
    public long addNewItemToList(String name, int weight, float price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MenuContract.ItemEntry.COLUMN_NAME, name);
        values.put(MenuContract.ItemEntry.COLUMN_WEIGHT, weight);
        values.put(MenuContract.ItemEntry.COLUMN_PRICE, price);

        final long id = db.insert(MenuContract.ItemEntry.TABLE_NAME, null, values);
        db.close();

        return id;
    }
// -------------- ITEM --------------

// -------------- ORDER --------------
    public long addNewItemToOrder() {

        final long id = 1;
        return id;
    }
// -------------- ORDER --------------

    public int getOpenOrders(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " + MenuContract.TableEntry.COLUMN_OPEN_ORDERS + " FROM "
                + MenuContract.TableEntry.TABLE_NAME + " WHERE "
                + MenuContract.TableEntry._ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        if (cursor == null)
        {
            Log.d("my SQLite", "finding of open orders error");
            return -1;
        }

        cursor.moveToFirst();
        int openOrders = cursor.getInt(cursor.getColumnIndex(MenuContract.TableEntry.COLUMN_OPEN_ORDERS));
        db.close();

        Log.d("my SQLite", "open orders = " + String.valueOf(openOrders));
        return openOrders;
    }

    public long setOpenOrders(long tableItem, int num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MenuContract.TableEntry.COLUMN_OPEN_ORDERS, num);

        final long id = db.update(MenuContract.TableEntry.TABLE_NAME, values,
                MenuContract.TableEntry._ID + " = ?",
                new String[] {String.valueOf(tableItem)});

        db.close();

        Log.d("my SQLite", "updated table id = " + String.valueOf(id));
        return id;
    }

}
