module com.msmith.investx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.msmith.investx to javafx.fxml;
    exports com.msmith.investx;
    exports com.msmith.investx.controller;
    opens com.msmith.investx.controller to javafx.fxml;
}