package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DefaultTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspTag parentTag = getParent();
        if (parentTag instanceof SwitchTag) {
            getJspBody().invoke(null);
        } else {
            throw new JspException("case tag should be inside switch tag!");
        }
    }

}