package com.example8.mbatara.demo_1;

/**
 * Created by mbatara on 18/01/2018.
 */

public class SetterGeter
{
    String column_1, column_2, column_3;

    public SetterGeter(String column_1, String column_2, String column_3)
    {
        this.column_1 = column_1;
        this.column_2 = column_2;
        this.column_3 = column_3;
    }

    public String getColumn_1()
    {
        return column_1;
    }

    public String getColumn_2()
    {
        return column_2;
    }

    public String getColumn_3()
    {
        return column_3;
    }
}
