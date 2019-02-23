package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CaseTag extends SimpleTagSupport {
    private Object value;

    @Override
    public void doTag() throws JspException, IOException {
        JspTag parentTag = getParent();
        if (parentTag instanceof SwitchTag) {
            SwitchTag switchTag = (SwitchTag) parentTag;
            if (value.equals(switchTag.getValue())) {
                getJspBody().invoke(null);
            }
        } else {
            throw new JspException("case tag should be inside switch tag!");
        }
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
