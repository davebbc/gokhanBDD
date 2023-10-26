package io.cucumber.Requests;

public class Requests {

    public static String createObjectRequestBody(){
         String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 13\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"description\": \"Intel Core i9\",\n" +
                "      \"color\": \"Purple\"\n" +
                "   }\n" +
                "}";
         return requestBody;
    }

    public static String updateObjectRequestBody(){
        String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 12\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1349.99,\n" +
                "      \"description\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";
        return requestBody;
    }

    public static String updateObjectNameRequestBody(String name) {
        String requestBody = "{\n" +
                "   \"name\": \"" + name + "\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1349.99,\n" +
                "      \"description\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";
        return requestBody;
    }


}
