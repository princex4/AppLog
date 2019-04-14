package com.example.applogs.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.applogs.model.ProductModel;

import java.util.ArrayList;

public class DBHelper {

    private static DBHelper dbHelper = null;
    private static SQLiteDatabase db;


    private DBHelper(){
    }

    public static DBHelper getInstance(Context context){
        if(dbHelper==null){
            dbHelper = new DBHelper();
            db = new DbOpenHelper(context).getWritableDatabase();
        }
        return dbHelper;
    }

    public void insertProducts(ArrayList<ProductModel> productModelArrayList) {

        for (int i = 0; i < productModelArrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(IDbConstants.Product.PRODUCT_ID, productModelArrayList.get(i).getProductId());
            contentValues.put(IDbConstants.Product.PRODUCT_NAME, productModelArrayList.get(i).getName());
            contentValues.put(IDbConstants.Product.DESCRIPTION, productModelArrayList.get(i).getDescription());
            contentValues.put(IDbConstants.Product.WEIGHT, productModelArrayList.get(i).getWeight());
            contentValues.put(IDbConstants.Product.PHONE, productModelArrayList.get(i).getPhone());
            contentValues.put(IDbConstants.Product.WEB, productModelArrayList.get(i).getWeb());
            contentValues.put(IDbConstants.Product.PRICE, productModelArrayList.get(i).getPrice());
            db.insert(IDbConstants.Product.TABLE_NAME, null, contentValues);
        }
    }

        public ArrayList<ProductModel> getProduct(){
            ArrayList<ProductModel> productModelArrayList = null;
            String productQuery = "SELECT * FROM "+ IDbConstants.Product.TABLE_NAME;
            Cursor cursor =  db.rawQuery(productQuery, null);
            if(cursor!=null && cursor.moveToFirst()){
                productModelArrayList = new ArrayList<>();
                do{
                    ProductModel productModel = new ProductModel();
                    productModel.setProductId(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.PRODUCT_ID)));
                    productModel.setName(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.PRODUCT_NAME)));
                    productModel.setDescription(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.DESCRIPTION)));
                    productModel.setWeight(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.WEIGHT)));
                    productModel.setPhone(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.PHONE)));
                    productModel.setWeb(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.WEB)));
                    productModel.setPrice(cursor.getDouble(cursor.getColumnIndex(IDbConstants.Product.PRICE)));

                    productModelArrayList.add(productModel);


                }while (cursor.moveToNext());
            }
            return productModelArrayList;
        }










}
