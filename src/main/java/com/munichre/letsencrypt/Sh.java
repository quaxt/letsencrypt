package com.munichre.letsencrypt;

import com.google.common.collect.ImmutableMap;
import java.nio.file.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.Map;

class Sh {
    static CompletableFuture<String> readOutStream(InputStream is) {
        return CompletableFuture.supplyAsync(() -> {
                try (
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader br = new BufferedReader(isr);
                     ){
                    StringBuilder res = new StringBuilder();
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        res.append(inputLine).append(System.lineSeparator());
                    }
                    return res.toString();
                } catch (Throwable e) {
                    throw new RuntimeException("problem with executing program", e);
                }
            });
    }

    static Map<String, String> sh(Path directory, String... commandArgs) {
        try {
            Process p = new ProcessBuilder(commandArgs)
                .directory(directory.toFile())
                .start();
            return ImmutableMap.of("out", readOutStream(p.getInputStream()).get(),
                               "err", readOutStream(p.getErrorStream()).get());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
