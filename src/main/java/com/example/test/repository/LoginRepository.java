package com.example.test.repository;

import com.example.test.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query( value = "SELECT DISTINCT ON (login_time, \"user\") id, \"user\", DATE(login_time) AS login_time," +
            "  attribute1, attribute2, attribute3, attribute4 FROM test ORDER BY login_time ASC", nativeQuery = true)
    List<Login> findAll();

    @Query( value = "SELECT DISTINCT ON (\"user\") id, \"user\", DATE(login_time) AS login_time," +
            "  attribute1, attribute2, attribute3, attribute4 FROM test where login_time > TO_DATE(:startDate, 'YYYYMMDD') - 1  AND login_time < TO_DATE(:endDate, 'YYYYMMDD') + 1 ORDER BY \"user\" ASC", nativeQuery = true)
    List<Login> findAllByLogin_timeBetween(@Param("startDate") String start, @Param("endDate")String end);

    @Query( value = "WITH usr AS (SELECT \"user\", COUNT(login_time) as instance FROM test WHERE DATE(login_time) > DATE(:startDate)-1 " +
            "AND DATE(login_time) < DATE(:endDate) + 1 group by \"user\") SELECT  CAST(json_build_object(\"user\", instance) AS text) FROM usr", nativeQuery = true)
    List<?> findAll(@Param("startDate")String start, @Param("endDate")String end);

    @Query( value = "WITH usr AS (SELECT \"user\", COUNT(login_time) as instance FROM test WHERE DATE(login_time) > DATE(:startDate)-1 " +
            "AND DATE(login_time) < DATE(:endDate) + 1 AND attribute1 in :attribute1 group by \"user\") SELECT  CAST(json_build_object(\"user\", instance) AS text) FROM usr", nativeQuery = true)
    List<?> findAll(@Param("startDate") String start, @Param("endDate")String end, @Param("attribute1") List<String> attribute1);

    @Query( value = "WITH usr AS (SELECT \"user\", COUNT(login_time) as instance FROM test WHERE DATE(login_time) > DATE(:startDate)-1 " +
            "AND DATE(login_time) < DATE(:endDate) + 1 AND attribute1 in :attribute1 AND attribute2 in :attribute2 group by \"user\") SELECT  CAST(json_build_object(\"user\", instance) AS text) FROM usr", nativeQuery = true)
    List<?> findAll(@Param("startDate") String start, @Param("endDate")String end, @Param("attribute1") List<String> attribute1, @Param("attribute2") List<String> attribute2);

    @Query( value = "WITH usr AS (SELECT \"user\", COUNT(login_time) as instance FROM test WHERE DATE(login_time) > DATE(:startDate)-1 " +
            "AND DATE(login_time) < DATE(:endDate) + 1 AND attribute1 in :attribute1 AND attribute2 in :attribute2 AND attribute3 in :attribute3 group by \"user\") " +
            "SELECT  CAST(json_build_object(\"user\", instance) AS text) FROM usr", nativeQuery = true)
    List<?> findAll(@Param("startDate") String start, @Param("endDate")String end, @Param("attribute1") List<String> attribute1, @Param("attribute2") List<String> attribute2,
                    @Param("attribute3")List<String> attribute3);

    @Query( value = "WITH usr AS (SELECT \"user\", COUNT(login_time) as instance FROM test WHERE DATE(login_time) > DATE(:startDate)-1 " +
            "AND DATE(login_time) < DATE(:endDate) + 1 AND attribute1 in :attribute1 AND attribute2 in :attribute2 AND attribute3 in :attribute3 AND attribute4 in :attribute4" +
            " group by \"user\") SELECT  CAST(json_build_object(\"user\", instance) AS text) FROM usr", nativeQuery = true)
    List<?> findAll(@Param("startDate") String start, @Param("endDate")String end, @Param("attribute1") List<String> attribute1, @Param("attribute2") List<String> attribute2,
                    @Param("attribute3")List<String> attribute3, @Param("attribute4")List<String> attribute4);



}