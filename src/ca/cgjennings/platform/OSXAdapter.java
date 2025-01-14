package ca.cgjennings.platform;

/*
 * Adapted from Apple's OSXAdapter, which allows redistribution and modification
 * with the requirement that if the software is distributed unmodified their
 * license and disclaimers must be included. (This is a modified copy, so a copy
 *  of their license is not required.)
 */
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Proxy class that reflectively installs handlers through Apple's
 * {@code com.apple.eawt.Application} class. Adapted from Apple's
 * OSXAdapter, which allows redistribution and modification.
 */
public class OSXAdapter implements InvocationHandler {

    protected Object targetObject;
    protected Method targetMethod;
    protected String proxySignature;
    protected static Object macOSXApplication;

    /**
     * Pass this method an Object and Method equipped to perform application
     * shutdown logic. The method passed should return a boolean stating whether
     * or not the quit should occur.
     */
    public static void setQuitHandler(Object target, Method quitHandler) {
        setHandler(new OSXAdapter("handleQuit", target, quitHandler));
    }

    /**
     * Pass this method an Object and Method equipped to display application
     * info. They will be called when the About menu item is selected from the
     * application menu.
     */
    public static void setAboutHandler(Object target, Method aboutHandler) {
        boolean enableAboutMenu = (target != null && aboutHandler != null);
        if (enableAboutMenu) {
            setHandler(new OSXAdapter("handleAbout", target, aboutHandler));
        }
        // If we're setting a handler, enable the About menu item by calling
        // com.apple.eawt.Application reflectively
        try {
            Method enableAboutMethod = macOSXApplication.getClass().getDeclaredMethod("setEnabledAboutMenu", new Class[]{boolean.class});
            enableAboutMethod.invoke(macOSXApplication, new Object[]{enableAboutMenu});
        } catch (Exception ex) {
            Logger.getLogger(OSXAdapter.class.getName()).log(Level.WARNING, "could not access the About Menu", ex);
        }
    }

    /**
     * Pass this method an Object and a Method equipped to display application
     * options. They will be called when the Preferences menu item is selected
     * from the application menu.
     */
    public static void setPreferencesHandler(Object target, Method prefsHandler) {
        boolean enablePrefsMenu = (target != null && prefsHandler != null);
        if (enablePrefsMenu) {
            setHandler(new OSXAdapter("handlePreferences", target, prefsHandler));
        }
        // If we're setting a handler, enable the Preferences menu item by calling
        // com.apple.eawt.Application reflectively
        try {
            Method enablePrefsMethod = macOSXApplication.getClass().getDeclaredMethod("setEnabledPreferencesMenu", new Class[]{boolean.class});
            enablePrefsMethod.invoke(macOSXApplication, new Object[]{enablePrefsMenu});
        } catch (Exception ex) {
            Logger.getLogger(OSXAdapter.class.getName()).log(Level.WARNING, "could not access the About Menu", ex);
        }
    }

    /**
     * Pass this method an Object and a Method equipped to handle document
     * events from the Finder Documents are registered with the Finder via the
     * CFBundleDocumentTypes dictionary in the application bundle's Info.plist.
     */
    public static void setFileHandler(Object target, Method fileHandler) {
        setHandler(new OSXAdapter("handleOpenFile", target, fileHandler) {
            // Override OSXAdapter.callTarget to send information on the
            // file to be opened

            @Override
            public boolean callTarget(Object appleEvent) {
                if (appleEvent != null) {
                    try {
                        Method getFilenameMethod = appleEvent.getClass().getDeclaredMethod("getFilename", (Class[]) null);
                        String filename = (String) getFilenameMethod.invoke(appleEvent, (Object[]) null);
                        this.targetMethod.invoke(this.targetObject, new Object[]{filename});
                    } catch (Exception ex) {
                    }
                }
                return true;
            }
        });
    }

    // setHandler creates a Proxy object from the passed OSXAdapter and adds it as an ApplicationListener
    public static void setHandler(OSXAdapter adapter) {
        try {
            Class applicationClass = Class.forName("com.apple.eawt.Application");
            if (macOSXApplication == null) {
                macOSXApplication = applicationClass.getConstructor().newInstance();
            }
            Class applicationListenerClass = Class.forName("com.apple.eawt.ApplicationListener");
            Method addListenerMethod = applicationClass.getDeclaredMethod("addApplicationListener", new Class[]{applicationListenerClass});
            // Create a proxy object around this handler that can be reflectively added as an Apple ApplicationListener
            Object osxAdapterProxy = Proxy.newProxyInstance(OSXAdapter.class.getClassLoader(), new Class[]{applicationListenerClass}, adapter);
            addListenerMethod.invoke(macOSXApplication, new Object[]{osxAdapterProxy});
        } catch (ClassNotFoundException cnfe) {
            Logger.getLogger(OSXAdapter.class.getName()).warning("OS X/Java version does not support com.apple.eawt.Application");
        } catch (Exception ex) {
            Logger.getLogger(OSXAdapter.class.getName()).log(Level.SEVERE, "unexpected error while installing OSXAdapter", ex);
        }
    }

    // Each OSXAdapter has the name of the EAWT method it intends to listen for (handleAbout, for example),
    // the Object that will ultimately perform the task, and the Method to be called on that Object
    protected OSXAdapter(String proxySignature, Object target, Method handler) {
        this.proxySignature = proxySignature;
        this.targetObject = target;
        this.targetMethod = handler;
    }

    // Override this method to perform any operations on the event
    // that comes with the various callbacks
    // See setFileHandler above for an example
    public boolean callTarget(Object appleEvent) throws InvocationTargetException, IllegalAccessException {
        Object result = targetMethod.invoke(targetObject, (Object[]) null);
        if (result == null) {
            return true;
        }
        return Boolean.valueOf(result.toString());
    }

    // InvocationHandler implementation
    // This is the entry point for our proxy object; it is called every time an ApplicationListener method is invoked
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isCorrectMethod(method, args)) {
            boolean handled = callTarget(args[0]);
            setApplicationEventHandled(args[0], handled);
        }
        // All of the ApplicationListener methods are void; return null regardless of what happens
        return null;
    }

    // Compare the method that was called to the intended method when the OSXAdapter instance was created
    // (e.g. handleAbout, handleQuit, handleOpenFile, etc.)
    protected boolean isCorrectMethod(Method method, Object[] args) {
        return (targetMethod != null && proxySignature.equals(method.getName()) && args.length == 1);
    }

    // It is important to mark the ApplicationEvent as handled and cancel the default behavior
    // This method checks for a boolean result from the proxy method and sets the event accordingly
    protected void setApplicationEventHandled(Object event, boolean handled) {
        if (event != null) {
            try {
                Method setHandledMethod = event.getClass().getDeclaredMethod("setHandled", new Class[]{boolean.class});
                // If the target method returns a boolean, use that as a hint
                setHandledMethod.invoke(event, handled);
            } catch (Exception ex) {
                Logger.getLogger(OSXAdapter.class.getName()).log(Level.SEVERE, "unexpected error handling ApplicationEvent", ex);
            }
        }
    }
}
