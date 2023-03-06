package cn.imut.ncee.repository;

import cn.imut.ncee.entity.pojo.JWTTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auth zhanglei
 * @Date 2023/2/28 19:30
 */
@Repository
public interface JWTRepository extends JpaRepository<JWTTokenEntity, String> {

    List<JWTTokenEntity> findAllByOrgIdAndPrKey(String orgId, String prKey);

    List<JWTTokenEntity> findAllByOrgId(String orgId);

}
