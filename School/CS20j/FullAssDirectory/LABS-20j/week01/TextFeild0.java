import javafx.scene.control.TextField;

class TextField0 extends TextField {

    public TextField0(String name) {
        super(name);
    }

    public boolean ifCondition_validate(String text) {
        boolean retValue = false;
        retValue = (text.matches("[0-9.]*"));
        return retValue;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (ifCondition_validate(text))
            super.replaceText(start, end, text);
    }

    @Override
    public void replaceSelection(String text) {
        if (ifCondition_validate(text))
            super.replaceSelection(text);
    }
}
