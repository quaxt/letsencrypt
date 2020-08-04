package com.munichre.letsencrypt;

import com.google.common.collect.ImmutableMap;

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

    Map<String, String> sh(Path directory, String... commandArgs) {
        ProcessBuilder pb =
            new ProcessBuilder(commandArgs);
        pb.directory(directory.toFile());
        Process p = pb.start();
        return ImmutableMap.of("out", readOutStream(p.getInputStream()),
                               "err", readOutStream(p.getErrorStream()));
    }
}
