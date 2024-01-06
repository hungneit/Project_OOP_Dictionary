package App;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslateAPI {
    private static final String API_ENDPOINT = "https://api.mymemory.translated.net/get";

    public static String translateText(String text, String sourceLang, String targetLang) {
        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            String apiUrl = String.format("%s?q=%s&langpair=%s|%s", API_ENDPOINT, encodedText, sourceLang, targetLang);

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());

            if (jsonResponse.containsKey("responseData")) {
                JSONObject responseData = (JSONObject) jsonResponse.get("responseData");

                if (responseData.containsKey("translatedText")) {
                    String translatedText = (String) responseData.get("translatedText");
                    return translatedText;
                }
            }
            return "Not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}

