package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.payroll.CompanyBenefit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ICompanyBenefitMapper {

    @Insert("<script>" +
                "insert into tbl_company_benefit (" +
                "client_id, " +
                "company_id, " +
                "name, " +
                "salary_sacrifice, " +
                "is_deleted, " +
                "updated_by, " +
                "updated_time) " +
                "select " +
                "#{client.id}, " +
                "#{company.id}, " +
                "#{name}, " +
                "#{salarySacrifice}, " +
                "#{deleted}, " +
                "#{updatedBy}, " +
                "#{updatedTime}" +
            "</script>"
    )
    void insert(CompanyBenefit companyBenefit);

    @Select("select id from tbl_company_benefit " +
            "where client_id = #{client.id} " +
            "and company_id = #{company.id} " +
            "and name = #{name} "
    )
    Long findId(CompanyBenefit companyBenefit);

    @Delete("delete from tbl_company_benefit " +
            "where client_id = #{clientId} " +
            "and company_id = #{companyId}")
    void deleteByClientAndCompanyId(long clientId, long companyId);

    @Delete("delete from tbl_company_benefit " +
            "where client_id = #{clientId}")
    void deleteByClientId(long clientId);

}
