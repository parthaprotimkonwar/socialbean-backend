package communication.email.processor;

import communication.email.templates.Template;

import java.util.Map;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class TemplateFieldsFacade {

    private Template template = null;
    private Fields fields = null;

    public TemplateFieldsFacade(Template template, Fields fields) {
        this.template = template;
        this.fields = fields;
    }


    public String merge() {
        Map<String, String> placeholderMap = fields.getFields();
        StringBuilder templateBuilder = new StringBuilder();
        boolean isTemplate = false;
        StringBuilder placeholder = new StringBuilder();
        for(char templateChar : template.getTemplateString().toCharArray()) {

            switch (templateChar) {
                case '`' :
                    if(isTemplate) {
                        String placeholderValue = placeholderMap.get(placeholder.toString());
                        templateBuilder.append(placeholderValue);
                        placeholder = new StringBuilder();
                        isTemplate = false;
                    } else {
                        isTemplate = true;
                    }
                    break;

                default :
                    if(!isTemplate) {
                        templateBuilder.append(templateChar);
                    }
                    break;
            }

            if(isTemplate && templateChar!='`') {
                placeholder.append(templateChar);
            }
        }
        return templateBuilder.toString();
    }

}
