package com.carebears;

import com.carebears.servlets.*;
import com.carebears.servlets.ParametersServlet;

import java.io.IOException;

public class Server {

    public static ConfigBear CONFIG = new ConfigBear();

    public static void main(String[] args) throws IOException {
        String path = CONFIG.getDocumentRoot();
        String portString = "" + CONFIG.getPort();
        int numArgs = args.length;

        if (numArgs == 2 || numArgs == 4) {
            int xctr = 0;

            while(xctr < numArgs) {
                switch (args[xctr]) {
                    case "-p":
                        portString = args[xctr + 1];
                        xctr += 2;
                        break;
                    case "-d":
                        path = args[xctr + 1];
                        xctr += 2;
                        break;
                    default:
                        System.out.println("Invalid argument: " + args[xctr]);
                        break;
                }
            }
        }

        CONFIG.setDocumentRoot(path);
        CONFIG.setPort(Integer.parseInt(portString));
        CONFIG.setHandler(new InternetHttpHandler());
        CONFIG.setServerSocket(new InternetServerSocket());

        MimeTypesStore mimeTypesStore = MimeTypesStore.getInstance();
        mimeTypesStore.addMimeType("image/jpg jpg JPEG JPG jpeg");
        mimeTypesStore.addMimeType("text/html htm html");
        mimeTypesStore.addMimeType("text/plain txt TXT");
        mimeTypesStore.addMimeType("text/javascript js");
        mimeTypesStore.addMimeType("image/png png");
        mimeTypesStore.addMimeType("image/gif gif");

        CareBearHttpHandler handler = CONFIG.getHandler();
        handler.registerServlet(new FormServlet());
        handler.registerServlet(new RootServlet());
        handler.registerServlet(new RedirectServlet());
        handler.registerServlet(new ParametersServlet());
        handler.registerServlet(new MethodOptionsServlet());

        CONFIG.getServerSocket().start();
    }

}
