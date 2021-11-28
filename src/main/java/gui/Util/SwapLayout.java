package gui.Util;

import java.awt.*;

public class SwapLayout extends CardLayout {

    Container parent;
    public SwapLayout(Container parent) {
        this.parent = parent;
    }

    public SwapLayout(int hgap, int vgap,Container parent) {
        super(hgap, vgap);
        this.parent = parent;
    }


    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        super.addLayoutComponent(comp, constraints);
        super.next(parent);

        if (parent.getComponents().length > 1){
            parent.remove(0); // Delete old
        }
    }



    /* DO NOTHING */

    @Override
    public void first(Container parent) {}

    @Override
    public void next(Container parent) {}

    @Override
    public void previous(Container parent) {}

    @Override
    public void last(Container parent) {}

    @Override
    public void show(Container parent, String name) {}

    
}
