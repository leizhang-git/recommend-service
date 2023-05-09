package com.recommend.consumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @auther zhanglei
 * @create 2023-03-22-22:56
 * 实现Serializable接口就可以完成序列化和反序列化操作
 * 序列化：将一个对象以一种方式打包压缩，成为一个字符串，使他适合存放和传输，这就是序列化
 * 反序列化：将其从字符串中解压缩，返回成一个对象
 *
 *
 * @EntityListeners:
 * 被@Prepersist注解的方法 ，完成save之前的操作。
 * 被@Preupdate注解的方法 ，完成update之前的操作。
 * 被@PreRemove注解的方法 ，完成remove之前的操作。
 * 被@Postpersist注解的方法 ，完成save之后的操作。
 * 被@Postupdate注解的方法 ，完成update之后的操作。
 * @Audited
 * 当应用于类时，表示其所有属性都应该被审计。 当应用于一个字段时，表示该字段应该被审计
 * @MappedSuperclass
 * 指定一个类，其映射信息应用于从其继承的实体
 */
@MappedSuperclass
@Audited
@EntityListeners(DefaultEntity.class)
public class DefaultEntity implements Serializable {
    /**
     * 若不指定，则会根据包名、类名等生成一个64位的哈希字段，在反序列化时会校验版本是否一致！
     */
    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     * nullable:不允许为空
     * updatable：
     */
    @CreatedBy
    @Column(name = "creator", nullable = false, length = 50, updatable = false)
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_date", updatable = false)
    private Instant createDate;

    /**
     * 最后一次的修改者
     */
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;


    public void setTs(Instant ts) {
        this.ts = ts;
    }

    /**
     * 最后修改的时间
     */
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    /**
     * 时间戳
     * @JsonIgnore 序列化时忽略此字段
     */
    @Column(name = "ts")
    @JsonIgnore
    private Instant ts;

    public Instant getCreateDate() {
        if(null == createDate) {
            createDate = Instant.now();
        }
        return createDate;
    }

    public Instant getLastModifiedDate() {
        if(null == lastModifiedDate) {
            lastModifiedDate = Instant.now();
        }
        return lastModifiedDate;
    }

    public Instant getTs() {
        if(null == ts) {
            ts = Instant.now();
        }
        return ts;
    }

    public boolean tsIsNull() {
        return this.ts == null;
    }

    public boolean lastModifiedDateIsNull() {
        return this.lastModifiedDate == null;
    }

    public DefaultEntity() {
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        this.createDate = now;
        this.lastModifiedDate = now;
        this.ts = now;
    }
}
