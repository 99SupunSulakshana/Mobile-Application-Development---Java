package com.example.DataBase;

        import android.provider.BaseColumns;

public final class UserProfile{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "dateOfBirth";
        public static final String COLUMN_3 = "password";
        public static final String COLUMN_4 = "gender";

    }
    public static class Products implements BaseColumns {
        public static final String TABLE_NAME ="ProductInfo";
        public static final String COLUMN_1 = "productId";
        public static final String COLUMN_2 = "productName";
        public static final String COLUMN_3 = "productImage";

    }

    public static class Delivery implements BaseColumns {
        public static final String TABLE_NAME = "DeliveryInfo";
        public static final String COLUMN_1 = "Name";
        public static final String COLUMN_2 = "Id";
        public static final String COLUMN_3 = "gender";
        public static final String COLUMN_4 = "address";
        public static final String COLUMN_5 = "HotLine";
        public static final String COLUMN_6 = "email";

    }

    public static class Order implements BaseColumns{
        public static final String TABLE_NAME = "OrderInfo";
        public static final String COLUMN_1 = "Name";
        public static final String COLUMN_2 = "Address";
        public static final String COLUMN_3 = "OrderDate";
        public static final String COLUMN_4 = "DateOfRequired";
        public static final String COLUMN_5 = "ZipCode";
        public static final String COLUMN_6 = "mobileNo";

        
    }

}
