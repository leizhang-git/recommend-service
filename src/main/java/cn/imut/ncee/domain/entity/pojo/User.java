package cn.imut.ncee.domain.entity.pojo;

import cn.imut.ncee.domain.DefaultEntity;
import cn.imut.ncee.domain.config.condition.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * @auther zhanglei
 * @create 2023-03-22-22:56
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends DefaultEntity {
    private static final long serialVersionID = 1L;

    /**
     * 主键
     * @GeneratorValue JPA通用策略生成器
     * @GenericGenerator 自定义主键生成策略
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 36)
    private String id;

    /**
     * UserId
     * @Pattern 正则
     */
    @NotNull
    @Pattern(regexp = Constants.USER_ID_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "user_id", length = 50, unique = true, nullable = false)
    private String userId;

    /**
     * password
     * @JsonIgnore 实体类向前台返回数据时用来忽略不想传递给前台的属性或接口
     */
    @JsonIgnore
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    /**
     * 初始姓名
     */
    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    /**
     * 对外网名
     */
    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * 邮箱
     */
    @Size(max = 254)
    @Column(length = 254)
    private String email;

    /**
     * 头像
     */
    @Size(max = 256)
    @Column(name = "head_sculpture", length = 256)
    private String headSculpture;

    /**
     * 手机号
     */
    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;

    /**
     * 所属组织
     */
    @Value("tenant1_id")
    @NotNull
    @Size(max = 36)
    @Column(name = "organization", length = 36)
    private String organization;


    /**
     * 角色信息
     */
    @Transient
    private String roles;
}
