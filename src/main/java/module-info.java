module com.msmith.investx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires YahooFinanceAPI;
    requires java.desktop;

    opens com.msmith.investx to javafx.fxml;
    exports com.msmith.investx;
    exports com.msmith.investx.controller.setup;
    opens com.msmith.investx.controller.setup to javafx.fxml;
    exports com.msmith.investx.controller;
    opens com.msmith.investx.controller to javafx.fxml;
    exports com.msmith.investx.model;
    opens com.msmith.investx.model to javafx.fxml;
    exports com.msmith.investx.controller.utilities;
    opens com.msmith.investx.controller.utilities to javafx.fxml;
}