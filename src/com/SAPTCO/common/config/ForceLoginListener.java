package com.SAPTCO.common.config;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class ForceLoginListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent arg0) {
	}

	public void beforePhase(PhaseEvent arg0) {
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		Application app = context.getApplication();
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		if (!viewId.equals( "/faces/pages/common/login.xhtml" ) && request.getSession().getAttribute("userInf") == null){
			ViewHandler viewHandler = app.getViewHandler();
			UIViewRoot viewRoot = viewHandler.createView(context,"/faces/pages/common/login.xhtml");
			context.setViewRoot(viewRoot);
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
