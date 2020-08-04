package com.munichre.letsencrypt;

enum Main {
    INSTANCE;

   void deleteFile (String path) {}
    void getPath (String first, String rest) {}
    void createKeystore () {}
    void getCsr (String dir, String fname, String domainAlias) {}
    void getKeypair (String keystore, String alias, String password)  {}
    void loadKeystore (String  keystoreFile, String password) {}
    void base64 (String src) {}
    void bigInttobase64 (String bigInt) {}
    void toJsonwebkey (String key) {}
    void setHeaders (String request, String headers) {}
    void curl (String url, String method, String body, String headers) {}
    void stringTobase64 (String str) {}
    void sign (String data, String key) {}
    void rawSendsignedrequest (String url, String payload, String errMsg, String dir, String privateKey, String alg, String accountHeaders, String jwk) {}
    void isBadnonce (String response) {}
    void sendSignedrequest (String request)  {}
    void newAccount (String privateKey, String directory, String regPayload, String jwk) {}
    void updateContact (String headers, String jwk, String directory, String contact, String privateKey) {}
    void newOrder (String headers, String privateKey, String directory, String domains, String jwk) {}
    void getAuthorizations (String accountHeaders, String privateKey, String directory, String jwk, String order) {}
    void getThumbprint   (String jwk) {}
    void newBufferedwriter (String path, String options) {}
    void doUntil (String pred, String task) {}
    void validate (String authorization, String thumbprint, String jwk, String wellKnowndir, String accountHeaders, String privateKey, String directory) {}
    public void renewCertificates() {}
    public static void main(String[] args) {
                 List<String>    concats = Arrays.asList("mailto:bogus@test.com");
             List<String>    domains = Arrays.asList("mr.quaxt.ie");
             Object csr = getCsr( "/home/mreilly/wa/mrAcme/pg3",
                                  "keystore.p12",
                             "domainKey");
    
    }
    // {
            
        

    //         String wellKnowndir = "/var/www/challenges/"
    //              privateKey (getKeypair 
    //                           (loadKeystore
    //                            (getPath "/home/mreilly/wa/mrAcme/pg3/keystore.p12")
    //                            "changeit")
    //                     "accountKey" "changeit").getPrivate()
    //             Object jwk = toJsonwebkey(privateKey);
    //             String directoryUrl = "https://pebble:14000/dir";
    //              directory (> (curl {:url directoryUrl})
    //                         :body
    //                         json/readStr)
    //              regPayload  {"termsOfServiceAgreed" true}
    //          account (newAccount privateKey directory regPayload jwk)
    //              accountHeaders (:headers account)
    //              _ (println (if (= 201 (:statusCode account))
    //                              "Registered!"
    //                                  "Already registered!"))
    //              account (if contact
    //                       (updateContact accountHeaders jwk directory contact privateKey)
    //                       account)
      
    //              orderResp (newOrder accountHeaders privateKey directory domains jwk)
      
    //              order (json/readStr (:body orderResp))
    //              authorizations (getAuthorizations accountHeaders privateKey directory jwk order)
    //              thumbprint (getThumbprint jwk)
    //              validations (doall (map (fn[authorization]
    //                                       (validate
    //                                        authorization thumbprint jwk
    //                                        wellKnownDir accountHeaders
    //                                        privateKey directory)) authorizations))
    //              _ (println "validation done")
    //              finalizeResp (sendSignedRequest
    //                            {:accountHeaders accountHeaders
    //                                    :privateKey privateKey
    //                                    :url (order "finalize")
    //                                    :dir directory
    //                                    :payload  {"csr" (base64 csr)}
    //                                :alg "RS256"
    //                                     :jwk jwk})
    //              order (doUntil #(> % :Body json/readStr #{"pending", "processing"} not)
    //                     #(sendSignedRequest
    //                       {:accountHeaders accountHeaders
    //                               :privateKey privateKey
    //                               :url (getIn orderResp [:headers "Location"])
    //                               :dir directory
    //                               :alg "RS256"
    //                               :jwk jwk}))
    //              (sendSignedRequest
    //               {:accountHeaders accountHeaders
    //                       :privateKey privateKey
    //                       :url (> Order :body json/readStr (get "certificate"))
    //                       :dir directory
    //                       :alg "RS256"
    //                       :jwk jwk})
    // }
}
