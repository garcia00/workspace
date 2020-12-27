package br.com.j.hemopa.digital.model;

import java.util.Date;
import java.util.Map;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleRenderingMode;

public class CustomScheduleEvent implements ScheduleEvent {
	
	 private String id;
	    private String title;
	    private Date startDate;
	    private Date endDate;
	    private String styleClass;
	    private Object data;
	    private String url;
	    private String description;
	    private boolean allDay;
	    private boolean editable;

	    public CustomScheduleEvent() {
	    }

	    public CustomScheduleEvent(String title, Date start, Date end, boolean allDay, Object data) {
	        this.title = title;
	        this.startDate = start;
	        this.endDate = end;
	        this.allDay = allDay;
	        this.data = data;
	    }

	    public CustomScheduleEvent(String title, Date start, Date end, String styleClass, boolean allDay, Object data) {
	        this.title = title;
	        this.startDate = start;
	        this.endDate = end;
	        this.styleClass = styleClass;
	        this.allDay = allDay;
	        this.data = data;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    
	    public Date getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(Date startDate) {
	        this.startDate = startDate;
	    }

	    
	    public Date getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(Date endDate) {
	        this.endDate = endDate;
	    }

	    
	    public boolean isAllDay() {
	        return allDay;
	    }

	    public void setAllDay(boolean allDay) {
	        this.allDay = allDay;
	    }

	    
	    public String getStyleClass() {
	        return styleClass;
	    }

	    public void setStyleClass(String styleClass) {
	        this.styleClass = styleClass;
	    }

	    
	    public Object getData() {
	        return data;
	    }

	    public void setData(Object data) {
	        this.data = data;
	    }

	    
	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    
	    public boolean isEditable() {
	        return editable;
	    }

	    public void setEditable(boolean editable) {
	        this.editable = editable;
	    }

		@Override
		public ScheduleRenderingMode getRenderingMode() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Object> getDynamicProperties() {
			// TODO Auto-generated method stub
			return null;
		}

}
