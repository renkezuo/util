package com.renke.util.collection;

public @interface Column {
	public enum ColumnType{CHAR,VARCHAR,VARCHAR2,INT,FLOAT,DOUBLE,TIMESTAMP,DATE};
	public int columnIndex();
	public String columnName();
	ColumnType columnType() default ColumnType.VARCHAR;
	public int columnLength();
}
