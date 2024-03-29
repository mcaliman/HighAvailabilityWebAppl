package com.trueprogramming.hawa;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 * Migrated to glassfish 5.1.0 from 4.1.2
 *
 * @author Massimo Caliman
 */
@SessionScoped
@Named
public class Bean implements Serializable {

    public String hostname() {
        String hostname = "undefined";
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                hostname = System.getenv("COMPUTERNAME");
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac os x")) {
                hostname = cat("/bin/cat /etc/hostname");
            }
        } catch (IOException e) {
            hostname += e.getMessage();
        }
        return hostname;
    }

    private String cat(String execCommand) throws IOException {
        try (Scanner s = new Scanner(Runtime.getRuntime().exec(execCommand).getInputStream()).useDelimiter("\\A")) {
            return s.hasNext() ? s.next() : "";
        }
    }
}
