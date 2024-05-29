package Client.Features.Notifications.popup.component;

public abstract class PopupController {

    private boolean consume;

    public boolean getConsume() {
        return consume;
    }

    public void consume() {
        consume = true;
    }

    public abstract void closePopup();
}
