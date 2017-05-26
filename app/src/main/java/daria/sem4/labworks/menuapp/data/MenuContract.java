package daria.sem4.labworks.menuapp.data;

import android.provider.BaseColumns;

/**
 * Created by Daria on 26.05.2017.
 */

public final class MenuContract {

    private MenuContract() {}

    public static final class TableEntry implements BaseColumns {
        public final static String TABLE_NAME = "tables";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WAITER = "waiter";
    }

    public static final class OrderEntry implements BaseColumns {
        public final static String TABLE_NAME = "orders";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ITEM = "item";
        public final static String COLUMN_NUMBER = "number";
    }

    public static final class ItemEntry implements BaseColumns {
        public final static String TABLE_NAME = "items";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_WEIGHT = "weight";
    }

}
