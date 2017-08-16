package com.lembrol.ana.Model;

import java.io.Serializable;

public class Reminder implements Serializable{

    private String reminderBody;
    private String titleReminder;

    public Reminder(String titleReminder, String reminderBody) {
        this.titleReminder = titleReminder;
        this.reminderBody = reminderBody;
    }

    public Reminder(){

    }

    public String getReminderBody() {
        return reminderBody;
    }

    public void setReminderBody(String reminderBody) {
        this.reminderBody = reminderBody;
    }

    public String getTitleReminder() {
        return titleReminder;
    }

    public void setTitleReminder(String titleReminder) {
        this.titleReminder = titleReminder;
    }
}
