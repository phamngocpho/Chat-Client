package Client.Features.Notifications.drawer.component.menu;

public class MenuAction {
    private boolean consume;

    private boolean selected;

    protected boolean getConsume() {
        return consume;
    }

    public void consume() {
        consume = true;
    }

    protected boolean getSelected() {
        return selected;
    }

    public void selected() {
        selected = true;
    }
}