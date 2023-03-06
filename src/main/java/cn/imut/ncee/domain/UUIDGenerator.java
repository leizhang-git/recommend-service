package cn.imut.ncee.domain;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.io.Serializable;

/**
 * @Auth zhanglei
 * @Date 2023/2/28 19:15
 */
public class UUIDGenerator extends org.hibernate.id.UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        try {
           Object id = FieldUtils.readField(object, "id", true);
            if (id != null) {
                return (Serializable) id;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return super.generate(session, object);
    }
}
