package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class SwitchTag extends SimpleTagSupport {
    private Object value;

    @Override
    public void doTag() throws JspException, IOException {
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
