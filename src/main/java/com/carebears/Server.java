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
        CONFIG.setSession(new Session());

        MimeTypesStore mimeTypesStore = MimeTypesStore.getInstance();
        mimeTypesStore.addMimeType("image/jpg jpg JPEG JPG jpeg", true);
        mimeTypesStore.addMimeType("text/html htm html", false);
        mimeTypesStore.addMimeType("text/plain txt TXT", false);
        mimeTypesStore.addMimeType("text/javascript js", false);
        mimeTypesStore.addMimeType("image/png png", true);
        mimeTypesStore.addMimeType("image/gif gif", true);

        CareBearHttpHandler handler = CONFIG.getHandler();
        handler.registerServlet(new FormServlet());
        handler.registerServlet(new RootServlet());
        handler.registerServlet(new RedirectServlet());
        handler.registerServlet(new ParametersServlet());
        handler.registerServlet(new MethodOptionsServlet());
        handler.registerServlet(new LogsServlet());
<<<<<<< HEAD
        handler.registerServlet(new LogServlet());
        handler.registerServlet(new TheseServlet());
        handler.registerServlet(new RequestsServlet());
=======
        handler.registerServlet(new PatchWithETagServlet());
>>>>>>> ca0e7bccf30efee3229adbf7841bf3c54a9ff2de

        CONFIG.getServerSocket().start();
    }

}
