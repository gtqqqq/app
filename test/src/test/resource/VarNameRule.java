package resource;

 import java.util.Map;

/**
 * *************************************************************
 *
 * @项目名 : test
 * @文件名 : VarNameRule
 * @建立日期    : 2018/11/30
 * @author    : guantianqi
 * @模块        :
 * @描述        :
 * @备注        : *************************************************************
 */
public class VarNameRule {
    private static Map typeMap = new SelfCurrentHashMap();

static {
typeMap.put("VARCHAR",new String[]{"String","java.lang.String"});
typeMap.put("CHAR",new String[]{"String","java.lang.String"});
typeMap.put("BLOB",new String[]{"byte[]","java.lang.byte[]"});
typeMap.put("TEXT",new String[]{"String","java.lang.String"});
typeMap.put("INTEGER",new String[]{"Long","java.lang.Long"});
typeMap.put("TINYINT",new String[]{"Integer","java.lang.Integer"});
typeMap.put("SMALLINT",new String[]{"Integer","java.lang.Integer"});
typeMap.put("MEDIUMINT",new String[]{"Integer","java.lang.Integer"});
typeMap.put("BIT",new String[]{"Boolean","java.lang.Boolean"});
typeMap.put("BIGINT",new String[]{"BigInteger","java.math.BigInteger"});
typeMap.put("FLOAT",new String[]{"Float","java.lang.Float"});
typeMap.put("DOUBLE",new String[]{"Double","java.lang.Double"});
typeMap.put("DECIMAL",new String[]{"BigDecimal","java.math.BigDecimal"});
typeMap.put("BOOLEAN",new String[]{"Integer","java.lang.Integer"});
typeMap.put("ID",new String[]{"Long","java.lang.Long"});
typeMap.put("DATE",new String[]{"Date","java.Util.Date"});
typeMap.put("TIME",new String[]{"Date","java.Util.Date"});
typeMap.put("DATETIME",new String[]{"Date","java.Util.Date"});
typeMap.put("TIMESTAMP",new String[]{"Date","java.Util.Date"});
typeMap.put("YEAR",new String[]{"Date","java.Util.Date"});
typeMap.put("varchar"+"VARCHAR",new String[]{"String","java.lang.String"});
typeMap.put("char",new String[]{"String","java.lang.String"});
typeMap.put("cidr",new String[]{"String","java.lang.String"});
typeMap.put("inet",new String[]{"String","java.lang.String"});
typeMap.put("macaddr",new String[]{"String","java.lang.String"});
typeMap.put("text",new String[]{"String","java.lang.String"});
typeMap.put("int8",new String[]{"Long","java.lang.Long"});
typeMap.put("bytea",new String[]{"Byte","java.lang.Byte"});
typeMap.put("box",new String[]{"String","java.lang.String"});
typeMap.put("circle",new String[]{"String","java.lang.String"});
typeMap.put("float8",new String[]{"Double","java.lang.Double"});
typeMap.put("interval",new String[]{"String","java.lang.String"});
typeMap.put("line",new String[]{"String","java.lang.String"});
typeMap.put("lseg",new String[]{"String","java.lang.String"});
typeMap.put("money",new String[]{"Double","java.lang.Double"});
typeMap.put("numeric",new String[]{"BigDecimal","java.math.BigDecimal"});
typeMap.put("path",new String[]{"String","java.lang.String"});
typeMap.put("point",new String[]{"String","java.lang.String"});
typeMap.put("polygon",new String[]{"String","java.lang.String"});
typeMap.put("float4",new String[]{"Float","java.lang.Float"});
typeMap.put("int",new String[]{"Integer","java.lang.Integer"});
typeMap.put("int2",new String[]{"Integer","java.lang.Integer"});
typeMap.put("int4",new String[]{"Integer","java.lang.Integer"});
typeMap.put("time",new String[]{"Date","java.Util.Date"});
typeMap.put("timestamp",new String[]{"Date","java.Util.Date"});
typeMap.put("bit",new String[]{"Boolean","java.lang.Boolean"});
typeMap.put("varbit",new String[]{"String","java.lang.String"});
typeMap.put("bool",new String[]{"Boolean","java.lang.Boolean"});
}
    public static String columnToProperty(String dbColumn){
        StringBuilder property = new StringBuilder("");
        String[] strs = dbColumn.split("_");
        for (String str:strs ) {
           StringBuilder strTmp= new StringBuilder(str);
            property.append(strTmp.replace(0,1,strTmp.substring(0,1).toUpperCase()));
        }
        return property.replace(0,1,property.substring(0,1).toLowerCase()).toString();
    }

    public static String tableToClass(String table){
        StringBuilder property = new StringBuilder("");
        String[] strs = table.split("_");
        for (String str:strs ) {
            StringBuilder strTmp= new StringBuilder(str.toLowerCase());
            property.append(strTmp.replace(0,1,strTmp.substring(0,1).toUpperCase()));
        }
        return property.toString();
    }

    public static String tableToClassVar(String table){
        StringBuilder property = new StringBuilder("");
        String[] strs = table.split("_");
        for (String str:strs ) {
            StringBuilder strTmp= new StringBuilder(str.toLowerCase());
            property.append(strTmp.replace(0,1,strTmp.substring(0,1).toUpperCase()));
        }
        return property.replace(0,1,property.substring(0,1).toLowerCase()).toString();
    }

    public static String dbTypeToJavaType(String dbType){
        boolean f =typeMap.get(dbType.toUpperCase()) == null && typeMap.get(dbType.toLowerCase()) == null;
        if(typeMap.get(dbType.toUpperCase()) == null && typeMap.get(dbType.toLowerCase()) == null){
            return "Object";
        }else{
         String[] toUpLow   = (String[]) (typeMap.get(dbType.toUpperCase()) == null?typeMap.get(dbType.toLowerCase()): typeMap.get(dbType.toUpperCase()));
            return toUpLow[0];
        }
    }

    public static String postgredbTypeToJavaType(String dbType){
        if(typeMap.get(dbType) == null){
            return null;
        }else{
            return ((String[])typeMap.get(dbType))[0];
        }
    }
}
