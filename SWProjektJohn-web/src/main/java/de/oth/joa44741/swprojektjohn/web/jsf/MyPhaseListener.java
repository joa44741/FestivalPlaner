/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import java.util.Map;
import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MyPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        printLog(event, "after phase:");
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        printLog(event, "before phase:");
    }

    protected void printLog(PhaseEvent event, String msg) {
        UIViewRoot view = event.getFacesContext().getViewRoot();
        String viewID = "no view";
        if (view != null) {
            viewID = view.getViewId();
        }
        System.out.println(msg + event.getPhaseId() + " " + viewID);

        printRequestParameters(event);
        printRequestAttributes(event);
        printSessionAttributes(event);

    }

    private void printSessionAttributes(PhaseEvent event) {
        Map<String, Object> sessAttrs = event.getFacesContext().getExternalContext().getSessionMap();
        StringBuilder sb = new StringBuilder();
        for (String key : sessAttrs.keySet()) {
            sb.append("(" + key + "=" + sessAttrs.get(key) + ") ");
        }
        System.out.println("Session Attributes : " + sb.toString());
    }

    private void printRequestAttributes(PhaseEvent event) {
        Map<String, Object> reqAttrs = event.getFacesContext().getExternalContext().getRequestMap();
        StringBuilder sb = new StringBuilder();
        for (String key : reqAttrs.keySet()) {
            sb.append("(" + key + "=" + reqAttrs.get(key) + ") ");
        }
        System.out.println("Request Attributes : " + sb.toString());
    }

    private void printRequestParameters(PhaseEvent event) {
        Map<String, String> reqParams = event.getFacesContext().getExternalContext().getRequestParameterMap();
        StringBuilder sb = new StringBuilder();
        for (String key : reqParams.keySet()) {
            sb.append("(" + key + "=" + reqParams.get(key) + ") ");
        }
        System.out.println("Request Parameters : " + sb.toString());
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
