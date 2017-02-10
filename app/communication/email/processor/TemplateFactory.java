package communication.email.processor;

import communication.email.templates.CalenderInvite;
import communication.email.templates.MeetingInviteTemplate;
import communication.email.templates.TEMPLATES;
import communication.email.templates.Template;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class TemplateFactory {


    /**
     * Get a Template
     * @param template
     * @return
     */
    public Template getTemplate(TEMPLATES template) {

        if (template == TEMPLATES.MEETING_INVITE_EMAIL_TEMPLATE) {
            //meeting invite body
            return new MeetingInviteTemplate();
        } else if(template == TEMPLATES.CALENDER_INVITE) {
            //calender invite
            return new CalenderInvite();
        }
        return null;
    }
}
