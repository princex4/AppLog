package com.example.applogs.data;

import android.app.Application;

public interface IDbConstants {

    int DB_VERSION = 1;
    String DB_NAME = "AppLogs.db";

     interface Product{
        String TABLE_NAME = "products";
        String ID = "_id";
        String PRODUCT_ID = "productId";
        String PRODUCT_NAME = "productName";
        String DESCRIPTION = "description";
        String WEIGHT = "weight";
        String PHONE = "phone";
        String WEB = "web";
        String PRICE = "price";

         String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+" ( "
                +ID+  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +PRODUCT_ID+ " TEXT, "
                + PRODUCT_NAME+ " TEXT, "
                + DESCRIPTION+ " TEXT, "
                + WEIGHT+ " TEXT, "
                + PHONE+ " TEXT, "
                + WEB+ " TEXT, "
                +PRICE+ " TEXT"
                + " )";



    }

}
