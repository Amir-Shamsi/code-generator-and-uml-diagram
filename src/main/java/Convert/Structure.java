package Convert;

import java.util.ArrayList;

public abstract class Structure extends ClassInfo {
    public static ArrayList<ClassInfo> classInfo = new ArrayList<ClassInfo>();
    protected final String CLASS = "class";
    protected final String PUBLIC = "public";
    protected final String PROTECTED = "protected";
    protected final String PRIVATE = "private";

    abstract protected void engine();

    abstract void addClass(ClassInfo cls);

    abstract void addMethod(Method met);

    abstract void addBody(Method.Body bd, boolean status);

    protected abstract void error(Object err);


}
