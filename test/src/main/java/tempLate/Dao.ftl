package ${daoPackName};

import ${poPackName}.${tableName}Bean;
import ${poPackName}.${tableName}Cond;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* *************************************************************
*
* @项目名 : ${project}
* @文件名 : ${tableName}Mapper.java
* @建立日期 : ${date}
* @author : guantianqi
* @模块 :
* @描述 :${tabledesc}
* @备注 :
* ************************************************************
*/
@Repository("${tableName}Mapper")
public interface ${tableName}Mapper {

    /**
    * 方法描述 ：${tabledesc}删除
    *
    * @param : [${tableName}Id]
    * @return : int
    * @throws :
    * @auther : guantianqi
    * @createDate : 2018/11/16
    */
    int deleteByPrimaryKey(String cargoEnquiryDetailId);

    /**
    * 方法描述 ：${tabledesc}录入
    *
    * @param : [${tableName}Cond]
    * @return : int
    * @throws :
    * @auther : guantianqi
    * @createDate : 2018/11/16
    */
    int insertSelective(${tableName}Cond record);

    /**
    * 方法描述 ：${tabledesc}查询
    *
    * @param : [${tableName}Id]
    * @return : int
    * @throws :
    * @auther : guantianqi
    * @createDate : 2018/11/16
    */
    BizCargoEnquiryDetailBean selectByPrimaryKey(String ${tableName}Id);

    /**
    * 方法描述 ：${tabledesc}修改
    *
    * @param : [${tableName}Cond]
    * @return : int
    * @throws :
    * @auther : guantianqi
    * @createDate : 2018/11/16
    */
    int updateByPrimaryKeySelective(${tableName}Cond record);

    /**
    * 方法描述 ：${tabledesc}列表查询
    *
    * @param : [${tableName}Cond]
    * @return : List<${tableName}Bean>
    * @throws :
    * @auther : guantianqi
    * @createDate : 2018/11/16
    */
    List<${tableName}Bean> selectBizCargoEnquiryDetailByList(${tableName}Cond confVo);
}