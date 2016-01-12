/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import static utils.Utils.addMessage;
import static utils.Utils.fDateStr;
import static utils.Utils.tinf;
import webf.ws.Course;
import webf.ws.Person;
import webf.ws.PersonCourseMembership;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author WDEZLA
 */

@ManagedBean
@ViewScoped
public class CalendarView implements Serializable {
    
    private ScheduleModel eventModel;
    
    private ScheduleModel lazyEventModel;
    
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        
        eventModel.addEvent(new DefaultScheduleEvent("Testeintrag 3", previousDay8Pm(), previousDay8Pm()));
/*
        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
*/         
  
        lazyEventModel = new LazyScheduleModel() {
             
            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Testeintrag 1", random, random));
                 
                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Testeintrag 2", random, random));
            }   
        };
    }
    
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
    }
        
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
    
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
         
        return t.getTime();
    }
    
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
}
