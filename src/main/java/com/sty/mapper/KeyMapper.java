package com.sty.mapper;

import com.sty.entity.Key;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyMapper {
        @Select("SELECT * FROM t_key WHERE name like '%' || #{name} || '%'")
        @Results({ @Result(property = "name", column = "name"), @Result(property = "key", column = "key"),
                        @Result(property = "value", column = "value") })
        List<Key> search(String name);

        @Select("SELECT * FROM t_key WHERE name = #{name}")
        @Results({ @Result(property = "name", column = "name"), @Result(property = "key", column = "key"),
                        @Result(property = "value", column = "value") })
        Key get(String name);

        @Insert("INSERT INTO t_key (name, key, value) VALUES (#{name}, #{key}, #{value})")
        void insert(Key key);

        @Update("UPDATE t_key SET key = #{key}, value = #{value} WHERE name = #{name}")
        void update(Key key);

        @Delete("DELETE FROM t_key WHERE name= #{name}")
        void delete(String name);
}
