package daria.sem4.labworks.menuapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Daria on 26.05.2017.
 */

public class MenuDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = MenuDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "menuapp.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor {@link MenuDbHelper}.
     *
     * @param context
     */
    public MenuDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dropAllTables(db);

        // create tables table
        String SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.TableEntry.TABLE_NAME + " ("
                + MenuContract.TableEntry._ID + " INTEGER PRIMARY KEY, "
                + MenuContract.TableEntry.COLUMN_WAITER + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);

        // create orders table
        SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.OrderEntry.TABLE_NAME + " ("
                + MenuContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MenuContract.OrderEntry.COLUMN_ITEM + " TEXT NOT NULL, "
                + MenuContract.OrderEntry.COLUMN_NUMBER + "INTEGER NOT NULL DEFAULT 1);";
        db.execSQL(SQL_CREATE_TABLE);

        // create items table
        SQL_CREATE_TABLE = "CREATE TABLE " + MenuContract.ItemEntry.TABLE_NAME + " ("
                + MenuContract.ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MenuContract.ItemEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + MenuContract.ItemEntry.COLUMN_PRICE + " FLOAT NOT NULL, "
                + MenuContract.ItemEntry.COLUMN_WEIGHT + " INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Update from version " + oldVersion + " to version " + newVersion);
        dropAllTables(db);
        onCreate(db);
    }

    private void dropAllTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF IT EXISTS " + MenuContract.TableEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF IT EXISTS " + MenuContract.OrderEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF IT EXISTS " + MenuContract.ItemEntry.TABLE_NAME);
    }

    public void insertTestTables(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // first test
        int i = 12;
        values.put(MenuContract.TableEntry._ID, i);
        values.put(MenuContract.TableEntry.COLUMN_WAITER, "Peter");

        if (db.insert(MenuContract.TableEntry.TABLE_NAME, null, values) == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
