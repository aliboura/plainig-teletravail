package dz.djezzydevs.hrplaning.config;

import dz.djezzydevs.hrplaning.utils.TravisAes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {


    @Override
    public void run(String...args) throws Exception {
        TravisAes travisAes = new TravisAes();
        String encrypted = travisAes.encrypt("appdjz", "ali.bouras");
        String decrypted = travisAes.decrypt("appdjz", encrypted);
        System.out.println("encrypted: " + encrypted);
        System.out.println("decrypted: " + decrypted);


    }
}