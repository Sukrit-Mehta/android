package ca.binder.remote.binding;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ca.binder.domain.Course;
import ca.binder.domain.Match;

/**
 * @author Mitchell Hentges
 * @since 11/11/2015
 */
public class MatchBinding implements JsonToModelBinding<Match> {
    @Override
    public Match model(JSONObject json) {
        try {
            String name = json.getString("name");
			String bio = json.getString("bio");
			String phone = json.getString("phone");
			String program = json.getString("program");
			String year = json.getString("year");

			JSONArray courseArray = json.getJSONArray("courses");
			List<Course> coursesList = new ArrayList<>();
			for (int i = 0; i < courseArray.length(); i++) {
				coursesList.add(new Course(courseArray.get(i).toString()));
			}

            if (name == null || phone == null) {
                Log.w("MatchBinding", "server didn't provide or returned null value");
                return null;
            }

            return new Match(
                    name, bio, phone, program, year,
					//photo TODO
					coursesList);
        } catch (JSONException e) {
            return null;
        }
    }
}
