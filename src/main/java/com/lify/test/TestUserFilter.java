package com.lify.test;

import com.lify.annotations.Column;
import com.lify.annotations.Table;
import com.lify.domain.User;
import com.lify.filter.Filter;
import com.lify.filter.UserFilter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestUserFilter {

    public static void main(String[] args) {
        UserFilter filter1 = new UserFilter();
        filter1.setAge(10);
        filter1.setNickName("heihei");
        UserFilter filter2 = new UserFilter();
        filter2.setUserName("lify");

        UserFilter filter3 = new UserFilter();
        filter3.setCity("QINGDAO,WEIFANG,YANTAI");

        System.out.println(query(filter1));
        System.out.println(query(filter2));
        System.out.println(query(filter3));
    }

    private static String query(Filter filter) {
        StringBuilder sb = new StringBuilder();
        try {
            Class c = filter.getClass();
            boolean isExist = c.isAnnotationPresent(Table.class);
            //拿到表名
            Table table = (Table) c.getAnnotation(Table.class);
            String tableName = table.value();
            sb.append("select*from ").append(tableName).append(" where 1 = 1");
            Field[] fArray = c.getDeclaredFields();
            for (Field field : fArray) {
                //处理每个字段对应的sql
                //拿到字段名
                boolean isExistF = field.isAnnotationPresent(Column.class);
                if (!isExistF) {
                    continue;
                }
                Column column = (Column) field.getAnnotation(Column.class);
                String columnName = column.value();//拿到字段名
                //拿字段值
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Object fieldValue = null;
                try {
                    Method getMethod = c.getMethod(getMethodName);
                    fieldValue = (Object) getMethod.invoke(filter, null);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                //拼接前判断下，fieldValue是否为null,为null则不拼接
                if(fieldValue == null){
                    continue;
                }
                if(fieldValue instanceof String){
                    fieldValue = "'"+fieldValue+"'";
                }
                //拼接sql
                sb.append(" and ").append(fieldName).append(" = ").append(fieldValue.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
