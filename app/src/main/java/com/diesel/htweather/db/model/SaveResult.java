package com.diesel.htweather.db.model;

public class SaveResult {

    // 失败
    public static final int FAIL = 0;

    // 增加
    public static final int ADD = 1;

    // 更新
    public static final int UPDATE = 2;


    private final int mResult;

    public int getResult() {
        return mResult;
    }


    public SaveResult(int result) {
        if (result != FAIL && result != ADD && result != UPDATE) {
            throw new IllegalArgumentException("illegal result:" + result);
        }

        mResult = result;
    }


    public boolean isFail() {
        return mResult == FAIL;
    }

    public boolean isAdd() {
        return mResult == ADD;
    }

    public boolean isUpdate() {
        return mResult == UPDATE;
    }
    
}
