package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.Plant;

/**
 * 厂商Repository
 *
 * @author Administrator
 */
public interface PlantRepository extends JpaRepository<Plant, Integer>, JpaSpecificationExecutor<Plant> {
    /**
     * 根据dep_id查询所有员工信息
     *
     * @param id
     * @return
     */
    @Query(value = "select * from t_plant where type_id=?1", nativeQuery = true)
    public List<Plant> findByDepId(Integer id);

    /**
     * @Description: 模糊查询所有厂商信息
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @Query(value = "select * from t_plant where name like ?1", nativeQuery = true)
    public List<Plant> findByName(String s);
}
