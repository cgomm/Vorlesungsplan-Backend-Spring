package controller;

import org.jsoup.Jsoup;

public class JsoupNetworkController {
    private static JsoupNetworkController instance;
    private JsoupNetworkController() {

    }

    public static JsoupNetworkController getInstance() {
        if (JsoupNetworkController.getInstance() == null) {
            JsoupNetworkController.instance = new JsoupNetworkController();
        }
        return JsoupNetworkController.instance;
    }
}
