package test20;

import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JSONExample {
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    // Constructor, getters và setters

    public JSONObject toJSONObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fullName", fullName);
            json.put("gender", gender);

            // Chuyển LocalDate sang String
            if (dateOfBirth != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                String dateOfBirthStr = dateOfBirth.format(formatter);
                json.put("dateOfBirth", dateOfBirthStr);
            }

            json.put("email", email);
            json.put("password", password);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String jsonString = "{ \"fullName\": \"John Doe\", \"gender\": \"male\", \"dateOfBirth\": \"1990-01-01\", \"email\": \"john.doe@example.com\", \"password\": \"password123\" }";
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            String dateOfBirthStr = json.getString("dateOfBirth");
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr, formatter);
            System.out.println("Parsed LocalDate: " + dateOfBirth);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

