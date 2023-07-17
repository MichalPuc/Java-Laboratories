import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MusicBrainzAPI {

    private static final String API_ROOT_URL = "https://musicbrainz.org/ws/2/";

    /**
     * Returns a random MusicBrainz recording ID (MBID) for a given genre.
     *
     * @param genre the genre to retrieve random recording from
     * @return a random recording MBID for the given genre
     * @throws IOException if an error occurs while making the API request
     */
    public List<String> getRandomMusic(String genre) throws IOException, JSONException {
        // Create API request URL
        String requestUrl = API_ROOT_URL + "recording?" + "query=tag:" + genre + "&limit=100";

        // Send GET request to MusicBrainz API
        String response = sendGetRequest(requestUrl, "application/json");

        // Parse JSON response to extract recording IDs
        List<String> recordingIds = parseRecordingIds(response);

        // Return a random recording ID from the list
        return recordingIds;
    }

    /**
     * Returns the name of the artist associated with a given MusicBrainz recording ID (MBID).
     *
     * @param music the MusicBrainz recording ID (MBID)
     * @return the name of the artist associated with the given MBID
     * @throws IOException if an error occurs while making the API request
     */
    public String getAuthor(String music) throws IOException, JSONException {
        // Create API request URL
        String requestUrl = API_ROOT_URL + "recording/" + music + "?inc=artist-credits";

        // Send GET request to MusicBrainz API
        String response = sendGetRequest(requestUrl, "application/json");

        // Parse JSON response to extract artist name
        return parseArtistName(response);
    }

    public String getMusicTitle(String music) throws IOException, JSONException {
        // Create API request URL
        String requestUrl = API_ROOT_URL + "recording/" + music;

        // Send GET request to MusicBrainz API
        String response = sendGetRequest(requestUrl, "application/json");

        // Parse JSON response to extract music title
        return parseRecordingName(response);
    }

    /**
     * Sends a GET request to the MusicBrainz API and returns the response as a String.
     *
     * @param requestUrl   the URL to send the request to
     * @param acceptHeader the value of the Accept header to be set in the request
     * @return the API response as a String
     * @throws IOException if an error occurs while making the API request
     */
    private String sendGetRequest(String requestUrl, String acceptHeader) throws IOException {
        // Create HTTP connection
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", acceptHeader);

        // Get response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Return response as a String
        return response.toString();
    }

    /**
     * Parses a JSON response from the MusicBrainz API to extract recording IDs.
     *
     * @param response the JSON response from the API
     * @return a List of recording IDs in the response
     */
    private List<String> parseRecordingIds(String response) throws JSONException {
        // Check if the response is not empty and starts with a '{' character
        if (response == null || response.isEmpty() || response.charAt(0) != '{') {
            throw new JSONException("Invalid JSON response: " + response);
        }
        List<String> recordingIds = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(response);
        JSONArray recordingsArray = jsonObject.getJSONArray("recordings");

        // Iterate through the array to extract recording IDs
        for (int i = 0; i < recordingsArray.length(); i++) {
            JSONObject recording = recordingsArray.getJSONObject(i);
            String recordingId = recording.getString("id");
            recordingIds.add(recordingId);
        }

        return recordingIds;
    }

    /**
     * Parses a JSON response from the MusicBrainz API to extract the artist name associated with a recording ID.
     *
     * @param response the JSON response from the API
     * @return the name of the artist associated with the recording ID
     */
    private String parseArtistName(String response) throws JSONException {
        // Check if the response is not empty and starts with a '{' character
        if (response == null || response.isEmpty() || response.charAt(0) != '{') {
            throw new JSONException("Invalid JSON response: response is empty or does not start with '{'");
        }

        JSONObject jsonObject = new JSONObject(response);
        JSONArray artistCreditArray = jsonObject.getJSONArray("artist-credit");

        // Get the first artist credit object in the array
        JSONObject artistCredit = artistCreditArray.getJSONObject(0);

        // Get the artist name from the artist credit object
        JSONObject artist = artistCredit.getJSONObject("artist");
        String artistName = artist.getString("name");
        return artistName;
    }

    /**
     * Parses the name of the recording from the given JSON response.
     *
     * @param response the JSON response from the MusicBrainz API.
     * @return the name of the recording.
     * @throws JSONException if there is an error parsing the JSON response.
     */
    private String parseRecordingName(String response) throws JSONException {
        // Check if the response is not empty and starts with a '{' character
        if (response == null || response.isEmpty() || response.charAt(0) != '{') {
            throw new JSONException("Invalid JSON response: " + response);
        }

        JSONObject jsonObject = new JSONObject(response);
        String recordingName = jsonObject.getString("title");

        return recordingName;
    }

}