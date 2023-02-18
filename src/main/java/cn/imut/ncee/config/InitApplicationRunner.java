package cn.imut.ncee.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:08
 */
@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void run(ApplicationArguments args) {
        kafkaProducer.send();
    }
}
