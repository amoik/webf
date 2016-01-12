/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import static utils.Utils.fStrDate;
import static utils.Utils.strToDate;
import static utils.Utils.tinf;
import webf.ws.Course;

/**
 *
 * @author WDEZLA
 */

public class CalendarView implements Serializable
{
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Login login;
    private Courses courses;

    public CalendarView()
    {
        eventModel = new DefaultScheduleModel();

        lazyEventModel = new LazyScheduleModel()
        {
            @Override
            public void loadEvents(Date start, Date end){clear();}       
        };
    }

    public void onload()
    {
        tinf("loading calendar");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        login = (Login) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "login");
        courses = (Courses) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "courses");
        courses.onload();

        if(login.getAccount() == null)
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
            return;
        }
        
        
        
        getAll();
    }

        
    public void getAll()
    {
        eventModel.clear();
        for(Course i : courses.getCourses())
        {
            tinf("adding event: " + strToDate(fStrDate(i.getBegin())).toString() + " - " + strToDate(fStrDate(i.getEnd())).toString());
            eventModel.addEvent(new DefaultScheduleEvent(i.getTitle(), strToDate(fStrDate(i.getBegin())), strToDate(fStrDate(i.getEnd()))));
        }
    }
    
    public Date getRandomDate(Date base)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);        //set random day of month

        return date.getTime();
    }

    public Date getInitialDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel()
    {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel()
    {
        return lazyEventModel;
    }

    private Calendar today()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);         
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm()
    {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent()
    {
        tinf("Getting event: " + event.getId() + " " + event.getTitle());
        return event;
    }

    public void setEvent(ScheduleEvent event)
    {
        tinf("Setting event");
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent)
    {
        tinf("Adding event");
        if(event.getId() == null)
        {
            eventModel.addEvent(event);}
        else
        {
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
        tinf("");
    }

    public void onEventMove(ScheduleEntryMoveEvent event)
    {
        tinf("Moved event");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event)
    {
        tinf("Resized event");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message)
    {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
