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
        public final static String COLUMN_PERSONS = "persons";
        //public final static String COLUMN_WAITER = "waiter";
        public final static String COLUMN_OPEN_ORDERS = "open_orders";
    }

    public static final class OrderEntry implements BaseColumns {
        public final static String TABLE_NAME = "orders";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TABLE_ID = "table_id";
        public final static String COLUMN_ORDER_ID = "order_id";
        public final static String COLUMN_ITEM = "item_id";
        public final static String COLUMN_NUMBER = "number";
    }

    public static final class ItemEntry implements BaseColumns {
        public final static String TABLE_NAME = "items";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TYPE_ID = "type";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_WEIGHT = "weight";
        public final static String COLUMN_PRICE = "price";
    }

}
