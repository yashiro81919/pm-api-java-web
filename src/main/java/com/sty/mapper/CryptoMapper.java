package com.sty.mapper;

import java.util.List;

import com.sty.entity.Crypto;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoMapper {
    @Select("SELECT * FROM t_crypto")
    @Results({ @Result(property = "cmcId", column = "cmc_id"), @Result(property = "quantity", column = "quantity"),
            @Result(property = "remark", column = "remark") })
    List<Crypto> getAll();

    @Insert("INSERT INTO t_crypto (cmc_id, quantity, remark) VALUES (#{cmcId}, #{quantity}, #{remark})")
    void insert(Crypto crypto);

    @Update("UPDATE t_crypto SET quantity = #{quantity}, remark = #{remark} WHERE cmc_id = #{cmcId}")
    void update(Crypto crypto);

    @Delete("DELETE FROM t_crypto WHERE cmc_id = #{cmcId}")
    void delete(String name);
}
