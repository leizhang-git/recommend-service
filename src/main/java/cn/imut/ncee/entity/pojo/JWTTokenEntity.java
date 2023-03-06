package cn.imut.ncee.entity.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Auth zhanglei
 * @Date 2023/2/28 19:05
 */
@Entity
@Table(name = "jwt_token_entity")
public class JWTTokenEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "cn.imut.ncee.domain.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "pb_key", columnDefinition = "text")
    private String pbKey;

    @Column(name = "pr_key", columnDefinition = "text")
    private String prKey;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "create_time")
    private Instant createTime = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPbKey() {
        return pbKey;
    }

    public void setPbKey(String pbKey) {
        this.pbKey = pbKey;
    }

    public String getPrKey() {
        return prKey;
    }

    public void setPrKey(String prKey) {
        this.prKey = prKey;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTTokenEntity)) return false;
        JWTTokenEntity that = (JWTTokenEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPbKey(), that.getPbKey()) && Objects.equals(getPrKey(), that.getPrKey()) && Objects.equals(getOrgId(), that.getOrgId()) && Objects.equals(getCreateTime(), that.getCreateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPbKey(), getPrKey(), getOrgId(), getCreateTime());
    }


    @Override
    public String toString() {
        return "JWTTokenEntity{" +
                "id='" + id + '\'' +
                ", pbKey='" + pbKey + '\'' +
                ", prKey='" + prKey + '\'' +
                ", orgId='" + orgId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
