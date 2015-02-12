package org.bojo.calendarcolors;

/**
 * Created by artemsavenkov on 06.02.15.
 */
public class UserCalendars {
    public String name;
    public String id;
    public UserCalendars(String _name, String _id) {
        name = _name;
        id = _id;
    }
    @Override
    public String toString() {
        return name;
    }
}
