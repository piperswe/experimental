package me.piperswe;

import com.google.common.util.concurrent.ServiceManager;
import me.piperswe.shell.Shell;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try {
            var serviceManager = new ServiceManager(Collections.singletonList(new Shell()));
            serviceManager.startAsync();
            serviceManager.awaitStopped();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}