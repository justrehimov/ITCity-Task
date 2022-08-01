package az.itcity.itcitytask;

import az.itcity.itcitytask.entity.Status;
import az.itcity.itcitytask.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ItCityTaskApplication implements CommandLineRunner {

    @Autowired
    private StatusRepository statusRepository;

    public static void main(String[] args) {
        SpringApplication.run(ItCityTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(statusRepository.findAll().isEmpty()){
            List<Status> statusList = Arrays.asList(
                    new Status(null, "online"),
                    new Status(null, "offline")
            );
            statusRepository.saveAll(statusList);
        }
    }
}
