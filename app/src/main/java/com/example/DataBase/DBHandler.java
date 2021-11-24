package com.example.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LoLa_CupCake.db";

    public DBHandler(Context context) {// context = activity
        super(context, "LoLa_CupCake.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);// table ekak hadana eka
        db.execSQL(SQL_CREATE_ENTRIES_PRODUCT);

    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        onCreate(db);
        db.execSQL(SQL_DELETE_ENTRIES_PRODUCT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //create operator users
    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.COLUMN_1 + " TEXT," +
                    UserProfile.Users.COLUMN_2 + " TEXT," +
                    UserProfile.Users.COLUMN_3 + " TEXT," +
                    UserProfile.Users.COLUMN_4 + " TEXT)";


    private static final String SQL_CREATE_ENTRIES_PRODUCT =
            "CREATE TABLE " + UserProfile.Products.TABLE_NAME + " (" +
                    UserProfile.Products.COLUMN_1 + " TEXT," +
                    UserProfile.Products.COLUMN_2 + " TEXT,"+
                    UserProfile.Products.COLUMN_3 + " BLOB)";



    //create products

    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_PRODUCT =
            "DROP TABLE IF EXISTS " + UserProfile.Products.TABLE_NAME;

    //insert operator
    public long addInfo(String username, String dob, String password, String gender) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_1, username);
        values.put(UserProfile.Users.COLUMN_2, dob);
        values.put(UserProfile.Users.COLUMN_3, password);
        values.put(UserProfile.Users.COLUMN_4, gender);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);

        return newRowId;
    }

//    public long insert_product(Product product){
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(UserProfile.Products.COLUMN_1, Product.);
//
//
//    }
//    public long productaddInfo(ItemModel itemModel) {
//        // Gets the data repository in write mode
//        SQLiteDatabase db = getWritableDatabase();
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(UserProfile.Products.COLUMN_1, itemModel.getId());
//        values.put(UserProfile.Products.COLUMN_2, itemModel.getName());
////        values.put(UserProfile.Products.COLUMN_3, itemModel.getCategory());
////        values.put(UserProfile.Products.COLUMN_4, itemModel.getDescription());
//        values.put(UserProfile.Products.COLUMN_3, itemModel.getImage());
//
//     return db.insert(UserProfile.Products.TABLE_NAME, null, values);
//    }
//    public long addproduct(String pname, String pprice, String pCategories, String pdes, Byte img) {
//        // Gets the data repository in write mode
//        SQLiteDatabase db = getWritableDatabase();
//
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(UserProfile.Products.COLUMN_1, pname);
//        values.put(UserProfile.Products.COLUMN_2, pprice);
//        values.put(UserProfile.Products.COLUMN_3, pCategories);
//        values.put(UserProfile.Products.COLUMN_4, pdes);
//        values.put(UserProfile.Products.COLUMN_4, img);
//        // Insert the new row, returning the primary key value of the new row
//        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);
//
//        return newRowId;
//    }
    //update operator
    public Boolean updateInfo(String username, String dob, String password, String gender) {

        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_2, dob);
        values.put(UserProfile.Users.COLUMN_3, password);
        values.put(UserProfile.Users.COLUMN_4, gender);

        // Which row to update, based on the title
        String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = {username};
        //update eka effect wena data tika int ekak vidht denv
        int count = db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >= 1) {
            return true;
        } else {
            return false;
        }
    }

    //delete operator
    public void deleteInfo(String username) {

        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {username};
        // Issue SQL statement.
        int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
    }

    //Data view operator
    public ArrayList readAllInfo() {

        String username = "supun";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_3,
                UserProfile.Users.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_1 + " = ?";
        String[] selectionArgs = {username};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all) projection = sql select
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause, ? mark ekata ona agruments tika pass krnn oni
                null,                   // don't group the rows
                null,                   // don't filter by row groups, group eka at6hule krna conditions tika
                sortOrder               // The sort order, ena order eka sort krnn
        );

        //data retrive
        // We can user cursor obj
        ArrayList usernames = new ArrayList<>();
        while (cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            usernames.add(user);


            //record ekk nath nam cursor eka close wela ynv.

        }
        cursor.close();

        return usernames;
    }


    //User details retrive
    public List readAllInfo(String username) {


        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_3,
                UserProfile.Users.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = {username};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all) projection = sql select,
                selection,// The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause, ? mark ekata ona agruments tika pass krnn oni
                null,                   // don't group the rows
                null,                   // don't filter by row groups, group eka at6hule krna conditions tika
                sortOrder               // The sort order, ena order eka sort krnn
        );

        //data retrive
        // We can user cursor obj
        List userInfo = new ArrayList<>();
        while (cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_2));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_3));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_4));
            userInfo.add(user); //record ekk nath nam cursor eka close wela ynv.//0
            userInfo.add(dob);//1
            userInfo.add(pass);//2
            userInfo.add(gender);//3 index

        }
        cursor.close();

        return userInfo;
    }


    //login part eka
    public Boolean loginUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_3
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_1 + " = ? AND " + UserProfile.Users.COLUMN_3 + " = ?";
        String[] selectionArgs = {username, password};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List validUser = new ArrayList();
        while (cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            validUser.add(user);
        }
        cursor.close();

        if (validUser.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


    //View All products
    public ArrayList readAllproductsInfo() {

        String productName = "supun";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Products.COLUMN_1,
                UserProfile.Products.COLUMN_2,
                UserProfile.Products.COLUMN_3,
//                UserProfile.Products.COLUMN_4,
//                UserProfile.Products.COLUMN_5
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Products.COLUMN_1 + " = ?";
        String[] selectionArgs = {productName};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Products.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Products.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all) projection = sql select
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause, ? mark ekata ona agruments tika pass krnn oni
                null,                   // don't group the rows
                null,                   // don't filter by row groups, group eka at6hule krna conditions tika
                sortOrder               // The sort order, ena order eka sort krnn
        );

        //data retrive
        // We can user cursor obj
        ArrayList productCategory = new ArrayList<>();
        while (cursor.moveToNext()) {
            String product = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Products.COLUMN_1));
            productCategory.add(product);


            //record ekk nath nam cursor eka close wela ynv.

        }
        cursor.close();

        return productCategory;
    }


//    public ArrayList<ItemModel> viewAllProducts() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM ProductInfo";
//        Cursor cursor = db.rawQuery(sql, null);
//        int count = cursor.getCount();
//        ArrayList<ItemModel> itemModels = new ArrayList<ItemModel>();
//        if (count != 0) {
//            ItemModel itemModel = new ItemModel();
//            itemModel.setName(cursor.getString(0));
//            Log.i("ID ->",""+ cursor.getString(0));
//            itemModel.setPrice(cursor.getString(1));
//            itemModel.setCategory(cursor.getString(2));
//            itemModel.setDescription(cursor.getString(3));
//            itemModel.setImage(cursor.getBlob(4));
//            itemModels.add(itemModel);
//        }
//        return itemModels;
//    }

}
