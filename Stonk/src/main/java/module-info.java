module stonk {
    requires javafx.controls;
    requires javafx.fxml;

    opens stonk to javafx.graphics, javafx.fxml;
}
