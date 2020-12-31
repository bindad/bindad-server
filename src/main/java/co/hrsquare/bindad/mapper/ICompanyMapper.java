package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.company.Company;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

@Mapper
public interface ICompanyMapper {

    @Insert("insert into tbl_company (" +
            "client_id, " +
            "public_id, " +
            "full_name, " +
            "short_name, " +
            "trading_name, " +
            "registered_name, " +
            "website, " +
            "contact_telephone, " +
            "registered_charity_number, " +
            "vat_number, " +
            "is_partnership, " +
            "admin_title, " +
            "admin_first_name, " +
            "admin_known_as, " +
            "admin_middle_initial, " +
            "admin_last_name, " +
            "admin_email, " +
            "admin_telephone, " +
            "admin_telephone_ext, " +
            "admin_mobile, " +
            "is_finance_same_as_admin, " +
            "finance_title, " +
            "finance_first_name, " +
            "finance_known_as, " +
            "finance_middle_initial, " +
            "finance_last_name, " +
            "finance_email, " +
            "finance_telephone, " +
            "finance_telephone_ext, " +
            "finance_mobile, " +
            "primary_address_id, " +
            "other_1_address_id, " +
            "other_2_address_id, " +
            "other_3_address_id, " +
            "other_4_address_id, " +
            "other_5_address_id, " +
            "is_deleted, " +
            "updated_by, " +
            "updated_time) " +
            "select " +
            "#{client.id}, " +
            "#{publicId}, " +
            "#{fullName}, " +
            "#{shortName}, " +
            "#{tradingName}, " +
            "#{registeredName}, " +
            "#{website}, " +
            "#{contactTelephone}, " +
            "#{registeredCharityNumber}, " +
            "#{vatNumber}, " +
            "#{partnership}, " +
            "#{adminNameDetails.title.name}, " +
            "#{adminNameDetails.firstName}, " +
            "#{adminNameDetails.knownAs}, " +
            "#{adminNameDetails.middleInitial}, " +
            "#{adminNameDetails.lastName}, " +
            "#{adminContactDetails.email}, " +
            "#{adminContactDetails.telephone}, " +
            "#{adminContactDetails.TelephoneExt}, " +
            "#{adminContactDetails.mobile}, " +
            "#{financeSameAsAdmin}, " +
            "#{financeNameDetails.title.name}, " +
            "#{financeNameDetails.firstName}, " +
            "#{financeNameDetails.knownAs}, " +
            "#{financeNameDetails.middleInitial}, " +
            "#{financeNameDetails.lastName}, " +
            "#{financeContactDetails.email}, " +
            "#{financeContactDetails.telephone}, " +
            "#{financeContactDetails.TelephoneExt}, " +
            "#{financeContactDetails.mobile}, " +
            "#{primaryAddress.id}, " +
            "#{additionalAddress1.id}, " +
            "#{additionalAddress2.id}, " +
            "#{additionalAddress3.id}, " +
            "#{additionalAddress4.id}, " +
            "#{additionalAddress5.id}, " +
            "#{deleted}, " +
            "#{updatedBy}, " +
            "#{updatedTime}")
    void insert(Company company);

    @Select("select id from tbl_company where full_name=#{fullName}")
    Long findId(Company company);

    @Select("select " +
            "id, " +
            "client_id, " +
            "public_id, " +
            "full_name, " +
            "short_name, " +
            "trading_name, " +
            "registered_name, " +
            "website, " +
            "contact_telephone, " +
            "registered_charity_number, " +
            "vat_number, " +
            "is_partnership, " +
            "admin_title, " +
            "admin_first_name, " +
            "admin_known_as, " +
            "admin_middle_initial, " +
            "admin_last_name, " +
            "admin_email, " +
            "admin_telephone, " +
            "admin_telephone_ext, " +
            "admin_mobile, " +
            "is_finance_same_as_admin, " +
            "finance_title, " +
            "finance_first_name, " +
            "finance_known_as, " +
            "finance_middle_initial, " +
            "finance_last_name, " +
            "finance_email, " +
            "finance_telephone, " +
            "finance_telephone_ext, " +
            "finance_mobile, " +
            "primary_address_id, " +
            "other_1_address_id, " +
            "other_2_address_id, " +
            "other_3_address_id, " +
            "other_4_address_id, " +
            "other_5_address_id, " +
            "is_deleted, " +
            "updated_by, " +
            "updated_time " +
            "from tbl_company " +
            "where full_name=#{name} " +
            "and is_deleted = 0")
    @Results({
            @Result(column = "client_id", property = "client.id"),
            @Result(column = "public_id", property = "publicId"),
            @Result(column = "full_name", property = "fullName"),
            @Result(column = "short_name", property = "shortName"),
            @Result(column = "trading_name", property = "shortName"),
            @Result(column = "registered_name", property = "tradingName"),
            @Result(column = "website", property = "website"),
            @Result(column = "contact_telephone", property = "contactTelephone"),
            @Result(column = "registered_charity_number", property = "registeredCharityNumber"),
            @Result(column = "vat_number", property = "vatNumber"),
            @Result(column = "is_partnership", property = "partnership"),
            @Result(column = "admin_title", property = "adminNameDetails.title", typeHandler = EnumTypeHandler.class),
            @Result(column = "admin_first_name", property = "adminNameDetails.firstName"),
            @Result(column = "admin_known_as", property = "adminNameDetails.knownAs"),
            @Result(column = "admin_middle_initial", property = "adminNameDetails.middleInitial"),
            @Result(column = "admin_last_name", property = "adminNameDetails.lastName"),
            @Result(column = "admin_email", property = "adminContactDetails.email"),
            @Result(column = "admin_telephone", property = "adminContactDetails.telephone"),
            @Result(column = "admin_telephone_ext", property = "adminContactDetails.telephoneExt"),
            @Result(column = "admin_mobile", property = "adminContactDetails.mobile"),
            @Result(column = "is_finance_same_as_admin", property = "financeSameAsAdmin"),
            @Result(column = "finance_title", property = "financeNameDetails.title", typeHandler = EnumTypeHandler.class),
            @Result(column = "finance_first_name", property = "financeNameDetails.firstName"),
            @Result(column = "finance_known_as", property = "financeNameDetails.knownAs"),
            @Result(column = "finance_middle_initial", property = "financeNameDetails.middleInitial"),
            @Result(column = "finance_last_name", property = "financeNameDetails.lastName"),
            @Result(column = "finance_email", property = "financeContactDetails.email"),
            @Result(column = "finance_telephone", property = "financeContactDetails.telephone"),
            @Result(column = "finance_telephone_ext", property = "financeContactDetails.telephoneExt"),
            @Result(column = "finance_mobile", property = "financeContactDetails.mobile"),
            @Result(column = "primary_address_id", property = "primaryAddress.id"),
            @Result(column = "other_1_address_id", property = "additionalAddress1.id"),
            @Result(column = "other_2_address_id", property = "additionalAddress2.id"),
            @Result(column = "other_3_address_id", property = "additionalAddress3.id"),
            @Result(column = "other_4_address_id", property = "additionalAddress4.id"),
            @Result(column = "other_5_address_id", property = "additionalAddress5.id"),
            @Result(column = "is_deleted", property = "deleted"),
            @Result(column = "updated_by", property = "updatedBy"),
            @Result(column = "updated_time", property = "updatedTime")
    })
    Company findByFullName(String name);

    @Delete("<script>" +
                "delete from tbl_company " +
                "where client_id = #{clientId}; " +
            "</script>")
    void deleteByClientId(Long clientId);

    @Select("select " +
            "id, " +
            "client_id, " +
            "public_id, " +
            "full_name, " +
            "short_name, " +
            "trading_name, " +
            "registered_name, " +
            "website, " +
            "contact_telephone, " +
            "registered_charity_number, " +
            "vat_number, " +
            "is_partnership, " +
            "admin_title, " +
            "admin_first_name, " +
            "admin_known_as, " +
            "admin_middle_initial, " +
            "admin_last_name, " +
            "admin_email, " +
            "admin_telephone, " +
            "admin_telephone_ext, " +
            "admin_mobile, " +
            "is_finance_same_as_admin, " +
            "finance_title, " +
            "finance_first_name, " +
            "finance_known_as, " +
            "finance_middle_initial, " +
            "finance_last_name, " +
            "finance_email, " +
            "finance_telephone, " +
            "finance_telephone_ext, " +
            "finance_mobile, " +
            "primary_address_id, " +
            "other_1_address_id, " +
            "other_2_address_id, " +
            "other_3_address_id, " +
            "other_4_address_id, " +
            "other_5_address_id, " +
            "is_deleted, " +
            "updated_by, " +
            "updated_time " +
            "from tbl_company " +
            "where client_id=#{clientId} " +
            "and is_deleted = 0")
    @Results({
            @Result(column = "client_id", property = "client.id"),
            @Result(column = "public_id", property = "publicId"),
            @Result(column = "full_name", property = "fullName"),
            @Result(column = "short_name", property = "shortName"),
            @Result(column = "trading_name", property = "shortName"),
            @Result(column = "registered_name", property = "tradingName"),
            @Result(column = "website", property = "website"),
            @Result(column = "contact_telephone", property = "contactTelephone"),
            @Result(column = "registered_charity_number", property = "registeredCharityNumber"),
            @Result(column = "vat_number", property = "vatNumber"),
            @Result(column = "is_partnership", property = "partnership"),
            @Result(column = "admin_title", property = "adminNameDetails.title", typeHandler = EnumTypeHandler.class),
            @Result(column = "admin_first_name", property = "adminNameDetails.firstName"),
            @Result(column = "admin_known_as", property = "adminNameDetails.knownAs"),
            @Result(column = "admin_middle_initial", property = "adminNameDetails.middleInitial"),
            @Result(column = "admin_last_name", property = "adminNameDetails.lastName"),
            @Result(column = "admin_email", property = "adminContactDetails.email"),
            @Result(column = "admin_telephone", property = "adminContactDetails.telephone"),
            @Result(column = "admin_telephone_ext", property = "adminContactDetails.telephoneExt"),
            @Result(column = "admin_mobile", property = "adminContactDetails.mobile"),
            @Result(column = "is_finance_same_as_admin", property = "financeSameAsAdmin"),
            @Result(column = "finance_title", property = "financeNameDetails.title", typeHandler = EnumTypeHandler.class),
            @Result(column = "finance_first_name", property = "financeNameDetails.firstName"),
            @Result(column = "finance_known_as", property = "financeNameDetails.knownAs"),
            @Result(column = "finance_middle_initial", property = "financeNameDetails.middleInitial"),
            @Result(column = "finance_last_name", property = "financeNameDetails.lastName"),
            @Result(column = "finance_email", property = "financeContactDetails.email"),
            @Result(column = "finance_telephone", property = "financeContactDetails.telephone"),
            @Result(column = "finance_telephone_ext", property = "financeContactDetails.telephoneExt"),
            @Result(column = "finance_mobile", property = "financeContactDetails.mobile"),
            @Result(column = "primary_address_id", property = "primaryAddress.id"),
            @Result(column = "other_1_address_id", property = "additionalAddress1.id"),
            @Result(column = "other_2_address_id", property = "additionalAddress2.id"),
            @Result(column = "other_3_address_id", property = "additionalAddress3.id"),
            @Result(column = "other_4_address_id", property = "additionalAddress4.id"),
            @Result(column = "other_5_address_id", property = "additionalAddress5.id"),
            @Result(column = "is_deleted", property = "deleted"),
            @Result(column = "updated_by", property = "updatedBy"),
            @Result(column = "updated_time", property = "updatedTime")
    })
    Company findByClientId(long clientId);
}
