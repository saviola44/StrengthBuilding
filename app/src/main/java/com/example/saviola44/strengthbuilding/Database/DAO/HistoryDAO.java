package com.example.saviola44.strengthbuilding.Database.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.saviola44.strengthbuilding.Database.StrengthBuilderOpenHelper;
import com.example.saviola44.strengthbuilding.Database.Tables.HistoryTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 27.05.16.
 */

//tutaj zapisujemy samą date wykonanego treningu
    //tej klasy raczej nie uzywamy z poziomu klienta ona bedzie pomocnicza dla ExHistDao
    //kiedy bedzie ^klasa pobierac liste wszystkich treningow pobierze sobie wszystkie daty stąd
    //i w petli dla kazdej daty pobierze dla danej daty liste wykonanych cwiczen/serii
public class HistoryDAO implements DAO<Long> {
    public static final String INSERT = "insert into " + HistoryTable.TABLE_NAME
            + " (" + HistoryTable.date + ")" +
            " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    private Context context;
    public HistoryDAO(Context context){
        this.context = context;
        SQLiteOpenHelper openHelper = new StrengthBuilderOpenHelper(context);
        db = openHelper.getWritableDatabase();
        insertStatement = db.compileStatement(INSERT);
    }



    @Override
    public long saveElement(Long element) {
        insertStatement.clearBindings();
        insertStatement.bindLong(1,element);
        return insertStatement.executeInsert();
    }





    @Override
    public void delete(Long element) {

    }

    @Override
    public List<Long> getAllElements() {
        List<Long> dates = new ArrayList<>();
        Cursor c = db.query(HistoryTable.TABLE_NAME, new String[]{HistoryTable.date}, null, null, null, null, null, null);
        if(c.moveToFirst()){
            do{
                long date = c.getLong(0);
                dates.add(date);

            }while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return dates;

    }

    @Override
    public Long getElementById(long id) {
        return id;
    }
}
