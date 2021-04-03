package com.example.dazuoye.shujuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.mainpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class shujukuinfo {
    public static final String name="yonghu";
    public static final int version_code=1;
    DatabaseHelper databaseHelper;
    String []zl={"zh","password","dh","dz","bj","ye","xm","dingdanid","im"};
    String []dingdanxq={"sl","je","ms","im"};
    String []dingdanzl={"zje","total","date","zt","dz","id"};
    public
    shujukuinfo(Context context){
        databaseHelper=new DatabaseHelper(context);
    }
    public void userinsert(HashMap<String,String> u){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        for(int i=0;i<zl.length;i++){
            values.put(zl[i],u.get(zl[i]));
        }
        db.insert("user",null,values);
        db.close();
    }
    public void userupdate(String zh,HashMap <String,String> u){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        for(int i=0;i<zl.length;i++){
            values.put(zl[i],u.get(zl[i]));
        }
        db.update("user",values,"zh = ?",new String[]{zh});
        db.close();
    }
    public HashMap<String,String> userfind(String zh){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        String SQL="select * from user";
        HashMap<String,String> user=new HashMap<>();
        user.put("find","false");
        Cursor cursor=db.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex("zh")).equals(zh)){
                user.put("find","true");
                for(int in=0;in<zl.length;in++){
                    int f=cursor.getColumnIndex(zl[in]);
                    user.put(zl[in],cursor.getString(f));
                }
            }
        }
        cursor.close();
        db.close();
        return user;
    }
    public void insertdingdans(ArrayList<HashMap<String,String>> dingdan){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        int dingdanid=Integer.parseInt(mainpage.info.zlmap.get("dingdanid"))+1;
        values.put("id",new Integer(dingdanid).toString());
        mainpage.info.zlmap.put("dingdanid",new Integer(dingdanid).toString());

        values.put("zh",mainpage.info.zlmap.get("zh"));
        String sum="";
        for(int i=0;i<dingdan.size()-1;i++){
            String s="";
            HashMap<String,String>dd=dingdan.get(i);
            for(int j=0;j<dingdanxq.length;j++){
                if(j!=dingdanxq.length-1){
                    s=s+dd.get(dingdanxq[j])+"/:";
                }
                else{
                    s=s+dd.get(dingdanxq[j]);
                }
            }
            sum=sum+s+";";
        }
        HashMap<String,String>dd=dingdan.get(dingdan.size()-1);
        dd.put("id",new Integer(dingdanid).toString());
        String p="";
        for(int i=0;i<dingdanzl.length;i++){
            if(i!=dingdanzl.length-1){
                p=p+dd.get(dingdanzl[i])+"/:";
            }
            else{
                p=p+dd.get(dingdanzl[i]);
            }
        }
        sum=sum+p;
        values.put("nr",sum);
        db.insert("dingdans",null,values);
        db.close();
    }
    public ArrayList<ArrayList<HashMap<String,String>>> dingdansfind(String zh){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        ArrayList<ArrayList<HashMap<String,String>>>dingdans=new ArrayList<>();
        Cursor cursor=db.query("dingdans",null,"zh = ?",new String[]{zh},null,null,null);
        while(cursor.moveToNext()){
            ArrayList<HashMap<String,String>> arr=new ArrayList<>();
            String [] v = cursor.getString(cursor.getColumnIndex("nr")).split(";");

            for(int i=0;i<v.length-1;i++){
                String f=v[i];
                Log.d("shujuku", "dingdansfind: "+f);
                String []t=f.split("/:");

                HashMap<String,String>map=new HashMap<>();
                for(int j=0;j<dingdanxq.length;j++){
                    map.put(dingdanxq[j],t[j]);
                }
                arr.add(map);
            }
            String []t=v[v.length-1].split("/:");
            HashMap<String,String>map=new HashMap<>();
            for(int j=0;j<dingdanzl.length;j++){
                map.put(dingdanzl[j],t[j]);
            }
            arr.add(map);
            dingdans.add(0,arr);
        }
        cursor.close();
        db.close();
        return  dingdans;
    }
    public void dingdandelete(String zh,String id){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        db.delete("dingdans","zh = ? AND id = ? ",new String[]{zh,id});
        db.close();
    }
    public  String sqzh(){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        Cursor cursor=db.query("zhfp",new String[]{"id"},null,null,null,null,null);
        String p="";
        while (cursor.moveToNext()){
            p=cursor.getString(cursor.getColumnIndex("id"));
            break;
        }
        p=new Long(Long.parseLong(p)+1).toString();
        db.close();
        return p;
    }
    public  void addzh(){
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        Cursor cursor=db.query("zhfp",new String[]{"id"},null,null,null,null,null);
        String p="";
        while (cursor.moveToNext()){
            p=cursor.getString(cursor.getColumnIndex("id"));
            break;
        }
        p=new Long(Long.parseLong(p)+1).toString();
        ContentValues values=new ContentValues();
        values.put("id",p);
        db.update("zhfp",values,null,null);
        db.close();
    }
}
