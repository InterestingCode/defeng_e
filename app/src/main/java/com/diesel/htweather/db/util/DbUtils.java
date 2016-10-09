package com.diesel.htweather.db.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.diesel.htweather.db.model.SaveResult;

public final class DbUtils {

    public static void endTransaction(SQLiteDatabase db) {
        if (db != null) {
            try {
                db.endTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static SaveResult updateOrInsertOneRow(SQLiteDatabase db, String tableName,
            ContentValues data,
            String whereClause, String[] whereArgs) {
        SaveResult saveResult = null;
        try {
            // 先尝试更新，若更新失败，尝试插入
            if (db.updateWithOnConflict(tableName, data, whereClause, whereArgs,
                    SQLiteDatabase.CONFLICT_IGNORE) > 0) {
                saveResult = new SaveResult(SaveResult.UPDATE);
            } else {
                saveResult = db.insertWithOnConflict(tableName, null, data,
                        SQLiteDatabase.CONFLICT_IGNORE) == -1
                        ? new SaveResult(SaveResult.FAIL)
                        : new SaveResult(SaveResult.ADD);
            }
        } catch (Throwable tr) {
            Log.e("updateOrInsertOneRow", " error", tr);
        }
        return saveResult == null ? new SaveResult(SaveResult.FAIL) : saveResult;
    }

    /**
     * @return 更新行数大于0，返回true，否则返回false
     */
    public static boolean update(SQLiteDatabase db, String tableName, ContentValues data,
            String whereClause, String[] whereArgs) {
        try {
            return db.updateWithOnConflict(tableName, data, whereClause, whereArgs,
                    SQLiteDatabase.CONFLICT_IGNORE) > 0;
        } catch (Throwable tr) {
            Log.e("update", " error", tr);
        }
        return false;
    }

    public static boolean insert(SQLiteDatabase db, String tableName, ContentValues data) {
        return db.insertWithOnConflict(tableName, null, data, SQLiteDatabase.CONFLICT_IGNORE) == -1;
    }

}
