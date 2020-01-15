//package com.iMobile3.framework.utilities;
//
//import com.iMobile3.framework.base.BasePage;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpHead;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.junit.Assert;
//
//import static com.iMobile3.framework.base.Browser.linkHREF;
//
//public class HttpLinkChecker extends BasePage {
//
//    //Method to check response status code of HEAD request asserting that file exists on the server.
//    public static void fileCanBeDownloaded() throws Exception {
//        String strLink = linkHREF;
//        String fileName = "";
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpHead request = new HttpHead(strLink);
//
//        CloseableHttpResponse response = httpClient.execute(request);
//        String contentType = response.getFirstHeader("Content-Type").getValue();
//        int contentStatus = response.getStatusLine().getStatusCode();
//        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());
//        fileName = strLink.substring(strLink.lastIndexOf("/") + 1, strLink.length());
//
//        System.out.println("----------------------------------------");
//        System.out.println("Status Code = " + contentStatus);//TO DEBUG
//        System.out.println("Content-Type = " + contentType);//TO DEBUG
//        System.out.println("Content-Length = " + contentLength);//TO DEBUG
//
//
//        Assert.assertTrue("The content is not available", contentStatus == (200));
//        System.out.println("Final Endpoint = " + fileName);
//        System.out.println("File downloaded");
//        response.close();
//        httpClient.close();
//    }
//
//
//}
