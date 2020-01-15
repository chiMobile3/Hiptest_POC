package com.iMobile3.framework.utilities;

import cucumber.api.DataTable;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CucumberUtil {

    private static Dictionary<String, DataCollection> _dataCollection = new Hashtable<>();

    public static Dictionary<String, DataCollection> ConvertDataTableToDict(DataTable table)
    {
        List<List<String>> data = table.raw();

        int rowNumber = 0;

        for (List<String> col:data)
        {
            for(int colIndex=0; colIndex <col.size(); colIndex++)
            {
                _dataCollection.put(data.get(0).get(colIndex), new DataCollection(data.get(0).get(colIndex), col.get(colIndex), rowNumber));
            }

            rowNumber++;
        }

        return _dataCollection;
    }


    //Todo: Passing the rowIndex to get the columnValue based on Row number
    public static String GetCellValue(String columnName)
    {
        return _dataCollection.get(columnName).ColumnValue;

    }


    private static class DataCollection
    {
        private String ColumnName;
        private String ColumnValue;
        private int RowNumber;


        public DataCollection(String columnName, String columnValue, int rowNumber) {
            ColumnName = columnName;
            ColumnValue = columnValue;
            RowNumber = rowNumber;
        }
    }


}


