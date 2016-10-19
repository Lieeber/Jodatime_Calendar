package demo.lieeber.com.calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Created by lieeber on 16/8/29.
 */

public class ScheduleDate {

    public DateTime dateTime;

    public int todoCount;
    public boolean isSpace;
    public boolean isSelected;
    public boolean isFinished = false;
    public boolean isBeforeNow;

    public ScheduleDate(DateTime dateTime, boolean isSelected, int todoCount) {
        this.dateTime = dateTime;
        this.isSelected = isSelected;
        this.todoCount = todoCount;
        DateTime nowDate = new DateTime();
        if (Days.daysBetween(dateTime, nowDate).getDays() > 0) {
            isBeforeNow = true;
        } else {
            isBeforeNow = false;
        }

    }

    public ScheduleDate() {
        this.dateTime = new DateTime();
        isSpace = true;
    }
}
