package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class ForEachTag extends SimpleTagSupport {

    private String var;
    private Object items;

    public ForEachTag() {
        setDefaults();
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (items instanceof Iterable) {
            Iterable itemsIterable = (Iterable) items;
            for (Object item : itemsIterable) {
                processItem(item);
            }
        } else {
            if (items instanceof Iterator) {
                Iterator itemsIterator = (Iterator) items;
                while (itemsIterator.hasNext()) {
                    processItem(itemsIterator.next());
                }
            } else {
                if (items instanceof Enumeration) {
                    Enumeration itemsEnumeration = (Enumeration) items;
                    while (itemsEnumeration.hasMoreElements()) {
                        processItem(itemsEnumeration.nextElement());
                    }
                } else {
                    if (items instanceof String) {
                        String itemsString = (String) items;
                        for (Object item : itemsString.split(",")) {
                            processItem(item);
                        }
                    } else {
                        if (items != null) {
                            throw new JspException("Can't iterate throw items:" + items.getClass());
                        }
                    }
                }
            }
        }
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    private void setDefaults() {
        items = Arrays.asList("first", "second");
        //items = Arrays.asList(null, null);
        //items = new String[]{"first", "second"};
    }

    private void processItem(Object item) throws IOException, JspException {
        getJspContext().setAttribute(var, item);
        getJspBody().invoke(null);
    }
}
