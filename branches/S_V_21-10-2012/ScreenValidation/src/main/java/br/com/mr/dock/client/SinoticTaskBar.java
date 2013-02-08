package br.com.mr.dock.client;

import java.util.Date;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.i18n.client.DateTimeFormat;

public class SinoticTaskBar extends LayoutContainer{

	
	public void update(final Date date) {
        // extract hours/minutes/seconds from the date
        final DateTimeFormat secondFormat = DateTimeFormat.getFormat("s");
        final DateTimeFormat minuteFormat = DateTimeFormat.getFormat("m");
        final DateTimeFormat hourFormat = DateTimeFormat.getFormat("h");
 
        final int seconds = Integer.valueOf(secondFormat.format(date));
        final int minutes = Integer.valueOf(minuteFormat.format(date));
        final int hours = Integer.valueOf(hourFormat.format(date));

        // 60 seconds = 360 degrees ; 1 second = 6 degrees ; x seconds = x * 6
        // degrees
        // 6d is the notation for (double) 6
 
        // 60 minutes = 360 degrees ; 1 minute = 6 degrees ; x minutes = x * 6
        // degrees
        // 6d is the notation for (double) 6
 
        // 12 hours = 360 degrees ; 1 hour = 30 degrees ; x hours = x * 6
        // degrees
        // 30d is the notation for (double) 30
 
}
}
