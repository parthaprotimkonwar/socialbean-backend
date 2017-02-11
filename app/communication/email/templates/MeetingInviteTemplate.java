package communication.email.templates;

import application.utilities.Constants;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class MeetingInviteTemplate implements Template {


    @Override
    public String getTemplateString() {
        String htmlMessage = "<html>\n" +
                "<head>\n" +
                "    <meta name=\"content\" content=\"Email Invite\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <!-- IE Edge Meta Tag -->\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <!-- Viewport -->\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <!-- Minified CSS -->\n" +
                "    <title>Email Invite</title>\n" +
                "    <style>\n" +
                "        .bgimage {\n" +
                "            background-color: midnightblue;\n" +
                "            height: 40px;\n" +
                "            vertical-align: middle;\n" +
                "            line-height: 40px;\n" +
                "            color: beige;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"row\">\n" +
                "        <!--padding-->\n" +
                "        <div style=\"margin-left: 0%; margin-right: 0%; border-style: solid; border-width: 2px; border-color: darkgray\">\n" +
                "            <!--Actual Content-->\n" +
                "            <!--Header-->\n" +
                "            <br>\n" +
                "            <div>\n" +
                "                <img src=\"" + Constants.BACKEND_ASSETS_LOGO +"\">\n" +
                "            </div>\n" +
                "            <hr>\n" +
                "\n" +
                "            <div>\n" +
                "                <div style=\"text-align: center\">\n" +
                "                    <p><strong style=\"font-size: 2em\">`professor_name`</strong>, `professor_designation`,</p>\n" +
                "                    <p>Department of `department_name`</p>\n" +
                "                    <p><em>&nbsp;</em></p>\n" +
                "                    <p><em>invites you to join a meeting scheduled on</em></p>\n" +
                "                    <p><strong style=\"font-size: 1.2em\">`scheduled_datetime`</strong>\n" +
                "                    </p>\n" +
                "                    <p><strong>&nbsp;</strong></p>\n" +
                "                    <p><strong>&nbsp;</strong></p>\n" +
                "                    <p><strong style=\"font-size: 1.7em; color: midnightblue\">Topic : &ldquo;`topic`&rdquo;</strong></p>\n" +
                "\n" +
                "                    <p><strong>&nbsp;</strong></p>\n" +
                "                    <p style=\"font-size: 1.2em\">Meeting Url : &nbsp;<a href=\"`url`\">`url`</a>\n" +
                "                    </p>\n" +
                "                    <p>&nbsp;</p>\n" +
                "\n" +
                "                    <div style=\"display: inline-block\">\n" +
                "                        <span style=\"text-align: left\">\n" +
                "                            <p>Rules:</p>\n" +
                "                            <ul>\n" +
                "                                <li>Please click on the above URL below to join the meeting</li>\n" +
                "                                <li>Please use your microphones and speakers (VoIP) – headset is recommended.</li>\n" +
                "                            </ul>\n" +
                "                        </span>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <br><br>\n" +
                "            <div class=\"bgimage\" style=\"text-align: center\">\n" +
                "                &nbsp;Powered by Consocio ®\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return htmlMessage;
    }
}
